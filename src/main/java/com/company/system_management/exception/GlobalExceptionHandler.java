package com.company.system_management.exception;

import com.company.system_management.custom.APIResponse;
import com.company.system_management.exception.code.EApplicationCode;
import com.company.system_management.exception.code.EValidationCode;
import com.company.system_management.exception.custom.AppException;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //Application Handler
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<APIResponse> handlingAppException(AppException appException) {
        EApplicationCode EApplicationCode = appException.getEApplicationCode();
        APIResponse apiResponse = new APIResponse();

        apiResponse.setCode(EApplicationCode.getCode());
        apiResponse.setMessage(EApplicationCode.getMessage());

        return ResponseEntity
                .status(EApplicationCode.getStatus())
                .body(apiResponse);
    }

    //Handling Unknown Exception
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<APIResponse> handlingUnknownException(Exception exception) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(EApplicationCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(EApplicationCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    //Handling Validation Exception
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<APIResponse<Object>> handlingValidationException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String, String> errors = new HashMap<>();
        int code = HttpStatus.BAD_REQUEST.value();

        for(FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            String field = error instanceof FieldError fieldError ? fieldError.getField() : "unknow error";
            String errorCode = error.getDefaultMessage();

            EValidationCode EValidationCode = getValidationCode(errorCode);
            String message = EValidationCode.getMessage();

            try {
                ConstraintViolation<?> violation = error.unwrap(ConstraintViolation.class);
                Map<String, Object> attributes = violation.getConstraintDescriptor().getAttributes();
                message = mapAttributesToMessage(message, attributes);
            } catch (Exception exception) {
                log.debug("Cannot unwrap constraint violation or get attributes", exception);
            }
            errors.put(field, message);
            code = EValidationCode.getCode();
        }

        return ResponseEntity
                .badRequest()
                .body(
                        APIResponse.builder()
                                .code(code)
                                .result(errors)
                                .message("Validation handled successfully")
                                .build()
                );
    }

    //Get Validation Code inside validator message
    private EValidationCode getValidationCode(String errorCode) {
        for (EValidationCode EValidationCode : EValidationCode.values()) {
            if (EValidationCode.name().equals(errorCode)) {
                return EValidationCode;
            }
        }
        return EValidationCode.UNKNOWN_VALIDATOR;
    }

    //Map attributes from business input to display into message
    private String mapAttributesToMessage(String template, Map<String, Object> attributes) {
        String result = template;
        for(Map.Entry<String, Object> entry : attributes.entrySet()) {
            String placeHolder = "{" + entry.getKey() + "}";
            result = result.replace(placeHolder, String.valueOf(entry.getValue()));
        }
        return result;
    }
}
