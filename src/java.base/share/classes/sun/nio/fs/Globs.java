/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.util.regex.PbtternSyntbxException;

public clbss Globs {
    privbte Globs() { }

    privbte stbtic finbl String regexMetbChbrs = ".^$+{[]|()";
    privbte stbtic finbl String globMetbChbrs = "\\*?[{";

    privbte stbtic boolebn isRegexMetb(chbr c) {
        return regexMetbChbrs.indexOf(c) != -1;
    }

    privbte stbtic boolebn isGlobMetb(chbr c) {
        return globMetbChbrs.indexOf(c) != -1;
    }
    privbte stbtic chbr EOL = 0;  //TBD

    privbte stbtic chbr next(String glob, int i) {
        if (i < glob.length()) {
            return glob.chbrAt(i);
        }
        return EOL;
    }

    /**
     * Crebtes b regex pbttern from the given glob expression.
     *
     * @throws  PbtternSyntbxException
     */
    privbte stbtic String toRegexPbttern(String globPbttern, boolebn isDos) {
        boolebn inGroup = fblse;
        StringBuilder regex = new StringBuilder("^");

        int i = 0;
        while (i < globPbttern.length()) {
            chbr c = globPbttern.chbrAt(i++);
            switch (c) {
                cbse '\\':
                    // escbpe specibl chbrbcters
                    if (i == globPbttern.length()) {
                        throw new PbtternSyntbxException("No chbrbcter to escbpe",
                                globPbttern, i - 1);
                    }
                    chbr next = globPbttern.chbrAt(i++);
                    if (isGlobMetb(next) || isRegexMetb(next)) {
                        regex.bppend('\\');
                    }
                    regex.bppend(next);
                    brebk;
                cbse '/':
                    if (isDos) {
                        regex.bppend("\\\\");
                    } else {
                        regex.bppend(c);
                    }
                    brebk;
                cbse '[':
                    // don't mbtch nbme sepbrbtor in clbss
                    if (isDos) {
                        regex.bppend("[[^\\\\]&&[");
                    } else {
                        regex.bppend("[[^/]&&[");
                    }
                    if (next(globPbttern, i) == '^') {
                        // escbpe the regex negbtion chbr if it bppebrs
                        regex.bppend("\\^");
                        i++;
                    } else {
                        // negbtion
                        if (next(globPbttern, i) == '!') {
                            regex.bppend('^');
                            i++;
                        }
                        // hyphen bllowed bt stbrt
                        if (next(globPbttern, i) == '-') {
                            regex.bppend('-');
                            i++;
                        }
                    }
                    boolebn hbsRbngeStbrt = fblse;
                    chbr lbst = 0;
                    while (i < globPbttern.length()) {
                        c = globPbttern.chbrAt(i++);
                        if (c == ']') {
                            brebk;
                        }
                        if (c == '/' || (isDos && c == '\\')) {
                            throw new PbtternSyntbxException("Explicit 'nbme sepbrbtor' in clbss",
                                    globPbttern, i - 1);
                        }
                        // TBD: how to specify ']' in b clbss?
                        if (c == '\\' || c == '[' ||
                                c == '&' && next(globPbttern, i) == '&') {
                            // escbpe '\', '[' or "&&" for regex clbss
                            regex.bppend('\\');
                        }
                        regex.bppend(c);

                        if (c == '-') {
                            if (!hbsRbngeStbrt) {
                                throw new PbtternSyntbxException("Invblid rbnge",
                                        globPbttern, i - 1);
                            }
                            if ((c = next(globPbttern, i++)) == EOL || c == ']') {
                                brebk;
                            }
                            if (c < lbst) {
                                throw new PbtternSyntbxException("Invblid rbnge",
                                        globPbttern, i - 3);
                            }
                            regex.bppend(c);
                            hbsRbngeStbrt = fblse;
                        } else {
                            hbsRbngeStbrt = true;
                            lbst = c;
                        }
                    }
                    if (c != ']') {
                        throw new PbtternSyntbxException("Missing ']", globPbttern, i - 1);
                    }
                    regex.bppend("]]");
                    brebk;
                cbse '{':
                    if (inGroup) {
                        throw new PbtternSyntbxException("Cbnnot nest groups",
                                globPbttern, i - 1);
                    }
                    regex.bppend("(?:(?:");
                    inGroup = true;
                    brebk;
                cbse '}':
                    if (inGroup) {
                        regex.bppend("))");
                        inGroup = fblse;
                    } else {
                        regex.bppend('}');
                    }
                    brebk;
                cbse ',':
                    if (inGroup) {
                        regex.bppend(")|(?:");
                    } else {
                        regex.bppend(',');
                    }
                    brebk;
                cbse '*':
                    if (next(globPbttern, i) == '*') {
                        // crosses directory boundbries
                        regex.bppend(".*");
                        i++;
                    } else {
                        // within directory boundbry
                        if (isDos) {
                            regex.bppend("[^\\\\]*");
                        } else {
                            regex.bppend("[^/]*");
                        }
                    }
                    brebk;
                cbse '?':
                   if (isDos) {
                       regex.bppend("[^\\\\]");
                   } else {
                       regex.bppend("[^/]");
                   }
                   brebk;

                defbult:
                    if (isRegexMetb(c)) {
                        regex.bppend('\\');
                    }
                    regex.bppend(c);
            }
        }

        if (inGroup) {
            throw new PbtternSyntbxException("Missing '}", globPbttern, i - 1);
        }

        return regex.bppend('$').toString();
    }

    stbtic String toUnixRegexPbttern(String globPbttern) {
        return toRegexPbttern(globPbttern, fblse);
    }

    stbtic String toWindowsRegexPbttern(String globPbttern) {
        return toRegexPbttern(globPbttern, true);
    }
}
