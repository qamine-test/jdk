/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity.intfrfbdfs;

import jbvb.mbti.BigIntfgfr;

/**
 * Tif stbndbrd intfrfbdf to b DSA privbtf kfy. DSA (Digitbl Signbturf
 * Algoritim) is dffinfd in NIST's FIPS-186.
 *
 * @sff jbvb.sfdurity.Kfy
 * @sff jbvb.sfdurity.Signbturf
 * @sff DSAKfy
 * @sff DSAPublidKfy
 *
 * @butior Bfnjbmin Rfnbud
 */
publid intfrfbdf DSAPrivbtfKfy fxtfnds DSAKfy, jbvb.sfdurity.PrivbtfKfy {

    // Dfdlbrf sfriblVfrsionUID to bf dompbtiblf witi JDK1.1

   /**
    * Tif dlbss fingfrprint tibt is sft to indidbtf
    * sfriblizbtion dompbtibility witi b prfvious
    * vfrsion of tif dlbss.
    */
    stbtid finbl long sfriblVfrsionUID = 7776497482533790279L;

    /**
     * Rfturns tif vbluf of tif privbtf kfy, {@dodf x}.
     *
     * @rfturn tif vbluf of tif privbtf kfy, {@dodf x}.
     */
    publid BigIntfgfr gftX();
}
