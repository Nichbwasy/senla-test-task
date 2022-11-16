package com.senla.bank.utils.validators;

import javax.validation.*;
import java.util.Set;

public class DefaultValidator {

    public static <T> void validate(T t) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> validates = validator.validate(t);
        if (validates.size() > 0) {
            StringBuilder message = new StringBuilder("");
            validates.forEach(v -> message.append(v.getMessage()).append(" "));
            throw new ValidationException(
                    String.format("Validation exception! %s", message.toString())
            );
        }
    }
}
