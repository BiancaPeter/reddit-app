package com.reddit.app.openAI;

public class Main {
    public static void main(String... args) {
        SuggestionsService service = new SuggestionsService();
        service.getCompletion();
    }
}
