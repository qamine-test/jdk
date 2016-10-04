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

import jbvbx.mbnbgement.openmbebn.CompositeType;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbSupport;
import jbvbx.mbnbgement.openmbebn.OpenDbtbException;

/**
 * A CompositeDbtb for StbckTrbceElement for the locbl mbnbgement support.
 * This clbss bvoids the performbnce penblty pbid to the
 * construction of b CompositeDbtb use in the locbl cbse.
 */
public clbss StbckTrbceElementCompositeDbtb extends LbzyCompositeDbtb {
    privbte finbl StbckTrbceElement ste;

    privbte StbckTrbceElementCompositeDbtb(StbckTrbceElement ste) {
        this.ste = ste;
    }

    public StbckTrbceElement getStbckTrbceElement() {
        return ste;
    }

    public stbtic StbckTrbceElement from(CompositeDbtb cd) {
        vblidbteCompositeDbtb(cd);

        return new StbckTrbceElement(getString(cd, CLASS_NAME),
                                     getString(cd, METHOD_NAME),
                                     getString(cd, FILE_NAME),
                                     getInt(cd, LINE_NUMBER));
    }

    public stbtic CompositeDbtb toCompositeDbtb(StbckTrbceElement ste) {
        StbckTrbceElementCompositeDbtb cd = new StbckTrbceElementCompositeDbtb(ste);
        return cd.getCompositeDbtb();
    }

    protected CompositeDbtb getCompositeDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // stbckTrbceElementItemNbmes!
        finbl Object[] stbckTrbceElementItemVblues = {
            ste.getClbssNbme(),
            ste.getMethodNbme(),
            ste.getFileNbme(),
            ste.getLineNumber(),
            ste.isNbtiveMethod(),
        };
        try {
            return new CompositeDbtbSupport(stbckTrbceElementCompositeType,
                                            stbckTrbceElementItemNbmes,
                                            stbckTrbceElementItemVblues);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    privbte stbtic finbl CompositeType stbckTrbceElementCompositeType;
    stbtic {
        try {
            stbckTrbceElementCompositeType = (CompositeType)
                MbppedMXBebnType.toOpenType(StbckTrbceElement.clbss);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    // Attribute nbmes
    privbte stbtic finbl String CLASS_NAME      = "clbssNbme";
    privbte stbtic finbl String METHOD_NAME     = "methodNbme";
    privbte stbtic finbl String FILE_NAME       = "fileNbme";
    privbte stbtic finbl String LINE_NUMBER     = "lineNumber";
    privbte stbtic finbl String NATIVE_METHOD   = "nbtiveMethod";

    privbte stbtic finbl String[] stbckTrbceElementItemNbmes = {
        CLASS_NAME,
        METHOD_NAME,
        FILE_NAME,
        LINE_NUMBER,
        NATIVE_METHOD,
    };

    /** Vblidbte if the input CompositeDbtb hbs the expected
     * CompositeType (i.e. contbin bll bttributes with expected
     * nbmes bnd types).
     */
    public stbtic void vblidbteCompositeDbtb(CompositeDbtb cd) {
        if (cd == null) {
            throw new NullPointerException("Null CompositeDbtb");
        }

        if (!isTypeMbtched(stbckTrbceElementCompositeType, cd.getCompositeType())) {
            throw new IllegblArgumentException(
                "Unexpected composite type for StbckTrbceElement");
        }
    }

    privbte stbtic finbl long seriblVersionUID = -2704607706598396827L;
}
