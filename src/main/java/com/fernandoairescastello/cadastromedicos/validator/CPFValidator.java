package com.fernandoairescastello.cadastromedicos.validator;

import java.util.InputMismatchException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.fernandoairescastello.cadastromedicos.dao.MedicoDao;

@FacesValidator("cpf")
public class CPFValidator implements Validator {
    
    @EJB
    private MedicoDao dao;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String cpf = value.toString().replaceAll("[\\.-]", "");
        if (!isValidCpf(cpf))
            throw new ValidatorException(new FacesMessage("CPF inválido"));
        
        boolean novo = (boolean)component.getAttributes().get("novo");
        if (novo && existsWithCpf(cpf))
            throw new ValidatorException(new FacesMessage("CPF já cadastrado"));
    }
    
    public boolean isValidCpf(String CPF) {
        
        if (CPF.matches("([0-9])\\1{10}") || CPF.length() != 11)
            return false;
          
        char dig10, dig11;
        int sm, i, r, num, peso;
          
        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {              
                num = (int)(CPF.charAt(i) - 48); 
                sm = sm + (num * peso);
                peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char)(r + 48);
          
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else 
                dig11 = (char)(r + 48);
          
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return true;
            else
                return false;
        }
        catch (InputMismatchException erro) {
            return false;
        }
    }
    
    public boolean existsWithCpf(String cpf) {
        return dao.existsWithCpf(cpf);
    }
}
