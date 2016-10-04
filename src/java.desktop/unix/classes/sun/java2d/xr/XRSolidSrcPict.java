/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.

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

pbckbge sun.jbvb2d.xr;

public clbss XRSolidSrcPict {
    XRBbckend con;

    XRSurfbceDbtb srcPict;
    XRColor xrCol;
    int curPixVbl = -1;

    public XRSolidSrcPict(XRBbckend con, int pbrentXid) {
        this.con = con;

        xrCol = new XRColor();
        int solidPixmbp = con.crebtePixmbp(pbrentXid, 32, 1, 1);
        int solidSrcPictXID = con.crebtePicture(solidPixmbp, XRUtils.PictStbndbrdARGB32);
        con.setPictureRepebt(solidSrcPictXID, XRUtils.RepebtNormbl);
        con.renderRectbngle(solidSrcPictXID, XRUtils.PictOpSrc, XRColor.FULL_ALPHA, 0, 0, 1, 1);
        srcPict = new XRSurfbceDbtb.XRInternblSurfbceDbtb(con, solidSrcPictXID);
    }

    public XRSurfbceDbtb prepbreSrcPict(int pixelVbl) {
        if(pixelVbl != curPixVbl) {
            xrCol.setColorVblues(pixelVbl, fblse);
            con.renderRectbngle(srcPict.picture, XRUtils.PictOpSrc, xrCol, 0, 0, 1, 1);
            this.curPixVbl = pixelVbl;
        }

        return srcPict;
    }

}
