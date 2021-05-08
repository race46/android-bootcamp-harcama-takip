package com.example.ayagini_yorganina_gore_uzat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ayagini_yorganina_gore_uzat.R
import com.example.ayagini_yorganina_gore_uzat.room_database.Urun
import com.example.ayagini_yorganina_gore_uzat.room_database.ViewModel
import kotlinx.android.synthetic.main.fragment_ekle.*
import kotlinx.android.synthetic.main.fragment_ekle.view.*
import kotlinx.coroutines.flow.callbackFlow

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Ekle.newInstance] factory method to
 * create an instance of this fragment.
 */
class Ekle : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mUserViewModel: ViewModel

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ekle, container, false)

        mUserViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        view.floatingActionButton.setOnClickListener(){
            insertToRoom()
            activity?.onBackPressed()
            Toast.makeText(context,"eklendi",Toast.LENGTH_SHORT).show()
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Ekle.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Ekle().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun insertToRoom(){
        var aciklama = input_aciklama.text.toString()
        if (aciklama == "")
            aciklama = "günlük gider"
        val tutarString = input_tutar.text.toString()
        var tutar = 0f
        if(tutarString!="")
            tutar = tutarString.toFloat()
        var tip_id =  input_tip.checkedRadioButtonId
        var tip = ""
        when(tip_id){
            R.id.radioButton2 -> tip = R.drawable.ic_baseline_attach_money_24.toString()
            R.id.radioButton4 -> tip = R.drawable.ic_baseline_home_24.toString()
            R.id.radioButton5 -> tip = R.drawable.ic_baseline_device_unknown_24.toString()
        }
        var birim_id = input_birim.checkedRadioButtonId
        var birim = ""
        when(birim_id){
            R.id.radioButton6 -> birim = "tl"
            R.id.radioButton7 -> birim = "dolar"
            R.id.radioButton8 -> birim = "euro"
            R.id.radioButton9 -> birim = "sterlin"
        }
        val urun = Urun(0,aciklama,tutar,tip,birim)
        mUserViewModel.urunEkle(urun)
    }

}