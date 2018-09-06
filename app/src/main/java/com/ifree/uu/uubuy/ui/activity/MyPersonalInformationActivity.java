package com.ifree.uu.uubuy.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.custom.rounded.RoundedImageView;
import com.ifree.uu.uubuy.dialog.EditContentDialog;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.PhotoUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */
public class MyPersonalInformationActivity extends BaseActivity {
    @BindView(R.id.ri_my_icon_img)
    RoundedImageView mUserIcon;
    @BindView(R.id.ll_nick_name)
    LinearLayout llNickName;
    @BindView(R.id.text_personal_information_nick_name)
    TextView mNickName;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.text_personal_information_sex)
    TextView mSex;
    @BindView(R.id.ll_birthday)
    LinearLayout llBirthday;
    @BindView(R.id.text_personal_information_birthday)
    TextView mBirthday;

    private AlertDialog builder; //底部弹出菜单
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri,cropImageUri;

    private EditContentDialog dialog;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_information;
    }


    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("个人中心");
    }


    @Override
    protected void loadData() {

    }
    @OnClick({R.id.ri_my_icon_img, R.id.ll_nick_name, R.id.ll_sex, R.id.ll_birthday})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.ri_my_icon_img:
                showChoosePicDialog();
                break;
            case R.id.ll_nick_name:
                dialog = new EditContentDialog(context, R.string.nick_name, new EditContentDialog.OnSureBtnClickListener() {
                    @Override
                    public void sure(String nickName) {
                        mNickName.setText(nickName);
                        dialog.dismiss();
                    }
                });
                dialog.setEditText(R.string.edit_nick_name,false);
                dialog.show();
                break;
            case R.id.ll_sex:
                new AlertDialog.Builder(MyPersonalInformationActivity.this).setMessage("性别")
                        .setPositiveButton("男", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mSex.setText("男");
//                                userSex = "0";
                            }
                        }).setNegativeButton("女", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSex.setText("女");
//                        userSex = "1";
                    }
                }).show();
                break;
            case R.id.ll_birthday:

                break;
        }
    }
    private void showChoosePicDialog() {
        builder = new AlertDialog.Builder(context, R.style.Dialog).create(); // 先得到构造器
        builder.show();
        LayoutInflater factory = LayoutInflater.from(context);
        View view = factory.inflate(R.layout.dialog_photo_upload, null);
        builder.getWindow().setContentView(view);
        view.findViewById(R.id.tv_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoObtainStoragePermission();
                builder.dismiss();
            }
        });
        view.findViewById(R.id.tv_photograph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoObtainCameraPermission();
                builder.dismiss();
            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        Window dialogWindow = builder.getWindow();
        dialogWindow.setWindowAnimations(R.style.Dialog);
        dialogWindow.setGravity(Gravity.BOTTOM);//显示在底部
        WindowManager m = getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        Point size = new Point();
        display.getSize(size);
        p.width = size.x;
        dialogWindow.setAttributes(p);
    }


    //自动获取相机权限
    private void autoObtainCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ToastUtils.makeText(this, "您已经拒绝过一次");

            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                imageUri = Uri.fromFile(fileUri);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    imageUri = FileProvider.getUriForFile(MyPersonalInformationActivity.this, "com.ifree.coupon_uu.uubuy.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                PhotoUtil.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
            } else {
                ToastUtils.makeText(this, "设备没有SD卡！");
            }
        }
    }



    //自动获取sdk权限
    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtil.openPic(this, CODE_GALLERY_REQUEST);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST_CODE: {//调用系统相机申请拍照权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(MyPersonalInformationActivity.this, "com.ifree.coupon_uu.uubuy.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                        PhotoUtil.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        ToastUtils.makeText(this, "设备没有SD卡！");
                    }
                } else {
                    ToastUtils.makeText(this, "请允许打开相机！！");
                }
                break;
            }
            case STORAGE_PERMISSIONS_REQUEST_CODE://调用系统相册申请Sdcard权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtil.openPic(this, CODE_GALLERY_REQUEST);
                } else {
                    ToastUtils.makeText(this, "请允许打操作SDCard！！");
                }
                break;
        }
    }

    private static final int output_X = 480;
    private static final int output_Y = 480;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    cropImageUri = Uri.fromFile(fileCropUri);
                    PhotoUtil.cropImageUri(this, imageUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtil.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(this, "com.ifree.coupon_uu.uubuy.fileprovider", new File(newUri.getPath()));
                        PhotoUtil.cropImageUri(this, newUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    } else {
                        ToastUtils.makeText(this, "设备没有SD卡！");
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtil.getBitmapFromUri(cropImageUri, this);
                    if (bitmap != null) {
                        showImages(bitmap);
                    }
                    break;
            }
        }
    }

    private void showImages(Bitmap bitmap) {
        mUserIcon.setImageBitmap(bitmap);
//        mPicture = ImageUtil.bitmaptoString(bitmap);
    }

    //检查设备是否存在SDCard的工具方法
    private static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
