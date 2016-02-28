/**
 * 
 */
package printworld.descuentosbanorte.VM;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;

/**
 * @author Carlos Palalía López
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class HomeVM {

	@Wire("#header")
	private Div header;
	@Wire("#menu")
	private Div menu;
	@Wire("#content")
	private Div content;
	@Wire("#footer")
	private Div footer;

	@Init
	public void init() {

	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@NotifyChange("*")
	@GlobalCommand
	public void updateWorkArea(
			@BindingParam(MenuVM.PAGE_TO_RENDER) String pageToRender) {
		List<Component> components = content.getChildren();
		if (components != null) {
			components.clear();
			content.appendChild(new Include(pageToRender));
		}
	}

}
