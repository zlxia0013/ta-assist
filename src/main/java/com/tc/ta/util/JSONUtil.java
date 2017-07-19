package com.tc.ta.util;

/*
 * java内置转换为json时，简单类型的问题。
 */

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * JSONUtil :json util for java 简易JSON解析工具<br>
 * <br>
 * 使用方法和限制：使用调用其中静态方法即可，不支持数组[]<br>
 * 键值对对象key必须是基础类型，否则报错。<br>
 * <br>
 * 四个核心方法：<br>
 * 1、Java Object to json String example：<br>
 * String str=JSONUtil.toJSONstring(obj); <br>
 * desc:<br>
 * 支持map、List转换json。不能使用Object[]这种形式，需以Collection代替。<br>
 * <br>
 * 2、Json String to Java Object example：<br>
 * String str= "{\"name\":\"xuchujun\",\"addr\":{\"city\":\"深圳\",\"street\":\"凤凰街11号\"}}"<br>
 * Map m=(Map)JSONUtil.jsonToObject(str);<br>
 * desc:<br>
 * 限制Map<String,Object>中Object必须为基础类型。<br>
 * <br>
 * 3、Json String to Java Object List desc: 将jsonString解析为一个ArrayList，以List返回。<br>
 * <br>
 * 4、Json String to javaBean example:<br>
 * String phonejson="{\"brand\":\"sony\",\"price\":1000.5}";<br>
 * Phone myPhone=JSONUtil.jsonToBean(phonejson,Phone.class);<br>
 * desc:<br>
 * JavaBean需有无参构造方法，getter,setter。class为set、list、Map默认创建HashSet、ArrayList、 HashMap返回<br>
 * 若为具体类以该类型返回，若为其他接口则报错。<br>
 * <br>
 * 4、JSON String to javaBean list example:<br>
 * String str= "[{\"brand\":\"sony\",\"price\":1000.5},{\"brand\":\"nokia\",\"price\":999}]"<br>
 * List phonelist=JSONUtil.jsonToBeanList(str,Phone.Class);<br>
 * desc:规则与json转javaBean相同<br>
 * <br>
 * <br>
 * 
 * @author xcj<br>
 * @date 2014-12-31
 * @version 1.1
 * @description 2014-11-29 1.0 2014-12-31 1.1
 */
public class JSONUtil {
	// 本地异常日志记录对象
	protected static Logger	log	= Logger.getLogger(JSONUtil.class);

	/**
	 * 将json字符串转换为java对象(非List)
	 * 
	 * @param json字符串
	 *            ,非数组形式,否则报错。
	 */
	public static Map<String, Object> jsonToObject(String js) {
		log.info("jsonToObject start");
		Map<String, Object> reMap = new HashMap<String, Object>();
		// 刨去首尾括号
		js = js.substring(1, js.length() - 1);
		int lindex = 0, rindex = 1;
		String keystring;
		while (true) {
			// 匹配键
			while (js.charAt(rindex) != '\"' || js.charAt(rindex + 1) != ':')
				rindex++;
			keystring = js.substring(lindex + 1, rindex);
			System.out.println(js.charAt(rindex));
			lindex = rindex = rindex + 2;
			// 匹配值
			Object value = "";
			if (js.charAt(lindex) == '{') {
				rindex = findRightPartIndex(js, lindex, rindex, '{');
				value = jsonToObject(js.substring(lindex, rindex + 1));
			} else if (js.charAt(lindex) == '[') {
				rindex = findRightPartIndex(js, lindex, rindex, '[');
				value = jsonToArray(js.substring(lindex, rindex + 1));
			} else if (js.charAt(lindex) == '\"') {// 此处注意防止匹配下表错误
				rindex = findRightPartIndex(js, lindex, rindex, '\"');
				value = js.substring(lindex + 1, rindex);
			} else {
				rindex = findRightPartIndex(js, lindex, rindex);
				value = js.substring(lindex, rindex + 1);// 匹配到数值,因为有double、int、float多种,此处还是以字串存入
			}
			// System.out.println("chat right index"+js.substring(lindex+1,
			// rindex));
			reMap.put(keystring, value);
			// 判断是否到达字符串尾部
			if (rindex == js.length() - 1)
				break;
			// 若未到尾部,此时rindex在逗号前面
			lindex = rindex + 2;
			rindex += 3;
		}

		log.info("jsonToObject END");
		return reMap;

	}

	/**
	 * 将json字符串转换为数组,格式必须正确
	 */
	public static List jsonToArray(String js) {
		log.info("jsonToArray start");
		List list = new ArrayList();
		js = js.substring(1, js.length() - 1);
		int rightIndex = 0;
		int i = 0;
		while (i < js.length()) {
			// 匹配一个值
			if (js.charAt(i) == '{') {
				int leftPartCount = 1;
				rightIndex = i;
				Map.Entry<String, Object> entry;
				// 匹配键
				while (leftPartCount != 0) {
					rightIndex++;
					if (js.charAt(rightIndex) == '{')
						leftPartCount++;
					if (js.charAt(rightIndex) == '}')
						leftPartCount--;

				}
				Map m = JSONUtil.jsonToObject(js.substring(i, rightIndex));
				list.add(m);

				i = rightIndex;
				if (rightIndex == js.length() - 1)
					break;
			}
			i++;
		}
		log.info("jsonToArray END");
		return list;
	}

	/**
	 * JAVA对象(支持map、list、int、String、Long、double、float类型) to json string
	 * 
	 * @param obj
	 *            list or map
	 * @author xcj date:2014-11-19 map、list 2014-12-26 基础类型支持
	 */
	public static String toJSONstring(Object obj) throws Exception {
		log.info("toJSONstring start");
		/*
		 * 判断数组为map or list
		 */
		if (obj instanceof Collection == false && obj instanceof Map == false)
			throw new JsonException("ERROR:CAN NOT Stringfy a null Object TO JSON STRING");

		StringBuilder sb = new StringBuilder();
		if (obj instanceof List) {
			List list = (List) obj;
			sb.append("[");
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				sb.append(toJSONstring(iterator.next()));
				sb.append(",");
			}
			sb.setLength(sb.length() - 1);
			sb.append("]");
		} else if (obj instanceof Map) {
			Map<String, Object> m = (Map) obj;
			sb.append("{");
			for (Map.Entry<String, Object> entry : m.entrySet()) {
				sb.append("\"" + entry.getKey() + "\":");
				sb.append(toJSONstring(entry.getValue()));
				sb.append(",");
			}
			sb.setLength(sb.length() - 1);
			sb.append("}");
		}
		log.info("toJSONstring END");
		return sb.toString();
	}

	/**
	 * 简单类型转为json,未完
	 * 
	 * @return "name":"xcj"或者"age":23格式的字符串
	 * @throws JsonException
	 */
	private String SimpleDatatoJsonString(Object obj) throws JsonException {
		log.info("SimpleDatatoJsonString start");
		Class c = obj.getClass();
		if (!c.isPrimitive())// 枚举判定
		{
			throw new JsonException("非基础类型");
		}
		String name = obj.getClass().getName();
		if (obj instanceof Number) {
			return obj.toString();
		} else if (obj instanceof String) {
			return "\"" + obj + "\"";
		} else if (obj instanceof Float) {
			return obj.toString();
		} else if (obj instanceof Double) {
			return obj.toString();
		}
		log.info("SimpleDatatoJsonString END");
		return "";
	}

	/**
	 * json转换为指定JavaBean
	 * 
	 * @throws JsonException
	 * @description 不能转换为抽象类或接口。
	 */
	public static <T> T jsonToBean(String js, Class<T> beanClass) throws JsonException {
		log.info("jsonToBean start");
		/*
		 * 步骤： 创建对象,并获取其所有属性数组fields和公开方法数组methods。 解析key,遍历fields获取等于key的Field 获取field的声明类型。进一步获取该属性的set方法Method 解析获得value,根据value的不同类型进行实例化,分为集合,数值,字符串,对象四种。
		 * 调用method的invoke方法给对象赋值。 循环直到json串尾,返回。
		 */

		try {
			T returnobj = beanClass.newInstance();// 创建对象
			Method[] methods = returnobj.getClass().getMethods();// 获取对象所有共有方法
			Field[] fields = returnobj.getClass().getDeclaredFields();// 获取对象所有属性

			// 刨去首尾括号
			js = js.substring(1, js.length() - 1);

			// 循环过程中用来记录的变量
			int lindex = 0, rindex = 1;
			Method method = null;
			Field field = null;
			String methodString;
			String keystring;
			Class fieldClass = null;
			while (true) {
				// 匹配键
				while (js.charAt(rindex) != '\"' || js.charAt(rindex + 1) != ':')
					rindex++;
				keystring = js.substring(lindex + 1, rindex);
				methodString = "set" + keystring.substring(0, 1).toUpperCase() + keystring.substring(1, keystring.length());
				// method=beanClass.getMethod(methodString);
				Boolean isFieldChanged = false;
				for (Field f : fields) {
					// 匹配属性名称
					if (f.getName().equals(keystring)) {
						// 获取属性的声明类型
						fieldClass = f.getType();
						field = f;
						// 获取该属性的set方法
						method = beanClass.getMethod(methodString, fieldClass);
						isFieldChanged = true;
						break;
					}

				}

				if (isFieldChanged == false)
					throw new JsonException("JSON结构错误," + beanClass.getName() + "无法找到匹配key:" + keystring);
				lindex = rindex = rindex + 2;

				/* 匹配值value */
				Object value = "";
				if (js.charAt(lindex) == '{') {
					// 对象,则传入该属性的声明类型,递归构造
					rindex = findRightPartIndex(js, lindex, rindex, '{');
					if (!isJavaInsidedClass(fieldClass))
						value = jsonToBean(js.substring(lindex, rindex + 1), fieldClass);
					else
						value = jsonToObject(js);
				} else if (js.charAt(lindex) == '[') {
					// 集合
					rindex = findRightPartIndex(js, lindex, rindex, '[');
					Type type = field.getGenericType();
					if (type instanceof ParameterizedType) {// type代表泛型化参数
						ParameterizedType paramType = (ParameterizedType) type;
						Type basicType = paramType.getRawType();
						System.out.println("获取基本类型（即该属性的类型，是集合）" + basicType);

						Type[] types = paramType.getActualTypeArguments();
						int i = 0;
						Type t = types[0];
						System.out.println("第" + 1 + "个泛型参数是" + t);
						/*
						 * types是一个泛型参数列表，并不止一个，这里是因为集合只有一个泛型, 所以直接获取第一个作为jsontoBeanlist第三个参数 获取到类型后，使用方法类设置到returnobj
						 */
						value = jsonToBeanList(js.substring(lindex, rindex + 1), fieldClass, (Class) t);
						method.invoke(returnobj, value);
					}

				} else if (js.charAt(lindex) == '\"') {// 此处注意防止匹配下表错误
					// 字符串
					rindex = findRightPartIndex(js, lindex, rindex, '\"');
					value = method.invoke(returnobj, js.substring(lindex + 1, rindex));
				} else {
					// 数值
					rindex = findRightPartIndex(js, lindex, rindex);
					String valuestr = js.substring(lindex, rindex + 1);

					if (fieldClass == Integer.class) {
						value = method.invoke(returnobj, Integer.parseInt(valuestr));
					} else if (fieldClass == Float.class) {
						value = method.invoke(returnobj, Float.parseFloat(valuestr));
					} else if (fieldClass == Double.class) {
						value = method.invoke(returnobj, Double.parseDouble(valuestr));
					} else if (fieldClass == Long.class) {
						value = method.invoke(returnobj, Long.parseLong(valuestr));
					}
				}
				// System.out.println(returnobj);
				// 判断是否到达字符串尾部
				if (rindex == js.length() - 1)
					break;
				// 若未到尾部,此时rindex在逗号前面
				lindex = rindex + 2;
				rindex += 3;
			}
			return returnobj;
		} catch (JsonException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// JsonException ep=new JsonException("反射构造JavaBean失败");
			// ep.setStackTrace(e.getStackTrace());
			// throw ep;
			e.printStackTrace();
		}
		log.info("jsonToBean END");
		return null;
	}

	/**
	 * json转换为JavaBean指定类型数组
	 * 
	 * @param <T>
	 * @param js
	 *            json格式串
	 * @param beanClass转换为目标类型
	 *            ，需为Set,List的具体类，抽象类或接口，将抛出JSONException itemClass数组元素的类型，null或接口将抛出JSONException
	 * 
	 * example：//build jsonstring List phonelist=JSONUtil.jsonToBeanlist(jsonstring,ArrayList.class);
	 * @throws JsonException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> Object jsonToBeanList(String js, Class<T> beanClass, Class itemClass) throws JsonException, InstantiationException,
			IllegalAccessException {
		log.info("jsonToBeanList start");
		// 判断是否符合转换格式强求
		if ((beanClass.isInterface() && !beanClass.getSimpleName().equals("Set") && !beanClass.getSimpleName().equals("List")) || beanClass == null) {
			throw new JsonException("args beanClass can not be null or interface");
		}
		if (itemClass.isInterface() || itemClass == null) {
			if (itemClass == null)
				return js;// 由于泛型的使用，在过程中，没法事先定下list元素的类型，如果是在json中含有[]，此处itemClass将为空
			throw new JsonException("args itemClass can not be null or interface");
		}
		// 并非来自Set和List的具体类
		if (!beanClass.isAssignableFrom(Set.class) && !beanClass.isAssignableFrom(List.class))
			throw new JsonException("only support parse to Class implements Collection");

		Collection coll;
		if (beanClass.getSimpleName().equals("Set"))
			coll = new HashSet();
		else if (beanClass.getSimpleName().equals("List"))
			coll = new ArrayList();
		else
			coll = (Collection) beanClass.newInstance();

		// 除去首尾中括号
		js = js.substring(1, js.length() - 1);
		int rightIndex = 0;
		int i = 0;
		while (i < js.length()) {
			// 匹配一对大括号，并把大括号内容转化为JAVA对象
			if (js.charAt(i) == '{') {
				int leftPartCount = 1;
				rightIndex = i;
				Map.Entry<String, Object> entry;
				// 匹配键
				while (leftPartCount != 0) {
					rightIndex++;
					if (js.charAt(rightIndex) == '{')
						leftPartCount++;
					if (js.charAt(rightIndex) == '}')
						leftPartCount--;

				}
				Object m = JSONUtil.jsonToBean(js.substring(i, rightIndex), itemClass);
				coll.add(m);

				i = rightIndex;
				if (rightIndex == js.length() - 1)
					break;
			}
			i++;
		}
		log.info("jsonToBeanList END");
		return coll;
	}

	/**
	 * javaBean to Json串
	 * 
	 * @throws JsonException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @params 解析对象，只能为具体类
	 */
	public static String beanToJson(Object obj) throws JsonException, IllegalArgumentException, IllegalAccessException {
		log.info("beanToJson start" + obj.getClass().getSimpleName());
		if (obj == null)
			return "";
		Class c = obj.getClass();
		Field[] fields = c.getDeclaredFields();// 虽然获取field，但是Java内置对象不应使用field和method
		Method[] methods = c.getDeclaredMethods();

		boolean isCollection = false;
		boolean isMap = false;
		Class[] faces = c.getInterfaces();
		for (Class tc : faces) {// 判断对象是否实现Set,List或者Map
			if (tc.getSimpleName().equals("Set") || tc.getSimpleName().equals("List") || tc.getSimpleName().equals("Collection"))
				;
			isCollection = true;
			if (tc.getSimpleName().equals("Map")) {
				isMap = true;
			}
		}

		StringBuffer bf = new StringBuffer();
		if (isMap) {// map实现类
			/*
			 * 获取map泛型，如果有类型，那么iterator递归的类也应该是该类型。
			 */
			bf.append("{");
			Set<Map.Entry> set = ((Map) obj).entrySet();

			for (Map.Entry en : set) {
				Class valueClass = en.getValue().getClass();
				Class keyClass = en.getKey().getClass();

				if (SimpleClass.isSimpleClass(keyClass)) {// 值是基础的
					bf.append(simpleObjectJSON(en.getKey().toString(), en.getValue().toString()) + ",");
				} else {
					bf.append("\"" + en.getKey() + "\":" + beanToJson(en.getValue()) + ",");
				}
			}
			bf.deleteCharAt(bf.length() - 1).append("}");
		} else if (isCollection) {// Collection实现类,Collection中不能装int等基础类型
			bf.append("[");
			Collection coll = ((Collection) obj);
			Iterator iterator = coll.iterator();

			while (iterator.hasNext()) {
				Object item = iterator.next();
				if (SimpleClass.isSimpleClass(item.getClass())) {
					// 值是基础的,1231注：此if无用，因为不支持无key的json解析
					bf.append(simpleObjectJSON(null, item) + "\",");
				} else {
					bf.append(beanToJson(item) + ",");
				}
			}
			bf.deleteCharAt(bf.length() - 1).append("]");
		} else if (c.isPrimitive()) {
			/*
			 * 最初调用就是基础类型，无法获取对象名称。直接返回toString 最初调用非基础类型，则不使用基础类型调用beanTojson
			 */
			return obj.toString();
		} else {// 自定义javabean
			bf.append("{");
			for (Field f : fields) {
				/*
				 * 基础类型直接转成字符串，非基础类型将属性当做对象递归解析
				 */
				if (SimpleClass.isSimpleClass(f.getType())) {
					bf.append(simpleObjectJSON(f.getName(), f.get(obj)) + ",");
				} else {
					bf.append("\"" + f.getName() + "\":" + beanToJson(f.get(obj)) + ",");
				}
			}
			bf.deleteCharAt(bf.length() - 1).append("}");
		}
		log.info("beanToJson END");
		return bf.toString();
	}

	/**
	 * 将基础类型对象转换为json，不含括号 example: use:JSONUtil.simpleObjectJSON("name","xcj") "name":"xcj" use:JSONUtil.simpleObjectJSON(null,"xcj")
	 * JSONUtil.simpleObjectJSON("name",null) JSONException null key or value use:JSONUtil.simpleObjectJSON("age",123) "age":123 注：json可以无key，但工具现在无法保证无 key
	 * 数据的转换正确，所以此处无key报错。
	 */
	public static String simpleObjectJSON(String key, Object item) {
		try {
			if (key == null || item == null)
				throw new JsonException("转换key:" + key + ",对象:" + item + ",key 或value 为空");
			if (!SimpleClass.isSimpleClass(item.getClass()))
				throw new JsonException("转换的对象必须为基础类型");
		} catch (JsonException e) {
			e.printStackTrace();
		}
		String classname = item.getClass().getSimpleName();
		if (classname.equals("Integer") || classname.equals("Double") || classname.equals("Float") || classname.equals("long"))
			return "\"" + key + "\":" + item;
		else
			return "\"" + key + "\"" + ":\"" + item + "\"";
	}

	/**
	 * 自定义转换为json的统计方法
	 */
	public static String toJSON() {
		return "";
	}

	/**
	 * 查找字符串中指定左半部的右半部下标,如左半部是{,可以查找到对应}的下标
	 * 
	 * @param js
	 * @param lindex
	 *            rindex 两个参数相等,为值的第一个下标
	 * @param c
	 *            左半部
	 * @return 匹配的右半部的下标
	 */
	private static int findRightPartIndex(String js, int lindex, int rindex, char c) {
		int leftPartCount = 1;
		char rc = '\"';
		if (c == '{')
			rc = '}';
		else if (c == '\"')
			rc = '\"';
		else if (c == '[')
			rc = ']';
		else if (c == ',')
			return 0;

		while (rindex < js.length()) {
			rindex++;
			char charIndex = js.charAt(rindex);
			if (charIndex == rc)
				leftPartCount--;
			else if (charIndex == c)
				leftPartCount++;

			if (leftPartCount == 0)
				break;

		}
		if (rindex == js.length())
			throw new RuntimeException("json字符串解析错误：无法找到匹配右部");
		return rindex;
	}

	/**
	 * 返回数值型值的右部下标
	 * 
	 * @param js
	 * @param lindex
	 * @param rindex
	 * @return
	 */
	private static int findRightPartIndex(String js, int lindex, int rindex) {// 有两种情况
		// 1、下标是结尾
		// 2、非结尾,判断标志是下一个字符为逗号

		int leftPartCount = 1;

		while (js.length() - rindex > 4) {// 当js.length()-rindex<6时,说明这已经是最后一个键值对了。没有别计较的必要,直接返回最后下标
			rindex++;
			char charIndex = js.charAt(rindex + 1);
			if (charIndex == ',')
				return rindex;

		}
		return js.length() - 1;
	}

	/**
	 * 是否java内置对象
	 * 
	 * @param c
	 * @return
	 */
	private static boolean isJavaInsidedClass(Class c) {
		String className = c.getSimpleName();
		if (c.getName().matches("java*") || c.getName().matches("javax*"))
			return true;
		return false;
	}
}
