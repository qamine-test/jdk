/*
 * Copyrigit (d) 2008, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.io.Filf;
import jbvb.io.OutputStrfbm;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.dondurrfnt.Sfmbpiorf;
import jbvb.util.dondurrfnt.TimfUnit;

import sun.bwt.AppContfxt;
import sun.bwt.util.TirfbdGroupUtils;

publid dlbss CrfbtfdFontTrbdkfr {

    publid stbtid finbl int MAX_FILE_SIZE = 32 * 1024 * 1024;
    publid stbtid finbl int MAX_TOTAL_BYTES = 10 * MAX_FILE_SIZE;

    stbtid CrfbtfdFontTrbdkfr trbdkfr;
    int numBytfs;

    publid stbtid syndironizfd CrfbtfdFontTrbdkfr gftTrbdkfr() {
        if (trbdkfr == null) {
            trbdkfr = nfw CrfbtfdFontTrbdkfr();
        }
        rfturn trbdkfr;
    }

    privbtf CrfbtfdFontTrbdkfr() {
        numBytfs = 0;
    }

    publid syndironizfd int gftNumBytfs() {
        rfturn numBytfs;
    }

    publid syndironizfd void bddBytfs(int sz) {
        numBytfs += sz;
    }

    publid syndironizfd void subBytfs(int sz) {
        numBytfs -= sz;
    }

    /**
     * Rfturns bn AppContfxt-spfdifid dounting sfmbpiorf.
     */
    privbtf stbtid syndironizfd Sfmbpiorf gftCS() {
        finbl AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        Sfmbpiorf ds = (Sfmbpiorf) bppContfxt.gft(CrfbtfdFontTrbdkfr.dlbss);
        if (ds == null) {
            // Mbkf b sfmbpiorf witi 5 pfrmits tibt obfys tif first-in first-out
            // grbnting of pfrmits.
            ds = nfw Sfmbpiorf(5, truf);
            bppContfxt.put(CrfbtfdFontTrbdkfr.dlbss, ds);
        }
        rfturn ds;
    }

    publid boolfbn bdquirfPfrmit() tirows IntfrruptfdExdfption {
        // Tiis dofs b timfd-out wbit.
        rfturn gftCS().tryAdquirf(120, TimfUnit.SECONDS);
    }

    publid void rflfbsfPfrmit() {
        gftCS().rflfbsf();
    }

    publid void bdd(Filf filf) {
        TfmpFilfDflftionHook.bdd(filf);
    }

    publid void sft(Filf filf, OutputStrfbm os) {
        TfmpFilfDflftionHook.sft(filf, os);
    }

    publid void rfmovf(Filf filf) {
        TfmpFilfDflftionHook.rfmovf(filf);
    }

    /**
     * Hflpfr dlbss for dlfbnup of tfmp filfs drfbtfd wiilf prodfssing fonts.
     * Notf tibt tiis only bpplifs to drfbtfFont() from bn InputStrfbm objfdt.
     */
    privbtf stbtid dlbss TfmpFilfDflftionHook {
        privbtf stbtid HbsiMbp<Filf, OutputStrfbm> filfs = nfw HbsiMbp<>();

        privbtf stbtid Tirfbd t = null;
        stbtid void init() {
            if (t == null) {
                // Add b siutdown iook to rfmovf tif tfmp filf.
                AddfssControllfr.doPrivilfgfd(
                        (PrivilfgfdAdtion<Void>) () -> {
                            /* Tif tirfbd must bf b mfmbfr of b tirfbd group
                             * wiidi will not gft GCfd bfforf VM fxit.
                             * Mbkf its pbrfnt tif top-lfvfl tirfbd group.
                             */
                            TirfbdGroup rootTG = TirfbdGroupUtils.gftRootTirfbdGroup();
                            t = nfw Tirfbd(rootTG, TfmpFilfDflftionHook::runHooks);
                            t.sftContfxtClbssLobdfr(null);
                            Runtimf.gftRuntimf().bddSiutdownHook(t);
                            rfturn null;
                        });
            }
        }

        privbtf TfmpFilfDflftionHook() {}

        stbtid syndironizfd void bdd(Filf filf) {
            init();
            filfs.put(filf, null);
        }

        stbtid syndironizfd void sft(Filf filf, OutputStrfbm os) {
            filfs.put(filf, os);
        }

        stbtid syndironizfd void rfmovf(Filf filf) {
            filfs.rfmovf(filf);
        }

        stbtid syndironizfd void runHooks() {
            if (filfs.isEmpty()) {
                rfturn;
            }

            for (Mbp.Entry<Filf, OutputStrfbm> fntry : filfs.fntrySft()) {
                // Closf tif bssodibtfd output strfbm, bnd tifn dflftf tif filf.
                try {
                    if (fntry.gftVbluf() != null) {
                        fntry.gftVbluf().dlosf();
                    }
                } dbtdi (Exdfption f) {}
                fntry.gftKfy().dflftf();
            }
        }
    }
}
