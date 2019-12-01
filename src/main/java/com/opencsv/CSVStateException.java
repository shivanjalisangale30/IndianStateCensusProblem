package com.opencsv;

public class CSVStateException extends Exception {
    enum ExceptionType {
        ENTERED_NULL, ENTERED_EMPTY, NO_SUCH_FIELD, NO_SUCH_METHOD, NO_SUCH_CLASS,
        OBJECT_CREATION_ISSUE, METHOD_INVOCATION_ISSUE, FIELD_SETTING_ISSUE, NO_ACCESS, NO_SUCH_FILE, FILE_TYPE_NOT_SUPPORTED, DELIMETER_EXCEPTION,
        FILE_HEADER_ISSUE
    }

    ExceptionType type;

    public CSVStateException(ExceptionType type) {
        this.type = type;
    }

    public CSVStateException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
