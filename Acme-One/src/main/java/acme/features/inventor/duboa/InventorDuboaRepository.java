package acme.features.inventor.duboa;
//package acme.features.inventor.duboa;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Configuration;
import acme.entities.Duboa;
import acme.entities.Item;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorDuboaRepository extends AbstractRepository {

	@Query("SELECT userAccount FROM UserAccount userAccount WHERE userAccount.id = :id")
	UserAccount findOneUserAccountById(int id);
	
	@Query("SELECT inventor FROM Inventor inventor WHERE inventor.id = :id")
	Inventor findOneInventorById(int id);
	
	@Query("SELECT inventor FROM Inventor inventor WHERE inventor.userAccount.id = :id")
	Inventor findOneInventorByAccountId(int id);
	
	@Query("SELECT c FROM Duboa c WHERE c.id = :id")
	Duboa findOneDuboaById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findOneItemById(int id);
	
	@Query("SELECT c FROM Duboa c")
	Collection<Duboa> findAllDuboas();
	
	@Query("SELECT c FROM Duboa c where c.item.inventor.id = :id")
	Collection<Duboa> findAllDuboasByInventorId(int id);
	
	@Query("select configuration from Configuration configuration")
	Configuration findConfiguration();
}