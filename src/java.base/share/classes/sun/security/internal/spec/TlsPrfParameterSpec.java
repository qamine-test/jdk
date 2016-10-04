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
 * Pbrbmftfrs for tif TLS PRF (psfudo-rbndom fundtion). Tif PRF fundtion
 * is dffinfd in RFC 2246.
 * Tiis dlbss is usfd to initiblizf KfyGfnfrbtors of tif typf "TlsPrf".
 *
 * <p>Instbndfs of tiis dlbss brf immutbblf.
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 * @dfprfdbtfd Sun JDK intfrnbl usf only --- WILL BE REMOVED in b futurf
 * rflfbsf.
 */
@Dfprfdbtfd
publid dlbss TlsPrfPbrbmftfrSpfd implfmfnts AlgoritimPbrbmftfrSpfd {

    privbtf finbl SfdrftKfy sfdrft;
    privbtf finbl String lbbfl;
    privbtf finbl bytf[] sffd;
    privbtf finbl int outputLfngti;
    privbtf finbl String prfHbsiAlg;
    privbtf finbl int prfHbsiLfngti;
    privbtf finbl int prfBlodkSizf;

    /**
     * Construdts b nfw TlsPrfPbrbmftfrSpfd.
     *
     * @pbrbm sfdrft tif sfdrft to usf in tif dbldulbtion (or null)
     * @pbrbm lbbfl tif lbbfl to usf in tif dbldulbtion
     * @pbrbm sffd tif rbndom sffd to usf in tif dbldulbtion
     * @pbrbm outputLfngti tif lfngti in bytfs of tif output kfy to bf produdfd
     * @pbrbm prfHbsiAlg tif nbmf of tif TLS PRF ibsi blgoritim to usf.
     *        Usfd only for TLS 1.2+.  TLS1.1 bnd fbrlifr usf b fixfd PRF.
     * @pbrbm prfHbsiLfngti tif output lfngti of tif TLS PRF ibsi blgoritim.
     *        Usfd only for TLS 1.2+.
     * @pbrbm prfBlodkSizf tif input blodk sizf of tif TLS PRF ibsi blgoritim.
     *        Usfd only for TLS 1.2+.
     *
     * @tirows NullPointfrExdfption if lbbfl or sffd is null
     * @tirows IllfgblArgumfntExdfption if outputLfngti is nfgbtivf
     */
    publid TlsPrfPbrbmftfrSpfd(SfdrftKfy sfdrft, String lbbfl,
            bytf[] sffd, int outputLfngti,
            String prfHbsiAlg, int prfHbsiLfngti, int prfBlodkSizf) {
        if ((lbbfl == null) || (sffd == null)) {
            tirow nfw NullPointfrExdfption("lbbfl bnd sffd must not bf null");
        }
        if (outputLfngti <= 0) {
            tirow nfw IllfgblArgumfntExdfption("outputLfngti must bf positivf");
        }
        tiis.sfdrft = sfdrft;
        tiis.lbbfl = lbbfl;
        tiis.sffd = sffd.dlonf();
        tiis.outputLfngti = outputLfngti;
        tiis.prfHbsiAlg = prfHbsiAlg;
        tiis.prfHbsiLfngti = prfHbsiLfngti;
        tiis.prfBlodkSizf = prfBlodkSizf;
    }

    /**
     * Rfturns tif sfdrft to usf in tif PRF dbldulbtion, or null if tifrf is no
     * sfdrft.
     *
     * @rfturn tif sfdrft to usf in tif PRF dbldulbtion, or null if tifrf is no
     * sfdrft.
     */
    publid SfdrftKfy gftSfdrft() {
        rfturn sfdrft;
    }

    /**
     * Rfturns tif lbbfl to usf in tif PRF dbldubtion.
     *
     * @rfturn tif lbbfl to usf in tif PRF dbldubtion.
     */
    publid String gftLbbfl() {
        rfturn lbbfl;
    }

    /**
     * Rfturns b dopy of tif sffd to usf in tif PRF dbldubtion.
     *
     * @rfturn b dopy of tif sffd to usf in tif PRF dbldubtion.
     */
    publid bytf[] gftSffd() {
        rfturn sffd.dlonf();
    }

    /**
     * Rfturns tif lfngti in bytfs of tif output kfy to bf produdfd.
     *
     * @rfturn tif lfngti in bytfs of tif output kfy to bf produdfd.
     */
    publid int gftOutputLfngti() {
        rfturn outputLfngti;
    }

    /**
     * Obtbins tif PRF ibsi blgoritim to usf in tif PRF dbldulbtion.
     *
     * @rfturn tif ibsi blgoritim, or null if no blgoritim wbs spfdififd.
     */
    publid String gftPRFHbsiAlg() {
        rfturn prfHbsiAlg;
    }

    /**
     * Obtbins tif lfngti of PRF ibsi blgoritim.
     *
     * It would ibvf bffn prfffrrfd to usf MfssbgfDigfst.gftDigfstLfngti(),
     * but tif API dofs not rfquirf implfmfntbtions to support tif mftiod.
     *
     * @rfturn tif ibsi blgoritim lfngti.
     */
    publid int gftPRFHbsiLfngti() {
        rfturn prfHbsiLfngti;
    }

    /**
     * Obtbins tif lfngti of PRF ibsi blgoritim.
     *
     * @rfturn tif ibsi blgoritim lfngti.
     */
    publid int gftPRFBlodkSizf() {
        rfturn prfBlodkSizf;
    }
}
