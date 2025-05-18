package likelion.team6th.fortune.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import likelion.team6th.fortune.entity.Tarot;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface TarotRepository extends JpaRepository<Tarot, Long> {
	
	@Query("SELECT t.id FROM Tarot t")
	List<Long> findAllIds(); 

}
