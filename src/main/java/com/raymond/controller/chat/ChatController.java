package com.raymond.controller.chat;

import com.raymond.service.chat.ChatAssistantService;
import com.raymond.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Flux;

/**
 * 用于处理与通义模型的交互请求测试。
 */
@RestController
public class ChatController {


    @Autowired
    ChatAssistantService chatAssistantService;

    /**
     * 聊天
     *
     * @param message 内容
     * @return
     */
    @GetMapping("/api/ai/generate")
    public Flux<String> chat(@RequestParam("message") String message) {
        Flux<String> chat = chatAssistantService.chat(message);
        return chat;
    }
}
