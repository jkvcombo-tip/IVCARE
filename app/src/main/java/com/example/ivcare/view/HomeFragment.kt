package com.example.ivcare.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.ivcare.R
import com.example.ivcare.databinding.ActivityMainBinding
import com.example.ivcare.databinding.FragmentHomeBinding
import com.example.ivcare.display.MyRecyclerViewAdapter
import com.example.ivcare.display.UserViewModel
import com.example.ivcare.display.UserViewModelFactory
import com.example.ivcare.userdatabase.UserDatabase
import com.example.ivcare.userdatabase.UserRepository
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ivcare.userdatabase.User

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: MyRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireContext()

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val dao = UserDatabase.getInstance(application).userDAO
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this,factory)[UserViewModel::class.java]
        binding.myViewModel = userViewModel
        binding.lifecycleOwner = this

        initRecyclerView()

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initRecyclerView(){
        binding.userRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MyRecyclerViewAdapter { selectedItem: User -> listItemClicked(selectedItem) }
        binding.userRecyclerView.adapter = adapter
        displayUsersList()
    }
    private fun displayUsersList(){
        userViewModel.users.observe(requireActivity()) {
            Log.i("MYTAG", it.toString())
            //binding.subscriberRecyclerView.adapter = MyRecyclerViewAdapter(it,{selectedItem:Subscriber->listItemClicked(selectedItem)})
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }
    private fun listItemClicked(user: User){
        Toast.makeText(requireContext(),"selected name is ${user.name}", Toast.LENGTH_LONG).show()
        userViewModel.initUpdateAndDelete(user)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onContextItemSelected(item)
    }
}