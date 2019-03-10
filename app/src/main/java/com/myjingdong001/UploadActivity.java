package com.myjingdong001;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.myjingdong001.aboatupdate.SelectPicPopupWindow;

public class UploadActivity extends AppCompatActivity{
    private ImageView img_upload;

    SelectPicPopupWindow menuWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        img_upload = (ImageView) findViewById(R.id.img_upload_img);

    }
    public void updatego(View view){

    }
    View.OnClickListener itemsOnClick;
    public void imagechange(View view){
        menuWindow = new SelectPicPopupWindow(UploadActivity.this, itemsOnClick); //显示窗口
        menuWindow.showAtLocation(UploadActivity.this.findViewById(R.id.img_upload_img), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

    }

}
