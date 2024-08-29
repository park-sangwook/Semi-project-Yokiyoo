function pay(name,count){
	const address2=$("#address2").val();
	const phone=$("#phone").val();
	if(address2.trim().length==0){
		alert("상세주소를 입력해주세요");
		$("#address2").focus();
		return false;
	}
	if(phone.trim().length==0){
		alert("휴대전화번호를 입력해주세요");
		$("#phone").focus();
		return false;
	}
	const form = $("<form action='/payOk' method='post'></form>");
	for(let i=0;i<name.length;i++){
		form.append($("<input type='hidden' name='order_name' value='"+name[i]+"' />"));
		form.append($("<input type='hidden' name='order_count' value='"+count[i]+"' />"));	
			
	}
		form.append($("<input type='hidden' name='address' value='"+$("#address").val()+"' />"));
		form.append($("<input type='hidden' name='address2' value='"+address2+"' />"));
		form.append($("<input type='hidden' name='phone' value='"+phone+"' />"));
	$("body").append(form);
	form.submit();
}
document.querySelector("#address").value=sessionStorage.getItem("address");
