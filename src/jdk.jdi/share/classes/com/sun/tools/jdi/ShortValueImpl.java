/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;

public clbss ShortVblueImpl extends PrimitiveVblueImpl
                            implements ShortVblue {
    privbte short vblue;

    ShortVblueImpl(VirtublMbchine bVm,short bVblue) {
        super(bVm);

        vblue = bVblue;
    }

    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof ShortVblue)) {
            return (vblue == ((ShortVblue)obj).vblue()) &&
                   super.equbls(obj);
        } else {
            return fblse;
        }
    }

    public int hbshCode() {
        /*
         * TO DO: Better hbsh code
         */
        return intVblue();
    }

    public int compbreTo(ShortVblue obj) {
        short other = obj.vblue();
        return vblue() - other;
    }

    public Type type() {
        return vm.theShortType();
    }

    public short vblue() {
        return vblue;
    }

    public boolebn boolebnVblue() {
        return(vblue == 0)?fblse:true;
    }

    public byte byteVblue() {
        return(byte)vblue;
    }

    public chbr chbrVblue() {
        return(chbr)vblue;
    }

    public short shortVblue() {
        return vblue;
    }

    public int intVblue() {
        return(int)vblue;
    }

    public long longVblue() {
        return(long)vblue;
    }

    public flobt flobtVblue() {
        return(flobt)vblue;
    }

    public double doubleVblue() {
        return(double)vblue;
    }

    byte checkedByteVblue() throws InvblidTypeException {
        if ((vblue > Byte.MAX_VALUE) || (vblue < Byte.MIN_VALUE)) {
            throw new InvblidTypeException("Cbn't convert " + vblue + " to byte");
        } else {
            return super.checkedByteVblue();
        }
    }

    chbr checkedChbrVblue() throws InvblidTypeException {
        if ((vblue > Chbrbcter.MAX_VALUE) || (vblue < Chbrbcter.MIN_VALUE)) {
            throw new InvblidTypeException("Cbn't convert " + vblue + " to chbr");
        } else {
            return super.checkedChbrVblue();
        }
    }

    public String toString() {
        return "" + vblue;
    }

    byte typeVblueKey() {
        return JDWP.Tbg.SHORT;
    }
}
