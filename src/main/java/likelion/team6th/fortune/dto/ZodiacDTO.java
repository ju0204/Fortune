package likelion.team6th.fortune.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import likelion.team6th.fortune.entity.Zodiac;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class ZodiacDTO {

	private Long id;
	
	@Setter
	private String context;
	
	@Setter
	private String imgLink;
	
	@Setter
	private String zodiacType;
	
	protected ZodiacDTO() {}
	
	private ZodiacDTO(Long id, String context, String imgLink, String zodiacType) {
		this.id = id;
		this.context = context;
		this.imgLink = imgLink;
		this.zodiacType = zodiacType;
	}
	
	public static ZodiacDTO of(Long id, String context, String imgLink, String zodiacType) {
		return new ZodiacDTO(id, context, imgLink, zodiacType);
	}
	
	public static ZodiacDTO from(Zodiac zodiac, int index) {
	    String context;
	    switch (index) {
	        case 0 -> context = zodiac.getContext1();
	        case 1 -> context = zodiac.getContext2();
	        case 2 -> context = zodiac.getContext3();
	        default -> throw new IllegalArgumentException("index는 1~3 사이여야 합니다.");
	    }

	    return new ZodiacDTO(zodiac.getId(), context, zodiac.getZodiacType(), zodiac.getImgLink());
	}
}


