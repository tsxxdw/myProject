package cn.tsxxdw.service.mycharacterspinyin;

import cn.tsxxdw.service.mystr.MyStrUtils;
import com.google.common.collect.Lists;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Arrays;
import java.util.List;

public class CharactersPinyinUtil {

    /**
     * 将汉字转换为全拼
     *
     * @param src
     * @return String
     */
    public static String getPinYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        // System.out.println(t1.length);
        String[] t2 = new String[t1.length];
        // System.out.println(t2.length);
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                // System.out.println(t1[i]);
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
                    t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
                } else {
                    // 如果不是汉字字符，直接取出字符并连接到字符串t4后
                    t4 += Character.toString(t1[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t4;
    }

    /**
     * 提取每个汉字的首字母
     *
     * @param str
     * @return String
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    /**
     * 将字符串转换成ASCII码
     *
     * @param cnStr
     * @return String
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        // 将字符串转换成字节序列
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i] & 0xff));
            // 将每个字符转换成ASCII码
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff) + " ");
        }
        return strBuf.toString();
    }

    public static void main(String[] args) {
        String cnStr = "/data/4_uploadfile/images/vegetable_chart/常见菜式/补偿金";
        System.out.println(getPinYin(cnStr));
        System.out.println(getPinYinHeadChar(cnStr));
//      System.out.println(getCnASCII(cnStr));

        a();
       // b();

    }


    private static void a() {
        String replaceStr = "pyzm";
        String url = "https://home.meishichina.com/recipe/pyzm/page/1/";
      //  String 常见菜式="热菜,凉菜,汤羹,主食,小吃,家常菜,泡酱腌菜,西餐,烘焙,烤箱菜,饮品,零食,火锅,自制食材,海鲜,宴客菜";
    //    String 甜品饮品="甜品,冰品,果汁,糖水,布丁,果酱,果冻,酸奶,鸡尾酒,咖啡,豆浆,奶昔,冰淇淋";
      //  String 主食小吃="米饭,炒饭,面食,包子,饺子,馒头花卷,面条,饼,粥,馄饨,五谷杂粮,北京小吃,陕西小吃,广东小吃,四川小吃,重庆小吃,天津小吃,上海小吃,福建小吃,湖南小吃,湖北小吃,江西小吃,山东小吃,山西小吃,河南小吃,台湾小吃,江浙小吃,云贵小吃,东北小吃,西北小吃";
       String 中式菜系 = "新疆菜,xianggangcai,taiwancai,上海菜,客家菜,北京菜,川菜,鲁菜,闽菜,粤菜,苏菜,浙菜,湘菜,徽菜,淮扬菜,豫菜,晋菜,鄂菜,云南菜,东北菜,西北菜,贵州菜,客家菜,aomeicai,赣菜,zhongshicaixi";
      //  String 外国美食 = "ribencai,hanguocai,taiguocai,yiducai,faguocai,yidalicai,xibanya,yingguocai,yuenancai,moxigecai,waiguomeishi";
      //  String 烘培 = "蛋糕,面包,饼干,派塔,纸杯,蛋糕卷,吐司,戚风,玛芬,乳酪,芝士,批萨,奶油,慕斯,曲奇,翻糖";
       // String 传统美食 = "粽子,月饼,春饼,元宵,腊八粥,春卷,汤圆,青团,传统美食";
      //  String 节日食俗 = "立冬,冬至,腊八,端午节,中秋,立春,元宵节,贴秋膘,清明,年夜饭,圣诞节,感恩节,万圣节,情人节,复活节,雨水,惊蛰,春分,谷雨,立夏,小满,芒种,夏至,小暑,大暑,立秋,处暑,白露,秋分,寒露,霜降,小雪,大雪,小寒,大寒,二月二,母亲节,父亲节,儿童节,七夕,重阳节,节日习俗";

        List<String> urlList1 = Lists.newArrayList();
        List<String> list1 = Arrays.asList(中式菜系.split(","));
        list1.forEach(o -> {
            String py = getPinYin(o);
            String tempUrl = url.replace(replaceStr, py);
            System.out.println(tempUrl);
        });

    }


    private static void b() {
        String str = "https://home.meishichina.com/recipe-type-do-technics-view-replaceStr.html";
        String replaceStr = "replaceStr";
        MyStrUtils.replaceStrForInt(str, replaceStr, 1, 51).forEach(o->{
            System.out.println(o);
        });
    }


}






