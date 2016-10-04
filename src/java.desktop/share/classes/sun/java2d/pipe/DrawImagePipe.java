/*
 * Copyright (c) 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.BufferedImbgeOp;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.geom.AffineTrbnsform;
import sun.jbvb2d.SunGrbphics2D;

/**
 * This interfbce defines the set of cblls thbt pipeline objects
 * cbn use to pbss on responsibility for performing vbrious
 * imbge copy commbnds.
 * There bre 3 types of imbge copies hbndled by this clbss:
 *    - copyImbge: These methods simply copy the pixels
 *      from the src to dest, either from (0, 0) (implicit)
 *      or from b given (sx, sy) locbtion.
 *    - scbleImbge: These methods copy from src to dest while
 *      scbling the source imbge.  The src bnd dest rectbngles
 *      bre used to specify the scble.
 *    - copyImbgeBg: These methods behbve the sbme bs the
 *      copyImbge methods except they substitute the given
 *      bbckground color for bny trbnspbrent pixels.
 *    - scbleImbgeBg: These methods behbve the sbme bs the
 *      scbleImbge methods except they substitute the given
 *      bbckground color for bny trbnspbrent pixels.
 *    - trbnsformImbge....
 */
public interfbce DrbwImbgePipe {

    public boolebn copyImbge(SunGrbphics2D sg, Imbge img,
                             int x, int y,
                             Color bgColor,
                             ImbgeObserver observer);

    public boolebn copyImbge(SunGrbphics2D sg, Imbge img,
                             int dx, int dy, int sx, int sy, int w, int h,
                             Color bgColor,
                             ImbgeObserver observer);

    public boolebn scbleImbge(SunGrbphics2D sg, Imbge img, int x, int y,
                              int width, int height,
                              Color bgColor,
                              ImbgeObserver observer);

    public boolebn scbleImbge(SunGrbphics2D sg, Imbge img,
                              int dx1, int dy1, int dx2, int dy2,
                              int sx1, int sy1, int sx2, int sy2,
                              Color bgColor,
                              ImbgeObserver observer);

    public boolebn trbnsformImbge(SunGrbphics2D sg, Imbge img,
                                  AffineTrbnsform btfm,
                                  ImbgeObserver observer);

    public void trbnsformImbge(SunGrbphics2D sg, BufferedImbge img,
                               BufferedImbgeOp op, int x, int y);


}
