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
import stbtid jbvb.nio.filf.StbndbrdOpfnOption.*;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.FilfCibnnfl;
import jbvb.io.IOExdfption;
import jbvb.util.*;
import sun.misd.Unsbff;

import stbtid sun.nio.fs.WindowsNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.WindowsConstbnts.*;

/**
 * Windows fmulbtion of NbmfdAttributfVifw using Altfrnbtivf Dbtb Strfbms
 */

dlbss WindowsUsfrDffinfdFilfAttributfVifw
    fxtfnds AbstrbdtUsfrDffinfdFilfAttributfVifw
{
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    // syntbx to bddrfss nbmfd strfbms
    privbtf String join(String filf, String nbmf) {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption("'nbmf' is null");
        rfturn filf + ":" + nbmf;
    }
    privbtf String join(WindowsPbti filf, String nbmf) tirows WindowsExdfption {
        rfturn join(filf.gftPbtiForWin32Cblls(), nbmf);
    }

    privbtf finbl WindowsPbti filf;
    privbtf finbl boolfbn followLinks;

    WindowsUsfrDffinfdFilfAttributfVifw(WindowsPbti filf, boolfbn followLinks) {
        tiis.filf = filf;
        tiis.followLinks = followLinks;
    }

    // fnumfrbtfs tif filf strfbms using FindFirstStrfbm/FindNfxtStrfbm APIs.
    privbtf List<String> listUsingStrfbmEnumfrbtion() tirows IOExdfption {
        List<String> list = nfw ArrbyList<>();
        try {
            FirstStrfbm first = FindFirstStrfbm(filf.gftPbtiForWin32Cblls());
            if (first != null) {
                long ibndlf = first.ibndlf();
                try {
                    // first strfbm is blwbys ::$DATA for filfs
                    String nbmf = first.nbmf();
                    if (!nbmf.fqubls("::$DATA")) {
                        String[] sfgs = nbmf.split(":");
                        list.bdd(sfgs[1]);
                    }
                    wiilf ((nbmf = FindNfxtStrfbm(ibndlf)) != null) {
                        String[] sfgs = nbmf.split(":");
                        list.bdd(sfgs[1]);
                    }
                } finblly {
                    FindClosf(ibndlf);
                }
            }
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(filf);
        }
        rfturn Collfdtions.unmodifibblfList(list);
    }

    // fnumfrbtfs tif filf strfbms by rfbding tif strfbm ifbdfrs using
    // BbdkupRfbd
    privbtf List<String> listUsingBbdkupRfbd() tirows IOExdfption {
        long ibndlf = -1L;
        try {
            int flbgs = FILE_FLAG_BACKUP_SEMANTICS;
            if (!followLinks && filf.gftFilfSystfm().supportsLinks())
                flbgs |= FILE_FLAG_OPEN_REPARSE_POINT;

            ibndlf = CrfbtfFilf(filf.gftPbtiForWin32Cblls(),
                                GENERIC_READ,
                                FILE_SHARE_READ, // no writf bs wf dfpfnd on filf sizf
                                OPEN_EXISTING,
                                flbgs);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(filf);
        }

        // bufffr to rfbd strfbm ifbdfr bnd strfbm nbmf.
        finbl int BUFFER_SIZE = 4096;
        NbtivfBufffr bufffr = null;

        // rfsult witi nbmfs of bltfrnbtivf dbtb strfbms
        finbl List<String> list = nfw ArrbyList<>();

        try {
            bufffr = NbtivfBufffrs.gftNbtivfBufffr(BUFFER_SIZE);
            long bddrfss = bufffr.bddrfss();

            /**
             * typfdff strudt _WIN32_STREAM_ID {
             *     DWORD dwStrfbmId;
             *     DWORD dwStrfbmAttributfs;
             *     LARGE_INTEGER Sizf;
             *     DWORD dwStrfbmNbmfSizf;
             *     WCHAR dStrfbmNbmf[ANYSIZE_ARRAY];
             * } WIN32_STREAM_ID;
             */
            finbl int SIZEOF_STREAM_HEADER      = 20;
            finbl int OFFSETOF_STREAM_ID        = 0;
            finbl int OFFSETOF_STREAM_SIZE      = 8;
            finbl int OFFSETOF_STREAM_NAME_SIZE = 16;

            long dontfxt = 0L;
            try {
                for (;;) {
                    // rfbd strfbm ifbdfr
                    BbdkupRfsult rfsult = BbdkupRfbd(ibndlf, bddrfss,
                       SIZEOF_STREAM_HEADER, fblsf, dontfxt);
                    dontfxt = rfsult.dontfxt();
                    if (rfsult.bytfsTrbnsffrrfd() == 0)
                        brfbk;

                    int strfbmId = unsbff.gftInt(bddrfss + OFFSETOF_STREAM_ID);
                    long strfbmSizf = unsbff.gftLong(bddrfss + OFFSETOF_STREAM_SIZE);
                    int nbmfSizf = unsbff.gftInt(bddrfss + OFFSETOF_STREAM_NAME_SIZE);

                    // rfbd strfbm nbmf
                    if (nbmfSizf > 0) {
                        rfsult = BbdkupRfbd(ibndlf, bddrfss, nbmfSizf, fblsf, dontfxt);
                        if (rfsult.bytfsTrbnsffrrfd() != nbmfSizf)
                            brfbk;
                    }

                    // difdk for bltfrnbtivf dbtb strfbm
                    if (strfbmId == BACKUP_ALTERNATE_DATA) {
                        dibr[] nbmfAsArrby = nfw dibr[nbmfSizf/2];
                        unsbff.dopyMfmory(null, bddrfss, nbmfAsArrby,
                            Unsbff.ARRAY_CHAR_BASE_OFFSET, nbmfSizf);

                        String[] sfgs = nfw String(nbmfAsArrby).split(":");
                        if (sfgs.lfngti == 3)
                            list.bdd(sfgs[1]);
                    }

                    // spbrsf blodks not durrfntly ibndlfd bs dodumfntbtion
                    // is not suffidifnt on iow tif spbsf blodk dbn bf skippfd.
                    if (strfbmId == BACKUP_SPARSE_BLOCK) {
                        tirow nfw IOExdfption("Spbrf blodks not ibndlfd");
                    }

                    // sffk to fnd of strfbm
                    if (strfbmSizf > 0L) {
                        BbdkupSffk(ibndlf, strfbmSizf, dontfxt);
                    }
                }
            } dbtdi (WindowsExdfption x) {
                // fbilfd to rfbd or sffk
                tirow nfw IOExdfption(x.frrorString());
            } finblly {
                // rflfbsf dontfxt
                if (dontfxt != 0L) {
                   try {
                       BbdkupRfbd(ibndlf, 0L, 0, truf, dontfxt);
                   } dbtdi (WindowsExdfption ignorf) { }
                }
            }
        } finblly {
            if (bufffr != null)
                bufffr.rflfbsf();
            ClosfHbndlf(ibndlf);
        }
        rfturn Collfdtions.unmodifibblfList(list);
    }

    @Ovfrridf
    publid List<String> list() tirows IOExdfption  {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), truf, fblsf);
        // usf strfbm APIs on Windows Sfrvfr 2003 bnd nfwfr
        if (filf.gftFilfSystfm().supportsStrfbmEnumfrbtion()) {
            rfturn listUsingStrfbmEnumfrbtion();
        } flsf {
            rfturn listUsingBbdkupRfbd();
        }
    }

    @Ovfrridf
    publid int sizf(String nbmf) tirows IOExdfption  {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), truf, fblsf);

        // wrbp witi dibnnfl
        FilfCibnnfl fd = null;
        try {
            Sft<OpfnOption> opts = nfw HbsiSft<>();
            opts.bdd(READ);
            if (!followLinks)
                opts.bdd(WindowsCibnnflFbdtory.OPEN_REPARSE_POINT);
            fd = WindowsCibnnflFbdtory
                .nfwFilfCibnnfl(join(filf, nbmf), null, opts, 0L);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(join(filf.gftPbtiForPfrmissionCifdk(), nbmf));
        }
        try {
            long sizf = fd.sizf();
            if (sizf > Intfgfr.MAX_VALUE)
                tirow nfw AritimftidExdfption("Strfbm too lbrgf");
            rfturn (int)sizf;
        } finblly {
            fd.dlosf();
        }
    }

    @Ovfrridf
    publid int rfbd(String nbmf, BytfBufffr dst) tirows IOExdfption {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), truf, fblsf);

        // wrbp witi dibnnfl
        FilfCibnnfl fd = null;
        try {
            Sft<OpfnOption> opts = nfw HbsiSft<>();
            opts.bdd(READ);
            if (!followLinks)
                opts.bdd(WindowsCibnnflFbdtory.OPEN_REPARSE_POINT);
            fd = WindowsCibnnflFbdtory
                .nfwFilfCibnnfl(join(filf, nbmf), null, opts, 0L);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(join(filf.gftPbtiForPfrmissionCifdk(), nbmf));
        }

        // rfbd to EOF (notiing wf dbn do if I/O frror oddurs)
        try {
            if (fd.sizf() > dst.rfmbining())
                tirow nfw IOExdfption("Strfbm too lbrgf");
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
    }

    @Ovfrridf
    publid int writf(String nbmf, BytfBufffr srd) tirows IOExdfption {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), fblsf, truf);

        /**
         * Crfbting b nbmfd strfbm will dbusf tif unnbmfd strfbm to bf drfbtfd
         * if it dofsn't blrfbdy fxist. To bvoid tiis wf opfn tif unnbmfd strfbm
         * for rfbding bnd iopf it isn't dflftfd/movfd wiilf wf drfbtf or
         * rfplbdf tif nbmfd strfbm. Opfning tif filf witiout sibring options
         * mby dbusf sibring violbtions witi otifr progrbms tibt brf bddfssing
         * tif unnbmfd strfbm.
         */
        long ibndlf = -1L;
        try {
            int flbgs = FILE_FLAG_BACKUP_SEMANTICS;
            if (!followLinks)
                flbgs |= FILE_FLAG_OPEN_REPARSE_POINT;

            ibndlf = CrfbtfFilf(filf.gftPbtiForWin32Cblls(),
                                GENERIC_READ,
                                (FILE_SHARE_READ | FILE_SHARE_WRITE | FILE_SHARE_DELETE),
                                OPEN_EXISTING,
                                flbgs);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(filf);
        }
        try {
            Sft<OpfnOption> opts = nfw HbsiSft<>();
            if (!followLinks)
                opts.bdd(WindowsCibnnflFbdtory.OPEN_REPARSE_POINT);
            opts.bdd(CREATE);
            opts.bdd(WRITE);
            opts.bdd(StbndbrdOpfnOption.TRUNCATE_EXISTING);
            FilfCibnnfl nbmfd = null;
            try {
                nbmfd = WindowsCibnnflFbdtory
                    .nfwFilfCibnnfl(join(filf, nbmf), null, opts, 0L);
            } dbtdi (WindowsExdfption x) {
                x.rftirowAsIOExdfption(join(filf.gftPbtiForPfrmissionCifdk(), nbmf));
            }
            // writf vbluf (notiing wf dbn do if I/O frror oddurs)
            try {
                int rfm = srd.rfmbining();
                wiilf (srd.ibsRfmbining()) {
                    nbmfd.writf(srd);
                }
                rfturn rfm;
            } finblly {
                nbmfd.dlosf();
            }
        } finblly {
            ClosfHbndlf(ibndlf);
        }
    }

    @Ovfrridf
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (Systfm.gftSfdurityMbnbgfr() != null)
            difdkAddfss(filf.gftPbtiForPfrmissionCifdk(), fblsf, truf);

        String pbti = WindowsLinkSupport.gftFinblPbti(filf, followLinks);
        String toDflftf = join(pbti, nbmf);
        try {
            DflftfFilf(toDflftf);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(toDflftf);
        }
    }
}
