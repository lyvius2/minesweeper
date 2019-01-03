package com.walters.minesweeper;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static java.util.stream.Collectors.toList;
import java.util.*;
import java.util.stream.IntStream;

@Controller
@EnableAutoConfiguration
public class MinesweeperController {

	/**
	 * 기본 화면
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) {

		// 사각형(100개)에 대한 숫자를 구하여 ArrayList에 매핑
		List<List<Integer>> mapList = new ArrayList<>();
		List<Integer> numList = IntStream.range(0, 100).boxed().collect(toList());
		Collections.shuffle(numList);
		for (int i = 0; i < 10; i++) {
			mapList.add(numList.subList(i * 10, ((i + 1) * 10)));
		}
		model.addAttribute("tiles", mapList);
		return "main";
	}

	/**
	 * 폭탄에 해당되는 번호 배열을 프런트엔드에 웹서비스로 응답해주기 위한 메서드
	 * @return
	 */
	@RequestMapping(value = "/getBombCount", method = RequestMethod.GET)
	public ResponseEntity getBombCount() {
		// 폭탄(10개) 위치 번호를 랜덤으로 생성하여 HashSet에 저장
		Random random = new Random();
		Set<Integer> bombSet = new HashSet<>();
		while (bombSet.size() < 10) {
			bombSet.add(random.nextInt(100));
		}
		return new ResponseEntity(bombSet, HttpStatus.OK);
	}
}
