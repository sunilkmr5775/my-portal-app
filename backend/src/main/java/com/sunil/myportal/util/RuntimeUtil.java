package com.sunil.myportal.util;

import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class RuntimeUtil {
    private static final long GIGABYTE_FACTOR = 1024L * 1024L * 1024L;
    private static final DecimalFormat ROUNDED_DOUBLE_DECIMALFORMAT;
    private static final String MIB = "GB";

    static {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        ROUNDED_DOUBLE_DECIMALFORMAT = new DecimalFormat("####0.00", otherSymbols);
        ROUNDED_DOUBLE_DECIMALFORMAT.setGroupingUsed(false);
    }

    private RuntimeUtil() {
        //No Init
    }

    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    public static long getUsedMemory() {
        return getMaxMemory() - getFreeMemory();
    }

    public static long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    public static String getTotalMemoryInMiB() {
        double totalMiB = bytesToMiB(getTotalMemory());
//        return String.format("%s %s", ROUNDED_DOUBLE_DECIMALFORMAT.format(totalMiB), MIB);
        return String.format("%s", ROUNDED_DOUBLE_DECIMALFORMAT.format(totalMiB));
    }

    public static String getFreeMemoryInMiB() {
        double freeMiB = bytesToMiB(getFreeMemory());
//        return String.format("%s %s", ROUNDED_DOUBLE_DECIMALFORMAT.format(freeMiB), MIB);
        return String.format("%s", ROUNDED_DOUBLE_DECIMALFORMAT.format(freeMiB));
    }

    public static String getUsedMemoryInMiB() {
        double usedMiB = bytesToMiB(getUsedMemory());
//        return String.format("%s %s", ROUNDED_DOUBLE_DECIMALFORMAT.format(usedMiB), MIB);
        return String.format("%s", ROUNDED_DOUBLE_DECIMALFORMAT.format(usedMiB));
    }

    public static String getMaxMemoryInMiB() {
        double maxMiB = bytesToMiB(getMaxMemory());
//        return String.format("%s %s", ROUNDED_DOUBLE_DECIMALFORMAT.format(maxMiB), MIB);
        return String.format("%s", ROUNDED_DOUBLE_DECIMALFORMAT.format(maxMiB));
    }

    public static double getPercentageUsed() {
        return ((double) getUsedMemory() / getMaxMemory()) * 100;
    }

    public static String getPercentageUsedFormatted() {
        double usedPercentage = getPercentageUsed();
        return ROUNDED_DOUBLE_DECIMALFORMAT.format(usedPercentage);
    }

    private static double bytesToMiB(long bytes) {
        return ((double) bytes / GIGABYTE_FACTOR);
    }

    public static String getHostAdress() {
        try {
            java.net.InetAddress addr = java.net.InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            // looks like a strange machine
            System.out.println(e.getMessage());
        }
//    return StringUtil.EMPTY;
        return null;
    }

    public static String getHostName() {
        try {
            java.net.InetAddress addr = java.net.InetAddress.getLocalHost();
            return addr.getHostName();
        } catch (UnknownHostException e) {
            // looks like a strange machine
            System.out.println(e.getMessage());
        }
        return null;
    }

//    public static void main(String[] args) {
//        String result = getSystemInformation();
//        System.out.println(result);
//    }

//    public static String getSystemInformation() {
//    return String.format("SystemInfo=Current heap:%s; Used:%s; Free:%s; Maximum Heap:%s; Percentage Used:%s",
//            getTotalMemoryInMiB(),
//            getUsedMemoryInMiB(),
//            getFreeMemoryInMiB(),
//            getMaxMemoryInMiB(),
//            getPercentageUsedFormatted());
//}

//    public static String getSystemInformation() {
//        String TotalMemory = getTotalMemoryInMiB();
//        String UsedMemory = getUsedMemoryInMiB();
//        String FreeMemory = getFreeMemoryInMiB();
//        String MaxMemory = getMaxMemoryInMiB();
//        String PercentageUsed = getPercentageUsedFormatted();
//    }
}