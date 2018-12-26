package com.lhm.view.easyphotosex

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.huantansheng.easyphotos.EasyPhotos
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun camera(view :View){
        EasyPhotos.createCamera(this)
                .setFileProviderAuthority("com.lhm.view.easyphotosex.provider")
                .start(1)

    }

    fun album(view :View){
        EasyPhotos.createAlbum(this, false, GlideEngine.getInstance())
                .setFileProviderAuthority("com.lhm.view.easyphotosex.provider")//参数说明：见下方`FileProvider的配置`
                .setCount(9)//参数说明：最大可选数，默认1
                .start(1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
//                GlideEngine.getInstance().loadPhoto(this,
//                        data!!.getStringArrayListExtra(EasyPhotos.RESULT_PATHS)[0],iv)
                val sourceUri=data!!.data
                val destinationUri = Uri.fromFile(File(cacheDir, "CroppedImage.jpg"))
                UCrop.of(sourceUri, destinationUri)
//                        .withAspectRatio(16, 9)
//                        .withMaxResultSize(maxWidth, maxHeight)
                        .start(this);
            }
        }else if (requestCode == UCrop.REQUEST_CROP){
            val  resultUri = UCrop.getOutput(data!!)
//            GlideEngine.getInstance().loadPhoto(this,
//                        resultUri,iv)
            Log.e("resultUri",resultUri.toString())
        }
    }




}
