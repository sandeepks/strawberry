package com.dataworks.notification;

import com.dataworks.error.ErrorCode;
import com.dataworks.error.ThrowIt;


import java.net.InetAddress;
import java.util.List;

/**
 * Created by Sandeep on 5/17/17.
 */
public class HtmlEmail extends Email {
    private String HTML_EMAIL_TEMPLATE = null;

    public void sendMail(List<InetAddress> toAddress, String message, ErrorCode code) throws ThrowIt {

    }
}
