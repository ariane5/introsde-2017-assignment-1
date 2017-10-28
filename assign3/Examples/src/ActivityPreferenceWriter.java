import java.io.File;
import java.io.FileReader;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.ActivityPreference;
import model.Person;
import dao.PeopleStore;

public class ActivityPreferenceWriter {  	
	public static PeopleStore people = new PeopleStore();

	public static void initializeDB() {
		Person pallino = new Person();

		Person pallo = new Person(new Long(1), "Pallo", "Pinco", "1984-06-21");
		ActivityPreference ap2 = new ActivityPreference("running","running to the park","Gocciadoro","2014-09-20T18:00:00.000+02:00");
		Person john = new Person(new Long(2), "John", "Doe", "1985-03-20", ap2);

		people.getData().add(pallino);
		people.getData().add(pallo);
		people.getData().add(john);
	}	

	public static void main(String[] args) throws Exception {
		
		initializeDB();
		
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        m.marshal(people,new File("people1.xml")); // marshalling into a file
        m.marshal(people, System.out);			  // marshalling into the system default output
    }
}
