package com.example.kotlinforfilemanager.music

import java.io.Serializable

data class MusicListModel(var fileName :String ="",
                          var filePath :String ="",
                          var fileSize :String ="",
                          var fileCreatedTime :String ="",
                          var position :Int=0): Serializable