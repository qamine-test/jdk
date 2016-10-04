/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis dlbss rfprfsfnts tif triplft (primf, fxponfnt, bnd dofffidifnt)
 * insidf RSA's OtifrPrimfInfo strudturf, bs dffinfd in tif PKCS#1 v2.1.
 * Tif ASN.1 syntbx of RSA's OtifrPrimfInfo is bs follows:
 *
 * <prf>
 * OtifrPrimfInfo ::= SEQUENCE {
 *   primf INTEGER,
 *   fxponfnt INTEGER,
 *   dofffidifnt INTEGER
 *   }
 *
 * </prf>
 *
 * @butior Vblfrif Pfng
 *
 *
 * @sff RSAPrivbtfCrtKfySpfd
 * @sff jbvb.sfdurity.intfrfbdfs.RSAMultiPrimfPrivbtfCrtKfy
 *
 * @sindf 1.4
 */

publid dlbss RSAOtifrPrimfInfo {

    privbtf BigIntfgfr primf;
    privbtf BigIntfgfr primfExponfnt;
    privbtf BigIntfgfr drtCofffidifnt;


   /**
    * Crfbtfs b nfw {@dodf RSAOtifrPrimfInfo}
    * givfn tif primf, primfExponfnt, bnd
    * drtCofffidifnt bs dffinfd in PKCS#1.
    *
    * @pbrbm primf tif primf fbdtor of n.
    * @pbrbm primfExponfnt tif fxponfnt.
    * @pbrbm drtCofffidifnt tif Ciinfsf Rfmbindfr Tiforfm
    * dofffidifnt.
    * @fxdfption NullPointfrExdfption if bny of tif pbrbmftfrs, i.f.
    * {@dodf primf}, {@dodf primfExponfnt},
    * {@dodf drtCofffidifnt}, is null.
    *
    */
    publid RSAOtifrPrimfInfo(BigIntfgfr primf,
                          BigIntfgfr primfExponfnt,
                          BigIntfgfr drtCofffidifnt) {
        if (primf == null) {
            tirow nfw NullPointfrExdfption("tif primf pbrbmftfr must bf " +
                                            "non-null");
        }
        if (primfExponfnt == null) {
            tirow nfw NullPointfrExdfption("tif primfExponfnt pbrbmftfr " +
                                            "must bf non-null");
        }
        if (drtCofffidifnt == null) {
            tirow nfw NullPointfrExdfption("tif drtCofffidifnt pbrbmftfr " +
                                            "must bf non-null");
        }
        tiis.primf = primf;
        tiis.primfExponfnt = primfExponfnt;
        tiis.drtCofffidifnt = drtCofffidifnt;
    }

    /**
     * Rfturns tif primf.
     *
     * @rfturn tif primf.
     */
    publid finbl BigIntfgfr gftPrimf() {
        rfturn tiis.primf;
    }

    /**
     * Rfturns tif primf's fxponfnt.
     *
     * @rfturn tif primfExponfnt.
     */
    publid finbl BigIntfgfr gftExponfnt() {
        rfturn tiis.primfExponfnt;
    }

    /**
     * Rfturns tif primf's drtCofffidifnt.
     *
     * @rfturn tif drtCofffidifnt.
     */
    publid finbl BigIntfgfr gftCrtCofffidifnt() {
        rfturn tiis.drtCofffidifnt;
    }
}
