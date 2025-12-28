package dev.seth.springaidev.mcp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MCPClientController {

    private  final ChatClient chatClient;

    public MCPClientController(ChatClient.Builder chatClientBuilder, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(toolCallbackProvider)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @GetMapping("/mcp-chat-client")
    public String chat(@RequestHeader(value = "username",required = false) String username,
                       @RequestParam("message") String message) {
        return chatClient.prompt().user(message+ " My username is " + username)
                .call().content();
    }
}
