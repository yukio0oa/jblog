<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<script>
var dialogDeleteForm = null;
var isEnd = false;
var page = 0;
var render = function( vo, prepend ){
	var html = " <tr id='del-" + vo.cno + "'> " +
			   " 	<td> " + vo.cno + " </td> " +
			   " 	<td> " + vo.cname + " </td> " +
			   " 	<td>10</td> " +
			   " 	<td> " + vo.description + " </td> " +
			   " 	<td> " +
			   " 		<a href='#' data-no=" + vo.cno + ">" +
			   " 			<img src=" + " ${pageContext.request.contextPath}/assets/images/delete.jpg" + " > " +
			   " 		</a> " +
			   " 	</td> " +
			   " </tr> " 
	
	if( prepend == true ) {
		$( "#list" ).prepend( html );
	} else {
		$( "#list" ).append( html );
	}
}

 var fetchList = function(){
	if( isEnd == true ) {
		return;
	}
	console.log( "!" );
	$.ajax( {
		url : "/jblog/list",
		type: "get",
	    dataType: "json",
	    data: "",
	    success: function( response ){
	    	if( response.result != "success" ) {
	    		console.log( response.message );
	    		return;
	    	} 
	    	if( response.data.length == 0 ) {
	    		isEnd = true;
	    		return;	
	    	}
	    	$( response.data ).each( function(index, vo){
	    		render( vo, false );
	    	});
	    },
	    error: function( XHR, status, error ){
	       console.error( status + " : " + error );
	   	}
  });
}
 
$(function(){
	$( "#write-form" ).submit( function(event){
		// 폼의 submit 기본 이벤트 처리를 막는다.
		event.preventDefault();
		
		/* ajax 입력 */
		$.ajax( {
			url : "/jblog/add",
			type: "post",
		    dataType: "json",
		    data: "cname=" + $("input[name='name']").val() + "&" + 
		    	  "blogId=" + $("input[name='blogId']").val() + "&" +
		          "description=" + $("input[name='desc']").val(),
		    success: function( response ){
				console.log( response );
				render( response.data, false );
				$("input[type='text']").val("");
		    },
		    error: function( XHR, status, error ){
		       console.error( status + " : " + error );
		   	}
	    });
		return false;
	});
	
	//삭제 버튼 클릭 이벤트 매핑(Live Event Mapping)
	$( document ).on( "click", "#list tr td a", function(event){
		event.preventDefault();
		var $a = $(this);
		var no = $a.attr( "data-no" );
		console.log( no );
		 $.ajax( {
			url : "/jblog/delete",
			type: "post",
		    dataType: "json",
		    data: "cno=" + no,
		    success: function( response ){
				console.log( response.data );
				$( "#del-" + response.data ).remove();
		    },
		    error: function( XHR, status, error ){
		       console.error( status + " : " + error );
		   	}
	    });
	});
});
</script>

<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/blogheader.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/include/blognavigation.jsp" />
		      	<table class="admin-cat" id="list">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>		
		      		</tr>
		      		
		      		<c:forEach items="${list }" var="vo" varStatus="status">	
					<tr id="del-${vo.cno }">
						<td>${vo.cno }</td>
						<td>${vo.cname }</td>
						<td>10</td>
						<td>${vo.description }</td>
						<td>
							<a href="#" data-no="${vo.cno }" >
								<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
							</a>
						</td>
					</tr>
					</c:forEach>			  
				</table>
				
      		<form id="write-form" action="" method="post">
      			<input type="hidden" name="blogId" value="${authUser.userId }">
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table>
		      </form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>