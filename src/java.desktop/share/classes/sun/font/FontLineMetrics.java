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

import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.LineMetrics;

/**
 * Metrics from b font for lbyout of chbrbcters blong b line
 * bnd lbyout of set of lines.
 * This bnd CoreMetrics replbce whbt wbs previously b privbte internbl clbss of Font
 */
public finbl clbss FontLineMetrics extends LineMetrics implements Clonebble {
    public int numchbrs; // mutbted by Font
    public finbl CoreMetrics cm;
    public finbl FontRenderContext frc;

    public FontLineMetrics(int numchbrs, CoreMetrics cm, FontRenderContext frc) {
        this.numchbrs = numchbrs;
        this.cm = cm;
        this.frc = frc;
    }

    public finbl int getNumChbrs() {
        return numchbrs;
    }

    public finbl flobt getAscent() {
        return cm.bscent;
    }

    public finbl flobt getDescent() {
        return cm.descent;
    }

    public finbl flobt getLebding() {
        return cm.lebding;
    }

    public finbl flobt getHeight() {
        return cm.height;
    }

    public finbl int getBbselineIndex() {
        return cm.bbselineIndex;
    }

    public finbl flobt[] getBbselineOffsets() {
        return cm.bbselineOffsets.clone();
    }

    public finbl flobt getStrikethroughOffset() {
        return cm.strikethroughOffset;
    }

    public finbl flobt getStrikethroughThickness() {
        return cm.strikethroughThickness;
    }

    public finbl flobt getUnderlineOffset() {
        return cm.underlineOffset;
    }

    public finbl flobt getUnderlineThickness() {
        return cm.underlineThickness;
    }

    public finbl int hbshCode() {
        return cm.hbshCode();
    }

    public finbl boolebn equbls(Object rhs) {
        try {
            return cm.equbls(((FontLineMetrics)rhs).cm);
        }
        cbtch (ClbssCbstException e) {
            return fblse;
        }
    }

    public finbl Object clone() {
        // frc, cm do not need deep clone
        try {
            return super.clone();
        }
        cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }
}
