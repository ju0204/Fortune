package likelion.team6th.fortune.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import likelion.team6th.fortune.dto.TarotDTO;
import likelion.team6th.fortune.dto.ZodiacAdminDTO;
import likelion.team6th.fortune.dto.ZodiacDTO;
import likelion.team6th.fortune.entity.Tarot;
import likelion.team6th.fortune.entity.Zodiac;
import likelion.team6th.fortune.repository.TarotRepository;
import likelion.team6th.fortune.repository.ZodiacRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonService {
	
	private final TarotRepository tarotRepository;
	private final ZodiacRepository zodiacRepository;
	private static final String[] zodiacType = {"mouse", "cow", "tiger", "rabbit", "dragon", "snake", "horse", "sheep", "monkey", "chicken", "dog", "pig"};
	
	// 연도 + 이름 + 날짜로 랜덤 시드값을 생성
	public static long generateDailySeed(int birthYear, String name) {
       
		LocalDate date = LocalDate.now(); 
		
		// 날짜를 "yyyyMMdd" 형태로 문자열로 만듦
        String dateStr = date.toString(); // 기본 형식이 yyyy-MM-dd

        // seed를 구성할 문자열
        String seedSource = birthYear + name + dateStr;

        // 해시값을 long으로 변환
        return Objects.hash(seedSource);
	
	}
	
	// 출생연도를 띠로 변환
	public static String calcZodiac(int year) {
		
		int idx = (year-4)%12;
		
		if (idx<0) {
			idx = idx+12;
		}
		return zodiacType[idx];
	}

	public TarotDTO getTarot(int year, String name) {
		
		// DB에 있는 모든 id값를 가져온다. + 가져온 id값을 배열로 저장한다.
		List<Long> ids = tarotRepository.findAllIds();		
		
		// 랜덤함수를 이용해 배열 안의 임의의 한 id값을 가져온다(.lengh 사용)
		Random random = new Random(generateDailySeed(year, name));
		
		int index =  random.nextInt(ids.size());
		
		// 랜덤으로 가져온 id값의 데이터 정보를 return해준다. 
		
		Tarot tarot = tarotRepository.getReferenceById(ids.get(index));
		
		return TarotDTO.from(tarot);
	}
	
	
	public ZodiacDTO getZodiac(int year, String name) {
		
		String zodiacType = calcZodiac(year);
		
		List<Zodiac> zodiacList = zodiacRepository.getReferencesByZodiacType(zodiacType);	
		
		Random random = new Random(generateDailySeed(year, name));
		
		int index = random.nextInt(zodiacList.size()); 
		
		ZodiacDTO zodiacDTO = ZodiacDTO.from(zodiacList.get(index));
				
		return zodiacDTO;
	}
	
	public Page<TarotDTO> getAllTarots(Pageable pageable) {
		Page<TarotDTO> tarotList = tarotRepository.findAll(pageable)
												.map(TarotDTO::from);
		return tarotList;
	}

	public Page<ZodiacAdminDTO> getAllZodiacs(Pageable pageable) {
		Page<ZodiacAdminDTO> zodiacList = zodiacRepository.findAll(pageable)
												.map(ZodiacAdminDTO::from);
		
		return zodiacList;
	}
	
}
