package org.ctoolkit.restapi.client;

/**
 * The resource identifier with possibility to express parent child relation.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class Identifier<K>
{
    private Identifier<K> child;

    private K value;

    /**
     * for debugging purpose only
     */
    private String description;

    /**
     * Constructs identifier with given value.
     *
     * @param value the identifier value to be set
     */
    public Identifier( K value )
    {
        if ( value == null )
        {
            throw new NullPointerException();
        }
        this.value = value;
    }

    /**
     * Returns the identifier value.
     *
     * @return the identifier value.
     */
    public K value()
    {
        return value;
    }

    /**
     * Returns the identifier string value as parsed {@link Long}.
     *
     * @return the identifier value.
     * @throws NumberFormatException
     */
    public Long valueAsLong()
    {
        if ( value == null || value.toString().length() == 0 )
        {
            return null;
        }

        return Long.valueOf( value.toString() );
    }

    /**
     * Add identifier value as child to this parent.
     *
     * @param value the value to be set as child value to this parent
     * @return the new identifier child instance
     */
    public Identifier<K> addChild( K value )
    {
        child = new Identifier<>( value );
        return child;
    }

    /**
     * Returns the child identifier if any.
     *
     * @return the child identifier or <tt>null</tt>
     */
    public Identifier<K> getChild()
    {
        return child;
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
                '}';
    }
}
