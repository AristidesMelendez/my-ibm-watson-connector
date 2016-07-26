package org.mule.modules.myibmwatson.model;

import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.developer_cloud.alchemy.v1.model.Concept;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Entity;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Taxonomy;

public class TextAnalyzed {
	private String text;
	private String language;
	private String sentiment;
	private List<TextEntity> entities;
	private List<String> concepts;
	private List<String> categories;

	public TextAnalyzed(String text) {
		super();
		this.text = text;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public List<String> getCategories() {
		if (categories == null)
			categories = new ArrayList<>();
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public void setCategoriesByTaxonomy(List<Taxonomy> categories) {
		for (Taxonomy taxonomy : categories) {
			getCategories().add(taxonomy.getLabel());
		}
	}

	public List<String> getConcepts() {
		if (concepts == null)
			concepts = new ArrayList<>();
		return concepts;
	}

	public void setConcepts(List<String> concepts) {
		this.concepts = concepts;
	}

	public void setConceptsAsStrings(List<Concept> concepts) {
		for (Concept concept : concepts) {
			getConcepts().add(concept.getText());
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<TextEntity> getEntities() {
		if (entities == null)
			entities = new ArrayList<>();
		return entities;
	}

	public void setTextEntities(List<TextEntity> entities) {
		this.entities = entities;
	}

	public void setEntities(List<Entity> entities) {
		for (Entity entity : entities) {
			getEntities().add(new TextEntity(entity));
		}
	}
}
