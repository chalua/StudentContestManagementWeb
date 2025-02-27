package com.datn.qlct.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class ContrainViolationExecption extends DataIntegrityViolationException {
    public ContrainViolationExecption(String msg) {
        super(msg);
    }
}
