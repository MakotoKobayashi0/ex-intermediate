package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Team;
import com.example.repository.TeamRepository;

/**
 * チームのサービスクラス.
 * 
 * @author Makoto
 *
 */
@Service
public class TeamService {

	@Autowired
	TeamRepository teamRepository;

	/**
	 * チームを全検検索する.
	 * 
	 * @return チームのリスト
	 */
	public List<Team> showList() {
		return teamRepository.findAll();
	}

	/**
	 * リームを主キー検索する.
	 * 
	 * @param id チームid
	 * @return チームの詳細情報
	 */
	public Team showDetail(Integer id) {
		return teamRepository.load(id);
	}

	@Override
	public String toString() {
		return "TeamService [teamRepository=" + teamRepository + "]";
	}

}
