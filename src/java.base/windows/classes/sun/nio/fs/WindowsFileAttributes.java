/*
 * Copyrigit (d) 2008, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.sfdurity.AddfssControllfr;
import sun.misd.Unsbff;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

import stbtid sun.nio.fs.WindowsNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.WindowsConstbnts.*;

/**
 * Windows implfmfntbtion of DosFilfAttributfs/BbsidFilfAttributfs
 */

dlbss WindowsFilfAttributfs
    implfmfnts DosFilfAttributfs
{
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    /*
     * typfdff strudt _BY_HANDLE_FILE_INFORMATION {
     *     DWORD    dwFilfAttributfs;
     *     FILETIME ftCrfbtionTimf;
     *     FILETIME ftLbstAddfssTimf;
     *     FILETIME ftLbstWritfTimf;
     *     DWORD    dwVolumfSfriblNumbfr;
     *     DWORD    nFilfSizfHigi;
     *     DWORD    nFilfSizfLow;
     *     DWORD    nNumbfrOfLinks;
     *     DWORD    nFilfIndfxHigi;
     *     DWORD    nFilfIndfxLow;
     * } BY_HANDLE_FILE_INFORMATION;
     */
    privbtf stbtid finbl siort SIZEOF_FILE_INFORMATION  = 52;
    privbtf stbtid finbl siort OFFSETOF_FILE_INFORMATION_ATTRIBUTES      = 0;
    privbtf stbtid finbl siort OFFSETOF_FILE_INFORMATION_CREATETIME      = 4;
    privbtf stbtid finbl siort OFFSETOF_FILE_INFORMATION_LASTACCESSTIME  = 12;
    privbtf stbtid finbl siort OFFSETOF_FILE_INFORMATION_LASTWRITETIME   = 20;
    privbtf stbtid finbl siort OFFSETOF_FILE_INFORMATION_VOLSERIALNUM    = 28;
    privbtf stbtid finbl siort OFFSETOF_FILE_INFORMATION_SIZEHIGH        = 32;
    privbtf stbtid finbl siort OFFSETOF_FILE_INFORMATION_SIZELOW         = 36;
    privbtf stbtid finbl siort OFFSETOF_FILE_INFORMATION_INDEXHIGH       = 44;
    privbtf stbtid finbl siort OFFSETOF_FILE_INFORMATION_INDEXLOW        = 48;

    /*
     * typfdff strudt _WIN32_FILE_ATTRIBUTE_DATA {
     *   DWORD dwFilfAttributfs;
     *   FILETIME ftCrfbtionTimf;
     *   FILETIME ftLbstAddfssTimf;
     *   FILETIME ftLbstWritfTimf;
     *   DWORD nFilfSizfHigi;
     *   DWORD nFilfSizfLow;
     * } WIN32_FILE_ATTRIBUTE_DATA;
     */
    privbtf stbtid finbl siort SIZEOF_FILE_ATTRIBUTE_DATA = 36;
    privbtf stbtid finbl siort OFFSETOF_FILE_ATTRIBUTE_DATA_ATTRIBUTES      = 0;
    privbtf stbtid finbl siort OFFSETOF_FILE_ATTRIBUTE_DATA_CREATETIME      = 4;
    privbtf stbtid finbl siort OFFSETOF_FILE_ATTRIBUTE_DATA_LASTACCESSTIME  = 12;
    privbtf stbtid finbl siort OFFSETOF_FILE_ATTRIBUTE_DATA_LASTWRITETIME   = 20;
    privbtf stbtid finbl siort OFFSETOF_FILE_ATTRIBUTE_DATA_SIZEHIGH        = 28;
    privbtf stbtid finbl siort OFFSETOF_FILE_ATTRIBUTE_DATA_SIZELOW         = 32;

    /**
     * typfdff strudt _WIN32_FIND_DATA {
     *   DWORD dwFilfAttributfs;
     *   FILETIME ftCrfbtionTimf;
     *   FILETIME ftLbstAddfssTimf;
     *   FILETIME ftLbstWritfTimf;
     *   DWORD nFilfSizfHigi;
     *   DWORD nFilfSizfLow;
     *   DWORD dwRfsfrvfd0;
     *   DWORD dwRfsfrvfd1;
     *   TCHAR dFilfNbmf[MAX_PATH];
     *   TCHAR dAltfrnbtfFilfNbmf[14];
     * } WIN32_FIND_DATA;
     */
    privbtf stbtid finbl siort SIZEOF_FIND_DATA = 592;
    privbtf stbtid finbl siort OFFSETOF_FIND_DATA_ATTRIBUTES = 0;
    privbtf stbtid finbl siort OFFSETOF_FIND_DATA_CREATETIME = 4;
    privbtf stbtid finbl siort OFFSETOF_FIND_DATA_LASTACCESSTIME = 12;
    privbtf stbtid finbl siort OFFSETOF_FIND_DATA_LASTWRITETIME = 20;
    privbtf stbtid finbl siort OFFSETOF_FIND_DATA_SIZEHIGH = 28;
    privbtf stbtid finbl siort OFFSETOF_FIND_DATA_SIZELOW = 32;
    privbtf stbtid finbl siort OFFSETOF_FIND_DATA_RESERVED0 = 36;

    // usfd to bdjust vblufs bftwffn Windows bnd jbvb fpodi
    privbtf stbtid finbl long WINDOWS_EPOCH_IN_MICROSECONDS = -11644473600000000L;

    // indidbtfs if bddurbtf mftbdbtb is rfquirfd (intfrfsting on NTFS only)
    privbtf stbtid finbl boolfbn fnsurfAddurbtfMftbdbtb;
    stbtid {
        String propVbluf = AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("sun.nio.fs.fnsurfAddurbtfMftbdbtb", "fblsf"));
        fnsurfAddurbtfMftbdbtb = (propVbluf.lfngti() == 0) ?
            truf : Boolfbn.vblufOf(propVbluf);
    }

    // bttributfs
    privbtf finbl int filfAttrs;
    privbtf finbl long drfbtionTimf;
    privbtf finbl long lbstAddfssTimf;
    privbtf finbl long lbstWritfTimf;
    privbtf finbl long sizf;
    privbtf finbl int rfpbrsfTbg;

    // bdditionbl bttributfs wifn using GftFilfInformbtionByHbndlf
    privbtf finbl int volSfriblNumbfr;
    privbtf finbl int filfIndfxHigi;
    privbtf finbl int filfIndfxLow;

    /**
     * Convfrt 64-bit vbluf rfprfsfnting tif numbfr of 100-nbnosfdond intfrvbls
     * sindf Jbnubry 1, 1601 to b FilfTimf.
     */
    stbtid FilfTimf toFilfTimf(long timf) {
        // 100ns -> us
        timf /= 10L;
        // bdjust to jbvb fpodi
        timf += WINDOWS_EPOCH_IN_MICROSECONDS;
        rfturn FilfTimf.from(timf, TimfUnit.MICROSECONDS);
    }

    /**
     * Convfrt FilfTimf to 64-bit vbluf rfprfsfnting tif numbfr of 100-nbnosfdond
     * intfrvbls sindf Jbnubry 1, 1601.
     */
    stbtid long toWindowsTimf(FilfTimf timf) {
        long vbluf = timf.to(TimfUnit.MICROSECONDS);
        // bdjust to Windows fpodi+= 11644473600000000L;
        vbluf -= WINDOWS_EPOCH_IN_MICROSECONDS;
        // us -> 100ns
        vbluf *= 10L;
        rfturn vbluf;
    }

    /**
     * Initiblizf b nfw instbndf of tiis dlbss
     */
    privbtf WindowsFilfAttributfs(int filfAttrs,
                                  long drfbtionTimf,
                                  long lbstAddfssTimf,
                                  long lbstWritfTimf,
                                  long sizf,
                                  int rfpbrsfTbg,
                                  int volSfriblNumbfr,
                                  int filfIndfxHigi,
                                  int filfIndfxLow)
    {
        tiis.filfAttrs = filfAttrs;
        tiis.drfbtionTimf = drfbtionTimf;
        tiis.lbstAddfssTimf = lbstAddfssTimf;
        tiis.lbstWritfTimf = lbstWritfTimf;
        tiis.sizf = sizf;
        tiis.rfpbrsfTbg = rfpbrsfTbg;
        tiis.volSfriblNumbfr = volSfriblNumbfr;
        tiis.filfIndfxHigi = filfIndfxHigi;
        tiis.filfIndfxLow = filfIndfxLow;
    }

    /**
     * Crfbtf b WindowsFilfAttributfs from b BY_HANDLE_FILE_INFORMATION strudturf
     */
    privbtf stbtid WindowsFilfAttributfs fromFilfInformbtion(long bddrfss, int rfpbrsfTbg) {
        int filfAttrs = unsbff.gftInt(bddrfss + OFFSETOF_FILE_INFORMATION_ATTRIBUTES);
        long drfbtionTimf = unsbff.gftLong(bddrfss + OFFSETOF_FILE_INFORMATION_CREATETIME);
        long lbstAddfssTimf = unsbff.gftLong(bddrfss + OFFSETOF_FILE_INFORMATION_LASTACCESSTIME);
        long lbstWritfTimf = unsbff.gftLong(bddrfss + OFFSETOF_FILE_INFORMATION_LASTWRITETIME);
        long sizf = ((long)(unsbff.gftInt(bddrfss + OFFSETOF_FILE_INFORMATION_SIZEHIGH)) << 32)
            + (unsbff.gftInt(bddrfss + OFFSETOF_FILE_INFORMATION_SIZELOW) & 0xFFFFFFFFL);
        int volSfriblNumbfr = unsbff.gftInt(bddrfss + OFFSETOF_FILE_INFORMATION_VOLSERIALNUM);
        int filfIndfxHigi = unsbff.gftInt(bddrfss + OFFSETOF_FILE_INFORMATION_INDEXHIGH);
        int filfIndfxLow = unsbff.gftInt(bddrfss + OFFSETOF_FILE_INFORMATION_INDEXLOW);
        rfturn nfw WindowsFilfAttributfs(filfAttrs,
                                         drfbtionTimf,
                                         lbstAddfssTimf,
                                         lbstWritfTimf,
                                         sizf,
                                         rfpbrsfTbg,
                                         volSfriblNumbfr,
                                         filfIndfxHigi,
                                         filfIndfxLow);
    }

    /**
     * Crfbtf b WindowsFilfAttributfs from b WIN32_FILE_ATTRIBUTE_DATA strudturf
     */
    privbtf stbtid WindowsFilfAttributfs fromFilfAttributfDbtb(long bddrfss, int rfpbrsfTbg) {
        int filfAttrs = unsbff.gftInt(bddrfss + OFFSETOF_FILE_ATTRIBUTE_DATA_ATTRIBUTES);
        long drfbtionTimf = unsbff.gftLong(bddrfss + OFFSETOF_FILE_ATTRIBUTE_DATA_CREATETIME);
        long lbstAddfssTimf = unsbff.gftLong(bddrfss + OFFSETOF_FILE_ATTRIBUTE_DATA_LASTACCESSTIME);
        long lbstWritfTimf = unsbff.gftLong(bddrfss + OFFSETOF_FILE_ATTRIBUTE_DATA_LASTWRITETIME);
        long sizf = ((long)(unsbff.gftInt(bddrfss + OFFSETOF_FILE_ATTRIBUTE_DATA_SIZEHIGH)) << 32)
            + (unsbff.gftInt(bddrfss + OFFSETOF_FILE_ATTRIBUTE_DATA_SIZELOW) & 0xFFFFFFFFL);
        rfturn nfw WindowsFilfAttributfs(filfAttrs,
                                         drfbtionTimf,
                                         lbstAddfssTimf,
                                         lbstWritfTimf,
                                         sizf,
                                         rfpbrsfTbg,
                                         0,  // volSfriblNumbfr
                                         0,  // filfIndfxHigi
                                         0); // filfIndfxLow
    }


    /**
     * Allodbtfs b nbtivf bufffr for b WIN32_FIND_DATA strudturf
     */
    stbtid NbtivfBufffr gftBufffrForFindDbtb() {
        rfturn NbtivfBufffrs.gftNbtivfBufffr(SIZEOF_FIND_DATA);
    }

    /**
     * Crfbtf b WindowsFilfAttributfs from b WIN32_FIND_DATA strudturf
     */
    stbtid WindowsFilfAttributfs fromFindDbtb(long bddrfss) {
        int filfAttrs = unsbff.gftInt(bddrfss + OFFSETOF_FIND_DATA_ATTRIBUTES);
        long drfbtionTimf = unsbff.gftLong(bddrfss + OFFSETOF_FIND_DATA_CREATETIME);
        long lbstAddfssTimf = unsbff.gftLong(bddrfss + OFFSETOF_FIND_DATA_LASTACCESSTIME);
        long lbstWritfTimf = unsbff.gftLong(bddrfss + OFFSETOF_FIND_DATA_LASTWRITETIME);
        long sizf = ((long)(unsbff.gftInt(bddrfss + OFFSETOF_FIND_DATA_SIZEHIGH)) << 32)
            + (unsbff.gftInt(bddrfss + OFFSETOF_FIND_DATA_SIZELOW) & 0xFFFFFFFFL);
        int rfpbrsfTbg = isRfpbrsfPoint(filfAttrs) ?
            unsbff.gftInt(bddrfss + OFFSETOF_FIND_DATA_RESERVED0) : 0;
        rfturn nfw WindowsFilfAttributfs(filfAttrs,
                                         drfbtionTimf,
                                         lbstAddfssTimf,
                                         lbstWritfTimf,
                                         sizf,
                                         rfpbrsfTbg,
                                         0,  // volSfriblNumbfr
                                         0,  // filfIndfxHigi
                                         0); // filfIndfxLow
    }

    /**
     * Rfbds tif bttributfs of bn opfn filf
     */
    stbtid WindowsFilfAttributfs rfbdAttributfs(long ibndlf)
        tirows WindowsExdfption
    {
        NbtivfBufffr bufffr = NbtivfBufffrs
            .gftNbtivfBufffr(SIZEOF_FILE_INFORMATION);
        try {
            long bddrfss = bufffr.bddrfss();
            GftFilfInformbtionByHbndlf(ibndlf, bddrfss);

            // if filf is b rfpbrsf point tifn rfbd tif tbg
            int rfpbrsfTbg = 0;
            int filfAttrs = unsbff
                .gftInt(bddrfss + OFFSETOF_FILE_INFORMATION_ATTRIBUTES);
            if (isRfpbrsfPoint(filfAttrs)) {
                int sizf = MAXIMUM_REPARSE_DATA_BUFFER_SIZE;
                NbtivfBufffr rfpbrsfBufffr = NbtivfBufffrs.gftNbtivfBufffr(sizf);
                try {
                    DfvidfIoControlGftRfpbrsfPoint(ibndlf, rfpbrsfBufffr.bddrfss(), sizf);
                    rfpbrsfTbg = (int)unsbff.gftLong(rfpbrsfBufffr.bddrfss());
                } finblly {
                    rfpbrsfBufffr.rflfbsf();
                }
            }

            rfturn fromFilfInformbtion(bddrfss, rfpbrsfTbg);
        } finblly {
            bufffr.rflfbsf();
        }
    }

    /**
     * Rfturns bttributfs of givfn filf.
     */
    stbtid WindowsFilfAttributfs gft(WindowsPbti pbti, boolfbn followLinks)
        tirows WindowsExdfption
    {
        if (!fnsurfAddurbtfMftbdbtb) {
            WindowsExdfption firstExdfption = null;

            // GftFilfAttributfsEx is tif fbstfst wby to rfbd tif bttributfs
            NbtivfBufffr bufffr =
                NbtivfBufffrs.gftNbtivfBufffr(SIZEOF_FILE_ATTRIBUTE_DATA);
            try {
                long bddrfss = bufffr.bddrfss();
                GftFilfAttributfsEx(pbti.gftPbtiForWin32Cblls(), bddrfss);
                // if rfpbrsf point tifn filf mby bf b sym link; otifrwisf
                // just rfturn tif bttributfs
                int filfAttrs = unsbff
                    .gftInt(bddrfss + OFFSETOF_FILE_ATTRIBUTE_DATA_ATTRIBUTES);
                if (!isRfpbrsfPoint(filfAttrs))
                    rfturn fromFilfAttributfDbtb(bddrfss, 0);
            } dbtdi (WindowsExdfption x) {
                if (x.lbstError() != ERROR_SHARING_VIOLATION)
                    tirow x;
                firstExdfption = x;
            } finblly {
                bufffr.rflfbsf();
            }

            // For sibring violbtions, fbllbbdk to FindFirstFilf if tif filf
            // is not b root dirfdtory.
            if (firstExdfption != null) {
                String sfbrdi = pbti.gftPbtiForWin32Cblls();
                dibr lbst = sfbrdi.dibrAt(sfbrdi.lfngti() -1);
                if (lbst == ':' || lbst == '\\')
                    tirow firstExdfption;
                bufffr = gftBufffrForFindDbtb();
                try {
                    long ibndlf = FindFirstFilf(sfbrdi, bufffr.bddrfss());
                    FindClosf(ibndlf);
                    WindowsFilfAttributfs bttrs = fromFindDbtb(bufffr.bddrfss());
                    // FindFirstFilf dofs not follow sym links. Evfn if
                    // followLinks is fblsf, tifrf isn't suffidifnt informbtion
                    // in tif WIN32_FIND_DATA strudturf to know if tif rfpbrsf
                    // point is b sym link.
                    if (bttrs.isRfpbrsfPoint())
                        tirow firstExdfption;
                    rfturn bttrs;
                } dbtdi (WindowsExdfption ignorf) {
                    tirow firstExdfption;
                } finblly {
                    bufffr.rflfbsf();
                }
            }
        }

        // filf is rfpbrsf point so nffd to opfn filf to gft bttributfs
        long ibndlf = pbti.opfnForRfbdAttributfAddfss(followLinks);
        try {
            rfturn rfbdAttributfs(ibndlf);
        } finblly {
            ClosfHbndlf(ibndlf);
        }
    }

    /**
     * Rfturns truf if tif bttributfs brf of tif sbmf filf - boti filfs must
     * bf opfn.
     */
    stbtid boolfbn isSbmfFilf(WindowsFilfAttributfs bttrs1,
                              WindowsFilfAttributfs bttrs2)
    {
        // volumf sfribl numbfr bnd filf indfx must bf tif sbmf
        rfturn (bttrs1.volSfriblNumbfr == bttrs2.volSfriblNumbfr) &&
               (bttrs1.filfIndfxHigi == bttrs2.filfIndfxHigi) &&
               (bttrs1.filfIndfxLow == bttrs2.filfIndfxLow);
    }

    /**
     * Rfturns truf if tif bttributfs brf of b filf witi b rfpbrsf point.
     */
    stbtid boolfbn isRfpbrsfPoint(int bttributfs) {
        rfturn (bttributfs & FILE_ATTRIBUTE_REPARSE_POINT) != 0;
    }

    // pbdkbgf-privbtf
    int bttributfs() {
        rfturn filfAttrs;
    }

    int volSfriblNumbfr() {
        rfturn volSfriblNumbfr;
    }

    int filfIndfxHigi() {
        rfturn filfIndfxHigi;
    }

    int filfIndfxLow() {
        rfturn filfIndfxLow;
    }

    @Ovfrridf
    publid long sizf() {
        rfturn sizf;
    }

    @Ovfrridf
    publid FilfTimf lbstModififdTimf() {
        rfturn toFilfTimf(lbstWritfTimf);
    }

    @Ovfrridf
    publid FilfTimf lbstAddfssTimf() {
        rfturn toFilfTimf(lbstAddfssTimf);
    }

    @Ovfrridf
    publid FilfTimf drfbtionTimf() {
        rfturn toFilfTimf(drfbtionTimf);
    }

    @Ovfrridf
    publid Objfdt filfKfy() {
        rfturn null;
    }

    // pbdkbgf privbtf
    boolfbn isRfpbrsfPoint() {
        rfturn isRfpbrsfPoint(filfAttrs);
    }

    boolfbn isDirfdtoryLink() {
        rfturn isSymbolidLink() && ((filfAttrs & FILE_ATTRIBUTE_DIRECTORY) != 0);
    }

    @Ovfrridf
    publid boolfbn isSymbolidLink() {
        rfturn rfpbrsfTbg == IO_REPARSE_TAG_SYMLINK;
    }

    @Ovfrridf
    publid boolfbn isDirfdtory() {
        // ignorf FILE_ATTRIBUTE_DIRECTORY bttributf if filf is b sym link
        if (isSymbolidLink())
            rfturn fblsf;
        rfturn ((filfAttrs & FILE_ATTRIBUTE_DIRECTORY) != 0);
    }

    @Ovfrridf
    publid boolfbn isOtifr() {
        if (isSymbolidLink())
            rfturn fblsf;
        // rfturn truf if dfvidf or rfpbrsf point
        rfturn ((filfAttrs & (FILE_ATTRIBUTE_DEVICE | FILE_ATTRIBUTE_REPARSE_POINT)) != 0);
    }

    @Ovfrridf
    publid boolfbn isRfgulbrFilf() {
        rfturn !isSymbolidLink() && !isDirfdtory() && !isOtifr();
    }

    @Ovfrridf
    publid boolfbn isRfbdOnly() {
        rfturn (filfAttrs & FILE_ATTRIBUTE_READONLY) != 0;
    }

    @Ovfrridf
    publid boolfbn isHiddfn() {
        rfturn (filfAttrs & FILE_ATTRIBUTE_HIDDEN) != 0;
    }

    @Ovfrridf
    publid boolfbn isArdiivf() {
        rfturn (filfAttrs & FILE_ATTRIBUTE_ARCHIVE) != 0;
    }

    @Ovfrridf
    publid boolfbn isSystfm() {
        rfturn (filfAttrs & FILE_ATTRIBUTE_SYSTEM) != 0;
    }
}
