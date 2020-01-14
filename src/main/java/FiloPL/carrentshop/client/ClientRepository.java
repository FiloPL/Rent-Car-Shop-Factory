package FiloPL.carrentshop.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Set<Client> findByNameContainsIgnoreCase(String name);
    Set<Client> findByAddressContainsIgnoreCase(String address);
    Set<Client> findByTelephoneNrContainsIgnoreCase(String telephoneNr);
}
