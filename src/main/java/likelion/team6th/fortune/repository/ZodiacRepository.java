package likelion.team6th.fortune.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import likelion.team6th.fortune.entity.Tarot;
import likelion.team6th.fortune.entity.Zodiac;

@Repository
public interface ZodiacRepository extends JpaRepository<Zodiac, Long> {
	
	@Query("SELECT z.id FROM Zodiac z")
	List<Long> findAllIds();
	
	@Query("SELECT z FROM Zodiac z WHERE z.zodiacType = :zodiacType")
	List<Zodiac> getReferencesByZodiacType(@Param("zodiacType") String zodiacType); 
}
