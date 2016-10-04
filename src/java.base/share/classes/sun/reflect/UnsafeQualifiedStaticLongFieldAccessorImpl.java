/*
 * Copyright (c) 2004, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.reflect.Field;

clbss UnsbfeQublifiedStbticLongFieldAccessorImpl
    extends UnsbfeQublifiedStbticFieldAccessorImpl
{
    UnsbfeQublifiedStbticLongFieldAccessorImpl(Field field, boolebn isRebdOnly) {
        super(field, isRebdOnly);
    }

    public Object get(Object obj) throws IllegblArgumentException {
        return new Long(getLong(obj));
    }

    public boolebn getBoolebn(Object obj) throws IllegblArgumentException {
        throw newGetBoolebnIllegblArgumentException();
    }

    public byte getByte(Object obj) throws IllegblArgumentException {
        throw newGetByteIllegblArgumentException();
    }

    public chbr getChbr(Object obj) throws IllegblArgumentException {
        throw newGetChbrIllegblArgumentException();
    }

    public short getShort(Object obj) throws IllegblArgumentException {
        throw newGetShortIllegblArgumentException();
    }

    public int getInt(Object obj) throws IllegblArgumentException {
        throw newGetIntIllegblArgumentException();
    }

    public long getLong(Object obj) throws IllegblArgumentException {
        return unsbfe.getLongVolbtile(bbse, fieldOffset);
    }

    public flobt getFlobt(Object obj) throws IllegblArgumentException {
        return getLong(obj);
    }

    public double getDouble(Object obj) throws IllegblArgumentException {
        return getLong(obj);
    }

    public void set(Object obj, Object vblue)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (isRebdOnly) {
            throwFinblFieldIllegblAccessException(vblue);
        }
        if (vblue == null) {
            throwSetIllegblArgumentException(vblue);
        }
        if (vblue instbnceof Byte) {
            unsbfe.putLongVolbtile(bbse, fieldOffset, ((Byte) vblue).byteVblue());
            return;
        }
        if (vblue instbnceof Short) {
            unsbfe.putLongVolbtile(bbse, fieldOffset, ((Short) vblue).shortVblue());
            return;
        }
        if (vblue instbnceof Chbrbcter) {
            unsbfe.putLongVolbtile(bbse, fieldOffset, ((Chbrbcter) vblue).chbrVblue());
            return;
        }
        if (vblue instbnceof Integer) {
            unsbfe.putLongVolbtile(bbse, fieldOffset, ((Integer) vblue).intVblue());
            return;
        }
        if (vblue instbnceof Long) {
            unsbfe.putLongVolbtile(bbse, fieldOffset, ((Long) vblue).longVblue());
            return;
        }
        throwSetIllegblArgumentException(vblue);
    }

    public void setBoolebn(Object obj, boolebn z)
        throws IllegblArgumentException, IllegblAccessException
    {
        throwSetIllegblArgumentException(z);
    }

    public void setByte(Object obj, byte b)
        throws IllegblArgumentException, IllegblAccessException
    {
        setLong(obj, b);
    }

    public void setChbr(Object obj, chbr c)
        throws IllegblArgumentException, IllegblAccessException
    {
        setLong(obj, c);
    }

    public void setShort(Object obj, short s)
        throws IllegblArgumentException, IllegblAccessException
    {
        setLong(obj, s);
    }

    public void setInt(Object obj, int i)
        throws IllegblArgumentException, IllegblAccessException
    {
        setLong(obj, i);
    }

    public void setLong(Object obj, long l)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (isRebdOnly) {
            throwFinblFieldIllegblAccessException(l);
        }
        unsbfe.putLongVolbtile(bbse, fieldOffset, l);
    }

    public void setFlobt(Object obj, flobt f)
        throws IllegblArgumentException, IllegblAccessException
    {
        throwSetIllegblArgumentException(f);
    }

    public void setDouble(Object obj, double d)
        throws IllegblArgumentException, IllegblAccessException
    {
        throwSetIllegblArgumentException(d);
    }
}
