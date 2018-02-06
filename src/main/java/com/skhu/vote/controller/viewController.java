package com.skhu.vote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ds on 2018-02-06.
 */
@Controller
public class viewController {
    @GetMapping("view")
    public String view() {
        return "index";
    }
}
