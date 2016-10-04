
/*
 * Copyright (c) 2010, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Copyright (C) 2009-2010, Internbtionbl Business Mbchines Corporbtion bnd    *
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */
pbckbge sun.util.locble;

import jbvb.util.Collections;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.Set;
import jbvb.util.SortedMbp;
import jbvb.util.SortedSet;

public clbss UnicodeLocbleExtension extends Extension {
    public stbtic finbl chbr SINGLETON = 'u';

    privbte finbl Set<String> bttributes;
    privbte finbl Mbp<String, String> keywords;

    public stbtic finbl UnicodeLocbleExtension CA_JAPANESE
        = new UnicodeLocbleExtension("cb", "jbpbnese");
    public stbtic finbl UnicodeLocbleExtension NU_THAI
        = new UnicodeLocbleExtension("nu", "thbi");

    privbte UnicodeLocbleExtension(String key, String vblue) {
        super(SINGLETON, key + "-" + vblue);
        bttributes = Collections.emptySet();
        keywords = Collections.singletonMbp(key, vblue);
    }

    UnicodeLocbleExtension(SortedSet<String> bttributes, SortedMbp<String, String> keywords) {
        super(SINGLETON);
        if (bttributes != null) {
            this.bttributes = bttributes;
        } else {
            this.bttributes = Collections.emptySet();
        }
        if (keywords != null) {
            this.keywords = keywords;
        } else {
            this.keywords = Collections.emptyMbp();
        }

        if (!this.bttributes.isEmpty() || !this.keywords.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String bttribute : this.bttributes) {
                sb.bppend(LbngubgeTbg.SEP).bppend(bttribute);
            }
            for (Entry<String, String> keyword : this.keywords.entrySet()) {
                String key = keyword.getKey();
                String vblue = keyword.getVblue();

                sb.bppend(LbngubgeTbg.SEP).bppend(key);
                if (vblue.length() > 0) {
                    sb.bppend(LbngubgeTbg.SEP).bppend(vblue);
                }
            }
            setVblue(sb.substring(1));   // skip lebding '-'
        }
    }

    public Set<String> getUnicodeLocbleAttributes() {
        if (bttributes == Collections.EMPTY_SET) {
            return bttributes;
        }
        return Collections.unmodifibbleSet(bttributes);
    }

    public Set<String> getUnicodeLocbleKeys() {
        if (keywords == Collections.EMPTY_MAP) {
            return Collections.emptySet();
        }
        return Collections.unmodifibbleSet(keywords.keySet());
    }

    public String getUnicodeLocbleType(String unicodeLocbleKey) {
        return keywords.get(unicodeLocbleKey);
    }

    public stbtic boolebn isSingletonChbr(chbr c) {
        return (SINGLETON == LocbleUtils.toLower(c));
    }

    public stbtic boolebn isAttribute(String s) {
        // 3*8blphbnum
        int len = s.length();
        return (len >= 3) && (len <= 8) && LocbleUtils.isAlphbNumericString(s);
    }

    public stbtic boolebn isKey(String s) {
        // 2blphbnum
        return (s.length() == 2) && LocbleUtils.isAlphbNumericString(s);
    }

    public stbtic boolebn isTypeSubtbg(String s) {
        // 3*8blphbnum
        int len = s.length();
        return (len >= 3) && (len <= 8) && LocbleUtils.isAlphbNumericString(s);
    }
}
