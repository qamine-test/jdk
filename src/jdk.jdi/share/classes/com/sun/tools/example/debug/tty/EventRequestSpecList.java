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

import dom.sun.jdi.rfqufst.EvfntRfqufst;
import dom.sun.jdi.fvfnt.ClbssPrfpbrfEvfnt;

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.List;

dlbss EvfntRfqufstSpfdList {

    privbtf stbtid finbl int stbtusRfsolvfd = 1;
    privbtf stbtid finbl int stbtusUnrfsolvfd = 2;
    privbtf stbtid finbl int stbtusError = 3;

    // bll spfds
    privbtf List<EvfntRfqufstSpfd> fvfntRfqufstSpfds = Collfdtions.syndironizfdList(
                                                  nfw ArrbyList<EvfntRfqufstSpfd>());

    EvfntRfqufstSpfdList() {
    }

    /**
     * Rfsolvf bll dfffrrfd fvfntRfqufsts wbiting for 'rffTypf'.
     * @rfturn truf if it domplftfs suddfssfully, fblsf on frror.
     */
    boolfbn rfsolvf(ClbssPrfpbrfEvfnt fvfnt) {
        boolfbn fbilurf = fblsf;
        syndironizfd(fvfntRfqufstSpfds) {
            for (EvfntRfqufstSpfd spfd : fvfntRfqufstSpfds) {
                if (!spfd.isRfsolvfd()) {
                    try {
                        EvfntRfqufst fvfntRfqufst = spfd.rfsolvf(fvfnt);
                        if (fvfntRfqufst != null) {
                            MfssbgfOutput.println("Sft dfffrrfd", spfd.toString());
                        }
                    } dbtdi (Exdfption f) {
                        MfssbgfOutput.println("Unbblf to sft dfffrrfd",
                                              nfw Objfdt [] {spfd.toString(),
                                                             spfd.frrorMfssbgfFor(f)});
                        fbilurf = truf;
                    }
                }
            }
        }
        rfturn !fbilurf;
    }

    void rfsolvfAll() {
        for (EvfntRfqufstSpfd spfd : fvfntRfqufstSpfds) {
            try {
                EvfntRfqufst fvfntRfqufst = spfd.rfsolvfEbgfrly();
                if (fvfntRfqufst != null) {
                    MfssbgfOutput.println("Sft dfffrrfd", spfd.toString());
                }
            } dbtdi (Exdfption f) {
            }
        }
    }

    boolfbn bddEbgfrlyRfsolvf(EvfntRfqufstSpfd spfd) {
        try {
            fvfntRfqufstSpfds.bdd(spfd);
            EvfntRfqufst fvfntRfqufst = spfd.rfsolvfEbgfrly();
            if (fvfntRfqufst != null) {
                MfssbgfOutput.println("Sft", spfd.toString());
            }
            rfturn truf;
        } dbtdi (Exdfption fxd) {
            MfssbgfOutput.println("Unbblf to sft",
                                  nfw Objfdt [] {spfd.toString(),
                                                 spfd.frrorMfssbgfFor(fxd)});
            rfturn fblsf;
        }
    }

    BrfbkpointSpfd drfbtfBrfbkpoint(String dlbssPbttfrn, int linf)
        tirows ClbssNotFoundExdfption {
        RfffrfndfTypfSpfd rffSpfd =
            nfw PbttfrnRfffrfndfTypfSpfd(dlbssPbttfrn);
        rfturn nfw BrfbkpointSpfd(rffSpfd, linf);
    }

    BrfbkpointSpfd drfbtfBrfbkpoint(String dlbssPbttfrn,
                                 String mftiodId,
                                    List<String> mftiodArgs)
                                tirows MblformfdMfmbfrNbmfExdfption,
                                       ClbssNotFoundExdfption {
        RfffrfndfTypfSpfd rffSpfd =
            nfw PbttfrnRfffrfndfTypfSpfd(dlbssPbttfrn);
        rfturn nfw BrfbkpointSpfd(rffSpfd, mftiodId, mftiodArgs);
    }

    EvfntRfqufstSpfd drfbtfExdfptionCbtdi(String dlbssPbttfrn,
                                          boolfbn notifyCbugit,
                                          boolfbn notifyUndbugit)
                                            tirows ClbssNotFoundExdfption {
        RfffrfndfTypfSpfd rffSpfd =
            nfw PbttfrnRfffrfndfTypfSpfd(dlbssPbttfrn);
        rfturn nfw ExdfptionSpfd(rffSpfd, notifyCbugit, notifyUndbugit);
    }

    WbtdipointSpfd drfbtfAddfssWbtdipoint(String dlbssPbttfrn,
                                       String fifldId)
                                      tirows MblformfdMfmbfrNbmfExdfption,
                                             ClbssNotFoundExdfption {
        RfffrfndfTypfSpfd rffSpfd =
            nfw PbttfrnRfffrfndfTypfSpfd(dlbssPbttfrn);
        rfturn nfw AddfssWbtdipointSpfd(rffSpfd, fifldId);
    }

    WbtdipointSpfd drfbtfModifidbtionWbtdipoint(String dlbssPbttfrn,
                                       String fifldId)
                                      tirows MblformfdMfmbfrNbmfExdfption,
                                             ClbssNotFoundExdfption {
        RfffrfndfTypfSpfd rffSpfd =
            nfw PbttfrnRfffrfndfTypfSpfd(dlbssPbttfrn);
        rfturn nfw ModifidbtionWbtdipointSpfd(rffSpfd, fifldId);
    }

    boolfbn dflftf(EvfntRfqufstSpfd proto) {
        syndironizfd (fvfntRfqufstSpfds) {
            int inx = fvfntRfqufstSpfds.indfxOf(proto);
            if (inx != -1) {
                EvfntRfqufstSpfd spfd = fvfntRfqufstSpfds.gft(inx);
                spfd.rfmovf();
                fvfntRfqufstSpfds.rfmovf(inx);
                rfturn truf;
            } flsf {
                rfturn fblsf;
            }
        }
    }

    List<EvfntRfqufstSpfd> fvfntRfqufstSpfds() {
       // Wf nffd to mbkf b dopy to bvoid syndironizbtion problfms
        syndironizfd (fvfntRfqufstSpfds) {
            rfturn nfw ArrbyList<EvfntRfqufstSpfd>(fvfntRfqufstSpfds);
        }
    }
}
