package XPath;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test{

    Document doc;
    XPath xpath;

    public void loadXML() throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        doc = builder.parse("people.xml");

        //creating xpath object
        getXPathObj();
    }

    public XPath getXPathObj() {

        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
        return xpath;
    }
    
     // 1) Get description

    public Node getActivityDescription(String personID) throws XPathExpressionException {

      
        XPathExpression expr = xpath.compile("//person[@id='"+ personID + "']/activitypreference/description");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node;
    }


     // 2) Get place
public Node getActivityPlace(String personID) throws XPathExpressionException {

       
        XPathExpression expr = xpath.compile("//person[@id='"+ personID + "']/activitypreference/place");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node;
    }


      // 3) Get all people
 public NodeList getAllPeople() throws XPathExpressionException{
        XPathExpression expr = xpath.compile("/people");
        //Object result = expr.evaluate(doc, XPathConstants.NODESET);
        Node result =(Node) expr.evaluate(doc, XPathConstants.NODE);
	NodeList nodes = (NodeList) result;
        //NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);;
        return nodes;
}


    // 4) Get Activitypreference

public Node getActivitypreference(String personID) throws XPathExpressionException {

      
        XPathExpression expr = xpath.compile("//person[@id='" + personID + "']/activitypreference");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node;
    }


   //A function which accepts a startdate and an operator (=, > , <) as parameters and prints people that fulfill that condition
    public NodeList getPeopleByStartdate(String startdate, String condition) throws XPathExpressionException {
        
        XPathExpression expr = xpath.compile("//person[activitypreference/startdate " + condition + "'" + startdate + "']");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
      return nodes;
    }


public static void main(String[] args) throws ParserConfigurationException, SAXException,
            IOException, XPathExpressionException {

       Test test = new Test();
        test.loadXML();


        /*/getting Description by ID number
        Node node = test.getActivityDescription("0001");
        System.out.println("\nthe weight of person with id 0001\n");
        System.out.println(node.getTextContent());
        
        //getting Place by ID number
        Node node1 = test.getActivityPlace("0003");
        System.out.println("\nthe height of person with id 0003\n");
        System.out.println(node1.getTextContent());*/
       
        //getting all people
        NodeList node2 = test.getAllPeople();
        System.out.println(node2.getLength());
        System.out.println("\ngetting all people\n");
        for (int i = 0; i < node2.getLength(); i++) {

            System.out.println(node2.item(i).getTextContent());

       }

        //getting Activity Preference by ID number
        Node node3 = test.getActivitypreference("0005");
        System.out.println("\nthe Activitypreference of person with id 0005\n");
        System.out.println(node3.getTextContent());


       
       //prints people that fulfill a condition
       NodeList node4 = test.getPeopleByStartdate("2017-10-13",">");
       System.out.println("\ngetting people with startdate >2017-13-10\n");
       for (int i = 0; i < node4.getLength(); i++) {

            System.out.println(node4.item(i).getTextContent());

       }

       //prints people that fulfill a condition =
       NodeList node5 = test.getPeopleByStartdate("2017-13-10","=");
       System.out.println("\ngetting people with startdate =2017-13-10\n");
       for (int i = 0; i < node4.getLength(); i++) {

            System.out.println(node4.item(i).getTextContent());

       }


    }
}
