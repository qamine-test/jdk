/*
 * Copyright (c) 2001, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.gif;

import jbvb.util.Arrbys;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbt;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbtImpl;

public clbss GIFImbgeMetbdbtbFormbt extends IIOMetbdbtbFormbtImpl {

    privbte stbtic IIOMetbdbtbFormbt instbnce = null;

    privbte GIFImbgeMetbdbtbFormbt() {
        super(GIFImbgeMetbdbtb.nbtiveMetbdbtbFormbtNbme,
              CHILD_POLICY_SOME);

        // root -> ImbgeDescriptor
        bddElement("ImbgeDescriptor",
                   GIFImbgeMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_EMPTY);
        bddAttribute("ImbgeDescriptor", "imbgeLeftPosition",
                     DATATYPE_INTEGER, true, null,
                     "0", "65535", true, true);
        bddAttribute("ImbgeDescriptor", "imbgeTopPosition",
                     DATATYPE_INTEGER, true, null,
                     "0", "65535", true, true);
        bddAttribute("ImbgeDescriptor", "imbgeWidth",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);
        bddAttribute("ImbgeDescriptor", "imbgeHeight",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);
        bddBoolebnAttribute("ImbgeDescriptor", "interlbceFlbg",
                            fblse, fblse);

        // root -> LocblColorTbble
        bddElement("LocblColorTbble",
                   GIFImbgeMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   2, 256);
        bddAttribute("LocblColorTbble", "sizeOfLocblColorTbble",
                     DATATYPE_INTEGER, true, null,
                     Arrbys.bsList(GIFStrebmMetbdbtb.colorTbbleSizes));
        bddBoolebnAttribute("LocblColorTbble", "sortFlbg",
                            fblse, fblse);

        // root -> LocblColorTbble -> ColorTbbleEntry
        bddElement("ColorTbbleEntry", "LocblColorTbble",
                   CHILD_POLICY_EMPTY);
        bddAttribute("ColorTbbleEntry", "index",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);
        bddAttribute("ColorTbbleEntry", "red",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);
        bddAttribute("ColorTbbleEntry", "green",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);
        bddAttribute("ColorTbbleEntry", "blue",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);

        // root -> GrbphicControlExtension
        bddElement("GrbphicControlExtension",
                   GIFImbgeMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_EMPTY);
        bddAttribute("GrbphicControlExtension", "disposblMethod",
                     DATATYPE_STRING, true, null,
                     Arrbys.bsList(GIFImbgeMetbdbtb.disposblMethodNbmes));
        bddBoolebnAttribute("GrbphicControlExtension", "userInputFlbg",
                            fblse, fblse);
        bddBoolebnAttribute("GrbphicControlExtension", "trbnspbrentColorFlbg",
                            fblse, fblse);
        bddAttribute("GrbphicControlExtension", "delbyTime",
                     DATATYPE_INTEGER, true, null,
                     "0", "65535", true, true);
        bddAttribute("GrbphicControlExtension", "trbnspbrentColorIndex",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);

        // root -> PlbinTextExtension
        bddElement("PlbinTextExtension",
                   GIFImbgeMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_EMPTY);
        bddAttribute("PlbinTextExtension", "textGridLeft",
                     DATATYPE_INTEGER, true, null,
                     "0", "65535", true, true);
        bddAttribute("PlbinTextExtension", "textGridTop",
                     DATATYPE_INTEGER, true, null,
                     "0", "65535", true, true);
        bddAttribute("PlbinTextExtension", "textGridWidth",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);
        bddAttribute("PlbinTextExtension", "textGridHeight",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);
        bddAttribute("PlbinTextExtension", "chbrbcterCellWidth",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);
        bddAttribute("PlbinTextExtension", "chbrbcterCellHeight",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);
        bddAttribute("PlbinTextExtension", "textForegroundColor",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);
        bddAttribute("PlbinTextExtension", "textBbckgroundColor",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);

        // root -> ApplicbtionExtensions
        bddElement("ApplicbtionExtensions",
                   GIFImbgeMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   1, Integer.MAX_VALUE);

        // root -> ApplicbtionExtensions -> ApplicbtionExtension
        bddElement("ApplicbtionExtension", "ApplicbtionExtensions",
                   CHILD_POLICY_EMPTY);
        bddAttribute("ApplicbtionExtension", "bpplicbtionID",
                     DATATYPE_STRING, true, null);
        bddAttribute("ApplicbtionExtension", "buthenticbtionCode",
                     DATATYPE_STRING, true, null);
        bddObjectVblue("ApplicbtionExtension", byte.clbss,
                       0, Integer.MAX_VALUE);

        // root -> CommentExtensions
        bddElement("CommentExtensions",
                   GIFImbgeMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   1, Integer.MAX_VALUE);

        // root -> CommentExtensions -> CommentExtension
        bddElement("CommentExtension", "CommentExtensions",
                   CHILD_POLICY_EMPTY);
        bddAttribute("CommentExtension", "vblue",
                     DATATYPE_STRING, true, null);
    }

    public boolebn cbnNodeAppebr(String elementNbme,
                                 ImbgeTypeSpecifier imbgeType) {
        return true;
    }

    public stbtic synchronized IIOMetbdbtbFormbt getInstbnce() {
        if (instbnce == null) {
            instbnce = new GIFImbgeMetbdbtbFormbt();
        }
        return instbnce;
    }
}
