package org.test.flowable.controller;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.test.flowable.service.CustomProcessService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CarController {

    @Autowired
    private CustomProcessService customProcessService;

    @Autowired
    private TaskService taskService;

    @PostMapping(value="/get-car")
    public void getCar(@RequestParam String car, @RequestParam String assignee) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("assignee", assignee);
        variables.put("car", car);

        customProcessService.startProcess("getCar", variables);
    }

    @PostMapping("return-car")
    public void returnCarForAssignee(@RequestParam String assignee, @RequestParam Boolean carExists) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();

        for (Task task : tasks) {
            Map<String, Object> variables = new HashMap<>();
            variables.put("assignee", assignee);
            variables.put("carExists", carExists);

            log.info("returnCarForAssignee completed with variables {}", variables);
            taskService.complete(task.getId(), variables);
        }
    }

}
