/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.utils;

import com.unionbankng.ussd.constants.Constants;
import com.unionbankng.ussd.converters.JsonConverter;
import static com.unionbankng.ussd.converters.JsonConverter.toJson;
import com.unionbankng.ussd.dtos.AuthToken;
import com.unionbankng.ussd.dtos.FundsTransferRequest;
import com.unionbankng.ussd.dtos.FundsTransferResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author oawe
 */
public class MiServeImpl {

    static Logger log = LogManager.getLogger(MiServeImpl.class);
    // private static volatile String authToken = "";
    // private static String refreshToken = "";

//    static {
//        setAuthToken();
//    }
    public static AuthToken getToken() {
        AuthToken response = new AuthToken();
        for (int i = 0; i <= 1; i++) { //attempt get token, if first call failed.
            Client client = new ClientHelper().getClinet();
            Response httpResponse = client.target(Constants.BASE_URL + String.format(Constants.AUTH_RESOURCE + "?client_secret=%s&client_id=%s&grant_type=%s&username=%s&password=%s",
                    Constants.CLIENT_SECRET,
                    Constants.CLIENT_ID,
                    Constants.GRANT_TYPE,
                    Constants.WS_USERNAME,
                    Constants.WS_PASSWORD)).request().header("Content-Type", "application/x-www-form-urlencoded").post(Entity.json(""));
            log.info("auth token http response code=" + httpResponse.getStatus());
            if (httpResponse.getStatus() == 200) {
                response = httpResponse.readEntity(AuthToken.class);
                log.info("auth token expired in=" + response.getExpires_in());
                SharedToken.token = new StringBuffer();
                SharedToken.token.append(response.getAccess_token());
                break;
            } else {
                //SharedToken.token = null;
            }
            httpResponse.close();
            client.close();
        }
        return response;
    }

    public static FundsTransferResponse fundsTransfer(FundsTransferRequest request) {
        FundsTransferResponse response = new FundsTransferResponse();
        int counter = 0;
        while (counter++ <= 2) {
            response = _fundsTransfer(request);
            if (!(response == null || response.getCode() == null || response.getCode().equals("63"))) {
                break;
            }
        }
        return response;
    }

    private static FundsTransferResponse _fundsTransfer(FundsTransferRequest request) {
        FundsTransferResponse response = new FundsTransferResponse();
        log.info("_fundsTransfer request=" + toJson(request));
        if (SharedToken.token == null) {
            response.setCode("101");
            log.info("Auth Token not found");
            return response;
        }

        Client client = new ClientHelper().getClinet();

        Response res = client.target(Constants.BASE_URL + String.format(Constants.FT_RESOURCE + "?access_token=%s", SharedToken.token.toString())).request().header("Content-Type", "application/json").post(Entity.json(request));

        String resStr = res.readEntity(String.class);
         log.info("fundtranser http response code=" + res.getStatus());
        log.info("_fundsTransfer resposne=" +resStr);
       
        if (res.getStatus() == 200) {
            response = JsonConverter.toObj(resStr, FundsTransferResponse.class);
        } else {
            if (res.getStatus() == 401) {
                response.setCode("101");
                SharedToken.token = null;
                log.info("Token expired");
            } else {
                response.setCode("101");
            }
        }
        res.close();
        client.close();
        return response;
        //return res;
    }
}
