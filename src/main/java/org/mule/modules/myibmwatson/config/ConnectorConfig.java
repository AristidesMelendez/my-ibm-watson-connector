package org.mule.modules.myibmwatson.config;

import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.ConnectionException;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.param.ConnectionKey;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;

@ConnectionManagement(friendlyName = "Configuration")
public class ConnectorConfig {

    private AlchemyLanguage service;
    
    @Connect
    @TestConnectivity
    public void connect(@ConnectionKey String apiKey) throws ConnectionException{
    	setService(new AlchemyLanguage(apiKey));
    }

	public void setService(AlchemyLanguage service) {
		this.service = service;
	}
	
	public AlchemyLanguage getService(){
		return this.service;
	}
	
	@Disconnect
	public void disconnect(){
		setService(null);
	}
	
	@ValidateConnection
	public boolean isConnected(){
		return getService() != null;
	}

	@ConnectionIdentifier
	public String connectionId() {
		return "WATSON-Connector-001";
	}
}