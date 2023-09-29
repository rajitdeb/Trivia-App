package com.rajit.triviaapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.rajit.triviaapp.R
import com.rajit.triviaapp.databinding.FragmentWelcomeBinding
import com.rajit.triviaapp.viewmodel.MainViewModel

/**
 * This is @WelcomeFragment
 * Here, the user enters a player name before proceeding to the Quiz
 */
class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get(): FragmentWelcomeBinding = _binding!!

    private val mainViewModel: MainViewModel by lazy { MainViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        binding.playerNameEdt.editText?.addTextChangedListener {
            if(it?.trim()?.isNotEmpty() == true) {
                binding.playerNameEdt.error = ""
            }
        }

        binding.playQuizBtn.setOnClickListener {
            val playerName = binding.playerNameEdt.editText?.text.toString()
            val isSuccess = mainViewModel.setPlayerName(playerName)
            if(!isSuccess) {
                binding.playerNameEdt.error = "Please enter a valid name"
            } else {
                Toast.makeText(
                    requireContext(),
                    "Success! Starting Quiz",
                    Toast.LENGTH_SHORT
                ).show()

                // Move to Quiz Category Fragment
                findNavController().navigate(R.id.action_welcomeFragment_to_selectCategoryFragment)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}