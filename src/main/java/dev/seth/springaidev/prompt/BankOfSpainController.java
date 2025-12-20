package dev.seth.springaidev.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankofspain")
public class BankOfSpainController {

    private final ChatClient chatClient;

    public BankOfSpainController(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message){

        var systemMessage = """
                You are a customer service assistant for Bank of Spain.
                You can ONLY discuss:
                - Account balances and transactions
                - Branch Locations and Hours
                - General Banking Services
                
                If asked about anything else, respond: "Sorry, I can only help with banking-related questions."  
                """;

        return chatClient.prompt()
                .system(systemMessage)
                .user(message)
                .call()
                .content();
    }
}
