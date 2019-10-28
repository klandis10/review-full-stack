package reviewfullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerMockMVCTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private GameRepository gameRepo;

	@MockBean
	private TopicRepository topicRepo;

	@Mock
	private Game game;

	@Mock
	private Game anotherGame;

	@Mock
	private Topic topic;

	@Mock
	private Topic anotherTopic;

	@Test
	public void shouldRouteToSingleGameView() throws Exception {
		long arbitraryGameId = 1;
		when(gameRepo.findById(arbitraryGameId)).thenReturn(Optional.of(game));
		mvc.perform(get("/single-game?id=1")).andExpect(view().name(is("game")));
	}

	@Test
	public void shouldBeOkForSingleGame() throws Exception {
		long arbitraryGameId = 1;
		when(gameRepo.findById(arbitraryGameId)).thenReturn(Optional.of(game));
		this.mvc.perform(get("/single-game?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldBeNotOkForSingleGame() throws Exception {
		long arbitraryGameId = 0;
		when(gameRepo.findById(arbitraryGameId)).thenReturn(Optional.of(game));
		this.mvc.perform(get("/single-game?id=2")).andExpect(status().isNotFound());

	}

	@Test
	public void shouldPutSingleGameIntoModel() throws Exception {
		when(gameRepo.findById(1L)).thenReturn(Optional.of(game));
		this.mvc.perform(get("/single-game?id=1")).andExpect(model().attribute("game", is(game)));
	}

	@Test
	public void shouldRouteToAllGames() throws Exception {
		this.mvc.perform(get("/all-games")).andExpect(view().name(is("games")));

	}

	@Test
	public void shouldBeOkForAllGames() throws Exception {
		this.mvc.perform(get("/all-games")).andExpect(status().isOk());
	}

	@Test
	public void shouldPutAllGamesIntoModel() throws Exception {
		Collection<Game> allGames = Arrays.asList(game, anotherGame);
		when(gameRepo.findAll()).thenReturn(allGames);
		this.mvc.perform(get("/all-games")).andExpect(model().attribute("games", is(allGames)));
	}

	@Test
	public void shouldRouteToSingleTopic() throws Exception {
		long arbitraryTopicId = 1;
		when(topicRepo.findById(arbitraryTopicId)).thenReturn(Optional.of(topic));
		this.mvc.perform(get("/topic?id=1")).andExpect(view().name(is("topic")));
	}

	@Test
	public void shouldBeOkForSingleTopic() throws Exception {
		long arbitraryTopicId = 1;
		when(topicRepo.findById(arbitraryTopicId)).thenReturn(Optional.of(topic));
		this.mvc.perform(get("/topic?id=1")).andExpect(status().isOk());

	}

//	@Test
//	public void shouldBeNotOkForSingleTopic() throws Exception {
//		long arbitraryTopicId = 1;
//		when(topicRepo.findById(arbitraryTopicId)).thenReturn(Optional.of(topic));
//		this.mvc.perform(get("/topic?id=2")).andExpect(status().isNotFound());
//	}

	@Test
	public void shouldPutSingleTopicIntoModel() throws Exception {
		when(topicRepo.findById(1L)).thenReturn(Optional.of(topic));
		this.mvc.perform(get("/topic?id=1")).andExpect(model().attribute("topics", is(topic)));
	}

//	@Test
//	public void shouldBeOkForAllTopics() throws Exception {
//		this.mvc.perform(get("/all-topics")).andExpect(status().isOk());
//	}

//	@Test
//	public void shouldRouteToAllTopicsView() throws Exception {
//		this.mvc.perform(get("/all-topics")).andExpect(view().name(is("topics")));
//
//	}

//	@Test
//	public void shouldPutAllTopicsIntoModel() throws Exception {
//		Collection<Topic> allTopics = Arrays.asList(topic, anotherTopic);
//		when(topicRepo.findAll()).thenReturn(allTopics);
//		this.mvc.perform(get("/all-topics")).andExpect(model().attribute("topics", is(allTopics)));
//	}

}
