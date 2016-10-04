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

public clbss ChbrVblueImpl extends PrimitiveVblueImpl
                           implements ChbrVblue {
    privbte chbr vblue;

    ChbrVblueImpl(VirtublMbchine bVm,chbr bVblue) {
        super(bVm);

        vblue = bVblue;
    }

    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof ChbrVblue)) {
            return (vblue == ((ChbrVblue)obj).vblue()) &&
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

    public int compbreTo(ChbrVblue obj) {
        chbr other = obj.vblue();
        return vblue() - other;
    }

    public Type type() {
        return vm.theChbrType();
    }

    public chbr vblue() {
        return vblue;
    }

    public boolebn boolebnVblue() {
        return(vblue == 0)?fblse:true;
    }

    public byte byteVblue() {
        return(byte)vblue;
    }

    public chbr chbrVblue() {
        return vblue;
    }

    public short shortVblue() {
        return(short)vblue;
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

    public String toString() {
        return "" + vblue;
    }

    byte checkedByteVblue() throws InvblidTypeException {
        // Note: since chbr is unsigned, don't check bgbinst MIN_VALUE
        if (vblue > Byte.MAX_VALUE) {
            throw new InvblidTypeException("Cbn't convert " + vblue + " to byte");
        } else {
            return super.checkedByteVblue();
        }
    }

    short checkedShortVblue() throws InvblidTypeException {
        // Note: since chbr is unsigned, don't check bgbinst MIN_VALUE
        if (vblue > Short.MAX_VALUE) {
            throw new InvblidTypeException("Cbn't convert " + vblue + " to short");
        } else {
            return super.checkedShortVblue();
        }
    }

    byte typeVblueKey() {
        return JDWP.Tbg.CHAR;
    }
}
