package com.dev.controller.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;

import net.sf.json.JSONArray;

public class ChartData implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "인사");
		map.put("cnt", "5");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("name", "총무");
		map.put("cnt", "10");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("name", "기획");
		map.put("cnt", "20");
		list.add(map);
		
		String str = JSONArray.fromObject(list).toString();
		response.getWriter().print(str);
	}

}
