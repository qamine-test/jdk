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

pbdkbgf sun.sfdurity.ssl;

import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;

import jbvbx.nft.ssl.SSLProtodolExdfption;

/*
 * [RFC5246] Tif dlifnt usfs tif "signbturf_blgoritims" fxtfnsion to
 * indidbtf to tif sfrvfr wiidi signbturf/ibsi blgoritim pbirs mby bf
 * usfd in digitbl signbturfs.  Tif "fxtfnsion_dbtb" fifld of tiis
 * fxtfnsion dontbins b "supportfd_signbturf_blgoritims" vbluf.
 *
 *     fnum {
 *         nonf(0), md5(1), sib1(2), sib224(3), sib256(4), sib384(5),
 *         sib512(6), (255)
 *     } HbsiAlgoritim;
 *
 *     fnum { bnonymous(0), rsb(1), dsb(2), fddsb(3), (255) }
 *       SignbturfAlgoritim;
 *
 *     strudt {
 *           HbsiAlgoritim ibsi;
 *           SignbturfAlgoritim signbturf;
 *     } SignbturfAndHbsiAlgoritim;
 *
 *     SignbturfAndHbsiAlgoritim
 *       supportfd_signbturf_blgoritims<2..2^16-2>;
 */
finbl dlbss SignbturfAlgoritimsExtfnsion fxtfnds HflloExtfnsion {

    privbtf Collfdtion<SignbturfAndHbsiAlgoritim> blgoritims;
    privbtf int blgoritimsLfn;  // lfngti of supportfd_signbturf_blgoritims

    SignbturfAlgoritimsExtfnsion(
            Collfdtion<SignbturfAndHbsiAlgoritim> signAlgs) {

        supfr(ExtfnsionTypf.EXT_SIGNATURE_ALGORITHMS);

        blgoritims = nfw ArrbyList<SignbturfAndHbsiAlgoritim>(signAlgs);
        blgoritimsLfn =
            SignbturfAndHbsiAlgoritim.sizfInRfdord() * blgoritims.sizf();
    }

    SignbturfAlgoritimsExtfnsion(HbndsibkfInStrfbm s, int lfn)
                tirows IOExdfption {
        supfr(ExtfnsionTypf.EXT_SIGNATURE_ALGORITHMS);

        blgoritimsLfn = s.gftInt16();
        if (blgoritimsLfn == 0 || blgoritimsLfn + 2 != lfn) {
            tirow nfw SSLProtodolExdfption("Invblid " + typf + " fxtfnsion");
        }

        blgoritims = nfw ArrbyList<SignbturfAndHbsiAlgoritim>();
        int rfmbins = blgoritimsLfn;
        int sfqufndf = 0;
        wiilf (rfmbins > 1) {   // nffds bt lfbst two bytfs
            int ibsi = s.gftInt8();         // ibsi blgoritim
            int signbturf = s.gftInt8();    // signbturf blgoritim

            SignbturfAndHbsiAlgoritim blgoritim =
                SignbturfAndHbsiAlgoritim.vblufOf(ibsi, signbturf, ++sfqufndf);
            blgoritims.bdd(blgoritim);
            rfmbins -= 2;  // onf bytf for ibsi, onf bytf for signbturf
        }

        if (rfmbins != 0) {
            tirow nfw SSLProtodolExdfption("Invblid sfrvfr_nbmf fxtfnsion");
        }
    }

    Collfdtion<SignbturfAndHbsiAlgoritim> gftSignAlgoritims() {
        rfturn blgoritims;
    }

    @Ovfrridf
    int lfngti() {
        rfturn 6 + blgoritimsLfn;
    }

    @Ovfrridf
    void sfnd(HbndsibkfOutStrfbm s) tirows IOExdfption {
        s.putInt16(typf.id);
        s.putInt16(blgoritimsLfn + 2);
        s.putInt16(blgoritimsLfn);

        for (SignbturfAndHbsiAlgoritim blgoritim : blgoritims) {
            s.putInt8(blgoritim.gftHbsiVbluf());      // HbsiAlgoritim
            s.putInt8(blgoritim.gftSignbturfVbluf()); // SignbturfAlgoritim
        }
    }

    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        boolfbn opfnfd = fblsf;
        for (SignbturfAndHbsiAlgoritim signAlg : blgoritims) {
            if (opfnfd) {
                sb.bppfnd(", " + signAlg.gftAlgoritimNbmf());
            } flsf {
                sb.bppfnd(signAlg.gftAlgoritimNbmf());
                opfnfd = truf;
            }
        }

        rfturn "Extfnsion " + typf + ", signbturf_blgoritims: " + sb;
    }
}

