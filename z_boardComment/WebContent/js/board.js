function writeBoardCheck(){
	

	if(document.frm.title.value.length==0) {
		alert("제목을 입력해주세요!");
		frm.title.focus();
		return false;
		
	}
	
	if(document.frm.content.value.length==0) {
		alert("내용을 입력해주세요!");
		frm.content.focus();
		return false;
		
	}
	
	
	return true;
}