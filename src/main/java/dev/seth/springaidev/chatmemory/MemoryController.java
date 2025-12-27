package dev.seth.springaidev.chatmemory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
public class MemoryController {

    private final ChatClient chatClient;

    public MemoryController(@Qualifier("chatMemoryChatClient") ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @GetMapping("/chat-memory")
    public String memory(@RequestHeader("username") String username, @RequestParam String message){
        return chatClient.prompt()
                .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID, username))
                .user(message)
                .call()
                .content();
    }
}
