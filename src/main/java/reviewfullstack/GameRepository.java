package reviewfullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {

	Game tombRaider = new Game("Tomb Raider", "/resources/static/images/TR.jpg", "Tomb Raider is a third-person perspective survival game developed by Crystal Dynamics and published by Square Enix.");
	Game re2 = new Game("Resident Evil 2", "/resources/static/images/RE2.jpg", "Resident Evil 2 is a third-person perspective survival game developed by Capcom.");
	Game bioshock = new Game("Bioshock", "/resources/static/images/Bio.jpg", "Bioshock is a first-person shooter game developed by 2K Boston and released by 2K Games.");
	
	Collection<Game> findByTopicsContains(Topic topic);
	
	Collection<Game> findByTopicsId(Long id);
}
