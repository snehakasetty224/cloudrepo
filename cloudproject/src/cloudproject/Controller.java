package cloudproject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Controller {
	//to get the Status of Vehicle , weather it is "ON" or " OFF"
	@Path("status")
	@GET
	@Produces("application/json")
	public String getOnStatus(@QueryParam("user") String user, 
			@QueryParam("role") String role) throws ClassNotFoundException, SQLException {
		int actcount = 0;
		int inactcount = 0;
		if(role.equals("admin")){
			actcount = Mysqlquery.getCount("SELECT count(*) as count FROM Hosts where status=1");
			inactcount = Mysqlquery.getCount("SELECT count(*) as count FROM Hosts where status=0");
		}else {
			actcount = Mysqlquery.getCount("SELECT count(*) as count FROM Hosts where status=1 and creator_id='"+user+"'");
			inactcount = Mysqlquery.getCount("SELECT count(*) as count FROM Hosts where status=0 and creator_id='"+user+"'");
		}
		
		return "{\"active\":\""+actcount+"\",\"inactive\":\""+inactcount+"\"}";
	}
	
	//to get the number of sensors
	@Path("sensor")
	@GET
	@Produces("application/json")
	public String getSensorNumber(
			@QueryParam("user") String user, 
			@QueryParam("role") String role) throws ClassNotFoundException, SQLException{ 
		int location = 0;
		int clipper = 0;
		int speed = 0;
		int temperature = 0;
		if(role.equals("admin")){
			location = Mysqlquery.getCount("SELECT count(*) as count FROM Sensors where type=1");
			clipper = Mysqlquery.getCount("SELECT count(*) as count FROM Sensors where type=2");
			speed = Mysqlquery.getCount("SELECT count(*) as count FROM Sensors where type=3");
			temperature = Mysqlquery.getCount("SELECT count(*) as count FROM Sensors where type=4");
		}else {
			location = Mysqlquery.getCount("SELECT count(*) as count FROM Sensors where type=1 and provider_id='"+user+"'");
			clipper = Mysqlquery.getCount("SELECT count(*) as count FROM Sensors where type=2 and provider_id='"+user+"'");
			speed = Mysqlquery.getCount("SELECT count(*) as count FROM Sensors where type=3 and provider_id='"+user+"'");
			temperature = Mysqlquery.getCount("SELECT count(*) as count FROM Sensors where type=4 and provider_id='"+user+"'");
		}

		return "{\"location\":\""+location+"\",\"clipper\":\""+clipper+"\",\"speed\":\""+speed+"\",\"temperature\":\""+temperature+"\"}";
	}
	
	//to post the login infomation username and password and return the users role (user,admin or end user) and the userid
	@Path("login")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public String login(User user) throws Exception {
		System.out.println(user.getUsername());
		ArrayList<String> result= Mysqlquery.getRole("SELECT roles, id FROM Users where email='"+user.getUsername()+"' and password='"+user.getPassword()+"'" );
		
		if(result != null)
		{
			return "{\"role\":\""+result.get(0)+"\",\"user\":\""+result.get(1)+"\"}";
		}
		else{
			return null;
		}
	}	
	
	//to get the latitude and logitude values from location table, and description from sensor table where the user id's match
	@Path("map")
	@GET
	@Produces("application/json")
	public ArrayList<Map> login(@QueryParam("user") String user, @QueryParam("role") String role) throws Exception {
		ArrayList<Map> list = null;
		if(role.equals("admin")){
			list = Mysqlquery.getMap("select l.latitude, l.longitude, s.description from LocationSensors l, Sensors s where l.sensor_id=s.id;" );
		}else {
			list = Mysqlquery.getMap("select l.latitude, l.longitude, s.description from LocationSensors l, Sensors s where l.sensor_id=s.id and  s.provider_id='"+user+"';" );
		} 
		return list;
	}
	
	@Path("billamount")
	@GET
	@Produces("application/json")
	public ArrayList<Bill> login(@QueryParam("user") String user) throws Exception {
		ArrayList<Bill> list = null;
			list = Mysqlquery.getBill("SELECT sum(amount) as sum,MONTHNAME(STR_TO_DATE(month(startDate),'%m')) as m, month(startDate) as monthnumber FROM CMPE281_2.Billings where userId='"+user+"' group by m,monthnumber order by monthnumber;" );
		
		return list;
	}
	
    
	
	
}