package dev.seth.springaidev.multimodal.audio;

import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.audio.tts.TextToSpeechResponse;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AudioGeneration {

    private final OpenAiAudioSpeechModel openAiAudioSpeechModel;

    public AudioGeneration(OpenAiAudioSpeechModel openAiAudioSpeechModel){
        this.openAiAudioSpeechModel = openAiAudioSpeechModel;
    }

    @GetMapping("/generate-audio")
    public ResponseEntity<byte[]> generateAudio(
            @RequestParam(
                    defaultValue = "It's great time to invest in S&P because of the US inflation and high employment rate")
            String prompt
    ){
        OpenAiAudioSpeechOptions options = OpenAiAudioSpeechOptions.builder()
                .model("tts-1-hd")
                .voice(OpenAiAudioApi.SpeechRequest.Voice.ALLOY)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .speed(1.0)
                .build();

        TextToSpeechPrompt textToSpeechPrompt = new TextToSpeechPrompt(prompt, options);
        TextToSpeechResponse textToSpeechResponse = openAiAudioSpeechModel.call(textToSpeechPrompt);
        byte[] speechBytes = textToSpeechResponse.getResult().getOutput();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\speechaigenerate.mp3\"")
                .body(speechBytes);
    }
}
