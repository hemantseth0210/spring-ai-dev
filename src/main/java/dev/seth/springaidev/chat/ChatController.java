package dev.seth.springaidev.chat;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public String chat(){
        return chatClient.prompt()
                .user("Tell me an interesting fact about Europe")
                .call()
                .content();
    }

    @GetMapping("/stream")
    public Flux<String> chatStream(){
        return chatClient.prompt()
                .user("Tell me 100 best places to see in Europe")
                .stream()
                .content();
    }

    @GetMapping("/joke")
    public ChatResponse chatResponse(){
        return chatClient.prompt()
                .user("Tell me a joke about Europe")
                .call()
                .chatResponse();
    }

}
