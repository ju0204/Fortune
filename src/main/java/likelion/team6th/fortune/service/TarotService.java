package likelion.team6th.fortune.service;

import org.springframework.stereotype.Service;
import likelion.team6th.fortune.dto.TarotDTO;
import likelion.team6th.fortune.entity.Tarot;
import likelion.team6th.fortune.repository.TarotRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TarotService {
	
	private final TarotRepository tarotRepository;
	
	
	public TarotDTO registerTarots(TarotDTO tarotdto) {
		
		Tarot tarot = Tarot.of(
			tarotdto.getContextLove(),
			tarotdto.getContextCareer(),
			tarotdto.getContextMoney(),
			tarotdto.getContextHealth(),
			tarotdto.getImgLink()
			);
		
		Tarot save = tarotRepository.save(tarot);
		
		TarotDTO result = TarotDTO.from(save);
		
		return result;
	}


	public TarotDTO updateTarot(Long id, TarotDTO tarotDTO) {
		
		Tarot tarot = tarotRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 ID의 타로가 없습니다: " + id));
		
		tarot.setContextLove(tarotDTO.getContextLove());
		tarot.setContextCareer(tarotDTO.getContextCareer());
		tarot.setContextHealth(tarotDTO.getContextHealth());
		tarot.setContextMoney(tarotDTO.getContextMoney());
		tarot.setImgLink(tarotDTO.getImgLink());
		

	    Tarot update = tarotRepository.save(tarot);
	   
	    return TarotDTO.from(update);
		
	}
	
	//수정페이지로 이동시 찾을 id
	public TarotDTO findById(Long id) {
	    Tarot tarot = tarotRepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("해당 타로가 없습니다: " + id));

	    return TarotDTO.from(tarot);
	}


	public boolean deleteTarot(Long id) {
		if (tarotRepository.existsById(id)) {
			tarotRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
	}
}
