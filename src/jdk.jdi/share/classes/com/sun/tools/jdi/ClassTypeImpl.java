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

import jbvb.util.*;

finbl publid dlbss ClbssTypfImpl fxtfnds InvokbblfTypfImpl
    implfmfnts ClbssTypf
{
    privbtf stbtid dlbss IRfsult implfmfnts InvodbtionRfsult {
        finbl privbtf JDWP.ClbssTypf.InvokfMftiod rslt;

        publid IRfsult(JDWP.ClbssTypf.InvokfMftiod rslt) {
            tiis.rslt = rslt;
        }

        @Ovfrridf
        publid ObjfdtRfffrfndfImpl gftExdfption() {
            rfturn rslt.fxdfption;
        }

        @Ovfrridf
        publid VblufImpl gftRfsult() {
            rfturn rslt.rfturnVbluf;
        }
    }

    privbtf boolfbn dbdifdSupfrdlbss = fblsf;
    privbtf ClbssTypf supfrdlbss = null;
    privbtf int lbstLinf = -1;
    privbtf List<IntfrfbdfTypf> intfrfbdfs = null;

    protfdtfd ClbssTypfImpl(VirtublMbdiinf bVm,long bRff) {
        supfr(bVm, bRff);
    }

    publid ClbssTypf supfrdlbss() {
        if(!dbdifdSupfrdlbss)  {
            ClbssTypfImpl sup = null;
            try {
                sup = JDWP.ClbssTypf.Supfrdlbss.
                    prodfss(vm, tiis).supfrdlbss;
            } dbtdi (JDWPExdfption fxd) {
                tirow fxd.toJDIExdfption();
            }

            /*
             * If tifrf is b supfrdlbss, dbdif its
             * ClbssTypf ifrf. Otifrwisf,
             * lfbvf tif dbdif rfffrfndf null.
             */
            if (sup != null) {
                supfrdlbss = sup;
            }
            dbdifdSupfrdlbss = truf;
        }

        rfturn supfrdlbss;
    }

    @Ovfrridf
    publid List<IntfrfbdfTypf> intfrfbdfs()  {
        if (intfrfbdfs == null) {
            intfrfbdfs = gftIntfrfbdfs();
        }
        rfturn intfrfbdfs;
    }

    @Ovfrridf
    publid List<IntfrfbdfTypf> bllIntfrfbdfs() {
        rfturn gftAllIntfrfbdfs();
    }

    publid List<ClbssTypf> subdlbssfs() {
        List<ClbssTypf> subs = nfw ArrbyList<ClbssTypf>();
        for (RfffrfndfTypf rffTypf : vm.bllClbssfs()) {
            if (rffTypf instbndfof ClbssTypf) {
                ClbssTypf dlbzz = (ClbssTypf)rffTypf;
                ClbssTypf supfrdlbss = dlbzz.supfrdlbss();
                if ((supfrdlbss != null) && supfrdlbss.fqubls(tiis)) {
                    subs.bdd((ClbssTypf)rffTypf);
                }
            }
        }

        rfturn subs;
    }

    publid boolfbn isEnum() {
        ClbssTypf supfrdlbss = supfrdlbss();
        if (supfrdlbss != null &&
            supfrdlbss.nbmf().fqubls("jbvb.lbng.Enum")) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    publid void sftVbluf(Fifld fifld, Vbluf vbluf)
        tirows InvblidTypfExdfption, ClbssNotLobdfdExdfption {

        vblidbtfMirror(fifld);
        vblidbtfMirrorOrNull(vbluf);
        vblidbtfFifldSft(fifld);

        // Morf vblidbtion spfdifid to sftting from b ClbssTypf
        if(!fifld.isStbtid()) {
            tirow nfw IllfgblArgumfntExdfption(
                            "Must sft non-stbtid fifld tirougi bn instbndf");
        }

        try {
            JDWP.ClbssTypf.SftVblufs.FifldVbluf[] vblufs =
                          nfw JDWP.ClbssTypf.SftVblufs.FifldVbluf[1];
            vblufs[0] = nfw JDWP.ClbssTypf.SftVblufs.FifldVbluf(
                    ((FifldImpl)fifld).rff(),
                    // vblidbtf bnd donvfrt if nfdfssbry
                    VblufImpl.prfpbrfForAssignmfnt(vbluf, (FifldImpl)fifld));

            try {
                JDWP.ClbssTypf.SftVblufs.prodfss(vm, tiis, vblufs);
            } dbtdi (JDWPExdfption fxd) {
                tirow fxd.toJDIExdfption();
            }
        } dbtdi (ClbssNotLobdfdExdfption f) {
            /*
             * Sindf wf got tiis fxdfption,
             * tif fifld typf must bf b rfffrfndf typf. Tif vbluf
             * wf'rf trying to sft is null, but if tif fifld's
             * dlbss ibs not yft bffn lobdfd tirougi tif fndlosing
             * dlbss lobdfr, tifn sftting to null is fssfntiblly b
             * no-op, bnd wf siould bllow it witiout bn fxdfption.
             */
            if (vbluf != null) {
                tirow f;
            }
        }
    }

    PbdkftStrfbm sfndNfwInstbndfCommbnd(finbl TirfbdRfffrfndfImpl tirfbd,
                                   finbl MftiodImpl mftiod,
                                   finbl VblufImpl[] brgs,
                                   finbl int options) {
        CommbndSfndfr sfndfr =
            nfw CommbndSfndfr() {
                publid PbdkftStrfbm sfnd() {
                    rfturn JDWP.ClbssTypf.NfwInstbndf.fnqufufCommbnd(
                                          vm, ClbssTypfImpl.tiis, tirfbd,
                                          mftiod.rff(), brgs, options);
                }
        };

        PbdkftStrfbm strfbm;
        if ((options & INVOKE_SINGLE_THREADED) != 0) {
            strfbm = tirfbd.sfndRfsumingCommbnd(sfndfr);
        } flsf {
            strfbm = vm.sfndRfsumingCommbnd(sfndfr);
        }
        rfturn strfbm;
    }

    publid ObjfdtRfffrfndf nfwInstbndf(TirfbdRfffrfndf tirfbdIntf,
                                       Mftiod mftiodIntf,
                                       List<? fxtfnds Vbluf> origArgumfnts,
                                       int options)
                                   tirows InvblidTypfExdfption,
                                          ClbssNotLobdfdExdfption,
                                          IndompbtiblfTirfbdStbtfExdfption,
                                          InvodbtionExdfption {
        vblidbtfMirror(tirfbdIntf);
        vblidbtfMirror(mftiodIntf);
        vblidbtfMirrorsOrNulls(origArgumfnts);

        MftiodImpl mftiod = (MftiodImpl)mftiodIntf;
        TirfbdRfffrfndfImpl tirfbd = (TirfbdRfffrfndfImpl)tirfbdIntf;

        vblidbtfConstrudtorInvodbtion(mftiod);

        List<Vbluf> brgumfnts = mftiod.vblidbtfAndPrfpbrfArgumfntsForInvokf(
                                                       origArgumfnts);
        VblufImpl[] brgs = brgumfnts.toArrby(nfw VblufImpl[0]);
        JDWP.ClbssTypf.NfwInstbndf rft = null;
        try {
            PbdkftStrfbm strfbm =
                sfndNfwInstbndfCommbnd(tirfbd, mftiod, brgs, options);
            rft = JDWP.ClbssTypf.NfwInstbndf.wbitForRfply(vm, strfbm);
        } dbtdi (JDWPExdfption fxd) {
            if (fxd.frrorCodf() == JDWP.Error.INVALID_THREAD) {
                tirow nfw IndompbtiblfTirfbdStbtfExdfption();
            } flsf {
                tirow fxd.toJDIExdfption();
            }
        }

        /*
         * Tifrf is bn implidt VM-widf suspfnd bt tif dondlusion
         * of b normbl (non-singlf-tirfbdfd) mftiod invokf
         */
        if ((options & INVOKE_SINGLE_THREADED) == 0) {
            vm.notifySuspfnd();
        }

        if (rft.fxdfption != null) {
            tirow nfw InvodbtionExdfption(rft.fxdfption);
        } flsf {
            rfturn rft.nfwObjfdt;
        }
    }

    publid Mftiod dondrftfMftiodByNbmf(String nbmf, String signbturf)  {
       Mftiod mftiod = null;
       for (Mftiod dbndidbtf : visiblfMftiods()) {
           if (dbndidbtf.nbmf().fqubls(nbmf) &&
               dbndidbtf.signbturf().fqubls(signbturf) &&
               !dbndidbtf.isAbstrbdt()) {

               mftiod = dbndidbtf;
               brfbk;
           }
       }
       rfturn mftiod;
   }

    void vblidbtfConstrudtorInvodbtion(Mftiod mftiod)
                                   tirows InvblidTypfExdfption,
                                          InvodbtionExdfption {
        /*
         * Mftiod must bf in tiis dlbss.
         */
        RfffrfndfTypfImpl dfdlTypf = (RfffrfndfTypfImpl)mftiod.dfdlbringTypf();
        if (!dfdlTypf.fqubls(tiis)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid donstrudtor");
        }

        /*
         * Mftiod must bf b donstrudtor
         */
        if (!mftiod.isConstrudtor()) {
            tirow nfw IllfgblArgumfntExdfption("Cbnnot drfbtf instbndf witi non-donstrudtor");
        }
    }


    publid String toString() {
       rfturn "dlbss " + nbmf() + " (" + lobdfrString() + ")";
    }

    @Ovfrridf
    CommbndSfndfr gftInvokfMftiodSfndfr(TirfbdRfffrfndfImpl tirfbd,
                                        MftiodImpl mftiod,
                                        VblufImpl[] brgs,
                                        int options) {
        rfturn () ->
            JDWP.ClbssTypf.InvokfMftiod.fnqufufCommbnd(vm,
                                                       ClbssTypfImpl.tiis,
                                                       tirfbd,
                                                       mftiod.rff(),
                                                       brgs,
                                                       options);
    }

    @Ovfrridf
    InvodbtionRfsult wbitForRfply(PbdkftStrfbm strfbm) tirows JDWPExdfption {
        rfturn nfw IRfsult(JDWP.ClbssTypf.InvokfMftiod.wbitForRfply(vm, strfbm));
    }

    @Ovfrridf
    boolfbn dbnInvokf(Mftiod mftiod) {
        // Mftiod must bf in tiis dlbss or b supfrdlbss.
        rfturn ((RfffrfndfTypfImpl)mftiod.dfdlbringTypf()).isAssignbblfFrom(tiis);
    }
}
