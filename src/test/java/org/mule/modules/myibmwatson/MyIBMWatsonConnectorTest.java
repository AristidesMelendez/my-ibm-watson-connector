package org.mule.modules.myibmwatson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mule.modules.myibmwatson.config.ConnectorConfig;
import org.mule.modules.myibmwatson.model.TextAnalyzed;
import org.mule.modules.myibmwatson.model.TextEntity;

public class MyIBMWatsonConnectorTest {

	private static final String API_KEY = "xxxxxyyyyyyyzzzzzzz"; //Replace with your API_KEY
	private static final String TEXT_TEST = "OCDE recomienda a países entregar bonos para compras en lugar de subsidios en efectivo";
	private MyIBMWatsonConnector connector;

	@Before
	public void setUp() throws Exception {
		ConnectorConfig config = new ConnectorConfig();
		config.connect(API_KEY);
		connector = new MyIBMWatsonConnector();
		connector.setConfig(config);
		connector.init();
	}

	@Test
	public void testProcessText() {

		Object obj = connector.processText(TEXT_TEST);

		assertEquals("Object class is not accepted,", TextAnalyzed.class, obj.getClass());
		TextAnalyzed analysis = (TextAnalyzed) obj;

		assertEquals("Language is wrong.", "spanish", analysis.getLanguage());
		assertEquals("text is wrong", "OCDE recomienda a países entregar bonos para compras en lugar de subsidios en efectivo", analysis.getText());
		assertEquals("NEUTRAL", analysis.getSentiment());
		assertEquals(1, analysis.getEntities().size());
		TextEntity entity = analysis.getEntities().get(0);
		assertEquals("OCDE", entity.getName());
		assertEquals("Organization", entity.getType());
		assertEquals("", entity.getSentiment());
		assertEquals(1,analysis.getConcepts().size());
		assertEquals("País", analysis.getConcepts().get(0));
		assertEquals(3, analysis.getCategories().size());
		assertTrue(analysis.getCategories().contains("/finance/investing/stocks"));
		assertTrue(analysis.getCategories().contains("/finance/investing/funds/mutual funds"));
		assertTrue(analysis.getCategories().contains("/business and industrial/energy/oil/oil and gas prices"));
	}
}
