package likelion.team6th.fortune.dto;

import likelion.team6th.fortune.entity.Zodiac;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class ZodiacAdminDTO {
	
	private Long id;
	
	@Setter
	private String context1;
	
	@Setter
	private String context2;
	
	@Setter
	private String context3;
	
	@Setter
	private String imgLink;
	
	@Setter
	private String zodiacType;
	
	protected ZodiacAdminDTO() {}
	
	private ZodiacAdminDTO(Long id, String context1, String context2, String context3, String imgLink, String zodiacType) {
		this.id = id;
		this.context1 = context1;
		this.context2 = context2;
		this.context3 = context3;
		this.imgLink = imgLink;
		this.zodiacType = zodiacType;
	}
	
	public static ZodiacAdminDTO of(Long id, String context1, String context2, String context3, String imgLink, String zodiacType) {
		return new ZodiacAdminDTO(id, context1, context2, context3, imgLink, zodiacType);
	}
	
	public static ZodiacAdminDTO from(Zodiac zodiac) {
	    return new ZodiacAdminDTO(zodiac.getId(), zodiac.getContext1(), zodiac.getContext2(), zodiac.getContext3(), zodiac.getZodiacType(), zodiac.getImgLink());
	}
}
