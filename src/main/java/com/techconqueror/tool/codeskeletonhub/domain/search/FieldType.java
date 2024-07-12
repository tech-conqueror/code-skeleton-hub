package com.techconqueror.tool.codeskeletonhub.domain.search;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public enum FieldType {

    BOOLEAN {
        public Object parse(final String value) {
            return Boolean.valueOf(value);
        }
    },
    CHAR {
        public Object parse(final String value) {
            return value.charAt(0);
        }
    },
    DATE {
        public Object parse(final String value) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            return LocalDateTime.parse(value, formatter);
        }
    },
    DOUBLE {
        public Object parse(final String value) {
            return Double.valueOf(value);
        }
    },
    INTEGER {
        public Object parse(final String value) {
            return Integer.valueOf(value);
        }
    },
    LONG {
        public Object parse(final String value) {
            return Long.valueOf(value);
        }
    },
    STRING {
        public Object parse(final String value) {
            return value;
        }
    };

    public abstract Object parse(final String value);
}
