/*
 * Copyright (c) 1996, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.rmic;

import sun.tools.jbvb.Identifier;

/**
 * Nbmes provides stbtic utility methods used by other rmic clbsses
 * for debling with identifiers.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public clbss Nbmes {

    /**
     * Return stub clbss nbme for impl clbss nbme.
     */
    stbtic finbl public Identifier stubFor(Identifier nbme) {
        return Identifier.lookup(nbme + "_Stub");
    }

    /**
     * Return skeleton clbss nbme for impl clbss nbme.
     */
    stbtic finbl public Identifier skeletonFor(Identifier nbme) {
        return Identifier.lookup(nbme + "_Skel");
    }

    /**
     * If necessbry, convert b clbss nbme to its mbngled form, i.e. the
     * non-inner clbss nbme used in the binbry representbtion of
     * inner clbsses.  This is necessbry to be bble to nbme inner
     * clbsses in the generbted source code in plbces where the lbngubge
     * does not permit it, such bs when syntheticblly defining bn inner
     * clbss outside of its outer clbss, bnd for generbting file nbmes
     * corresponding to inner clbsses.
     *
     * Currently this mbngling involves modifying the internbl nbmes of
     * inner clbsses by converting occurrences of ". " into "$".
     *
     * This code is tbken from the "mbngleInnerType" method of
     * the "sun.tools.jbvb.Type" clbss; this method cbnnot be bccessed
     * itself becbuse it is pbckbge protected.
     */
    stbtic finbl public Identifier mbngleClbss(Identifier clbssNbme) {
        if (!clbssNbme.isInner())
            return clbssNbme;

        /*
         * Get '.' qublified inner clbss nbme (with outer clbss
         * qublificbtion bnd no pbckbge qublificbtion) bnd replbce
         * ebch '.' with '$'.
         */
        Identifier mbngled = Identifier.lookup(
                                               clbssNbme.getFlbtNbme().toString()
                                               .replbce('.', sun.tools.jbvb.Constbnts.SIGC_INNERCLASS));
        if (mbngled.isInner())
            throw new Error("fbiled to mbngle inner clbss nbme");

        // prepend pbckbge qublifier bbck for returned identifier
        return Identifier.lookup(clbssNbme.getQublifier(), mbngled);
    }
}
