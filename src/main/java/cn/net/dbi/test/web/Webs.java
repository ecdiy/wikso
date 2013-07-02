package cn.net.dbi.test.web;

import org.springframework.stereotype.Component;

import cn.net.dbi.CommonFilter;

@Component
public class Webs {
	public long getLong(String name, long defaultLong) {
		String val = CommonFilter.getRequest().getParameter(name);
		if (val == null)
			return defaultLong;
		val = val.trim();
		if (isNum(val)) {
			return Long.parseLong(val);
		}
		return defaultLong;
	}

	public int getInt(String name, int defaultLong) {
		String val = CommonFilter.getRequest().getParameter(name);
		if (val == null)
			return defaultLong;
		val = val.trim();
		if (isNum(val)) {
			return Integer.parseInt(val);
		}
		return defaultLong;
	}

	public boolean isNum(final String str) {
		if (str == null || str.toString().isEmpty()) {
			return false;
		}
		char[] cs = str.toString().toCharArray();
		for (char c : cs) {
			if (c < '0' || c > '9')
				return false;
		}
		return true;
	}
}
