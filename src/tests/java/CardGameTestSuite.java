package tests.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    CardTest.class,
    CardDeckTest.class,
    PlayerTest.class,
    PlayerDeckTest.class,
    TableDeckTest.class,
    PlayerDiscardTest.class,
    CardGameTest.class,
    MockGameTest1.class
})
public class CardGameTestSuite {
}
