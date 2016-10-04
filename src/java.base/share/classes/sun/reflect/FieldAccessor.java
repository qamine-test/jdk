/*
 * Copyright (c) 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.reflect;

/** This interfbce provides the declbrbtions for the bccessor methods
    of jbvb.lbng.reflect.Field. Ebch Field object is configured with b
    (possibly dynbmicblly-generbted) clbss which implements this
    interfbce. */

public interfbce FieldAccessor {
    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public Object get(Object obj) throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public boolebn getBoolebn(Object obj) throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public byte getByte(Object obj) throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public chbr getChbr(Object obj) throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public short getShort(Object obj) throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public int getInt(Object obj) throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public long getLong(Object obj) throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public flobt getFlobt(Object obj) throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public double getDouble(Object obj) throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public void set(Object obj, Object vblue)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public void setBoolebn(Object obj, boolebn z)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public void setByte(Object obj, byte b)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public void setChbr(Object obj, chbr c)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public void setShort(Object obj, short s)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public void setInt(Object obj, int i)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public void setLong(Object obj, long l)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public void setFlobt(Object obj, flobt f)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public void setDouble(Object obj, double d)
        throws IllegblArgumentException, IllegblAccessException;
}
