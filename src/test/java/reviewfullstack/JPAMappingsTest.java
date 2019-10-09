package reviewfullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

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
	private GenreRepository genreRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Test
	public void shouldSaveAndLoadGenre() {
		Genre genre = genreRepo.save(new Genre("Action Adventure"));
		long genreId = genre.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Genre> result = genreRepo.findById(genreId);
		genre = result.get();
		assertThat(genre.getType(), is("Action Adventure"));
	}
	
	@Test
	public void shouldGenerateGenreId() {
		Genre genre = genreRepo.save(new Genre("Action Adventure"));
		long genreId = genre.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(genreId, is(greaterThan(0L)));	
	}
	
	@Test
	public void shouldSaveAndLoadReview() {
		Review review = new Review("review1", "imageUrl", "description");
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getName(), is("review1"));
	}
	@Test
	public void shouldGenerateReviewId() {
		Review review = reviewRepo.save(new Review("Name", "imageUrl", "description"));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(reviewId, is(greaterThan(0L)));
		
	}
	@Test //Genre = Topic, Review = Course
	public void shouldEstablishReviewToGenreRelationship() {
		Review tombRaider  = reviewRepo.save(new Review("Tomb Raider", "Tomb Raider is a third-person perspective survival game developed by Crystal Dynamics and published by Square Enix.", "/static/images/TR.jpg"));
		Review re2 = reviewRepo.save(new Review("Resident Evil 2", "Resident Evil 2 is a third-person perspective survival game developed by Capcom.", "/static/images/RE2.jpg"));
		Review bioshock = reviewRepo.save(new Review("Bioshock", "Bioshock is a first-person shooter game developed by 2K Boston and released by 2K Games.","/static/images/Bio.jpg"));
		
		Genre type = new Genre("Action Adventure");
		type = genreRepo.save(type);
		long genreId = type.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Genre> result = genreRepo.findById(genreId);
		type = result.get();
		
		assertThat(type.getReviews(), containsInAnyOrder(tombRaider, re2, bioshock));
	}
	
//Keeps Crashing my project
//	@Test
//	public void shouldFindReviewsForGenre() {
//		Genre actionAdventure = genreRepo.save(new Genre("Action Adventure"));
//		Genre survivalHorror = genreRepo.save(new Genre("Survival Horror"));
//		
//		Review tombRaider = reviewRepo.save(new Review("Tomb Raider","static/images/TR.jpg","description"));
//		Review re2 = reviewRepo.save(new Review("Resident Evil 2", "static/images/RE2.jpg", "description"));
//		Review bioshock = reviewRepo.save(new Review("Bioshock", "static/images/Bio.jpg", "description"));
//		
//		entityManager.flush();
//		entityManager.clear();
//		
//		Collection<Review> reviewsForGenre = reviewRepo.findByGenresContains(actionAdventure, survivalHorror);
//		
//		assertThat(reviewsForGenre, containsInAnyOrder(tombRaider, re2, bioshock));
//	}

}
