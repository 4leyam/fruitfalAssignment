package `in`.lemreh.itw_assign.service.repository

import `in`.lemreh.itw_assign.AppUtil
import `in`.lemreh.itw_assign.service.FruitService
import `in`.lemreh.itw_assign.service.dto.CommitListItemDTO
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.*
import retrofit2.awaitResponse

class CommitPagingSource (private val gitHubService : FruitService ,
                          private val owner : String ,
                          private val repoName : String) : PagingSource<Int , CommitListItemDTO>() {


    private var currentPage : Int = 1


    override fun getRefreshKey(state: PagingState<Int, CommitListItemDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    /**
     * Loading API for [PagingSource].
     *
     * Implement this method to trigger your async load (e.g. from database or network).
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommitListItemDTO> {

        try {

            // Start refresh at page 1 if undefined.
            currentPage = params.key ?:1
            val queryString : Map<String , String> = mapOf(Pair("page" , "$currentPage"))
            Log.e(AppUtil.TAG, "load: loading index $currentPage")


            val response = withContext(Dispatchers.IO) {
                async {
                    this@CommitPagingSource.gitHubService.getCommitList(
                        this@CommitPagingSource.owner ,
                        this@CommitPagingSource.repoName ,
                        queryString).awaitResponse()
                }
            }.await()

            if (response.isSuccessful) {
                response.body()?.let {

                    return LoadResult.Page(
                        data = it.asList() ,
                        nextKey = ++currentPage ,
                        prevKey = null
                    )

                }

                Log.e(AppUtil.TAG, "load: Failed to load commit list")
                return LoadResult.Error(Exception("failed to fetch data and we still don't know why"))
            } else {
                Log.e(AppUtil.TAG, "load: Failed to load commit list $response")
                return LoadResult.Error(Exception("failed to fetch data"))
            }



        } catch (e : Exception) {
            Log.e(AppUtil.TAG, "load: Failed to load commit list" , e)
            return LoadResult.Error(e)
        }

    }


}