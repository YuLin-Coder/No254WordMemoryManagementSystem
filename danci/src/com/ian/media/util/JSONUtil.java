package com.ian.media.util;

/**
 * User: ZhangKe
 * Date: 12-8-3
 * Time: 上午10:51
 * Version: 1.0
 */

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.beanutils.BeanUtils;

import java.sql.Timestamp;
import java.util.*;

/**
 * JSON工具类
 *
 * @author zhangke
 */
public final class JSONUtil {

    private static JsonConfig jsonConfig = new JsonConfig();

    static {
        jsonConfig.registerJsonValueProcessor(String.class, new JSONStringDateTimeProcessor());
        jsonConfig.registerJsonValueProcessor(Date.class, new JSONDateProcessor());
        jsonConfig.registerJsonValueProcessor(Timestamp.class, new JSONDateTimeProcessor());
        jsonConfig.registerJsonValueProcessor(Integer.class, new JSONIntegerProcessor());
    }

    public static void registerDatePattern(String[] datePattern) {
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(datePattern));
    }

    public static String xml2JSONString(String xml){
        XMLSerializer serializer = new XMLSerializer();
        return serializer.read(xml).toString();
    }

    /**
     * 将List对象序列化为JSON文本
     */
    public static <T> String toJSONString(List<T> list) {
        JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);

        return jsonArray.toString();
    }
    
    /**
     * 将List对象序列化为JSON文本
     */
    public static <T> String toJSONString(Set<T> set) {
        JSONArray jsonArray = JSONArray.fromObject(set, jsonConfig);

        return jsonArray.toString();
    }

    /**
     * 将对象序列化为JSON文本
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);
        
        return jsonArray.toString();
    }

    /**
     * 将JSON对象数组序列化为JSON文本
     *
     * @param jsonArray
     * @return
     */
    public static String toJSONString(JSONArray jsonArray) {
        return jsonArray.toString();
    }

    /**
     * 将JSON对象序列化为JSON文本
     *
     * @param jsonObject
     * @return
     */
    public static String toJSONString(JSONObject jsonObject) {
        return jsonObject.toString();
    }

    /**
     * 将对象转换为List对象
     *
     * @param object
     * @return
     */
    public static List toArrayList(Object object) {
        List arrayList = new ArrayList();

        JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);

        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JSONObject jsonObject = (JSONObject) it.next();

            Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                Object key = keys.next();
                Object value = jsonObject.get(key);
                arrayList.add(value);
            }
        }

        return arrayList;
    }

    /**
     * 将对象转换为Collection对象
     *
     * @param object
     * @return
     */
    public static Collection toCollection(Object object) {
        JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);

        return JSONArray.toCollection(jsonArray);
    }

    /**
     * 将对象转换为JSON对象数组
     *
     * @param object
     * @return
     */
    public static JSONArray toJSONArray(Object object) {
        return JSONArray.fromObject(object, jsonConfig);
    }

    /**
     * 将对象转换为JSON对象
     *
     * @param object
     * @return
     */
    public static JSONObject toJSONObject(Object object) {
        return JSONObject.fromObject(object, jsonConfig);
    }

    /**
     * 将对象转换为HashMap
     * @param object
     * @return
     */
    public static HashMap toHashMap(Object object) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        JSONObject jsonObject = JSONUtil.toJSONObject(object);
        Iterator it = jsonObject.keys();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            data.put(key, value);
        }

        return data;
    }

    /**
     * 将对象转换为List<Map<String,Object>>
     *
     * @param object
     * @return
     */
// 返回非实体类型(Map<String,Object>)的List
    public static List<Map<String, Object>> toList(Object object) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Object> map = new HashMap<String, Object>();
            Iterator it = jsonObject.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                Object value = jsonObject.get(key);
                map.put((String) key, value);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 将JSON对象数组转换为传入类型的List
     *
     * @param <T>
     * @param jsonArray
     * @param objectClass
     * @return
     */
    public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass) {
        return JSONArray.toList(jsonArray, objectClass, jsonConfig);
    }

    /**
     * 将对象转换为传入类型的List
     *
     * @param object
     * @param objectClass
     * @return
     */
    public static <T> List<T> toList(Object object, Class<T> objectClass) {
        JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);

        return JSONArray.toList(jsonArray, objectClass, jsonConfig);
    }

    /**
     * 将JSON对象转换为传入类型的对象
     *
     * @param <T>
     * @param jsonObject
     * @param beanClass
     * @return
     */
    public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass) {
        return (T) JSONObject.toBean(jsonObject, beanClass, jsonConfig);
    }

    /**
     * 将将对象转换为传入类型的对象
     *
     * @param <T>
     * @param object
     * @param beanClass
     * @return
     */
    public static <T> T toBean(Object object, Class<T> beanClass) {
        JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);

        return (T) JSONObject.toBean(jsonObject, beanClass, jsonConfig);
    }

    /**
     * 将JSON文本反序列化为主从关系的实体
     *
     * @param <T>         泛型T 代表主实体类型
     * @param <D>         泛型D 代表从实体类型
     * @param jsonString  JSON文本
     * @param mainClass   主实体类型
     * @param detailName  从实体类在主实体类中的属性名称
     * @param detailClass 从实体类型
     * @return
     */
    public static <T, D> T toBean(String jsonString, Class<T> mainClass,
                                  String detailName, Class<D> detailClass) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString, jsonConfig);
        JSONArray jsonArray = (JSONArray) jsonObject.get(detailName);

        T mainEntity = JSONUtil.toBean(jsonObject, mainClass);
        List<D> detailList = JSONUtil.toList(jsonArray, detailClass);

        try {
            BeanUtils.setProperty(mainEntity, detailName, detailList);
        } catch (Exception ex) {
            throw new RuntimeException("主从关系JSON反序列化实体失败！");
        }

        return mainEntity;
    }

    /**
     * 将JSON文本反序列化为主从关系的实体
     *
     * @param <T>泛型T       代表主实体类型
     * @param <D1>泛型D1     代表从实体类型
     * @param <D2>泛型D2     代表从实体类型
     * @param jsonString   JSON文本
     * @param mainClass    主实体类型
     * @param detailName1  从实体类在主实体类中的属性
     * @param detailClass1 从实体类型
     * @param detailName2  从实体类在主实体类中的属性
     * @param detailClass2 从实体类型
     * @return
     */
    public static <T, D1, D2> T toBean(String jsonString, Class<T> mainClass,
                                       String detailName1, Class<D1> detailClass1, String detailName2,
                                       Class<D2> detailClass2) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString, jsonConfig);
        JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
        JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);

        T mainEntity = JSONUtil.toBean(jsonObject, mainClass);
        List<D1> detailList1 = JSONUtil.toList(jsonArray1, detailClass1);
        List<D2> detailList2 = JSONUtil.toList(jsonArray2, detailClass2);

        try {
            BeanUtils.setProperty(mainEntity, detailName1, detailList1);
            BeanUtils.setProperty(mainEntity, detailName2, detailList2);
        } catch (Exception ex) {
            throw new RuntimeException("主从关系JSON反序列化实体失败！");
        }

        return mainEntity;
    }

    /**
     * 将JSON文本反序列化为主从关系的实体
     *
     * @param <T>泛型T       代表主实体类型
     * @param <D1>泛型D1     代表从实体类型
     * @param <D2>泛型D2     代表从实体类型
     * @param jsonString   JSON文本
     * @param mainClass    主实体类型
     * @param detailName1  从实体类在主实体类中的属性
     * @param detailClass1 从实体类型
     * @param detailName2  从实体类在主实体类中的属性
     * @param detailClass2 从实体类型
     * @param detailName3  从实体类在主实体类中的属性
     * @param detailClass3 从实体类型
     * @return
     */
    public static <T, D1, D2, D3> T toBean(String jsonString,
                                           Class<T> mainClass, String detailName1, Class<D1> detailClass1,
                                           String detailName2, Class<D2> detailClass2, String detailName3,
                                           Class<D3> detailClass3) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString, jsonConfig);
        JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
        JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);
        JSONArray jsonArray3 = (JSONArray) jsonObject.get(detailName3);

        T mainEntity = JSONUtil.toBean(jsonObject, mainClass);
        List<D1> detailList1 = JSONUtil.toList(jsonArray1, detailClass1);
        List<D2> detailList2 = JSONUtil.toList(jsonArray2, detailClass2);
        List<D3> detailList3 = JSONUtil.toList(jsonArray3, detailClass3);

        try {
            BeanUtils.setProperty(mainEntity, detailName1, detailList1);
            BeanUtils.setProperty(mainEntity, detailName2, detailList2);
            BeanUtils.setProperty(mainEntity, detailName3, detailList3);
        } catch (Exception ex) {
            throw new RuntimeException("主从关系JSON反序列化实体失败！");
        }

        return mainEntity;
    }

    /**
     * 将JSON文本反序列化为主从关系的实体
     *
     * @param <T>         主实体类型
     * @param jsonString  JSON文本
     * @param mainClass   主实体类型
     * @param detailClass 存放了多个从实体在主实体中属性名称和类型
     * @return
     */
    public static <T> T toBean(String jsonString, Class<T> mainClass,
                               HashMap<String, Class> detailClass) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString, jsonConfig);
        T mainEntity = JSONUtil.toBean(jsonObject, mainClass);
        for (Object key : detailClass.keySet()) {
            try {
                Class value = (Class) detailClass.get(key);
                BeanUtils.setProperty(mainEntity, key.toString(), value);
            } catch (Exception ex) {
                throw new RuntimeException("主从关系JSON反序列化实体失败！");
            }
        }
        return mainEntity;
    }
    

    
    /**
     * 获取集合JSON串
     * @param list 数据集合
     * @param code 成功或失败代码
     * @param msg  成功或失败信息
     * @return
     */
    public static String getJsonStr(List list, int code, String msg) {
    	Map map = new HashMap();
		map.put("data", list);
		map.put("return_code", code);
		map.put("return_msg", msg);
		JSONObject json = JSONObject.fromObject(map);
    	return String.valueOf(json);
    }
    
   
    
    /**
     * 获取集合JSON串
     * @param map 数据map对象
     * @param code 成功或失败代码
     * @param msg  成功或失败信息
     * @return
     */
    public static String getJsonStr(Map map, int code, String msg) {
		map.put("return_code", code);
		map.put("return_msg", msg);
		JSONObject json = JSONObject.fromObject(map);
    	return String.valueOf(json);
    }
    
        
    /**
     * 获取JSON字符串	
     * @param code  成功或失败代码
     * @param msg   成功或失败提示信息
     * @return
     */
    public static String getJsonStr(int code, String msg) {
    	Map map = new HashMap();
		map.put("return_code", code);
		map.put("return_msg", msg);
		JSONObject json = JSONObject.fromObject(map);
    	return String.valueOf(json);
    }
}

