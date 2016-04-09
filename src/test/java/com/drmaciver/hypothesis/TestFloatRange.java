package com.drmaciver.hypothesis;

import com.drmaciver.hypothesis.generators.BytesGenerator;
import com.drmaciver.hypothesis.generators.DataGenerator;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by david on 4/9/16.
 */
public class TestFloatRange {
    @Rule
    public final TestDataRule data = new TestDataRule();

    public final DataGenerator<byte[]> FLOATBYTES = new BytesGenerator(4);
    public final DataGenerator<Float> FLOAT01 = new FloatRangeGenerator(0, 1);


    @Test
    public void testDrawIsStable() {
        byte[] bytes = data.draw(FLOATBYTES);
        float x = new TestDataForBuffer(bytes).draw(FLOAT01);
        float y = new TestDataForBuffer(bytes).draw(FLOAT01);
        assertEquals(x, y, 0.0);
    }

    @Test
    public void testDrawTrivial() {
        float x = data.draw(FLOAT01);
        assertTrue(x >= 0.0);
        assertTrue(x <= 1.0);
    }

    @Test
    public void testDrawTrivialOtherRange() {
        float x = data.draw(new FloatRangeGenerator(10, 12));
        assertTrue(x >= 10.0);
        assertTrue(x <= 12.0);
    }
}
