const navLink = document.querySelectorAll(".nav-item");
let navLinkIdx=0;
navLink.forEach((item,index)=>{
    item.addEventListener("click",function(){
        navLink[navLinkIdx].children[0].classList.remove("active");
        navLink[navLinkIdx].children[1].style.display='none';
        navLink[1].children[2].style.display='none';
        if(index==1){
            this.children[2].style.display='block';
        }
        this.children[0].classList.add("active");
        this.children[1].style.display='flex';
        if(index==2)this.children[1].style.display='block';
        navLinkIdx=index;
    })
})

const file = document.querySelector(".company-logo input");
file.addEventListener("change",function(e){
    console.log(e.target.files[0]);
    if(e.target.files[0])document.querySelector("#company-logo").setAttribute("src","/images/"+e.target.files[0].name);
})

const menuItem = document.querySelectorAll(".menu-item");
menuItem.forEach((item,index)=>{
    item.addEventListener("click",function(){
        document.querySelector(".menu-info").style.display='block';
        document.querySelector(".menu-info-img img").setAttribute("src",this.children[0].getAttribute("src"));
        document.querySelector(".menu-info-price").children[0].value=this.children[2].textContent;
        document.querySelector("#menu_name").value=document.querySelectorAll(".menu-title")[index].textContent;
        document.querySelector(".hide-container").style.display='block';
    })
})
document.querySelector(".menu-info-title img").addEventListener("click",function(){
    document.querySelector(".menu-info").style.display='none';
    document.querySelector(".menu-insert").style.display='none';
    document.querySelector(".hide-container").style.display='none';
})
document.querySelector(".cancel").addEventListener("click",function(){
    document.querySelector(".menu-info").style.display='none';
    document.querySelector(".menu-insert").style.display='none';
    document.querySelector(".hide-container").style.display='none';
})

document.querySelector("#form").addEventListener("submit",function(){
    let menuPrice = document.querySelector("#menu_price").value;
    menuPrice=menuPrice.replace(",","");
    document.querySelector("#menu_price").value=menuPrice;
    return true;
})



const fileInput = document.getElementById('fileInput');
const preview = document.getElementById('preview');

fileInput.addEventListener('change', function(event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            preview.src = e.target.result;
            preview.style.display = 'block'; // 미리보기 이미지 보이기
        }
        reader.readAsDataURL(file);
    }
});

function menuInsert(){
    document.querySelector(".menu-insert").style.display='block';
    document.querySelector(".hide-container").style.display='block';
}


function deleteOk(){
	const menu_name = document.querySelector("#menu_name").value;
	axios.delete('/menuDeleteOk/'+menu_name)
	.then(()=>{
		alert('삭제완료');
		location.reload();
		})
	.catch(error=>console.log(error));
}



// 구글 차트

 google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {
		const idx = document.querySelector("#company_idx").value;
		axios.get('/orderedList/'+idx)
		.then(response=>{
			const arr = (response.data);
			console.log(arr);
	        var data = new google.visualization.DataTable();
	        data.addColumn('string', 'Topping');
	        data.addColumn('number', 'Slices');
	        arr.forEach(item=>{
				console.log(typeof item.menu_name);
				data.addRow([''+item.menu_name,item.pay_count]);
			})
	     /*   data.addRows([
	          ['Mushrooms', 3],
	          ['Onions', 1],
	          ['Olives', 1],
	          ['Zucchini', 1],
	          ['Pepperoni', 2]
	        ]);
	*/
	        // Set chart options
	        var options = {'title':'메뉴별 점유율',
	                       'width':800,
	                       'height':700};
	
	        // Instantiate and draw our chart, passing in some options.
	        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	        chart.draw(data, options);
		}).catch(error=>console.log(error));
        // Create the data table.
      }