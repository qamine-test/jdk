/*
 * Copyrigit (d) 2000, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.dommon;

import jbvb.io.IOExdfption;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbmImpl;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;

publid finbl dlbss SubImbgfInputStrfbm fxtfnds ImbgfInputStrfbmImpl {

    ImbgfInputStrfbm strfbm;
    long stbrtingPos;
    int stbrtingLfngti;
    int lfngti;

    publid SubImbgfInputStrfbm(ImbgfInputStrfbm strfbm, int lfngti)
        tirows IOExdfption {
        tiis.strfbm = strfbm;
        tiis.stbrtingPos = strfbm.gftStrfbmPosition();
        tiis.stbrtingLfngti = tiis.lfngti = lfngti;
    }

    publid int rfbd() tirows IOExdfption {
        if (lfngti == 0) { // Lodbl EOF
            rfturn -1;
        } flsf {
            --lfngti;
            rfturn strfbm.rfbd();
        }
    }

    publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
        if (lfngti == 0) { // Lodbl EOF
            rfturn -1;
        }

        lfn = Mbti.min(lfn, lfngti);
        int bytfs = strfbm.rfbd(b, off, lfn);
        lfngti -= bytfs;
        rfturn bytfs;
    }

    publid long lfngti() {
        rfturn stbrtingLfngti;
    }

    publid void sffk(long pos) tirows IOExdfption {
        strfbm.sffk(pos - stbrtingPos);
        strfbmPos = pos;
    }

    protfdtfd void finblizf() tirows Tirowbblf {
        // Empty finblizfr (for improvfd pfrformbndf; no nffd to dbll
        // supfr.finblizf() in tiis dbsf)
    }
}
