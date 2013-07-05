package cn.net.dbi.test.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.net.dbi.test.model.Scheme;
import cn.net.dbi.test.repository.SchemeRepository;
import cn.net.dbi.test.service.DBService;

@Controller
public class UploadController {
	@Autowired
	DBService dbService;
	@Autowired
	SchemeRepository schemeRepository;

	@RequestMapping(value = "/uploadScheme.jspa", method = RequestMethod.POST)
	public String addUser(@RequestParam("file") MultipartFile file,
			@RequestParam("name") String schemeName, HttpServletRequest request)
			throws IOException {
		Scheme scheme = new Scheme();
		scheme.setName(schemeName);
		scheme = schemeRepository.save(scheme);
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/upload");
		File upfile = new File(realPath, System.nanoTime() + ".csv");
		if (!upfile.getParentFile().exists())
			upfile.getParentFile().mkdirs();
		FileUtils.copyInputStreamToFile(file.getInputStream(), upfile);
		dbService.loadFromFile(scheme.getId(), upfile);
		return "redirect:scheme.jspa?id=" + scheme.getId();
	}
}
