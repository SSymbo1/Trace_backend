package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.vo.Welcome;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface WelcomeService {

    Result login(Welcome welcome);

    Result pictureCaptcha();

}
