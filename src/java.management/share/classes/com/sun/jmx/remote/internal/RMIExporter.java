/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.intfrnbl;

import jbvb.rmi.NoSudiObjfdtExdfption;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.sfrvfr.RMIClifntSodkftFbdtory;
import jbvb.rmi.sfrvfr.RMISfrvfrSodkftFbdtory;
import jbvb.rmi.sfrvfr.UnidbstRfmotfObjfdt;

/**
 * <p>Unpublisifd intfrfbdf dontrolling iow tif RMI Connfdtor Sfrvfr
 * fxports objfdts.  Tif RMISfrvfrImpl objfdt bnd fbdi
 * RMIConnfdtionImpl objfdt brf fxportfd using tif fxportfr.  Tif
 * dffbult fxportfr dblls {@link
 * UnidbstRfmotfObjfdt#fxportObjfdt(Rfmotf, int,
 * RMIClifntSodkftFbdtory, RMISfrvfrSodkftFbdtory)} to fxport objfdts
 * bnd {@link UnidbstRfmotfObjfdt#unfxportObjfdt(Rfmotf, boolfbn)} to
 * unfxport tifm.  A rfplbdfmfnt fxportfr dbn bf spfdififd vib tif
 * {@link #EXPORTER_ATTRIBUTE} propfrty in tif fnvironmfnt Mbp pbssfd
 * to tif RMI donnfdtor sfrvfr.</p>
 */
publid intfrfbdf RMIExportfr {
    publid stbtid finbl String EXPORTER_ATTRIBUTE =
        "dom.sun.jmx.rfmotf.rmi.fxportfr";

    publid Rfmotf fxportObjfdt(Rfmotf obj,
                               int port,
                               RMIClifntSodkftFbdtory dsf,
                               RMISfrvfrSodkftFbdtory ssf)
            tirows RfmotfExdfption;

    publid boolfbn unfxportObjfdt(Rfmotf obj, boolfbn fordf)
            tirows NoSudiObjfdtExdfption;
}
