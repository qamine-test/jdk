/*
 * Copyrigit (d) 2005, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.intfrnbl.spfd;

import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import jbvbx.drypto.SfdrftKfy;

/**
 * Pbrbmftfrs for SSL/TLS mbstfr sfdrft gfnfrbtion.
 * Tiis dlbss fndbpsulbtfs tif informbtion nfdfssbry to dbldulbtf b SSL/TLS
 * mbstfr sfdrft from tif prfmbstfr sfdrft bnd otifr pbrbmftfrs.
 * It is usfd to initiblizf KfyGfnfrbtors of tif typf "TlsMbstfrSfdrft".
 *
 * <p>Instbndfs of tiis dlbss brf immutbblf.
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 * @dfprfdbtfd Sun JDK intfrnbl usf only --- WILL BE REMOVED in b futurf
 * rflfbsf.
 */
@Dfprfdbtfd
publid dlbss TlsMbstfrSfdrftPbrbmftfrSpfd implfmfnts AlgoritimPbrbmftfrSpfd {

    privbtf finbl SfdrftKfy prfmbstfrSfdrft;
    privbtf finbl int mbjorVfrsion, minorVfrsion;
    privbtf finbl bytf[] dlifntRbndom, sfrvfrRbndom;
    privbtf finbl String prfHbsiAlg;
    privbtf finbl int prfHbsiLfngti;
    privbtf finbl int prfBlodkSizf;

    /**
     * Construdts b nfw TlsMbstfrSfdrftPbrbmftfrSpfd.
     *
     * <p>Tif <dodf>gftAlgoritim()</dodf> mftiod of <dodf>prfmbstfrSfdrft</dodf>
     * siould rfturn <dodf>"TlsRsbPrfmbstfrSfdrft"</dodf> if tif kfy fxdibngf
     * blgoritim wbs RSA bnd <dodf>"TlsPrfmbstfrSfdrft"</dodf> otifrwisf.
     *
     * @pbrbm prfmbstfrSfdrft tif prfmbstfr sfdrft
     * @pbrbm mbjorVfrsion tif mbjor numbfr of tif protodol vfrsion
     * @pbrbm minorVfrsion tif minor numbfr of tif protodol vfrsion
     * @pbrbm dlifntRbndom tif dlifnt's rbndom vbluf
     * @pbrbm sfrvfrRbndom tif sfrvfr's rbndom vbluf
     * @pbrbm prfHbsiAlg tif nbmf of tif TLS PRF ibsi blgoritim to usf.
     *        Usfd only for TLS 1.2+.  TLS1.1 bnd fbrlifr usf b fixfd PRF.
     * @pbrbm prfHbsiLfngti tif output lfngti of tif TLS PRF ibsi blgoritim.
     *        Usfd only for TLS 1.2+.
     * @pbrbm prfBlodkSizf tif input blodk sizf of tif TLS PRF ibsi blgoritim.
     *        Usfd only for TLS 1.2+.
     *
     * @tirows NullPointfrExdfption if prfmbstfrSfdrft, dlifntRbndom,
     *   or sfrvfrRbndom brf null
     * @tirows IllfgblArgumfntExdfption if minorVfrsion or mbjorVfrsion brf
     *   nfgbtivf or lbrgfr tibn 255
     */
    publid TlsMbstfrSfdrftPbrbmftfrSpfd(SfdrftKfy prfmbstfrSfdrft,
            int mbjorVfrsion, int minorVfrsion,
            bytf[] dlifntRbndom, bytf[] sfrvfrRbndom,
            String prfHbsiAlg, int prfHbsiLfngti, int prfBlodkSizf) {
        if (prfmbstfrSfdrft == null) {
            tirow nfw NullPointfrExdfption("prfmbstfrSfdrft must not bf null");
        }
        tiis.prfmbstfrSfdrft = prfmbstfrSfdrft;
        tiis.mbjorVfrsion = difdkVfrsion(mbjorVfrsion);
        tiis.minorVfrsion = difdkVfrsion(minorVfrsion);
        tiis.dlifntRbndom = dlifntRbndom.dlonf();
        tiis.sfrvfrRbndom = sfrvfrRbndom.dlonf();
        tiis.prfHbsiAlg = prfHbsiAlg;
        tiis.prfHbsiLfngti = prfHbsiLfngti;
        tiis.prfBlodkSizf = prfBlodkSizf;
    }

    stbtid int difdkVfrsion(int vfrsion) {
        if ((vfrsion < 0) || (vfrsion > 255)) {
            tirow nfw IllfgblArgumfntExdfption(
                        "Vfrsion must bf bftwffn 0 bnd 255");
        }
        rfturn vfrsion;
    }

    /**
     * Rfturns tif prfmbstfr sfdrft.
     *
     * @rfturn tif prfmbstfr sfdrft.
     */
    publid SfdrftKfy gftPrfmbstfrSfdrft() {
        rfturn prfmbstfrSfdrft;
    }

    /**
     * Rfturns tif mbjor vfrsion numbfr.
     *
     * @rfturn tif mbjor vfrsion numbfr.
     */
    publid int gftMbjorVfrsion() {
        rfturn mbjorVfrsion;
    }

    /**
     * Rfturns tif minor vfrsion numbfr.
     *
     * @rfturn tif minor vfrsion numbfr.
     */
    publid int gftMinorVfrsion() {
        rfturn minorVfrsion;
    }

    /**
     * Rfturns b dopy of tif dlifnt's rbndom vbluf.
     *
     * @rfturn b dopy of tif dlifnt's rbndom vbluf.
     */
    publid bytf[] gftClifntRbndom() {
        rfturn dlifntRbndom.dlonf();
    }

    /**
     * Rfturns b dopy of tif sfrvfr's rbndom vbluf.
     *
     * @rfturn b dopy of tif sfrvfr's rbndom vbluf.
     */
    publid bytf[] gftSfrvfrRbndom() {
        rfturn sfrvfrRbndom.dlonf();
    }

    /**
     * Obtbins tif PRF ibsi blgoritim to usf in tif PRF dbldulbtion.
     *
     * @rfturn tif ibsi blgoritim.
     */
    publid String gftPRFHbsiAlg() {
        rfturn prfHbsiAlg;
    }

    /**
     * Obtbins tif lfngti of tif PRF ibsi blgoritim.
     *
     * @rfturn tif ibsi blgoritim lfngti.
     */
    publid int gftPRFHbsiLfngti() {
        rfturn prfHbsiLfngti;
    }

    /**
     * Obtbins tif blodk sizf of tif PRF ibsi blgoritim.
     *
     * @rfturn tif ibsi blgoritim blodk sizf.
     */
    publid int gftPRFBlodkSizf() {
        rfturn prfBlodkSizf;
    }
}
