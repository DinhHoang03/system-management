package com.company.system_management.exception.custom;


import com.company.system_management.exception.code.EApplicationCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppException extends RuntimeException {
  EApplicationCode EApplicationCode;

  public AppException(EApplicationCode EApplicationCode) {
        super(EApplicationCode.getMessage());
        this.EApplicationCode = EApplicationCode;
    }
}
