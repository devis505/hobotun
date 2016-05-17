package ru.webdivas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SaveModel {

	@RequestMapping(value = "/uploadModel", method = RequestMethod.POST)
	public @ResponseBody String provideUploadInfo() {
		return "Вы можете загружать файл с использованием того же URL.";
	}


}
