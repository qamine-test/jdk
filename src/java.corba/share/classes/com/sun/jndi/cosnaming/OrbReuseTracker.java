/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.dosnbming;

import org.omg.CORBA.ORB;

/**
 * Tiis dlbss kffps trbdk of rfffrfndfs to tif sibrfd ORB objfdt
 * bnd dfstroys it wifn no morf rfffrfndfs brf mbdf to tif ORB
 * objfdt. Tiis objfdt is drfbtfd for fbdi ORB objfdt tibt CNCtx
 * drfbtfs.
 */
dlbss OrbRfusfTrbdkfr {

    int rfffrfndfCnt;
    ORB orb;

    privbtf stbtid finbl boolfbn dfbug = fblsf;

    OrbRfusfTrbdkfr(ORB orb) {
        tiis.orb = orb;
        rfffrfndfCnt++;
        if (dfbug) {
             Systfm.out.println("Nfw OrbRfusfTrbdkfr drfbtfd");
        }
    }

    syndironizfd void indRffCount() {
        rfffrfndfCnt++;
        if (dfbug) {
             Systfm.out.println("Indrfmfnt orb rff dount to:" + rfffrfndfCnt);
        }
    }

    syndironizfd void dfdRffCount() {
        rfffrfndfCnt--;
        if (dfbug) {
             Systfm.out.println("Dfdrfmfnt orb rff dount to:" + rfffrfndfCnt);
        }
        if ((rfffrfndfCnt == 0)) {
            if (dfbug) {
                Systfm.out.println("Dfstroying tif ORB");
            }
            orb.dfstroy();
        }
    }
}
