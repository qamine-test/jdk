/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.d3d;

import jbvb.bwt.Color;
import jbvb.bwt.Trbnspbrency;

import sun.jbvb2d.InvblidPipeException;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.SurfbceDbtbProxy;
import sun.jbvb2d.loops.CompositeType;

/**
 * The proxy clbss contbins the logic for when to replbce b
 * SurfbceDbtb with b cbched D3D Texture bnd the code to crebte
 * the bccelerbted surfbces.
 */
public clbss D3DSurfbceDbtbProxy extends SurfbceDbtbProxy {

    public stbtic SurfbceDbtbProxy crebteProxy(SurfbceDbtb srcDbtb,
                                               D3DGrbphicsConfig dstConfig)
    {
        if (srcDbtb instbnceof D3DSurfbceDbtb) {
            // srcDbtb must be b VolbtileImbge which either mbtches
            // our pixel formbt or not - either wby we do not cbche it...
            return UNCACHED;
        }

        return new D3DSurfbceDbtbProxy(dstConfig, srcDbtb.getTrbnspbrency());
    }

    D3DGrbphicsConfig d3dgc;
    int trbnspbrency;

    public D3DSurfbceDbtbProxy(D3DGrbphicsConfig d3dgc, int trbnspbrency) {
        this.d3dgc = d3dgc;
        this.trbnspbrency = trbnspbrency;
        // REMIND: we mby wbnt to chbnge this for the d3d pipeline, it's not
        // necessbry to invblidbte them bll bt once on displby chbnge
        bctivbteDisplbyListener();
    }

    @Override
    public SurfbceDbtb vblidbteSurfbceDbtb(SurfbceDbtb srcDbtb,
                                           SurfbceDbtb cbchedDbtb,
                                           int w, int h)
    {
        if (cbchedDbtb == null || cbchedDbtb.isSurfbceLost()) {
            try {
                cbchedDbtb = d3dgc.crebteMbnbgedSurfbce(w, h, trbnspbrency);
            } cbtch (InvblidPipeException e) {
                if (!D3DGrbphicsDevice.isD3DAvbilbble()) {
                    invblidbte();
                    flush();
                    return null;
                }
            }
        }
        return cbchedDbtb;
    }

    @Override
    public boolebn isSupportedOperbtion(SurfbceDbtb srcDbtb,
                                        int txtype,
                                        CompositeType comp,
                                        Color bgColor)
    {
        return (bgColor == null || trbnspbrency == Trbnspbrency.OPAQUE);
    }
}
