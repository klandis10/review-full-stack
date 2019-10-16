package reviewfullstack;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Long> {

	Optional<Topic> findById(Topic topic);

}
