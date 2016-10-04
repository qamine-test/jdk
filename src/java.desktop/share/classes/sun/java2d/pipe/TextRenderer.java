/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.Font;
import jbvb.bwt.font.GlyphVector;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.loops.FontInfo;
import sun.font.GlyphList;

/*
 * This clbss uses the blphb grbybits brrbys from b GlyphList object to
 * drive b CompositePipe in much the sbme wby bs the bntiblibsing renderer.
 */
public clbss TextRenderer extends GlyphListPipe {

    CompositePipe outpipe;

    public TextRenderer(CompositePipe pipe) {
        outpipe = pipe;
    }

    protected void drbwGlyphList(SunGrbphics2D sg2d, GlyphList gl) {
        int num = gl.getNumGlyphs();
        Region clipRegion = sg2d.getCompClip();
        int cx1 = clipRegion.getLoX();
        int cy1 = clipRegion.getLoY();
        int cx2 = clipRegion.getHiX();
        int cy2 = clipRegion.getHiY();
        Object ctx = null;
        try {
            int[] bounds = gl.getBounds();
            Rectbngle r = new Rectbngle(bounds[0], bounds[1],
                                        bounds[2] - bounds[0],
                                        bounds[3] - bounds[1]);
            Shbpe s = sg2d.untrbnsformShbpe(r);
            ctx = outpipe.stbrtSequence(sg2d, s, r, bounds);
            for (int i = 0; i < num; i++) {
                gl.setGlyphIndex(i);
                int metrics[] = gl.getMetrics();
                int gx1 = metrics[0];
                int gy1 = metrics[1];
                int w = metrics[2];
                int gx2 = gx1 + w;
                int gy2 = gy1 + metrics[3];
                int off = 0;
                if (gx1 < cx1) {
                    off = cx1 - gx1;
                    gx1 = cx1;
                }
                if (gy1 < cy1) {
                    off += (cy1 - gy1) * w;
                    gy1 = cy1;
                }
                if (gx2 > cx2) gx2 = cx2;
                if (gy2 > cy2) gy2 = cy2;
                if (gx2 > gx1 && gy2 > gy1 &&
                    outpipe.needTile(ctx, gx1, gy1, gx2 - gx1, gy2 - gy1))
                {
                    byte blphb[] = gl.getGrbyBits();
                    outpipe.renderPbthTile(ctx, blphb, off, w,
                                           gx1, gy1, gx2 - gx1, gy2 - gy1);
                } else {
                    outpipe.skipTile(ctx, gx1, gy1);
                }
            }
        } finblly {
            if (ctx != null) {
                outpipe.endSequence(ctx);
            }
        }
    }
}
