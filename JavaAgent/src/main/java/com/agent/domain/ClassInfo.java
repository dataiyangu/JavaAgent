package com.agent.domain;

import java.security.ProtectionDomain;

/**
 * @description:
 * @author: leesin
 * @date: Created in 2019-12-26 10:57
 * @version:
 * @modified By:
 */
public class ClassInfo {
	public ClassLoader classLoader;
	public String className;
	public java.security.ProtectionDomain protectionDomain;

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public ProtectionDomain getProtectionDomain() {
		return protectionDomain;
	}

	public void setProtectionDomain(ProtectionDomain protectionDomain) {
		this.protectionDomain = protectionDomain;
	}
}
