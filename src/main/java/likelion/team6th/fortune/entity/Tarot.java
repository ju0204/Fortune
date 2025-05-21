package likelion.team6th.fortune.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@ToString
@Entity
@Table(name = "tarot")
public class Tarot {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Setter
	@Column(columnDefinition = "TEXT", name = "contextlove")
	private String contextLove;
	
	@Setter
	@Column(columnDefinition = "TEXT", name = "contextcareer")
	private String contextCareer;
	
	@Setter
	@Column(columnDefinition = "TEXT", name = "contextmoney")
	private String contextMoney;
	
	@Setter
	@Column(columnDefinition = "TEXT", name = "contexthealth")
	private String contextHealth;
	
	@Setter
	@Column(name = "imglink")
	private String imgLink;
	
	protected Tarot() {}
	
	private Tarot(String contextLove, String contextCareer, String contextMoney, String contextHealth,
			String imgLink) {
		this.contextLove = contextLove;
		this.contextCareer = contextCareer;
		this.contextMoney = contextMoney;
		this.contextHealth = contextHealth;
		this.imgLink = imgLink;
	}
	
	public static Tarot of(String contextLove, String contextCareer, String contextMoney, String contextHealth,
			String imgLink) {
		return new Tarot(contextLove, contextCareer, contextMoney, contextHealth, imgLink);
	}
}
