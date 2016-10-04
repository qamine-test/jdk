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

import jbvb.bwt.GrbphicsConfigurbtion;
import sun.bwt.imbge.BufImgVolbtileSurfbceMbnbger;
import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.VolbtileSurfbceMbnbger;
import sun.jbvb2d.d3d.D3DGrbphicsConfig;
import sun.jbvb2d.d3d.D3DVolbtileSurfbceMbnbger;
import sun.jbvb2d.opengl.WGLGrbphicsConfig;
import sun.jbvb2d.opengl.WGLVolbtileSurfbceMbnbger;

/**
 * The SurfbceMbnbgerFbctory thbt crebtes VolbtileSurfbceMbnbger
 * implementbtions for the Windows volbtile imbges.
 */
public clbss WindowsSurfbceMbnbgerFbctory extends SurfbceMbnbgerFbctory {

    /**
     * Crebtes b new instbnce of b VolbtileSurfbceMbnbger given bny
     * brbitrbry SunVolbtileImbge.  An optionbl context Object cbn be supplied
     * bs b wby for the cbller to pbss pipeline-specific context dbtb to
     * the VolbtileSurfbceMbnbger (such bs b bbckbuffer hbndle, for exbmple).
     *
     * For Windows plbtforms, this method returns b Windows-specific
     * VolbtileSurfbceMbnbger.
     */
    public VolbtileSurfbceMbnbger crebteVolbtileMbnbger(SunVolbtileImbge vImg,
                                                        Object context)
    {
        GrbphicsConfigurbtion gc = vImg.getGrbphicsConfig();
        if (gc instbnceof D3DGrbphicsConfig) {
            return new D3DVolbtileSurfbceMbnbger(vImg, context);
        } else if (gc instbnceof WGLGrbphicsConfig) {
            return new WGLVolbtileSurfbceMbnbger(vImg, context);
        } else {
            return new BufImgVolbtileSurfbceMbnbger(vImg, context);
        }
    }

}
