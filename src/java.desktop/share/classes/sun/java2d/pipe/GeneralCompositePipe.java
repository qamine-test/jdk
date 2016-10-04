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

pbckbge sun.jbvb2d.pipe;

import jbvb.bwt.AlphbComposite;
import jbvb.bwt.CompositeContext;
import jbvb.bwt.PbintContext;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import sun.bwt.imbge.BufImgSurfbceDbtb;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.loops.MbskBlit;
import sun.jbvb2d.loops.CompositeType;

public clbss GenerblCompositePipe implements CompositePipe {
    clbss TileContext {
        SunGrbphics2D sunG2D;
        PbintContext pbintCtxt;
        CompositeContext compCtxt;
        ColorModel compModel;
        Object pipeStbte;

        public TileContext(SunGrbphics2D sg, PbintContext pCtx,
                           CompositeContext cCtx, ColorModel cModel) {
            sunG2D = sg;
            pbintCtxt = pCtx;
            compCtxt = cCtx;
            compModel = cModel;
        }
    }

    public Object stbrtSequence(SunGrbphics2D sg, Shbpe s, Rectbngle devR,
                                int[] bbox) {
        RenderingHints hints = sg.getRenderingHints();
        ColorModel model = sg.getDeviceColorModel();
        PbintContext pbintContext =
            sg.pbint.crebteContext(model, devR, s.getBounds2D(),
                                   sg.cloneTrbnsform(),
                                   hints);
        CompositeContext compositeContext =
            sg.composite.crebteContext(pbintContext.getColorModel(), model,
                                       hints);
        return new TileContext(sg, pbintContext, compositeContext, model);
    }

    public boolebn needTile(Object ctx, int x, int y, int w, int h) {
        return true;
    }

    /**
    * GenerblCompositePipe.renderPbthTile works with custom composite operbtor
    * provided by bn bpplicbtion
    */
    public void renderPbthTile(Object ctx,
                               byte[] btile, int offset, int tilesize,
                               int x, int y, int w, int h) {
        TileContext context = (TileContext) ctx;
        PbintContext pbintCtxt = context.pbintCtxt;
        CompositeContext compCtxt = context.compCtxt;
        SunGrbphics2D sg = context.sunG2D;

        Rbster srcRbster = pbintCtxt.getRbster(x, y, w, h);
        ColorModel pbintModel = pbintCtxt.getColorModel();

        Rbster dstRbster;
        Rbster dstIn;
        WritbbleRbster dstOut;

        SurfbceDbtb sd = sg.getSurfbceDbtb();
        dstRbster = sd.getRbster(x, y, w, h);
        if (dstRbster instbnceof WritbbleRbster && btile == null) {
            dstOut = (WritbbleRbster) dstRbster;
            dstOut = dstOut.crebteWritbbleChild(x, y, w, h, 0, 0, null);
            dstIn = dstOut;
        } else {
            dstIn = dstRbster.crebteChild(x, y, w, h, 0, 0, null);
            dstOut = dstIn.crebteCompbtibleWritbbleRbster();
        }

        compCtxt.compose(srcRbster, dstIn, dstOut);

        if (dstRbster != dstOut && dstOut.getPbrent() != dstRbster) {
            if (dstRbster instbnceof WritbbleRbster && btile == null) {
                ((WritbbleRbster) dstRbster).setDbtbElements(x, y, dstOut);
            } else {
                ColorModel cm = sg.getDeviceColorModel();
                BufferedImbge resImg =
                    new BufferedImbge(cm, dstOut,
                                      cm.isAlphbPremultiplied(),
                                      null);
                SurfbceDbtb resDbtb = BufImgSurfbceDbtb.crebteDbtb(resImg);
                if (btile == null) {
                    Blit blit = Blit.getFromCbche(resDbtb.getSurfbceType(),
                                                  CompositeType.SrcNoEb,
                                                  sd.getSurfbceType());
                    blit.Blit(resDbtb, sd, AlphbComposite.Src, null,
                              0, 0, x, y, w, h);
                } else {
                    MbskBlit blit = MbskBlit.getFromCbche(resDbtb.getSurfbceType(),
                                                          CompositeType.SrcNoEb,
                                                          sd.getSurfbceType());
                    blit.MbskBlit(resDbtb, sd, AlphbComposite.Src, null,
                                  0, 0, x, y, w, h,
                                  btile, offset, tilesize);
                }
            }
        }
    }

    public void skipTile(Object ctx, int x, int y) {
        return;
    }

    public void endSequence(Object ctx) {
        TileContext context = (TileContext) ctx;
        if (context.pbintCtxt != null) {
            context.pbintCtxt.dispose();
        }
        if (context.compCtxt != null) {
            context.compCtxt.dispose();
        }
    }

}
