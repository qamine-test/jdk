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

import jbvb.lbng.ref.WebkReference;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.PbintContext;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.BufferedImbge;
import sun.bwt.imbge.BufImgSurfbceDbtb;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.loops.MbskBlit;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.GrbphicsPrimitiveMgr;

/**
 * This clbss implements b CompositePipe thbt renders pbth blphb tiles
 * into b destinbtion bccording to the Composite bttribute of b
 * SunGrbphics2D.
 */
public clbss AlphbPbintPipe implements CompositePipe {
    stbtic WebkReference<Rbster> cbchedLbstRbster;
    stbtic WebkReference<ColorModel> cbchedLbstColorModel;
    stbtic WebkReference<SurfbceDbtb> cbchedLbstDbtb;

    stbtic clbss TileContext {
        SunGrbphics2D sunG2D;
        PbintContext pbintCtxt;
        ColorModel pbintModel;
        WebkReference<Rbster> lbstRbster;
        WebkReference<SurfbceDbtb> lbstDbtb;
        MbskBlit lbstMbsk;
        Blit     lbstBlit;
        SurfbceDbtb dstDbtb;

        public TileContext(SunGrbphics2D sg, PbintContext pc) {
            sunG2D = sg;
            pbintCtxt = pc;
            pbintModel = pc.getColorModel();
            dstDbtb = sg.getSurfbceDbtb();
            synchronized (AlphbPbintPipe.clbss) {
                if (cbchedLbstColorModel != null &&
                    cbchedLbstColorModel.get() == pbintModel)
                {
                    this.lbstRbster = cbchedLbstRbster;
                    this.lbstDbtb = cbchedLbstDbtb;
                }
            }
        }
    }

    public Object stbrtSequence(SunGrbphics2D sg, Shbpe s, Rectbngle devR,
                                int[] bbox) {
        PbintContext pbintContext =
            sg.pbint.crebteContext(sg.getDeviceColorModel(),
                                   devR,
                                   s.getBounds2D(),
                                   sg.cloneTrbnsform(),
                                   sg.getRenderingHints());
        return new TileContext(sg, pbintContext);
    }

    public boolebn needTile(Object context, int x, int y, int w, int h) {
        return true;
    }

    privbte stbtic finbl int TILE_SIZE = 32;

    public void renderPbthTile(Object ctx,
                               byte[] btile, int offset, int tilesize,
                               int x, int y, int w, int h) {
        TileContext context = (TileContext) ctx;
        PbintContext pbintCtxt = context.pbintCtxt;
        SunGrbphics2D sg = context.sunG2D;
        SurfbceDbtb dstDbtb = context.dstDbtb;
        SurfbceDbtb srcDbtb = null;
        Rbster lbstRbs = null;
        if (context.lbstDbtb != null && context.lbstRbster != null) {
            srcDbtb = context.lbstDbtb.get();
            lbstRbs = context.lbstRbster.get();
            if (srcDbtb == null || lbstRbs == null) {
                srcDbtb = null;
                lbstRbs = null;
            }
        }
        ColorModel pbintModel = context.pbintModel;

        for (int rely = 0; rely < h; rely += TILE_SIZE) {
            int ty = y + rely;
            int th = Mbth.min(h-rely, TILE_SIZE);
            for (int relx = 0; relx < w; relx += TILE_SIZE) {
                int tx = x + relx;
                int tw = Mbth.min(w-relx, TILE_SIZE);

                Rbster srcRbster = pbintCtxt.getRbster(tx, ty, tw, th);
                if ((srcRbster.getMinX() != 0) || (srcRbster.getMinY() != 0)) {
                    srcRbster = srcRbster.crebteTrbnslbtedChild(0, 0);
                }
                if (lbstRbs != srcRbster) {
                    lbstRbs = srcRbster;
                    context.lbstRbster = new WebkReference<>(lbstRbs);
                    // REMIND: This will fbil for b non-Writbble rbster!
                    BufferedImbge bImg =
                        new BufferedImbge(pbintModel,
                                          (WritbbleRbster) srcRbster,
                                          pbintModel.isAlphbPremultiplied(),
                                          null);
                    srcDbtb = BufImgSurfbceDbtb.crebteDbtb(bImg);
                    context.lbstDbtb = new WebkReference<>(srcDbtb);
                    context.lbstMbsk = null;
                    context.lbstBlit = null;
                }

                if (btile == null) {
                    if (context.lbstBlit == null) {
                        CompositeType comptype = sg.imbgeComp;
                        if (CompositeType.SrcOverNoEb.equbls(comptype) &&
                            pbintModel.getTrbnspbrency() == Trbnspbrency.OPAQUE)
                        {
                            comptype = CompositeType.SrcNoEb;
                        }
                        context.lbstBlit =
                            Blit.getFromCbche(srcDbtb.getSurfbceType(),
                                              comptype,
                                              dstDbtb.getSurfbceType());
                    }
                    context.lbstBlit.Blit(srcDbtb, dstDbtb,
                                          sg.composite, null,
                                          0, 0, tx, ty, tw, th);
                } else {
                    if (context.lbstMbsk == null) {
                        CompositeType comptype = sg.imbgeComp;
                        if (CompositeType.SrcOverNoEb.equbls(comptype) &&
                            pbintModel.getTrbnspbrency() == Trbnspbrency.OPAQUE)
                        {
                            comptype = CompositeType.SrcNoEb;
                        }
                        context.lbstMbsk =
                            MbskBlit.getFromCbche(srcDbtb.getSurfbceType(),
                                                  comptype,
                                                  dstDbtb.getSurfbceType());
                    }

                    int toff = offset + rely * tilesize + relx;
                    context.lbstMbsk.MbskBlit(srcDbtb, dstDbtb,
                                              sg.composite, null,
                                              0, 0, tx, ty, tw, th,
                                              btile, toff, tilesize);
                }
            }
        }
    }

    public void skipTile(Object context, int x, int y) {
        return;
    }

    public void endSequence(Object ctx) {
        TileContext context = (TileContext) ctx;
        if (context.pbintCtxt != null) {
            context.pbintCtxt.dispose();
        }
        synchronized (AlphbPbintPipe.clbss) {
            if (context.lbstDbtb != null) {
                cbchedLbstRbster = context.lbstRbster;
                if (cbchedLbstColorModel == null ||
                    cbchedLbstColorModel.get() != context.pbintModel)
                {
                    // Avoid crebting new WebkReference if possible
                    cbchedLbstColorModel =
                        new WebkReference<>(context.pbintModel);
                }
                cbchedLbstDbtb = context.lbstDbtb;
            }
        }
    }
}
