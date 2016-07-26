package org.mule.modules.myibmwatson;

import java.util.HashMap;
import java.util.Map;

import org.mule.modules.myibmwatson.model.TextAnalyzed;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Concepts;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Entities;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Taxonomies;

public class TextProcessor {
	
	private AlchemyLanguage service;
	
	public TextProcessor(AlchemyLanguage service){
		super();
		this.service = service;
	}

	public TextAnalyzed processText(String message){
		
		TextAnalyzed result = new TextAnalyzed(message);
		Map<String, Object> params = new HashMap<>();
		params.put(AlchemyLanguage.TEXT, message);
		
		Entities entities = service.getEntities(params).execute();
		
		result.setLanguage(entities.getLanguage());
		result.setEntities(entities.getEntities());
		
		Concepts concepts = service.getConcepts(params).execute();
		result.setConceptsAsStrings(concepts.getConcepts());
		
		Taxonomies taxonomies = service.getTaxonomy(params).execute();
		result.setCategoriesByTaxonomy(taxonomies.getTaxonomy());

		DocumentSentiment sentiment = service.getSentiment(params).execute();
		result.setSentiment(sentiment.getSentiment().getType().name());

		
		return result;
	}
}
