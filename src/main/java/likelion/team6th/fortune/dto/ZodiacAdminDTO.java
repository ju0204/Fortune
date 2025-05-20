package likelion.team6th.fortune.dto;

import likelion.team6th.fortune.entity.Zodiac;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
public class ZodiacAdminDTO {
	
	private Long id;
	
	@Setter
	private String context;
	
	@Setter
	private String imgLink;
	
	@Setter
	private String zodiacType;
	
	protected ZodiacAdminDTO() {}
	
	private ZodiacAdminDTO(Long id, String context, String imgLink, String zodiacType) {
		this.id = id;
		this.context = context;
		this.imgLink = imgLink;
		this.zodiacType = zodiacType;
	}
	
	public static ZodiacAdminDTO of(Long id, String context, String imgLink, String zodiacType) {
		return new ZodiacAdminDTO(id, context, imgLink, zodiacType);
	}
	
	public static ZodiacAdminDTO from(Zodiac zodiac) {
	    return new ZodiacAdminDTO(zodiac.getId(), zodiac.getContext(), zodiac.getImgLink(), zodiac.getZodiacType());
	}
}
