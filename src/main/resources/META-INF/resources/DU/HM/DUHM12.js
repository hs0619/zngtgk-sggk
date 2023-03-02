$(function () {
	var iframe1=document.getElementById("wdglDiv");
	iframe1.src= "http://"+window.location.hostname + ":8080/elfinder-2.x-servlet-master/ShowFile.jsp";
	$("#wdglDiv").css("height", $('#page-container').height() - 55);
});