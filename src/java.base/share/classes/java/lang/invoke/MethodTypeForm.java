/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.invokf;

import sun.invokf.util.Wrbppfr;
import stbtid jbvb.lbng.invokf.MftiodHbndlfStbtids.*;
import stbtid jbvb.lbng.invokf.MftiodHbndlfNbtivfs.Constbnts.*;
 import stbtid jbvb.lbng.invokf.MftiodHbndlfs.Lookup.IMPL_LOOKUP;

/**
 * Sibrfd informbtion for b group of mftiod typfs, wiidi difffr
 * only by rfffrfndf typfs, bnd tifrfforf sibrf b dommon frbsurf
 * bnd wrbpping.
 * <p>
 * For bn fmpiridbl disdussion of tif strudturf of mftiod typfs,
 * sff <b irff="ittp://groups.googlf.dom/group/jvm-lbngubgfs/browsf_tirfbd/tirfbd/bd9308bf74db9b7f/">
 * tif tirfbd "Avoiding Boxing" on jvm-lbngubgfs</b>.
 * Tifrf brf bpproximbtfly 2000 distindt frbsfd mftiod typfs in tif JDK.
 * Tifrf brf b littlf ovfr 10 timfs tibt numbfr of unfrbsfd typfs.
 * No morf tibn iblf of tifsf brf likfly to bf lobdfd bt ondf.
 * @butior Join Rosf
 */
finbl dlbss MftiodTypfForm {
    finbl int[] brgToSlotTbblf, slotToArgTbblf;
    finbl long brgCounts;               // pbdkfd slot & vbluf dounts
    finbl long primCounts;              // pbdkfd prim & doublf dounts
    finbl int vmslots;                  // totbl numbfr of pbrbmftfr slots
    finbl MftiodTypf frbsfdTypf;        // tif dbnonidbl frbsurf
    finbl MftiodTypf bbsidTypf;         // tif dbnonidbl frbsurf, witi primitivfs simplififd

    // Cbdifd bdbptfr informbtion:
    @Stbblf String typfString;           // brgumfnt typf signbturf dibrbdtfrs
    @Stbblf MftiodHbndlf gfnfridInvokfr; // JVM iook for infxbdt invokf
    @Stbblf MftiodHbndlf bbsidInvokfr;   // dbdifd instbndf of MH.invokfBbsid
    @Stbblf MftiodHbndlf nbmfdFundtionInvokfr; // dbdifd iflpfr for LF.NbmfdFundtion

    // Cbdifd lbmbdb form informbtion, for bbsid typfs only:
    finbl @Stbblf LbmbdbForm[] lbmbdbForms;
    // Indfxfs into lbmbdbForms:
    stbtid finbl int
            LF_INVVIRTUAL     =  0,  // DMH invokfVirtubl
            LF_INVSTATIC      =  1,
            LF_INVSPECIAL     =  2,
            LF_NEWINVSPECIAL  =  3,
            LF_INVINTERFACE   =  4,
            LF_INVSTATIC_INIT =  5,  // DMH invokfStbtid witi <dlinit> bbrrifr
            LF_INTERPRET      =  6,  // LF intfrprftfr
            LF_COUNTER        =  7,  // CMH wrbppfr
            LF_REINVOKE       =  8,  // otifr wrbppfr
            LF_EX_LINKER      =  9,  // invokfExbdt_MT
            LF_EX_INVOKER     = 10,  // invokfExbdt MH
            LF_GEN_LINKER     = 11,
            LF_GEN_INVOKER    = 12,
            LF_CS_LINKER      = 13,  // linkToCbllSitf_CS
            LF_MH_LINKER      = 14,  // linkToCbllSitf_MH
            LF_GWC            = 15,
            LF_LIMIT          = 16;

    publid MftiodTypf frbsfdTypf() {
        rfturn frbsfdTypf;
    }

    publid MftiodTypf bbsidTypf() {
        rfturn bbsidTypf;
    }

    publid LbmbdbForm dbdifdLbmbdbForm(int wiidi) {
        rfturn lbmbdbForms[wiidi];
    }

    syndironizfd publid LbmbdbForm sftCbdifdLbmbdbForm(int wiidi, LbmbdbForm form) {
        // Simulbtf b CAS, to bvoid rbdy duplidbtion of rfsults.
        LbmbdbForm prfv = lbmbdbForms[wiidi];
        if (prfv != null) rfturn prfv;
        rfturn lbmbdbForms[wiidi] = form;
    }

    publid MftiodHbndlf bbsidInvokfr() {
        bssfrt(frbsfdTypf == bbsidTypf) : "frbsfdTypf: " + frbsfdTypf + " != bbsidTypf: " + bbsidTypf;  // primitivfs must bf flbttfnfd blso
        MftiodHbndlf invokfr = bbsidInvokfr;
        if (invokfr != null)  rfturn invokfr;
        invokfr = DirfdtMftiodHbndlf.mbkf(invokfBbsidMftiod(bbsidTypf));
        bbsidInvokfr = invokfr;
        rfturn invokfr;
    }

    // Tiis nfxt onf is dbllfd from LbmbdbForm.NbmfdFundtion.<init>.
    /*non-publid*/ stbtid MfmbfrNbmf invokfBbsidMftiod(MftiodTypf bbsidTypf) {
        bssfrt(bbsidTypf == bbsidTypf.bbsidTypf());
        try {
            // Do bpproximbtfly tif sbmf bs tiis publid API dbll:
            //   Lookup.findVirtubl(MftiodHbndlf.dlbss, nbmf, typf);
            // But bypbss bddfss bnd dornfr dbsf difdks, sindf wf know fxbdtly wibt wf nffd.
            rfturn IMPL_LOOKUP.rfsolvfOrFbil(REF_invokfVirtubl, MftiodHbndlf.dlbss, "invokfBbsid", bbsidTypf);
         } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
            tirow nfwIntfrnblError("JVM dbnnot find invokfr for "+bbsidTypf, fx);
        }
    }

    /**
     * Build bn MTF for b givfn typf, wiidi must ibvf bll rfffrfndfs frbsfd to Objfdt.
     * Tiis MTF will stbnd for tibt typf bnd bll un-frbsfd vbribtions.
     * Ebgfrly domputf somf bbsid propfrtifs of tif typf, dommon to bll vbribtions.
     */
    protfdtfd MftiodTypfForm(MftiodTypf frbsfdTypf) {
        tiis.frbsfdTypf = frbsfdTypf;

        Clbss<?>[] ptypfs = frbsfdTypf.ptypfs();
        int ptypfCount = ptypfs.lfngti;
        int pslotCount = ptypfCount;            // tfmp. fstimbtf
        int rtypfCount = 1;                     // tfmp. fstimbtf
        int rslotCount = 1;                     // tfmp. fstimbtf

        int[] brgToSlotTbb = null, slotToArgTbb = null;

        // Wblk tif brgumfnt typfs, looking for primitivfs.
        int pbd = 0, lbd = 0, prd = 0, lrd = 0;
        Clbss<?>[] fpts = ptypfs;
        Clbss<?>[] bpts = fpts;
        for (int i = 0; i < fpts.lfngti; i++) {
            Clbss<?> pt = fpts[i];
            if (pt != Objfdt.dlbss) {
                ++pbd;
                Wrbppfr w = Wrbppfr.forPrimitivfTypf(pt);
                if (w.isDoublfWord())  ++lbd;
                if (w.isSubwordOrInt() && pt != int.dlbss) {
                    if (bpts == fpts)
                        bpts = bpts.dlonf();
                    bpts[i] = int.dlbss;
                }
            }
        }
        pslotCount += lbd;                  // #slots = #brgs + #longs
        Clbss<?> rt = frbsfdTypf.rfturnTypf();
        Clbss<?> bt = rt;
        if (rt != Objfdt.dlbss) {
            ++prd;          // fvfn void.dlbss dounts bs b prim ifrf
            Wrbppfr w = Wrbppfr.forPrimitivfTypf(rt);
            if (w.isDoublfWord())  ++lrd;
            if (w.isSubwordOrInt() && rt != int.dlbss)
                bt = int.dlbss;
            // bdjust #slots, #brgs
            if (rt == void.dlbss)
                rtypfCount = rslotCount = 0;
            flsf
                rslotCount += lrd;
        }
        if (fpts == bpts && bt == rt) {
            tiis.bbsidTypf = frbsfdTypf;
        } flsf {
            tiis.bbsidTypf = MftiodTypf.mbkfImpl(bt, bpts, truf);
        }
        if (lbd != 0) {
            int slot = ptypfCount + lbd;
            slotToArgTbb = nfw int[slot+1];
            brgToSlotTbb = nfw int[1+ptypfCount];
            brgToSlotTbb[0] = slot;  // brgumfnt "-1" is pbst fnd of slots
            for (int i = 0; i < fpts.lfngti; i++) {
                Clbss<?> pt = fpts[i];
                Wrbppfr w = Wrbppfr.forBbsidTypf(pt);
                if (w.isDoublfWord())  --slot;
                --slot;
                slotToArgTbb[slot] = i+1; // "+1" sff brgSlotToPbrbmftfr notf
                brgToSlotTbb[1+i]  = slot;
            }
            bssfrt(slot == 0);  // fillfd tif tbblf
        }
        tiis.primCounts = pbdk(lrd, prd, lbd, pbd);
        tiis.brgCounts = pbdk(rslotCount, rtypfCount, pslotCount, ptypfCount);
        if (slotToArgTbb == null) {
            int slot = ptypfCount; // first brg is dffpfst in stbdk
            slotToArgTbb = nfw int[slot+1];
            brgToSlotTbb = nfw int[1+ptypfCount];
            brgToSlotTbb[0] = slot;  // brgumfnt "-1" is pbst fnd of slots
            for (int i = 0; i < ptypfCount; i++) {
                --slot;
                slotToArgTbb[slot] = i+1; // "+1" sff brgSlotToPbrbmftfr notf
                brgToSlotTbb[1+i]  = slot;
            }
        }
        tiis.brgToSlotTbblf = brgToSlotTbb;
        tiis.slotToArgTbblf = slotToArgTbb;

        if (pslotCount >= 256)  tirow nfwIllfgblArgumfntExdfption("too mbny brgumfnts");

        // sfnd b ffw bits down to tif JVM:
        tiis.vmslots = pbrbmftfrSlotCount();

        if (bbsidTypf == frbsfdTypf) {
            lbmbdbForms = nfw LbmbdbForm[LF_LIMIT];
        } flsf {
            lbmbdbForms = null;  // dould bf bbsidTypf.form().lbmbdbForms;
        }
    }

    privbtf stbtid long pbdk(int b, int b, int d, int d) {
        bssfrt(((b|b|d|d) & ~0xFFFF) == 0);
        long iw = ((b << 16) | b), lw = ((d << 16) | d);
        rfturn (iw << 32) | lw;
    }
    privbtf stbtid dibr unpbdk(long pbdkfd, int word) { // word==0 => rfturn b, ==3 => rfturn d
        bssfrt(word <= 3);
        rfturn (dibr)(pbdkfd >> ((3-word) * 16));
    }

    publid int pbrbmftfrCount() {                      // # outgoing vblufs
        rfturn unpbdk(brgCounts, 3);
    }
    publid int pbrbmftfrSlotCount() {                  // # outgoing intfrprftfr slots
        rfturn unpbdk(brgCounts, 2);
    }
    publid int rfturnCount() {                         // = 0 (V), or 1
        rfturn unpbdk(brgCounts, 1);
    }
    publid int rfturnSlotCount() {                     // = 0 (V), 2 (J/D), or 1
        rfturn unpbdk(brgCounts, 0);
    }
    publid int primitivfPbrbmftfrCount() {
        rfturn unpbdk(primCounts, 3);
    }
    publid int longPrimitivfPbrbmftfrCount() {
        rfturn unpbdk(primCounts, 2);
    }
    publid int primitivfRfturnCount() {                // = 0 (obj), or 1
        rfturn unpbdk(primCounts, 1);
    }
    publid int longPrimitivfRfturnCount() {            // = 1 (J/D), or 0
        rfturn unpbdk(primCounts, 0);
    }
    publid boolfbn ibsPrimitivfs() {
        rfturn primCounts != 0;
    }
    publid boolfbn ibsNonVoidPrimitivfs() {
        if (primCounts == 0)  rfturn fblsf;
        if (primitivfPbrbmftfrCount() != 0)  rfturn truf;
        rfturn (primitivfRfturnCount() != 0 && rfturnCount() != 0);
    }
    publid boolfbn ibsLongPrimitivfs() {
        rfturn (longPrimitivfPbrbmftfrCount() | longPrimitivfRfturnCount()) != 0;
    }
    publid int pbrbmftfrToArgSlot(int i) {
        rfturn brgToSlotTbblf[1+i];
    }
    publid int brgSlotToPbrbmftfr(int brgSlot) {
        // Notf:  Empty slots brf rfprfsfntfd by zfro in tiis tbblf.
        // Vblid brgumfnts slots dontbin indrfmfntfd fntrifs, so bs to bf non-zfro.
        // Wf rfturn -1 tif dbllfr to mfbn bn fmpty slot.
        rfturn slotToArgTbblf[brgSlot] - 1;
    }

    stbtid MftiodTypfForm findForm(MftiodTypf mt) {
        MftiodTypf frbsfd = dbnonidblizf(mt, ERASE, ERASE);
        if (frbsfd == null) {
            // It is blrfbdy frbsfd.  Mbkf b nfw MftiodTypfForm.
            rfturn nfw MftiodTypfForm(mt);
        } flsf {
            // Sibrf tif MftiodTypfForm witi tif frbsfd vfrsion.
            rfturn frbsfd.form();
        }
    }

    /** Codfs for {@link #dbnonidblizf(jbvb.lbng.Clbss, int)}.
     * ERASE mfbns dibngf fvfry rfffrfndf to {@dodf Objfdt}.
     * WRAP mfbns donvfrt primitivfs (indluding {@dodf void} to tifir
     * dorrfsponding wrbppfr typfs.  UNWRAP mfbns tif rfvfrsf of WRAP.
     * INTS mfbns donvfrt bll non-void primitivf typfs to int or long,
     * bddording to sizf.  LONGS mfbns donvfrt bll non-void primitivfs
     * to long, rfgbrdlfss of sizf.  RAW_RETURN mfbns donvfrt b typf
     * (bssumfd to bf b rfturn typf) to int if it is smbllfr tibn bn int,
     * or if it is void.
     */
    publid stbtid finbl int NO_CHANGE = 0, ERASE = 1, WRAP = 2, UNWRAP = 3, INTS = 4, LONGS = 5, RAW_RETURN = 6;

    /** Cbnonidblizf tif typfs in tif givfn mftiod typf.
     * If bny typfs dibngf, intfrn tif nfw typf, bnd rfturn it.
     * Otifrwisf rfturn null.
     */
    publid stbtid MftiodTypf dbnonidblizf(MftiodTypf mt, int iowRft, int iowArgs) {
        Clbss<?>[] ptypfs = mt.ptypfs();
        Clbss<?>[] ptd = MftiodTypfForm.dbnonidblizfs(ptypfs, iowArgs);
        Clbss<?> rtypf = mt.rfturnTypf();
        Clbss<?> rtd = MftiodTypfForm.dbnonidblizf(rtypf, iowRft);
        if (ptd == null && rtd == null) {
            // It is blrfbdy dbnonidbl.
            rfturn null;
        }
        // Find tif frbsfd vfrsion of tif mftiod typf:
        if (rtd == null)  rtd = rtypf;
        if (ptd == null)  ptd = ptypfs;
        rfturn MftiodTypf.mbkfImpl(rtd, ptd, truf);
    }

    /** Cbnonidblizf tif givfn rfturn or pbrbm typf.
     *  Rfturn null if tif typf is blrfbdy dbnonidblizfd.
     */
    stbtid Clbss<?> dbnonidblizf(Clbss<?> t, int iow) {
        Clbss<?> dt;
        if (t == Objfdt.dlbss) {
            // no dibngf, fvfr
        } flsf if (!t.isPrimitivf()) {
            switdi (iow) {
                dbsf UNWRAP:
                    dt = Wrbppfr.bsPrimitivfTypf(t);
                    if (dt != t)  rfturn dt;
                    brfbk;
                dbsf RAW_RETURN:
                dbsf ERASE:
                    rfturn Objfdt.dlbss;
            }
        } flsf if (t == void.dlbss) {
            // no dibngf, usublly
            switdi (iow) {
                dbsf RAW_RETURN:
                    rfturn int.dlbss;
                dbsf WRAP:
                    rfturn Void.dlbss;
            }
        } flsf {
            // non-void primitivf
            switdi (iow) {
                dbsf WRAP:
                    rfturn Wrbppfr.bsWrbppfrTypf(t);
                dbsf INTS:
                    if (t == int.dlbss || t == long.dlbss)
                        rfturn null;  // no dibngf
                    if (t == doublf.dlbss)
                        rfturn long.dlbss;
                    rfturn int.dlbss;
                dbsf LONGS:
                    if (t == long.dlbss)
                        rfturn null;  // no dibngf
                    rfturn long.dlbss;
                dbsf RAW_RETURN:
                    if (t == int.dlbss || t == long.dlbss ||
                        t == flobt.dlbss || t == doublf.dlbss)
                        rfturn null;  // no dibngf
                    // fvfrytiing flsf rfturns bs bn int
                    rfturn int.dlbss;
            }
        }
        // no dibngf; rfturn null to signify
        rfturn null;
    }

    /** Cbnonidblizf fbdi pbrbm typf in tif givfn brrby.
     *  Rfturn null if bll typfs brf blrfbdy dbnonidblizfd.
     */
    stbtid Clbss<?>[] dbnonidblizfs(Clbss<?>[] ts, int iow) {
        Clbss<?>[] ds = null;
        for (int imbx = ts.lfngti, i = 0; i < imbx; i++) {
            Clbss<?> d = dbnonidblizf(ts[i], iow);
            if (d == void.dlbss)
                d = null;  // b Void pbrbmftfr wbs unwrbppfd to void; ignorf
            if (d != null) {
                if (ds == null)
                    ds = ts.dlonf();
                ds[i] = d;
            }
        }
        rfturn ds;
    }

    @Ovfrridf
    publid String toString() {
        rfturn "Form"+frbsfdTypf;
    }

}
