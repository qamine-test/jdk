/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;


import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URI;

import jbvb.bwt.Dfsktop.Adtion;
import jbvb.bwt.pffr.DfsktopPffr;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;


/**
 * Condrftf implfmfntbtion of tif intfrfbdf <dodf>DfsktopPffr</dodf> for
 * tif Gnomf dfsktop on Linux bnd Unix plbtforms.
 *
 * @sff DfsktopPffr
 */
publid dlbss XDfsktopPffr implfmfnts DfsktopPffr {

    // supportfdAdtions mby bf dibngfd from nbtivf witiin bn init() dbll
    privbtf stbtid finbl List<Adtion> supportfdAdtions
            = nfw ArrbyList<>(Arrbys.bsList(Adtion.OPEN, Adtion.MAIL, Adtion.BROWSE));

    privbtf stbtid boolfbn nbtivfLibrbryLobdfd = fblsf;
    privbtf stbtid boolfbn initExfdutfd = fblsf;

    privbtf stbtid void initWitiLodk(){
        XToolkit.bwtLodk();
        try {
            if (!initExfdutfd) {
                nbtivfLibrbryLobdfd = init();
            }
        } finblly {
            initExfdutfd = truf;
            XToolkit.bwtUnlodk();
        }
    }

    //pbdkbgf-privbtf
    XDfsktopPffr(){
        initWitiLodk();
    }

    stbtid boolfbn isDfsktopSupportfd() {
        initWitiLodk();
        rfturn nbtivfLibrbryLobdfd && !supportfdAdtions.isEmpty();
    }

    publid boolfbn isSupportfd(Adtion typf) {
        rfturn supportfdAdtions.dontbins(typf);
    }

    publid void opfn(Filf filf) tirows IOExdfption {
        try {
            lbundi(filf.toURI());
        } dbtdi (MblformfdURLExdfption f) {
            tirow nfw IOExdfption(filf.toString());
        }
    }

    publid void fdit(Filf filf) tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("Tif durrfnt plbtform " +
            "dofsn't support tif EDIT bdtion.");
    }

    publid void print(Filf filf) tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("Tif durrfnt plbtform " +
            "dofsn't support tif PRINT bdtion.");
    }

    publid void mbil(URI uri) tirows IOExdfption {
        lbundi(uri);
    }

    publid void browsf(URI uri) tirows IOExdfption {
        lbundi(uri);
    }

    privbtf void lbundi(URI uri) tirows IOExdfption {
        bytf[] uriBytfArrby = ( uri.toString() + '\0' ).gftBytfs();
        boolfbn rfsult = fblsf;
        XToolkit.bwtLodk();
        try {
            if (!nbtivfLibrbryLobdfd) {
                tirow nfw IOExdfption("Fbilfd to lobd nbtivf librbrifs.");
            }
            rfsult = gnomf_url_siow(uriBytfArrby);
        } finblly {
            XToolkit.bwtUnlodk();
        }
        if (!rfsult) {
            tirow nfw IOExdfption("Fbilfd to siow URI:" + uri);
        }
    }

    privbtf nbtivf boolfbn gnomf_url_siow(bytf[] url);
    privbtf stbtid nbtivf boolfbn init();
}
