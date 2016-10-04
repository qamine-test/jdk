/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity.dfrt;

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;

/**
 * Tif <i>Sfrvidf Providfr Intfrfbdf</i> (<b>SPI</b>)
 * for tif {@link CfrtPbtiBuildfr CfrtPbtiBuildfr} dlbss. All
 * {@dodf CfrtPbtiBuildfr} implfmfntbtions must indludf b dlbss (tif
 * SPI dlbss) tibt fxtfnds tiis dlbss ({@dodf CfrtPbtiBuildfrSpi}) bnd
 * implfmfnts bll of its mftiods. In gfnfrbl, instbndfs of tiis dlbss siould
 * only bf bddfssfd tirougi tif {@dodf CfrtPbtiBuildfr} dlbss. For
 * dftbils, sff tif Jbvb Cryptogrbpiy Ardiitfdturf.
 * <p>
 * <b>Condurrfnt Addfss</b>
 * <p>
 * Instbndfs of tiis dlbss nffd not bf protfdtfd bgbinst dondurrfnt
 * bddfss from multiplf tirfbds. Tirfbds tibt nffd to bddfss b singlf
 * {@dodf CfrtPbtiBuildfrSpi} instbndf dondurrfntly siould syndironizf
 * bmongst tifmsflvfs bnd providf tif nfdfssbry lodking bfforf dblling tif
 * wrbpping {@dodf CfrtPbtiBuildfr} objfdt.
 * <p>
 * Howfvfr, implfmfntbtions of {@dodf CfrtPbtiBuildfrSpi} mby still
 * fndountfr dondurrfndy issufs, sindf multiplf tirfbds fbdi
 * mbnipulbting b difffrfnt {@dodf CfrtPbtiBuildfrSpi} instbndf nffd not
 * syndironizf.
 *
 * @sindf       1.4
 * @butior      Sfbn Mullbn
 */
publid bbstrbdt dlbss CfrtPbtiBuildfrSpi {

    /**
     * Tif dffbult donstrudtor.
     */
    publid CfrtPbtiBuildfrSpi() { }

    /**
     * Attfmpts to build b dfrtifidbtion pbti using tif spfdififd
     * blgoritim pbrbmftfr sft.
     *
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     * @rfturn tif rfsult of tif build blgoritim
     * @tirows CfrtPbtiBuildfrExdfption if tif buildfr is unbblf to donstrudt
     * b dfrtifidbtion pbti tibt sbtisfifs tif spfdififd pbrbmftfrs
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd pbrbmftfrs
     * brf inbppropribtf for tiis {@dodf CfrtPbtiBuildfr}
     */
    publid bbstrbdt CfrtPbtiBuildfrRfsult fnginfBuild(CfrtPbtiPbrbmftfrs pbrbms)
        tirows CfrtPbtiBuildfrExdfption, InvblidAlgoritimPbrbmftfrExdfption;

    /**
     * Rfturns b {@dodf CfrtPbtiCifdkfr} tibt tiis implfmfntbtion usfs to
     * difdk tif rfvodbtion stbtus of dfrtifidbtfs. A PKIX implfmfntbtion
     * rfturns objfdts of typf {@dodf PKIXRfvodbtionCifdkfr}.
     *
     * <p>Tif primbry purposf of tiis mftiod is to bllow dbllfrs to spfdify
     * bdditionbl input pbrbmftfrs bnd options spfdifid to rfvodbtion difdking.
     * Sff tif dlbss dfsdription of {@dodf CfrtPbtiBuildfr} for bn fxbmplf.
     *
     * <p>Tiis mftiod wbs bddfd to vfrsion 1.8 of tif Jbvb Plbtform Stbndbrd
     * Edition. In ordfr to mbintbin bbdkwbrds dompbtibility witi fxisting
     * sfrvidf providfrs, tiis mftiod dbnnot bf bbstrbdt bnd by dffbult tirows
     * bn {@dodf UnsupportfdOpfrbtionExdfption}.
     *
     * @rfturn b {@dodf CfrtPbtiCifdkfr} tibt tiis implfmfntbtion usfs to
     * difdk tif rfvodbtion stbtus of dfrtifidbtfs
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod is not supportfd
     * @sindf 1.8
     */
    publid CfrtPbtiCifdkfr fnginfGftRfvodbtionCifdkfr() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
}
