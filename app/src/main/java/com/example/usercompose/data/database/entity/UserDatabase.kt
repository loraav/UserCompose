package com.example.usercompose.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.usercompose.screens.entity.Name
import com.example.usercompose.screens.entity.User

@Entity(tableName = "User")
data class UserDatabase(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val gender: String,
    val title: String,
    val first: String,
    val last: String
)

fun User.toUserDatabase(): UserDatabase {
    return UserDatabase(
        id = 0,
        gender = this.gender,
        title = this.name.title,
        first = this.name.first,
        last = this.name.last
    )
}

fun UserDatabase.toUser(): User {
    return User(
        gender = this.gender,
        name = Name(
            title = this.title,
            first = this.first,
            last = this.last
        )
    )
}
