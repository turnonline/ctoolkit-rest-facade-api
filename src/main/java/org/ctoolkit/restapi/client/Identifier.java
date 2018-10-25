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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;

/**
 * The resource identifier with possibility to express parent child relationship.
 * Examples:
 * <pre>
 * {@code
 * The first value acts as a root identifier and next will act as a child identifier(s).
 * In terms of URL the root identifier is the first from the left side: /user-id/10/foo-id/20/child-id/30
 *
 * Identifier identifier = new Identifier( 10L, 20L, 30L );
 *
 * // chaining String id's
 * Identifier identifier = new Identifier( "abc" ).add( "ghb" ).add( "klf" );
 *
 * // mixing id's type
 * Identifier identifier = new Identifier( "abc", "ghb" ).add( 40L ).add( "xbbc" );
 * }
 * </pre>
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class Identifier
{
    private Identifier child;

    private Identifier parent;

    private String controller;

    private Object value;

    /**
     * Constructs identifier with given identification values.
     * The first value acts as a root identifier and next will act as a child identifier(s).
     *
     * @param value the string type identifier(s) to be set
     */
    public Identifier( @Nonnull String... value )
    {
        if ( value.length == 0 )
        {
            throw new NullPointerException( "Identifier value cannot be null or empty!" );
        }

        for ( String next : value )
        {
            if ( next == null || next.isEmpty() )
            {
                throw new NullPointerException( "Any of the Identifier value cannot be null or empty! "
                        + Arrays.toString( value ) );
            }
        }

        this.value = value[0];

        Identifier next = this;
        for ( int index = 1; index < value.length; index++ )
        {
            next = next.setChild( value[index] );
        }
    }

    /**
     * Constructs identifier with given identification values.
     * The first value acts as a root identifier and next will act as a child identifier(s).
     *
     * @param value the long type identifier(s) to be set
     */
    public Identifier( @Nonnull Long... value )
    {
        if ( value.length == 0 )
        {
            throw new NullPointerException( "Identifier value cannot be null!" );
        }

        for ( Long next : value )
        {
            if ( next == null )
            {
                throw new NullPointerException( "Any of the Identifier value cannot be null! "
                        + Arrays.toString( value ) );
            }
        }

        this.value = value[0];

        Identifier next = this;
        for ( int index = 1; index < value.length; index++ )
        {
            next = next.setChild( value[index] );
        }
    }

    /**
     * Constructs identifier with given identification values.
     * The first value acts as a root identifier and next will act as a child identifier(s).
     *
     * @param value the long type identifier(s) to be set
     */
    public Identifier( @Nonnull Integer... value )
    {
        if ( value.length == 0 )
        {
            throw new NullPointerException( "Identifier value cannot be null!" );
        }

        for ( Integer next : value )
        {
            if ( next == null )
            {
                throw new NullPointerException( "Any of the Identifier value cannot be null! "
                        + Arrays.toString( value ) );
            }
        }

        this.value = value[0];

        Identifier next = this;
        for ( int index = 1; index < value.length; index++ )
        {
            next = next.setChild( value[index] );
        }
    }

    /**
     * Adds given value as a child identifier to this identifier that acts as a parent.
     *
     * @param value the string type identifier to be set as child identifier
     * @return the root identifier to chain calls
     */
    public Identifier add( @Nonnull String value )
    {
        leaf().setChild( value );
        return this;
    }

    /**
     * Adds given value as a child identifier to this identifier that acts as a parent.
     *
     * @param value the long type identifier to be set as child identifier
     * @return the root identifier to chain calls
     */
    public Identifier add( @Nonnull Long value )
    {
        leaf().setChild( value );
        return this;
    }

    /**
     * Adds given value as a child identifier to this identifier that acts as a parent.
     *
     * @param value the integer type identifier to be set as child identifier
     * @return the root identifier to chain calls
     */
    public Identifier add( @Nonnull Integer value )
    {
        leaf().setChild( value );
        return this;
    }

    /**
     * Appends given controller to the path as a discriminator for the current resource.
     * For example, origin /accounts/{account_id} extended: /accounts/{account_id}/controller
     *
     * @param controller the controller to be appended (excluding flash)
     * @return the root identifier to chain calls
     */
    public Identifier controller( @Nullable String controller )
    {
        if ( controller != null && controller.trim().startsWith( "/" ) )
        {
            throw new IllegalArgumentException( "Controller must not start with slash" );
        }
        leaf().setController( controller );
        return this;
    }

    /**
     * Returns a boolean identification whether this identifier has a controller.
     *
     * @return true if this identifier has a controller
     */
    public boolean hasController()
    {
        return this.controller != null;
    }

    /**
     * Returns the controller value.
     *
     * @return the controller
     */
    public String getController()
    {
        return controller;
    }

    private void setController( @Nullable String controller )
    {
        this.controller = controller == null ? null : controller.trim();
    }

    /**
     * Returns the boolean indicating whether the identifier value is type of {@link Long} or not.
     *
     * @return true if the value is type of Long
     */
    public boolean isLong()
    {
        return value instanceof Long;
    }

    /**
     * Returns the boolean indicating whether the identifier value is type of {@link Integer} or not.
     *
     * @return true if the value is type of Integer
     */
    public boolean isInt()
    {
        return value instanceof Integer;
    }

    /**
     * Returns the identifier raw value.
     *
     * @return the identifier raw value.
     */
    public Object value()
    {
        return value;
    }

    /**
     * Returns the identifier value.
     *
     * @return the identifier value.
     */
    public String getString()
    {
        return value.toString();
    }

    /**
     * Returns the identifier value.
     *
     * @return the identifier value
     */
    public Long getLong()
    {
        return ( Long ) value;
    }

    /**
     * Returns the identifier value.
     *
     * @return the identifier value
     */
    public Integer getInt()
    {
        return ( Integer ) value;
    }

    /**
     * Returns a boolean identification whether this identifier has a parent identifier.
     *
     * @return true if this identifier has a parent identifier
     */
    public boolean hasParent()
    {
        return parent != null;
    }

    /**
     * Returns a boolean identification whether this identifier has a child identifier.
     *
     * @return true if this identifier has a child identifier
     */
    public boolean hasChild()
    {
        return child != null;
    }

    /**
     * Sets identifier value as child to this parent.
     *
     * @param value the string type identifier to be set as child value to this parent
     * @return the new identifier child instance
     */
    private Identifier setChild( @Nonnull String value )
    {
        child = new Identifier( value );
        child.parent = this;

        return child;
    }

    /**
     * Returns the child identifier.
     * <p>
     * It throws {@link NullPointerException} if child is null.
     * To check child presence use {@link #hasChild()}.
     *
     * @return the child identifier.
     */
    public Identifier child()
    {
        if ( child == null )
        {
            throw new NullPointerException( "Child identifier is null, to check presence use #hasChild()" );
        }
        return child;
    }

    /**
     * Sets identifier value as child to this parent.
     *
     * @param value the long type identifier to be set as child value to this parent
     * @return the new identifier child instance
     */
    private Identifier setChild( @Nonnull Long value )
    {
        child = new Identifier( value );
        child.parent = this;

        return child;
    }

    /**
     * Sets identifier value as child to this parent.
     *
     * @param value the integer type identifier to be set as child value to this parent
     * @return the new identifier child instance
     */
    private Identifier setChild( @Nonnull Integer value )
    {
        child = new Identifier( value );
        child.parent = this;

        return child;
    }

    /**
     * Traverses and returns the leaf identifier, as opposite to the root identifier.
     *
     * @return the leaf identifier
     */
    public Identifier leaf()
    {
        Identifier child = this;
        while ( child.hasChild() )
        {
            child = child.child();
        }
        return child;
    }

    /**
     * Traverses and returns the root (top level) identifier.
     *
     * @return the root identifier
     */
    public Identifier root()
    {
        Identifier root = this;
        while ( root.hasParent() )
        {
            root = root.getParent();
        }
        return root;
    }

    /**
     * Returns the parent of this identifier. If you need root identifier use {@link #root()} instead.
     * <p>
     * It throws {@link NullPointerException} if parent is null.
     * To check parent presence use {@link #hasParent()}.
     *
     * @return the parent identifier
     */
    public Identifier getParent()
    {
        if ( parent == null )
        {
            throw new NullPointerException( "Parent identifier is null, to check presence use #hasParent()" );
        }
        return parent;
    }

    /**
     * Returns the identifier as a string key composed always from the root separated by ':'.
     *
     * @return the string key of the identifier
     */
    public String key()
    {
        Identifier child = root();
        StringBuilder builder = child.hasController()
                ? new StringBuilder( "/" + child.getController() ) : new StringBuilder();

        if ( child.hasChild() && child.hasController() )
        {
            // first child
            builder.append( ":" );
        }
        builder.append( child.value().toString() );

        while ( child.hasChild() )
        {
            child = child.child();
            if ( child.hasController() )
            {
                builder.append( ":/" );
                builder.append( child.getController() );
            }
            builder.append( ":" );
            builder.append( child.value().toString() );
        }
        return builder.toString();
    }

    private void append( StringBuilder builder, Identifier identifier )
    {
        if ( identifier == this )
        {
            // marking the current identifier
            builder.append( "[" );
            if ( identifier.hasController() )
            {
                builder.append( identifier.getController() );
                builder.append( ":" );
            }
            builder.append( identifier.value().toString() );
            builder.append( "]" );
        }
        else
        {
            if ( identifier.hasController() )
            {
                builder.append( identifier.getController() );
                builder.append( ":" );
            }
            builder.append( identifier.value().toString() );
        }
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( !( o instanceof Identifier ) ) return false;
        Identifier that = ( Identifier ) o;
        return Objects.equals( child, that.child ) &&
                Objects.equals( parent, that.parent ) &&
                Objects.equals( value, that.value ) &&
                Objects.equals( controller, that.controller );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( child, parent, value, controller );
    }

    @Override
    public String toString()
    {
        Identifier child = root();
        StringBuilder builder = new StringBuilder();
        append( builder, child );

        while ( child.hasChild() )
        {
            child = child.child();
            builder.append( ":" );
            append( builder, child );
        }

        return "Identifier: " + builder;
    }
}
