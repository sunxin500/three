package com.definesys.rt.service;

import java.util.Map;

public interface SendSms {
    public boolean send(String phoneNumber, String templateCode, Map<String, Object> code);
}
