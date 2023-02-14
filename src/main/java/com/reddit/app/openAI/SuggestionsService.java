package com.reddit.app.openAI;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionsService {
    @Autowired
    private OpenAiService openAiService;

    public List<String> getCompletion() {

        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Give me a suitable title for a forum thread about cars")
                .model("text-davinci-003")
                .echo(true)
                .build();
        return openAiService.createCompletion(completionRequest).getChoices().stream().map(choice -> choice.getText()).collect(Collectors.toList());

    }
}