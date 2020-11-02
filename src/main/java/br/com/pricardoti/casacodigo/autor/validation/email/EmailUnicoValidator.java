package br.com.pricardoti.casacodigo.autor.validation.email;

import br.com.pricardoti.casacodigo.autor.AutorRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, String> {

    private final AutorRepository autorRepository;

    public EmailUnicoValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !this.autorRepository.findByEmail(email).isPresent();
    }
}
