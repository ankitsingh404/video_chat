package com.example.videochat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService;
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

  EditText userIdEditText;
  Button startBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         userIdEditText = findViewById(R.id.user_id_edit_text);
         startBtn = findViewById(R.id.start_btn);
         startBtn.setOnClickListener((v)->{
             String userID =userIdEditText.getText().toString().trim();
             if(userID.isEmpty()){
                 return;
             }
             startService(userID);
             Intent intent = new Intent(MainActivity.this, ConferenceActivity.class);
             intent.putExtra("userID",userID);
             startActivity(intent);
         });
    }
    void startService(String userID){
        Application application = getApplication(); // Android's application context
        long appID = 1881816494;   // yourAppID
        String appSign ="065087d67807dcf9dc276ce8c406eb26c65fbcc9eccda1c2a1b9f26202a89113";  // yourAppSign
        String userName =userID;   // yourUserName
        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        ZegoUIKitPrebuiltCallService.init(getApplication(), appID, appSign, userID, userName, callInvitationConfig);
        ZegoNotificationConfig notificationConfig= new ZegoNotificationConfig();
        notificationConfig.sound ="zego_uikit_sound_call";
        notificationConfig.channelID="CallInvitation";
        notificationConfig.channelName="CallInvitation";
        ZegoUIKitPrebuiltCallService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        ZegoUIKitPrebuiltCallService.unInit();

    }

}
