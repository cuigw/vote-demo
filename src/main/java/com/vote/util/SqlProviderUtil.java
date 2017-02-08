package com.vote.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cgw on 2017/2/7.
 */
public class SqlProviderUtil {

    /**
     * 按非空值拼装insert 语句，支持sequence
     * @param obj
     * @param tableName
     * @param columns 有效字段，传入时筛选，不区分大小写
     * @return
     */
    public static String provideInsertNotBlank(Object obj, String tableName, String ... columns) {
        Map<String, String> aliasMap = MapUtil.getFiledAlias(obj);
        StringBuffer buff = new StringBuffer("insert into ");
        buff.append(tableName);
        List<String> list = new ArrayList<String>();
        Map<String, Object> objMap = toMap(obj);
        for (Map.Entry<String, Object> entry : objMap.entrySet()) {
            String k = entry.getKey();
            if (!ArrayUtils.isEmpty(columns) && !containsIgnoreCase(columns, k)) {
                continue;
            }
            Object v = entry.getValue();
            if (v != null && StringUtils.isNotBlank(String.valueOf(v))) {
                list.add(k);
            }
        }
        buff.append("(");
        for (String col : list) {
            String columnName = col;
            if (aliasMap.containsKey(col)) {
                columnName = aliasMap.get(col);
            }
            buff.append(columnName).append(",");
        }
        buff.append(") values(");
        for (String col : list) {
//            Object v = objMap.get(col);
//            if (v instanceof SysDate) {
//                // buff.append("sysdate,");
//                dealSysDateParam4Insert((SysDate)v, buff);
//            } else if (StringUtils.endsWithIgnoreCase(String.valueOf(v), SEQUENCE_NEXTVAL)) {
//                buff.append(v).append(",");
//            } else {
                buff.append("#{").append(col).append("}").append(",");
           // }
        }
        buff.append(")");
        return buff.toString().replaceAll(",\\)", "\\)");
    }

    /**
     * 按非空值拼装update中的setter
     * @param obj
     * @param columns 有效字段，传入时筛选，不区分大小写
     * @return
     */
    public static String provideSetterNotBlank(Object obj, String ... columns) {
        StringBuffer buff = new StringBuffer("set  ");
        Map<String, String> aliasMap = MapUtil.getFiledAlias(obj);
        for (Map.Entry<String, Object> entry : toMap(obj).entrySet()) {
            String k = entry.getKey();
            if (!ArrayUtils.isEmpty(columns) && !containsIgnoreCase(columns, k)) {
                continue;
            }
            Object v = entry.getValue();
            if (v != null && StringUtils.isNotBlank(String.valueOf(v))) {
//                if (v instanceof SysDate) {
//                    // buff.append(k).append("=sysdate, ");
//                    dealSysDateParam4Setter(k, (SysDate)v, buff);
//                } else {
                    String columnName = k;
                    if (aliasMap.containsKey(k)) {
                        columnName = aliasMap.get(k);
                    }
                    buff.append(columnName).append("=").append("#{").append(k).append("}, ");
//                }
            }
        }
        return buff.substring(0, buff.length() - 2);
    }

    /**
     * 把对象转成Map<String,Object> <br/>
     * 这里不能直接用MapUtil.toMap(obj)来转，因为toMap支持别名，得到的map中有别名的会有2个字段和值，这就比数据库表中的字段多
     * @param obj
     * @return
     */
    private static Map<String, Object> toMap(Object obj) {
        return MapUtil.toMapIgnoreAlias(obj);
    }

    /**
     * 判断包含关系，不区分大小写
     * @param array
     * @param value
     * @return
     */
    private static boolean containsIgnoreCase(String[] array, String value) {
        if (array == null || array.length == 0) {
            return false;
        }
        for (String v : array) {
            if (StringUtils.equalsIgnoreCase(v, value)) {
                return true;
            }
        }
        return false;
    }
}
