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

public class PageGenerator {

	
	
	public static void main(String[] args) throws Exception {

		List<String> elements=getRelatedParameters("LoopContract", "SmartMusicContract","smcdef");	
		System.out.println(generateInputForm2(elements));
		stringToFile("page.html",generateInputForm2(elements));
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
					"<body>\r\n" +  
					"\r\n" + 
					"<form action=\"http://localhost:8080/SmartMusicContract/TripletInsert\">";
			String body="";
			for(int j=0;j<elements.size();j++)
			{
				String elementname=elements.get(j);
				
				/*
				body+=elementname+": <input type=\"text\" name=\""+elementname+"\" value=\"Insert Text Here\"><br>\r\n";
				*/
				body+="<div>\r\n" + 
				elementname+": <input type=\"text\" name=\""+elementname+"\" value=\"Insert Text Here\">   <button type=\"button\" onclick=\"duplicate(event)\">ADD NEW</button> </input>  <br/> \r\n" + 
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
	
	public static String generateInputForm2(List<String> elements)
	{
			 
			String header="<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<body>\r\n" +  
					"\r\n" + 
					"<form action=\"http://localhost:8080/SmartMusicContract/TripletInsert\">";
			String body="";
			String submit="  <input type=\"submit\" value=\"Submit\">\r\n"; 
			body+=submit;
			body+="<div>\r\n";
			String select="<select name=\"parameter_name\">\r\n"; 
			
			
			body+=select;
			for(int j=0;j<elements.size();j++)
			{
				String elementname=elements.get(j);
				String option="    <option value=\""+elementname+"\">"+elementname+"</option>\r\n";
				body+=option;
				
			
			}

			body+="</select>";
			String inputbutton="<input type=\"text\" name=\"parameter_value"+"\" value=\"Insert Text Here\">   <button type=\"button\" onclick=\"duplicate(event)\">ADD NEW</button> </input>  <br/> \r\n";
			body+=inputbutton;
			body+="<div>\r\n";
			body+="</form> ";
		String script="\r\n" + 
				"<script>\r\n" + 
				"\r\n" + 
				"function duplicate(event) { \r\n" + 
				"  var x = event.target;\r\n" + 
				"  var par=x.parentElement.childNodes[1];\r\n" + 
				"  var par2=par.cloneNode(true);\r\n" + 
				"  var par3=x.parentElement.childNodes[2];\r\n" + 
				"  var par4=par3.cloneNode(true);\r\n" + 
				"  var par2_container=document.createElement('div');  \r\n" + 
				"  \r\n" + 
				"  \r\n" + 
				"par2_container.appendChild(par2);\r\n" + 
				"\r\n" + 
				"par2_container.appendChild(par4);\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"par.parentElement.appendChild(par2_container);\r\n" + 
				"\r\n" + 
				"\r\n" + 
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
		return header+body+script+footer;
	}

	public static List<String> getRelatedParameters(String classname, String ontology, String graphname)
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
					"<http://www.example.com/"+graphname+"/"+classname+">"+ " smc:hasParameter ?p." + 
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
			String par=s.split("http://www.tasorone.com/tsc/resources/SmartMusicContract/")[1];
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



	
	


	
}
