package com.techconqueror.tool.codeskeletonhub.batch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techconqueror.tool.codeskeletonhub.entity.JobEntity;
import com.techconqueror.tool.codeskeletonhub.entity.JobStatus;
import com.techconqueror.tool.codeskeletonhub.resource.chatgpt.ChatCompletionRequest;
import com.techconqueror.tool.codeskeletonhub.resource.chatgpt.ChatCompletionResponse;
import com.techconqueror.tool.codeskeletonhub.resource.chatgpt.Message;
import com.techconqueror.tool.codeskeletonhub.service.ChatCompletionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class JobItemProcessor implements ItemProcessor<JobItem, JobEntity> {

    private static final Logger LOG = LoggerFactory.getLogger(JobItemProcessor.class);

    private final ChatCompletionService chatCompletionService;

    private final ObjectMapper objectMapper;

    @Override
    public JobEntity process(@NonNull final JobItem jobItem) {
        var jobStatus = JobStatus.SUCCESS;

        LOG.info("Processing job item {}", jobItem);

        ChatCompletionResponse completion = chatCompletionService.createCompletion(new ChatCompletionRequest("gpt-4o-mini", List.of(
                new Message("system", "You are a helpful assistant."),
                new Message("user", """
                        Please help extract the job responsibilities (tasks that the employee needs to perform if they take the job) from the following job description. Keep the responsibilities as they are in the description and return the result in a JSON array containing all the responsibilities as elements. Return the JSON only, without saying anything else:
                        "%s"
                        """.formatted(jobItem.description())
                )
        )));

        List<String> jobResponsibilities = parseAndPrintJson(completion.choices().getFirst().message().content());

        if (CollectionUtils.isEmpty(jobResponsibilities)) {
            jobStatus = JobStatus.ERROR;
        }

        return new JobEntity(
                jobItem.job_id(),
                jobItem.description(),
                jobResponsibilities,
                jobStatus
        );
    }


    private List<String> parseAndPrintJson(String response) {
        try {
            String cleanedResponse = response
                    .replaceAll("```json\\s*([\\s\\S]*?)\\s*```", "$1")
                    .trim();

            return objectMapper.readValue(cleanedResponse, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            LOG.error("Parsing error: {}", e.getMessage());
        }

        return Collections.emptyList();
    }
}
