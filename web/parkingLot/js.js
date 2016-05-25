var staticUserName = null;

function changeClass(selector,oldClass,newClass){
	var sel = $(selector);
	var _className = sel.attr("class").replace(oldClass,newClass);
	sel.attr("class",_className);
}


function wrapInfo(srcTarget) {
	var wrapInfo = {
		height: [],
		width: []
	};
	wrapInfo.height = $(srcTarget).height();
	wrapInfo.width = $(srcTarget).width();
	return wrapInfo;
}

function setWrapPosition(srcTarget, leftPer, topPer, widthPer, heightPer) {
	//参数为百分比
	var wrapInfomation = wrapInfo(srcTarget);
	var position = {
		left: [],
		top: [],
		width: [],
		height: []
	};
	position.left = wrapInfomation.width * leftPer;
	position.top = wrapInfomation.height * topPer;
	position.width = wrapInfomation.width * widthPer;
	position.height = wrapInfomation.height * heightPer;
	return position;
}

function position(srcTarget, target, leftPer, topPer, widthPer, heightPer) {
	var position = setWrapPosition(srcTarget, leftPer, topPer, widthPer, heightPer);
	$(target).css({
		left: position.left,
		top: position.top,
		width: position.width,
		height: position.height
	})
}

function positionRealTime(srcTarget, target, leftPer, topPer, widthPer, heightPer) {
	function interval() {
		position(srcTarget, target, leftPer, topPer, widthPer, heightPer);
	}
	var number = setInterval(interval, 10);
	return number;
}



function makeSelect(target, num) {
	var elem = $(target);
	for (var i = 1; i <= num; i++) {
		(function(arg) {
			var _s = "<option value=\"" + arg + "\">" + arg + "</option>"
			elem.append(_s);
		})(i);
	}
}


function makeMinSelect(target, num) {
	var elem = $(target);
	for (var i = 10; i <= num; i = i + 10) {
		(function(arg) {
			var _s = "<option value=\"" + arg + "\">" + arg + "</option>"
			elem.append(_s);
		})(i);
	}
}


//ajaxData可以为空
function myAjax(successFunction, ajaxData, ajaxUrl, isInterval, roundTime) {
	if (isInterval) {
		function ajaxInterval() {
			$.ajax({
				type: "GET",
				url: ajaxUrl,
				data: ajaxData,
				dataType: "json",
				success: function(data) {
					successFunction(data);
				},
				error: function(jqXHR) {
					clearInterval(intervalCount);
					alert('服务器出问题了！');
				}
			});
		}
		var intervalCount = window.setInterval(ajaxInterval, roundTime);
	} else {
		$.ajax({
			type: "GET",
			url: ajaxUrl,
			data: ajaxData,
			dataType: "json",
			success: function(data) {
				successFunction(data);
			},
			error: function(jqXHR) {
				clearInterval(intervalCount);
				alert('服务器出问题了！');
			}
		});
	}
}


// function record() {
// 	if ($('#wrap-result').css("display") === "block") {
// 		$('#ol').html('');
// 		$('#wrap-result').css("display", 'none');
// 	} else {
// 		if (staticUserName === null) {
// 			alert('请登录！')
// 		} else {
// 			myAjax(checkAndShow, '', 'http://192.168.31.203:8080/ParkingSystem/webapi/parkingrecord/query', false, 0);
// 		}
// 	}
// }


function record(){
	if (staticUserName !== null) {
		myAjax(checkAndShow, '', 'http://192.168.31.203:8080/ParkingSystem/webapi/parkingrecord/query', false, 0);
		displayRecord();
	} else {
		alert("请登录！");
	}
}


function checkAndShow(data) {
	for (var i = 0; i < data.length; i++) {
		$("ol").append("<li>" + data[i].stayTime + "</li>");
	}
}

function getUser(data) {
	if (data.hasOwnProperty('status')) {
		alert("请登录")
	} else {
		staticUserName = data.name;
		var userInfo = 'hello,' + data.name + '！';
		$('#userinfo').html(userInfo);
		$('#userinfo').css("display","block");
		$('#button3').css("display","block");
		$('#button1').css("display","none");
		$('#button2').css("display","none");
	}
}


function rotatecoffee() {
	var coffee = $('#coffee').attr('class');
	var shadow = $('#shadow').attr('class');
	if(/coffee_right/.test(coffee)){
		coffee = coffee.replace(/coffee_right/,'coffee_left');
		shadow = shadow.replace(/shadow_right/,'shadow_left');
	}else{
		coffee = coffee.replace(/coffee_left/,'coffee_right');
		shadow = shadow.replace(/shadow_left/,'shadow_right');
	}
	$('#coffee').attr('class',coffee);
	$('#shadow').attr('class',shadow);
}

function addPaper() {
	var _html = "";
	for (var i = 0; i < 6; i++) {
		_html = _html +　"<div class=\"singleport port_center port_busy\" id=\"NO_"+i+"_busy\"><img src=\"paper/"+i+"_busy.jpg\"></div><div class=\"singleport port_center port_none\" id=\"NO_"+i+"_none\"><img src=\"paper/"+i+"_none.jpg\"></div>"
	}
	$("#carport").html(_html);
}

function setPaperPosition(){
	for (var i = 0; i < 3; i++) {
		(function(arg){	
			$("#NO_"+arg+"_busy").css("left",""+(-28-(2-arg)*28)+"%");
			$("#NO_"+arg+"_none").css("left",""+(-28-(2-arg)*28)+"%");
		})(i);
	}
	for (var j = 3; j < 6; j++) {
		(function(arg){	
			$("#NO_"+arg+"_busy").css("left",""+(28+(arg-3)*28)+"%");
			$("#NO_"+arg+"_none").css("left",""+(28+(arg-3)*28)+"%");
		})(j);
	}
}

function displayRecord(){
	var display = $("#wrap-grey").css("display");
	if (display==="none") {
		rotatecoffee();
			$("#wrap-grey").css("display","block");
			var a = ""; 
			a = $("#wrap-result").attr("class").replace("wrap-1result","wrap-result");
			setTimeout(function(){
				$("#wrap-result").attr("class",a);
			}, 100);
			
	} else {
		$("ol").html("");
		var b = "";
		b = $("#wrap-result").attr("class").replace("wrap-result","wrap-1result");
		setTimeout(function(){
			$("#wrap-result").attr("class",b);
			setTimeout(function(){
				$("#wrap-grey").css("display","none");
			},1200);
		}, 100)
		rotatecoffee();
	}
}


function turn(data) {
	for (var i = 0; i < data.length; i++) {
		(function(arg) {
			var num = data[arg].id;
			if (last[num] !== data[num].state) {
				if (data[num].state === 0) {
					last[num] = data[num].state;
					turnPaper(num,data[num].state);
				}
				if (data[num].state === 1) {
					last[num] = data[num].state;
					turnPaper(num,data[num].state);
				}
			}
		})(i);
	}
}

function turnPaper(NO,status){
	if(status === 0){
		if (/port_busy/.test($("#NO_"+NO+"_none").attr("class"))) {
			changeClass("#NO_"+NO+"_none","busy","none");
			changeClass("#NO_"+NO+"_busy","none","busy");
		}
	}
	if(status === 1){
		if (/port_none/.test($("#NO_"+NO+"_none").attr("class"))) {
			changeClass("#NO_"+NO+"_none","none","busy");
			changeClass("#NO_"+NO+"_busy","busy","none");
		}
	}
}
