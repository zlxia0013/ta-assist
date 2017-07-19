package com.tc.ta.util;

import java.util.ArrayList;
import java.util.List;

public class ValueSet {
	private String dictName = null;
	private ArrayList<String[]> set = null;
	List<ValueSetObject> valueSetList = null;

	/**
	 * 构造函数。
	 */
	public ValueSet() {
		set = new ArrayList<String[]>();
		valueSetList = new ArrayList<ValueSetObject>();
	}

	/**
	 * 数据字典大类名的构造函数。
	 * 
	 * @param dictName
	 */
	public ValueSet(String dictName) {
		this.dictName = dictName;
		set = new ArrayList<String[]>();
		valueSetList = new ArrayList<ValueSetObject>();
	}

	/**
	 * 设置数据字典大类名称。
	 * 
	 * @param dictName
	 */
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	/**
	 * 添加一条记录。
	 * 
	 * @param subKey
	 *            - 子关键字。
	 * @param value
	 *            - 对应值。
	 */
	public void addValue(String subKey, String subName) {
		String[] row = new String[2];
		row[0] = subKey;
		row[1] = subName;
		set.add(row);

		ValueSetObject obj = new ValueSetObject();
		obj.setKey(subKey);
		obj.setValue(subName);
		valueSetList.add(obj);
	}

	/**
	 * 获取数据字典大类的名称。
	 */
	public String getDictName() {
		return this.dictName;
	}

	/**
	 * 根据指定子关键字获取值。
	 * 
	 * @param subKey
	 *            - 子关键字
	 */
	public String getValue(String subKey) {
		for (int i = 0; i < set.size(); i++) {
			String[] row = (String[]) set.get(i);
			if (row[0].equals(subKey)) {
				return row[1];
			}
		}
		return "";
	}
	
	/**
	 * 根据指定值获取关键字
	 * 
	 * @param subKey
	 *            - 子关键字
	 */
	public String getKey(String subValue) {
		for (int i = 0; i < set.size(); i++) {
			String[] row = (String[]) set.get(i);
			if (row[1].equals(subValue)) {
				return row[0];
			}
		}
		return "";
	}

	/**
	 * 获取所有值。
	 */
	public ArrayList<String[]> getValue() {
		return set;
	}

	/**
	 * 取得json对象值
	 * 
	 * @return
	 */
	public List<ValueSetObject> getListSet() {
		return valueSetList;
	}
}
