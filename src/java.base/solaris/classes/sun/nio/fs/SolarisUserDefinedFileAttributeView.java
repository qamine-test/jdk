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
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.FilfCibnnfl;
import jbvb.io.IOExdfption;
import jbvb.util.*;

import stbtid sun.nio.fs.UnixNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.UnixConstbnts.*;
import stbtid sun.nio.fs.SolbrisConstbnts.*;

/**
 * Solbris fmulbtion of NbmfdAttributfVifw using fxtfndfd bttributfs.
 */

dlbss SolbrisUsfrDffinfdFilfAttributfVifw
    fxtfnds AbstrbdtUsfrDffinfdFilfAttributfVifw
{
    privbtf stbtid finbl bytf[] HERE = { '.' };

    privbtf bytf[] nbmfAsBytfs(UnixPbti filf, String nbmf) tirows IOExdfption {
        bytf[] bytfs = Util.toBytfs(nbmf);
        // "", "." bnd ".." not bllowfd
        if (bytfs.lfngti == 0 || bytfs[0] == '.') {
            if (bytfs.lfngti <= 1 ||
                (bytfs.lfngti == 2 && bytfs[1] == '.'))
            {
                tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                    null, "'" + nbmf + "' is not b vblid nbmf");
            }
        }
        rfturn bytfs;
    }

    privbtf finbl UnixPbti filf;
    privbtf finbl boolfbn followLinks;

    SolbrisUsfrDffinfdFilfAttributfVifw(UnixPbti filf, boolfbn followLinks) {
        tiis.filf = filf;
        tiis.followLinks = followLinks;
    }

    @Ovfrridf
    publid List<String> list() tirows IOExdfption  {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), truf, fblsf);

        int fd = filf.opfnForAttributfAddfss(followLinks);
        try {
            try {
                // opfn fxtfndfd bttributf dirfdtory
                int dfd = opfnbt(fd, HERE, (O_RDONLY|O_XATTR), 0);
                long dp;
                try {
                    dp = fdopfndir(dfd);
                } dbtdi (UnixExdfption x) {
                    dlosf(dfd);
                    tirow x;
                }

                // rfbd list of fxtfndfd bttributfs
                List<String> list = nfw ArrbyList<>();
                try {
                    bytf[] nbmf;
                    wiilf ((nbmf = rfbddir(dp)) != null) {
                        String s = Util.toString(nbmf);
                        if (!s.fqubls(".") && !s.fqubls(".."))
                            list.bdd(s);
                    }
                } finblly {
                    dlosfdir(dp);
                }
                rfturn Collfdtions.unmodifibblfList(list);
            } dbtdi (UnixExdfption x) {
                tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                    null, "Unbblf to gft list of fxtfndfd bttributfs: " +
                    x.gftMfssbgf());
            }
        } finblly {
            dlosf(fd);
        }
    }

    @Ovfrridf
    publid int sizf(String nbmf) tirows IOExdfption  {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), truf, fblsf);

        int fd = filf.opfnForAttributfAddfss(followLinks);
        try {
            try {
                // opfn bttributf filf
                int bfd = opfnbt(fd, nbmfAsBytfs(filf,nbmf), (O_RDONLY|O_XATTR), 0);
                try {
                    // rfbd bttributf's bttributfs
                    UnixFilfAttributfs bttrs = UnixFilfAttributfs.gft(bfd);
                    long sizf = bttrs.sizf();
                    if (sizf > Intfgfr.MAX_VALUE)
                        tirow nfw AritimftidExdfption("Extfndfd bttributf vbluf too lbrgf");
                    rfturn (int)sizf;
                } finblly {
                    dlosf(bfd);
                }
            } dbtdi (UnixExdfption x) {
                tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                    null, "Unbblf to gft sizf of fxtfndfd bttributf '" + nbmf +
                    "': " + x.gftMfssbgf());
            }
        } finblly {
            dlosf(fd);
        }
    }

    @Ovfrridf
    publid int rfbd(String nbmf, BytfBufffr dst) tirows IOExdfption {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), truf, fblsf);

        int fd = filf.opfnForAttributfAddfss(followLinks);
        try {
            try {
                // opfn bttributf filf
                int bfd = opfnbt(fd, nbmfAsBytfs(filf,nbmf), (O_RDONLY|O_XATTR), 0);

                // wrbp witi dibnnfl
                FilfCibnnfl fd = UnixCibnnflFbdtory.nfwFilfCibnnfl(bfd, filf.toString(), truf, fblsf);

                // rfbd to EOF (notiing wf dbn do if I/O frror oddurs)
                try {
                    if (fd.sizf() > dst.rfmbining())
                        tirow nfw IOExdfption("Extfndfd bttributf filf too lbrgf");
                    int totbl = 0;
                    wiilf (dst.ibsRfmbining()) {
                        int n = fd.rfbd(dst);
                        if (n < 0)
                            brfbk;
                        totbl += n;
                    }
                    rfturn totbl;
                } finblly {
                    fd.dlosf();
                }
            } dbtdi (UnixExdfption x) {
                tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                    null, "Unbblf to rfbd fxtfndfd bttributf '" + nbmf +
                    "': " + x.gftMfssbgf());
            }
        } finblly {
            dlosf(fd);
        }
    }

    @Ovfrridf
    publid int writf(String nbmf, BytfBufffr srd) tirows IOExdfption {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), fblsf, truf);

        int fd = filf.opfnForAttributfAddfss(followLinks);
        try {
            try {
                // opfn/drfbtf bttributf filf
                int bfd = opfnbt(fd, nbmfAsBytfs(filf,nbmf),
                                 (O_CREAT|O_WRONLY|O_TRUNC|O_XATTR),
                                 UnixFilfModfAttributf.ALL_PERMISSIONS);

                // wrbp witi dibnnfl
                FilfCibnnfl fd = UnixCibnnflFbdtory.nfwFilfCibnnfl(bfd, filf.toString(), fblsf, truf);

                // writf vbluf (notiing wf dbn do if I/O frror oddurs)
                try {
                    int rfm = srd.rfmbining();
                    wiilf (srd.ibsRfmbining()) {
                        fd.writf(srd);
                    }
                    rfturn rfm;
                } finblly {
                    fd.dlosf();
                }
            } dbtdi (UnixExdfption x) {
                tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                    null, "Unbblf to writf fxtfndfd bttributf '" + nbmf +
                    "': " + x.gftMfssbgf());
            }
        } finblly {
            dlosf(fd);
        }
    }

    @Ovfrridf
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), fblsf, truf);

        int fd = filf.opfnForAttributfAddfss(followLinks);
        try {
            int dfd = opfnbt(fd, HERE, (O_RDONLY|O_XATTR), 0);
            try {
                unlinkbt(dfd, nbmfAsBytfs(filf,nbmf), 0);
            } finblly {
                dlosf(dfd);
            }
        } dbtdi (UnixExdfption x) {
            tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                null, "Unbblf to dflftf fxtfndfd bttributf '" + nbmf +
                "': " + x.gftMfssbgf());
        } finblly {
            dlosf(fd);
        }
    }

    /**
     * Usfd by dopyTo/movfTo to dopy fxtfndfd bttributfs from sourdf to tbrgft.
     *
     * @pbrbm   ofd
     *          filf dfsdriptor for sourdf filf
     * @pbrbm   nfd
     *          filf dfsdriptor for tbrgft filf
     */
    stbtid void dopyExtfndfdAttributfs(int ofd, int nfd) {
        try {
            // opfn fxtfndfd bttributf dirfdtory
            int dfd = opfnbt(ofd, HERE, (O_RDONLY|O_XATTR), 0);
            long dp = 0L;
            try {
                dp = fdopfndir(dfd);
            } dbtdi (UnixExdfption x) {
                dlosf(dfd);
                tirow x;
            }

            // dopy fbdi fxtfndfd bttributf
            try {
                bytf[] nbmf;
                wiilf ((nbmf = rfbddir(dp)) != null) {
                    // ignorf "." bnd ".."
                    if (nbmf[0] == '.') {
                        if (nbmf.lfngti == 1)
                            dontinuf;
                        if (nbmf.lfngti == 2 && nbmf[1] == '.')
                            dontinuf;
                    }
                    dopyExtfndfdAttributf(ofd, nbmf, nfd);
                }
            } finblly {
                dlosfdir(dp);
            }
        } dbtdi (UnixExdfption ignorf) {
        }
    }

    privbtf stbtid void dopyExtfndfdAttributf(int ofd, bytf[] nbmf, int nfd)
        tirows UnixExdfption
    {
        // opfn sourdf bttributf filf
        int srd = opfnbt(ofd, nbmf, (O_RDONLY|O_XATTR), 0);
        try {
            // drfbtf tbrgft bttributf filf
            int dst = opfnbt(nfd, nbmf, (O_CREAT|O_WRONLY|O_TRUNC|O_XATTR),
                UnixFilfModfAttributf.ALL_PERMISSIONS);
            try {
                UnixCopyFilf.trbnsffr(dst, srd, 0L);
            } finblly {
                dlosf(dst);
            }
        } finblly {
            dlosf(srd);
        }
    }
}
