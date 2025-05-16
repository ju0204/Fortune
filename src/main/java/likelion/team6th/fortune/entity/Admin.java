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

@ToString(callSuper = true) // callSuper: 부모(AuditingFields)의 값도 로그 찍어주고 싶을 때 사용

@Entity

@Table(name = "admin") // class 이름과 db table 이름이 다를 경우 써줘야함 (대소문자)

public class Admin {

	

	// null값을 허용해야 Hibernate가 "값 없음"을 인식하고 INSERT 시 자동 생성해줌

	// int는 null 허용 안함

	@Id

	@Column(name = "id")

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	

	@Setter

	private String adminId;

	

	@Setter

	private String adminPw;

	

	protected Admin() {}

	

	private Admin(String id, String pw) {

		this.adminId = id;

		this.adminPw = pw;

	}



	public static Admin of(String id, String pw) {

		return new Admin(id, pw);

	}



}
