package com.techconqueror.tool.codeskeletonhub.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techconqueror.tool.codeskeletonhub.service.ChatCompletionService;
import com.techconqueror.tool.codeskeletonhub.util.JsonMergePatchUtil;
import com.techconqueror.tool.codeskeletonhub.transformer.MapStructUserTransformer;
import com.techconqueror.tool.codeskeletonhub.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaAuditing
public class ApplicationConfiguration {

    public ApplicationConfiguration(ObjectMapper objectMapper) {
        JsonMergePatchUtil.setObjectMapper(objectMapper);
    }

    @Bean
    public UserTransformer userTransformer() {
        return MapStructUserTransformer.INSTANCE;
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {

            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                var attributes = super.getErrorAttributes(webRequest, options);
                var throwable = getError(webRequest);

                if (throwable == null) {
                    return attributes;
                }

                if (throwable instanceof BindingResult bindingResult) {
                    var errors = bindingResult.getAllErrors().stream()
                            .collect(
                                    HashMap::new,
                                    (map, error) -> map.put(((FieldError) error).getField(), error.getDefaultMessage()),
                                    HashMap::putAll
                            );
                    attributes.put("errors", errors);
                }

                return attributes;
            }
        };
    }

    @Bean
    public ChatCompletionService chatCompletionService(ConfigurableBeanFactory configurableBeanFactory, @Value("${app.chatgpt.api-key}") String apiKey) {
        RestClient restClient = RestClient.builder().requestInterceptor((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + apiKey);
            return execution.execute(request, body);
        }).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter)
                .embeddedValueResolver(configurableBeanFactory::resolveEmbeddedValue)
                .build();
        return factory.createClient(ChatCompletionService.class);
    }
}
