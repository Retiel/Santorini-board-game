package it.polimi.ingsw.PSP33.controller.rules.tools;

import it.polimi.ingsw.PSP33.utils.Coord;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DataBufferTest {

    private DataBuffer dataBuffer;

    @Before
    public void setUp(){
        dataBuffer = new DataBuffer();
    }

    @Test
    public void setDataBuffer_correctOutput() {

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

        assertEquals(3, dataBuffer.getCoordList().size());
        assertEquals(1, dataBuffer.getCoordList().get(1).getX());

        assertEquals(3, dataBuffer.getCoordListGods().size());
        assertEquals(2, dataBuffer.getCoordListGods().get(1).getX());

    }
}