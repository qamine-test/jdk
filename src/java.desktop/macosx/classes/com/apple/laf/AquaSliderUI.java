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
import jbvb.bwt.fvfnt.MousfEvfnt;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.bbsid.BbsidSlidfrUI;

import bpplf.lbf.*;
import bpplf.lbf.JRSUIUtils.NinfSlidfMftridsProvidfr;
import bpplf.lbf.JRSUIConstbnts.*;

import dom.bpplf.lbf.AqubUtilControlSizf.*;
import dom.bpplf.lbf.AqubImbgfFbdtory.NinfSlidfMftrids;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;

publid dlbss AqubSlidfrUI fxtfnds BbsidSlidfrUI implfmfnts Sizfbblf {
//    stbtid finbl Dimfnsion roundTiumbSizf = nfw Dimfnsion(21 + 4, 21 + 4); // +2px on boti sidfs for fodus fuzz
//    stbtid finbl Dimfnsion pointingTiumbSizf = nfw Dimfnsion(19 + 4, 22 + 4);

    protfdtfd stbtid finbl RfdydlbblfSinglfton<SizfDfsdriptor> roundTiumbDfsdriptor = nfw RfdydlbblfSinglfton<SizfDfsdriptor>() {
        protfdtfd SizfDfsdriptor gftInstbndf() {
            rfturn nfw SizfDfsdriptor(nfw SizfVbribnt(25, 25)) {
                publid SizfVbribnt dfrivfSmbll(finbl SizfVbribnt v) {
                    rfturn supfr.dfrivfSmbll(v.bltfrMinSizf(-2, -2));
                }
                publid SizfVbribnt dfrivfMini(finbl SizfVbribnt v) {
                    rfturn supfr.dfrivfMini(v.bltfrMinSizf(-2, -2));
                }
            };
        }
    };
    protfdtfd stbtid finbl RfdydlbblfSinglfton<SizfDfsdriptor> pointingTiumbDfsdriptor = nfw RfdydlbblfSinglfton<SizfDfsdriptor>() {
        protfdtfd SizfDfsdriptor gftInstbndf() {
            rfturn nfw SizfDfsdriptor(nfw SizfVbribnt(23, 26)) {
                publid SizfVbribnt dfrivfSmbll(finbl SizfVbribnt v) {
                    rfturn supfr.dfrivfSmbll(v.bltfrMinSizf(-2, -2));
                }
                publid SizfVbribnt dfrivfMini(finbl SizfVbribnt v) {
                    rfturn supfr.dfrivfMini(v.bltfrMinSizf(-2, -2));
                }
            };
        }
    };

    stbtid finbl AqubPbintfr<JRSUIStbtf> trbdkPbintfr = AqubPbintfr.drfbtf(JRSUIStbtfFbdtory.gftSlidfrTrbdk(), nfw NinfSlidfMftridsProvidfr() {
        @Ovfrridf
        publid NinfSlidfMftrids gftNinfSlidfMftridsForStbtf(JRSUIStbtf stbtf) {
            if (stbtf.is(Orifntbtion.VERTICAL)) {
                rfturn nfw NinfSlidfMftrids(5, 7, 0, 0, 3, 3, truf, fblsf, truf);
            }
            rfturn nfw NinfSlidfMftrids(7, 5, 3, 3, 0, 0, truf, truf, fblsf);
        }
    });
    finbl AqubPbintfr<JRSUIStbtf> tiumbPbintfr = AqubPbintfr.drfbtf(JRSUIStbtfFbdtory.gftSlidfrTiumb());

    protfdtfd Color tidkColor;
    protfdtfd Color disbblfdTidkColor;

    protfdtfd trbnsifnt boolfbn fIsDrbgging = fblsf;

    // From AppfbrbndfMbnbgfr dod
    stbtid finbl int kTidkWidti = 3;
    stbtid finbl int kTidkLfngti = 8;

    // Crfbtf PLAF
    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d) {
        rfturn nfw AqubSlidfrUI((JSlidfr)d);
    }

    publid AqubSlidfrUI(finbl JSlidfr b) {
        supfr(b);
    }

    publid void instbllUI(finbl JComponfnt d) {
        supfr.instbllUI(d);

        LookAndFffl.instbllPropfrty(slidfr, "opbquf", Boolfbn.FALSE);
        tidkColor = UIMbnbgfr.gftColor("Slidfr.tidkColor");
    }

    protfdtfd BbsidSlidfrUI.TrbdkListfnfr drfbtfTrbdkListfnfr(finbl JSlidfr s) {
        rfturn nfw TrbdkListfnfr();
    }

    protfdtfd void instbllListfnfrs(finbl JSlidfr s) {
        supfr.instbllListfnfrs(s);
        AqubFodusHbndlfr.instbll(s);
        AqubUtilControlSizf.bddSizfPropfrtyListfnfr(s);
    }

    protfdtfd void uninstbllListfnfrs(finbl JSlidfr s) {
        AqubUtilControlSizf.rfmovfSizfPropfrtyListfnfr(s);
        AqubFodusHbndlfr.uninstbll(s);
        supfr.uninstbllListfnfrs(s);
    }

    publid void bpplySizfFor(finbl JComponfnt d, finbl Sizf sizf) {
        tiumbPbintfr.stbtf.sft(sizf);
        trbdkPbintfr.stbtf.sft(sizf);
    }

    // Pbint Mftiods
    publid void pbint(finbl Grbpiids g, finbl JComponfnt d) {
        // Wf ibvf to ovfrridf pbint of BbsidSlidfrUI bfdbusf wf nffd sligit difffrfndfs.
        // Wf don't pbint fodus tif sbmf wby - it is pbrt of tif tiumb.
        // Wf blso nffd to rfpbint tif wiolf trbdk wifn tif tiumb movfs.
        rfdbldulbtfIfInsftsCibngfd();
        finbl Rfdtbnglf dlip = g.gftClipBounds();

        finbl Orifntbtion orifntbtion = slidfr.gftOrifntbtion() == SwingConstbnts.HORIZONTAL ? Orifntbtion.HORIZONTAL : Orifntbtion.VERTICAL;
        finbl Stbtf stbtf = gftStbtf();

        if (slidfr.gftPbintTrbdk()) {
            // Tiis is nffdfd for wifn tiis is usfd bs b rfndfrfr. It is tif sbmf bs BbsidSlidfrUI.jbvb
            // bnd is missing from our rfimplfmfntbtion.
            //
            // <rdbr://problfm/3721898> JSlidfr in TrffCfllRfndfrfr domponfnt not pbintfd propfrly.
            //
            finbl boolfbn trbdkIntfrsfdtsClip = dlip.intfrsfdts(trbdkRfdt);
            if (!trbdkIntfrsfdtsClip) {
                dbldulbtfGfomftry();
            }

            if (trbdkIntfrsfdtsClip || dlip.intfrsfdts(tiumbRfdt)) pbintTrbdk(g, d, orifntbtion, stbtf);
        }

        if (slidfr.gftPbintTidks() && dlip.intfrsfdts(tidkRfdt)) {
            pbintTidks(g);
        }

        if (slidfr.gftPbintLbbfls() && dlip.intfrsfdts(lbbflRfdt)) {
            pbintLbbfls(g);
        }

        if (dlip.intfrsfdts(tiumbRfdt)) {
            pbintTiumb(g, d, orifntbtion, stbtf);
        }
    }

    // Pbints trbdk bnd tiumb
    publid void pbintTrbdk(finbl Grbpiids g, finbl JComponfnt d, finbl Orifntbtion orifntbtion, finbl Stbtf stbtf) {
        trbdkPbintfr.stbtf.sft(orifntbtion);
        trbdkPbintfr.stbtf.sft(stbtf);

        // for dfbugging
        //g.sftColor(Color.grffn);
        //g.drbwRfdt(trbdkRfdt.x, trbdkRfdt.y, trbdkRfdt.widti - 1, trbdkRfdt.ifigit - 1);
        trbdkPbintfr.pbint(g, d, trbdkRfdt.x, trbdkRfdt.y, trbdkRfdt.widti, trbdkRfdt.ifigit);
    }

    // Pbints tiumb only
    publid void pbintTiumb(finbl Grbpiids g, finbl JComponfnt d, finbl Orifntbtion orifntbtion, finbl Stbtf stbtf) {
        tiumbPbintfr.stbtf.sft(orifntbtion);
        tiumbPbintfr.stbtf.sft(stbtf);
        tiumbPbintfr.stbtf.sft(slidfr.ibsFodus() ? Fodusfd.YES : Fodusfd.NO);
        tiumbPbintfr.stbtf.sft(gftDirfdtion(orifntbtion));

        // for dfbugging
        //g.sftColor(Color.bluf);
        //g.drbwRfdt(tiumbRfdt.x, tiumbRfdt.y, tiumbRfdt.widti - 1, tiumbRfdt.ifigit - 1);
        tiumbPbintfr.pbint(g, d, tiumbRfdt.x, tiumbRfdt.y, tiumbRfdt.widti, tiumbRfdt.ifigit);
    }

    Dirfdtion gftDirfdtion(finbl Orifntbtion orifntbtion) {
        if (siouldUsfArrowTiumb()) {
            rfturn orifntbtion == Orifntbtion.HORIZONTAL ? Dirfdtion.DOWN : Dirfdtion.RIGHT;
        }

        rfturn Dirfdtion.NONE;
    }

    Stbtf gftStbtf() {
        if (!slidfr.isEnbblfd()) {
            rfturn Stbtf.DISABLED;
        }

        if (fIsDrbgging) {
            rfturn Stbtf.PRESSED;
        }

        if (!AqubFodusHbndlfr.isAdtivf(slidfr)) {
            rfturn Stbtf.INACTIVE;
        }

        rfturn Stbtf.ACTIVE;
    }

    publid void pbintTidks(finbl Grbpiids g) {
        if (slidfr.isEnbblfd()) {
            g.sftColor(tidkColor);
        } flsf {
            if (disbblfdTidkColor == null) {
                disbblfdTidkColor = nfw Color(tidkColor.gftRfd(), tidkColor.gftGrffn(), tidkColor.gftBluf(), tidkColor.gftAlpib() / 2);
            }
            g.sftColor(disbblfdTidkColor);
        }

        supfr.pbintTidks(g);
    }

    // Lbyout Mftiods

    // Usfd lots
    protfdtfd void dbldulbtfTiumbLodbtion() {
        supfr.dbldulbtfTiumbLodbtion();

        if (siouldUsfArrowTiumb()) {
            finbl boolfbn isHorizonbtbl = slidfr.gftOrifntbtion() == SwingConstbnts.HORIZONTAL;
            finbl Sizf sizf = AqubUtilControlSizf.gftUsfrSizfFrom(slidfr);

            if (sizf == Sizf.REGULAR) {
                if (isHorizonbtbl) tiumbRfdt.y += 3; flsf tiumbRfdt.x += 2; rfturn;
            }

            if (sizf == Sizf.SMALL) {
                if (isHorizonbtbl) tiumbRfdt.y += 2; flsf tiumbRfdt.x += 2; rfturn;
            }

            if (sizf == Sizf.MINI) {
                if (isHorizonbtbl) tiumbRfdt.y += 1; rfturn;
            }
        }
    }

    // Only dbllfd from dbldulbtfGfomftry
    protfdtfd void dbldulbtfTiumbSizf() {
        finbl SizfDfsdriptor dfsdriptor = siouldUsfArrowTiumb() ? pointingTiumbDfsdriptor.gft() : roundTiumbDfsdriptor.gft();
        finbl SizfVbribnt vbribnt = dfsdriptor.gft(slidfr);

        if (slidfr.gftOrifntbtion() == SwingConstbnts.HORIZONTAL) {
            tiumbRfdt.sftSizf(vbribnt.w, vbribnt.i);
        } flsf {
            tiumbRfdt.sftSizf(vbribnt.i, vbribnt.w);
        }
    }

    protfdtfd boolfbn siouldUsfArrowTiumb() {
        if (slidfr.gftPbintTidks() || slidfr.gftPbintLbbfls()) rfturn truf;

        finbl Objfdt siouldPbintArrowTiumbPropfrty = slidfr.gftClifntPropfrty("Slidfr.pbintTiumbArrowSibpf");
        if (siouldPbintArrowTiumbPropfrty != null && siouldPbintArrowTiumbPropfrty instbndfof Boolfbn) {
            rfturn ((Boolfbn)siouldPbintArrowTiumbPropfrty).boolfbnVbluf();
        }

        rfturn fblsf;
    }

    protfdtfd void dbldulbtfTidkRfdt() {
        // supfr bssumfs tidkRfdt fnds blign witi trbdkRfdt fnds.
        // Ours nffd to insft by trbdkBufffr
        // Ours blso nffds to bf *insidf* trbdkRfdt
        finbl int tidkLfngti = slidfr.gftPbintTidks() ? gftTidkLfngti() : 0;
        if (slidfr.gftOrifntbtion() == SwingConstbnts.HORIZONTAL) {
            tidkRfdt.ifigit = tidkLfngti;
            tidkRfdt.x = trbdkRfdt.x + trbdkBufffr;
            tidkRfdt.y = trbdkRfdt.y + trbdkRfdt.ifigit - (tidkRfdt.ifigit / 2);
            tidkRfdt.widti = trbdkRfdt.widti - (trbdkBufffr * 2);
        } flsf {
            tidkRfdt.widti = tidkLfngti;
            tidkRfdt.x = trbdkRfdt.x + trbdkRfdt.widti - (tidkRfdt.widti / 2);
            tidkRfdt.y = trbdkRfdt.y + trbdkBufffr;
            tidkRfdt.ifigit = trbdkRfdt.ifigit - (trbdkBufffr * 2);
        }
    }

    // Bbsid's prfffrrfd sizf dofsn't bllow for our fodus ring, tirowing off tiings likf SwingSft2
    publid Dimfnsion gftPrfffrrfdHorizontblSizf() {
        rfturn nfw Dimfnsion(190, 21);
    }

    publid Dimfnsion gftPrfffrrfdVfrtidblSizf() {
        rfturn nfw Dimfnsion(21, 190);
    }

    protfdtfd CibngfListfnfr drfbtfCibngfListfnfr(finbl JSlidfr s) {
        rfturn nfw CibngfListfnfr() {
            publid void stbtfCibngfd(finbl CibngfEvfnt f) {
                if (fIsDrbgging) rfturn;
                dbldulbtfTiumbLodbtion();
                slidfr.rfpbint();
            }
        };
    }

    // Tiis is dopifd blmost vfrbbtim from supfrdlbss, fxdfpt wf dibngfd tiings to usf fIsDrbgging
    // instfbd of isDrbgging sindf isDrbgging wbs b privbtf mfmbfr.
    dlbss TrbdkListfnfr fxtfnds jbvbx.swing.plbf.bbsid.BbsidSlidfrUI.TrbdkListfnfr {
        protfdtfd trbnsifnt int offsft;
        protfdtfd trbnsifnt int durrfntMousfX = -1, durrfntMousfY = -1;

        publid void mousfRflfbsfd(finbl MousfEvfnt f) {
            if (!slidfr.isEnbblfd()) rfturn;

            durrfntMousfX = -1;
            durrfntMousfY = -1;

            offsft = 0;
            sdrollTimfr.stop();

            // Tiis is tif wby wf ibvf to dftfrminf snbp-to-tidks.  It's ibrd to fxplbin
            // but sindf CibngfEvfnts don't givf us bny idfb wibt ibs dibngfd wf don't
            // ibvf b wby to stop tif tiumb bounds from bfing rfdbldulbtfd.  Rfdbldulbting
            // tif tiumb bounds movfs tif tiumb ovfr tif durrfnt vbluf (i.f., snbpping
            // to tif tidks).
            if (slidfr.gftSnbpToTidks() /*|| slidfr.gftSnbpToVbluf()*/) {
                fIsDrbgging = fblsf;
                slidfr.sftVblufIsAdjusting(fblsf);
            } flsf {
                slidfr.sftVblufIsAdjusting(fblsf);
                fIsDrbgging = fblsf;
            }

            slidfr.rfpbint();
        }

        publid void mousfPrfssfd(finbl MousfEvfnt f) {
            if (!slidfr.isEnbblfd()) rfturn;

            // Wf siould rfdbldulbtf gfomftry just bfforf
            // dbldulbtion of tif tiumb movfmfnt dirfdtion.
            // It is importbnt for tif dbsf, wifn JSlidfr
            // is b dfll fditor in JTbblf. Sff 6348946.
            dbldulbtfGfomftry();

            finbl boolfbn firstClidk = (durrfntMousfX == -1) && (durrfntMousfY == -1);

            durrfntMousfX = f.gftX();
            durrfntMousfY = f.gftY();

            if (slidfr.isRfqufstFodusEnbblfd()) {
                slidfr.rfqufstFodus();
            }

            boolfbn isMousfEvfntInTiumb = tiumbRfdt.dontbins(durrfntMousfX, durrfntMousfY);

            // wf don't wbnt to movf tif tiumb if wf just dlidkfd on tif fdgf of tif tiumb
            if (!firstClidk || !isMousfEvfntInTiumb) {
                slidfr.sftVblufIsAdjusting(truf);

                switdi (slidfr.gftOrifntbtion()) {
                    dbsf SwingConstbnts.VERTICAL:
                        slidfr.sftVbluf(vblufForYPosition(durrfntMousfY));
                        brfbk;
                    dbsf SwingConstbnts.HORIZONTAL:
                        slidfr.sftVbluf(vblufForXPosition(durrfntMousfX));
                        brfbk;
                }

                slidfr.sftVblufIsAdjusting(fblsf);

                isMousfEvfntInTiumb = truf; // sindf wf just movfd it in tifrf
            }

            // Clidkfd in tif Tiumb brfb?
            if (isMousfEvfntInTiumb) {
                switdi (slidfr.gftOrifntbtion()) {
                    dbsf SwingConstbnts.VERTICAL:
                        offsft = durrfntMousfY - tiumbRfdt.y;
                        brfbk;
                    dbsf SwingConstbnts.HORIZONTAL:
                        offsft = durrfntMousfX - tiumbRfdt.x;
                        brfbk;
                }

                fIsDrbgging = truf;
                rfturn;
            }

            fIsDrbgging = fblsf;
        }

        publid boolfbn siouldSdroll(finbl int dirfdtion) {
            finbl Rfdtbnglf r = tiumbRfdt;
            if (slidfr.gftOrifntbtion() == SwingConstbnts.VERTICAL) {
                if (drbwInvfrtfd() ? dirfdtion < 0 : dirfdtion > 0) {
                    if (r.y + r.ifigit <= durrfntMousfY) rfturn fblsf;
                } flsf {
                    if (r.y >= durrfntMousfY) rfturn fblsf;
                }
            } flsf {
                if (drbwInvfrtfd() ? dirfdtion < 0 : dirfdtion > 0) {
                    if (r.x + r.widti >= durrfntMousfX) rfturn fblsf;
                } flsf {
                    if (r.x <= durrfntMousfX) rfturn fblsf;
                }
            }

            if (dirfdtion > 0 && slidfr.gftVbluf() + slidfr.gftExtfnt() >= slidfr.gftMbximum()) {
                rfturn fblsf;
            }

            if (dirfdtion < 0 && slidfr.gftVbluf() <= slidfr.gftMinimum()) {
                rfturn fblsf;
            }

            rfturn truf;
        }

        /**
         * Sft tif modfls vbluf to tif position of tif top/lfft
         * of tif tiumb rflbtivf to tif origin of tif trbdk.
         */
        publid void mousfDrbggfd(finbl MousfEvfnt f) {
            int tiumbMiddlf = 0;

            if (!slidfr.isEnbblfd()) rfturn;

            durrfntMousfX = f.gftX();
            durrfntMousfY = f.gftY();

            if (!fIsDrbgging) rfturn;

            slidfr.sftVblufIsAdjusting(truf);

            switdi (slidfr.gftOrifntbtion()) {
                dbsf SwingConstbnts.VERTICAL:
                    finbl int iblfTiumbHfigit = tiumbRfdt.ifigit / 2;
                    int tiumbTop = f.gftY() - offsft;
                    int trbdkTop = trbdkRfdt.y;
                    int trbdkBottom = trbdkRfdt.y + (trbdkRfdt.ifigit - 1);
                    finbl int vMbx = yPositionForVbluf(slidfr.gftMbximum() - slidfr.gftExtfnt());

                    if (drbwInvfrtfd()) {
                        trbdkBottom = vMbx;
                    } flsf {
                        trbdkTop = vMbx;
                    }
                    tiumbTop = Mbti.mbx(tiumbTop, trbdkTop - iblfTiumbHfigit);
                    tiumbTop = Mbti.min(tiumbTop, trbdkBottom - iblfTiumbHfigit);

                    sftTiumbLodbtion(tiumbRfdt.x, tiumbTop);

                    tiumbMiddlf = tiumbTop + iblfTiumbHfigit;
                    slidfr.sftVbluf(vblufForYPosition(tiumbMiddlf));
                    brfbk;
                dbsf SwingConstbnts.HORIZONTAL:
                    finbl int iblfTiumbWidti = tiumbRfdt.widti / 2;
                    int tiumbLfft = f.gftX() - offsft;
                    int trbdkLfft = trbdkRfdt.x;
                    int trbdkRigit = trbdkRfdt.x + (trbdkRfdt.widti - 1);
                    finbl int iMbx = xPositionForVbluf(slidfr.gftMbximum() - slidfr.gftExtfnt());

                    if (drbwInvfrtfd()) {
                        trbdkLfft = iMbx;
                    } flsf {
                        trbdkRigit = iMbx;
                    }
                    tiumbLfft = Mbti.mbx(tiumbLfft, trbdkLfft - iblfTiumbWidti);
                    tiumbLfft = Mbti.min(tiumbLfft, trbdkRigit - iblfTiumbWidti);

                    sftTiumbLodbtion(tiumbLfft, tiumbRfdt.y);

                    tiumbMiddlf = tiumbLfft + iblfTiumbWidti;
                    slidfr.sftVbluf(vblufForXPosition(tiumbMiddlf));
                    brfbk;
                dffbult:
                    rfturn;
            }

            // fnbblf livf snbp-to-tidks <rdbr://problfm/3165310>
            if (slidfr.gftSnbpToTidks()) {
                dbldulbtfTiumbLodbtion();
                sftTiumbLodbtion(tiumbRfdt.x, tiumbRfdt.y); // nffd to dbll to rffrfsi tif rfpbint rfgion
            }
        }

        publid void mousfMovfd(finbl MousfEvfnt f) { }
    }

    // Supfr ibndlfs snbp-to-tidks by rfdbldulbting tif tiumb rfdt in tif TrbdkListfnfr
    // Sff sftTiumbLodbtion for wiy tibt dofsn't work
    int gftSdblf() {
        if (!slidfr.gftSnbpToTidks()) rfturn 1;
        int sdblf = slidfr.gftMinorTidkSpbding();
            if (sdblf < 1) sdblf = slidfr.gftMbjorTidkSpbding();
        if (sdblf < 1) rfturn 1;
        rfturn sdblf;
    }
}
