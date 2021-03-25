package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.BoardsService;

@Controller
@RequestMapping("/exam05") //공통경로
public class Exam05Controller {
	private static final Logger logger =
			LoggerFactory.getLogger(Exam05Controller.class);
	//스프링이 관리하고 있는 IoC컨테이너 안에서 동일한 타입의 객체가 있는지 찾아보고, 찾으면 그 번지를 필드에 자동으로 대입
	@Autowired
	private BoardsService boardsService; //필드로 선언해놓음

	@RequestMapping("/content")
	public String content(Model model) {
		return "exam05/content";
	}

	@GetMapping("/list")
	public String getBoardList(String pageNo,Model model, HttpSession session) {
		int intPageNo = 1;
		//세션에서 pager를 찾고, 있으면 pageNo설정
		if(pageNo == null) {	//클라이언트에서 pageNo가 넘어오지 않았을 경우
			Pager pager = (Pager)session.getAttribute("pager");
			if(pager!=null) {
				intPageNo = pager.getPageNo();
			}
		}else { //클라이언트에서 pageNo가 넘어왔을 때
			intPageNo = Integer.parseInt(pageNo);
		}
		
		int totalRows = boardsService.getTotalRows();
		Pager pager = new Pager(10,5,totalRows,intPageNo);
		session.setAttribute("pager", pager);
		List<Board> list = boardsService.getBoardList(pager);
		model.addAttribute("list", list);
		model.addAttribute("pager",pager);
		return "exam05/boardlist";
	}

	@GetMapping("/read")
	public String read(int bno, Model model) {
		boardsService.addHitcount(bno);
		Board board = boardsService.getBoard(bno);
		model.addAttribute("board", board);
		return "exam05/read";
	}

	@GetMapping("/downloadAttach")
	//HttpServletResponse에서 응답을 만들어서 보내니까 jsp를 실행할 필요가 없음.
	//jsp에서 만들어내는 html이 아니라 여기서 직접 이미지를 보내면 jsp로 보낼 필요가 없다!
	public void downloadAttach(int bno, HttpServletResponse response) {
		try {
			Board board = boardsService.getBoard(bno); //bno에 해당하는 board얻기
			//응답 HTTP 헤더에 저장될 바디의 타입
			response.setContentType(board.getBattachtype());
			//응답 HTTP 헤더에 다운로드할 수 있도록 파일 이름을 지정
			String originalName = board.getBattachoname();
			//한글 파일일 경우, 깨짐 현상을 방지
			originalName = new String(originalName.getBytes("UTF-8"), "ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + originalName + "\";");
			//응답 HTTP 바디로 이미지 데이터를 출력
			InputStream is = new FileInputStream("c:/backEnd/uploadfiles/" + board.getBattachsname());
			OutputStream os = response.getOutputStream(); //출력스트림 필요함
			FileCopyUtils.copy(is, os);
			os.flush();
			is.close();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/updateForm")
	public String updateForm(int bno, Model model) {
		Board board = boardsService.getBoard(bno);
		model.addAttribute("board", board);
		return "exam05/updateForm";
	}

	@PostMapping(value="/update", produces="application/json;charset=UTF-8")
	@ResponseBody //Body속에 들어가는 내용이 json이라는 것을 content type으로 명시해야 함. 위에 produces로 작성
	public String update(Board board) {
		boardsService.updateBoard(board);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		return jsonObject.toString();
		//ajax는 redirect 못 씀. 무조건 응답을 줘야 함!
	}

	@GetMapping(value="/delete", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String delete(int bno) {
		boardsService.deleteBoard(bno);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		return jsonObject.toString();
	}

	@GetMapping("/createForm")
	public String createFormWithAttach() {
		return "exam05/createForm";
	}

	@PostMapping(value="/create", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String create(Board board) {
		MultipartFile battach = board.getBattach();
		if(battach!=null&&!battach.isEmpty()) {
			board.setBattachoname(battach.getOriginalFilename());
			board.setBattachtype(battach.getContentType());
			//파일 저장 시 이름 중복 제거
			String saveName = new Date().getTime() + "-" + battach.getOriginalFilename();
			board.setBattachsname(saveName);

			File file = new File("c:/backEnd/uploadfiles/" + saveName);
			try {
				battach.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		logger.info(board.getBtitle());
		logger.info(board.getBcontent());
		logger.info(board.getBattachoname());
		logger.info(board.getBattachsname());
		logger.info(board.getBattachtype());

		board.setBwriter("user1");
		boardsService.saveBoard(board);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		return jsonObject.toString();
	}


}
