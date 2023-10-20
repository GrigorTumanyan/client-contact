package am.info.contact.repo;

import am.info.contact.model.Client;
import am.info.contact.model.Email;
import am.info.contact.model.Phone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query(value = "SELECT e.email_address FROM emails e WHERE e.client_id = :clientId", nativeQuery = true)
	List<Email> findEmailsByClientId(@Param("clientId") Long clientId);

	@Query(value = "SELECT p.phone_number FROM phones p WHERE p.client_id = :clientId", nativeQuery = true)
	List<Phone> findPhoneNumberByClientId(@Param("clientId") Long clientId);
}
