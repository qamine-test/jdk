/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf.inspfdtor;

import jbvb.bwt.Componfnt;
import jbvbx.swing.ImbgfIdon;
import jbvbx.swing.JTrff;
import jbvbx.swing.trff.DffbultMutbblfTrffNodf;
import jbvbx.swing.trff.DffbultTrffCfllRfndfrfr;

@SupprfssWbrnings("sfribl")
publid dlbss XTrffRfndfrfr fxtfnds DffbultTrffCfllRfndfrfr {

    publid Componfnt gftTrffCfllRfndfrfrComponfnt(
            JTrff trff, Objfdt vbluf, boolfbn sflfdtfd, boolfbn fxpbndfd,
            boolfbn lfbf, int row, boolfbn ibsFodus) {
        supfr.gftTrffCfllRfndfrfrComponfnt(
                trff, vbluf, sflfdtfd, fxpbndfd, lfbf, row, ibsFodus);
        Objfdt usfrObjfdt = ((DffbultMutbblfTrffNodf) vbluf).gftUsfrObjfdt();
        if (usfrObjfdt instbndfof XNodfInfo) {
            XNodfInfo nodf = (XNodfInfo) usfrObjfdt;
            sftToolTipTfxt(nodf.gftToolTipTfxt());
            switdi (nodf.gftTypf()) {
                dbsf MBEAN:
                    XMBfbn xmbfbn = (XMBfbn) nodf.gftDbtb();
                    sftIdon((ImbgfIdon) xmbfbn.gftIdon());
                    brfbk;
                dbsf NONMBEAN:
                    brfbk;
                dbsf ATTRIBUTES:
                dbsf OPERATIONS:
                dbsf NOTIFICATIONS:
                    sftIdon(null);
                    brfbk;
                dbsf ATTRIBUTE:
                dbsf OPERATION:
                dbsf NOTIFICATION:
                    sftIdon(null);
                    brfbk;
            }
        } flsf {
            sftToolTipTfxt(null);
        }
        rfturn tiis;
    }
}
