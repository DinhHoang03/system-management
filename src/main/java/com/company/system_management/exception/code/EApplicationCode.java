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
public enum EApplicationCode {
    UNCATEGORIZED_EXCEPTION(1001, "Unknow exception!", HttpStatus.INTERNAL_SERVER_ERROR),
    REQUEST_NULL(1002, "Request is null!", HttpStatus.BAD_REQUEST),
    ACCOUNT_EXIST(1003, "This user account is already exist!", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_EXIST(1004, "This user account is not exist!", HttpStatus.BAD_REQUEST),
    LIST_EMPTY(1005, "The current list is empty", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.BAD_REQUEST),

    ;
    int code;
    String message;
    HttpStatusCode status;
}
