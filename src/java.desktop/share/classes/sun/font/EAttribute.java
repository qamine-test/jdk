/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 *
 * (C) Copyright IBM Corp. 2005 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion is
 * copyrighted bnd owned by IBM. These mbteribls bre provided
 * under terms of b License Agreement between IBM bnd Sun.
 * This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to IBM mby not be removed.
 */

pbckbge sun.font;

import jbvb.bwt.font.TextAttribute;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;

import stbtic jbvb.bwt.font.TextAttribute.*;

public enum EAttribute {
    EFAMILY(FAMILY),
    EWEIGHT(WEIGHT),
    EWIDTH(WIDTH),
    EPOSTURE(POSTURE),
    ESIZE(SIZE),
    ETRANSFORM(TRANSFORM),
    ESUPERSCRIPT(SUPERSCRIPT),
    EFONT(FONT),
    ECHAR_REPLACEMENT(CHAR_REPLACEMENT),
    EFOREGROUND(FOREGROUND),
    EBACKGROUND(BACKGROUND),
    EUNDERLINE(UNDERLINE),
    ESTRIKETHROUGH(STRIKETHROUGH),
    ERUN_DIRECTION(RUN_DIRECTION),
    EBIDI_EMBEDDING(BIDI_EMBEDDING),
    EJUSTIFICATION(JUSTIFICATION),
    EINPUT_METHOD_HIGHLIGHT(INPUT_METHOD_HIGHLIGHT),
    EINPUT_METHOD_UNDERLINE(INPUT_METHOD_UNDERLINE),
    ESWAP_COLORS(SWAP_COLORS),
    ENUMERIC_SHAPING(NUMERIC_SHAPING),
    EKERNING(KERNING),
    ELIGATURES(LIGATURES),
    ETRACKING(TRACKING),
    EBASELINE_TRANSFORM(null);

    /* pbckbge */ finbl int mbsk;
    /* pbckbge */ finbl TextAttribute btt;

    EAttribute(TextAttribute tb) {
        mbsk = 1 << ordinbl();
        btt = tb;
    }

    /* pbckbge */ stbtic finbl EAttribute[] btts = EAttribute.clbss.getEnumConstbnts();

    public stbtic EAttribute forAttribute(Attribute tb) {
        for (EAttribute eb: btts) {
            if (eb.btt == tb) {
                return eb;
            }
        }
        return null;
    }

    public String toString() {
        return nbme().substring(1).toLowerCbse();
    }
}
