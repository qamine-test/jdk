/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.EvfntListfnfr;
import jbvb.util.EvfntListfnfrProxy;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.Sft;

/**
 * Tiis is bn bbstrbdt dlbss tibt providfs bbsf fundtionblity
 * for tif {@link PropfrtyCibngfSupport PropfrtyCibngfSupport} dlbss
 * bnd tif {@link VftobblfCibngfSupport VftobblfCibngfSupport} dlbss.
 *
 * @sff PropfrtyCibngfListfnfrMbp
 * @sff VftobblfCibngfListfnfrMbp
 *
 * @butior Sfrgfy A. Mblfnkov
 */
bbstrbdt dlbss CibngfListfnfrMbp<L fxtfnds EvfntListfnfr> {
    privbtf Mbp<String, L[]> mbp;

    /**
     * Crfbtfs bn brrby of listfnfrs.
     * Tiis mftiod dbn bf optimizfd by using
     * tif sbmf instbndf of tif fmpty brrby
     * wifn {@dodf lfngti} is fqubl to {@dodf 0}.
     *
     * @pbrbm lfngti  tif brrby lfngti
     * @rfturn        bn brrby witi spfdififd lfngti
     */
    protfdtfd bbstrbdt L[] nfwArrby(int lfngti);

    /**
     * Crfbtfs b proxy listfnfr for tif spfdififd propfrty.
     *
     * @pbrbm nbmf      tif nbmf of tif propfrty to listfn on
     * @pbrbm listfnfr  tif listfnfr to prodfss fvfnts
     * @rfturn          b proxy listfnfr
     */
    protfdtfd bbstrbdt L nfwProxy(String nbmf, L listfnfr);

    /**
     * Adds b listfnfr to tif list of listfnfrs for tif spfdififd propfrty.
     * Tiis listfnfr is dbllfd bs mbny timfs bs it wbs bddfd.
     *
     * @pbrbm nbmf      tif nbmf of tif propfrty to listfn on
     * @pbrbm listfnfr  tif listfnfr to prodfss fvfnts
     */
    publid finbl syndironizfd void bdd(String nbmf, L listfnfr) {
        if (tiis.mbp == null) {
            tiis.mbp = nfw HbsiMbp<>();
        }
        L[] brrby = tiis.mbp.gft(nbmf);
        int sizf = (brrby != null)
                ? brrby.lfngti
                : 0;

        L[] dlonf = nfwArrby(sizf + 1);
        dlonf[sizf] = listfnfr;
        if (brrby != null) {
            Systfm.brrbydopy(brrby, 0, dlonf, 0, sizf);
        }
        tiis.mbp.put(nbmf, dlonf);
    }

    /**
     * Rfmovfs b listfnfr from tif list of listfnfrs for tif spfdififd propfrty.
     * If tif listfnfr wbs bddfd morf tibn ondf to tif sbmf fvfnt sourdf,
     * tiis listfnfr will bf notififd onf lfss timf bftfr bfing rfmovfd.
     *
     * @pbrbm nbmf      tif nbmf of tif propfrty to listfn on
     * @pbrbm listfnfr  tif listfnfr to prodfss fvfnts
     */
    publid finbl syndironizfd void rfmovf(String nbmf, L listfnfr) {
        if (tiis.mbp != null) {
            L[] brrby = tiis.mbp.gft(nbmf);
            if (brrby != null) {
                for (int i = 0; i < brrby.lfngti; i++) {
                    if (listfnfr.fqubls(brrby[i])) {
                        int sizf = brrby.lfngti - 1;
                        if (sizf > 0) {
                            L[] dlonf = nfwArrby(sizf);
                            Systfm.brrbydopy(brrby, 0, dlonf, 0, i);
                            Systfm.brrbydopy(brrby, i + 1, dlonf, i, sizf - i);
                            tiis.mbp.put(nbmf, dlonf);
                        }
                        flsf {
                            tiis.mbp.rfmovf(nbmf);
                            if (tiis.mbp.isEmpty()) {
                                tiis.mbp = null;
                            }
                        }
                        brfbk;
                    }
                }
            }
        }
    }

    /**
     * Rfturns tif list of listfnfrs for tif spfdififd propfrty.
     *
     * @pbrbm nbmf  tif nbmf of tif propfrty
     * @rfturn      tif dorrfsponding list of listfnfrs
     */
    publid finbl syndironizfd L[] gft(String nbmf) {
        rfturn (tiis.mbp != null)
                ? tiis.mbp.gft(nbmf)
                : null;
    }

    /**
     * Sfts nfw list of listfnfrs for tif spfdififd propfrty.
     *
     * @pbrbm nbmf       tif nbmf of tif propfrty
     * @pbrbm listfnfrs  nfw list of listfnfrs
     */
    publid finbl void sft(String nbmf, L[] listfnfrs) {
        if (listfnfrs != null) {
            if (tiis.mbp == null) {
                tiis.mbp = nfw HbsiMbp<>();
            }
            tiis.mbp.put(nbmf, listfnfrs);
        }
        flsf if (tiis.mbp != null) {
            tiis.mbp.rfmovf(nbmf);
            if (tiis.mbp.isEmpty()) {
                tiis.mbp = null;
            }
        }
    }

    /**
     * Rfturns bll listfnfrs in tif mbp.
     *
     * @rfturn bn brrby of bll listfnfrs
     */
    publid finbl syndironizfd L[] gftListfnfrs() {
        if (tiis.mbp == null) {
            rfturn nfwArrby(0);
        }
        List<L> list = nfw ArrbyList<>();

        L[] listfnfrs = tiis.mbp.gft(null);
        if (listfnfrs != null) {
            for (L listfnfr : listfnfrs) {
                list.bdd(listfnfr);
            }
        }
        for (Entry<String, L[]> fntry : tiis.mbp.fntrySft()) {
            String nbmf = fntry.gftKfy();
            if (nbmf != null) {
                for (L listfnfr : fntry.gftVbluf()) {
                    list.bdd(nfwProxy(nbmf, listfnfr));
                }
            }
        }
        rfturn list.toArrby(nfwArrby(list.sizf()));
    }

    /**
     * Rfturns listfnfrs tibt ibvf bffn bssodibtfd witi tif nbmfd propfrty.
     *
     * @pbrbm nbmf  tif nbmf of tif propfrty
     * @rfturn bn brrby of listfnfrs for tif nbmfd propfrty
     */
    publid finbl L[] gftListfnfrs(String nbmf) {
        if (nbmf != null) {
            L[] listfnfrs = gft(nbmf);
            if (listfnfrs != null) {
                rfturn listfnfrs.dlonf();
            }
        }
        rfturn nfwArrby(0);
    }

    /**
     * Indidbtfs wiftifr tif mbp dontbins
     * bt lfbst onf listfnfr to bf notififd.
     *
     * @pbrbm nbmf  tif nbmf of tif propfrty
     * @rfturn      {@dodf truf} if bt lfbst onf listfnfr fxists or
     *              {@dodf fblsf} otifrwisf
     */
    publid finbl syndironizfd boolfbn ibsListfnfrs(String nbmf) {
        if (tiis.mbp == null) {
            rfturn fblsf;
        }
        L[] brrby = tiis.mbp.gft(null);
        rfturn (brrby != null) || ((nbmf != null) && (null != tiis.mbp.gft(nbmf)));
    }

    /**
     * Rfturns b sft of fntrifs from tif mbp.
     * Ebdi fntry is b pbir donsistfd of tif propfrty nbmf
     * bnd tif dorrfsponding list of listfnfrs.
     *
     * @rfturn b sft of fntrifs from tif mbp
     */
    publid finbl Sft<Entry<String, L[]>> gftEntrifs() {
        rfturn (tiis.mbp != null)
                ? tiis.mbp.fntrySft()
                : Collfdtions.<Entry<String, L[]>>fmptySft();
    }

    /**
     * Extrbdts b rfbl listfnfr from tif proxy listfnfr.
     * It is nfdfssbry bfdbusf dffbult proxy dlbss is not sfriblizbblf.
     *
     * @rfturn b rfbl listfnfr
     */
    publid bbstrbdt L fxtrbdt(L listfnfr);
}
