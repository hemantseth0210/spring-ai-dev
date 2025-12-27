package dev.seth.springaidev.structuredoutput;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPlan {

    private ChatClient chatClient;

    public VacationPlan(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @GetMapping("/vacation/unstructured")
    public String unstructured(){
        return chatClient.prompt()
                .user("I want to plan a trip to Switzerland. Give me a list of things to do")
                .call()
                .content();
    }

    @GetMapping("/vacation/structured")
    public Itinerary structured(){
        return chatClient.prompt()
                .user("I want to plan a trip to Switzerland. Give me a list of things to do")
                .call()
                .entity(Itinerary.class);
    }
}
