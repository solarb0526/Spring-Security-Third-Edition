package com.packtpub.springsecurity.web.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CORS Filter
 * <p>
 * This filter is an implementation of W3C's CORS
 * (Cross-Origin Resource Sharing) specification,
 * which is a mechanism that enables cross-origin requests.
 * <p>
 * Special notes for <b>Access-Control-Allow-Credentials</b>:
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Allow-Credentials
 */
public class CORSFilter extends GenericFilterBean implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "HEAD,GET,PUT,POST,DELETE,PATCH");

        httpResponse.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, X-Auth-Token, X-Csrf-Token, WWW-Authenticate, Authorization");
        httpResponse.setHeader("Access-Control-Expose-Headers", "custom-token1, custom-token2");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "false");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");

        StringBuilder sb = new StringBuilder();
        sb.append("\nCORS HEADERS:\n");
        sb.append("---------------\n");
        httpResponse.getHeaderNames()
                .forEach(name -> {
                            sb.append(name).append(": ").append(httpResponse.getHeader(name)).append("\n");
                        }
                );
        logger.info("********** CORS Configuration Completed **********");
        logger.info(sb.toString());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

} // The End...
