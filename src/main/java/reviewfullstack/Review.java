package reviewfullstack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Review {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String imageUrl;
	
	@ManyToMany(mappedBy = "reviews")
	private Collection<Genre> genres;
	
	public Collection<Genre> getGenres() {
		return genres;
	}
	
	@Lob
	private String description;
	
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
	public Review() {
	}

	public Review(String name, String imageUrl, String description) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.description = description;
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
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
