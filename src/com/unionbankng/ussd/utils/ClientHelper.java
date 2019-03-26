/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.utils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.client.ClientProperties;

/**
 *
 * @author oawe
 */
public class ClientHelper {

    public Client getClinet() {

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            // Create a new X509TrustManager
            sslContext.init(null, getTrustManager(), null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            // throw e;
        }
        final Client client = ClientBuilder.newBuilder().hostnameVerifier((s, session) -> true)
                .sslContext(sslContext).build();
        client.property(ClientProperties.CONNECT_TIMEOUT, 120000);
        client.property(ClientProperties.READ_TIMEOUT, 120000);
        return client;
    }

    private TrustManager[] getTrustManager() {
        return new TrustManager[]{
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }
            }
        };
    }
}
