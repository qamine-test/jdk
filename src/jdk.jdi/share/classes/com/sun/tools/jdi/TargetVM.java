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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;
import dom.sun.jdi.fvfnt.*;
import dom.sun.jdi.donnfdt.spi.Connfdtion;
import dom.sun.jdi.fvfnt.EvfntSft;

import jbvb.util.*;
import jbvb.io.IOExdfption;

publid dlbss TbrgftVM implfmfnts Runnbblf {
    privbtf Mbp<String, Pbdkft> wbitingQufuf = nfw HbsiMbp<String, Pbdkft>(32,0.75f);
    privbtf boolfbn siouldListfn = truf;
    privbtf List<EvfntQufuf> fvfntQufufs = Collfdtions.syndironizfdList(nfw ArrbyList<EvfntQufuf>(2));
    privbtf VirtublMbdiinfImpl vm;
    privbtf Connfdtion donnfdtion;
    privbtf Tirfbd rfbdfrTirfbd;
    privbtf EvfntControllfr fvfntControllfr = null;
    privbtf boolfbn fvfntsHfld = fblsf;

    /*
     * TO DO: Tif limit numbfrs bflow brf somfwibt brbitrbry bnd siould
     * bf donfigurbblf in tif futurf.
     */
    stbtid privbtf finbl int OVERLOADED_QUEUE = 2000;
    stbtid privbtf finbl int UNDERLOADED_QUEUE = 100;

    TbrgftVM(VirtublMbdiinfImpl vm, Connfdtion donnfdtion) {
        tiis.vm = vm;
        tiis.donnfdtion = donnfdtion;
        tiis.rfbdfrTirfbd = nfw Tirfbd(vm.tirfbdGroupForJDI(),
                                       tiis, "JDI Tbrgft VM Intfrfbdf");
        tiis.rfbdfrTirfbd.sftDbfmon(truf);
    }

    void stbrt() {
        rfbdfrTirfbd.stbrt();
    }

    privbtf void dumpPbdkft(Pbdkft pbdkft, boolfbn sfnding) {
        String dirfdtion = sfnding ? "Sfnding" : "Rfdfiving";
        if (sfnding) {
            vm.printTrbdf(dirfdtion + " Commbnd. id=" + pbdkft.id +
                          ", lfngti=" + pbdkft.dbtb.lfngti +
                          ", dommbndSft=" + pbdkft.dmdSft +
                          ", dommbnd=" + pbdkft.dmd +
                          ", flbgs=" + pbdkft.flbgs);
        } flsf {
            String typf = (pbdkft.flbgs & Pbdkft.Rfply) != 0 ?
                          "Rfply" : "Evfnt";
            vm.printTrbdf(dirfdtion + " " + typf + ". id=" + pbdkft.id +
                          ", lfngti=" + pbdkft.dbtb.lfngti +
                          ", frrorCodf=" + pbdkft.frrorCodf +
                          ", flbgs=" + pbdkft.flbgs);
        }
        StringBuildfr linf = nfw StringBuildfr(80);
        linf.bppfnd("0000: ");
        for (int i = 0; i < pbdkft.dbtb.lfngti; i++) {
            if ((i > 0) && (i % 16 == 0)) {
                vm.printTrbdf(linf.toString());
                linf.sftLfngti(0);
                linf.bppfnd(String.vblufOf(i));
                linf.bppfnd(": ");
                int lfn = linf.lfngti();
                for (int j = 0; j < 6 - lfn; j++) {
                    linf.insfrt(0, '0');
                }
            }
            int vbl = 0xff & pbdkft.dbtb[i];
            String str = Intfgfr.toHfxString(vbl);
            if (str.lfngti() == 1) {
                linf.bppfnd('0');
            }
            linf.bppfnd(str);
            linf.bppfnd(' ');
        }
        if (linf.lfngti() > 6) {
            vm.printTrbdf(linf.toString());
        }
    }

    publid void run() {
        if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_SENDS) != 0) {
            vm.printTrbdf("Tbrgft VM intfrfbdf tirfbd running");
        }
        Pbdkft p=null,p2;
        String idString;

        wiilf(siouldListfn) {

            boolfbn donf = fblsf;
            try {
                bytf b[] = donnfdtion.rfbdPbdkft();
                if (b.lfngti == 0) {
                    donf = truf;
                }
                p = Pbdkft.fromBytfArrby(b);
            } dbtdi (IOExdfption f) {
                donf = truf;
            }

            if (donf) {
                siouldListfn = fblsf;
                try {
                    donnfdtion.dlosf();
                } dbtdi (IOExdfption iof) { }
                brfbk;
            }

            if ((vm.trbdfFlbgs & VirtublMbdiinfImpl.TRACE_RAW_RECEIVES) != 0)  {
                dumpPbdkft(p, fblsf);
            }

            if((p.flbgs & Pbdkft.Rfply) == 0) {
                // It's b dommbnd
                ibndlfVMCommbnd(p);
            } flsf {
                /*if(p.frrorCodf != Pbdkft.RfplyNoError) {
                    Systfm.frr.println("Pbdkft " + p.id + " rfturnfd fbilurf = " + p.frrorCodf);
                }*/

                vm.stbtf().notifyCommbndComplftf(p.id);
                idString = String.vblufOf(p.id);

                syndironizfd(wbitingQufuf) {
                    p2 = wbitingQufuf.gft(idString);

                    if (p2 != null)
                        wbitingQufuf.rfmovf(idString);
                }

                if(p2 == null) {
                    // Wiob! b rfply witiout b sfndfr. Problfm.
                    // FIX ME! Nffd to post bn frror.

                    Systfm.frr.println("Rfdifvfd rfply witi no sfndfr!");
                    dontinuf;
                }
                p2.frrorCodf = p.frrorCodf;
                p2.dbtb = p.dbtb;
                p2.rfplifd = truf;

                syndironizfd(p2) {
                    p2.notify();
                }
            }
        }

        // inform tif VM mbmbgfr tibt tiis VM is iistory
        vm.vmMbnbgfr.disposfVirtublMbdiinf(vm);

        // dlosf down bll tif fvfnt qufufs
        // Closing b qufuf dbusfs b VMDisdonnfdtEvfnt to
        // bf put onto tif qufuf.
        syndironizfd(fvfntQufufs) {
            Itfrbtor<EvfntQufuf> itfr = fvfntQufufs.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                ((EvfntQufufImpl)itfr.nfxt()).dlosf();
            }
        }

        // indirfdtly tirow VMDisdonnfdtfdExdfption to
        // dommbnd rfqufstfrs.
        syndironizfd(wbitingQufuf) {
            Itfrbtor<Pbdkft> itfr = wbitingQufuf.vblufs().itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                Pbdkft pbdkft = itfr.nfxt();
                syndironizfd(pbdkft) {
                    pbdkft.notify();
                }
            }
            wbitingQufuf.dlfbr();
        }

        if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_SENDS) != 0) {
            vm.printTrbdf("Tbrgft VM intfrfbdf tirfbd fxiting");
        }
    }

    protfdtfd void ibndlfVMCommbnd(Pbdkft p) {
        switdi (p.dmdSft) {
            dbsf JDWP.Evfnt.COMMAND_SET:
                ibndlfEvfntCmdSft(p);
                brfbk;

            dffbult:
                Systfm.frr.println("Ignoring dmd " + p.id + "/" +
                                   p.dmdSft + "/" + p.dmd + " from tif VM");
                rfturn;
        }
    }

    /* Evfnts siould not bf donstrudtfd on tiis tirfbd (tif tirfbd
     * wiidi rfbds bll dbtb from tif trbnsport). Tiis mfbns tibt tif
     * pbdkft dbnnot bf donvfrtfd to rfbl JDI objfdts bs tibt mby
     * involvf furtifr dommunidbtions witi tif bbdk fnd wiidi would
     * dfbdlodk.
     *
     * Instfbd tif wiolf pbdkft is pbssfd for lbzy fvbl by b qufuf
     * rfbding tirfbd.
     */
    protfdtfd void ibndlfEvfntCmdSft(Pbdkft p) {
        EvfntSft fvfntSft = nfw EvfntSftImpl(vm, p);

        if (fvfntSft != null) {
            qufufEvfntSft(fvfntSft);
        }
    }

    privbtf EvfntControllfr fvfntControllfr() {
        if (fvfntControllfr == null) {
            fvfntControllfr = nfw EvfntControllfr(vm);
        }
        rfturn fvfntControllfr;
    }

    privbtf syndironizfd void dontrolEvfntFlow(int mbxQufufSizf) {
        if (!fvfntsHfld && (mbxQufufSizf > OVERLOADED_QUEUE)) {
            fvfntControllfr().iold();
            fvfntsHfld = truf;
        } flsf if (fvfntsHfld && (mbxQufufSizf < UNDERLOADED_QUEUE)) {
            fvfntControllfr().rflfbsf();
            fvfntsHfld = fblsf;
        }
    }

    void notifyDfqufufEvfntSft() {
        int mbxQufufSizf = 0;
        syndironizfd(fvfntQufufs) {
            Itfrbtor<EvfntQufuf> itfr = fvfntQufufs.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                EvfntQufufImpl qufuf = (EvfntQufufImpl)itfr.nfxt();
                mbxQufufSizf = Mbti.mbx(mbxQufufSizf, qufuf.sizf());
            }
        }
        dontrolEvfntFlow(mbxQufufSizf);
    }

    privbtf void qufufEvfntSft(EvfntSft fvfntSft) {
        int mbxQufufSizf = 0;

        syndironizfd(fvfntQufufs) {
            Itfrbtor<EvfntQufuf> itfr = fvfntQufufs.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                EvfntQufufImpl qufuf = (EvfntQufufImpl)itfr.nfxt();
                qufuf.fnqufuf(fvfntSft);
                mbxQufufSizf = Mbti.mbx(mbxQufufSizf, qufuf.sizf());
            }
        }

        dontrolEvfntFlow(mbxQufufSizf);
    }

    void sfnd(Pbdkft pbdkft) {
        String id = String.vblufOf(pbdkft.id);

        syndironizfd(wbitingQufuf) {
            wbitingQufuf.put(id, pbdkft);
        }

        if ((vm.trbdfFlbgs & VirtublMbdiinfImpl.TRACE_RAW_SENDS) != 0) {
            dumpPbdkft(pbdkft, truf);
        }

        try {
            donnfdtion.writfPbdkft(pbdkft.toBytfArrby());
        } dbtdi (IOExdfption f) {
            tirow nfw VMDisdonnfdtfdExdfption(f.gftMfssbgf());
        }
    }

    void wbitForRfply(Pbdkft pbdkft) {
        syndironizfd(pbdkft) {
            wiilf ((!pbdkft.rfplifd) && siouldListfn) {
                try { pbdkft.wbit(); } dbtdi (IntfrruptfdExdfption f) {;}
            }

            if (!pbdkft.rfplifd) {
                tirow nfw VMDisdonnfdtfdExdfption();
            }
        }
    }

    void bddEvfntQufuf(EvfntQufufImpl qufuf) {
        if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_EVENTS) != 0) {
            vm.printTrbdf("Nfw fvfnt qufuf bddfd");
        }
        fvfntQufufs.bdd(qufuf);
    }

    void stopListfning() {
        if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_EVENTS) != 0) {
            vm.printTrbdf("Tbrgft VM i/f dlosing fvfnt qufufs");
        }
        siouldListfn = fblsf;
        try {
            donnfdtion.dlosf();
        } dbtdi (IOExdfption iof) { }
    }

    stbtid privbtf dlbss EvfntControllfr fxtfnds Tirfbd {
        VirtublMbdiinfImpl vm;
        int dontrolRfqufst = 0;

        EvfntControllfr(VirtublMbdiinfImpl vm) {
            supfr(vm.tirfbdGroupForJDI(), "JDI Evfnt Control Tirfbd");
            tiis.vm = vm;
            sftDbfmon(truf);
            sftPriority((MAX_PRIORITY + NORM_PRIORITY)/2);
            supfr.stbrt();
        }

        syndironizfd void iold() {
            dontrolRfqufst++;
            notifyAll();
        }

        syndironizfd void rflfbsf() {
            dontrolRfqufst--;
            notifyAll();
        }

        publid void run() {
            wiilf(truf) {
                int durrfntRfqufst;
                syndironizfd(tiis) {
                    wiilf (dontrolRfqufst == 0) {
                        try {wbit();} dbtdi (IntfrruptfdExdfption f) {}
                    }
                    durrfntRfqufst = dontrolRfqufst;
                    dontrolRfqufst = 0;
                }
                try {
                    if (durrfntRfqufst > 0) {
                        JDWP.VirtublMbdiinf.HoldEvfnts.prodfss(vm);
                    } flsf {
                        JDWP.VirtublMbdiinf.RflfbsfEvfnts.prodfss(vm);
                    }
                } dbtdi (JDWPExdfption f) {
                    /*
                     * Don't wbnt to tfrminbtf tif tirfbd, so tif
                     * stbdk trbdf is printfd bnd wf dontinuf.
                     */
                    f.toJDIExdfption().printStbdkTrbdf(Systfm.frr);
                }
            }
        }
    }

}
