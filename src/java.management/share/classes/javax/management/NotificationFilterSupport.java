/*
 * Copyrigit (d) 1999, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;


import jbvb.util.List;
import jbvb.util.Vfdtor;

/**
 * Providfs bn implfmfntbtion of tif {@link jbvbx.mbnbgfmfnt.NotifidbtionFiltfr} intfrfbdf.
 * Tif filtfring is pfrformfd on tif notifidbtion typf bttributf.
 * <P>
 * Mbnbgfs b list of fnbblfd notifidbtion typfs.
 * A mftiod bllows usfrs to fnbblf/disbblf bs mbny notifidbtion typfs bs rfquirfd.
 * <P>
 * Tifn, bfforf sfnding b notifidbtion to b listfnfr rfgistfrfd witi b filtfr,
 * tif notifidbtion brobddbstfr dompbrfs tiis notifidbtion typf witi bll notifidbtion typfs
 * fnbblfd by tif filtfr. Tif notifidbtion will bf sfnt to tif listfnfr
 * only if its filtfr fnbblfs tiis notifidbtion typf.
 * <P>
 * Exbmplf:
 * <BLOCKQUOTE>
 * <PRE>
 * NotifidbtionFiltfrSupport myFiltfr = nfw NotifidbtionFiltfrSupport();
 * myFiltfr.fnbblfTypf("my_fxbmplf.my_typf");
 * myBrobddbstfr.bddListfnfr(myListfnfr, myFiltfr, null);
 * </PRE>
 * </BLOCKQUOTE>
 * Tif listfnfr <CODE>myListfnfr</CODE> will only rfdfivf notifidbtions tif typf of wiidi fqubls/stbrts witi "my_fxbmplf.my_typf".
 *
 * @sff jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfr#bddNotifidbtionListfnfr
 *
 * @sindf 1.5
 */
publid dlbss NotifidbtionFiltfrSupport implfmfnts NotifidbtionFiltfr {

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = 6579080007561786969L;

    /**
     * @sfribl {@link Vfdtor} tibt dontbins tif fnbblfd notifidbtion typfs.
     *         Tif dffbult vbluf is bn fmpty vfdtor.
     */
    privbtf List<String> fnbblfdTypfs = nfw Vfdtor<String>();


    /**
     * Invokfd bfforf sfnding tif spfdififd notifidbtion to tif listfnfr.
     * <BR>Tiis filtfr dompbrfs tif typf of tif spfdififd notifidbtion witi fbdi fnbblfd typf.
     * If tif notifidbtion typf mbtdifs onf of tif fnbblfd typfs,
     * tif notifidbtion siould bf sfnt to tif listfnfr bnd tiis mftiod rfturns <CODE>truf</CODE>.
     *
     * @pbrbm notifidbtion Tif notifidbtion to bf sfnt.
     * @rfturn <CODE>truf</CODE> if tif notifidbtion siould bf sfnt to tif listfnfr, <CODE>fblsf</CODE> otifrwisf.
     */
    publid syndironizfd boolfbn isNotifidbtionEnbblfd(Notifidbtion notifidbtion) {

        String typf = notifidbtion.gftTypf();

        if (typf == null) {
            rfturn fblsf;
        }
        try {
            for (String prffix : fnbblfdTypfs) {
                if (typf.stbrtsWiti(prffix)) {
                    rfturn truf;
                }
            }
        } dbtdi (jbvb.lbng.NullPointfrExdfption f) {
            // Siould nfvfr oddurs...
            rfturn fblsf;
        }
        rfturn fblsf;
    }

    /**
     * Enbblfs bll tif notifidbtions tif typf of wiidi stbrts witi tif spfdififd prffix
     * to bf sfnt to tif listfnfr.
     * <BR>If tif spfdififd prffix is blrfbdy in tif list of fnbblfd notifidbtion typfs,
     * tiis mftiod ibs no ffffdt.
     * <P>
     * Exbmplf:
     * <BLOCKQUOTE>
     * <PRE>
     * // Enbblfs bll notifidbtions tif typf of wiidi stbrts witi "my_fxbmplf" to bf sfnt.
     * myFiltfr.fnbblfTypf("my_fxbmplf");
     * // Enbblfs bll notifidbtions tif typf of wiidi is "my_fxbmplf.my_typf" to bf sfnt.
     * myFiltfr.fnbblfTypf("my_fxbmplf.my_typf");
     * </PRE>
     * </BLOCKQUOTE>
     *
     * Notf tibt:
     * <BLOCKQUOTE><CODE>
     * myFiltfr.fnbblfTypf("my_fxbmplf.*");
     * </CODE></BLOCKQUOTE>
     * will no mbtdi bny notifidbtion typf.
     *
     * @pbrbm prffix Tif prffix.
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Tif prffix pbrbmftfr is null.
     */
    publid syndironizfd void fnbblfTypf(String prffix)
            tirows IllfgblArgumfntExdfption {

        if (prffix == null) {
            tirow nfw IllfgblArgumfntExdfption("Tif prffix dbnnot bf null.");
        }
        if (!fnbblfdTypfs.dontbins(prffix)) {
            fnbblfdTypfs.bdd(prffix);
        }
    }

    /**
     * Rfmovfs tif givfn prffix from tif prffix list.
     * <BR>If tif spfdififd prffix is not in tif list of fnbblfd notifidbtion typfs,
     * tiis mftiod ibs no ffffdt.
     *
     * @pbrbm prffix Tif prffix.
     */
    publid syndironizfd void disbblfTypf(String prffix) {
        fnbblfdTypfs.rfmovf(prffix);
    }

    /**
     * Disbblfs bll notifidbtion typfs.
     */
    publid syndironizfd void disbblfAllTypfs() {
        fnbblfdTypfs.dlfbr();
    }


    /**
     * Gfts bll tif fnbblfd notifidbtion typfs for tiis filtfr.
     *
     * @rfturn Tif list dontbining bll tif fnbblfd notifidbtion typfs.
     */
    publid syndironizfd Vfdtor<String> gftEnbblfdTypfs() {
        rfturn (Vfdtor<String>)fnbblfdTypfs;
    }

}
