package printworld.descuentosbanorte.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonElement;

import printworld.descuentosbanorte.VM.BasicStructure;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.services.ProgramasService;

@Path("/tools")
public class UpdateSession extends BasicStructure {

	

	@Path("{f}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response convertFtoCfromInput(@PathParam("f") String param) throws JSONException {
		System.err.println("Parametros: " + param);
		return Response.ok(salvarToDB(Integer.parseInt(param))).header("Access-Control-Allow-Origin", "*").build();
	}

	public String salvarToDB(int programaParam) {
		String status = "34404";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(urlConnection, user, pass);
			
			//UPDATE
			String sql = "UPDATE werzeuge SET descripcion = ?, ultimaActualizacion = ?"
					+ "WHERE idWerzeuge = 5";
			PreparedStatement myStmt = conexion.prepareStatement(sql);
			myStmt.setString(1, String.valueOf(programaParam));
			Timestamp updatedAt = new Timestamp(Calendar.getInstance().getTimeInMillis());
			myStmt.setTimestamp(2, updatedAt);
			
			myStmt.executeUpdate();
			status = "P4064AM 0K";

		} catch (Exception e) {
			System.err.println(e);
		}
		return status;
	}

}
