package com.care.am.service.craw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.dto.typeDTO;
import com.care.am.mapper.CrawMapper;

@Service
public class CrawServiceImpl implements CrawService{

	@Autowired
	CrawMapper mapper;
	
	@Override
	public void dogCraw(String url) {
		
		// Document�� �������� ��� �ҽ��� ��
		Document doc = null;
		
		List<typeDTO> list = new ArrayList<typeDTO>();
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// <ul class="_panel"> �±� ���� ��� ���� ������
		Elements element = doc.select("ul._panel");
		
		// element�� �����ִ� ���� �� <h3 class="_text">�� ������ ��� ������
		Iterator<Element> el = element.select("a._text").iterator();
		
		while(el.hasNext()) {
			typeDTO type = new typeDTO();
			String pType = el.next().text();
			
			type.settNum(1);
			type.setpType(pType);
			list.add(type);
		}
		mapper.insertType(list);
	}

	@Override
	public void catCraw(String url) {
				Document doc = null;
				List<typeDTO> list = new ArrayList<typeDTO>();
				
				try {
					doc = Jsoup.connect(url).get();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Elements element = doc.select("ul._panel");
				
				Iterator<Element> el = element.select("a._text").iterator();
				
				while(el.hasNext()) {
					typeDTO type = new typeDTO();
					String pType = el.next().text();
					
					type.settNum(2);
					type.setpType(pType);
					list.add(type);
				}
				mapper.insertType(list);
	}
}
