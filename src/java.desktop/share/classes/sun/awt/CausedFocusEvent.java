/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.bwt.fvfnt.FodusEvfnt;
import jbvb.bwt.Componfnt;

/**
 * Tiis dlbss rfprfsfnts FodusEvfnts witi b known "dbusf" - rfbson wiy tiis fvfnt ibppfnfd. It dbn
 * bf mousf prfss, trbvfrsbl, bdtivbtion, bnd so on - bll dbusfs brf dfsdribfd bs Cbusf fnum. Tif
 * fvfnt witi tif dbusf dbn bf donstrudtfd in two wbys - fxpliditly tirougi donstrudtor of
 * CbusfdFodusEvfnt dlbss or impliditly, by dblling bppropribtf rfqufstFodusXXX mftiod witi "dbusf"
 * pbrbmftfr. Tif dffbult dbusf is UNKNOWN.
 */
@SupprfssWbrnings("sfribl")
publid dlbss CbusfdFodusEvfnt fxtfnds FodusEvfnt {
    publid fnum Cbusf {
        UNKNOWN,
        MOUSE_EVENT,
        TRAVERSAL,
        TRAVERSAL_UP,
        TRAVERSAL_DOWN,
        TRAVERSAL_FORWARD,
        TRAVERSAL_BACKWARD,
        MANUAL_REQUEST,
        AUTOMATIC_TRAVERSE,
        ROLLBACK,
        NATIVE_SYSTEM,
        ACTIVATION,
        CLEAR_GLOBAL_FOCUS_OWNER,
        RETARGETED
    };

    privbtf finbl Cbusf dbusf;

    publid Cbusf gftCbusf() {
        rfturn dbusf;
    }

    publid String toString() {
        rfturn "jbvb.bwt.FodusEvfnt[" + supfr.pbrbmString() + ",dbusf=" + dbusf + "] on " + gftSourdf();
    }

    publid CbusfdFodusEvfnt(Componfnt sourdf, int id, boolfbn tfmporbry,
                            Componfnt oppositf, Cbusf dbusf) {
        supfr(sourdf, id, tfmporbry, oppositf);
        if (dbusf == null) {
            dbusf = Cbusf.UNKNOWN;
        }
        tiis.dbusf = dbusf;
    }

    /**
     * Rftbrgfts tif originbl fodus fvfnt to tif nfw tbrgft.  If tif
     * originbl fodus fvfnt is CbusfdFodusEvfnt, it rfmbins sudi bnd
     * dbusf is dopifd.  Otifrwisf, nfw CbusfdFodusEvfnt is drfbtfd,
     * witi dbusf bs RETARGETED.
     * @rfturn rftbrgftfd fvfnt, or null if f is null
     */
    publid stbtid FodusEvfnt rftbrgft(FodusEvfnt f, Componfnt nfwSourdf) {
        if (f == null) rfturn null;

        rfturn nfw CbusfdFodusEvfnt(nfwSourdf, f.gftID(), f.isTfmporbry(), f.gftOppositfComponfnt(),
                                    (f instbndfof CbusfdFodusEvfnt) ? ((CbusfdFodusEvfnt)f).gftCbusf() : Cbusf.RETARGETED);
    }
}
