package com.rajit.triviaapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rajit.triviaapp.databinding.FragmentResultBinding
import com.rajit.triviaapp.viewmodel.MainViewModel

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get(): FragmentResultBinding = _binding!!

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        val score = mainViewModel.score

        if(score >= 7) {
            binding.scoreTv.text = "Congratulations!\nYou scored $score/15"
        } else {
            binding.scoreTv.text = "You need to work more on your Knowledge. You scored: $score"
        }

        return binding.root
    }

}