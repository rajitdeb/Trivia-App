package com.rajit.triviaapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rajit.triviaapp.databinding.FragmentQuizBinding
import com.rajit.triviaapp.viewmodel.MainViewModel

/**
 * This is @QuizFragment
 * Here, we will show all the quiz questions, and users can answer them.
 */
class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get(): FragmentQuizBinding = _binding!!

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuizBinding.inflate(inflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.playerNameTv.text = "Hi, ${mainViewModel.playerName}!"

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}