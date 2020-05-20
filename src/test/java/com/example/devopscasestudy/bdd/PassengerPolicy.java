package com.example.devopscasestudy.bdd;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PassengerPolicy {
private Flight economyFlight;
private Flight businessFlight;
private Passenger mike;
private Passenger john;

@Given("^there is an economy flight$")
public void thereIsAnEconomyFlight() throws Throwable {
economyFlight = new EconomyFlight("1");
}

@When("^we have a usual passenger$")
public void weHaveAUsualPassenger() throws Throwable {
mike = new Passenger("Mike", false);
}

@Then("^you can add and remove him from an economy flight$")
public void youCanAddAndRemoveHimFromAnEconomyFlight() throws Throwable {
assertAll("Verify all conditions for a usual passenger and an economy flight",
() -> assertEquals("1", economyFlight.getId()),
() -> assertEquals(true, economyFlight.addPassenger(mike)),
() -> assertEquals(1, economyFlight.getPassengersList().size()),
() -> assertEquals("Mike", economyFlight.getPassengersList().get(0).getName()),
() -> assertEquals(true, economyFlight.removePassenger(mike)),
() -> assertEquals(0, economyFlight.getPassengersList().size())
);
}

@When("^we have a VIP passenger$")
public void weHaveAVIPPassenger() throws Throwable {
john = new Passenger("John", true);
}

@Then("^you can add him but cannot remove him from an economy flight$")
public void youCanAddHimButCannotRemoveHimFromAnEconomyFlight() throws Throwable {
assertAll("Verify all conditions for a VIP passenger and an economy flight",
() -> assertEquals("1", economyFlight.getId()),
() -> assertEquals(true, economyFlight.addPassenger(john)),
() -> assertEquals(1, economyFlight.getPassengersList().size()),
() -> assertEquals("John", economyFlight.getPassengersList().get(0).getName()),
() -> assertEquals(false, economyFlight.removePassenger(john)),
() -> assertEquals(1, economyFlight.getPassengersList().size())
);
}

@Given("^there is an business flight$")
public void thereIsAnBusinessFlight() throws Throwable {
businessFlight = new BusinessFlight("2");
}

@Then("^you cannot add or remove him from a business flight$")
public void youCannotAddOrRemoveHimFromABusinessFlight() throws Throwable {
assertAll("Verify all conditions for a usual passenger and a business flight",
() -> assertEquals(false, businessFlight.addPassenger(mike)),
() -> assertEquals(0, businessFlight.getPassengersList().size()),
() -> assertEquals(false, businessFlight.removePassenger(mike)),
() -> assertEquals(0, businessFlight.getPassengersList().size())
);
}

@Then("^you can add him but cannot remove him from a business flight$")
public void youCanAddHimButCannotRemoveHimFromABusinessFlight() throws Throwable {
assertAll("Verify all conditions for a VIP passenger and a business flight",
() -> assertEquals(true, businessFlight.addPassenger(john)),
() -> assertEquals(1, businessFlight.getPassengersList().size()),
() -> assertEquals(false, businessFlight.removePassenger(john)),
() -> assertEquals(1, businessFlight.getPassengersList().size())
);
}
}