package it.polimi.ingsw.PSP33.controller.rules.tools;

import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Gods;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DataControlTest {

    @Test
    public void controlInput() {

        DataBuffer dataBuffer = new DataBuffer();

        List<Coord> sample = new ArrayList<>();
        sample.add(new Coord(1,1));
        sample.add(new Coord(1,2));
        sample.add(new Coord(1,3));

        List<Coord> sample2 = new ArrayList<>();
        sample2.add(new Coord(2,1));
        sample2.add(new Coord(2,2));
        sample2.add(new Coord(2,3));

        dataBuffer.setCoordList(sample);
        dataBuffer.setCoordListGods(sample2);

        Coord coord = new Coord(2,1);
        Coord coord1 = new Coord(3,4);
        Coord coord2 = new Coord(1,1);

        assertTrue(DataControl.controlInput(coord, dataBuffer));
        assertFalse(DataControl.controlInput(coord1, dataBuffer));
        assertTrue(DataControl.controlInput(coord2, dataBuffer));
    }

    @Test
    public void exclusiveList() {
        List<Coord> sample = new ArrayList<>();
        sample.add(new Coord(1,1));
        sample.add(new Coord(1,2));
        sample.add(new Coord(1,3));

        List<Coord> sample2 = new ArrayList<>();
        sample2.add(new Coord(2,1));
        sample2.add(new Coord(2,2));
        sample2.add(new Coord(2,3));

        List<Coord> sample3 = new ArrayList<>();
        sample3.add(new Coord(1,1));
        sample3.add(new Coord(2,2));
        sample3.add(new Coord(3,3));

        assertTrue(DataControl.exclusiveList(sample,sample2));
        assertFalse(DataControl.exclusiveList(sample2,sample3));
        assertFalse(DataControl.exclusiveList(sample,sample3));
    }

    @Test
    public void checkStart() {
        assertFalse(DataControl.checkStart(Gods.APOLLO));
        assertFalse(DataControl.checkStart(Gods.ARTEMIS));
        assertFalse(DataControl.checkStart(Gods.ATHENA));
        assertFalse(DataControl.checkStart(Gods.ATLAS));
        assertFalse(DataControl.checkStart(Gods.DEMETER));
        assertFalse(DataControl.checkStart(Gods.HEPHAESTUS));
        assertFalse(DataControl.checkStart(Gods.MINOTAUR));
        assertFalse(DataControl.checkStart(Gods.PAN));

        assertTrue(DataControl.checkStart(Gods.PROMETHEUS));

        assertFalse(DataControl.checkStart(Gods.NOGOD));
    }

    @Test
    public void checkBuild() {
        assertFalse(DataControl.checkBuild(Gods.APOLLO));
        assertFalse(DataControl.checkBuild(Gods.ARTEMIS));
        assertFalse(DataControl.checkBuild(Gods.ATHENA));

        assertTrue(DataControl.checkBuild(Gods.ATLAS));

        assertFalse(DataControl.checkBuild(Gods.DEMETER));
        assertFalse(DataControl.checkBuild(Gods.HEPHAESTUS));
        assertFalse(DataControl.checkBuild(Gods.MINOTAUR));
        assertFalse(DataControl.checkBuild(Gods.PAN));
        assertFalse(DataControl.checkBuild(Gods.PROMETHEUS));
        assertFalse(DataControl.checkBuild(Gods.NOGOD));
    }

    @Test
    public void limitReset() {
        assertFalse(DataControl.limitReset(Gods.ATHENA));
    }
}