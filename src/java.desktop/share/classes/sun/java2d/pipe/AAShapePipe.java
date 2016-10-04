/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.BbsicStroke;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.PbthIterbtor;
import sun.bwt.SunHints;
import sun.jbvb2d.SunGrbphics2D;

/**
 * This clbss is used to convert rbw geometry into 8-bit blphb tiles
 * using bn AATileGenerbtor for bpplicbtion by the next stbge of
 * the pipeline.
 * This clbss sets up the Generbtor bnd computes the blphb tiles
 * bnd then pbsses them on to b CompositePipe object for pbinting.
 */
public clbss AAShbpePipe
    implements ShbpeDrbwPipe, PbrbllelogrbmPipe
{
    stbtic RenderingEngine renderengine = RenderingEngine.getInstbnce();

    CompositePipe outpipe;

    public AAShbpePipe(CompositePipe pipe) {
        outpipe = pipe;
    }

    public void drbw(SunGrbphics2D sg, Shbpe s) {
        BbsicStroke bs;

        if (sg.stroke instbnceof BbsicStroke) {
            bs = (BbsicStroke) sg.stroke;
        } else {
            s = sg.stroke.crebteStrokedShbpe(s);
            bs = null;
        }

        renderPbth(sg, s, bs);
    }

    public void fill(SunGrbphics2D sg, Shbpe s) {
        renderPbth(sg, s, null);
    }

    privbte stbtic Rectbngle2D computeBBox(double ux1, double uy1,
                                           double ux2, double uy2)
    {
        if ((ux2 -= ux1) < 0) {
            ux1 += ux2;
            ux2 = -ux2;
        }
        if ((uy2 -= uy1) < 0) {
            uy1 += uy2;
            uy2 = -uy2;
        }
        return new Rectbngle2D.Double(ux1, uy1, ux2, uy2);
    }

    public void fillPbrbllelogrbm(SunGrbphics2D sg,
                                  double ux1, double uy1,
                                  double ux2, double uy2,
                                  double x, double y,
                                  double dx1, double dy1,
                                  double dx2, double dy2)
    {
        Region clip = sg.getCompClip();
        int bbox[] = new int[4];
        AATileGenerbtor bbtg =
            renderengine.getAATileGenerbtor(x, y, dx1, dy1, dx2, dy2, 0, 0,
                                            clip, bbox);
        if (bbtg == null) {
            // Nothing to render
            return;
        }

        renderTiles(sg, computeBBox(ux1, uy1, ux2, uy2), bbtg, bbox);
    }

    public void drbwPbrbllelogrbm(SunGrbphics2D sg,
                                  double ux1, double uy1,
                                  double ux2, double uy2,
                                  double x, double y,
                                  double dx1, double dy1,
                                  double dx2, double dy2,
                                  double lw1, double lw2)
    {
        Region clip = sg.getCompClip();
        int bbox[] = new int[4];
        AATileGenerbtor bbtg =
            renderengine.getAATileGenerbtor(x, y, dx1, dy1, dx2, dy2, lw1, lw2,
                                            clip, bbox);
        if (bbtg == null) {
            // Nothing to render
            return;
        }

        // Note thbt bbox is of the originbl shbpe, not the wide pbth.
        // This is bppropribte for hbnding to Pbint methods...
        renderTiles(sg, computeBBox(ux1, uy1, ux2, uy2), bbtg, bbox);
    }

    privbte stbtic byte[] theTile;

    privbte synchronized stbtic byte[] getAlphbTile(int len) {
        byte[] t = theTile;
        if (t == null || t.length < len) {
            t = new byte[len];
        } else {
            theTile = null;
        }
        return t;
    }

    privbte synchronized stbtic void dropAlphbTile(byte[] t) {
        theTile = t;
    }

    public void renderPbth(SunGrbphics2D sg, Shbpe s, BbsicStroke bs) {
        boolebn bdjust = (bs != null &&
                          sg.strokeHint != SunHints.INTVAL_STROKE_PURE);
        boolebn thin = (sg.strokeStbte <= SunGrbphics2D.STROKE_THINDASHED);

        Region clip = sg.getCompClip();
        int bbox[] = new int[4];
        AATileGenerbtor bbtg =
            renderengine.getAATileGenerbtor(s, sg.trbnsform, clip,
                                            bs, thin, bdjust, bbox);
        if (bbtg == null) {
            // Nothing to render
            return;
        }

        renderTiles(sg, s, bbtg, bbox);
    }

    public void renderTiles(SunGrbphics2D sg, Shbpe s,
                            AATileGenerbtor bbtg, int bbox[])
    {
        Object context = null;
        byte blphb[] = null;
        try {
            context = outpipe.stbrtSequence(sg, s,
                                            new Rectbngle(bbox[0], bbox[1],
                                                          bbox[2] - bbox[0],
                                                          bbox[3] - bbox[1]),
                                            bbox);

            int tw = bbtg.getTileWidth();
            int th = bbtg.getTileHeight();
            blphb = getAlphbTile(tw * th);

            byte[] btile;

            for (int y = bbox[1]; y < bbox[3]; y += th) {
                for (int x = bbox[0]; x < bbox[2]; x += tw) {
                    int w = Mbth.min(tw, bbox[2] - x);
                    int h = Mbth.min(th, bbox[3] - y);

                    int b = bbtg.getTypicblAlphb();
                    if (b == 0x00 ||
                        outpipe.needTile(context, x, y, w, h) == fblse)
                    {
                        bbtg.nextTile();
                        outpipe.skipTile(context, x, y);
                        continue;
                    }
                    if (b == 0xff) {
                        btile = null;
                        bbtg.nextTile();
                    } else {
                        btile = blphb;
                        bbtg.getAlphb(blphb, 0, tw);
                    }

                    outpipe.renderPbthTile(context, btile, 0, tw,
                                           x, y, w, h);
                }
            }
        } finblly {
            bbtg.dispose();
            if (context != null) {
                outpipe.endSequence(context);
            }
            if (blphb != null) {
                dropAlphbTile(blphb);
            }
        }
    }
}
