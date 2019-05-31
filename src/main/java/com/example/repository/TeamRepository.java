package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Team;

/**
 * チームテーブルを操作するリポジトリ.
 * 
 * @author Makoto
 *
 */
@Repository
public class TeamRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Team> TEAM_ROW_MAPPER = (rs, i) -> {
		Team team = new Team();
		team.setId(rs.getInt("id"));
		team.setLeagueName(rs.getString("league_name"));
		team.setTeamName(rs.getString("team_name"));
		team.setHeadquarters(rs.getString("headquarters"));
		team.setInauguration(rs.getString("inauguration"));
		team.setHistory(rs.getString("history"));
		
		return team;
	};
	
	/**
	 * 全チームリストを取得する.
	 * 
	 * @return teamList チームが格納されたArrayListを返す.
	 */
	public List<Team> findAll() {
		String sql = "select id, league_name, team_name, headquarters, headquarters, inauguration, history "
				+ "from teams order by inauguration";
		List<Team> teamList = template.query(sql, TEAM_ROW_MAPPER);

		return teamList;
	}
	
	/**
	 * idで主キー検索を行う.
	 * 
	 * @param id 指定されたid
	 * @return 指定されたidに対応するteamを返す
	 */
	public Team load(Integer id) {
		String sql = "select id, league_name, team_name, headquarters, headquarters, inauguration, history "
				+ "from teams where id=:id";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		Team team = template.queryForObject(sql, param,TEAM_ROW_MAPPER);
		
		return team;
	}
	
	
			
}
