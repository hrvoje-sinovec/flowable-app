package org.test.flowable.controller;

import lombok.AllArgsConstructor;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.*;
import org.test.flowable.service.CustomProcessService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class ProcessController {

    private final CustomProcessService customProcessService;

    @RequestMapping(value="/tasks", method= RequestMethod.GET)
    public List<Map<String, Object>> getTasks(@RequestParam String assignee) {
        List<Task> tasks = customProcessService.getTasks(assignee);

        return tasks.stream()
                .map(task -> {
                    Map<String, Object> details = new HashMap<>();
                    details.put("id", task.getId());
                    details.put("processDefinitionId", task.getProcessDefinitionId());
                    details.put("name", task.getName());
                    details.put("assignee", task.getAssignee());
                    details.put("delegationState", task.getDelegationState());
                    return details;
                })
                .collect(Collectors.toList());
    }

    @RequestMapping(value="/processes", method= RequestMethod.GET)
    public List<Map<String, Object>> getProcesses(@RequestParam String process) {
        List<ProcessInstance> instances = customProcessService.getProcessInstances(process);

        return instances.stream()
                .map(instance -> {
                    Map<String, Object> details = new HashMap<>();
                    details.put("id", instance.getId());
                    details.put("processDefinitionId", instance.getProcessDefinitionId());
                    details.put("isEnded", instance.isEnded());
                    return details;
                })
                .collect(Collectors.toList());
    }
}
