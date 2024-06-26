package org.kodigo.subscriptions.utils.interfaces;

import java.text.ParseException;
import java.util.Date;

public interface IDateService {

    String formatDate(Date date, String targetFormat);

    Date parseToDate(String date, String sourceFormat) throws ParseException;
}
