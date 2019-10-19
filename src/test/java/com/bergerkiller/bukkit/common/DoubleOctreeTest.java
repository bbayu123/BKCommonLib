package com.bergerkiller.bukkit.common;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.util.Vector;
import org.junit.Test;

import com.bergerkiller.bukkit.common.bases.IntVector3;
import com.bergerkiller.bukkit.common.collections.octree.DoubleOctree;
import com.bergerkiller.bukkit.common.collections.octree.DoubleOctreeIterator;
import com.bergerkiller.bukkit.common.utils.MathUtil;

public class DoubleOctreeTest {

    private void putDemoValues(DoubleOctree<String> tree) {
        tree.add(0.0, 0.0, 0.0, "A");
        tree.add(10.0, 5.5, -6.2, "B");
        tree.add(100.5, 50.5, 20.5, "C");
        tree.add(100.6, 50.6, 20.6, "D");
        tree.add(100.6, 50.7, 20.7, "E");
        tree.add(2147483647.0, 2147483647.0, 2147483647.0, "F");
        tree.add(2147483647.5, 2147483647.5, 2147483647.5, "G");
        tree.add(-2147483648.0, -2147483648.0, -2147483648.0, "H");
        tree.add(-2147483647.5, -2147483647.5, -2147483647.5, "I");
    }

    @Test
    public void testPutGet() {
        DoubleOctree<String> tree = new DoubleOctree<>();
        putDemoValues(tree);

        assertTreeGetEquals(tree, 0, 0, 0, "A");
        assertTreeGetEquals(tree, 10.0, 5.5, -6.2, "B");
        assertTreeGetEquals(tree, 100.5, 50.5, 20.5, "C");
        assertTreeGetEquals(tree, 100.6, 50.6, 20.6, "D");
        assertTreeGetEquals(tree, 100.6, 50.7, 20.7, "E");
        assertTreeGetEquals(tree, 2147483647.0, 2147483647.0, 2147483647.0, "F");
        assertTreeGetEquals(tree, 2147483647.5, 2147483647.5, 2147483647.5, "G");
        assertTreeGetEquals(tree, -2147483648.0, -2147483648.0, -2147483648.0, "H");
        assertTreeGetEquals(tree, -2147483647.5, -2147483647.5, -2147483647.5, "I");

        // Also check some failure cases, both outside any block and inside
        assertTreeGetEmpty(tree, 30.0, 30.0, 50.0);
        assertTreeGetEmpty(tree, 0.1, 0.0, 0.0);
        assertTreeGetEmpty(tree, 0.0, 0.1, 0.0);
        assertTreeGetEmpty(tree, 0.0, 0.0, 0.1);
    }

    @Test
    public void testContains() {
        DoubleOctree<String> tree = new DoubleOctree<>();
        putDemoValues(tree);

        assertTrue(tree.contains(0, 0, 0, "A"));
        assertTrue(tree.contains(10.0, 5.5, -6.2, "B"));
        assertTrue(tree.contains(100.5, 50.5, 20.5, "C"));
        assertTrue(tree.contains(100.6, 50.6, 20.6, "D"));
        assertTrue(tree.contains(100.6, 50.7, 20.7, "E"));
        assertTrue(tree.contains(2147483647.0, 2147483647.0, 2147483647.0, "F"));
        assertTrue(tree.contains(2147483647.5, 2147483647.5, 2147483647.5, "G"));
        assertTrue(tree.contains(-2147483648.0, -2147483648.0, -2147483648.0, "H"));
        assertTrue(tree.contains(-2147483647.5, -2147483647.5, -2147483647.5, "I"));

        // Also check some failure cases, both outside any block and inside, and value differences
        assertFalse(tree.contains(30.0, 30.0, 50.0, "A"));
        assertFalse(tree.contains(0.1, 0.0, 0.0, "A"));
        assertFalse(tree.contains(0.0, 0.1, 0.0, "A"));
        assertFalse(tree.contains(0.0, 0.0, 0.1, "A"));
        assertFalse(tree.contains(10.0, 5.5, -6.2, "A"));
        assertFalse(tree.contains(0, 0, 0, "B"));
    }

    @Test
    public void testRemove() {
        DoubleOctree<String> tree = new DoubleOctree<>();
        putDemoValues(tree);

        // Remove C, D should become head of chain and D and E should both remain
        assertTrue(tree.remove(100.5, 50.5, 20.5, "C"));
        assertTreeGetEmpty(tree, 100.5, 50.5, 20.5);
        assertFalse(tree.contains(100.5, 50.5, 20.5, "C"));
        assertTreeGetEquals(tree, 100.6, 50.6, 20.6, "D");
        assertTreeGetEquals(tree, 100.6, 50.7, 20.7, "E");

        // Remove E now, D should remain
        assertTrue(tree.remove(100.6, 50.7, 20.7, "E"));
        assertTreeGetEmpty(tree, 100.6, 50.7, 20.7);
        assertFalse(tree.contains(100.6, 50.7, 20.7, "E"));
        assertTreeGetEquals(tree, 100.6, 50.6, 20.6, "D");

        // Remove D, which should remove the entire 1x1x1 block
        // TODO: Verify block is removed in base Octree
        assertTrue(tree.remove(100.6, 50.6, 20.6, "D"));
        assertTreeGetEmpty(tree, 100.6, 50.6, 20.6);
        assertFalse(tree.contains(100.6, 50.6, 20.6, "D"));
    }

    @Test
    public void testIterator() {
        DoubleOctree<String> tree = new DoubleOctree<>();
        putDemoValues(tree);

        DoubleOctreeIterator<String> iter = tree.iterator();
        assertNext(iter, "A", 0.0, 0.0, 0.0);
        assertNext(iter, "C", 100.5, 50.5, 20.5);
        assertNext(iter, "D", 100.6, 50.6, 20.6);
        assertNext(iter, "E", 100.6, 50.7, 20.7);
        assertNext(iter, "F", 2147483647.0, 2147483647.0, 2147483647.0);
        assertNext(iter, "G", 2147483647.5, 2147483647.5, 2147483647.5);
        assertNext(iter, "B", 10.0, 5.5, -6.2);
        assertNext(iter, "H", -2147483648.0, -2147483648.0, -2147483648.0);
        assertNext(iter, "I", -2147483647.5, -2147483647.5, -2147483647.5);
        assertFalse(iter.hasNext());
    }

    @Test
    public void testValues() {
        DoubleOctree<String> tree = new DoubleOctree<>();
        putDemoValues(tree);

        Collection<String> values = tree.values();
        assertTrue(values.contains("A"));
        assertTrue(values.contains("B"));
        assertTrue(values.contains("C"));
        assertTrue(values.contains("D"));
        assertTrue(values.contains("E"));
        assertTrue(values.contains("F"));
        assertTrue(values.contains("G"));
        assertTrue(values.contains("H"));
        assertTrue(values.contains("I"));
    }
    
    @Test
    public void testCuboidIterator() {
        DoubleOctree<String> tree = new DoubleOctree<>();
        putDemoValues(tree);

        DoubleOctreeIterator<String> iter = tree.cuboid(new IntVector3(9, 4, -7), new IntVector3(200, 60, 40)).iterator();
        assertNext(iter, "C", 100.5, 50.5, 20.5);
        assertNext(iter, "D", 100.6, 50.6, 20.6);
        assertNext(iter, "E", 100.6, 50.7, 20.7);
        assertNext(iter, "B", 10.0, 5.5, -6.2);
        assertFalse(iter.hasNext());
    }

    @Test
    public void testBlockIterator() {
        DoubleOctree<String> tree = new DoubleOctree<>();
        putDemoValues(tree);

        DoubleOctreeIterator<String> iter = tree.block(new IntVector3(100, 50, 20)).iterator();
        assertNext(iter, "C", 100.5, 50.5, 20.5);
        assertNext(iter, "D", 100.6, 50.6, 20.6);
        assertNext(iter, "E", 100.6, 50.7, 20.7);
        assertFalse(iter.hasNext());
    }

    @Test
    public void testMoveSamePositionChangeValue() {
        // Create a chain at 10/10/10 with 4 values
        DoubleOctree<String> tree = new DoubleOctree<>();
        tree.add(10.0, 10.0, 10.0, "A");
        tree.add(10.5, 10.0, 10.0, "B");
        tree.add(10.5, 10.5, 10.0, "C");
        tree.add(10.5, 10.5, 10.5, "D");

        // Change the value of A to A_new
        assertTrue(tree.move(10.0, 10.0, 10.0, "A",
                             10.0, 10.0, 10.0, "A_new"));

        // Change the value of B to B_new
        assertTrue(tree.move(10.5, 10.0, 10.0, "B",
                             10.5, 10.0, 10.0, "B_new"));

        // Change the value of C to C_new
        assertTrue(tree.move(10.5, 10.5, 10.0, "C",
                             10.5, 10.5, 10.0, "C_new"));

        // Change the value of D to D_new
        assertTrue(tree.move(10.5, 10.5, 10.5, "D",
                             10.5, 10.5, 10.5, "D_new"));

        // Verify that changing a non-existant value fails
        assertFalse(tree.move(10.9, 10.9, 10.9, "A",
                              10.9, 10.9, 10.9, "FAIL"));

        // A was moved and should not be movable again
        assertFalse(tree.move(10.0, 10.0, 10.0, "A",
                              10.9, 10.9, 10.9, "B"));

        // Verify the tree contents after these changes
        assertTreeGetEquals(tree, 10.0, 10.0, 10.0, "A_new");
        assertTreeGetEquals(tree, 10.5, 10.0, 10.0, "B_new");
        assertTreeGetEquals(tree, 10.5, 10.5, 10.0, "C_new");
        assertTreeGetEquals(tree, 10.5, 10.5, 10.5, "D_new");
        assertFalse(tree.contains(10.9, 10.9, 10.9, "FAIL"));
    }

    @Test
    public void benchmarkMoveSameBlock() {
        DoubleOctree<String> tree = new DoubleOctree<>();
        HashSet<Vector> blocks = new HashSet<>();
        List<DoubleOctree.Entry<String>> values = new ArrayList<>();
        List<DoubleOctree.Entry<String>> newValues = new ArrayList<>();

        // Initialize the map with random blocks dotted with 4 values
        Random rand = new Random(0x23532532);
        for (int n = 0; n < 1000; n++) {
            Vector v;
            do {
                v = new Vector(rand.nextInt(1000)-500,
                               rand.nextInt(1000)-500,
                               rand.nextInt(1000)-500);
            } while (!blocks.add(v));

            values.add(new DoubleOctree.Entry<>(new Vector(0.0, 0.0, 0.0).add(v), "A"));
            values.add(new DoubleOctree.Entry<>(new Vector(0.5, 0.0, 0.0).add(v), "B"));
            values.add(new DoubleOctree.Entry<>(new Vector(0.5, 0.5, 0.0).add(v), "C"));
            values.add(new DoubleOctree.Entry<>(new Vector(0.5, 0.5, 0.5).add(v), "D"));

            newValues.add(new DoubleOctree.Entry<>(new Vector(0.3, 0.1, 0.4).add(v), "A"));
            newValues.add(new DoubleOctree.Entry<>(new Vector(0.9, 0.2, 0.3).add(v), "B"));
            newValues.add(new DoubleOctree.Entry<>(new Vector(0.6, 0.3, 0.2).add(v), "C"));
            newValues.add(new DoubleOctree.Entry<>(new Vector(0.6, 0.4, 0.1).add(v), "D"));

            for (int i = values.size()-4; i < values.size(); i++) {
                tree.addEntry(values.get(i));
            }
        }

        // Move all entries back and forth repeatedly
        long start = System.currentTimeMillis();
        int iterations = 200;
        for (int k = 0; k < iterations; k++) {
            for (int i = 0; i < values.size(); i++) {
                assertTrue(tree.moveEntry(values.get(i), newValues.get(i)));
            }
            for (int i = 0; i < values.size(); i++) {
                assertTrue(tree.moveEntry(newValues.get(i), values.get(i)));
            }
        }
        long end = System.currentTimeMillis();
        double time = (double) (end-start) / (double) iterations / 2.0;
        System.out.println("Moving " + values.size() + " entries within the same block takes " + time + " ms/iteration");
    }

    @Test
    public void testMoveSameBlock_moveFirstNode() {
        // Move the first in the chain (A) while staying in front of B in the chain
        testMoveSameBlock("A", 10.3, 10.2, 10.1);

        // Move the first in the chain (A) to a spot between B and C
        testMoveSameBlock("A", 10.5, 10.2, 10.0);

        // Move the first in the chain (A) to the end of the chain
        testMoveSameBlock("A", 10.7, 10.5, 10.5);
    }

    @Test
    public void testMoveSameBlock_moveToBeforeFirstNode() {
        // Move node B to before node A
        testMoveSameBlock("B", 10.1, 10.1, 10.1);

        // Move node C to before node A
        testMoveSameBlock("B", 10.1, 10.1, 10.1);

        // Move the last node in the chain (D) to before node A
        testMoveSameBlock("D", 10.1, 10.1, 10.1);
    }

    @Test
    public void testMoveSameBlock_moveMiddleForwards() {
        // Move node B forwards to the same spot in the chain as it is now
        testMoveSameBlock("B", 10.5, 10.2, 10.0);
        
        // Move node B forwards to between node C and D
        testMoveSameBlock("B", 10.5, 10.5, 10.3);

        testMoveSameBlock("C", 10.5, 10.5, 10.2);
        
        // Move node B forwards to after the end of the chain (D)
        testMoveSameBlock("B", 10.7, 10.5, 10.5);

        // Move node D forwards to the same spot in the chain
        testMoveSameBlock("D", 10.7, 10.5, 10.5);
    }

    @Test
    public void testMoveSameBlock_moveMiddleBackwards() {
        // Move node B backwards to the same spot in the chain as it is now
        testMoveSameBlock("B", 10.4, 10.0, 10.0);

        // Move node C backwards to the same spot in the chain as it is now
        testMoveSameBlock("C", 10.5, 10.3, 10.0);

        // Move node C backwards to between node A and B
        testMoveSameBlock("C", 10.3, 10.3, 10.3);

        // Move node D backwards to between node B and C
        testMoveSameBlock("D", 10.5, 10.2, 10.0);

        // Move node D backwards to the same spot in the chain as it is now
        testMoveSameBlock("D", 10.5, 10.5, 10.3);
    }

    @Test
    public void testMoveSameBlock_firstNodeNextOccupied() {
        // Create a chain at 10/10/10 with 4 values
        DoubleOctree<String> tree = new DoubleOctree<>();
        tree.add(10.0, 10.0, 10.0, "A");
        tree.add(10.5, 10.0, 10.0, "B");
        tree.add(10.5, 10.5, 10.0, "C");
        tree.add(10.5, 10.5, 10.5, "D");

        // Move the first in the chain (A) to the position of B
        // This should put it on the same coordinates, but still searchable
        assertTrue(tree.move(10.0, 10.0, 10.0, "A",
                             10.5, 10.0, 10.0, "A_new"));

        // Verify the tree contents after these changes
        assertFalse(tree.contains(10.0, 10.0, 10.0, "A"));
        assertTreeGetEquals(tree, 10.5, 10.5, 10.0, "C");
        assertTreeGetEquals(tree, 10.5, 10.5, 10.5, "D");
        assertTreeGetEquals(tree, 10.5, 10.0, 10.0, "B", "A_new");
    }

    @Test
    public void testMoveSameBlock_nodeBeforeFirstMissing() {
        // Create a chain at 10/10/10 with 4 values
        DoubleOctree<String> tree = new DoubleOctree<>();
        tree.add(10.2, 10.0, 10.0, "A");
        tree.add(10.5, 10.0, 10.0, "B");
        tree.add(10.5, 10.5, 10.0, "C");
        tree.add(10.5, 10.5, 10.5, "D");

        // Move a non-existant node that is before A to elsewhere
        assertFalse(tree.move(10.0, 10.0, 10.0, "A",
                              10.1, 10.1, 10.1, "FAIL"));

        // Verify the tree contents after these changes
        assertTreeGetEquals(tree, 10.2, 10.0, 10.0, "A");
        assertTreeGetEquals(tree, 10.5, 10.0, 10.0, "B");
        assertTreeGetEquals(tree, 10.5, 10.5, 10.0, "C");
        assertTreeGetEquals(tree, 10.5, 10.5, 10.5, "D");
        assertFalse(tree.contains(10.1, 10.1, 10.1, "FAIL"));
    }

    @Test
    public void testMoveSameBlock_missingNodeMoveToBeforeFirst() {
        // Create a chain at 10/10/10 with 4 values
        DoubleOctree<String> tree = new DoubleOctree<>();
        tree.add(10.2, 10.0, 10.0, "A");
        tree.add(10.5, 10.0, 10.0, "B");
        tree.add(10.5, 10.5, 10.0, "C");
        tree.add(10.5, 10.5, 10.5, "D");

        // Move a non-existant node inside the chain order to a position
        // in-between A and B. This should fail.
        assertFalse(tree.move(10.5, 10.2, 10.0, "B",
                              10.1, 10.1, 10.1, "FAIL"));

        // Move a non-existant node beyond the end of the chain order
        // to a position in-between A and B. This should fail.
        assertFalse(tree.move(10.8, 10.5, 10.2, "D",
                              10.1, 10.1, 10.1, "FAIL"));

        // Verify the tree contents after these changes
        assertTreeGetEquals(tree, 10.2, 10.0, 10.0, "A");
        assertTreeGetEquals(tree, 10.5, 10.0, 10.0, "B");
        assertTreeGetEquals(tree, 10.5, 10.5, 10.0, "C");
        assertTreeGetEquals(tree, 10.5, 10.5, 10.5, "D");
        assertFalse(tree.contains(10.5, 10.2, 10.0, "FAIL"));
        assertFalse(tree.contains(10.1, 10.1, 10.1, "FAIL"));
    }

    @Test
    public void testMoveSameBlock_secondNodeMoveToFirst() {
        // Create a chain at 10/10/10 with 4 values
        DoubleOctree<String> tree = new DoubleOctree<>();
        tree.add(10.2, 10.0, 10.0, "A");
        tree.add(10.5, 10.0, 10.0, "B");
        tree.add(10.5, 10.5, 10.0, "C");
        tree.add(10.5, 10.5, 10.5, "D");

        // Move node B to where node A is, which should add it in
        // a way that it is still searchable
        assertTrue(tree.move(10.5, 10.0, 10.0, "B",
                             10.2, 10.0, 10.0, "B_new"));

        // Verify the tree contents after these changes
        assertFalse(tree.contains(10.5, 10.0, 10.0, "B"));

        assertTreeGetEquals(tree, 10.5, 10.5, 10.0, "C");
        assertTreeGetEquals(tree, 10.5, 10.5, 10.5, "D");
        assertTreeGetEquals(tree, 10.2, 10.0, 10.0, "A", "B_new");
    }

    @Test
    public void testMoveDifferentBlock_moveFirstNode() {
        // Move the first node (A1) to before the chain (A2)
        testMoveDifferentBlock("A1", 50.1, 50.0, 50.0);

        // Move the first node (A1) to between A2 and B2
        testMoveDifferentBlock("A1", 50.3, 50.0, 50.0);

        // Move the first node (A1) to beyond the chain (D2)
        testMoveDifferentBlock("A1", 50.6, 50.0, 50.0);
    }

    @Test
    public void testMoveDifferentBlock_moveMiddleNode() {
        // Move the second node (B1) to before the chain (A2)
        testMoveDifferentBlock("B1", 50.1, 50.0, 50.0);

        // Move the second node (B1) to between A2 and B2
        testMoveDifferentBlock("B1", 50.3, 50.0, 50.0);

        // Move the second node (B1) to beyond the chain (D2)
        testMoveDifferentBlock("B1", 50.6, 50.0, 50.0);

        // Move the third node (C1) to before the chain (A2)
        testMoveDifferentBlock("C1", 50.1, 50.0, 50.0);

        // Move the third node (C1) to between A2 and B2
        testMoveDifferentBlock("C1", 50.3, 50.0, 50.0);

        // Move the third node (C1) to beyond the chain (D2)
        testMoveDifferentBlock("C1", 50.6, 50.0, 50.0);
    }

    @Test
    public void testMoveDifferentBlock_moveLastNode() {
        // Move the last node (D1) to before the chain (A2)
        testMoveDifferentBlock("D1", 50.1, 50.0, 50.0);

        // Move the last node (D1) to between A2 and B2
        testMoveDifferentBlock("D1", 50.3, 50.0, 50.0);

        // Move the last node (D1) to beyond the chain (D2)
        testMoveDifferentBlock("D1", 50.6, 50.0, 50.0);
    }

    private void testMoveDifferentBlock(String name, double toX, double toY, double toZ) {
        // Create two chains at 10/10/10 and 50/50/50 with 4 values each
        DoubleOctree<String> tree = new DoubleOctree<>();
        tree.add(10.2, 10.0, 10.0, "A1");
        tree.add(10.5, 10.0, 10.0, "B1");
        tree.add(10.5, 10.5, 10.0, "C1");
        tree.add(10.5, 10.5, 10.5, "D1");
        tree.add(50.2, 50.0, 50.0, "A2");
        tree.add(50.5, 50.0, 50.0, "B2");
        tree.add(50.5, 50.5, 50.0, "C2");
        tree.add(50.5, 50.5, 50.5, "D2");

        // List all entries, find the old entry by name, store other entries for later
        double fromX = 0.0, fromY = 0.0, fromZ = 0.0;
        HashSet<String> outputValues = new HashSet<>();
        List<DoubleOctree.Entry<String>> unchangedEntries = new ArrayList<>();
        {
            DoubleOctreeIterator<String> iter = tree.iterator();
            while (iter.hasNext()) {
                DoubleOctree.Entry<String> entry = iter.nextEntry();
                if (name.equals(entry.getValue())) {
                    fromX = entry.getX();
                    fromY = entry.getY();
                    fromZ = entry.getZ();
                    outputValues.add(name + "_moved");
                } else {
                    unchangedEntries.add(entry);
                    outputValues.add(entry.getValue());
                }
            }
        }
        assertEquals(7, unchangedEntries.size());

        // Do the move
        assertTrue(tree.move(fromX, fromY, fromZ, name,
                             toX, toY, toZ, name + "_moved"));

        // Verify it was moved
        for (DoubleOctree.Entry<String> entry : unchangedEntries) {
            assertTrue(tree.containsEntry(entry));
            assertTreeGetEquals(tree, entry.getX(), entry.getY(), entry.getZ(), entry.getValue());
        }
        assertTreeGetEquals(tree, toX, toY, toZ, name + "_moved");
        assertFalse(tree.contains(fromX, fromY, fromZ, name));
        assertTrue(tree.contains(toX, toY, toZ, name + "_moved"));

        // Verify iteration works fine
        for (String value : tree) {
            assertTrue("Entry exists twice or is unexpected: " + value, outputValues.remove(value));
        }
    }

    private void testMoveSameBlock(String name, double toX, double toY, double toZ) {
        // Create a chain at 10/10/10 with 4 values
        DoubleOctree<String> tree = new DoubleOctree<>();
        tree.add(10.2, 10.0, 10.0, "A");
        tree.add(10.5, 10.0, 10.0, "B");
        tree.add(10.5, 10.5, 10.0, "C");
        tree.add(10.5, 10.5, 10.5, "D");

        // List all entries, find the old entry by name, store other entries for later
        double fromX = 0.0, fromY = 0.0, fromZ = 0.0;
        HashSet<String> outputValues = new HashSet<>();
        List<DoubleOctree.Entry<String>> unchangedEntries = new ArrayList<>();
        {
            DoubleOctreeIterator<String> iter = tree.iterator();
            while (iter.hasNext()) {
                DoubleOctree.Entry<String> entry = iter.nextEntry();
                if (name.equals(entry.getValue())) {
                    fromX = entry.getX();
                    fromY = entry.getY();
                    fromZ = entry.getZ();
                    outputValues.add(name + "_moved");
                } else {
                    unchangedEntries.add(entry);
                    outputValues.add(entry.getValue());
                }
            }
        }
        assertEquals(3, unchangedEntries.size());

        // Do the move
        assertTrue(tree.move(fromX, fromY, fromZ, name,
                             toX, toY, toZ, name + "_moved"));

        // Verify it was moved
        for (DoubleOctree.Entry<String> entry : unchangedEntries) {
            assertTrue(tree.containsEntry(entry));
            assertTreeGetEquals(tree, entry.getX(), entry.getY(), entry.getZ(), entry.getValue());
        }
        assertTreeGetEquals(tree, toX, toY, toZ, name + "_moved");
        assertFalse(tree.contains(fromX, fromY, fromZ, name));
        assertTrue(tree.contains(toX, toY, toZ, name + "_moved"));

        // Verify iteration works fine
        for (String value : tree) {
            assertTrue("Entry exists twice or is unexpected: " + value, outputValues.remove(value));
        }
    }

    private void assertNext(DoubleOctreeIterator<String> iter, String value, double x, double y, double z) {
        assertTrue(iter.hasNext());
        assertEquals(value, iter.next());
        assertEquals(x, iter.getX(), 0.0);
        assertEquals(y, iter.getY(), 0.0);
        assertEquals(z, iter.getZ(), 0.0);
        assertEquals(MathUtil.floor(x), iter.getBlockX());
        assertEquals(MathUtil.floor(y), iter.getBlockY());
        assertEquals(MathUtil.floor(z), iter.getBlockZ());
    }

    // checks that get is returning exactly the values stored for a particular x/y/z
    private void assertTreeGetEquals(DoubleOctree<String> tree, double x, double y, double z, String... values) {
        if (values.length == 0) {
            // No values
            assertTreeGetEmpty(tree, x, y, z);
        } else if (values.length == 1) {
            // One value
            String expectMsg = "Expected value " + values[0] + " to be stored at [" + x + ", " + y + ", " + z + "]";
            Collection<String> at = tree.get(x, y, z);
            assertFalse(expectMsg + ", but nothing is stored", at.isEmpty());
            assertEquals(expectMsg + ", but !=1 entries (" + at.size() + ") are stored", 1, at.size());

            Iterator<String> iter = at.iterator();
            assertTrue("PositionCollection Iterator hasNext() not working [1]", iter.hasNext());
            String stored = iter.next();
            assertEquals(expectMsg + ", but value was instead " + stored, values[0], stored);
            assertFalse("PositionCollection Iterator hasNext() not working [2]", iter.hasNext());
        } else {
            // Multiple values, use containsAll
            Collection<String> expected = new HashSet<>(Arrays.asList(values));
            Collection<String> actual = tree.get(x, y, z);
            assertTrue("Values stored at [" + x + ", " + y + ", " + z + "] are different",
                    expected.containsAll(actual) && actual.containsAll(expected));
        }
    }

    // checks that get is returning zero values stored for a particular x/y/z
    private void assertTreeGetEmpty(DoubleOctree<String> tree, double x, double y, double z) {
        String expectMsg = "Expected no values to be stored at [" + x + ", " + y + ", " + z + "]";
        Collection<String> at = tree.get(x, y, z);
        if (!at.isEmpty()) {
            expectMsg += ", but instead it stores";
            for (String value : at) {
                expectMsg += " " + value;
            }
            fail(expectMsg);
        }
        assertEquals(expectMsg + ", but !=0 entries (" + at.size() + ") are stored", 0, at.size());
        assertFalse("PositionCollection Iterator hasNext() not working [3]", at.iterator().hasNext());
    }
}
