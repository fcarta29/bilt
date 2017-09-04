// Data from http://www.espn.com/college-football/scoreboard/_/group/80/year/2017/seasontype/2/week/1
// #window.espn.scoreboardData = {... -> ...};window.espn.scoreboardSettings

function getGameDate(gameData) {
    return gameData.date;
}

function getGameClock(gameData) {
	return gameData.status.displayClock;
}

function getGameStatus(gameData) {
	return gameData.status.type.name;
}

function getGameHomeTeam(gameData) {
	for (let competitor of gameData.competitions[0].competitors) {
  	if (competitor.homeAway == "home") {
    	return competitor.team.abbreviation + " (home)";
    }
  }
	throw "Home team not found!"
}

function getGameAwayTeam(gameData) {
	for (let competitor of gameData.competitions[0].competitors) {
  	if (competitor.homeAway == "away") {
    	return competitor.team.abbreviation + " (away)";
    }
  }
	alert("Away team not found!");
}

function getGameInfo(gameIndex) {
	gameData = data.events[gameIndex];
  return getGameDate(gameData) + " " + getGameHomeTeam(gameData) + " vs " + getGameAwayTeam(gameData) + " " + getGameStatus(gameData) + " " + getGameClock(gameData);
}

function getAllGameInfo() {
	var gamesInfo = "Games:";
  for (gameIndex in data.events) {
  		gameData = data.events[gameIndex];
      console.log(gameData.competitions[0].competitors[0].id);
			if ((teamIds.indexOf(gameData.competitions[0].competitors[0].id) > -1) || (teamIds.indexOf(gameData.competitions[0].competitors[1].id) > -1)) {
		  	gamesInfo += "\n" + getGameInfo(gameIndex);
  		}
  }
  return gamesInfo;
}

var teamIds = ["254","30","158","87","213","26","59"];
alert(getAllGameInfo());
