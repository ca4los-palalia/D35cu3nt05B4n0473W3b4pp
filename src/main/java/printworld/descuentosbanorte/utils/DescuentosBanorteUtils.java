/**
 * 
 */
package printworld.descuentosbanorte.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Repository;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

/**
 * @author Carlos Palalía López
 */
@Repository
public class DescuentosBanorteUtils {

	private static final String ALGORITHM = "md5";
	private static final String DIGEST_STRING = "HG58YZ3CR9";
	private static final String CHARSET_UTF_8 = "utf-8";
	private static final String SECRET_KEY_ALGORITHM = "DESede";
	private static final String TRANSFORMATION_PADDING = "DESede/CBC/PKCS5Padding";

	private DecimalFormat format = new DecimalFormat(
			DescuentosBanorteConstants.CURRENCY_FORMAT);

	/**
	 * Create a window programmatically and use it as a modal dialog. eg
	 * /widgets/window/modal_dialog/employee_dialog.zul
	 */

	public Window createModelDialog(final String locationView) {
		Window window = (Window) Executions.createComponents(locationView,
				null, null);
		return window;
	}

	/**
	 * Create a window programmatically and use it as a modal dialog. eg
	 * /widgets/window/modal_dialog/employee_dialog.zul
	 */

	public Window createModelDialogWithParams(final String locationView,
			Map<String, Object> params) {
		Window window = (Window) Executions.createComponents(locationView,
				null, params);
		return window;
	}

	/** Redirect to a new web page eg /login.zul */
	public void redirect(final String page) {
		Executions.getCurrent().sendRedirect(page);
	}

	/**
	 * Notificador de mensajes en vista
	 * 
	 * @param Mensaje
	 * @param Clients
	 *            .NOTIFICATION_TYPE_INFO
	 */
	public static void showSuccessmessage(String mensaje, String tipo,
			Integer duracionEnVista, Component componente) {
		Clients.showNotification(mensaje, tipo, componente, null,
				duracionEnVista);
	}

	public String formatCurrency(Double quantity) {
		if (quantity != null) {
			return format.format(quantity);
		}
		return null;
	}

	public Calendar convertirDateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public Date convertirCalendarToDate(Calendar calendar) {
		Date date = calendar.getTime();
		return date;
	}

	public String convertirCalendarToString(Calendar calendar) {
		Date date = convertirCalendarToDate(calendar);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	public Calendar convertirStringToCalendar(Integer dia, Integer mes, Integer anyo){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, anyo);
		calendar.set(Calendar.MONTH, (mes-1)); // OJO: Recueda que los valores de los meses comienzan por 0.
		calendar.set(Calendar.DATE, dia);
		return calendar;
	}
	
	/* Encryption Method */
	public static String encrypt(String message) {
		try {
			final MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			final byte[] digestOfPassword = md.digest(DIGEST_STRING
					.getBytes(CHARSET_UTF_8));
			final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			for (int j = 0, k = 16; j < 8;) {
				keyBytes[k++] = keyBytes[j++];
			}

			final SecretKey key = new SecretKeySpec(keyBytes,
					SECRET_KEY_ALGORITHM);
			final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
			final Cipher cipher = Cipher.getInstance(TRANSFORMATION_PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);

			final byte[] plainTextBytes = message.getBytes(CHARSET_UTF_8);
			final byte[] cipherText = cipher.doFinal(plainTextBytes);

			return new String(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* Decryption Method */
	public static String decrypt(String message) {
		try {
			final MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			final byte[] digestOfPassword = md.digest(DIGEST_STRING
					.getBytes(CHARSET_UTF_8));
			final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			for (int j = 0, k = 16; j < 8;) {
				keyBytes[k++] = keyBytes[j++];
			}

			final SecretKey key = new SecretKeySpec(keyBytes,
					SECRET_KEY_ALGORITHM);
			final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
			final Cipher decipher = Cipher.getInstance(TRANSFORMATION_PADDING);
			decipher.init(Cipher.DECRYPT_MODE, key, iv);

			final byte[] plainText = decipher.doFinal(message.getBytes());

			return new String(plainText, CHARSET_UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getFechaActualConHora(Date date) {
		if(date == null)
			date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return hourdateFormat.format(date);
	}

	public FileInputStream getLogotipoDeOrganizacionParaJasper(
			String nombreAtchivo) {
		File archivoLogotipo = new File(
				DescuentosBanorteConstants.CARPETA_ARCHIVOS_LOGOTIPOS + nombreAtchivo);
		FileInputStream streamLogotipo = null;
		if (archivoLogotipo.exists()) {
			try {
				streamLogotipo = new FileInputStream(archivoLogotipo);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return streamLogotipo;
	}
	
	//Convierte texto de ISO-8859-1 a  UTF-8
	public static String convertFromUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }
	
	//Convierte texto de UTF-8 a  ISO-8859-1
	public static String convertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }
	
	//Escribir un archivo al disco duro
	//writeTo(urlImage.openStream(), new FileOutputStream(new File("c:/printworld/imagesJson/"+stringOut.substring(44) + ".jpg")));
	public static void writeTo(InputStream in, OutputStream out) throws IOException {
		try {
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}

		return;
	}
	
	public AImage convertirImagenJSonToAIMage(String urlString){
		URL urlImage = null;
		AImage aImage = null;
		ByteArrayOutputStream baos = null;

		try {
			if(urlString != null){
				if(!urlString.equals("null")){
					urlImage = new URL(urlString);
					byte[] imageInByte;
					BufferedImage originalImage = ImageIO.read(urlImage);

					baos = new ByteArrayOutputStream();
					ImageIO.write(originalImage, "jpg", baos);
					baos.flush();
					imageInByte = baos.toByteArray();
					aImage = new AImage(urlString.substring(44), imageInByte);
				}
			}
			if(baos != null)
				baos.close();
		} catch (IOException e) {
			System.err.println(urlString);
			System.out.println(e.getMessage());
			try {
				if(baos != null)
					baos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return aImage;
	}
	
	
	public byte[] getBytesDeObjeto(Object objeto){
		ByteArrayOutputStream out = null;
	    ObjectOutputStream os = null;
	    byte[] bytes = null;
		try {
	    	out = new ByteArrayOutputStream();
			os = new ObjectOutputStream(out);
			os.writeObject(String.valueOf(objeto));
			bytes = out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	public Date convertirStringToDate(String fechaString){
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
			date = formatter.parse(fechaString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public boolean convertirCadenaToBoolean(String value){
		if(value.equals("false"))
			return false;
		else
			return true;
	}
	
	public String suprimirComillas(String input) {
		String salida = input;
		if (input.contains("\""))
			salida = input.substring(1, (input.length() - 1));
		return salida;
	}
}
