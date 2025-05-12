package com.raymond.service.chat;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

public interface ChatAssistantService {

    /**
     * 聊天
     *
     * @param message 消息
     * @return {@link Flux }<{@link String }>
     */
    Flux<String> chat(String message);
}
