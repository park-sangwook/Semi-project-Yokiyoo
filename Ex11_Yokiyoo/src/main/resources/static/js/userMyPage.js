document.querySelector("#pwCheck").addEventListener("keyup",function(e){
    const pw = document.querySelector("#pw").value;
    let pwCheck=e.target.value;
    if(pw.trim().length>0 && pw!=pwCheck)document.querySelector(".pwShow").textContent="비밀번호가 다릅니다.";
    else document.querySelector(".pwShow").textContent="";
})
document.querySelector("#form").addEventListener("submit",function(e){
	const pw = document.querySelector("#pw").value;
	const pwCheck = document.querySelector("#pwCheck").value;
	if(pw!=pwCheck){
		alert("비밀번호를 일치시켜야 합니다.");
		return e.preventDefault();
	}
	
})