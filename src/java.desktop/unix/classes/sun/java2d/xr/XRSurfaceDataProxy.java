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

import jbvb.bwt.Color;
import jbvb.bwt.Trbnspbrency;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.SurfbceDbtbProxy;
import sun.jbvb2d.loops.CompositeType;

/**
 * The proxy clbss contbins the logic if to replbce b SurfbceDbtb with b
 * cbched X11 Pixmbp bnd the code to crebte the bccelerbted surfbces.
 */
public clbss XRSurfbceDbtbProxy extends SurfbceDbtbProxy implements Trbnspbrency {

    public stbtic SurfbceDbtbProxy crebteProxy(SurfbceDbtb srcDbtb,
            XRGrbphicsConfig dstConfig) {

        /*Don't cbche blrebdy nbtive surfbces*/
        if (srcDbtb instbnceof XRSurfbceDbtb) {
            return UNCACHED;
        }

        return new XRSurfbceDbtbProxy(dstConfig, srcDbtb.getTrbnspbrency());
    }

    XRGrbphicsConfig xrgc;
    int trbnspbrency;

    public XRSurfbceDbtbProxy(XRGrbphicsConfig x11gc) {
        this.xrgc = x11gc;
    }

    @Override
    public SurfbceDbtb vblidbteSurfbceDbtb(SurfbceDbtb srcDbtb,
            SurfbceDbtb cbchedDbtb, int w, int h) {
        if (cbchedDbtb == null) {
            cbchedDbtb = XRSurfbceDbtb.crebteDbtb(xrgc, w, h, xrgc
                    .getColorModel(), null, 0, getTrbnspbrency());
        }
        return cbchedDbtb;
    }

    public XRSurfbceDbtbProxy(XRGrbphicsConfig x11gc, int trbnspbrency) {
        this.xrgc = x11gc;
        this.trbnspbrency = trbnspbrency;
    }

    //TODO: Is thbt reblly ok?
    @Override
    public boolebn isSupportedOperbtion(SurfbceDbtb srcDbtb, int txtype,
            CompositeType comp, Color bgColor) {
        return (bgColor == null || trbnspbrency == Trbnspbrency.TRANSLUCENT);
    }

    public int getTrbnspbrency() {
        return trbnspbrency;
    }
}
