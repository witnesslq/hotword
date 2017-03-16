package com.sohu.mrd.hotword.controller;

import com.sohu.mrd.hotword.service.GetHotWordRedisDate;
import com.sohu.mrd.hotword.util.WordFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Map;


/**
 * @author ideawu
 * 
 */
@Controller
@RequestMapping(value = "/search")
public class HotWordController {
	/**
	 * Created by yonghongli on 2016/8/4.
	 */

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH");

	@RequestMapping(value = "/hotword.do")
    @ResponseBody
	public String handleRequest() throws Exception {
		String date = sdf.format(System.currentTimeMillis());

		Map<String,Integer> h= GetHotWordRedisDate.getGetRedisDate(date, 20);
		if(h.size()!=0){
            h= WordFilter.filterSex(h);
            return StringUtils.join(h.keySet(),",");
        }else {
            return "";
        }

		//return StringUtils.join(h,",");
	}



}
