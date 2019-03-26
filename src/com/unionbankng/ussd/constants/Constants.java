/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.constants;

import com.unionbankng.ussd.utils.Config;

/**
 *
 * @author oawe
 */
public class Constants {

    public static final int REFRESH_TOKEN_SCHEDULE = Config.getIntValue("jobs.refreshtoken.schedule", false);
    public static final int UPDATE_SESSION_FILE_SCHEDULE = Config.getIntValue("jobs.session.update.schedule", false);
    public final static String BASE_URL = Config.getValue("miserve.baseurl", false);
    public final static String CLIENT_SECRET = Config.getValue("miserve.clientsecret", true);
    public final static String CLIENT_ID = Config.getValue("miserve.clientid", false);
    public final static String GRANT_TYPE = Config.getValue("miserve.granttype", false);
    public final static String WS_USERNAME = Config.getValue("miserve.username", false);
    public final static String WS_PASSWORD = Config.getValue("miserve.password", true);
    public final static String AUTH_RESOURCE = Config.getValue("miserve.resource.auth", false);
    public final static String FT_RESOURCE = Config.getValue("miserve.resource.ft", false);
    public final static String LOG_PATH = Config.getValue("log.path", false);
    public final static String LOG_FILE_PATTERN = Config.getValue("log.file.pattern", false);
    public final static int LOG_MAX_FILE_SIZE = Config.getIntValue("log.file.maxsize", false);

    public final static String DB_USERNAME = Config.getValue("db.username", false);
    public final static String DB_PASSWORD = Config.getValue("db.password", true);
    public final static String DB_URL = Config.getValue("db.url", false);

    public static int NUMBER_OF_THREADS = Config.getIntValue("jobs.thread.count", false);
    public static final int DELAY = Config.getIntValue("jobs.delay", false);
	
	public static final String CREDIT_ACCOUNT = Config.getValue("credit.acc", false);

}
