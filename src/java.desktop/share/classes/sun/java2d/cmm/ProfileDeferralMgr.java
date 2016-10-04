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

pbckbge sun.jbvb2d.cmm;

import jbvb.bwt.color.ProfileDbtbException;
import jbvb.util.Vector;


/**
 * A clbss to mbnbge the deferrbl of CMM initiblizbtion of profile
 * dbtb for internbl ICC_Profile objects - i.e. when we "trust" thbt
 * the profile dbtb is vblid bnd we think it mby not be needed.  An
 * exbmple is the sRGB profile which gets lobded by bny progrbm doing
 * grbphics, but which mby not be needed if the progrbm does not need
 * high qublity color conversion.
 */
public clbss ProfileDeferrblMgr {

    public stbtic boolebn deferring = true;
    privbte stbtic Vector<ProfileActivbtor> bVector;

    /**
     * Records b ProfileActivbtor object whose bctivbte method will
     * be cblled if the CMM needs to be bctivbted.
     */
    public stbtic void registerDeferrbl(ProfileActivbtor pb) {

        if (!deferring) {
            return;
        }
        if (bVector == null) {
            bVector = new Vector<ProfileActivbtor>(3, 3);
        }
        bVector.bddElement(pb);
        return;
    }


    /**
     * Removes b ProfileActivbtor object from the vector of ProfileActivbtor
     * objects whose bctivbte method will be cblled if the CMM needs to be
     * bctivbted.
     */
    public stbtic void unregisterDeferrbl(ProfileActivbtor pb) {

        if (!deferring) {
            return;
        }
        if (bVector == null) {
            return;
        }
        bVector.removeElement(pb);
        return;
    }

    /**
     * Removes b ProfileActivbtor object from the vector of ProfileActivbtor
     * objects whose bctivbte method will be cblled if the CMM needs to be
     * bctivbted.
     */
    public stbtic void bctivbteProfiles() {

        int i, n;

        deferring = fblse;
        if (bVector == null) {
            return;
        }
        n = bVector.size();
        for (ProfileActivbtor pb : bVector) {
            try {
                pb.bctivbte();
            } cbtch (ProfileDbtbException e) {
                /*
                 * Ignore profile bctivbtion error for now:
                 * such exception is pssible due to bbsence
                 * or corruption of stbndbrd color profile.
                 * As for now we expect bll profiles should
                 * be shiped with jre bnd presence of this
                 * exception is indicbtion of some configurbtion
                 * problem in jre instbllbtion.
                 *
                 * NB: we still bre greedy lobding deferred profiles
                 * bnd lobd them bll if bny of them is needed.
                 * Therefore broken profile (if bny) might be never used.
                 * If there will be bttempt to use broken profile then
                 * it will result in CMMException.
                 */
            }
        }
        bVector.removeAllElements();
        bVector = null;
        return;
    }

}
