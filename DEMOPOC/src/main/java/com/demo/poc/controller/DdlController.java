package com.demo.poc.controller;

import com.demo.poc.entity.Create;
import com.demo.poc.service.demoservice;
import java.sql.SQLException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DdlController {

    @Autowired
    private demoservice demoService;

    @PostMapping("/create")
    public String createTable(@RequestBody Create tableDetails){
        try {
            demoService.createTable(tableDetails);
        }
        catch (Exception e) {
            return e.getMessage();
        }
        return "Table Created Successfully";
    }

    @DeleteMapping("/deleteTable")
    public String  deleteTable(@RequestHeader Map<String, String> tbname) throws SQLException {
        try {
            demoService.deleteTable(tbname.get("tbname"));
        }
        catch (Exception e) {
            return e.getMessage();
        }
        return "Table Deleted Successfully";
    }
}
