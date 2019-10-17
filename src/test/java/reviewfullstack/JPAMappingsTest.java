package reviewfullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private TopicRepository topicRepo;

	@Resource
	private GameRepository gameRepo;

	@Test
	public void shouldSaveAndLoadTopic() {
		Topic topic = topicRepo.save(new Topic("topic"));
		long topicId = topic.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Topic> result = topicRepo.findById(topicId);
		topic = result.get();
		assertThat(topic.getName(), is("topic"));
	}

	@Test
	public void shouldGenerateTopicId() {
		Topic topic = topicRepo.save(new Topic("topic"));
		long topicId = topic.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(topicId, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadGame() {
		Game game = new Game("game name", "imageUrl", "description");
		game = gameRepo.save(game);
		long gameId = game.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Game> result = gameRepo.findById(gameId);
		game = result.get();
		assertThat(game.getName(), is("game name"));
	}

	@Test
	public void shouldEstablishGameToTopicsRelationships() {
		// topic is not the owner so we create these first
		Topic action = topicRepo.save(new Topic("Action"));
		Topic adventure = topicRepo.save(new Topic("Adventure"));

		Game tombRaider = new Game("Tomb Raider", "/static/images/TR.jpg", "description", action, adventure);
		tombRaider = gameRepo.save(tombRaider);
		long gameId = tombRaider.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Game> result = gameRepo.findById(gameId);
		tombRaider = result.get();

		assertThat(tombRaider.getTopics(), containsInAnyOrder(action, adventure));

	}

	@Test
	public void shouldFindGamesForTopic() {
		Topic horror = topicRepo.save(new Topic("Horror"));

		Game re2 = gameRepo.save(new Game("Resident Evil 2", "/static/images/RE2.jpg", "description", horror));
		Game bioshock = gameRepo.save(new Game("Bioshock", "/static/images/Bio.jpg", "description", horror));

		entityManager.flush();
		entityManager.clear();

		Collection<Game> gamesForTopic = gameRepo.findByTopicsContains(horror);

		assertThat(gamesForTopic, containsInAnyOrder(re2, bioshock));
	}

	@Test
	public void shouldFindGamesForTopicsId() {
		Topic action = topicRepo.save(new Topic("Action"));
		long topicId = action.getId();

		Game tombRaider = gameRepo.save(new Game("Tomb Raider", "/static/images/TR.jpg", "description", action));

		entityManager.flush();
		entityManager.clear();

		Collection<Game> gamesForTopic = gameRepo.findByTopicsId(topicId);

		assertThat(gamesForTopic, containsInAnyOrder(tombRaider));
	}

}