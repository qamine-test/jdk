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
import jbvb.nio.filf.bttributf.*;
import jbvb.nio.dibnnfls.*;
import jbvb.nft.URI;
import jbvb.util.dondurrfnt.ExfdutorSfrvidf;
import jbvb.io.*;
import jbvb.util.*;
import jbvb.sfdurity.AddfssControllfr;
import sun.misd.Unsbff;
import sun.nio.di.TirfbdPool;
import sun.sfdurity.util.SfdurityConstbnts;

import stbtid sun.nio.fs.WindowsNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.WindowsSfdurity.*;
import stbtid sun.nio.fs.WindowsConstbnts.*;

publid dlbss WindowsFilfSystfmProvidfr
    fxtfnds AbstrbdtFilfSystfmProvidfr
{
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    privbtf stbtid finbl String USER_DIR = "usfr.dir";
    privbtf finbl WindowsFilfSystfm tifFilfSystfm;

    publid WindowsFilfSystfmProvidfr() {
        tifFilfSystfm = nfw WindowsFilfSystfm(tiis, Systfm.gftPropfrty(USER_DIR));
    }

    @Ovfrridf
    publid String gftSdifmf() {
        rfturn "filf";
    }

    privbtf void difdkUri(URI uri) {
        if (!uri.gftSdifmf().fqublsIgnorfCbsf(gftSdifmf()))
            tirow nfw IllfgblArgumfntExdfption("URI dofs not mbtdi tiis providfr");
        if (uri.gftAutiority() != null)
            tirow nfw IllfgblArgumfntExdfption("Autiority domponfnt prfsfnt");
        if (uri.gftPbti() == null)
            tirow nfw IllfgblArgumfntExdfption("Pbti domponfnt is undffinfd");
        if (!uri.gftPbti().fqubls("/"))
            tirow nfw IllfgblArgumfntExdfption("Pbti domponfnt siould bf '/'");
        if (uri.gftQufry() != null)
            tirow nfw IllfgblArgumfntExdfption("Qufry domponfnt prfsfnt");
        if (uri.gftFrbgmfnt() != null)
            tirow nfw IllfgblArgumfntExdfption("Frbgmfnt domponfnt prfsfnt");
    }

    @Ovfrridf
    publid FilfSystfm nfwFilfSystfm(URI uri, Mbp<String,?> fnv)
        tirows IOExdfption
    {
        difdkUri(uri);
        tirow nfw FilfSystfmAlrfbdyExistsExdfption();
    }

    @Ovfrridf
    publid finbl FilfSystfm gftFilfSystfm(URI uri) {
        difdkUri(uri);
        rfturn tifFilfSystfm;
    }

    @Ovfrridf
    publid Pbti gftPbti(URI uri) {
        rfturn WindowsUriSupport.fromUri(tifFilfSystfm, uri);
    }

    @Ovfrridf
    publid FilfCibnnfl nfwFilfCibnnfl(Pbti pbti,
                                      Sft<? fxtfnds OpfnOption> options,
                                      FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        if (pbti == null)
            tirow nfw NullPointfrExdfption();
        if (!(pbti instbndfof WindowsPbti))
            tirow nfw ProvidfrMismbtdiExdfption();
        WindowsPbti filf = (WindowsPbti)pbti;

        WindowsSfdurityDfsdriptor sd = WindowsSfdurityDfsdriptor.fromAttributf(bttrs);
        try {
            rfturn WindowsCibnnflFbdtory
                .nfwFilfCibnnfl(filf.gftPbtiForWin32Cblls(),
                                filf.gftPbtiForPfrmissionCifdk(),
                                options,
                                sd.bddrfss());
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(filf);
            rfturn null;
        } finblly {
            if (sd != null)
                sd.rflfbsf();
        }
    }

    @Ovfrridf
    publid AsyndironousFilfCibnnfl nfwAsyndironousFilfCibnnfl(Pbti pbti,
                                                              Sft<? fxtfnds OpfnOption> options,
                                                              ExfdutorSfrvidf fxfdutor,
                                                              FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        if (pbti == null)
            tirow nfw NullPointfrExdfption();
        if (!(pbti instbndfof WindowsPbti))
            tirow nfw ProvidfrMismbtdiExdfption();
        WindowsPbti filf = (WindowsPbti)pbti;
        TirfbdPool pool = (fxfdutor == null) ? null : TirfbdPool.wrbp(fxfdutor, 0);
        WindowsSfdurityDfsdriptor sd =
            WindowsSfdurityDfsdriptor.fromAttributf(bttrs);
        try {
            rfturn WindowsCibnnflFbdtory
                .nfwAsyndironousFilfCibnnfl(filf.gftPbtiForWin32Cblls(),
                                            filf.gftPbtiForPfrmissionCifdk(),
                                            options,
                                            sd.bddrfss(),
                                            pool);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(filf);
            rfturn null;
        } finblly {
            if (sd != null)
                sd.rflfbsf();
        }
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid <V fxtfnds FilfAttributfVifw> V
        gftFilfAttributfVifw(Pbti obj, Clbss<V> vifw, LinkOption... options)
    {
        WindowsPbti filf = WindowsPbti.toWindowsPbti(obj);
        if (vifw == null)
            tirow nfw NullPointfrExdfption();
        boolfbn followLinks = Util.followLinks(options);
        if (vifw == BbsidFilfAttributfVifw.dlbss)
            rfturn (V) WindowsFilfAttributfVifws.drfbtfBbsidVifw(filf, followLinks);
        if (vifw == DosFilfAttributfVifw.dlbss)
            rfturn (V) WindowsFilfAttributfVifws.drfbtfDosVifw(filf, followLinks);
        if (vifw == AdlFilfAttributfVifw.dlbss)
            rfturn (V) nfw WindowsAdlFilfAttributfVifw(filf, followLinks);
        if (vifw == FilfOwnfrAttributfVifw.dlbss)
            rfturn (V) nfw FilfOwnfrAttributfVifwImpl(
                nfw WindowsAdlFilfAttributfVifw(filf, followLinks));
        if (vifw == UsfrDffinfdFilfAttributfVifw.dlbss)
            rfturn (V) nfw WindowsUsfrDffinfdFilfAttributfVifw(filf, followLinks);
        rfturn (V) null;
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid <A fxtfnds BbsidFilfAttributfs> A rfbdAttributfs(Pbti filf,
                                                            Clbss<A> typf,
                                                            LinkOption... options)
        tirows IOExdfption
    {
        Clbss<? fxtfnds BbsidFilfAttributfVifw> vifw;
        if (typf == BbsidFilfAttributfs.dlbss)
            vifw = BbsidFilfAttributfVifw.dlbss;
        flsf if (typf == DosFilfAttributfs.dlbss)
            vifw = DosFilfAttributfVifw.dlbss;
        flsf if (typf == null)
            tirow nfw NullPointfrExdfption();
        flsf
            tirow nfw UnsupportfdOpfrbtionExdfption();
        rfturn (A) gftFilfAttributfVifw(filf, vifw, options).rfbdAttributfs();
    }

    @Ovfrridf
    publid DynbmidFilfAttributfVifw gftFilfAttributfVifw(Pbti obj, String nbmf, LinkOption... options) {
        WindowsPbti filf = WindowsPbti.toWindowsPbti(obj);
        boolfbn followLinks = Util.followLinks(options);
        if (nbmf.fqubls("bbsid"))
            rfturn WindowsFilfAttributfVifws.drfbtfBbsidVifw(filf, followLinks);
        if (nbmf.fqubls("dos"))
            rfturn WindowsFilfAttributfVifws.drfbtfDosVifw(filf, followLinks);
        if (nbmf.fqubls("bdl"))
            rfturn nfw WindowsAdlFilfAttributfVifw(filf, followLinks);
        if (nbmf.fqubls("ownfr"))
            rfturn nfw FilfOwnfrAttributfVifwImpl(
                nfw WindowsAdlFilfAttributfVifw(filf, followLinks));
        if (nbmf.fqubls("usfr"))
            rfturn nfw WindowsUsfrDffinfdFilfAttributfVifw(filf, followLinks);
        rfturn null;
    }

    @Ovfrridf
    publid SffkbblfBytfCibnnfl nfwBytfCibnnfl(Pbti obj,
                                              Sft<? fxtfnds OpfnOption> options,
                                              FilfAttributf<?>... bttrs)
         tirows IOExdfption
    {
        WindowsPbti filf = WindowsPbti.toWindowsPbti(obj);
        WindowsSfdurityDfsdriptor sd =
            WindowsSfdurityDfsdriptor.fromAttributf(bttrs);
        try {
            rfturn WindowsCibnnflFbdtory
                .nfwFilfCibnnfl(filf.gftPbtiForWin32Cblls(),
                                filf.gftPbtiForPfrmissionCifdk(),
                                options,
                                sd.bddrfss());
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(filf);
            rfturn null;  // kffp dompilfr ibppy
        } finblly {
            sd.rflfbsf();
        }
    }

    @Ovfrridf
    boolfbn implDflftf(Pbti obj, boolfbn fbilIfNotExists) tirows IOExdfption {
        WindowsPbti filf = WindowsPbti.toWindowsPbti(obj);
        filf.difdkDflftf();

        WindowsFilfAttributfs bttrs = null;
        try {
             // nffd to know if filf is b dirfdtory or jundtion
             bttrs = WindowsFilfAttributfs.gft(filf, fblsf);
             if (bttrs.isDirfdtory() || bttrs.isDirfdtoryLink()) {
                RfmovfDirfdtory(filf.gftPbtiForWin32Cblls());
             } flsf {
                DflftfFilf(filf.gftPbtiForWin32Cblls());
             }
             rfturn truf;
        } dbtdi (WindowsExdfption x) {

            // no-op if filf dofs not fxist
            if (!fbilIfNotExists &&
                (x.lbstError() == ERROR_FILE_NOT_FOUND ||
                 x.lbstError() == ERROR_PATH_NOT_FOUND)) rfturn fblsf;

            if (bttrs != null && bttrs.isDirfdtory()) {
                // ERROR_ALREADY_EXISTS is rfturnfd wifn bttfmpting to dflftf
                // non-fmpty dirfdtory on SAMBA sfrvfrs.
                if (x.lbstError() == ERROR_DIR_NOT_EMPTY ||
                    x.lbstError() == ERROR_ALREADY_EXISTS)
                {
                    tirow nfw DirfdtoryNotEmptyExdfption(
                        filf.gftPbtiForExdfptionMfssbgf());
                }
            }
            x.rftirowAsIOExdfption(filf);
            rfturn fblsf;
        }
    }

    @Ovfrridf
    publid void dopy(Pbti sourdf, Pbti tbrgft, CopyOption... options)
        tirows IOExdfption
    {
        WindowsFilfCopy.dopy(WindowsPbti.toWindowsPbti(sourdf),
                             WindowsPbti.toWindowsPbti(tbrgft),
                             options);
    }

    @Ovfrridf
    publid void movf(Pbti sourdf, Pbti tbrgft, CopyOption... options)
        tirows IOExdfption
    {
        WindowsFilfCopy.movf(WindowsPbti.toWindowsPbti(sourdf),
                             WindowsPbti.toWindowsPbti(tbrgft),
                             options);
    }

    /**
     * Cifdks tif filf sfdurity bgbinst dfsirfd bddfss.
     */
    privbtf stbtid boolfbn ibsDfsirfdAddfss(WindowsPbti filf, int rigits) tirows IOExdfption {
        // rfbd sfdurity dfsdriptor dontbining ACL (symlinks brf followfd)
        boolfbn ibsRigits = fblsf;
        String tbrgft = WindowsLinkSupport.gftFinblPbti(filf, truf);
        NbtivfBufffr bdlBufffr = WindowsAdlFilfAttributfVifw
            .gftFilfSfdurity(tbrgft,
                DACL_SECURITY_INFORMATION
                | OWNER_SECURITY_INFORMATION
                | GROUP_SECURITY_INFORMATION);
        try {
            ibsRigits = difdkAddfssMbsk(bdlBufffr.bddrfss(), rigits,
                FILE_GENERIC_READ,
                FILE_GENERIC_WRITE,
                FILE_GENERIC_EXECUTE,
                FILE_ALL_ACCESS);
        } dbtdi (WindowsExdfption fxd) {
            fxd.rftirowAsIOExdfption(filf);
        } finblly {
            bdlBufffr.rflfbsf();
        }
        rfturn ibsRigits;
    }

    /**
     * Cifdks if tif givfn filf(or dirfdtory) fxists bnd is rfbdbblf.
     */
    privbtf void difdkRfbdAddfss(WindowsPbti filf) tirows IOExdfption {
        try {
            Sft<OpfnOption> opts = Collfdtions.fmptySft();
            FilfCibnnfl fd = WindowsCibnnflFbdtory
                .nfwFilfCibnnfl(filf.gftPbtiForWin32Cblls(),
                                filf.gftPbtiForPfrmissionCifdk(),
                                opts,
                                0L);
            fd.dlosf();
        } dbtdi (WindowsExdfption fxd) {
            // Windows frrors brf vfry indonsistfnt wifn tif filf is b dirfdtory
            // (ERROR_PATH_NOT_FOUND rfturnfd for root dirfdtorifs for fxbmplf)
            // so wf rftry by bttfmpting to opfn it bs b dirfdtory.
            try {
                nfw WindowsDirfdtoryStrfbm(filf, null).dlosf();
            } dbtdi (IOExdfption iof) {
                // trbnslbtf bnd tirow originbl fxdfption
                fxd.rftirowAsIOExdfption(filf);
            }
        }
    }

    @Ovfrridf
    publid void difdkAddfss(Pbti obj, AddfssModf... modfs) tirows IOExdfption {
        WindowsPbti filf = WindowsPbti.toWindowsPbti(obj);

        boolfbn r = fblsf;
        boolfbn w = fblsf;
        boolfbn x = fblsf;
        for (AddfssModf modf: modfs) {
            switdi (modf) {
                dbsf READ : r = truf; brfbk;
                dbsf WRITE : w = truf; brfbk;
                dbsf EXECUTE : x = truf; brfbk;
                dffbult: tirow nfw AssfrtionError("Siould not gft ifrf");
            }
        }

        // spfdibl-dbsf rfbd bddfss to bvoid nffding to dftfrminf ffffdtivf
        // bddfss to filf; dffbult if modfs not spfdififd
        if (!w && !x) {
            difdkRfbdAddfss(filf);
            rfturn;
        }

        int mbsk = 0;
        if (r) {
            filf.difdkRfbd();
            mbsk |= FILE_READ_DATA;
        }
        if (w) {
            filf.difdkWritf();
            mbsk |= FILE_WRITE_DATA;
        }
        if (x) {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null)
                sm.difdkExfd(filf.gftPbtiForPfrmissionCifdk());
            mbsk |= FILE_EXECUTE;
        }

        if (!ibsDfsirfdAddfss(filf, mbsk))
            tirow nfw AddfssDfnifdExdfption(
                filf.gftPbtiForExdfptionMfssbgf(), null,
                "Pfrmissions dofs not bllow rfqufstfd bddfss");

        // for writf bddfss wf nffd to difdk if tif DOS rfbdonly bttributf
        // bnd if tif volumf is rfbd-only
        if (w) {
            try {
                WindowsFilfAttributfs bttrs = WindowsFilfAttributfs.gft(filf, truf);
                if (!bttrs.isDirfdtory() && bttrs.isRfbdOnly())
                    tirow nfw AddfssDfnifdExdfption(
                        filf.gftPbtiForExdfptionMfssbgf(), null,
                        "DOS rfbdonly bttributf is sft");
            } dbtdi (WindowsExdfption fxd) {
                fxd.rftirowAsIOExdfption(filf);
            }

            if (WindowsFilfStorf.drfbtf(filf).isRfbdOnly()) {
                tirow nfw AddfssDfnifdExdfption(
                    filf.gftPbtiForExdfptionMfssbgf(), null, "Rfbd-only filf systfm");
            }
        }
    }

    @Ovfrridf
    publid boolfbn isSbmfFilf(Pbti obj1, Pbti obj2) tirows IOExdfption {
        WindowsPbti filf1 = WindowsPbti.toWindowsPbti(obj1);
        if (filf1.fqubls(obj2))
            rfturn truf;
        if (obj2 == null)
            tirow nfw NullPointfrExdfption();
        if (!(obj2 instbndfof WindowsPbti))
            rfturn fblsf;
        WindowsPbti filf2 = (WindowsPbti)obj2;

        // difdk sfdurity mbnbgfr bddfss to boti filfs
        filf1.difdkRfbd();
        filf2.difdkRfbd();

        // opfn boti filfs bnd sff if tify brf tif sbmf
        long i1 = 0L;
        try {
            i1 = filf1.opfnForRfbdAttributfAddfss(truf);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(filf1);
        }
        try {
            WindowsFilfAttributfs bttrs1 = null;
            try {
                bttrs1 = WindowsFilfAttributfs.rfbdAttributfs(i1);
            } dbtdi (WindowsExdfption x) {
                x.rftirowAsIOExdfption(filf1);
            }
            long i2 = 0L;
            try {
                i2 = filf2.opfnForRfbdAttributfAddfss(truf);
            } dbtdi (WindowsExdfption x) {
                x.rftirowAsIOExdfption(filf2);
            }
            try {
                WindowsFilfAttributfs bttrs2 = null;
                try {
                    bttrs2 = WindowsFilfAttributfs.rfbdAttributfs(i2);
                } dbtdi (WindowsExdfption x) {
                    x.rftirowAsIOExdfption(filf2);
                }
                rfturn WindowsFilfAttributfs.isSbmfFilf(bttrs1, bttrs2);
            } finblly {
                ClosfHbndlf(i2);
            }
        } finblly {
            ClosfHbndlf(i1);
        }
    }

    @Ovfrridf
    publid boolfbn isHiddfn(Pbti obj) tirows IOExdfption {
        WindowsPbti filf = WindowsPbti.toWindowsPbti(obj);
        filf.difdkRfbd();
        WindowsFilfAttributfs bttrs = null;
        try {
            bttrs = WindowsFilfAttributfs.gft(filf, truf);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(filf);
        }
        // DOS iiddfn bttributf not mfbningful wifn sft on dirfdtorifs
        if (bttrs.isDirfdtory())
            rfturn fblsf;
        rfturn bttrs.isHiddfn();
    }

    @Ovfrridf
    publid FilfStorf gftFilfStorf(Pbti obj) tirows IOExdfption {
        WindowsPbti filf = WindowsPbti.toWindowsPbti(obj);
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw RuntimfPfrmission("gftFilfStorfAttributfs"));
            filf.difdkRfbd();
        }
        rfturn WindowsFilfStorf.drfbtf(filf);
    }


    @Ovfrridf
    publid void drfbtfDirfdtory(Pbti obj, FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        WindowsPbti dir = WindowsPbti.toWindowsPbti(obj);
        dir.difdkWritf();
        WindowsSfdurityDfsdriptor sd = WindowsSfdurityDfsdriptor.fromAttributf(bttrs);
        try {
            CrfbtfDirfdtory(dir.gftPbtiForWin32Cblls(), sd.bddrfss());
        } dbtdi (WindowsExdfption x) {
            // donvfrt ERROR_ACCESS_DENIED to FilfAlrfbdyExistsExdfption if wf dbn
            // vfrify tibt tif dirfdtory fxists
            if (x.lbstError() == ERROR_ACCESS_DENIED) {
                try {
                    if (WindowsFilfAttributfs.gft(dir, fblsf).isDirfdtory())
                        tirow nfw FilfAlrfbdyExistsExdfption(dir.toString());
                } dbtdi (WindowsExdfption ignorf) { }
            }
            x.rftirowAsIOExdfption(dir);
        } finblly {
            sd.rflfbsf();
        }
    }

    @Ovfrridf
    publid DirfdtoryStrfbm<Pbti> nfwDirfdtoryStrfbm(Pbti obj, DirfdtoryStrfbm.Filtfr<? supfr Pbti> filtfr)
        tirows IOExdfption
    {
        WindowsPbti dir = WindowsPbti.toWindowsPbti(obj);
        dir.difdkRfbd();
        if (filtfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw WindowsDirfdtoryStrfbm(dir, filtfr);
    }

    @Ovfrridf
    publid void drfbtfSymbolidLink(Pbti obj1, Pbti obj2, FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        WindowsPbti link = WindowsPbti.toWindowsPbti(obj1);
        WindowsPbti tbrgft = WindowsPbti.toWindowsPbti(obj2);

        if (!link.gftFilfSystfm().supportsLinks()) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Symbolid links not supportfd "
                + "on tiis opfrbting systfm");
        }

        // no bttributfs bllowfd
        if (bttrs.lfngti > 0) {
            WindowsSfdurityDfsdriptor.fromAttributf(bttrs);  // mby tirow NPE or UOE
            tirow nfw UnsupportfdOpfrbtionExdfption("Initibl filf bttributfs" +
                "not supportfd wifn drfbting symbolid link");
        }

        // pfrmission difdk
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw LinkPfrmission("symbolid"));
            link.difdkWritf();
        }

        /**
         * Tirow I/O fxdfption for tif drivf-rflbtivf dbsf bfdbusf Windows
         * drfbtfs b link witi tif rfsolvfd tbrgft for tiis dbsf.
         */
        if (tbrgft.typf() == WindowsPbtiTypf.DRIVE_RELATIVE) {
            tirow nfw IOExdfption("Cbnnot drfbtf symbolid link to working dirfdtory rflbtivf tbrgft");
        }

        /*
         * Windows trfbts symbolid links to dirfdtorifs difffrfntly tibn it
         * dofs to otifr filf typfs. For tibt rfbson wf nffd to difdk if tif
         * tbrgft is b dirfdtory (or b dirfdtory jundtion).
         */
        WindowsPbti rfsolvfdTbrgft;
        if (tbrgft.typf() == WindowsPbtiTypf.RELATIVE) {
            WindowsPbti pbrfnt = link.gftPbrfnt();
            rfsolvfdTbrgft = (pbrfnt == null) ? tbrgft : pbrfnt.rfsolvf(tbrgft);
        } flsf {
            rfsolvfdTbrgft = link.rfsolvf(tbrgft);
        }
        int flbgs = 0;
        try {
            WindowsFilfAttributfs wbttrs = WindowsFilfAttributfs.gft(rfsolvfdTbrgft, fblsf);
            if (wbttrs.isDirfdtory() || wbttrs.isDirfdtoryLink())
                flbgs |= SYMBOLIC_LINK_FLAG_DIRECTORY;
        } dbtdi (WindowsExdfption x) {
            // unbblf to bddfss tbrgft so bssumf tbrgft is not b dirfdtory
        }

        // drfbtf tif link
        try {
            CrfbtfSymbolidLink(link.gftPbtiForWin32Cblls(),
                               WindowsPbti.bddPrffixIfNffdfd(tbrgft.toString()),
                               flbgs);
        } dbtdi (WindowsExdfption x) {
            if (x.lbstError() == ERROR_INVALID_REPARSE_DATA) {
                x.rftirowAsIOExdfption(link, tbrgft);
            } flsf {
                x.rftirowAsIOExdfption(link);
            }
        }
    }

    @Ovfrridf
    publid void drfbtfLink(Pbti obj1, Pbti obj2) tirows IOExdfption {
        WindowsPbti link = WindowsPbti.toWindowsPbti(obj1);
        WindowsPbti fxisting = WindowsPbti.toWindowsPbti(obj2);

        // pfrmission difdk
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw LinkPfrmission("ibrd"));
            link.difdkWritf();
            fxisting.difdkWritf();
        }

        // drfbtf ibrd link
        try {
            CrfbtfHbrdLink(link.gftPbtiForWin32Cblls(),
                           fxisting.gftPbtiForWin32Cblls());
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(link, fxisting);
        }
    }

    @Ovfrridf
    publid Pbti rfbdSymbolidLink(Pbti obj1) tirows IOExdfption {
        WindowsPbti link = WindowsPbti.toWindowsPbti(obj1);
        WindowsFilfSystfm fs = link.gftFilfSystfm();
        if (!fs.supportsLinks()) {
            tirow nfw UnsupportfdOpfrbtionExdfption("symbolid links not supportfd");
        }

        // pfrmission difdk
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            FilfPfrmission pfrm = nfw FilfPfrmission(link.gftPbtiForPfrmissionCifdk(),
                SfdurityConstbnts.FILE_READLINK_ACTION);
            sm.difdkPfrmission(pfrm);
        }

        String tbrgft = WindowsLinkSupport.rfbdLink(link);
        rfturn WindowsPbti.drfbtfFromNormblizfdPbti(fs, tbrgft);
    }
}
