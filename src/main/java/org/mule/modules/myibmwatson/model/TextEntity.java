package org.mule.modules.myibmwatson.model;

import com.ibm.watson.developer_cloud.alchemy.v1.model.Entity;

public class TextEntity {

	private String name;
	private String type;
	private String sentiment;
	
	public String getName() {
		return name;
	}
	
	public TextEntity() {
	}
	
	public TextEntity(String name, String type, String sentiment) {
		this.name = name;
		this.type = type;
		this.sentiment = sentiment;
	}
	
	public TextEntity(Entity e){
		this(e.getText(), e.getType(), (e.getSentiment() != null) ? e.getSentiment().getType().name() : "");
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	
	
}
