package test.com.virtuona.os.rdfstore;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.virtuona.os.rdfstore.SPARQLstore;
import com.virtuona.os.rdfstore.SPARQLstoreClient;

public class ContractGenerator {
	
	public static void main(String[] args)
	{
		//System.out.println(getRelatedParameters("Contract1","SmartMusicContract","smcdemo"));
	//	System.out.println(getParameterValue("SmartMusicContract","smcdemo","StartDate"));
	//	String<parameters>
	String contract_code=generateContract("Contract1", "SmartMusicContract","smcdemo");
	try {
		stringToFile("contract.txt",contract_code);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(contract_code);
	}

	
	
	public static void stringToFile(String filename, String text) throws FileNotFoundException
	{
			try (PrintWriter out = new PrintWriter(filename)) {
			    out.println(text);
			}
	}
		
	
	public static String generateContract(String contractname, String ontology, String graphname)
	{
		String code="";
		String Price="";
		String StartDate="";
		String EndDate="";
		String Receiver="";
		String Sender="";
		
		List<String> all_parameters=getRelatedParameters(contractname,ontology,graphname);
		for(String p:all_parameters)
		{
			String val=getParameterValue(ontology,graphname,p);
			if(val.isEmpty())
			{
			}else
			{
				if(p.equals("Price"))
				{
					Price=val;
				}
				
				if(p.equals("StartDate"))
				{
					StartDate=val;
				}
				if(p.equals("EndDate"))
				{
					EndDate=val;
				}
				if(p.equals("Sender"))
				{
					Sender=val;
				}
				if(p.equals("Receiver"))
				{
					Receiver=val;
				}
				
				
			}		
		}
		
		String c_head="pragma solidity ^0.4.21;\r\n" + 
				"contract SampleLibrary{\r\n" + 
				"	event Sent(address from, address to, uint amount);\r\n" + 
				"	function send() public {\r\n";
			
	    String c_date="		if(now>"+StartDate + " && now<"+EndDate+"){     \r\n";  
		String c_body="			if (balances["+Sender+"] < "+Price+") return;\r\n" + 
				"				balances["+Sender+"] -="+Price+";\r\n" + 
				"				balances["+Receiver+"] +="+Price+";\r\n" + 
				"				emit Sent"+"("+Sender+", "+Receiver+", "+ Price+ ");\r\n";  
		String c_date_end="		}\r\n"; 
		String end="	}\r\n" + 
				"}";
		
		if(StartDate.isEmpty() || EndDate.isEmpty())
		{
			
			code=c_head+c_body+end;
		}
		else
		{
			code=c_head+c_date+c_body+c_date_end+end;
		}
		
		
		return code;
		
	}
	
	
	public static List<String> getRelatedParameters(String contractname, String ontology, String graphname)
	{
		List<String> nodes=new ArrayList<String>();
		try {	
			String serviceEP = "http://infosys1.elfak.ni.ac.rs/scor/rdfstore";
			SPARQLstore store = new SPARQLstoreClient(serviceEP);
			
			String testQuery1 =" PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntaxns#>  \r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n" + 
					"PREFIX smc:<http://www.tasorone.com/tsc/resources/"+ontology+"/>\r\n" + 
					"SELECT DISTINCT ?p\r\n" + 
					"WHERE {\r\n" +
					"GRAPH "+ "<http://www.example.com/"+graphname+"> {"+
					"<http://www.example.com/"+graphname+"/"+contractname+">"+ " smc:hasParameter ?p." + 
					"} } ";				
			System.out.println(testQuery1);		
			//String testQuery2 = "SELECT * { GRAPH ?g { <"+TEST_GRAPH_NS+node_id+">"+ " <"+TEST_GRAPH_NS + "hasMeasured>"+ "?o; <"+TEST_GRAPH_NS + "hasValue> ?v. } }";
			String proba=new String();
			// Create a stream to hold the output
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			// IMPORTANT: Save the old System.out!
			PrintStream old = System.out;
			// Tell Java to use your special stream
			//System.setOut(ps);

			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			PrintStream ps2 = new PrintStream(baos2);

			store.executeDatasetSPARQL(testQuery1,baos, "json", proba);
			String result=baos.toString();
			System.out.println(result);
			
			JSONObject obj = new JSONObject(result);

			JSONObject obj2 = obj.getJSONObject("results");
			JSONArray arro=obj2.getJSONArray("bindings");
			for(int i=0;i<arro.length();i++)
			{
			String s=arro.getJSONObject(i).getJSONObject("p").getString("value").toString();
			String par=s.split("http://www.example.com/"+graphname+"/")[1];
			//	JSONObject obj4=arro.getJSONObject(0).getJSONObject("y");
			nodes.add(par);
			}
		
			
			baos.flush();
			baos.reset();
            
		//	System.out.println(arr.length);
		/*	if(arr.length>0)
			{
				for(int i=1;i<arr.length;i++)
				{
			
					String finres=arr[i].split(">")[0];
					//System.out.println(finres);
					nodes.add(finres);
				}
			}
			*/	
			
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
			return nodes;
		
		
	}
	
	
	
	
	public static String getParameterValue(String ontology, String graphname, String parameter)
	{
		String val="";
		try {	
			String serviceEP = "http://infosys1.elfak.ni.ac.rs/scor/rdfstore";
			SPARQLstore store = new SPARQLstoreClient(serviceEP);
			
			String testQuery1 ="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntaxns#>  \r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n" + 
					"PREFIX smc:<http://www.tasorone.com/tsc/resources/"+ontology+"/>\r\n" + 
					"SELECT DISTINCT ?v\r\n" + 
					"WHERE {\r\n" +
					"GRAPH "+ "<http://www.example.com/"+graphname+"> {"+
					"<http://www.example.com/"+graphname+"/"+parameter+">"+ " smc:hasValue ?v." + 
					"} } ";				
			System.out.println(testQuery1);		
			//String testQuery2 = "SELECT * { GRAPH ?g { <"+TEST_GRAPH_NS+node_id+">"+ " <"+TEST_GRAPH_NS + "hasMeasured>"+ "?o; <"+TEST_GRAPH_NS + "hasValue> ?v. } }";
			String proba=new String();
			// Create a stream to hold the output
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			// IMPORTANT: Save the old System.out!
			PrintStream old = System.out;
			// Tell Java to use your special stream
			//System.setOut(ps);

			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			PrintStream ps2 = new PrintStream(baos2);
			store.executeDatasetSPARQL(testQuery1,baos, "json", proba);
			
			String result=baos.toString();
			System.out.println(result);
			
			JSONObject obj = new JSONObject(result);

			JSONObject obj2 = obj.getJSONObject("results");
			JSONArray arro=obj2.getJSONArray("bindings");
		
			val=arro.getJSONObject(0).getJSONObject("v").getString("value").toString();			
			baos.flush();
			baos.reset();
          
			
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
			return val;
		
		
	}
	
	
}
