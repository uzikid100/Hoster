package com.example.uzezi.activitieswithintents;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.uzezi.activitieswithintents.NewEvent_Package.EventFeaturesData;

import java.util.ArrayList;
import java.util.List;

public class createEvent extends AppCompatActivity implements EventOptionsAdapter.OnListItemClickedListener{

    private ImageView mImageIcon;
    private Button mCancelEventButton;
    private TextView mEventHost, mEventTitleTextView;
    private EditText mSetEventName;
    private EventOptionsAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private View mDialogView;
    private AlertDialog.Builder mLogBuilder;
    private TextView mOKButton, mCancelLogButton;
    private AlertDialog mDlog;
    private TextView mVisTV1;
    private View mDLogLayout;
    private RelativeLayout relativeLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        //Finding all references for this activity
        mEventTitleTextView = (TextView) findViewById(R.id.tv_eventName);
        mEventHost = (TextView) findViewById(R.id.event_host);
        mSetEventName = (EditText) findViewById(R.id.et_set_event_name);
        mImageIcon = (ImageView) findViewById(R.id.iv_insert_eventImage_icon);


        //Setting Adapter for RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_event_options);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new EventOptionsAdapter(GetData(), this);
        mRecyclerView.setAdapter(mAdapter);


        //Init Dialog
        mDialogView = getLayoutInflater().inflate(R.layout.generic_option_dialog, null);
        mLogBuilder = new AlertDialog.Builder(this);
        mLogBuilder.setView(mDialogView);
        mDlog = mLogBuilder.create();
        mCancelLogButton = (TextView) mDialogView.findViewById(R.id.cancel_action_button);
        mOKButton = (TextView) findViewById(R.id.ok_action_button);
        mVisTV1 = (TextView) mDialogView.findViewById(R.id.visibility_dialog_title);
        mDLogLayout = mDialogView.findViewById(R.id.options_dlog_header);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareApp("You're invited to " + mEventTitleTextView.getText().toString()
                + " " + mEventHost.getText().toString() + "\n\n" +
                "View the event now on Hostr: \n https//:hostr.com/download");
            }
        });

        mCancelEventButton =(Button) findViewById(R.id.cancel_createEvent_button);
        mCancelEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mImageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accessPhotosIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                if(accessPhotosIntent.resolveActivity(getPackageManager())!= null){
                    startActivityForResult(accessPhotosIntent, 1);
                }
            }
        });


        mSetEventName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        keyCode == KeyEvent.KEYCODE_ENTER){

                    if(mSetEventName == null || mSetEventName.equals("")){
                        mEventTitleTextView.setText("~EVENT NAME");
                    }

                    String name = mSetEventName.getText().toString();

                    if(name == null || name.equals("")){
                        mEventTitleTextView.setText("~EVENT NAME");
                    }else{
                        mEventTitleTextView.setText(name);
                    }
                    mSetEventName.setVisibility(View.INVISIBLE);
                    mEventTitleTextView.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });
        mCancelLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDlog.cancel();
                
            }
        });
    }

    public static List<EventFeaturesData> GetData(){
        List<EventFeaturesData> data = new ArrayList<>();

        String[] optDescription = {"SELECT VISIBILITY", "ENTER DATE AND TIME", "ENTER PRICE",
                                    "ENTER CAPACITY", "SET LOCATION", "CHOOSE EVENT TYPE"};
        int[] optIcons = {R.drawable.mask_flat_icon, R.drawable.calendar_icon_three, R.drawable.dollar,
                            R.drawable.followers_icon, R.drawable.find_event_icon, R.drawable.event_icon};

        for (int i = 0; i < optDescription.length && i < optIcons.length; i++) {
            EventFeaturesData current = new EventFeaturesData();
            current.icon = optIcons[i];
            current.optionTitle = optDescription[i];
            data.add(current);
        }
        return data;
    }

    public void shareApp (String linkToDownloadApp){
        ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(linkToDownloadApp)
                .setChooserTitle("Share Hostr with your friends!")
                .startChooser();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode){
//            case 1: if(requestCode == RESULT_OK){
//                Uri intentDataReturned = data.getData();
//                mImageFromPhotoIcon.setImageURI(intentDataReturned);
//                break;
//            }
//        }
//    }

    public void onClickEventName(View view) {
        mEventTitleTextView.setVisibility(View.INVISIBLE);
        mSetEventName.setVisibility(View.VISIBLE);
    }


    @Override
    public void OnListItemClicked(int position) {
        SetCustomDialog(position);
        mDlog.show();
    }


    private void SetCustomDialog(int index){
        switch (index){
            case 0:
                mVisTV1.setText("visibility");
                mDLogLayout.setBackgroundResource(R.color.midGray);
                break;
            case 1:
                mVisTV1.setText("Date and time");
                mDLogLayout.setBackgroundResource(R.color.lightMidBrown);
                break;
            case 2:
                mVisTV1.setText("price");
//                appendView();
                mDLogLayout.setBackgroundResource(R.color.green);
                break;
            case 3:
                mVisTV1.setText("capacity");
                mDLogLayout.setBackgroundResource(R.color.blue_grey);
                break;
            case 4:
                mVisTV1.setText("location");
                mDLogLayout.setBackgroundResource(R.color.offOrange);
                break;
            case 5:
                mVisTV1.setText("Event type");
                mDLogLayout.setBackgroundResource(R.color.deepOrange);
                break;
        }
    }

//    private void appendView() {
//        View view = View.inflate(getApplicationContext(), R.layout.price_and_cap_dlog, null);
////        mDlog.addContentView(view, new LinearLayout.LayoutParams(
////                LinearLayout.LayoutParams.MATCH_PARENT,
////                LinearLayout.LayoutParams.MATCH_PARENT
////        ));
//        mDlog.show();
//    }

    private SeekBar mSeekBar;
    private TextView mEntryAmount;
    private ImageView imageView;

    private void DLogSeekBar(){
        mSeekBar = (SeekBar) mDialogView.findViewById(R.id.dialog_seekBar);
        imageView = (ImageView) mDialogView.findViewById(R.id.tv_seekBar_icon);
        mEntryAmount = (TextView) mDialogView.findViewById(R.id.entry_amount);


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            String text = null;
            int progress_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                text = (progress == 0) ? "Free" : String.valueOf(progress) + "$";
                mEntryAmount.setText(text);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


}
