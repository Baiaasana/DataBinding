package com.example.databinding.fragment.modal.bottom_sheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.databinding.R
import com.example.databinding.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        if (childFragmentManager.popBackStackImmediate()) {
                            Toast.makeText(requireContext(), "back", Toast.LENGTH_SHORT).show()
                        } else {
                            dismiss()
                            Toast.makeText(requireContext(), "dismiss", Toast.LENGTH_SHORT).show()
                        }
                    } else requireActivity().onBackPressed()
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()

//        val navHostFragment =
//            childFragmentManager.findFragmentById(R.id.nav_host_fragment_modal) as NavHostFragment
//        val navController = navHostFragment.navController
//        val navGraph = navController.navInflater.inflate(R.navigation.bottom_sheet_nav_graph)
//
//        navController.graph = navGraph

    }

    private fun listeners() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}