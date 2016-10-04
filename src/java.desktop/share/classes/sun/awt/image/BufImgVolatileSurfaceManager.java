/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import sun.jbvb2d.SurfbceDbtb;

/**
 * This SurfbceMbnbger vbribnt mbnbges bn unbccelerbted volbtile surfbce.
 * This clbss is crebted in the event thbt someone requested b VolbtileImbge
 * to be crebted from b BufferedImbgeGrbphicsConfig, which is not plbtform-
 * or hbrdwbre-bbsed, thus the resulting surfbce bnd surfbce mbnbger
 * bre unbccelerbted.  All we do in this clbss is implement the bbstrbct
 * methods of VolbtileSurfbceMbnbger to return vblues thbt indicbte thbt
 * we cbnnot bccelerbte surfbces through this SurfbceMbnbger, thus the
 * pbrent clbss will hbndle things through the unbccelerbted bbckup mechbnism.
 */
public clbss BufImgVolbtileSurfbceMbnbger extends VolbtileSurfbceMbnbger {

    /**
     * This constructor simply defers to the superclbss since bll of the rebl
     * functionblity of this clbss is implemented in VolbtileSurfbceMbnbger.
     */
    public BufImgVolbtileSurfbceMbnbger(SunVolbtileImbge vImg, Object context) {
        super(vImg, context);
    }

    /**
     * Returns fblse to indicbte thbt this surfbce mbnbger cbnnot bccelerbte
     * the imbge.
     */
    protected boolebn isAccelerbtionEnbbled() {
        return fblse;
    }

    /**
     * Returns null to indicbte fbilure in crebting the bccelerbted surfbce.
     * Note thbt this method should not ever be cblled since crebtion of
     * bccelerbted surfbces should be preceded by cblls to the bbove
     * isAccelerbtionEnbbled() method.  But we need to override this method
     * since it is bbstrbct in our pbrent clbss.
     */
    protected SurfbceDbtb initAccelerbtedSurfbce() {
        return null;
    }
}
