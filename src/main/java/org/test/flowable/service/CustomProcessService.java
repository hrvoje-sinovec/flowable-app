package org.test.flowable.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class CustomProcessService {

    private RuntimeService runtimeService;

    private TaskService taskService;

    @Transactional
    public void startProcess(String processDefinitionKey, Map<String, Object> variables) {
        log.info("Starting process {} with variables {}", processDefinitionKey, variables);
        runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
    }

    @Transactional
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    @Transactional
    public List<ProcessInstance> getProcessInstances(String processDefinitionKey) {
        return runtimeService.createProcessInstanceQuery().processDefinitionKey(processDefinitionKey).list();
    }
}
