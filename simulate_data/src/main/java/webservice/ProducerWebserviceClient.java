package webservice;

import javax.xml.namespace.QName;  

import org.apache.axis2.AxisFault;  
import org.apache.axis2.addressing.EndpointReference;  
import org.apache.axis2.client.Options;  
import org.apache.axis2.rpc.client.RPCServiceClient;

import util.ConfigUtil;  
  
public class ProducerWebserviceClient  {  
	
    public static Object[] RPCServiceCall(String method, Object[] parameters, Class[] returnTypes){      
        try {  
        	//使用RPC方式调用WebService
            RPCServiceClient rpcServiceClient = new RPCServiceClient();  
  
            Options options = rpcServiceClient.getOptions(); 
            
            //指定调用WebService的URL  
            EndpointReference targetEPR = new EndpointReference(ConfigUtil.WEBSERVICE_URL);  
            options.setTo(targetEPR);  
            //指定调用的方法
            options.setAction("urn:" + method);  
              
            //指定要调用的方法及WSDL文件的命名空间  
            QName opAddEntry = new QName("http://service", method);  
            
//            //指定调用方法的参数值  
//            Object[] parameters = new Object[] { param.toString()};  
//            //指定调用方法返回值的数据类型  
//            Class[] returnTypes = new Class[] { String.class };  
             
            // 调用sfexpressService方法并输出该方法的返回值  
            return rpcServiceClient.invokeBlocking(opAddEntry, parameters, returnTypes);    
            
        } catch (AxisFault e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return null;  
    }  
      
    public static void main(String[] args) { 

    	//指定调用方法的参数值  
    	Object[] parameters = new Object[] { "test" }; 
    	//指定调用方法返回值的数据类型  
    	Class[] returnTypes = new Class[] { String.class }; 
    	   
    	Object[] results = ProducerWebserviceClient.RPCServiceCall(ConfigUtil.WEBSERVICE_METHOD, parameters, returnTypes);
    	for(Object result : results) {
    		System.out.println(result); 
    	}
    }  
}

