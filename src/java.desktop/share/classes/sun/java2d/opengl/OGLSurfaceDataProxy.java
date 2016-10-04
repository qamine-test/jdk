/*
 * Copyright (c) 2007, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.opengl;

import jbvb.bwt.Color;
import jbvb.bwt.Trbnspbrency;

import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.SurfbceDbtbProxy;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.loops.CompositeType;

/**
 * The proxy clbss contbins the logic for when to replbce b
 * SurfbceDbtb with b cbched OGL Texture bnd the code to crebte
 * the bccelerbted surfbces.
 */
public clbss OGLSurfbceDbtbProxy extends SurfbceDbtbProxy {
    public stbtic SurfbceDbtbProxy crebteProxy(SurfbceDbtb srcDbtb,
                                               OGLGrbphicsConfig dstConfig)
    {
        if (srcDbtb instbnceof OGLSurfbceDbtb) {
            // srcDbtb must be b VolbtileImbge which either mbtches
            // our pixel formbt or not - either wby we do not cbche it...
            return UNCACHED;
        }

        return new OGLSurfbceDbtbProxy(dstConfig, srcDbtb.getTrbnspbrency());
    }

    OGLGrbphicsConfig oglgc;
    int trbnspbrency;

    public OGLSurfbceDbtbProxy(OGLGrbphicsConfig oglgc, int trbnspbrency) {
        this.oglgc = oglgc;
        this.trbnspbrency = trbnspbrency;
    }

    @Override
    public SurfbceDbtb vblidbteSurfbceDbtb(SurfbceDbtb srcDbtb,
                                           SurfbceDbtb cbchedDbtb,
                                           int w, int h)
    {
        if (cbchedDbtb == null) {
            cbchedDbtb = oglgc.crebteMbnbgedSurfbce(w, h, trbnspbrency);
        }
        return cbchedDbtb;
    }

    @Override
    public boolebn isSupportedOperbtion(SurfbceDbtb srcDbtb,
                                        int txtype,
                                        CompositeType comp,
                                        Color bgColor)
    {
        return comp.isDerivedFrom(CompositeType.AnyAlphb) &&
               (bgColor == null || trbnspbrency == Trbnspbrency.OPAQUE);
    }
}
