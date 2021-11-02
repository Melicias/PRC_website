package com.example.prc.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class MyEntityNotFoundExceptionMapper implements
        ExceptionMapper<com.example.academics.exceptions.MyEntityNotFoundException> {
    private static final Logger logger =
            Logger.getLogger("exceptions.MyEntityNotFoundExceptionMapper");
    @Override
    public Response toResponse(com.example.academics.exceptions.MyEntityNotFoundException e) {
        String errorMsg = e.getMessage();
        logger.warning("ERROR: " + errorMsg);
        return
                Response.status(Response.Status.NOT_FOUND).entity(errorMsg).build();
    }
}
