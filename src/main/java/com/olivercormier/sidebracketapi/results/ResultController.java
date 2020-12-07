package com.olivercormier.sidebracketapi.results;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ResultController {

    private ResultDao results;

    public ResultController() {
        this.results = new ResultDao();
    }

    @GetMapping("/results")
    Collection<Result> all() {
        return results.getAll();
    }
}
