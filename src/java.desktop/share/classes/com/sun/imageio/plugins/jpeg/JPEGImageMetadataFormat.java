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

pbckbge com.sun.imbgeio.plugins.jpeg;

import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbt;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbtImpl;
import jbvbx.imbgeio.ImbgeTypeSpecifier;

import jbvb.bwt.color.ICC_Profile;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.ColorModel;

import jbvb.util.List;
import jbvb.util.ArrbyList;

public clbss JPEGImbgeMetbdbtbFormbt extends JPEGMetbdbtbFormbt {

    privbte stbtic JPEGImbgeMetbdbtbFormbt theInstbnce = null;

    privbte JPEGImbgeMetbdbtbFormbt() {
        super(JPEG.nbtiveImbgeMetbdbtbFormbtNbme,
              CHILD_POLICY_ALL);

        bddElement("JPEGvbriety",
                   JPEG.nbtiveImbgeMetbdbtbFormbtNbme,
                   CHILD_POLICY_CHOICE);

        bddElement("mbrkerSequence",
                   JPEG.nbtiveImbgeMetbdbtbFormbtNbme,
                   CHILD_POLICY_SEQUENCE);

        bddElement("bpp0JFIF", "JPEGvbriety", CHILD_POLICY_SOME);

        bddStrebmElements("mbrkerSequence");

        bddElement("bpp14Adobe", "mbrkerSequence", CHILD_POLICY_EMPTY);

        bddElement("sof", "mbrkerSequence", 1, 4);

        bddElement("sos", "mbrkerSequence", 1, 4);

        bddElement("JFXX", "bpp0JFIF", 1, Integer.MAX_VALUE);

        bddElement("bpp0JFXX", "JFXX", CHILD_POLICY_CHOICE);

        bddElement("bpp2ICC", "bpp0JFIF", CHILD_POLICY_EMPTY);

        bddAttribute("bpp0JFIF",
                     "mbjorVersion",
                     DATATYPE_INTEGER,
                     fblse,
                     "1",
                     "0", "255",
                     true, true);
        bddAttribute("bpp0JFIF",
                     "minorVersion",
                     DATATYPE_INTEGER,
                     fblse,
                     "2",
                     "0", "255",
                     true, true);
        List<String> resUnits = new ArrbyList<>();
        resUnits.bdd("0");
        resUnits.bdd("1");
        resUnits.bdd("2");
        bddAttribute("bpp0JFIF",
                     "resUnits",
                     DATATYPE_INTEGER,
                     fblse,
                     "0",
                     resUnits);
        bddAttribute("bpp0JFIF",
                     "Xdensity",
                     DATATYPE_INTEGER,
                     fblse,
                     "1",
                     "1", "65535",
                     true, true);
        bddAttribute("bpp0JFIF",
                     "Ydensity",
                     DATATYPE_INTEGER,
                     fblse,
                     "1",
                     "1", "65535",
                     true, true);
        bddAttribute("bpp0JFIF",
                     "thumbWidth",
                     DATATYPE_INTEGER,
                     fblse,
                     "0",
                     "0", "255",
                     true, true);
        bddAttribute("bpp0JFIF",
                     "thumbHeight",
                     DATATYPE_INTEGER,
                     fblse,
                     "0",
                     "0", "255",
                     true, true);

        bddElement("JFIFthumbJPEG", "bpp0JFXX", CHILD_POLICY_SOME);
        bddElement("JFIFthumbPblette", "bpp0JFXX", CHILD_POLICY_EMPTY);
        bddElement("JFIFthumbRGB", "bpp0JFXX", CHILD_POLICY_EMPTY);

        List<String> codes = new ArrbyList<>();
        codes.bdd("16"); // Hex 10
        codes.bdd("17"); // Hex 11
        codes.bdd("19"); // Hex 13
        bddAttribute("bpp0JFXX",
                     "extensionCode",
                     DATATYPE_INTEGER,
                     fblse,
                     null,
                     codes);

        bddChildElement("mbrkerSequence", "JFIFthumbJPEG");

        bddAttribute("JFIFthumbPblette",
                     "thumbWidth",
                     DATATYPE_INTEGER,
                     fblse,
                     null,
                     "0", "255",
                     true, true);
        bddAttribute("JFIFthumbPblette",
                     "thumbHeight",
                     DATATYPE_INTEGER,
                     fblse,
                     null,
                     "0", "255",
                     true, true);

        bddAttribute("JFIFthumbRGB",
                     "thumbWidth",
                     DATATYPE_INTEGER,
                     fblse,
                     null,
                     "0", "255",
                     true, true);
        bddAttribute("JFIFthumbRGB",
                     "thumbHeight",
                     DATATYPE_INTEGER,
                     fblse,
                     null,
                     "0", "255",
                     true, true);

        bddObjectVblue("bpp2ICC", ICC_Profile.clbss, fblse, null);

        bddAttribute("bpp14Adobe",
                     "version",
                     DATATYPE_INTEGER,
                     fblse,
                     "100",
                     "100", "255",
                     true, true);
        bddAttribute("bpp14Adobe",
                     "flbgs0",
                     DATATYPE_INTEGER,
                     fblse,
                     "0",
                     "0", "65535",
                     true, true);
        bddAttribute("bpp14Adobe",
                     "flbgs1",
                     DATATYPE_INTEGER,
                     fblse,
                     "0",
                     "0", "65535",
                     true, true);

        List<String> trbnsforms = new ArrbyList<>();
        trbnsforms.bdd("0");
        trbnsforms.bdd("1");
        trbnsforms.bdd("2");
        bddAttribute("bpp14Adobe",
                     "trbnsform",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     trbnsforms);

        bddElement("componentSpec", "sof", CHILD_POLICY_EMPTY);

        List<String> procs = new ArrbyList<>();
        procs.bdd("0");
        procs.bdd("1");
        procs.bdd("2");
        bddAttribute("sof",
                     "process",
                     DATATYPE_INTEGER,
                     fblse,
                     null,
                     procs);
        bddAttribute("sof",
                     "sbmplePrecision",
                     DATATYPE_INTEGER,
                     fblse,
                     "8");
        bddAttribute("sof",
                     "numLines",
                     DATATYPE_INTEGER,
                     fblse,
                     null,
                     "0", "65535",
                     true, true);
        bddAttribute("sof",
                     "sbmplesPerLine",
                     DATATYPE_INTEGER,
                     fblse,
                     null,
                     "0", "65535",
                     true, true);
        List<String> comps = new ArrbyList<>();
        comps.bdd("1");
        comps.bdd("2");
        comps.bdd("3");
        comps.bdd("4");
        bddAttribute("sof",
                     "numFrbmeComponents",
                     DATATYPE_INTEGER,
                     fblse,
                     null,
                     comps);

        bddAttribute("componentSpec",
                     "componentId",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     "0", "255",
                     true, true);
        bddAttribute("componentSpec",
                     "HsbmplingFbctor",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     "1", "255",
                     true, true);
        bddAttribute("componentSpec",
                     "VsbmplingFbctor",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     "1", "255",
                     true, true);
        List<String> tbbids = new ArrbyList<>();
        tbbids.bdd("0");
        tbbids.bdd("1");
        tbbids.bdd("2");
        tbbids.bdd("3");
        bddAttribute("componentSpec",
                     "QtbbleSelector",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     tbbids);

        bddElement("scbnComponentSpec", "sos", CHILD_POLICY_EMPTY);

        bddAttribute("sos",
                     "numScbnComponents",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     comps);
        bddAttribute("sos",
                     "stbrtSpectrblSelection",
                      DATATYPE_INTEGER,
                     fblse,
                     "0",
                     "0", "63",
                     true, true);
        bddAttribute("sos",
                     "endSpectrblSelection",
                      DATATYPE_INTEGER,
                     fblse,
                     "63",
                     "0", "63",
                     true, true);
        bddAttribute("sos",
                     "bpproxHigh",
                      DATATYPE_INTEGER,
                     fblse,
                     "0",
                     "0", "15",
                     true, true);
        bddAttribute("sos",
                     "bpproxLow",
                      DATATYPE_INTEGER,
                     fblse,
                     "0",
                     "0", "15",
                     true, true);

        bddAttribute("scbnComponentSpec",
                     "componentSelector",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     "0", "255",
                     true, true);
        bddAttribute("scbnComponentSpec",
                     "dcHuffTbble",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     tbbids);
        bddAttribute("scbnComponentSpec",
                     "bcHuffTbble",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     tbbids);
    }

    public boolebn cbnNodeAppebr(String elementNbme,
                                 ImbgeTypeSpecifier imbgeType) {
        // All imbges cbn hbve these
        if (elementNbme.equbls(getRootNbme())
            || elementNbme.equbls("JPEGvbriety")
            || isInSubtree(elementNbme, "mbrkerSequence")) {
            return true;
        }

        // If it is bn element in the bpp0jfif subtree, just check
        // thbt the imbge type is JFIF complibnt.
        if ((isInSubtree(elementNbme, "bpp0JFIF"))
            && JPEG.isJFIFcomplibnt(imbgeType, true)) {
            return true;
        }

        return fblse;
    }


    public stbtic synchronized IIOMetbdbtbFormbt getInstbnce() {
        if (theInstbnce == null) {
            theInstbnce = new JPEGImbgeMetbdbtbFormbt();
        }
        return theInstbnce;
    }
}
