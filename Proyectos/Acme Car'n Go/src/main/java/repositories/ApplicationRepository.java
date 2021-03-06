
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.Customer;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select a from Application a where a.post.id = ?1")
	Collection<Application> findAllByPost(Integer postId);

	@Query("select a from Application a where a.customer.id = ?1")
	Collection<Application> findAllByCustomer(Integer customerId);

	@Query("select a from Application a where a.post.customer.id = ?1")
	Collection<Application> findAllReceived(Integer customerId);

	@Query("select count(a)*1.0/(select count(p)*1.0 from Post p) from Application a")
	Double avgApplicationsPerPost();

	@Query("select a.customer from Application a where a.status = 'ACCEPTED' group by a.customer order by count(a) DESC")
	Collection<Customer> customersWithMoreAccepted();

	@Query("select a.customer from Application a where a.status = 'DENIED' group by a.customer order by count(a) DESC")
	Collection<Customer> customersWithMoreDenied();
}
