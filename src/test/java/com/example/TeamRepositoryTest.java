package com.example;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Team;
import com.example.service.TeamService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamRepositoryTest {
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	private static NamedParameterJdbcTemplate template;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String sql = "INSERT INTO teams (league_name, team_name, headquarters, inauguration, history) VALUES " + 
				"('test_league_name','test_team_name','test_headquarters','1951年1月12日','test_history";

		template.update(sql, new MapSqlParameterSource());
		System.out.println("complete　insert test data");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		String sql = "delete from teams where id = 7";
		template.update(sql, new MapSqlParameterSource());
	}
	
	@Test
	public void showListTest() {
		List<Team> teamList = new ArrayList<>();
		teamList = teamService.showList();
		assertThat("missmatch teamList size", teamList.size(), is(7));
		assertThat("missmatch sorted result inauguration", teamList.get(0).getInauguration(), is("1934年12月26日"));
		assertThat("missmatch sorted result inauguration", teamList.size(), is("1951年1月12日"));
	}
	
	@Test
	public void showDetailTest() {
		Integer maxId = template.queryForObject("select max(id) from teams", new MapSqlParameterSource(), Integer.class);
		Team team = teamService.showDetail(maxId);
		assertThat("missmatch league_name", team.getLeagueName(), is("test_league_name"));
		assertThat("missmatch test_team_name", team.getTeamName(), is("test_team_name"));
		assertThat("missmatch headquarters", team.getHeadquarters(), is("test_headquarters"));
		assertThat("missmatch inauguration", team.getInauguration(), is("1951年1月12日"));
		assertThat("missmatch history", team.getHistory(), is("test_history"));
		
		System.out.println();
	}

}
