/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.jobs;

import com.unionbankng.ussd.db.DbImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author oawe
 */

public class SummaryUpdateJob {
    static Logger log = LogManager.getLogger(SummaryUpdateJob.class);
    public static  void execute()  {
        try {
            new DbImpl().updateSummary();
        } catch (Exception ex) {
            log.error(ex);
        }
    }
}
