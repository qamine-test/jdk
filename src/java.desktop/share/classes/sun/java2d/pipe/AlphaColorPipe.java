/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.jbvb2d.SunGrbphics2D;

/**
 * This clbss implements b CompositePipe thbt renders pbth blphb tiles
 * into b destinbtion thbt supports direct blphb compositing of b solid
 * color, bccording to one of the rules in the AlphbComposite clbss.
 */
public clbss AlphbColorPipe implements CompositePipe, PbrbllelogrbmPipe {
    public AlphbColorPipe() {
    }

    public Object stbrtSequence(SunGrbphics2D sg, Shbpe s, Rectbngle dev,
                                int[] bbox) {
        return sg;
    }

    public boolebn needTile(Object context, int x, int y, int w, int h) {
        return true;
    }

    public void renderPbthTile(Object context,
                               byte[] btile, int offset, int tilesize,
                               int x, int y, int w, int h) {
        SunGrbphics2D sg = (SunGrbphics2D) context;

        sg.blphbfill.MbskFill(sg, sg.getSurfbceDbtb(), sg.composite,
                              x, y, w, h,
                              btile, offset, tilesize);
    }

    public void skipTile(Object context, int x, int y) {
        return;
    }

    public void endSequence(Object context) {
        return;
    }

    public void fillPbrbllelogrbm(SunGrbphics2D sg,
                                  double ux1, double uy1,
                                  double ux2, double uy2,
                                  double x, double y,
                                  double dx1, double dy1,
                                  double dx2, double dy2)
    {
        sg.blphbfill.FillAAPgrbm(sg, sg.getSurfbceDbtb(), sg.composite,
                                 x, y, dx1, dy1, dx2, dy2);
    }

    public void drbwPbrbllelogrbm(SunGrbphics2D sg,
                                  double ux1, double uy1,
                                  double ux2, double uy2,
                                  double x, double y,
                                  double dx1, double dy1,
                                  double dx2, double dy2,
                                  double lw1, double lw2)
    {
        sg.blphbfill.DrbwAAPgrbm(sg, sg.getSurfbceDbtb(), sg.composite,
                                 x, y, dx1, dy1, dx2, dy2, lw1, lw2);
    }
}
