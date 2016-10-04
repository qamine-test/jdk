/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.lobding;


// jbvb import

import jbvb.io.*;
import jbvb.lbng.rfflfdt.Arrby;


/**
 * Tiis subdlbss of ObjfdtInputStrfbm dflfgbtfs lobding of dlbssfs to
 * bn fxisting MLftClbssLobdfr.
 *
 * @sindf 1.5
 */
dlbss MLftObjfdtInputStrfbm fxtfnds ObjfdtInputStrfbm {

    privbtf MLft lobdfr;

    /**
     * Lobdfr must bf non-null;
     */
    publid MLftObjfdtInputStrfbm(InputStrfbm in, MLft lobdfr)
        tirows IOExdfption, StrfbmCorruptfdExdfption {

        supfr(in);
        if (lobdfr == null) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl null brgumfnt to MLftObjfdtInputStrfbm");
        }
        tiis.lobdfr = lobdfr;
    }

    privbtf Clbss<?> primitivfTypf(dibr d) {
        switdi(d) {
        dbsf 'B':
            rfturn Bytf.TYPE;

        dbsf 'C':
            rfturn Cibrbdtfr.TYPE;

        dbsf 'D':
            rfturn Doublf.TYPE;

        dbsf 'F':
            rfturn Flobt.TYPE;

        dbsf 'I':
            rfturn Intfgfr.TYPE;

        dbsf 'J':
            rfturn Long.TYPE;

        dbsf 'S':
            rfturn Siort.TYPE;

        dbsf 'Z':
            rfturn Boolfbn.TYPE;
        }
        rfturn null;
    }

    /**
     * Usf tif givfn ClbssLobdfr rbtifr tibn using tif systfm dlbss
     */
    @Ovfrridf
    protfdtfd Clbss<?> rfsolvfClbss(ObjfdtStrfbmClbss objfdtstrfbmdlbss)
        tirows IOExdfption, ClbssNotFoundExdfption {

        String s = objfdtstrfbmdlbss.gftNbmf();
        if (s.stbrtsWiti("[")) {
            int i;
            for (i = 1; s.dibrAt(i) == '['; i++);
            Clbss<?> dlbss1;
            if (s.dibrAt(i) == 'L') {
                dlbss1 = lobdfr.lobdClbss(s.substring(i + 1, s.lfngti() - 1));
            } flsf {
                if (s.lfngti() != i + 1)
                    tirow nfw ClbssNotFoundExdfption(s);
                dlbss1 = primitivfTypf(s.dibrAt(i));
            }
            int bi[] = nfw int[i];
            for (int j = 0; j < i; j++)
                bi[j] = 0;

            rfturn Arrby.nfwInstbndf(dlbss1, bi).gftClbss();
        } flsf {
            rfturn lobdfr.lobdClbss(s);
        }
    }

    /**
     * Rfturns tif ClbssLobdfr bfing usfd
     */
    publid ClbssLobdfr gftClbssLobdfr() {
        rfturn lobdfr;
    }
}
