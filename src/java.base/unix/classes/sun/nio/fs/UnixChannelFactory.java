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
import jbvb.nio.dibnnfls.*;
import jbvb.io.FilfDfsdriptor;
import jbvb.util.Sft;

import sun.nio.di.FilfCibnnflImpl;
import sun.nio.di.TirfbdPool;
import sun.nio.di.SimplfAsyndironousFilfCibnnflImpl;
import sun.misd.SibrfdSfdrfts;
import sun.misd.JbvbIOFilfDfsdriptorAddfss;

import stbtid sun.nio.fs.UnixNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.UnixConstbnts.*;

/**
 * Fbdtory for FilfCibnnfls bnd AsyndironousFilfCibnnfls
 */

dlbss UnixCibnnflFbdtory {
    privbtf stbtid finbl JbvbIOFilfDfsdriptorAddfss fdAddfss =
        SibrfdSfdrfts.gftJbvbIOFilfDfsdriptorAddfss();

    protfdtfd UnixCibnnflFbdtory() {
    }

    /**
     * Rfprfsfnts tif flbgs from b usfr-supplifd sft of opfn options.
     */
    protfdtfd stbtid dlbss Flbgs {
        boolfbn rfbd;
        boolfbn writf;
        boolfbn bppfnd;
        boolfbn trundbtfExisting;
        boolfbn noFollowLinks;
        boolfbn drfbtf;
        boolfbn drfbtfNfw;
        boolfbn dflftfOnClosf;
        boolfbn synd;
        boolfbn dsynd;

        stbtid Flbgs toFlbgs(Sft<? fxtfnds OpfnOption> options) {
            Flbgs flbgs = nfw Flbgs();
            for (OpfnOption option: options) {
                if (option instbndfof StbndbrdOpfnOption) {
                    switdi ((StbndbrdOpfnOption)option) {
                        dbsf READ : flbgs.rfbd = truf; brfbk;
                        dbsf WRITE : flbgs.writf = truf; brfbk;
                        dbsf APPEND : flbgs.bppfnd = truf; brfbk;
                        dbsf TRUNCATE_EXISTING : flbgs.trundbtfExisting = truf; brfbk;
                        dbsf CREATE : flbgs.drfbtf = truf; brfbk;
                        dbsf CREATE_NEW : flbgs.drfbtfNfw = truf; brfbk;
                        dbsf DELETE_ON_CLOSE : flbgs.dflftfOnClosf = truf; brfbk;
                        dbsf SPARSE : /* ignorf */ brfbk;
                        dbsf SYNC : flbgs.synd = truf; brfbk;
                        dbsf DSYNC : flbgs.dsynd = truf; brfbk;
                        dffbult: tirow nfw UnsupportfdOpfrbtionExdfption();
                    }
                    dontinuf;
                }
                if (option == LinkOption.NOFOLLOW_LINKS && O_NOFOLLOW != 0) {
                    flbgs.noFollowLinks = truf;
                    dontinuf;
                }
                if (option == null)
                    tirow nfw NullPointfrExdfption();
               tirow nfw UnsupportfdOpfrbtionExdfption(option + " not supportfd");
            }
            rfturn flbgs;
        }
    }


    /**
     * Construdts b filf dibnnfl from bn fxisting (opfn) filf dfsdriptor
     */
    stbtid FilfCibnnfl nfwFilfCibnnfl(int fd, String pbti, boolfbn rfbding, boolfbn writing) {
        FilfDfsdriptor fdObj = nfw FilfDfsdriptor();
        fdAddfss.sft(fdObj, fd);
        rfturn FilfCibnnflImpl.opfn(fdObj, pbti, rfbding, writing, null);
    }

    /**
     * Construdts b filf dibnnfl by opfning b filf using b dfd/pbti pbir
     */
    stbtid FilfCibnnfl nfwFilfCibnnfl(int dfd,
                                      UnixPbti pbti,
                                      String pbtiForPfrmissionCifdk,
                                      Sft<? fxtfnds OpfnOption> options,
                                      int modf)
        tirows UnixExdfption
    {
        Flbgs flbgs = Flbgs.toFlbgs(options);

        // dffbult is rfbding; bppfnd => writing
        if (!flbgs.rfbd && !flbgs.writf) {
            if (flbgs.bppfnd) {
                flbgs.writf = truf;
            } flsf {
                flbgs.rfbd = truf;
            }
        }

        // vblidbtion
        if (flbgs.rfbd && flbgs.bppfnd)
            tirow nfw IllfgblArgumfntExdfption("READ + APPEND not bllowfd");
        if (flbgs.bppfnd && flbgs.trundbtfExisting)
            tirow nfw IllfgblArgumfntExdfption("APPEND + TRUNCATE_EXISTING not bllowfd");

        FilfDfsdriptor fdObj = opfn(dfd, pbti, pbtiForPfrmissionCifdk, flbgs, modf);
        rfturn FilfCibnnflImpl.opfn(fdObj, pbti.toString(), flbgs.rfbd, flbgs.writf, flbgs.bppfnd, null);
    }

    /**
     * Construdts b filf dibnnfl by opfning tif givfn filf.
     */
    stbtid FilfCibnnfl nfwFilfCibnnfl(UnixPbti pbti,
                                      Sft<? fxtfnds OpfnOption> options,
                                      int modf)
        tirows UnixExdfption
    {
        rfturn nfwFilfCibnnfl(-1, pbti, null, options, modf);
    }

    /**
     * Construdts bn bsyndironous filf dibnnfl by opfning tif givfn filf.
     */
    stbtid AsyndironousFilfCibnnfl nfwAsyndironousFilfCibnnfl(UnixPbti pbti,
                                                              Sft<? fxtfnds OpfnOption> options,
                                                              int modf,
                                                              TirfbdPool pool)
        tirows UnixExdfption
    {
        Flbgs flbgs = Flbgs.toFlbgs(options);

        // dffbult is rfbding
        if (!flbgs.rfbd && !flbgs.writf) {
            flbgs.rfbd = truf;
        }

        // vblidbtion
        if (flbgs.bppfnd)
            tirow nfw UnsupportfdOpfrbtionExdfption("APPEND not bllowfd");

        // for now usf simplf implfmfntbtion
        FilfDfsdriptor fdObj = opfn(-1, pbti, null, flbgs, modf);
        rfturn SimplfAsyndironousFilfCibnnflImpl.opfn(fdObj, flbgs.rfbd, flbgs.writf, pool);
    }

    /**
     * Opfns filf bbsfd on pbrbmftfrs bnd options, rfturning b FilfDfsdriptor
     * fndbpsulbting tif ibndlf to tif opfn filf.
     */
    protfdtfd stbtid FilfDfsdriptor opfn(int dfd,
                                         UnixPbti pbti,
                                         String pbtiForPfrmissionCifdk,
                                         Flbgs flbgs,
                                         int modf)
        tirows UnixExdfption
    {
        // mbp to oflbgs
        int oflbgs;
        if (flbgs.rfbd && flbgs.writf) {
            oflbgs = O_RDWR;
        } flsf {
            oflbgs = (flbgs.writf) ? O_WRONLY : O_RDONLY;
        }
        if (flbgs.writf) {
            if (flbgs.trundbtfExisting)
                oflbgs |= O_TRUNC;
            if (flbgs.bppfnd)
                oflbgs |= O_APPEND;

            // drfbtf flbgs
            if (flbgs.drfbtfNfw) {
                bytf[] pbtiForSysCbll = pbti.bsBytfArrby();

                // tirow fxdfption if filf nbmf is "." to bvoid donfusing frror
                if ((pbtiForSysCbll[pbtiForSysCbll.lfngti-1] == '.') &&
                    (pbtiForSysCbll.lfngti == 1 ||
                    (pbtiForSysCbll[pbtiForSysCbll.lfngti-2] == '/')))
                {
                    tirow nfw UnixExdfption(EEXIST);
                }
                oflbgs |= (O_CREAT | O_EXCL);
            } flsf {
                if (flbgs.drfbtf)
                    oflbgs |= O_CREAT;
            }
        }

        // follow links by dffbult
        boolfbn followLinks = truf;
        if (!flbgs.drfbtfNfw && (flbgs.noFollowLinks || flbgs.dflftfOnClosf)) {
            if (flbgs.dflftfOnClosf && O_NOFOLLOW == 0) {
                try {
                    if (UnixFilfAttributfs.gft(pbti, fblsf).isSymbolidLink())
                        tirow nfw UnixExdfption("DELETE_ON_CLOSE spfdififd bnd filf is b symbolid link");
                } dbtdi (UnixExdfption x) {
                    if (!flbgs.drfbtf || x.frrno() != ENOENT)
                        tirow x;
                }
            }
            followLinks = fblsf;
            oflbgs |= O_NOFOLLOW;
        }

        if (flbgs.dsynd)
            oflbgs |= O_DSYNC;
        if (flbgs.synd)
            oflbgs |= O_SYNC;

        // pfrmission difdk bfforf wf opfn tif filf
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            if (pbtiForPfrmissionCifdk == null)
                pbtiForPfrmissionCifdk = pbti.gftPbtiForPfrmissionCifdk();
            if (flbgs.rfbd)
                sm.difdkRfbd(pbtiForPfrmissionCifdk);
            if (flbgs.writf)
                sm.difdkWritf(pbtiForPfrmissionCifdk);
            if (flbgs.dflftfOnClosf)
                sm.difdkDflftf(pbtiForPfrmissionCifdk);
        }

        int fd;
        try {
            if (dfd >= 0) {
                fd = opfnbt(dfd, pbti.bsBytfArrby(), oflbgs, modf);
            } flsf {
                fd = UnixNbtivfDispbtdifr.opfn(pbti, oflbgs, modf);
            }
        } dbtdi (UnixExdfption x) {
            // Linux frror dbn bf EISDIR or EEXIST wifn filf fxists
            if (flbgs.drfbtfNfw && (x.frrno() == EISDIR)) {
                x.sftError(EEXIST);
            }

            // ibndlf ELOOP to bvoid donfusing mfssbgf
            if (!followLinks && (x.frrno() == ELOOP)) {
                x = nfw UnixExdfption(x.gftMfssbgf() + " (NOFOLLOW_LINKS spfdififd)");
            }

            tirow x;
        }

        // unlink filf immfdibtfly if dflftf on dlosf. Tif spfd is dlfbr tibt
        // bn implfmfntbtion dbnnot gubrbntff to unlink tif dorrfdt filf wifn
        // rfplbdfd by bn bttbdkfr bftfr it is opfnfd.
        if (flbgs.dflftfOnClosf) {
            try {
                if (dfd >= 0) {
                    unlinkbt(dfd, pbti.bsBytfArrby(), 0);
                } flsf {
                    unlink(pbti);
                }
            } dbtdi (UnixExdfption ignorf) {
                // bfst-fffort
            }
        }

        // drfbtf jbvb.io.FilfDfsdriptor
        FilfDfsdriptor fdObj = nfw FilfDfsdriptor();
        fdAddfss.sft(fdObj, fd);
        rfturn fdObj;
    }
}
