package com.fernandoairescastello.cadastromedicos.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("email")
public class EmailValidator implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String email = value.toString();
        boolean valido = org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(email);
        if (!valido)
            throw new ValidatorException(new FacesMessage("Email inválido"));
    }
}
