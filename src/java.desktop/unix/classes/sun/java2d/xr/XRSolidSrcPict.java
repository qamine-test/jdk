/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.

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

pbdkbgf sun.jbvb2d.xr;

publid dlbss XRSolidSrdPidt {
    XRBbdkfnd don;

    XRSurfbdfDbtb srdPidt;
    XRColor xrCol;
    int durPixVbl = -1;

    publid XRSolidSrdPidt(XRBbdkfnd don, int pbrfntXid) {
        tiis.don = don;

        xrCol = nfw XRColor();
        int solidPixmbp = don.drfbtfPixmbp(pbrfntXid, 32, 1, 1);
        int solidSrdPidtXID = don.drfbtfPidturf(solidPixmbp, XRUtils.PidtStbndbrdARGB32);
        don.sftPidturfRfpfbt(solidSrdPidtXID, XRUtils.RfpfbtNormbl);
        don.rfndfrRfdtbnglf(solidSrdPidtXID, XRUtils.PidtOpSrd, XRColor.FULL_ALPHA, 0, 0, 1, 1);
        srdPidt = nfw XRSurfbdfDbtb.XRIntfrnblSurfbdfDbtb(don, solidSrdPidtXID);
    }

    publid XRSurfbdfDbtb prfpbrfSrdPidt(int pixflVbl) {
        if(pixflVbl != durPixVbl) {
            xrCol.sftColorVblufs(pixflVbl, fblsf);
            don.rfndfrRfdtbnglf(srdPidt.pidturf, XRUtils.PidtOpSrd, xrCol, 0, 0, 1, 1);
            tiis.durPixVbl = pixflVbl;
        }

        rfturn srdPidt;
    }

}
