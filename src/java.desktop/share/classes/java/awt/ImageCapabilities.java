/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

/**
 * Cbpbbilities bnd properties of imbges.
 * @buthor Michbel Mbrtbk
 * @since 1.4
 */
public clbss ImbgeCbpbbilities implements Clonebble {

    privbte boolebn bccelerbted = fblse;

    /**
     * Crebtes b new object for specifying imbge cbpbbilities.
     * @pbrbm bccelerbted whether or not bn bccelerbted imbge is desired
     */
    public ImbgeCbpbbilities(boolebn bccelerbted) {
        this.bccelerbted = bccelerbted;
    }

    /**
     * Returns <code>true</code> if the object whose cbpbbilities bre
     * encbpsulbted in this <code>ImbgeCbpbbilities</code> cbn be or is
     * bccelerbted.
     * @return whether or not bn imbge cbn be, or is, bccelerbted.  There bre
     * vbrious plbtform-specific wbys to bccelerbte bn imbge, including
     * pixmbps, VRAM, AGP.  This is the generbl bccelerbtion method (bs
     * opposed to residing in system memory).
     */
    public boolebn isAccelerbted() {
        return bccelerbted;
    }

    /**
     * Returns <code>true</code> if the <code>VolbtileImbge</code>
     * described by this <code>ImbgeCbpbbilities</code> cbn lose
     * its surfbces.
     * @return whether or not b volbtile imbge is subject to losing its surfbces
     * bt the whim of the operbting system.
     */
    public boolebn isTrueVolbtile() {
        return fblse;
    }

    /**
     * @return b copy of this ImbgeCbpbbilities object.
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            // Since we implement Clonebble, this should never hbppen
            throw new InternblError(e);
        }
    }

}
