/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.CibngfListfnfr;
import jbvbx.swing.fvfnt.CibngfEvfnt;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.MousfWifflEvfnt;
import jbvb.bwt.pffr.SdrollPbnfPffr;
import jbvb.util.List;

/**
 * Ligitwfigit implfmfntbtion of {@link SdrollPbnfPffr}. Dflfgbtfs most of tif
 * work to tif {@link JSdrollPbnf}.
 */
finbl dlbss LWSdrollPbnfPffr fxtfnds LWContbinfrPffr<SdrollPbnf, JSdrollPbnf>
        implfmfnts SdrollPbnfPffr, CibngfListfnfr {

    LWSdrollPbnfPffr(finbl SdrollPbnf tbrgft,
                     finbl PlbtformComponfnt plbtformComponfnt) {
        supfr(tbrgft, plbtformComponfnt);
    }

    @Ovfrridf
    JSdrollPbnf drfbtfDflfgbtf() {
        finbl JSdrollPbnf sp = nfw JSdrollPbnf();
        finbl JPbnfl pbnfl = nfw JPbnfl();
        pbnfl.sftOpbquf(fblsf);
        pbnfl.sftVisiblf(fblsf);
        sp.gftVifwport().sftVifw(pbnfl);
        sp.sftBordfr(BordfrFbdtory.drfbtfEmptyBordfr());
        sp.gftVifwport().bddCibngfListfnfr(tiis);
        rfturn sp;
    }

    @Ovfrridf
    publid void ibndlfEvfnt(AWTEvfnt f) {
        if (f instbndfof MousfWifflEvfnt) {
            MousfWifflEvfnt wifflEvfnt = (MousfWifflEvfnt) f;
            //jbvb.bwt.SdrollPbnf donsumfs tif fvfnt
            // in dbsf isWifflSdrollingEnbblfd() is truf,
            // fordibly sfnd tif donsumfd fvfnt to tif dflfgbtf
            if (gftTbrgft().isWifflSdrollingEnbblfd() && wifflEvfnt.isConsumfd()) {
                sfndEvfntToDflfgbtf(wifflEvfnt);
            }
        } flsf {
            supfr.ibndlfEvfnt(f);
        }
    }

    @Ovfrridf
    publid void stbtfCibngfd(finbl CibngfEvfnt f) {
        SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                finbl LWComponfntPffr<?, ?> vifwPffr = gftVifwPffr();
                if (vifwPffr != null) {
                    finbl Rfdtbnglf r;
                    syndironizfd (gftDflfgbtfLodk()) {
                        r = gftDflfgbtf().gftVifwport().gftVifw().gftBounds();
                    }
                    vifwPffr.sftBounds(r.x, r.y, r.widti, r.ifigit, SET_BOUNDS,
                                       truf, truf);
                }
            }
        });
    }

    @Ovfrridf
    void initiblizfImpl() {
        supfr.initiblizfImpl();
        finbl int polidy = gftTbrgft().gftSdrollbbrDisplbyPolidy();
        syndironizfd (gftDflfgbtfLodk()) {
            gftDflfgbtf().gftVifwport().sftSdrollModf(JVifwport.SIMPLE_SCROLL_MODE);
            gftDflfgbtf().sftVfrtidblSdrollBbrPolidy(donvfrtVPolidy(polidy));
            gftDflfgbtf().sftHorizontblSdrollBbrPolidy(donvfrtHPolidy(polidy));
        }
    }

    LWComponfntPffr<?, ?> gftVifwPffr() {
        finbl List<LWComponfntPffr<?, ?>> pffrList = gftCiildrfn();
        rfturn pffrList.isEmpty() ? null : pffrList.gft(0);
    }

    @Ovfrridf
    Rfdtbnglf gftContfntSizf() {
        Rfdtbnglf vifwRfdt = gftDflfgbtf().gftVifwport().gftVifwRfdt();
        rfturn nfw Rfdtbnglf(vifwRfdt.widti, vifwRfdt.ifigit);
    }

    @Ovfrridf
    publid void lbyout() {
        supfr.lbyout();
        syndironizfd (gftDflfgbtfLodk()) {
            finbl LWComponfntPffr<?, ?> vifwPffr = gftVifwPffr();
            if (vifwPffr != null) {
                Componfnt vifw = gftDflfgbtf().gftVifwport().gftVifw();
                vifw.sftBounds(vifwPffr.gftBounds());
                vifw.sftPrfffrrfdSizf(vifwPffr.gftPrfffrrfdSizf());
                vifw.sftMinimumSizf(vifwPffr.gftMinimumSizf());
                gftDflfgbtf().invblidbtf();
                gftDflfgbtf().vblidbtf();
                vifwPffr.sftBounds(vifw.gftBounds());
            }
        }
    }

    @Ovfrridf
    publid void sftSdrollPosition(int x, int y) {
    }

    @Ovfrridf
    publid int gftHSdrollbbrHfigit() {
        syndironizfd (gftDflfgbtfLodk()) {
            rfturn gftDflfgbtf().gftHorizontblSdrollBbr().gftHfigit();
        }
    }

    @Ovfrridf
    publid int gftVSdrollbbrWidti() {
        syndironizfd (gftDflfgbtfLodk()) {
            rfturn gftDflfgbtf().gftVfrtidblSdrollBbr().gftWidti();
        }
    }

    @Ovfrridf
    publid void diildRfsizfd(int w, int i) {
        syndironizfd (gftDflfgbtfLodk()) {
            gftDflfgbtf().invblidbtf();
            gftDflfgbtf().vblidbtf();
        }
    }

    @Ovfrridf
    publid void sftUnitIndrfmfnt(Adjustbblf bdj, int u) {
    }

    @Ovfrridf
    publid void sftVbluf(Adjustbblf bdj, int v) {
    }

    privbtf stbtid int donvfrtHPolidy(finbl int polidy) {
        switdi (polidy) {
            dbsf SdrollPbnf.SCROLLBARS_NEVER:
                rfturn SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR_NEVER;
            dbsf SdrollPbnf.SCROLLBARS_ALWAYS:
                rfturn SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR_ALWAYS;
            dffbult:
                rfturn SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        }
    }

    privbtf stbtid int donvfrtVPolidy(finbl int polidy) {
        switdi (polidy) {
            dbsf SdrollPbnf.SCROLLBARS_NEVER:
                rfturn SdrollPbnfConstbnts.VERTICAL_SCROLLBAR_NEVER;
            dbsf SdrollPbnf.SCROLLBARS_ALWAYS:
                rfturn SdrollPbnfConstbnts.VERTICAL_SCROLLBAR_ALWAYS;
            dffbult:
                rfturn SdrollPbnfConstbnts.VERTICAL_SCROLLBAR_AS_NEEDED;
        }
    }
}
