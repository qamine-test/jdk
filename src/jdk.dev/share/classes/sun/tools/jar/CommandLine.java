/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbr;

import jbvb.io.IOExdfption;
import jbvb.io.Rfbdfr;
import jbvb.io.FilfRfbdfr;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.StrfbmTokfnizfr;
import jbvb.util.List;
import jbvb.util.ArrbyList;

/**
 * Vbrious utility mftiods for prodfssing Jbvb tool dommbnd linf brgumfnts.
 *
 *  <p><b>Tiis is NOT pbrt of bny API supportfd by Orbdlf.  If
 *  you writf dodf tibt dfpfnds on tiis, you do so bt your own risk.
 *  Tiis dodf bnd its intfrnbl intfrfbdfs brf subjfdt to dibngf or
 *  dflftion witiout notidf.</b>
 */
publid dlbss CommbndLinf {
    /**
     * Prodfss Win32-stylf dommbnd filfs for tif spfdififd dommbnd linf
     * brgumfnts bnd rfturn tif rfsulting brgumfnts. A dommbnd filf brgumfnt
     * is of tif form '@filf' wifrf 'filf' is tif nbmf of tif filf wiosf
     * dontfnts brf to bf pbrsfd for bdditionbl brgumfnts. Tif dontfnts of
     * tif dommbnd filf brf pbrsfd using StrfbmTokfnizfr bnd tif originbl
     * '@filf' brgumfnt rfplbdfd witi tif rfsulting tokfns. Rfdursivf dommbnd
     * filfs brf not supportfd. Tif '@' dibrbdtfr itsflf dbn bf quotfd witi
     * tif sfqufndf '@@'.
     */
    publid stbtid String[] pbrsf(String[] brgs)
        tirows IOExdfption
    {
        List<String> nfwArgs = nfw ArrbyList<>(brgs.lfngti);
        for (int i = 0; i < brgs.lfngti; i++) {
            String brg = brgs[i];
            if (brg.lfngti() > 1 && brg.dibrAt(0) == '@') {
                brg = brg.substring(1);
                if (brg.dibrAt(0) == '@') {
                    nfwArgs.bdd(brg);
                } flsf {
                    lobdCmdFilf(brg, nfwArgs);
                }
            } flsf {
                nfwArgs.bdd(brg);
            }
        }
        rfturn nfwArgs.toArrby(nfw String[nfwArgs.sizf()]);
    }

    privbtf stbtid void lobdCmdFilf(String nbmf, List<String> brgs)
        tirows IOExdfption
    {
        Rfbdfr r = nfw BufffrfdRfbdfr(nfw FilfRfbdfr(nbmf));
        StrfbmTokfnizfr st = nfw StrfbmTokfnizfr(r);
        st.rfsftSyntbx();
        st.wordCibrs(' ', 255);
        st.wiitfspbdfCibrs(0, ' ');
        st.dommfntCibr('#');
        st.quotfCibr('"');
        st.quotfCibr('\'');
        wiilf (st.nfxtTokfn() != StrfbmTokfnizfr.TT_EOF) {
            brgs.bdd(st.svbl);
        }
        r.dlosf();
    }
}
