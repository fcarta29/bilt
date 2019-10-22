<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link href="css/bootstrap-4.3.1.min.css" rel="stylesheet">
<link href="css/jquery.json-view.min.css" rel="stylesheet">

<title>BILT</title>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
		<a class="navbar-brand" href="#">BILT</a>
	</nav>

	<main role="main" class="container">
		<div class="mb-3"><h1>BILT</h1></div>

        <div class="container" id="gamePicksContainer">
            <#list gamesList as game>
            <div class="input-group mb-3 row">
                <div class="form-check form-check-inline col-sm">
                  <input class="form-check-input" type="radio" name="gamePick${game.id}" id="${game.awayTeam.id}" value="${game.awayTeam.id}">
                  <label class="form-check-label" for="gamePick${game.id}">${game.awayTeam.abbreviation} (${game.awayTeam.spread})</label>
                </div>
                <div class="form-check form-check-inline col-sm">
                  <input class="form-check-input" type="radio" name="gamePick${game.id}" id="${game.homeTeam.id}" value="${game.homeTeam.id}">
                  <label class="form-check-label" for="gamePick${game.id}">${game.homeTeam.abbreviation} (${game.homeTeam.spread})</label>
                </div>
                <div class="col-sm">${game.gameTime?datetime}</div>
            </div>
            </#list>
        </div>

        <div class="input-group mb-3 row">
            <div class="col-sm"></div>
            <div class="form-check form-check-inline col-sm">
                <button type="button" class="btn btn-primary">Submit</button>
            </div>
            <div class="col-sm"></div>
        </div>
	</main>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/popper-1.14.7.min.js"></script>
	<script src="js/bootstrap-4.3.1.min.js"></script>
	<script src="js/jquery.json-view.min.js"></script>
	<script>
    	$(document).ready(function() {
    	   var gamesPickContainer = $("#gamePicksContainer");

		});
	</script>
</body>
</html>
