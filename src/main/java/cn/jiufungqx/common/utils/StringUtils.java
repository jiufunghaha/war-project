package cn.jiufungqx.common.utils;


import com.alibaba.fastjson.JSONObject;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangluzhen on 2017/8/18.
 */
public class StringUtils {
    public static final String ELLIPSIS = "...";
    public static final String BLANK = "";
    public static final String SPACE = " ";

    /**
     * <p>功能描述:	截取输入空格</p>
     *
     * @param str1
     * @return
     * @author:张然
     * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    public static String getTrim(String str1) {
        if (str1 != null) {

            return str1.trim();
        }
        return null;
    }

    /**
     * <p>Description 传入一个数组类型的字符串 </p>
     *
     * @param str1
     * @return
     * @author : wangjl
     * @update :
     * @date : 2015-7-13
     */
    public static String getTrim(String[] str1) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str1.length; i++) {

            sb.append(getTrim(str1[i]));
            if (i < str1.length - 1) {
                sb.append(",");
            }


        }
        return sb.toString();
    }

    /**
     * <p>功能描述:	截取输入空格(数组)</p>
     *
     * @param objs
     * @return
     * @author:张然
     * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    public static void getTrims(String[] objs) {
        System.out.println(objs[0].trim());

        for (String str : objs) {
            if (str != null) {
                str = str.trim();
                System.out.println(str);
            }

        }
    }

    public static boolean isHaveChineseStr(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            return true;
        }
        return false;
    }

    public static boolean contains(int subStr, String str) {
        if (str != null && !str.equals("")) {
            if (str.contains(",," + subStr + ",,")) {
                return true;
            }
        }
        return false;
    }

    public static String ellipsisStr(String str, int len) {
        return ellipsisStr(str, len, false);
    }

    /**
     * 将字符串截取为指定长度，如果超长，在尾部附加省略号
     *
     * @param str         要截取的字符串
     * @param len         要保留的字符串的长度
     * @param nullToBlank null对象是否转换为空字符串
     * @return 按要求截取后的字符串 示例:	len = 6
     * null	==>	null 或者为空字符串
     * 12345	==>	12345
     * 123456	==>	123456
     * 1234567	==>	123456...
     * 中华人民共	==>	中华人民共
     * 中华人民共和	==>	中华人民共和
     * 中华人民共和国	==>	中华人民共和...
     */
    public static String ellipsisStr(String str, int len, boolean nullToBlank) {
        if (str == null) if (nullToBlank) return BLANK;
        else return str;
        if (str.length() <= len) return str;
        else return str.substring(0, len) + ELLIPSIS;
    }

    /**
     * 判断给定字符串是否为空 或者为空字符串
     *
     * @param str
     * @return true表示空， false表示不为空
     */
    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str) || "null".equals(str));
    }

    /**
     * 判断给定字符串是否不为空 或者不为空字符串
     *
     * @param str
     * @return fasle表示空， true表示不为空
     */
    public static boolean isNotEmpty(String str) {
        return (str != null && !"".equals(str) && !"null".equals(str));
    }

    /**
     * 判断给定Long是否为空 或者为0
     *
     * @param str
     * @return true表示空， false表示不为空
     */
    public static boolean isEmpty(Long str) {
        return (str == null || str.longValue() == 0);
    }

    /**
     * 判断给定Long是否不为空 或者不为0
     *
     * @param str
     * @return fasle表示空， true表示不为空
     */
    public static boolean isNotEmpty(Long str) {
        return (str != null && str.longValue() != 0);
    }

    /**
     * 判断给定Integer是否为空 或者为0
     *
     * @param str
     * @return true表示空， false表示不为空
     */
    public static boolean isEmpty(Integer str) {
        return (str == null || str.intValue() == 0);
    }

    /**
     * 判断给定Integer是否不为空 或者不为0
     *
     * @param str
     * @return fasle表示空， true表示不为空
     */
    public static boolean isNotEmpty(Integer str) {
        return (str != null && str.intValue() != 0);
    }

    /**
     * 判断字符串对象与给定值是否相等
     * 都为空表示相等
     *
     * @param StringObject 字符串对象
     * @param value        给定值
     * @return 相等返回true，不相等返回false
     */
    public static boolean isEqualString(String StringObject, String value) {
        if ((StringObject == null && value == null) || (isNotEmpty(StringObject) && StringObject.equals(value)))
            return true;
        return false;
    }

    public static String toJavaScriptStyle(String str) {
        if (str == null || "".equals(str)) return "";
        int strLen = str.length();
        StringBuffer sb = new StringBuffer();
        boolean lastCharIsCR = false;
        for (int i = 0; i < strLen; i++) {
            switch (str.charAt(i)) {
                case '\\':
                    sb.append("\\\\");
                    break;
                case '"':
                    sb.append('\\').append('"');
                    break;
                case '\n':
                    sb.append("\\").append('n').append("\\").append('r');
                    break;
                case '\r':
                    lastCharIsCR = true;
                    break;
                default:
                    if (lastCharIsCR) {
                        sb.append("\\").append('n').append("\\").append('r');
                        lastCharIsCR = false;
                        sb.append(str.charAt(i));
                    } else {
                        sb.append(str.charAt(i));
                    }
            }
        }
        return sb.toString();
//        return str.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\"").replaceAll("\n\r", "\\\\\\\\n\\\\\\\\r");

    }

    /**
     * <p>Description [去除字符创中的\n 回车(\u000a) \t 水平制表符(\u0009)  \s 空格(\u0008) \r 换行(\u000d)]</p>
     *
     * @param str 字符串
     * @return
     * @author : wangjl
     * @update :
     * @date : 2015-7-27
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String getArrayToString(String str[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            sb.append(",");
            sb.append("'");
            sb.append(str[i]);
            sb.append("'");
        }
        String strback = sb.toString();
        return strback.substring(1, strback.length());
    }

    /**
     * <p>Description [字符串转Integer,空值返回null]</p>
     *
     * @param str 字符串
     * @return
     * @author : zlz
     * @update :
     * @date : 2017-8-18
     */
    public static Integer strToInteger(String str) {
        Integer result = null;
        if (str != null && !"".equals(str) && !"null".equals(str)) {
            result = Double.valueOf(str).intValue();
        }
        return result;
    }

    /**
     * <p>Description [字符串转Boolean,空值返回false]</p>
     *
     * @param str 字符串
     * @return
     * @author : zlz
     * @update :
     * @date : 2017-8-18
     */
    public static Boolean strToBoolean(String str) {
        Boolean result = null;
        if ("true".equals(str)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * <p>Description [字符串转Date,空值返回null]</p>
     *
     * @param str 字符串
     * @return
     * @author : zlz
     * @update :
     * @date : 2017-8-18
     */
    public static Date strToDate(String str) {
        Date result = null;
        try {
            if (str != null && !"".equals(str) && !"null".equals(str)) {
                str = str.replace("/", "-");
                if (str.length() >= 17) {
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    result = fmt.parse(str);
                } else if (str.length() == 16) {
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    result = fmt.parse(str);
                } else if (str.length() == 10) {
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                    result = fmt.parse(str);
                } else if (str.length() == 8) {
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                    result = fmt.parse(str);
                } else {
                    DateFormat df = DateFormat.getDateInstance();
                    result = df.parse(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * <p>Description [字符串转Double,空值返回null]</p>
     *
     * @param str 字符串
     * @return
     * @author : zlz
     * @update :
     * @date : 2017-8-18
     */
    public static Double strToDouble(String str) {
        Double result = new Double(0.0);
        if (str != null && !"".equals(str) && !"null".equals(str)) {
            result = Double.valueOf(str);
        }
        return result;
    }

    /**
     * <p>Description [字符串转Double,空值返回null]</p>
     *
     * @param str 字符串
     * @return
     * @author : zlz
     * @update :
     * @date : 2017-8-18
     */
    public static Double strToDoubleTimes(String str) {
        Double result = new Double(0.0);
        if (str != null && !"".equals(str) && !"null".equals(str)) {
            String[] timeStr = str.split(":");
            result = Double.valueOf(timeStr[0]) * 60;
            if (timeStr.length > 1) {
                result += Double.valueOf(timeStr[1]);
            }
        }
        return result;
    }

    /**
     * 转换一个xml格式的字符串到json格式
     *
     * @param xml xml格式的字符串
     * @return 成功返回json 格式的字符串;失败反回null
     */
    public static JSONObject xml2JSON(String xml) {
        JSONObject obj = new JSONObject();
        try {
            if (xml.contains("encoding")) {
                xml = xml.substring(xml.indexOf("?>") + 2);
            }
            InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));
            SAXBuilder sb = new SAXBuilder();
            Document doc = sb.build(is);
            Element root = doc.getRootElement();
            obj.put(root.getName(), iterateElement(root));
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 一个迭代方法
     *
     * @param element : org.jdom.Element
     * @return java.util.Map 实例
     */
    @SuppressWarnings("unchecked")
    private static Map iterateElement(Element element) {
        List jiedian = element.getChildren();
        Element et = null;
        Map obj = new HashMap();
        List list = null;
        for (int i = 0; i < jiedian.size(); i++) {
            list = new LinkedList();
            et = (Element) jiedian.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.getChildren().size() == 0) {
                    continue;
                }
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(iterateElement(et));
                obj.put(et.getName(), list);
            } else {
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(et.getTextTrim());
                obj.put(et.getName(), list);
            }
        }
        return obj;
    }

    /**
     * 获取同一天两个时间之间的差值
     * startingTime ： 开始时间  例：07:11
     * endingTime ： 结束时间  例：10:29
     **/
    public static String getBetweenTime(String startingTime, String endingTime) {
        String betweenTime = "";
        if (startingTime != null && !"".equals(startingTime) && !"null".equals(startingTime)
                && endingTime != null && !"".equals(endingTime) && !"null".equals(endingTime)) {
            String[] startingTimes = startingTime.split(":");
            String[] endingTimes = endingTime.split(":");
            Integer hours = Integer.valueOf(endingTimes[0]) - Integer.valueOf(startingTimes[0]);
            Integer minutes = Integer.valueOf(endingTimes[1]) - Integer.valueOf(startingTimes[1]);
            if (minutes < 0) {
                minutes += 60;
                hours--;
            }
            betweenTime = hours + "小时" + minutes + "分钟";
        }
        return betweenTime;
    }

    /**
     * @param obj 获取对象的string；
     * @Author: noob
     * @Date: 2018/01/11
     */
    public static String getObjectString(Object obj) {
        try {
            if (obj != null) {
                String inp = obj.toString();
                if (isEmpty(inp)) {
                    return "";
                } else {
                    return inp;
                }
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }

    /**
     * @param obj
     * @Author: noob
     * @Date: 2018/01/11
     */
    public static Double getStringDouble(Object obj) {
        String inp = getObjectString(obj);
        try {
            if (isEmpty(inp)) {
                return 0.00D;
            } else {
                return Double.parseDouble(inp);
            }
        } catch (Exception e) {
            return 0.00D;
        }
    }

    /**
     * @param obj
     * @Author: noob
     * @Date: 2018/01/11
     */
    public static Integer getStringInteger(Object obj) {
        String inp = getObjectString(obj);
        try {
            if (isEmpty(inp)) {
                return 0;
            } else {
                return Integer.parseInt(inp) < 0 ? 0 : Integer.parseInt(inp);
            }
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * array装换为string
     *
     * @param array
     * @param split
     * @return
     */
    public static String arrayToString(Object[] array, String split) {
        if (array == null) {
            return "";
        }
        String str = "";
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                str += array[i].toString() + split;
            } else {
                str += array[i].toString();
            }
        }
        return str;
    }

    /**
     * Object装换为sql.date
     *
     * @param object
     * @return
     */
    public static java.sql.Date strTosqlDate(Object object) {
        Date date = strToDate(getObjectString(object));
        if (date != null) {
            return new java.sql.Date(date.getTime());
        }
        return null;
    }

    /**
     * @param
     * @return
     * @Author: noob
     * @dsescription 获取主键
     * @Date: 2018/01/31
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String join(Object[] o, String flag) {
        StringBuffer str_buff = new StringBuffer();

        for (int i = 0, len = o.length; i < len; i++) {
            str_buff.append(String.valueOf(o[i]));
            if (i < len - 1) str_buff.append(flag);
        }

        return str_buff.toString();
    }

    public static String spild(String str, String spild, String newSpild) {
        Object[] array = str.split(spild);
        return join(array, newSpild);
    }


}
