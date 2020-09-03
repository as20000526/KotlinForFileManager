package com.example.kotlinforfilemanager.internal_storage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.persistableBundleOf
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinforfilemanager.BR
import com.example.kotlinforfilemanager.InternalStorageFragmentContract
import com.example.kotlinforfilemanager.R
import com.example.kotlinforfilemanager.base.ViewHolder
import com.example.kotlinforfilemanager.databinding.ItemInternalStorageBinding
import java.io.File




class InternalStorageFragmentAdapter : RecyclerView.Adapter<InternalStorageFragmentAdapter.ViewHolder<ItemInternalStorageBinding>>() ,InternalStorageFragmentContract.Adapter {


    companion object {
//        @JvmStatic
//        @BindingAdapter("uuko:src")
        fun setImageViewResource(imageView: ImageView, resource: Int) {
            imageView.setImageResource(resource)
        }
    }
    private var dataList : MutableList<InternalStorageFilesModel> = ArrayList<InternalStorageFilesModel>()
    private lateinit var  binding :ItemInternalStorageBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<ItemInternalStorageBinding> {
        var  layoutInflator : LayoutInflater= LayoutInflater.from(parent.context)
        binding  = ItemInternalStorageBinding.inflate(layoutInflator,parent,false)
        binding.view=this
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<ItemInternalStorageBinding>, position: Int) {
        var internalStorageFilesModel : InternalStorageFilesModel =dataList.get(position)
        internalStorageFilesModel.position=position
        binding.data=internalStorageFilesModel


        var fileExtension=internalStorageFilesModel.extension
        var file : File = File(internalStorageFilesModel.filePath)
        if (file.isDirectory){
            setImageViewResource(binding.icon, R.drawable.ic_launcher_foreground)
        }
        else{
            var imgFile : File = File(internalStorageFilesModel.filePath)
            var bitmap : Bitmap?= ThumbnailUtils.createVideoThumbnail(internalStorageFilesModel.filePath, MediaStore.Video.Thumbnails.MICRO_KIND)
            when(fileExtension){
                "png"->
                    if (imgFile.exists()) {
                        val THUMB_SIZE = 64
                        val ThumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(internalStorageFilesModel.filePath),
                            THUMB_SIZE, THUMB_SIZE
                        )
                        binding.icon.setImageBitmap(ThumbImage)
                    }
                "pdf"-> setImageViewResource(binding.icon,R.drawable.ic_launcher_foreground)
                "mp3"->setImageViewResource(binding.icon,R.drawable.ic_launcher_background)
                //"mp3"->binding.icon.setImageResource(R.drawable.ic_music_note_black_24dp)
                "txt"->setImageViewResource(binding.icon,R.drawable.ic_launcher_foreground)
                "zip"->setImageViewResource(binding.icon,R.drawable.ic_launcher_foreground)
                "rar"->setImageViewResource(binding.icon,R.drawable.ic_launcher_foreground)
                "html"->setImageViewResource(binding.icon,R.drawable.ic_launcher_foreground)
                "xml"->setImageViewResource(binding.icon,R.drawable.ic_launcher_foreground)
                "mp4"->binding.icon.setImageBitmap(bitmap)
                "3gp"->binding.icon.setImageBitmap(bitmap)
                "apk"->setImageViewResource(binding.icon,R.drawable.ic_launcher_foreground)
                else -> setImageViewResource(binding.icon,R.drawable.ic_launcher_foreground)
            }
        }

        if(internalStorageFilesModel.isCheckboxVisible){
            binding.checkBox.visibility=View.VISIBLE
        }
        else{
            binding.checkBox.visibility=View.GONE
        }

        holder.bind(dataList.get(position))
//        if (internalStorageFilesModel.isSelected()) {
//            holder.checkBox.setChecked(true)
//        } else {
//            holder.checkBox.setChecked(false)
//        }
    }

    override fun getItemCount(): Int {
        var size:Int=if (dataList.size!=0) dataList.size else 0
        return size
    }


    fun setDataList(list : MutableList<InternalStorageFilesModel>){
        dataList=list
    }

    override fun onItemClick(position: Int) {
        var fileExtension : String = dataList.get(position).fileName!!.substring(dataList.get(position).fileName!!.lastIndexOf(".") +1)
        var file : File=File(dataList.get(position).filePath)
        openFile(file,dataList.get(position))
    }

    fun openFile(file : File,model :InternalStorageFilesModel){
        if (file.isDirectory) {
            if (file.canRead()) {
                dataList.clear()
                getInernalDataList(model.filePath)
                notifyDataSetChanged()
            } else {
                Log.d("", "can not read")
            }
        }
    }

    class ViewHolder<B : ViewDataBinding>(private val mViewDataBinding: ItemInternalStorageBinding) :
        RecyclerView.ViewHolder(mViewDataBinding.root), View.OnLongClickListener {

        private var visibility : Boolean =true
        private lateinit var data :InternalStorageFilesModel
        init {
            mViewDataBinding.root.setOnLongClickListener(this)
        }

        override fun onLongClick(p0: View?): Boolean {
            data.isCheckboxVisible=true
            if (visibility){
                mViewDataBinding.checkBox.visibility=View.VISIBLE
            }else{
                mViewDataBinding.checkBox.visibility=View.GONE
            }
            visibility=!visibility
            return true
        }

        // why `` ?
        fun bind(`object`: InternalStorageFilesModel) {
            data=`object`
            mViewDataBinding.setVariable(BR.data, `object`)
            mViewDataBinding.executePendingBindings()
        }

    }
    //
//            if (fileExtension == "png" || fileExtension == "jpeg" || fileExtension == "jpg")
//        {
//            val imageIntent = Intent(getActivity().getApplicationContext(), FullImageViewActivity::class.java)
//            imageIntent.putExtra("imagePath", internalStorageFilesModel.getFilePath())
//            getActivity().startActivity(imageIntent)
//        }
//        else if (fileExtension == "mp3")
//        {
//            showAudioPlayer(internalStorageFilesModel.getFileName(), internalStorageFilesModel.getFilePath())
//        }
//        else if (fileExtension == "txt" || fileExtension == "html" || fileExtension == "xml")
//        {
//            val txtIntent = Intent(getActivity().getApplicationContext(), TextFileViewActivity::class.java)
//            txtIntent.putExtra("filePath", internalStorageFilesModel.getFilePath())
//            txtIntent.putExtra("fileName", internalStorageFilesModel.getFileName())
//            getActivity().startActivity(txtIntent)
//        }
//        else if (fileExtension == "zip" || fileExtension == "rar")
//        {
//            extractZip(internalStorageFilesModel.getFileName(), internalStorageFilesModel.getFilePath())
//        }
//        else if (fileExtension == "pdf")
//        {
//            val pdfFile = File(internalStorageFilesModel.getFilePath())
//            val packageManager = getActivity().getPackageManager()
//            val testIntent = Intent(Intent.ACTION_VIEW)
//            testIntent.setType("application/pdf")
//            val list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY)
//            if (list.size > 0 && pdfFile.isFile())
//            {
//                val intent = Intent()
//                intent.setAction(Intent.ACTION_VIEW)
//                val uri = Uri.fromFile(pdfFile)
//                intent.setDataAndType(uri, "application/pdf")
//                startActivity(intent)
//            }
//            else
//            {
//                Toast.makeText(getActivity().getApplicationContext(), "There is no app to handle this type of file", Toast.LENGTH_SHORT).show()
//            }
//        }
//        else if (fileExtension == "mp4" || fileExtension == "3gp" || fileExtension == "wmv")
//        {
//            val fileUri = Uri.fromFile(File(internalStorageFilesModel.getFilePath()))
//            val intent = Intent(android.content.Intent.ACTION_VIEW)
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.setDataAndType(fileUri, "video/*")
//            getActivity().startActivity(intent)
//        }
//        else if (fileExtension == "apk")
//        {
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.setDataAndType(Uri.fromFile(File(internalStorageFilesModel.getFilePath())), "application/vnd.android.package-archive")
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
//        }



    fun getInernalDataList(path : String){
        var folderPath=path

        var file : File =File(folderPath)
        val fileArray = file.listFiles()
        if (fileArray!=null){
            for (f : File in fileArray){
                var internalStorageFileModelData=
                    InternalStorageFilesModel()
                internalStorageFileModelData.fileName=f.name
                internalStorageFileModelData.filePath=f.canonicalPath
                internalStorageFileModelData.isCheckboxVisible=false
                internalStorageFileModelData.isSelected=false

                if (f.isDirectory){
                    internalStorageFileModelData.setDir(true)
                }else{
                    internalStorageFileModelData.setDir(false)
                }

                dataList.add(internalStorageFileModelData)

            }
        }

    }
}