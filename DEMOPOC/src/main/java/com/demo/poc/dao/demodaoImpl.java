package com.demo.poc.dao;

import com.demo.poc.entity.Create;
import com.demo.poc.entity.Insert;
import com.demo.poc.entity.View;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.support.xml.SqlXmlFeatureNotImplementedException;
import org.springframework.stereotype.Repository;

@Repository
public class demodaoImpl implements demodao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void createTable(Create tbdetails) throws SQLException {
    String sql = "CREATE TABLE IF NOT EXISTS " + tbdetails.getTbname() + " ( ";
    for (Map.Entry<String,String> m : tbdetails.getColumns().entrySet()){
      sql += m.getKey() + " " + m.getValue() + ",";
    }
    sql = sql.substring(0,sql.length() - 1) + " );";
    System.out.println(sql);
    //jdbcTemplate.execute("USE restapi");
    //jdbcTemplate.execute("SELECT Sagar FROM harsh");
    jdbcTemplate.execute(sql);
    System.out.println("Table Created");
  }

  @Override
  public void insertData(Insert data) throws SQLException {
    String sql = "INSERT INTO " + data.getTbname() + "( ";
    for (Map.Entry<String,String> m : data.getData().entrySet()){
      sql += m.getKey() + ",";
    }
    sql = sql.substring(0,sql.length() - 1) + ") VALUES (";
    for (Map.Entry<String,String> m : data.getData().entrySet()){
      sql += "'" + m.getValue() + "',";
    }
    sql = sql.substring(0,sql.length() - 1) + ");";
    System.out.println(sql);
    jdbcTemplate.execute(sql);
  }

  public String viewData(String tbname) throws Exception {
    String sql = " SELECT * FROM " + tbname;
    List<Map<String,Object>> result =  jdbcTemplate.queryForList(sql);

    ObjectMapper objectMapper = new ObjectMapper();

    return objectMapper.writeValueAsString(result);
  }

  public void deleteTable(String tbname) throws SQLException {
    String sql = " DROP TABLE " + tbname;
    jdbcTemplate.execute(sql);
  }

  public String listTables(String dbname) throws Exception {
    String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema ='"+dbname+"';";
    List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);

    ObjectMapper objectMapper = new ObjectMapper();

    return objectMapper.writeValueAsString(result);
  }

  public String viewSchema(String tbname) throws Exception {
    String sql = "DESCRIBE " + tbname;
    List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);

    ObjectMapper objectMapper = new ObjectMapper();

    return objectMapper.writeValueAsString(result);
  }
}
