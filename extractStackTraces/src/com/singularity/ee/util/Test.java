package com.singularity.ee.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jun.ouyang on 11/30/16.
 */
public class Test {

    public static void main(String[] args ) {
//        Pattern pattern = Pattern.compile("<p style=\"font-family: Arial, sans-serif; font-size: 12px;\"><em><strong><span style=\"color: rgb\\(0, 0, 128\\);\">Opportunity Amount Range:</span></strong></em></p><p style=\"font-family: Arial, sans-serif; font-size: 12px;\">(?:<span style=\"font-family: Arial, Helvetica, sans-serif; background-color: rgb\\(255, 255, 255\\);\">)?\\$(?:</span><span style=\"font-family: Arial, Helvetica, sans-serif; background-color: rgb\\(255, 255, 255\\);\">)?(.+?)(?:</span><span style=\"font-family: Arial, Helvetica, sans-serif; background-color: rgb\\(255, 255, 255\\);\">)?(?: |&nbsp;)- \\$(.+?)(?:</span>)?</p>");

        Pattern pattern = Pattern.compile("<p style=\"font-family: Arial, sans-serif; font-size: 12px;\"><em><strong><span style=\"color: rgb\\(0, 0, 128\\);\">Opportunity Amount Range:</span></strong></em></p><p [^>]+>(.+?)</p>");
        String[] groups = getFields(pattern, "</p><p style=\"font-family: Arial, sans-serif; font-size: 12px;\"><em><strong><span style=\"color: rgb(0, 0, 128);\">Opportunity Amount Range:</span></strong></em></p><p style=\"font-family: Arial, sans-serif; font-size: 12px;\"><span style=\"font-family: Arial, Helvetica, sans-serif; background-color: rgb(255, 255, 255);\">$</span><span style=\"font-family: Arial, Helvetica, sans-serif; background-color: rgb(255, 255, 255);\">1.5M</span><span style=\"font-family: Arial, Helvetica, sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;- $5M</span></p>");
        for( String s : groups ) {
            System.out.println(s);
        }
        System.out.println("-------------------------");
        groups = getFields(pattern, "<p style=\"font-family: Arial, sans-serif; font-size: 12px;\"><em><strong><span style=\"color: rgb(0, 0, 128);\">Opportunity Amount Range:</span></strong></em></p><p style=\"font-family: Arial, sans-serif; font-size: 12px;\"><span style=\"font-family: Arial, Helvetica, sans-serif; background-color: rgb(255, 255, 255);\">$5M+</span></p>");
        for( String s : groups ) {
            System.out.println(s);
        }
        System.out.println(groups.length);



        Map<Long[], Long> map = new HashMap<>();
        map.put(new Long[]{1L, 2L}, 1L);
        map.put(new Long[]{1L, 2L}, 2L);
        System.out.println(map.size());
    }

    private static String[] getFields(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        String group = matcher.group(1);
        String[] splits = group.split("<span [^>]+>|</span>|\\$|\\+");
//        return splits;
        return Arrays.stream(splits).filter( s -> s.matches("\\d.*")).toArray(size->new String[size]);
    }
}
