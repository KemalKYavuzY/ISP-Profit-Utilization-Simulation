# ISP Simulation Project

This project simulates an Internet Service Provider (ISP) business in a town. The ISP provides internet services to different types of customers, including Casual users, Resellers, and Streamers. The simulation models the behavior of these customers and the ISP's decisions based on the current state of the town.

## Classes

### ISP Package

- `Town`: Represents the town where the ISP operates. It contains methods for initializing the town grid and updating it based on the simulation rules.
- `TownCell`: Represents a cell in the town grid. It is an abstract class extended by various cell types such as Casual, Reseller, Streamer, Empty, and Outage.
- `State`: Enumerates the possible states of a cell (Casual, Reseller, Streamer, Empty, Outage).
- Other specific cell classes (`Casual`, `Reseller`, `Streamer`, `Empty`, `Outage`): Implement the behavior of different types of customers and cells in the town.
- `ISPBusinessTest`: Contains JUnit tests for the `ISPBusiness` class, which manages the overall ISP business logic.
- `CasualTest`, `ResellerTest`, `StreamerTest`, `EmptyTest`, `OutageTest`: Contains JUnit tests for the specific cell classes.

## Running the Simulation

To run the simulation, execute the `ISPBusiness` class, which contains the main method. You can choose to populate the town grid either randomly or from a file. Follow the instructions provided by the program.

## Running Tests

You can run the JUnit tests to ensure the correctness of the implementation. Simply execute the test classes in your preferred IDE or build tool.

## Cell Update Rules

Each month, the state of each cell in the town grid can change based on its current state and the state of its neighbors. The rules for updating the cells are as follows:

1. **Casual (C)**: If the current cell is occupied by a casual user, it may change to an outage state if there is a reseller in its neighborhood, or it may become a streamer if there is a streamer neighbor.
2. **Streamer (S)**: A streamer cell becomes empty if there is a reseller in its neighborhood or if there is an outage in the neighborhood.
3. **Reseller (R)**: A reseller cell may become empty if there are few casual users in the neighborhood or if there are many empty cells in the neighborhood.
4. **Outage (O)**: An outage cell becomes empty, indicating that internet access is restored.
5. **Empty (E)**: An empty cell may become a casual user if certain conditions are met. Otherwise, it may become a reseller.
