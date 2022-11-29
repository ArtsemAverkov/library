package com.example.settingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SettingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettingApiApplication.class, args);
	}

}
