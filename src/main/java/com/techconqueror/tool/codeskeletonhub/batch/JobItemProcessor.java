package com.techconqueror.tool.codeskeletonhub.batch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techconqueror.tool.codeskeletonhub.entity.JobEntity;
import com.techconqueror.tool.codeskeletonhub.resource.chatgpt.ChatCompletionRequest;
import com.techconqueror.tool.codeskeletonhub.resource.chatgpt.ChatCompletionResponse;
import com.techconqueror.tool.codeskeletonhub.resource.chatgpt.Message;
import com.techconqueror.tool.codeskeletonhub.service.ChatCompletionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

@AllArgsConstructor
public class JobItemProcessor implements ItemProcessor<JobItem, JobEntity> {

    private static final Logger log = LoggerFactory.getLogger(JobItemProcessor.class);

    private final ChatCompletionService chatCompletionService;

    private final ObjectMapper objectMapper;

    @Override
    public JobEntity process(final JobItem jobItem) {
        log.info("Processing job item {}", jobItem);
        ChatCompletionResponse completion = chatCompletionService.createCompletion(new ChatCompletionRequest("gpt-4o-mini", List.of(
                new Message("system", "You are a helpful assistant."),
                new Message("user", """
                            Please help extract the job responsibilities (tasks that the employee needs to perform if they take the job) from the following job description. Keep the responsibilities as they are in the description, and return the result in a JSON array containing all responsibilities as elements:
                            "%s"
                            """.formatted(jobItem.description())
                )
        )));

        List<String> jobResponsibilities = parseAndPrintJson(completion.choices().getFirst().message().content());

        return new JobEntity(
                jobItem.job_id(),
                jobItem.description(),
                jobResponsibilities
        );
    }



    private List<String> parseAndPrintJson(String response) {
        try {
            String cleanedResponse = response
                    .replaceAll("```json\\s*([\\s\\S]*?)\\s*```", "$1")
                    .trim();

            return objectMapper.readValue(cleanedResponse, new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            System.err.println("Parsing error: " + e.getMessage());
        }

        return null;
    }
}
