package com.reporting.iris.irissearch.utils;

import java.util.List;

public interface EmailUtil {

    void sendEmail(String toAddress, String subject, String body);

    void sendEmailsToList(List<String> toAddressList, String subject, String body);
}
