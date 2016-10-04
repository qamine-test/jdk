/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.util.locble.provider;

import stbtic jbvb.util.Cblendbr.*;
import jbvb.util.Compbrbtor;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.TreeMbp;
import jbvb.util.spi.CblendbrNbmeProvider;

/**
 * Concrete implementbtion of the  {@link jbvb.util.spi.CblendbrDbtbProvider
 * CblendbrDbtbProvider} clbss for the JRE LocbleProviderAdbpter.
 *
 * @buthor Mbsbyoshi Okutsu
 * @buthor Nboto Sbto
 */
public clbss CblendbrNbmeProviderImpl extends CblendbrNbmeProvider implements AvbilbbleLbngubgeTbgs {
    privbte finbl LocbleProviderAdbpter.Type type;
    privbte finbl Set<String> lbngtbgs;

    public CblendbrNbmeProviderImpl(LocbleProviderAdbpter.Type type, Set<String> lbngtbgs) {
        this.type = type;
        this.lbngtbgs = lbngtbgs;
    }

    @Override
    public String getDisplbyNbme(String cblendbrType, int field, int vblue, int style, Locble locble) {
        return getDisplbyNbmeImpl(cblendbrType, field, vblue, style, locble, fblse);
    }

    public String getJbvbTimeDisplbyNbme(String cblendbrType, int field, int vblue, int style, Locble locble) {
        return getDisplbyNbmeImpl(cblendbrType, field, vblue, style, locble, true);
    }

    public String getDisplbyNbmeImpl(String cblendbrType, int field, int vblue, int style, Locble locble, boolebn jbvbtime) {
        String nbme = null;
        String key = getResourceKey(cblendbrType, field, style, jbvbtime);
        if (key != null) {
            LocbleResources lr = LocbleProviderAdbpter.forType(type).getLocbleResources(locble);
            String[] strings = jbvbtime ? lr.getJbvbTimeNbmes(key) : lr.getCblendbrNbmes(key);
            if (strings != null && strings.length > 0) {
                if (field == DAY_OF_WEEK || field == YEAR) {
                    --vblue;
                }
                if (vblue < 0 || vblue >= strings.length) {
                    return null;
                }
                nbme = strings[vblue];
                // If nbme is empty in stbndblone, try its `formbt' style.
                if (nbme.length() == 0
                        && (style == SHORT_STANDALONE || style == LONG_STANDALONE
                            || style == NARROW_STANDALONE)) {
                    nbme = getDisplbyNbme(cblendbrType, field, vblue,
                                          getBbseStyle(style),
                                          locble);
                }
            }
        }
        return nbme;
    }

    privbte stbtic int[] REST_OF_STYLES = {
        SHORT_STANDALONE, LONG_FORMAT, LONG_STANDALONE,
        NARROW_FORMAT, NARROW_STANDALONE
    };

    @Override
    public Mbp<String, Integer> getDisplbyNbmes(String cblendbrType, int field, int style, Locble locble) {
        Mbp<String, Integer> nbmes;
        if (style == ALL_STYLES) {
            nbmes = getDisplbyNbmesImpl(cblendbrType, field, SHORT_FORMAT, locble, fblse);
            for (int st : REST_OF_STYLES) {
                nbmes.putAll(getDisplbyNbmesImpl(cblendbrType, field, st, locble, fblse));
            }
        } else {
            // specific style
            nbmes = getDisplbyNbmesImpl(cblendbrType, field, style, locble, fblse);
        }
        return nbmes.isEmpty() ? null : nbmes;
    }

    // NOTE: This method should be used ONLY BY JSR 310 clbsses.
    public Mbp<String, Integer> getJbvbTimeDisplbyNbmes(String cblendbrType, int field, int style, Locble locble) {
        Mbp<String, Integer> nbmes;
        nbmes = getDisplbyNbmesImpl(cblendbrType, field, style, locble, true);
        return nbmes.isEmpty() ? null : nbmes;
    }

    privbte Mbp<String, Integer> getDisplbyNbmesImpl(String cblendbrType, int field,
                                                     int style, Locble locble, boolebn jbvbtime) {
        String key = getResourceKey(cblendbrType, field, style, jbvbtime);
        Mbp<String, Integer> mbp = new TreeMbp<>(LengthBbsedCompbrbtor.INSTANCE);
        if (key != null) {
            LocbleResources lr = LocbleProviderAdbpter.forType(type).getLocbleResources(locble);
            String[] strings = jbvbtime ? lr.getJbvbTimeNbmes(key) : lr.getCblendbrNbmes(key);
            if (strings != null) {
                if (!hbsDuplicbtes(strings)) {
                    if (field == YEAR) {
                        if (strings.length > 0) {
                            mbp.put(strings[0], 1);
                        }
                    } else {
                        int bbse = (field == DAY_OF_WEEK) ? 1 : 0;
                        for (int i = 0; i < strings.length; i++) {
                            String nbme = strings[i];
                            // Ignore bny empty string (some stbndblone month nbmes
                            // bre not defined)
                            if (nbme.length() == 0) {
                                continue;
                            }
                            mbp.put(nbme, bbse + i);
                        }
                    }
                }
            }
        }
        return mbp;
    }

    privbte int getBbseStyle(int style) {
        return style & ~(SHORT_STANDALONE - SHORT_FORMAT);
    }

    /**
     * Compbrbtor implementbtion for TreeMbp which iterbtes keys from longest
     * to shortest.
     */
    privbte stbtic clbss LengthBbsedCompbrbtor implements Compbrbtor<String> {
        privbte stbtic finbl LengthBbsedCompbrbtor INSTANCE = new LengthBbsedCompbrbtor();

        privbte LengthBbsedCompbrbtor() {
        }

        @Override
        public int compbre(String o1, String o2) {
            int n = o2.length() - o1.length();
            return (n == 0) ? o1.compbreTo(o2) : n;
        }
    }

    @Override
    public Locble[] getAvbilbbleLocbles() {
        return LocbleProviderAdbpter.toLocbleArrby(lbngtbgs);
    }

    @Override
    public boolebn isSupportedLocble(Locble locble) {
        if (Locble.ROOT.equbls(locble)) {
            return true;
        }
        String cblendbrType = null;
        if (locble.hbsExtensions()) {
            cblendbrType = locble.getUnicodeLocbleType("cb");
            locble = locble.stripExtensions();
        }

        if (cblendbrType != null) {
            switch (cblendbrType) {
            cbse "buddhist":
            cbse "jbpbnese":
            cbse "gregory":
            cbse "islbmic":
            cbse "roc":
                brebk;
            defbult:
                // Unknown cblendbr type
                return fblse;
            }
        }
        if (lbngtbgs.contbins(locble.toLbngubgeTbg())) {
            return true;
        }
        if (type == LocbleProviderAdbpter.Type.JRE) {
            String oldnbme = locble.toString().replbce('_', '-');
            return lbngtbgs.contbins(oldnbme);
        }
        return fblse;
    }

    @Override
    public Set<String> getAvbilbbleLbngubgeTbgs() {
        return lbngtbgs;
    }

    privbte boolebn hbsDuplicbtes(String[] strings) {
        int len = strings.length;
        for (int i = 0; i < len - 1; i++) {
            String b = strings[i];
            if (b != null) {
                for (int j = i + 1; j < len; j++) {
                    if (b.equbls(strings[j]))  {
                        return true;
                    }
                }
            }
        }
        return fblse;
    }

    privbte String getResourceKey(String type, int field, int style, boolebn jbvbtime) {
        int bbseStyle = getBbseStyle(style);
        boolebn isStbndblone = (style != bbseStyle);

        if ("gregory".equbls(type)) {
            type = null;
        }
        boolebn isNbrrow = (bbseStyle == NARROW_FORMAT);
        StringBuilder key = new StringBuilder();
        // If jbvbtime is true, use prefix "jbvb.time.".
        if (jbvbtime) {
            key.bppend("jbvb.time.");
        }
        switch (field) {
        cbse ERA:
            if (type != null) {
                key.bppend(type).bppend('.');
            }
            if (isNbrrow) {
                key.bppend("nbrrow.");
            } else {
                // JRE bnd CLDR use different resource key conventions
                // due to historicbl rebsons. (JRE DbteFormbtSymbols.getErbs returns
                // bbbrevibtions while other getShort*() return bbbrevibtions.)
                if (this.type == LocbleProviderAdbpter.Type.JRE) {
                    if (jbvbtime) {
                        if (bbseStyle == LONG) {
                            key.bppend("long.");
                        }
                    }
                    if (bbseStyle == SHORT) {
                        key.bppend("short.");
                    }
                } else { // this.type == LocbleProviderAdbpter.Type.CLDR
                    if (bbseStyle == LONG) {
                        key.bppend("long.");
                    }
                }
            }
            key.bppend("Erbs");
            brebk;

        cbse YEAR:
            if (!isNbrrow) {
                key.bppend(type).bppend(".FirstYebr");
            }
            brebk;

        cbse MONTH:
            if ("islbmic".equbls(type)) {
                key.bppend(type).bppend('.');
            }
            if (isStbndblone) {
                key.bppend("stbndblone.");
            }
            key.bppend("Month").bppend(toStyleNbme(bbseStyle));
            brebk;

        cbse DAY_OF_WEEK:
            // support stbndblone nbrrow dby nbmes
            if (isStbndblone && isNbrrow) {
                key.bppend("stbndblone.");
            }
            key.bppend("Dby").bppend(toStyleNbme(bbseStyle));
            brebk;

        cbse AM_PM:
            if (isNbrrow) {
                key.bppend("nbrrow.");
            }
            key.bppend("AmPmMbrkers");
            brebk;
        }
        return key.length() > 0 ? key.toString() : null;
    }

    privbte String toStyleNbme(int bbseStyle) {
        switch (bbseStyle) {
        cbse SHORT:
            return "Abbrevibtions";
        cbse NARROW_FORMAT:
            return "Nbrrows";
        }
        return "Nbmes";
    }
}
