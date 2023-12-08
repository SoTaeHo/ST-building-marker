package com.example.map_test;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DirectionsJsonParser {

    public List<LatLng> parse(String jsonData) {
        List<LatLng> path = new ArrayList<>();

        try {
            // 전체 JSON 응답 객체
            JSONObject jsonObject = new JSONObject(jsonData);

            // "routes" 배열
            JSONArray routes = jsonObject.getJSONArray("routes");

            // "legs" 배열
            JSONObject route = routes.getJSONObject(0);
            JSONObject leg = route.getJSONArray("legs").getJSONObject(0);

            // "steps" 배열
            JSONArray steps = leg.getJSONArray("steps");

            // 각 "step"에서 "polyline"을 추출
            for (int i = 0; i < steps.length(); i++) {
                JSONObject step = steps.getJSONObject(i);
                JSONObject polyline = step.getJSONObject("polyline");
                String points = polyline.getString("points");

                // Polyline 디코딩
                List<LatLng> decodedPath = PolyUtil.decode(points);
                path.addAll(decodedPath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}
