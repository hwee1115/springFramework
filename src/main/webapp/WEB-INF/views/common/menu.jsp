<%@ page contentType="text/html; charset=UTF-8"%>

<ul class="nav flex-column">
  <li class="nav-item mb-2">
    <h6 class="text-white">Controller</h6>
    <a class="nav-link text-warning" href="<%=application.getContextPath()%>">Home</a>
    <a class="nav-link text-warning" href="<%=application.getContextPath()%>/exam01/boardlist">Controller에서 데이터를 JSP 전달 </a>
     <a class="nav-link text-warning" href="<%=application.getContextPath()%>/exam02/method1form">문자 인코딩을 통해 한글 복원</a>
      <a class="nav-link text-warning" href="<%=application.getContextPath()%>/exam02/method2">리다이렉트(요청 재 지정)</a>
       <a class="nav-link text-warning" href="<%=application.getContextPath()%>/exam02/method3">요청 방식별 메소드 실행</a>
       <a class="nav-link text-warning" href="<%=application.getContextPath()%>/exam03/content">요청 파라미터 받기 </a>
      
        
  </li>
   <li class="nav-item mb-2">
    <h6 class="text-white">db 연동</h6>
     <a class="nav-link text-warning" href="<%=application.getContextPath()%>/exam04/content">게시판 </a>
    </li>
    <li class="nav-item mb-2">
    <h6 class="text-white">AJAX 연동</h6>
     <a class="nav-link text-warning" href="<%=application.getContextPath()%>/exam05/content">게시판 </a>
    </li>
    <li class="nav-item mb-2">
    <h6 class="text-white">상태 유지</h6>
     <a class="nav-link text-warning" href="<%=application.getContextPath()%>/exam06/content">쿠키 & 세션</a>
    </li>
      <li class="nav-item mb-2">
    <h6 class="text-white">회원 서비스</h6>
     <a class="nav-link text-warning" href="<%=application.getContextPath()%>/exam07/joinForm">회원 가입</a>
     <a class="nav-link text-warning" href="<%=application.getContextPath()%>/exam07/loginForm">로그인</a>
    </li>
    <li class="nav-item mb-2">
    <h6 class="text-white">스프링 시큐리티</h6>
     <a class="nav-link text-warning" href="<%=application.getContextPath()%>/exam08/user/boardlist">사용자가 사용하는 페이지</a>
     <a class="nav-link text-warning" href="<%=application.getContextPath()%>/exam08/admin/boardlist">관리자가 사용하는 페이지</a>
    </li>
    <li class="nav-item mb-2">
    <h6 class="text-white">유효성 검사</h6>
     <a class="nav-link text-warning" href="<%=application.getContextPath()%>/exam09/joinForm">폼 유효성 검사</a>

    </li>
</ul>