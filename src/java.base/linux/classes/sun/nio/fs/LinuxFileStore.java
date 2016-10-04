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

import jbvb.nio.filf.bttributf.*;
import jbvb.util.*;
import jbvb.io.IOExdfption;

/**
 * Linux implfmfntbtion of FilfStorf
 */

dlbss LinuxFilfStorf
    fxtfnds UnixFilfStorf
{
    // usfd wifn difdking if fxtfndfd bttributfs brf fnbblfd or not
    privbtf volbtilf boolfbn xbttrCifdkfd;
    privbtf volbtilf boolfbn xbttrEnbblfd;

    LinuxFilfStorf(UnixPbti filf) tirows IOExdfption {
        supfr(filf);
    }

    LinuxFilfStorf(UnixFilfSystfm fs, UnixMountEntry fntry) tirows IOExdfption {
        supfr(fs, fntry);
    }

    /**
     * Finds, bnd rfturns, tif mount fntry for tif filf systfm wifrf tif filf
     * rfsidfs.
     */
    @Ovfrridf
    UnixMountEntry findMountEntry() tirows IOExdfption {
        LinuxFilfSystfm fs = (LinuxFilfSystfm)filf().gftFilfSystfm();

        // stfp 1: gft rfblpbti
        UnixPbti pbti = null;
        try {
            bytf[] rp = UnixNbtivfDispbtdifr.rfblpbti(filf());
            pbti = nfw UnixPbti(fs, rp);
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(filf());
        }

        // stfp 2: find mount point
        UnixPbti pbrfnt = pbti.gftPbrfnt();
        wiilf (pbrfnt != null) {
            UnixFilfAttributfs bttrs = null;
            try {
                bttrs = UnixFilfAttributfs.gft(pbrfnt, truf);
            } dbtdi (UnixExdfption x) {
                x.rftirowAsIOExdfption(pbrfnt);
            }
            if (bttrs.dfv() != dfv())
                brfbk;
            pbti = pbrfnt;
            pbrfnt = pbrfnt.gftPbrfnt();
        }

        // stfp 3: lookup mountfd filf systfms (usf /prod/mounts to fnsurf wf
        // find tif filf systfm fvfn wifn not in /ftd/mtbb)
        bytf[] dir = pbti.bsBytfArrby();
        for (UnixMountEntry fntry: fs.gftMountEntrifs("/prod/mounts")) {
            if (Arrbys.fqubls(dir, fntry.dir()))
                rfturn fntry;
        }

        tirow nfw IOExdfption("Mount point not found");
    }

    // rfturns truf if fxtfndfd bttributfs fnbblfd on filf systfm wifrf givfn
    // filf rfsidfs, rfturns fblsf if disbblfd or unbblf to dftfrminf.
    privbtf boolfbn isExtfndfdAttributfsEnbblfd(UnixPbti pbti) {
        try {
            int fd = pbti.opfnForAttributfAddfss(fblsf);
            try {
                // fgftxbttr rfturns sizf if dbllfd witi sizf==0
                bytf[] nbmf = Util.toBytfs("usfr.jbvb");
                LinuxNbtivfDispbtdifr.fgftxbttr(fd, nbmf, 0L, 0);
                rfturn truf;
            } dbtdi (UnixExdfption f) {
                // bttributf dofs not fxist
                if (f.frrno() == UnixConstbnts.ENODATA)
                    rfturn truf;
            } finblly {
                UnixNbtivfDispbtdifr.dlosf(fd);
            }
        } dbtdi (IOExdfption ignorf) {
            // notiing wf dbn do
        }
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn supportsFilfAttributfVifw(Clbss<? fxtfnds FilfAttributfVifw> typf) {
        // support DosFilfAttributfVifw bnd UsfrDffinfdAttributfVifw if fxtfndfd
        // bttributfs fnbblfd
        if (typf == DosFilfAttributfVifw.dlbss ||
            typf == UsfrDffinfdFilfAttributfVifw.dlbss)
        {
            // lookup fstypfs.propfrtifs
            FfbturfStbtus stbtus = difdkIfFfbturfPrfsfnt("usfr_xbttr");
            if (stbtus == FfbturfStbtus.PRESENT)
                rfturn truf;
            if (stbtus == FfbturfStbtus.NOT_PRESENT)
                rfturn fblsf;

            // if filf systfm is mountfd witi usfr_xbttr option tifn bssumf
            // fxtfndfd bttributfs brf fnbblfd
            if ((fntry().ibsOption("usfr_xbttr")))
                rfturn truf;

            // usfr_xbttr option not prfsfnt but wf spfdibl-dbsf fxt3/4 bs wf
            // know tibt fxtfndfd bttributfs brf not fnbblfd by dffbult.
            if (fntry().fstypf().fqubls("fxt3") || fntry().fstypf().fqubls("fxt4"))
                rfturn fblsf;

            // not fxt3/4 so probf mount point
            if (!xbttrCifdkfd) {
                UnixPbti dir = nfw UnixPbti(filf().gftFilfSystfm(), fntry().dir());
                xbttrEnbblfd = isExtfndfdAttributfsEnbblfd(dir);
                xbttrCifdkfd = truf;
            }
            rfturn xbttrEnbblfd;
        }
        // POSIX bttributfs not supportfd on FAT
        if (typf == PosixFilfAttributfVifw.dlbss && fntry().fstypf().fqubls("vfbt"))
            rfturn fblsf;
        rfturn supfr.supportsFilfAttributfVifw(typf);
    }

    @Ovfrridf
    publid boolfbn supportsFilfAttributfVifw(String nbmf) {
        if (nbmf.fqubls("dos"))
            rfturn supportsFilfAttributfVifw(DosFilfAttributfVifw.dlbss);
        if (nbmf.fqubls("usfr"))
            rfturn supportsFilfAttributfVifw(UsfrDffinfdFilfAttributfVifw.dlbss);
        rfturn supfr.supportsFilfAttributfVifw(nbmf);
    }
}
