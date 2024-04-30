// JavaScript Document
function Page(count){
	this.absolute = count; //每页最大记录数
	this.tableId = "listtable";
	this.tBodyId = "qkList";
 
	//this.tFootId = sTFootId;
	this.rowCount = 0;//记录数
	this.pageCount = 0;//页数
	this.pageIndex = 0;//页索引
	this.__oTable__ = null;//表格引用
	this.__oTBody__ = null;//要分页内容
	this.__oTFoot__ = null;//要分页内容
	this.__dataRows__ = 0;//记录行引用
	this.__oldTBody__ = null;
	this.__init__(); //初始化;

};
/**//*
初始化
*/
Page.prototype.__init__ = function() {
this.__oTable__ = document.getElementById(this.tableId);//获取table引用
this.__oTBody__ = this.__oTable__.tBodies[this.tBodyId];//获取tBody引用
//this.__oTFoot__= document.getElementById(this.tFootId)
oTable =document.getElementById(this.tableId);
this.__dataRows__ = this.__oTBody__.rows;
this.rowCount = this.__dataRows__.length;
try {
this.absolute = (this.absolute <= 0) || (this.absolute>this.rowCount) ? this.rowCount : this.absolute; 
this.pageCount = parseInt(this.rowCount%this.absolute == 0 
? this.rowCount/this.absolute : this.rowCount/this.absolute+1);
}catch(exception) {}

this.__updateTableRows__();

};

Page.prototype.GetBar=function(obj)
{
    var bar= document.getElementById(obj.id);
	if(this.rowCount=='')this.rowCount==0;
    bar.innerHTML= "每页"+this.absolute+"条/共"+this.rowCount+"条";
	// 第2页/共6页 首页 上一页 1 2 3 4 5 6 下一页 末页  
}

/**//*
下一页
*/
Page.prototype.nextPage = function() {
if(this.pageIndex + 1 < this.pageCount) {
this.pageIndex += 1;
this.__updateTableRows__();
}
//FY();
};
/**//*
上一页
*/
Page.prototype.prePage = function() {
if(this.pageIndex >= 1) {
this.pageIndex -= 1;
this.__updateTableRows__();
}
};
/**//*
首页
*/
Page.prototype.firstPage = function() {
if(this.pageIndex != 0) {
this.pageIndex = 0;
this.__updateTableRows__();
} 
};
/**//*
尾页
*/
Page.prototype.lastPage = function() {
if(this.pageIndex+1 != this.pageCount) {
this.pageIndex = this.pageCount - 1;
this.__updateTableRows__();
}
};
/**//*
页定位方法
*/
Page.prototype.aimPage = function(iPageIndex) {
if(iPageIndex > this.pageCount-1) {
this.pageIndex = this.pageCount - 1;
}else if(iPageIndex < 0) {
this.pageIndex = 0;
}else {
this.pageIndex = iPageIndex;
}
this.__updateTableRows__();
};

/**//*
执行分页时，更新显示表格内容
*/

Page.prototype.__updateTableRows__ = function() {
// alert("ok");
var iCurrentRowCount = this.absolute * this.pageIndex;
var iMoreRow = this.absolute+iCurrentRowCount > this.rowCount ? this.absolute+iCurrentRowCount - this.rowCount : 0;
var tempRows = this.__cloneRows__();
//alert(tempRows === this.dataRows);
//alert(this.dataRows.length);
var removedTBody = this.__oTable__.removeChild(this.__oTBody__);
var newTBody = document.createElement("tbody");
newTBody.setAttribute("id", this.tBodyId);

for(var i=iCurrentRowCount; i < this.absolute+iCurrentRowCount-iMoreRow; i++) {
    newTBody.appendChild(tempRows[i]);
}
this.__oTable__.appendChild(newTBody);

//页脚显示分页
var divFood = document.getElementById("divFood");//分页工具栏
divFood.innerHTML="";
var leftDIV = document.createElement("SPAN")
leftDIV.setAttribute("display","");
leftDIV.setAttribute("float","left");
leftDIV.setAttribute("style","margin-right:10px;");
var rightDIV = document.createElement("SPAN");
rightDIV.setAttribute("display","");
rightDIV.setAttribute("float","left");
rightDIV.setAttribute("style","margin-right:10px;");

var rightBar = document.createElement("SPAN");
rightBar.setAttribute("display","");
rightBar.setAttribute("float","left");
if(isNaN(this.pageCount)){this.pageCount=1}
rightBar.innerHTML="每页"+this.absolute+"条/共"+this.rowCount+"条"+" 第"+(this.pageIndex+1)+"页/共"+this.pageCount+"页 "; 
var btnFrist =document.createElement("a");
var btnLast =document.createElement("a");
var btnPre =document.createElement("a");
var btnNext =document.createElement("a");
btnFrist.href="javascript:page.firstPage()";  
btnFrist.innerHTML= "&nbsp;首页"; 
       
rightBar.appendChild(btnFrist);
btnPre.href="javascript:page.prePage()";  
btnPre.innerHTML= "&nbsp;上一页&nbsp;";   
      
rightBar.appendChild(btnPre);
btnNext.href="javascript:page.nextPage()";  
btnNext.innerHTML= "下一页";         
rightBar.appendChild(btnNext);
btnLast.href="javascript:page.lastPage()";  
btnLast.innerHTML= "&nbsp;末页";         
rightBar.appendChild(btnLast); 
var fdiv = document.createElement("SPAN");
fdiv.setAttribute("display","");
fdiv.setAttribute("float","left");
fdiv.setAttribute("class","pagefl");
fdiv.setAttribute("style","margin-right:10px;");
var ldiv = document.createElement("SPAN");
ldiv.setAttribute("display","");
ldiv.setAttribute("float","left");
ldiv.setAttribute("class","pagefl");
ldiv.setAttribute("style","margin-right:10px;");
var pdiv = document.createElement("SPAN");
pdiv.setAttribute("display","");
pdiv.setAttribute("float","left");
pdiv.setAttribute("class","pagefl");
pdiv.setAttribute("style","margin-right:10px;");
var ndiv = document.createElement("SPAN");
ndiv.setAttribute("display","");
ndiv.setAttribute("float","left");
ndiv.setAttribute("class","pagefl");
ndiv.setAttribute("style","margin-right:10px;");
//div
fdiv.appendChild(btnFrist);
ldiv.appendChild(btnLast);
pdiv.appendChild(btnPre);
ndiv.appendChild(btnNext);
var isOK="Y";
var cssColor="";
divFood.appendChild(rightBar);
divFood.appendChild(fdiv);
divFood.appendChild(pdiv);
//数字排序
for(var i = 0 ; i <this.pageCount;i++)
{
    var numIndex="0";
    if(this.pageIndex>=9)
    {
        //if(this.pageIndex<this.pageCount-5)
        numIndex = this.pageIndex-5;
        
        if(isOK=="Y")
        {  
            i=parseInt(numIndex)+parseInt(i);
        }
        isOK="N";            
    }
    if(i<=(4+parseInt(numIndex)))
    {                
        var a=document.createElement("a");  
        a.href="javascript:page.aimPage("+i+")";
        if(i==this.pageIndex) 
        {
            cssColor="red";
        }else{
            cssColor="";
        }   
        a.innerHTML= " "+"<font color='"+cssColor+"'>"+ parseInt(i+1)+" </font>";         
        leftDIV.appendChild(a);   
                  
    }else if(i>=(4+parseInt(numIndex)) && i<(10+parseInt(numIndex)))
    {
        var a=document.createElement("a");  
        a.href="javascript:page.aimPage("+i+")";  
        if(i==this.pageIndex) 
        {
            cssColor="red";
        }else{
            cssColor="";
        }   
        a.innerHTML= "&nbsp;"+"<font color='"+cssColor+"'>"+ parseInt(i+1)+" </font>"+" "; 
        rightDIV.appendChild(a);    
    }
}

divFood.appendChild(leftDIV);
divFood.appendChild(rightDIV);
divFood.appendChild(ndiv);
divFood.appendChild(ldiv);
 
if(this.pageIndex==0)
{
    btnPre.disabled=btnFrist.disabled="disabled";
    //alert(btnPre.disabled)
}
if(this.pageCount-1==this.pageIndex)
{
    btnLast.disabled=btnNext.disabled="disabled";
}
////页脚显示分页结束
 
/**//*
this.dataRows为this.oTBody的一个引用，
移除this.oTBody那么this.dataRows引用将销失,
code:this.dataRows = tempRows;恢复原始操作行集合.
*/
this.__dataRows__ = tempRows;
this.__oTBody__ = newTBody;
//alert(this.dataRows.length);
//alert(this.absolute+iCurrentRowCount);
//alert(this.pageCount);
//alert("tempRows:"+tempRows.length);
App.init();//初始化表格中复选框的样式
};
/**//*
克隆原始操作行集合
*/
Page.prototype.__cloneRows__ = function() {
var tempRows = [];
for(var i=0; i<this.__dataRows__.length; i++) {
/**//*
code:this.dataRows[i].cloneNode(param), 
param = 1 or true:复制以指定节点发展出去的所有节点,
param = 0 or false:只有指定的节点和它的属性被复制.
*/
tempRows[i] = this.__dataRows__[i].cloneNode(1);
}
return tempRows;
};
