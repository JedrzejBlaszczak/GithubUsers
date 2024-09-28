import com.jedrzejblaszczak.githubusers.repository.UserRepository
import com.jedrzejblaszczak.githubusers.ui.users.details.UserDetailsModel
import com.jedrzejblaszczak.githubusers.ui.users.details.UserModelToUserDetailsModelConverter
import com.jedrzejblaszczak.githubusers.ui.users.usecase.GetUserDetailsByIdUseCase
import com.jedrzejblaszczak.githubusers.userDetailsModel
import com.jedrzejblaszczak.githubusers.userModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserDetailsByIdUseCaseTest {

    private val userRepository: UserRepository = mockk()
    private val userModelToUserDetailsModelConverter: UserModelToUserDetailsModelConverter = mockk()

    private val useCase =
        GetUserDetailsByIdUseCase(userRepository, userModelToUserDetailsModelConverter)

    @Test
    fun `GIVEN valid user WHEN invoke THEN usecase returns converted model`() = runTest {
        // GIVEN
        val userModel = userModel(id = EXAMPLE_USER_ID)
        val userDetailsModel = userDetailsModel(id = EXAMPLE_USER_ID)
        coEvery { userRepository.getUserById(EXAMPLE_USER_ID) } returns flow { emit(userModel) }
        coEvery { userModelToUserDetailsModelConverter(userModel) } returns userDetailsModel

        // WHEN
        val result: Flow<UserDetailsModel?> = useCase(EXAMPLE_USER_ID)

        // THEN
        assertEquals(userDetailsModel, result)
    }

    @Test(expected = IllegalStateException::class)
    fun `invoke use case throws IllegalStateException when user does not exist`() = runTest {
        // GIVEN
        coEvery { userRepository.getUserById(EXAMPLE_USER_ID) } returns flow { emit(null) }

        // WHEN
        val result = useCase(EXAMPLE_USER_ID).toList()

        // THEN
        assert(result is IllegalStateException)
    }

    companion object {
        const val EXAMPLE_USER_ID = 1
    }
}
