package br.com.pricardoti.casacodigo.commons.validation.unique;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> entity;

    private final EntityManager entityManager;

    public UniqueValueValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(
            final UniqueValue annotation
    ) {
        this.domainAttribute = annotation.fieldName();
        this.entity = annotation.domainClass();
    }

    @Override
    public boolean isValid(
            final Object object,
            final ConstraintValidatorContext constraintValidatorContext
    ) {
        final Query query = entityManager.createQuery(
                String.format("SELECT 1 FROM %s WHERE %s = :value", entity.getName(), domainAttribute)
        );

        query.setParameter("value", object);
        List<?> result = query.getResultList();

        return result.isEmpty();
    }
}
