/*
 * Copyright (c) 2017 Comvai, s.r.o. All Rights Reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.ctoolkit.restapi.client;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * {@link Identifier} unit testing.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class IdentifierTest
{
    @Test
    public void identifierHierarchyLongOnly()
    {
        Identifier identifier = new Identifier( 10L, 20L, 30L );

        assertEquals( identifier.getLong(), Long.valueOf( 10L ) );
        assertEquals( identifier.child().getLong(), Long.valueOf( 20L ) );
        assertEquals( identifier.child().child().getLong(), Long.valueOf( 30L ) );

        // chaining Long ids
        identifier = new Identifier( 10L ).add( 20L ).add( 30L );

        assertEquals( identifier.getLong(), Long.valueOf( 10L ) );

        assertTrue( identifier.hasChild() );
        assertEquals( identifier.child().getLong(), Long.valueOf( 20L ) );

        assertTrue( identifier.child().hasChild() );
        assertEquals( identifier.child().child().getLong(), Long.valueOf( 30L ) );
    }

    @Test
    public void identifierHierarchyStringOnly()
    {
        Identifier identifier = new Identifier( "abc", "ghb", "klf" );

        assertTrue( identifier.hasChild() );
        assertTrue( identifier.child().hasChild() );

        assertEquals( identifier.getString(), "abc" );
        assertEquals( identifier.child().getString(), "ghb" );
        assertEquals( identifier.child().child().getString(), "klf" );

        // chaining String ids
        identifier = new Identifier( "abc" ).add( "ghb" ).add( "klf" );

        assertNotNull( identifier.child() );
        assertNotNull( identifier.child().child() );

        assertEquals( identifier.getString(), "abc" );
        assertEquals( identifier.child().getString(), "ghb" );
        assertEquals( identifier.child().child().getString(), "klf" );
    }

    @Test
    public void identifierHierarchyIntegerOnly()
    {
        Identifier identifier = new Identifier( 10, 20, 30 );

        assertEquals( identifier.getInt(), Integer.valueOf( 10 ) );
        assertEquals( identifier.child().getInt(), Integer.valueOf( 20 ) );
        assertEquals( identifier.child().child().getInt(), Integer.valueOf( 30 ) );

        // chaining Integer ids
        identifier = new Identifier( 10 ).add( 20 ).add( 30 );

        assertEquals( identifier.getInt(), Integer.valueOf( 10 ) );

        assertTrue( identifier.hasChild() );
        assertEquals( identifier.child().getInt(), Integer.valueOf( 20 ) );

        assertTrue( identifier.child().hasChild() );
        assertEquals( identifier.child().child().getInt(), Integer.valueOf( 30 ) );
    }

    @Test
    public void identifierHierarchyMixedType()
    {
        Identifier identifier = new Identifier( "abc", "ghb" ).add( 40L ).add( "xbbc" ).add( 9 );

        assertTrue( identifier.hasChild() );
        assertTrue( identifier.child().hasChild() );
        assertTrue( identifier.child().child().hasChild() );

        assertEquals( identifier.getString(), "abc" );
        assertEquals( identifier.child().getString(), "ghb" );
        assertEquals( identifier.child().child().getLong(), Long.valueOf( 40L ) );
        assertEquals( identifier.child().child().child().getString(), "xbbc" );
        assertEquals( identifier.child().child().child().child().getInt(), Integer.valueOf( 9 ) );
    }

    @Test
    public void rootLeafIdentifierRetrieval()
    {
        Identifier identifier = new Identifier( "salma", "something" ).add( 45L ).add( "last" );
        Identifier leaf = identifier.leaf();

        assertFalse( leaf.hasChild() );
        assertEquals( leaf.getString(), "last" );

        Identifier root = leaf.root();

        assertFalse( root.hasParent() );
        assertEquals( root.getString(), "salma" );
    }

    @Test( expectedExceptions = NullPointerException.class )
    public void getParentNull()
    {
        Identifier identifier = new Identifier( "salma" );
        identifier.getParent();
    }

    @Test( expectedExceptions = NullPointerException.class )
    public void getChildNull()
    {
        Identifier identifier = new Identifier( "salma" );
        identifier.child();
    }

    @Test
    public void toStringTest()
    {
        Identifier identifier = new Identifier( "salma", "something" )
                .add( 45L )
                .add( "last" )
                .controller( "action" );

        assertEquals( identifier.leaf().toString(), "Identifier: salma:something:45:[action:last]" );
        assertEquals( identifier.root().toString(), "Identifier: [salma]:something:45:action:last" );
    }

    @Test
    public void keyTest()
    {
        // single
        Identifier identifier = new Identifier( "salma" );

        assertEquals( identifier.leaf().key(), "salma" );
        assertEquals( identifier.root().key(), "salma" );

        // parent/child
        identifier = new Identifier( "salma", "something" ).add( 45L ).add( "last" );

        assertEquals( identifier.leaf().key(), "salma:something:45:last" );
        assertEquals( identifier.root().key(), "salma:something:45:last" );
    }

    @Test
    public void keyTest_withController()
    {
        // controller first
        Identifier identifier = new Identifier( "salma" )
                .controller( "control" )
                .add( 45L )
                .add( "last" );

        assertEquals( identifier.leaf().key(), "/control:salma:45:last" );
        assertEquals( identifier.root().key(), "/control:salma:45:last" );

        // controller in middle
        identifier = new Identifier( "salma", "something" )
                .add( 45L )
                .controller( "control" )
                .add( "last" );

        assertEquals( identifier.leaf().key(), "salma:something:/control:45:last" );
        assertEquals( identifier.root().key(), "salma:something:/control:45:last" );

        // controller as last
        identifier = new Identifier( "salma", "something" )
                .add( 45L )
                .add( "last" )
                .controller( "control" );

        assertEquals( identifier.leaf().key(), "salma:something:45:/control:last" );
        assertEquals( identifier.root().key(), "salma:something:45:/control:last" );
    }

    @Test
    public void identifierEquals()
    {
        Identifier identifier = new Identifier( "abc", "ghb" ).add( 40L ).add( "xbbc" ).controller( "control" );

        assertTrue( identifier.hasChild() );
        assertNotEquals( identifier, identifier.child() );

        assertTrue( identifier.child().hasChild() );
        assertNotEquals( identifier.child(), identifier.child().child() );

        assertTrue( identifier.child().child().hasChild() );
        assertNotEquals( identifier.child(), identifier.child().child().child() );

        assertEquals( identifier.leaf(), identifier.child().leaf() );
    }

    @Test
    public void isLong()
    {
        Identifier identifier = new Identifier( 40L );
        assertTrue( identifier.isLong() );

        identifier = new Identifier( "40" );
        assertFalse( identifier.isLong() );
    }

    @Test
    public void isInteger()
    {
        Identifier identifier = new Identifier( 40 );
        assertTrue( identifier.isInt() );

        identifier = new Identifier( "40" );
        assertFalse( identifier.isLong() );
        assertFalse( identifier.isInt() );
    }

    @Test( expectedExceptions = NullPointerException.class )
    public void firstNullLongValueCheck()
    {
        Long[] ids = new Long[1];
        ids[0] = null;
        new Identifier( ids );
    }

    @Test( expectedExceptions = NullPointerException.class )
    public void anywhereNullLongValueCheck()
    {
        Long[] ids = new Long[2];
        ids[0] = 1L;
        ids[1] = null;
        new Identifier( ids );
    }

    @Test( expectedExceptions = NullPointerException.class )
    public void firstNullIntegerValueCheck()
    {
        Integer[] ids = new Integer[1];
        ids[0] = null;
        new Identifier( ids );
    }

    @Test( expectedExceptions = NullPointerException.class )
    public void anywhereNullIntegerValueCheck()
    {
        Integer[] ids = new Integer[2];
        ids[0] = 1;
        ids[1] = null;
        new Identifier( ids );
    }

    @Test( expectedExceptions = NullPointerException.class )
    public void firstNullStringValueCheck()
    {
        String[] ids = new String[1];
        ids[0] = null;
        new Identifier( ids );
    }

    @Test( expectedExceptions = NullPointerException.class )
    public void anywhereNullStringValueCheck()
    {
        String[] ids = new String[2];
        ids[0] = "abc";
        ids[1] = null;
        new Identifier( ids );
    }

    @Test( expectedExceptions = NullPointerException.class )
    public void anywhereEmptyStringValueCheck()
    {
        String[] ids = new String[2];
        ids[0] = "abc";
        ids[1] = "";
        new Identifier( ids );
    }
}