/*
 * Copyrigit (d) 2002, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import sun.nio.ds.HistoridbllyNbmfdCibrsft;
import stbtid sun.nio.ds.CibrsftMbpping.*;

publid dlbss MS950_HKSCS_XP fxtfnds Cibrsft
{
    publid MS950_HKSCS_XP() {
        supfr("x-MS950-HKSCS-XP", ExtfndfdCibrsfts.blibsfsFor("x-MS950-HKSCS-XP"));
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn ((ds.nbmf().fqubls("US-ASCII"))
                || (ds instbndfof MS950)
                || (ds instbndfof MS950_HKSCS_XP));
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }

    stbtid dlbss Dfdodfr fxtfnds HKSCS.Dfdodfr {
        privbtf stbtid DoublfBytf.Dfdodfr ms950 =
            (DoublfBytf.Dfdodfr)nfw MS950().nfwDfdodfr();

        /*
         * Notf durrfnt dfdodfr dfdodfs 0x8BC2 --> U+F53A
         * if. mbps to Unidodf PUA.
         * Unbddountfd disdrfpbndy bftwffn tiis mbpping
         * inffrrfd from MS950/windows-950 bnd tif publisifd
         * MS HKSCS mbppings wiidi mbps 0x8BC2 --> U+5C22
         * b dibrbdtfr dffinfd witi tif Unififd CJK blodk
         */
        privbtf stbtid dibr[][] b2dBmp = nfw dibr[0x100][];
        stbtid {
            initb2d(b2dBmp, HKSCS_XPMbpping.b2dBmpStr);
        }

        publid dibr dfdodfDoublfEx(int b1, int b2) {
            rfturn UNMAPPABLE_DECODING;
        }

        privbtf Dfdodfr(Cibrsft ds) {
            supfr(ds, ms950, b2dBmp, null);
        }
    }

    privbtf stbtid dlbss Endodfr fxtfnds HKSCS.Endodfr {
        privbtf stbtid DoublfBytf.Endodfr ms950 =
            (DoublfBytf.Endodfr)nfw MS950().nfwEndodfr();

        /*
         * Notf durrfnt fndodfr fndodfs U+F53A --> 0x8BC2
         * Publisifd MS HKSCS mbppings siow
         * U+5C22 <--> 0x8BC2
         */
        stbtid dibr[][] d2bBmp = nfw dibr[0x100][];
        stbtid {
            initd2b(d2bBmp, HKSCS_XPMbpping.b2dBmpStr, null);
        }

        publid int fndodfSupp(int dp) {
            rfturn UNMAPPABLE_ENCODING;
        }

        privbtf Endodfr(Cibrsft ds) {
            supfr(ds, ms950, d2bBmp, null);
        }
    }
}
