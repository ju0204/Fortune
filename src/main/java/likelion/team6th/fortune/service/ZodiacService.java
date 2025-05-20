package likelion.team6th.fortune.service;

import org.springframework.stereotype.Service;

import likelion.team6th.fortune.dto.ZodiacAdminDTO;
import likelion.team6th.fortune.dto.ZodiacDTO;
import likelion.team6th.fortune.entity.Zodiac;
import likelion.team6th.fortune.repository.ZodiacRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZodiacService {
	
	public final ZodiacRepository zodiacRepository;
	
	public ZodiacAdminDTO registerZodiac(ZodiacAdminDTO zodiacAdminDTO) {
		
		Zodiac zodiac = Zodiac.of(
			zodiacAdminDTO.getContext(),
			zodiacAdminDTO.getImgLink(),
			zodiacAdminDTO.getZodiacType()
			);
		
		Zodiac save = zodiacRepository.save(zodiac);
		
		ZodiacAdminDTO result = ZodiacAdminDTO.from(save);
		
		return result;
	}


	public ZodiacAdminDTO updateZodiac(Long id, ZodiacAdminDTO zodiacAdminDTO) {
		
		Zodiac zodiac = zodiacRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 ID의 십이지신이 없습니다: " + id));
		
		zodiac.setContext(zodiacAdminDTO.getContext());
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


	public ZodiacDTO findById(Long id) {
		Zodiac zodiac = zodiacRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 띠 운세가 없습니다: " + id));
		return ZodiacDTO.from(zodiac);
	}

}
