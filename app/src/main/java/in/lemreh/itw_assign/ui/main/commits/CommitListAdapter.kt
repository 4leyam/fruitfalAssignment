package `in`.lemreh.itw_assign.ui.main.commits

import `in`.lemreh.itw_assign.databinding.CommitListItemBinding
import `in`.lemreh.itw_assign.service.dto.CommitListItemDTO
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


class CommitListAdapter(private val handler : (it : CommitListItemDTO) -> Unit)
    : PagingDataAdapter<CommitListItemDTO, CommitViewHolder>(diff) {

    companion object {

        val diff = object : DiffUtil.ItemCallback<CommitListItemDTO>() {

            override fun areItemsTheSame(
                oldItem: CommitListItemDTO,
                newItem: CommitListItemDTO
            ): Boolean {
                return oldItem.sha == newItem.sha
            }


            override fun areContentsTheSame(
                oldItem: CommitListItemDTO,
                newItem: CommitListItemDTO
            ): Boolean {
                return oldItem == newItem
            }

        }

    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {

        val currentItem = super.getItem(position)
        if (currentItem != null) {

            val copy = currentItem.copy()
            copy.sha = currentItem.sha.substring(IntRange(0 , 7))
            holder.commitItem.itemDTO = copy
            holder.commitItem.root.setOnClickListener {
                this@CommitListAdapter.handler.invoke(currentItem)
            }

        } else {
            holder.commitItem.root.setOnClickListener(null)
        }

        if (holder.commitItem.hasPendingBindings()) {
            holder.commitItem.executePendingBindings()
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        return CommitViewHolder(CommitListItemBinding.inflate(
            LayoutInflater.from(parent.context) , parent , false
        ))
    }

}

class CommitViewHolder(val commitItem: CommitListItemBinding)
    : RecyclerView.ViewHolder(commitItem.root)