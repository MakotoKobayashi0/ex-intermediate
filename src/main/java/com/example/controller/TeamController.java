package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Team;
import com.example.service.TeamService;

/**
 * チーム情報を操作するコントローラー
 * 
 * @author Makoto
 *
 */

@Controller
@RequestMapping("/intermediate")
public class TeamController {

	@Autowired
	TeamService teamService;

	/**
	 * チーム一覧を表示する.
	 * 
	 * @param model チームの詳細情報を格納する
	 * @return チームリストへフォワード
	 */
	@RequestMapping("/show-list")
	public String showList(Model model) {
		model.addAttribute("teamList", teamService.showList());
		return "display-team-detail/team-list";
	}

	/**
	 * チームの詳細情報を表示する.
	 * 
	 * @param id チームid
	 * @param model チーム詳細情報を格納する
	 * @return チーム詳細にフォワード
	 */
	@RequestMapping("/show-detail")
	public String showDetail(String id, Model model) {
		Team team = teamService.showDetail(Integer.parseInt(id));
		// team.setHistory(team.getHistory().replaceAll(Pattern.quote("\r"), "<br/>"));
		model.addAttribute("team", team);
		System.out.println(team.getHistory());
		return "display-team-detail/team-detail";
	}
}
