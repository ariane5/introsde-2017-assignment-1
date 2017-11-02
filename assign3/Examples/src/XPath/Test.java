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
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
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


// 4)This function Get Activitypreference by person Id 

public Node getActivitypreference(String personID) throws XPathExpressionException {

      
        XPathExpression expr = xpath.compile("//person[@id='" + personID + "']/activitypreference");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node;
    }


 


// 6) This function prints out all the startdate
public NodeList getStartdate() throws XPathExpressionException {
        
        //XPathExpression expr = xpath.compile("//book[price " + condition + "'" + price + "']");
        
        XPathExpression expr = xpath.compile("//person/activitypreference/startdate");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        return nodes;
        }

//This functionc prints all people by startdate
public NodeList getPersonByActivity(String startdate) throws XPathExpressionException {
        

        
        XPathExpression expr = xpath.compile("//person[activitypreference/startdate ='" + startdate + "']");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        return nodes;
        }


public NodeList getPersonByActivity1(String startdate) throws XPathExpressionException {
        
        //XPathExpression expr = xpath.compile("//book[price " + condition + "'" + price + "']");
        
        XPathExpression expr = xpath.compile("//person[activitypreference/startdate ='" + startdate + "']");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        return nodes;
        }



        
    



public static void main(String[] args) throws ParserConfigurationException, SAXException,
            IOException, XPathExpressionException {

       Test test = new Test ();
        test.loadXML();


        //getting Description by ID number
        Node node = test.getActivityDescription("0001");
        System.out.println("\nthe weight of person with id 0001\n");
        System.out.println(node.getTextContent());
        
        //getting Place by ID number
        Node node1 = test.getActivityPlace("0003");
        System.out.println("\nthe height of person with id 0003\n");
        System.out.println(node1.getTextContent());
       
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



        //prints people that fulfill a condition >2017-13-10
       NodeList node6 = test.getStartdate();
       System.out.println("\ngetting people with startdate >2017-13-10\n");
       String startdate="2017-10-13";
       for (int j= 0; j < node6.getLength(); j++) {
            String ch=node6.item(j).getTextContent();
            //System.out.println(node6.item(i).getTextContent());


           try{
          String dateReceivedFromUser = ch;
          DateFormat userDateFormat = new SimpleDateFormat("yyyy-MM-dd");
          DateFormat dateFormatNeeded = new SimpleDateFormat("yyyyMMdd");
          Date date = userDateFormat.parse(dateReceivedFromUser);
          String convertedDate = dateFormatNeeded.format(date);
          int dateToint = Integer.parseInt(convertedDate);

          DateFormat userDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
          DateFormat dateFormatNeeded1 = new SimpleDateFormat("yyyyMMdd");
          Date date1 = userDateFormat1.parse(startdate);
          String convertedDate1 = dateFormatNeeded1.format(date1);
          int dateToint1= Integer.parseInt(convertedDate1);
        if(dateToint>dateToint1){

        
           NodeList node8 = test.getPersonByActivity(ch);
        //System.out.println(node8.getLength());
        System.out.println("\ngetting the "+j+"th\n");
        for (int i = 0; i < node8.getLength(); i++) {

            System.out.println(node8.item(i).getTextContent());

            }

            }
         // System.out.println(convertedDate);
         // System.out.println(convertedDate1);
        
            }
  catch(Exception e){}
 
            }



      
       //prints people that fulfill a condition = startdate
       NodeList node10 = test.getStartdate();
       System.out.println("\ngetting people with startdate =2017-13-10\n");
     
       for (int j= 0; j < node10.getLength(); j++) {
            String ch=node10.item(j).getTextContent();
            


           try{
          String dateReceivedFromUser = ch;
          DateFormat userDateFormat = new SimpleDateFormat("yyyy-MM-dd");
          DateFormat dateFormatNeeded = new SimpleDateFormat("yyyyMMdd");
          Date date = userDateFormat.parse(dateReceivedFromUser);
          String convertedDate = dateFormatNeeded.format(date);
          int dateToint = Integer.parseInt(convertedDate);

          DateFormat userDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
          DateFormat dateFormatNeeded1 = new SimpleDateFormat("yyyyMMdd");
          Date date1 = userDateFormat1.parse(startdate);
          String convertedDate1 = dateFormatNeeded1.format(date1);
          int dateToint1= Integer.parseInt(convertedDate1);
        if(dateToint==dateToint1){

        
           NodeList node9 = test.getPersonByActivity(ch);
           System.out.println("\ngetting the "+j+"th\n");
           System.out.println(node9.item(node9.getLength()-1).getTextContent());

      

            }
            }
  catch(Exception e){}

            }
            }
}
