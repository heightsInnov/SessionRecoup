/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.jobs;

import com.unionbankng.ussd.utils.MiServeImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author oawe
 */
public class TokenRefresh {

    static Logger log = LogManager.getLogger(TokenRefresh.class);

    public static void execute() {
        log.info("token refreshed started");
        try {
            MiServeImpl.getToken();
        } catch (Exception ex) {
            log.error(ex);
        }
        log.info("token refreshed ended");
    }

}
