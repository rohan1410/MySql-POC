package com.demo.poc.controller;

import com.demo.poc.entity.Create;
import com.demo.poc.entity.Insert;
import com.demo.poc.entity.View;
import com.demo.poc.service.demoservice;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DmlController {

  @Autowired
  private demoservice demoService;

  @PostMapping("/insertData")
  public String insertData(@RequestBody Insert data){
    try {
      demoService.insertData(data);
    }
    catch (Exception e) {
      return e.getMessage();
    }
    return "Data Inserted Successfuly";
  }

  @GetMapping("/viewData")
  public String viewData(@RequestHeader Map<String,String> tbname) throws Exception {
      return demoService.viewData(tbname.get("tbname"));
  }

  @GetMapping("/listAllTables")
  public String listTables(@RequestHeader Map<String,String> dbname) throws Exception {
    return demoService.listTables(dbname.get("dbname"));
  }

  @GetMapping("/viewSchema/" + "{tbname}")
  public String viewSchema(@PathVariable("tbname") String tbname) throws Exception {
    return demoService.viewSchema(tbname);
  }
}
