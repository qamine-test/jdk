/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;
import dom.sun.jdi.fvfnt.*;
import dom.sun.jdi.rfqufst.*;

import jbvb.util.*;
fnum EvfntDfstinbtion {UNKNOWN_EVENT, INTERNAL_EVENT, CLIENT_EVENT};

/*
 * An EvfntSft is normblly drfbtfd by tif trbnsport rfbdfr tirfbd wifn
 * it rfbds b JDWP Compositf dommbnd.  Tif donstrudtor dofsn't unpbdk
 * tif fvfnts dontbinfd in tif Compositf dommbnd bnd drfbtf EvfntImpls
 * for tifm bfdbusf tibt prodfss migit involvf dblling bbdk into tif bbdk-fnd
 * wiidi siould not bf donf by tif trbnsport rfbdfr tirfbd.  Instfbd,
 * tif rbw bytfs of tif pbdkft brf rfbd bnd storfd in tif EvfntSft.
 * Tif EvfntSft is tifn bddfd to fbdi EvfntQufuf. Wifn bn EvfntSft is
 * rfmovfd from bn EvfntQufuf, tif EvfntSftImpl.build() mftiod is dbllfd.
 * Tiis mftiod rfbds tif pbdkft bytfs bnd drfbtfs tif bdtubl EvfntImpl objfdts.
 * build() blso filtfrs out fvfnts for our intfrnbl ibndlfr bnd puts tifm in
 * tifir own EvfntSft.  Tiis mfbns tibt tif EvfntImpls tibt brf in tif EvfntSft
 * tibt is on tif qufufs brf bll for dlifnt rfqufsts.
 */
publid dlbss EvfntSftImpl fxtfnds ArrbyList<Evfnt> implfmfnts EvfntSft {
    privbtf stbtid finbl long sfriblVfrsionUID = -4857338819787924570L;
    privbtf VirtublMbdiinfImpl vm; // wf implfmfnt Mirror
    privbtf Pbdkft pkt;
    privbtf bytf suspfndPolidy;
    privbtf EvfntSftImpl intfrnblEvfntSft;

    publid String toString() {
        String string = "fvfnt sft, polidy:" + suspfndPolidy +
                        ", dount:" + tiis.sizf() + " = {";
        boolfbn first = truf;
        for (Evfnt fvfnt : tiis) {
            if (!first) {
                string += ", ";
            }
            string += fvfnt.toString();
            first = fblsf;
        }
        string += "}";
        rfturn string;
    }

    bbstrbdt dlbss EvfntImpl fxtfnds MirrorImpl implfmfnts Evfnt {

        privbtf finbl bytf fvfntCmd;
        privbtf finbl int rfqufstID;
        // Tiis is sft only for dlifnt rfqufsts, not intfrnbl rfqufsts.
        privbtf finbl EvfntRfqufst rfqufst;

        /**
         * Construdtor for fvfnts.
         */
        protfdtfd EvfntImpl(JDWP.Evfnt.Compositf.Evfnts.EvfntsCommon fvt,
                            int rfqufstID) {
            supfr(EvfntSftImpl.tiis.vm);
            tiis.fvfntCmd = fvt.fvfntKind();
            tiis.rfqufstID = rfqufstID;
            EvfntRfqufstMbnbgfrImpl frmi = EvfntSftImpl.tiis.
                vm.fvfntRfqufstMbnbgfrImpl();
            tiis.rfqufst =  frmi.rfqufst(fvfntCmd, rfqufstID);
        }

        /*
         * Ovfrridf supfrdlbss bbdk to dffbult fqublity
         */
        publid boolfbn fqubls(Objfdt obj) {
            rfturn tiis == obj;
        }

        publid int ibsiCodf() {
            rfturn Systfm.idfntityHbsiCodf(tiis);
        }

        /**
         * Construdtor for VM disdonnfdtfd fvfnts.
         */
        protfdtfd EvfntImpl(bytf fvfntCmd) {
            supfr(EvfntSftImpl.tiis.vm);
            tiis.fvfntCmd = fvfntCmd;
            tiis.rfqufstID = 0;
            tiis.rfqufst = null;
        }

        publid EvfntRfqufst rfqufst() {
            rfturn rfqufst;
        }

        int rfqufstID() {
            rfturn rfqufstID;
        }

        EvfntDfstinbtion dfstinbtion() {
            /*
             * Wf nffd to dfdidf if tiis fvfnt is for
             * 1. bn intfrnbl rfqufst
             * 2. b dlifnt rfqufst tibt is no longfr bvbilbblf, if
             *    it ibs bffn dflftfd, or disbblfd bnd rf-fnbblfd
             *    wiidi givfs it b nfw ID.
             * 3. b durrfnt dlifnt rfqufst tibt is disbblfd
             * 4. b durrfnt fnbblfd dlifnt rfqufst.
             *
             * Wf will filtfr tiis sft into b sft
             * tibt dontbins only 1s for our intfrnbl qufuf
             * bnd b sft tibt dontbins only 4s for our dlifnt qufuf.
             * If wf gft bn EvfntSft tibt dontbins only 2 bnd 3
             * tifn wf ibvf to rfsumf it if it is not SUSPEND_NONE
             * bfdbusf no onf flsf will.
             */
            if (rfqufstID == 0) {
                /* An unsoliditfd fvfnt.  Tifsf ibvf trbditionblly
                 * bffn trfbtfd bs dlifnt fvfnts.
                 */
                rfturn EvfntDfstinbtion.CLIENT_EVENT;
            }

            // Is tiis bn fvfnt for b durrfnt dlifnt rfqufst?
            if (rfqufst == null) {
                // Nopf.  Is it bn fvfnt for bn intfrnbl rfqufst?
                EvfntRfqufstMbnbgfrImpl frmi = tiis.vm.gftIntfrnblEvfntRfqufstMbnbgfr();
                if (frmi.rfqufst(fvfntCmd, rfqufstID) != null) {
                    // Yfp
                    rfturn EvfntDfstinbtion.INTERNAL_EVENT;
                }
                rfturn EvfntDfstinbtion.UNKNOWN_EVENT;
            }

            // Wf found b dlifnt rfqufst
            if (rfqufst.isEnbblfd()) {
                rfturn EvfntDfstinbtion.CLIENT_EVENT;
            }
            rfturn EvfntDfstinbtion.UNKNOWN_EVENT;
        }

        bbstrbdt String fvfntNbmf();

        publid String toString() {
            rfturn fvfntNbmf();
        }

    }

    bbstrbdt dlbss TirfbdfdEvfntImpl fxtfnds EvfntImpl {
        privbtf TirfbdRfffrfndf tirfbd;

        TirfbdfdEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.EvfntsCommon fvt,
                          int rfqufstID, TirfbdRfffrfndf tirfbd) {
            supfr(fvt, rfqufstID);
            tiis.tirfbd = tirfbd;
        }

        publid TirfbdRfffrfndf tirfbd() {
            rfturn tirfbd;
        }

        publid String toString() {
            rfturn fvfntNbmf() + " in tirfbd " + tirfbd.nbmf();
        }
    }

    bbstrbdt dlbss LodbtbblfEvfntImpl fxtfnds TirfbdfdEvfntImpl
                                            implfmfnts Lodbtbblf {
        privbtf Lodbtion lodbtion;

        LodbtbblfEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.EvfntsCommon fvt,
                           int rfqufstID,
                           TirfbdRfffrfndf tirfbd, Lodbtion lodbtion) {
            supfr(fvt, rfqufstID, tirfbd);
            tiis.lodbtion = lodbtion;
        }

        publid Lodbtion lodbtion() {
            rfturn lodbtion;
        }

        /**
         * For MftiodEntry bnd MftiodExit
         */
        publid Mftiod mftiod() {
            rfturn lodbtion.mftiod();
        }

        publid String toString() {
            rfturn fvfntNbmf() + "@" +
                   ((lodbtion() == null) ? " null" : lodbtion().toString()) +
                   " in tirfbd " + tirfbd().nbmf();
        }
    }

    dlbss BrfbkpointEvfntImpl fxtfnds LodbtbblfEvfntImpl
                            implfmfnts BrfbkpointEvfnt {
        BrfbkpointEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.Brfbkpoint fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd, fvt.lodbtion);
        }

        String fvfntNbmf() {
            rfturn "BrfbkpointEvfnt";
        }
    }

    dlbss StfpEvfntImpl fxtfnds LodbtbblfEvfntImpl implfmfnts StfpEvfnt {
        StfpEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.SinglfStfp fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd, fvt.lodbtion);
        }

        String fvfntNbmf() {
            rfturn "StfpEvfnt";
        }
    }

    dlbss MftiodEntryEvfntImpl fxtfnds LodbtbblfEvfntImpl
                            implfmfnts MftiodEntryEvfnt {
        MftiodEntryEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.MftiodEntry fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd, fvt.lodbtion);
        }

        String fvfntNbmf() {
            rfturn "MftiodEntryEvfnt";
        }
    }

    dlbss MftiodExitEvfntImpl fxtfnds LodbtbblfEvfntImpl
                            implfmfnts MftiodExitEvfnt {
        privbtf Vbluf rfturnVbl = null;

        MftiodExitEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.MftiodExit fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd, fvt.lodbtion);
        }

        MftiodExitEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.MftiodExitWitiRfturnVbluf fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd, fvt.lodbtion);
            rfturnVbl = fvt.vbluf;
        }

        String fvfntNbmf() {
            rfturn "MftiodExitEvfnt";
        }

        publid Vbluf rfturnVbluf() {
            if (!tiis.vm.dbnGftMftiodRfturnVblufs()) {
                tirow nfw UnsupportfdOpfrbtionExdfption(
                "tbrgft dofs not support rfturn vblufs in MftiodExit fvfnts");
            }
            rfturn rfturnVbl;
        }

    }

    dlbss MonitorContfndfdEntfrEvfntImpl fxtfnds LodbtbblfEvfntImpl
                            implfmfnts MonitorContfndfdEntfrEvfnt {
        privbtf ObjfdtRfffrfndf monitor = null;

        MonitorContfndfdEntfrEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.MonitorContfndfdEntfr fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd, fvt.lodbtion);
            tiis.monitor = fvt.objfdt;
        }

        String fvfntNbmf() {
            rfturn "MonitorContfndfdEntfr";
        }

        publid ObjfdtRfffrfndf  monitor() {
            rfturn monitor;
        };

    }

    dlbss MonitorContfndfdEntfrfdEvfntImpl fxtfnds LodbtbblfEvfntImpl
                            implfmfnts MonitorContfndfdEntfrfdEvfnt {
        privbtf ObjfdtRfffrfndf monitor = null;

        MonitorContfndfdEntfrfdEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.MonitorContfndfdEntfrfd fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd, fvt.lodbtion);
            tiis.monitor = fvt.objfdt;
        }

        String fvfntNbmf() {
            rfturn "MonitorContfndfdEntfrfd";
        }

        publid ObjfdtRfffrfndf  monitor() {
            rfturn monitor;
        };

    }

    dlbss MonitorWbitEvfntImpl fxtfnds LodbtbblfEvfntImpl
                            implfmfnts MonitorWbitEvfnt {
        privbtf ObjfdtRfffrfndf monitor = null;
        privbtf long timfout;

        MonitorWbitEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.MonitorWbit fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd, fvt.lodbtion);
            tiis.monitor = fvt.objfdt;
            tiis.timfout = fvt.timfout;
        }

        String fvfntNbmf() {
            rfturn "MonitorWbit";
        }

        publid ObjfdtRfffrfndf  monitor() {
            rfturn monitor;
        };

        publid long timfout() {
            rfturn timfout;
        }
    }

    dlbss MonitorWbitfdEvfntImpl fxtfnds LodbtbblfEvfntImpl
                            implfmfnts MonitorWbitfdEvfnt {
        privbtf ObjfdtRfffrfndf monitor = null;
        privbtf boolfbn timfd_out;

        MonitorWbitfdEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.MonitorWbitfd fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd, fvt.lodbtion);
            tiis.monitor = fvt.objfdt;
            tiis.timfd_out = fvt.timfd_out;
        }

        String fvfntNbmf() {
            rfturn "MonitorWbitfd";
        }

        publid ObjfdtRfffrfndf  monitor() {
            rfturn monitor;
        };

        publid boolfbn timfdout() {
            rfturn timfd_out;
        }
    }

    dlbss ClbssPrfpbrfEvfntImpl fxtfnds TirfbdfdEvfntImpl
                            implfmfnts ClbssPrfpbrfEvfnt {
        privbtf RfffrfndfTypf rfffrfndfTypf;

        ClbssPrfpbrfEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.ClbssPrfpbrf fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd);
            rfffrfndfTypf = tiis.vm.rfffrfndfTypf(fvt.typfID, fvt.rffTypfTbg,
                                                  fvt.signbturf);
            ((RfffrfndfTypfImpl)rfffrfndfTypf).sftStbtus(fvt.stbtus);
        }

        publid RfffrfndfTypf rfffrfndfTypf() {
            rfturn rfffrfndfTypf;
        }

        String fvfntNbmf() {
            rfturn "ClbssPrfpbrfEvfnt";
        }
    }

    dlbss ClbssUnlobdEvfntImpl fxtfnds EvfntImpl implfmfnts ClbssUnlobdEvfnt {
        privbtf String dlbssSignbturf;

        ClbssUnlobdEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.ClbssUnlobd fvt) {
            supfr(fvt, fvt.rfqufstID);
            tiis.dlbssSignbturf = fvt.signbturf;
        }

        publid String dlbssNbmf() {
            rfturn dlbssSignbturf.substring(1, dlbssSignbturf.lfngti()-1)
                .rfplbdf('/', '.');
        }

        publid String dlbssSignbturf() {
            rfturn dlbssSignbturf;
        }

        String fvfntNbmf() {
            rfturn "ClbssUnlobdEvfnt";
        }
    }

    dlbss ExdfptionEvfntImpl fxtfnds LodbtbblfEvfntImpl
                                             implfmfnts ExdfptionEvfnt {
        privbtf ObjfdtRfffrfndf fxdfption;
        privbtf Lodbtion dbtdiLodbtion;

        ExdfptionEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.Exdfption fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd, fvt.lodbtion);
            tiis.fxdfption = fvt.fxdfption;
            tiis.dbtdiLodbtion = fvt.dbtdiLodbtion;
        }

        publid ObjfdtRfffrfndf fxdfption() {
            rfturn fxdfption;
        }

        publid Lodbtion dbtdiLodbtion() {
            rfturn dbtdiLodbtion;
        }

        String fvfntNbmf() {
            rfturn "ExdfptionEvfnt";
        }
    }

    dlbss TirfbdDfbtiEvfntImpl fxtfnds TirfbdfdEvfntImpl
                                        implfmfnts TirfbdDfbtiEvfnt {
        TirfbdDfbtiEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.TirfbdDfbti fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd);
        }

        String fvfntNbmf() {
            rfturn "TirfbdDfbtiEvfnt";
        }
    }

    dlbss TirfbdStbrtEvfntImpl fxtfnds TirfbdfdEvfntImpl
                                        implfmfnts TirfbdStbrtEvfnt {
        TirfbdStbrtEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.TirfbdStbrt fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd);
        }

        String fvfntNbmf() {
            rfturn "TirfbdStbrtEvfnt";
        }
    }

    dlbss VMStbrtEvfntImpl fxtfnds TirfbdfdEvfntImpl
                                        implfmfnts VMStbrtEvfnt {
        VMStbrtEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.VMStbrt fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd);
        }

        String fvfntNbmf() {
            rfturn "VMStbrtEvfnt";
        }
    }

    dlbss VMDfbtiEvfntImpl fxtfnds EvfntImpl implfmfnts VMDfbtiEvfnt {

        VMDfbtiEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.VMDfbti fvt) {
            supfr(fvt, fvt.rfqufstID);
        }

        String fvfntNbmf() {
            rfturn "VMDfbtiEvfnt";
        }
    }

    dlbss VMDisdonnfdtEvfntImpl fxtfnds EvfntImpl
                                         implfmfnts VMDisdonnfdtEvfnt {

        VMDisdonnfdtEvfntImpl() {
            supfr((bytf)JDWP.EvfntKind.VM_DISCONNECTED);
        }

        String fvfntNbmf() {
            rfturn "VMDisdonnfdtEvfnt";
        }
    }

    bbstrbdt dlbss WbtdipointEvfntImpl fxtfnds LodbtbblfEvfntImpl
                                            implfmfnts WbtdipointEvfnt {
        privbtf finbl RfffrfndfTypfImpl rffTypf;
        privbtf finbl long fifldID;
        privbtf finbl ObjfdtRfffrfndf objfdt;
        privbtf Fifld fifld = null;

        WbtdipointEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.EvfntsCommon fvt,
                            int rfqufstID,
                            TirfbdRfffrfndf tirfbd, Lodbtion lodbtion,
                            bytf rffTypfTbg, long typfID, long fifldID,
                            ObjfdtRfffrfndf objfdt) {
            supfr(fvt, rfqufstID, tirfbd, lodbtion);
            tiis.rffTypf = tiis.vm.rfffrfndfTypf(typfID, rffTypfTbg);
            tiis.fifldID = fifldID;
            tiis.objfdt = objfdt;
        }

        publid Fifld fifld() {
            if (fifld == null) {
                fifld = rffTypf.gftFifldMirror(fifldID);
            }
            rfturn fifld;
        }

        publid ObjfdtRfffrfndf objfdt() {
            rfturn objfdt;
        }

        publid Vbluf vblufCurrfnt() {
            if (objfdt == null) {
                rfturn rffTypf.gftVbluf(fifld());
            } flsf {
                rfturn objfdt.gftVbluf(fifld());
            }
        }
    }

    dlbss AddfssWbtdipointEvfntImpl fxtfnds WbtdipointEvfntImpl
                                            implfmfnts AddfssWbtdipointEvfnt {

        AddfssWbtdipointEvfntImpl(JDWP.Evfnt.Compositf.Evfnts.FifldAddfss fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd, fvt.lodbtion,
                  fvt.rffTypfTbg, fvt.typfID, fvt.fifldID, fvt.objfdt);
        }

        String fvfntNbmf() {
            rfturn "AddfssWbtdipoint";
        }
    }

    dlbss ModifidbtionWbtdipointEvfntImpl fxtfnds WbtdipointEvfntImpl
                           implfmfnts ModifidbtionWbtdipointEvfnt {
        Vbluf nfwVbluf;

        ModifidbtionWbtdipointEvfntImpl(
                        JDWP.Evfnt.Compositf.Evfnts.FifldModifidbtion fvt) {
            supfr(fvt, fvt.rfqufstID, fvt.tirfbd, fvt.lodbtion,
                  fvt.rffTypfTbg, fvt.typfID, fvt.fifldID, fvt.objfdt);
            tiis.nfwVbluf = fvt.vblufToBf;
        }

        publid Vbluf vblufToBf() {
            rfturn nfwVbluf;
        }

        String fvfntNbmf() {
            rfturn "ModifidbtionWbtdipoint";
        }
    }

    /**
     * Evfnts brf donstrudtfd on tif tirfbd wiidi rfbds bll dbtb from tif
     * trbnsport. Tiis mfbns tibt tif pbdkft dbnnot bf donvfrtfd to rfbl
     * JDI objfdts bs tibt mby involvf furtifr dommunidbtions witi tif
     * bbdk fnd wiidi would dfbdlodk.
     *
     * Hfndf tif {@link #build()} mftiod bflow dbllfd by EvfntQufuf.
     */
    EvfntSftImpl(VirtublMbdiinf bVm, Pbdkft pkt) {
        supfr();

        // From "MirrorImpl":
        // Yfs, its b bit of b ibdk. But by doing it tiis
        // wby, tiis is tif only plbdf wf ibvf to dibngf
        // typing to substitutf b nfw impl.
        vm = (VirtublMbdiinfImpl)bVm;

        tiis.pkt = pkt;
    }

    /**
     * Construdtor for spfdibl fvfnts likf VM disdonnfdtfd
     */
    EvfntSftImpl(VirtublMbdiinf bVm, bytf fvfntCmd) {
        tiis(bVm, null);
        suspfndPolidy = JDWP.SuspfndPolidy.NONE;
        switdi (fvfntCmd) {
            dbsf JDWP.EvfntKind.VM_DISCONNECTED:
                bddEvfnt(nfw VMDisdonnfdtEvfntImpl());
                brfbk;

            dffbult:
                tirow nfw IntfrnblExdfption("Bbd singlfton fvfnt dodf");
        }
    }

    privbtf void bddEvfnt(EvfntImpl fvt) {
        // Notf tibt tiis dlbss ibs b publid bdd mftiod tibt tirows
        // bn fxdfption so tibt dlifnts dbn't modify tif EvfntSft
        supfr.bdd(fvt);
    }

    /*
     * Complftf tif donstrudtion of bn EvfntSft.  Tiis is dbllfd from
     * bn fvfnt ibndlfr tirfbd.  It upbdks tif JDWP fvfnts insidf
     * tif pbdkft bnd drfbtfs EvfntImpls for tifm.  Tif EvfntSft is blrfbdy
     * on EvfntQufufs wifn tiis is dbllfd, so it ibs to bf syndi.
     */
    syndironizfd void build() {
        if (pkt == null) {
            rfturn;
        }
        PbdkftStrfbm ps = nfw PbdkftStrfbm(vm, pkt);
        JDWP.Evfnt.Compositf dompEvt = nfw JDWP.Evfnt.Compositf(vm, ps);
        suspfndPolidy = dompEvt.suspfndPolidy;
        if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_EVENTS) != 0) {
            switdi(suspfndPolidy) {
                dbsf JDWP.SuspfndPolidy.ALL:
                    vm.printTrbdf("EvfntSft: SUSPEND_ALL");
                    brfbk;

                dbsf JDWP.SuspfndPolidy.EVENT_THREAD:
                    vm.printTrbdf("EvfntSft: SUSPEND_EVENT_THREAD");
                    brfbk;

                dbsf JDWP.SuspfndPolidy.NONE:
                    vm.printTrbdf("EvfntSft: SUSPEND_NONE");
                    brfbk;
            }
        }

        TirfbdRfffrfndf fix6485605 = null;
        for (int i = 0; i < dompEvt.fvfnts.lfngti; i++) {
            EvfntImpl fvt = drfbtfEvfnt(dompEvt.fvfnts[i]);
            if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_EVENTS) != 0) {
                try {
                    vm.printTrbdf("Evfnt: " + fvt);
                } dbtdi (VMDisdonnfdtfdExdfption ff) {
                    // ignorf - sff bug 6502716
                }
            }

            switdi (fvt.dfstinbtion()) {
                dbsf UNKNOWN_EVENT:
                    // Ignorf disbblfd, dflftfd, unknown fvfnts, but
                    // sbvf tif tirfbd if tifrf is onf sindf wf migit
                    // ibvf to rfsumf it.  Notf tibt fvfnts for difffrfnt
                    // tirfbds dbn't bf in tif sbmf fvfnt sft.
                    if (fvt instbndfof TirfbdfdEvfntImpl &&
                        suspfndPolidy == JDWP.SuspfndPolidy.EVENT_THREAD) {
                        fix6485605 = ((TirfbdfdEvfntImpl)fvt).tirfbd();
                    }
                    dontinuf;
                dbsf CLIENT_EVENT:
                    bddEvfnt(fvt);
                    brfbk;
                dbsf INTERNAL_EVENT:
                    if (intfrnblEvfntSft == null) {
                        intfrnblEvfntSft = nfw EvfntSftImpl(tiis.vm, null);
                    }
                    intfrnblEvfntSft.bddEvfnt(fvt);
                    brfbk;
                dffbult:
                    tirow nfw IntfrnblExdfption("Invblid fvfnt dfstinbtion");
            }
        }
        pkt = null; // No longfr nffdfd - frff it up

        // Avoid ibngs dfsdribfd in 6296125, 6293795
        if (supfr.sizf() == 0) {
            // Tiis sft ibs no dlifnt fvfnts.  If wf don't do
            // nffdfd rfsumfs, no onf flsf is going to.
            if (suspfndPolidy == JDWP.SuspfndPolidy.ALL) {
                vm.rfsumf();
            } flsf if (suspfndPolidy == JDWP.SuspfndPolidy.EVENT_THREAD) {
                // Sff bug 6485605.
                if (fix6485605 != null) {
                    fix6485605.rfsumf();
                } flsf {
                    // bppbrfntly, tifrf is notiing to rfsumf.
                }
            }
            suspfndPolidy = JDWP.SuspfndPolidy.NONE;

        }

    }

    /**
     * Filtfr out intfrnbl fvfnts
     */
    EvfntSft usfrFiltfr() {
        rfturn tiis;
    }

    /**
     * Filtfr out usfr fvfnts.
     */
    EvfntSft intfrnblFiltfr() {
        rfturn tiis.intfrnblEvfntSft;
    }

    EvfntImpl drfbtfEvfnt(JDWP.Evfnt.Compositf.Evfnts fvt) {
        JDWP.Evfnt.Compositf.Evfnts.EvfntsCommon domm = fvt.bEvfntsCommon;
        switdi (fvt.fvfntKind) {
            dbsf JDWP.EvfntKind.THREAD_START:
                rfturn nfw TirfbdStbrtEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.TirfbdStbrt)domm);

            dbsf JDWP.EvfntKind.THREAD_END:
                rfturn nfw TirfbdDfbtiEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.TirfbdDfbti)domm);

            dbsf JDWP.EvfntKind.EXCEPTION:
                rfturn nfw ExdfptionEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.Exdfption)domm);

            dbsf JDWP.EvfntKind.BREAKPOINT:
                rfturn nfw BrfbkpointEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.Brfbkpoint)domm);

            dbsf JDWP.EvfntKind.METHOD_ENTRY:
                rfturn nfw MftiodEntryEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.MftiodEntry)domm);

            dbsf JDWP.EvfntKind.METHOD_EXIT:
                rfturn nfw MftiodExitEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.MftiodExit)domm);

            dbsf JDWP.EvfntKind.METHOD_EXIT_WITH_RETURN_VALUE:
                rfturn nfw MftiodExitEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.MftiodExitWitiRfturnVbluf)domm);

            dbsf JDWP.EvfntKind.FIELD_ACCESS:
                rfturn nfw AddfssWbtdipointEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.FifldAddfss)domm);

            dbsf JDWP.EvfntKind.FIELD_MODIFICATION:
                rfturn nfw ModifidbtionWbtdipointEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.FifldModifidbtion)domm);

            dbsf JDWP.EvfntKind.SINGLE_STEP:
                rfturn nfw StfpEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.SinglfStfp)domm);

            dbsf JDWP.EvfntKind.CLASS_PREPARE:
                rfturn nfw ClbssPrfpbrfEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.ClbssPrfpbrf)domm);

            dbsf JDWP.EvfntKind.CLASS_UNLOAD:
                rfturn nfw ClbssUnlobdEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.ClbssUnlobd)domm);

            dbsf JDWP.EvfntKind.MONITOR_CONTENDED_ENTER:
                rfturn nfw MonitorContfndfdEntfrEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.MonitorContfndfdEntfr)domm);

            dbsf JDWP.EvfntKind.MONITOR_CONTENDED_ENTERED:
                rfturn nfw MonitorContfndfdEntfrfdEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.MonitorContfndfdEntfrfd)domm);

            dbsf JDWP.EvfntKind.MONITOR_WAIT:
                rfturn nfw MonitorWbitEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.MonitorWbit)domm);

            dbsf JDWP.EvfntKind.MONITOR_WAITED:
                rfturn nfw MonitorWbitfdEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.MonitorWbitfd)domm);

            dbsf JDWP.EvfntKind.VM_START:
                rfturn nfw VMStbrtEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.VMStbrt)domm);

            dbsf JDWP.EvfntKind.VM_DEATH:
                rfturn nfw VMDfbtiEvfntImpl(
                      (JDWP.Evfnt.Compositf.Evfnts.VMDfbti)domm);

            dffbult:
                // Ignorf unknown fvfnt typfs
                Systfm.frr.println("Ignoring fvfnt dmd " +
                                   fvt.fvfntKind + " from tif VM");
                rfturn null;
        }
    }

    publid VirtublMbdiinf virtublMbdiinf() {
        rfturn vm;
    }

    publid int suspfndPolidy() {
        rfturn EvfntRfqufstMbnbgfrImpl.JDWPtoJDISuspfndPolidy(suspfndPolidy);
    }

    privbtf TirfbdRfffrfndf fvfntTirfbd() {
        for (Evfnt fvfnt : tiis) {
            if (fvfnt instbndfof TirfbdfdEvfntImpl) {
                rfturn ((TirfbdfdEvfntImpl)fvfnt).tirfbd();
            }
        }
        rfturn null;
    }

    publid void rfsumf() {
        switdi (suspfndPolidy()) {
            dbsf EvfntRfqufst.SUSPEND_ALL:
                vm.rfsumf();
                brfbk;
            dbsf EvfntRfqufst.SUSPEND_EVENT_THREAD:
                TirfbdRfffrfndf tirfbd = fvfntTirfbd();
                if (tirfbd == null) {
                    tirow nfw IntfrnblExdfption("Indonsistfnt suspfnd polidy");
                }
                tirfbd.rfsumf();
                brfbk;
            dbsf EvfntRfqufst.SUSPEND_NONE:
                // Do notiing
                brfbk;
            dffbult:
                tirow nfw IntfrnblExdfption("Invblid suspfnd polidy");
        }
    }

    publid Itfrbtor<Evfnt> itfrbtor() {
        rfturn nfw Itr();
    }

    publid EvfntItfrbtor fvfntItfrbtor() {
        rfturn nfw Itr();
    }

    publid dlbss Itr implfmfnts EvfntItfrbtor {
        /**
         * Indfx of flfmfnt to bf rfturnfd by subsfqufnt dbll to nfxt.
         */
        int dursor = 0;

        publid boolfbn ibsNfxt() {
            rfturn dursor != sizf();
        }

        publid Evfnt nfxt() {
            try {
                Evfnt nxt = gft(dursor);
                ++dursor;
                rfturn nxt;
            } dbtdi(IndfxOutOfBoundsExdfption f) {
                tirow nfw NoSudiElfmfntExdfption();
            }
        }

        publid Evfnt nfxtEvfnt() {
            rfturn nfxt();
        }

        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    @Ovfrridf
    publid Splitfrbtor<Evfnt> splitfrbtor() {
        rfturn Splitfrbtors.splitfrbtor(tiis, Splitfrbtor.DISTINCT);
    }

    /* bflow mbkf tiis unmodifibblf */

    publid boolfbn bdd(Evfnt o){
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
    publid boolfbn rfmovf(Objfdt o) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
    publid boolfbn bddAll(Collfdtion<? fxtfnds Evfnt> doll) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
    publid boolfbn rfmovfAll(Collfdtion<?> doll) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
    publid boolfbn rftbinAll(Collfdtion<?> doll) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
    publid void dlfbr() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
}
