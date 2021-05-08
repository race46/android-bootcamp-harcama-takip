package com.example.ayagini_yorganina_gore_uzat.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ayagini_yorganina_gore_uzat.R
import com.example.ayagini_yorganina_gore_uzat.databinding.FragmentDetayBinding
import com.example.ayagini_yorganina_gore_uzat.databinding.FragmentGirisBinding
import com.example.ayagini_yorganina_gore_uzat.retrofit.MainViewModel
import com.example.ayagini_yorganina_gore_uzat.room_database.ViewModel
import kotlinx.android.synthetic.main.fragment_giris.*
import kotlinx.android.synthetic.main.fragment_giris.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.roundToInt

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Giris.newInstance] factory method to
 * create an instance of this fragment.
 */
class Giris : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val adapter = RecyclerViewAdapter()
    lateinit var mainViewModel : MainViewModel
    private lateinit var dataBinding : FragmentGirisBinding
    private var birim = "Tl"
    var total = 0.0
    var EUR:Float? = 0.0f
    var USD:Float? = 0.0f
    var GBP:Float? = 0.0f


    private lateinit var mUserViewModel:ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        mainViewModel = MainViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_giris,container,false)
        dataBinding.fraGiris = this


        //recycler view
        val rec_view = dataBinding.recyclerView
        rec_view.adapter = adapter
        rec_view.layoutManager = LinearLayoutManager(requireContext())

        //user view model
        mUserViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        mUserViewModel.readAll.observe(viewLifecycleOwner, Observer {
            adapter.setData(it,birim,USD!!,EUR!!,GBP!!)
            var x = 0.0
            val sprf = context?.getSharedPreferences("sp", Context.MODE_PRIVATE)
            USD = sprf?.getFloat("USD",1f)
            EUR = sprf?.getFloat("EUR",1f)
            GBP = sprf?.getFloat("GBP",1f)
            for (i in it){
                when(i.birim){
                    "tl" -> x+=i.tutar
                    "dolar" -> x+= i.tutar*USD!!
                    "euro" -> x+= i.tutar*EUR!!
                    "sterlin" -> x+=i.tutar*GBP!!
                }
            }

            setHarcanan(x)
            total = x
        })


        return dataBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Giris.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Giris().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        when(birim){
            "Tl" -> dataBinding.button3.setTextColor(Color.BLACK)
            "Dolar" -> dataBinding.button5.setTextColor(Color.BLACK)
            "Euro" -> dataBinding.button4.setTextColor(Color.BLACK)
            "Sterlin" -> dataBinding.button6.setTextColor(Color.BLACK)
        }
        val sp = context?.getSharedPreferences("sp", Context.MODE_PRIVATE)
        isim.text = "Merhaba\n"+
        sp?.getString("name","no-one")+"\n"+sp?.getString("gender","")




    }

    fun setHarcanan(tutar:Double){
        var Tutar = tutar
        when(birim){
            "Dolar" ->Tutar/=USD!!
            "Euro" ->Tutar/=EUR!!
            "Sterlin" -> Tutar/=GBP!!
        }
        dataBinding.harcam.text = "Harcanan\n"+(Tutar*100).roundToInt()/100.0+" "+birim
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       runBlocking {
        try {


            mainViewModel.EUR.observe(requireActivity(), Observer {
                EUR = it.toFloat()
                dataBinding.button4.text = "€ EURo ("+(EUR.toString()+"0000").substring(0,4)+" tl)"

            })
            mainViewModel.USD.observe(requireActivity(), Observer {
                USD = it.toFloat()
                dataBinding.button5.text = "\$ Dolar ("+(USD.toString()+"0000").substring(0,4)+" tl)"

            })
            mainViewModel.GBP.observe(requireActivity(), Observer {
                GBP = it.toFloat()
                dataBinding.button6.text = "£ Sterlin ("+(GBP.toString()+"0000").substring(0,5)+" tl)"

            })
        } finally {

        }}
    }

    fun floatingaction(view: View){
        Navigation.findNavController(view).navigate(R.id.action_giris_to_ekle)
    }
    fun btn3(view: View){
        dataBinding.button3.setTextColor(Color.BLACK)
        dataBinding.button4.setTextColor(Color.WHITE)
        dataBinding.button5.setTextColor(Color.WHITE)
        dataBinding.button6.setTextColor(Color.WHITE)
        birim = "Tl"
        adapter.changeCurrency(birim,USD!!,EUR!!,GBP!!)
        setHarcanan(total)
    }
    fun btn4(view: View){
        dataBinding.button3.setTextColor(Color.WHITE)
        dataBinding.button4.setTextColor(Color.BLACK)
        dataBinding.button5.setTextColor(Color.WHITE)
        dataBinding.button6.setTextColor(Color.WHITE)
        birim = "Euro"
        adapter.changeCurrency(birim,USD!!,EUR!!,GBP!!)
        setHarcanan(total)
    }
    fun btn5(view: View){
        dataBinding.button3.setTextColor(Color.WHITE)
        dataBinding.button4.setTextColor(Color.WHITE)
        dataBinding.button5.setTextColor(Color.BLACK)
        dataBinding.button6.setTextColor(Color.WHITE)
        birim = "Dolar"
        adapter.changeCurrency(birim,USD!!,EUR!!,GBP!!)
        setHarcanan(total)
    }
    fun btn6(view: View){
        dataBinding.button3.setTextColor(Color.WHITE)
        dataBinding.button4.setTextColor(Color.WHITE)
        dataBinding.button5.setTextColor(Color.WHITE)
        dataBinding.button6.setTextColor(Color.BLACK)
        birim = "Sterlin"
        adapter.changeCurrency(birim,USD!!,EUR!!,GBP!!)
        setHarcanan(total)
    }
    fun changeName(view: View){
        Navigation.findNavController(view).navigate(R.id.action_giris_to_isimDegistir)
    }

}