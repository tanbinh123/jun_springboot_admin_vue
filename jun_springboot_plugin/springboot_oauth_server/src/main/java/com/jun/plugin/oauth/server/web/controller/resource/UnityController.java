package com.jun.plugin.oauth.server.web.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.plugin.oauth.server.service.UserService;
import com.jun.plugin.oauth.server.service.dto.UserJsonDto;

/**
 * @author Wujun
 */
@Controller
@RequestMapping("/unity/")
public class UnityController {


    @Autowired
    private UserService userService;


    @RequestMapping("dashboard")
    public String dashboard() {
        return "unity/dashboard";
    }

    @RequestMapping("user_info")
    @ResponseBody
    public UserJsonDto userInfo() {
        return userService.loadCurrentUserJsonDto();
    }

}