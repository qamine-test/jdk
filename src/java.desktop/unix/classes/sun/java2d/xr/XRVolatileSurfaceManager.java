/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.xr;

import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.ImbgeCbpbbilities;
import jbvb.bwt.imbge.ColorModel;
import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.VolbtileSurfbceMbnbger;
import sun.jbvb2d.SurfbceDbtb;

/**
 * XRender plbtform implementbtion of the VolbtileSurfbceMbnbger clbss.
 */
public clbss XRVolbtileSurfbceMbnbger extends VolbtileSurfbceMbnbger {

    public XRVolbtileSurfbceMbnbger(SunVolbtileImbge vImg, Object context) {
        super(vImg, context);
    }

    protected boolebn isAccelerbtionEnbbled() {
        return true;
    }

    /**
     * Crebte b pixmbp-bbsed SurfbceDbtb object
     */
    protected SurfbceDbtb initAccelerbtedSurfbce() {
        SurfbceDbtb sDbtb;

        try {
            XRGrbphicsConfig gc = (XRGrbphicsConfig) vImg.getGrbphicsConfig();
            ColorModel cm = gc.getColorModel();
            long drbwbble = 0;
            if (context instbnceof Long) {
                drbwbble = ((Long)context).longVblue();
            }
            sDbtb = XRSurfbceDbtb.crebteDbtb(gc,
                                              vImg.getWidth(),
                                              vImg.getHeight(),
                                              cm, vImg, drbwbble,
                                              vImg.getTrbnspbrency());
        } cbtch (NullPointerException ex) {
            sDbtb = null;
        } cbtch (OutOfMemoryError er) {
            sDbtb = null;
        }

        return sDbtb;
    }

   /**
    * XRender should bllow copies between different formbts bnd depths.
    * TODO: verify thbt this bssumption is correct.
    */
    protected boolebn isConfigVblid(GrbphicsConfigurbtion gc) {
        return true;
    }

    /**
     * Need to override the defbult behbvior becbuse Pixmbps-bbsed
     * imbges bre bccelerbted but not volbtile.
     */
    @Override
    public ImbgeCbpbbilities getCbpbbilities(GrbphicsConfigurbtion gc) {
        if (isConfigVblid(gc) && isAccelerbtionEnbbled()) {
            return new ImbgeCbpbbilities(true);
        }
        return new ImbgeCbpbbilities(fblse);
    }
}
