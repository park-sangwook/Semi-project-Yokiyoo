const menuItem = document.querySelectorAll(".main-wrapper .item");
menuItem.forEach((item, index) => {
	item.addEventListener("click", function() {
		location.href = "/company/" + index;
	})
})

document.querySelector(".search-btn").addEventListener("click", function() {
	new daum.Postcode({
		oncomplete: function(data) {
			console.log(data);
			document.querySelector(".showLocation").textContent=data.jibunAddress;
			sessionStorage.setItem("address",data.jibunAddress);
		}
	}).open();

})
if(sessionStorage.getItem("address")){
	document.querySelector(".showLocation").textContent=sessionStorage.getItem("address");	
}