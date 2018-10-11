package com.hbase.controller;

import java.io.IOException;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hbase.app.HbaseRestClient;

@Controller
public class RestControllerHbase {
	
	@RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
	@ResponseBody
	public String retrieveCoursesForStudent(@PathVariable String accountId) throws IOException {
		HbaseRestClient restExample = new HbaseRestClient();
		String transactionId = restExample.restClient(accountId);
		return transactionId;
	}

}
