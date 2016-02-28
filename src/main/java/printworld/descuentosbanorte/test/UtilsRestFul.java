package printworld.descuentosbanorte.test;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class UtilsRestFul {

	private JsonObject obj;
	private String keyJSon;
	private JsonElement valueJSon;
	private JsonElement programas;
	private JsonElement categorias;
	private JsonElement promociones;
	private JsonArray array;
	private JsonPrimitive valor;
	
	public ArrayList<JsonElement> datos;

	// ************ PROGRAMAS FROM JSON ***************************
	public void dumpProgramaJSONElement(JsonElement elemento) {
		datos = new ArrayList<JsonElement>();
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				java.util.Map.Entry<String, JsonElement> entrada = iter.next();

				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();

				if (keyJSon.equals("programas")) {
					programas = valueJSon;
					datos.add(valueJSon);
				} else if (keyJSon.equals("categorias")) {
					categorias = valueJSon;
					datos.add(valueJSon);
				} else if (keyJSon.equals("promociones")) {
					promociones = valueJSon;
					datos.add(valueJSon);
				}
				dumpProgramaJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpProgramaJSONElement(entrada);
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

	public ArrayList<JsonElement> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<JsonElement> datos) {
		this.datos = datos;
	}

	public JsonElement getProgramas() {
		return programas;
	}

	public void setProgramas(JsonElement programas) {
		this.programas = programas;
	}

	public JsonElement getCategorias() {
		return categorias;
	}

	public void setCategorias(JsonElement categorias) {
		this.categorias = categorias;
	}

	public JsonElement getPromociones() {
		return promociones;
	}

	public void setPromociones(JsonElement promociones) {
		this.promociones = promociones;
	}
	
	

}
