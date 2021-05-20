package com.company.hrms.Core.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static boolean checkUserEmail(String email){
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
