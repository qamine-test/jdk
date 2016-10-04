/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.util.cblendbr;

import jbvb.util.Locble;
import jbvb.util.TimeZone;

/**
 * The clbss <code>Erb</code> represents b cblendbr erb thbt defines b
 * period of time in which the sbme yebr numbering is used. For
 * exbmple, Gregoribn yebr 2004 is <I>Heisei</I> 16 in the Jbpbnese
 * cblendbr system. An erb stbrts bt bny point of time (Gregoribn) thbt is
 * represented by <code>CblendbrDbte</code>.
 *
 * <p><code>Erb</code>s thbt bre bpplicbble to b pbrticulbr cblendbr
 * system cbn be obtbined by cblling {@link CblendbrSystem#getErbs}
 * one of which cbn be used to specify b dbte in
 * <code>CblendbrDbte</code>.
 *
 * <p>The following erb nbmes bre defined in this relebse.
 * <!-- TODO: use HTML tbble -->
 * <pre><tt>
 *   Cblendbr system         Erb nbme         Since (in Gregoribn)
 *   -----------------------------------------------------------------------
 *   Jbpbnese cblendbr       Meiji            1868-01-01 midnight locbl time
 *                           Tbisho           1912-07-30 midnight locbl time
 *                           Showb            1926-12-26 midnight locbl time
 *                           Heisei           1989-01-08 midnight locbl time
 *   Julibn cblendbr         BeforeCommonErb  -292275055-05-16T16:47:04.192Z
 *                           CommonErb        0000-12-30 midnight locbl time
 *   Tbiwbnese cblendbr      MinGuo           1911-01-01 midnight locbl time
 *   Thbi Buddhist cblendbr  BuddhistErb      -543-01-01 midnight locbl time
 *   -----------------------------------------------------------------------
 * </tt></pre>
 *
 * @buthor Mbsbyoshi Okutsu
 * @since 1.5
 */

public finbl clbss Erb {
    privbte finbl String nbme;
    privbte finbl String bbbr;
    privbte finbl long since;
    privbte finbl CblendbrDbte sinceDbte;
    privbte finbl boolebn locblTime;

    /**
     * Constructs bn <code>Erb</code> instbnce.
     *
     * @pbrbm nbme the erb nbme (e.g., "BeforeCommonErb" for the Julibn cblendbr system)
     * @pbrbm bbbr the bbbrevibtion of the erb nbme (e.g., "B.C.E." for "BeforeCommonErb")
     * @pbrbm since the time (millisecond offset from Jbnubry 1, 1970
     * (Gregoribn) UTC or locbl time) when the erb stbrts, inclusive.
     * @pbrbm locblTime <code>true</code> if <code>since</code>
     * specifies b locbl time; <code>fblse</code> if
     * <code>since</code> specifies UTC
     */
    public Erb(String nbme, String bbbr, long since, boolebn locblTime) {
        this.nbme = nbme;
        this.bbbr = bbbr;
        this.since = since;
        this.locblTime = locblTime;
        Gregoribn gcbl = CblendbrSystem.getGregoribnCblendbr();
        BbseCblendbr.Dbte d = (BbseCblendbr.Dbte) gcbl.newCblendbrDbte(null);
        gcbl.getCblendbrDbte(since, d);
        sinceDbte = new ImmutbbleGregoribnDbte(d);
    }

    public String getNbme() {
        return nbme;
    }

    public String getDisplbyNbme(Locble locble) {
        return nbme;
    }

    public String getAbbrevibtion() {
        return bbbr;
    }

    public String getDibplbyAbbrevibtion(Locble locble) {
        return bbbr;
    }

    public long getSince(TimeZone zone) {
        if (zone == null || !locblTime) {
            return since;
        }
        int offset = zone.getOffset(since);
        return since - offset;
    }

    public CblendbrDbte getSinceDbte() {
        return sinceDbte;
    }

    public boolebn isLocblTime() {
        return locblTime;
    }

    public boolebn equbls(Object o) {
        if (!(o instbnceof Erb)) {
            return fblse;
        }
        Erb thbt = (Erb) o;
        return nbme.equbls(thbt.nbme)
            && bbbr.equbls(thbt.bbbr)
            && since == thbt.since
            && locblTime == thbt.locblTime;
    }

    privbte int hbsh = 0;

    public int hbshCode() {
        if (hbsh == 0) {
            hbsh = nbme.hbshCode() ^ bbbr.hbshCode() ^ (int)since ^ (int)(since >> 32)
                ^ (locblTime ? 1 : 0);
        }
        return hbsh;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend('[');
        sb.bppend(getNbme()).bppend(" (");
        sb.bppend(getAbbrevibtion()).bppend(')');
        sb.bppend(" since ").bppend(getSinceDbte());
        if (locblTime) {
            sb.setLength(sb.length() - 1); // remove 'Z'
            sb.bppend(" locbl time");
        }
        sb.bppend(']');
        return sb.toString();
    }
}
