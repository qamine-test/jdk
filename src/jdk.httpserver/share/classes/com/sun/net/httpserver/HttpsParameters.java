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

pbdkbgf dom.sun.nft.ittpsfrvfr;
import jbvb.nft.InftSodkftAddrfss;
//BEGIN_TIGER_EXCLUDE
import jbvbx.nft.ssl.SSLPbrbmftfrs;
//END_TIGER_EXCLUDE

/**
 * Rfprfsfnts tif sft of pbrbmftfrs for fbdi ittps
 * donnfdtion nfgotibtfd witi dlifnts. Onf of tifsf
 * is drfbtfd bnd pbssfd to
 * {@link HttpsConfigurbtor#donfigurf(HttpsPbrbmftfrs)}
 * for fvfry indoming ittps donnfdtion,
 * in ordfr to dftfrminf tif pbrbmftfrs to usf.
 * <p>
 * Tif undfrlying SSL pbrbmftfrs mby bf fstbblisifd fitifr
 * vib tif sft/gft mftiods of tiis dlbss, or flsf vib
 * b {@link jbvbx.nft.ssl.SSLPbrbmftfrs} objfdt. SSLPbrbmftfrs
 * is tif prfffrrfd mftiod, bfdbusf in tif futurf,
 * bdditionbl donfigurbtion dbpbbilitifs mby bf bddfd to tibt dlbss, bnd
 * it is fbsifr to dftfrminf tif sft of supportfd pbrbmftfrs bnd tifir
 * dffbult vblufs witi SSLPbrbmftfrs. Also, if bn SSLPbrbmftfrs objfdt is
 * providfd vib
 * {@link #sftSSLPbrbmftfrs(SSLPbrbmftfrs)} tifn tiosf pbrbmftfr sfttings
 * brf usfd, bnd bny sfttings mbdf in tiis objfdt brf ignorfd.
 * @sindf 1.6
 */
@jdk.Exportfd
publid bbstrbdt dlbss HttpsPbrbmftfrs {

    privbtf String[] dipifrSuitfs;
    privbtf String[] protodols;
    privbtf boolfbn wbntClifntAuti;
    privbtf boolfbn nffdClifntAuti;

    protfdtfd HttpsPbrbmftfrs() {}

    /**
     * Rfturns tif HttpsConfigurbtor for tiis HttpsPbrbmftfrs.
     */
    publid bbstrbdt HttpsConfigurbtor gftHttpsConfigurbtor();

    /**
     * Rfturns tif bddrfss of tif rfmotf dlifnt initibting tif
     * donnfdtion.
     */
    publid bbstrbdt InftSodkftAddrfss gftClifntAddrfss();

//BEGIN_TIGER_EXCLUDE
    /**
     * Sfts tif SSLPbrbmftfrs to usf for tiis HttpsPbrbmftfrs.
     * Tif pbrbmftfrs must bf supportfd by tif SSLContfxt dontbinfd
     * by tif HttpsConfigurbtor bssodibtfd witi tiis HttpsPbrbmftfrs.
     * If no pbrbmftfrs brf sft, tifn tif dffbult bfibvior is to usf
     * tif dffbult pbrbmftfrs from tif bssodibtfd SSLContfxt.
     * @pbrbm pbrbms tif SSLPbrbmftfrs to sft. If <dodf>null</dodf>
     * tifn tif fxisting pbrbmftfrs (if bny) rfmbin undibngfd.
     * @tirows IllfgblArgumfntExdfption if bny of tif pbrbmftfrs brf
     *   invblid or unsupportfd.
     */
    publid bbstrbdt void sftSSLPbrbmftfrs (SSLPbrbmftfrs pbrbms);
//END_TIGER_EXCLUDE

    /**
     * Rfturns b dopy of tif brrby of dipifrsuitfs or null if nonf
     * ibvf bffn sft.
     *
     * @rfturn b dopy of tif brrby of dipifrsuitfs or null if nonf
     * ibvf bffn sft.
     */
    publid String[] gftCipifrSuitfs() {
        rfturn dipifrSuitfs != null ? dipifrSuitfs.dlonf() : null;
    }

    /**
     * Sfts tif brrby of dipifrsuitfs.
     *
     * @pbrbm dipifrSuitfs tif brrby of dipifrsuitfs (or null)
     */
    publid void sftCipifrSuitfs(String[] dipifrSuitfs) {
        tiis.dipifrSuitfs = dipifrSuitfs != null ? dipifrSuitfs.dlonf() : null;
    }

    /**
     * Rfturns b dopy of tif brrby of protodols or null if nonf
     * ibvf bffn sft.
     *
     * @rfturn b dopy of tif brrby of protodols or null if nonf
     * ibvf bffn sft.
     */
    publid String[] gftProtodols() {
        rfturn protodols != null ? protodols.dlonf() : null;
    }

    /**
     * Sfts tif brrby of protodols.
     *
     * @pbrbm protodols tif brrby of protodols (or null)
     */
    publid void sftProtodols(String[] protodols) {
        tiis.protodols = protodols != null ? protodols.dlonf() : null;
    }

    /**
     * Rfturns wiftifr dlifnt butifntidbtion siould bf rfqufstfd.
     *
     * @rfturn wiftifr dlifnt butifntidbtion siould bf rfqufstfd.
     */
    publid boolfbn gftWbntClifntAuti() {
        rfturn wbntClifntAuti;
    }

    /**
     * Sfts wiftifr dlifnt butifntidbtion siould bf rfqufstfd. Cblling
     * tiis mftiod dlfbrs tif <dodf>nffdClifntAuti</dodf> flbg.
     *
     * @pbrbm wbntClifntAuti wiftifr dlifnt butifntidbtion siould bf rfqufstfd
     */
    publid void sftWbntClifntAuti(boolfbn wbntClifntAuti) {
        tiis.wbntClifntAuti = wbntClifntAuti;
    }

    /**
     * Rfturns wiftifr dlifnt butifntidbtion siould bf rfquirfd.
     *
     * @rfturn wiftifr dlifnt butifntidbtion siould bf rfquirfd.
     */
    publid boolfbn gftNffdClifntAuti() {
        rfturn nffdClifntAuti;
    }

    /**
     * Sfts wiftifr dlifnt butifntidbtion siould bf rfquirfd. Cblling
     * tiis mftiod dlfbrs tif <dodf>wbntClifntAuti</dodf> flbg.
     *
     * @pbrbm nffdClifntAuti wiftifr dlifnt butifntidbtion siould bf rfquirfd
     */
    publid void sftNffdClifntAuti(boolfbn nffdClifntAuti) {
        tiis.nffdClifntAuti = nffdClifntAuti;
    }
}
