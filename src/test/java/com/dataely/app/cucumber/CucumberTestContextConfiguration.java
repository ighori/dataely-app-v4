package com.dataely.app.cucumber;

import com.dataely.app.DataelyApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = DataelyApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
