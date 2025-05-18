package likelion.team6th.fortune.service;

import org.springframework.stereotype.Service;

import likelion.team6th.fortune.dto.ZodiacAdminDTO;
import likelion.team6th.fortune.entity.Zodiac;
import likelion.team6th.fortune.repository.TarotRepository;
import likelion.team6th.fortune.repository.ZodiacRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZodiacService {
	
	public final ZodiacRepository zodiacRepository;
	
	public ZodiacAdminDTO registerZodiac(ZodiacAdminDTO zodiacAdminDTO) {
		
		Zodiac zodiac = Zodiac.of(
			zodiacAdminDTO.getContext1(),
			zodiacAdminDTO.getContext2(),
			zodiacAdminDTO.getContext3(),
			zodiacAdminDTO.getZodiacType(),
			zodiacAdminDTO.getImgLink()
			);
		
		Zodiac save = zodiacRepository.save(zodiac);
		
		ZodiacAdminDTO result = ZodiacAdminDTO.from(save);
		
		return result;
	}


	public ZodiacAdminDTO updateZodiac(Long id, ZodiacAdminDTO zodiacAdminDTO) {
		
		Zodiac zodiac = zodiacRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 ID의 십이지신이 없습니다: " + id));
		
		zodiac.setContext1(zodiacAdminDTO.getContext1());
		zodiac.setContext2(zodiacAdminDTO.getContext2());
		zodiac.setContext3(zodiacAdminDTO.getContext3());
		zodiac.setZodiacType(zodiacAdminDTO.getZodiacType());
		zodiac.setImgLink(zodiacAdminDTO.getImgLink());

		Zodiac update = zodiacRepository.save(zodiac);
	   
	    return ZodiacAdminDTO.from(update);
		
	}

	public boolean deleteZodiacById(Long id) {
        if (zodiacRepository.existsById(id)) {
        	zodiacRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
