package ru.webdivas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hobotun.db.model.tbl.ModelTbl;

@Controller
public class SaveModel {

	@RequestMapping(value = "/uploadModel", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		return "Вы можете загружать файл с использованием того же URL.";
	}
	
	@RequestMapping(value="/uploadModel", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("model") ModelTbl model){
        if (model == null) {
        	
        } else {
            return "Не удалось загрузить файл.";
        }
        
		return "Модель учано загружена, вы модете просмотреть её статус в разделе мои модели.";
    }

}
