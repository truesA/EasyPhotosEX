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

             //视频
             EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                            .setVideo(true)
                            .setVideoMaxSecond(15)
                            .setVideoMinSecond(3)
                            .setFileProviderAuthority("com.lhm.view.easyphotosex.provider")//参数说明：见下方`FileProvider的配置`
                            .setCount(9)//参数说明：最大可选数，默认1
                            .start(1)
                            
      //新增视频选择 回调外置的录制                       
             EasyPhotos.createAlbum(this, false, GlideEngine.getInstance())
                .setVideoListener {
                    Toast.makeText(this, "去录制视频", Toast.LENGTH_SHORT).show()
                }
                .onlyVideo(true)
                .setVideo(true)
                .setVideoMaxSecond(15)
                .setVideoMinSecond(3)
                .setFileProviderAuthority("com.lhm.view.easyphotosex.provider")//参数说明：见下方`FileProvider的配置`
                .setCount(9)//参数说明：最大可选数，默认1
                .start(1)

     /**
     * 启动裁剪
     * @param activity 上下文
     * @param sourceFilePath 需要裁剪图片的绝对路径
     * startUCrop  p(activity: Activity, sourceFilePath: String ):
     * retutrn cameraScalePath  val cameraScalePath = outFile.absolutePath
     */
  
    


