package com.example.map_test;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private final String[] name = {
            "대학본부(1)",
            "다산관(2)",
            "창학관(3)",
            "제2창업보육센터(4)",
            "혜성관(5)",
            "청운관(6)",
            "서울테크노파크(7)",
            "창조관(8)",
            "파워플랜트(10)",
            "붕어방(11)",
            "정문(13)",
            "도예실습실(14)",
            "서울테크어린이집(30)",
            "창업보육센터(31)",
            "프론티어관(32)",
            "하이테크관(33)",
            "중앙도서관(34)",
            "중앙도서관 별관(35)",
            "수연관(36)",
            "학생회관(37)",
            "국제관(38)",
            "다빈치관(39)",
            "어의관(40)",
            "불암학사(41)",
            "KB학사(42)",
            "성림학사(43)",
            "협동문(44)",
            "수림학사(45)",
            "누리학사(46)",
            "창명학사(47)",
            "100주년기념관(51)",
            "제2학생회관(52)",
            "상상관(53)",
            "아름관(54)",
            "체육관(55)",
            "대륙관(56)",
            "무궁관(57)",
            "제2파워플랜트(58)",
            "학군단(59)",
            "미래관(60)",
            "창의문(61)",
            "테크노큐브(62)",
            "축구장(63)"
    };
    private final LatLng[] coordinates = {
            new LatLng(37.631940109290205, 127.08028475455848), // 1.대학본부
            new LatLng(37.632606797757845, 127.07797042387246), // 2.다산관
            new LatLng(37.63256520457462, 127.07939122387255), // 3.창학관
            new LatLng(37.631810247201855, 127.0810698190898), // 4. 제2창업보육센터
            new LatLng(37.63191849620882, 127.08190199688703), // 5. 혜성관
            new LatLng(37.63347841287489, 127.08074173921553), // 6. 청운관
            new LatLng(37.634606728660415, 127.08058101037982), // 7. 서울테크노파크
            new LatLng(37.634933300785754, 127.07926785085797), // 8. 창조관
            new LatLng(37.63369184609446, 127.0793280158986), // 10. 파워플랜트
            new LatLng(37.63348144403566, 127.07804605357771), // 11. 붕어방
            new LatLng(37.63030574745412, 127.07704500341129), // 13. 정문
            new LatLng(37.63528854028758, 127.07921219833554), // 14. 도예실습실
            new LatLng(37.63088913376205, 127.07632905455836), // 30. 서울테크어린이집
            new LatLng(37.630982542723814, 127.07584625678824), // 31. 창업보육센터
            new LatLng(37.631596813283814, 127.07602498339413), // 32. 프론티어관
            new LatLng(37.63205774208335, 127.07611288154399), // 33. 하이테크관
            new LatLng(37.633127311697486, 127.07677819873736), // 34. 중앙도서관
            new LatLng(37.633858012200776, 127.07659580852965), // 35. 중앙도서관 별관
            new LatLng(37.63353788848489, 127.07703876620097), // 36. 수연관
            new LatLng(37.63425830078016, 127.07699118348893), // 37. 학생회관
            new LatLng(37.63496148055425, 127.077527266892), // 38. 국제관
            new LatLng(37.63522487934611, 127.0785036232184), // 39. 다빈치관
            new LatLng(37.635407549989054, 127.07667972114099), // 40. 어의관
            new LatLng(37.63643134691555, 127.07610572842343), // 41. 불암학사
            new LatLng(37.63595131111219, 127.07612182167706), // 42. KB학사
            new LatLng(37.63530638169927, 127.07589795400675), // 43. 성림학사
            new LatLng(37.636251489959804, 127.07548449443777), // 44. 협동문
            new LatLng(37.6362977198388, 127.07829570402158), // 45. 수림학사
            new LatLng(37.634645683633025, 127.07700052061527), // 46. 누리학사
            new LatLng(37.636747429338, 127.07939373244844), // 47. 창명학사
            new LatLng(37.630596729944436, 127.07847490540215), // 51. 100주년기념관
            new LatLng(37.630772619223464, 127.07925896051216), // 52. 제2학생회관
            new LatLng(37.63100542581396, 127.0804414683763), // 53. 상상관
            new LatLng(37.6299884552601, 127.08086843825708), // 54. 아름관
            new LatLng(37.62917652186653, 127.07948043098813), // 55. 체육관
            new LatLng(37.62926574297632, 127.08043540942784), // 56. 대륙관
            new LatLng(37.630800809903704, 127.08093240703127), // 57. 무궁관
            new LatLng(37.63086453606586, 127.08204284153675), // 58. 제2파워플랜트
            new LatLng(37.6287596864867, 127.08129100651882), // 59. 학군단
            new LatLng(37.629150550155096, 127.08133392186184), // 60. 미래관
            new LatLng(37.631468953758585, 127.08285999320655), // 61. 창의문
            new LatLng(37.63007527958225, 127.07991824254486), // 62. 테크노큐브
            new LatLng(37.62992804786635, 127.07815411654242), // 63. 축구장
    };
    private final String[] info = {
            "총장님 있음",
            "공과대학",
            "전자IT미디어학과",
            "창업분위기 조성",
            "공동실험 실습관",
            "화학공학과",
            "서울시에서 600억, 과기대에서 80억을 들여 만든 초대박 건물",
            "대학원 건물",
            "발전기 및 보일러 관리",
            "붕어방에는 붕어가 진짜 산다.",
            "한자 [큰 대]를 형상화",
            "가마가 매우 많은 곳",
            "꿈나무가 자라는 곳",
            "창업 분위기 조성",
            "신소재공학과, 기계시스템디자인학과, 기계자동차공학과, 산업공학과",
            "각종 실험실 보유",
            "꿈을 키우는 곳",
            "노트북 열람실 보유",
            "복사실, 구두수선점, 미용실 등 보유",
            "편의점과 각종 동아리실 보유",
            "어학 관련 수업 진행",
            "조형대학에서 사용하는 건물",
            "각종 교양 수업 진행",
            "서울과학기술대학교 기숙사",
            "서울과학기술대학교 기숙사",
            "서울과학기술대학교 기숙사",
            "하계역에 가까운 출입구",
            "서울과학기술대학교 기숙사",
            "서울과학기술대학교 기숙사",
            "고시반용 기숙사",
            "개교 100주년을 맞아 지은 건물",
            "편의점, 농협은행, 우체국",
            "318억이 들어간 대박 건물",
            "건설시스템공학과",
            "체육관입니다.",
            "현재 철거중",
            "어네지바이오대학 일부, 건축학부, 기술경영융합대학 소속 학과 입주",
            "작업실 및 숙직실",
            "충성",
            "컴퓨터공학과 및 전기정보공학과 사용. 매우 춥다",
            "후문",
            "260억이 투입된 대박 건물",
            "인조 잔디 축구장과 육상트랙"
    };
    private GoogleMap mMap;
    private TextView information;
    private Button btnViewTogether;
    private ImageButton btnCurrentLocation;
    private FusedLocationProviderClient fusedLocationClient;
    private SearchView searchView;
    private final Map<String, Marker> markerMap = new HashMap<>();
    private Marker selectedMarker;

    private CursorAdapter suggestionAdapter;

    @SuppressLint("UseSupportActionBar")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        information = findViewById(R.id.information);

        // GoogleMap : 기본 지도 기능 및 데이터를 관리하기 위한 진입점 앱은 SupportMapFragment
        // 또는 MapView 객체에서 가져온 GoogleMap 객체에만 액세스 가능
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        // Android에서 위치 정보를 가져오기 위한 Google Play Services 라이브러리 참조
        // 직접 제작한 현재 위치로 이동하는 버튼
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        btnCurrentLocation = findViewById(R.id.btnCurrentLocation);
        btnCurrentLocation.setOnClickListener(view -> {
            // 현재 위치에 접근할 때 필요한 permission 확인
            if (ActivityCompat.checkSelfPermission(
                    MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(
                    MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return;
            }
            // permission이 확인되었다면 현재 위치의 위도와 경도를 받아옴
            fusedLocationClient.getLastLocation().addOnSuccessListener(
                    MainActivity.this, location -> {
                        if (location != null) {
                            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                        }
                    });
        });
        // toolbar를 적용하여 검색에 활용
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 같이보기 버튼에 Listener 추가
        btnViewTogether = findViewById(R.id.btnViewTogether);
        btnViewTogether.setOnClickListener(view -> {
            // 마커의 위치
            if (selectedMarker != null) {
                LatLng markerPosition = selectedMarker.getPosition();
                // 현재 위치 (예시)
                final LatLng[] currentPosition = new LatLng[1];
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                // getLastLocation()은 비동기적으로 작동하기 때문에 getLastLocation()의 결과는 Listener 내부에서만 유효
                // 해당 method가 완료되기 전에는 currentPosition[0]의 값이 설정되지 않음
                fusedLocationClient.getLastLocation().addOnSuccessListener(
                        MainActivity.this, location -> {
                            if (location != null) {
                                currentPosition[0] = new LatLng(location.getLatitude(), location.getLongitude());
                                // LatLngBounds 생성
                                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                                builder.include(markerPosition);
                                builder.include(currentPosition[0]);
                                LatLngBounds bounds = builder.build();
                                // 카메라 이동 (여백 추가를 위해 마지막 인자로 padding 값을 설정)
                                int padding = 100; // 화면 가장자리와 객체 사이의 공간(픽셀 단위)
                                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                                // 지도 카메라 업데이트
                                mMap.animateCamera(cu);
                            } else {
                                System.out.println("not success");
                            }
                        });
            } else {
                Toast.makeText(getApplicationContext(), "마커를 선택해주세요", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // toolbar에 검색버튼 추가를 위해 사용하는 callback method
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // 검색 제안을 위한 CursorAdapter 초기화
        suggestionAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                null,
                new String[]{"name"},
                new int[]{android.R.id.text1},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        assert searchView != null;
        searchView.setSuggestionsAdapter(suggestionAdapter);

        // searchView에 Listener 추가
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 검색어 입력이 감지되었을 때
                Marker marker = markerMap.get(query);
                if (marker != null) {
                    onMarkerClick(marker);
                    return false;
                }
                for (String s : name) {
                    if (s.contains(query)) {
                        marker = markerMap.get(s);
                        if (marker != null) {
                            onMarkerClick(marker);
                            return false;
                        }
                    }
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                // 텍스트가 변경될 때 수행
                updateSearchSuggestions(newText);
                return false;
            }
        });

        // serachView에 추천관련 Listener 추가
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false; // 일반적으로 사용하지 않음
            }

            @Override
            public boolean onSuggestionClick(int position) {
                // 추천 검색어를 클릭했을 때 정의
                Cursor cursor = (Cursor) suggestionAdapter.getItem(position);
                // SuppressLint를 이용하여 getColumnIndex가 0 미만의 값을 가져오는 에러 무시
                // 에러를 무시해야 실행할 수 있지만 문제가 될 수 있음
                @SuppressLint("Range") String selectedItem = cursor.getString(cursor.getColumnIndex("name"));
                searchView.setQuery(selectedItem, true); // true로 설정하면 자동으로 검색 실행
                return true;
            }
        });
        return true;
    }
    //  GoogleMap 객체의 이벤트 및 사용자 상호작용을 처리하는 콜백 인터페이스
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull final GoogleMap googleMap) {
        mMap = googleMap;
//        // LatLngBounds를 이용하여 제약을 둘 공간을 생성
//        LatLngBounds limitation = new LatLngBounds(
//                new LatLng(37.63, 127.073), // 좌하단 좌표
//                new LatLng(37.636245, 127.082717)  // 우상단 좌표
//        );
//
//        // limitation내에서만 카메라를 움직일 수 있게 설정
//        mMap.setLatLngBoundsForCameraTarget(limitation);
        PolylineOptions polylineOptions = new PolylineOptions()
                .add(new LatLng(37.630285217221534, 127.07647686656554))
                .add(new LatLng(37.62849239496751, 127.07765856285083))
                .add(new LatLng(37.628469843469006, 127.08160941476578))
                .add(new LatLng(37.62893214781636, 127.08182297440464))
                .add(new LatLng(37.62893214781636, 127.08182297440464))
                .add(new LatLng(37.63080859288266, 127.08232678854247))
                .add(new LatLng(37.6313332454328, 127.08305407051573))
                .add(new LatLng(37.63201754162557, 127.08295806432389))
                .add(new LatLng(37.633196124698976, 127.08295806432389))
                .add(new LatLng(37.634967331534426, 127.08146984594387)) // 테크노파크 우상단
                .add(new LatLng(37.63560711073242, 127.08016871784719))
                .add(new LatLng(37.635782207227194, 127.07894412672304))
                .add(new LatLng(37.6364691202606, 127.07863797894203))
                .add(new LatLng(37.63588995871119, 127.07729433034748))
                .add(new LatLng(37.63609199232254, 127.07654596910497))
                .add(new LatLng(37.63641524495807, 127.07645242394965))
                .add(new LatLng(37.63647585467067, 127.07593367354289)) // 불암학사
                .add(new LatLng(37.636143493681004, 127.0756020215813))
                .add(new LatLng(37.63496424154558, 127.07519611307885))
                .add(new LatLng(37.634586400934786, 127.07642357606895))
                .add(new LatLng(37.63434003145244, 127.07645091934103))
                .add(new LatLng(37.63340117104594, 127.07612905426855))
                .add(new LatLng(37.6331837509401, 127.07620859677958))
                .add(new LatLng(37.63285238464207, 127.07617104585448))
                .add(new LatLng(37.63081318320869, 127.07553799786206))
                .add(new LatLng(37.63069729071907, 127.07627305128595))
                .add(new LatLng(37.630285217221534, 127.07647686656554));

        mMap.addPolyline(polylineOptions);

        // 건물에 대한 마커 추가
        for (int i = 0; i < name.length; i++) {
            addMarker(coordinates[i], name[i], info[i]);
        }

        // 줌 컨트롤 활성화
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        // moveCamera : 즉시 카메라 위치 이동
        // animateCamera : 부드러운 애니메이션 효과와 함께 이동
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates[39], 17));
        mMap.setOnMarkerClickListener(this);

        // 현재 위치로 이동하는 기능을 사용하기 위해 permission 확인
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 위치 권한 요청
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        // 현재 위치 버튼 활성화
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onMarkerClick(final Marker marker) {
        // 마커의 제목과 부가 정보를 TextView에 설정
        information.setText(marker.getTitle() + "\n" + marker.getSnippet());
        searchView.setQuery("", false); // true로 설정하면 자동으로 검색 실행
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 19.0f));
        selectedMarker = marker;
        return true; // 이벤트 처리가 완료되었음을 나타냄
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT)
                .show();
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            // 모든 요청된 권한이 승인되었는지 확인
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // 모든 권한이 승인됨
                Toast.makeText(this, "권한이 승인되었어요.", Toast.LENGTH_SHORT).show();
            } else {
                // 적어도 하나의 권한이 거부됨
                Toast.makeText(this, "거절된 권한이 있어요.", Toast.LENGTH_SHORT).show();

            }
        }
    }
    private void addMarker(LatLng latLng, String title, String snippet) {
        MarkerOptions options = new MarkerOptions().position(latLng).title(title).snippet(snippet);
        Marker marker = mMap.addMarker(options);
        markerMap.put(title, marker); // 제목으로 마커 매핑
    }

    // 검색 제안을 업데이트하는 method
    private void updateSearchSuggestions(String query) {
        MatrixCursor cursor = new MatrixCursor(new String[]{"_id", "name"});
        for (int i = 0; i < name.length; i++) {
            if (name[i].contains(query)) {
                cursor.addRow(new Object[]{i, name[i]});
            }
        }
        suggestionAdapter.changeCursor(cursor);
    }
}