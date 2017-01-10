package org.sword.wechat4j.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by liangkang on 2016/10/25.
 */
public class AnalysisCode {

    public static final Integer RULE_A = 6;

    public static final Integer RULE_B = 9;

    public static String returnA(String code){
        return StringUtils.isNotEmpty(code)?(code.length()>= RULE_A?code.substring(0,RULE_A):""):"";
    }

    public static String returnB(String code){
        return StringUtils.isNotEmpty(code)?(code.length()>= RULE_B?code.substring(0,RULE_B):""):"";
    }
}
