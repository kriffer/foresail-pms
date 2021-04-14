package fi.foresail.pms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// handle 403 page
@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {


	@Override
	public void handle(HttpServletRequest httpServletRequest,
	                   HttpServletResponse httpServletResponse,
	                   AccessDeniedException e) throws IOException, ServletException {

		Authentication auth
				= SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			log.info("User '" + auth.getName()
					+ "' attempted to access the protected URL: "
					+ httpServletRequest.getRequestURI());
		}

		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403");

	}
}