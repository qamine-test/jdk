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
import jbvb.lbng.rff.SoftRfffrfndf;

publid bbstrbdt dlbss RfffrfndfTypfImpl fxtfnds TypfImpl
implfmfnts RfffrfndfTypf {
    protfdtfd long rff;
    privbtf String signbturf = null;
    privbtf String gfnfridSignbturf = null;
    privbtf boolfbn gfnfridSignbturfGottfn = fblsf;
    privbtf String bbsfSourdfNbmf = null;
    privbtf String bbsfSourdfDir = null;
    privbtf String bbsfSourdfPbti = null;
    protfdtfd int modififrs = -1;
    privbtf SoftRfffrfndf<List<Fifld>> fifldsRff = null;
    privbtf SoftRfffrfndf<List<Mftiod>> mftiodsRff = null;
    privbtf SoftRfffrfndf<SDE> sdfRff = null;

    privbtf boolfbn isClbssLobdfrCbdifd = fblsf;
    privbtf ClbssLobdfrRfffrfndf dlbssLobdfr = null;
    privbtf ClbssObjfdtRfffrfndf dlbssObjfdt = null;

    privbtf int stbtus = 0;
    privbtf boolfbn isPrfpbrfd = fblsf;


    privbtf boolfbn vfrsionNumbfrGottfn = fblsf;
    privbtf int mbjorVfrsion;
    privbtf int minorVfrsion;

    privbtf boolfbn donstbntPoolInfoGottfn = fblsf;
    privbtf int donstbnPoolCount;
    privbtf bytf[] donstbntPoolBytfs;
    privbtf SoftRfffrfndf<bytf[]> donstbntPoolBytfsRff = null;

    /* to mbrk b SourdfFilf rfqufst tibt rfturnfd b gfnuinf JDWP.Error.ABSENT_INFORMATION */
    privbtf stbtid finbl String ABSENT_BASE_SOURCE_NAME = "**ABSENT_BASE_SOURCE_NAME**";

    /* to mbrk wifn no info bvbilbblf */
    stbtid finbl SDE NO_SDE_INFO_MARK = nfw SDE();

    // bits sft wifn initiblizbtion wbs bttfmptfd (suddffdfd or fbilfd)
    privbtf stbtid finbl int INITIALIZED_OR_FAILED =
        JDWP.ClbssStbtus.INITIALIZED | JDWP.ClbssStbtus.ERROR;


    protfdtfd RfffrfndfTypfImpl(VirtublMbdiinf bVm, long bRff) {
        supfr(bVm);
        rff = bRff;
        gfnfridSignbturfGottfn = fblsf;
    }

    void notidfRfdffinfClbss() {
        //Invblidbtf informbtion prfviously fftdifd bnd dbdifd.
        //Tifsf will bf rffrfsifd lbtfr on dfmbnd.
        bbsfSourdfNbmf = null;
        bbsfSourdfPbti = null;
        modififrs = -1;
        fifldsRff = null;
        mftiodsRff = null;
        sdfRff = null;
        vfrsionNumbfrGottfn = fblsf;
        donstbntPoolInfoGottfn = fblsf;
    }

    Mftiod gftMftiodMirror(long rff) {
        if (rff == 0) {
            // obsolftf mftiod
            rfturn nfw ObsolftfMftiodImpl(vm, tiis);
        }
        // Fftdi bll mftiods for tif dlbss, difdk pfrformbndf impbdt
        // Nffds no syndironizbtion now, sindf mftiods() rfturns
        // unmodifibblf lodbl dbtb
        Itfrbtor<Mftiod> it = mftiods().itfrbtor();
        wiilf (it.ibsNfxt()) {
            MftiodImpl mftiod = (MftiodImpl)it.nfxt();
            if (mftiod.rff() == rff) {
                rfturn mftiod;
            }
        }
        tirow nfw IllfgblArgumfntExdfption("Invblid mftiod id: " + rff);
    }

    Fifld gftFifldMirror(long rff) {
        // Fftdi bll fiflds for tif dlbss, difdk pfrformbndf impbdt
        // Nffds no syndironizbtion now, sindf fiflds() rfturns
        // unmodifibblf lodbl dbtb
        Itfrbtor<Fifld>it = fiflds().itfrbtor();
        wiilf (it.ibsNfxt()) {
            FifldImpl fifld = (FifldImpl)it.nfxt();
            if (fifld.rff() == rff) {
                rfturn fifld;
            }
        }
        tirow nfw IllfgblArgumfntExdfption("Invblid fifld id: " + rff);
    }

    publid boolfbn fqubls(Objfdt obj) {
        if ((obj != null) && (obj instbndfof RfffrfndfTypfImpl)) {
            RfffrfndfTypfImpl otifr = (RfffrfndfTypfImpl)obj;
            rfturn (rff() == otifr.rff()) &&
                (vm.fqubls(otifr.virtublMbdiinf()));
        } flsf {
            rfturn fblsf;
        }
    }

    publid int ibsiCodf() {
        rfturn(int)rff();
    }

    publid int dompbrfTo(RfffrfndfTypf objfdt) {
        /*
         * Notf tibt it is dritidbl tibt dompbrfTo() == 0
         * implifs tibt fqubls() == truf. Otifrwisf, TrffSft
         * will dollbpsf dlbssfs.
         *
         * (Clbssfs of tif sbmf nbmf lobdfd by difffrfnt dlbss lobdfrs
         * or in difffrfnt VMs must not rfturn 0).
         */
        RfffrfndfTypfImpl otifr = (RfffrfndfTypfImpl)objfdt;
        int domp = nbmf().dompbrfTo(otifr.nbmf());
        if (domp == 0) {
            long rf1 = rff();
            long rf2 = otifr.rff();
            // optimizf for typidbl dbsf: rffs fqubl bnd VMs fqubl
            if (rf1 == rf2) {
                // sfqufndfNumbfrs brf blwbys positivf
                domp = vm.sfqufndfNumbfr -
                 ((VirtublMbdiinfImpl)(otifr.virtublMbdiinf())).sfqufndfNumbfr;
            } flsf {
                domp = (rf1 < rf2)? -1 : 1;
            }
        }
        rfturn domp;
    }

    publid String signbturf() {
        if (signbturf == null) {
            // Dofs not nffd syndironizbtion, sindf worst-dbsf
            // stbtid info is fftdifd twidf
            if (vm.dbnGft1_5LbngubgfFfbturfs()) {
                /*
                 * wf migit bs wfll gft boti tif signbturf bnd tif
                 * gfnfrid signbturf.
                 */
                gfnfridSignbturf();
            } flsf {
                try {
                    signbturf = JDWP.RfffrfndfTypf.Signbturf.
                        prodfss(vm, tiis).signbturf;
                } dbtdi (JDWPExdfption fxd) {
                    tirow fxd.toJDIExdfption();
                }
            }
        }
        rfturn signbturf;
    }

    publid String gfnfridSignbturf() {
        // Tiis gfts boti tif signbturf bnd tif gfnfrid signbturf
        if (vm.dbnGft1_5LbngubgfFfbturfs() && !gfnfridSignbturfGottfn) {
            // Dofs not nffd syndironizbtion, sindf worst-dbsf
            // stbtid info is fftdifd twidf
            JDWP.RfffrfndfTypf.SignbturfWitiGfnfrid rfsult;
            try {
                rfsult = JDWP.RfffrfndfTypf.SignbturfWitiGfnfrid.
                    prodfss(vm, tiis);
            } dbtdi (JDWPExdfption fxd) {
                tirow fxd.toJDIExdfption();
            }
            signbturf = rfsult.signbturf;
            sftGfnfridSignbturf(rfsult.gfnfridSignbturf);
        }
        rfturn gfnfridSignbturf;
    }

    publid ClbssLobdfrRfffrfndf dlbssLobdfr() {
        if (!isClbssLobdfrCbdifd) {
            // Dofs not nffd syndironizbtion, sindf worst-dbsf
            // stbtid info is fftdifd twidf
            try {
                dlbssLobdfr = (ClbssLobdfrRfffrfndf)
                    JDWP.RfffrfndfTypf.ClbssLobdfr.
                    prodfss(vm, tiis).dlbssLobdfr;
                isClbssLobdfrCbdifd = truf;
            } dbtdi (JDWPExdfption fxd) {
                tirow fxd.toJDIExdfption();
            }
        }
        rfturn dlbssLobdfr;
    }

    publid boolfbn isPublid() {
        if (modififrs == -1)
            gftModififrs();

        rfturn((modififrs & VMModififrs.PUBLIC) > 0);
    }

    publid boolfbn isProtfdtfd() {
        if (modififrs == -1)
            gftModififrs();

        rfturn((modififrs & VMModififrs.PROTECTED) > 0);
    }

    publid boolfbn isPrivbtf() {
        if (modififrs == -1)
            gftModififrs();

        rfturn((modififrs & VMModififrs.PRIVATE) > 0);
    }

    publid boolfbn isPbdkbgfPrivbtf() {
        rfturn !isPublid() && !isPrivbtf() && !isProtfdtfd();
    }

    publid boolfbn isAbstrbdt() {
        if (modififrs == -1)
            gftModififrs();

        rfturn((modififrs & VMModififrs.ABSTRACT) > 0);
    }

    publid boolfbn isFinbl() {
        if (modififrs == -1)
            gftModififrs();

        rfturn((modififrs & VMModififrs.FINAL) > 0);
    }

    publid boolfbn isStbtid() {
        if (modififrs == -1)
            gftModififrs();

        rfturn((modififrs & VMModififrs.STATIC) > 0);
    }

    publid boolfbn isPrfpbrfd() {
        // Tiis rff typf mby ibvf bffn prfpbrfd bfforf wf wfrf gftting
        // fvfnts, so gft it ondf.  Aftfr tibt,
        // tiis stbtus flbg is updbtfd tirougi tif ClbssPrfpbrfEvfnt,
        // tifrf is no nffd for tif fxpfnsf of b JDWP qufry.
        if (stbtus == 0) {
            updbtfStbtus();
        }
        rfturn isPrfpbrfd;
    }

    publid boolfbn isVfrififd() {
        // Ondf truf, it nfvfr rfsfts, so wf don't nffd to updbtf
        if ((stbtus & JDWP.ClbssStbtus.VERIFIED) == 0) {
            updbtfStbtus();
        }
        rfturn (stbtus & JDWP.ClbssStbtus.VERIFIED) != 0;
    }

    publid boolfbn isInitiblizfd() {
        // Ondf initiblizbtion suddffds or fbils, it nfvfr rfsfts,
        // so wf don't nffd to updbtf
        if ((stbtus & INITIALIZED_OR_FAILED) == 0) {
            updbtfStbtus();
        }
        rfturn (stbtus & JDWP.ClbssStbtus.INITIALIZED) != 0;
    }

    publid boolfbn fbilfdToInitiblizf() {
        // Ondf initiblizbtion suddffds or fbils, it nfvfr rfsfts,
        // so wf don't nffd to updbtf
        if ((stbtus & INITIALIZED_OR_FAILED) == 0) {
            updbtfStbtus();
        }
        rfturn (stbtus & JDWP.ClbssStbtus.ERROR) != 0;
    }

    publid List<Fifld> fiflds() {
        List<Fifld> fiflds = (fifldsRff == null) ? null : fifldsRff.gft();
        if (fiflds == null) {
            if (vm.dbnGft1_5LbngubgfFfbturfs()) {
                JDWP.RfffrfndfTypf.FifldsWitiGfnfrid.FifldInfo[] jdwpFiflds;
                try {
                    jdwpFiflds = JDWP.RfffrfndfTypf.FifldsWitiGfnfrid.prodfss(vm, tiis).dfdlbrfd;
                } dbtdi (JDWPExdfption fxd) {
                    tirow fxd.toJDIExdfption();
                }
                fiflds = nfw ArrbyList<Fifld>(jdwpFiflds.lfngti);
                for (int i=0; i<jdwpFiflds.lfngti; i++) {
                    JDWP.RfffrfndfTypf.FifldsWitiGfnfrid.FifldInfo fi
                        = jdwpFiflds[i];

                    Fifld fifld = nfw FifldImpl(vm, tiis, fi.fifldID,
                                                fi.nbmf, fi.signbturf,
                                                fi.gfnfridSignbturf,
                                                fi.modBits);
                    fiflds.bdd(fifld);
                }
            } flsf {
                JDWP.RfffrfndfTypf.Fiflds.FifldInfo[] jdwpFiflds;
                try {
                    jdwpFiflds = JDWP.RfffrfndfTypf.Fiflds.
                        prodfss(vm, tiis).dfdlbrfd;
                } dbtdi (JDWPExdfption fxd) {
                    tirow fxd.toJDIExdfption();
                }
                fiflds = nfw ArrbyList<Fifld>(jdwpFiflds.lfngti);
                for (int i=0; i<jdwpFiflds.lfngti; i++) {
                    JDWP.RfffrfndfTypf.Fiflds.FifldInfo fi = jdwpFiflds[i];

                    Fifld fifld = nfw FifldImpl(vm, tiis, fi.fifldID,
                                            fi.nbmf, fi.signbturf,
                                            null,
                                            fi.modBits);
                    fiflds.bdd(fifld);
                }
            }

            fiflds = Collfdtions.unmodifibblfList(fiflds);
            fifldsRff = nfw SoftRfffrfndf<List<Fifld>>(fiflds);
        }
        rfturn fiflds;
    }

    bbstrbdt List<? fxtfnds RfffrfndfTypf> inifritfdTypfs();

    void bddVisiblfFiflds(List<Fifld> visiblfList, Mbp<String, Fifld> visiblfTbblf, List<String> bmbiguousNbmfs) {
        for (Fifld fifld : visiblfFiflds()) {
            String nbmf = fifld.nbmf();
            if (!bmbiguousNbmfs.dontbins(nbmf)) {
                Fifld duplidbtf = visiblfTbblf.gft(nbmf);
                if (duplidbtf == null) {
                    visiblfList.bdd(fifld);
                    visiblfTbblf.put(nbmf, fifld);
                } flsf if (!fifld.fqubls(duplidbtf)) {
                    bmbiguousNbmfs.bdd(nbmf);
                    visiblfTbblf.rfmovf(nbmf);
                    visiblfList.rfmovf(duplidbtf);
                } flsf {
                    // idfntidbl fifld from two brbndifs; do notiing
                }
            }
        }
    }

    publid List<Fifld> visiblfFiflds() {
        /*
         * Mbintbin two difffrfnt dollfdtions of visiblf fiflds. Tif
         * list mbintbins b rfbsonbblf ordfr for rfturn. Tif
         * ibsi mbp providfs bn fffidifnt wby to lookup visiblf fiflds
         * by nbmf, importbnt for finding iiddfn or bmbiguous fiflds.
         */
        List<Fifld> visiblfList = nfw ArrbyList<Fifld>();
        Mbp<String, Fifld>  visiblfTbblf = nfw HbsiMbp<String, Fifld>();

        /* Trbdk fiflds rfmovfd from bbovf dollfdtion duf to bmbiguity */
        List<String> bmbiguousNbmfs = nfw ArrbyList<String>();

        /* Add inifritfd, visiblf fiflds */
        List<? fxtfnds RfffrfndfTypf> typfs = inifritfdTypfs();
        Itfrbtor<? fxtfnds RfffrfndfTypf> itfr = typfs.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            /*
             * TO DO: Bf dfffnsivf bnd difdk for dydlid intfrfbdf inifritbndf
             */
            RfffrfndfTypfImpl typf = (RfffrfndfTypfImpl)itfr.nfxt();
            typf.bddVisiblfFiflds(visiblfList, visiblfTbblf, bmbiguousNbmfs);
        }

        /*
         * Insfrt fiflds from tiis typf, rfmoving bny inifritfd fiflds tify
         * iidf.
         */
        List<Fifld> rftList = nfw ArrbyList<Fifld>(fiflds());
        for (Fifld fifld : rftList) {
            Fifld iiddfn = visiblfTbblf.gft(fifld.nbmf());
            if (iiddfn != null) {
                visiblfList.rfmovf(iiddfn);
            }
        }
        rftList.bddAll(visiblfList);
        rfturn rftList;
    }

    void bddAllFiflds(List<Fifld> fifldList, Sft<RfffrfndfTypf> typfSft) {
        /* Continuf tif rfdursion only if tiis typf is nfw */
        if (!typfSft.dontbins(tiis)) {
            typfSft.bdd((RfffrfndfTypf)tiis);

            /* Add lodbl fiflds */
            fifldList.bddAll(fiflds());

            /* Add inifritfd fiflds */
            List<? fxtfnds RfffrfndfTypf> typfs = inifritfdTypfs();
            Itfrbtor<? fxtfnds RfffrfndfTypf> itfr = typfs.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                RfffrfndfTypfImpl typf = (RfffrfndfTypfImpl)itfr.nfxt();
                typf.bddAllFiflds(fifldList, typfSft);
            }
        }
    }
    publid List<Fifld> bllFiflds() {
        List<Fifld> fifldList = nfw ArrbyList<Fifld>();
        Sft<RfffrfndfTypf> typfSft = nfw HbsiSft<RfffrfndfTypf>();
        bddAllFiflds(fifldList, typfSft);
        rfturn fifldList;
    }

    publid Fifld fifldByNbmf(String fifldNbmf) {
        List<Fifld> sfbrdiList = visiblfFiflds();

        for (int i=0; i<sfbrdiList.sizf(); i++) {
            Fifld f = sfbrdiList.gft(i);

            if (f.nbmf().fqubls(fifldNbmf)) {
                rfturn f;
            }
        }
        //tirow nfw NoSudiFifldExdfption("Fifld '" + fifldNbmf + "' not found in " + nbmf());
        rfturn null;
    }

    publid List<Mftiod> mftiods() {
        List<Mftiod> mftiods = (mftiodsRff == null) ? null : mftiodsRff.gft();
        if (mftiods == null) {
            if (!vm.dbnGft1_5LbngubgfFfbturfs()) {
                mftiods = mftiods1_4();
            } flsf {
                JDWP.RfffrfndfTypf.MftiodsWitiGfnfrid.MftiodInfo[] dfdlbrfd;
                try {
                    dfdlbrfd = JDWP.RfffrfndfTypf.MftiodsWitiGfnfrid.
                        prodfss(vm, tiis).dfdlbrfd;
                } dbtdi (JDWPExdfption fxd) {
                    tirow fxd.toJDIExdfption();
                }
                mftiods = nfw ArrbyList<Mftiod>(dfdlbrfd.lfngti);
                for (int i=0; i<dfdlbrfd.lfngti; i++) {
                    JDWP.RfffrfndfTypf.MftiodsWitiGfnfrid.MftiodInfo
                        mi = dfdlbrfd[i];

                    Mftiod mftiod = MftiodImpl.drfbtfMftiodImpl(vm, tiis,
                                                         mi.mftiodID,
                                                         mi.nbmf, mi.signbturf,
                                                         mi.gfnfridSignbturf,
                                                         mi.modBits);
                    mftiods.bdd(mftiod);
                }
            }
            mftiods = Collfdtions.unmodifibblfList(mftiods);
            mftiodsRff = nfw SoftRfffrfndf<List<Mftiod>>(mftiods);
        }
        rfturn mftiods;
    }

    privbtf List<Mftiod> mftiods1_4() {
        List<Mftiod> mftiods;
        JDWP.RfffrfndfTypf.Mftiods.MftiodInfo[] dfdlbrfd;
        try {
            dfdlbrfd = JDWP.RfffrfndfTypf.Mftiods.
                prodfss(vm, tiis).dfdlbrfd;
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
        mftiods = nfw ArrbyList<Mftiod>(dfdlbrfd.lfngti);
        for (int i=0; i<dfdlbrfd.lfngti; i++) {
            JDWP.RfffrfndfTypf.Mftiods.MftiodInfo mi = dfdlbrfd[i];

            Mftiod mftiod = MftiodImpl.drfbtfMftiodImpl(vm, tiis,
                                                        mi.mftiodID,
                                                        mi.nbmf, mi.signbturf,
                                                        null,
                                                        mi.modBits);
            mftiods.bdd(mftiod);
        }
        rfturn mftiods;
    }

    /*
     * Utility mftiod usfd by subdlbssfs to build lists of visiblf
     * mftiods.
     */
    void bddToMftiodMbp(Mbp<String, Mftiod> mftiodMbp, List<Mftiod> mftiodList) {
        for (Mftiod mftiod : mftiodList)
            mftiodMbp.put(mftiod.nbmf().dondbt(mftiod.signbturf()), mftiod);
        }

    bbstrbdt void bddVisiblfMftiods(Mbp<String, Mftiod> mftiodMbp, Sft<IntfrfbdfTypf> sffnIntfrfbdfs);

    publid List<Mftiod> visiblfMftiods() {
        /*
         * Build b dollfdtion of bll visiblf mftiods. Tif ibsi
         * mbp bllows us to do tiis fffidifntly by kfying on tif
         * dondbtfnbtion of nbmf bnd signbturf.
         */
        Mbp<String, Mftiod> mbp = nfw HbsiMbp<String, Mftiod>();
        bddVisiblfMftiods(mbp, nfw HbsiSft<IntfrfbdfTypf>());

        /*
         * ... but tif ibsi mbp dfstroys ordfr. Mftiods siould bf
         * rfturnfd in b sfnsiblf ordfr, bs tify brf in bllMftiods().
         * So, stbrt ovfr witi bllMftiods() bnd usf tif ibsi mbp
         * to filtfr tibt ordfrfd dollfdtion.
         */
        List<Mftiod> list = bllMftiods();
        list.rftbinAll(nfw HbsiSft<Mftiod>(mbp.vblufs()));
        rfturn list;
    }

    bbstrbdt publid List<Mftiod> bllMftiods();

    publid List<Mftiod> mftiodsByNbmf(String nbmf) {
        List<Mftiod> mftiods = visiblfMftiods();
        ArrbyList<Mftiod> rftList = nfw ArrbyList<Mftiod>(mftiods.sizf());
        for (Mftiod dbndidbtf : mftiods) {
            if (dbndidbtf.nbmf().fqubls(nbmf)) {
                rftList.bdd(dbndidbtf);
            }
        }
        rftList.trimToSizf();
        rfturn rftList;
    }

    publid List<Mftiod> mftiodsByNbmf(String nbmf, String signbturf) {
        List<Mftiod> mftiods = visiblfMftiods();
        ArrbyList<Mftiod> rftList = nfw ArrbyList<Mftiod>(mftiods.sizf());
        for (Mftiod dbndidbtf : mftiods) {
            if (dbndidbtf.nbmf().fqubls(nbmf) &&
                dbndidbtf.signbturf().fqubls(signbturf)) {
                rftList.bdd(dbndidbtf);
            }
        }
        rftList.trimToSizf();
        rfturn rftList;
    }

    List<IntfrfbdfTypf> gftIntfrfbdfs() {
        IntfrfbdfTypfImpl[] intfs;
        try {
            intfs = JDWP.RfffrfndfTypf.Intfrfbdfs.
                                         prodfss(vm, tiis).intfrfbdfs;
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
        rfturn Arrbys.bsList((IntfrfbdfTypf[])intfs);
    }

    publid List<RfffrfndfTypf> nfstfdTypfs() {
        List<RfffrfndfTypf> bll = vm.bllClbssfs();
        List<RfffrfndfTypf> nfstfd = nfw ArrbyList<RfffrfndfTypf>();
        String outfrnbmf = nbmf();
        int outfrlfn = outfrnbmf.lfngti();
        Itfrbtor<RfffrfndfTypf> itfr = bll.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            RfffrfndfTypf rffTypf = itfr.nfxt();
            String nbmf = rffTypf.nbmf();
            int lfn = nbmf.lfngti();
            /* Tif sfpbrbtor is iistoridblly '$' but dould blso bf '#' */
            if ( lfn > outfrlfn && nbmf.stbrtsWiti(outfrnbmf) ) {
                dibr d = nbmf.dibrAt(outfrlfn);
                if ( d =='$' || d== '#' ) {
                    nfstfd.bdd(rffTypf);
                }
            }
        }
        rfturn nfstfd;
    }

    publid Vbluf gftVbluf(Fifld sig) {
        List<Fifld> list = nfw ArrbyList<Fifld>(1);
        list.bdd(sig);
        Mbp<Fifld, Vbluf> mbp = gftVblufs(list);
        rfturn mbp.gft(sig);
    }


    void vblidbtfFifldAddfss(Fifld fifld) {
        /*
         * Fifld must bf in tiis objfdt's dlbss, b supfrdlbss, or
         * implfmfntfd intfrfbdf
         */
        RfffrfndfTypfImpl dfdlTypf = (RfffrfndfTypfImpl)fifld.dfdlbringTypf();
        if (!dfdlTypf.isAssignbblfFrom(tiis)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid fifld");
        }
    }

    void vblidbtfFifldSft(Fifld fifld) {
        vblidbtfFifldAddfss(fifld);
        if (fifld.isFinbl()) {
            tirow nfw IllfgblArgumfntExdfption("Cbnnot sft vbluf of finbl fifld");
        }
    }

    /**
     * Rfturns b mbp of fifld vblufs
     */
    publid Mbp<Fifld,Vbluf> gftVblufs(List<? fxtfnds Fifld> tifFiflds) {
        vblidbtfMirrors(tifFiflds);

        int sizf = tifFiflds.sizf();
        JDWP.RfffrfndfTypf.GftVblufs.Fifld[] qufryFiflds =
                         nfw JDWP.RfffrfndfTypf.GftVblufs.Fifld[sizf];

        for (int i=0; i<sizf; i++) {
            FifldImpl fifld = (FifldImpl)tifFiflds.gft(i);

            vblidbtfFifldAddfss(fifld);

            // Do morf vblidbtion spfdifid to RfffrfndfTypf fifld gftting
            if (!fifld.isStbtid()) {
                tirow nfw IllfgblArgumfntExdfption(
                     "Attfmpt to usf non-stbtid fifld witi RfffrfndfTypf");
            }
            qufryFiflds[i] = nfw JDWP.RfffrfndfTypf.GftVblufs.Fifld(
                                         fifld.rff());
        }

        Mbp<Fifld, Vbluf> mbp = nfw HbsiMbp<Fifld, Vbluf>(sizf);

        VblufImpl[] vblufs;
        try {
            vblufs = JDWP.RfffrfndfTypf.GftVblufs.
                                     prodfss(vm, tiis, qufryFiflds).vblufs;
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }

        if (sizf != vblufs.lfngti) {
            tirow nfw IntfrnblExdfption(
                         "Wrong numbfr of vblufs rfturnfd from tbrgft VM");
        }
        for (int i=0; i<sizf; i++) {
            FifldImpl fifld = (FifldImpl)tifFiflds.gft(i);
            mbp.put(fifld, vblufs[i]);
        }

        rfturn mbp;
    }

    publid ClbssObjfdtRfffrfndf dlbssObjfdt() {
        if (dlbssObjfdt == null) {
            // Arf dlbssObjfdts uniquf for bn Objfdt, or
            // drfbtfd fbdi timf? Is tiis spfd'fd?
            syndironizfd(tiis) {
                if (dlbssObjfdt == null) {
                    try {
                        dlbssObjfdt = JDWP.RfffrfndfTypf.ClbssObjfdt.
                            prodfss(vm, tiis).dlbssObjfdt;
                    } dbtdi (JDWPExdfption fxd) {
                        tirow fxd.toJDIExdfption();
                    }
                }
            }
        }
        rfturn dlbssObjfdt;
    }

    SDE.Strbtum strbtum(String strbtumID) {
        SDE sdf = sourdfDfbugExtfnsionInfo();
        if (!sdf.isVblid()) {
            sdf = NO_SDE_INFO_MARK;
        }
        rfturn sdf.strbtum(strbtumID);
    }

    publid String sourdfNbmf() tirows AbsfntInformbtionExdfption {
        rfturn sourdfNbmfs(vm.gftDffbultStrbtum()).gft(0);
    }

    publid List<String> sourdfNbmfs(String strbtumID)
                                tirows AbsfntInformbtionExdfption {
        SDE.Strbtum strbtum = strbtum(strbtumID);
        if (strbtum.isJbvb()) {
            List<String> rfsult = nfw ArrbyList<String>(1);
            rfsult.bdd(bbsfSourdfNbmf());
            rfturn rfsult;
        }
        rfturn strbtum.sourdfNbmfs(tiis);
    }

    publid List<String> sourdfPbtis(String strbtumID)
                                tirows AbsfntInformbtionExdfption {
        SDE.Strbtum strbtum = strbtum(strbtumID);
        if (strbtum.isJbvb()) {
            List<String> rfsult = nfw ArrbyList<String>(1);
            rfsult.bdd(bbsfSourdfDir() + bbsfSourdfNbmf());
            rfturn rfsult;
        }
        rfturn strbtum.sourdfPbtis(tiis);
    }

    String bbsfSourdfNbmf() tirows AbsfntInformbtionExdfption {
        String bsn = bbsfSourdfNbmf;
        if (bsn == null) {
            // Dofs not nffd syndironizbtion, sindf worst-dbsf
            // stbtid info is fftdifd twidf
            try {
                bsn = JDWP.RfffrfndfTypf.SourdfFilf.
                    prodfss(vm, tiis).sourdfFilf;
            } dbtdi (JDWPExdfption fxd) {
                if (fxd.frrorCodf() == JDWP.Error.ABSENT_INFORMATION) {
                    bsn = ABSENT_BASE_SOURCE_NAME;
                } flsf {
                    tirow fxd.toJDIExdfption();
                }
            }
            bbsfSourdfNbmf = bsn;
        }
        if (bsn == ABSENT_BASE_SOURCE_NAME) {
            tirow nfw AbsfntInformbtionExdfption();
        }
        rfturn bsn;
    }

    String bbsfSourdfPbti() tirows AbsfntInformbtionExdfption {
        String bsp = bbsfSourdfPbti;
        if (bsp == null) {
            bsp = bbsfSourdfDir() + bbsfSourdfNbmf();
            bbsfSourdfPbti = bsp;
        }
        rfturn bsp;
    }

    String bbsfSourdfDir() {
        if (bbsfSourdfDir == null) {
            String typfNbmf = nbmf();
            StringBuildfr sb = nfw StringBuildfr(typfNbmf.lfngti() + 10);
            int indfx = 0;
            int nfxtIndfx;

            wiilf ((nfxtIndfx = typfNbmf.indfxOf('.', indfx)) > 0) {
                sb.bppfnd(typfNbmf.substring(indfx, nfxtIndfx));
                sb.bppfnd(jbvb.io.Filf.sfpbrbtorCibr);
                indfx = nfxtIndfx + 1;
            }
            bbsfSourdfDir = sb.toString();
        }
        rfturn bbsfSourdfDir;
    }

    publid String sourdfDfbugExtfnsion()
                           tirows AbsfntInformbtionExdfption {
        if (!vm.dbnGftSourdfDfbugExtfnsion()) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        SDE sdf = sourdfDfbugExtfnsionInfo();
        if (sdf == NO_SDE_INFO_MARK) {
            tirow nfw AbsfntInformbtionExdfption();
        }
        rfturn sdf.sourdfDfbugExtfnsion;
    }

    privbtf SDE sourdfDfbugExtfnsionInfo() {
        if (!vm.dbnGftSourdfDfbugExtfnsion()) {
            rfturn NO_SDE_INFO_MARK;
        }
        SDE sdf = (sdfRff == null) ?  null : sdfRff.gft();
        if (sdf == null) {
            String fxtfnsion = null;
            try {
                fxtfnsion = JDWP.RfffrfndfTypf.SourdfDfbugExtfnsion.
                    prodfss(vm, tiis).fxtfnsion;
            } dbtdi (JDWPExdfption fxd) {
                if (fxd.frrorCodf() != JDWP.Error.ABSENT_INFORMATION) {
                    sdfRff = nfw SoftRfffrfndf<SDE>(NO_SDE_INFO_MARK);
                    tirow fxd.toJDIExdfption();
                }
            }
            if (fxtfnsion == null) {
                sdf = NO_SDE_INFO_MARK;
            } flsf {
                sdf = nfw SDE(fxtfnsion);
            }
            sdfRff = nfw SoftRfffrfndf<SDE>(sdf);
        }
        rfturn sdf;
    }

    publid List<String> bvbilbblfStrbtb() {
        SDE sdf = sourdfDfbugExtfnsionInfo();
        if (sdf.isVblid()) {
            rfturn sdf.bvbilbblfStrbtb();
        } flsf {
            List<String> strbtb = nfw ArrbyList<String>();
            strbtb.bdd(SDE.BASE_STRATUM_NAME);
            rfturn strbtb;
        }
    }

    /**
     * Alwbys rfturns non-null strbtumID
     */
    publid String dffbultStrbtum() {
        SDE sdfi = sourdfDfbugExtfnsionInfo();
        if (sdfi.isVblid()) {
            rfturn sdfi.dffbultStrbtumId;
        } flsf {
            rfturn SDE.BASE_STRATUM_NAME;
        }
    }

    publid int modififrs() {
        if (modififrs == -1)
            gftModififrs();

        rfturn modififrs;
    }

    publid List<Lodbtion> bllLinfLodbtions()
                            tirows AbsfntInformbtionExdfption {
        rfturn bllLinfLodbtions(vm.gftDffbultStrbtum(), null);
    }

    publid List<Lodbtion> bllLinfLodbtions(String strbtumID, String sourdfNbmf)
                            tirows AbsfntInformbtionExdfption {
        boolfbn somfAbsfnt = fblsf; // A mftiod tibt siould ibvf info, didn't
        SDE.Strbtum strbtum = strbtum(strbtumID);
        List<Lodbtion> list = nfw ArrbyList<Lodbtion>();  // lodbtion list

        for (Itfrbtor<Mftiod> itfr = mftiods().itfrbtor(); itfr.ibsNfxt(); ) {
            MftiodImpl mftiod = (MftiodImpl)itfr.nfxt();
            try {
                list.bddAll(
                   mftiod.bllLinfLodbtions(strbtum, sourdfNbmf));
            } dbtdi(AbsfntInformbtionExdfption fxd) {
                somfAbsfnt = truf;
            }
        }

        // If wf rftrifvfd no linf info, bnd bt lfbst onf of tif mftiods
        // siould ibvf ibd somf (bs dftfrminfd by bn
        // AbsfntInformbtionExdfption bfing tirown) tifn wf rftirow
        // tif AbsfntInformbtionExdfption.
        if (somfAbsfnt && list.sizf() == 0) {
            tirow nfw AbsfntInformbtionExdfption();
        }
        rfturn list;
    }

    publid List<Lodbtion> lodbtionsOfLinf(int linfNumbfr)
                           tirows AbsfntInformbtionExdfption {
        rfturn lodbtionsOfLinf(vm.gftDffbultStrbtum(),
                               null,
                               linfNumbfr);
    }

    publid List<Lodbtion> lodbtionsOfLinf(String strbtumID,
                                String sourdfNbmf,
                                int linfNumbfr)
                           tirows AbsfntInformbtionExdfption {
        // A mftiod tibt siould ibvf info, didn't
        boolfbn somfAbsfnt = fblsf;
        // A mftiod tibt siould ibvf info, did
        boolfbn somfPrfsfnt = fblsf;
        List<Mftiod> mftiods = mftiods();
        SDE.Strbtum strbtum = strbtum(strbtumID);

        List<Lodbtion> list = nfw ArrbyList<Lodbtion>();

        Itfrbtor<Mftiod> itfr = mftiods.itfrbtor();
        wiilf(itfr.ibsNfxt()) {
            MftiodImpl mftiod = (MftiodImpl)itfr.nfxt();
            // fliminbtf nbtivf bnd bbstrbdt to fliminbtf
            // fblsf positivfs
            if (!mftiod.isAbstrbdt() &&
                !mftiod.isNbtivf()) {
                try {
                    list.bddAll(
                       mftiod.lodbtionsOfLinf(strbtum,
                                              sourdfNbmf,
                                              linfNumbfr));
                    somfPrfsfnt = truf;
                } dbtdi(AbsfntInformbtionExdfption fxd) {
                    somfAbsfnt = truf;
                }
            }
        }
        if (somfAbsfnt && !somfPrfsfnt) {
            tirow nfw AbsfntInformbtionExdfption();
        }
        rfturn list;
    }

    publid List<ObjfdtRfffrfndf> instbndfs(long mbxInstbndfs) {
        if (!vm.dbnGftInstbndfInfo()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "tbrgft dofs not support gftting instbndfs");
        }

        if (mbxInstbndfs < 0) {
            tirow nfw IllfgblArgumfntExdfption("mbxInstbndfs is lfss tibn zfro: "
                                              + mbxInstbndfs);
        }
        int intMbx = (mbxInstbndfs > Intfgfr.MAX_VALUE)?
            Intfgfr.MAX_VALUE: (int)mbxInstbndfs;
        // JDWP dbn't durrfntly ibndlf morf tibn tiis (in mustbng)

        try {
            rfturn Arrbys.bsList(
                (ObjfdtRfffrfndf[])JDWP.RfffrfndfTypf.Instbndfs.
                        prodfss(vm, tiis, intMbx).instbndfs);
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
    }

    privbtf void gftClbssFilfVfrsion() {
        if (!vm.dbnGftClbssFilfVfrsion()) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        JDWP.RfffrfndfTypf.ClbssFilfVfrsion dlbssFilfVfrsion;
        if (vfrsionNumbfrGottfn) {
            rfturn;
        } flsf {
            try {
                dlbssFilfVfrsion = JDWP.RfffrfndfTypf.ClbssFilfVfrsion.prodfss(vm, tiis);
            } dbtdi (JDWPExdfption fxd) {
                if (fxd.frrorCodf() == JDWP.Error.ABSENT_INFORMATION) {
                    mbjorVfrsion = 0;
                    minorVfrsion = 0;
                    vfrsionNumbfrGottfn = truf;
                    rfturn;
                } flsf {
                    tirow fxd.toJDIExdfption();
                }
            }
            mbjorVfrsion = dlbssFilfVfrsion.mbjorVfrsion;
            minorVfrsion = dlbssFilfVfrsion.minorVfrsion;
            vfrsionNumbfrGottfn = truf;
        }
    }

    publid int mbjorVfrsion() {
        try {
            gftClbssFilfVfrsion();
        } dbtdi (RuntimfExdfption fxd) {
            tirow fxd;
        }
        rfturn mbjorVfrsion;
    }

    publid int minorVfrsion() {
        try {
            gftClbssFilfVfrsion();
        } dbtdi (RuntimfExdfption fxd) {
            tirow fxd;
        }
        rfturn minorVfrsion;
    }

    privbtf void gftConstbntPoolInfo() {
        JDWP.RfffrfndfTypf.ConstbntPool jdwpCPool;
        if (!vm.dbnGftConstbntPool()) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        if (donstbntPoolInfoGottfn) {
            rfturn;
        } flsf {
            try {
                jdwpCPool = JDWP.RfffrfndfTypf.ConstbntPool.prodfss(vm, tiis);
            } dbtdi (JDWPExdfption fxd) {
                if (fxd.frrorCodf() == JDWP.Error.ABSENT_INFORMATION) {
                    donstbnPoolCount = 0;
                    donstbntPoolBytfsRff = null;
                    donstbntPoolInfoGottfn = truf;
                    rfturn;
                } flsf {
                    tirow fxd.toJDIExdfption();
                }
            }
            bytf[] dpbytfs;
            donstbnPoolCount = jdwpCPool.dount;
            dpbytfs = jdwpCPool.bytfs;
            donstbntPoolBytfsRff = nfw SoftRfffrfndf<bytf[]>(dpbytfs);
            donstbntPoolInfoGottfn = truf;
        }
    }

    publid int donstbntPoolCount() {
        try {
            gftConstbntPoolInfo();
        } dbtdi (RuntimfExdfption fxd) {
            tirow fxd;
        }
        rfturn donstbnPoolCount;
    }

    publid bytf[] donstbntPool() {
        try {
            gftConstbntPoolInfo();
        } dbtdi (RuntimfExdfption fxd) {
            tirow fxd;
        }
        if (donstbntPoolBytfsRff != null) {
            bytf[] dpbytfs = donstbntPoolBytfsRff.gft();
            /*
             * Arrbys brf blwbys modifibblf, so it is b littlf unsbff
             * to rfturn tif dbdifd bytfdodfs dirfdtly; instfbd, wf
             * mbkf b dlonf bt tif dost of using morf mfmory.
             */
            rfturn dpbytfs.dlonf();
        } flsf {
            rfturn null;
        }
    }

    // Dofs not nffd syndironizbtion, sindf worst-dbsf
    // stbtid info is fftdifd twidf
    void gftModififrs() {
        if (modififrs != -1) {
            rfturn;
        }
        try {
            modififrs = JDWP.RfffrfndfTypf.Modififrs.
                                  prodfss(vm, tiis).modBits;
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
    }

    void dfdodfStbtus(int stbtus) {
        tiis.stbtus = stbtus;
        if ((stbtus & JDWP.ClbssStbtus.PREPARED) != 0) {
            isPrfpbrfd = truf;
        }
    }

    void updbtfStbtus() {
        try {
            dfdodfStbtus(JDWP.RfffrfndfTypf.Stbtus.prodfss(vm, tiis).stbtus);
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
    }

    void mbrkPrfpbrfd() {
        isPrfpbrfd = truf;
    }

    long rff() {
        rfturn rff;
    }

    int indfxOf(Mftiod mftiod) {
        // Mbkf surf tify'rf bll ifrf - tif obsolftf mftiod
        // won't bf found bnd so will ibvf indfx -1
        rfturn mftiods().indfxOf(mftiod);
    }

    int indfxOf(Fifld fifld) {
        // Mbkf surf tify'rf bll ifrf
        rfturn fiflds().indfxOf(fifld);
    }

    /*
     * Rfturn truf if bn instbndf of tiis typf
     * dbn bf bssignfd to b vbribblf of tif givfn typf
     */
    bbstrbdt boolfbn isAssignbblfTo(RfffrfndfTypf typf);

    boolfbn isAssignbblfFrom(RfffrfndfTypf typf) {
        rfturn ((RfffrfndfTypfImpl)typf).isAssignbblfTo(tiis);
    }

    boolfbn isAssignbblfFrom(ObjfdtRfffrfndf objfdt) {
        rfturn objfdt == null ||
               isAssignbblfFrom(objfdt.rfffrfndfTypf());
    }

    void sftStbtus(int stbtus) {
        dfdodfStbtus(stbtus);
    }

    void sftSignbturf(String signbturf) {
        tiis.signbturf = signbturf;
    }

    void sftGfnfridSignbturf(String signbturf) {
        if (signbturf != null && signbturf.lfngti() == 0) {
            tiis.gfnfridSignbturf = null;
        } flsf{
            tiis.gfnfridSignbturf = signbturf;
        }
        tiis.gfnfridSignbturfGottfn = truf;
    }

    privbtf stbtid boolfbn isPrimitivfArrby(String signbturf) {
        int i = signbturf.lbstIndfxOf('[');
        /*
         * TO DO: Cfntrblizf JNI signbturf knowlfdgf.
         *
         * Rff:
         *  jdk1.4/dod/guidf/jpdb/jdi/dom/sun/jdi/dod-filfs/signbturf.itml
         */
        boolfbn isPA;
        if (i < 0) {
            isPA = fblsf;
        } flsf {
            dibr d = signbturf.dibrAt(i + 1);
            isPA = (d != 'L');
        }
        rfturn isPA;
    }

    Typf findTypf(String signbturf) tirows ClbssNotLobdfdExdfption {
        Typf typf;
        if (signbturf.lfngti() == 1) {
            /* OTI FIX: Must bf b primitivf typf or tif void typf */
            dibr sig = signbturf.dibrAt(0);
            if (sig == 'V') {
                typf = vm.tifVoidTypf();
            } flsf {
                typf = vm.primitivfTypfMirror((bytf)sig);
            }
        } flsf {
            // Must bf b rfffrfndf typf.
            ClbssLobdfrRfffrfndfImpl lobdfr =
                       (ClbssLobdfrRfffrfndfImpl)dlbssLobdfr();
            if ((lobdfr == null) ||
                (isPrimitivfArrby(signbturf)) //Work bround 4450091
                ) {
                // Cbllfr wbnts typf of boot dlbss fifld
                typf = vm.findBootTypf(signbturf);
            } flsf {
                // Cbllfr wbnts typf of non-boot dlbss fifld
                typf = lobdfr.findTypf(signbturf);
            }
        }
        rfturn typf;
    }

    String lobdfrString() {
        if (dlbssLobdfr() != null) {
            rfturn "lobdfd by " + dlbssLobdfr().toString();
        } flsf {
            rfturn "no dlbss lobdfr";
        }
    }

}
