package erik.chatinyan.ameriatest.data.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseUserList(

	@field:SerializedName("ResponseUserList")
	val responseUserList: List<UserListItem>
)