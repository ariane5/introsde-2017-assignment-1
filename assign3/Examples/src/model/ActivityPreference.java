package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
//import java.text.SimpleDateFormat;
@XmlRootElement(name="activitypreference")
@XmlType(propOrder = { "name","description", "place", "startdate" })
@XmlAccessorType(XmlAccessType.FIELD)
public class ActivityPreference {
        private String name;
	private String description; 
	private String place; 
        private String startdate; 

	public ActivityPreference(String name,String description,String place,String startdate) {

                this.name=name;
		this.description = description;
		this.place = place;
                this.startdate=startdate;
	}

	public ActivityPreference() {
                this.startdate="2014-09-20T18:00:00.000+02:00";
		this.name = "Marco";
		this.description ="dance";
               this.place="Chiesa cristiana";

	}

     
}
