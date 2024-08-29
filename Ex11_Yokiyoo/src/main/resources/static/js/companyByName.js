let swiper = new Swiper(".mySwiper", {
  slidesPerView: 4,
  slidesPerGroupSkip: 1,
  keyboard: {
    enabled: true,
  },
  scrollbar: {
    el: ".swiper-scrollbar",
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
});
const restaurantItem = document.querySelectorAll(".restaurant-item");
let restaurantItemIdx=0;
restaurantItem.forEach((item,index)=>{
    item.addEventListener("click",function(){
        restaurantItemIdx=index-1;
        location.href="/company/"+(index-1);
    })
})
let obj={
  name:[],
  count:[],
  price:[]
}

let quantity=1;
const menuItem = document.querySelectorAll(".swiper-slide .item");
menuItem.forEach(item=>{
  item.addEventListener("click",function(){
    // let options = "toolbar=no,scrollbars=no,resizable=yes,status=no,menubar=no,width=500, height=500, top=20px,left=550px";
    // window.open("menuDetail/"+this.querySelector(" h3").textContent,"_blank", options);
    document.querySelector(".menuDetail").style.display='block';
    
    const menuName = this.children[1].children[0].textContent;
    axios.get('/selectMenuName/'+menuName)
    .then(response=>{
		const data = (response.data);
    $(".menu-content").html($("<img src='"+data.menu_image+"'/>"));
    $(".menu-text").text(data.menu_name);
    $(".menu-price").html($("<div>가격</div><div>"+data.menu_price.toLocaleString('ko-KR')+"</div>"));
    $(".menu-count").html($("<div>수량</div><div class='count-group'><button class='minus' onclick='updateCount(\"minus\","+data.menu_price+")'>-</button><div class='quantity'>1</div><button class='plus' onclick='updateCount(\"plus\","+data.menu_price+")'>+</button></div>"));
    $(".total").html($("<div>총 주문금액</div><div class='total-text'>"+data.menu_price.toLocaleString('ko-KR')+"</div>"));
	}).catch(error=>console.log(error));

  })
})

document.querySelector(".menuDetail .menu-title img").addEventListener("click",function(){
  document.querySelector(".menuDetail").style.display='none';
  quantity=1;
})

function updateCount(type,price){
  if(type=='plus')quantity++;
  else if(type=='minus' && quantity>1)quantity--;
  $(".menu-count .quantity").text(quantity);
  $(".total-text").text((quantity*price).toLocaleString('ko-KR'));
}
document.querySelector(".menuDetail .pay").addEventListener("click",function(){
	if(obj.name.includes($(".menu-text").text())){
		const idx = obj.name.indexOf($(".menu-text").text());
		obj.count[idx]+=parseInt($(".menu-count .quantity").text());
	}else{
      obj.name.push($(".menu-text").text());
	  obj.count.push(parseInt($(".menu-count .quantity").text()));
	}
		quantity=0;
		console.log(obj.count);
	  obj.price.push(parseInt($(".total-text").text().replace(",",""))/parseInt($(".menu-count .quantity").text()));
	
  $(".menuDetail").hide();
  let total = 0;
  let div = $("<div></div>");
  for(let i=0;i<obj.name.length;i++){
	div.append($("<div class='pay-load'><div>"+obj.name[i]+"</div><img src='/images/cancel.svg' style='position:absolute;cursor:pointer;' onclick='update("+i+")'/><div class='pay-price' id='list"+i+"'><div style='display:inline-block;'>"+(obj.price[i]*obj.count[i]).toLocaleString("ko-KR")+"</div><div style='display:inline-block;'><div class='count-group'><button class='minus' onclick='orderUpdate("+i+","+obj.count[i]+",\"minus\")'>-</button><div class='quantity'>"+obj.count[i]+"</div><button class='plus' onclick='orderUpdate("+i+","+obj.count[i]+",\"plus\")'>+</button></div></div></div></div>"));
    console.log(obj);
    total+=obj.price[i]*obj.count[i];
  }
  div.append($("<div class='totalOrder'>합계 : <span class='orderTotal'>"+total.toLocaleString("ko-KR")+"</span></div>"));
  $(".sub-body").html(div);
})
function update(idx){
  obj.name=obj.name.filter((item,index)=>index!==idx);
  obj.price=obj.price.filter((item,index)=>index!==idx);
  obj.count=obj.count.filter((item,index)=>index!==idx);
  let div = $("<div></div>");
  let total=0;
  for(let i=0;i<obj.name.length;i++){
	div.append($("<div class='pay-load'><div>"+obj.name[i]+"</div><img src='/images/cancel.svg' style='position:absolute;cursor:pointer;' onclick='update("+i+")'/><div class='pay-price' id='list"+i+"'><div style='display:inline-block;'>"+(obj.price[i]*obj.count[i]).toLocaleString("ko-KR")+"</div><div style='display:inline-block;'><div class='count-group'><button class='minus' onclick='orderUpdate("+i+","+obj.count[i]+",\"minus\")'>-</button><div class='quantity'>"+obj.count[i]+"</div><button class='plus' onclick='orderUpdate("+i+","+obj.count[i]+",\"plus\")'>+</button></div></div></div></div>"));
    total+=obj.price[i]*obj.count[i];
  }
  if(obj.count.length>0)div.append($("<div class='totalOrder'>합계 : <span class='orderTotal'>"+total.toLocaleString("ko-KR")+"</span></div>"));
  else div.append($("<div class='sub-body'>주문표에 담긴 메뉴가 없습니다.</div>"));
  $(".sub-body").html(div);
}
function orderUpdate(idx,count,type){
	const tmp = obj.price[idx]*obj.count[idx];
	if(type=='plus')obj.count[idx]++;
	else if(type=='minus' && obj.count[idx]>1)obj.count[idx]--;
	let total = parseInt(document.querySelector(".orderTotal").textContent.replace(",",""));
	console.log(parseInt(total));
	document.querySelector("#list"+idx+" .quantity").textContent=obj.count[idx];
	document.querySelector("#list"+idx).children[0].textContent=(obj.price[idx]*obj.count[idx]).toLocaleString("ko-KR");
	document.querySelector(".orderTotal").textContent=(total-tmp+obj.price[idx]*(obj.count[idx])).toLocaleString("ko-KR");
}






// menu-list
const menuList = document.querySelectorAll(".menu-list li");
let menuListIdx=0;
menuList.forEach((item,index)=>{
	if(index==0){
		document.querySelector("footer").style.top='196px';
		
	}
	item.addEventListener("click",function(){
		menuList[menuListIdx].classList.remove("selected");
		menuList[menuListIdx].children[1].style.display='none';
		this.classList.add("selected");
		this.children[1].style.display='block';
		const height = (this.children[1].offsetHeight);
		console.log(document.querySelector("footer").offsetHeight);
		document.querySelector("footer").style.top=height+'px';
		menuListIdx=index;
	})
})




// 결제(payCheck)

function payCheck(){
	const form=$("<form action='/pay' method='post'></form>");
	form.append($("<input type='hidden' name='company_name' value='"+$(".restaurant-title").text()+"'/>"));
	console.log(obj.price);
	for(let i=0;i<obj.count.length;i++){
		form.append($("<input type='hidden' name='order_name' value='"+obj.name[i]+"'/>"));		
		form.append($("<input type='hidden' name='order_count' value='"+obj.count[i]+"'/>"));		
		form.append($("<input type='hidden' name='order_price' value='"+obj.price[i]*obj.count[i]+"'/>"));		
	}
	$("#result").html(form);
	form.submit();
}



const reviewRate = document.querySelectorAll(".review-rate");
reviewRate.forEach(item=>{
  let star="";
  const idx = item.textContent;
  for(let i = 0;i<5;i++){
    if(i<idx)star+='★';
    else star+='☆';
  }

  item.innerHTML=star;
})




function orderCheck(){
	const menuTitle = document.querySelector(".menu-text").textContent;
	const menuPrice = parseInt(document.querySelector(".total-text").textContent.replace(",",""));
	const menuCount = parseInt(document.querySelector(".quantity").textContent);
	const form=$("<form action='/pay' method='post'></form>");
	form.append($("<input type='hidden' name='company_name' value='"+$(".restaurant-title").text()+"'/>"));
	form.append($("<input type='hidden' name='order_name' value='"+menuTitle+"'/>"));		
	form.append($("<input type='hidden' name='order_count' value='"+menuCount+"'/>"));		
	form.append($("<input type='hidden' name='order_price' value='"+menuPrice+"'/>"));		
	$("#result").html(form);
	form.submit();
}