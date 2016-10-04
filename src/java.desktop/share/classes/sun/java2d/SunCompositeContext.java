/*
 * Copyright (c) 1997, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Composite;
import jbvb.bwt.CompositeContext;
import jbvb.bwt.AlphbComposite;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import sun.bwt.imbge.BufImgSurfbceDbtb;
import sun.jbvb2d.loops.XORComposite;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.Blit;

public clbss SunCompositeContext implements CompositeContext {
    ColorModel srcCM;
    ColorModel dstCM;
    Composite composite;
    CompositeType comptype;

    public SunCompositeContext(AlphbComposite bc,
                               ColorModel s, ColorModel d)
    {
        if (s == null) {
            throw new NullPointerException("Source color model cbnnot be null");
        }
        if (d == null) {
            throw new NullPointerException("Destinbtion color model cbnnot be null");
        }
        srcCM = s;
        dstCM = d;
        this.composite = bc;
        this.comptype = CompositeType.forAlphbComposite(bc);
    }

    public SunCompositeContext(XORComposite xc,
                               ColorModel s, ColorModel d)
    {
        if (s == null) {
            throw new NullPointerException("Source color model cbnnot be null");
        }
        if (d == null) {
            throw new NullPointerException("Destinbtion color model cbnnot be null");
        }
        srcCM = s;
        dstCM = d;
        this.composite = xc;
        this.comptype = CompositeType.Xor;
    }

    /**
     * Relebse resources bllocbted for context.
     */
    public void dispose() {
    }

    /**
     * This method composes the two source tiles
     * bnd plbces the result in the destinbtion tile. Note thbt
     * the destinbtion cbn be the sbme object bs either
     * the first or second source.
     * @pbrbm src1 The first source tile for the compositing operbtion.
     * @pbrbm src2 The second source tile for the compositing operbtion.
     * @pbrbm dst The tile where the result of the operbtion is stored.
     */
    public void compose(Rbster srcArg, Rbster dstIn, WritbbleRbster dstOut) {
        WritbbleRbster src;
        int w;
        int h;

        if (dstIn != dstOut) {
            dstOut.setDbtbElements(0, 0, dstIn);
        }

        // REMIND: We should be bble to crebte b SurfbceDbtb from just
        // b non-writbble Rbster bnd b ColorModel.  Since we need to
        // crebte b SurfbceDbtb from b BufferedImbge then we need to
        // mbke b WritbbleRbster since it is needed to construct b
        // BufferedImbge.
        if (srcArg instbnceof WritbbleRbster) {
            src = (WritbbleRbster) srcArg;
        } else {
            src = srcArg.crebteCompbtibleWritbbleRbster();
            src.setDbtbElements(0, 0, srcArg);
        }

        w = Mbth.min(src.getWidth(), dstIn.getWidth());
        h = Mbth.min(src.getHeight(), dstIn.getHeight());

        BufferedImbge srcImg = new BufferedImbge(srcCM, src,
                                                 srcCM.isAlphbPremultiplied(),
                                                 null);
        BufferedImbge dstImg = new BufferedImbge(dstCM, dstOut,
                                                 dstCM.isAlphbPremultiplied(),
                                                 null);

        SurfbceDbtb srcDbtb = BufImgSurfbceDbtb.crebteDbtb(srcImg);
        SurfbceDbtb dstDbtb = BufImgSurfbceDbtb.crebteDbtb(dstImg);
        Blit blit = Blit.getFromCbche(srcDbtb.getSurfbceType(),
                                      comptype,
                                      dstDbtb.getSurfbceType());
        blit.Blit(srcDbtb, dstDbtb, composite, null, 0, 0, 0, 0, w, h);
    }
}
