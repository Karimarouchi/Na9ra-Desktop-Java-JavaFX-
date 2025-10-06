module tn.TheInformants {

        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;
        requires java.mail;
        requires com.google.gson;
        requires org.apache.commons.text; // Add this line
        requires java.desktop;
        requires stripe.java;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires javafx.swing;
    requires facebook4j.core;
    requires org.apache.httpcomponents.httpcore;
    requires org.json;
    requires org.apache.httpcomponents.httpclient;
    requires jbcrypt;

    opens tn.TheInformants.Na9ra to javafx.fxml;
        opens tn.TheInformants.controller to javafx.fxml;
        exports tn.TheInformants.Na9ra;
        }