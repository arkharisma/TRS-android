package com.velxoz.finalproject.views.fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.velxoz.finalproject.MainActivity;
import com.velxoz.finalproject.R;
import com.velxoz.finalproject.entity.auth.signin.SignInRequest;
import com.velxoz.finalproject.entity.auth.signin.SignInResponse;
import com.velxoz.finalproject.entity.stop.GetResponse;
import com.velxoz.finalproject.models.APIClient;
import com.velxoz.finalproject.models.AuthApiInterface;
import com.velxoz.finalproject.models.StopApiInterface;
import com.velxoz.finalproject.util.session.MainSession;
import com.velxoz.finalproject.views.auth.LoginActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    CarouselView carouselView;
    Calendar calendar;
    DatePickerDialog.OnDateSetListener date;
    EditText etKalender, etKalenderTemp;
    Spinner spSourceStop, spDestStop;

    StopApiInterface stopApiInterface;

    List<String> spinnerListName;
    List<String> spinnerListId;

    MainSession mainSession;
    HashMap<String, String> user;
    String token;
    private View view;

    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        loadComponent();

        loadStops();

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        calendar = Calendar.getInstance();

        etKalender = view.findViewById(R.id.etKalender);

        date = (view1, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(etKalenderTemp);
        };

        etKalender.setOnClickListener(v -> {
            showCalendar(date);
            etKalenderTemp = etKalender;
        });

        spSourceStop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                parent.getChildAt(position).setText
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    private void loadComponent() {
        spSourceStop = view.findViewById(R.id.spSourceStop);
        spDestStop = view.findViewById(R.id.spDestStop);
        spinnerListName = new ArrayList<>();
        spinnerListId = new ArrayList<>();
        mainSession = new MainSession(view.getContext());
        user = mainSession.getUserDetails();
        token = user.get("token");
    }

    ImageListener imageListener = (position, imageView) -> imageView.setImageResource(sampleImages[position]);

    private void showCalendar(DatePickerDialog.OnDateSetListener date) {
        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                date,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMinDate(new Date().getTime());
        dialog.show();
    }

    private void updateLabel(EditText editText) {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(sdf.format(calendar.getTime()));
    }

    private void loadStops(){
        stopApiInterface = APIClient.getClient(token).create(StopApiInterface.class);
        Call<GetResponse> stopsCall = stopApiInterface.getAllStops();

        stopsCall.enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
                GetResponse getResponse = response.body();
                if (getResponse.getSuccess()){
                    for (int i = 0; i < getResponse.getData().size(); i++ ){
                        spinnerListId.add(getResponse.getData().get(i).getId());
                        spinnerListName.add(getResponse.getData().get(i).getName());
                    }
                    Log.d("SpinnerName", "onResponse: " + spinnerListName.toString());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.spinner_list_item, spinnerListName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spSourceStop.setAdapter(adapter);
                    spDestStop.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed Parsing Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}