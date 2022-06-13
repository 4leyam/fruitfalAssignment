package `in`.lemreh.itw_assign.service.dto

data class CommitListItemDTO(val commit : Commit?,
                             var sha : String) {
    constructor() : this(null , "") {}
}

// commit info
data class Commit(val author : UserInfo?,
                  val committer : UserInfo?,
                  val message : String) {
    constructor() : this(null , null , "")
}
data class UserInfo(val name : String,
                    val email : String,
                    val date : String) {
    constructor() : this("" , "" , "")
}
//end commit info
