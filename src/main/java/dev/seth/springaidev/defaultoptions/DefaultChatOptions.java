package dev.seth.springaidev.defaultoptions;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultChatOptions {

    private final ChatClient chatClient;

    public DefaultChatOptions(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @GetMapping("/default-option")
    public String messageRoles(@RequestParam("message") String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
