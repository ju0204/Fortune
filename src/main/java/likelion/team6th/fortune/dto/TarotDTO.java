package likelion.team6th.fortune.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import likelion.team6th.fortune.entity.Tarot;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
public class TarotDTO {

	private Long id;
	
	@Setter
	private String contextLove;
	
	@Setter
	private String contextCareer;
	
	@Setter
	private String contextMoney;
	
	@Setter
	private String contextHealth;
	
	@Setter
	private String imgLink;
	
	protected TarotDTO() {}
	
	private TarotDTO(Long id, String contextLove, String contextCareer, String contextMoney, String contextHealth,
			String imgLink) {
		this.id = id;
		this.contextLove = contextLove;
		this.contextCareer = contextCareer;
		this.contextMoney = contextMoney;
		this.contextHealth = contextHealth;
		this.imgLink = imgLink;
	}
	
	public static TarotDTO of(Long id, String contextLove, String contextCareer, String contextMoney, String contextHealth,
			String imgLink) {
		return new TarotDTO(id, contextLove, contextCareer, contextMoney, contextHealth, imgLink);
	}
	
	public static TarotDTO from(Tarot tarot) {
		return new TarotDTO(tarot.getId(), tarot.getContextLove(), tarot.getContextCareer(), tarot.getContextMoney(), tarot.getContextHealth(), tarot.getImgLink());
	}
}
