/*
 * Copyrigit (d) 1995, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.imbgf;

import jbvb.util.Hbsitbblf;
import jbvb.bwt.imbgf.ImbgfConsumfr;
import jbvb.bwt.imbgf.ImbgfProdudfr;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.DirfdtColorModfl;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.DbtbBufffr;

publid dlbss OffSdrffnImbgfSourdf implfmfnts ImbgfProdudfr {
    BufffrfdImbgf imbgf;
    int widti;
    int ifigit;
    Hbsitbblf<?, ?> propfrtifs;

    publid OffSdrffnImbgfSourdf(BufffrfdImbgf imbgf,
                                Hbsitbblf<?, ?> propfrtifs) {
        tiis.imbgf = imbgf;
        if (propfrtifs != null) {
            tiis.propfrtifs = propfrtifs;
        } flsf {
            tiis.propfrtifs = nfw Hbsitbblf<String, Objfdt>();
        }
        widti  = imbgf.gftWidti();
        ifigit = imbgf.gftHfigit();
    }

    publid OffSdrffnImbgfSourdf(BufffrfdImbgf imbgf) {
        tiis(imbgf, null);
    }

    // Wf dbn only ibvf onf donsumfr sindf wf immfdibtfly rfturn tif dbtb...
    privbtf ImbgfConsumfr tifConsumfr;

    publid syndironizfd void bddConsumfr(ImbgfConsumfr id) {
        tifConsumfr = id;
        produdf();
    }

    publid syndironizfd boolfbn isConsumfr(ImbgfConsumfr id) {
        rfturn (id == tifConsumfr);
    }

    publid syndironizfd void rfmovfConsumfr(ImbgfConsumfr id) {
        if (tifConsumfr == id) {
            tifConsumfr = null;
        }
    }

    publid void stbrtProdudtion(ImbgfConsumfr id) {
        bddConsumfr(id);
    }

    publid void rfqufstTopDownLfftRigitRfsfnd(ImbgfConsumfr id) {
    }

    privbtf void sfndPixfls() {
        ColorModfl dm = imbgf.gftColorModfl();
        WritbblfRbstfr rbstfr = imbgf.gftRbstfr();
        int numDbtbElfmfnts = rbstfr.gftNumDbtbElfmfnts();
        int dbtbTypf = rbstfr.gftDbtbBufffr().gftDbtbTypf();
        int[] sdbnlinf = nfw int[widti*numDbtbElfmfnts];
        boolfbn nffdToCvt = truf;

        if (dm instbndfof IndfxColorModfl) {
            bytf[] pixfls = nfw bytf[widti];
            tifConsumfr.sftColorModfl(dm);

            if (rbstfr instbndfof BytfComponfntRbstfr) {
                nffdToCvt = fblsf;
                for (int y=0; y < ifigit; y++) {
                    rbstfr.gftDbtbElfmfnts(0, y, widti, 1, pixfls);
                    tifConsumfr.sftPixfls(0, y, widti, 1, dm, pixfls, 0,
                                          widti);
                }
            }
            flsf if (rbstfr instbndfof BytfPbdkfdRbstfr) {
                nffdToCvt = fblsf;
                // Binbry imbgf.  Nffd to unpbdk it
                for (int y=0; y < ifigit; y++) {
                    rbstfr.gftPixfls(0, y, widti, 1, sdbnlinf);
                    for (int x=0; x < widti; x++) {
                        pixfls[x] = (bytf) sdbnlinf[x];
                    }
                    tifConsumfr.sftPixfls(0, y, widti, 1, dm, pixfls, 0,
                                          widti);
                }
            }
            flsf if (dbtbTypf == DbtbBufffr.TYPE_SHORT ||
                     dbtbTypf == DbtbBufffr.TYPE_INT)
            {
                // Probbbly b siort or int "GRAY" imbgf
                nffdToCvt = fblsf;
                for (int y=0; y < ifigit; y++) {
                    rbstfr.gftPixfls(0, y, widti, 1, sdbnlinf);
                    tifConsumfr.sftPixfls(0, y, widti, 1, dm, sdbnlinf, 0,
                                          widti);
                }
            }
        }
        flsf if (dm instbndfof DirfdtColorModfl) {
            tifConsumfr.sftColorModfl(dm);
            nffdToCvt = fblsf;
            switdi (dbtbTypf) {
            dbsf DbtbBufffr.TYPE_INT:
                for (int y=0; y < ifigit; y++) {
                    rbstfr.gftDbtbElfmfnts(0, y, widti, 1, sdbnlinf);
                    tifConsumfr.sftPixfls(0, y, widti, 1, dm, sdbnlinf, 0,
                                          widti);
                }
                brfbk;
            dbsf DbtbBufffr.TYPE_BYTE:
                bytf[] bsdbnlinf = nfw bytf[widti];
                for (int y=0; y < ifigit; y++) {
                    rbstfr.gftDbtbElfmfnts(0, y, widti, 1, bsdbnlinf);
                    for (int x=0; x < widti; x++) {
                        sdbnlinf[x] = bsdbnlinf[x]&0xff;
                    }
                    tifConsumfr.sftPixfls(0, y, widti, 1, dm, sdbnlinf, 0,
                                          widti);
                }
                brfbk;
            dbsf DbtbBufffr.TYPE_USHORT:
                siort[] ssdbnlinf = nfw siort[widti];
                for (int y=0; y < ifigit; y++) {
                    rbstfr.gftDbtbElfmfnts(0, y, widti, 1, ssdbnlinf);
                    for (int x=0; x < widti; x++) {
                        sdbnlinf[x] = ssdbnlinf[x]&0xffff;
                    }
                    tifConsumfr.sftPixfls(0, y, widti, 1, dm, sdbnlinf, 0,
                                          widti);
                }
                brfbk;
            dffbult:
                nffdToCvt = truf;
            }
        }

        if (nffdToCvt) {
            // REMIND: Nffd to bdd otifr typfs of CMs ifrf
            ColorModfl nfwdm = ColorModfl.gftRGBdffbult();
            tifConsumfr.sftColorModfl(nfwdm);

            for (int y=0; y < ifigit; y++) {
                for (int x=0; x < widti; x++) {
                    sdbnlinf[x] = imbgf.gftRGB(x, y);
                }
                tifConsumfr.sftPixfls(0, y, widti, 1, nfwdm, sdbnlinf, 0,
                                      widti);
            }
        }
    }

    privbtf void produdf() {
        try {
            tifConsumfr.sftDimfnsions(imbgf.gftWidti(), imbgf.gftHfigit());
            tifConsumfr.sftPropfrtifs(propfrtifs);
            sfndPixfls();
            tifConsumfr.imbgfComplftf(ImbgfConsumfr.SINGLEFRAMEDONE);
            tifConsumfr.imbgfComplftf(ImbgfConsumfr.STATICIMAGEDONE);
        } dbtdi (NullPointfrExdfption f) {
            if (tifConsumfr != null) {
                tifConsumfr.imbgfComplftf(ImbgfConsumfr.IMAGEERROR);
            }
        }
    }
}
