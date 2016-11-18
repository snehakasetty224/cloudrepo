package cloudproject;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/")
public class Controller {
	@Path("status")
	@GET
	@Produces("application/json")
	public String getOnStatus() throws ClassNotFoundException, SQLException {
		int actcount = Mysqlquery.main("SELECT count(*) as count FROM Hosts where status=1");
		int inactcount = Mysqlquery.main("SELECT count(*) as count FROM Hosts where status=0");
		return "{\"active\":\""+actcount+"\",\"inactive\":\""+inactcount+"\"}";
	}
	
	@Path("sensor")
	@GET
	@Produces("application/json")
	public String getOffStatus() throws ClassNotFoundException, SQLException{ 
	int location = Mysqlquery.main("SELECT count(*) as count FROM Sensors where type=1");
	int clipper = Mysqlquery.main("SELECT count(*) as count FROM Sensors where type=2");
	int speed = Mysqlquery.main("SELECT count(*) as count FROM Sensors where type=3");
	int temperature = Mysqlquery.main("SELECT count(*) as count FROM Sensors where type=4");
		return "{\"location\":\""+location+"\",\"clipper\":\""+clipper+"\",\"speed\":\""+speed+"\",\"temperature\":\""+temperature+"\"}";
	}
	
}