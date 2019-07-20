package com.ny.ex56_multimedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    Button button1;
    ImageView imageView;
    //파일 저장시
    String currentPicturePath;
    File  pictureFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        imageView = findViewById(R.id.imageView);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();

            }
        });
        AutoPermissions.Companion.loadAllPermissions(this,101);
    }
    private void dispatchTakePictureIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!= null){
            //startActivityForResult(intent,100);
            pictureFile = null;
            try {
                pictureFile = createImageFile();
            }catch (Exception e){   }

            //manifest에서 프로바이더 설정필요함.
            if(pictureFile != null){
                Uri pictureURI = FileProvider.getUriForFile(this, "com.ny.ex56_multimedia",pictureFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureURI);
                startActivityForResult(intent,100);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       if(requestCode==100){
           if(resultCode ==RESULT_OK){
             // Bundle extras=  data.getExtras();
             //  Bitmap imageBitmap = (Bitmap)extras.get("data");

               BitmapFactory.Options options = new BitmapFactory.Options();
               options.inSampleSize = 8;
               Bitmap imageBitmap = BitmapFactory.decodeFile(pictureFile.getAbsolutePath(),options);

               imageView.setImageBitmap(imageBitmap);
           }
       }
    }

    private File createImageFile() throws IOException {
        //오늘날짜 형식지정 >> 파일이름 지정
        String msg = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imgName = "JPEG_"+msg+"_";
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imgName,".jpg",  dir  );
        currentPicturePath = image.getAbsolutePath();
        Toast.makeText(this, currentPicturePath, Toast.LENGTH_SHORT).show();
        return image;
    }

    @Override
    public void onDenied(int i, String[] strings) {  }

    @Override
    public void onGranted(int i, String[] strings) { }
}
