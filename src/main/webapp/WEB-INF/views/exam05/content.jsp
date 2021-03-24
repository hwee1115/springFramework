<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<script>
	$(function() {
		getList(1);
	});
	
	const getList = (pageNo) => {
		$.ajax({
			url: "list",
			data: {pageNo},
			method: "get"
		}).then(data => {
			$("#board").html(data);
		});
	};
	
	const read = (bno) => {
		$.ajax({
			url: "read",
			data: {bno},
			method: "get"
		}).then(data => {
			$("#board").html(data);
		});
	};
	
	const updateForm = (bno) => {
		$.ajax({
			url: "updateForm",
			data: {bno},
			method: "get"
		}).then(data => {
			$("#board").html(data);
		});
	};
	
	const update = (bno) => {
		event.preventDefault(); //기본적으로 form태그는 서버에 요청기능이 있음.
		//그 요청 기능을 끄는 것. 우리는 AJAX를 써야하니까!
		const btitle = $("#btitle").val();
		const bcontent = $("#bcontent").val();
		$.ajax({
				url: "update",
				data: {bno, btitle, bcontent},
				method: "post"
			}).then(data => {
				if(data.result == "success"){ //result는 controller에서 result라는 속성으로 값을 저장함.
					getList(1);
				}
			});		
		};
		
		const deleteBoard = (bno) =>{
			$.ajax({
				url: "delete",
				data: {bno},
				method: "get"
			}).then(data => {
				if(data.result == "success"){ //result는 controller에서 result라는 속성으로 값을 저장함.
					getList(1);
				}
			});		
		};
		
		const createForm = () =>{
			$.ajax({
				url: "createForm",
				method: "get"
			}).then(data => {
				$("#board").html(data);
				
			});	
		}
		
		const create = () =>{
			event.preventDefault();
			const btitle = $("#btitle").val();
			const bcontent = $("#bcontent").val();
			const battach = $("#battach")[0].files[0];
			const formData = new FormData();
			formData.append("btitle",btitle);
			formData.append("bcontent",bcontent);
			if(battach){
				formData.append("battach",battach);
			}
			
			$.ajax({
				url:"create",
				data:formData,
				method:"post",
				cache:false,
				processData:false,
				contentType:false
			}).then(data=>{
				if(data.result == "success"){ //result는 controller에서 result라는 속성으로 값을 저장함.
					getList(1);
				}
			})
		};
</script>

<div>
 		<div class="alert alert-primary">
 			AJAX를 이용한 게시판
 		</div>

 		<div id="board">
 		</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %> 