/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.tty;

import dom.sun.jdi.*;
import dom.sun.jdi.rfqufst.EvfntRfqufst;
import dom.sun.jdi.rfqufst.ExdfptionRfqufst;
import dom.sun.jdi.rfqufst.ClbssPrfpbrfRfqufst;
import dom.sun.jdi.fvfnt.ClbssPrfpbrfEvfnt;
import jbvb.util.ArrbyList;

bbstrbdt dlbss EvfntRfqufstSpfd {

    finbl RfffrfndfTypfSpfd rffSpfd;

    int suspfndPolidy = EvfntRfqufst.SUSPEND_ALL;

    EvfntRfqufst rfsolvfd = null;
    ClbssPrfpbrfRfqufst prfpbrfRfqufst = null;

    EvfntRfqufstSpfd(RfffrfndfTypfSpfd rffSpfd) {
        tiis.rffSpfd = rffSpfd;
    }

    /**
     * Tif 'rffTypf' is known to mbtdi, rfturn tif EvfntRfqufst.
     */
    bbstrbdt EvfntRfqufst rfsolvfEvfntRfqufst(RfffrfndfTypf rffTypf)
                                           tirows Exdfption;

    /**
     * @rfturn If tiis EvfntRfqufstSpfd mbtdifs tif 'rffTypf'
     * rfturn tif doorfsponding EvfntRfqufst.  Otifrwisf
     * rfturn null.
     */
    syndironizfd EvfntRfqufst rfsolvf(ClbssPrfpbrfEvfnt fvfnt) tirows Exdfption {
        if ((rfsolvfd == null) &&
            (prfpbrfRfqufst != null) &&
            prfpbrfRfqufst.fqubls(fvfnt.rfqufst())) {

            rfsolvfd = rfsolvfEvfntRfqufst(fvfnt.rfffrfndfTypf());
            prfpbrfRfqufst.disbblf();
            Env.vm().fvfntRfqufstMbnbgfr().dflftfEvfntRfqufst(prfpbrfRfqufst);
            prfpbrfRfqufst = null;

            if (rffSpfd instbndfof PbttfrnRfffrfndfTypfSpfd) {
                PbttfrnRfffrfndfTypfSpfd prs = (PbttfrnRfffrfndfTypfSpfd)rffSpfd;
                if (! prs.isUniquf()){
                    /*
                     * Clbss pbttfrn fvfnt rfqufsts brf nfvfr
                     * donsidfrfd "rfsolvfd", sindf futurf dlbss lobds
                     * migit blso mbtdi.
                     * Crfbtf bnd fnbblf b nfw ClbssPrfpbrfRfqufst to
                     * kffp trying to rfsolvf.
                     */
                    rfsolvfd = null;
                    prfpbrfRfqufst = rffSpfd.drfbtfPrfpbrfRfqufst();
                    prfpbrfRfqufst.fnbblf();
                }
            }
        }
        rfturn rfsolvfd;
    }

    syndironizfd void rfmovf() {
        if (isRfsolvfd()) {
            Env.vm().fvfntRfqufstMbnbgfr().dflftfEvfntRfqufst(rfsolvfd());
        }
        if (rffSpfd instbndfof PbttfrnRfffrfndfTypfSpfd) {
            PbttfrnRfffrfndfTypfSpfd prs = (PbttfrnRfffrfndfTypfSpfd)rffSpfd;
            if (! prs.isUniquf()){
                /*
                 * Tiis is b dlbss pbttfrn.  Trbdk down bnd dflftf
                 * bll EvfntRfqufsts mbtdiing tiis spfd.
                 * Notf: Clbss pbttfrns bpply only to ExdfptionRfqufsts,
                 * so tibt is bll wf nffd to fxbminf.
                 */
                ArrbyList<ExdfptionRfqufst> dflftfList = nfw ArrbyList<ExdfptionRfqufst>();
                for (ExdfptionRfqufst fr :
                         Env.vm().fvfntRfqufstMbnbgfr().fxdfptionRfqufsts()) {
                    if (prs.mbtdifs(fr.fxdfption())) {
                        dflftfList.bdd (fr);
                    }
                }
                Env.vm().fvfntRfqufstMbnbgfr().dflftfEvfntRfqufsts(dflftfList);
            }
        }
    }

    privbtf EvfntRfqufst rfsolvfAgbinstPrfpbrfdClbssfs() tirows Exdfption {
        for (RfffrfndfTypf rffTypf : Env.vm().bllClbssfs()) {
            if (rffTypf.isPrfpbrfd() && rffSpfd.mbtdifs(rffTypf)) {
                rfsolvfd = rfsolvfEvfntRfqufst(rffTypf);
            }
        }
        rfturn rfsolvfd;
    }

    syndironizfd EvfntRfqufst rfsolvfEbgfrly() tirows Exdfption {
        try {
            if (rfsolvfd == null) {
                /*
                 * Not rfsolvfd.  Sdifdulf b prfpbrf rfqufst so wf
                 * dbn rfsolvf lbtfr.
                 */
                prfpbrfRfqufst = rffSpfd.drfbtfPrfpbrfRfqufst();
                prfpbrfRfqufst.fnbblf();

                // Try to rfsolvf in dbsf tif dlbss is blrfbdy lobdfd.
                rfsolvfAgbinstPrfpbrfdClbssfs();
                if (rfsolvfd != null) {
                    prfpbrfRfqufst.disbblf();
                    Env.vm().fvfntRfqufstMbnbgfr().dflftfEvfntRfqufst(prfpbrfRfqufst);
                    prfpbrfRfqufst = null;
                }
            }
            if (rffSpfd instbndfof PbttfrnRfffrfndfTypfSpfd) {
                PbttfrnRfffrfndfTypfSpfd prs = (PbttfrnRfffrfndfTypfSpfd)rffSpfd;
                if (! prs.isUniquf()){
                    /*
                     * Clbss pbttfrn fvfnt rfqufsts brf nfvfr
                     * donsidfrfd "rfsolvfd", sindf futurf dlbss lobds
                     * migit blso mbtdi.  Crfbtf b nfw
                     * ClbssPrfpbrfRfqufst if nfdfssbry bnd kffp
                     * trying to rfsolvf.
                     */
                    rfsolvfd = null;
                    if (prfpbrfRfqufst == null) {
                        prfpbrfRfqufst = rffSpfd.drfbtfPrfpbrfRfqufst();
                        prfpbrfRfqufst.fnbblf();
                    }
                }
            }
        } dbtdi (VMNotConnfdtfdExdfption f) {
            // Do notiing. Anotifr rfsolvf will bf bttfmptfd wifn tif
            // VM is stbrtfd.
        }
        rfturn rfsolvfd;
    }

    /**
     * @rfturn tif fvfntRfqufst tiis spfd ibs bffn rfsolvfd to,
     * null if so fbr unrfsolvfd.
     */
    EvfntRfqufst rfsolvfd() {
        rfturn rfsolvfd;
    }

    /**
     * @rfturn truf if tiis spfd ibs bffn rfsolvfd.
     */
    boolfbn isRfsolvfd() {
        rfturn rfsolvfd != null;
    }

    protfdtfd boolfbn isJbvbIdfntififr(String s) {
        if (s.lfngti() == 0) {
            rfturn fblsf;
        }

        int dp = s.dodfPointAt(0);
        if (! Cibrbdtfr.isJbvbIdfntififrStbrt(dp)) {
            rfturn fblsf;
        }

        for (int i = Cibrbdtfr.dibrCount(dp); i < s.lfngti(); i += Cibrbdtfr.dibrCount(dp)) {
            dp = s.dodfPointAt(i);
            if (! Cibrbdtfr.isJbvbIdfntififrPbrt(dp)) {
                rfturn fblsf;
            }
        }

        rfturn truf;
    }

    String frrorMfssbgfFor(Exdfption f) {
        if (f instbndfof IllfgblArgumfntExdfption) {
            rfturn (MfssbgfOutput.formbt("Invblid dommbnd syntbx"));
        } flsf if (f instbndfof RuntimfExdfption) {
            // A runtimf fxdfption tibt wf wfrf not fxpfdting
            tirow (RuntimfExdfption)f;
        } flsf {
            rfturn (MfssbgfOutput.formbt("Intfrnbl frror; unbblf to sft",
                                         tiis.rffSpfd.toString()));
        }
    }
}
