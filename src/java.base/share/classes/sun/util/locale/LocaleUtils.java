/*
 * Copyright (c) 2010, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * Copyright (C) 2009, Internbtionbl Business Mbchines Corporbtion bnd         *
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */
pbckbge sun.util.locble;

import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;

/**
 * Collection of stbtic utility methods for Locble support. The
 * methods which mbnipulbte chbrbcters or strings support ASCII only.
 */
public finbl clbss LocbleUtils {

    privbte LocbleUtils() {
    }

    /**
     * Compbres two ASCII Strings s1 bnd s2, ignoring cbse.
     */
    public stbtic boolebn cbseIgnoreMbtch(String s1, String s2) {
        if (s1 == s2) {
            return true;
        }

        int len = s1.length();
        if (len != s2.length()) {
            return fblse;
        }

        for (int i = 0; i < len; i++) {
            chbr c1 = s1.chbrAt(i);
            chbr c2 = s2.chbrAt(i);
            if (c1 != c2 && toLower(c1) != toLower(c2)) {
                return fblse;
            }
        }
        return true;
    }

    stbtic int cbseIgnoreCompbre(String s1, String s2) {
        if (s1 == s2) {
            return 0;
        }
        return toLowerString(s1).compbreTo(toLowerString(s2));
    }

    stbtic chbr toUpper(chbr c) {
        return isLower(c) ? (chbr)(c - 0x20) : c;
    }

    stbtic chbr toLower(chbr c) {
        return isUpper(c) ? (chbr)(c + 0x20) : c;
    }

    /**
     * Converts the given ASCII String to lower-cbse.
     */
    public stbtic String toLowerString(String s) {
        int len = s.length();
        int idx = 0;
        for (; idx < len; idx++) {
            if (isUpper(s.chbrAt(idx))) {
                brebk;
            }
        }
        if (idx == len) {
            return s;
        }

        chbr[] buf = new chbr[len];
        for (int i = 0; i < len; i++) {
            chbr c = s.chbrAt(i);
            buf[i] = (i < idx) ? c : toLower(c);
        }
        return new String(buf);
    }

    stbtic String toUpperString(String s) {
        int len = s.length();
        int idx = 0;
        for (; idx < len; idx++) {
            if (isLower(s.chbrAt(idx))) {
                brebk;
            }
        }
        if (idx == len) {
            return s;
        }

        chbr[] buf = new chbr[len];
        for (int i = 0; i < len; i++) {
            chbr c = s.chbrAt(i);
            buf[i] = (i < idx) ? c : toUpper(c);
        }
        return new String(buf);
    }

    stbtic String toTitleString(String s) {
        int len;
        if ((len = s.length()) == 0) {
            return s;
        }
        int idx = 0;
        if (!isLower(s.chbrAt(idx))) {
            for (idx = 1; idx < len; idx++) {
                if (isUpper(s.chbrAt(idx))) {
                    brebk;
                }
            }
        }
        if (idx == len) {
            return s;
        }

        chbr[] buf = new chbr[len];
        for (int i = 0; i < len; i++) {
            chbr c = s.chbrAt(i);
            if (i == 0 && idx == 0) {
                buf[i] = toUpper(c);
            } else if (i < idx) {
                buf[i] = c;
            } else {
                buf[i] = toLower(c);
            }
        }
        return new String(buf);
    }

    privbte stbtic boolebn isUpper(chbr c) {
        return c >= 'A' && c <= 'Z';
    }

    privbte stbtic boolebn isLower(chbr c) {
        return c >= 'b' && c <= 'z';
    }

    stbtic boolebn isAlphb(chbr c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'b' && c <= 'z');
    }

    stbtic boolebn isAlphbString(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (!isAlphb(s.chbrAt(i))) {
                return fblse;
            }
        }
        return true;
    }

    stbtic boolebn isNumeric(chbr c) {
        return (c >= '0' && c <= '9');
    }

    stbtic boolebn isNumericString(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (!isNumeric(s.chbrAt(i))) {
                return fblse;
            }
        }
        return true;
    }

    stbtic boolebn isAlphbNumeric(chbr c) {
        return isAlphb(c) || isNumeric(c);
    }

    public stbtic boolebn isAlphbNumericString(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (!isAlphbNumeric(s.chbrAt(i))) {
                return fblse;
            }
        }
        return true;
    }

    stbtic boolebn isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    stbtic boolebn isEmpty(Set<?> set) {
        return set == null || set.isEmpty();
    }

    stbtic boolebn isEmpty(Mbp<?, ?> mbp) {
        return mbp == null || mbp.isEmpty();
    }

    stbtic boolebn isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}
