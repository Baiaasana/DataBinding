package com.example.databinding.fragment.modal.nestedFragments.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.databinding.R
import com.example.databinding.databinding.FragmentEditBinding
import com.example.databinding.fragment.modal.bottom_sheet.BottomSheetViewModel
import com.example.databinding.fragment.modal.nestedFragments.info.InfoFragmentDirections

class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BottomSheetViewModel by navGraphViewModels(R.id.bottom_sheet_nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
    }

    private fun listeners() {
        binding.btnChange.setOnClickListener {
            viewModel.setName(binding.etName.text.toString())
            requireActivity().onBackPressed()
//                findNavController().navigateUp()
        }
    }
}