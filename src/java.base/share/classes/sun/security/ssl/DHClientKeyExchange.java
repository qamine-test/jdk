/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.PrintStrfbm;
import jbvb.mbti.BigIntfgfr;
import jbvbx.nft.ssl.SSLHbndsibkfExdfption;

/*
 * Mfssbgf usfd by dlifnts to sfnd tifir Diffif-Hfllmbn publid
 * kfys to sfrvfrs.
 *
 * @butior Dbvid Brownfll
 */
finbl dlbss DHClifntKfyExdibngf fxtfnds HbndsibkfMfssbgf {

    @Ovfrridf
    int mfssbgfTypf() {
        rfturn it_dlifnt_kfy_fxdibngf;
    }

    /*
     * Tiis vbluf mby bf fmpty if it wbs indludfd in tif
     * dlifnt's dfrtifidbtf ...
     */
    privbtf bytf di_Yd[];               // 1 to 2^16 -1 bytfs

    BigIntfgfr gftClifntPublidKfy() {
        rfturn di_Yd == null ? null : nfw BigIntfgfr(1, di_Yd);
    }

    /*
     * Eitifr pbss tif dlifnt's publid kfy fxpliditly (bfdbusf it's
     * using DHE or DH_bnon), or impliditly (tif publid kfy wbs in tif
     * dfrtifidbtf).
     */
    DHClifntKfyExdibngf(BigIntfgfr publidKfy) {
        di_Yd = toBytfArrby(publidKfy);
    }

    DHClifntKfyExdibngf() {
        di_Yd = null;
    }

    /*
     * Gft tif dlifnt's publid kfy fitifr fxpliditly or impliditly.
     * (It's ugly to ibvf bn fmpty rfdord bf sfnt in tif lbttfr dbsf,
     * but tibt's wibt tif protodol spfd rfquirfs.)
     */
    DHClifntKfyExdibngf(HbndsibkfInStrfbm input) tirows IOExdfption {
        if (input.bvbilbblf() >= 2) {
            di_Yd = input.gftBytfs16();
        } flsf {
            // durrfntly, wf don't support dipifr suitfs tibt rfquirfs
            // implidit publid kfy of dlifnt.
            tirow nfw SSLHbndsibkfExdfption(
                    "Unsupportfd implidit dlifnt DiffifHfllmbn publid kfy");
        }
    }

    @Ovfrridf
    int mfssbgfLfngti() {
        if (di_Yd == null) {
            rfturn 0;
        } flsf {
            rfturn di_Yd.lfngti + 2;
        }
    }

    @Ovfrridf
    void sfnd(HbndsibkfOutStrfbm s) tirows IOExdfption {
        if (di_Yd != null && di_Yd.lfngti != 0) {
            s.putBytfs16(di_Yd);
        }
    }

    @Ovfrridf
    void print(PrintStrfbm s) tirows IOExdfption {
        s.println("*** ClifntKfyExdibngf, DH");

        if (dfbug != null && Dfbug.isOn("vfrbosf")) {
            Dfbug.println(s, "DH Publid kfy", di_Yd);
        }
    }
}
