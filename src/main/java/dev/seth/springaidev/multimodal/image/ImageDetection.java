package dev.seth.springaidev.multimodal.image;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageDetection {

    private final ChatClient chatClient;

    @Value("classpath:/images/sincerely-media-2UlZpdNzn2w-unsplash.jpg")
    Resource sampleImage;

    public ImageDetection(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @GetMapping("/image-to-text")
    public String imageToText(){
        return chatClient.prompt()
                .user(promptUserSpec -> {
                    promptUserSpec.text("Can you please describe what you see in the following image?");
                    promptUserSpec.media(MimeTypeUtils.IMAGE_JPEG, sampleImage);
                })
                .call()
                .content();
    }
}
