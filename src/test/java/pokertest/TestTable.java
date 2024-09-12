package pokertest;

import org.junit.Before;
import org.junit.Test;
import poker.Player;
import poker.TableImpl;

import static org.junit.Assert.assertEquals;

public class TestTable {

    private TableImpl table;

    @Before
    public void setUp() {
        // Initialize a new TableImpl before each test
        table = new TableImpl();
    }

    @Test
    public void testGetPlayerPosition() {
        // Create three players for the test
        Player player1 = new Player("Alice", 100);
        Player player2 = new Player("Bob", 100);
        Player player3 = new Player("Charlie", 100);

        // Add players to the table
        table.addPlayer(player1);
        table.addPlayer(player2);
        table.addPlayer(player3);

        // Set currentPlayerIndex to simulate dealer
        // Dealer is the first player
        setCurrentPlayerIndex(0);  // Set dealer to player1 (Alice)

        // Verify positions: player1 should be Dealer, player2 Small Blind, player3 Big Blind
        assertEquals("Dealer", table.getPlayerPosition(player1));
        assertEquals("Small Blind", table.getPlayerPosition(player2));
        assertEquals("Big Blind", table.getPlayerPosition(player3));
    }

    // Helper method to set the currentPlayerIndex in TableImpl for testing
    private void setCurrentPlayerIndex(int index) {
        try {
            // Use reflection to set the private currentPlayerIndex field in TableImpl
            java.lang.reflect.Field field = TableImpl.class.getDeclaredField("currentPlayerIndex");
            field.setAccessible(true);
            field.set(table, index);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set currentPlayerIndex", e);
        }
    }
}
