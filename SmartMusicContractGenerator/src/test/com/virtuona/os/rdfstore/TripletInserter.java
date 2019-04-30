package test.com.virtuona.os.rdfstore;




import com.virtuona.os.rdfstore.SPARQLstore;
import com.virtuona.os.rdfstore.SPARQLstoreClient;
import com.virtuona.os.sparql.TripletUpdateRequestBuffer;
import com.virtuona.os.sparql.TripletUpdateRequestBuffer.UpdateAction;



/**
 * Servlet implementation class FirstServlet
 */

public class TripletInserter {
	
  
public static void main(String[] args)
{
	try {
	TripletInserter ti=new TripletInserter();
ti.insertPointTriplets("exper1", "1.2","2.4");	
	}catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
}
    
	public void insertPointTriplets(String exp, String x, String y)
	{
	
		String TEST_GRAPH = "http://www.example.com/testgraph"+exp;
		String TEST_GRAPH_NS = TEST_GRAPH+"/";	
		
		 String serviceEP = "http://infosys1.elfak.ni.ac.rs/scor/rdfstore";
		 SPARQLstore store = new SPARQLstoreClient(serviceEP);
		 TripletUpdateRequestBuffer turb = new TripletUpdateRequestBuffer(1000, TEST_GRAPH, store, UpdateAction.INSERT);
		 String teront="http://www.tasorone.com/tsc/resources/TERont/";
		 String rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#";
		 try {
		 double d=Math.random();
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point"+d,rdf+"type", teront+"Point", null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point"+d, teront+"hasX", x, null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point"+d, teront+"hasY", y, null);
		 System.out.println("ja");
		 }catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }	 
	}
	
	
	
	public void insertLineTriplets(String exp, String x, String y)
	{
	
		String TEST_GRAPH = "http://www.example.com/testgraph"+exp;
		String TEST_GRAPH_NS = TEST_GRAPH+"/";	
		
		 String serviceEP = "http://infosys1.elfak.ni.ac.rs/scor/rdfstore";
		 SPARQLstore store = new SPARQLstoreClient(serviceEP);
		 TripletUpdateRequestBuffer turb = new TripletUpdateRequestBuffer(1000, TEST_GRAPH, store, UpdateAction.INSERT);
		 String teront="http://www.tasorone.com/tsc/resources/TERont/";
		 String rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#";
		 try {
		 double d=Math.random();
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Line"+d,rdf+"type", teront+"Point", null);

		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point1"+d,rdf+"type", teront+"Point", null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point1"+d, teront+"hasX", x, null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point1"+d, teront+"hasY", y, null);

		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point2"+d,rdf+"type", teront+"Point", null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point2"+d, teront+"hasX", x, null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point2"+d, teront+"hasY", y, null);
		 
		 turb.flush();
		 }catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }	 
	}
	
	
	

}