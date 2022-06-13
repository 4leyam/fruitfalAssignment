package `in`.lemreh.itw_assign.ui.main

import `in`.lemreh.itw_assign.service.FruitService
import `in`.lemreh.itw_assign.service.ServiceContainer
import `in`.lemreh.itw_assign.service.dto.CommitListItemDTO
import `in`.lemreh.itw_assign.service.dto.FullCommitDTO
import `in`.lemreh.itw_assign.service.repository.CommitPagingSource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.awaitResponse

class MainViewModel : ViewModel() {

    private var commitService: FruitService = ServiceContainer
        .createService(FruitService::class.java)

    var sharedSha : String = ""


    fun loadSingleCommit(ref : String) : Flow<FullCommitDTO?> {
        return flow {
            val response = this@MainViewModel.commitService.getSingleCommit("square" ,
                "retrofit" , ref).awaitResponse()
            emit(response.body())
        }
    }

    fun loadCommits(): Flow<PagingData<CommitListItemDTO>> {
        return Pager(
            config = PagingConfig(pageSize = 30) ,
            initialKey = null ,
            pagingSourceFactory = {
                CommitPagingSource(commitService , "square" , "retrofit")
            }).flow.cachedIn(viewModelScope)
    }

}