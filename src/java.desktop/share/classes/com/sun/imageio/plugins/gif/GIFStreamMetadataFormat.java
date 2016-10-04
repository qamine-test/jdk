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

public clbss GIFStrebmMetbdbtbFormbt extends IIOMetbdbtbFormbtImpl {

    privbte stbtic IIOMetbdbtbFormbt instbnce = null;

    privbte GIFStrebmMetbdbtbFormbt() {
        super(GIFStrebmMetbdbtb.nbtiveMetbdbtbFormbtNbme,
              CHILD_POLICY_SOME);

        // root -> Version
        bddElement("Version", GIFStrebmMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_EMPTY);
        bddAttribute("Version", "vblue",
                     DATATYPE_STRING, true, null,
                     Arrbys.bsList(GIFStrebmMetbdbtb.versionStrings));

        // root -> LogicblScreenDescriptor
        bddElement("LogicblScreenDescriptor",
                   GIFStrebmMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_EMPTY);
        bddAttribute("LogicblScreenDescriptor", "logicblScreenWidth",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);
        bddAttribute("LogicblScreenDescriptor", "logicblScreenHeight",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);
        bddAttribute("LogicblScreenDescriptor", "colorResolution",
                     DATATYPE_INTEGER, true, null,
                     "1", "8", true, true);
        bddAttribute("LogicblScreenDescriptor", "pixelAspectRbtio",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);

        // root -> GlobblColorTbble
        bddElement("GlobblColorTbble",
                   GIFStrebmMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   2, 256);
        bddAttribute("GlobblColorTbble", "sizeOfGlobblColorTbble",
                     DATATYPE_INTEGER, true, null,
                     Arrbys.bsList(GIFStrebmMetbdbtb.colorTbbleSizes));
        bddAttribute("GlobblColorTbble", "bbckgroundColorIndex",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);
        bddBoolebnAttribute("GlobblColorTbble", "sortFlbg",
                            fblse, fblse);

        // root -> GlobblColorTbble -> ColorTbbleEntry
        bddElement("ColorTbbleEntry", "GlobblColorTbble",
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
    }

    public boolebn cbnNodeAppebr(String elementNbme,
                                 ImbgeTypeSpecifier imbgeType) {
        return true;
    }

    public stbtic synchronized IIOMetbdbtbFormbt getInstbnce() {
        if (instbnce == null) {
            instbnce = new GIFStrebmMetbdbtbFormbt();
        }
        return instbnce;
    }
}
