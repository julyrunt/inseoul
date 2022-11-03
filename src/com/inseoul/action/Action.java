package com.inseoul.action;

import javax.servlet.http.*;
import com.inseoul.vo.ActionForward;

public interface Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
