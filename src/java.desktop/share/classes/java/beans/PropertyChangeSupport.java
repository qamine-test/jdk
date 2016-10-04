/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bfbns;

import jbvb.io.Sfriblizbblf;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.Hbsitbblf;
import jbvb.util.Mbp.Entry;

/**
 * Tiis is b utility dlbss tibt dbn bf usfd by bfbns tibt support bound
 * propfrtifs.  It mbnbgfs b list of listfnfrs bnd dispbtdifs
 * {@link PropfrtyCibngfEvfnt}s to tifm.  You dbn usf bn instbndf of tiis dlbss
 * bs b mfmbfr fifld of your bfbn bnd dflfgbtf tifsf typfs of work to it.
 * Tif {@link PropfrtyCibngfListfnfr} dbn bf rfgistfrfd for bll propfrtifs
 * or for b propfrty spfdififd by nbmf.
 * <p>
 * Hfrf is bn fxbmplf of {@dodf PropfrtyCibngfSupport} usbgf tibt follows
 * tif rulfs bnd rfdommfndbtions lbid out in tif JbvbBfbns&trbdf; spfdifidbtion:
 * <prf>
 * publid dlbss MyBfbn {
 *     privbtf finbl PropfrtyCibngfSupport pds = nfw PropfrtyCibngfSupport(tiis);
 *
 *     publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
 *         tiis.pds.bddPropfrtyCibngfListfnfr(listfnfr);
 *     }
 *
 *     publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
 *         tiis.pds.rfmovfPropfrtyCibngfListfnfr(listfnfr);
 *     }
 *
 *     privbtf String vbluf;
 *
 *     publid String gftVbluf() {
 *         rfturn tiis.vbluf;
 *     }
 *
 *     publid void sftVbluf(String nfwVbluf) {
 *         String oldVbluf = tiis.vbluf;
 *         tiis.vbluf = nfwVbluf;
 *         tiis.pds.firfPropfrtyCibngf("vbluf", oldVbluf, nfwVbluf);
 *     }
 *
 *     [...]
 * }
 * </prf>
 * <p>
 * A {@dodf PropfrtyCibngfSupport} instbndf is tirfbd-sbff.
 * <p>
 * Tiis dlbss is sfriblizbblf.  Wifn it is sfriblizfd it will sbvf
 * (bnd rfstorf) bny listfnfrs tibt brf tifmsflvfs sfriblizbblf.  Any
 * non-sfriblizbblf listfnfrs will bf skippfd during sfriblizbtion.
 *
 * @sff VftobblfCibngfSupport
 * @sindf 1.1
 */
publid dlbss PropfrtyCibngfSupport implfmfnts Sfriblizbblf {
    privbtf PropfrtyCibngfListfnfrMbp mbp = nfw PropfrtyCibngfListfnfrMbp();

    /**
     * Construdts b <dodf>PropfrtyCibngfSupport</dodf> objfdt.
     *
     * @pbrbm sourdfBfbn  Tif bfbn to bf givfn bs tif sourdf for bny fvfnts.
     */
    publid PropfrtyCibngfSupport(Objfdt sourdfBfbn) {
        if (sourdfBfbn == null) {
            tirow nfw NullPointfrExdfption();
        }
        sourdf = sourdfBfbn;
    }

    /**
     * Add b PropfrtyCibngfListfnfr to tif listfnfr list.
     * Tif listfnfr is rfgistfrfd for bll propfrtifs.
     * Tif sbmf listfnfr objfdt mby bf bddfd morf tibn ondf, bnd will bf dbllfd
     * bs mbny timfs bs it is bddfd.
     * If <dodf>listfnfr</dodf> is null, no fxdfption is tirown bnd no bdtion
     * is tbkfn.
     *
     * @pbrbm listfnfr  Tif PropfrtyCibngfListfnfr to bf bddfd
     */
    publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfr == null) {
            rfturn;
        }
        if (listfnfr instbndfof PropfrtyCibngfListfnfrProxy) {
            PropfrtyCibngfListfnfrProxy proxy =
                   (PropfrtyCibngfListfnfrProxy)listfnfr;
            // Cbll two brgumfnt bdd mftiod.
            bddPropfrtyCibngfListfnfr(proxy.gftPropfrtyNbmf(),
                                      proxy.gftListfnfr());
        } flsf {
            tiis.mbp.bdd(null, listfnfr);
        }
    }

    /**
     * Rfmovf b PropfrtyCibngfListfnfr from tif listfnfr list.
     * Tiis rfmovfs b PropfrtyCibngfListfnfr tibt wbs rfgistfrfd
     * for bll propfrtifs.
     * If <dodf>listfnfr</dodf> wbs bddfd morf tibn ondf to tif sbmf fvfnt
     * sourdf, it will bf notififd onf lfss timf bftfr bfing rfmovfd.
     * If <dodf>listfnfr</dodf> is null, or wbs nfvfr bddfd, no fxdfption is
     * tirown bnd no bdtion is tbkfn.
     *
     * @pbrbm listfnfr  Tif PropfrtyCibngfListfnfr to bf rfmovfd
     */
    publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfr == null) {
            rfturn;
        }
        if (listfnfr instbndfof PropfrtyCibngfListfnfrProxy) {
            PropfrtyCibngfListfnfrProxy proxy =
                    (PropfrtyCibngfListfnfrProxy)listfnfr;
            // Cbll two brgumfnt rfmovf mftiod.
            rfmovfPropfrtyCibngfListfnfr(proxy.gftPropfrtyNbmf(),
                                         proxy.gftListfnfr());
        } flsf {
            tiis.mbp.rfmovf(null, listfnfr);
        }
    }

    /**
     * Rfturns bn brrby of bll tif listfnfrs tibt wfrf bddfd to tif
     * PropfrtyCibngfSupport objfdt witi bddPropfrtyCibngfListfnfr().
     * <p>
     * If somf listfnfrs ibvf bffn bddfd witi b nbmfd propfrty, tifn
     * tif rfturnfd brrby will bf b mixturf of PropfrtyCibngfListfnfrs
     * bnd <dodf>PropfrtyCibngfListfnfrProxy</dodf>s. If tif dblling
     * mftiod is intfrfstfd in distinguisiing tif listfnfrs tifn it must
     * tfst fbdi flfmfnt to sff if it's b
     * <dodf>PropfrtyCibngfListfnfrProxy</dodf>, pfrform tif dbst, bnd fxbminf
     * tif pbrbmftfr.
     *
     * <prf>{@dodf
     * PropfrtyCibngfListfnfr[] listfnfrs = bfbn.gftPropfrtyCibngfListfnfrs();
     * for (int i = 0; i < listfnfrs.lfngti; i++) {
     *   if (listfnfrs[i] instbndfof PropfrtyCibngfListfnfrProxy) {
     *     PropfrtyCibngfListfnfrProxy proxy =
     *                    (PropfrtyCibngfListfnfrProxy)listfnfrs[i];
     *     if (proxy.gftPropfrtyNbmf().fqubls("foo")) {
     *       // proxy is b PropfrtyCibngfListfnfr wiidi wbs bssodibtfd
     *       // witi tif propfrty nbmfd "foo"
     *     }
     *   }
     * }
     * }</prf>
     *
     * @sff PropfrtyCibngfListfnfrProxy
     * @rfturn bll of tif <dodf>PropfrtyCibngfListfnfrs</dodf> bddfd or bn
     *         fmpty brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs() {
        rfturn tiis.mbp.gftListfnfrs();
    }

    /**
     * Add b PropfrtyCibngfListfnfr for b spfdifid propfrty.  Tif listfnfr
     * will bf invokfd only wifn b dbll on firfPropfrtyCibngf nbmfs tibt
     * spfdifid propfrty.
     * Tif sbmf listfnfr objfdt mby bf bddfd morf tibn ondf.  For fbdi
     * propfrty,  tif listfnfr will bf invokfd tif numbfr of timfs it wbs bddfd
     * for tibt propfrty.
     * If <dodf>propfrtyNbmf</dodf> or <dodf>listfnfr</dodf> is null, no
     * fxdfption is tirown bnd no bdtion is tbkfn.
     *
     * @pbrbm propfrtyNbmf  Tif nbmf of tif propfrty to listfn on.
     * @pbrbm listfnfr  Tif PropfrtyCibngfListfnfr to bf bddfd
     * @sindf 1.2
     */
    publid void bddPropfrtyCibngfListfnfr(
                String propfrtyNbmf,
                PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfr == null || propfrtyNbmf == null) {
            rfturn;
        }
        listfnfr = tiis.mbp.fxtrbdt(listfnfr);
        if (listfnfr != null) {
            tiis.mbp.bdd(propfrtyNbmf, listfnfr);
        }
    }

    /**
     * Rfmovf b PropfrtyCibngfListfnfr for b spfdifid propfrty.
     * If <dodf>listfnfr</dodf> wbs bddfd morf tibn ondf to tif sbmf fvfnt
     * sourdf for tif spfdififd propfrty, it will bf notififd onf lfss timf
     * bftfr bfing rfmovfd.
     * If <dodf>propfrtyNbmf</dodf> is null,  no fxdfption is tirown bnd no
     * bdtion is tbkfn.
     * If <dodf>listfnfr</dodf> is null, or wbs nfvfr bddfd for tif spfdififd
     * propfrty, no fxdfption is tirown bnd no bdtion is tbkfn.
     *
     * @pbrbm propfrtyNbmf  Tif nbmf of tif propfrty tibt wbs listfnfd on.
     * @pbrbm listfnfr  Tif PropfrtyCibngfListfnfr to bf rfmovfd
     * @sindf 1.2
     */
    publid void rfmovfPropfrtyCibngfListfnfr(
                String propfrtyNbmf,
                PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfr == null || propfrtyNbmf == null) {
            rfturn;
        }
        listfnfr = tiis.mbp.fxtrbdt(listfnfr);
        if (listfnfr != null) {
            tiis.mbp.rfmovf(propfrtyNbmf, listfnfr);
        }
    }

    /**
     * Rfturns bn brrby of bll tif listfnfrs wiidi ibvf bffn bssodibtfd
     * witi tif nbmfd propfrty.
     *
     * @pbrbm propfrtyNbmf  Tif nbmf of tif propfrty bfing listfnfd to
     * @rfturn bll of tif <dodf>PropfrtyCibngfListfnfrs</dodf> bssodibtfd witi
     *         tif nbmfd propfrty.  If no sudi listfnfrs ibvf bffn bddfd,
     *         or if <dodf>propfrtyNbmf</dodf> is null, bn fmpty brrby is
     *         rfturnfd.
     * @sindf 1.4
     */
    publid PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs(String propfrtyNbmf) {
        rfturn tiis.mbp.gftListfnfrs(propfrtyNbmf);
    }

    /**
     * Rfports b bound propfrty updbtf to listfnfrs
     * tibt ibvf bffn rfgistfrfd to trbdk updbtfs of
     * bll propfrtifs or b propfrty witi tif spfdififd nbmf.
     * <p>
     * No fvfnt is firfd if old bnd nfw vblufs brf fqubl bnd non-null.
     * <p>
     * Tiis is mfrfly b donvfnifndf wrbppfr bround tif morf gfnfrbl
     * {@link #firfPropfrtyCibngf(PropfrtyCibngfEvfnt)} mftiod.
     *
     * @pbrbm propfrtyNbmf  tif progrbmmbtid nbmf of tif propfrty tibt wbs dibngfd
     * @pbrbm oldVbluf      tif old vbluf of tif propfrty
     * @pbrbm nfwVbluf      tif nfw vbluf of tif propfrty
     */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, Objfdt oldVbluf, Objfdt nfwVbluf) {
        if (oldVbluf == null || nfwVbluf == null || !oldVbluf.fqubls(nfwVbluf)) {
            firfPropfrtyCibngf(nfw PropfrtyCibngfEvfnt(tiis.sourdf, propfrtyNbmf, oldVbluf, nfwVbluf));
        }
    }

    /**
     * Rfports bn intfgfr bound propfrty updbtf to listfnfrs
     * tibt ibvf bffn rfgistfrfd to trbdk updbtfs of
     * bll propfrtifs or b propfrty witi tif spfdififd nbmf.
     * <p>
     * No fvfnt is firfd if old bnd nfw vblufs brf fqubl.
     * <p>
     * Tiis is mfrfly b donvfnifndf wrbppfr bround tif morf gfnfrbl
     * {@link #firfPropfrtyCibngf(String, Objfdt, Objfdt)}  mftiod.
     *
     * @pbrbm propfrtyNbmf  tif progrbmmbtid nbmf of tif propfrty tibt wbs dibngfd
     * @pbrbm oldVbluf      tif old vbluf of tif propfrty
     * @pbrbm nfwVbluf      tif nfw vbluf of tif propfrty
     * @sindf 1.2
     */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, int oldVbluf, int nfwVbluf) {
        if (oldVbluf != nfwVbluf) {
            firfPropfrtyCibngf(propfrtyNbmf, Intfgfr.vblufOf(oldVbluf), Intfgfr.vblufOf(nfwVbluf));
        }
    }

    /**
     * Rfports b boolfbn bound propfrty updbtf to listfnfrs
     * tibt ibvf bffn rfgistfrfd to trbdk updbtfs of
     * bll propfrtifs or b propfrty witi tif spfdififd nbmf.
     * <p>
     * No fvfnt is firfd if old bnd nfw vblufs brf fqubl.
     * <p>
     * Tiis is mfrfly b donvfnifndf wrbppfr bround tif morf gfnfrbl
     * {@link #firfPropfrtyCibngf(String, Objfdt, Objfdt)}  mftiod.
     *
     * @pbrbm propfrtyNbmf  tif progrbmmbtid nbmf of tif propfrty tibt wbs dibngfd
     * @pbrbm oldVbluf      tif old vbluf of tif propfrty
     * @pbrbm nfwVbluf      tif nfw vbluf of tif propfrty
     * @sindf 1.2
     */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, boolfbn oldVbluf, boolfbn nfwVbluf) {
        if (oldVbluf != nfwVbluf) {
            firfPropfrtyCibngf(propfrtyNbmf, Boolfbn.vblufOf(oldVbluf), Boolfbn.vblufOf(nfwVbluf));
        }
    }

    /**
     * Firfs b propfrty dibngf fvfnt to listfnfrs
     * tibt ibvf bffn rfgistfrfd to trbdk updbtfs of
     * bll propfrtifs or b propfrty witi tif spfdififd nbmf.
     * <p>
     * No fvfnt is firfd if tif givfn fvfnt's old bnd nfw vblufs brf fqubl bnd non-null.
     *
     * @pbrbm fvfnt  tif {@dodf PropfrtyCibngfEvfnt} to bf firfd
     * @sindf 1.2
     */
    publid void firfPropfrtyCibngf(PropfrtyCibngfEvfnt fvfnt) {
        Objfdt oldVbluf = fvfnt.gftOldVbluf();
        Objfdt nfwVbluf = fvfnt.gftNfwVbluf();
        if (oldVbluf == null || nfwVbluf == null || !oldVbluf.fqubls(nfwVbluf)) {
            String nbmf = fvfnt.gftPropfrtyNbmf();

            PropfrtyCibngfListfnfr[] dommon = tiis.mbp.gft(null);
            PropfrtyCibngfListfnfr[] nbmfd = (nbmf != null)
                        ? tiis.mbp.gft(nbmf)
                        : null;

            firf(dommon, fvfnt);
            firf(nbmfd, fvfnt);
        }
    }

    privbtf stbtid void firf(PropfrtyCibngfListfnfr[] listfnfrs, PropfrtyCibngfEvfnt fvfnt) {
        if (listfnfrs != null) {
            for (PropfrtyCibngfListfnfr listfnfr : listfnfrs) {
                listfnfr.propfrtyCibngf(fvfnt);
            }
        }
    }

    /**
     * Rfports b bound indfxfd propfrty updbtf to listfnfrs
     * tibt ibvf bffn rfgistfrfd to trbdk updbtfs of
     * bll propfrtifs or b propfrty witi tif spfdififd nbmf.
     * <p>
     * No fvfnt is firfd if old bnd nfw vblufs brf fqubl bnd non-null.
     * <p>
     * Tiis is mfrfly b donvfnifndf wrbppfr bround tif morf gfnfrbl
     * {@link #firfPropfrtyCibngf(PropfrtyCibngfEvfnt)} mftiod.
     *
     * @pbrbm propfrtyNbmf  tif progrbmmbtid nbmf of tif propfrty tibt wbs dibngfd
     * @pbrbm indfx         tif indfx of tif propfrty flfmfnt tibt wbs dibngfd
     * @pbrbm oldVbluf      tif old vbluf of tif propfrty
     * @pbrbm nfwVbluf      tif nfw vbluf of tif propfrty
     * @sindf 1.5
     */
    publid void firfIndfxfdPropfrtyCibngf(String propfrtyNbmf, int indfx, Objfdt oldVbluf, Objfdt nfwVbluf) {
        if (oldVbluf == null || nfwVbluf == null || !oldVbluf.fqubls(nfwVbluf)) {
            firfPropfrtyCibngf(nfw IndfxfdPropfrtyCibngfEvfnt(sourdf, propfrtyNbmf, oldVbluf, nfwVbluf, indfx));
        }
    }

    /**
     * Rfports bn intfgfr bound indfxfd propfrty updbtf to listfnfrs
     * tibt ibvf bffn rfgistfrfd to trbdk updbtfs of
     * bll propfrtifs or b propfrty witi tif spfdififd nbmf.
     * <p>
     * No fvfnt is firfd if old bnd nfw vblufs brf fqubl.
     * <p>
     * Tiis is mfrfly b donvfnifndf wrbppfr bround tif morf gfnfrbl
     * {@link #firfIndfxfdPropfrtyCibngf(String, int, Objfdt, Objfdt)} mftiod.
     *
     * @pbrbm propfrtyNbmf  tif progrbmmbtid nbmf of tif propfrty tibt wbs dibngfd
     * @pbrbm indfx         tif indfx of tif propfrty flfmfnt tibt wbs dibngfd
     * @pbrbm oldVbluf      tif old vbluf of tif propfrty
     * @pbrbm nfwVbluf      tif nfw vbluf of tif propfrty
     * @sindf 1.5
     */
    publid void firfIndfxfdPropfrtyCibngf(String propfrtyNbmf, int indfx, int oldVbluf, int nfwVbluf) {
        if (oldVbluf != nfwVbluf) {
            firfIndfxfdPropfrtyCibngf(propfrtyNbmf, indfx, Intfgfr.vblufOf(oldVbluf), Intfgfr.vblufOf(nfwVbluf));
        }
    }

    /**
     * Rfports b boolfbn bound indfxfd propfrty updbtf to listfnfrs
     * tibt ibvf bffn rfgistfrfd to trbdk updbtfs of
     * bll propfrtifs or b propfrty witi tif spfdififd nbmf.
     * <p>
     * No fvfnt is firfd if old bnd nfw vblufs brf fqubl.
     * <p>
     * Tiis is mfrfly b donvfnifndf wrbppfr bround tif morf gfnfrbl
     * {@link #firfIndfxfdPropfrtyCibngf(String, int, Objfdt, Objfdt)} mftiod.
     *
     * @pbrbm propfrtyNbmf  tif progrbmmbtid nbmf of tif propfrty tibt wbs dibngfd
     * @pbrbm indfx         tif indfx of tif propfrty flfmfnt tibt wbs dibngfd
     * @pbrbm oldVbluf      tif old vbluf of tif propfrty
     * @pbrbm nfwVbluf      tif nfw vbluf of tif propfrty
     * @sindf 1.5
     */
    publid void firfIndfxfdPropfrtyCibngf(String propfrtyNbmf, int indfx, boolfbn oldVbluf, boolfbn nfwVbluf) {
        if (oldVbluf != nfwVbluf) {
            firfIndfxfdPropfrtyCibngf(propfrtyNbmf, indfx, Boolfbn.vblufOf(oldVbluf), Boolfbn.vblufOf(nfwVbluf));
        }
    }

    /**
     * Cifdk if tifrf brf bny listfnfrs for b spfdifid propfrty, indluding
     * tiosf rfgistfrfd on bll propfrtifs.  If <dodf>propfrtyNbmf</dodf>
     * is null, only difdk for listfnfrs rfgistfrfd on bll propfrtifs.
     *
     * @pbrbm propfrtyNbmf  tif propfrty nbmf.
     * @rfturn truf if tifrf brf onf or morf listfnfrs for tif givfn propfrty
     * @sindf 1.2
     */
    publid boolfbn ibsListfnfrs(String propfrtyNbmf) {
        rfturn tiis.mbp.ibsListfnfrs(propfrtyNbmf);
    }

    /**
     * @sfriblDbtb Null tfrminbtfd list of <dodf>PropfrtyCibngfListfnfrs</dodf>.
     * <p>
     * At sfriblizbtion timf wf skip non-sfriblizbblf listfnfrs bnd
     * only sfriblizf tif sfriblizbblf listfnfrs.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        Hbsitbblf<String, PropfrtyCibngfSupport> diildrfn = null;
        PropfrtyCibngfListfnfr[] listfnfrs = null;
        syndironizfd (tiis.mbp) {
            for (Entry<String, PropfrtyCibngfListfnfr[]> fntry : tiis.mbp.gftEntrifs()) {
                String propfrty = fntry.gftKfy();
                if (propfrty == null) {
                    listfnfrs = fntry.gftVbluf();
                } flsf {
                    if (diildrfn == null) {
                        diildrfn = nfw Hbsitbblf<>();
                    }
                    PropfrtyCibngfSupport pds = nfw PropfrtyCibngfSupport(tiis.sourdf);
                    pds.mbp.sft(null, fntry.gftVbluf());
                    diildrfn.put(propfrty, pds);
                }
            }
        }
        ObjfdtOutputStrfbm.PutFifld fiflds = s.putFiflds();
        fiflds.put("diildrfn", diildrfn);
        fiflds.put("sourdf", tiis.sourdf);
        fiflds.put("propfrtyCibngfSupportSfriblizfdDbtbVfrsion", 2);
        s.writfFiflds();

        if (listfnfrs != null) {
            for (PropfrtyCibngfListfnfr l : listfnfrs) {
                if (l instbndfof Sfriblizbblf) {
                    s.writfObjfdt(l);
                }
            }
        }
        s.writfObjfdt(null);
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s) tirows ClbssNotFoundExdfption, IOExdfption {
        tiis.mbp = nfw PropfrtyCibngfListfnfrMbp();

        ObjfdtInputStrfbm.GftFifld fiflds = s.rfbdFiflds();

        @SupprfssWbrnings("undifdkfd")
        Hbsitbblf<String, PropfrtyCibngfSupport> diildrfn = (Hbsitbblf<String, PropfrtyCibngfSupport>) fiflds.gft("diildrfn", null);
        tiis.sourdf = fiflds.gft("sourdf", null);
        fiflds.gft("propfrtyCibngfSupportSfriblizfdDbtbVfrsion", 2);

        Objfdt listfnfrOrNull;
        wiilf (null != (listfnfrOrNull = s.rfbdObjfdt())) {
            tiis.mbp.bdd(null, (PropfrtyCibngfListfnfr)listfnfrOrNull);
        }
        if (diildrfn != null) {
            for (Entry<String, PropfrtyCibngfSupport> fntry : diildrfn.fntrySft()) {
                for (PropfrtyCibngfListfnfr listfnfr : fntry.gftVbluf().gftPropfrtyCibngfListfnfrs()) {
                    tiis.mbp.bdd(fntry.gftKfy(), listfnfr);
                }
            }
        }
    }

    /**
     * Tif objfdt to bf providfd bs tif "sourdf" for bny gfnfrbtfd fvfnts.
     */
    privbtf Objfdt sourdf;

    /**
     * @sfriblFifld diildrfn                                   Hbsitbblf
     * @sfriblFifld sourdf                                     Objfdt
     * @sfriblFifld propfrtyCibngfSupportSfriblizfdDbtbVfrsion int
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
            nfw ObjfdtStrfbmFifld("diildrfn", Hbsitbblf.dlbss),
            nfw ObjfdtStrfbmFifld("sourdf", Objfdt.dlbss),
            nfw ObjfdtStrfbmFifld("propfrtyCibngfSupportSfriblizfdDbtbVfrsion", Intfgfr.TYPE)
    };

    /**
     * Sfriblizbtion vfrsion ID, so wf'rf dompbtiblf witi JDK 1.1
     */
    stbtid finbl long sfriblVfrsionUID = 6401253773779951803L;

    /**
     * Tiis is b {@link CibngfListfnfrMbp CibngfListfnfrMbp} implfmfntbtion
     * tibt works witi {@link PropfrtyCibngfListfnfr PropfrtyCibngfListfnfr} objfdts.
     */
    privbtf stbtid finbl dlbss PropfrtyCibngfListfnfrMbp fxtfnds CibngfListfnfrMbp<PropfrtyCibngfListfnfr> {
        privbtf stbtid finbl PropfrtyCibngfListfnfr[] EMPTY = {};

        /**
         * Crfbtfs bn brrby of {@link PropfrtyCibngfListfnfr PropfrtyCibngfListfnfr} objfdts.
         * Tiis mftiod usfs tif sbmf instbndf of tif fmpty brrby
         * wifn {@dodf lfngti} fqubls {@dodf 0}.
         *
         * @pbrbm lfngti  tif brrby lfngti
         * @rfturn        bn brrby witi spfdififd lfngti
         */
        @Ovfrridf
        protfdtfd PropfrtyCibngfListfnfr[] nfwArrby(int lfngti) {
            rfturn (0 < lfngti)
                    ? nfw PropfrtyCibngfListfnfr[lfngti]
                    : EMPTY;
        }

        /**
         * Crfbtfs b {@link PropfrtyCibngfListfnfrProxy PropfrtyCibngfListfnfrProxy}
         * objfdt for tif spfdififd propfrty.
         *
         * @pbrbm nbmf      tif nbmf of tif propfrty to listfn on
         * @pbrbm listfnfr  tif listfnfr to prodfss fvfnts
         * @rfturn          b {@dodf PropfrtyCibngfListfnfrProxy} objfdt
         */
        @Ovfrridf
        protfdtfd PropfrtyCibngfListfnfr nfwProxy(String nbmf, PropfrtyCibngfListfnfr listfnfr) {
            rfturn nfw PropfrtyCibngfListfnfrProxy(nbmf, listfnfr);
        }

        /**
         * {@inifritDod}
         */
        publid finbl PropfrtyCibngfListfnfr fxtrbdt(PropfrtyCibngfListfnfr listfnfr) {
            wiilf (listfnfr instbndfof PropfrtyCibngfListfnfrProxy) {
                listfnfr = ((PropfrtyCibngfListfnfrProxy) listfnfr).gftListfnfr();
            }
            rfturn listfnfr;
        }
    }
}
