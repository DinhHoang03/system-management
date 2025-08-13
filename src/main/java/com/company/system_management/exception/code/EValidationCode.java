package com.company.system_management.exception.code;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EValidationCode {
    UNKNOWN_VALIDATOR(100, "Unknown Validator", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(101, "Username must be equal or higher than {min} character", HttpStatus.BAD_REQUEST),
    NOT_BLANK(102, "This field must not be blanked", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(103, "Please enter the valid password(Must be between 8 to 16 characters and required at least a lowercase letter, a uppercase letter and at least a special character", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(104, "Email is invalid", HttpStatus.BAD_REQUEST),
    INVALID_PHONE(105, "Phone must long as {length} character and around from 0-9", HttpStatus.BAD_REQUEST),
    INVALID_DOB(106, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    NOT_NULL(107, "This field must not null", HttpStatus.BAD_REQUEST),

    ;
    int code;
    String message;
    HttpStatusCode status;
}
