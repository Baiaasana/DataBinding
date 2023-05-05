package com.example.databinding.fragment.modal.bottom_sheet

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.databinding.R
import com.example.databinding.databinding.FragmentBottomSheetBinding
import com.example.keyboardevent.InsetsWithKeyboardAnimationCallback
import com.example.keyboardevent.InsetsWithKeyboardCallback
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("log", "onCreate")
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        Log.d("log", "onCreateView")
        return binding.root
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        Log.d("log", "onAttach")
//        requireActivity().onBackPressedDispatcher.addCallback(this,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    if (isEnabled) {
//                        if ((childFragmentManager.findFragmentById(R.id.nav_host_fragment_modal) as NavHostFragment).navController.navigateUp()) {
//                            binding.btnBack.animate().apply {
//                                duration = 500
//                                rotationBy(-90f)
//                            }
//                        } else {
//                            dismiss()
//                        }
//                    } else requireActivity().onBackPressed()
//                }
//            })
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("log", "onViewCreated")
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false);
        setUpNavController()
        listeners()

        val insetsWithKeyboardCallback = InsetsWithKeyboardCallback(requireActivity().window)
        ViewCompat.setOnApplyWindowInsetsListener(requireView(), insetsWithKeyboardCallback)
        ViewCompat.setWindowInsetsAnimationCallback(requireView(), insetsWithKeyboardCallback)

        val insetsWithKeyboardAnimationCallback = InsetsWithKeyboardAnimationCallback(binding.rootId)
        ViewCompat.setWindowInsetsAnimationCallback(binding.rootId, insetsWithKeyboardAnimationCallback)
        showSoftKeyboard(binding.etName)
    }

    private fun showSoftKeyboard(view: View) {
        Log.d("log", "showSoftKeyboard")
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        binding.etName.requestFocus()
        imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun showSoftKeyboard2(view: View) {
        binding.etName.requestFocus()
        Handler().postDelayed({
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }, 1300)
    }


    private fun setUpKeyboard() {
        Log.d("log", "setUpKeyboard")
        val insetsWithKeyboardCallback = InsetsWithKeyboardCallback(requireActivity().window)
        ViewCompat.setOnApplyWindowInsetsListener(binding.rootId, insetsWithKeyboardCallback)
        ViewCompat.setWindowInsetsAnimationCallback(binding.rootId, insetsWithKeyboardCallback)

        val insetsWithKeyboardAnimationCallback = InsetsWithKeyboardAnimationCallback(binding.etName)
        ViewCompat.setWindowInsetsAnimationCallback(binding.etName, insetsWithKeyboardAnimationCallback)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("log", "onCreateDialog")
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
//                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
                behaviour.skipCollapsed = true

            }
        }

        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    private fun setUpNavController() {
        val navHostFragment =
            childFragmentManager.findFragmentById(com.example.databinding.R.id.nav_host_fragment_modal) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph =
            navController.navInflater.inflate(com.example.databinding.R.navigation.bottom_sheet_nav_graph)
        navController.graph = navGraph

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == com.example.databinding.R.id.editFragment) {
                binding.btnBack.animate().apply {
                    duration = 500
                    rotationBy(90f)
                }
            }
        }
    }


    private fun listeners() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }


    }

    override fun onDestroyView() {
        Log.d("log", "onDestroyView")
        super.onDestroyView()
        _binding = null
    }
}
