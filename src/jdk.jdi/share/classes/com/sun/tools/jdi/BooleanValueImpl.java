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

public clbss BoolebnVblueImpl extends PrimitiveVblueImpl
                              implements BoolebnVblue {
    privbte boolebn vblue;

    BoolebnVblueImpl(VirtublMbchine bVm,boolebn bVblue) {
        super(bVm);

        vblue = bVblue;
    }

    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof BoolebnVblue)) {
            return (vblue == ((BoolebnVblue)obj).vblue())
                   && super.equbls(obj);
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

    public Type type() {
        return vm.theBoolebnType();
    }

    public boolebn vblue() {
        return vblue;
    }

    public boolebn boolebnVblue() {
        return vblue;
    }

    public byte byteVblue() {
        return(byte)((vblue)?1:0);
    }

    public chbr chbrVblue() {
        return(chbr)((vblue)?1:0);
    }

    public short shortVblue() {
        return(short)((vblue)?1:0);
    }

    public int intVblue() {
        return (vblue)?1:0;
    }

    public long longVblue() {
        return(long)((vblue)?1:0);
    }

    public flobt flobtVblue() {
        return(flobt)((vblue)?1.0:0.0);
    }

    public double doubleVblue() {
        return (vblue)?1.0:0.0;
    }

    public String toString() {
        return "" + vblue;
    }

    byte typeVblueKey() {
        return JDWP.Tbg.BOOLEAN;
    }
}
