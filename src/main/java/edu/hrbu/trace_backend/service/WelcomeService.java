package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.Welcome;
import org.springframework.transaction.annotation.Transactional;

public interface WelcomeService {

    Result login(Welcome welcome);

    Result pictureCaptcha();

}
