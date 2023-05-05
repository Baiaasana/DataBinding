package com.example.databinding.fragment.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.databinding.data.Image
import com.example.databinding.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.image = Image("AVATARI", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSbpKuhQnt2Gn5wm0usuP2QK8CB5z8_yBB8oQ&usqp=CAU", 5)
        listeners()
    }

    private fun listeners(){
        binding.btnBottomSheet.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToBottomSheetFragment())

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}