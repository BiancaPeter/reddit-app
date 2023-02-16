package com.reddit.app.service;

import com.reddit.app.DTO.SubredditRequestDTO;
import com.reddit.app.DTO.SubredditResponseDTO;
import com.reddit.app.mapper.SubredditMapper;
import com.reddit.app.model.Subreddit;
import com.reddit.app.repository.SubredditRepository;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubredditService {
    private SubredditRepository subredditRepository;

    private SubredditMapper subredditMapper;

    private OpenAiService openAiService;

    @Autowired
    public SubredditService(SubredditRepository subredditRepository, SubredditMapper subredditMapper, OpenAiService openAiService) {
        this.subredditRepository = subredditRepository;
        this.subredditMapper = subredditMapper;
        this.openAiService = openAiService;
    }

    public Subreddit addSubreddit(SubredditRequestDTO subredditRequestDTO) {
        return subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditRequestDTO));

    }

    public List<SubredditResponseDTO> getAllSubreddits() {
        return subredditRepository.findAll().stream()
                .map(subreddit -> subredditMapper.mapSubredditToDto(subreddit))
                .collect(Collectors.toList());
    }

    public SubredditResponseDTO getSubreddit(Long id) {
        Subreddit foundSubreddit = subredditRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the subreddit was not found"));
        return subredditMapper.mapSubredditToDto(foundSubreddit);
    }

    public void deleteSubreddit(Long id) {
        Subreddit foundSubreddit = subredditRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the subreddit was not found"));
        subredditRepository.delete(foundSubreddit);
    }


    public Subreddit findSubreddit(Long id) {
        return subredditRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the subreddit was not found"));
    }

    public List<String> getCompletion(String about) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Give me a suitable title for a forum thread about " + about)
                .model("text-davinci-003")
                .echo(true)
                .build();
        return openAiService.createCompletion(completionRequest).getChoices().stream().map(choice -> choice.getText()).collect(Collectors.toList());
    }
}
