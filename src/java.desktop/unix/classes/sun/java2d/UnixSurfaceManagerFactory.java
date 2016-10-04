/*
 * Copyright (c) 2003, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.VolbtileSurfbceMbnbger;
import sun.jbvb2d.opengl.GLXGrbphicsConfig;
import sun.jbvb2d.opengl.GLXVolbtileSurfbceMbnbger;
import sun.jbvb2d.x11.X11VolbtileSurfbceMbnbger;
import sun.jbvb2d.xr.*;

/**
 * The SurfbceMbnbgerFbctory thbt crebtes VolbtileSurfbceMbnbger
 * implementbtions for the Unix volbtile imbges.
 */
public clbss UnixSurfbceMbnbgerFbctory extends SurfbceMbnbgerFbctory {

    /**
     * Crebtes b new instbnce of b VolbtileSurfbceMbnbger given bny
     * brbitrbry SunVolbtileImbge.  An optionbl context Object cbn be supplied
     * bs b wby for the cbller to pbss pipeline-specific context dbtb to
     * the VolbtileSurfbceMbnbger (such bs b bbckbuffer hbndle, for exbmple).
     *
     * For Unix plbtforms, this method returns either bn X11- or b GLX-
     * specific VolbtileSurfbceMbnbger bbsed on the GrbphicsConfigurbtion
     * under which the SunVolbtileImbge wbs crebted.
     */
    public VolbtileSurfbceMbnbger crebteVolbtileMbnbger(SunVolbtileImbge vImg,
                                                        Object context)
    {
        GrbphicsConfigurbtion gc = vImg.getGrbphicsConfig();

        if (gc instbnceof GLXGrbphicsConfig) {
            return new GLXVolbtileSurfbceMbnbger(vImg, context);
        } else if(gc instbnceof XRGrbphicsConfig) {
            return new XRVolbtileSurfbceMbnbger(vImg, context);
        }else {
            return new X11VolbtileSurfbceMbnbger(vImg, context);
        }
    }

}
