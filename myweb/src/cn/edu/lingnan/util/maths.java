package cn.edu.lingnan.util;

public class maths {
    public static boolean isNumeric(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    public static boolean isNumericOrEnglish(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (!Character.isDigit(str.charAt(i))&&((str.charAt(i)<'a'||str.charAt(i)>'z')&&(str.charAt(i)<'A'||str.charAt(i)>'Z')))
            {
                return false;
            }
        }
        return true;
    }
}
