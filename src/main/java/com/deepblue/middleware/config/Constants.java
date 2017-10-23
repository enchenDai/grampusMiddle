package com.deepblue.middleware.config;

import java.util.Locale;

/**
 * Application constants.
 */
public final class Constants {

    //palmset name prefix
    public static final String PALMSET_NAME_PREFIX = "deepblue_palmset_";

    //palmset max n value
    public static final String PALMSET_MAX_N_VALUE = "9999";

    //system constant
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    public static final String SPRING_PROFILE_CLOUD = "cloud";
    public static final String SPRING_PROFILE_HEROKU = "heroku";
    public static final String SPRING_PROFILE_SWAGGER = "swagger";
    public static final String SPRING_PROFILE_NO_LIQUIBASE = "no-liquibase";

    //Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final String CHAR_SET_UTF_8 = "utf-8";

    /**
     * languae
     */
    public static final String DEFAULT_LANGUAGE = "zh_CN";
    public static final String EN_LANGUAGE = "en";

    public static final Locale DEFAULT_LOCALE = new Locale(DEFAULT_LANGUAGE);

    public static final Long SYSTEM_COMPANY = 1L;

    public static final Long DEFAULT_APP = 1L;

    public static final String DEFAULT_APP_STRING = "1";

    public static final Long DELIVERY_PERMISSION_ID = 21L;

    public static final String DEFAULT_PAGE_NO = "1";

    public static final String DEFAULT_PAGE_SIZE = "20";

    private Constants() {
    }
}
