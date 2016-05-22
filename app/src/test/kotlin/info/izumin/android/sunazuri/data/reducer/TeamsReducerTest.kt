package info.izumin.android.sunazuri.data.reducer

import info.izumin.android.sunazuri.data.action.team.AddTeamsAction
import info.izumin.android.sunazuri.domain.entity.Team
import info.izumin.android.sunazuri.domain.model.Teams
import org.junit.Before
import org.junit.Test
import kotlin.test.expect

/**
 * Created by izumin on 5/22/2016 AD.
 */
class TeamsReducerTest {

    companion object {
        val TEAM1_NAME = "team1"
        val TEAM1 = Team(
                TEAM1_NAME,
                "open",
                "test team 1",
                "http://example.com/test_team1.png",
                1
        )

        val TEAM2_NAME = "team2"
        val TEAM2 = Team(
                TEAM2_NAME,
                "open",
                "test team 2",
                "http://example.com/test_team2.png",
                1
        )
    }

    val reducer = TeamsReducer()

    lateinit var teams: Teams

    @Before
    fun setUp() {
        teams = Teams()
    }

    @Test
    fun testAddTeam() {
        val addedTeams = Teams()
        addedTeams.add(TEAM1)
        val value = AddTeamsAction.RequsetValue(addedTeams)
        val action = AddTeamsAction()
        action.value = value

        val newState = reducer.add(teams, action)

        expect(1, { newState.count() })
    }

    @Test
    fun testAddTeams() {
        val addedTeams = Teams()
        addedTeams.add(TEAM1)
        addedTeams.add(TEAM2)
        val value = AddTeamsAction.RequsetValue(addedTeams)
        val action = AddTeamsAction()
        action.value = value

        val newState = reducer.add(teams, action)

        expect(2, { newState.count() })
    }

    @Test
    fun testAddExistedTeam() {
        teams.add(TEAM1)
        val addedTeams = Teams()
        addedTeams.add(Team(TEAM1_NAME, "close", "new test team 1", "http://example.com/test_team1.png", 1))
        val value = AddTeamsAction.RequsetValue(addedTeams)
        val action = AddTeamsAction()
        action.value = value

        val newState = reducer.add(teams, action)

        expect(1, { newState.count() })
    }

    @Test
    fun testAddExistedTeamAndNotExistedTeam() {
        teams.add(TEAM1)
        val addedTeams = Teams()
        addedTeams.add(Team(TEAM1_NAME, "close", "new test team 1", "http://example.com/test_team1.png", 1))
        addedTeams.add(TEAM2)
        val value = AddTeamsAction.RequsetValue(addedTeams)
        val action = AddTeamsAction()
        action.value = value

        val newState = reducer.add(teams, action)

        expect(2, { newState.count() })
    }
}