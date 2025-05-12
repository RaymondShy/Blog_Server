package com.raymond.config;

import com.raymond.service.chat.ChatAssistantService;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration(proxyBeanMethods = false)
public class LLMConfig {
    /**
     * 流返回
     * @return
     */
    @Bean
    public StreamingChatLanguageModel streamingChatLanguageModel(){
        return OpenAiStreamingChatModel.builder()
                .apiKey("xxx")
                .modelName("qwen-plus")
                .logRequests(true)
                .logResponses(true)
                .timeout(Duration.ofSeconds(10))
                .baseUrl("xxx")
                .build();
    }


    @Bean
    public ChatAssistantService chatAssistant(StreamingChatLanguageModel streamingChatLanguageModel){
        return AiServices.create(ChatAssistantService.class, streamingChatLanguageModel);
    }

}
