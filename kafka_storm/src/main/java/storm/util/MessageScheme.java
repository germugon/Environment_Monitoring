package storm.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class MessageScheme implements Scheme { 
    
	private String outputField = null;
	
	public MessageScheme( String outputField ) {
		this.outputField = outputField;
	}
	
    public List<Object> deserialize(byte[] bytes) {
        try {
			return new Values(new String(bytes, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return null;
    }
    
    public Fields getOutputFields() {
        return new Fields(outputField);  
    }  
}
