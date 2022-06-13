package `in`.lemreh.itw_assign.service.dto

//TODO make sure the date on the committer is in IST
data class FullCommitDTO(val commit : Commit? ,
                         val sha : String , val stats : Stats?) {
    constructor() : this(null , "" , null)
}

data class Stats(val additions : Int , val deletions : Int , val total : Int) {
    constructor() : this(0 ,  0 , 0)
}