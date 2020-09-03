
package com.example.kotlinforfilemanager.videoshow

import java.io.Serializable

data class VideoListModel(var fileName :String ="",
                          var filePath :String ="",
                          var fileSize :String ="",
                          var fileCreatedTime :String ="",
                          var position :Int=0):Serializable