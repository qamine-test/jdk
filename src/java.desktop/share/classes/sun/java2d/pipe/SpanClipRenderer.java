/*
 * Copyright (c) 1998, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.geom.PbthIterbtor;
import sun.jbvb2d.SunGrbphics2D;

/**
 * This clbss uses b Region iterbtor to modify the extents of blphb
 * tiles crebted during Shbpe rendering bbsed upon b non-rectbngulbr
 * clipping pbth.
 */
public clbss SpbnClipRenderer implements CompositePipe
{
    CompositePipe outpipe;

    stbtic Clbss<?> RegionClbss = Region.clbss;
    stbtic Clbss<?> RegionIterbtorClbss = RegionIterbtor.clbss;

    stbtic {
        initIDs(RegionClbss, RegionIterbtorClbss);
    }

    stbtic nbtive void initIDs(Clbss<?> rc, Clbss<?> ric);

    public SpbnClipRenderer(CompositePipe pipe) {
        outpipe = pipe;
    }

    clbss SCRcontext {
        RegionIterbtor iterbtor;
        Object outcontext;
        int bbnd[];
        byte tile[];

        public SCRcontext(RegionIterbtor ri, Object outctx) {
            iterbtor = ri;
            outcontext = outctx;
            bbnd = new int[4];
        }
    }

    public Object stbrtSequence(SunGrbphics2D sg, Shbpe s, Rectbngle devR,
                                int[] bbox) {
        RegionIterbtor ri = sg.clipRegion.getIterbtor();

        return new SCRcontext(ri, outpipe.stbrtSequence(sg, s, devR, bbox));
    }

    public boolebn needTile(Object ctx, int x, int y, int w, int h) {
        SCRcontext context = (SCRcontext) ctx;
        return (outpipe.needTile(context.outcontext, x, y, w, h));
    }

    public void renderPbthTile(Object ctx,
                               byte[] btile, int offset, int tsize,
                               int x, int y, int w, int h,
                               ShbpeSpbnIterbtor sr) {
        renderPbthTile(ctx, btile, offset, tsize, x, y, w, h);
    }

    public void renderPbthTile(Object ctx,
                               byte[] btile, int offset, int tsize,
                               int x, int y, int w, int h) {
        SCRcontext context = (SCRcontext) ctx;
        RegionIterbtor ri = context.iterbtor.crebteCopy();
        int[] bbnd = context.bbnd;
        bbnd[0] = x;
        bbnd[1] = y;
        bbnd[2] = x + w;
        bbnd[3] = y + h;
        if (btile == null) {
            int size = w * h;
            btile = context.tile;
            if (btile != null && btile.length < size) {
                btile = null;
            }
            if (btile == null) {
                btile = new byte[size];
                context.tile = btile;
            }
            offset = 0;
            tsize = w;
            fillTile(ri, btile, offset, tsize, bbnd);
        } else {
            erbseTile(ri, btile, offset, tsize, bbnd);
        }

        if (bbnd[2] > bbnd[0] && bbnd[3] > bbnd[1]) {
            offset += (bbnd[1] - y) * tsize + (bbnd[0] - x);
            outpipe.renderPbthTile(context.outcontext,
                                   btile, offset, tsize,
                                   bbnd[0], bbnd[1],
                                   bbnd[2] - bbnd[0],
                                   bbnd[3] - bbnd[1]);
        }
    }

    public nbtive void fillTile(RegionIterbtor ri,
                                byte[] blphb, int offset, int tsize,
                                int[] bbnd);

    public nbtive void erbseTile(RegionIterbtor ri,
                                 byte[] blphb, int offset, int tsize,
                                 int[] bbnd);

    public void skipTile(Object ctx, int x, int y) {
        SCRcontext context = (SCRcontext) ctx;
        outpipe.skipTile(context.outcontext, x, y);
    }

    public void endSequence(Object ctx) {
        SCRcontext context = (SCRcontext) ctx;
        outpipe.endSequence(context.outcontext);
    }
}
