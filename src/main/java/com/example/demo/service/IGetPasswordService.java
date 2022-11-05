package com.example.demo.service;

import java.util.Map;

import com.example.demo.bean.MailRequest;
import com.example.demo.bean.MailResponse;

public interface IGetPasswordService {

	public MailResponse sendEmail(MailRequest request, Map<String, Object> model);
}
