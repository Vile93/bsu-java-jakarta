package com.example.bsu.utils;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public class ValidationFailedExceptionUtil extends Exception {
    private Map<String,List<String>> errors;
    private String message;
    private static final ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
            .configure()
            .messageInterpolator(new org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator())
            .buildValidatorFactory();
    private static final Validator validator = validatorFactory.getValidator();
    public ValidationFailedExceptionUtil() {}
    public ValidationFailedExceptionUtil(String message) {
        this.message = message;
    }
    public <T> void setErrors(final Set<ConstraintViolation<T>> violations) {
        Map<String, List<String>> errors = new HashMap<>();
        for(ConstraintViolation<T>violation : violations) {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            List<String> errorsList = errors.getOrDefault(field, new ArrayList<>());
            errorsList.add(message);
            errors.put(field, errorsList);
        }
        this.errors = errors;
    }
    public String getJSONMessage() {
        if(this.message != null) {
            return "{ \"message\" : \"" + this.message + "\"}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"errors\": { ");
        int errorsLength = errors.size();
        int currIndex = 0;
        for (Map.Entry<String, List<String>> entry : errors.entrySet()) {
            sb.append("\"" + entry.getKey() + "\"");
                sb.append(":[");
                int i = 0;
                for(String error : entry.getValue()) {
                    i += 1;
                    if(i != 1) {
                        sb.append(",\"" + error + "\"");
                    } else {
                        sb.append("\"" + error + "\"");
                    }
                }
                sb.append("]");
            if(currIndex != errorsLength - 1) {
                sb.append(",");
            }
            currIndex++;
        }
        sb.append("} }");
        return "{ \"message\" : " + sb.toString() + "}";
    }
    public static <T> void validate(T entity) throws ValidationFailedExceptionUtil {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if(!violations.isEmpty()){
            ValidationFailedExceptionUtil ve = new ValidationFailedExceptionUtil();
            ve.setErrors(violations);
            throw ve;
        }
    }
}
