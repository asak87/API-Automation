package pojoClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
	
	
	private String name;
	
	private String id;
	
	private String year;
	
	public String getname() {
		return name;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	
	
	public String getid() {
		return id;
	}
	
	public void setid(String id) {
		this.id = id;
	}
	
	public String getyear() {
		return year;
	}
	
	
	public void setyear(String year) {
		this.year = year;
	}
	

}

