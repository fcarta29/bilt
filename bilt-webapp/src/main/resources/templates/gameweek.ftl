<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link href="css/bootstrap-4.3.1.min.css" rel="stylesheet">
<link href="css/jquery.json-view.min.css" rel="stylesheet">

<title>VMC Explorer</title>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
		<a class="navbar-brand" href="#">VMC Explorer</a>
	</nav>

	<main role="main" class="container">
		<div class="mb-3"><h1>VMCE</h1></div>

		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">Refresh Token:</span>
			</div>
			<input type="text" class="form-control" placeholder="refresh_token" id="refreshToken" value="">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">Org Id:</span>
			</div>
			<input type="text" class="form-control" placeholder="org_id" id="orgId" value="">
		</div>			
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">SDDC Id:</span>
			</div>
			<input type="text" class="form-control" placeholder="sddc_id" id="sddcId" value="">
		</div>			

		<div class="input-group mb-3">
		  <div class="input-group-prepend">
		    <button class="btn btn-primary" type="button" id="getOrgList">doIt!</button>
		  </div>
		  <span class="form-control" aria-label="" aria-describedby="basic-addon1">List Orgs (https://vmc.vmware.com/vmc/api/orgs)</span>
		  <div class="input-group-append">
		    <button class="btn btn-primary" type="button" id="clearOrgList">Clear</button>
		  </div>
		</div>
		<div class="mb-3 w-100" id="resultOrgList"></div>

		
		<div class="input-group mb-3">
		  <div class="input-group-prepend">
		    <button class="btn btn-primary" type="button" id="getSDDCList">doIt!</button>
		  </div>
		  <span class="form-control" aria-label="" aria-describedby="basic-addon1">List SDDCs (https://vmc.vmware.com/vmc/api/orgs/:org/sddcs)</span>
		  <div class="input-group-append">
		    <button class="btn btn-primary" type="button" id="clearSDDCList">Clear</button>
		  </div>
		</div>
		<div class="mb-3 w-100" id="resultSDDCList"></div>
		
		<div class="input-group mb-3">
		  <div class="input-group-prepend">
		    <button class="btn btn-primary" type="button" id="getSDDC">doIt!</button>
		  </div>
		  <span class="form-control" aria-label="" aria-describedby="basic-addon1">Get SDDC (https://vmc.vmware.com/vmc/api/orgs/:org/sddcs/:sddc)</span>
		  <div class="input-group-append">
		    <button class="btn btn-primary" type="button" id="clearSDDC">Clear</button>
		  </div>
		</div>
		<div class="mb-3 w-100" id="resultSDDC"></div>		
		
	</main>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/popper-1.14.7.min.js"></script>
	<script src="js/bootstrap-4.3.1.min.js"></script>
	<script src="js/jquery.json-view.min.js"></script>
	<script>
    	$(document).ready(function() {
    		var refreshTokenDiv = $("#refreshToken");
    		var orgIdDiv = $("#orgId");
    		var sddcIdDiv = $("#sddcId");

    		$('#getOrgList').click(function(){
    			var resultDiv = $("#resultOrgList");
				var refreshTokenVal = refreshTokenDiv.val();
				
				resultDiv.empty().append("Fetching...");				
				$.ajax({
					url:"orgs/",
					type:"POST",
					data: JSON.stringify({'refreshToken':refreshTokenVal}),
					dataType: "json",
					contentType:"application/json; charset=utf-8",
					success: function(data) {
						resultDiv.empty();
						resultDiv.jsonView(data);
					}
				});
			});

    		$('#clearOrgList').click(function(){
    			$("#resultOrgList").empty();
    		});
    		
    		$('#getSDDCList').click(function(){
    			var resultDiv = $("#resultSDDCList");
				var refreshTokenVal = refreshTokenDiv.val();
				var orgIdVal = orgIdDiv.val();
				
				resultDiv.empty().append("Fetching...");				
				$.ajax({
					url:"sddcs/",
					type:"POST",
					data: JSON.stringify({'refreshToken':refreshTokenVal,'orgId':orgIdVal}),
					dataType: "json",
					contentType:"application/json; charset=utf-8",
					success: function(data) {
						resultDiv.empty();
						resultDiv.jsonView(data);
					}
				});
			});

    		$('#clearSDDCList').click(function(){
    			$("#resultSDDCList").empty();
    		});    		
    		
    		
    		$('#getSDDC').click(function(){
    			var resultDiv = $("#resultSDDC");
				var refreshTokenVal = refreshTokenDiv.val();
				var orgIdVal = orgIdDiv.val();
				var sddcIdVal = sddcIdDiv.val();
				
				resultDiv.empty().append("Fetching...");				
				$.ajax({
					url:"sddcs/"+sddcIdVal,
					type:"POST",
					data: JSON.stringify({'refreshToken':refreshTokenVal,'orgId':orgIdVal}),
					dataType: "json",
					contentType:"application/json; charset=utf-8",
					success: function(data) {
						resultDiv.empty();
						resultDiv.jsonView(data);
					}
				});
			});

    		$('#clearSDDC').click(function(){
    			$("#resultSDDC").empty();
    		});        		
  		
		});
	</script>
</body>
</html>
