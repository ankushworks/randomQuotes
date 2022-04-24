package com.ankushsoni.randomquotes.view.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ankushsoni.randomquotes.R
import com.ankushsoni.randomquotes.model.QuotesDao
import com.ankushsoni.randomquotes.model.QuotesDatabase
import com.ankushsoni.randomquotes.model.QuotesTable
import com.ankushsoni.randomquotes.network.RetroInstance
import com.ankushsoni.randomquotes.network.RetroServiceInterface
import com.ankushsoni.randomquotes.repository.QuotesRepository
import com.ankushsoni.randomquotes.view.adapters.TestingFavAdapter
import com.ankushsoni.randomquotes.viewmodel.TestingFavViewModel
import com.ankushsoni.randomquotes.viewmodelfactory.TestingFAvViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavouriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouriteFragment : Fragment() , TestingFavAdapter.RowClicksListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var favRecyclerView: RecyclerView
    private lateinit var text : TextView
    private lateinit var favImage : ImageView

    private lateinit var testingFavViewModel: TestingFavViewModel
    private lateinit var testingFavAdapter: TestingFavAdapter
    private lateinit var quotesDao: QuotesDao
    lateinit var myClipboardManager: ClipboardManager
    private lateinit var quotesRepository: QuotesRepository




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
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        Log.d("FAVFRAG" , "onViewCreated cALLED")
        myClipboardManager  = view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        favRecyclerView = view.findViewById(R.id.favRecyclerView)
        text = view.findViewById(R.id.emptyTv)
        favImage = view.findViewById(R.id.favImage)
        linearLayoutManager = LinearLayoutManager(requireContext())
        testingFavAdapter = TestingFavAdapter(this)
        quotesDao = QuotesDatabase.getDatabase(view.context).quotesDao()
        val quoteService = RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)

        quotesRepository = context?.let { QuotesRepository(quotesDao, quoteService, it) }!!

        testingFavViewModel = ViewModelProvider(this , TestingFAvViewModelFactory(quotesRepository)).get(TestingFavViewModel::class.java)

        testingFavViewModel.getQuotes().observe(viewLifecycleOwner) {
            Log.d("QuotesData", it.toString())
            if(it.isEmpty()){
                favRecyclerView.visibility = View.GONE
                text.visibility = View.VISIBLE
                favImage.visibility = View.VISIBLE
            }else{
                favRecyclerView.visibility = View.VISIBLE
                text.visibility = View.GONE
                favImage.visibility = View.GONE

            }
            testingFavAdapter.submitList(it)
        }

        favRecyclerView.layoutManager = linearLayoutManager
        favRecyclerView.setHasFixedSize(true)
        favRecyclerView.adapter = testingFavAdapter

        super.onViewCreated(view, savedInstanceState)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavouriteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            FavouriteFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDeleteItemClickListener(quotes: QuotesTable) {
        GlobalScope.launch {
            testingFavViewModel.deleteQuote(quotes)
        }
    }

    override fun onCopyItemClickListener(quotes: QuotesTable) {
        val clipDate = ClipData.newPlainText("Quotes" , "${quotes.quote}\n\n${quotes.author}" )
        Toast.makeText(requireContext(),"Copied" , Toast.LENGTH_SHORT).show()
        myClipboardManager.setPrimaryClip(clipDate)
    }


}