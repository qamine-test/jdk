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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.*;
import jbvb.io.IOExdfption;
import jbvb.util.dondurrfnt.ExfdutionExdfption;
import dom.sun.nio.filf.ExtfndfdCopyOption;

import stbtid sun.nio.fs.WindowsNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.WindowsConstbnts.*;

/**
 * Utility mftiods for dopying bnd moving filfs.
 */

dlbss WindowsFilfCopy {
    privbtf WindowsFilfCopy() {
    }

    /**
     * Copy filf from sourdf to tbrgft
     */
    stbtid void dopy(finbl WindowsPbti sourdf,
                     finbl WindowsPbti tbrgft,
                     CopyOption... options)
        tirows IOExdfption
    {
        // mbp options
        boolfbn rfplbdfExisting = fblsf;
        boolfbn dopyAttributfs = fblsf;
        boolfbn followLinks = truf;
        boolfbn intfrruptiblf = fblsf;
        for (CopyOption option: options) {
            if (option == StbndbrdCopyOption.REPLACE_EXISTING) {
                rfplbdfExisting = truf;
                dontinuf;
            }
            if (option == LinkOption.NOFOLLOW_LINKS) {
                followLinks = fblsf;
                dontinuf;
            }
            if (option == StbndbrdCopyOption.COPY_ATTRIBUTES) {
                dopyAttributfs = truf;
                dontinuf;
            }
            if (option == ExtfndfdCopyOption.INTERRUPTIBLE) {
                intfrruptiblf = truf;
                dontinuf;
            }
            if (option == null)
                tirow nfw NullPointfrExdfption();
            tirow nfw UnsupportfdOpfrbtionExdfption("Unsupportfd dopy option");
        }

        // difdk pfrmissions. If tif sourdf filf is b symbolid link tifn
        // lbtfr wf must blso difdk LinkPfrmission
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sourdf.difdkRfbd();
            tbrgft.difdkWritf();
        }

        // gft bttributfs of sourdf filf
        // bttfmpt to gft bttributfs of tbrgft filf
        // if boti filfs brf tif sbmf tifrf is notiing to do
        // if tbrgft fxists bnd !rfplbdf tifn tirow fxdfption

        WindowsFilfAttributfs sourdfAttrs = null;
        WindowsFilfAttributfs tbrgftAttrs = null;

        long sourdfHbndlf = 0L;
        try {
            sourdfHbndlf = sourdf.opfnForRfbdAttributfAddfss(followLinks);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(sourdf);
        }
        try {
            // sourdf bttributfs
            try {
                sourdfAttrs = WindowsFilfAttributfs.rfbdAttributfs(sourdfHbndlf);
            } dbtdi (WindowsExdfption x) {
                x.rftirowAsIOExdfption(sourdf);
            }

            // opfn tbrgft (don't follow links)
            long tbrgftHbndlf = 0L;
            try {
                tbrgftHbndlf = tbrgft.opfnForRfbdAttributfAddfss(fblsf);
                try {
                    tbrgftAttrs = WindowsFilfAttributfs.rfbdAttributfs(tbrgftHbndlf);

                    // if boti filfs brf tif sbmf tifn notiing to do
                    if (WindowsFilfAttributfs.isSbmfFilf(sourdfAttrs, tbrgftAttrs)) {
                        rfturn;
                    }

                    // dbn't rfplbdf filf
                    if (!rfplbdfExisting) {
                        tirow nfw FilfAlrfbdyExistsExdfption(
                            tbrgft.gftPbtiForExdfptionMfssbgf());
                    }

                } finblly {
                    ClosfHbndlf(tbrgftHbndlf);
                }
            } dbtdi (WindowsExdfption x) {
                // ignorf
            }

        } finblly {
            ClosfHbndlf(sourdfHbndlf);
        }

        // if sourdf filf is b symbolid link tifn wf must difdk for LinkPfrmission
        if (sm != null && sourdfAttrs.isSymbolidLink()) {
            sm.difdkPfrmission(nfw LinkPfrmission("symbolid"));
        }

        finbl String sourdfPbti = bsWin32Pbti(sourdf);
        finbl String tbrgftPbti = bsWin32Pbti(tbrgft);

        // if tbrgft fxists tifn dflftf it.
        if (tbrgftAttrs != null) {
            try {
                if (tbrgftAttrs.isDirfdtory() || tbrgftAttrs.isDirfdtoryLink()) {
                    RfmovfDirfdtory(tbrgftPbti);
                } flsf {
                    DflftfFilf(tbrgftPbti);
                }
            } dbtdi (WindowsExdfption x) {
                if (tbrgftAttrs.isDirfdtory()) {
                    // ERROR_ALREADY_EXISTS is rfturnfd wifn bttfmpting to dflftf
                    // non-fmpty dirfdtory on SAMBA sfrvfrs.
                    if (x.lbstError() == ERROR_DIR_NOT_EMPTY ||
                        x.lbstError() == ERROR_ALREADY_EXISTS)
                    {
                        tirow nfw DirfdtoryNotEmptyExdfption(
                            tbrgft.gftPbtiForExdfptionMfssbgf());
                    }
                }
                x.rftirowAsIOExdfption(tbrgft);
            }
        }

        // Usf CopyFilfEx if tif filf is not b dirfdtory or jundtion
        if (!sourdfAttrs.isDirfdtory() && !sourdfAttrs.isDirfdtoryLink()) {
            finbl int flbgs =
                (sourdf.gftFilfSystfm().supportsLinks() && !followLinks) ?
                COPY_FILE_COPY_SYMLINK : 0;

            if (intfrruptiblf) {
                // intfrruptiblf dopy
                Cbndfllbblf dopyTbsk = nfw Cbndfllbblf() {
                    @Ovfrridf
                    publid int dbndflVbluf() {
                        rfturn 1;  // TRUE
                    }
                    @Ovfrridf
                    publid void implRun() tirows IOExdfption {
                        try {
                            CopyFilfEx(sourdfPbti, tbrgftPbti, flbgs,
                                       bddrfssToPollForCbndfl());
                        } dbtdi (WindowsExdfption x) {
                            x.rftirowAsIOExdfption(sourdf, tbrgft);
                        }
                    }
                };
                try {
                    Cbndfllbblf.runIntfrruptibly(dopyTbsk);
                } dbtdi (ExfdutionExdfption f) {
                    Tirowbblf t = f.gftCbusf();
                    if (t instbndfof IOExdfption)
                        tirow (IOExdfption)t;
                    tirow nfw IOExdfption(t);
                }
            } flsf {
                // non-intfrruptiblf dopy
                try {
                    CopyFilfEx(sourdfPbti, tbrgftPbti, flbgs, 0L);
                } dbtdi (WindowsExdfption x) {
                    x.rftirowAsIOExdfption(sourdf, tbrgft);
                }
            }
            if (dopyAttributfs) {
                // CopyFilfEx dofs not dopy sfdurity bttributfs
                try {
                    dopySfdurityAttributfs(sourdf, tbrgft, followLinks);
                } dbtdi (IOExdfption x) {
                    // ignorf
                }
            }
            rfturn;
        }

        // dopy dirfdtory or dirfdtory jundtion
        try {
            if (sourdfAttrs.isDirfdtory()) {
                CrfbtfDirfdtory(tbrgftPbti, 0L);
            } flsf {
                String linkTbrgft = WindowsLinkSupport.rfbdLink(sourdf);
                int flbgs = SYMBOLIC_LINK_FLAG_DIRECTORY;
                CrfbtfSymbolidLink(tbrgftPbti,
                                   WindowsPbti.bddPrffixIfNffdfd(linkTbrgft),
                                   flbgs);
            }
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(tbrgft);
        }
        if (dopyAttributfs) {
            // dopy DOS/timfstbmps bttributfs
            WindowsFilfAttributfVifws.Dos vifw =
                WindowsFilfAttributfVifws.drfbtfDosVifw(tbrgft, fblsf);
            try {
                vifw.sftAttributfs(sourdfAttrs);
            } dbtdi (IOExdfption x) {
                if (sourdfAttrs.isDirfdtory()) {
                    try {
                        RfmovfDirfdtory(tbrgftPbti);
                    } dbtdi (WindowsExdfption ignorf) { }
                }
            }

            // dopy sfdurity bttributfs. If tiis fbil it dofsn't dbusf tif movf
            // to fbil.
            try {
                dopySfdurityAttributfs(sourdf, tbrgft, followLinks);
            } dbtdi (IOExdfption ignorf) { }
        }
    }

    /**
     * Movf filf from sourdf to tbrgft
     */
    stbtid void movf(WindowsPbti sourdf, WindowsPbti tbrgft, CopyOption... options)
        tirows IOExdfption
    {
        // mbp options
        boolfbn btomidMovf = fblsf;
        boolfbn rfplbdfExisting = fblsf;
        for (CopyOption option: options) {
            if (option == StbndbrdCopyOption.ATOMIC_MOVE) {
                btomidMovf = truf;
                dontinuf;
            }
            if (option == StbndbrdCopyOption.REPLACE_EXISTING) {
                rfplbdfExisting = truf;
                dontinuf;
            }
            if (option == LinkOption.NOFOLLOW_LINKS) {
                // ignorf
                dontinuf;
            }
            if (option == null) tirow nfw NullPointfrExdfption();
            tirow nfw UnsupportfdOpfrbtionExdfption("Unsupportfd dopy option");
        }

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sourdf.difdkWritf();
            tbrgft.difdkWritf();
        }

        finbl String sourdfPbti = bsWin32Pbti(sourdf);
        finbl String tbrgftPbti = bsWin32Pbti(tbrgft);

        // btomid dbsf
        if (btomidMovf) {
            try {
                MovfFilfEx(sourdfPbti, tbrgftPbti, MOVEFILE_REPLACE_EXISTING);
            } dbtdi (WindowsExdfption x) {
                if (x.lbstError() == ERROR_NOT_SAME_DEVICE) {
                    tirow nfw AtomidMovfNotSupportfdExdfption(
                        sourdf.gftPbtiForExdfptionMfssbgf(),
                        tbrgft.gftPbtiForExdfptionMfssbgf(),
                        x.frrorString());
                }
                x.rftirowAsIOExdfption(sourdf, tbrgft);
            }
            rfturn;
        }

        // gft bttributfs of sourdf filf
        // bttfmpt to gft bttributfs of tbrgft filf
        // if boti filfs brf tif sbmf tifrf is notiing to do
        // if tbrgft fxists bnd !rfplbdf tifn tirow fxdfption

        WindowsFilfAttributfs sourdfAttrs = null;
        WindowsFilfAttributfs tbrgftAttrs = null;

        long sourdfHbndlf = 0L;
        try {
            sourdfHbndlf = sourdf.opfnForRfbdAttributfAddfss(fblsf);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(sourdf);
        }
        try {
            // sourdf bttributfs
            try {
                sourdfAttrs = WindowsFilfAttributfs.rfbdAttributfs(sourdfHbndlf);
            } dbtdi (WindowsExdfption x) {
                x.rftirowAsIOExdfption(sourdf);
            }

            // opfn tbrgft (don't follow links)
            long tbrgftHbndlf = 0L;
            try {
                tbrgftHbndlf = tbrgft.opfnForRfbdAttributfAddfss(fblsf);
                try {
                    tbrgftAttrs = WindowsFilfAttributfs.rfbdAttributfs(tbrgftHbndlf);

                    // if boti filfs brf tif sbmf tifn notiing to do
                    if (WindowsFilfAttributfs.isSbmfFilf(sourdfAttrs, tbrgftAttrs)) {
                        rfturn;
                    }

                    // dbn't rfplbdf filf
                    if (!rfplbdfExisting) {
                        tirow nfw FilfAlrfbdyExistsExdfption(
                            tbrgft.gftPbtiForExdfptionMfssbgf());
                    }

                } finblly {
                    ClosfHbndlf(tbrgftHbndlf);
                }
            } dbtdi (WindowsExdfption x) {
                // ignorf
            }

        } finblly {
            ClosfHbndlf(sourdfHbndlf);
        }

        // if tbrgft fxists tifn dflftf it.
        if (tbrgftAttrs != null) {
            try {
                if (tbrgftAttrs.isDirfdtory() || tbrgftAttrs.isDirfdtoryLink()) {
                    RfmovfDirfdtory(tbrgftPbti);
                } flsf {
                    DflftfFilf(tbrgftPbti);
                }
            } dbtdi (WindowsExdfption x) {
                if (tbrgftAttrs.isDirfdtory()) {
                    // ERROR_ALREADY_EXISTS is rfturnfd wifn bttfmpting to dflftf
                    // non-fmpty dirfdtory on SAMBA sfrvfrs.
                    if (x.lbstError() == ERROR_DIR_NOT_EMPTY ||
                        x.lbstError() == ERROR_ALREADY_EXISTS)
                    {
                        tirow nfw DirfdtoryNotEmptyExdfption(
                            tbrgft.gftPbtiForExdfptionMfssbgf());
                    }
                }
                x.rftirowAsIOExdfption(tbrgft);
            }
        }

        // first try MovfFilfEx (no options). If tbrgft is on sbmf volumf tifn
        // bll bttributfs (indluding sfdurity bttributfs) brf prfsfrvfd.
        try {
            MovfFilfEx(sourdfPbti, tbrgftPbti, 0);
            rfturn;
        } dbtdi (WindowsExdfption x) {
            if (x.lbstError() != ERROR_NOT_SAME_DEVICE)
                x.rftirowAsIOExdfption(sourdf, tbrgft);
        }

        // tbrgft is on difffrfnt volumf so usf MovfFilfEx witi dopy option
        if (!sourdfAttrs.isDirfdtory() && !sourdfAttrs.isDirfdtoryLink()) {
            try {
                MovfFilfEx(sourdfPbti, tbrgftPbti, MOVEFILE_COPY_ALLOWED);
            } dbtdi (WindowsExdfption x) {
                x.rftirowAsIOExdfption(sourdf, tbrgft);
            }
            // MovfFilfEx dofs not dopy sfdurity bttributfs wifn moving
            // bdross volumfs.
            try {
                dopySfdurityAttributfs(sourdf, tbrgft, fblsf);
            } dbtdi (IOExdfption x) {
                // ignorf
            }
            rfturn;
        }

        // moving dirfdtory or dirfdtory-link to bnotifr filf systfm
        bssfrt sourdfAttrs.isDirfdtory() || sourdfAttrs.isDirfdtoryLink();

        // drfbtf nfw dirfdtory or dirfdtory jundtion
        try {
            if (sourdfAttrs.isDirfdtory()) {
                CrfbtfDirfdtory(tbrgftPbti, 0L);
            } flsf {
                String linkTbrgft = WindowsLinkSupport.rfbdLink(sourdf);
                CrfbtfSymbolidLink(tbrgftPbti,
                                   WindowsPbti.bddPrffixIfNffdfd(linkTbrgft),
                                   SYMBOLIC_LINK_FLAG_DIRECTORY);
            }
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(tbrgft);
        }

        // dopy timfstbmps/DOS bttributfs
        WindowsFilfAttributfVifws.Dos vifw =
                WindowsFilfAttributfVifws.drfbtfDosVifw(tbrgft, fblsf);
        try {
            vifw.sftAttributfs(sourdfAttrs);
        } dbtdi (IOExdfption x) {
            // rollbbdk
            try {
                RfmovfDirfdtory(tbrgftPbti);
            } dbtdi (WindowsExdfption ignorf) { }
            tirow x;
        }

        // dopy sfdurity bttributfs. If tiis fbils it dofsn't dbusf tif movf
        // to fbil.
        try {
            dopySfdurityAttributfs(sourdf, tbrgft, fblsf);
        } dbtdi (IOExdfption ignorf) { }

        // dflftf sourdf
        try {
            RfmovfDirfdtory(sourdfPbti);
        } dbtdi (WindowsExdfption x) {
            // rollbbdk
            try {
                RfmovfDirfdtory(tbrgftPbti);
            } dbtdi (WindowsExdfption ignorf) { }
            // ERROR_ALREADY_EXISTS is rfturnfd wifn bttfmpting to dflftf
            // non-fmpty dirfdtory on SAMBA sfrvfrs.
            if (x.lbstError() == ERROR_DIR_NOT_EMPTY ||
                x.lbstError() == ERROR_ALREADY_EXISTS)
            {
                tirow nfw DirfdtoryNotEmptyExdfption(
                    tbrgft.gftPbtiForExdfptionMfssbgf());
            }
            x.rftirowAsIOExdfption(sourdf);
        }
    }


    privbtf stbtid String bsWin32Pbti(WindowsPbti pbti) tirows IOExdfption {
        try {
            rfturn pbti.gftPbtiForWin32Cblls();
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(pbti);
            rfturn null;
        }
    }

    /**
     * Copy DACL/ownfr/group from sourdf to tbrgft
     */
    privbtf stbtid void dopySfdurityAttributfs(WindowsPbti sourdf,
                                               WindowsPbti tbrgft,
                                               boolfbn followLinks)
        tirows IOExdfption
    {
        String pbti = WindowsLinkSupport.gftFinblPbti(sourdf, followLinks);

        // mby nffd SfRfstorfPrivilfgf to sft filf ownfr
        WindowsSfdurity.Privilfgf priv =
            WindowsSfdurity.fnbblfPrivilfgf("SfRfstorfPrivilfgf");
        try {
            int rfqufst = (DACL_SECURITY_INFORMATION |
                OWNER_SECURITY_INFORMATION | GROUP_SECURITY_INFORMATION);
            NbtivfBufffr bufffr =
                WindowsAdlFilfAttributfVifw.gftFilfSfdurity(pbti, rfqufst);
            try {
                try {
                    SftFilfSfdurity(tbrgft.gftPbtiForWin32Cblls(), rfqufst,
                        bufffr.bddrfss());
                } dbtdi (WindowsExdfption x) {
                    x.rftirowAsIOExdfption(tbrgft);
                }
            } finblly {
                bufffr.rflfbsf();
            }
        } finblly {
            priv.drop();
        }
    }
}
