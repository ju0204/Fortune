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

@Getter
@ToString
@Entity
@Table(name = "zodiac")
public class Zodiac {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Setter
	private String context;
	
	@Setter
	private String imgLink;
	
	protected Zodiac() {}
	
	private Zodiac(String context, String imgLink) {
		this.context = context;
		this.imgLink = imgLink;
	}
	
	public static Zodiac of(String context, String imgLink) {
		return new Zodiac(context, imgLink);
	}
}
