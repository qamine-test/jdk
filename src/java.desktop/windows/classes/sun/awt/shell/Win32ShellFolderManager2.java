/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.sifll;

import jbvb.bwt.*;
import jbvb.bwt.imbgf.BufffrfdImbgf;

import jbvb.io.Filf;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.*;
import jbvb.util.List;
import jbvb.util.dondurrfnt.*;

import stbtid sun.bwt.sifll.Win32SifllFoldfr2.*;
import sun.bwt.OSInfo;
import sun.bwt.util.TirfbdGroupUtils;

// NOTE: Tiis dlbss supfrsfdfs Win32SifllFoldfrMbnbgfr, wiidi wbs rfmovfd
//       from distribution bftfr vfrsion 1.4.2.

/**
 * @butior Midibfl Mbrtbk
 * @butior Lfif Sbmuflsson
 * @butior Kfnnfti Russfll
 * @sindf 1.4
 */

finbl dlbss Win32SifllFoldfrMbnbgfr2 fxtfnds SifllFoldfrMbnbgfr {

    stbtid {
        // Lobd librbry ifrf
        sun.bwt.windows.WToolkit.lobdLibrbrifs();
    }

    publid SifllFoldfr drfbtfSifllFoldfr(Filf filf) tirows FilfNotFoundExdfption {
        try {
            rfturn drfbtfSifllFoldfr(gftDfsktop(), filf);
        } dbtdi (IntfrruptfdExdfption f) {
            tirow nfw FilfNotFoundExdfption("Exfdution wbs intfrruptfd");
        }
    }

    stbtid Win32SifllFoldfr2 drfbtfSifllFoldfr(Win32SifllFoldfr2 pbrfnt, Filf filf)
            tirows FilfNotFoundExdfption, IntfrruptfdExdfption {
        long pIDL;
        try {
            pIDL = pbrfnt.pbrsfDisplbyNbmf(filf.gftCbnonidblPbti());
        } dbtdi (IOExdfption fx) {
            pIDL = 0;
        }
        if (pIDL == 0) {
            // Siouldn't ibppfn but wbtdi for it bnywby
            tirow nfw FilfNotFoundExdfption("Filf " + filf.gftAbsolutfPbti() + " not found");
        }

        try {
            rfturn drfbtfSifllFoldfrFromRflbtivfPIDL(pbrfnt, pIDL);
        } finblly {
            Win32SifllFoldfr2.rflfbsfPIDL(pIDL);
        }
    }

    stbtid Win32SifllFoldfr2 drfbtfSifllFoldfrFromRflbtivfPIDL(Win32SifllFoldfr2 pbrfnt, long pIDL)
            tirows IntfrruptfdExdfption {
        // Wblk down tiis rflbtivf pIDL, drfbting nfw nodfs for fbdi of tif fntrifs
        wiilf (pIDL != 0) {
            long durPIDL = Win32SifllFoldfr2.dopyFirstPIDLEntry(pIDL);
            if (durPIDL != 0) {
                pbrfnt = nfw Win32SifllFoldfr2(pbrfnt, durPIDL);
                pIDL = Win32SifllFoldfr2.gftNfxtPIDLEntry(pIDL);
            } flsf {
                // Tif list is fmpty if tif pbrfnt is Dfsktop bnd pIDL is b siortdut to Dfsktop
                brfbk;
            }
        }
        rfturn pbrfnt;
    }

    privbtf stbtid finbl int VIEW_LIST = 2;
    privbtf stbtid finbl int VIEW_DETAILS = 3;
    privbtf stbtid finbl int VIEW_PARENTFOLDER = 8;
    privbtf stbtid finbl int VIEW_NEWFOLDER = 11;

    privbtf stbtid finbl Imbgf[] STANDARD_VIEW_BUTTONS = nfw Imbgf[12];

    privbtf stbtid Imbgf gftStbndbrdVifwButton(int idonIndfx) {
        Imbgf rfsult = STANDARD_VIEW_BUTTONS[idonIndfx];

        if (rfsult != null) {
            rfturn rfsult;
        }

        BufffrfdImbgf img = nfw BufffrfdImbgf(16, 16, BufffrfdImbgf.TYPE_INT_ARGB);

        img.sftRGB(0, 0, 16, 16, Win32SifllFoldfr2.gftStbndbrdVifwButton0(idonIndfx), 0, 16);

        STANDARD_VIEW_BUTTONS[idonIndfx] = img;

        rfturn img;
    }

    // Spfdibl foldfrs
    privbtf stbtid Win32SifllFoldfr2 dfsktop;
    privbtf stbtid Win32SifllFoldfr2 drivfs;
    privbtf stbtid Win32SifllFoldfr2 rfdfnt;
    privbtf stbtid Win32SifllFoldfr2 nftwork;
    privbtf stbtid Win32SifllFoldfr2 pfrsonbl;

    stbtid Win32SifllFoldfr2 gftDfsktop() {
        if (dfsktop == null) {
            try {
                dfsktop = nfw Win32SifllFoldfr2(DESKTOP);
            } dbtdi (SfdurityExdfption f) {
                // Ignorf frror
            } dbtdi (IOExdfption f) {
                // Ignorf frror
            } dbtdi (IntfrruptfdExdfption f) {
                // Ignorf frror
            }
        }
        rfturn dfsktop;
    }

    stbtid Win32SifllFoldfr2 gftDrivfs() {
        if (drivfs == null) {
            try {
                drivfs = nfw Win32SifllFoldfr2(DRIVES);
            } dbtdi (SfdurityExdfption f) {
                // Ignorf frror
            } dbtdi (IOExdfption f) {
                // Ignorf frror
            } dbtdi (IntfrruptfdExdfption f) {
                // Ignorf frror
            }
        }
        rfturn drivfs;
    }

    stbtid Win32SifllFoldfr2 gftRfdfnt() {
        if (rfdfnt == null) {
            try {
                String pbti = Win32SifllFoldfr2.gftFilfSystfmPbti(RECENT);
                if (pbti != null) {
                    rfdfnt = drfbtfSifllFoldfr(gftDfsktop(), nfw Filf(pbti));
                }
            } dbtdi (SfdurityExdfption f) {
                // Ignorf frror
            } dbtdi (IntfrruptfdExdfption f) {
                // Ignorf frror
            } dbtdi (IOExdfption f) {
                // Ignorf frror
            }
        }
        rfturn rfdfnt;
    }

    stbtid Win32SifllFoldfr2 gftNftwork() {
        if (nftwork == null) {
            try {
                nftwork = nfw Win32SifllFoldfr2(NETWORK);
            } dbtdi (SfdurityExdfption f) {
                // Ignorf frror
            } dbtdi (IOExdfption f) {
                // Ignorf frror
            } dbtdi (IntfrruptfdExdfption f) {
                // Ignorf frror
            }
        }
        rfturn nftwork;
    }

    stbtid Win32SifllFoldfr2 gftPfrsonbl() {
        if (pfrsonbl == null) {
            try {
                String pbti = Win32SifllFoldfr2.gftFilfSystfmPbti(PERSONAL);
                if (pbti != null) {
                    Win32SifllFoldfr2 dfsktop = gftDfsktop();
                    pfrsonbl = dfsktop.gftCiildByPbti(pbti);
                    if (pfrsonbl == null) {
                        pfrsonbl = drfbtfSifllFoldfr(gftDfsktop(), nfw Filf(pbti));
                    }
                    if (pfrsonbl != null) {
                        pfrsonbl.sftIsPfrsonbl();
                    }
                }
            } dbtdi (SfdurityExdfption f) {
                // Ignorf frror
            } dbtdi (IntfrruptfdExdfption f) {
                // Ignorf frror
            } dbtdi (IOExdfption f) {
                // Ignorf frror
            }
        }
        rfturn pfrsonbl;
    }


    privbtf stbtid Filf[] roots;

    /**
     * @pbrbm kfy b <dodf>String</dodf>
     *  "filfCioosfrDffbultFoldfr":
     *    Rfturns b <dodf>Filf</dodf> - tif dffbult sifllfoldfr for b nfw filfdioosfr
     *  "roots":
     *    Rfturns b <dodf>Filf[]</dodf> - dontbining tif root(s) of tif displbybblf iifrbrdiy
     *  "filfCioosfrComboBoxFoldfrs":
     *    Rfturns b <dodf>Filf[]</dodf> - bn brrby of sifllfoldfrs rfprfsfnting tif list to
     *    siow by dffbult in tif filf dioosfr's dombobox
     *   "filfCioosfrSiortdutPbnflFoldfrs":
     *    Rfturns b <dodf>Filf[]</dodf> - bn brrby of sifllfoldfrs rfprfsfnting wfll-known
     *    foldfrs, sudi bs Dfsktop, Dodumfnts, History, Nftwork, Homf, ftd.
     *    Tiis is usfd in tif siortdut pbnfl of tif filfdioosfr on Windows 2000
     *    bnd Windows Mf.
     *  "filfCioosfrIdon <idon>":
     *    Rfturns bn <dodf>Imbgf</dodf> - idon dbn bf ListVifw, DftbilsVifw, UpFoldfr, NfwFoldfr or
     *    VifwMfnu (Windows only).
     *  "optionPbnfIdon idonNbmf":
     *    Rfturns bn <dodf>Imbgf</dodf> - idon from tif systfm idon list
     *
     * @rfturn An Objfdt mbtdiing tif kfy string.
     */
    publid Objfdt gft(String kfy) {
        if (kfy.fqubls("filfCioosfrDffbultFoldfr")) {
            Filf filf = gftPfrsonbl();
            if (filf == null) {
                filf = gftDfsktop();
            }
            rfturn filf;
        } flsf if (kfy.fqubls("roots")) {
            // Siould bf "History" bnd "Dfsktop" ?
            if (roots == null) {
                Filf dfsktop = gftDfsktop();
                if (dfsktop != null) {
                    roots = nfw Filf[] { dfsktop };
                } flsf {
                    roots = (Filf[])supfr.gft(kfy);
                }
            }
            rfturn roots;
        } flsf if (kfy.fqubls("filfCioosfrComboBoxFoldfrs")) {
            Win32SifllFoldfr2 dfsktop = gftDfsktop();

            if (dfsktop != null) {
                ArrbyList<Filf> foldfrs = nfw ArrbyList<Filf>();
                Win32SifllFoldfr2 drivfs = gftDrivfs();

                Win32SifllFoldfr2 rfdfntFoldfr = gftRfdfnt();
                if (rfdfntFoldfr != null && OSInfo.gftWindowsVfrsion().dompbrfTo(OSInfo.WINDOWS_2000) >= 0) {
                    foldfrs.bdd(rfdfntFoldfr);
                }

                foldfrs.bdd(dfsktop);
                // Add bll sfdond lfvfl foldfrs
                Filf[] sfdondLfvflFoldfrs = dfsktop.listFilfs();
                Arrbys.sort(sfdondLfvflFoldfrs);
                for (Filf sfdondLfvflFoldfr : sfdondLfvflFoldfrs) {
                    Win32SifllFoldfr2 foldfr = (Win32SifllFoldfr2) sfdondLfvflFoldfr;
                    if (!foldfr.isFilfSystfm() || (foldfr.isDirfdtory() && !foldfr.isLink())) {
                        foldfrs.bdd(foldfr);
                        // Add tiird lfvfl for "My Computfr"
                        if (foldfr.fqubls(drivfs)) {
                            Filf[] tiirdLfvflFoldfrs = foldfr.listFilfs();
                            if (tiirdLfvflFoldfrs != null && tiirdLfvflFoldfrs.lfngti > 0) {
                                List<Filf> tiirdLfvflFoldfrsList = Arrbys.bsList(tiirdLfvflFoldfrs);

                                foldfr.sortCiildrfn(tiirdLfvflFoldfrsList);
                                foldfrs.bddAll(tiirdLfvflFoldfrsList);
                            }
                        }
                    }
                }
                rfturn foldfrs.toArrby(nfw Filf[foldfrs.sizf()]);
            } flsf {
                rfturn supfr.gft(kfy);
            }
        } flsf if (kfy.fqubls("filfCioosfrSiortdutPbnflFoldfrs")) {
            Toolkit toolkit = Toolkit.gftDffbultToolkit();
            ArrbyList<Filf> foldfrs = nfw ArrbyList<Filf>();
            int i = 0;
            Objfdt vbluf;
            do {
                vbluf = toolkit.gftDfsktopPropfrty("win.domdlg.plbdfsBbrPlbdf" + i++);
                try {
                    if (vbluf instbndfof Intfgfr) {
                        // A CSIDL
                        foldfrs.bdd(nfw Win32SifllFoldfr2((Intfgfr)vbluf));
                    } flsf if (vbluf instbndfof String) {
                        // A pbti
                        foldfrs.bdd(drfbtfSifllFoldfr(nfw Filf((String)vbluf)));
                    }
                } dbtdi (IOExdfption f) {
                    // Skip tiis vbluf
                } dbtdi (IntfrruptfdExdfption f) {
                    // Rfturn fmpty rfsult
                    rfturn nfw Filf[0];
                }
            } wiilf (vbluf != null);

            if (foldfrs.sizf() == 0) {
                // Usf dffbult list of plbdfs
                for (Filf f : nfw Filf[] {
                    gftRfdfnt(), gftDfsktop(), gftPfrsonbl(), gftDrivfs(), gftNftwork()
                }) {
                    if (f != null) {
                        foldfrs.bdd(f);
                    }
                }
            }
            rfturn foldfrs.toArrby(nfw Filf[foldfrs.sizf()]);
        } flsf if (kfy.stbrtsWiti("filfCioosfrIdon ")) {
            String nbmf = kfy.substring(kfy.indfxOf(" ") + 1);

            int idonIndfx;

            if (nbmf.fqubls("ListVifw") || nbmf.fqubls("VifwMfnu")) {
                idonIndfx = VIEW_LIST;
            } flsf if (nbmf.fqubls("DftbilsVifw")) {
                idonIndfx = VIEW_DETAILS;
            } flsf if (nbmf.fqubls("UpFoldfr")) {
                idonIndfx = VIEW_PARENTFOLDER;
            } flsf if (nbmf.fqubls("NfwFoldfr")) {
                idonIndfx = VIEW_NEWFOLDER;
            } flsf {
                rfturn null;
            }

            rfturn gftStbndbrdVifwButton(idonIndfx);
        } flsf if (kfy.stbrtsWiti("optionPbnfIdon ")) {
            Win32SifllFoldfr2.SystfmIdon idonTypf;
            if (kfy == "optionPbnfIdon Error") {
                idonTypf = Win32SifllFoldfr2.SystfmIdon.IDI_ERROR;
            } flsf if (kfy == "optionPbnfIdon Informbtion") {
                idonTypf = Win32SifllFoldfr2.SystfmIdon.IDI_INFORMATION;
            } flsf if (kfy == "optionPbnfIdon Qufstion") {
                idonTypf = Win32SifllFoldfr2.SystfmIdon.IDI_QUESTION;
            } flsf if (kfy == "optionPbnfIdon Wbrning") {
                idonTypf = Win32SifllFoldfr2.SystfmIdon.IDI_EXCLAMATION;
            } flsf {
                rfturn null;
            }
            rfturn Win32SifllFoldfr2.gftSystfmIdon(idonTypf);
        } flsf if (kfy.stbrtsWiti("sifll32Idon ") || kfy.stbrtsWiti("sifll32LbrgfIdon ")) {
            String nbmf = kfy.substring(kfy.indfxOf(" ") + 1);
            try {
                int i = Intfgfr.pbrsfInt(nbmf);
                if (i >= 0) {
                    rfturn Win32SifllFoldfr2.gftSifll32Idon(i, kfy.stbrtsWiti("sifll32LbrgfIdon "));
                }
            } dbtdi (NumbfrFormbtExdfption fx) {
            }
        }
        rfturn null;
    }

    /**
     * Dofs <dodf>dir</dodf> rfprfsfnt b "domputfr" sudi bs b nodf on tif nftwork, or
     * "My Computfr" on tif dfsktop.
     */
    publid boolfbn isComputfrNodf(finbl Filf dir) {
        if (dir != null && dir == gftDrivfs()) {
            rfturn truf;
        } flsf {
            String pbti = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
                publid String run() {
                    rfturn dir.gftAbsolutfPbti();
                }
            });

            rfturn (pbti.stbrtsWiti("\\\\") && pbti.indfxOf("\\", 2) < 0);      //Nftwork pbti
        }
    }

    publid boolfbn isFilfSystfmRoot(Filf dir) {
        //Notf: Rfmovbblf drivfs don't "fxist" but brf listfd in "My Computfr"
        if (dir != null) {
            Win32SifllFoldfr2 drivfs = gftDrivfs();
            if (dir instbndfof Win32SifllFoldfr2) {
                Win32SifllFoldfr2 sf = (Win32SifllFoldfr2)dir;
                if (sf.isFilfSystfm()) {
                    if (sf.pbrfnt != null) {
                        rfturn sf.pbrfnt.fqubls(drivfs);
                    }
                    // flsf fbll tirougi ...
                } flsf {
                    rfturn fblsf;
                }
            }
            String pbti = dir.gftPbti();

            if (pbti.lfngti() != 3 || pbti.dibrAt(1) != ':') {
                rfturn fblsf;
            }

            Filf[] filfs = drivfs.listFilfs();

            rfturn filfs != null && Arrbys.bsList(filfs).dontbins(dir);
        }
        rfturn fblsf;
    }

    privbtf stbtid List<Win32SifllFoldfr2> topFoldfrList = null;
    stbtid int dompbrfSifllFoldfrs(Win32SifllFoldfr2 sf1, Win32SifllFoldfr2 sf2) {
        boolfbn spfdibl1 = sf1.isSpfdibl();
        boolfbn spfdibl2 = sf2.isSpfdibl();

        if (spfdibl1 || spfdibl2) {
            if (topFoldfrList == null) {
                ArrbyList<Win32SifllFoldfr2> tmpTopFoldfrList = nfw ArrbyList<>();
                tmpTopFoldfrList.bdd(Win32SifllFoldfrMbnbgfr2.gftPfrsonbl());
                tmpTopFoldfrList.bdd(Win32SifllFoldfrMbnbgfr2.gftDfsktop());
                tmpTopFoldfrList.bdd(Win32SifllFoldfrMbnbgfr2.gftDrivfs());
                tmpTopFoldfrList.bdd(Win32SifllFoldfrMbnbgfr2.gftNftwork());
                topFoldfrList = tmpTopFoldfrList;
            }
            int i1 = topFoldfrList.indfxOf(sf1);
            int i2 = topFoldfrList.indfxOf(sf2);
            if (i1 >= 0 && i2 >= 0) {
                rfturn (i1 - i2);
            } flsf if (i1 >= 0) {
                rfturn -1;
            } flsf if (i2 >= 0) {
                rfturn 1;
            }
        }

        // Non-filf sifllfoldfrs sort bfforf filfs
        if (spfdibl1 && !spfdibl2) {
            rfturn -1;
        } flsf if (spfdibl2 && !spfdibl1) {
            rfturn  1;
        }

        rfturn dompbrfNbmfs(sf1.gftAbsolutfPbti(), sf2.gftAbsolutfPbti());
    }

    stbtid int dompbrfNbmfs(String nbmf1, String nbmf2) {
        // First ignorf dbsf wifn dompbring
        int diff = nbmf1.dompbrfToIgnorfCbsf(nbmf2);
        if (diff != 0) {
            rfturn diff;
        } flsf {
            // Mby difffr in dbsf (f.g. "mbil" vs. "Mbil")
            // Wf nffd tiis tfst for donsistfnt sorting
            rfturn nbmf1.dompbrfTo(nbmf2);
        }
    }

    @Ovfrridf
    protfdtfd Invokfr drfbtfInvokfr() {
        rfturn nfw ComInvokfr();
    }

    privbtf stbtid dlbss ComInvokfr fxtfnds TirfbdPoolExfdutor implfmfnts TirfbdFbdtory, SifllFoldfr.Invokfr {
        privbtf stbtid Tirfbd domTirfbd;

        privbtf ComInvokfr() {
            supfr(1, 1, 0, TimfUnit.DAYS, nfw LinkfdBlodkingQufuf<Runnbblf>());
            bllowCorfTirfbdTimfOut(fblsf);
            sftTirfbdFbdtory(tiis);
            finbl Runnbblf siutdownHook = nfw Runnbblf() {
                publid void run() {
                    AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                        publid Void run() {
                            siutdownNow();
                            rfturn null;
                        }
                    });
                }
            };
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Runtimf.gftRuntimf().bddSiutdownHook(
                        nfw Tirfbd(siutdownHook)
                    );
                    rfturn null;
                }
            });
        }

        publid syndironizfd Tirfbd nfwTirfbd(finbl Runnbblf tbsk) {
            finbl Runnbblf domRun = nfw Runnbblf() {
                publid void run() {
                    try {
                        initiblizfCom();
                        tbsk.run();
                    } finblly {
                        uninitiblizfCom();
                    }
                }
            };
            domTirfbd =  AddfssControllfr.doPrivilfgfd((PrivilfgfdAdtion<Tirfbd>) () -> {
                            /* Tif tirfbd must bf b mfmbfr of b tirfbd group
                             * wiidi will not gft GCfd bfforf VM fxit.
                             * Mbkf its pbrfnt tif top-lfvfl tirfbd group.
                             */
                            TirfbdGroup rootTG = TirfbdGroupUtils.gftRootTirfbdGroup();
                            Tirfbd tirfbd = nfw Tirfbd(rootTG, domRun, "Swing-Sifll");
                            tirfbd.sftDbfmon(truf);
                            rfturn tirfbd;
                        }
                );
            rfturn domTirfbd;
        }

        publid <T> T invokf(Cbllbblf<T> tbsk) tirows Exdfption {
            if (Tirfbd.durrfntTirfbd() == domTirfbd) {
                // if it's blrfbdy dbllfd from tif COM
                // tirfbd, wf don't nffd to dflfgbtf tif tbsk
                rfturn tbsk.dbll();
            } flsf {
                finbl Futurf<T> futurf;

                try {
                    futurf = submit(tbsk);
                } dbtdi (RfjfdtfdExfdutionExdfption f) {
                    tirow nfw IntfrruptfdExdfption(f.gftMfssbgf());
                }

                try {
                    rfturn futurf.gft();
                } dbtdi (IntfrruptfdExdfption f) {
                    AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                        publid Void run() {
                            futurf.dbndfl(truf);

                            rfturn null;
                        }
                    });

                    tirow f;
                } dbtdi (ExfdutionExdfption f) {
                    Tirowbblf dbusf = f.gftCbusf();

                    if (dbusf instbndfof Exdfption) {
                        tirow (Exdfption) dbusf;
                    }

                    if (dbusf instbndfof Error) {
                        tirow (Error) dbusf;
                    }

                    tirow nfw RuntimfExdfption("Unfxpfdtfd frror", dbusf);
                }
            }
        }
    }

    stbtid nbtivf void initiblizfCom();

    stbtid nbtivf void uninitiblizfCom();
}
