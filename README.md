# EasyPhotosEX
EasyPhotos的简化版 相机 相册 后续增加裁剪功能 开源作者地址https://github.com/HuanTanSheng/EasyPhotos

裁剪独立出来  参考UCrop 使用

        //单独使用相机
                EasyPhotos.createCamera(this)
                        .setFileProviderAuthority("com.huantansheng.easyphotos.sample.fileprovider")
                        .start(101);


            //相册单选，无相机功能

                EasyPhotos.createAlbum(this, false, GlideEngine.getInstance())
                        .start(101);


            //相册多选，无相机功能

                EasyPhotos.createAlbum(this, false, GlideEngine.getInstance())
                        .setCount(9)
                        .start(101);


            //相册单选，有相机功能

                EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                        .setFileProviderAuthority("com.huantansheng.easyphotos.sample.fileprovider")
                        .start(101);
                break;

            //相册多选，有相机功能

                EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                        .setFileProviderAuthority("com.huantansheng.easyphotos.sample.fileprovider")
                        .setCount(22)
                        .start(101);
                        
                        
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


