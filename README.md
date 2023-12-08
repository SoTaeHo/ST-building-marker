# ST-building-marker



## 목차

1. [프로젝트 소개](#프로젝트-소개)
2. [기능](#기능)
3. [기술 스택](#기술-스택)
4. [사용된 라이브러리 및 오픈소스 목록](#사용된-라이브러리-및-오픈소스-목록)
5. [구축 및 개발](#구축-및-개발)
6. [개발자/저자 정보](#개발자저자-정보)

## 프로젝트 소개

서울과학기술대학교의 지리에 익숙하지 않은 사람들을 위한 앱입니다.
Java기반으로 구현하였습니다.

## 기능

1. 교내 건물 및 주요 장소에 대해 마커가 있으며, 마커 클릭 시 해당 건물의 이름 및 간략한 소개를 출력해줍니다.
2. 상단의 검색어를 입력하여 찾고자 하는 건물의 마커로 이동할 수 있습니다.
3. **현재 위치로 이동하기 버튼**을 클릭하여 위치 정보를 수신하여 지도를 현재 위치로 이동시켜줍니다.
4. **"같이보기"버튼**을 이용하여 현재 선택한 마커와 현재 위치를 동시에 볼 수 있게 지도를 조정해줍니다.
5. 서울과학기술대학교가 어디까지인지 표시한 경계가 있습니다.

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
   - `setOnQueryTextListener`를 호출하여, `onQueryTextSubmit`, `onQueryTextChange`를 override하여 검색어 입력이 감지되었을 때와, 검색어가 변경되었을 때에 대한 event를 정의할 수 있습니다.
6.



## 사용된 라이브러리 및 오픈소스 목록

프로젝트에서 사용된 라이브러리 및 오픈소스 소프트웨어 목록과 각각에 대한 설명.

## 구축 및 개발

개발 환경 설정 및 빌드 프로세스에 대한 정보.

## 기여 방법

프로젝트에 기여할 수 있는 방법 및 지침.

## 라이선스

프로젝트 라이선스에 대한 정보.

## 개발자/저자 정보

프로젝트에 기여한 개발자 또는 팀 멤버의 정보.

## 감사의 말

기여자, 후원자, 조언을 주신 분들에 대한 감사의 말.