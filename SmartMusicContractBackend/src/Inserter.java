
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtuona.os.rdfstore.SPARQLstore;
import com.virtuona.os.rdfstore.SPARQLstoreClient;
import com.virtuona.os.sparql.TripletUpdateRequestBuffer;
import com.virtuona.os.sparql.TripletUpdateRequestBuffer.UpdateAction;





@WebServlet(description = "Inserter", urlPatterns = { "/TripletInsert" , "/TripletInsert.do"}, initParams = {@WebInitParam(name="id",value="18"),@WebInitParam(name="name",value="penenadpi")})
public class Inserter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";
	private static TripletInserter2 ti=new TripletInserter2();
       

    public Inserter() {
        super();
        // TODO Auto-generated constructor stub
    }


    
    
    
    
    
    public void insertContractTriplets(String exp, String ontology, String[] names, String[] values)
	{
	
		String TEST_GRAPH = "http://www.example.com/"+exp;
		String TEST_GRAPH_NS = TEST_GRAPH+"/";	
		 String serviceEP = "http://infosys1.elfak.ni.ac.rs/scor/rdfstore";
		 SPARQLstore store = new SPARQLstoreClient(serviceEP);
		 TripletUpdateRequestBuffer turb = new TripletUpdateRequestBuffer(1000, TEST_GRAPH, store, UpdateAction.INSERT);

		 String rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#";
		 try {
		 double d=Math.random();
		 turb.addDataPropTriplet(TEST_GRAPH_NS+"Contract"+d,rdf+"type", ontology+"Contract", null);
		 
		 for(int i=0;i<names.length;i++)
		 {
			 turb.addDataPropTriplet(TEST_GRAPH_NS+"Contract"+d, ontology+"hasParameter", names[i], null);
			 turb.addDataPropTriplet(TEST_GRAPH_NS+names[i],rdf+"type", ontology+"Parameter", null);
			 turb.addDataPropTriplet(TEST_GRAPH_NS+names[i], ontology+"hasValue", values[i], null);

		 }
		 
	
		 turb.flush();
		 
		 }catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }	 
	}
	
	
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Date date = new Date();
	
		out.println(HTML_START + "<h2>Hi There!</h2><br/><h3>Date="+date +"</h3>"+HTML_END);

		String[] names=request.getParameterValues("parameter_name");
		String[] values=request.getParameterValues("parameter_value");
		

	  insertContractTriplets("smcdemo","<http://www.tasorone.com/tsc/resources/SmartMusicContract/>",names, values);
		//out.println(res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

	
	

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	
	public static void write(String fileName, String content) 
			  throws IOException {
			    FileWriter fileWriter = new FileWriter(fileName);
			    PrintWriter printWriter = new PrintWriter(fileWriter);
			    printWriter.print(content);
			  
	}

}