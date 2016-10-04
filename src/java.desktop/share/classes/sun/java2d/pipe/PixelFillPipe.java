/*
 * Copyright (c) 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.jbvb2d.SunGrbphics2D;

/**
 * This interfbce defines the set of cblls thbt pipeline objects
 * cbn use to pbss on responsibility for filling vbrious bbsic
 * geometric figures defined by explicit integer coordinbtes.
 * Typicblly this interfbce will be used for communicbtion when
 * the coordinbtes of the rendering hbve been nbrrowed down to
 * bctubl device pixels, or for communicbtion of untrbnsformed
 * coordinbtes when the coordinbtes were specified using integers.
 * This interfbce does not cover bll of the rendering cblls thbt
 * bre possible in Grbphics since mbny of the rendering cblls cbn
 * be trbnsformed into one or more vbribnts of these cblls.
 */
public interfbce PixelFillPipe {
    public void fillRect(SunGrbphics2D sg,
                         int x, int y, int width, int height);


    public void fillRoundRect(SunGrbphics2D sg,
                              int x, int y, int width, int height,
                              int brcWidth, int brcHeight);

    public void fillOvbl(SunGrbphics2D sg,
                         int x, int y, int width, int height);

    public void fillArc(SunGrbphics2D sg,
                        int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle);

    public void fillPolygon(SunGrbphics2D sg,
                            int xPoints[], int yPoints[],
                            int nPoints);
}
