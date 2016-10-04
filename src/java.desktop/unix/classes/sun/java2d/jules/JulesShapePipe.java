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

pbckbge sun.jbvb2d.jules;

import jbvb.bwt.*;
import sun.bwt.*;
import sun.jbvb2d.*;
import sun.jbvb2d.pipe.*;
import sun.jbvb2d.xr.*;

public clbss JulesShbpePipe implements ShbpeDrbwPipe {

    XRCompositeMbnbger compMbn;
    JulesPbthBuf buf = new JulesPbthBuf();

    public JulesShbpePipe(XRCompositeMbnbger compMbn) {
        this.compMbn = compMbn;
    }

    /**
     * Common vblidbte method, used by bll XRRender functions to vblidbte the
     * destinbtion context.
     */
    privbte finbl void vblidbteSurfbce(SunGrbphics2D sg2d) {
        XRSurfbceDbtb xrsd = (XRSurfbceDbtb) sg2d.surfbceDbtb;
        xrsd.vblidbteAsDestinbtion(sg2d, sg2d.getCompClip());
        xrsd.mbskBuffer.vblidbteCompositeStbte(sg2d.composite, sg2d.trbnsform,
                                               sg2d.pbint, sg2d);
    }

    public void drbw(SunGrbphics2D sg2d, Shbpe s) {
        try {
            SunToolkit.bwtLock();
            vblidbteSurfbce(sg2d);
            XRSurfbceDbtb xrsd = (XRSurfbceDbtb) sg2d.surfbceDbtb;

            BbsicStroke bs;

            if (sg2d.stroke instbnceof BbsicStroke) {
                bs = (BbsicStroke) sg2d.stroke;
            } else { //TODO: Whbt hbppens in the cbse of b !BbsicStroke??
                s = sg2d.stroke.crebteStrokedShbpe(s);
                bs = null;
            }

            boolebn bdjust =
                (bs != null && sg2d.strokeHint != SunHints.INTVAL_STROKE_PURE);
            boolebn thin = (sg2d.strokeStbte <= SunGrbphics2D.STROKE_THINDASHED);

            TrbpezoidList trbps =
                 buf.tesselbteStroke(s, bs, thin, bdjust, true,
                                     sg2d.trbnsform, sg2d.getCompClip());
            compMbn.XRCompositeTrbps(xrsd.picture,
                                     sg2d.trbnsX, sg2d.trbnsY, trbps);

            buf.clebr();

        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    public void fill(SunGrbphics2D sg2d, Shbpe s) {
        try {
            SunToolkit.bwtLock();
            vblidbteSurfbce(sg2d);

            XRSurfbceDbtb xrsd = (XRSurfbceDbtb) sg2d.surfbceDbtb;

            TrbpezoidList trbps = buf.tesselbteFill(s, sg2d.trbnsform,
                                                    sg2d.getCompClip());
            compMbn.XRCompositeTrbps(xrsd.picture, 0, 0, trbps);

            buf.clebr();
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }
}
