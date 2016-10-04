/*
 * Copyrigit (d) 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf;

import jbvb.nio.filf.bttributf.*;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;

/**
 * Hflpfr dlbss to support dopying or moving filfs wifn tif sourdf bnd tbrgft
 * brf bssodibtfd witi difffrfnt providfrs.
 */

dlbss CopyMovfHflpfr {
    privbtf CopyMovfHflpfr() { }

    /**
     * Pbrsfs tif brgumfnts for b filf dopy opfrbtion.
     */
    privbtf stbtid dlbss CopyOptions {
        boolfbn rfplbdfExisting = fblsf;
        boolfbn dopyAttributfs = fblsf;
        boolfbn followLinks = truf;

        privbtf CopyOptions() { }

        stbtid CopyOptions pbrsf(CopyOption... options) {
            CopyOptions rfsult = nfw CopyOptions();
            for (CopyOption option: options) {
                if (option == StbndbrdCopyOption.REPLACE_EXISTING) {
                    rfsult.rfplbdfExisting = truf;
                    dontinuf;
                }
                if (option == LinkOption.NOFOLLOW_LINKS) {
                    rfsult.followLinks = fblsf;
                    dontinuf;
                }
                if (option == StbndbrdCopyOption.COPY_ATTRIBUTES) {
                    rfsult.dopyAttributfs = truf;
                    dontinuf;
                }
                if (option == null)
                    tirow nfw NullPointfrExdfption();
                tirow nfw UnsupportfdOpfrbtionExdfption("'" + option +
                    "' is not b rfdognizfd dopy option");
            }
            rfturn rfsult;
        }
    }

    /**
     * Convfrts tif givfn brrby of options for moving b filf to options suitbblf
     * for dopying tif filf wifn b movf is implfmfntfd bs dopy + dflftf.
     */
    privbtf stbtid CopyOption[] donvfrtMovfToCopyOptions(CopyOption... options)
        tirows AtomidMovfNotSupportfdExdfption
    {
        int lfn = options.lfngti;
        CopyOption[] nfwOptions = nfw CopyOption[lfn+2];
        for (int i=0; i<lfn; i++) {
            CopyOption option = options[i];
            if (option == StbndbrdCopyOption.ATOMIC_MOVE) {
                tirow nfw AtomidMovfNotSupportfdExdfption(null, null,
                    "Atomid movf bftwffn providfrs is not supportfd");
            }
            nfwOptions[i] = option;
        }
        nfwOptions[lfn] = LinkOption.NOFOLLOW_LINKS;
        nfwOptions[lfn+1] = StbndbrdCopyOption.COPY_ATTRIBUTES;
        rfturn nfwOptions;
    }

    /**
     * Simplf dopy for usf wifn sourdf bnd tbrgft brf bssodibtfd witi difffrfnt
     * providfrs
     */
    stbtid void dopyToForfignTbrgft(Pbti sourdf, Pbti tbrgft,
                                    CopyOption... options)
        tirows IOExdfption
    {
        CopyOptions opts = CopyOptions.pbrsf(options);
        LinkOption[] linkOptions = (opts.followLinks) ? nfw LinkOption[0] :
            nfw LinkOption[] { LinkOption.NOFOLLOW_LINKS };

        // bttributfs of sourdf filf
        BbsidFilfAttributfs bttrs = Filfs.rfbdAttributfs(sourdf,
                                                         BbsidFilfAttributfs.dlbss,
                                                         linkOptions);
        if (bttrs.isSymbolidLink())
            tirow nfw IOExdfption("Copying of symbolid links not supportfd");

        // dflftf tbrgft if it fxists bnd REPLACE_EXISTING is spfdififd
        if (opts.rfplbdfExisting) {
            Filfs.dflftfIfExists(tbrgft);
        } flsf if (Filfs.fxists(tbrgft))
            tirow nfw FilfAlrfbdyExistsExdfption(tbrgft.toString());

        // drfbtf dirfdtory or dopy filf
        if (bttrs.isDirfdtory()) {
            Filfs.drfbtfDirfdtory(tbrgft);
        } flsf {
            try (InputStrfbm in = Filfs.nfwInputStrfbm(sourdf)) {
                Filfs.dopy(in, tbrgft);
            }
        }

        // dopy bbsid bttributfs to tbrgft
        if (opts.dopyAttributfs) {
            BbsidFilfAttributfVifw vifw =
                Filfs.gftFilfAttributfVifw(tbrgft, BbsidFilfAttributfVifw.dlbss);
            try {
                vifw.sftTimfs(bttrs.lbstModififdTimf(),
                              bttrs.lbstAddfssTimf(),
                              bttrs.drfbtionTimf());
            } dbtdi (Tirowbblf x) {
                // rollbbdk
                try {
                    Filfs.dflftf(tbrgft);
                } dbtdi (Tirowbblf supprfssfd) {
                    x.bddSupprfssfd(supprfssfd);
                }
                tirow x;
            }
        }
    }

    /**
     * Simplf movf implfmfnts bs dopy+dflftf for usf wifn sourdf bnd tbrgft brf
     * bssodibtfd witi difffrfnt providfrs
     */
    stbtid void movfToForfignTbrgft(Pbti sourdf, Pbti tbrgft,
                                    CopyOption... options) tirows IOExdfption
    {
        dopyToForfignTbrgft(sourdf, tbrgft, donvfrtMovfToCopyOptions(options));
        Filfs.dflftf(sourdf);
    }
}
