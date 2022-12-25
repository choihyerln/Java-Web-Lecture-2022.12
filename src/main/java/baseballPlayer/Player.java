package baseballPlayer;

import java.time.LocalDate;
/**
 * DTO (Data Transfer Object)
 */
public class Player {
	private int bNum;
	private String name;
	private String position;
	private LocalDate birthDate;
	private int height;
	private int isDeleted;
	
	Player() {}
	Player(int bNum, String name, String position, LocalDate birthDate, int height) {
		super();
		this.bNum = bNum;
		this.name = name;
		this.position = position;
		this.birthDate = birthDate;
		this.height = height;
	}
	Player(int bNum, String name, String position, LocalDate birthDate, int height, int isDeleted) {
		super();
		this.bNum = bNum;
		this.name = name;
		this.position = position;
		this.birthDate = birthDate;
		this.height = height;
		this.isDeleted = isDeleted;
	}
	public Player(int bNum, String name) {
		this.bNum = bNum;
		this.name = name;
	}
	public Player(int bNum, String name, String position, String birthDate, int height) {
		this.bNum = bNum;
		this.name = name;
		this.position = position;
		this.birthDate = LocalDate.parse(birthDate);
		this.height = height;
	}
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public String toString() {
		return "Player [" + bNum + ", " + name + ", " + position + ", " + birthDate
				+ ", " + height + ", " + isDeleted + "]";
	}
}