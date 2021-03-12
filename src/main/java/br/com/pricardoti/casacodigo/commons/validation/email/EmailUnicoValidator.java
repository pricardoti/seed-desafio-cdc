package br.com.pricardoti.casacodigo.commons.validation.email;

import br.com.pricardoti.casacodigo.autor.AutorRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, String> {

    private final AutorRepository autorRepository;

    public EmailUnicoValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public boolean isValid(
            final String email,
            final ConstraintValidatorContext constraintValidatorContext
    ) {
        return autorRepository.findFirstByEmail(email).isPresent();
    }
}
