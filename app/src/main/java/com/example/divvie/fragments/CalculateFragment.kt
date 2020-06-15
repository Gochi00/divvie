package com.example.divvie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.divvie.*

class CalculateFragment : Fragment() {
    companion object {
        fun newInstance() = CalculateFragment()
    }
    private lateinit var viewModel: DivvieViewModel
    private lateinit var calculateButton: Button
    private lateinit var backButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = inflater.inflate(R.layout.calculate_fragment, container, false)
        calculateButton = fragment.findViewById(R.id.calculate)
        backButton = fragment.findViewById(R.id.back_button)
        return fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(DivvieViewModel::class.java)
        viewModel.onEvent(DivvieViewEvent.DisplayCalculateFragment)

        calculateButton.setOnClickListener {
            viewModel.onEvent(DivvieViewEvent.CalculateToResult)
            fragmentManager!!.beginTransaction().replace(
                R.id.info_fragment_layout,
                ResultFragment.newInstance()
            ).commit()
        }

        backButton.setOnClickListener {
            viewModel.onEvent(DivvieViewEvent.CalculateToItem)
            fragmentManager!!.beginTransaction().replace(
                R.id.info_fragment_layout, ItemFragment.newInstance()
            ).commit()
        }
    }
}