package com.example.usercompose.data.network


import com.example.usercompose.screens.entity.Name
import com.example.usercompose.screens.entity.User
import com.google.gson.annotations.SerializedName

data class UserNetwork(
     val gender:String,
     @SerializedName("name")
     val nameNetwork:NameNetwork
 )
data class NameNetwork(
    val title:String,
    val first:String,
    val last:String
)

fun User.toUserNetwork(): UserNetwork {
    return UserNetwork(
        gender= this.gender,
        nameNetwork = this.name.toNameNetwork()
    )
}
fun UserNetwork.toUser(): User {
    return User(
        gender= this.gender,
        name = this.nameNetwork.toName()
    )
}

fun NameNetwork.toName():Name{
    return Name(
        title = this.title,
        first= this.first,
        last=this.last
    )
}
fun Name.toNameNetwork():NameNetwork {
    return NameNetwork(
        title = this.title,
        first= this.first,
        last=this.last
    )

}



