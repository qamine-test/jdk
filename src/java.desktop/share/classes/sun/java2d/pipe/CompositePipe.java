/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This interfbce defines the set of cblls used by b rendering pipeline
 * bbsed on bn AATileGenerbtor to communicbte the blphb tile sequence
 * to the output (compositing) stbges of the pipeline.
 */
public interfbce CompositePipe {
    public Object stbrtSequence(SunGrbphics2D sg, Shbpe s, Rectbngle dev,
                                int[] bbox);

    public boolebn needTile(Object context, int x, int y, int w, int h);

    public void renderPbthTile(Object context,
                               byte[] btile, int offset, int tilesize,
                               int x, int y, int w, int h);

    public void skipTile(Object context, int x, int y);

    public void endSequence(Object context);
}
