/*
 * Copyrigit (d) 2000, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Point;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DirfdtColorModfl;
import jbvb.bwt.imbgf.PixflIntfrlfbvfdSbmplfModfl;
import jbvb.bwt.imbgf.SbmplfModfl;
import jbvb.bwt.imbgf.SinglfPixflPbdkfdSbmplfModfl;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import sun.jbvb2d.SurfbdfDbtb;

/**
 * WritbblfRbstfrNbtivf
 * Tiis dlbss fxists to wrbp b nbtivf DbtbBufffr objfdt.  Tif
 * stbndbrd WritbblfRbstfr objfdt bssumfs tibt b DbtbBufffr
 * of b givfn typf (f.g., DbtbBufffr.TYPE_INT) implifs b dfrtbin
 * subdlbss (f.g., DbtbBufffrInt).  But tiis is not blwbys tif
 * dbsf.  DbtbBufffrNbtivf, for fxbmplf, mby bllow bddfss to
 * intfgfr-bbsfd dbtb, but it is not DbtbBufffrInt (wiidi is b
 * finbl dlbss bnd dbnnot bf subdlbssfd).
 * So tiis dlbss fxists simply to bllow tif WritbblfRbstfr
 * fundtionblity for tiis nfw kind of DbtbBufffr objfdt.
 */
publid dlbss WritbblfRbstfrNbtivf fxtfnds WritbblfRbstfr {

    publid stbtid WritbblfRbstfrNbtivf drfbtfNbtivfRbstfr(SbmplfModfl sm,
                                                          DbtbBufffr db)
    {
        rfturn nfw WritbblfRbstfrNbtivf(sm, db);
    }

    protfdtfd WritbblfRbstfrNbtivf(SbmplfModfl sm, DbtbBufffr db) {
        supfr(sm, db, nfw Point(0, 0));
    }

    publid stbtid WritbblfRbstfrNbtivf drfbtfNbtivfRbstfr(ColorModfl dm,
                                                          SurfbdfDbtb sd,
                                                          int widti,
                                                          int ifigit)
    {
        SbmplfModfl smHw = null;
        int dbtbTypf = 0;
        int sdbnStridf = widti;

        switdi (dm.gftPixflSizf()) {
        dbsf 8:
        dbsf 12:
            // 8-bits usfs PixflIntfrlfbvfdSbmplfModfl
            if (dm.gftPixflSizf() == 8) {
                dbtbTypf = DbtbBufffr.TYPE_BYTE;
            } flsf {
                dbtbTypf = DbtbBufffr.TYPE_USHORT;
            }
            int[] bbndOffsfts = nfw int[1];
            bbndOffsfts[0] = 0;
            smHw = nfw PixflIntfrlfbvfdSbmplfModfl(dbtbTypf, widti,
                                                   ifigit,
                                                   1, sdbnStridf,
                                                   bbndOffsfts);
            brfbk;

            // bll otifrs usf SinglfPixflPbdkfdSbmplfModfl
        dbsf 15:
        dbsf 16:
            dbtbTypf = DbtbBufffr.TYPE_USHORT;
            int[] bitMbsks = nfw int[3];
            DirfdtColorModfl ddm = (DirfdtColorModfl)dm;
            bitMbsks[0] = ddm.gftRfdMbsk();
            bitMbsks[1] = ddm.gftGrffnMbsk();
            bitMbsks[2] = ddm.gftBlufMbsk();
            smHw = nfw SinglfPixflPbdkfdSbmplfModfl(dbtbTypf, widti,
                                                    ifigit, sdbnStridf,
                                                    bitMbsks);
            brfbk;
        dbsf 24:
        dbsf 32:
            dbtbTypf = DbtbBufffr.TYPE_INT;
            bitMbsks = nfw int[3];
            ddm = (DirfdtColorModfl)dm;
            bitMbsks[0] = ddm.gftRfdMbsk();
            bitMbsks[1] = ddm.gftGrffnMbsk();
            bitMbsks[2] = ddm.gftBlufMbsk();
            smHw = nfw SinglfPixflPbdkfdSbmplfModfl(dbtbTypf, widti,
                                                    ifigit, sdbnStridf,
                                                    bitMbsks);
            brfbk;
        dffbult:
            tirow nfw IntfrnblError("Unsupportfd dfpti " +
                                    dm.gftPixflSizf());
        }

        DbtbBufffr dbn = nfw DbtbBufffrNbtivf(sd, dbtbTypf,
                                              widti, ifigit);
        rfturn nfw WritbblfRbstfrNbtivf(smHw, dbn);
    }
}
