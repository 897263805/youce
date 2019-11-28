//设置各个a标签按钮或文本
var totalTag = {
	decide : 1,
	pageTip : document.getElementById("pageTip"),
	onePage : "<a href=\"#\" onClick=\"goHome()\" class=\"pagingFont\">首页</a>"+ "&nbsp",
	twoPage : "<a href=\"#\" onClick=\"goSelect()\" class=\"pagingFont\">选择题</a>"+ "&nbsp",
	threePage : "<a href=\"#\" onClick=\"goJudge()\" class=\"pagingFont\">判断题</a>"+ "&nbsp",
	fourPage : "<a href=\"#\" onClick=\"goGap()\" class=\"pagingFont\">填空题</a>"+ "&nbsp",
	fivePage : "<a href=\"#\" onClick=\"goShort()\" class=\"pagingFont\">简答题</a>"+ "&nbsp",
	sixPage : "<a href=\"#\" onClick=\"goSyn()\" class=\"pagingFont\">综合题</a>"
};
//开始加载页面后展示首页
window.onload = function(){
	var one = "<b class=\"pageFont\">首页</b>"+ "&nbsp";
	var skip = one + totalTag.twoPage + totalTag.threePage + 
				totalTag.fourPage + totalTag.fivePage + totalTag.sixPage ;	
	totalTag.pageTip.innerHTML = skip;
	pageDisplay(totalTag.decide);
}

//对页面class进行遍历，先使所有页面隐藏,后判断进行页面的现实
function pageDisplay(decide){
	var displayPage = document.getElementsByClassName("paperPage");
		for(var i = 0; i < displayPage.length; i++){
			displayPage[i].style.display = "none";
			if(decide === 1){
				displayPage[0].style.display = "block";
				displayPage[1].style.display = "block";
			}else if(decide === 2){
				displayPage[2].style.display = "block";
				displayPage[3].style.display = "block";
			}else if(decide === 3){
				displayPage[4].style.display = "block";
				displayPage[5].style.display = "block";
			}else if(decide === 4){
				displayPage[6].style.display = "block";
				displayPage[7].style.display = "block";
			}else if(decide === 5){
				displayPage[8].style.display = "block";
				displayPage[9].style.display = "block";
			}else if(decide === 6){
				displayPage[10].style.display = "block";
				displayPage[11].style.display = "block";
			}
		}
}

//当点击各个页面的a标签时，显示的的标签改变
function goHome(){
	var one = "<b class=\"pageFont\">首页</b>"+ "&nbsp";
	var skip = one + totalTag.twoPage + totalTag.threePage + 
				totalTag.fourPage + totalTag.fivePage + totalTag.sixPage ;	
	totalTag.pageTip.innerHTML = skip;
	totalTag.decide = 1;
	pageDisplay(totalTag.decide);
}
function goSelect(){
	var two = "<b class=\"pageFont\">选择题</b>"+ "&nbsp";
	var skip = totalTag.onePage + two + totalTag.threePage + 
				totalTag.fourPage + totalTag.fivePage + totalTag.sixPage ;	
	totalTag.pageTip.innerHTML = skip;
	totalTag.decide = 2;
	pageDisplay(totalTag.decide);
}
function goJudge(){
	var three = "<b class=\"pageFont\">判断题</b>"+ "&nbsp";
	var skip = totalTag.onePage + totalTag.twoPage + three + 
				totalTag.fourPage + totalTag.fivePage + totalTag.sixPage ;	
	totalTag.pageTip.innerHTML = skip;
	totalTag.decide = 3;
	pageDisplay(totalTag.decide);
}
function goGap(){
	var four = "<b class=\"pageFont\">填空题</b>"+ "&nbsp";
	var skip = totalTag.onePage + totalTag.twoPage + totalTag.threePage + 
				four + totalTag.fivePage + totalTag.sixPage ;	
	totalTag.pageTip.innerHTML = skip;
	totalTag.decide = 4;
	pageDisplay(totalTag.decide);
}
function goShort(){
	var five = "<b class=\"pageFont\">简单题</b>"+ "&nbsp";
	var skip = totalTag.onePage + totalTag.twoPage + totalTag.threePage + 
				totalTag.fourPage + five + totalTag.sixPage ;	
	totalTag.pageTip.innerHTML = skip;
	totalTag.decide = 5;
	pageDisplay(totalTag.decide);
}
function goSyn(){
	var six = "<b class=\"pageFont\">综合题</b>";
	var skip = totalTag.onePage + totalTag.twoPage + totalTag.threePage + 
				totalTag.fourPage + totalTag.fivePage + six ;	
	totalTag.pageTip.innerHTML = skip;
	totalTag.decide = 6;
	pageDisplay(totalTag.decide);
}






	