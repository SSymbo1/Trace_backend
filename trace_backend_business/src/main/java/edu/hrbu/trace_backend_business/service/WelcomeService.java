package edu.hrbu.trace_backend_business.service;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.RePassword;
import edu.hrbu.trace_backend_business.entity.dto.Welcome;

import javax.servlet.http.HttpServletRequest;

public interface WelcomeService {

    Result login(Welcome welcome);

    Result logout(HttpServletRequest request);

    Result rePassword(RePassword rePassword);

    Result pictureCaptcha();

}
