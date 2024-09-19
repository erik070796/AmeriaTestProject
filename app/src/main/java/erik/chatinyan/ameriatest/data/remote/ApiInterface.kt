package erik.chatinyan.ameriatest.data.remote

import erik.chatinyan.ameriatest.data.remote.model.UserDetails
import erik.chatinyan.ameriatest.data.remote.model.UserListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("users")
    suspend fun getUserList(
    ): Response<List<UserListItem>>

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String
    ): Response<UserDetails>
}