package in.swapnilbhoite.projects.ilovezappos.utils;

import android.text.Html;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    private static String regexUnicode = "&#\\d+;";
    private static Pattern pattern = Pattern.compile(regexUnicode);

    public static String decodeUnicodes(String string) {
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            //noinspection deprecation
            return matcher.replaceAll(Html.fromHtml(matcher.group(0)).toString());
        }
        return string;
    }
}
