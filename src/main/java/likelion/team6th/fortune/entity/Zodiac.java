package likelion.team6th.fortune.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
	@Column(columnDefinition = "TEXT")
	private String context1;
	
	@Setter
	@Column(columnDefinition = "TEXT")
	private String context2;
	
	@Setter
	@Column(columnDefinition = "TEXT")
	private String context3;
	
	@Setter
	@Column(columnDefinition = "TEXT", name = "imgLink")
	private String imgLink;
	
	@Setter
	@Column(name = "zodiacType")
	private String zodiacType;
	
	protected Zodiac() {}
	
	private Zodiac(String context1, String context2, String context3, String imgLink, String zodiacType) {
		this.context1 = context1;
		this.context2 = context2;
		this.context3 = context3;
		this.imgLink = imgLink;
		this.zodiacType = zodiacType;
	}

	public static Zodiac of(String context1, String context2, String context3, String imgLink, String zodiacType) {
		return new Zodiac(context1, context2, context3, imgLink, zodiacType);

	}
}
