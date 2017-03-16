
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select a from Application a where a.post.id = ?1")
	Collection<Application> findAllByPost(Integer postId);

	@Query("select a from Application a where a.customer.id = ?1")
	Collection<Application> findAllByCustomer(Integer customerId);

	@Query("select a from Application a where a.post.customer.id = ?1")
	Collection<Application> findAllReceived(Integer customerId);
}
