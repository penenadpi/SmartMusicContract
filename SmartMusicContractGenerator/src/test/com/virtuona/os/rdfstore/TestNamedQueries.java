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

public class TestNamedQueries {

	
	
	public static void main(String[] args) throws Exception {

		List<String> elements=getRelatedClasses("Question", "ScriptConcordanceTestAssessmentMethod");
		//List<String> elements=new ArrayList<String>();		
	//	elements.add(new String("Description"));
		//elements.add(new String("NewInformation"));
		
		
		
		System.out.println(generateInputForm(elements));
		stringToFile("page1.html",generateInputForm(elements));
		/*
		for(int j=0;j<elements.size();j++)
		{
			System.out.println(elements.get(j));
		}*/
		
	}
	

	public static void requestParser(String request)
	{
		
		
		
	}
		public static void stringToFile(String filename, String text) throws FileNotFoundException
		{
			try (PrintWriter out = new PrintWriter(filename)) {
			    out.println(text);
			}
		}
		
		public static String generateInputForm(List<String> elements)
		{
			 
			String header="<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<body onclick=\"duplicate(event)\">\r\n" +  
					"\r\n" + 
					"<form action=\"/action_page.php\">";
			String body="";
			for(int j=0;j<elements.size();j++)
			{
				String elementname=elements.get(j);
				
				/*
				body+=elementname+": <input type=\"text\" name=\""+elementname+"\" value=\"Insert Text Here\"><br>\r\n";
				*/
				body+="<div>\r\n" + 
				elementname+": <input type=\"text\" name=\""+elementname+"\" value=\"Insert Text Here\">   <button type=\"button\">ADD NEW</button> </input>  <br/> \r\n" + 
				"</div>";
				
			//	System.out.println(elements.get(j));
			}
		
		String endform="  <input type=\"submit\" value=\"Submit\">\r\n" + 
				"</form> ";
		String script="\r\n" + 
				"<script>\r\n" + 
				"\r\n" + 
				"function duplicate(event) { \r\n" + 
				"  var x = event.target;\r\n" + 
				"  var par=x.parentElement.childNodes[0];\r\n" + 
				"  var par2=par.cloneNode(true);\r\n" + 
				"  var par3=x.parentElement.childNodes[1];\r\n" + 
				"  var par4=par3.cloneNode(true);\r\n" + 
				"  var par2_container=document.createElement('div');  \r\n" + 
				"  var par4_container=document.createElement('div');\r\n" + 
				"  \r\n" + 
				"  \r\n" + 
				"// Append the cloned <li> element to <ul> with id=\"myList1\"\r\n" + 
				"par2_container.appendChild(par2);\r\n" + 
				"\r\n" + 
				"par4_container.appendChild(par4);\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"par.parentElement.appendChild(par2_container);\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"par.parentElement.appendChild(par4_container);  \r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"</script>";
		String footer="\r\n" + 
				"</body>\r\n" + 
				"</html>";
		return header+body+endform+script+footer;
	}
	
	public static List<String> getRelatedClasses(String classname)
	{
		List<String> nodes=new ArrayList<String>();
		try {	
			String serviceEP = "http://infosys4.elfak.ni.ac.rs/tsc/rdfstore";
			SPARQLstore store = new SPARQLstoreClient(serviceEP);
			
			String testQuery1 =" PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntaxns#>  \r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n" + 
					"PREFIX sct:<http://www.tasorone.com/tsc/resources/ScriptConcordanceTestAssessmentMethod/>\r\n" + 
					"SELECT DISTINCT ?o\r\n" + 
					"WHERE {\r\n" + 
					"   sct:"+classname+ " ?p ?o. \r\n" + 
					"} ";				
					
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
		//	System.out.println(result);
			String[] arr=result.split("<http://www.tasorone.com/tsc/resources/ScriptConcordanceTestAssessmentMethod/");
			
			/*
			JSONObject obj = new JSONObject(result);

			JSONObject obj2 = obj.getJSONObject("results");
			JSONArray arr=obj2.getJSONArray("bindings");
		
			*/
			
			
			baos.flush();
			baos.reset();
            
		//	System.out.println(arr.length);
			if(arr.length>0)
			{
				for(int i=1;i<arr.length;i++)
				{
			
					String finres=arr[i].split(">")[0];
					//System.out.println(finres);
					nodes.add(finres);
				}
			}
				
			
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
			return nodes;
		
		
	}
	
	

	public static List<String> getRelatedClasses(String classname, String ontology)
	{
		List<String> nodes=new ArrayList<String>();
		try {	
			String serviceEP = "http://infosys4.elfak.ni.ac.rs/tsc/rdfstore";
			SPARQLstore store = new SPARQLstoreClient(serviceEP);
			
			String testQuery1 =" PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntaxns#>  \r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n" + 
					//"PREFIX sct:<http://www.tasorone.com/tsc/resources/ScriptConcordanceTestAssessmentMethod/>\r\n" + 
					"PREFIX sct:<http://www.tasorone.com/tsc/resources/" + ontology +"/>\r\n" 
					+"SELECT DISTINCT ?o\r\n" + 
					"WHERE {\r\n" + 
					"   sct:"+classname+ " ?p ?o. \r\n" + 
					"} ";				
					
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
		//	System.out.println(result);
			String[] arr=result.split("<http://www.tasorone.com/tsc/resources/"+ontology+"/");
			
			/*
			JSONObject obj = new JSONObject(result);

			JSONObject obj2 = obj.getJSONObject("results");
			JSONArray arr=obj2.getJSONArray("bindings");
		
			*/
			
			
			baos.flush();
			baos.reset();
            
		//	System.out.println(arr.length);
			if(arr.length>0)
			{
				for(int i=1;i<arr.length;i++)
				{
			
					String finres=arr[i].split(">")[0];
					//System.out.println(finres);
					nodes.add(finres);
				}
			}
				
			
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
			return nodes;
		
		
	}



	
	


	
}
