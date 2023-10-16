package com.brandon.running_app.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.brandon.running_app.R
import com.brandon.running_app.ui.viewmodels.MainViewModel
import com.brandon.running_app.ui.viewmodels.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint  // 안드로이드 구성 요소 내부에 injection을 하는 경우 선언되어야 함
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val viewModel : StatisticsViewModel by viewModels()  // dagger Hilt will inject this

}