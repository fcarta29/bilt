package com.byteknowledge.bilt.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.bilt.dao.Dao;
import com.byteknowledge.bilt.dao.TeamDao;
import com.byteknowledge.bilt.model.Team;

@RestController
@RequestMapping("/teams")
public class TeamRestController extends AbstractRestController<Team> {

	@Autowired
	private TeamDao teamDao;

	@Override
	protected Dao<Team> getDao() {
		return teamDao;
	}

	@Override
	protected void merge(final Team persistedTeam, final Team team) {
    	persistedTeam.setName(team.getName());
    	persistedTeam.setAbbreviation(team.getAbbreviation());
	}

}
