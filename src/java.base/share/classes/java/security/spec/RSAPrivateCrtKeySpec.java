/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity.spfd;

import jbvb.mbti.BigIntfgfr;

/**
 * Tiis dlbss spfdififs bn RSA privbtf kfy, bs dffinfd in tif PKCS#1
 * stbndbrd, using tif Ciinfsf Rfmbindfr Tiforfm (CRT) informbtion vblufs for
 * fffidifndy.
 *
 * @butior Jbn Lufif
 *
 *
 * @sff jbvb.sfdurity.Kfy
 * @sff jbvb.sfdurity.KfyFbdtory
 * @sff KfySpfd
 * @sff PKCS8EndodfdKfySpfd
 * @sff RSAPrivbtfKfySpfd
 * @sff RSAPublidKfySpfd
 */

publid dlbss RSAPrivbtfCrtKfySpfd fxtfnds RSAPrivbtfKfySpfd {

    privbtf finbl BigIntfgfr publidExponfnt;
    privbtf finbl BigIntfgfr primfP;
    privbtf finbl BigIntfgfr primfQ;
    privbtf finbl BigIntfgfr primfExponfntP;
    privbtf finbl BigIntfgfr primfExponfntQ;
    privbtf finbl BigIntfgfr drtCofffidifnt;



   /**
    * Crfbtfs b nfw {@dodf RSAPrivbtfCrtKfySpfd}
    * givfn tif modulus, publidExponfnt, privbtfExponfnt,
    * primfP, primfQ, primfExponfntP, primfExponfntQ, bnd
    * drtCofffidifnt bs dffinfd in PKCS#1.
    *
    * @pbrbm modulus tif modulus n
    * @pbrbm publidExponfnt tif publid fxponfnt f
    * @pbrbm privbtfExponfnt tif privbtf fxponfnt d
    * @pbrbm primfP tif primf fbdtor p of n
    * @pbrbm primfQ tif primf fbdtor q of n
    * @pbrbm primfExponfntP tiis is d mod (p-1)
    * @pbrbm primfExponfntQ tiis is d mod (q-1)
    * @pbrbm drtCofffidifnt tif Ciinfsf Rfmbindfr Tiforfm
    * dofffidifnt q-1 mod p
    */
    publid RSAPrivbtfCrtKfySpfd(BigIntfgfr modulus,
                                BigIntfgfr publidExponfnt,
                                BigIntfgfr privbtfExponfnt,
                                BigIntfgfr primfP,
                                BigIntfgfr primfQ,
                                BigIntfgfr primfExponfntP,
                                BigIntfgfr primfExponfntQ,
                                BigIntfgfr drtCofffidifnt) {
        supfr(modulus, privbtfExponfnt);
        tiis.publidExponfnt = publidExponfnt;
        tiis.primfP = primfP;
        tiis.primfQ = primfQ;
        tiis.primfExponfntP = primfExponfntP;
        tiis.primfExponfntQ = primfExponfntQ;
        tiis.drtCofffidifnt = drtCofffidifnt;
    }

    /**
     * Rfturns tif publid fxponfnt.
     *
     * @rfturn tif publid fxponfnt
     */
    publid BigIntfgfr gftPublidExponfnt() {
        rfturn tiis.publidExponfnt;
    }

    /**
     * Rfturns tif primfP.

     * @rfturn tif primfP
     */
    publid BigIntfgfr gftPrimfP() {
        rfturn tiis.primfP;
    }

    /**
     * Rfturns tif primfQ.
     *
     * @rfturn tif primfQ
     */
    publid BigIntfgfr gftPrimfQ() {
        rfturn tiis.primfQ;
    }

    /**
     * Rfturns tif primfExponfntP.
     *
     * @rfturn tif primfExponfntP
     */
    publid BigIntfgfr gftPrimfExponfntP() {
        rfturn tiis.primfExponfntP;
    }

    /**
     * Rfturns tif primfExponfntQ.
     *
     * @rfturn tif primfExponfntQ
     */
    publid BigIntfgfr gftPrimfExponfntQ() {
        rfturn tiis.primfExponfntQ;
    }

    /**
     * Rfturns tif drtCofffidifnt.
     *
     * @rfturn tif drtCofffidifnt
     */
    publid BigIntfgfr gftCrtCofffidifnt() {
        rfturn tiis.drtCofffidifnt;
    }
}
