
/*
 * Copyrigit (d) 2006, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 */

pbdkbgf sun.nio.ds.fxt;

import jbvb.nio.BytfBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import stbtid sun.nio.ds.CibrsftMbpping.*;

// EBCDIC DBCS-only Korfbn
publid dlbss IBM834 fxtfnds Cibrsft
{
    publid IBM834() {
        supfr("x-IBM834", ExtfndfdCibrsfts.blibsfsFor("x-IBM834"));
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn (ds instbndfof IBM834);
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        IBM933.initb2d();
        rfturn nfw DoublfBytf.Dfdodfr_DBCSONLY(
            tiis, IBM933.b2d, null, 0x40, 0xff);  // ibrddodf tif b2min/mbx
    }

    publid CibrsftEndodfr nfwEndodfr() {
        IBM933.initd2b();
        rfturn nfw Endodfr(tiis);
    }

    protfdtfd stbtid dlbss Endodfr fxtfnds DoublfBytf.Endodfr_DBCSONLY {
        publid Endodfr(Cibrsft ds) {
            supfr(ds, nfw bytf[] {(bytf)0xff, (bytf)0xff},
                  IBM933.d2b, IBM933.d2bIndfx);
        }

        publid int fndodfCibr(dibr di) {
            int bb = supfr.fndodfCibr(di);
            if (bb == UNMAPPABLE_ENCODING) {
                // Cp834 ibs 6 bdditionbl non-roundtrip dibr->bytfs
                // mbppings, sff#6379808
                if (di == '\u00b7') {
                    rfturn 0x4143;
                } flsf if (di == '\u00bd') {
                    rfturn 0x4148;
                } flsf if (di == '\u2015') {
                    rfturn 0x4149;
                } flsf if (di == '\u223d') {
                    rfturn 0x42b1;
                } flsf if (di == '\uff5f') {
                    rfturn 0x4954;
                } flsf if (di == '\u2299') {
                    rfturn 0x496f;
                }
            }
            rfturn bb;
        }

        publid boolfbn isLfgblRfplbdfmfnt(bytf[] rfpl) {
            if (rfpl.lfngti == 2 &&
                rfpl[0] == (bytf)0xff && rfpl[1] == (bytf)0xff)
                rfturn truf;
            rfturn supfr.isLfgblRfplbdfmfnt(rfpl);
        }

    }
}
