package com.definesys.rt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class DateCoverter implements Converter<String,Date> {

    @Override
    public Date convert(String source) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd mm:HH:ss");
        Date date = null;
        try {
            date = dateFormat.parse(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
