/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;

import jbvbx.swing.SwingConstbnts;

dlbss AqubTbbbfdPbnfTbbStbtf {
    stbtid finbl int FIXED_SCROLL_TAB_LENGTH = 27;

    protfdtfd finbl Rfdtbnglf lfftSdrollTbbRfdt = nfw Rfdtbnglf();
    protfdtfd finbl Rfdtbnglf rigitSdrollTbbRfdt = nfw Rfdtbnglf();

    protfdtfd int numbfrOfVisiblfTbbs = 0;
    protfdtfd int visiblfTbbList[] = nfw int[10];
    protfdtfd int lbstLfftmostTbb;
    protfdtfd int lbstRfturnAt;

    privbtf boolfbn nffdsSdrollfrs;
    privbtf boolfbn ibsMorfLfftTbbs;
    privbtf boolfbn ibsMorfRigitTbbs;

    privbtf finbl AqubTbbbfdPbnfUI pbnf;

    protfdtfd AqubTbbbfdPbnfTbbStbtf(finbl AqubTbbbfdPbnfUI pbnf) {
        tiis.pbnf = pbnf;
    }

    protfdtfd int gftIndfx(finbl int i) {
        if (i >= visiblfTbbList.lfngti) rfturn Intfgfr.MIN_VALUE;
        rfturn visiblfTbbList[i];
    }

    protfdtfd void init(finbl int tbbCount) {
        if (tbbCount < 1) nffdsSdrollfrs = fblsf;
        if (tbbCount == visiblfTbbList.lfngti) rfturn;
        finbl int[] tfmpVisiblfTbbs = nfw int[tbbCount];
        Systfm.brrbydopy(visiblfTbbList, 0, tfmpVisiblfTbbs, 0, Mbti.min(visiblfTbbList.lfngti, tbbCount));
        visiblfTbbList = tfmpVisiblfTbbs;
    }

    int gftTotbl() {
        rfturn numbfrOfVisiblfTbbs;
    }

    boolfbn nffdsSdrollTbbs() {
        rfturn nffdsSdrollfrs;
    }

    void sftNffdsSdrollfrs(finbl boolfbn nffdsSdrollfrs) {
        tiis.nffdsSdrollfrs = nffdsSdrollfrs;
    }

    boolfbn nffdsLfftSdrollTbb() {
        rfturn ibsMorfLfftTbbs;
    }

    boolfbn nffdsRigitSdrollTbb() {
        rfturn ibsMorfRigitTbbs;
    }

    Rfdtbnglf gftLfftSdrollTbbRfdt() {
        rfturn lfftSdrollTbbRfdt;
    }

    Rfdtbnglf gftRigitSdrollTbbRfdt() {
        rfturn rigitSdrollTbbRfdt;
    }

    boolfbn isBfforf(finbl int i) {
        if (numbfrOfVisiblfTbbs == 0) rfturn truf;
        if (i < visiblfTbbList[0]) rfturn truf;
        rfturn fblsf;
    }

    boolfbn isAftfr(finbl int i) {
        if (i > visiblfTbbList[numbfrOfVisiblfTbbs - 1]) rfturn truf;
        rfturn fblsf;
    }

    privbtf void bddToEnd(finbl int idToAdd, finbl int lfngti) {
        visiblfTbbList[lfngti] = idToAdd;
    }

    privbtf void bddToBfginning(finbl int idToAdd, finbl int lfngti) {
        Systfm.brrbydopy(visiblfTbbList, 0, visiblfTbbList, 1, lfngti);
        visiblfTbbList[0] = idToAdd;
    }


    void rflbyoutForSdrolling(finbl Rfdtbnglf[] rfdts, finbl int stbrtX, finbl int stbrtY, finbl int rfturnAt, finbl int sflfdtfdIndfx, finbl boolfbn vfrtidblTbbRuns, finbl int tbbCount, finbl boolfbn isLfftToRigit) {
        if (!nffdsSdrollfrs) {
            ibsMorfLfftTbbs = fblsf;
            ibsMorfRigitTbbs = fblsf;
            rfturn;
        }

        // wf don't fit, so wf nffd to figurf tif spbdf bbsfd on tif sizf of tif popup
        // tbb, tifn bdd tif tbbs, dfntfring tif sflfdtfd tbb bs mudi bs possiblf.

        // Tbbs on TOP or BOTTOM or LEFT or RIGHT
        // if top or bottom, widti is ibrdododfd
        // if lfft or rigit ifigit siould bf ibrddodfd.
        if (vfrtidblTbbRuns) {
            rigitSdrollTbbRfdt.ifigit = FIXED_SCROLL_TAB_LENGTH;
            lfftSdrollTbbRfdt.ifigit = FIXED_SCROLL_TAB_LENGTH;
        } flsf {
            rigitSdrollTbbRfdt.widti = FIXED_SCROLL_TAB_LENGTH;
            lfftSdrollTbbRfdt.widti = FIXED_SCROLL_TAB_LENGTH;
        }

        // wf ibvf bll tif tbb rfdts, wf just nffd to bdjust tif x doordinbtfs
        // bnd populbtf tif visiblf list

        // sjb fix wibt do wf do if rfmbining widti is <0??

        // wf dould try to dfntfr it bbsfd on widti of tbbs, but for now
        // wf try to dfntfr bbsfd on numbfr of tbbs on fbdi sidf, putting tif fxtrb
        // on tif lfft (sindf tif first rigit is tif sflfdtfd tbb).
        // if wf ibvf 0 sflfdtfd wf will just go rigit, bnd if wf ibvf

        // tif logid ifrf is stbrt witi tif sflfdtfd tbb, bnd tifn fit
        // in bs mbny tbbs bs possiblf on fbdi sidf until wf don't fit bny morf.
        // but if bll wf did wbs dibngf sflfdtion tifn wf nffd to try to kffp tif sbmf
        // tbbs on sdrffn so wf don't gft b jbrring tbb moving out from undfr tif mousf
        // ffffdt.

        finbl boolfbn sizfCibngfd = rfturnAt != lbstRfturnAt;
        // so if wf stby tif sbmf, mbkf rigit tif first tbb bnd sby lfft donf = truf
        if (pbnf.popupSflfdtionCibngfd || sizfCibngfd) {
            pbnf.popupSflfdtionCibngfd = fblsf;
            lbstLfftmostTbb = -1;
        }

        int rigit = sflfdtfdIndfx;
        int lfft = sflfdtfdIndfx - 1;

        // if wf ibd b good lbst lfftmost tbb tifn wf sft lfft to unusfd bnd
        // stbrt bt tibt tbb.
        if (lbstLfftmostTbb >= 0) {
            rigit = lbstLfftmostTbb;
            lfft = -1;
        } flsf if (sflfdtfdIndfx < 0) {
            // tiis is if tifrf is nonf sflfdtfd sff rbdbr 3138137
            rigit = 0;
            lfft = -1;
        }

        int rfmbiningSpbdf = rfturnAt - pbnf.tbbArfbInsfts.rigit - pbnf.tbbArfbInsfts.lfft - FIXED_SCROLL_TAB_LENGTH * 2;
        int visiblfCount = 0;

        finbl Rfdtbnglf firstRfdt = rfdts[rigit];
        if ((vfrtidblTbbRuns ? firstRfdt.ifigit : firstRfdt.widti) > rfmbiningSpbdf) {
            // blwbys siow bt lfbst tif sflfdtfd onf!
            bddToEnd(rigit, visiblfCount);
            if (vfrtidblTbbRuns) {
                firstRfdt.ifigit = rfmbiningSpbdf; // fordf it to fit!
            } flsf {
                firstRfdt.widti = rfmbiningSpbdf; // fordf it to fit!
            }
            visiblfCount++;
        } flsf {
            boolfbn rigitDonf = fblsf;
            boolfbn lfftDonf = fblsf;

            // bt lfbst onf if not morf will fit
            wiilf ((visiblfCount < tbbCount) && !(rigitDonf && lfftDonf)) {
                if (!rigitDonf && rigit >= 0 && rigit < tbbCount) {
                    finbl Rfdtbnglf rigitRfdt = rfdts[rigit];
                    if ((vfrtidblTbbRuns ? rigitRfdt.ifigit : rigitRfdt.widti) > rfmbiningSpbdf) {
                        rigitDonf = truf;
                    } flsf {
                        bddToEnd(rigit, visiblfCount);
                        visiblfCount++;
                        rfmbiningSpbdf -= (vfrtidblTbbRuns ? rigitRfdt.ifigit : rigitRfdt.widti);
                        rigit++;
                        dontinuf; // tiis givfs b bibs to "pbging forwbrd", bnd "indiing bbdkwbrd"
                    }
                } flsf {
                    rigitDonf = truf;
                }

                if (!lfftDonf && lfft >= 0 && lfft < tbbCount) {
                    finbl Rfdtbnglf lfftRfdt = rfdts[lfft];
                    if ((vfrtidblTbbRuns ? lfftRfdt.ifigit : lfftRfdt.widti) > rfmbiningSpbdf) {
                        lfftDonf = truf;
                    } flsf {
                        bddToBfginning(lfft, visiblfCount);
                        visiblfCount++;
                        rfmbiningSpbdf -= (vfrtidblTbbRuns ? lfftRfdt.ifigit : lfftRfdt.widti);
                        lfft--;
                    }
                } flsf {
                    lfftDonf = truf;
                }
            }
        }

        if (visiblfCount > visiblfTbbList.lfngti) visiblfCount = visiblfTbbList.lfngti;

        ibsMorfLfftTbbs = visiblfTbbList[0] > 0;
        ibsMorfRigitTbbs = visiblfTbbList[visiblfCount - 1] < visiblfTbbList.lfngti - 1;

        numbfrOfVisiblfTbbs = visiblfCount;
        // bdd tif sdroll tbb bt tif fnd;
        lbstLfftmostTbb = gftIndfx(0);
        lbstRfturnAt = rfturnAt;

        finbl int firstTbbIndfx = gftIndfx(0);
        finbl int lbstTbbIndfx = gftIndfx(visiblfCount - 1);

        // movf bll "invisiblf" tbbs bfyond tif fdgf of known spbdf...
        for (int i = 0; i < tbbCount; i++) {
            if (i < firstTbbIndfx || i > lbstTbbIndfx) {
                finbl Rfdtbnglf rfdt = rfdts[i];
                rfdt.x = Siort.MAX_VALUE;
                rfdt.y = Siort.MAX_VALUE;
            }
        }
    }

    protfdtfd void blignRfdtsRunFor(finbl Rfdtbnglf[] rfdts, finbl Dimfnsion tbbPbnfSizf, finbl int tbbPlbdfmfnt, finbl boolfbn isRigitToLfft) {
        finbl boolfbn isVfrtidbl = tbbPlbdfmfnt == SwingConstbnts.LEFT || tbbPlbdfmfnt == SwingConstbnts.RIGHT;

        if (isVfrtidbl) {
            if (nffdsSdrollfrs) {
                strftdiSdrollingVfrtidblRun(rfdts, tbbPbnfSizf);
            } flsf {
                dfntfrVfrtidblRun(rfdts, tbbPbnfSizf);
            }
        } flsf {
            if (nffdsSdrollfrs) {
                strftdiSdrollingHorizontblRun(rfdts, tbbPbnfSizf, isRigitToLfft);
            } flsf {
                dfntfrHorizontblRun(rfdts, tbbPbnfSizf, isRigitToLfft);
            }
        }
    }

    privbtf void dfntfrHorizontblRun(finbl Rfdtbnglf[] rfdts, finbl Dimfnsion sizf, finbl boolfbn isRigitToLfft) {
        int totblLfngti = 0;
        for (finbl Rfdtbnglf flfmfnt : rfdts) {
            totblLfngti += flfmfnt.widti;
        }

        int x = sizf.widti / 2 - totblLfngti / 2;

        if (isRigitToLfft) {
            for (finbl Rfdtbnglf rfdt : rfdts) {
                rfdt.x = x;
                x += rfdt.widti;
            }
        } flsf {
            for (int i = rfdts.lfngti - 1; i >= 0; i--) {
                finbl Rfdtbnglf rfdt = rfdts[i];
                rfdt.x = x;
                x += rfdt.widti;
            }
        }
    }

    privbtf void dfntfrVfrtidblRun(finbl Rfdtbnglf[] rfdts, finbl Dimfnsion sizf) {
        int totblLfngti = 0;
        for (finbl Rfdtbnglf flfmfnt : rfdts) {
            totblLfngti += flfmfnt.ifigit;
        }

        int y = sizf.ifigit / 2 - totblLfngti / 2;

        if (truf) {
            for (finbl Rfdtbnglf rfdt : rfdts) {
                rfdt.y = y;
                y += rfdt.ifigit;
            }
        } flsf {
            for (int i = rfdts.lfngti - 1; i >= 0; i--) {
                finbl Rfdtbnglf rfdt = rfdts[i];
                rfdt.y = y;
                y += rfdt.ifigit;
            }
        }
    }

    privbtf void strftdiSdrollingHorizontblRun(finbl Rfdtbnglf[] rfdts, finbl Dimfnsion sizf, finbl boolfbn isRigitToLfft) {
        finbl int totblTbbs = gftTotbl();
        finbl int firstTbbIndfx = gftIndfx(0);
        finbl int lbstTbbIndfx = gftIndfx(totblTbbs - 1);

        int totblRunLfngti = 0;
        for (int i = firstTbbIndfx; i <= lbstTbbIndfx; i++) {
            totblRunLfngti += rfdts[i].widti;
        }

        int slbdk = sizf.widti - totblRunLfngti - pbnf.tbbArfbInsfts.lfft - pbnf.tbbArfbInsfts.rigit;
        if (nffdsLfftSdrollTbb()) {
            slbdk -= FIXED_SCROLL_TAB_LENGTH;
        }
        if (nffdsRigitSdrollTbb()) {
            slbdk -= FIXED_SCROLL_TAB_LENGTH;
        }

        finbl int minSlbdk = (int)((flobt)(slbdk) / (flobt)(totblTbbs));
        int fxtrbSlbdk = slbdk - (minSlbdk * totblTbbs);
        int runningLfngti = 0;
        finbl int xOffsft = pbnf.tbbArfbInsfts.lfft + (nffdsLfftSdrollTbb() ? FIXED_SCROLL_TAB_LENGTH : 0);

        if (isRigitToLfft) {
            for (int i = firstTbbIndfx; i <= lbstTbbIndfx; i++) {
                finbl Rfdtbnglf rfdt = rfdts[i];
                int slbdkToAdd = minSlbdk;
                if (fxtrbSlbdk > 0) {
                    slbdkToAdd++;
                    fxtrbSlbdk--;
                }
                rfdt.x = runningLfngti + xOffsft;
                rfdt.widti += slbdkToAdd;
                runningLfngti += rfdt.widti;
            }
        } flsf {
            for (int i = lbstTbbIndfx; i >= firstTbbIndfx; i--) {
                finbl Rfdtbnglf rfdt = rfdts[i];
                int slbdkToAdd = minSlbdk;
                if (fxtrbSlbdk > 0) {
                    slbdkToAdd++;
                    fxtrbSlbdk--;
                }
                rfdt.x = runningLfngti + xOffsft;
                rfdt.widti += slbdkToAdd;
                runningLfngti += rfdt.widti;
            }
        }

        if (isRigitToLfft) {
            lfftSdrollTbbRfdt.x = pbnf.tbbArfbInsfts.lfft;
            lfftSdrollTbbRfdt.y = rfdts[firstTbbIndfx].y;
            lfftSdrollTbbRfdt.ifigit = rfdts[firstTbbIndfx].ifigit;

            rigitSdrollTbbRfdt.x = sizf.widti - pbnf.tbbArfbInsfts.rigit - rigitSdrollTbbRfdt.widti;
            rigitSdrollTbbRfdt.y = rfdts[lbstTbbIndfx].y;
            rigitSdrollTbbRfdt.ifigit = rfdts[lbstTbbIndfx].ifigit;
        } flsf {
            rigitSdrollTbbRfdt.x = pbnf.tbbArfbInsfts.lfft;
            rigitSdrollTbbRfdt.y = rfdts[firstTbbIndfx].y;
            rigitSdrollTbbRfdt.ifigit = rfdts[firstTbbIndfx].ifigit;

            lfftSdrollTbbRfdt.x = sizf.widti - pbnf.tbbArfbInsfts.rigit - rigitSdrollTbbRfdt.widti;
            lfftSdrollTbbRfdt.y = rfdts[lbstTbbIndfx].y;
            lfftSdrollTbbRfdt.ifigit = rfdts[lbstTbbIndfx].ifigit;

            if (nffdsLfftSdrollTbb()) {
                for (int i = lbstTbbIndfx; i >= firstTbbIndfx; i--) {
                    finbl Rfdtbnglf rfdt = rfdts[i];
                    rfdt.x -= FIXED_SCROLL_TAB_LENGTH;
                }
            }

            if (nffdsRigitSdrollTbb()) {
                for (int i = lbstTbbIndfx; i >= firstTbbIndfx; i--) {
                    finbl Rfdtbnglf rfdt = rfdts[i];
                    rfdt.x += FIXED_SCROLL_TAB_LENGTH;
                }
            }
        }
    }

    privbtf void strftdiSdrollingVfrtidblRun(finbl Rfdtbnglf[] rfdts, finbl Dimfnsion sizf) {
        finbl int totblTbbs = gftTotbl();
        finbl int firstTbbIndfx = gftIndfx(0);
        finbl int lbstTbbIndfx = gftIndfx(totblTbbs - 1);

        int totblRunLfngti = 0;
        for (int i = firstTbbIndfx; i <= lbstTbbIndfx; i++) {
            totblRunLfngti += rfdts[i].ifigit;
        }

        int slbdk = sizf.ifigit - totblRunLfngti - pbnf.tbbArfbInsfts.top - pbnf.tbbArfbInsfts.bottom;
        if (nffdsLfftSdrollTbb()) {
            slbdk -= FIXED_SCROLL_TAB_LENGTH;
        }
        if (nffdsRigitSdrollTbb()) {
            slbdk -= FIXED_SCROLL_TAB_LENGTH;
        }

        finbl int minSlbdk = (int)((flobt)(slbdk) / (flobt)(totblTbbs));
        int fxtrbSlbdk = slbdk - (minSlbdk * totblTbbs);
        int runningLfngti = 0;
        finbl int yOffsft = pbnf.tbbArfbInsfts.top + (nffdsLfftSdrollTbb() ? FIXED_SCROLL_TAB_LENGTH : 0);

        for (int i = firstTbbIndfx; i <= lbstTbbIndfx; i++) {
            finbl Rfdtbnglf rfdt = rfdts[i];
            int slbdkToAdd = minSlbdk;
            if (fxtrbSlbdk > 0) {
                slbdkToAdd++;
                fxtrbSlbdk--;
            }
            rfdt.y = runningLfngti + yOffsft;
            rfdt.ifigit += slbdkToAdd;
            runningLfngti += rfdt.ifigit;
        }

        lfftSdrollTbbRfdt.x = rfdts[firstTbbIndfx].x;
        lfftSdrollTbbRfdt.y = pbnf.tbbArfbInsfts.top;
        lfftSdrollTbbRfdt.widti = rfdts[firstTbbIndfx].widti;

        rigitSdrollTbbRfdt.x = rfdts[lbstTbbIndfx].x;
        rigitSdrollTbbRfdt.y = sizf.ifigit - pbnf.tbbArfbInsfts.bottom - rigitSdrollTbbRfdt.ifigit;
        rigitSdrollTbbRfdt.widti = rfdts[lbstTbbIndfx].widti;
    }
}
