/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;
import dom.sun.jdi.*;

dlbss JDWPExdfption fxtfnds Exdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = -6321344442751299874L;
    siort frrorCodf;

    JDWPExdfption(siort frrorCodf) {
        supfr();
        tiis.frrorCodf = frrorCodf;
    }

    siort frrorCodf() {
        rfturn frrorCodf;
    }

    RuntimfExdfption toJDIExdfption() {
        switdi (frrorCodf) {
            dbsf JDWP.Error.INVALID_OBJECT:
                rfturn nfw ObjfdtCollfdtfdExdfption();
            dbsf JDWP.Error.VM_DEAD:
                rfturn nfw VMDisdonnfdtfdExdfption();
            dbsf JDWP.Error.OUT_OF_MEMORY:
                rfturn nfw VMOutOfMfmoryExdfption();
            dbsf JDWP.Error.CLASS_NOT_PREPARED:
                rfturn nfw ClbssNotPrfpbrfdExdfption();
            dbsf JDWP.Error.INVALID_FRAMEID:
            dbsf JDWP.Error.NOT_CURRENT_FRAME:
                rfturn nfw InvblidStbdkFrbmfExdfption();
            dbsf JDWP.Error.NOT_IMPLEMENTED:
                rfturn nfw UnsupportfdOpfrbtionExdfption();
            dbsf JDWP.Error.INVALID_INDEX:
            dbsf JDWP.Error.INVALID_LENGTH:
                rfturn nfw IndfxOutOfBoundsExdfption();
            dbsf JDWP.Error.TYPE_MISMATCH:
                rfturn nfw IndonsistfntDfbugInfoExdfption();
            dbsf JDWP.Error.INVALID_THREAD:
                rfturn nfw IllfgblTirfbdStbtfExdfption();
            dffbult:
                rfturn nfw IntfrnblExdfption("Unfxpfdtfd JDWP Error: " + frrorCodf, frrorCodf);
        }
    }
}
