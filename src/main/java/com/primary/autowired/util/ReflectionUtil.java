package com.primary.autowired.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtil {

	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

	private ReflectionUtil() {

	}

	private static ReflectionUtil refObj = null;

	private static HashMap<String, String> objectBeanMap = new HashMap<String, String>() {
		{
			objectBeanMap.put("Avatar", "com.primary.autowired.entity.Avatar");
		}
	};

	private static HashMap<String, HashMap> objGetterPropsMap = new HashMap<String, HashMap>();
	private static HashMap<String, HashMap> objSetterPropsMap = new HashMap<String, HashMap>();

	public static ReflectionUtil getInstance() {
		logger.info("*********************Inside GetInstance Method*************************");
		if (refObj == null) {
			refObj = new ReflectionUtil();
			setUpMethod();
		}
		return refObj;
	}

	private static void setUpMethod() {
		logger.info("*******************************Inside SetUp Methods**************************");
		for (Iterator iterator = objectBeanMap.keySet().iterator(); iterator.hasNext();) {
			String propName = (String) iterator.next();

			HashMap<String, Method> propGetMethodsMap = new HashMap<String, Method>();
			HashMap<String, Method> propSetMethodsMap = new HashMap<String, Method>();

			try {
				logger.info("*******************Inside try catch block of SetUpMethods**********************");

				Class<?> cls = Class.forName(objectBeanMap.get(propName));
				for (PropertyDescriptor pd : Introspector.getBeanInfo(cls).getPropertyDescriptors()) {
					if (!"class".equals(pd.getName())) {
						if (pd.getWriteMethod() != null) {
							propSetMethodsMap.put(pd.getName(), pd.getWriteMethod());
						}
						if (pd.getReadMethod() != null) {
							propGetMethodsMap.put(pd.getName(), pd.getReadMethod());
						}
					}
				}
			} catch (final ClassNotFoundException | IntrospectionException ex) {
				logger.error(ex.getMessage());
			}

			objGetterPropsMap.put(propName, propGetMethodsMap);
			objSetterPropsMap.put(propName, propSetMethodsMap);

		}
		logger.info("***************************End Of SetUp Methods****************************");
	}

	public HashMap<String, HashMap> getObjGetterPropsMap() {
		return objGetterPropsMap;
	}

	public HashMap<String, HashMap> getObjSetterPropsMap() {
		return objSetterPropsMap;
	}

	public Method getSetterMethod(String objectName, String propName) {
		HashMap propMethod = getObjSetterPropsMap().get(objectName);
		return (Method) propMethod.get(propName);
	}

	public Method getGetterMethod(String objectName, String propName) {
		HashMap propMethod = getObjSetterPropsMap().get(objectName);
		return (Method) propMethod.get(propName);

	}

}
