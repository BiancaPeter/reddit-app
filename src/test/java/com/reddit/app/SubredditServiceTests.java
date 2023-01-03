package com.reddit.app;

import com.reddit.app.DTO.SubredditRequestDTO;
import com.reddit.app.mapper.SubredditMapper;
import com.reddit.app.model.Subreddit;
import com.reddit.app.repository.SubredditRepository;
import com.reddit.app.service.SubredditService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class SubredditServiceTests {

	@InjectMocks
	private SubredditService subredditService;

	@Mock
	private SubredditRepository subredditRepository;

	@Mock
	private SubredditMapper subredditMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void testAddSubreddit(){
		//given
		SubredditRequestDTO subredditRequestDTO = new SubredditRequestDTO("Subreddit1", "Description of Subreddit1");

		//when
		Subreddit subredditMapperObject = new Subreddit(1L,"Subreddit1","Description of subreddit1", LocalDateTime.now(), null);
		//am mock-uit dependinta SubredditMapper subredditMapper pt a crea obiectul Subreddit subredditMapperObject
		when(subredditMapper.mapDtoToSubreddit(any())).thenReturn(subredditMapperObject);

		//am mock-uit dependinta SubredditRepository subredditRepository pt a simula salvarea obiectului Subreddit subredditMapperObject
		when(subredditRepository.save(any())).thenReturn(subredditMapperObject);

		Subreddit result = subredditService.addSubreddit(subredditRequestDTO);

		//then
		assertEquals("Subreddit1",result.getName());
	}

//	@Test
//	void testGetAllSubreddits(){
//		//given
//		SubredditRequestDTO subredditRequestDTO1 = new SubredditRequestDTO("Subreddit1", "Description of Subreddit1");
//		SubredditRequestDTO subredditRequestDTO2 = new SubredditRequestDTO("Subreddit2", "Description of Subreddit2");
//		SubredditRequestDTO subredditRequestDTO3 = new SubredditRequestDTO("Subreddit3", "Description of Subreddit3");
//		List<SubredditRequestDTO>subredditRequestDTOList= Arrays.asList(subredditRequestDTO1, subredditRequestDTO2,subredditRequestDTO3);
//
//		//when
//		//TODO:??????????????
//	}
//
//	@Test
//	void testGetSubreddit(){
//		//given
//		SubredditRequestDTO subredditRequestDTO = new SubredditRequestDTO("Subreddit1", "Description of Subreddit1");
//		//TODO: SubredditResponseDTO
//		//when
//		when(subredditRepository.findById(any())).thenReturn(Optional.of(new Subreddit()));
//	}


}
