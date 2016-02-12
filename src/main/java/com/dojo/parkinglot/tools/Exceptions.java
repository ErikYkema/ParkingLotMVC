package com.dojo.parkinglot.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;

public class Exceptions {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void handle (Exception ex) {
        handle(ex, "");
    }

    public static void handle (Exception ex, String acceptedSQLState) {
        if (ex instanceof SQLException) {
            SQLException sqlEx = (SQLException) ex;
            LOG.info(
                    String.format("errorCode %s sqlstate %s message %s ",
                            sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage()));
            if (acceptedSQLState.equals(((SQLException) ex).getSQLState())) {
                LOG.info("Accepted error. continuing...");
            } else {
                LOG.error(ex.getMessage());
                throw new RuntimeException(ex.getCause());
            }

        } else if (ex instanceof RuntimeException && ex.getCause() instanceof SQLException) {
            LOG.warn("Runtime Exception with nested SQLException...");
            handle((SQLException) ex.getCause(), acceptedSQLState);
        } else {
            LOG.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }

    }

}
