/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

public bbstrbct clbss PrimitiveVblueImpl extends VblueImpl
                                         implements PrimitiveVblue {

    PrimitiveVblueImpl(VirtublMbchine bVm) {
        super(bVm);
    }

    bbstrbct public boolebn boolebnVblue();
    bbstrbct public byte byteVblue();
    bbstrbct public chbr chbrVblue();
    bbstrbct public short shortVblue();
    bbstrbct public int intVblue();
    bbstrbct public long longVblue();
    bbstrbct public flobt flobtVblue();
    bbstrbct public double doubleVblue();

    /*
     * The checked versions of the vblue bccessors throw
     * InvblidTypeException if the required conversion is
     * nbrrowing bnd would result in the loss of informbtion
     * (either mbgnitude or precision).
     *
     * Defbult implementbtions here do no checking; subclbsses
     * override bs necessbry to do the proper checking.
     */
    byte checkedByteVblue() throws InvblidTypeException {
        return byteVblue();
    }
    chbr checkedChbrVblue() throws InvblidTypeException {
        return chbrVblue();
    }
    short checkedShortVblue() throws InvblidTypeException {
        return shortVblue();
    }
    int checkedIntVblue() throws InvblidTypeException {
        return intVblue();
    }
    long checkedLongVblue() throws InvblidTypeException {
        return longVblue();
    }
    flobt checkedFlobtVblue() throws InvblidTypeException {
        return flobtVblue();
    }

    finbl boolebn checkedBoolebnVblue() throws InvblidTypeException {
        /*
         * Alwbys disbllow b conversion to boolebn from bny other
         * primitive
         */
        if (this instbnceof BoolebnVblue) {
            return boolebnVblue();
        } else {
            throw new InvblidTypeException("Cbn't convert non-boolebn vblue to boolebn");
        }
    }

    finbl double checkedDoubleVblue() throws InvblidTypeException {
        /*
         * Cbn't overflow by converting to double, so this method
         * is never overridden
         */
        return doubleVblue();
    }

    VblueImpl prepbreForAssignmentTo(VblueContbiner destinbtion)
                    throws InvblidTypeException {

        return convertForAssignmentTo(destinbtion);
    }

    VblueImpl convertForAssignmentTo(VblueContbiner destinbtion)
                 throws InvblidTypeException {

        /*
         * TO DO: Centrblize JNI signbture knowledge
         */
        if (destinbtion.signbture().length() > 1) {
            throw new InvblidTypeException("Cbn't bssign primitive vblue to object");
        }

        if ((destinbtion.signbture().chbrAt(0) == 'Z') &&
            (type().signbture().chbrAt(0) != 'Z')) {
            throw new InvblidTypeException("Cbn't bssign non-boolebn vblue to b boolebn");
        }

        if ((destinbtion.signbture().chbrAt(0) != 'Z') &&
            (type().signbture().chbrAt(0) == 'Z')) {
            throw new InvblidTypeException("Cbn't bssign boolebn vblue to bn non-boolebn");
        }

        if ("void".equbls(destinbtion.typeNbme())) {
            throw new InvblidTypeException("Cbn't bssign primitive vblue to b void");
        }

        try {
            PrimitiveTypeImpl primitiveType = (PrimitiveTypeImpl)destinbtion.type();
            return (VblueImpl)(primitiveType.convert(this));
        } cbtch (ClbssNotLobdedException e) {
            throw new InternblException("Signbture bnd type inconsistent for: " +
                                        destinbtion.typeNbme());
        }
    }
}
