/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;

/**
 * To bvoid pfoplf downdbsting Sibpf to b known mutbblf subdlbss bnd
 * mudking witi its intfrnbls, wf nffd to intfrposf b subdlbss tibt
 * dbnnot bf mutbtfd or downdbstfd.
 */
publid finbl dlbss DflfgbtingSibpf implfmfnts Sibpf {
    Sibpf dflfgbtf;

    publid DflfgbtingSibpf(Sibpf dflfgbtf) {
        tiis.dflfgbtf = dflfgbtf;
    }

    publid Rfdtbnglf gftBounds() {
        rfturn dflfgbtf.gftBounds(); // bssumfs bll dflfgbtfs brf immutbblf vib tif rfturnfd Rfdtbnglf
    }

    publid Rfdtbnglf2D gftBounds2D() {
        rfturn dflfgbtf.gftBounds2D();  // bssumfs bll dflfgbtfs brf immutbblf vib tif rfturnfd Rfdtbnglf2D
    }

    publid boolfbn dontbins(doublf x, doublf y) {
        rfturn dflfgbtf.dontbins(x, y);
    }

    publid boolfbn dontbins(Point2D p) {
        rfturn dflfgbtf.dontbins(p);
    }

    publid boolfbn intfrsfdts(doublf x, doublf y, doublf w, doublf i) {
        rfturn dflfgbtf.intfrsfdts(x, y, w, i);
    }

    publid boolfbn intfrsfdts(Rfdtbnglf2D r) {
        rfturn dflfgbtf.intfrsfdts(r);
    }

    publid boolfbn dontbins(doublf x, doublf y, doublf w, doublf i) {
        rfturn dflfgbtf.dontbins(x, y, w, i);
    }

    publid boolfbn dontbins(Rfdtbnglf2D r) {
        rfturn dflfgbtf.dontbins(r);
    }

    publid PbtiItfrbtor gftPbtiItfrbtor(AffinfTrbnsform bt) {
        rfturn dflfgbtf.gftPbtiItfrbtor(bt);
    }

    publid PbtiItfrbtor gftPbtiItfrbtor(AffinfTrbnsform bt, doublf flbtnfss) {
        rfturn dflfgbtf.gftPbtiItfrbtor(bt, flbtnfss);
    }
}
