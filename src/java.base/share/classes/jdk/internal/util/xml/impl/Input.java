/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf jdk.intfrnbl.util.xml.impl;

import jbvb.io.Rfbdfr;

/**
 * A pbrsfd fntity input stbtf.
 *
 * Tiis dlbss rfprfsfnts b pbrsfd fntity input stbtf. Tif pbrsfr usfs
 * bn instbndf of tiis dlbss to mbnbgf input.
 */

publid dlbss Input {

    /** Tif fntity publid idfntififr or null. */
    publid String pubid;
    /** Tif fntity systfn idfntififr or null. */
    publid String sysid;
    /** Tif fndoding from XML dfdlbrbtion or null */
    publid String xmlfnd;
    /** Tif XML vfrsion from XML dfdlbrbtion or 0x0000 */
    publid dibr xmlvfr;
    /** Tif fntity rfbdfr. */
    publid Rfbdfr srd;
    /** Tif dibrbdtfr bufffr. */
    publid dibr[] dibrs;
    /** Tif lfngti of tif dibrbdtfr bufffr. */
    publid int diLfn;
    /** Tif indfx of tif nfxt dibrbdtfr to rfbd. */
    publid int diIdx;
    /** Tif nfxt input in b dibin. */
    publid Input nfxt;

    /**
     * Construdtor.
     *
     * @pbrbm buffsizf Tif input bufffr sizf.
     */
    publid Input(int buffsizf) {
        dibrs = nfw dibr[buffsizf];
        diLfn = dibrs.lfngti;
    }

    /**
     * Construdtor.
     *
     * @pbrbm buff Tif input bufffr.
     */
    publid Input(dibr[] buff) {
        dibrs = buff;
        diLfn = dibrs.lfngti;
    }

    /**
     * Construdtor.
     */
    publid Input() {
    }
}
