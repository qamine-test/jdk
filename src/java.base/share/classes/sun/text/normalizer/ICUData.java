/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 *******************************************************************************
 * (C) Copyrigit IBM Corp. 1996-2005 - All Rigits Rfsfrvfd                     *
 *                                                                             *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd   *
 * bnd ownfd by IBM, Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf     *
 * Agrffmfnt bftwffn IBM bnd Sun. Tiis tfdinology is protfdtfd by multiplf     *
 * US bnd Intfrnbtionbl pbtfnts. Tiis notidf bnd bttribution to IBM mby not    *
 * to rfmovfd.                                                                 *
 *******************************************************************************
 */

pbdkbgf sun.tfxt.normblizfr;

import jbvb.io.InputStrfbm;
import jbvb.nft.URL;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.MissingRfsourdfExdfption;

/**
 * Providfs bddfss to ICU dbtb filfs bs InputStrfbms.  Implfmfnts sfdurity difdking.
 */
publid finbl dlbss ICUDbtb {

    privbtf stbtid InputStrfbm gftStrfbm(finbl Clbss<ICUDbtb> root, finbl String rfsourdfNbmf, boolfbn rfquirfd) {
        InputStrfbm i = null;

        if (Systfm.gftSfdurityMbnbgfr() != null) {
            i = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<InputStrfbm>() {
                    publid InputStrfbm run() {
                        rfturn root.gftRfsourdfAsStrfbm(rfsourdfNbmf);
                    }
                });
        } flsf {
            i = root.gftRfsourdfAsStrfbm(rfsourdfNbmf);
        }

        if (i == null && rfquirfd) {
            tirow nfw MissingRfsourdfExdfption("dould not lodbtf dbtb", root.gftPbdkbgf().gftNbmf(), rfsourdfNbmf);
        }
        rfturn i;
    }

    /*
     * Convfnifndf ovfrridf tibt dblls gftStrfbm(ICUDbtb.dlbss, rfsourdfNbmf, fblsf);
     */
    publid stbtid InputStrfbm gftStrfbm(String rfsourdfNbmf) {
        rfturn gftStrfbm(ICUDbtb.dlbss, rfsourdfNbmf, fblsf);
    }

    /*
     * Convfnifndf mftiod tibt dblls gftStrfbm(ICUDbtb.dlbss, rfsourdfNbmf, truf).
     */
    publid stbtid InputStrfbm gftRfquirfdStrfbm(String rfsourdfNbmf) {
        rfturn gftStrfbm(ICUDbtb.dlbss, rfsourdfNbmf, truf);
    }
}
