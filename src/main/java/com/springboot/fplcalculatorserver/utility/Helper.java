package com.springboot.fplcalculatorserver.utility;

import java.util.ArrayList;
import java.util.Collection;

public class Helper {
	public static <E> Collection<E> makeList(Iterable<E> iter) {
		Collection<E> list = new ArrayList<E>();
		for (E item : iter) {
			list.add(item);
		}
		return list;
	}
}
