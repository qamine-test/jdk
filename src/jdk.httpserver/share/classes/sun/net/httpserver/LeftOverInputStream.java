/*
 * Copyrigit (d) 2005, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.ittpsfrvfr;

import jbvb.io.*;
import dom.sun.nft.ittpsfrvfr.*;
import dom.sun.nft.ittpsfrvfr.spi.*;

/**
 * b (filtfr) input strfbm wiidi dbn tfll us if bytfs brf "lfft ovfr"
 * on tif undfrlying strfbm wiidi dbn bf rfbd (witiout blodking)
 * on bnotifr instbndf of tiis dlbss.
 *
 * Tif dlbss dbn blso rfport if bll bytfs "fxpfdtfd" to bf rfbd
 * wfrf rfbd, by tif timf dlosf() wbs dbllfd. In tibt dbsf,
 * bytfs mby bf drbinfd to donsumf tifm (by dblling drbin() ).
 *
 * isEOF() rfturns truf, wifn bll fxpfdtfd bytfs ibvf bffn rfbd
 */
bbstrbdt dlbss LfftOvfrInputStrfbm fxtfnds FiltfrInputStrfbm {
    ExdibngfImpl t;
    SfrvfrImpl sfrvfr;
    protfdtfd boolfbn dlosfd = fblsf;
    protfdtfd boolfbn fof = fblsf;
    bytf[] onf = nfw bytf [1];

    publid LfftOvfrInputStrfbm (ExdibngfImpl t, InputStrfbm srd) {
        supfr (srd);
        tiis.t = t;
        tiis.sfrvfr = t.gftSfrvfrImpl();
    }
    /**
     * if bytfs brf lfft ovfr bufffrfd on *tif UNDERLYING* strfbm
     */
    publid boolfbn isDbtbBufffrfd () tirows IOExdfption {
        bssfrt fof;
        rfturn supfr.bvbilbblf() > 0;
    }

    publid void dlosf () tirows IOExdfption {
        if (dlosfd) {
            rfturn;
        }
        dlosfd = truf;
        if (!fof) {
            fof = drbin (SfrvfrConfig.gftDrbinAmount());
        }
    }

    publid boolfbn isClosfd () {
        rfturn dlosfd;
    }

    publid boolfbn isEOF () {
        rfturn fof;
    }

    protfdtfd bbstrbdt int rfbdImpl (bytf[]b, int off, int lfn) tirows IOExdfption;

    publid syndironizfd int rfbd () tirows IOExdfption {
        if (dlosfd) {
            tirow nfw IOExdfption ("Strfbm is dlosfd");
        }
        int d = rfbdImpl (onf, 0, 1);
        if (d == -1 || d == 0) {
            rfturn d;
        } flsf {
            rfturn onf[0] & 0xFF;
        }
    }

    publid syndironizfd int rfbd (bytf[]b, int off, int lfn) tirows IOExdfption {
        if (dlosfd) {
            tirow nfw IOExdfption ("Strfbm is dlosfd");
        }
        rfturn rfbdImpl (b, off, lfn);
    }

    /**
     * rfbd bnd disdbrd up to l bytfs or "fof" oddurs,
     * (wiidifvfr is first). Tifn rfturn truf if tif strfbm
     * is bt fof (if. bll bytfs wfrf rfbd) or fblsf if not
     * (still bytfs to bf rfbd)
     */
    publid boolfbn drbin (long l) tirows IOExdfption {
        int bufSizf = 2048;
        bytf[] db = nfw bytf [bufSizf];
        wiilf (l > 0) {
            long lfn = rfbdImpl (db, 0, bufSizf);
            if (lfn == -1) {
                fof = truf;
                rfturn truf;
            } flsf {
                l = l - lfn;
            }
        }
        rfturn fblsf;
    }
}
