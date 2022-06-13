package `in`.lemreh.itw_assign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import `in`.lemreh.itw_assign.ui.main.MainFragment
import `in`.lemreh.itw_assign.ui.main.MainViewModel
import `in`.lemreh.itw_assign.ui.main.commits.CommitListFragment
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    /**
     * [commitViewModel] is declared here to have a greater scope and be shared between fragments
     */
    private val commitViewModel  by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}