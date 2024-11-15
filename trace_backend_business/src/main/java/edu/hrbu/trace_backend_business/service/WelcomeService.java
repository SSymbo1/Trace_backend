package edu.hrbu.trace_backend_business.service;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.RePassword;
import edu.hrbu.trace_backend_business.entity.dto.Welcome;

public interface WelcomeService {

    Result login(Welcome welcome);

    Result rePassword(RePassword rePassword);

    Result pictureCaptcha();

}
