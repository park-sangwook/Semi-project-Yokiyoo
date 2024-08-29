window.onload=function(){
	const msg = document.querySelector("#msg");
	if(msg && msg.value.length>0)alert(document.querySelector("#msg").value);
}

Kakao.init(''); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
        	  const nickName = response.properties.nickname;
        	  console.log(nickName);
        	  axios.post('/kakaoLoginOk',{
				'id':nickName
			  }).then((response)=>{
				location.href="/";
				
				})
			  .catch(error=>console.log(error));
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }
  
  
  function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response) {
        	console.log(response);
        	axios.get("/logout");
        	location.href="/";
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
    }else{
		location.href="/logout";
	}
  }  