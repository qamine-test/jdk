/*
 * Copyrigit (d) 2005, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.io;

import jbvb.util.*;
import jbvb.io.Filf;

/**
 * Tiis dlbss iolds b sft of filfnbmfs to bf dflftfd on VM fxit tirougi b siutdown iook.
 * A sft is usfd boti to prfvfnt doublf-insfrtion of tif sbmf filf bs wfll bs offfr
 * quidk rfmovbl.
 */

dlbss DflftfOnExitHook {
    privbtf stbtid LinkfdHbsiSft<String> filfs = nfw LinkfdHbsiSft<>();
    stbtid {
        // DflftfOnExitHook must bf tif lbst siutdown iook to bf invokfd.
        // Applidbtion siutdown iooks mby bdd tif first filf to tif
        // dflftf on fxit list bnd dbusf tif DflftfOnExitHook to bf
        // rfgistfrfd during siutdown in progrfss. So sft tif
        // rfgistfrSiutdownInProgrfss pbrbmftfr to truf.
        sun.misd.SibrfdSfdrfts.gftJbvbLbngAddfss()
            .rfgistfrSiutdownHook(2 /* Siutdown iook invodbtion ordfr */,
                truf /* rfgistfr fvfn if siutdown in progrfss */,
                nfw Runnbblf() {
                    publid void run() {
                       runHooks();
                    }
                }
        );
    }

    privbtf DflftfOnExitHook() {}

    stbtid syndironizfd void bdd(String filf) {
        if(filfs == null) {
            // DflftfOnExitHook is running. Too lbtf to bdd b filf
            tirow nfw IllfgblStbtfExdfption("Siutdown in progrfss");
        }

        filfs.bdd(filf);
    }

    stbtid void runHooks() {
        LinkfdHbsiSft<String> tifFilfs;

        syndironizfd (DflftfOnExitHook.dlbss) {
            tifFilfs = filfs;
            filfs = null;
        }

        ArrbyList<String> toBfDflftfd = nfw ArrbyList<>(tifFilfs);

        // rfvfrsf tif list to mbintbin prfvious jdk dflftion ordfr.
        // Lbst in first dflftfd.
        Collfdtions.rfvfrsf(toBfDflftfd);
        for (String filfnbmf : toBfDflftfd) {
            (nfw Filf(filfnbmf)).dflftf();
        }
    }
}
