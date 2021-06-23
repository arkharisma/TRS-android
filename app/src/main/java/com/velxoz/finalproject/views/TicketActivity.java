package com.velxoz.finalproject.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.velxoz.finalproject.MainActivity;
import com.velxoz.finalproject.R;
import com.velxoz.finalproject.adapter.TicketListAdapter;
import com.velxoz.finalproject.entity.ListResponse;
import com.velxoz.finalproject.entity.ObjectResponse;
import com.velxoz.finalproject.entity.ticket.Ticket;
import com.velxoz.finalproject.models.APIClient;
import com.velxoz.finalproject.models.TicketInterface;
import com.velxoz.finalproject.util.session.MainSession;

import java.util.HashMap;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketActivity extends AppCompatActivity {

    TicketInterface ticketInterface;
    MainSession mainSession;
    HashMap<String, String> user;
    String token, id_ticket;
    ImageView ivQRCode;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    TextView tvAgencyName, tvBusCode, tvSourceStop, tvDestStop, tvTanggal, tvJam, tvSeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        mainSession = new MainSession(TicketActivity.this);
        user = mainSession.getUserDetails();
        token = user.get("token");
        Bundle bundle = getIntent().getExtras();
        id_ticket = bundle.getString("id_ticket");

        ivQRCode = findViewById(R.id.ivQRCode);
        tvAgencyName = findViewById(R.id.tvAgencyName);
        tvBusCode = findViewById(R.id.tvBusCode);
        tvSourceStop = findViewById(R.id.tvSourceStop);
        tvDestStop = findViewById(R.id.tvDestStop);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvJam = findViewById(R.id.tvJam);
        tvSeat = findViewById(R.id.tvSeat);

        ticketInterface = APIClient.getClient(token).create(TicketInterface.class);

        final ProgressDialog progressDialog = new ProgressDialog(TicketActivity.this, R.style.dialogWaiting);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();

        new Thread(){
            @Override
            public void run(){
                super.run();
                try{
                    Thread.sleep(1000);
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    try{
                        runOnUiThread(() -> {
                            generateQR();
                            refresh();
                        });
                    } catch(Exception e) {
                        e.printStackTrace();
                        Log.wtf("Error : ", e.getMessage());
                    }
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void refresh(){
        Call<ObjectResponse<Ticket>> ticketCall = ticketInterface.ticketDetail(id_ticket);
        ticketCall.enqueue(new Callback<ObjectResponse<Ticket>>() {
            @Override
            public void onResponse(Call<ObjectResponse<Ticket>> call, Response<ObjectResponse<Ticket>> response) {
                if(response.body() != null) {
                    ObjectResponse<Ticket> ticketObj = response.body();
                    if(ticketObj.getSuccess()){
                        tvAgencyName.setText(ticketObj.getObject().getTripSchedule().getTripDetail().getAgency().getName());
                        tvBusCode.setText(ticketObj.getObject().getTripSchedule().getTripDetail().getBus().getCode());
                        tvSourceStop.setText(ticketObj.getObject().getTripSchedule().getTripDetail().getSourceStop().getName());
                        tvDestStop.setText(ticketObj.getObject().getTripSchedule().getTripDetail().getDestStop().getName());
                        tvTanggal.setText(ticketObj.getObject().getJourneyDate());
                        tvJam.setText(ticketObj.getObject().getTripSchedule().getTripDate());
                        tvSeat.setText(ticketObj.getObject().getSeatNumber().toString());
                    } else{
                        Toast.makeText(TicketActivity.this, ticketObj.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ObjectResponse<Ticket>> call, Throwable t) {
                Toast.makeText(TicketActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateQR(){
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        Display display = manager.getDefaultDisplay();

        Point point = new Point();
        display.getSize(point);

        int width = point.x;
        int height = point.y;

        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        qrgEncoder = new QRGEncoder(id_ticket, null, QRGContents.Type.TEXT, dimen);
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            ivQRCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}