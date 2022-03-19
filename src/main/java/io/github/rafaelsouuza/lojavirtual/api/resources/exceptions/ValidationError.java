package io.github.rafaelsouuza.lojavirtual.api.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1L;

    List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void setErrors(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
