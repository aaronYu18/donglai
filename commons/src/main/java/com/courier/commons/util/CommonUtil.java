package com.courier.commons.util;


/**
 * Created by admin on 2015/7/14.
 */
public class CommonUtil {
    /**
     *
     * @param bytes
     * @return
     */
    public static String convertByteArrayToString(Byte[] bytes){
        if(bytes == null || bytes.length == 0) return "";

        StringBuffer sb = new StringBuffer();

        for (Byte by : bytes)
            sb.append(by + ",");

        if(sb != null) {
            String s = sb.toString();
            return s.substring(0, s.length() - 1);
        }

        return "";
    }

    /**
     * 格式化工号
     */
    public static String formatJobNo(String jobNo){
        if (jobNo == null) return null;
        if (jobNo.trim().equals("")) return null;
        return String.format("%8s", jobNo).replaceAll(" ", "0");
    }
}
