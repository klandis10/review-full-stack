package reviewfullstack;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class GameControllerTest {

	@InjectMocks
	private GameController underTest;

	@Mock
	private Game game;

	@Mock
	private Topic topic;

	@Mock
	private Topic anotherTopic;

	@Mock
	private Game anotherGame;

	@Mock
	private GameRepository gameRepo;

	@Mock
	private TopicRepository topicRepo;

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddSingleGameToModel() throws GameNotFoundException {
		long arbitraryGameId = 1;
		when(gameRepo.findById(arbitraryGameId)).thenReturn(Optional.of(game));

		underTest.findOneGame(arbitraryGameId, model);
		verify(model).addAttribute("game", game);

	}

	@Test
	public void shouldAddAllGamesToModel() {
		Collection<Game> allGames = Arrays.asList(game, anotherGame);
		when(gameRepo.findAll()).thenReturn(allGames);

		underTest.findAllGames(model);
		verify(model).addAttribute("games", allGames);
	}

	@Test
	public void shouldAddSingleTopicToModel() throws TopicNotFoundException {
		long arbitraryTopicId = 1;
		when(topicRepo.findById(arbitraryTopicId)).thenReturn(Optional.of(topic));

		underTest.findOneTopic(arbitraryTopicId, model);
		verify(model).addAttribute("topics", topic);
	}

	@Test
	public void shouldAddAllTopicsToModel() {
		Collection<Topic> allTopics = Arrays.asList(topic, anotherTopic);
		when(topicRepo.findAll()).thenReturn(allTopics);

		underTest.findAllTopics(model);
		verify(model).addAttribute("topics", allTopics);
	}

}
