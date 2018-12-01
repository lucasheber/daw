package distribuidora.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("comecaComMaiuscula")
public class ComecaComMaiusculo {

	// validando 
	public void comecaComMaiuscula(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		
		if (!valor.matches("[A-Z].*")) {
			throw new ValidatorException(new FacesMessage("Deve comecar com maiuscula"));
		}
	}
}
