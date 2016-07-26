package org.mule.modules.myibmwatson;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.lifecycle.Start;
import org.mule.modules.myibmwatson.config.ConnectorConfig;
import org.mule.modules.myibmwatson.model.TextAnalyzed;

@Connector(name="my-ibm-watson", friendlyName="MyIBMWatson")
public class MyIBMWatsonConnector {

	@Config
	ConnectorConfig config;

	private TextProcessor textProcessor;
	
	@Start
	public void init(){
		textProcessor = new TextProcessor(config.getService());
	}
	@Processor
	public TextAnalyzed processText(String message) {
		return textProcessor.processText(message);
	}

	public ConnectorConfig getConfig() {
		return config;
	}

	public void setConfig(ConnectorConfig config) {
		this.config = config;
	}

}