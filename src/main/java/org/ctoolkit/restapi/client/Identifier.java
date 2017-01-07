/*
 * Copyright (c) 2016 Comvai, s.r.o. All Rights Reserved.
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

/**
 * The resource identifier with possibility to express parent child relation.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class Identifier
{
    private Identifier child;

    private Identifier parent;

    private Object value;

    /**
     * for debugging purpose only
     */
    private String description;

    /**
     * Constructs root (top level) identifier with given value.
     *
     * @param value the string type identifier to be set
     */
    public Identifier( String value )
    {
        if ( value == null )
        {
            throw new NullPointerException( "Identifier value cannot be null!" );
        }
        this.value = value;
    }

    /**
     * Constructs root (top level) identifier with given value.
     *
     * @param value the long type identifier to be set
     */
    public Identifier( Long value )
    {
        if ( value == null )
        {
            throw new NullPointerException( "Identifier value cannot be null!" );
        }
        this.value = value;
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
     * Sets identifier value as child to this parent.
     *
     * @param value the string type identifier to be set as child value to this parent
     * @return the new identifier child instance
     */
    public Identifier setChild( String value )
    {
        child = new Identifier( value );
        child.parent = this;

        return child;
    }

    /**
     * Returns the child identifier if any.
     *
     * @return the child identifier or <tt>null</tt>
     */
    public Identifier getChild()
    {
        return child;
    }

    /**
     * Sets identifier value as child to this parent.
     *
     * @param value the long type identifier to be set as child value to this parent
     * @return the new identifier child instance
     */
    public Identifier setChild( Long value )
    {
        child = new Identifier( value );
        child.parent = this;

        return child;
    }

    public Identifier getParent()
    {
        return parent;
    }

    /**
     * Sets the optional description, used for debugging purpose only.
     *
     * @param description the description to be set
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "Identifier{" +
                "value=" + value +
                ", description='" + description + '\'' +
                ", child=" + child +
                '}';
    }
}
