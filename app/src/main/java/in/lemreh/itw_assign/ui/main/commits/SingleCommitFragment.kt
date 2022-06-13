package `in`.lemreh.itw_assign.ui.main.commits

import `in`.lemreh.itw_assign.databinding.SingleCommitFragmentBinding
import `in`.lemreh.itw_assign.ui.main.MainViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SingleCommitFragment : Fragment() {


    private val commitViewModel : MainViewModel by activityViewModels()
    private val navArgs : SingleCommitFragmentArgs by navArgs()
    private lateinit var root : SingleCommitFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return SingleCommitFragmentBinding.inflate(inflater).apply {
            this.sharedSha = commitViewModel.sharedSha
            this.navBack.setOnClickListener {
                findNavController().popBackStack()
            }
            this@SingleCommitFragment.root = this
            this@SingleCommitFragment.loadCommit(this)

        }.root
    }

    private fun loadCommit(root : SingleCommitFragmentBinding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                commitViewModel.loadSingleCommit(navArgs.ref).collectLatest {
                    it?.let {
                        root.commit = it
                        if (root.hasPendingBindings()) {
                            root.executePendingBindings()
                        }
                    }
                }

            }
        }
    }

}