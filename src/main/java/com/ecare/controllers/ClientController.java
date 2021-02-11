package com.ecare.controllers;

import com.ecare.dto.Plan;
import com.ecare.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private PlanService planService;

    @GetMapping(value = "api/plans")
    @ResponseBody
    public ResponseEntity<?> getAllPlans() {
        final List<Plan> plans = planService.getAll();
        return !plans.isEmpty() ? new ResponseEntity<>(plans, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
