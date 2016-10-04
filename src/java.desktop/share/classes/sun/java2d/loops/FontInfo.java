/*
 * Copyright (c) 1999, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.loops;

import jbvb.bwt.Font;
import jbvb.bwt.geom.AffineTrbnsform;

import sun.font.Font2D;
import sun.font.FontStrike;
import sun.font.FontStrikeDesc;

/*
 * A FontInfo object holds bll cblculbted or derived dbtb needed
 * to hbndle rendering operbtions bbsed on b pbrticulbr set of
 * Grbphics2D rendering bttributes.
 * Note thbt this does not use b Font2DHbndle, bnd blso hbs b reference
 * to the strike which blso references the Font2D.
 * So presently, until SG2D objects no longer reference this FontInfo,
 * there is still some potentibl for b bbd Font2D to be used for b short
 * time. I bm reluctbnt to bdd the overhebd of thbt mbchinery here without
 * b proven benefit.
 */
public clbss FontInfo implements Clonebble {
    public Font font;
    public Font2D font2D;
    public FontStrike fontStrike;
    public double[] devTx;
    public double[] glyphTx;
    public int pixelHeight;
    public flobt originX;
    public flobt originY;
    public int bbHint;
    public boolebn lcdRGBOrder;
    /* lcdSubPixPos is used if FM is ON for HRGB/HBGR LCD text mode */
    public boolebn lcdSubPixPos;

    public String mtx(double[] mbtrix) {
        return ("["+
                mbtrix[0]+", "+
                mbtrix[1]+", "+
                mbtrix[2]+", "+
                mbtrix[3]+
                "]");
    }

    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String toString() {
        return ("FontInfo["+
                "font="+font+", "+
                "devTx="+mtx(devTx)+", "+
                "glyphTx="+mtx(glyphTx)+", "+
                "pixelHeight="+pixelHeight+", "+
                "origin=("+originX+","+originY+"), "+
                "bbHint="+bbHint+", "+
                "lcdRGBOrder="+(lcdRGBOrder ? "RGB" : "BGR")+
                "lcdSubPixPos="+lcdSubPixPos+
                "]");
    }
}
