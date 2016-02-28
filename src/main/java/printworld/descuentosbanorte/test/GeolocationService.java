package printworld.descuentosbanorte.test;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

import printworld.descuentosbanorte.VM.BasicStructure;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.Ubicacion;
import printworld.descuentosbanorte.services.ProgramasService;

@Path("/geolocation")
public class GeolocationService extends BasicStructure  {

	private static final long serialVersionUID = 6774854482186257536L;

	@Path("{f}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response convertFtoCfromInput(@PathParam("f") String param) throws JSONException {
		String[] output = param.split(",");
		
		if(output.length == 0){
			JsonElement elemento = inicializarConexionJsonUrl();
			dumpPromocionJSONElement(elemento);
			output = (lonTemp + ", " + latTemp ).split(",");
		}
		System.err.println("Coordenadas: " + output);
		return Response.ok(salvarToDB((output[0] + ", " + output[1]))).header("Access-Control-Allow-Origin", "*").build();
	}

	public String salvarToDB(String coordenadas) {
		String status = "34404";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(urlConnection, user, pass);
			
			//UPDATE
			String sql = "UPDATE werzeuge SET ultimaActualizacion = ?, nombre = ? "
					+ "WHERE idWerzeuge = 5";
			PreparedStatement myStmt = conexion.prepareStatement(sql);
			
			Timestamp updatedAt = new Timestamp(Calendar.getInstance().getTimeInMillis());
			myStmt.setTimestamp(1, updatedAt);
			myStmt.setString(2, coordenadas);
			myStmt.executeUpdate();
			status = "C004D3N4D4 0K";

		} catch (Exception e) {
			System.err.println(e);
		}
		return status;
	}

	
	
	@SuppressWarnings("static-access")
	public JsonElement inicializarConexionJsonUrl() {
		JsonElement datosGlobalesJSON = null;
		JsonParser parser;
		Calendar calendario = Calendar.getInstance();
		boolean continuar = false;
		int count = 0;
		if (datosGlobalesJSON != null) {

		} else {
			do {
				try {
					String uri = "http://ip-api.com/json";
					URL url = new URL(uri);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					JsonReader reader = new JsonReader(new InputStreamReader(con.getInputStream()));
					parser = new JsonParser();
					datosGlobalesJSON = parser.parse(reader);
					continuar = true;
				} catch (Exception e) {
					count++;
					System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
							+ calendario.getInstance().get(Calendar.MINUTE) + ":"
							+ calendario.getInstance().get(Calendar.SECOND) + " > " + e.getMessage()
							+ ": Retrying connection " + count);
				}
			} while (!continuar);

		}
		// datosGlobalesJSON = null;

		return datosGlobalesJSON;
	}
	
	
	String keyJSon;
	JsonElement valueJSon;

	JsonObject obj;
	JsonArray array;
	JsonPrimitive valor;
	
	String lonTemp = "";
	String latTemp = "";
	
	public void dumpPromocionJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			// System.out.println("Es objeto");
			obj = elemento.getAsJsonObject();
			java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				java.util.Map.Entry<String, JsonElement> entrada = iter.next();

				// **************************************************
				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();
				
				if(keyJSon.equals("lat")){
					latTemp = valueJSon.toString();
				}else if(keyJSon.equals("lon")){
					lonTemp = valueJSon.toString();
					break;
				}
				
				dumpPromocionJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpPromocionJSONElement(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
			} else if (valor.isNumber()) {
			} else if (valor.isString()) {
			}
		} else if (elemento.isJsonNull()) {
		} else {
		}
	}
}



