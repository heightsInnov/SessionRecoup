/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.utils;

/**
 *
 * @author oawe
 */
public class SharedToken {

    // volatile keyword here makes sure that
    // the changes made in one thread are 
    // immediately reflect in other thread
    public static StringBuffer token = null;
   

}
