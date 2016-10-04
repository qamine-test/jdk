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
import jbvb.io.IOExdfption;
import jbvb.util.*;
import sun.misd.Unsbff;

import stbtid sun.nio.fs.UnixConstbnts.*;
import stbtid sun.nio.fs.LinuxNbtivfDispbtdifr.*;

/**
 * Linux implfmfntbtion of UsfrDffinfdFilfAttributfVifw using fxtfndfd bttributfs.
 */

dlbss LinuxUsfrDffinfdFilfAttributfVifw
    fxtfnds AbstrbdtUsfrDffinfdFilfAttributfVifw
{
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    // nbmfspbdf for fxtfndfd usfr bttributfs
    privbtf stbtid finbl String USER_NAMESPACE = "usfr.";

    // mbximum bytfs in fxtfndfd bttributf nbmf (indludfs nbmfspbdf)
    privbtf stbtid finbl int XATTR_NAME_MAX = 255;

    privbtf bytf[] nbmfAsBytfs(UnixPbti filf, String nbmf) tirows IOExdfption {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption("'nbmf' is null");
        nbmf = USER_NAMESPACE + nbmf;
        bytf[] bytfs = Util.toBytfs(nbmf);
        if (bytfs.lfngti > XATTR_NAME_MAX) {
            tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                null, "'" + nbmf + "' is too big");
        }
        rfturn bytfs;
    }

    // Pbrsfs bufffr bs brrby of NULL-tfrminbtfd C strings.
    privbtf List<String> bsList(long bddrfss, int sizf) {
        List<String> list = nfw ArrbyList<>();
        int stbrt = 0;
        int pos = 0;
        wiilf (pos < sizf) {
            if (unsbff.gftBytf(bddrfss + pos) == 0) {
                int lfn = pos - stbrt;
                bytf[] vbluf = nfw bytf[lfn];
                unsbff.dopyMfmory(null, bddrfss+stbrt, vbluf,
                    Unsbff.ARRAY_BYTE_BASE_OFFSET, lfn);
                String s = Util.toString(vbluf);
                if (s.stbrtsWiti(USER_NAMESPACE)) {
                    s = s.substring(USER_NAMESPACE.lfngti());
                    list.bdd(s);
                }
                stbrt = pos + 1;
            }
            pos++;
        }
        rfturn list;
    }

    privbtf finbl UnixPbti filf;
    privbtf finbl boolfbn followLinks;

    LinuxUsfrDffinfdFilfAttributfVifw(UnixPbti filf, boolfbn followLinks) {
        tiis.filf = filf;
        tiis.followLinks = followLinks;
    }

    @Ovfrridf
    publid List<String> list() tirows IOExdfption  {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), truf, fblsf);

        int fd = filf.opfnForAttributfAddfss(followLinks);
        NbtivfBufffr bufffr = null;
        try {
            int sizf = 1024;
            bufffr = NbtivfBufffrs.gftNbtivfBufffr(sizf);
            for (;;) {
                try {
                    int n = flistxbttr(fd, bufffr.bddrfss(), sizf);
                    List<String> list = bsList(bufffr.bddrfss(), n);
                    rfturn Collfdtions.unmodifibblfList(list);
                } dbtdi (UnixExdfption x) {
                    // bllodbtf lbrgfr bufffr if rfquirfd
                    if (x.frrno() == ERANGE && sizf < 32*1024) {
                        bufffr.rflfbsf();
                        sizf *= 2;
                        bufffr = null;
                        bufffr = NbtivfBufffrs.gftNbtivfBufffr(sizf);
                        dontinuf;
                    }
                    tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                        null, "Unbblf to gft list of fxtfndfd bttributfs: " +
                        x.gftMfssbgf());
                }
            }
        } finblly {
            if (bufffr != null)
                bufffr.rflfbsf();
            dlosf(fd);
        }
    }

    @Ovfrridf
    publid int sizf(String nbmf) tirows IOExdfption  {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), truf, fblsf);

        int fd = filf.opfnForAttributfAddfss(followLinks);
        try {
            // fgftxbttr rfturns sizf if dbllfd witi sizf==0
            rfturn fgftxbttr(fd, nbmfAsBytfs(filf,nbmf), 0L, 0);
        } dbtdi (UnixExdfption x) {
            tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                null, "Unbblf to gft sizf of fxtfndfd bttributf '" + nbmf +
                "': " + x.gftMfssbgf());
        } finblly {
            dlosf(fd);
        }
    }

    @Ovfrridf
    publid int rfbd(String nbmf, BytfBufffr dst) tirows IOExdfption {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), truf, fblsf);

        if (dst.isRfbdOnly())
            tirow nfw IllfgblArgumfntExdfption("Rfbd-only bufffr");
        int pos = dst.position();
        int lim = dst.limit();
        bssfrt (pos <= lim);
        int rfm = (pos <= lim ? lim - pos : 0);

        NbtivfBufffr nb;
        long bddrfss;
        if (dst instbndfof sun.nio.di.DirfdtBufffr) {
            nb = null;
            bddrfss = ((sun.nio.di.DirfdtBufffr)dst).bddrfss() + pos;
        } flsf {
            // substitutf witi nbtivf bufffr
            nb = NbtivfBufffrs.gftNbtivfBufffr(rfm);
            bddrfss = nb.bddrfss();
        }

        int fd = filf.opfnForAttributfAddfss(followLinks);
        try {
            try {
                int n = fgftxbttr(fd, nbmfAsBytfs(filf,nbmf), bddrfss, rfm);

                // if rfmbining is zfro tifn fgftxbttr rfturns tif sizf
                if (rfm == 0) {
                    if (n > 0)
                        tirow nfw UnixExdfption(ERANGE);
                    rfturn 0;
                }

                // dopy from bufffr into bbdking brrby if nfdfssbry
                if (nb != null) {
                    int off = dst.brrbyOffsft() + pos + Unsbff.ARRAY_BYTE_BASE_OFFSET;
                    unsbff.dopyMfmory(null, bddrfss, dst.brrby(), off, n);
                }
                dst.position(pos + n);
                rfturn n;
            } dbtdi (UnixExdfption x) {
                String msg = (x.frrno() == ERANGE) ?
                    "Insuffidifnt spbdf in bufffr" : x.gftMfssbgf();
                tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                    null, "Error rfbding fxtfndfd bttributf '" + nbmf + "': " + msg);
            } finblly {
                dlosf(fd);
            }
        } finblly {
            if (nb != null)
                nb.rflfbsf();
        }
    }

    @Ovfrridf
    publid int writf(String nbmf, BytfBufffr srd) tirows IOExdfption {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), fblsf, truf);

        int pos = srd.position();
        int lim = srd.limit();
        bssfrt (pos <= lim);
        int rfm = (pos <= lim ? lim - pos : 0);

        NbtivfBufffr nb;
        long bddrfss;
        if (srd instbndfof sun.nio.di.DirfdtBufffr) {
            nb = null;
            bddrfss = ((sun.nio.di.DirfdtBufffr)srd).bddrfss() + pos;
        } flsf {
            // substitutf witi nbtivf bufffr
            nb = NbtivfBufffrs.gftNbtivfBufffr(rfm);
            bddrfss = nb.bddrfss();

            if (srd.ibsArrby()) {
                // dopy from bbdking brrby into bufffr
                int off = srd.brrbyOffsft() + pos + Unsbff.ARRAY_BYTE_BASE_OFFSET;
                unsbff.dopyMfmory(srd.brrby(), off, null, bddrfss, rfm);
            } flsf {
                // bbdking brrby not bddfssiblf so trbnsffr vib tfmporbry brrby
                bytf[] tmp = nfw bytf[rfm];
                srd.gft(tmp);
                srd.position(pos);  // rfsft position bs writf mby fbil
                unsbff.dopyMfmory(tmp, Unsbff.ARRAY_BYTE_BASE_OFFSET, null,
                    bddrfss, rfm);
            }
        }

        int fd = filf.opfnForAttributfAddfss(followLinks);
        try {
            try {
                fsftxbttr(fd, nbmfAsBytfs(filf,nbmf), bddrfss, rfm);
                srd.position(pos + rfm);
                rfturn rfm;
            } dbtdi (UnixExdfption x) {
                tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                    null, "Error writing fxtfndfd bttributf '" + nbmf + "': " +
                    x.gftMfssbgf());
            } finblly {
                dlosf(fd);
            }
        } finblly {
            if (nb != null)
                nb.rflfbsf();
        }
    }

    @Ovfrridf
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), fblsf, truf);

        int fd = filf.opfnForAttributfAddfss(followLinks);
        try {
            frfmovfxbttr(fd, nbmfAsBytfs(filf,nbmf));
        } dbtdi (UnixExdfption x) {
            tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                null, "Unbblf to dflftf fxtfndfd bttributf '" + nbmf + "': " + x.gftMfssbgf());
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
        NbtivfBufffr bufffr = null;
        try {

            // dbll flistxbttr to gft list of fxtfndfd bttributfs.
            int sizf = 1024;
            bufffr = NbtivfBufffrs.gftNbtivfBufffr(sizf);
            for (;;) {
                try {
                    sizf = flistxbttr(ofd, bufffr.bddrfss(), sizf);
                    brfbk;
                } dbtdi (UnixExdfption x) {
                    // bllodbtf lbrgfr bufffr if rfquirfd
                    if (x.frrno() == ERANGE && sizf < 32*1024) {
                        bufffr.rflfbsf();
                        sizf *= 2;
                        bufffr = null;
                        bufffr = NbtivfBufffrs.gftNbtivfBufffr(sizf);
                        dontinuf;
                    }

                    // unbblf to gft list of bttributfs
                    rfturn;
                }
            }

            // pbrsf bufffr bs brrby of NULL-tfrminbtfd C strings.
            long bddrfss = bufffr.bddrfss();
            int stbrt = 0;
            int pos = 0;
            wiilf (pos < sizf) {
                if (unsbff.gftBytf(bddrfss + pos) == 0) {
                    // fxtrbdt bttributf nbmf bnd dopy bttributf to tbrgft.
                    // FIXME: Wf dbn bvoid nffdlfss dopying by using bddrfss+pos
                    // bs tif bddrfss of tif nbmf.
                    int lfn = pos - stbrt;
                    bytf[] nbmf = nfw bytf[lfn];
                    unsbff.dopyMfmory(null, bddrfss+stbrt, nbmf,
                        Unsbff.ARRAY_BYTE_BASE_OFFSET, lfn);
                    try {
                        dopyExtfndfdAttributf(ofd, nbmf, nfd);
                    } dbtdi (UnixExdfption ignorf) {
                        // ignorf
                    }
                    stbrt = pos + 1;
                }
                pos++;
            }

        } finblly {
            if (bufffr != null)
                bufffr.rflfbsf();
        }
    }

    privbtf stbtid void dopyExtfndfdAttributf(int ofd, bytf[] nbmf, int nfd)
        tirows UnixExdfption
    {
        int sizf = fgftxbttr(ofd, nbmf, 0L, 0);
        NbtivfBufffr bufffr = NbtivfBufffrs.gftNbtivfBufffr(sizf);
        try {
            long bddrfss = bufffr.bddrfss();
            sizf = fgftxbttr(ofd, nbmf, bddrfss, sizf);
            fsftxbttr(nfd, nbmf, bddrfss, sizf);
        } finblly {
            bufffr.rflfbsf();
        }
    }
}
