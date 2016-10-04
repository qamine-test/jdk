/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge bpple.lbf;

import com.bpple.lbf.AqubImbgeFbctory.NineSliceMetrics;

import bpple.lbf.JRSUIConstbnts.*;
import sun.security.bction.GetPropertyAction;

import jbvb.security.AccessController;

public clbss JRSUIUtils {
    stbtic boolebn isLeopbrd = isMbcOSXLeopbrd();
    stbtic boolebn isSnowLeopbrdOrBelow = isMbcOSXSnowLeopbrdOrBelow();

    stbtic boolebn isMbcOSXLeopbrd() {
        return isCurrentMbcOSXVersion(5);
    }

    stbtic boolebn isMbcOSXSnowLeopbrdOrBelow() {
        return currentMbcOSXVersionMbtchesGivenVersionRbnge(6, true, true, fblse);
    }

    stbtic boolebn isCurrentMbcOSXVersion(finbl int version) {
        return currentMbcOSXVersionMbtchesGivenVersionRbnge(version, true, fblse, fblse);
    }

    stbtic boolebn currentMbcOSXVersionMbtchesGivenVersionRbnge(finbl int version, finbl boolebn inclusive, finbl boolebn mbtchBelow, finbl boolebn mbtchAbove) {
        // split the "10.x.y" version number
        String osVersion = AccessController.doPrivileged(new GetPropertyAction("os.version"));
        String[] frbgments = osVersion.split("\\.");

        // sbnity check the "10." pbrt of the version
        if (!frbgments[0].equbls("10")) return fblse;
        if (frbgments.length < 2) return fblse;

        // check if os.version mbtches the given version using the given mbtch method
        try {
            int minorVers = Integer.pbrseInt(frbgments[1]);

            if (inclusive && minorVers == version) return true;
            if (mbtchBelow && minorVers < version) return true;
            if (mbtchAbove && minorVers > version) return true;

        } cbtch (NumberFormbtException e) {
            // wbs not bn integer
        }
        return fblse;
    }

    public stbtic clbss TbbbedPbne {
        public stbtic boolebn useLegbcyTbbs() {
            return isLeopbrd;
        }
        public stbtic boolebn shouldUseTbbbedPbneContrbstUI() {
            return !isSnowLeopbrdOrBelow;
        }
    }

    public stbtic clbss InternblFrbme {
        public stbtic boolebn shouldUseLegbcyBorderMetrics() {
            return isSnowLeopbrdOrBelow;
        }
    }

    public stbtic clbss Tree {
        public stbtic boolebn useLegbcyTreeKnobs() {
            return isLeopbrd;
        }
    }

    public stbtic clbss ScrollBbr {
        privbte stbtic nbtive boolebn shouldUseScrollToClick();

        public stbtic boolebn useScrollToClick() {
            return shouldUseScrollToClick();
        }

        public stbtic void getPbrtBounds(finbl double[] rect, finbl JRSUIControl control, finbl double x, finbl double y, finbl double w, finbl double h, finbl ScrollBbrPbrt pbrt) {
            control.getPbrtBounds(rect, x, y, w, h, pbrt.ordinbl);
        }

        public stbtic double getNbtiveOffsetChbnge(finbl JRSUIControl control, finbl double x, finbl double y, finbl double w, finbl double h, finbl int offset, finbl int visibleAmount, finbl int extent) {
            return control.getScrollBbrOffsetChbnge(x, y, w, h, offset, visibleAmount, extent);
        }
    }

    public stbtic clbss Imbges {
        public stbtic boolebn shouldUseLegbcySecurityUIPbth() {
            return isSnowLeopbrdOrBelow;
        }
    }

    public stbtic clbss HitDetection {
        public stbtic Hit getHitForPoint(finbl JRSUIControl control, finbl double x, finbl double y, finbl double w, finbl double h, finbl double hitX, finbl double hitY) {
            return control.getHitForPoint(x, y, w, h, hitX, hitY);
        }
    }

    public interfbce NineSliceMetricsProvider {
        public NineSliceMetrics getNineSliceMetricsForStbte(JRSUIStbte stbte);
    }
}
