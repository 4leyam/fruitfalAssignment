package `in`.lemreh.itw_assign.ui.main.commits

import `in`.lemreh.itw_assign.databinding.CommitListFragmentBinding
import `in`.lemreh.itw_assign.ui.main.MainViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CommitListFragment : Fragment(){

    private val commitViewModel : MainViewModel by activityViewModels()
    private var adapter: CommitListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return CommitListFragmentBinding.inflate(inflater).apply {

            if(adapter == null) {

                this@CommitListFragment.adapter = CommitListAdapter {
                    commitViewModel.sharedSha = it.sha.substring(IntRange(0 , 7))
                    val toSingleCommit = CommitListFragmentDirections
                        .actionCommitListFragmentToSingleCommitFragment2(it.sha)
                    findNavController().navigate(toSingleCommit)
                }

                this@CommitListFragment.lifecycleScope.launch {
                    commitViewModel.loadCommits().collectLatest {
                        this@CommitListFragment.adapter?.submitData(it)
                    }
                }


            }

            this.commitList.adapter = this@CommitListFragment.adapter

            if (hasPendingBindings()) {
                executePendingBindings()
            }

        }.root
    }


}