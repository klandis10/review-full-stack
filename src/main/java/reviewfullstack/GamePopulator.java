package reviewfullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GamePopulator {
	private Logger log = LoggerFactory.getLogger(GamePopulator.class);

	@Resource
	private GameRepository repository;
	
	public void run(String... args) throws Exception {
		repository.save(new Game("Tomb Raider","/static/images/TR.jpg","description"));
		repository.save(new Game("Resident Evil 2","/static/images/RE2.jpg","description"));
		repository.save(new Game("Bioshock","/static/images/Bio.jpg","description"));

		// fetch all games
		log.info("game found with findAll():");
		log.info("-------------------------------");
		for (Game game : repository.findAll()) {
			log.info(game.toString());
		}
		log.info("");

		// fetch an individual game by ID
		Optional<Game> game = repository.findById(1L);
		log.info("Game found with findOne(1L):");
		log.info("--------------------------------");
		log.info(game.toString());
		log.info("");

	}
}
