package org.kodigo.subscriptions.utils;

import org.kodigo.subscriptions.utils.interfaces.IDateService;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateService implements IDateService {


    @Override
    @Named("dateToString")
    public String formatDate(Date date, String targetFormat) {

        return new SimpleDateFormat(targetFormat).format(date);
    }
}
