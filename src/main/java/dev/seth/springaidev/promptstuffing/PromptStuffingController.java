package dev.seth.springaidev.promptstuffing;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromptStuffingController {

    private final ChatClient chatClient;

    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    Resource systemPrompt;

    public PromptStuffingController(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @GetMapping("/prompt-stuffing")
    public String promptStuffing(@RequestParam("message") String message){
        return chatClient.prompt()
                .system(systemPrompt)
                .user(message)
                .call()
                .content();
    }
}
