# ST-building-marker



## 목차

1. [프로젝트 소개](#프로젝트-소개)
2. [기능](#기능)
3. [레이아웃 구조](#레이아웃-구조)
4. [기술 스택](#기술-스택)
5. [사용된 라이브러리 및 오픈소스 목록](#사용된-라이브러리-및-오픈소스-목록)
6. [라이센스](#라이센스)
7. [개발자/저자 정보](#개발자-정보)

## 프로젝트 소개

서울과학기술대학교의 지리에 익숙하지 않은 사람들을 위한 앱입니다.
Java기반으로 구현하였습니다.
![start](/image/start.PNG)
![search](/image/search.PNG)
![route](/image/route.PNG)
![together](/image/together.PNG)

## 기능

1. 교내 건물 및 주요 장소에 대해 마커가 있으며, 마커 클릭 시 해당 건물의 이름 및 간략한 소개를 출력해줍니다.
2. 상단의 검색어를 입력하여 찾고자 하는 건물의 마커로 이동할 수 있습니다.
3. **현재 위치로 이동하기 버튼**을 클릭하여 위치 정보를 수신하여 지도를 현재 위치로 이동시켜줍니다.
4. **"같이보기"버튼**을 이용하여 현재 선택한 마커와 현재 위치를 동시에 볼 수 있게 지도를 조정해줍니다.
5. **"경로보기"버튼**을 이용하여 현재 선택한 마커와 정문 간 경로를 볼 수 있습니다.
6. 서울과학기술대학교가 어디까지인지 표시한 경계가 있습니다.

## 레이아웃 구조
![layout](df)
1. `activity-main.xml`
   - 앱 실행 시 가장 먼저 보이는 화면입니다.
   - AppBarLayout을 사용하여 상단에 검색을 위한 레이아웃을 추가하였습니다. AppBarLayout 내부에는 Androidx의 Toolbar widget을 사용하였습니다.
   - GoogleMap 사용을 위해 Fragment를 추가하였습니다.
   -  GoogleMap Fragment 하단에 RelativeLayout을 추가하여 마커에 대한 정보가 출력될 TextView와 "경로보기"버튼, "같이보기"버튼, 현재위치 버튼을 추가하기 위해 Button과 ImageButton을 추가하였습니다.
   - 사전에 지정된 크기의 TextView를 넘는 텍스트 입력 시 스크롤이 되게 하기 위해 TextView를 ScrollView로 감쌌습니다.
2. `menu_main.xml`
   - AppBar 내부에 돋보기 모양 버튼을 추가해줍니다. Android의 아이콘을 사용하기 위해서는 다음 의존성을 추가해야합니다.
   >implementation("com.google.android.material:material:1.10.0")

## 기술 스택

1. **GoogleMap**
   - GoogleMap 객체는 기본 지도 기능 및 데이터를 관리하기 위한 진입점으로 사용되며, 앱은 SupportMapFragment 또는 MapView 객체에서 가져온 GoogleMap 객체에만 접근할 수 있습니다.
   - 이후 GoogleMap 객체의 이벤트 및 사용자 상호작용을 처리하는 콜백 인터페이스 `public  void onMapReady(GoogleMap googleMap)`를 사용하여 객체에 대한 핸들을 가져와 마커 추가, 카메라 시점 세팅, UI 세팅등을 합니다.
   - GoogleMap에 대한 자세한 내용은 다음의 문서를 참고해주세요.	  - [Android용 Maps SDK 지도 추가](https://developers.google.com/maps/documentation/android-sdk/map?hl=ko)
2. **마커(사진)**
   - GoogleMap의 `import com.google.android.gms.maps.model.Marker`를 사용하여 구글 지도에 마커를 생성합니다. 마커는 위도와 경도로 이루어진 **position, title, snippet**으로 이루어져 있습니다.
   - GoogleMap의 `public boolean onMarkerClick(final Marker marker)` method를 override하여 마커가 클릭되었을 때의 event를 작성해줍니다.
   - GoogleMap 객체에 **addMarker(Marker marker)** method를 이용하여  마커를 추가할 수 있습니다.
   - 마커 클릭시 레이아웃 하단에 있는 TextView에 **setText()**를 호출하여 현재 선택된 마커의 title과 snippet을 TextView의 내용으로 대체해줍니다.
3. **fusedLocationClient**
   - 현재 위치를 가져오기 위해서 사용합니다.
   - Android에서 위치 정보를 가져오기 위해 Google Play service 라이브러리를 참조해야 하므로 Google Play service SDK를 추가적으로 설치해야합니다.
   - 위치 정보 관련 퍼미션이 확인되면 **getLastLocation()**, **addOnSuccessListener**를 이용하여 현재 위치 정보를 받아올 수 있으며, 해당 위치로 GoogleMap 객체의 카메라를 이동시키는 method를 사용하여 지도를 이동시킵니다.
4. **toolbar**
   - `  
     androidx.appcompat.widget.Toolbar`를 사용하였으며, `androidx.appcompat.widget.Toolbar`는 AndroidX 라이브러리의 일부입니다.
   - 기존의 ActionBar를 대체하며 유연한 배치, 메뉴 및 네비게이션 지원, 쉬운 스타일링, 통합호환성 등 기존 `android.widget.Toolbar`에 비해 더 많은 기능을 제공합니다.
5. **public boolean onCreateOptionsMenu(Menu menu)**
   - toolbar에 검색 버튼 추가 및 검색 제안을 위한 cursorAdaptor 추가를 위해 사용하는 callback method입니다.
   - `searchView`를 이용하여 `menu_main.xml`에 있는 `action_search`아이템을 `searchView`에 등록합니다. `searchView`에는 `setOnQueryTextListener`, `setOnSuggestionListener`를 등록하여 사용합니다.
   - `setOnQueryTextListener`는 `onQueryTextSubmit`, `onQueryTextChange`를 override하여 검색어 입력이 감지되었을 때와, 검색어가 변경되었을 때에 대한 event를 정의할 수 있습니다.
   - `setOnSuggestionListener`는 `onSuggestionClick()`을 override하여 추천 검색어를 클릭하였을 때 event를 정의할 수 있습니다.
6. **DirectionsApiRequest**
   - `Direction API`를 이용하여 출발지의 위도/경도와 목적지의 위도/경도를 이용하여 경로 정보가 담긴 JSON 객체를 요청합니다. API 네트워크 요청의 경우 main Thread에서 진행할 수 없어 별도의 Thread를 생성하여 API 네트워크 요청을 처리합니다.
   - 요청받은 JSON 객체는 문자열로 변환하여 `DirectionsJsonParser`의 인자로 전달합니다.
7. **DirectionsJsonParser**
   - `DirectionsApiRequest`로부터 전달받은 JSON 객체의 문자열을 분리하여 경로의 각 step에서 polyline을 그리기 위한 정보를 추출합니다. polyline decoding을 위해 다음의 의존성을 gradle에 추가합니다.
   > `implementation("com.google.maps.android:android-maps-utils:2.2.0")`

   - LatLng으로 이루어진 List를 반환하여 Polyline을 그리는 데 사용합니다.
8. **Button getRoutebtn**
   - `onClickListener()`를 등록하여 버튼을 눌렀을 때 정문과 선택한 마커 사이의 경로를 출력해줍니다.
   - API 요청을 위해 새로운 Thread를 사용하였습니다.


## 사용된 라이브러리 및 오픈소스 목록

1. AndroidX(Jetpack)
   - AndroidX는 Android Support Library의 후속이자 개선 버전으로, Android 개발을 위한 라이브러리 모음입니다.
   - 다양한 Android와 호환이 가능하며, androidx로 시작하는 일관된 name space를 사용하여 라이브러리 관리 및 지속적인 업데이트가 특징입니다.
   - [Android Jetpack](https://developer.android.com/jetpack?hl=ko)
2. GoogleMap API
   - Google에서 제공하는 API입니다.
   - [지도 SDK](https://developers.google.com/maps/documentation/android-sdk?hl=ko), [Directions API](https://developers.google.com/maps/documentation/directions?hl=ko)를 사용하였습니다.
   - 이외에도 Geocoding, 거리 행렬, 도로 식별 등 추가적인 API 사용이 가능하며 자세한 참고는 다음의 링크를 참고해주세요.[Google Maps Platform | Google for Developers](https://developers.google.com/maps?hl=ko)
3. 의존성 목록
   >implementation("com.google.android.gms:play-services-maps:18.2.0")  
   implementation("com.google.android.gms:play-services-location:21.0.1")  
   implementation("com.google.android.material:material:1.10.0")  
   implementation("com.google.maps.android:android-maps-utils:2.2.0")
4. Default config
   >defaultConfig {  
   applicationId = "com.example.map_test"  
   minSdk = 28  
   targetSdk = 33  
   versionCode = 1  
   versionName = "1.0"
   testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"  
   }

## 라이센스
MIT License

Copyright (c) [2023] [Taeho Soh]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## 개발자-정보
서울과학기술대학교 컴퓨터공학과 소태호
