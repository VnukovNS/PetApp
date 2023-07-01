package com.example.petapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.petapp.firstScreen.MainFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ShowFragment {

    // пока что не нужна, скорее всего в нее уйдет навигация
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            NavigationStrategy.ReplaceWOBackStack(MainFragment())
                .navigate(supportFragmentManager, R.id.container)
        }
    }

    // todo перенести навигацию
    override fun show(fragment: Fragment) =
        NavigationStrategy.Replace(fragment).navigate(supportFragmentManager, R.id.container)

}
