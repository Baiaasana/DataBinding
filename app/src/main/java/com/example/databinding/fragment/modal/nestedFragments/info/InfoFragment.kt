package com.example.databinding.fragment.modal.nestedFragments.info

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.databinding.R
import com.example.databinding.databinding.FragmentInfoBinding

import com.example.databinding.fragment.modal.bottom_sheet.BottomSheetViewModel

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel : BottomSheetViewModel by navGraphViewModels(R.id.bottom_sheet_nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        listeners()
    }

    private fun listeners(){
        binding.btnEdit.setOnClickListener {
            findNavController().navigate(InfoFragmentDirections.actionInfoFragmentToEditFragment())
        }
    }
}