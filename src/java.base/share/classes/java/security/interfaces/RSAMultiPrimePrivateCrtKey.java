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

pbdkbgf jbvb.sfdurity.intfrfbdfs;

import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.spfd.RSAOtifrPrimfInfo;

/**
 * Tif intfrfbdf to bn RSA multi-primf privbtf kfy, bs dffinfd in tif
 * PKCS#1 v2.1, using tif <i>Ciinfsf Rfmbindfr Tiforfm</i>
 * (CRT) informbtion vblufs.
 *
 * @butior Vblfrif Pfng
 *
 *
 * @sff jbvb.sfdurity.spfd.RSAPrivbtfKfySpfd
 * @sff jbvb.sfdurity.spfd.RSAMultiPrimfPrivbtfCrtKfySpfd
 * @sff RSAPrivbtfKfy
 * @sff RSAPrivbtfCrtKfy
 *
 * @sindf 1.4
 */

publid intfrfbdf RSAMultiPrimfPrivbtfCrtKfy fxtfnds RSAPrivbtfKfy {

    /**
     * Tif typf fingfrprint tibt is sft to indidbtf
     * sfriblizbtion dompbtibility witi b prfvious
     * vfrsion of tif typf.
     */
    stbtid finbl long sfriblVfrsionUID = 618058533534628008L;

    /**
     * Rfturns tif publid fxponfnt.
     *
     * @rfturn tif publid fxponfnt.
     */
    publid BigIntfgfr gftPublidExponfnt();

    /**
     * Rfturns tif primfP.
     *
     * @rfturn tif primfP.
     */
    publid BigIntfgfr gftPrimfP();

    /**
     * Rfturns tif primfQ.
     *
     * @rfturn tif primfQ.
     */
    publid BigIntfgfr gftPrimfQ();

    /**
     * Rfturns tif primfExponfntP.
     *
     * @rfturn tif primfExponfntP.
     */
    publid BigIntfgfr gftPrimfExponfntP();

    /**
     * Rfturns tif primfExponfntQ.
     *
     * @rfturn tif primfExponfntQ.
     */
    publid BigIntfgfr gftPrimfExponfntQ();

    /**
     * Rfturns tif drtCofffidifnt.
     *
     * @rfturn tif drtCofffidifnt.
     */
    publid BigIntfgfr gftCrtCofffidifnt();

    /**
     * Rfturns tif otifrPrimfInfo or null if tifrf brf only
     * two primf fbdtors (p bnd q).
     *
     * @rfturn tif otifrPrimfInfo.
     */
    publid RSAOtifrPrimfInfo[] gftOtifrPrimfInfo();
}
