package reviewfullstack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Genre {

	@Id
	@GeneratedValue
	private long id;
	
	
	@ManyToMany
	private Collection<Review> reviews;
	private String type;
	
	public Collection<Review> getReviews() {
		return reviews;
	}
	public Genre() {
	}
	public Genre(String type, Review...reviews) {
		this.type = type;
		this.reviews = new HashSet<>(Arrays.asList(reviews));
	}
	
	public long getId() {
		return id;
	}
	public Object getType() {
		return type;
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
		Genre other = (Genre) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
