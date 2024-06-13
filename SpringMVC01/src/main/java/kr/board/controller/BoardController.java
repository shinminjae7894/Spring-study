package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController { // new BoardController(); ->스프링 컨네이너 안에
	
	// /boardList.do
	@Autowired
	private BoardMapper mapper;
	// HandlerMapping
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		
		List<Board> list = mapper.getLists();
		model.addAttribute("list", list);
		return "boardList"; // /WEB-INF/views/boardList.jsp -> forward
	}
	
	@GetMapping("/boardForm.do")
	public String boardForm() {
		return "boardForm"; // /WEB-INF/views/boardForm.jsp -> forward
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) {//title, content, writer => 파라메터수집(Board) Board.java의 안에 이름과 동일해야함
		mapper.boardInsert(vo); //등록
		return "redirect:/boardList.do"; // redirect
	}
	
	@GetMapping("/boardContent.do")
	public String boardContent(@RequestParam("idx") int idx, Model model){  // ?idx=6 , RequestParam : idx=6를 받아옴 int idx에 집어넣음
		Board vo = mapper.boardContent(idx);
		model.addAttribute("vo", vo); //객체 바인딩
		return "boardContent"; //boardContent.jsp
	}
}
