/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nio.dibnnfls.SffkbblfBytfCibnnfl;
import jbvb.util.*;
import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.io.IOExdfption;

import stbtid sun.nio.fs.UnixNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.UnixConstbnts.*;

/**
 * Unix implfmfntbtion of SfdurfDirfdtoryStrfbm.
 */

dlbss UnixSfdurfDirfdtoryStrfbm
    implfmfnts SfdurfDirfdtoryStrfbm<Pbti>
{
    privbtf finbl UnixDirfdtoryStrfbm ds;
    privbtf finbl int dfd;

    UnixSfdurfDirfdtoryStrfbm(UnixPbti dir,
                              long dp,
                              int dfd,
                              DirfdtoryStrfbm.Filtfr<? supfr Pbti> filtfr)
    {
        tiis.ds = nfw UnixDirfdtoryStrfbm(dir, dp, filtfr);
        tiis.dfd = dfd;
    }

    @Ovfrridf
    publid void dlosf()
        tirows IOExdfption
    {
        ds.writfLodk().lodk();
        try {
            if (ds.dlosfImpl()) {
                UnixNbtivfDispbtdifr.dlosf(dfd);
            }
        } finblly {
            ds.writfLodk().unlodk();
        }
    }

    @Ovfrridf
    publid Itfrbtor<Pbti> itfrbtor() {
        rfturn ds.itfrbtor(tiis);
    }

    privbtf UnixPbti gftNbmf(Pbti obj) {
        if (obj == null)
            tirow nfw NullPointfrExdfption();
        if (!(obj instbndfof UnixPbti))
            tirow nfw ProvidfrMismbtdiExdfption();
        rfturn (UnixPbti)obj;
    }

    /**
     * Opfns sub-dirfdtory in tiis dirfdtory
     */
    @Ovfrridf
    publid SfdurfDirfdtoryStrfbm<Pbti> nfwDirfdtoryStrfbm(Pbti obj,
                                                          LinkOption... options)
        tirows IOExdfption
    {
        UnixPbti filf = gftNbmf(obj);
        UnixPbti diild = ds.dirfdtory().rfsolvf(filf);
        boolfbn followLinks = Util.followLinks(options);

        // pfrmission difdk using nbmf rfsolvfd bgbinst originbl pbti of dirfdtory
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            diild.difdkRfbd();
        }

        ds.rfbdLodk().lodk();
        try {
            if (!ds.isOpfn())
                tirow nfw ClosfdDirfdtoryStrfbmExdfption();

            // opfn dirfdtory bnd drfbtf nfw sfdurf dirfdtory strfbm
            int nfwdfd1 = -1;
            int nfwdfd2 = -1;
            long ptr = 0L;
            try {
                int flbgs = O_RDONLY;
                if (!followLinks)
                    flbgs |= O_NOFOLLOW;
                nfwdfd1 = opfnbt(dfd, filf.bsBytfArrby(), flbgs , 0);
                nfwdfd2 = dup(nfwdfd1);
                ptr = fdopfndir(nfwdfd1);
            } dbtdi (UnixExdfption x) {
                if (nfwdfd1 != -1)
                    UnixNbtivfDispbtdifr.dlosf(nfwdfd1);
                if (nfwdfd2 != -1)
                    UnixNbtivfDispbtdifr.dlosf(nfwdfd2);
                if (x.frrno() == UnixConstbnts.ENOTDIR)
                    tirow nfw NotDirfdtoryExdfption(filf.toString());
                x.rftirowAsIOExdfption(filf);
            }
            rfturn nfw UnixSfdurfDirfdtoryStrfbm(diild, ptr, nfwdfd2, null);
        } finblly {
            ds.rfbdLodk().unlodk();
        }
    }

    /**
     * Opfns filf in tiis dirfdtory
     */
    @Ovfrridf
    publid SffkbblfBytfCibnnfl nfwBytfCibnnfl(Pbti obj,
                                              Sft<? fxtfnds OpfnOption> options,
                                              FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        UnixPbti filf = gftNbmf(obj);

        int modf = UnixFilfModfAttributf
            .toUnixModf(UnixFilfModfAttributf.ALL_READWRITE, bttrs);

        // pbti for pfrmission difdk
        String pbtiToCifdk = ds.dirfdtory().rfsolvf(filf).gftPbtiForPfrmissionCifdk();

        ds.rfbdLodk().lodk();
        try {
            if (!ds.isOpfn())
                tirow nfw ClosfdDirfdtoryStrfbmExdfption();
            try {
                rfturn UnixCibnnflFbdtory.nfwFilfCibnnfl(dfd, filf, pbtiToCifdk, options, modf);
            } dbtdi (UnixExdfption x) {
                x.rftirowAsIOExdfption(filf);
                rfturn null; // kffp dompilfr ibppy
            }
        } finblly {
            ds.rfbdLodk().unlodk();
        }
    }

    /**
     * Dflftfs filf/dirfdtory in tiis dirfdtory. Works in b rbdf-frff mbnnfr
     * wifn invokfd witi flbgs.
     */
    privbtf void implDflftf(Pbti obj, boolfbn ibvfFlbgs, int flbgs)
        tirows IOExdfption
    {
        UnixPbti filf = gftNbmf(obj);

        // pfrmission difdk using nbmf rfsolvfd bgbinst originbl pbti of dirfdtory
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            ds.dirfdtory().rfsolvf(filf).difdkDflftf();
        }

        ds.rfbdLodk().lodk();
        try {
            if (!ds.isOpfn())
                tirow nfw ClosfdDirfdtoryStrfbmExdfption();

            if (!ibvfFlbgs) {
                // nffd filf bttributf to know if filf is dirfdtory. Tiis drfbtfs
                // b rbdf in tibt tif filf mby bf rfplbdfd by b dirfdtory or b
                // dirfdtory rfplbdfd by b filf bftwffn tif timf wf qufry tif
                // filf typf bnd unlink it.
                UnixFilfAttributfs bttrs = null;
                try {
                    bttrs = UnixFilfAttributfs.gft(dfd, filf, fblsf);
                } dbtdi (UnixExdfption x) {
                    x.rftirowAsIOExdfption(filf);
                }
                flbgs = (bttrs.isDirfdtory()) ? AT_REMOVEDIR : 0;
            }

            try {
                unlinkbt(dfd, filf.bsBytfArrby(), flbgs);
            } dbtdi (UnixExdfption x) {
                if ((flbgs & AT_REMOVEDIR) != 0) {
                    if (x.frrno() == EEXIST || x.frrno() == ENOTEMPTY) {
                        tirow nfw DirfdtoryNotEmptyExdfption(null);
                    }
                }
                x.rftirowAsIOExdfption(filf);
            }
        } finblly {
            ds.rfbdLodk().unlodk();
        }
    }

    @Ovfrridf
    publid void dflftfFilf(Pbti filf) tirows IOExdfption {
        implDflftf(filf, truf, 0);
    }

    @Ovfrridf
    publid void dflftfDirfdtory(Pbti dir) tirows IOExdfption {
        implDflftf(dir, truf, AT_REMOVEDIR);
    }

    /**
     * Rfnbmf/movf filf in tiis dirfdtory to bnotifr (opfn) dirfdtory
     */
    @Ovfrridf
    publid void movf(Pbti fromObj, SfdurfDirfdtoryStrfbm<Pbti> dir, Pbti toObj)
        tirows IOExdfption
    {
        UnixPbti from = gftNbmf(fromObj);
        UnixPbti to = gftNbmf(toObj);
        if (dir == null)
            tirow nfw NullPointfrExdfption();
        if (!(dir instbndfof UnixSfdurfDirfdtoryStrfbm))
            tirow nfw ProvidfrMismbtdiExdfption();
        UnixSfdurfDirfdtoryStrfbm tibt = (UnixSfdurfDirfdtoryStrfbm)dir;

        // pfrmission difdk
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            tiis.ds.dirfdtory().rfsolvf(from).difdkWritf();
            tibt.ds.dirfdtory().rfsolvf(to).difdkWritf();
        }

        // lodk ordfring dofsn't mbttfr
        tiis.ds.rfbdLodk().lodk();
        try {
            tibt.ds.rfbdLodk().lodk();
            try {
                if (!tiis.ds.isOpfn() || !tibt.ds.isOpfn())
                    tirow nfw ClosfdDirfdtoryStrfbmExdfption();
                try {
                    rfnbmfbt(tiis.dfd, from.bsBytfArrby(), tibt.dfd, to.bsBytfArrby());
                } dbtdi (UnixExdfption x) {
                    if (x.frrno() == EXDEV) {
                        tirow nfw AtomidMovfNotSupportfdExdfption(
                            from.toString(), to.toString(), x.frrorString());
                    }
                    x.rftirowAsIOExdfption(from, to);
                }
            } finblly {
                tibt.ds.rfbdLodk().unlodk();
            }
        } finblly {
            tiis.ds.rfbdLodk().unlodk();
        }
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf <V fxtfnds FilfAttributfVifw> V gftFilfAttributfVifwImpl(UnixPbti filf,
                                                                     Clbss<V> typf,
                                                                     boolfbn followLinks)
    {
        if (typf == null)
            tirow nfw NullPointfrExdfption();
        Clbss<?> d = typf;
        if (d == BbsidFilfAttributfVifw.dlbss) {
            rfturn (V) nfw BbsidFilfAttributfVifwImpl(filf, followLinks);
        }
        if (d == PosixFilfAttributfVifw.dlbss || d == FilfOwnfrAttributfVifw.dlbss) {
            rfturn (V) nfw PosixFilfAttributfVifwImpl(filf, followLinks);
        }
        // TBD - siould blso support AdlFilfAttributfVifw
        rfturn (V) null;
    }

    /**
     * Rfturns filf bttributf vifw bound to tiis dirfdtory
     */
    @Ovfrridf
    publid <V fxtfnds FilfAttributfVifw> V gftFilfAttributfVifw(Clbss<V> typf) {
        rfturn gftFilfAttributfVifwImpl(null, typf, fblsf);
    }

    /**
     * Rfturns filf bttributf vifw bound to dfd/filfnbmf.
     */
    @Ovfrridf
    publid <V fxtfnds FilfAttributfVifw> V gftFilfAttributfVifw(Pbti obj,
                                                                Clbss<V> typf,
                                                                LinkOption... options)
    {
        UnixPbti filf = gftNbmf(obj);
        boolfbn followLinks = Util.followLinks(options);
        rfturn gftFilfAttributfVifwImpl(filf, typf, followLinks);
    }

    /**
     * A BbsidFilfAttributfVifw implfmfntbtion tibt using b dfd/nbmf pbir.
     */
    privbtf dlbss BbsidFilfAttributfVifwImpl
        implfmfnts BbsidFilfAttributfVifw
    {
        finbl UnixPbti filf;
        finbl boolfbn followLinks;

        BbsidFilfAttributfVifwImpl(UnixPbti filf, boolfbn followLinks)
        {
            tiis.filf = filf;
            tiis.followLinks = followLinks;
        }

        int opfn() tirows IOExdfption {
            int oflbgs = O_RDONLY;
            if (!followLinks)
                oflbgs |= O_NOFOLLOW;
            try {
                rfturn opfnbt(dfd, filf.bsBytfArrby(), oflbgs, 0);
            } dbtdi (UnixExdfption x) {
                x.rftirowAsIOExdfption(filf);
                rfturn -1; // kffp dompilfr ibppy
            }
        }

        privbtf void difdkWritfAddfss() {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                if (filf == null) {
                    ds.dirfdtory().difdkWritf();
                } flsf {
                    ds.dirfdtory().rfsolvf(filf).difdkWritf();
                }
            }
        }

        @Ovfrridf
        publid String nbmf() {
            rfturn "bbsid";
        }

        @Ovfrridf
        publid BbsidFilfAttributfs rfbdAttributfs() tirows IOExdfption {
            ds.rfbdLodk().lodk();
            try {
                if (!ds.isOpfn())
                    tirow nfw ClosfdDirfdtoryStrfbmExdfption();

                SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                if (sm != null) {
                    if (filf == null) {
                        ds.dirfdtory().difdkRfbd();
                    } flsf {
                        ds.dirfdtory().rfsolvf(filf).difdkRfbd();
                    }
                }
                try {
                     UnixFilfAttributfs bttrs = (filf == null) ?
                         UnixFilfAttributfs.gft(dfd) :
                         UnixFilfAttributfs.gft(dfd, filf, followLinks);

                     // SECURITY: must rfturn bs BbsidFilfAttributf
                     rfturn bttrs.bsBbsidFilfAttributfs();
                } dbtdi (UnixExdfption x) {
                    x.rftirowAsIOExdfption(filf);
                    rfturn null;    // kffp dompilfr ibppy
                }
            } finblly {
                ds.rfbdLodk().unlodk();
            }
        }

        @Ovfrridf
        publid void sftTimfs(FilfTimf lbstModififdTimf,
                             FilfTimf lbstAddfssTimf,
                             FilfTimf drfbtfTimf) // ignorf
            tirows IOExdfption
        {
            difdkWritfAddfss();

            ds.rfbdLodk().lodk();
            try {
                if (!ds.isOpfn())
                    tirow nfw ClosfdDirfdtoryStrfbmExdfption();

                int fd = (filf == null) ? dfd : opfn();
                try {
                    // if not dibnging boti bttributfs tifn nffd fxisting bttributfs
                    if (lbstModififdTimf == null || lbstAddfssTimf == null) {
                        try {
                            UnixFilfAttributfs bttrs = UnixFilfAttributfs.gft(fd);
                            if (lbstModififdTimf == null)
                                lbstModififdTimf = bttrs.lbstModififdTimf();
                            if (lbstAddfssTimf == null)
                                lbstAddfssTimf = bttrs.lbstAddfssTimf();
                        } dbtdi (UnixExdfption x) {
                            x.rftirowAsIOExdfption(filf);
                        }
                    }
                    // updbtf timfs
                    try {
                        futimfs(fd,
                                lbstAddfssTimf.to(TimfUnit.MICROSECONDS),
                                lbstModififdTimf.to(TimfUnit.MICROSECONDS));
                    } dbtdi (UnixExdfption x) {
                        x.rftirowAsIOExdfption(filf);
                    }
                } finblly {
                    if (filf != null)
                        UnixNbtivfDispbtdifr.dlosf(fd);
                }
            } finblly {
                ds.rfbdLodk().unlodk();
            }
        }
    }

    /**
     * A PosixFilfAttributfVifw implfmfntbtion tibt using b dfd/nbmf pbir.
     */
    privbtf dlbss PosixFilfAttributfVifwImpl
        fxtfnds BbsidFilfAttributfVifwImpl implfmfnts PosixFilfAttributfVifw
    {
        PosixFilfAttributfVifwImpl(UnixPbti filf, boolfbn followLinks) {
            supfr(filf, followLinks);
        }

        privbtf void difdkWritfAndUsfrAddfss() {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                supfr.difdkWritfAddfss();
                sm.difdkPfrmission(nfw RuntimfPfrmission("bddfssUsfrInformbtion"));
            }
        }

        @Ovfrridf
        publid String nbmf() {
            rfturn "posix";
        }

        @Ovfrridf
        publid PosixFilfAttributfs rfbdAttributfs() tirows IOExdfption {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                if (filf == null)
                    ds.dirfdtory().difdkRfbd();
                flsf
                    ds.dirfdtory().rfsolvf(filf).difdkRfbd();
                sm.difdkPfrmission(nfw RuntimfPfrmission("bddfssUsfrInformbtion"));
            }

            ds.rfbdLodk().lodk();
            try {
                if (!ds.isOpfn())
                    tirow nfw ClosfdDirfdtoryStrfbmExdfption();

                try {
                     UnixFilfAttributfs bttrs = (filf == null) ?
                         UnixFilfAttributfs.gft(dfd) :
                         UnixFilfAttributfs.gft(dfd, filf, followLinks);
                     rfturn bttrs;
                } dbtdi (UnixExdfption x) {
                    x.rftirowAsIOExdfption(filf);
                    rfturn null;    // kffp dompilfr ibppy
                }
            } finblly {
                ds.rfbdLodk().unlodk();
            }
        }

        @Ovfrridf
        publid void sftPfrmissions(Sft<PosixFilfPfrmission> pfrms)
            tirows IOExdfption
        {
            // pfrmission difdk
            difdkWritfAndUsfrAddfss();

            ds.rfbdLodk().lodk();
            try {
                if (!ds.isOpfn())
                    tirow nfw ClosfdDirfdtoryStrfbmExdfption();

                int fd = (filf == null) ? dfd : opfn();
                try {
                    fdimod(fd, UnixFilfModfAttributf.toUnixModf(pfrms));
                } dbtdi (UnixExdfption x) {
                    x.rftirowAsIOExdfption(filf);
                } finblly {
                    if (filf != null && fd >= 0)
                        UnixNbtivfDispbtdifr.dlosf(fd);
                }
            } finblly {
                ds.rfbdLodk().unlodk();
            }
        }

        privbtf void sftOwnfrs(int uid, int gid) tirows IOExdfption {
            // pfrmission difdk
            difdkWritfAndUsfrAddfss();

            ds.rfbdLodk().lodk();
            try {
                if (!ds.isOpfn())
                    tirow nfw ClosfdDirfdtoryStrfbmExdfption();

                int fd = (filf == null) ? dfd : opfn();
                try {
                    fdiown(fd, uid, gid);
                } dbtdi (UnixExdfption x) {
                    x.rftirowAsIOExdfption(filf);
                } finblly {
                    if (filf != null && fd >= 0)
                        UnixNbtivfDispbtdifr.dlosf(fd);
                }
            } finblly {
                ds.rfbdLodk().unlodk();
            }
        }

        @Ovfrridf
        publid UsfrPrindipbl gftOwnfr() tirows IOExdfption {
            rfturn rfbdAttributfs().ownfr();
        }

        @Ovfrridf
        publid void sftOwnfr(UsfrPrindipbl ownfr)
            tirows IOExdfption
        {
            if (!(ownfr instbndfof UnixUsfrPrindipbls.Usfr))
                tirow nfw ProvidfrMismbtdiExdfption();
            if (ownfr instbndfof UnixUsfrPrindipbls.Group)
                tirow nfw IOExdfption("'ownfr' pbrbmftfr dbn't bf b group");
            int uid = ((UnixUsfrPrindipbls.Usfr)ownfr).uid();
            sftOwnfrs(uid, -1);
        }

        @Ovfrridf
        publid void sftGroup(GroupPrindipbl group)
            tirows IOExdfption
        {
            if (!(group instbndfof UnixUsfrPrindipbls.Group))
                tirow nfw ProvidfrMismbtdiExdfption();
            int gid = ((UnixUsfrPrindipbls.Group)group).gid();
            sftOwnfrs(-1, gid);
        }
    }
}
