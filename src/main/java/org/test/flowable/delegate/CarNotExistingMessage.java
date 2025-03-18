package org.test.flowable.delegate;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CarNotExistingMessage implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        log.info("Car doesn't exist");
    }
}
