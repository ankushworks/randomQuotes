package com.ankushsoni.randomquotes.view.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ankushsoni.randomquotes.QuotesApplication
import com.ankushsoni.randomquotes.R
import com.ankushsoni.randomquotes.model.QuotesDatabase
import com.ankushsoni.randomquotes.model.QuotesTable
import com.ankushsoni.randomquotes.viewmodel.TestingHomeViewModel
import com.ankushsoni.randomquotes.viewmodelfactory.TestingHomeViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.util.Log
import com.ankushsoni.randomquotes.Utils.NetworkUtils
import kotlinx.coroutines.Dispatchers


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {

    lateinit var quotesContentTextView: TextView
    lateinit var quotesAuthorTextView: TextView
    lateinit var refreshBtn: Button
    lateinit var copyBtn: Button
    lateinit var shareBtn: Button
    var incValue = 0
    lateinit var favBtn: Button
    lateinit var myClipboardManager: ClipboardManager
    lateinit var quotesDatabase: QuotesDatabase
    private lateinit var testingHomeViewModel: TestingHomeViewModel
    lateinit var quotesTable: QuotesTable



    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        quotesDatabase = QuotesDatabase.getDatabase(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        val application = requireActivity().application
        val repository = (application as QuotesApplication).quotesRepository
        val extras = requireActivity().intent.extras

        testingHomeViewModel = ViewModelProvider(this , TestingHomeViewModelFactory(repository)).get(TestingHomeViewModel::class.java)

        //testing
        testingHomeViewModel.getQuotesList().observe(viewLifecycleOwner) {
            Log.d("QuotesListData" , it.toString())
        }




        //end


        testingHomeViewModel.quoteData.observe(viewLifecycleOwner){
            Log.d("HomeDataModel" , it.toString())
            if (extras != null) {
                if(extras.getBoolean("is_opened")){
                    quotesAuthorTextView.text = extras.getString("quote_author")
                    quotesContentTextView.text = extras.getString("quote_content")
                }else{
                    quotesAuthorTextView.text = it.author
                    quotesContentTextView.text = it.content
                }
            }else{
                quotesAuthorTextView.text = it.author
                quotesContentTextView.text = it.content
            }
        }



            refreshBtn.setOnClickListener {
                extras?.remove("is_opened")
                quotesAuthorTextView.text = "Random Person"
                quotesContentTextView.text = "Loading..."
                testingHomeViewModel.makeApiCall()
            }
            copyBtn.setOnClickListener {
                if(NetworkUtils.isNetworkAvailable(view.context)){
                    val clipDate = ClipData.newPlainText("Quotes" , "${quotesContentTextView.text}\n\n${quotesAuthorTextView.text}" )
                    Toast.makeText(requireContext(),"Copied" , Toast.LENGTH_SHORT).show()
                    myClipboardManager.setPrimaryClip(clipDate)
                }
            }
            shareBtn.setOnClickListener {
                if(NetworkUtils.isNetworkAvailable(view.context)){
                    val intent =  Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.putExtra(Intent.EXTRA_TEXT, "${quotesContentTextView.text}\n\n${quotesAuthorTextView.text}")
                    startActivity(intent)
                }
            }
            favBtn.setOnClickListener {
                if(NetworkUtils.isNetworkAvailable(view.context)){
                    if(quotesContentTextView.text != "Loading..." && quotesAuthorTextView.text != "Random Person"){
                        Toast.makeText(requireContext(),"Quote is Added" , Toast.LENGTH_LONG).show()
                        GlobalScope.launch(Dispatchers.IO) {
                            repository.insertQuote(QuotesTable(0,quotesContentTextView.text.toString() , quotesAuthorTextView.text.toString()))
                        }
                    }
                }
            }
//
//        }else{
//            if(incValue == 0){
//                Toast.makeText(view.context,"No Network" , Toast.LENGTH_SHORT).show()
//            }
//            incValue = 1
//        }




        super.onViewCreated(view, savedInstanceState)
    }

    fun initView(view : View){
        quotesContentTextView = view.findViewById(R.id.quotes_content)
        quotesAuthorTextView = view.findViewById(R.id.quotes_author)
        refreshBtn = view.findViewById(R.id.btn_refresh)
        copyBtn = view.findViewById(R.id.btn_copy)
        shareBtn = view.findViewById(R.id.btn_Share)
        favBtn = view.findViewById(R.id.btn_fav)
        myClipboardManager  = view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }
}