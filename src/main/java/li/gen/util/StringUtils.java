package li.gen.util;

public class StringUtils {
    public static boolean isEmpty(String s){
        if(s.equals("")||s==null||s.trim().length()== 0){
            return true;
        }
        return false;
    }
}
