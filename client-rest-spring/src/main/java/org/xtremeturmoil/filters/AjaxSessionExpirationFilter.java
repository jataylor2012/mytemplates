package org.xtremeturmoil.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * {@link AjaxSessionExpirationFilter} is a servlet filter implementation
 * designed to assist with the incompatibility between http re-directs and ajax
 * requests. This filter checks to see if the request is ajax in origin and
 * returns a custom 901 error code to allow ajax to correctly process a null
 * session situation.
 * @author jataylor
 *
 */
public class AjaxSessionExpirationFilter implements Filter{

	private Logger logger = Logger.getLogger(getClass());
	private int customErrorCode = 901;
	
	@Override
	public void destroy() {
		//Nothing to do here.
	}

	/**
	 * If there is no logged in session and the call is AJAX, send the custom error code.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) 
			throws IOException, ServletException {
		HttpSession currentSession = ((HttpServletRequest)request).getSession(false);
		String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
		
		//Ajax call and no session present.
		if(currentSession == null && "XMLHttpRequest".equals(ajaxHeader)) {
			logger.info("Ajax call detected, send {} custom error code " + this.customErrorCode);
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendError(this.customErrorCode);
		} else { //Default action filter chain.
			filterChain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//Nothing to do here.
	}

}
