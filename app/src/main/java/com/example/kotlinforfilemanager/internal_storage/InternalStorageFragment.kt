package com.example.kotlinforfilemanager.internal_storage

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinforfilemanager.InternalStorageFragmentContract

import com.example.kotlinforfilemanager.databinding.FragmentInternalStorageBinding
import java.io.File




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [InternalStorageFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [InternalStorageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InternalStorageFragment : Fragment() ,
    InternalStorageFragmentContract.View {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var path : String =""
    lateinit var binding: FragmentInternalStorageBinding
    lateinit var internalStorageData : InternalStorageData
    lateinit var internalFileList : MutableList<InternalStorageFilesModel>
    lateinit var adapter: InternalStorageFragmentAdapter
    lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding=FragmentInternalStorageBinding.inflate(layoutInflater,container,false)

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.view=this
        path=Environment.getExternalStorageDirectory().absolutePath
        internalFileList = ArrayList<InternalStorageFilesModel>()
        getInernalDataList(path)
        binding.data=internalStorageData
        binding.recyclerView.layoutManager=LinearLayoutManager(this.context)
        binding.recyclerView.setItemAnimator(DefaultItemAnimator())
        adapter= InternalStorageFragmentAdapter()
        binding.recyclerView.adapter=adapter
        adapter.setDataList(internalFileList)
        adapter.notifyDataSetChanged()


    }


    fun getInernalDataList(path : String){
        var folderPath=path
        internalStorageData= InternalStorageData()
        internalStorageData.storageRootname=folderPath
        var file : File =File(folderPath)
        val fileArray = file.listFiles()
        if (fileArray!=null){
            for (f : File in fileArray){
                var internalStorageFileModelData=
                    InternalStorageFilesModel()
                internalStorageFileModelData.extension=f.extension
                internalStorageFileModelData.fileName=f.name
                internalStorageFileModelData.filePath=f.canonicalPath
                internalStorageFileModelData.isCheckboxVisible=false
                internalStorageFileModelData.isSelected=false

                if (f.isDirectory){
                    internalStorageFileModelData.setDir(true)
                }else{
                    internalStorageFileModelData.setDir(false)
                }

                internalFileList.add(internalStorageFileModelData)

            }
        }

    }
    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InternalStorageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InternalStorageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}