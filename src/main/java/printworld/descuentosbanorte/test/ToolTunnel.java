package printworld.descuentosbanorte.test;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import printworld.descuentosbanorte.VM.BasicStructure;

@Path("/api")
public class ToolTunnel extends BasicStructure {
	
	
	private static final long serialVersionUID = 4341908636931665803L;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response convertFtoC() throws JSONException {
		if(verificarActualizacion()){
			System.err.println("Conectando a repositorio...");
			JsonElement elemento = inicializarConexionJsonUrl();
			
			UtilsRestFul utilsRestFul = new UtilsRestFul();
			utilsRestFul.dumpProgramaJSONElement(elemento);
			salvarToDB(elemento, utilsRestFul);
			System.err.println("Datos obtenidos");
			return Response.ok(String.valueOf(elemento)).header("Access-Control-Allow-Origin", "*").build();
		}else{
			System.err.println("No se necesita actualizar");
			return Response.ok(String.valueOf("")).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@Path("{f}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response convertFtoCfromInput(@PathParam("f") int select) throws JSONException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(urlConnection, user, pass);
			Statement st = conexion.createStatement();
			
			//************* select ***********************
			int id = 0;
			ResultSet rs = st.executeQuery("SELECT * FROM werzeuge");
			while (rs.next()) {
				id = Integer.parseInt(String.valueOf(rs.getObject("idWerzeuge")));
				if(select == id){
					JsonReader reader = new JsonReader(new StringReader(new String(rs.getBytes("jSonSource"))));
					parser = new JsonParser();
					datosGlobalesJSON = parser.parse(reader);
					System.err.println("Datos obtenidos");
					break;
				}
			}
			rs.close();
			//************* select ***********************

		} catch (Exception e) {
			System.err.println(e);
		}
		
		return Response.ok(String.valueOf(datosGlobalesJSON)).header("Access-Control-Allow-Origin", "*").build();
	}

	public void salvarToDB(JsonElement elemento, UtilsRestFul utilsRestFul) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(urlConnection, user, pass);
			Statement st = conexion.createStatement();

			// UPDATE
			
			String sql = "UPDATE werzeuge SET jSonSource = ?, ultimaActualizacion = ? WHERE idWerzeuge = 1";
			PreparedStatement myStmt = conexion.prepareStatement(sql);
			myStmt.setBytes(1, elemento.toString().getBytes());
			Timestamp updatedAt = new Timestamp(Calendar.getInstance().getTimeInMillis());
			myStmt.setTimestamp(2, updatedAt);
			myStmt.executeUpdate();
			
			
			sql = "UPDATE werzeuge SET jSonSource = ?, ultimaActualizacion = ?  WHERE idWerzeuge = 2";
			myStmt = conexion.prepareStatement(sql);
			myStmt.setBytes(1, utilsRestFul.getProgramas().toString().getBytes());
			updatedAt = new Timestamp(Calendar.getInstance().getTimeInMillis());
			myStmt.setTimestamp(2, updatedAt);
			myStmt.executeUpdate();
			
			sql = "UPDATE werzeuge SET jSonSource = ?, ultimaActualizacion = ?  WHERE idWerzeuge = 3";
			myStmt = conexion.prepareStatement(sql);
			myStmt.setBytes(1, utilsRestFul.getCategorias().toString().getBytes());
			updatedAt = new Timestamp(Calendar.getInstance().getTimeInMillis());
			myStmt.setTimestamp(2, updatedAt);
			myStmt.executeUpdate();
			
			sql = "UPDATE werzeuge SET jSonSource = ?, ultimaActualizacion = ?   WHERE idWerzeuge = 4";
			myStmt = conexion.prepareStatement(sql);
			myStmt.setBytes(1, utilsRestFul.getPromociones().toString().getBytes());
			updatedAt = new Timestamp(Calendar.getInstance().getTimeInMillis());
			myStmt.setTimestamp(2, updatedAt);
			myStmt.executeUpdate();
			
			//************* select ***********************
			ResultSet rs = st.executeQuery("SELECT * FROM categoria");
			while (rs.next()) {
				System.out.println("nombre=" + rs.getObject("nombreCategoria"));
			}
			rs.close();
			//************* select ***********************

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private boolean verificarActualizacion(){
		boolean centinela = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(urlConnection, user, pass);
			Statement st = conexion.createStatement();

			Date localTime = new Date(); 
			DateFormat converter = new SimpleDateFormat("yyyy/MM/dd:HH:mm:ss");
			converter.setTimeZone(TimeZone.getTimeZone("GMT"));
			converter.format(localTime);
			
			//************* select ***********************
			ResultSet rs = st.executeQuery("SELECT * FROM werzeuge WHERE idWerzeuge = 1");
			long horasTranscurridas = 0;
			while (rs.next()) {
				Timestamp timestamp = rs.getTimestamp("ultimaActualizacion");
				Date actualizacion = timestamp;
				
				horasTranscurridas = diferenciaFechas(localTime, actualizacion);
				
			}
			if(horasTranscurridas >= 3)
				centinela = true;
			rs.close();
			//************* select ***********************

		} catch (Exception e) {
			System.err.println(e);
		}
		
		return centinela;
		
	}
	
	
	private long diferenciaFechas(Date local, Date actualizacion){
		
		
		Calendar calendarLocal = Calendar.getInstance();
		calendarLocal.setTime(local);
		
		Calendar calendarUpdate= Calendar.getInstance();
		calendarUpdate.setTime(actualizacion);

		
		long milis1 = calendarLocal.getTimeInMillis();
		long milis2 = calendarUpdate.getTimeInMillis();
		
		// calcular la diferencia en milisengundos
		long diff = milis2 - milis1;
		
		// calcular la diferencia en segundos
		long diffSeconds = diff / 1000;
		 
		// calcular la diferencia en minutos
		long diffMinutes = diff / (60 * 1000);		
		
		// calcular la diferencia en horas
		long diffHours = diff / (60 * 60 * 1000);
		
		// calcular la diferencia en dias
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		return diffHours * -1;
	}
}


