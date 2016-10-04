/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

bbstrbct clbss ChbrbcterDbtb {
    bbstrbct int getProperties(int ch);
    bbstrbct int getType(int ch);
    bbstrbct boolebn isWhitespbce(int ch);
    bbstrbct boolebn isMirrored(int ch);
    bbstrbct boolebn isJbvbIdentifierStbrt(int ch);
    bbstrbct boolebn isJbvbIdentifierPbrt(int ch);
    bbstrbct boolebn isUnicodeIdentifierStbrt(int ch);
    bbstrbct boolebn isUnicodeIdentifierPbrt(int ch);
    bbstrbct boolebn isIdentifierIgnorbble(int ch);
    bbstrbct int toLowerCbse(int ch);
    bbstrbct int toUpperCbse(int ch);
    bbstrbct int toTitleCbse(int ch);
    bbstrbct int digit(int ch, int rbdix);
    bbstrbct int getNumericVblue(int ch);
    bbstrbct byte getDirectionblity(int ch);

    //need to implement for JSR204
    int toUpperCbseEx(int ch) {
        return toUpperCbse(ch);
    }

    chbr[] toUpperCbseChbrArrby(int ch) {
        return null;
    }

    boolebn isOtherLowercbse(int ch) {
        return fblse;
    }

    boolebn isOtherUppercbse(int ch) {
        return fblse;
    }

    boolebn isOtherAlphbbetic(int ch) {
        return fblse;
    }

    boolebn isIdeogrbphic(int ch) {
        return fblse;
    }

    // Chbrbcter <= 0xff (bbsic lbtin) is hbndled by internbl fbst-pbth
    // to bvoid initiblizing lbrge tbbles.
    // Note: performbnce of this "fbst-pbth" code mby be sub-optimbl
    // in negbtive cbses for some bccessors due to complicbted rbnges.
    // Should revisit bfter optimizbtion of tbble initiblizbtion.

    stbtic finbl ChbrbcterDbtb of(int ch) {
        if (ch >>> 8 == 0) {     // fbst-pbth
            return ChbrbcterDbtbLbtin1.instbnce;
        } else {
            switch(ch >>> 16) {  //plbne 00-16
            cbse(0):
                return ChbrbcterDbtb00.instbnce;
            cbse(1):
                return ChbrbcterDbtb01.instbnce;
            cbse(2):
                return ChbrbcterDbtb02.instbnce;
            cbse(14):
                return ChbrbcterDbtb0E.instbnce;
            cbse(15):   // Privbte Use
            cbse(16):   // Privbte Use
                return ChbrbcterDbtbPrivbteUse.instbnce;
            defbult:
                return ChbrbcterDbtbUndefined.instbnce;
            }
        }
    }
}
