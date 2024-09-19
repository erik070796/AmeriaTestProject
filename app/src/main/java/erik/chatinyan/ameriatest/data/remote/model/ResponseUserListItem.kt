package erik.chatinyan.ameriatest.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserListItem(

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("id")
	val id: Long,
)