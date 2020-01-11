package clearfaun.com.coffeelog.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import clearfaun.com.coffeelog.R
import clearfaun.com.coffeelog.adapter.CharacterAdapter
import clearfaun.com.coffeelog.databinding.MainFragmentBinding
import clearfaun.com.coffeelog.model.Character


class MainFragment : Fragment(), DataCallback {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: MainFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.callback = this
        binding.lifecycleOwner = this


        println("Unconfined : I'm working in thread ${Thread.currentThread().name}")


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        test()

    }


    fun test() {

//
//        val data = arrayListOf(
//            Character("Raising Arizona", "","","",""),
//            Character("Raisijjkkjjkng Arizona", "","","",""),
//            Character("Raisinlkhlkjljzona", "","","",""),
//            Character("Raisihkjhhhhha", "","","",""),
//            Character("Raisyyyyyyyy777na", "","","",""),
//            Character("Rai987987987zona", "","","","")
//        )


    }


    override fun onResponse(data: ArrayList<Character>?) {
        Log.d("", "")
        initRv(data)
    }

    fun initRv(data: ArrayList<Character>?) {


        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")

        activity?.runOnUiThread(Runnable {
            // Stuff that updates the UI
            println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")

            if(data != null){
                val mLayoutManager = LinearLayoutManager(activity)
                mLayoutManager.orientation = LinearLayoutManager.VERTICAL
                binding.usersList.layoutManager = mLayoutManager
                binding.usersList.adapter = CharacterAdapter(data)
            }else{
                val toast = Toast.makeText(
                    activity,
                    "No Data",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }

        })

//        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
//
//
//        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
//
//        GlobalScope.launch{Main
//            if(data != null){
//                println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
//
//
//            }
//
//        }



    }

}
