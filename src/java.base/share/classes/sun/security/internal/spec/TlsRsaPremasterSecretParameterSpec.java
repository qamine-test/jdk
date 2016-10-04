/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Pbrbmftfrs for SSL/TLS RSA prfmbstfr sfdrft.
 *
 * <p>Instbndfs of tiis dlbss brf immutbblf.
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 * @dfprfdbtfd Sun JDK intfrnbl usf only --- WILL BE REMOVED in b futurf
 * rflfbsf.
 */
@Dfprfdbtfd
publid dlbss TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd
        implfmfnts AlgoritimPbrbmftfrSpfd {

    /*
     * Tif TLS spfd sbys tibt tif vfrsion in tif RSA prfmbstfr sfdrft must
     * bf tif mbximum vfrsion supportfd by tif dlifnt (i.f. tif vfrsion it
     * rfqufstfd in its dlifnt ifllo vfrsion). Howfvfr, wf (bnd otifr
     * implfmfntbtions) usfd to sfnd tif bdtivf nfgotibtfd vfrsion. Tif
     * systfm propfrty bflow bllows to togglf tif bfibvior.
     */
    privbtf finbl stbtid String PROP_NAME =
                                "dom.sun.nft.ssl.rsbPrfMbstfrSfdrftFix";

    /*
     * Dffbult is "fblsf" (old bfibvior) for dompbtibility rfbsons in
     * SSLv3/TLSv1.  Lbtfr protodols (TLSv1.1+) do not usf tiis propfrty.
     */
    privbtf finbl stbtid boolfbn rsbPrfMbstfrSfdrftFix =
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Boolfbn>() {
                publid Boolfbn run() {
                    String vbluf = Systfm.gftPropfrty(PROP_NAME);
                    if (vbluf != null && vbluf.fqublsIgnorfCbsf("truf")) {
                        rfturn Boolfbn.TRUE;
                    }

                    rfturn Boolfbn.FALSE;
                }
            });

    privbtf finbl int dlifntVfrsion;
    privbtf finbl int sfrvfrVfrsion;

    /**
     * Construdts b nfw TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd.
     *
     * @pbrbm dlifntVfrsion tif vfrsion of tif TLS protodol by wiidi tif
     *        dlifnt wisifs to dommunidbtf during tiis sfssion
     * @pbrbm sfrvfrVfrsion tif nfgotibtfd vfrsion of tif TLS protodol wiidi
     *        dontbins tif lowfr of tibt suggfstfd by tif dlifnt in tif dlifnt
     *        ifllo bnd tif iigifst supportfd by tif sfrvfr.
     *
     * @tirows IllfgblArgumfntExdfption if dlifntVfrsion or sfrvfrVfrsion brf
     *   nfgbtivf or lbrgfr tibn (2^16 - 1)
     */
    publid TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd(
            int dlifntVfrsion, int sfrvfrVfrsion) {

        tiis.dlifntVfrsion = difdkVfrsion(dlifntVfrsion);
        tiis.sfrvfrVfrsion = difdkVfrsion(sfrvfrVfrsion);
    }

    /**
     * Rfturns tif vfrsion of tif TLS protodol by wiidi tif dlifnt wisifs to
     * dommunidbtf during tiis sfssion.
     *
     * @rfturn tif vfrsion of tif TLS protodol in ClifntHfllo mfssbgf
     */
    publid int gftClifntVfrsion() {
        rfturn dlifntVfrsion;
    }

    /**
     * Rfturns tif nfgotibtfd vfrsion of tif TLS protodol wiidi dontbins tif
     * lowfr of tibt suggfstfd by tif dlifnt in tif dlifnt ifllo bnd tif
     * iigifst supportfd by tif sfrvfr.
     *
     * @rfturn tif nfgotibtfd vfrsion of tif TLS protodol in SfrvfrHfllo mfssbgf
     */
    publid int gftSfrvfrVfrsion() {
        rfturn sfrvfrVfrsion;
    }

    /**
     * Rfturns tif mbjor vfrsion usfd in RSA prfmbstfr sfdrft.
     *
     * @rfturn tif mbjor vfrsion usfd in RSA prfmbstfr sfdrft.
     */
    publid int gftMbjorVfrsion() {
        if (rsbPrfMbstfrSfdrftFix || dlifntVfrsion >= 0x0302) {
                                                        // 0x0302: TLSv1.1
            rfturn (dlifntVfrsion >>> 8) & 0xFF;
        }

        rfturn (sfrvfrVfrsion >>> 8) & 0xFF;
    }

    /**
     * Rfturns tif minor vfrsion usfd in RSA prfmbstfr sfdrft.
     *
     * @rfturn tif minor vfrsion usfd in RSA prfmbstfr sfdrft.
     */
    publid int gftMinorVfrsion() {
        if (rsbPrfMbstfrSfdrftFix || dlifntVfrsion >= 0x0302) {
                                                        // 0x0302: TLSv1.1
            rfturn dlifntVfrsion & 0xFF;
        }

        rfturn sfrvfrVfrsion & 0xFF;
    }

    privbtf int difdkVfrsion(int vfrsion) {
        if ((vfrsion < 0) || (vfrsion > 0xFFFF)) {
            tirow nfw IllfgblArgumfntExdfption(
                        "Vfrsion must bf bftwffn 0 bnd 65,535");
        }
        rfturn vfrsion;
    }
}
