package br.com.brytecnologia.desafio.backend.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class EntityUtils {
	private EntityUtils() {
	}

	public static <A, B> void copy(A a, B b) {
		if (a == null || b == null) {
			return;
		}
		try {
			BeanUtils.copyProperties(b, a);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new IllegalArgumentException("Falha na copia das propriedades das entidades do tipo " + a.getClass()
					+ " para o tipo " + b.getClass(), e);
		}
	}
}
