package nz.co.udenbrothers.yoobie.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Match {
    public static boolean mail(String s){
        Matcher m = Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*$").matcher(s);
        return m.matches( );
    }
}
