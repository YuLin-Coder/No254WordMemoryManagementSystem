package com.ian.media.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: 字符串工具类</p>
 * <p>Description: 该工具类是一个对字符串常用方法的总结，
 * 涉及到日常开发中对字符串处理常用的方法。</p>
 *
 * @author 张克、李琪
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public final class StringUtil {
    private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();
    private static final char[] AMP_ENCODE = "&amp;".toCharArray();
    private static final char[] LT_ENCODE = "&lt;".toCharArray();
    private static final char[] GT_ENCODE = "&gt;".toCharArray();
    private static final boolean B_FALSE = false;
    private static final boolean B_TRUE = true;
    public static final int V_IGNORE = 0;
    public static final int V_UNIGNORE = 1;
    private static MessageDigest digest = null;
    private static Random randGen = new Random();

    private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
            + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    private static String convert = "";


    /**
     * 无参数构造方法
     */
    public StringUtil() {
    }

    /**
     * 获取客户编码
     *
     * @return
     */
//    public static String getCustomerCode() {
//        return "A" + DateUtil.getCurDateStr("yyMMddSSS")+randomString(3);
//    }


    /**
     * 分割 指定字符串 <b>source</b> 参数中的元素。分割符需要设置 <b>delim</b> 参数。<br>
     * <b>注意：</b><br>
     * <b>source</b> 参数不能为null或空值。<br>
     * <b>delim</b> 参数为null或空值则使用默认分割符 [;] 分号。
     *
     * @param source 原始分割字符串
     * @param delim  分隔符
     * @return 分割后的元素集合 <code>java.util.List</code>;分割失败返回null！
     */
    public static List token(String source, String delim) {
        if (source == null || source.trim().length() == 0)
            return null;
        if (delim == null || delim.trim().length() == 0)
            delim = ";";

        List tokens = new ArrayList();
        StringTokenizer tokenizer = new StringTokenizer(source, delim);
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }

    public static String commaString(List<String> list) {
        if (list == null || list.size() == 0)
            return null;
        StringBuilder sb = new StringBuilder();
        for (String el : list) {
            sb.append(el).append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    public static boolean doValidateDate(String date){
        //db2 专有
        String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}.0";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(date);
        boolean dateFlag = m.matches();
        if (!dateFlag) {
            return B_FALSE;
        }
        return B_TRUE;
    }

    /**
     * 验证用户名的方法
     * <br>
     * 该方法通过正则表达式验证用户输入的用户名
     * 是否在 6 - 16 位之间,用户名必须是有效字
     * 符否则方法返回false。如果传入用户名为
     * null,则返回false。
     *
     * @param username 用户名
     * @return 返回用户名验证结果, 验证成功返回true, 否则返回false。
     */
    public static boolean doValidateUserName(String username) {
        if (username == null)
            return B_FALSE;
        Pattern p = Pattern.compile("^\\w{6,16}+$");
        Matcher m = p.matcher(username);
        if (m.find()) {
            return B_TRUE;
        }
        return B_FALSE;
    }

    /**
     * 验证用户密码的方法
     * <br>
     * 该方法通过正则表达式验证用户输入的用户密码
     * 是否在 6 - 16 位之间,用户密码必须是有效字
     * 符否则方法返回false。如果传入用户密码为
     * null,则返回false。
     *
     * @param password 用户密码
     * @return 返回用户密码验证结果, 验证成功返回true, 否则返回false。
     */
    public static boolean doValidateUserPass(String password) {
        if (password == null)
            return B_FALSE;
        Pattern p = Pattern.compile("^\\w{6,16}+$");
        Matcher m = p.matcher(password);
        if (m.find()) {
            return B_TRUE;
        }
        return B_FALSE;
    }

    /**
     * 验证E_Mail的方法
     * <br>
     * 该方法通过正则表达式验证用户输入的用户email
     * 是否符合email地址规则,email地址必须符合规则
     * 否则方法返回false。如果传入email地址为
     * null,则返回false。
     *
     * @param email E_Mail地址
     * @return 返回E_Mail验证结果, 验证规则成功返回true, 否则返回false。
     */
    public static boolean doValidateEmail(String email) {
        if (email == null)        //"^\\w+@+\\w(3,5)+.+\\w(3,5)+$"
            return B_FALSE;
        Pattern p = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+");
        Matcher m = p.matcher(email);
        if (m.find()) {
            return B_TRUE;
        }
        return B_FALSE;
    }

    /**
     * 简单验证对象的方法
     * <br>
     * 如果用户传入对象字段为null,则返回假，否则返回真。
     * 例如，在J2EE的Web开发中request对象获取表单字段，
     * 这时需要先验证request是否能获取到该字段，在
     * 这里就可以使用该方法验证。
     *
     * @param item 对象字段
     * @return 返回对象字段验证结果，为NULL返回true否则返回false
     */
    public static boolean doValidateNull(Object item) {
        if (item == null)
            return B_TRUE;

        return B_FALSE;
    }

    /**
     * 简单验证对象的方法
     * <br>
     * 如果用户传入对象字段为null,则返回假，否则返回真。
     * 例如，在J2EE的Web开发中request对象获取表单字段，
     * 这时需要先验证request是否能获取到该字段，在
     * 这里就可以使用该方法验证。
     *
     * @param items 对象字段
     * @return 返回对象字段验证结果，为NULL返回true否则返回false
     */
    public static boolean doValidateNull(Object... items) {
        if (items == null)
            return B_TRUE;
        for (Object item : items) {
            if (item == null) {
                return B_TRUE;
            }
        }
        return B_FALSE;
    }

    /**
     * 简单验证字符串是否为null或者空串。
     *
     * @param item 验证字符串
     * @return 为null或空串返回true
     */
    public static boolean doValidateNullOrBlank(String item) {
        if (null == item || "".equals(item.trim())) {
            return B_TRUE;
        }
        return B_FALSE;
    }

    /**
     * 简单验证字符串是否为null或者空串。
     *
     * @param items 验证字符串
     * @return 为null或空串返回true
     */
    public static boolean doValidateNullOrBlank(String... items) {
        if (items == null)
            return B_TRUE;
        for (String item : items) {
            if (null == item || "".equals(item.trim())) {
                return B_TRUE;
            }
        }

        return B_FALSE;
    }

    public static boolean doValidateNullOrBlank(Object... items) {
        if (items == null)
            return B_TRUE;
        for (Object item : items) {
            if(null == item)
                return B_TRUE;
            if(item instanceof String){
                String sitem = (String) item;
                if (null == sitem || "".equals(sitem.trim())) {
                    return B_TRUE;
                }
            }
            if(item instanceof Integer){
                if (null == item) {
                    return B_TRUE;
                }
            }
            if(item instanceof Timestamp)
                if(null == item)
                    return B_TRUE;
        }

        return B_FALSE;
    }

    /**
     * 验证两个字段的值
     * <br>
     * 该方法验证两个字段
     * 如果忽略大小写对比双方必须是字符串类型。
     * @param item   字段
     * @param value  值
     * @param ignore 是否忽略大小写
     * @return 相同返回true。
     */
    public static boolean doValidateItemEqualsValue(Object item, Object value, int ignore) {
        boolean isValidate = B_FALSE;
        if (doValidateNull(item) || doValidateNull(value))
            return isValidate;
        switch (ignore) {
            case V_IGNORE:
                if (item.toString().equalsIgnoreCase(value.toString())) {
                    isValidate = B_TRUE;
                } else {
                    isValidate = B_FALSE;
                }
                break;
            case V_UNIGNORE:
                if (item.equals(value)) {
                    isValidate = B_TRUE;
                } else {
                    isValidate = B_FALSE;
                }
                break;
            default:
                isValidate = B_FALSE;
        }
        return isValidate;
    }

    /**
     * 如果item与values中的项目相等，那么result数组中对应的值为0，不相等为1.
     * 形参校验失败result为-1.
     * 默认区分大小写。
     * @param item 原始项
     * @param values 验证项
     * @return 返回对应验证项的结果数组。0为相同，1为不同。-1校验失败。
     */
    public static int[] doValidateItemEqualsValue(Object item, Object... values){
        int[] result = new int[values.length];
        for(int i=0;i<result.length;i++)
            result[0] = -1;
        if (doValidateNull(item) || doValidateNull(values))
            return result;
        for(int i=0;i<values.length;i++){
             if(doValidateItemEqualsValue(item,values[i],1))
                 result[i]=0;
             else
                 result[i]=1;
        }
        return result;
    }


    /**
     * 字符串替换方法
     * <br>
     * 该方法需要三个参数:原始字符串、原字符串片段和新字符串片段;该方法
     * 通过在<b>原始字符串</b>中搜索<b>原字符串片段</b>如果搜索到该原
     * 字符串片段则将<b>新字符串片段</b>替换<b>原字符串片段</b>。
     * <br>
     * <font size="2" color="red"><b>
     * 注意:如果 原始字符串 为NULL则方法返回NULL,并且对字符串大小写敏感。
     * </b></font>
     *
     * @param line      原始字符串。
     * @param oldString 需要被替换的 原字符串片段。
     * @param newString 新字符串片段。
     * @return 返回一个经过替换过的字符串。
     */
    public static final String replace(String line, String oldString, String newString) {
        if (line == null) {
            return null;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    /**
     * 字符串替换方法
     * <br>
     * 该方法需要三个参数:原始字符串、原字符串片段和新字符串片段;该方法
     * 通过在<b>原始字符串</b>中搜索<b>原字符串片段</b>如果搜索到该原
     * 字符串片段则将<b>新字符串片段</b>替换<b>原字符串片段</b>。
     * <br>
     * <font size="2" color="red"><b>
     * 注意:如果 原始字符串 为NULL则方法返回NULL,并且忽略字符串大小写。
     * </b></font>
     *
     * @param line      原始字符串。
     * @param oldString 需要被替换的 原字符串片段。
     * @param newString 新字符串片段。
     * @return 返回一个经过替换过的字符串。
     */
    public static final String replaceIgnoreCase(String line, String oldString, String newString) {
        if (line == null) {
            return null;
        }
        String lcLine = line.toLowerCase();
        String lcOldString = oldString.toLowerCase();
        int i = 0;
        if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    /**
     * Replaces all instances of oldString with newString in line with the added
     * feature that matches of newString in oldString ignore case. The count
     * paramater is set to the number of replaces performed.
     *
     * @param line      the String to search to perform replacements on
     * @param oldString the String that should be replaced by newString
     * @param newString the String that will replace all instances of oldString
     * @param count     a value that will be updated with the number of replaces
     *                  performed.
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replaceIgnoreCase(String line, String oldString, String newString, int[] count) {
        if (line == null) {
            return null;
        }
        String lcLine = line.toLowerCase();
        String lcOldString = oldString.toLowerCase();
        int i = 0;
        if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
            int counter = 0;
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
                counter++;
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            count[0] = counter;
            return buf.toString();
        }
        return line;
    }

    /**
     * Replaces all instances of oldString with newString in line. The count
     * Integer is updated with number of replaces.
     *
     * @param line      the String to search to perform replacements on
     * @param oldString the String that should be replaced by newString
     * @param newString the String that will replace all instances of oldString
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replace(String line, String oldString, String newString, int[] count) {
        if (line == null) {
            return null;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            int counter = 0;
            counter++;
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                counter++;
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            count[0] = counter;
            return buf.toString();
        }
        return line;
    }

    /**
     * 这个方法是一个把 HTML 标签(例如:&lt;b&gt;,&lt;table&gt;, 等等)中的 &lt; 和 &gt;
     * 替换为实体字符(＆lt; ＆gt;).
     *
     * @param in 需要被替换的字符串输入
     * @return 返回一串被方法替换后的HTML标签.
     */
    public static final String escapeHTMLTags(String in) {
        if (in == null) {
            return null;
        }
        char ch;
        int i = 0;
        int last = 0;
        char[] input = in.toCharArray();
        int len = input.length;
        StringBuffer out = new StringBuffer((int) (len * 1.3));
        for (; i < len; i++) {
            ch = input[i];
            if (ch > '>') {
                continue;
            } else if (ch == '<') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(LT_ENCODE);
            } else if (ch == '>') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(GT_ENCODE);
            }
        }
        if (last == 0) {
            return in;
        }
        if (i > last) {
            out.append(input, last, i - last);
        }
        return out.toString();
    }

    /**
     * 哈希方法--是一个字符串经过MD5算法运算后返回一个十六进制的字符串.这个方法是一个同步的并且能
     * 够避免过多的消息对象的创建.如果在你的代码中出现瓶颈问题并且你希望去维持那些高类聚的对象的时候
     * 可以调用这个方法去代替他们是比较合适的.
     * <p/>
     * 哈希它是一个单路的函数.就是说,给出一个输入,那么输出是非常容易被计算出来的.然而,给出一个输出
     * ,这个输入是几乎不可能被计算出来的.它对于密码来说非常有用!以后我们可以以密码的哈希进行存储.并
     * 且黑客将会花费很多的时间去判断你密码的原型.
     * <p/>
     * 在Jive(一个开源论坛)中,每次当用户登录的时候,我们仅仅简单的取出密码框中的文本,计算它的哈希,
     * 并且去对比获取的哈希和存储的哈希.以后几乎不可能产生两个相同的哈希.我们知道如果用户给我们的可能
     * 是正确的密码或者是错误的密码.这只能决定系统去恢复密码,但着基本上是不可能的.因此,可以使用这个方法
     * 去代替.
     *
     * @param data 需要计算哈希的字符串.
     * @param bit  16位或32位
     * @return 返回一个经过哈希计算后的字符串.
     */
    public synchronized static final String hash(String data, int bit) {
        if (digest == null) {
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException nsae) {
                System.err.println("Failed to load the MD5 MessageDigest. "
                        + "We will be unable to function normally.");
                nsae.printStackTrace();
            }
        }
        // Now, compute hash.
        digest.update(data.getBytes());
        if (16 == bit) {
            return encodeHex(digest.digest()).substring(0, 16);
        } else if (32 == bit) {
            return encodeHex(digest.digest());
        } else {
            return encodeHex(digest.digest());
        }
    }

    /**
     * 支持MD5的16位加密或32位加密。
     * @param data 原文
     * @param bit 加密位数。16or32
     * @return 加密后的16位或32位密文。
     */
    public synchronized static final String MD5(String data, int bit) {
           return hash(data,bit);
//        if (digest == null) {
//            try {
//                digest = MessageDigest.getInstance("MD5");
//            } catch (NoSuchAlgorithmException nsae) {
//                System.err.println("Failed to load the MD5 MessageDigest. "
//                        + "We will be unable to function normally.");
//                nsae.printStackTrace();
//            }
//        }
//        // Now, compute hash.
//        digest.update(data.getBytes());
//        return new BigInteger(1, digest.digest()).toString(bit);
    }

    private static final String encodeHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer(hash.length * 2);
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }

    /**
     * 使用BreakIterator.wordInstance()方法把一行文本中的词组转换成数组.
     *
     * @param text 需要被转换的文本.
     * @return text broken up into an array of words.
     */
    @SuppressWarnings("unchecked")
    public static final String[] toLowerCaseWordArray(String text) {
        if (text == null || text.length() == 0) {
            return new String[0];
        }
        ArrayList wordList = new ArrayList();
        BreakIterator boundary = BreakIterator.getWordInstance();
        boundary.setText(text);
        int start = 0;
        for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary
                .next()) {
            String tmp = text.substring(start, end).trim();
            // Remove characters that are not needed.
            tmp = replace(tmp, "+", "");
            tmp = replace(tmp, "/", "");
            tmp = replace(tmp, "\\", "");
            tmp = replace(tmp, "#", "");
            tmp = replace(tmp, "*", "");
            tmp = replace(tmp, ")", "");
            tmp = replace(tmp, "(", "");
            tmp = replace(tmp, "&", "");
            if (tmp.length() > 0) {
                wordList.add(tmp);
            }
        }
        return (String[]) wordList.toArray(new String[wordList.size()]);
    }

    /**
     * 随机数字符串方法
     * 该方法通过指定的长度(参数length)并返回一个数字和字母(大写和小写)组成的字符串.
     * <p/>
     * <p/>
     * 指定的长度最少为1,否则,这个方法将返回null!
     *
     * @param length 指定返回字符串的长度参数.
     * @return 返回一个定长的随机数字符串.
     */
    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }
        // Create a char buffer to put random letters and numbers in.
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }

    /**
     * Intelligently chops a String at a word boundary (whitespace) that occurs at
     * the specified index in the argument or before. However, if there is a
     * newline character before <code>length</code>, the String will be chopped
     * there. If no newline or whitespace is found in <code>string</code> up to
     * the index <code>length</code>, the String will chopped at
     * <code>length</code>.
     * <p/>
     * For example, chopAtWord("This is a nice String", 10) will return "This is
     * a" which is the first word boundary less than or equal to 10 characters
     * into the original String.
     *
     * @param string the String to chop.
     * @param length the index in <code>string</code> to start looking for a
     *               whitespace boundary at.
     * @return a substring of <code>string</code> whose length is less than or
     *         equal to <code>length</code>, and that is chopped at whitespace.
     */
    public static final String chopAtWord(String string, int length) {
        if (string == null) {
            return string;
        }
        char[] charArray = string.toCharArray();
        int sLength = string.length();
        if (length < sLength) {
            sLength = length;
        }
        // First check if there is a newline character before length; if so,
        // chop word there.
        for (int i = 0; i < sLength - 1; i++) {
            // Windows
            if (charArray[i] == '\r' && charArray[i + 1] == '\n') {
                return string.substring(0, i + 1);
            }
            // Unix
            else if (charArray[i] == '\n') {
                return string.substring(0, i);
            }
        }
        // Also check boundary case of Unix newline
        if (charArray[sLength - 1] == '\n') {
            return string.substring(0, sLength - 1);
        }
        // Done checking for newline, now see if the total string is less than
        // the specified chop point.
        if (string.length() < length) {
            return string;
        }
        // No newline, so chop at the first whitespace.
        for (int i = length - 1; i > 0; i--) {
            if (charArray[i] == ' ') {
                return string.substring(0, i).trim();
            }
        }
        // Did not find word boundary so return original String chopped at
        // specified length.
        return string.substring(0, length);
    }


    public static final String escapeForSpecial(String string) {
        if (string == null) {
            return null;
        }
        char ch;
        int i = 0;
        int last = 0;
        char[] input = string.toCharArray();
        int len = input.length;
        StringBuffer out = new StringBuffer((int) (len * 1.3));
        for (; i < len; i++) {
            ch = input[i];
            if (ch > '>') {
                continue;
            } else if (ch == '<') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(LT_ENCODE);
            } else if (ch == '&') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(AMP_ENCODE);
            } else if (ch == '"') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(QUOTE_ENCODE);
            } else if (ch == '>') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(GT_ENCODE);
            }
        }
        if (last == 0) {
            return string;
        }
        if (i > last) {
            out.append(input, last, i - last);
        }
        return out.toString();
    }

    /**
     * 获取一个定长由数字和小写字母组成的随机数密码,但是在该密码中不包含0,o,l和I，以免误解!
     * <p/>
     * 指定的长度至少等于1,否则,返回null.
     *
     * @param length 指定随机数密码长度参数
     * @return 返回一个定长的随机数密码.
     */
    public static String genPassword(int length) {
        if (length < 1) {
            return null;
        }
        String[] strChars = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b",
                "c", "d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q", "r",
                "s", "t", "u", "v", "w", "x", "y", "z", "a"};
        // 没有0,o,l和I，以免误解
        StringBuffer strPassword = new StringBuffer();
        int nRand = (int) Math.round(Math.random() * 100);
        for (int i = 0; i < length; i++) {
            nRand = (int) Math.round(Math.random() * 100);
            strPassword.append(strChars[nRand % (strChars.length - 1)]);
            // strPassword += strChars[nRand % (strChars.length - 1)];
        }
        return strPassword.toString();
    }


    /**
     * 获取一个定长由纯数字组成的随机数密码.
     * <p/>
     * 指定的长度至少等于1,否则,返回null.
     *
     * @param length
     * @return
     */
    public static String genNumPassword(int length) {
        if (length < 1) {
            return null;
        }
        String[] strChars = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer strPassword = new StringBuffer();
        int nRand = (int) Math.round(Math.random() * 100);
        for (int i = 0; i < length; i++) {
            nRand = (int) Math.round(Math.random() * 100);
            strPassword.append(strChars[nRand % (strChars.length - 1)]);
            // strPassword += strChars[nRand % (strChars.length - 1)];
        }
        return strPassword.toString();

    }


    public static String toURLEncode(String str) {
        if (str == null)
            return "";
        try {
            convert = "";
            convert = URLEncoder.encode(str, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return convert;
    }

    public static String toURLDecoder(String str) {
        if (str == null)
            return "";
        try {
            convert = "";
            convert = URLDecoder.decode(str, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return convert;
    }

    public static String toGBK(String str){
        if(str==null)
            return "";
        try {
            String temp = new String(str.getBytes("ISO-8859-1"),"GBK");
            return temp;
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public static boolean isNull(String str) {
    	if(str == null || "".equals(str) || "null".equals(str)) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public static String getValue(String str) {
    	if(str == null || "".equals(str) || "null".equals(str)) {
    		return "";
    	} else {
    		return str;
    	}
    }

    public static boolean isNotNull(String str) {
    	return !isNull(str);
    }

    /**
	 * 判断集合是否为空
	 * @param list
	 * @return
	 */
	public static boolean isNotBlank(Collection list) {
		if(list != null && list.size() != 0) {
			return true;
		}
		return false;
	}
     /**
      * 校验手机号
      * @param str
      * @return
      */
	public static boolean isMobile(String str) { 
		Pattern p = null;
		Matcher m = null;
		boolean b = false; 
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches(); 
		return b;
	}
	
	/**
	 * 类不为空
	 * @return
	 */
	public static boolean isNotBlank(Object obj) {
		return obj != null;
	}
	
	
	
    public static void main(String[] args) {
        System.out.println(StringUtil.hash("123456",32));
        System.out.println(StringUtil.isMobile("18668945164"));
    }

}
