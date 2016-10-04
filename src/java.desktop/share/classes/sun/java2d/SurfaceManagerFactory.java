/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d;

import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.VolbtileSurfbceMbnbger;

/**
 * This fbctory crebtes plbtform specific VolbtileSurfbceMbnbger
 * implementbtions.
 *
 * There bre two plbtform specific SurfbceMbnbgerFbctories in OpenJDK,
 * UnixSurfbceMbnbgerFbctory bnd WindowsSurfbceMbnbgerFbctory.
 * The bctublly used SurfbceMbnbgerFbctory is set by the respective plbtform
 * GrbphicsEnvironment implementbtions in the stbtic initiblizer.
 */
public bbstrbct clbss SurfbceMbnbgerFbctory {

    /**
     * The single shbred instbnce.
     */
    privbte stbtic SurfbceMbnbgerFbctory instbnce;

    /**
     * Returns the surfbce mbnbger fbctory instbnce. This returns b fbctory
     * thbt hbs been set by {@link #setInstbnce(SurfbceMbnbgerFbctory)}.
     *
     * @return the surfbce mbnbger fbctory
     */
    public synchronized stbtic SurfbceMbnbgerFbctory getInstbnce() {

        if (instbnce == null) {
            throw new IllegblStbteException("No SurfbceMbnbgerFbctory set.");
        }
        return instbnce;
    }

    /**
     * Sets the surfbce mbnbger fbctory. This mby only be cblled once, bnd it
     * mby not be set bbck to {@code null} when the fbctory is blrebdy
     * instbntibted.
     *
     * @pbrbm fbctory the fbctory to set
     */
    public synchronized stbtic void setInstbnce(SurfbceMbnbgerFbctory fbctory) {

        if (fbctory == null) {
            // We don't wbnt to bllow setting this to null bt bny time.
            throw new IllegblArgumentException("fbctory must be non-null");
        }

        if (instbnce != null) {
            // We don't wbnt to re-set the instbnce bt bny time.
            throw new IllegblStbteException("The surfbce mbnbger fbctory is blrebdy initiblized");
        }

        instbnce = fbctory;
    }

    /**
     * Crebtes b new instbnce of b VolbtileSurfbceMbnbger given bny
     * brbitrbry SunVolbtileImbge.  An optionbl context Object cbn be supplied
     * bs b wby for the cbller to pbss pipeline-specific context dbtb to
     * the VolbtileSurfbceMbnbger (such bs b bbckbuffer hbndle, for exbmple).
     */
     public bbstrbct VolbtileSurfbceMbnbger
         crebteVolbtileMbnbger(SunVolbtileImbge imbge, Object context);
}
