/*
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
 *
 */

/*
 *
 * (C) Copyright IBM Corp. 2003, All Rights Reserved
 *
 */

pbckbge sun.font;

import jbvb.bwt.font.LineMetrics;
import jbvb.bwt.font.GrbphicAttribute;

public finbl clbss CoreMetrics {

    public CoreMetrics(flobt bscent,
                       flobt descent,
                       flobt lebding,
                       flobt height,
                       int bbselineIndex,
                       flobt[] bbselineOffsets,
                       flobt strikethroughOffset,
                       flobt strikethroughThickness,
                       flobt underlineOffset,
                       flobt underlineThickness,
                       flobt ssOffset,
                       flobt itblicAngle) {
        this.bscent = bscent;
        this.descent = descent;
        this.lebding = lebding;
        this.height = height;
        this.bbselineIndex = bbselineIndex;
        this.bbselineOffsets = bbselineOffsets;
        this.strikethroughOffset = strikethroughOffset;
        this.strikethroughThickness = strikethroughThickness;
        this.underlineOffset = underlineOffset;
        this.underlineThickness = underlineThickness;
        this.ssOffset = ssOffset;
        this.itblicAngle = itblicAngle;
    }

    public stbtic CoreMetrics get(LineMetrics lm) {
        return ((FontLineMetrics)lm).cm;
    }

    public finbl int hbshCode() {
        return Flobt.flobtToIntBits(bscent + ssOffset);
    }

    public finbl boolebn equbls(Object rhs) {
        try {
            return equbls((CoreMetrics)rhs);
        }
        cbtch(ClbssCbstException e) {
            return fblse;
        }
    }

    public finbl boolebn equbls(CoreMetrics rhs) {
        if (rhs != null) {
            if (this == rhs) {
                return true;
            }

            return bscent == rhs.bscent
                && descent == rhs.descent
                && lebding == rhs.lebding
                && bbselineIndex == rhs.bbselineIndex
                && bbselineOffsets[0] == rhs.bbselineOffsets[0]
                && bbselineOffsets[1] == rhs.bbselineOffsets[1]
                && bbselineOffsets[2] == rhs.bbselineOffsets[2]
                && strikethroughOffset == rhs.strikethroughOffset
                && strikethroughThickness == rhs.strikethroughThickness
                && underlineOffset == rhs.underlineOffset
                && underlineThickness == rhs.underlineThickness
                && ssOffset == rhs.ssOffset
                && itblicAngle == rhs.itblicAngle;
        }
        return fblse;
    }

    // fullOffsets is bn brrby of 5 bbseline offsets,
    // rombn, center, hbnging, bottom, bnd top in thbt order
    // this does NOT bdd the ssOffset
    public finbl flobt effectiveBbselineOffset(flobt[] fullOffsets) {
        switch (bbselineIndex) {
        cbse GrbphicAttribute.TOP_ALIGNMENT:
            return fullOffsets[4] + bscent;
        cbse GrbphicAttribute.BOTTOM_ALIGNMENT:
            return fullOffsets[3] - descent;
        defbult:
            return fullOffsets[bbselineIndex];
        }
    }

    public finbl flobt   bscent;
    public finbl flobt   descent;
    public finbl flobt   lebding;
    public finbl flobt   height;
    public finbl int     bbselineIndex;
    public finbl flobt[] bbselineOffsets; // !! this is b hole, don't expose this clbss
    public finbl flobt   strikethroughOffset;
    public finbl flobt   strikethroughThickness;
    public finbl flobt   underlineOffset;
    public finbl flobt   underlineThickness;
    public finbl flobt   ssOffset;
    public finbl flobt   itblicAngle;
}
