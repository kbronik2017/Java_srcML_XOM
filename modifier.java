


//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//import nu.xom.Nodes;
//import nu.xom.Builder;
//import nu.xom.Comment;
//import nu.xom.Document;
//import nu.xom.Element;
//import nu.xom.Elements;
//import nu.xom.ParsingException;
//import nu.xom.Serializer;
//import nu.xom.ValidityException;
import java.io.*;
import nu.xom.*;
public class modifier {

/**
   * 
   */
  public static void main(String[] args) throws ValidityException, ParsingException, IOException {
//    modifier p = new modifier();

    Builder builder = new Builder();
    InputStream ins = modifier.class.getClassLoader().getResourceAsStream("Fibonacci.xml");

    
    
    Document doc = builder.build(ins);
    Element root = doc.getRootElement();
    System.out.println(".....................................");
    System.out.println(".........Before modification.........");
    System.out.println(".....................................");
    printall(root, 0); 
    extract_info(root, doc );
    System.out.println("Output File name: " + "modified_outFIB.xml");
//    modification(doc);
    System.out.println("....................................");
    System.out.println(".........After modification.........");
    System.out.println("....................................");
    
    InputStream ins1= modifier.class.getClassLoader().getResourceAsStream("modified_outFIB.xml");
//    Document doc_mod = builder.build(ins1);
//    Element root_mod = doc_mod.getRootElement();
    printall(root, 0);
    
  }
  public static Element define_element(Document doc, String value) {

	  String l1 =  "\"";
	  String l2 = " : \"";
	  String value_n = l1 + " "+ value + l2;
	  Element element = new Element("expr_stmt");
	  Element expr = new Element("expr");
	  Element call = new Element("call");
	  Element name = new Element("name");
	  element.appendChild(expr);
	  expr.appendChild(call);
	  call.appendChild(name);
	  Element n_name = new Element("name");
	  name.appendChild(n_name);
	  n_name.appendChild("System");
	  Element operator = new Element("operator");
	  name.appendChild(operator);
	  operator.appendChild(".");
	  Element n_name1 = new Element("name");
	  name.appendChild(n_name1);
	  n_name1.appendChild("out");
	  Element operator1 = new Element("operator");
	  name.appendChild(operator1);
	  operator1.appendChild(".");
	  Element n_name2 = new Element("name");
	  name.appendChild(n_name2);
	  n_name2.appendChild("println");
	  Element argument_list = new Element("argument_list");
	  call.appendChild(argument_list);
	  argument_list.appendChild("(");
	  Element argument = new Element("argument");
	  argument_list.appendChild(argument);
	  Element expr1 = new Element("expr");
	  argument.appendChild(expr1);
	  Element literal = new Element("literal");
//	  literal.appendChild("\" t1 : \"");
	  literal.appendChild(value_n);
	  Attribute type = new Attribute("type", "string");
	  literal.addAttribute(type);
	  expr1.appendChild(literal);
	  Element operator2 = new Element("operator");
	  operator2.appendChild("+");
	  expr1.appendChild(operator2);
	  Element n_name3 = new Element("name");
	  expr1.appendChild(n_name3);
//	  n_name3.appendChild("t1");
	  n_name3.appendChild(value);
	  argument_list.appendChild(")");
	  element.appendChild(";");
	  return element;
	  
	  }
  
  public static void printall(Node current, int depth) {
	   
	    for (int i = 0; i < depth; i++) {
		      System.out.print(' '); 
		    }
		  
	    String obj = "";
	    if (current instanceof Element) {
	        Element temp = (Element) current;
	        obj = "<" + temp.getQualifiedName();   
	    }
	    else if (current instanceof ProcessingInstruction) {
	        ProcessingInstruction temp = (ProcessingInstruction) current;
	        obj = "<" + temp.getTarget();   
	    }
	    else if (current instanceof DocType) {
	        DocType temp = (DocType) current;
	        obj = "<" + temp.getRootElementName();   
	    }
	    else if (current instanceof Text || current instanceof Comment) {
	        String value = current.getValue();
//	        value = value.replace('\n', ' ').trim();
	        value = value.replace('\n', ' ').trim();
	        if (value.length() <= 20) obj = "<" + value;
	        else obj = "<" + current.getValue().substring(0, 17) + "...";   
	    }
	   
//	    System.out.println(current.getClass().getName() + data);
	    System.out.println(obj + ">" );
	    for (int i = 0; i < current.getChildCount(); i++) {
	    	printall(current.getChild(i), depth+1);
	    }
	    
	  }
  
  public static String find_elem(Node current) {
	   
	    String obj = "";
	    if (current instanceof Element) {
	        Element temp = (Element) current;
	        obj =temp.getQualifiedName();   
	    }
	    else if (current instanceof ProcessingInstruction) {
	        ProcessingInstruction temp = (ProcessingInstruction) current;
	        obj =temp.getTarget();   
	    }
	    else if (current instanceof DocType) {
	        DocType temp = (DocType) current;
	        obj =temp.getRootElementName();   
	    }
	    else if (current instanceof Text || current instanceof Comment) {
	        String value = current.getValue();
//	        value = value.replace('\n', ' ').trim();
	        value = value.replace('\n', ' ').trim();
	        if (value.length() <= 20) obj = "<" + value;
	        else obj =current.getValue().substring(0, 17);   
	    }

	    return obj;
	  }
  public static void listallChildren(Element current, int depth) {
	   
	    
	    System.out.println(current.getQualifiedName());
	    for (Element child : current.getChildElements()) {
	      listallChildren(child, depth+1);
	    }
	    
	  }
  
  public static void extract_info(Element element, Document doc) 
	      throws IOException {        
//	        Element root = doc.getRootElement();
	        for (Element child : element.getChildElements()) {
      
	            if (child.getQualifiedName().equals("operator")) {
//	            	System.out.println("found");
	            	
	            	run_modification(child, doc);
	            }
	            else {
	                extract_info(child, doc);    
	            }  
	        } // end chapters loop

	    }

	    
	    private static void run_modification(Element elm, Document doc) 
	      throws IOException {
//	    	Element root = doc.getRootElement();
//	        String fileName = example.getAttribute("id").getValue();

	    	if (elm.getValue().equals("=")) {

	        Comment timeofchange = new Comment("Last modified date: "
		             + new java.util.Date());
	        
	    	ParentNode parent1 = elm.getParent();
	    	String value = parent1.getChild(0).getValue();
//	        System.out.println("found value:" + value);
	    	ParentNode parent2 = parent1.getParent();
	    	ParentNode parent = parent2.getParent();
//	    	System.out.println("parent value:" + parent.getValue());
	    	Element curent = (Element) parent2.copy();
	    	int currentIndex = parent.indexOf(parent2);
	    	parent2.detach();
	    	parent.insertChild(timeofchange, currentIndex - 1);
	    	parent.insertChild(curent,currentIndex);
//	    	String value = "F1" ;
	    	Element element = define_element(doc, value);
	    	 
	    	parent.insertChild(element,currentIndex + 1);
	        
//	        FileOutputStream fout = new FileOutputStream(file);
//	        Writer out = new OutputStreamWriter(fout, "UTF-8");
	        
	            // Buffering almost always helps performance a lot
	  	    File inFile = new File("outFIB.xml");
		    FileOutputStream fos = new FileOutputStream("modified_" +  inFile.getName());
//		    System.out.println("Output File name: : " + "modified_" +
//		    	    inFile.getName());
		    

		    // Using a serializer with indention set to 2 spaces,
		    // write the XML document to the file
		    Serializer output = new Serializer(fos, "ISO-8859-1");
//		    output.setIndent(2);
		    output.write(doc);
	    	}
	    	
	    	return;
	    
	    }
	  
}

	    
