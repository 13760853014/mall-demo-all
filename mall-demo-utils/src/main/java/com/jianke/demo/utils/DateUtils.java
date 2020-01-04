package com.jianke.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
@Slf4j
public class DateUtils {
    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取当前时间到指定时间的时间差（毫秒）
     *
     * @param publishTime
     * @return
     */
    public static Long getDelay(Date publishTime) {
        Instant instNow = Instant.now();
        Instant instDelay = publishTime.toInstant();
        return Duration.between(instNow, instDelay).toMillis();
    }

    /**
     * 获取当天剩余时间（毫秒）
     *
     * @return
     */
    public static Long getCurDateExpireMillis() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime expireDay = LocalDateTime.now();
        expireDay = LocalDateTime.of(expireDay.getYear(), expireDay.getMonth(), expireDay.getDayOfMonth(), 23, 59, 59);
        Instant instNow = Instant.now();
        Instant instDelay = expireDay.atZone(zoneId).toInstant();
        return Duration.between(instNow, instDelay).toMillis();
    }

    /**
     * 获取当前时间到plusDays天后的时间（毫秒）
     *
     * @param plusDays
     * @return
     */
    public static Long getCurDateToNDayExpireMillis(Integer plusDays) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime expireDay = LocalDateTime.now();
        expireDay = expireDay.plus(plusDays, ChronoUnit.DAYS);
        expireDay = LocalDateTime.of(expireDay.getYear(), expireDay.getMonth(), expireDay.getDayOfMonth(), 23, 59, 59);
        Instant instNow = Instant.now();
        Instant instDelay = expireDay.atZone(zoneId).toInstant();
        return Duration.between(instNow, instDelay).toMillis();
    }

    public static Long getCurDateExpireMillis(Date date) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime expireDay = LocalDateTime.now();
        expireDay = LocalDateTime.of(expireDay.getYear(), expireDay.getMonth(), expireDay.getDayOfMonth(), 23, 59, 59);
        Instant startInst = date.toInstant();
        Instant instDelay = expireDay.atZone(zoneId).toInstant();
        return Duration.between(startInst, instDelay).toMillis();
    }

    /**
     * 根据传入的格式获取当前时间
     *
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter ft = DateTimeFormatter.ofPattern(format);
        return ft.format(now);
    }

    public static Long getDelayDate(Integer deleyDate) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, deleyDate);
        return cal.getTimeInMillis();
    }

    public static Long getDelaySecond(Integer second) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, second);
        return cal.getTimeInMillis();
    }


    /**
     * 时间戳转为Date
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date formatDate(Long date) {
        if (date == null) {
            return null;
        }
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(date / 1000, (int) (date % 1000), ZoneOffset.ofHours(8));
        return localDateTimeToDate(dateTime);
    }

    public static Date stringToDate(String date) {
        return stringToDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date stringToDate(String date, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return localDateTimeToDate(LocalDateTime.parse(date, df));
    }

    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据传入的格式与时间获取格式化的时间传
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        if (date != null) {
            try {
                LocalDateTime now = dateToLocalDateTime(date);
                DateTimeFormatter ft = DateTimeFormatter.ofPattern(format);
                return ft.format(now);
            } catch (Exception e) {
                log.error("时间{}转换失败：{}", date, e.getMessage());
            }
        }
        return "";
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);

        return Date.from(zdt.toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }
}
