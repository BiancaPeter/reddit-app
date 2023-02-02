package com.reddit.app.openAI;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestionsService {
    @Autowired
    private OpenAiService openAiService;

    public void getCompletion() {

        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Give me a suitable title for a forum thread about cars")
                .model("text-davinci-003")
                .echo(true)
                .build();
        openAiService.createCompletion(completionRequest).getChoices().forEach(System.out::println);
    }
//TODO:de ce NullPointerException: Cannot invoke "com.theokanning.openai.OpenAiService.createCompletion(com.theokanning.openai.completion.CompletionRequest)" because "this.openAiService" is null
    //avem un bean pe care il injectam, nu ar trebui sa-mi dea aceasta exceptie
}