package com.example.proxy.rest.exception;

import org.springframework.stereotype.Component;


@Component
public class SQLException {

    public String getError(Exception ex){
        String msg = ex.getMessage();
        if (ex.getCause().getCause() instanceof java.sql.SQLException) {
            java.sql.SQLException e = (java.sql.SQLException) ex.getCause().getCause();
            if (e.getMessage().contains("Key")) {
                msg = e.getMessage().substring(e.getMessage().indexOf("Key"));
            }
        }
        return msg;
    }
}
