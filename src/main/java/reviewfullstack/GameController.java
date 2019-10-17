package reviewfullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

	@Resource
	GameRepository gameRepo;

	@Resource
	TopicRepository topicRepo;

	@RequestMapping("/single-game?id=1")
	public String findOneGame(@RequestParam(value = "id") long id, Model model) throws GameNotFoundException {
		Optional<Game> game = gameRepo.findById(id);

		if (game.isPresent()) {
			model.addAttribute("games", game.get());
			return "game";

		}
		throw new GameNotFoundException();
	}

	@RequestMapping("/all-games")
	public String findAllGames(Model model) {
		model.addAttribute("games", gameRepo.findAll());
		return ("games");

	}

	public String findOneTopic(@RequestParam(value = "id") long id, Model model) throws TopicNotFoundException {
		Optional<Topic> topic = topicRepo.findById(id);

		if (topic.isPresent()) {
			model.addAttribute("topics", topic.get());
			model.addAttribute("games", gameRepo.findByTopicsContains(topic.get()));
			return "topic";
		}
		throw new TopicNotFoundException();

	}

	@RequestMapping("/all-topics")
	public String findAllTopics(Model model) {
		model.addAttribute("topics", topicRepo.findAll());
		return ("topics");

	}

}
