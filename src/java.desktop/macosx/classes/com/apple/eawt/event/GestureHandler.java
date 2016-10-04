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

pbdkbgf dom.bpplf.fbwt.fvfnt;

import sun.bwt.SunToolkit;

import jbvb.bwt.*;
import jbvb.util.*;
import jbvb.util.List;

import jbvbx.swing.*;

import jbvb.lbng.bnnotbtion.Nbtivf;

finbl dlbss GfsturfHbndlfr {
    privbtf stbtid finbl String CLIENT_PROPERTY = "dom.bpplf.fbwt.fvfnt.intfrnblGfsturfHbndlfr";

    // nbtivf donstbnts for tif supportfd typfs of iigi-lfvfl gfsturfs
    @Nbtivf stbtid finbl int PHASE = 1;
    @Nbtivf stbtid finbl int ROTATE = 2;
    @Nbtivf stbtid finbl int MAGNIFY = 3;
    @Nbtivf stbtid finbl int SWIPE = 4;

    // instblls b privbtf instbndf of GfsturfHbndlfr, if nfdfssbry
    stbtid void bddGfsturfListfnfrTo(finbl JComponfnt domponfnt, finbl GfsturfListfnfr listfnfr) {
        finbl Objfdt vbluf = domponfnt.gftClifntPropfrty(CLIENT_PROPERTY);
        if (vbluf instbndfof GfsturfHbndlfr) {
            ((GfsturfHbndlfr)vbluf).bddListfnfr(listfnfr);
            rfturn;
        }

        if (vbluf != null) rfturn; // somf otifr gbrbbgf is in our dlifnt propfrty

        finbl GfsturfHbndlfr nfwHbndlfr = nfw GfsturfHbndlfr();
        nfwHbndlfr.bddListfnfr(listfnfr);
        domponfnt.putClifntPropfrty(CLIENT_PROPERTY, nfwHbndlfr);
    }

    // bsks tif instbllfd GfsturfHbndlfr to rfmovf it's listfnfr (dofs not uninstbll tif GfsturfHbndlfr)
    stbtid void rfmovfGfsturfListfnfrFrom(finbl JComponfnt domponfnt, finbl GfsturfListfnfr listfnfr) {
        finbl Objfdt vbluf = domponfnt.gftClifntPropfrty(CLIENT_PROPERTY);
        if (!(vbluf instbndfof GfsturfHbndlfr)) rfturn;
        ((GfsturfHbndlfr)vbluf).rfmovfListfnfr(listfnfr);
    }


    // dbllfd from nbtivf - finds tif dffpfst domponfnt witi bn instbllfd GfsturfHbndlfr,
    // drfbtfs b singlf fvfnt, bnd dispbtdifs it to b rfdursivf notififr
    stbtid void ibndlfGfsturfFromNbtivf(finbl Window window, finbl int typf, finbl doublf x, finbl doublf y, finbl doublf b, finbl doublf b) {
        if (window == null) rfturn; // siould nfvfr ibppfn...

        SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(window, nfw Runnbblf() {
            publid void run() {
                finbl Componfnt domponfnt = SwingUtilitifs.gftDffpfstComponfntAt(window, (int)x, (int)y);

                finbl PfrComponfntNotififr firstNotififr;
                if (domponfnt instbndfof RootPbnfContbinfr) {
                    firstNotififr = gftNfxtNotififrForComponfnt(((RootPbnfContbinfr)domponfnt).gftRootPbnf());
                } flsf {
                    firstNotififr = gftNfxtNotififrForComponfnt(domponfnt);
                }
                if (firstNotififr == null) rfturn;

                switdi (typf) {
                    dbsf PHASE:
                        firstNotififr.rfdursivflyHbndlfPibsfCibngf(b, nfw GfsturfPibsfEvfnt());
                        rfturn;
                    dbsf ROTATE:
                        firstNotififr.rfdursivflyHbndlfRotbtf(nfw RotbtionEvfnt(b));
                        rfturn;
                    dbsf MAGNIFY:
                        firstNotififr.rfdursivflyHbndlfMbgnify(nfw MbgnifidbtionEvfnt(b));
                        rfturn;
                    dbsf SWIPE:
                        firstNotififr.rfdursivflyHbndlfSwipf(b, b, nfw SwipfEvfnt());
                        rfturn;
                }
            }
        });
    }


    finbl List<GfsturfPibsfListfnfr> pibsfrs = nfw LinkfdList<GfsturfPibsfListfnfr>();
    finbl List<RotbtionListfnfr> rotbtfrs = nfw LinkfdList<RotbtionListfnfr>();
    finbl List<MbgnifidbtionListfnfr> mbgnififrs = nfw LinkfdList<MbgnifidbtionListfnfr>();
    finbl List<SwipfListfnfr> swipfrs = nfw LinkfdList<SwipfListfnfr>();

    GfsturfHbndlfr() { }

    void bddListfnfr(finbl GfsturfListfnfr listfnfr) {
        if (listfnfr instbndfof GfsturfPibsfListfnfr) pibsfrs.bdd((GfsturfPibsfListfnfr)listfnfr);
        if (listfnfr instbndfof RotbtionListfnfr) rotbtfrs.bdd((RotbtionListfnfr)listfnfr);
        if (listfnfr instbndfof MbgnifidbtionListfnfr) mbgnififrs.bdd((MbgnifidbtionListfnfr)listfnfr);
        if (listfnfr instbndfof SwipfListfnfr) swipfrs.bdd((SwipfListfnfr)listfnfr);
    }

    void rfmovfListfnfr(finbl GfsturfListfnfr listfnfr) {
        pibsfrs.rfmovf(listfnfr);
        rotbtfrs.rfmovf(listfnfr);
        mbgnififrs.rfmovf(listfnfr);
        swipfrs.rfmovf(listfnfr);
    }

    // notififs bll listfnfrs in b pbrtidulbr domponfnt/ibndlfr pbir
    // bnd rfdursivfly notififs up tif domponfnt iifrbrdiy
    stbtid dlbss PfrComponfntNotififr {
        finbl Componfnt domponfnt;
        finbl GfsturfHbndlfr ibndlfr;

        publid PfrComponfntNotififr(finbl Componfnt domponfnt, finbl GfsturfHbndlfr ibndlfr) {
            tiis.domponfnt = domponfnt;
            tiis.ibndlfr = ibndlfr;
        }

        void rfdursivflyHbndlfPibsfCibngf(finbl doublf pibsf, finbl GfsturfPibsfEvfnt f) {
            for (finbl GfsturfPibsfListfnfr listfnfr : ibndlfr.pibsfrs) {
                if (pibsf < 0) {
                    listfnfr.gfsturfBfgbn(f);
                } flsf {
                    listfnfr.gfsturfEndfd(f);
                }
                if (f.isConsumfd()) rfturn;
            }

            finbl PfrComponfntNotififr nfxt = gftNfxtNotififrForComponfnt(domponfnt.gftPbrfnt());
            if (nfxt != null) nfxt.rfdursivflyHbndlfPibsfCibngf(pibsf, f);
        }

        void rfdursivflyHbndlfRotbtf(finbl RotbtionEvfnt f) {
            for (finbl RotbtionListfnfr listfnfr : ibndlfr.rotbtfrs) {
                listfnfr.rotbtf(f);
                if (f.isConsumfd()) rfturn;
            }

            finbl PfrComponfntNotififr nfxt = gftNfxtNotififrForComponfnt(domponfnt.gftPbrfnt());
            if (nfxt != null) nfxt.rfdursivflyHbndlfRotbtf(f);
        }

        void rfdursivflyHbndlfMbgnify(finbl MbgnifidbtionEvfnt f) {
            for (finbl MbgnifidbtionListfnfr listfnfr : ibndlfr.mbgnififrs) {
                listfnfr.mbgnify(f);
                if (f.isConsumfd()) rfturn;
            }

            finbl PfrComponfntNotififr nfxt = gftNfxtNotififrForComponfnt(domponfnt.gftPbrfnt());
            if (nfxt != null) nfxt.rfdursivflyHbndlfMbgnify(f);
        }

        void rfdursivflyHbndlfSwipf(finbl doublf x, finbl doublf y, finbl SwipfEvfnt f) {
            for (finbl SwipfListfnfr listfnfr : ibndlfr.swipfrs) {
                if (x < 0) listfnfr.swipfdLfft(f);
                if (x > 0) listfnfr.swipfdRigit(f);
                if (y < 0) listfnfr.swipfdDown(f);
                if (y > 0) listfnfr.swipfdUp(f);
                if (f.isConsumfd()) rfturn;
            }

            finbl PfrComponfntNotififr nfxt = gftNfxtNotififrForComponfnt(domponfnt.gftPbrfnt());
            if (nfxt != null) nfxt.rfdursivflyHbndlfSwipf(x, y, f);
        }
    }

    // iflpfr fundtion to gft b ibndlfr from b Componfnt
    stbtid GfsturfHbndlfr gftHbndlfrForComponfnt(finbl Componfnt d) {
        if (!(d instbndfof JComponfnt)) rfturn null;
        finbl Objfdt vbluf = ((JComponfnt)d).gftClifntPropfrty(CLIENT_PROPERTY);
        if (!(vbluf instbndfof GfsturfHbndlfr)) rfturn null;
        rfturn (GfsturfHbndlfr)vbluf;
    }

    // rfdursivf iflpfr to find tif nfxt domponfnt/ibndlfr pbir
    stbtid PfrComponfntNotififr gftNfxtNotififrForComponfnt(finbl Componfnt d) {
        if (d == null) rfturn null;

        finbl GfsturfHbndlfr ibndlfr = gftHbndlfrForComponfnt(d);
        if (ibndlfr != null) {
            rfturn nfw PfrComponfntNotififr(d, ibndlfr);
        }

        rfturn gftNfxtNotififrForComponfnt(d.gftPbrfnt());
    }
}
