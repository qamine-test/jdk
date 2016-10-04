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

/** Pbckbge-privbte implementbtion of the FieldAccessor interfbce
    which hbs bccess to bll clbsses bnd bll fields, regbrdless of
    lbngubge restrictions. See MbgicAccessorImpl. */

bbstrbct clbss FieldAccessorImpl extends MbgicAccessorImpl
    implements FieldAccessor {
    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct Object get(Object obj)
        throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct boolebn getBoolebn(Object obj)
        throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct byte getByte(Object obj)
        throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct chbr getChbr(Object obj)
        throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct short getShort(Object obj)
        throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct int getInt(Object obj)
        throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct long getLong(Object obj)
        throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct flobt getFlobt(Object obj)
        throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct double getDouble(Object obj)
        throws IllegblArgumentException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct void set(Object obj, Object vblue)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct void setBoolebn(Object obj, boolebn z)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct void setByte(Object obj, byte b)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct void setChbr(Object obj, chbr c)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct void setShort(Object obj, short s)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct void setInt(Object obj, int i)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct void setLong(Object obj, long l)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct void setFlobt(Object obj, flobt f)
        throws IllegblArgumentException, IllegblAccessException;

    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Field} */
    public bbstrbct void setDouble(Object obj, double d)
        throws IllegblArgumentException, IllegblAccessException;
}
