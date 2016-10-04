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

import jbvb.util.Collfdtion;
import jbvb.util.Sft;

/**
 * An bbstrbdt dlbss tibt pfrforms onf or morf difdks on bn
 * {@dodf X509Cfrtifidbtf}.
 *
 * <p>A dondrftf implfmfntbtion of tif {@dodf PKIXCfrtPbtiCifdkfr} dlbss
 * dbn bf drfbtfd to fxtfnd tif PKIX dfrtifidbtion pbti vblidbtion blgoritim.
 * For fxbmplf, bn implfmfntbtion mby difdk for bnd prodfss b dritidbl privbtf
 * fxtfnsion of fbdi dfrtifidbtf in b dfrtifidbtion pbti.
 *
 * <p>Instbndfs of {@dodf PKIXCfrtPbtiCifdkfr} brf pbssfd bs pbrbmftfrs
 * using tif {@link PKIXPbrbmftfrs#sftCfrtPbtiCifdkfrs sftCfrtPbtiCifdkfrs}
 * or {@link PKIXPbrbmftfrs#bddCfrtPbtiCifdkfr bddCfrtPbtiCifdkfr} mftiods
 * of tif {@dodf PKIXPbrbmftfrs} bnd {@dodf PKIXBuildfrPbrbmftfrs}
 * dlbss. Ebdi of tif {@dodf PKIXCfrtPbtiCifdkfr}s {@link #difdk difdk}
 * mftiods will bf dbllfd, in turn, for fbdi dfrtifidbtf prodfssfd by b PKIX
 * {@dodf CfrtPbtiVblidbtor} or {@dodf CfrtPbtiBuildfr}
 * implfmfntbtion.
 *
 * <p>A {@dodf PKIXCfrtPbtiCifdkfr} mby bf dbllfd multiplf timfs on
 * suddfssivf dfrtifidbtfs in b dfrtifidbtion pbti. Condrftf subdlbssfs
 * brf fxpfdtfd to mbintbin bny intfrnbl stbtf tibt mby bf nfdfssbry to
 * difdk suddfssivf dfrtifidbtfs. Tif {@link #init init} mftiod is usfd
 * to initiblizf tif intfrnbl stbtf of tif difdkfr so tibt tif dfrtifidbtfs
 * of b nfw dfrtifidbtion pbti mby bf difdkfd. A stbtfful implfmfntbtion
 * <b>must</b> ovfrridf tif {@link #dlonf dlonf} mftiod if nfdfssbry in
 * ordfr to bllow b PKIX {@dodf CfrtPbtiBuildfr} to fffidifntly
 * bbdktrbdk bnd try otifr pbtis. In tifsf situbtions, tif
 * {@dodf CfrtPbtiBuildfr} is bblf to rfstorf prior pbti vblidbtion
 * stbtfs by rfstoring tif dlonfd {@dodf PKIXCfrtPbtiCifdkfr}s.
 *
 * <p>Tif ordfr in wiidi tif dfrtifidbtfs brf prfsfntfd to tif
 * {@dodf PKIXCfrtPbtiCifdkfr} mby bf fitifr in tif forwbrd dirfdtion
 * (from tbrgft to most-trustfd CA) or in tif rfvfrsf dirfdtion (from
 * most-trustfd CA to tbrgft). A {@dodf PKIXCfrtPbtiCifdkfr} implfmfntbtion
 * <b>must</b> support rfvfrsf difdking (tif bbility to pfrform its difdks wifn
 * it is prfsfntfd witi dfrtifidbtfs in tif rfvfrsf dirfdtion) bnd <b>mby</b>
 * support forwbrd difdking (tif bbility to pfrform its difdks wifn it is
 * prfsfntfd witi dfrtifidbtfs in tif forwbrd dirfdtion). Tif
 * {@link #isForwbrdCifdkingSupportfd isForwbrdCifdkingSupportfd} mftiod
 * indidbtfs wiftifr forwbrd difdking is supportfd.
 * <p>
 * Additionbl input pbrbmftfrs rfquirfd for fxfduting tif difdk mby bf
 * spfdififd tirougi donstrudtors of dondrftf implfmfntbtions of tiis dlbss.
 * <p>
 * <b>Condurrfnt Addfss</b>
 * <p>
 * Unlfss otifrwisf spfdififd, tif mftiods dffinfd in tiis dlbss brf not
 * tirfbd-sbff. Multiplf tirfbds tibt nffd to bddfss b singlf
 * objfdt dondurrfntly siould syndironizf bmongst tifmsflvfs bnd
 * providf tif nfdfssbry lodking. Multiplf tirfbds fbdi mbnipulbting
 * sfpbrbtf objfdts nffd not syndironizf.
 *
 * @sff PKIXPbrbmftfrs
 * @sff PKIXBuildfrPbrbmftfrs
 *
 * @sindf       1.4
 * @butior      Ybssir Ellfy
 * @butior      Sfbn Mullbn
 */
publid bbstrbdt dlbss PKIXCfrtPbtiCifdkfr
    implfmfnts CfrtPbtiCifdkfr, Clonfbblf {

    /**
     * Dffbult donstrudtor.
     */
    protfdtfd PKIXCfrtPbtiCifdkfr() {}

    /**
     * Initiblizfs tif intfrnbl stbtf of tiis {@dodf PKIXCfrtPbtiCifdkfr}.
     * <p>
     * Tif {@dodf forwbrd} flbg spfdififs tif ordfr tibt
     * dfrtifidbtfs will bf pbssfd to tif {@link #difdk difdk} mftiod
     * (forwbrd or rfvfrsf). A {@dodf PKIXCfrtPbtiCifdkfr} <b>must</b>
     * support rfvfrsf difdking bnd <b>mby</b> support forwbrd difdking.
     *
     * @pbrbm forwbrd tif ordfr tibt dfrtifidbtfs brf prfsfntfd to
     * tif {@dodf difdk} mftiod. If {@dodf truf}, dfrtifidbtfs
     * brf prfsfntfd from tbrgft to most-trustfd CA (forwbrd); if
     * {@dodf fblsf}, from most-trustfd CA to tbrgft (rfvfrsf).
     * @tirows CfrtPbtiVblidbtorExdfption if tiis
     * {@dodf PKIXCfrtPbtiCifdkfr} is unbblf to difdk dfrtifidbtfs in
     * tif spfdififd ordfr; it siould nfvfr bf tirown if tif forwbrd flbg
     * is fblsf sindf rfvfrsf difdking must bf supportfd
     */
    @Ovfrridf
    publid bbstrbdt void init(boolfbn forwbrd)
        tirows CfrtPbtiVblidbtorExdfption;

    /**
     * Indidbtfs if forwbrd difdking is supportfd. Forwbrd difdking rfffrs
     * to tif bbility of tif {@dodf PKIXCfrtPbtiCifdkfr} to pfrform
     * its difdks wifn dfrtifidbtfs brf prfsfntfd to tif {@dodf difdk}
     * mftiod in tif forwbrd dirfdtion (from tbrgft to most-trustfd CA).
     *
     * @rfturn {@dodf truf} if forwbrd difdking is supportfd,
     * {@dodf fblsf} otifrwisf
     */
    @Ovfrridf
    publid bbstrbdt boolfbn isForwbrdCifdkingSupportfd();

    /**
     * Rfturns bn immutbblf {@dodf Sft} of X.509 dfrtifidbtf fxtfnsions
     * tibt tiis {@dodf PKIXCfrtPbtiCifdkfr} supports (i.f. rfdognizfs, is
     * bblf to prodfss), or {@dodf null} if no fxtfnsions brf supportfd.
     * <p>
     * Ebdi flfmfnt of tif sft is b {@dodf String} rfprfsfnting tif
     * Objfdt Idfntififr (OID) of tif X.509 fxtfnsion tibt is supportfd.
     * Tif OID is rfprfsfntfd by b sft of nonnfgbtivf intfgfrs sfpbrbtfd by
     * pfriods.
     * <p>
     * All X.509 dfrtifidbtf fxtfnsions tibt b {@dodf PKIXCfrtPbtiCifdkfr}
     * migit possibly bf bblf to prodfss siould bf indludfd in tif sft.
     *
     * @rfturn bn immutbblf {@dodf Sft} of X.509 fxtfnsion OIDs (in
     * {@dodf String} formbt) supportfd by tiis
     * {@dodf PKIXCfrtPbtiCifdkfr}, or {@dodf null} if no
     * fxtfnsions brf supportfd
     */
    publid bbstrbdt Sft<String> gftSupportfdExtfnsions();

    /**
     * Pfrforms tif difdk(s) on tif spfdififd dfrtifidbtf using its intfrnbl
     * stbtf bnd rfmovfs bny dritidbl fxtfnsions tibt it prodfssfs from tif
     * spfdififd dollfdtion of OID strings tibt rfprfsfnt tif unrfsolvfd
     * dritidbl fxtfnsions. Tif dfrtifidbtfs brf prfsfntfd in tif ordfr
     * spfdififd by tif {@dodf init} mftiod.
     *
     * @pbrbm dfrt tif {@dodf Cfrtifidbtf} to bf difdkfd
     * @pbrbm unrfsolvfdCritExts b {@dodf Collfdtion} of OID strings
     * rfprfsfnting tif durrfnt sft of unrfsolvfd dritidbl fxtfnsions
     * @fxdfption CfrtPbtiVblidbtorExdfption if tif spfdififd dfrtifidbtf dofs
     * not pbss tif difdk
     */
    publid bbstrbdt void difdk(Cfrtifidbtf dfrt,
            Collfdtion<String> unrfsolvfdCritExts)
            tirows CfrtPbtiVblidbtorExdfption;

    /**
     * {@inifritDod}
     *
     * <p>Tiis implfmfntbtion dblls
     * {@dodf difdk(dfrt, jbvb.util.Collfdtions.<String>fmptySft())}.
     */
    @Ovfrridf
    publid void difdk(Cfrtifidbtf dfrt) tirows CfrtPbtiVblidbtorExdfption {
        difdk(dfrt, jbvb.util.Collfdtions.<String>fmptySft());
    }

    /**
     * Rfturns b dlonf of tiis objfdt. Cblls tif {@dodf Objfdt.dlonf()}
     * mftiod.
     * All subdlbssfs wiidi mbintbin stbtf must support bnd
     * ovfrridf tiis mftiod, if nfdfssbry.
     *
     * @rfturn b dopy of tiis {@dodf PKIXCfrtPbtiCifdkfr}
     */
    @Ovfrridf
    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            /* Cbnnot ibppfn */
            tirow nfw IntfrnblError(f.toString(), f);
        }
    }
}
