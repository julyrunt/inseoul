package com.inseoul.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProfileCommandInter {
	String showData(HttpServletRequest request, HttpServletResponse response) throws Exception;
	String runData(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
