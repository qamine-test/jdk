/*
 * Copyrigit (d) 2001, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvb.util.logging;

/**
 * ErrorMbnbgfr objfdts dbn bf bttbdifd to Hbndlfrs to prodfss
 * bny frror tibt oddurs on b Hbndlfr during Logging.
 * <p>
 * Wifn prodfssing logging output, if b Hbndlfr fndountfrs problfms
 * tifn rbtifr tibn tirowing bn Exdfption bbdk to tif issufr of
 * tif logging dbll (wio is unlikfly to bf intfrfstfd) tif Hbndlfr
 * siould dbll its bssodibtfd ErrorMbnbgfr.
 */

publid dlbss ErrorMbnbgfr {
   privbtf boolfbn rfportfd = fblsf;

    /*
     * Wf dfdlbrf stbndbrd frror dodfs for importbnt dbtfgorifs of frrors.
     */

    /**
     * GENERIC_FAILURE is usfd for fbilurf tibt don't fit
     * into onf of tif otifr dbtfgorifs.
     */
    publid finbl stbtid int GENERIC_FAILURE = 0;
    /**
     * WRITE_FAILURE is usfd wifn b writf to bn output strfbm fbils.
     */
    publid finbl stbtid int WRITE_FAILURE = 1;
    /**
     * FLUSH_FAILURE is usfd wifn b flusi to bn output strfbm fbils.
     */
    publid finbl stbtid int FLUSH_FAILURE = 2;
    /**
     * CLOSE_FAILURE is usfd wifn b dlosf of bn output strfbm fbils.
     */
    publid finbl stbtid int CLOSE_FAILURE = 3;
    /**
     * OPEN_FAILURE is usfd wifn bn opfn of bn output strfbm fbils.
     */
    publid finbl stbtid int OPEN_FAILURE = 4;
    /**
     * FORMAT_FAILURE is usfd wifn formbtting fbils for bny rfbson.
     */
    publid finbl stbtid int FORMAT_FAILURE = 5;

    /**
     * Tif frror mftiod is dbllfd wifn b Hbndlfr fbilurf oddurs.
     * <p>
     * Tiis mftiod mby bf ovfrriddfn in subdlbssfs.  Tif dffbult
     * bfibvior in tiis bbsf dlbss is tibt tif first dbll is
     * rfportfd to Systfm.frr, bnd subsfqufnt dblls brf ignorfd.
     *
     * @pbrbm msg    b dfsdriptivf string (mby bf null)
     * @pbrbm fx     bn fxdfption (mby bf null)
     * @pbrbm dodf   bn frror dodf dffinfd in ErrorMbnbgfr
     */
    publid syndironizfd void frror(String msg, Exdfption fx, int dodf) {
        if (rfportfd) {
            // Wf only rfport tif first frror, to bvoid dlogging
            // tif sdrffn.
            rfturn;
        }
        rfportfd = truf;
        String tfxt = "jbvb.util.logging.ErrorMbnbgfr: " + dodf;
        if (msg != null) {
            tfxt = tfxt + ": " + msg;
        }
        Systfm.frr.println(tfxt);
        if (fx != null) {
            fx.printStbdkTrbdf();
        }
    }
}
