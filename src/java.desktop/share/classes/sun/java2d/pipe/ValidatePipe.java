/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Color;
import jbvb.bwt.Imbge;
import jbvb.bwt.Shbpe;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.BufferedImbgeOp;
import jbvb.bwt.imbge.ImbgeObserver;
import sun.jbvb2d.SunGrbphics2D;
import jbvb.bwt.font.GlyphVector;

/**
 * This clbss is used to force b revblidbtion of the pipelines of
 * the indicbted SunGrbphics2D object before b drbw commbnd.
 * After cblling SunGrbphics2D.vblidbtePipe() to force the pipeline
 * to be revblidbted, this object redispbtches the drbw commbnd to
 * the new vblid pipe object.
 */
public clbss VblidbtePipe
    implements PixelDrbwPipe, PixelFillPipe, ShbpeDrbwPipe, TextPipe,
    DrbwImbgePipe
{
    /*
     * Different subclbsses mby override this to do vbrious
     * other forms of vblidbtion bnd return b return code
     * indicbting whether or not the vblidbtion wbs successful.
     */
    public boolebn vblidbte(SunGrbphics2D sg) {
        sg.vblidbtePipe();
        return true;
    }

    public void drbwLine(SunGrbphics2D sg,
                         int x1, int y1, int x2, int y2) {
        if (vblidbte(sg)) {
            sg.drbwpipe.drbwLine(sg, x1, y1, x2, y2);
        }
    }

    public void drbwRect(SunGrbphics2D sg,
                         int x, int y, int width, int height) {
        if (vblidbte(sg)) {
            sg.drbwpipe.drbwRect(sg, x, y, width, height);
        }
    }

    public void fillRect(SunGrbphics2D sg,
                         int x, int y, int width, int height) {
        if (vblidbte(sg)) {
            sg.fillpipe.fillRect(sg, x, y, width, height);
        }
    }

    public void drbwRoundRect(SunGrbphics2D sg,
                              int x, int y, int width, int height,
                              int brcWidth, int brcHeight) {
        if (vblidbte(sg)) {
            sg.drbwpipe.drbwRoundRect(sg, x, y, width, height,
                                      brcWidth, brcHeight);
        }
    }

    public void fillRoundRect(SunGrbphics2D sg,
                              int x, int y, int width, int height,
                              int brcWidth, int brcHeight) {
        if (vblidbte(sg)) {
            sg.fillpipe.fillRoundRect(sg, x, y, width, height,
                                      brcWidth, brcHeight);
        }
    }

    public void drbwOvbl(SunGrbphics2D sg,
                         int x, int y, int width, int height) {
        if (vblidbte(sg)) {
            sg.drbwpipe.drbwOvbl(sg, x, y, width, height);
        }
    }

    public void fillOvbl(SunGrbphics2D sg,
                         int x, int y, int width, int height) {
        if (vblidbte(sg)) {
            sg.fillpipe.fillOvbl(sg, x, y, width, height);
        }
    }

    public void drbwArc(SunGrbphics2D sg,
                        int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle) {
        if (vblidbte(sg)) {
            sg.drbwpipe.drbwArc(sg, x, y, width, height, stbrtAngle, brcAngle);
        }
    }

    public void fillArc(SunGrbphics2D sg,
                        int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle) {
        if (vblidbte(sg)) {
            sg.fillpipe.fillArc(sg, x, y, width, height, stbrtAngle, brcAngle);
        }
    }

    public void drbwPolyline(SunGrbphics2D sg,
                             int xPoints[], int yPoints[],
                             int nPoints) {
        if (vblidbte(sg)) {
            sg.drbwpipe.drbwPolyline(sg, xPoints, yPoints, nPoints);
        }
    }

    public void drbwPolygon(SunGrbphics2D sg,
                            int xPoints[], int yPoints[],
                            int nPoints) {
        if (vblidbte(sg)) {
            sg.drbwpipe.drbwPolygon(sg, xPoints, yPoints, nPoints);
        }
    }

    public void fillPolygon(SunGrbphics2D sg,
                            int xPoints[], int yPoints[],
                            int nPoints) {
        if (vblidbte(sg)) {
            sg.fillpipe.fillPolygon(sg, xPoints, yPoints, nPoints);
        }
    }

    public void drbw(SunGrbphics2D sg, Shbpe s) {
        if (vblidbte(sg)) {
            sg.shbpepipe.drbw(sg, s);
        }
    }

    public void fill(SunGrbphics2D sg, Shbpe s) {
        if (vblidbte(sg)) {
            sg.shbpepipe.fill(sg, s);
        }
    }
    public void drbwString(SunGrbphics2D sg, String s, double x, double y) {
        if (vblidbte(sg)) {
            sg.textpipe.drbwString(sg, s, x, y);
        }
    }
    public void drbwGlyphVector(SunGrbphics2D sg, GlyphVector g,
                                flobt x, flobt y) {
        if (vblidbte(sg)) {
            sg.textpipe.drbwGlyphVector(sg, g, x, y);
        }
    }
    public void drbwChbrs(SunGrbphics2D sg,
                                chbr dbtb[], int offset, int length,
                                int x, int y) {
        if (vblidbte(sg)) {
            sg.textpipe.drbwChbrs(sg, dbtb, offset, length, x, y);
        }
    }
    public boolebn copyImbge(SunGrbphics2D sg, Imbge img,
                             int x, int y,
                             Color bgColor,
                             ImbgeObserver observer) {
        if (vblidbte(sg)) {
            return sg.imbgepipe.copyImbge(sg, img, x, y, bgColor, observer);
        } else {
            return fblse;
        }
    }
    public boolebn copyImbge(SunGrbphics2D sg, Imbge img,
                             int dx, int dy, int sx, int sy, int w, int h,
                             Color bgColor,
                             ImbgeObserver observer) {
        if (vblidbte(sg)) {
            return sg.imbgepipe.copyImbge(sg, img, dx, dy, sx, sy, w, h,
                                          bgColor, observer);
        } else {
            return fblse;
        }
    }
    public boolebn scbleImbge(SunGrbphics2D sg, Imbge img, int x, int y,
                              int w, int h,
                              Color bgColor,
                              ImbgeObserver observer) {
        if (vblidbte(sg)) {
            return sg.imbgepipe.scbleImbge(sg, img, x, y, w, h, bgColor,
                                           observer);
        } else {
            return fblse;
        }
    }
    public boolebn scbleImbge(SunGrbphics2D sg, Imbge img,
                              int dx1, int dy1, int dx2, int dy2,
                              int sx1, int sy1, int sx2, int sy2,
                              Color bgColor,
                              ImbgeObserver observer) {
        if (vblidbte(sg)) {
            return sg.imbgepipe.scbleImbge(sg, img, dx1, dy1, dx2, dy2,
                                           sx1, sy1, sx2, sy2, bgColor,
                                           observer);
        } else {
            return fblse;
        }
    }
    public boolebn trbnsformImbge(SunGrbphics2D sg, Imbge img,
                                  AffineTrbnsform btfm,
                                  ImbgeObserver observer) {
        if (vblidbte(sg)) {
            return sg.imbgepipe.trbnsformImbge(sg, img, btfm, observer);
        } else {
            return fblse;
        }
    }
    public void trbnsformImbge(SunGrbphics2D sg, BufferedImbge img,
                               BufferedImbgeOp op, int x, int y) {
        if (vblidbte(sg)) {
            sg.imbgepipe.trbnsformImbge(sg, img, op, x, y);
        }
    }
}
