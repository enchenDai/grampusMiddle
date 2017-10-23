package com.deepblue.middleware.exception;



import com.deepblue.middleware.config.Constants;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by enchen on 15/12/30.
 */
public class ObjectNotFoundException extends RuntimeException {

    private Object object;

    private Locale locale = Constants.DEFAULT_LOCALE;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public ObjectNotFoundException(Class targetObject, Long targetObjectID) {
        super(targetObject.getSimpleName() + "[id=" + targetObjectID + "] not found");
        this.object = targetObject;
    }

    public ObjectNotFoundException(Class targetObject, Integer targetObjectID) {
        super(targetObject.getSimpleName() + "[id=" + targetObjectID + "] not found");
        this.object = targetObject;
    }

    public ObjectNotFoundException(Class targetObject, UUID targetObjectOID) {
        super(targetObject.getSimpleName() + "[id=" + targetObjectOID + "] not found");
        this.object = targetObject;
    }

    public ObjectNotFoundException(Class targetObject, String message) {
        super(targetObject.getSimpleName() + "[" + message + "] not found");
        this.object = targetObject;
    }

    public ObjectNotFoundException(Class targetObject, Long targetObjectID, Locale locale) {
        super(targetObject.getSimpleName() + "[id=" + targetObjectID + "] not found");
        this.object = targetObject;
        this.locale = locale;
    }

    public ObjectNotFoundException(Class targetObject, UUID targetObjectOID, Locale locale) {
        super(targetObject.getSimpleName() + "[id=" + targetObjectOID + "] not found");
        this.object = targetObject;
        this.locale = locale;
    }

    public ObjectNotFoundException(Class targetObject, String message, Locale locale) {
        super(targetObject.getSimpleName() + "[" + message + "] not found");
        this.object = targetObject;
        this.locale = locale;
    }
}
