/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd;

import com.unionbankng.ussd.constants.Constants;
import com.unionbankng.ussd.jobs.TokenRefresh;
import com.unionbankng.ussd.jobs.TransactionProcessingJobV2;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author oawe
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
     static org.apache.logging.log4j.Logger log = LogManager.getLogger(Starter.class);
    public static void main(String[] args) {
        assertNoOtherInstanceRunning();
        try {
            System.out.println("Starting up...");
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            log.error(ex);
        }
        

        //token refresh
        final ScheduledExecutorService tokenRefreshService = Executors.newSingleThreadScheduledExecutor();
        Runnable runRefreshToken = () -> {
            TokenRefresh.execute();
        };
        tokenRefreshService.scheduleAtFixedRate(runRefreshToken, 0, Constants.REFRESH_TOKEN_SCHEDULE, TimeUnit.SECONDS);

        //posting of transaction to cba
        TransactionProcessingJobV2.processJob();

    }

    private static void assertNoOtherInstanceRunning() {
        new Thread(() -> {
            try {
                new ServerSocket(23472, 1, InetAddress.getLocalHost()).accept();
            } catch (IOException e) {
                System.out.println("Another application instance is already running. Exiting...");
                System.exit(0);
            }
        }).start();
    }

}
