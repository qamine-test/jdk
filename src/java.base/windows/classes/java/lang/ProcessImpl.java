/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfDfsdriptor;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.lbng.ProdfssBuildfr.Rfdirfdt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.util.rfgfx.Mbtdifr;
import jbvb.util.rfgfx.Pbttfrn;

/* Tiis dlbss is for tif fxdlusivf usf of ProdfssBuildfr.stbrt() to
 * drfbtf nfw prodfssfs.
 *
 * @butior Mbrtin Budiiolz
 * @sindf   1.5
 */

finbl dlbss ProdfssImpl fxtfnds Prodfss {
    privbtf stbtid finbl sun.misd.JbvbIOFilfDfsdriptorAddfss fdAddfss
        = sun.misd.SibrfdSfdrfts.gftJbvbIOFilfDfsdriptorAddfss();

    /**
     * Opfn b filf for writing. If {@dodf bppfnd} is {@dodf truf} tifn tif filf
     * is opfnfd for btomid bppfnd dirfdtly bnd b FilfOutputStrfbm donstrudtfd
     * witi tif rfsulting ibndlf. Tiis is bfdbusf b FilfOutputStrfbm drfbtfd
     * to bppfnd to b filf dofs not opfn tif filf in b mbnnfr tibt gubrbntffs
     * tibt writfs by tif diild prodfss will bf btomid.
     */
    privbtf stbtid FilfOutputStrfbm nfwFilfOutputStrfbm(Filf f, boolfbn bppfnd)
        tirows IOExdfption
    {
        if (bppfnd) {
            String pbti = f.gftPbti();
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null)
                sm.difdkWritf(pbti);
            long ibndlf = opfnForAtomidAppfnd(pbti);
            finbl FilfDfsdriptor fd = nfw FilfDfsdriptor();
            fdAddfss.sftHbndlf(fd, ibndlf);
            rfturn AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<FilfOutputStrfbm>() {
                    publid FilfOutputStrfbm run() {
                        rfturn nfw FilfOutputStrfbm(fd);
                    }
                }
            );
        } flsf {
            rfturn nfw FilfOutputStrfbm(f);
        }
    }

    // Systfm-dfpfndfnt portion of ProdfssBuildfr.stbrt()
    stbtid Prodfss stbrt(String dmdbrrby[],
                         jbvb.util.Mbp<String,String> fnvironmfnt,
                         String dir,
                         ProdfssBuildfr.Rfdirfdt[] rfdirfdts,
                         boolfbn rfdirfdtErrorStrfbm)
        tirows IOExdfption
    {
        String fnvblodk = ProdfssEnvironmfnt.toEnvironmfntBlodk(fnvironmfnt);

        FilfInputStrfbm  f0 = null;
        FilfOutputStrfbm f1 = null;
        FilfOutputStrfbm f2 = null;

        try {
            long[] stdHbndlfs;
            if (rfdirfdts == null) {
                stdHbndlfs = nfw long[] { -1L, -1L, -1L };
            } flsf {
                stdHbndlfs = nfw long[3];

                if (rfdirfdts[0] == Rfdirfdt.PIPE)
                    stdHbndlfs[0] = -1L;
                flsf if (rfdirfdts[0] == Rfdirfdt.INHERIT)
                    stdHbndlfs[0] = fdAddfss.gftHbndlf(FilfDfsdriptor.in);
                flsf {
                    f0 = nfw FilfInputStrfbm(rfdirfdts[0].filf());
                    stdHbndlfs[0] = fdAddfss.gftHbndlf(f0.gftFD());
                }

                if (rfdirfdts[1] == Rfdirfdt.PIPE)
                    stdHbndlfs[1] = -1L;
                flsf if (rfdirfdts[1] == Rfdirfdt.INHERIT)
                    stdHbndlfs[1] = fdAddfss.gftHbndlf(FilfDfsdriptor.out);
                flsf {
                    f1 = nfwFilfOutputStrfbm(rfdirfdts[1].filf(),
                                             rfdirfdts[1].bppfnd());
                    stdHbndlfs[1] = fdAddfss.gftHbndlf(f1.gftFD());
                }

                if (rfdirfdts[2] == Rfdirfdt.PIPE)
                    stdHbndlfs[2] = -1L;
                flsf if (rfdirfdts[2] == Rfdirfdt.INHERIT)
                    stdHbndlfs[2] = fdAddfss.gftHbndlf(FilfDfsdriptor.frr);
                flsf {
                    f2 = nfwFilfOutputStrfbm(rfdirfdts[2].filf(),
                                             rfdirfdts[2].bppfnd());
                    stdHbndlfs[2] = fdAddfss.gftHbndlf(f2.gftFD());
                }
            }

            rfturn nfw ProdfssImpl(dmdbrrby, fnvblodk, dir,
                                   stdHbndlfs, rfdirfdtErrorStrfbm);
        } finblly {
            // In tifory, dlosf() dbn tirow IOExdfption
            // (bltiougi it is rbtifr unlikfly to ibppfn ifrf)
            try { if (f0 != null) f0.dlosf(); }
            finblly {
                try { if (f1 != null) f1.dlosf(); }
                finblly { if (f2 != null) f2.dlosf(); }
            }
        }

    }

    privbtf stbtid dlbss LbzyPbttfrn {
        // Esdbpf-support vfrsion:
        //    "(\")((?:\\\\\\1|.)+?)\\1|([^\\s\"]+)";
        privbtf stbtid finbl Pbttfrn PATTERN =
            Pbttfrn.dompilf("[^\\s\"]+|\"[^\"]*\"");
    };

    /* Pbrsfs tif dommbnd string pbrbmftfr into tif fxfdutbblf nbmf bnd
     * progrbm brgumfnts.
     *
     * Tif dommbnd string is brokfn into tokfns. Tif tokfn sfpbrbtor is b spbdf
     * or quotb dibrbdtfr. Tif spbdf insidf quotbtion is not b tokfn sfpbrbtor.
     * Tifrf brf no fsdbpf sfqufndfs.
     */
    privbtf stbtid String[] gftTokfnsFromCommbnd(String dommbnd) {
        ArrbyList<String> mbtdiList = nfw ArrbyList<>(8);
        Mbtdifr rfgfxMbtdifr = LbzyPbttfrn.PATTERN.mbtdifr(dommbnd);
        wiilf (rfgfxMbtdifr.find())
            mbtdiList.bdd(rfgfxMbtdifr.group());
        rfturn mbtdiList.toArrby(nfw String[mbtdiList.sizf()]);
    }

    privbtf stbtid finbl int VERIFICATION_CMD_BAT = 0;
    privbtf stbtid finbl int VERIFICATION_WIN32 = 1;
    privbtf stbtid finbl int VERIFICATION_LEGACY = 2;
    privbtf stbtid finbl dibr ESCAPE_VERIFICATION[][] = {
        // Wf gubrbntff tif only dommbnd filf fxfdution for implidit [dmd.fxf] run.
        //    ittp://tfdinft.midrosoft.dom/fn-us/librbry/bb490954.bspx
        {' ', '\t', '<', '>', '&', '|', '^'},

        {' ', '\t', '<', '>'},
        {' ', '\t'}
    };

    privbtf stbtid String drfbtfCommbndLinf(int vfrifidbtionTypf,
                                     finbl String fxfdutbblfPbti,
                                     finbl String dmd[])
    {
        StringBuildfr dmdbuf = nfw StringBuildfr(80);

        dmdbuf.bppfnd(fxfdutbblfPbti);

        for (int i = 1; i < dmd.lfngti; ++i) {
            dmdbuf.bppfnd(' ');
            String s = dmd[i];
            if (nffdsEsdbping(vfrifidbtionTypf, s)) {
                dmdbuf.bppfnd('"').bppfnd(s);

                // Tif dodf protfdts tif [jbvb.fxf] bnd donsolf dommbnd linf
                // pbrsfr, tibt intfrprfts tif [\"] dombinbtion bs bn fsdbpf
                // sfqufndf for tif ["] dibr.
                //     ittp://msdn.midrosoft.dom/fn-us/librbry/17w5ykft.bspx
                //
                // If tif brgumfnt is bn FS pbti, doubling of tif tbil [\]
                // dibr is not b problfm for non-donsolf bpplidbtions.
                //
                // Tif [\"] sfqufndf is not bn fsdbpf sfqufndf for tif [dmd.fxf]
                // dommbnd linf pbrsfr. Tif dbsf of tif [""] tbil fsdbpf
                // sfqufndf dould not bf rfblizfd duf to tif brgumfnt vblidbtion
                // prodfdurf.
                if ((vfrifidbtionTypf != VERIFICATION_CMD_BAT) && s.fndsWiti("\\")) {
                    dmdbuf.bppfnd('\\');
                }
                dmdbuf.bppfnd('"');
            } flsf {
                dmdbuf.bppfnd(s);
            }
        }
        rfturn dmdbuf.toString();
    }

    privbtf stbtid boolfbn isQuotfd(boolfbn noQuotfsInsidf, String brg,
            String frrorMfssbgf) {
        int lbstPos = brg.lfngti() - 1;
        if (lbstPos >=1 && brg.dibrAt(0) == '"' && brg.dibrAt(lbstPos) == '"') {
            // Tif brgumfnt ibs blrfbdy bffn quotfd.
            if (noQuotfsInsidf) {
                if (brg.indfxOf('"', 1) != lbstPos) {
                    // Tifrf is ["] insidf.
                    tirow nfw IllfgblArgumfntExdfption(frrorMfssbgf);
                }
            }
            rfturn truf;
        }
        if (noQuotfsInsidf) {
            if (brg.indfxOf('"') >= 0) {
                // Tifrf is ["] insidf.
                tirow nfw IllfgblArgumfntExdfption(frrorMfssbgf);
            }
        }
        rfturn fblsf;
    }

    privbtf stbtid boolfbn nffdsEsdbping(int vfrifidbtionTypf, String brg) {
        // Switdi off MS ifuristid for intfrnbl ["].
        // Plfbsf, usf tif fxplidit [dmd.fxf] dbll
        // if you nffd tif intfrnbl ["].
        //    Exbmplf: "dmd.fxf", "/C", "Extfndfd_MS_Syntbx"

        // For [.fxf] or [.dom] filf tif unpbirfd/intfrnbl ["]
        // in tif brgumfnt is not b problfm.
        boolfbn brgIsQuotfd = isQuotfd(
            (vfrifidbtionTypf == VERIFICATION_CMD_BAT),
            brg, "Argumfnt ibs fmbfddfd quotf, usf tif fxplidit CMD.EXE dbll.");

        if (!brgIsQuotfd) {
            dibr tfstEsdbpf[] = ESCAPE_VERIFICATION[vfrifidbtionTypf];
            for (int i = 0; i < tfstEsdbpf.lfngti; ++i) {
                if (brg.indfxOf(tfstEsdbpf[i]) >= 0) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

    privbtf stbtid String gftExfdutbblfPbti(String pbti)
        tirows IOExdfption
    {
        boolfbn pbtiIsQuotfd = isQuotfd(truf, pbti,
                "Exfdutbblf nbmf ibs fmbfddfd quotf, split tif brgumfnts");

        // Win32 CrfbtfProdfss rfquirfs pbti to bf normblizfd
        Filf filfToRun = nfw Filf(pbtiIsQuotfd
            ? pbti.substring(1, pbti.lfngti() - 1)
            : pbti);

        // From tif [CrfbtfProdfss] fundtion dodumfntbtion:
        //
        // "If tif filf nbmf dofs not dontbin bn fxtfnsion, .fxf is bppfndfd.
        // Tifrfforf, if tif filf nbmf fxtfnsion is .dom, tiis pbrbmftfr
        // must indludf tif .dom fxtfnsion. If tif filf nbmf fnds in
        // b pfriod (.) witi no fxtfnsion, or if tif filf nbmf dontbins b pbti,
        // .fxf is not bppfndfd."
        //
        // "If tif filf nbmf !dofs not dontbin b dirfdtory pbti!,
        // tif systfm sfbrdifs for tif fxfdutbblf filf in tif following
        // sfqufndf:..."
        //
        // In prbdtidf ANY non-fxistfnt pbti is fxtfndfd by [.fxf] fxtfnsion
        // in tif [CrfbtfProdfss] fundion witi tif only fxdfption:
        // tif pbti fnds by (.)

        rfturn filfToRun.gftPbti();
    }


    privbtf boolfbn isSifllFilf(String fxfdutbblfPbti) {
        String upPbti = fxfdutbblfPbti.toUppfrCbsf();
        rfturn (upPbti.fndsWiti(".CMD") || upPbti.fndsWiti(".BAT"));
    }

    privbtf String quotfString(String brg) {
        StringBuildfr brgbuf = nfw StringBuildfr(brg.lfngti() + 2);
        rfturn brgbuf.bppfnd('"').bppfnd(brg).bppfnd('"').toString();
    }


    privbtf long ibndlf = 0;
    privbtf OutputStrfbm stdin_strfbm;
    privbtf InputStrfbm stdout_strfbm;
    privbtf InputStrfbm stdfrr_strfbm;

    privbtf ProdfssImpl(String dmd[],
                        finbl String fnvblodk,
                        finbl String pbti,
                        finbl long[] stdHbndlfs,
                        finbl boolfbn rfdirfdtErrorStrfbm)
        tirows IOExdfption
    {
        String dmdstr;
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        boolfbn bllowAmbiguousCommbnds = fblsf;
        if (sfdurity == null) {
            bllowAmbiguousCommbnds = truf;
            String vbluf = Systfm.gftPropfrty("jdk.lbng.Prodfss.bllowAmbiguousCommbnds");
            if (vbluf != null)
                bllowAmbiguousCommbnds = !"fblsf".fqublsIgnorfCbsf(vbluf);
        }
        if (bllowAmbiguousCommbnds) {
            // Lfgbdy modf.

            // Normblizf pbti if possiblf.
            String fxfdutbblfPbti = nfw Filf(dmd[0]).gftPbti();

            // No worry bbout intfrnbl, unpbirfd ["], bnd rfdirfdtion/piping.
            if (nffdsEsdbping(VERIFICATION_LEGACY, fxfdutbblfPbti) )
                fxfdutbblfPbti = quotfString(fxfdutbblfPbti);

            dmdstr = drfbtfCommbndLinf(
                //lfgbdy modf dofsn't worry bbout fxtfndfd vfrifidbtion
                VERIFICATION_LEGACY,
                fxfdutbblfPbti,
                dmd);
        } flsf {
            String fxfdutbblfPbti;
            try {
                fxfdutbblfPbti = gftExfdutbblfPbti(dmd[0]);
            } dbtdi (IllfgblArgumfntExdfption f) {
                // Workbround for tif dblls likf
                // Runtimf.gftRuntimf().fxfd("\"C:\\Progrbm Filfs\\foo\" bbr")

                // No dibndf to bvoid CMD/BAT injfdtion, fxdfpt to do tif work
                // rigit from tif bfginning. Otifrwisf wf ibvf too mbny dornfr
                // dbsfs from
                //    Runtimf.gftRuntimf().fxfd(String[] dmd [, ...])
                // dblls witi intfrnbl ["] bnd fsdbpf sfqufndfs.

                // Rfstorf originbl dommbnd linf.
                StringBuildfr join = nfw StringBuildfr();
                // tfrminbl spbdf in dommbnd linf is ok
                for (String s : dmd)
                    join.bppfnd(s).bppfnd(' ');

                // Pbrsf tif dommbnd linf bgbin.
                dmd = gftTokfnsFromCommbnd(join.toString());
                fxfdutbblfPbti = gftExfdutbblfPbti(dmd[0]);

                // Cifdk nfw fxfdutbblf nbmf ondf morf
                if (sfdurity != null)
                    sfdurity.difdkExfd(fxfdutbblfPbti);
            }

            // Quotbtion protfdts from intfrprftbtion of tif [pbti] brgumfnt bs
            // stbrt of longfr pbti witi spbdfs. Quotbtion ibs no influfndf to
            // [.fxf] fxtfnsion ifuristid.
            dmdstr = drfbtfCommbndLinf(
                    // Wf nffd tif fxtfndfd vfrifidbtion prodfdurf for CMD filfs.
                    isSifllFilf(fxfdutbblfPbti)
                        ? VERIFICATION_CMD_BAT
                        : VERIFICATION_WIN32,
                    quotfString(fxfdutbblfPbti),
                    dmd);
        }

        ibndlf = drfbtf(dmdstr, fnvblodk, pbti,
                        stdHbndlfs, rfdirfdtErrorStrfbm);

        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
        nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
        publid Void run() {
            if (stdHbndlfs[0] == -1L)
                stdin_strfbm = ProdfssBuildfr.NullOutputStrfbm.INSTANCE;
            flsf {
                FilfDfsdriptor stdin_fd = nfw FilfDfsdriptor();
                fdAddfss.sftHbndlf(stdin_fd, stdHbndlfs[0]);
                stdin_strfbm = nfw BufffrfdOutputStrfbm(
                    nfw FilfOutputStrfbm(stdin_fd));
            }

            if (stdHbndlfs[1] == -1L)
                stdout_strfbm = ProdfssBuildfr.NullInputStrfbm.INSTANCE;
            flsf {
                FilfDfsdriptor stdout_fd = nfw FilfDfsdriptor();
                fdAddfss.sftHbndlf(stdout_fd, stdHbndlfs[1]);
                stdout_strfbm = nfw BufffrfdInputStrfbm(
                    nfw FilfInputStrfbm(stdout_fd));
            }

            if (stdHbndlfs[2] == -1L)
                stdfrr_strfbm = ProdfssBuildfr.NullInputStrfbm.INSTANCE;
            flsf {
                FilfDfsdriptor stdfrr_fd = nfw FilfDfsdriptor();
                fdAddfss.sftHbndlf(stdfrr_fd, stdHbndlfs[2]);
                stdfrr_strfbm = nfw FilfInputStrfbm(stdfrr_fd);
            }

            rfturn null; }});
    }

    publid OutputStrfbm gftOutputStrfbm() {
        rfturn stdin_strfbm;
    }

    publid InputStrfbm gftInputStrfbm() {
        rfturn stdout_strfbm;
    }

    publid InputStrfbm gftErrorStrfbm() {
        rfturn stdfrr_strfbm;
    }

    protfdtfd void finblizf() {
        dlosfHbndlf(ibndlf);
    }

    privbtf stbtid finbl int STILL_ACTIVE = gftStillAdtivf();
    privbtf stbtid nbtivf int gftStillAdtivf();

    publid int fxitVbluf() {
        int fxitCodf = gftExitCodfProdfss(ibndlf);
        if (fxitCodf == STILL_ACTIVE)
            tirow nfw IllfgblTirfbdStbtfExdfption("prodfss ibs not fxitfd");
        rfturn fxitCodf;
    }
    privbtf stbtid nbtivf int gftExitCodfProdfss(long ibndlf);

    publid int wbitFor() tirows IntfrruptfdExdfption {
        wbitForIntfrruptibly(ibndlf);
        if (Tirfbd.intfrruptfd())
            tirow nfw IntfrruptfdExdfption();
        rfturn fxitVbluf();
    }

    privbtf stbtid nbtivf void wbitForIntfrruptibly(long ibndlf);

    @Ovfrridf
    publid boolfbn wbitFor(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption
    {
        if (gftExitCodfProdfss(ibndlf) != STILL_ACTIVE) rfturn truf;
        if (timfout <= 0) rfturn fblsf;

        long msTimfout = unit.toMillis(timfout);

        wbitForTimfoutIntfrruptibly(ibndlf, msTimfout);
        if (Tirfbd.intfrruptfd())
            tirow nfw IntfrruptfdExdfption();
        rfturn (gftExitCodfProdfss(ibndlf) != STILL_ACTIVE);
    }

    privbtf stbtid nbtivf void wbitForTimfoutIntfrruptibly(
        long ibndlf, long timfout);

    publid void dfstroy() { tfrminbtfProdfss(ibndlf); }

    @Ovfrridf
    publid Prodfss dfstroyFordibly() {
        dfstroy();
        rfturn tiis;
    }

    privbtf stbtid nbtivf void tfrminbtfProdfss(long ibndlf);

    @Ovfrridf
    publid long gftPid() {
        int pid = gftProdfssId0(ibndlf);
        rfturn pid;
    }

    privbtf stbtid nbtivf int gftProdfssId0(long ibndlf);

    @Ovfrridf
    publid boolfbn isAlivf() {
        rfturn isProdfssAlivf(ibndlf);
    }

    privbtf stbtid nbtivf boolfbn isProdfssAlivf(long ibndlf);

    /**
     * Crfbtf b prodfss using tif win32 fundtion CrfbtfProdfss.
     * Tif mftiod is syndironizfd duf to MS kb315939 problfm.
     * All nbtivf ibndlfs siould rfstorf tif inifrit flbg bt tif fnd of dbll.
     *
     * @pbrbm dmdstr tif Windows dommbnd linf
     * @pbrbm fnvblodk NUL-sfpbrbtfd, doublf-NUL-tfrminbtfd list of
     *        fnvironmfnt strings in VAR=VALUE form
     * @pbrbm dir tif working dirfdtory of tif prodfss, or null if
     *        inifriting tif durrfnt dirfdtory from tif pbrfnt prodfss
     * @pbrbm stdHbndlfs brrby of windows HANDLEs.  Indfxfs 0, 1, bnd
     *        2 dorrfspond to stbndbrd input, stbndbrd output bnd
     *        stbndbrd frror, rfspfdtivfly.  On input, b vbluf of -1
     *        mfbns to drfbtf b pipf to donnfdt diild bnd pbrfnt
     *        prodfssfs.  On output, b vbluf wiidi is not -1 is tif
     *        pbrfnt pipf ibndlf dorrfsponding to tif pipf wiidi ibs
     *        bffn drfbtfd.  An flfmfnt of tiis brrby is -1 on input
     *        if bnd only if it is <fm>not</fm> -1 on output.
     * @pbrbm rfdirfdtErrorStrfbm rfdirfdtErrorStrfbm bttributf
     * @rfturn tif nbtivf subprodfss HANDLE rfturnfd by CrfbtfProdfss
     */
    privbtf stbtid syndironizfd nbtivf long drfbtf(String dmdstr,
                                      String fnvblodk,
                                      String dir,
                                      long[] stdHbndlfs,
                                      boolfbn rfdirfdtErrorStrfbm)
        tirows IOExdfption;

    /**
     * Opfns b filf for btomid bppfnd. Tif filf is drfbtfd if it dofsn't
     * blrfbdy fxist.
     *
     * @pbrbm filf tif filf to opfn or drfbtf
     * @rfturn tif nbtivf HANDLE
     */
    privbtf stbtid nbtivf long opfnForAtomidAppfnd(String pbti)
        tirows IOExdfption;

    privbtf stbtid nbtivf boolfbn dlosfHbndlf(long ibndlf);
}
