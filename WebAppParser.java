package uk.ac.le.cs.CO3102;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WebAppParser {
	public static void main(String[] args)
    {
       try{
  		DOMParser parser = new DOMParser();
  		parser.parse(args[0]);
  		Document doc = parser.getDocument();
  		traverse_tree(doc);
       }
       catch(Exception e){
          e.printStackTrace(System.err);
	}
    }
	
    //Makes sure the counter is printed once
	static int exceptionCounter = 0;
	
	//counter to keep track of how many abstract methods
	static int abstractCounter = 0;
	
	//boolean variable to keep track if this is the last method
	static boolean lastMethod = false;
	
	//Keeps track of the total number of arguments
	//for parameters
	static int totalException= 0;
	
	
	static int totalArguments = 0;
	static int argumentCounter = 0;
	
	
	public static void traverse_tree(Node node)
	   {
		 
	      if(node == null) {return;}
	      int type = node.getNodeType();
	      switch (type) {
	         case Node.DOCUMENT_NODE: {
	        	 //Selects only the abstact_method into the node list
	        	 NodeList list = ((Document) node).getElementsByTagName("abstract_method");
	        	 
	        	 //loops over each abstract_method
	        	 //recursively calling traverse_tre
	        	 //passes the abstract_method
	        	 if(list != null) {
	        		 int length = list.getLength();
	        		 for(int index = 0; index<length; index++) {
	        			 //if the index is equal to the end of the list
	        			 //Then last method is reassigned to true
	        			 if(index == length-1) {
	        				 lastMethod = true;
	        				
	        			 }
	        		
	        			 traverse_tree(list.item(index));
	        		 }
	        	 }
	   
	    		break;
		    }
	         case Node.ELEMENT_NODE: {
	        	 
	        	 //Checks if the current node is equal to abstract_method
	        	 if(node.getNodeName().equals("abstract_method")) {
	        		//Increments the abstractCounter variable
	        		 abstractCounter++;
	        		 
	        		 //Checks abstractCounter if equal 1
	        		 //Prevents abstract_method being printed more then once
	        		 if(abstractCounter == 1) {
	        			 System.out.print("{");
	        			 System.out.println();
	        			 System.out.print("  "+ "\"" + node.getNodeName() + "\"" + ":");
	        			 System.out.print(" [");
	        			 System.out.println();
	        			 System.out.print("    "+"{");
	        		 }
	        		 
	        		 //Checks if the counter is more then 1
	        		 //Print a open braces
	        		 //Resets the counters
	        		 if(abstractCounter > 1) {
	        			 System.out.print("    "+"{");
	        			 totalArguments = 0;
	        			 argumentCounter = 0;
	        			 totalException= 0;
	        			 exceptionCounter = 0;
	        			 
	        			 
	        			 
	        		 }
	        		 
	        		 //Gets the value of the attribute name
	        		 //Outputs the name
	        		 NamedNodeMap attrs = node.getAttributes();
	        		 Attr attrib = (Attr)attrs.getNamedItem("name");
	        		 String sr = attrib.getValue();
	        		 System.out.println();
	        		 System.out.print("      "+  "\"" + "name" + "\"" +":" + "\"" +sr+ "\""+",");
	        		 
	        	 }
	        	 
	        	 //Create and initialise a variable for the type of a argument
	        	 String argumentType = "";
	        	 
	        	//If node name is not equal to abstract_method
	        	 if(!node.getNodeName().equals("abstract_method")) {
	        		 //Gets the attribute value of type
	        		 if(node.getNodeName().equals("argument")) {
	        			 NamedNodeMap attrs = node.getAttributes();
		        		 Attr attrib = (Attr)attrs.getNamedItem("type");
		        		 argumentType = attrib.getValue(); 
	        		 }
	        		 
	        		 //If the node name is throws then print closing brackets
	        		 else if(node.getNodeName().equals("throws")) {
	        			 //Closes the parameters array
	        			 if(argumentCounter > 0) {
	        				 System.out.print("      "+"],");
	        			 }
	        			 
	        			 //Do nothing 
	        			 else {
	        				 System.out.print("");
	        				 
	        			 }
	        			 
	        			 
	        			  
	        		 }
	        		 
	        		 //If node name is exception
	        		 //Prints exception only once with open brackets
	        		 else if(node.getNodeName().equals("exception")) {
	        			 exceptionCounter++;
	        			 if(exceptionCounter == 1) {
	        				 System.out.print("      "+"\""  + "exceptions" + "\"" +":"+" [");
	        				 System.out.println();
	        			 }
	        			
	        		 }
	        		 
	        		 //If node name is parameters
	        		 //Prints parameters with square brackets
	        		 else if(node.getNodeName().equals("parameters")) {
	        			 System.out.print("      "+"\""  + node.getNodeName() +"\"" +":"+ " ["); 
	        			 
	        		 }
	        		 //If node name is equal to return
	        		 //Prints the closing bracket and name
	        		 else if(node.getNodeName().equals("return")) {
	        			
	        			 
	        	         //if both are equal to zero
	        			 //print just return
	        			 if(totalException == 0 && totalArguments == 0) {
	        				 System.out.print("      "+"\""  + node.getNodeName() +"\""); 
	        				 
	        			 }
	        			 
	        			 
	        			 //otherwise print a closing bracket
	        			 //then return
	        			 else {
	        				 System.out.print("      "+"],");
	        				 System.out.println();
	        				 System.out.print("      "+"\""  + node.getNodeName() +"\""); 
	        			 }
	        			 
	        			 
	        			 
	        			 
	        			 
	        			 
	        		 }
	        		 
	        		 //Prints any other node
	        		 else {
	        			 System.out.print("      "+"\""  + node.getNodeName() +"\""); 
	        		 }
	        		 
	        		 
	        	 }
	        	 
	        	 //If the node has a child node
	        	 if(node.getChildNodes().getLength()==1){
	        		 //If the node is argument
	        		 //print the braces and var and type of the argument
	        		 if(node.getNodeName().equals("argument")) {
	        			 //Increment the child node counter
	        			 argumentCounter++;
	        			 System.out.print("        "+"{");
	        			 System.out.println();
	        			 System.out.print("          "+"\"" + "var" + "\"" + ":"+ "\""+node.getTextContent()+"\""+",");
	        			 System.out.println();
	        			 System.out.print("          "+"\"" + "type" + "\"" + ":"+ "\""+argumentType+"\"");
	        			 System.out.println();
	        			 
	        			 //Compares the counter against 
	        			 //the total number of child nodes
	        			 //if equal then output without comma
	        			 if(argumentCounter == totalArguments) {
	        				 System.out.print("        "+"}");
	                         //reset counters
	        				
	        				 
	        			 }
	        			 else {
	        				 System.out.print("        "+"},");
	        				 
	        			 }
	        			 
	        		 }
	        		 
	        		 //If the node is exception
	        		 else if(node.getNodeName().equals("exception")) {
	        			 //increment child node counter
	        			 exceptionCounter++;
	        			 
	        			 //Compares the counter against 
	        			 //the total number of child nodes
	        			 //if equal then output without comma
	        			 if(totalException == exceptionCounter) {
	        				 System.out.print("          "+""+"\""+node.getTextContent()+"\""+",");
	        			
	        				 
	        			 }
	        			 else {
	        				 System.out.print("          "+""+"\""+node.getTextContent()+"\"");
	        			 }
	        			 
	        		 }
	        		 
	        		 //If node is return
	        		 //Print closing brackets
	        		 else if(node.getNodeName().equals("return")) {
	        			 System.out.print(":"+"\"" +node.getTextContent()+"\"");
	        			 System.out.println();
	        			 //Since return is the last element of each method
	        			 //If lastMethod is true then
	        			 //Print all the closing braces and brackets
	        			 
	        			 
	        			 if(lastMethod == true) {
	        				 System.out.print("    "+"}");
	        				 System.out.println();
	        				 System.out.print("  "+"]");
	        				 System.out.println();
	        				 System.out.print("}");
	        			 }
	        			 else {
		        			 System.out.print("    "+"},"); 
	        			 }
	        			 
	        			 

	        			 
	        			 
	        			 
	        		 }
	        		 
	        		 //Any other nodes have a child node
	        		 else {
	        			 System.out.print(":"+"\"" +node.getTextContent()+"\""+",");
	        		 }
	        		 
	        		 
	        		
	        		
	        	 }
	        	
	        	 
	        	 System.out.println();
	        	 //Loops over each childNode a node has
	        	 //Recursively calls the traverse_tree
	        	 NodeList childNodes = node.getChildNodes();	
	             if(childNodes != null) {
	                int length = childNodes.getLength();
	                for (int index = 0; index < length ; index++)
	                {
	                	//check if the current child node is parameters
	                	if(childNodes.item(index).getNodeName().equals("parameters")) {
	                		//get the child nodes of parameters
	                		//for loop to find all arguments
	                		//increment the total child node counter
	                		NodeList parametersChildNodes = childNodes.item(index).getChildNodes();
	             
	                		int anLength = parametersChildNodes.getLength();
	                		for(int i = 0; i < anLength; i++) {
	                			if(parametersChildNodes.item(i).getNodeName().equals("argument")) {
	                				totalArguments++;
	       
	                	
	                				
	                			}
	                		}
	                		
	                		//System.out.print(totalArgument);
	       
	                	}
	                	//check if the current child node is parameters
	                	if(childNodes.item(index).getNodeName().equals("throws")) {
	                		//get the child nodes of throws
	                		//for loop to find all arguments
	                		//increment the total child node counter
	                		NodeList throwsChildNodes = childNodes.item(index).getChildNodes();
	                		
	                		int anLength = throwsChildNodes.getLength();
	                		for(int i = 0; i < anLength; i++) {
	                			if(throwsChildNodes.item(i).getNodeName().equals("exception")) {
	                				totalException++;
	                				
	                				
	       
	                	
	                				
	                			}
	                		}
	                		
	         
	       
	                	}
	                	
	                	traverse_tree(childNodes.item(index));
	                	
	                }
	             }
	             
		         break;
	         }   
	      }  
	      
	      
	   }
	

	

	

}
