package com.reddit.app;

import com.reddit.app.DTO.SubredditRequestDTO;
import com.reddit.app.mapper.SubredditMapper;
import com.reddit.app.model.Subreddit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
public class SubredditMapperTests {

//TODO: nu putem folosi adnotarea @ExtendWith({MockitoExtension.class}) pe clase abstracte;
// ce solutie avem in acest caz?
 //   private SubredditMapper subredditMapper;

    @Test
    void testMapStToSubreddit() {
//TODO: java.lang.NullPointerException: Cannot invoke "com.reddit.app.mapper.SubredditMapper.mapDtoToSubreddit(com.reddit.app.DTO.SubredditRequestDTO)"
// because "this.subredditMapper" is null
//        SubredditRequestDTO subredditRequestDTO = new SubredditRequestDTO("Subreddit1", "Description of Subreddit1");
//
//        subredditRequestDTO.setName("Subreddit1");
//        subredditRequestDTO.setDescription("Description of subreddit1");
//
//        Subreddit subredditMapperObject = subredditMapper.mapDtoToSubreddit(subredditRequestDTO);
//
//        assertEquals(subredditRequestDTO.getName(), subredditMapperObject.getName());
//        assertEquals(subredditRequestDTO.getDescription(), subredditMapperObject.getDescription());
    }
}
