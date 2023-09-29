package com.rajit.triviaapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rajit.triviaapp.databinding.FragmentSelectGameDifficultyBinding

/**
 * This is @SelectGameDifficultyFragment
 * Here, the user chooses a difficulty level for the quiz based on which questions would be fetched from API
 * Also, taking @selectedCategory accepted as argument by @SelectGameDifficultyFragment
 */
class SelectGameDifficultyFragment : Fragment() {

    private var _binding: FragmentSelectGameDifficultyBinding? = null
    private val binding get(): FragmentSelectGameDifficultyBinding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelectGameDifficultyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}