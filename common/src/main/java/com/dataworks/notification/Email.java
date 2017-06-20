package com.dataworks.notification;

import com.dataworks.error.ErrorCode;
import com.dataworks.error.ThrowIt;

import java.net.InetAddress;
import java.util.List;

/**
 * Created by Sandeep on 5/17/17.
 */
public abstract class Email {

    public abstract void sendMail(List<InetAddress> toAddress, String message, ErrorCode code) throws ThrowIt;

}
