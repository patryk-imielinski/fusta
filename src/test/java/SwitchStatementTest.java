import model.Animal;
import model.Point;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pl.pimielinski.fusta.fuswitch.Switch;
import pl.pimielinski.fusta.fuswitch.SwitchStatement;

import java.util.Optional;

import static org.junit.Assert.*;

public class SwitchStatementTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void test_switchStatementWithEnum() {

        Animal animal = Animal.DUCK;
        SwitchStatement<Animal, String> aSwitch = Switch.of(animal);

        Optional<String> result = aSwitch
                .singleCase(arg -> "Is a doggo", Animal.DOG)
                .singleCase(arg -> "Bulp bulp", Animal.TURTLE, Animal.FISH, Animal.SHARK)
                .singleCase(arg -> "Tortoises ftw", Animal.TURTLE)
                .singleCase(arg -> "Meow!", Animal.CAT)
                .singleCase(arg -> "A friggin poultry", Animal.CHICKEN, Animal.DUCK, Animal.GOOSE)
                .singleCase(arg -> "Iii-haa!", Animal.HORSE)
                .singleCase(arg -> "Hoo hoo!", Animal.OWL)
                .singleCase(arg -> "Fly\nOn your way\nLike an eagle...", Animal.EAGLE)
                .singleCase(arg -> "Sniff sniff", Animal.HAMSTER)
                .byDefault(arg -> "What is " + arg + "?");

        assertEquals(result.orElse("ERROR"), "A friggin poultry");
    }

    @Test
    public void test_switchStatementWithObject() {

        Point point = new Point(5, 12);
        SwitchStatement<Point, Integer> aSwitch = Switch.of(point);

        Optional<Integer> result = aSwitch
                .singleCase(Point::getX, Point.ZERO_ZERO)
                .singleCase(Point::getY, new Point(5, 12), new Point(7, 3))
                .byDefault(arg -> -1);
        int res = result.orElse(Integer.MIN_VALUE);

        assertEquals(res, 12);
    }

    @Test
    public void test_switchStatementWithNullArgument() {

        exception.expect(NullPointerException.class);
        exception.expectMessage("An 'argument' can not be null");

        SwitchStatement<String, String> aSwitch = Switch.of(null);
    }

    @Test
    public void test_switchStatementWithNullCaseAction() {

        exception.expect(NullPointerException.class);
        exception.expectMessage("An 'action' can not be null");

        SwitchStatement<String, String> aSwitch = Switch.of("");

        aSwitch
                .singleCase(null, "action");
    }

    @Test
    public void test_switchStatementWithNullCaseCondition() {

        exception.expect(NullPointerException.class);
        exception.expectMessage("A 'firstCondition' can not be null");

        SwitchStatement<String, String> aSwitch = Switch.of("");

        aSwitch
                .singleCase(String::toUpperCase, null);
    }
}
