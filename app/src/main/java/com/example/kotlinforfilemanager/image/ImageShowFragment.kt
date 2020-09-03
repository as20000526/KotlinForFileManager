package com.example.kotlinforfilemanager.image

import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinforfilemanager.databinding.FragmentImageShowBinding
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ImageShowFragment : Fragment() ,ImgContract.View {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    //////////////////////////////////////////////////////
    private  var imgList:ArrayList<ImgListModel> =ArrayList()
    lateinit var binding: FragmentImageShowBinding
    lateinit var adapter: ImgShowAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentImageShowBinding.inflate(inflater,container,false);
        var view : View=binding.root
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= ImgShowAdapter(this.context)
        getImgDataList()
        binding.imgViewRecycle.layoutManager=LinearLayoutManager(this.context)
        binding.imgViewRecycle.adapter=adapter


//        binding.imgViewRecycle.adapter=ImgShowAdapter()

    }

    private fun getImgDataList() {
        val mCursor = activity!!.getContentResolver().query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATA)
            , null, null,
            "LOWER(" + MediaStore.Images.Media.TITLE + ") ASC");



        if (mCursor!=null){
            while (mCursor.moveToNext()){
                var imgListModel : ImgListModel = ImgListModel()
                imgListModel.fileName=mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                imgListModel.filePath=mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                try {
                    val file =
                        File(mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)))
                    var length = file.length()
                    length = length / 1024
                    if (length >= 1024) {
                        length = length / 1024
                        imgListModel.fileSize=length.toString() + " MB"
                    } else {
                        imgListModel.fileSize=length.toString() + " KB"
                    }
                    val lastModDate = Date(file.lastModified())
                    imgListModel.fileCreatedTime=lastModDate.toString()
                } catch (e: Exception) {
                    imgListModel.fileSize="未知"
                }
                imgList.add(imgListModel)
            }
        }

        adapter.setDataList(imgList)
    }
}