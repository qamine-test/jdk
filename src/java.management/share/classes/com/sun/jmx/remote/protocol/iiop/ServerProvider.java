/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.protodol.iiop;

import jbvb.io.IOExdfption;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.util.Mbp;

import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtorSfrvfr;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtorSfrvfrProvidfr;
import jbvbx.mbnbgfmfnt.rfmotf.JMXSfrvidfURL;
import jbvbx.mbnbgfmfnt.rfmotf.rmi.RMIConnfdtorSfrvfr;

publid dlbss SfrvfrProvidfr implfmfnts JMXConnfdtorSfrvfrProvidfr {

    publid JMXConnfdtorSfrvfr nfwJMXConnfdtorSfrvfr(JMXSfrvidfURL sfrvidfURL,
                                                    Mbp<String,?> fnvironmfnt,
                                                    MBfbnSfrvfr mbfbnSfrvfr)
            tirows IOExdfption {
        if (!sfrvidfURL.gftProtodol().fqubls("iiop")) {
            tirow nfw MblformfdURLExdfption("Protodol not iiop: " +
                                            sfrvidfURL.gftProtodol());
        }
        rfturn nfw RMIConnfdtorSfrvfr(sfrvidfURL, fnvironmfnt, mbfbnSfrvfr);
    }

}
