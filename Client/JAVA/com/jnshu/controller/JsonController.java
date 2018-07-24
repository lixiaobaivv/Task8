/*
package com.jnshu.controller;

import com.jnshu.entity.Student;
import com.jnshu.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JsonController {

    private Logger logger = LoggerFactory.getLogger(JsonController.class);

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/json/{id}",method = RequestMethod.GET)
    public String JsonID(@PathVariable("id")Long id, Model model){
        Student student = studentService.getID(id);
        model.addAttribute("s",student);
        logger.info("json-getid-model"+model);
        return "JsonID";
    }
}
*/
