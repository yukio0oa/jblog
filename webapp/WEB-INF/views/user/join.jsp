<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(function(){
	$( "#join-form" ).submit( function(){
		/* 회원 가입 폼 유효성 검증(validation) */

			// 1.아이디
			// val 안에 값이 있으면 읽음 비었으면 받아옴
			var userId = $("#userId").val();
			if (userId == "") {
				alert("아이디가 비어 있습니다.");
				return false;
			}

			
			// 1.이름
			// val 안에 값이 있으면 읽음 비었으면 받아옴
			var name = $("#name").val();
			if (name == "") {
				alert("이름이 비어 있습니다.");
				return false;
			}

			// 3.비밀번호
			var password = $("#password").val();
			if (password == "") {
				alert("password기 비어 있습니다.");
				$("#password").focus();
				return false;
			}

			var isVisible = $("#imgUserIdChecked").is(":visible");
			if (isVisible == false) {
				return false;
			}

			// 4.약관동의
			var isChecked = $("#agree-prov").is(":checked");
			if (isChecked == false) {
				alert("약관 동의를 해주세요");
				return false;
			}
 
		return true;
	});
	$( "#btn-checkid" ).click( function(){
		var userId = $( "#userId" ).val();
		if( userId == "" ) {
			$( "#userId" ).focus();
			return;
		}
		
		console.log("!!!");
			
		/* ajax 통신 */
		$.ajax( {
		    url : "/jblog/user/checkemail?userId=" + userId,
		    type: "get",
		    dataType: "json",
		    data: "",
		//  contentType: "application/json",
		    success: function( response ){
		       if( response.result == "fail" ) {
		    	   console.log( "error:" + response.message );
		    	   return;
		       }
		    
		       //통신 성공(response.result == "success" )
		       if( response.data == "exist" ) {
		    	   $( "#dialogMessage" ).dialog();
		    	   
		    	   $("#userId").
		    	   val("").
		    	   focus();
		    	   return;
		       }
		       
		       //존재하지 않는 경우(사용가능)
		       $( "#imgUserIdChecked" ).show();
		       $( "input[type='button']" ).hide();
		    },
		    error: function( XHR, status, error ){
		       console.error( status + " : " + error );
		    }

		  });
	});
	
	$( "#userId" ).change( function(){
		$("#imgUserIdChecked").hide();
		$("input[type='button']").show();
	});
});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<form:form 
			modelAttribute="userVo"
		    id="join-form"
		    class="join-form" 
		    method="post" 
		    action="${pageContext.request.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('name') }">
					<p style="text-align:left; padding:5px 0; color:red">
						<strong>
							<spring:message
								code="${errors.getFieldError( 'name' ).codes[0] }"
								text="${errors.getFieldError( 'name' ).defaultMessage }"/>
						</strong>
					</p>
				</c:if>
			</spring:hasBindErrors>

			<label class="block-label" for="userId">아이디</label>
			<form:input path="userId" />
			<input id="btn-checkid" type="button" value="중복체크">
			<img id="imgUserIdChecked" src="/jblog/assets/images/check.png" style="display:none">
			<p style="font-weight:bold; text-align: left; padding: 5px 0; color: red">
				<form:errors path="userId" />
			</p>
				
			<label class="block-label" for="password">패스워드</label>
			<form:password path="password" />
			<p style="font-weight:bold; text-align: left; padding: 5px 0; color: red">
				<form:errors path="password" />
			</p>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
	<div id="dialogMessage" title="아이디 중복 확인" style="display:none">
		<p>사용중인 아이디 입니다.</p>
		<p>다른 아이디로 가입해주세요.</p>
	</div>
</body>
</html>
