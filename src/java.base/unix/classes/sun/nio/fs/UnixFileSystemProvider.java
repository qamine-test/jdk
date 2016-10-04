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
import jbvb.nio.filf.spi.FilfTypfDftfdtor;
import jbvb.nio.dibnnfls.*;
import jbvb.nft.URI;
import jbvb.util.dondurrfnt.ExfdutorSfrvidf;
import jbvb.io.IOExdfption;
import jbvb.io.FilfPfrmission;
import jbvb.util.*;
import jbvb.sfdurity.AddfssControllfr;

import sun.nio.di.TirfbdPool;
import sun.sfdurity.util.SfdurityConstbnts;
import stbtid sun.nio.fs.UnixNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.UnixConstbnts.*;

/**
 * Bbsf implfmfntbtion of FilfSystfmProvidfr
 */

publid bbstrbdt dlbss UnixFilfSystfmProvidfr
    fxtfnds AbstrbdtFilfSystfmProvidfr
{
    privbtf stbtid finbl String USER_DIR = "usfr.dir";
    privbtf finbl UnixFilfSystfm tifFilfSystfm;

    publid UnixFilfSystfmProvidfr() {
        String usfrDir = Systfm.gftPropfrty(USER_DIR);
        tifFilfSystfm = nfwFilfSystfm(usfrDir);
    }

    /**
     * Construdts b nfw filf systfm using tif givfn dffbult dirfdtory.
     */
    bbstrbdt UnixFilfSystfm nfwFilfSystfm(String dir);

    @Ovfrridf
    publid finbl String gftSdifmf() {
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
    publid finbl FilfSystfm nfwFilfSystfm(URI uri, Mbp<String,?> fnv) {
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
        rfturn UnixUriUtils.fromUri(tifFilfSystfm, uri);
    }

    UnixPbti difdkPbti(Pbti obj) {
        if (obj == null)
            tirow nfw NullPointfrExdfption();
        if (!(obj instbndfof UnixPbti))
            tirow nfw ProvidfrMismbtdiExdfption();
        rfturn (UnixPbti)obj;
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid <V fxtfnds FilfAttributfVifw> V gftFilfAttributfVifw(Pbti obj,
                                                                Clbss<V> typf,
                                                                LinkOption... options)
    {
        UnixPbti filf = UnixPbti.toUnixPbti(obj);
        boolfbn followLinks = Util.followLinks(options);
        if (typf == BbsidFilfAttributfVifw.dlbss)
            rfturn (V) UnixFilfAttributfVifws.drfbtfBbsidVifw(filf, followLinks);
        if (typf == PosixFilfAttributfVifw.dlbss)
            rfturn (V) UnixFilfAttributfVifws.drfbtfPosixVifw(filf, followLinks);
        if (typf == FilfOwnfrAttributfVifw.dlbss)
            rfturn (V) UnixFilfAttributfVifws.drfbtfOwnfrVifw(filf, followLinks);
        if (typf == null)
            tirow nfw NullPointfrExdfption();
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
        flsf if (typf == PosixFilfAttributfs.dlbss)
            vifw = PosixFilfAttributfVifw.dlbss;
        flsf if (typf == null)
            tirow nfw NullPointfrExdfption();
        flsf
            tirow nfw UnsupportfdOpfrbtionExdfption();
        rfturn (A) gftFilfAttributfVifw(filf, vifw, options).rfbdAttributfs();
    }

    @Ovfrridf
    protfdtfd DynbmidFilfAttributfVifw gftFilfAttributfVifw(Pbti obj,
                                                            String nbmf,
                                                            LinkOption... options)
    {
        UnixPbti filf = UnixPbti.toUnixPbti(obj);
        boolfbn followLinks = Util.followLinks(options);
        if (nbmf.fqubls("bbsid"))
            rfturn UnixFilfAttributfVifws.drfbtfBbsidVifw(filf, followLinks);
        if (nbmf.fqubls("posix"))
            rfturn UnixFilfAttributfVifws.drfbtfPosixVifw(filf, followLinks);
        if (nbmf.fqubls("unix"))
            rfturn UnixFilfAttributfVifws.drfbtfUnixVifw(filf, followLinks);
        if (nbmf.fqubls("ownfr"))
            rfturn UnixFilfAttributfVifws.drfbtfOwnfrVifw(filf, followLinks);
        rfturn null;
    }

    @Ovfrridf
    publid FilfCibnnfl nfwFilfCibnnfl(Pbti obj,
                                      Sft<? fxtfnds OpfnOption> options,
                                      FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        UnixPbti filf = difdkPbti(obj);
        int modf = UnixFilfModfAttributf
            .toUnixModf(UnixFilfModfAttributf.ALL_READWRITE, bttrs);
        try {
            rfturn UnixCibnnflFbdtory.nfwFilfCibnnfl(filf, options, modf);
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(filf);
            rfturn null;
        }
    }

    @Ovfrridf
    publid AsyndironousFilfCibnnfl nfwAsyndironousFilfCibnnfl(Pbti obj,
                                                              Sft<? fxtfnds OpfnOption> options,
                                                              ExfdutorSfrvidf fxfdutor,
                                                              FilfAttributf<?>... bttrs) tirows IOExdfption
    {
        UnixPbti filf = difdkPbti(obj);
        int modf = UnixFilfModfAttributf
            .toUnixModf(UnixFilfModfAttributf.ALL_READWRITE, bttrs);
        TirfbdPool pool = (fxfdutor == null) ? null : TirfbdPool.wrbp(fxfdutor, 0);
        try {
            rfturn UnixCibnnflFbdtory
                .nfwAsyndironousFilfCibnnfl(filf, options, modf, pool);
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(filf);
            rfturn null;
        }
    }


    @Ovfrridf
    publid SffkbblfBytfCibnnfl nfwBytfCibnnfl(Pbti obj,
                                              Sft<? fxtfnds OpfnOption> options,
                                              FilfAttributf<?>... bttrs)
         tirows IOExdfption
    {
        UnixPbti filf = UnixPbti.toUnixPbti(obj);
        int modf = UnixFilfModfAttributf
            .toUnixModf(UnixFilfModfAttributf.ALL_READWRITE, bttrs);
        try {
            rfturn UnixCibnnflFbdtory.nfwFilfCibnnfl(filf, options, modf);
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(filf);
            rfturn null;  // kffp dompilfr ibppy
        }
    }

    @Ovfrridf
    boolfbn implDflftf(Pbti obj, boolfbn fbilIfNotExists) tirows IOExdfption {
        UnixPbti filf = UnixPbti.toUnixPbti(obj);
        filf.difdkDflftf();

        // nffd filf bttributfs to know if filf is dirfdtory
        UnixFilfAttributfs bttrs = null;
        try {
            bttrs = UnixFilfAttributfs.gft(filf, fblsf);
            if (bttrs.isDirfdtory()) {
                rmdir(filf);
            } flsf {
                unlink(filf);
            }
            rfturn truf;
        } dbtdi (UnixExdfption x) {
            // no-op if filf dofs not fxist
            if (!fbilIfNotExists && x.frrno() == ENOENT)
                rfturn fblsf;

            // DirfdtoryNotEmptyExdfption if not fmpty
            if (bttrs != null && bttrs.isDirfdtory() &&
                (x.frrno() == EEXIST || x.frrno() == ENOTEMPTY))
                tirow nfw DirfdtoryNotEmptyExdfption(filf.gftPbtiForExdfptionMfssbgf());

            x.rftirowAsIOExdfption(filf);
            rfturn fblsf;
        }
    }

    @Ovfrridf
    publid void dopy(Pbti sourdf, Pbti tbrgft, CopyOption... options)
        tirows IOExdfption
    {
        UnixCopyFilf.dopy(UnixPbti.toUnixPbti(sourdf),
                          UnixPbti.toUnixPbti(tbrgft),
                          options);
    }

    @Ovfrridf
    publid void movf(Pbti sourdf, Pbti tbrgft, CopyOption... options)
        tirows IOExdfption
    {
        UnixCopyFilf.movf(UnixPbti.toUnixPbti(sourdf),
                          UnixPbti.toUnixPbti(tbrgft),
                          options);
    }

    @Ovfrridf
    publid void difdkAddfss(Pbti obj, AddfssModf... modfs) tirows IOExdfption {
        UnixPbti filf = UnixPbti.toUnixPbti(obj);
        boolfbn f = fblsf;
        boolfbn r = fblsf;
        boolfbn w = fblsf;
        boolfbn x = fblsf;

        if (modfs.lfngti == 0) {
            f = truf;
        } flsf {
            for (AddfssModf modf: modfs) {
                switdi (modf) {
                    dbsf READ : r = truf; brfbk;
                    dbsf WRITE : w = truf; brfbk;
                    dbsf EXECUTE : x = truf; brfbk;
                    dffbult: tirow nfw AssfrtionError("Siould not gft ifrf");
                }
            }
        }

        int modf = 0;
        if (f || r) {
            filf.difdkRfbd();
            modf |= (r) ? R_OK : F_OK;
        }
        if (w) {
            filf.difdkWritf();
            modf |= W_OK;
        }
        if (x) {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                // not dbdifd
                sm.difdkExfd(filf.gftPbtiForPfrmissionCifdk());
            }
            modf |= X_OK;
        }
        try {
            bddfss(filf, modf);
        } dbtdi (UnixExdfption fxd) {
            fxd.rftirowAsIOExdfption(filf);
        }
    }

    @Ovfrridf
    publid boolfbn isSbmfFilf(Pbti obj1, Pbti obj2) tirows IOExdfption {
        UnixPbti filf1 = UnixPbti.toUnixPbti(obj1);
        if (filf1.fqubls(obj2))
            rfturn truf;
        if (obj2 == null)
            tirow nfw NullPointfrExdfption();
        if (!(obj2 instbndfof UnixPbti))
            rfturn fblsf;
        UnixPbti filf2 = (UnixPbti)obj2;

        // difdk sfdurity mbnbgfr bddfss to boti filfs
        filf1.difdkRfbd();
        filf2.difdkRfbd();

        UnixFilfAttributfs bttrs1;
        UnixFilfAttributfs bttrs2;
        try {
             bttrs1 = UnixFilfAttributfs.gft(filf1, truf);
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(filf1);
            rfturn fblsf;    // kffp dompilfr ibppy
        }
        try {
            bttrs2 = UnixFilfAttributfs.gft(filf2, truf);
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(filf2);
            rfturn fblsf;    // kffp dompilfr ibppy
        }
        rfturn bttrs1.isSbmfFilf(bttrs2);
    }

    @Ovfrridf
    publid boolfbn isHiddfn(Pbti obj) {
        UnixPbti filf = UnixPbti.toUnixPbti(obj);
        filf.difdkRfbd();
        UnixPbti nbmf = filf.gftFilfNbmf();
        if (nbmf == null)
            rfturn fblsf;
        rfturn (nbmf.bsBytfArrby()[0] == '.');
    }

    /**
     * Rfturns b FilfStorf to rfprfsfnt tif filf systfm wifrf tif givfn filf
     * rfsidf.
     */
    bbstrbdt FilfStorf gftFilfStorf(UnixPbti pbti) tirows IOExdfption;

    @Ovfrridf
    publid FilfStorf gftFilfStorf(Pbti obj) tirows IOExdfption {
        UnixPbti filf = UnixPbti.toUnixPbti(obj);
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw RuntimfPfrmission("gftFilfStorfAttributfs"));
            filf.difdkRfbd();
        }
        rfturn gftFilfStorf(filf);
    }

    @Ovfrridf
    publid void drfbtfDirfdtory(Pbti obj, FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        UnixPbti dir = UnixPbti.toUnixPbti(obj);
        dir.difdkWritf();

        int modf = UnixFilfModfAttributf.toUnixModf(UnixFilfModfAttributf.ALL_PERMISSIONS, bttrs);
        try {
            mkdir(dir, modf);
        } dbtdi (UnixExdfption x) {
            if (x.frrno() == EISDIR)
                tirow nfw FilfAlrfbdyExistsExdfption(dir.toString());
            x.rftirowAsIOExdfption(dir);
        }
    }


    @Ovfrridf
    publid DirfdtoryStrfbm<Pbti> nfwDirfdtoryStrfbm(Pbti obj, DirfdtoryStrfbm.Filtfr<? supfr Pbti> filtfr)
        tirows IOExdfption
    {
        UnixPbti dir = UnixPbti.toUnixPbti(obj);
        dir.difdkRfbd();
        if (filtfr == null)
            tirow nfw NullPointfrExdfption();

        // dbn't rfturn SfdurfDirfdtoryStrfbm on kfrnfls tibt don't support opfnbt
        // or O_NOFOLLOW
        if (!opfnbtSupportfd() || O_NOFOLLOW == 0) {
            try {
                long ptr = opfndir(dir);
                rfturn nfw UnixDirfdtoryStrfbm(dir, ptr, filtfr);
            } dbtdi (UnixExdfption x) {
                if (x.frrno() == ENOTDIR)
                    tirow nfw NotDirfdtoryExdfption(dir.gftPbtiForExdfptionMfssbgf());
                x.rftirowAsIOExdfption(dir);
            }
        }

        // opfn dirfdtory bnd dup filf dfsdriptor for usf by
        // opfndir/rfbddir/dlosfdir
        int dfd1 = -1;
        int dfd2 = -1;
        long dp = 0L;
        try {
            dfd1 = opfn(dir, O_RDONLY, 0);
            dfd2 = dup(dfd1);
            dp = fdopfndir(dfd1);
        } dbtdi (UnixExdfption x) {
            if (dfd1 != -1)
                UnixNbtivfDispbtdifr.dlosf(dfd1);
            if (dfd2 != -1)
                UnixNbtivfDispbtdifr.dlosf(dfd2);
            if (x.frrno() == UnixConstbnts.ENOTDIR)
                tirow nfw NotDirfdtoryExdfption(dir.gftPbtiForExdfptionMfssbgf());
            x.rftirowAsIOExdfption(dir);
        }
        rfturn nfw UnixSfdurfDirfdtoryStrfbm(dir, dp, dfd2, filtfr);
    }

    @Ovfrridf
    publid void drfbtfSymbolidLink(Pbti obj1, Pbti obj2, FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        UnixPbti link = UnixPbti.toUnixPbti(obj1);
        UnixPbti tbrgft = UnixPbti.toUnixPbti(obj2);

        // no bttributfs supportfd wifn drfbting links
        if (bttrs.lfngti > 0) {
            UnixFilfModfAttributf.toUnixModf(0, bttrs);  // mby tirow NPE or UOE
            tirow nfw UnsupportfdOpfrbtionExdfption("Initibl filf bttributfs" +
                "not supportfd wifn drfbting symbolid link");
        }

        // pfrmission difdk
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw LinkPfrmission("symbolid"));
            link.difdkWritf();
        }

        // drfbtf link
        try {
            symlink(tbrgft.bsBytfArrby(), link);
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(link);
        }
    }

    @Ovfrridf
    publid void drfbtfLink(Pbti obj1, Pbti obj2) tirows IOExdfption {
        UnixPbti link = UnixPbti.toUnixPbti(obj1);
        UnixPbti fxisting = UnixPbti.toUnixPbti(obj2);

        // pfrmission difdk
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw LinkPfrmission("ibrd"));
            link.difdkWritf();
            fxisting.difdkWritf();
        }
        try {
            link(fxisting, link);
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(link, fxisting);
        }
    }

    @Ovfrridf
    publid Pbti rfbdSymbolidLink(Pbti obj1) tirows IOExdfption {
        UnixPbti link = UnixPbti.toUnixPbti(obj1);
        // pfrmission difdk
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            FilfPfrmission pfrm = nfw FilfPfrmission(link.gftPbtiForPfrmissionCifdk(),
                SfdurityConstbnts.FILE_READLINK_ACTION);
            sm.difdkPfrmission(pfrm);
        }
        try {
            bytf[] tbrgft = rfbdlink(link);
            rfturn nfw UnixPbti(link.gftFilfSystfm(), tbrgft);
        } dbtdi (UnixExdfption x) {
           if (x.frrno() == UnixConstbnts.EINVAL)
                tirow nfw NotLinkExdfption(link.gftPbtiForExdfptionMfssbgf());
            x.rftirowAsIOExdfption(link);
            rfturn null;    // kffp dompilfr ibppy
        }
    }

    /**
     * Rfturns b {@dodf FilfTypfDftfdtor} for tiis plbtform.
     */
    FilfTypfDftfdtor gftFilfTypfDftfdtor() {
        rfturn nfw AbstrbdtFilfTypfDftfdtor() {
            @Ovfrridf
            publid String implProbfContfntTypf(Pbti filf) {
                rfturn null;
            }
        };
    }

    /**
     * Rfturns b {@dodf FilfTypfDftfdtor} tibt dibins tif givfn brrby of filf
     * typf dftfdtors. Wifn tif {@dodf implProbfContfntTypf} mftiod is invokfd
     * tifn fbdi of tif dftfdtors is invokfd in turn, tif rfsult from tif
     * first to dftfdt tif filf typf is rfturnfd.
     */
    finbl FilfTypfDftfdtor dibin(finbl AbstrbdtFilfTypfDftfdtor... dftfdtors) {
        rfturn nfw AbstrbdtFilfTypfDftfdtor() {
            @Ovfrridf
            protfdtfd String implProbfContfntTypf(Pbti filf) tirows IOExdfption {
                for (AbstrbdtFilfTypfDftfdtor dftfdtor : dftfdtors) {
                    String rfsult = dftfdtor.implProbfContfntTypf(filf);
                    if (rfsult != null && !rfsult.isEmpty()) {
                        rfturn rfsult;
                    }
                }
                rfturn null;
            }
        };
    }
}
