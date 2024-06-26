package org.kodigo.subscriptions.utils;

import org.kodigo.subscriptions.utils.interfaces.IDateService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateService implements IDateService {


    @Override
    public String formatDate(Date date, String targetFormat) {

        return new SimpleDateFormat(targetFormat).format(date);
    }

    @Override
    public Date parseToDate(String date, String sourceFormat) throws ParseException {

        return new SimpleDateFormat(sourceFormat).parse(date);
    }
}
