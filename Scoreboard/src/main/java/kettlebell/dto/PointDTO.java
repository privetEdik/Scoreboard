package kettlebell.dto;

public enum PointDTO {
	ZERO("0"),FIFTY("15"),TRITY("30"),FORTY("40"),PLUS_ONE("+1"),PLUS_TWO("+2");
	
	private String title;
	
	PointDTO(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
}
