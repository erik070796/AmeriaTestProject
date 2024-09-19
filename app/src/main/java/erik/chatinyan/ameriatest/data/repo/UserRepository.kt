package erik.chatinyan.ameriatest.data.repo

import erik.chatinyan.ameriatest.data.remote.ApiInterface
import erik.chatinyan.ameriatest.data.remote.ResultWrapper
import erik.chatinyan.ameriatest.data.remote.model.UserDetails
import erik.chatinyan.ameriatest.data.remote.model.UserListItem
import erik.chatinyan.ameriatest.data.remote.safeApiCall

class UserRepository(private val apiInterface: ApiInterface,
) {
    suspend fun getUserList(): ResultWrapper<List<UserListItem>> {
        return safeApiCall {
            apiInterface.getUserList()
        }
    }

    suspend fun getUserDetails(login:String): ResultWrapper<UserDetails> {
        return safeApiCall {
            apiInterface.getUserDetails(login)
        }
    }

}