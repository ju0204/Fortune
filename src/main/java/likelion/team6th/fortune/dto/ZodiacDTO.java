package likelion.team6th.fortune.dto;

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
	
	public static ZodiacDTO from(Zodiac zodiac) {
	    return new ZodiacDTO(zodiac.getId(), zodiac.getContext(), zodiac.getImgLink(), zodiac.getZodiacType());
  }
	
	public static ZodiacDTO ofNew() {
		return new ZodiacDTO(null, null, null, null);
	}
  
}
