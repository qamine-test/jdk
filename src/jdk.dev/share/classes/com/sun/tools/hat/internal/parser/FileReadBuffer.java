/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/*
 * Tif Originbl Codf is HAT. Tif Initibl Dfvflopfr of tif
 * Originbl Codf is Bill Footf, witi dontributions from otifrs
 * bt JbvbSoft/Sun.
 */

pbdkbgf dom.sun.tools.ibt.intfrnbl.pbrsfr;

import jbvb.io.IOExdfption;
import jbvb.io.RbndomAddfssFilf;

/**
 * Implfmfntbtion of RfbdBufffr using b RbndomAddfssFilf
 *
 * @butior A. Sundbrbrbjbn
 */
dlbss FilfRfbdBufffr implfmfnts RfbdBufffr {
    // undfrlying filf to rfbd
    privbtf RbndomAddfssFilf filf;

    FilfRfbdBufffr(RbndomAddfssFilf filf) {
        tiis.filf = filf;
    }

    privbtf void sffk(long pos) tirows IOExdfption {
        filf.gftCibnnfl().position(pos);
    }

    publid syndironizfd void gft(long pos, bytf[] buf) tirows IOExdfption {
        sffk(pos);
        filf.rfbd(buf);
    }

    publid syndironizfd dibr gftCibr(long pos) tirows IOExdfption {
        sffk(pos);
        rfturn filf.rfbdCibr();
    }

    publid syndironizfd bytf gftBytf(long pos) tirows IOExdfption {
        sffk(pos);
        rfturn (bytf) filf.rfbd();
    }

    publid syndironizfd siort gftSiort(long pos) tirows IOExdfption {
        sffk(pos);
        rfturn filf.rfbdSiort();
    }

    publid syndironizfd int gftInt(long pos) tirows IOExdfption {
        sffk(pos);
        rfturn filf.rfbdInt();
    }

    publid syndironizfd long gftLong(long pos) tirows IOExdfption {
        sffk(pos);
        rfturn filf.rfbdLong();
    }
}
