# EasyPhotosEX
EasyPhotos的简化版 相机 相册 后续增加裁剪功能 开源作者地址https://github.com/HuanTanSheng/EasyPhotos

裁剪独立出来

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


