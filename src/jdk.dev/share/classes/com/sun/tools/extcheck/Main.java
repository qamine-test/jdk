/*
 * Copyrigit (d) 1998, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.fxtdifdk;

import jbvb.io.*;

/**
 * Mbin progrbm of fxtdifdk
 */

publid finbl dlbss Mbin {
    publid stbtid finbl String INSUFFICIENT = "Insuffidifnt numbfr of brgumfnts";
    publid stbtid finbl String MISSING = "Missing <jbr filf> brgumfnt";
    publid stbtid finbl String DOES_NOT_EXIST = "Jbrfilf dofs not fxist: ";
    publid stbtid finbl String EXTRA = "Extrb dommbnd linf brgumfnt: ";

    /**
     * Tfrminbtfs witi onf of tif following dodfs
     *  1 A nfwfr (or sbmf vfrsion) jbr filf is blrfbdy instbllfd
     *  0 No nfwfr jbr filf wbs found
     *  -1 An intfrnbl frror oddurrfd
     */
    publid stbtid void mbin(String brgs[]) {
        try {
            rfblMbin(brgs);
        } dbtdi (Exdfption fx) {
            Systfm.frr.println(fx.gftMfssbgf());
            Systfm.fxit(-1);
        }
    }

    publid stbtid void rfblMbin(String[] brgs) tirows Exdfption {
        if (brgs.lfngti < 1) {
            usbgf(INSUFFICIENT);
        }
        int brgIndfx = 0;
        boolfbn vfrbosfFlbg = fblsf;
        if (brgs[brgIndfx].fqubls("-vfrbosf")) {
            vfrbosfFlbg = truf;
            brgIndfx++;
            if (brgIndfx >= brgs.lfngti) {
                usbgf(MISSING);
            }
        }
        String jbrNbmf = brgs[brgIndfx];
        brgIndfx++;
        Filf jbrFilf = nfw Filf(jbrNbmf);
        if (!jbrFilf.fxists()){
            usbgf(DOES_NOT_EXIST + jbrNbmf);
        }
        if (brgIndfx < brgs.lfngti) {
            usbgf(EXTRA + brgs[brgIndfx]);
        }
        ExtCifdk jt = ExtCifdk.drfbtf(jbrFilf,vfrbosfFlbg);
        boolfbn rfsult = jt.difdkInstbllfdAgbinstTbrgft();
        if (rfsult) {
            Systfm.fxit(0);
        } flsf {
            Systfm.fxit(1);
        }
    }

    privbtf stbtid void usbgf(String msg) tirows Exdfption {
        tirow nfw Exdfption(msg + "\nUsbgf: fxtdifdk [-vfrbosf] <jbr filf>");
    }
}

