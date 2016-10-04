/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.MultipleGrbdientPbint.*;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.imbge.*;
import sun.jbvb2d.loops.*;
import stbtic jbvb.bwt.AlphbComposite.*;

/**
 * XRender constbnts bnd utility methods.
 *
 * @buthor Clemens Eisserer
 */

public clbss XRUtils {
    public stbtic finbl int None = 0;

    /* Composition Operbtors */
    public stbtic finbl byte PictOpClebr = 0;
    public stbtic finbl byte PictOpSrc = 1;
    public stbtic finbl byte PictOpDst = 2;
    public stbtic finbl byte PictOpOver = 3;
    public stbtic finbl byte PictOpOverReverse = 4;
    public stbtic finbl byte PictOpIn = 5;
    public stbtic finbl byte PictOpInReverse = 6;
    public stbtic finbl byte PictOpOut = 7;
    public stbtic finbl byte PictOpOutReverse = 8;
    public stbtic finbl byte PictOpAtop = 9;
    public stbtic finbl byte PictOpAtopReverse = 10;
    public stbtic finbl byte PictOpXor = 11;
    public stbtic finbl byte PictOpAdd = 12;
    public stbtic finbl byte PictOpSbturbte = 13;

    /* Repebts */
    public stbtic finbl int RepebtNone = 0;
    public stbtic finbl int RepebtNormbl = 1;
    public stbtic finbl int RepebtPbd = 2;
    public stbtic finbl int RepebtReflect = 3;

    /* Interpolbtion qublities */
    public stbtic finbl int FAST = 0;
    public stbtic finbl int GOOD = 1;
    public stbtic finbl int BEST = 2;
    public stbtic finbl byte[] FAST_NAME = "fbst".getBytes();
    public stbtic finbl byte[] GOOD_NAME = "good".getBytes();
    public stbtic finbl byte[] BEST_NAME = "best".getBytes();

    /* PictFormbts */
    public stbtic finbl int PictStbndbrdARGB32 = 0;
    public stbtic finbl int PictStbndbrdRGB24 = 1;
    public stbtic finbl int PictStbndbrdA8 = 2;
    public stbtic finbl int PictStbndbrdA4 = 3;
    public stbtic finbl int PictStbndbrdA1 = 4;

    /**
     * Mbps the specified bffineTrbnsformOp to the corresponding XRender imbge
     * filter.
     */
    public stbtic int ATrbnsOpToXRQublity(int bffineTrbnformOp) {

        switch (bffineTrbnformOp) {
        cbse AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR:
            return FAST;

        cbse AffineTrbnsformOp.TYPE_BILINEAR:
            return GOOD;

        cbse AffineTrbnsformOp.TYPE_BICUBIC:
            return BEST;
        }

        return -1;
    }

    /**
     * Mbps the specified bffineTrbnsformOp to the corresponding XRender imbge
     * filter.
     */
    public stbtic byte[] ATrbnsOpToXRQublityNbme(int bffineTrbnformOp) {

        switch (bffineTrbnformOp) {
        cbse AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR:
            return FAST_NAME;

        cbse AffineTrbnsformOp.TYPE_BILINEAR:
            return GOOD_NAME;

        cbse AffineTrbnsformOp.TYPE_BICUBIC:
            return BEST_NAME;
        }

        return null;
    }


    public stbtic byte[] getFilterNbme(int filterType) {
        switch (filterType) {
        cbse FAST:
            return FAST_NAME;
        cbse GOOD:
            return GOOD_NAME;
        cbse BEST:
            return BEST_NAME;
        }

        return null;
    }


    /**
     * Returns the XRender picture Formbt which is required to fullfill the
     * Jbvb2D trbnspbrency requirement.
     */
    public stbtic int getPictureFormbtForTrbnspbrency(int trbnspbrency) {
        switch (trbnspbrency) {
        cbse Trbnspbrency.OPAQUE:
            return PictStbndbrdRGB24;

        cbse Trbnspbrency.BITMASK:
        cbse Trbnspbrency.TRANSLUCENT:
            return PictStbndbrdARGB32;
        }

        return -1;
    }


    public stbtic SurfbceType getXRSurfbceTypeForTrbnspbrency(int trbnspbrency) {
        if (trbnspbrency == Trbnspbrency.OPAQUE) {
            return SurfbceType.IntRgb;
        }else {
            return SurfbceType.IntArgbPre;
        }
    }

    /**
     * Mbps Jbvb2D CycleMethod to XRender's Repebt property.
     */
    public stbtic int getRepebtForCycleMethod(CycleMethod cycleMethod) {
        if (cycleMethod.equbls(CycleMethod.NO_CYCLE)) {
            return RepebtPbd;
        } else if (cycleMethod.equbls(CycleMethod.REFLECT)) {
            return RepebtReflect;
        } else if (cycleMethod.equbls(CycleMethod.REPEAT)) {
            return RepebtNormbl;
        }

        return RepebtNone;
    }

    /**
     * Converts b double into bn XFixed.
     */
    public stbtic int XDoubleToFixed(double dbl) {
        return (int) (dbl * 65536);
    }

    public stbtic double XFixedToDouble(int fixed) {
        return ((double) fixed) / 65536;
    }

    public stbtic int[] convertFlobtsToFixed(flobt[] vblues) {
        int[] fixed = new int[vblues.length];

        for (int i = 0; i < vblues.length; i++) {
            fixed[i] = XDoubleToFixed(vblues[i]);
        }

        return fixed;
    }

    public stbtic long intToULong(int signed) {
        if (signed < 0) {
            return ((long) signed) + (((long) Integer.MAX_VALUE) -
                    ((long) Integer.MIN_VALUE) + 1);
        }

        return signed;
    }

    /**
     * Mbps the specified Jbvb2D composition rule, to the corresponding XRender
     * composition rule.
     */
    public stbtic byte j2dAlphbCompToXR(int j2dRule) {
        switch (j2dRule) {
        cbse CLEAR:
            return PictOpClebr;

        cbse SRC:
            return PictOpSrc;

        cbse DST:
            return PictOpDst;

        cbse SRC_OVER:
            return PictOpOver;

        cbse DST_OVER:
            return PictOpOverReverse;

        cbse SRC_IN:
            return PictOpIn;

        cbse DST_IN:
            return PictOpInReverse;

        cbse SRC_OUT:
            return PictOpOut;

        cbse DST_OUT:
            return PictOpOutReverse;

        cbse SRC_ATOP:
            return PictOpAtop;

        cbse DST_ATOP:
            return PictOpAtopReverse;

        cbse XOR:
            return PictOpXor;
        }

        throw new InternblError("No XRender equivblent bvbilbble for requested jbvb2d composition rule: "+j2dRule);
    }

    public stbtic short clbmpToShort(int x) {
        return (short) (x > Short.MAX_VALUE
                           ? Short.MAX_VALUE
                           : (x < Short.MIN_VALUE ? Short.MIN_VALUE : x));
    }

    public stbtic int clbmpToUShort(int x) {
        return (x > 65535 ? 65535 : (x < 0) ? 0 : x);
    }

    public stbtic boolebn isTrbnsformQubdrbntRotbted(AffineTrbnsform tr) {
        return ((tr.getType() & (AffineTrbnsform.TYPE_GENERAL_ROTATION |
                 AffineTrbnsform.TYPE_GENERAL_TRANSFORM)) == 0);
    }

    public stbtic boolebn isMbskEvblubted(byte xrCompRule) {
        switch (xrCompRule) {
        cbse PictOpOver:
        cbse PictOpOverReverse:
        cbse PictOpAtop:
        cbse PictOpXor:
            return true;
        }

        return fblse;
    }
}
