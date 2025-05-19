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
	private String context;
	
	@Setter
	@Column(columnDefinition = "TEXT", name = "imgLink")
	private String imgLink;
	
	@Setter
	@Column(name = "zodiacType")
	private String zodiacType;
	
	protected Zodiac() {}
	
	private Zodiac(String context, String imgLink, String zodiacType) {
		this.context = context;
		this.imgLink = imgLink;
		this.zodiacType = zodiacType;
	}

	public static Zodiac of(String context, String imgLink, String zodiacType) {
		return new Zodiac(context, imgLink, zodiacType);

	}
}
