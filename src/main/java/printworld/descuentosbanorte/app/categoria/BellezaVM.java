/**
 * 
 */
package printworld.descuentosbanorte.app.categoria;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;

import printworld.descuentosbanorte.VM.BasicStructure;

/**
 * @author Carlos Palalía López
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class BellezaVM extends BasicStructure {

	private static final long serialVersionUID = 3609891275507972007L;

	@Init
	public void init() {
		estados = estadoService.getAll();
		programa = programasService.getById(1L);
		categoria = categoriaService.getById(1L);
		comercios = comercioService.getByCategoriaPrograma(programa, categoria);
	}

	@Command("upload")
	@NotifyChange("myImage")
	public void onImageUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			int lengthofImage = media.getByteData().length;
			if (media instanceof Image) {
				/*if (lengthofSignature > 500 * 1024) {
					showInfo("Please Select a Image of size less than 500Kb.");
					return;
				} else {*/
					bannerProgramas = (AImage) media;// Initialize the bind object to
												// show image in zul page and
												// Notify it also
				//}
			} else {
				showInfo("The selected File is not an image.");
			}
		} else {
			System.err.println("Upload Event Is not Coming");
		}
	}

	protected void showInfo(String message) {
		Messagebox.show(message, "Alert !!", Messagebox.OK, Messagebox.INFORMATION);
	}

	@SuppressWarnings("static-access")
	@Command
	public void comercioSeleccionado(){
		descuentosBanorteUtils.showSuccessmessage("Comercio "
				+ comercio.getNombre() + " Seleccionado",
				Clients.NOTIFICATION_TYPE_INFO, 0, null);
		
	}
}
