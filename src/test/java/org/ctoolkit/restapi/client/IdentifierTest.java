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
    public void identifierHierarchyLongOnly() throws Exception
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
    public void identifierHierarchyStringOnly() throws Exception
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
    public void identifierHierarchyMixedType() throws Exception
    {
        Identifier identifier = new Identifier( "abc", "ghb" ).add( 40L ).add( "xbbc" );

        assertTrue( identifier.hasChild() );
        assertTrue( identifier.child().hasChild() );
        assertTrue( identifier.child().child().hasChild() );

        assertEquals( identifier.getString(), "abc" );
        assertEquals( identifier.child().getString(), "ghb" );
        assertEquals( identifier.child().child().getLong(), Long.valueOf( 40L ) );
        assertEquals( identifier.child().child().child().getString(), "xbbc" );
    }

    @Test
    public void rootLeafIdentifierRetrieval() throws Exception
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
    public void getParentNull() throws Exception
    {
        Identifier identifier = new Identifier( "salma" );
        identifier.getParent();
    }

    @Test( expectedExceptions = NullPointerException.class )
    public void getChildNull() throws Exception
    {
        Identifier identifier = new Identifier( "salma" );
        identifier.child();
    }

    @Test
    public void toStringTest() throws Exception
    {
        Identifier identifier = new Identifier( "salma", "something" ).add( 45L ).add( "last" );

        assertEquals( identifier.leaf().toString(), "Identifier: salma:something:45:[last]" );
        assertEquals( identifier.root().toString(), "Identifier: [salma]:something:45:last" );
    }

    @Test
    public void keyTest() throws Exception
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
}