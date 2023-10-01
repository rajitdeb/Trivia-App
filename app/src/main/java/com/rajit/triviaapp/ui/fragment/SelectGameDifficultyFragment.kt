package com.rajit.triviaapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rajit.triviaapp.R
import com.rajit.triviaapp.data.enum.GameDifficulty
import com.rajit.triviaapp.data.network.model.AllQuestions
import com.rajit.triviaapp.databinding.FragmentSelectGameDifficultyBinding
import com.rajit.triviaapp.util.Utils
import com.rajit.triviaapp.viewmodel.MainViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

private const val TAG = "SelectGameDifficulty"

/**
 * This is @SelectGameDifficultyFragment
 * Here, the user chooses a difficulty level for the quiz based on which questions would be fetched from API
 * Also, taking @selectedCategory accepted as argument by @SelectGameDifficultyFragment
 */
class SelectGameDifficultyFragment : Fragment() {

    private var _binding: FragmentSelectGameDifficultyBinding? = null
    private val binding get(): FragmentSelectGameDifficultyBinding = _binding!!

    private val args: SelectGameDifficultyFragmentArgs by navArgs()

    private val mainViewModel: MainViewModel by lazy { MainViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelectGameDifficultyBinding.inflate(inflater, container, false)

        binding.gameDifficultyChip.setOnCheckedStateChangeListener { _, checkedIds ->
            val value = when (checkedIds[0]) {
                R.id.easyChip -> GameDifficulty.EASY
                R.id.mediumChip -> GameDifficulty.MEDIUM
                R.id.hardChip -> GameDifficulty.HARD
                else -> GameDifficulty.EASY
            }

            mainViewModel.setSelectedGameDifficultyLevel(value)

            fetchQuestionsFromServer(
                args.selectedCategory.id,
                mainViewModel.getSelectedGameDifficultyLevel()
            )
        }

        return binding.root
    }

    /**
     * Fetch Questions from Server based on the Quiz Category and Difficulty Level selected
     * @param categoryId is the id of the category selected by the user
     * @param selectedDifficulty is the GameDifficulty Level selected by the User
     */
    private fun fetchQuestionsFromServer(categoryId: Int, selectedDifficulty: GameDifficulty) {
        mainViewModel
            .getQuestions(categoryId, Utils.convertGameDifficultyEnumToString(selectedDifficulty))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<AllQuestions> {
                override fun onSubscribe(d: Disposable) {
                    Toast.makeText(requireContext(), "Loading Quiz...", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "getQuestions: onSubscribe called")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "getQuestions: onError: ${e.message}")
                }

                override fun onComplete() {
                    Log.d(TAG, "getQuestions: onComplete called")
                    val isSuccess = mainViewModel.getResponseCode()

                    if (isSuccess) {
                        moveToQuizFragment(mainViewModel.getPlayerName())
                    }

                }

                override fun onNext(t: AllQuestions) {
                    Log.d(TAG, "getQuestions: onNext called")
                    if (t.responseCode == 0) {
                        mainViewModel.setResponseCode(true)
                        mainViewModel.setQuestions(t.questions)
                    } else {
                        mainViewModel.setResponseCode(false)
                        if (Utils.mapResponseCodeToMessageString(t.responseCode) != "Success") {
                            Toast.makeText(
                                requireContext(),
                                Utils.mapResponseCodeToMessageString(t.responseCode),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            })
    }

    /**
     * On Successfully getting all the questions from the Server, move to Quiz Fragment
     * @param playerName is the playerName we get from the MainViewModel and pass it as parameter
     */
    private fun moveToQuizFragment(playerName: String) {
        val directions = SelectGameDifficultyFragmentDirections
            .actionSelectGameDifficultyFragmentToQuizFragment(playerName)
        findNavController().navigate(directions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}