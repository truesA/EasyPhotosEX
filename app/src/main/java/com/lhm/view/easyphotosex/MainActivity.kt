package com.lhm.view.easyphotosex

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.huantansheng.easyphotos.EasyPhotos
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import android.os.Environment.DIRECTORY_PICTURES
import android.os.Environment.getExternalStoragePublicDirectory
import android.support.v4.app.ActivityCompat
import com.yalantis.ucrop.UCropActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun camera(view: View) {
        EasyPhotos.createCamera(this)
                .setFileProviderAuthority("com.lhm.view.easyphotosex.provider")
                .start(1)

    }

    fun album(view: View) {
        EasyPhotos.createAlbum(this, false, GlideEngine.getInstance())
                .setFileProviderAuthority("com.lhm.view.easyphotosex.provider")//参数说明：见下方`FileProvider的配置`
                .setCount(9)//参数说明：最大可选数，默认1
                .start(1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                startUCrop(this, data!!.getStringArrayListExtra(EasyPhotos.RESULT_PATHS)[0])
            }
        } else if (requestCode == UCrop.REQUEST_CROP) {
            if (null != data) {
                val resultUri = UCrop.getOutput(data)
                val bit = BitmapFactory.decodeStream(contentResolver.openInputStream(resultUri))
                iv.setImageBitmap(bit)
                Log.e("resultUri", resultUri.toString())
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val cropError = UCrop.getError(data!!)
        }
    }


    /**
     * 启动裁剪
     * @param activity 上下文
     * @param sourceFilePath 需要裁剪图片的绝对路径
     * @param requestCode 比如：UCrop.REQUEST_CROP
     * @param aspectRatioX 裁剪图片宽高比
     * @param aspectRatioY 裁剪图片宽高比
     * @return
     */
   private fun startUCrop(activity: Activity, sourceFilePath: String
    ): String {
        val sourceUri = Uri.fromFile(File(sourceFilePath))
        val outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        if (!outDir.exists()) {
            outDir.mkdirs()
        }
        val outFile = File(outDir, System.currentTimeMillis().toString() + ".jpg")
        //裁剪后图片的绝对路径
        val cameraScalePath = outFile.absolutePath
        val destinationUri = Uri.fromFile(outFile)
        //初始化，第一个参数：需要裁剪的图片；第二个参数：裁剪后图片
        val uCrop = UCrop.of(sourceUri, destinationUri)
        //初始化UCrop配置
        val options = UCrop.Options()
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL)
        //是否隐藏底部容器，默认显示
        options.setHideBottomControls(false)
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(activity, R.color.colorPrimary))
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(activity, R.color.colorPrimary))
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled(false)
        //UCrop配置
        uCrop.withOptions(options)
        //设置裁剪图片的宽高比，比如16：9
//        uCrop.withAspectRatio(aspectRatioX, aspectRatioY)
        //uCrop.useSourceImageAspectRatio();
        //跳转裁剪页面
        uCrop.start(activity)
        return cameraScalePath
    }

}
