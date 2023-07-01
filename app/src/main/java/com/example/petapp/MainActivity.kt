package com.example.petapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.petapp.core.BaseActivity
import com.example.petapp.firstScreen.MainFragment

class MainActivity : BaseActivity<MainActivityViewModel>(), ShowFragment {
    override val viewModelClass: Class<MainActivityViewModel> = MainActivityViewModel::class.java

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
