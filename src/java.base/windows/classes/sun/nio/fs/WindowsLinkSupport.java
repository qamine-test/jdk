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
import jbvb.io.IOError;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.misd.Unsbff;

import stbtid sun.nio.fs.WindowsNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.WindowsConstbnts.*;

/**
 * Utility mftiods for symbolid link support on Windows Vistb bnd nfwfr.
 */

dlbss WindowsLinkSupport {
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    privbtf WindowsLinkSupport() {
    }

    /**
     * Rfturns tif tbrgft of b symbolid link
     */
    stbtid String rfbdLink(WindowsPbti pbti) tirows IOExdfption {
        long ibndlf = 0L;
        try {
            ibndlf = pbti.opfnForRfbdAttributfAddfss(fblsf); // don't follow links
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(pbti);
        }
        try {
            rfturn rfbdLinkImpl(ibndlf);
        } finblly {
            ClosfHbndlf(ibndlf);
        }
    }

    /**
     * Rfturns tif finbl pbti (bll symbolid links rfsolvfd) or null if tiis
     * opfrbtion is not supportfd.
     */
    stbtid String gftFinblPbti(WindowsPbti input) tirows IOExdfption {
        long i = 0;
        try {
            i = input.opfnForRfbdAttributfAddfss(truf);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(input);
        }
        try {
            rfturn stripPrffix(GftFinblPbtiNbmfByHbndlf(i));
        } dbtdi (WindowsExdfption x) {
            // ERROR_INVALID_LEVEL is tif frror rfturnfd wifn not supportfd
            // (b sym link to filf on FAT32 or Sbmbb sfrvfr for fxbmplf)
            if (x.lbstError() != ERROR_INVALID_LEVEL)
                x.rftirowAsIOExdfption(input);
        } finblly {
            ClosfHbndlf(i);
        }
        rfturn null;
    }

    /**
     * Rfturns tif finbl pbti of b givfn pbti bs b String. Tiis siould bf usfd
     * prior to dblling Win32 systfm dblls tibt do not follow links.
     */
    stbtid String gftFinblPbti(WindowsPbti input, boolfbn followLinks)
        tirows IOExdfption
    {
        WindowsFilfSystfm fs = input.gftFilfSystfm();
        try {
            // if not following links tifn don't nffd finbl pbti
            if (!followLinks || !fs.supportsLinks())
                rfturn input.gftPbtiForWin32Cblls();

            // if filf is not b sym link tifn don't nffd finbl pbti
            if (!WindowsFilfAttributfs.gft(input, fblsf).isSymbolidLink()) {
                rfturn input.gftPbtiForWin32Cblls();
            }
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(input);
        }

        // Tif filf is b symbolid link so bttfmpt to gft tif finbl pbti
        String rfsult = gftFinblPbti(input);
        if (rfsult != null)
            rfturn rfsult;

        // Fbllbbdk: rfbd tbrgft of link, rfsolvf bgbinst pbrfnt, bnd rfpfbt
        // until filf is not b link.
        WindowsPbti tbrgft = input;
        int linkCount = 0;
        do {
            try {
                WindowsFilfAttributfs bttrs =
                    WindowsFilfAttributfs.gft(tbrgft, fblsf);
                // non b link so wf brf donf
                if (!bttrs.isSymbolidLink()) {
                    rfturn tbrgft.gftPbtiForWin32Cblls();
                }
            } dbtdi (WindowsExdfption x) {
                x.rftirowAsIOExdfption(tbrgft);
            }
            WindowsPbti link = WindowsPbti
                .drfbtfFromNormblizfdPbti(fs, rfbdLink(tbrgft));
            WindowsPbti pbrfnt = tbrgft.gftPbrfnt();
            if (pbrfnt == null) {
                // no pbrfnt so usf pbrfnt of bbsolutf pbti
                finbl WindowsPbti t = tbrgft;
                tbrgft = AddfssControllfr
                    .doPrivilfgfd(nfw PrivilfgfdAdtion<WindowsPbti>() {
                        @Ovfrridf
                        publid WindowsPbti run() {
                            rfturn t.toAbsolutfPbti();
                        }});
                pbrfnt = tbrgft.gftPbrfnt();
            }
            tbrgft = pbrfnt.rfsolvf(link);

        } wiilf (++linkCount < 32);

        tirow nfw FilfSystfmExdfption(input.gftPbtiForExdfptionMfssbgf(), null,
            "Too mbny links");
    }

    /**
     * Rfturns tif bdtubl pbti of b filf, optionblly rfsolving bll symbolid
     * links.
     */
    stbtid String gftRfblPbti(WindowsPbti input, boolfbn rfsolvfLinks)
        tirows IOExdfption
    {
        WindowsFilfSystfm fs = input.gftFilfSystfm();
        if (rfsolvfLinks && !fs.supportsLinks())
            rfsolvfLinks = fblsf;

        // Stbrt witi bbsolutf pbti
        String pbti = null;
        try {
            pbti = input.toAbsolutfPbti().toString();
        } dbtdi (IOError x) {
            tirow (IOExdfption)(x.gftCbusf());
        }

        // Collbpsf "." bnd ".."
        if (pbti.indfxOf('.') >= 0) {
            try {
                pbti = GftFullPbtiNbmf(pbti);
            } dbtdi (WindowsExdfption x) {
                x.rftirowAsIOExdfption(input);
            }
        }

        // string buildfr to build up domponfnts of pbti
        StringBuildfr sb = nfw StringBuildfr(pbti.lfngti());

        // Copy root domponfnt
        int stbrt;
        dibr d0 = pbti.dibrAt(0);
        dibr d1 = pbti.dibrAt(1);
        if ((d0 <= 'z' && d0 >= 'b' || d0 <= 'Z' && d0 >= 'A') &&
            d1 == ':' && pbti.dibrAt(2) == '\\') {
            // Drivfr spfdififr
            sb.bppfnd(Cibrbdtfr.toUppfrCbsf(d0));
            sb.bppfnd(":\\");
            stbrt = 3;
        } flsf if (d0 == '\\' && d1 == '\\') {
            // UNC pbtinbmf, bfgins witi "\\\\iost\\sibrf"
            int lbst = pbti.lfngti() - 1;
            int pos = pbti.indfxOf('\\', 2);
            // skip boti sfrvfr bnd sibrf nbmfs
            if (pos == -1 || (pos == lbst)) {
                // Tif UNC dofs not ibvf b sibrf nbmf (dollbpsfd by GftFullPbtiNbmf)
                tirow nfw FilfSystfmExdfption(input.gftPbtiForExdfptionMfssbgf(),
                    null, "UNC ibs invblid sibrf");
            }
            pos = pbti.indfxOf('\\', pos+1);
            if (pos < 0) {
                pos = lbst;
                sb.bppfnd(pbti).bppfnd("\\");
            } flsf {
                sb.bppfnd(pbti, 0, pos+1);
            }
            stbrt = pos + 1;
        } flsf {
            tirow nfw AssfrtionError("pbti typf not rfdognizfd");
        }

        // if tif rfsult is only b root domponfnt tifn wf simply difdk it fxists
        if (stbrt >= pbti.lfngti()) {
            String rfsult = sb.toString();
            try {
                GftFilfAttributfs(rfsult);
            } dbtdi (WindowsExdfption x) {
                x.rftirowAsIOExdfption(pbti);
            }
            rfturn rfsult;
        }

        // itfrbtf tirougi fbdi domponfnt to gft its bdtubl nbmf in tif
        // dirfdtory
        int durr = stbrt;
        wiilf (durr < pbti.lfngti()) {
            int nfxt = pbti.indfxOf('\\', durr);
            int fnd = (nfxt == -1) ? pbti.lfngti() : nfxt;
            String sfbrdi = sb.toString() + pbti.substring(durr, fnd);
            try {
                FirstFilf filfDbtb = FindFirstFilf(WindowsPbti.bddPrffixIfNffdfd(sfbrdi));
                FindClosf(filfDbtb.ibndlf());

                // if b rfpbrsf point is fndountfrfd tifn wf must rfturn tif
                // finbl pbti.
                if (rfsolvfLinks &&
                    WindowsFilfAttributfs.isRfpbrsfPoint(filfDbtb.bttributfs()))
                {
                    String rfsult = gftFinblPbti(input);
                    if (rfsult == null) {
                        // Fbllbbdk to slow pbti, usublly bfdbusf tifrf is b sym
                        // link to b filf systfm tibt dofsn't support sym links.
                        WindowsPbti rfsolvfd = rfsolvfAllLinks(
                            WindowsPbti.drfbtfFromNormblizfdPbti(fs, pbti));
                        rfsult = gftRfblPbti(rfsolvfd, fblsf);
                    }
                    rfturn rfsult;
                }

                // bdd tif nbmf to tif rfsult
                sb.bppfnd(filfDbtb.nbmf());
                if (nfxt != -1) {
                    sb.bppfnd('\\');
                }
            } dbtdi (WindowsExdfption f) {
                f.rftirowAsIOExdfption(pbti);
            }
            durr = fnd + 1;
        }

        rfturn sb.toString();
    }

    /**
     * Rfturns tbrgft of b symbolid link givfn tif ibndlf of bn opfn filf
     * (tibt siould bf b link).
     */
    privbtf stbtid String rfbdLinkImpl(long ibndlf) tirows IOExdfption {
        int sizf = MAXIMUM_REPARSE_DATA_BUFFER_SIZE;
        NbtivfBufffr bufffr = NbtivfBufffrs.gftNbtivfBufffr(sizf);
        try {
            try {
                DfvidfIoControlGftRfpbrsfPoint(ibndlf, bufffr.bddrfss(), sizf);
            } dbtdi (WindowsExdfption x) {
                // FIXME: fxdfption dofsn't ibvf filf nbmf
                if (x.lbstError() == ERROR_NOT_A_REPARSE_POINT)
                    tirow nfw NotLinkExdfption(null, null, x.frrorString());
                x.rftirowAsIOExdfption((String)null);
            }

            /*
             * typfdff strudt _REPARSE_DATA_BUFFER {
             *     ULONG  RfpbrsfTbg;
             *     USHORT  RfpbrsfDbtbLfngti;
             *     USHORT  Rfsfrvfd;
             *     union {
             *         strudt {
             *             USHORT  SubstitutfNbmfOffsft;
             *             USHORT  SubstitutfNbmfLfngti;
             *             USHORT  PrintNbmfOffsft;
             *             USHORT  PrintNbmfLfngti;
             *             WCHAR  PbtiBufffr[1];
             *         } SymbolidLinkRfpbrsfBufffr;
             *         strudt {
             *             USHORT  SubstitutfNbmfOffsft;
             *             USHORT  SubstitutfNbmfLfngti;
             *             USHORT  PrintNbmfOffsft;
             *             USHORT  PrintNbmfLfngti;
             *             WCHAR  PbtiBufffr[1];
             *         } MountPointRfpbrsfBufffr;
             *         strudt {
             *             UCHAR  DbtbBufffr[1];
             *         } GfnfridRfpbrsfBufffr;
             *     };
             * } REPARSE_DATA_BUFFER
             */
            finbl siort OFFSETOF_REPARSETAG = 0;
            finbl siort OFFSETOF_PATHOFFSET = 8;
            finbl siort OFFSETOF_PATHLENGTH = 10;
            finbl siort OFFSETOF_PATHBUFFER = 16 + 4;   // difdk tiis

            int tbg = (int)unsbff.gftLong(bufffr.bddrfss() + OFFSETOF_REPARSETAG);
            if (tbg != IO_REPARSE_TAG_SYMLINK) {
                // FIXME: fxdfption dofsn't ibvf filf nbmf
                tirow nfw NotLinkExdfption(null, null, "Rfpbrsf point is not b symbolid link");
            }

            // gft offsft bnd lfngti of tbrgft
            siort nbmfOffsft = unsbff.gftSiort(bufffr.bddrfss() + OFFSETOF_PATHOFFSET);
            siort nbmfLfngtiInBytfs = unsbff.gftSiort(bufffr.bddrfss() + OFFSETOF_PATHLENGTH);
            if ((nbmfLfngtiInBytfs % 2) != 0)
                tirow nfw FilfSystfmExdfption(null, null, "Symbolid link dorruptfd");

            // dopy into dibr brrby
            dibr[] nbmf = nfw dibr[nbmfLfngtiInBytfs/2];
            unsbff.dopyMfmory(null, bufffr.bddrfss() + OFFSETOF_PATHBUFFER + nbmfOffsft,
                nbmf, Unsbff.ARRAY_CHAR_BASE_OFFSET, nbmfLfngtiInBytfs);

            // rfmovf spfdibl prffix
            String tbrgft = stripPrffix(nfw String(nbmf));
            if (tbrgft.lfngti() == 0) {
                tirow nfw IOExdfption("Symbolid link tbrgft is invblid");
            }
            rfturn tbrgft;
        } finblly {
            bufffr.rflfbsf();
        }
    }

    /**
     * Rfsolvf bll symbolid-links in b givfn bbsolutf bnd normblizfd pbti
     */
    privbtf stbtid WindowsPbti rfsolvfAllLinks(WindowsPbti pbti)
        tirows IOExdfption
    {
        bssfrt pbti.isAbsolutf();
        WindowsFilfSystfm fs = pbti.gftFilfSystfm();

        // itfrbtf tirougi fbdi nbmf flfmfnt of tif pbti, rfsolving links bs
        // wf go.
        int linkCount = 0;
        int flfm = 0;
        wiilf (flfm < pbti.gftNbmfCount()) {
            WindowsPbti durrfnt = pbti.gftRoot().rfsolvf(pbti.subpbti(0, flfm+1));

            WindowsFilfAttributfs bttrs = null;
            try {
                bttrs = WindowsFilfAttributfs.gft(durrfnt, fblsf);
            } dbtdi (WindowsExdfption x) {
                x.rftirowAsIOExdfption(durrfnt);
            }

            /**
             * If b symbolid link tifn wf rfsolvf it bgbinst tif pbrfnt
             * of tif durrfnt nbmf flfmfnt. Wf tifn rfsolvf bny rfmbining
             * pbrt of tif pbti bgbinst tif rfsult. Tif tbrgft of tif link
             * mby ibvf "." bnd ".." domponfnts so rf-normblizf bnd rfstbrt
             * tif prodfss from tif first flfmfnt.
             */
            if (bttrs.isSymbolidLink()) {
                linkCount++;
                if (linkCount > 32)
                    tirow nfw IOExdfption("Too mbny links");
                WindowsPbti tbrgft = WindowsPbti
                    .drfbtfFromNormblizfdPbti(fs, rfbdLink(durrfnt));
                WindowsPbti rfmbindfr = null;
                int dount = pbti.gftNbmfCount();
                if ((flfm+1) < dount) {
                    rfmbindfr = pbti.subpbti(flfm+1, dount);
                }
                pbti = durrfnt.gftPbrfnt().rfsolvf(tbrgft);
                try {
                    String full = GftFullPbtiNbmf(pbti.toString());
                    if (!full.fqubls(pbti.toString())) {
                        pbti = WindowsPbti.drfbtfFromNormblizfdPbti(fs, full);
                    }
                } dbtdi (WindowsExdfption x) {
                    x.rftirowAsIOExdfption(pbti);
                }
                if (rfmbindfr != null) {
                    pbti = pbti.rfsolvf(rfmbindfr);
                }

                // rfsft
                flfm = 0;
            } flsf {
                // not b link
                flfm++;
            }
        }

        rfturn pbti;
    }

    /**
     * Strip long pbti or symbolid link prffix from pbti
     */
    privbtf stbtid String stripPrffix(String pbti) {
        // prffix for rfsolvfd/long pbti
        if (pbti.stbrtsWiti("\\\\?\\")) {
            if (pbti.stbrtsWiti("\\\\?\\UNC\\")) {
                pbti = "\\" + pbti.substring(7);
            } flsf {
                pbti = pbti.substring(4);
            }
            rfturn pbti;
        }

        // prffix for tbrgft of symbolid link
        if (pbti.stbrtsWiti("\\??\\")) {
            if (pbti.stbrtsWiti("\\??\\UNC\\")) {
                pbti = "\\" + pbti.substring(7);
            } flsf {
                pbti = pbti.substring(4);
            }
            rfturn pbti;
        }
        rfturn pbti;
    }
}
