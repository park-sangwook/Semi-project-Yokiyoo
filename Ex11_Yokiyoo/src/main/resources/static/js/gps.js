kakao.maps.load(function() {
	var mapContainer = document.getElementById('map'); // 지도를 표시할 div
	var mapOption = {
		center: new kakao.maps.LatLng(33.450701, 126.570667), // 기본 중심 좌표
		level: 3 // 확대 레벨
	};

	// 지도를 생성합니다
	//var map = new kakao.maps.Map(mapContainer, mapOption);

	// 지도 클릭 이벤트 추가
	mapContainer.addEventListener("click", function() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				var lat = position.coords.latitude; // 위도
				var lon = position.coords.longitude; // 경도

				var locPosition = new kakao.maps.LatLng(lat, lon); // 마커 위치 생성
				getAddress(locPosition);
				// 마커와 클릭 이벤트 설정
			}, function(error) {
				console.error('GeoLocation Error:', error);
				alert('현재 위치를 찾을 수 없습니다.');
			});
		} else {
			alert('이 브라우저는 GeoLocation을 지원하지 않습니다.');
		}
	});

	// 주소를 가져오는 함수
	function getAddress(locPosition) {
		var geocoder = new kakao.maps.services.Geocoder();
		geocoder.coord2Address(locPosition.getLng(), locPosition.getLat(), function(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				var address = result[0].address.address_name; // 주소 가져오기
				console.log(address);
				document.querySelector('.showLocation').textContent = address; // 주소 표시
				sessionStorage.setItem("address",address);
			} else {
				console.error('주소 변환 실패:', status);
			}
		});
	}
});