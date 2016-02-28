package printworld.descuentosbanorte.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import printworld.descuentosbanorte.VM.BasicStructure;

@Path("/coordenadasPromo")
public class CoordenadaPromocion extends BasicStructure {
	
	private static final long serialVersionUID = 7613369340265893824L;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getCoordenada() throws JSONException {
		System.err.println("Obteniendo coordenadas ");
		return Response.ok(verificarActualizacion()).header("Access-Control-Allow-Origin", "*").build();
	}
	
	private String verificarActualizacion(){
		String coordenadas = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(urlConnection, user, pass);
			Statement st = conexion.createStatement();
			
			//************* select ***********************
			ResultSet rs = st.executeQuery("SELECT * FROM werzeuge WHERE idWerzeuge = 6");
			while (rs.next()) {
				coordenadas = rs.getString("nombre");	
			}
			rs.close();
			//************* select ***********************

		} catch (Exception e) {
			System.err.println(e);
		}
		return coordenadas;
	}
}
