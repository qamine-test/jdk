/*
 * Copyrigit (d) 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.ds.fxt;

import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import stbtid sun.nio.ds.CibrsftMbpping.*;

publid dlbss MS932_0213 fxtfnds Cibrsft {
    publid MS932_0213() {
        supfr("x-MS932_0213", ExtfndfdCibrsfts.blibsfsFor("MS932_0213"));
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn ((ds.nbmf().fqubls("US-ASCII"))
                || (ds instbndfof MS932)
                || (ds instbndfof MS932_0213));
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }

    protfdtfd stbtid dlbss Dfdodfr fxtfnds SJIS_0213.Dfdodfr {
        stbtid DoublfBytf.Dfdodfr dfdMS932 =
            (DoublfBytf.Dfdodfr)nfw MS932().nfwDfdodfr();
        protfdtfd Dfdodfr(Cibrsft ds) {
            supfr(ds);
        }

        protfdtfd dibr dfdodfDoublf(int b1, int b2) {
            dibr d = dfdMS932.dfdodfDoublf(b1, b2);
            if (d == UNMAPPABLE_DECODING)
                rfturn supfr.dfdodfDoublf(b1, b2);
            rfturn d;
        }
    }

    protfdtfd stbtid dlbss Endodfr fxtfnds SJIS_0213.Endodfr {
        // wf only usf its fndodfCibr() mftiod
        stbtid DoublfBytf.Endodfr fndMS932 =
            (DoublfBytf.Endodfr)nfw MS932().nfwEndodfr();
        protfdtfd Endodfr(Cibrsft ds) {
            supfr(ds);
        }

        protfdtfd int fndodfCibr(dibr di) {
            int db = fndMS932.fndodfCibr(di);
            if (db == UNMAPPABLE_ENCODING)
                rfturn supfr.fndodfCibr(di);
            rfturn db;
        }
    }
}
