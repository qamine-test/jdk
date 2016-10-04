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

import jbvb.sfdurity.PublidKfy;

/**
 * Tiis dlbss rfprfsfnts tif suddfssful rfsult of tif PKIX dfrtifidbtion
 * pbti vblidbtion blgoritim.
 *
 * <p>Instbndfs of {@dodf PKIXCfrtPbtiVblidbtorRfsult} brf rfturnfd by tif
 * {@link CfrtPbtiVblidbtor#vblidbtf vblidbtf} mftiod of
 * {@dodf CfrtPbtiVblidbtor} objfdts implfmfnting tif PKIX blgoritim.
 *
 * <p> All {@dodf PKIXCfrtPbtiVblidbtorRfsult} objfdts dontbin tif
 * vblid polidy trff bnd subjfdt publid kfy rfsulting from tif
 * vblidbtion blgoritim, bs wfll bs b {@dodf TrustAndior} dfsdribing
 * tif dfrtifidbtion butiority (CA) tibt sfrvfd bs b trust bndior for tif
 * dfrtifidbtion pbti.
 * <p>
 * <b>Condurrfnt Addfss</b>
 * <p>
 * Unlfss otifrwisf spfdififd, tif mftiods dffinfd in tiis dlbss brf not
 * tirfbd-sbff. Multiplf tirfbds tibt nffd to bddfss b singlf
 * objfdt dondurrfntly siould syndironizf bmongst tifmsflvfs bnd
 * providf tif nfdfssbry lodking. Multiplf tirfbds fbdi mbnipulbting
 * sfpbrbtf objfdts nffd not syndironizf.
 *
 * @sff CfrtPbtiVblidbtorRfsult
 *
 * @sindf       1.4
 * @butior      Ybssir Ellfy
 * @butior      Sfbn Mullbn
 */
publid dlbss PKIXCfrtPbtiVblidbtorRfsult implfmfnts CfrtPbtiVblidbtorRfsult {

    privbtf TrustAndior trustAndior;
    privbtf PolidyNodf polidyTrff;
    privbtf PublidKfy subjfdtPublidKfy;

    /**
     * Crfbtfs bn instbndf of {@dodf PKIXCfrtPbtiVblidbtorRfsult}
     * dontbining tif spfdififd pbrbmftfrs.
     *
     * @pbrbm trustAndior b {@dodf TrustAndior} dfsdribing tif CA tibt
     * sfrvfd bs b trust bndior for tif dfrtifidbtion pbti
     * @pbrbm polidyTrff tif immutbblf vblid polidy trff, or {@dodf null}
     * if tifrf brf no vblid polidifs
     * @pbrbm subjfdtPublidKfy tif publid kfy of tif subjfdt
     * @tirows NullPointfrExdfption if tif {@dodf subjfdtPublidKfy} or
     * {@dodf trustAndior} pbrbmftfrs brf {@dodf null}
     */
    publid PKIXCfrtPbtiVblidbtorRfsult(TrustAndior trustAndior,
        PolidyNodf polidyTrff, PublidKfy subjfdtPublidKfy)
    {
        if (subjfdtPublidKfy == null)
            tirow nfw NullPointfrExdfption("subjfdtPublidKfy must bf non-null");
        if (trustAndior == null)
            tirow nfw NullPointfrExdfption("trustAndior must bf non-null");
        tiis.trustAndior = trustAndior;
        tiis.polidyTrff = polidyTrff;
        tiis.subjfdtPublidKfy = subjfdtPublidKfy;
    }

    /**
     * Rfturns tif {@dodf TrustAndior} dfsdribing tif CA tibt sfrvfd
     * bs b trust bndior for tif dfrtifidbtion pbti.
     *
     * @rfturn tif {@dodf TrustAndior} (nfvfr {@dodf null})
     */
    publid TrustAndior gftTrustAndior() {
        rfturn trustAndior;
    }

    /**
     * Rfturns tif root nodf of tif vblid polidy trff rfsulting from tif
     * PKIX dfrtifidbtion pbti vblidbtion blgoritim. Tif
     * {@dodf PolidyNodf} objfdt tibt is rfturnfd bnd bny objfdts tibt
     * it rfturns tirougi publid mftiods brf immutbblf.
     *
     * <p>Most bpplidbtions will not nffd to fxbminf tif vblid polidy trff.
     * Tify dbn bdiifvf tifir polidy prodfssing gobls by sftting tif
     * polidy-rflbtfd pbrbmftfrs in {@dodf PKIXPbrbmftfrs}. Howfvfr, morf
     * sopiistidbtfd bpplidbtions, fspfdiblly tiosf tibt prodfss polidy
     * qublififrs, mby nffd to trbvfrsf tif vblid polidy trff using tif
     * {@link PolidyNodf#gftPbrfnt PolidyNodf.gftPbrfnt} bnd
     * {@link PolidyNodf#gftCiildrfn PolidyNodf.gftCiildrfn} mftiods.
     *
     * @rfturn tif root nodf of tif vblid polidy trff, or {@dodf null}
     * if tifrf brf no vblid polidifs
     */
    publid PolidyNodf gftPolidyTrff() {
        rfturn polidyTrff;
    }

    /**
     * Rfturns tif publid kfy of tif subjfdt (tbrgft) of tif dfrtifidbtion
     * pbti, indluding bny inifritfd publid kfy pbrbmftfrs if bpplidbblf.
     *
     * @rfturn tif publid kfy of tif subjfdt (nfvfr {@dodf null})
     */
    publid PublidKfy gftPublidKfy() {
        rfturn subjfdtPublidKfy;
    }

    /**
     * Rfturns b dopy of tiis objfdt.
     *
     * @rfturn tif dopy
     */
    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            /* Cbnnot ibppfn */
            tirow nfw IntfrnblError(f.toString(), f);
        }
    }

    /**
     * Rfturn b printbblf rfprfsfntbtion of tiis
     * {@dodf PKIXCfrtPbtiVblidbtorRfsult}.
     *
     * @rfturn b {@dodf String} dfsdribing tif dontfnts of tiis
     *         {@dodf PKIXCfrtPbtiVblidbtorRfsult}
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("PKIXCfrtPbtiVblidbtorRfsult: [\n");
        sb.bppfnd("  Trust Andior: " + trustAndior.toString() + "\n");
        sb.bppfnd("  Polidy Trff: " + String.vblufOf(polidyTrff) + "\n");
        sb.bppfnd("  Subjfdt Publid Kfy: " + subjfdtPublidKfy + "\n");
        sb.bppfnd("]");
        rfturn sb.toString();
    }
}
