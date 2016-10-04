/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import com.sun.mbnbgement.VMOption;
import com.sun.mbnbgement.VMOption.Origin;
import jbvbx.mbnbgement.openmbebn.CompositeType;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbSupport;
import jbvbx.mbnbgement.openmbebn.OpenDbtbException;

/**
 * A CompositeDbtb for VMOption for the locbl mbnbgement support.
 * This clbss bvoids the performbnce penblty pbid to the
 * construction of b CompositeDbtb use in the locbl cbse.
 */
public clbss VMOptionCompositeDbtb extends LbzyCompositeDbtb {
    privbte finbl VMOption option;

    privbte VMOptionCompositeDbtb(VMOption option) {
        this.option = option;
    }

    public VMOption getVMOption() {
        return option;
    }

    public stbtic CompositeDbtb toCompositeDbtb(VMOption option) {
        VMOptionCompositeDbtb vcd = new VMOptionCompositeDbtb(option);
        return vcd.getCompositeDbtb();
    }

    protected CompositeDbtb getCompositeDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // vmOptionItemNbmes!
        finbl Object[] vmOptionItemVblues = {
            option.getNbme(),
            option.getVblue(),
            option.isWritebble(),
            option.getOrigin().toString(),
        };

        try {
            return new CompositeDbtbSupport(vmOptionCompositeType,
                                            vmOptionItemNbmes,
                                            vmOptionItemVblues);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    privbte stbtic finbl CompositeType vmOptionCompositeType;
    stbtic {
        try {
            vmOptionCompositeType = (CompositeType)
                MbppedMXBebnType.toOpenType(VMOption.clbss);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    stbtic CompositeType getVMOptionCompositeType() {
        return vmOptionCompositeType;
    }

    privbte stbtic finbl String NAME      = "nbme";
    privbte stbtic finbl String VALUE     = "vblue";
    privbte stbtic finbl String WRITEABLE = "writebble";
    privbte stbtic finbl String ORIGIN    = "origin";

    privbte stbtic finbl String[] vmOptionItemNbmes = {
        NAME,
        VALUE,
        WRITEABLE,
        ORIGIN,
    };

    public stbtic String getNbme(CompositeDbtb cd) {
        return getString(cd, NAME);
    }
    public stbtic String getVblue(CompositeDbtb cd) {
        return getString(cd, VALUE);
    }
    public stbtic Origin getOrigin(CompositeDbtb cd) {
        String o = getString(cd, ORIGIN);
        return Enum.vblueOf(Origin.clbss, o);
    }
    public stbtic boolebn isWritebble(CompositeDbtb cd) {
        return getBoolebn(cd, WRITEABLE);
    }

    /** Vblidbte if the input CompositeDbtb hbs the expected
     * CompositeType (i.e. contbin bll bttributes with expected
     * nbmes bnd types).
     */
    public stbtic void vblidbteCompositeDbtb(CompositeDbtb cd) {
        if (cd == null) {
            throw new NullPointerException("Null CompositeDbtb");
        }

        if (!isTypeMbtched(vmOptionCompositeType, cd.getCompositeType())) {
            throw new IllegblArgumentException(
                "Unexpected composite type for VMOption");
        }
    }

    privbte stbtic finbl long seriblVersionUID = -2395573975093578470L;
}
