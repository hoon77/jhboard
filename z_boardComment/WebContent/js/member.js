function joinCheck(){
	

	if(document.frm.id.value.length==0) {
		alert("아이디를 입력해주세요!");
		frm.id.focus();
		return false;
		
	}
	
	if(document.frm.id.value.length<4) {
		alert("아이디는 4글자 이상이어야 합니다!");
		frm.id.focus();
		return false;
		
	}
	
	
	if(document.frm.name.value.length==0) {
		alert("이름을 입력해주세요!");
		frm.name.focus();
		return false;
		
	}
	
	if(document.frm.pwd.value=="") {
		alert("비밀번호를 입력해주세요!");
		frm.pwd.focus();
		return false;
	}
	
	if(document.frm.pwd.value != document.frm.re_pwd.value) {
		alert("비밀번호가 일치하지 않습니다!");
		frm.pwd.focus();
		return false;
	}
	
	return true;
}