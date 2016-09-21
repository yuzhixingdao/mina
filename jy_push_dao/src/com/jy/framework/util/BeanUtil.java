package com.jy.framework.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

public class BeanUtil {
	static final Log log = LogFactory.getLog(BeanUtil.class);

	public static <OBJECT,TOOBJECT> List<TOOBJECT> object2object(List<OBJECT> objectList, Class<TOOBJECT> toobjectClass){
		if(objectList != null){
			List<TOOBJECT> toobjectList = new ArrayList<TOOBJECT>();
			for(int i=0; i<objectList.size(); i++){
				
				try {
					TOOBJECT toobject = toobjectClass.newInstance();
					OBJECT object = objectList.get(i);
					copy(toobject, object);
					toobjectList.add(toobject);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
			}
			return toobjectList;
		}
		return null;
	}
	
	public static <OBJECT,TOOBJECT> TOOBJECT object2object(OBJECT object, Class<TOOBJECT> toobjectClass){
		
		if(object != null){
			try {
				TOOBJECT toobject = toobjectClass.newInstance();
				copy(toobject, object);
				return toobject;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static void copy(Object dest, Object src) {
		try {
			if(dest != null && src != null){
				PropertyUtils.copyProperties(dest, src);
			}
		} catch (Exception e) {
			log.error(e.toString());
			throw new RuntimeException("error while copy properties");
		}
	}
	
	/**
	 * 
	 * @param listArray
	 * @param pojoClass
	 * @param setNameAndClass odd setName, even class
	 * @return
	 */
	public static List listArray2ListPojo(List<Object> listArray, Class pojoClass, Object ... setNameAndClass) {
		
		if(listArray != null && listArray.size() > 0){
			
			List retList = new ArrayList();
			
			for (int i = 0; i < listArray.size(); i++) {
				
				Object pojo = null;
				
				Object[] object = (Object[])listArray.get(i);
				
				int leng = object.length;
				if(leng > 0){
					
					try {
						pojo = pojoClass.newInstance();
						copy(pojo, object[0]);
						
						for (int j = 1; j < leng; j++) {
							Method method = pojo.getClass().getMethod(setNameAndClass[(j-1)*2].toString(), (Class)setNameAndClass[(j-1)*2+1]);
							method.invoke(pojo, object[j]);
						}
						
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					
				}
				
				retList.add(pojo);
			}
			
			return retList;
		}
		
		return null;
	}
	
	
	public static void copyProperties(Object source, Object target, String... ignoreProperties) throws BeansException {
		copyProperties(source, target, null, ignoreProperties);
	}
	
	private static void copyProperties(Object source, Object target, Class<?> editable, String... ignoreProperties)
		throws BeansException {
		
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");
		
		Class<?> actualEditable = target.getClass();
		if (editable != null) {
			if (!editable.isInstance(target)) {
				throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
						"] not assignable to Editable class [" + editable.getName() + "]");
			}
			actualEditable = editable;
		}
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
		
		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null &&
							ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						}
						catch (Throwable ex) {
							throw new FatalBeanException(
									"Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}
	}
	
	 public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	 public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz)
			throws BeansException {
		
		 PropertyDescriptor[] pdArray = null;
		 
		 try {
				Field[] fields = clazz.getDeclaredFields();
				if(fields != null && fields.length > 0){
					List<PropertyDescriptor> pdList = new ArrayList<PropertyDescriptor>();
					for (int i = 0; i < fields.length; i++) {
						try {
							PropertyDescriptor pd = new PropertyDescriptor(fields[i].getName(), clazz);
							pdList.add(pd);
						} catch (Exception e) {}
					}
					
					pdArray = new PropertyDescriptor[pdList.size()];
					for (int i = 0; i < pdList.size(); i++) {
						pdArray[i] = pdList.get(i);
					}
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		 
		return pdArray;
	}

	public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz,
			String propertyName) throws BeansException {
		
		try {
			PropertyDescriptor pd = new PropertyDescriptor(propertyName, clazz);
			return pd;
		} catch (Exception e) {}
		
		return null;
	}
	
}
