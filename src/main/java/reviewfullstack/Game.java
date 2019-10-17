package reviewfullstack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Game {
	// Game = review

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String imageUrl;

	@Lob
	private String description;

	@ManyToMany
	private Collection<Topic> topics;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public Collection<Topic> getTopics() {
		return topics;
	}

	public Game() {
	}

	public Game(String name, String imageUrl, String description, Topic... topics) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.description = description;
		this.topics = new HashSet<>(Arrays.asList(topics));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
