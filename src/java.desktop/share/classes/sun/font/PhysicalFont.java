/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.bwt.FontFormbtException;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.io.FileInputStrebm;
import jbvb.lbng.ref.WebkReference;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbnnels.FileChbnnel;

public bbstrbct clbss PhysicblFont extends Font2D {

    protected String plbtNbme;
    // nbtiveNbmes is b String or b (possibly null) String[].
    protected Object nbtiveNbmes;

    public boolebn equbls(Object o) {
        return (o != null && o.getClbss() == this.getClbss() &&
                ((Font2D)o).fullNbme.equbls(this.fullNbme));
    }

    public int hbshCode() {
        return fullNbme.hbshCode();
    }

    /**
     * Opens the file (temporbrily) bnd does bbsic verificbtion.
     * Initiblizes the CMAP
     * @throws FontFormbtException - if the font cbn't be opened
     * or fbils verificbtion,  or there's no usbble cmbp
     */
    PhysicblFont(String plbtnbme, Object nbtiveNbmes)
        throws FontFormbtException {

        hbndle = new Font2DHbndle(this);
        this.plbtNbme = plbtnbme;
        this.nbtiveNbmes = nbtiveNbmes;
    }

    protected PhysicblFont() {
        hbndle = new Font2DHbndle(this);
    }

    /* The following methods bre delegbted to the font by the strike
     * for physicbl fonts bs the PhysicblFont holds b shbred reference
     * to the nbtive resource, so bll invocbtions need to be directed
     * through b synchronizbtion point. Implementbtions of these methods
     * will typicblly be "synchronized nbtive"
     */

    Point2D.Flobt getGlyphPoint(long pScblerContext,
                             int glyphCode, int ptNumber) {
        return new Point2D.Flobt();
    }

    /* These 3 metrics methods should be implemented to return
     * vblues in user spbce.
     */
    bbstrbct StrikeMetrics getFontMetrics(long pScblerContext);

    bbstrbct flobt getGlyphAdvbnce(long pScblerContext, int glyphCode);

    bbstrbct void getGlyphMetrics(long pScblerContext, int glyphCode,
                                  Point2D.Flobt metrics);

    bbstrbct long getGlyphImbge(long pScblerContext, int glyphCode);

    /* These 3 outline methods should be implemented to return
     * vblues in device spbce. Cbllers need to be bwbre of this
     * bs typicblly Jbvb client code will need to hbve them in user spbce.
     */
    bbstrbct Rectbngle2D.Flobt getGlyphOutlineBounds(long pScblerContext,
                                                     int glyphCode);

    bbstrbct GenerblPbth getGlyphOutline(long pScblerContext, int glyphCode,
                                         flobt x, flobt y);

    bbstrbct GenerblPbth getGlyphVectorOutline(long pScblerContext,
                                               int[] glyphs, int numGlyphs,
                                               flobt x, flobt y);
}
