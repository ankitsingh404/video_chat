package com.example.videochat;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class ConferenceActivity extends AppCompatActivity {
    EditText userIdEditText;
    TextView heyUserTextView;
    ZegoSendCallInvitationButton voiceCallBtn, videoCallBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);

        userIdEditText = findViewById(R.id.user_id_edit_text);
        heyUserTextView = findViewById(R.id.hey_user_text_view);
        voiceCallBtn = findViewById(R.id.voice_call_btn);
        videoCallBtn = findViewById(R.id.video_call_btn);
        String userID = getIntent().getStringExtra("userID");
        heyUserTextView.setText("Hey " +userID);
        userIdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String targetUserID = userIdEditText.getText().toString().trim();
            setVoiceCall(targetUserID);
            setVideoCall(targetUserID);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    void setVoiceCall(String targetUserID){
        voiceCallBtn.setIsVideoCall(false);
        voiceCallBtn.setResourceID("zego_uikit_call");
        voiceCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID,targetUserID)));


    }
    void setVideoCall(String targetUserID){
        videoCallBtn.setIsVideoCall(true);
        videoCallBtn.setResourceID("zego_uikit_call");
        videoCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID,targetUserID)));


    }
}
