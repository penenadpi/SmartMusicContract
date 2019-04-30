package test.com.virtuona.os.rdfstore;

import com.virtuona.os.rdfstore.SPARQLstore;
import com.virtuona.os.rdfstore.SPARQLstoreClient;
import com.virtuona.os.sparql.TripletUpdateRequestBuffer;
import com.virtuona.os.sparql.TripletUpdateRequestBuffer.UpdateAction;

public class TripletManager {

	
	
	public static void main(String[] args) throws Exception {

	
		 TripletManager tm=new TripletManager();
	
		//INSERT
		System.out.println("Inserting triplets");
		//tm.testTripletInsert("graph2","2","3");
	//	tm.obstacleTripletInsert("g220","2","3");
		//tm.insertFire("graph200","1","2","3");
		
		
		
		tm.obstacleTripletInsert("rt94.edl","-1.5","-1.5");
		//tm.fireTripletInsert("g250","2","3");


	}
	
	
	public void fireTripletInsert(String graph, String x, String y) throws Exception{
		String serviceEP = "http://infosys1.elfak.ni.ac.rs/scor/rdfstore";
		SPARQLstore store = new SPARQLstoreClient(serviceEP);	
		String TEST_GRAPH = "http://www.example.com/"+graph;
		String TEST_GRAPH_NS = TEST_GRAPH+"/";
		
		TripletUpdateRequestBuffer turb = new TripletUpdateRequestBuffer(1000, TEST_GRAPH, store, UpdateAction.INSERT);
		 double node=Math.random();

		 String rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#";
		  String eOntology="http://www.tasorone.com/tsc/resources/SCOReo/";

			turb.addDataPropTriplet(TEST_GRAPH_NS+"Fire_"+node, rdf+"type", eOntology+"Event", null);
		  	
			turb.addDataPropTriplet(TEST_GRAPH_NS+"Fire_"+node, eOntology+"hasXcoordinate", x, null);
			turb.addDataPropTriplet(TEST_GRAPH_NS+"Fire_"+node, eOntology+"hasYcoordinate", y, null);
			
		
		turb.flush();
	}
	
	
	
	 /*
	  public void insertFire(String graph,String x, String y, String z)
	  {
		  String TEST_GRAPH = "http://www.example.com/"+graph;
		  String TEST_GRAPH_NS = TEST_GRAPH+"/";
		  String serviceEP = "http://infosys1.elfak.ni.ac.rs/scor/rdfstore";
		  SPARQLstore store = new SPARQLstoreClient(serviceEP);
		  TripletUpdateRequestBuffer turb = new TripletUpdateRequestBuffer(1000, graph, store, UpdateAction.INSERT);
		  String eOntology="http://www.tasorone.com/tsc/resources/SCOReo/";
		  String rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#";
		  String edlOntology="http://www.tasorone.com/tsc/resources/SEDLontology/";

		  try {
			    Double node=Math.random();
				turb.addDataPropTriplet(TEST_GRAPH_NS+"/Fire_"+node, rdf+"type", eOntology+"Event", null);
		  	
				turb.addDataPropTriplet(TEST_GRAPH_NS+"/Fire_"+node, eOntology+"hasXcoordinate", x, null);
				turb.addDataPropTriplet(TEST_GRAPH_NS+"/Fire_"+node, eOntology+"hasYcoordinate", y, null);
				turb.addDataPropTriplet(TEST_GRAPH_NS+"/Fire_"+node, eOntology+"hasZcoordinate", z, null);
				turb.flush();
			  System.out.println("INSERTED");
		  }catch(Exception e)
		  {
			  System.out.println(e.getMessage());
			  e.printStackTrace();
		  }
	  
	  
	  
	  }
	  */
	  public void obstacleTripletInsert(String graph, String x, String y) throws Exception{
			String serviceEP = "http://infosys1.elfak.ni.ac.rs/scor/rdfstore";
			SPARQLstore store = new SPARQLstoreClient(serviceEP);	
			String TEST_GRAPH = "http://www.example.com/"+graph;
			String TEST_GRAPH_NS = TEST_GRAPH+"/";
			
			TripletUpdateRequestBuffer turb = new TripletUpdateRequestBuffer(1000, TEST_GRAPH, store, UpdateAction.INSERT);
			 double node=Math.random();

			 String rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#";
			  String eOntology="http://www.tasorone.com/tsc/resources/SCOReo/";

				turb.addDataPropTriplet(TEST_GRAPH_NS+"Obstacle_"+node, rdf+"type", eOntology+"Event", null);
			  	
				turb.addDataPropTriplet(TEST_GRAPH_NS+"Obstacle_"+node, eOntology+"hasXcoordinate", x, null);
				turb.addDataPropTriplet(TEST_GRAPH_NS+"Obstacle_"+node, eOntology+"hasYcoordinate", y, null);
				
			
			turb.flush();
		}
	  
	  /*
	  public void insertObstacle(String graph, String x, String y, String z)
	  {
		  String TEST_GRAPH = "http://www.example.com/"+graph;
		  String TEST_GRAPH_NS = TEST_GRAPH+"/";
		  String serviceEP = "http://infosys1.elfak.ni.ac.rs/scor/rdfstore";
		  SPARQLstore store = new SPARQLstoreClient(serviceEP);
		  TripletUpdateRequestBuffer turb = new TripletUpdateRequestBuffer(1000, graph, store, UpdateAction.INSERT);
		  String eOntology="http://www.tasorone.com/tsc/resources/SCOReo/";
		  String rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#";
		  String edlOntology="http://www.tasorone.com/tsc/resources/SEDLontology/";

		  try {
			  	
			  	double node=2;
				//turb.addDataPropTriplet(TEST_GRAPH_NS+"Obstacle_"+node, rdf+"type", eOntology+"Event", null);
		  	
				turb.addDataPropTriplet(TEST_GRAPH_NS+"Obstacle_"+node, eOntology+"hasXcoordinate", x, null);
				turb.addDataPropTriplet(TEST_GRAPH_NS+"Obstacle_"+node, eOntology+"hasYcoordinate", y, null);
				turb.addDataPropTriplet(TEST_GRAPH_NS+"Obstacle_"+node, eOntology+"hasZcoordinate", z, null);
				turb.flush();
			  System.out.println("INSERTED");
		  }catch(Exception e)
		  {
			  System.out.println(e.getMessage());
			  e.printStackTrace();
		  }
	  
	  
	  
	  }
	*/
	
	/**
	 * Insert two resources. Each resource have 2 triplets.
	 * @param store
	 * @throws Exception
	 */
	public  void testTripletInsert(String graph, String x, String y) throws Exception{
		String serviceEP = "http://infosys1.elfak.ni.ac.rs/scor/rdfstore";
		SPARQLstore store = new SPARQLstoreClient(serviceEP);	
		String TEST_GRAPH = "http://www.example.com/"+graph;
		String TEST_GRAPH_NS = TEST_GRAPH+"/";
		
		TripletUpdateRequestBuffer turb = new TripletUpdateRequestBuffer(1000, TEST_GRAPH, store, UpdateAction.INSERT);
		 double d=Math.random();

		String teront="http://www.tasorone.com/tsc/resources/TERont/";
		 String rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#";
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Line"+d,rdf+"type", teront+"Point", null);

		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point1"+d,rdf+"type", teront+"Point", null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point1"+d, teront+"hasX", x, null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point1"+d, teront+"hasY", y, null);

		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point2"+d,rdf+"type", teront+"Point", null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point2"+d, teront+"hasX", x, null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point2"+d, teront+"hasY", y, null);
		 
		
		turb.flush();
	}
 
	public void pointTripletInsert(String graph, String x, String y) throws Exception{
		String serviceEP = "http://infosys1.elfak.ni.ac.rs/scor/rdfstore";
		SPARQLstore store = new SPARQLstoreClient(serviceEP);	
		String TEST_GRAPH = "http://www.example.com/"+graph;
		String TEST_GRAPH_NS = TEST_GRAPH+"/";
		
		TripletUpdateRequestBuffer turb = new TripletUpdateRequestBuffer(1000, TEST_GRAPH, store, UpdateAction.INSERT);
		 double d=Math.random();

		String teront="http://www.tasorone.com/tsc/resources/TERont/";
		 String rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#";

		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point1"+d,rdf+"type", teront+"Point", null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point1"+d, teront+"hasX", x, null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point1"+d, teront+"hasY", y, null);		 
		
		turb.flush();
	}
	
	
	
	
	public  void lineTripletInsert(String graph, String x1, String y1, String x2, String y2) throws Exception{
		String serviceEP = "http://infosys1.elfak.ni.ac.rs/scor/rdfstore";
		SPARQLstore store = new SPARQLstoreClient(serviceEP);	
		String TEST_GRAPH = "http://www.example.com/"+graph;
		String TEST_GRAPH_NS = TEST_GRAPH+"/";
		
		TripletUpdateRequestBuffer turb = new TripletUpdateRequestBuffer(1000, TEST_GRAPH, store, UpdateAction.INSERT);
		 double d=Math.random();

		String teront="http://www.tasorone.com/tsc/resources/TERont/";
		 String rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#";
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Line1"+d,rdf+"type", teront+"Line", null);

		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point1"+d,rdf+"type", teront+"Point", null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point1"+d, teront+"hasX", x1, null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point1"+d, teront+"hasY", y1, null);

		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point2"+d,rdf+"type", teront+"Point", null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point2"+d, teront+"hasX", x2, null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Point2"+d, teront+"hasY", y2, null);
		 
	 
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Line"+d, teront+"hasStartingPoint",TEST_GRAPH_NS+"Point1"+d, null);
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Line"+d, teront+"hasEndingPoint",TEST_GRAPH_NS+"Point2"+d, null);
			
		turb.flush();
	}
	
	
	
	

}
