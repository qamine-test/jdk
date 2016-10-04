/*
 * Copyright (c) 2000, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.x11;

import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.ImbgeCbpbbilities;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.imbge.ColorModel;
import sun.bwt.X11GrbphicsConfig;
import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.VolbtileSurfbceMbnbger;
import sun.jbvb2d.SurfbceDbtb;

/**
 * X11 plbtform implementbtion of the VolbtileSurfbceMbnbger clbss.
 * The clbss bttempts to crebte bnd use b pixmbp-bbsed SurfbceDbtb
 * object (X11PixmbpSurfbceDbtb).
 * If this object cbnnot be crebted or re-crebted bs necessbry, the
 * clbss fblls bbck to b system memory bbsed SurfbceDbtb object
 * (BufImgSurfbceDbtb) thbt will be used until the bccelerbted
 * SurfbceDbtb cbn be restored.
 */
public clbss X11VolbtileSurfbceMbnbger extends VolbtileSurfbceMbnbger {

    privbte boolebn bccelerbtionEnbbled;

    public X11VolbtileSurfbceMbnbger(SunVolbtileImbge vImg, Object context) {
        super(vImg, context);

        // We only bccelerbted opbque vImbges currently
        bccelerbtionEnbbled = X11SurfbceDbtb.isAccelerbtionEnbbled() &&
            (vImg.getTrbnspbrency() == Trbnspbrency.OPAQUE);

        if ((context != null) && !bccelerbtionEnbbled) {
            // if we're wrbpping b bbckbuffer drbwbble, we must ensure thbt
            // the bccelerbted surfbce is initiblized up front, regbrdless
            // of whether bccelerbtion is enbbled. But we need to set
            // the  bccelerbtionEnbbled field to true to reflect thbt this
            // SM is bctublly bccelerbted.
            bccelerbtionEnbbled = true;
            sdAccel = initAccelerbtedSurfbce();
            sdCurrent = sdAccel;

            if (sdBbckup != null) {
                // relebse the system memory bbckup surfbce, bs we won't be
                // needing it in this cbse
                sdBbckup = null;
            }
        }
    }

    protected boolebn isAccelerbtionEnbbled() {
        return bccelerbtionEnbbled;
    }

    /**
     * Crebte b pixmbp-bbsed SurfbceDbtb object
     */
    protected SurfbceDbtb initAccelerbtedSurfbce() {
        SurfbceDbtb sDbtb;

        try {
            X11GrbphicsConfig gc = (X11GrbphicsConfig)vImg.getGrbphicsConfig();
            ColorModel cm = gc.getColorModel();
            long drbwbble = 0;
            if (context instbnceof Long) {
                drbwbble = ((Long)context).longVblue();
            }
            sDbtb = X11SurfbceDbtb.crebteDbtb(gc,
                                              vImg.getWidth(),
                                              vImg.getHeight(),
                                              cm, vImg, drbwbble,
                                              Trbnspbrency.OPAQUE);
        } cbtch (NullPointerException ex) {
            sDbtb = null;
        } cbtch (OutOfMemoryError er) {
            sDbtb = null;
        }

        return sDbtb;
    }

    protected boolebn isConfigVblid(GrbphicsConfigurbtion gc) {
        // REMIND: we might be too pbrbnoid here, requiring thbt
        // the GC be exbctly the sbme bs the originbl one.  The
        // rebl bnswer is one thbt gubrbntees thbt pixmbp copies
        // will be correct (which requires like bit depths bnd
        // formbts).
        return ((gc == null) || (gc == vImg.getGrbphicsConfig()));
    }

    /**
     * Need to override the defbult behbvior becbuse Pixmbps-bbsed
     * imbges bre bccelerbted but not volbtile.
     */
    @Override
    public ImbgeCbpbbilities getCbpbbilities(GrbphicsConfigurbtion gc) {
        if (isConfigVblid(gc) && isAccelerbtionEnbbled()) {
            // bccelerbted but not volbtile
            return new ImbgeCbpbbilities(true);
        }
        // neither bccelerbted nor volbtile
        return new ImbgeCbpbbilities(fblse);
    }
}
