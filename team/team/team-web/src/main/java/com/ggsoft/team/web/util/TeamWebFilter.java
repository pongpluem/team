package com.ggsoft.team.web.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

/**
 * Servlet Filter implementation class CcmWebFilter
 */
@WebFilter(filterName = "/TeamWebFilter", urlPatterns = "/*")
public class TeamWebFilter implements Filter {

	private static final Logger log = LogManager.getLogger(TeamWebFilter.class);

	private static final String FACES_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<partial-response><redirect url=\"%s\"></redirect></partial-response>";

	public TeamWebFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();

		if (uri.equals("/team-web") || uri.equals("/team-web/") || uri.startsWith("/team-web/login.xhtml")) {
			chain.doFilter(request, response);
		} else if (uri.startsWith("/team-web/javax.faces.resource/") || uri.startsWith("/team-web/resources/")) {
			chain.doFilter(request, response);
		} else {
			HttpSession ses = req.getSession(false);
			if (ses == null) {
				//chain.doFilter(request, response);
				if ("partial/ajax".equals(req.getHeader("Faces-Request"))) {
					res.setContentType("text/xml");
					res.setCharacterEncoding("UTF-8");
					res.getWriter().printf(FACES_REDIRECT_XML, req.getContextPath() + "/login.xhtml");
				} else {
					res.sendRedirect("/team-web/login.xhtml");
				}
			} else {

				/*
				 * @SuppressWarnings("unchecked") Map<String, Mauth> mapAuth =
				 * (Map<String, Mauth>)
				 * ses.getAttribute(Constant.SESSION_MAP_AUTH);
				 * ThreadContext.put("userid",ses.getAttribute("userid").
				 * toString()); if(mapAuth == null){
				 * res.sendRedirect("/ccm-web/login.xhtml"); }else{ String path
				 * = uri.substring(8, uri.length()); boolean hasRead = true;
				 * if(path.contains("//")){ hasRead = false; }
				 * //log.trace("filtering for url="+uri);
				 * 
				 * List<Mauth> mAuths = new ArrayList<Mauth>(mapAuth.values());
				 * 
				 * for(Mauth mAuth : mAuths){
				 * if(path.startsWith(mAuth.getMmenu().getMenuLink())){ hasRead
				 * = mAuth.getRead(); break; } }
				 * 
				 * if(!hasRead){ log.debug("no read authorize for requestURI=" +
				 * uri + ";");
				 * res.sendError(HttpServletResponse.SC_UNAUTHORIZED); }else{
				 * chain.doFilter(request, response); }
				 */

				//log.debug("no read authorize for requestURI=" + uri + ";");
				//res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				chain.doFilter(request, response);
			}

		}
	}

	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
