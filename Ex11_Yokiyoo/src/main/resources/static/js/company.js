const restaurantItem = document.querySelectorAll(".restaurant-item");
let restaurantItemIdx=0;
restaurantItem.forEach((item,index)=>{
    item.addEventListener("click",function(){
        restaurantItemIdx=index-1;
        location.href="/company/"+(index-1);
    })
})

const listItem = document.querySelectorAll(".list-item");
listItem.forEach(item=>{
    item.addEventListener("click",function(){
        location.href=location.pathname+'/'+this.querySelector(" .item-title").textContent;
    })
})