	window.onload = function(){
		goPage(1);
	}
        function goPage(pno){  
        	//var table = document.getElementById("table");//获取table
        	var table = document.getElementsByClassName("table");//获取table
        	for(var i=0;i<table.length;i++){
        		var tableAllRow = table[i].rows.length;//获取table所有行数
        	}
            var allPage = 0;                    //定义初始总页数为0
            var pageRow = 8;                    //定义每一页的行数为8
            if(tableAllRow/pageRow > parseInt(tableAllRow/pageRow)){
                allPage = parseInt(tableAllRow/pageRow)+1;
            }else{
                allPage = parseInt(tableAllRow/pageRow);
            }
            var presentPage = pno;                //设置当前显示的页数为1
            var startRow = (presentPage - 1) * pageRow + 2;  //设置开始显示的行
            var endRow = presentPage * pageRow + 1;  //设置结束显示的行
            endRow = (endRow > tableAllRow) ?  tableAllRow : endRow; //最后行数与总行数判断
            //遍历显示与隐藏
            for(var i=2;i<=tableAllRow;i++){
            	for(var j=0;j<table.length;j++){
	                var iRow = table[j].rows[i-1];
	                if(i>=startRow && i<=endRow){
	                    iRow.style.display = "table-row";
	                }else{
	                    iRow.style.display = "none";
	                }
	                var aRow = table[j].rows[0];
	                aRow.style.display = "table-row";
            	}
            }
            aPage(tableAllRow,allPage,presentPage);
        }
        
        
        //创建a标签翻页与显示所有记录与当前页数
        function aPage(tableAllRow,allPage,presentPage){
            var record = document.getElementById("record");  
            var paging = document.getElementById("paging");
            var tempStr = "";
            record.innerHTML = "<font class=\"recoding\">" +
            						"一共"+(tableAllRow-1)+"条记录 分"+allPage+"页 当前第"+presentPage+"页" +
            					"</font>";
            if(presentPage>1){            	
                tempStr += "<a href=\"#\" onClick=\"goPage("+(1)+",8)\" class=\"pagingFont\">首页</a>"
                		+ "&nbsp";
                tempStr += "<a href=\"#\" onClick=\"goPage("+(presentPage-1)+",8)\" class=\"pagingFont\">上一页</a>"
                		+ "&nbsp";
            }else{
               tempStr += "<b class=\"pageFont\">首页</b>"+ "&nbsp";
                tempStr += "<b class=\"pageFont\">上一页</b>"+ "&nbsp";
            }
            if(presentPage<allPage){
                tempStr += "<a href=\"#\" onClick=\"goPage("+(presentPage+1)+",8)\" class=\"pagingFont\">下一页</a>"
                		+ "&nbsp";;
                tempStr += "<a href=\"#\" onClick=\"goPage("+(allPage)+",8)\" class=\"pagingFont\">尾页</a>";
            }else{
                tempStr += "<b class=\"pageFont\">下一页</b>"+ "&nbsp";
                tempStr += "<b class=\"pageFont\">尾页</b>";
            }

            paging.innerHTML = tempStr;
        }
        