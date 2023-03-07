package com.reddit.app;

import com.reddit.app.DTO.SubredditRequestDTO;
import com.reddit.app.DTO.SubredditResponseDTO;
import com.reddit.app.mapper.SubredditMapper;
import com.reddit.app.model.Subreddit;
import com.reddit.app.repository.SubredditRepository;
import com.reddit.app.service.SubredditService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class SubredditServiceTests {

    @InjectMocks
    private SubredditService subredditService;

    @Mock
    private SubredditRepository subredditRepository;

    @Mock
    private SubredditMapper subredditMapper;

    @Test
    void testAddSubreddit_ShouldReturnAddedSubreddit() {
        //given
        SubredditRequestDTO subredditRequestDTO = new SubredditRequestDTO("Subreddit1", "Description of Subreddit1");

        //when
        Subreddit subredditMapperObject = new Subreddit(1L, "Subreddit1", "Description of subreddit1", LocalDateTime.now(), null);
        when(subredditMapper.mapDtoToSubreddit(any())).thenReturn(subredditMapperObject);
        when(subredditRepository.save(any())).thenReturn(subredditMapperObject);

        Subreddit result = subredditService.addSubreddit(subredditRequestDTO);

        //then
        assertEquals("Subreddit1", result.getName());
    }

    @Test
    void testGetAllSubreddits_ShouldReturnAllSubreddits() {
        //given
        Subreddit subreddit1 = new Subreddit(1l, "name1", "adsfadsf", null, null);
        Subreddit subreddit2 = new Subreddit(2l, "name2", "adsfadsf", null, null);
        //when
        when(subredditRepository.findAll()).thenReturn(Arrays.asList(subreddit1, subreddit2));
        when(subredditMapper.mapSubredditToDto(subreddit1)).thenReturn(new SubredditResponseDTO("name1", null, null, null));
        when(subredditMapper.mapSubredditToDto(subreddit2)).thenReturn(new SubredditResponseDTO("name2", null, null, null));

        List<SubredditResponseDTO> result = subredditService.getAllSubreddits();
        //then
        assertEquals("name1", result.get(0).getName());
        assertEquals("name2", result.get(1).getName());
    }

    @Test
    void testGetSubredditById_ShouldReturnSubredditById() {
        //given
        Subreddit subreddit1 = new Subreddit(1l, "name1", "adsfadsf", null, null);
        //when
        when(subredditRepository.findById(any())).thenReturn(Optional.of(subreddit1));
        when(subredditMapper.mapSubredditToDto(subreddit1)).thenReturn(new SubredditResponseDTO("name1", null, null, null));

        SubredditResponseDTO result = subredditService.getSubreddit(1l);
        //then
        assertEquals("name1", result.getName());
    }
//TODO: nu merge!

    @Test
    void testGetSubredditById_ShouldReturnTrowException() throws ResponseStatusException {
        //given
        //when
        when(subredditRepository.findById(any())).thenReturn(Optional.empty());
        //then
        assertThrows(ResponseStatusException.class, () -> subredditService.getSubreddit(3L));
    }

    @Test
    void testDeleteSubredditById_ShouldThrowExceptionIfSubredditNotFound() {
        //given
        when(subredditRepository.findById(any())).thenReturn(Optional.empty());
        //then
        assertThrows(ResponseStatusException.class, () -> subredditService.deleteSubreddit(3L));
    }
}

