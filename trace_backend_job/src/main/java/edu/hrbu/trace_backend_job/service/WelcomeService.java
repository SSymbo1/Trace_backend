package edu.hrbu.trace_backend_job.service;

import edu.hrbu.trace_backend_job.entity.Result;
import edu.hrbu.trace_backend_job.entity.dto.RePassword;
import edu.hrbu.trace_backend_job.entity.dto.Welcome;

public interface WelcomeService {

    Result login(Welcome welcome);

    Result rePassword(RePassword rePassword);

    Result pictureCaptcha();

}
