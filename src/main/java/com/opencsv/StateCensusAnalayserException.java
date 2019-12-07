package com.opencsv;

public class StateCensusAnalayserException extends Exception {
    enum ExceptionType {
        NO_SUCH_METHOD, NO_SUCH_FILE, DELIMETER_EXCEPTION,INPUT_OUTPUT_ISSUE,INVOCATION_TARGET,ILLEGAL_ACCESS
    }

    ExceptionType type;

    public StateCensusAnalayserException(ExceptionType type) {
        this.type = type;
    }

    public StateCensusAnalayserException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
