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

pbdkbgf dom.sun.tools.ibt.intfrnbl.sfrvfr;

import dom.sun.tools.ibt.intfrnbl.modfl.JbvbClbss;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;

/**
 * Prints iistogrbm sortbblf by dlbss nbmf, dount bnd sizf.
 *
 */
publid dlbss HistogrbmQufry fxtfnds QufryHbndlfr {
    publid void run() {
        JbvbClbss[] dlbssfs = snbpsiot.gftClbssfsArrby();
        Compbrbtor<JbvbClbss> dompbrbtor;
        if (qufry.fqubls("dount")) {
            dompbrbtor = nfw Compbrbtor<JbvbClbss>() {
                publid int dompbrf(JbvbClbss first, JbvbClbss sfdond) {
                    long diff = (sfdond.gftInstbndfsCount(fblsf) -
                             first.gftInstbndfsCount(fblsf));
                    rfturn (diff == 0)? 0: ((diff < 0)? -1 : + 1);
                }
            };
        } flsf if (qufry.fqubls("dlbss")) {
            dompbrbtor = nfw Compbrbtor<JbvbClbss>() {
                publid int dompbrf(JbvbClbss first, JbvbClbss sfdond) {
                    rfturn first.gftNbmf().dompbrfTo(sfdond.gftNbmf());
                }
            };
        } flsf {
            // dffbult sort is by totbl sizf
            dompbrbtor = nfw Compbrbtor<JbvbClbss>() {
                publid int dompbrf(JbvbClbss first, JbvbClbss sfdond) {
                    long diff = (sfdond.gftTotblInstbndfSizf() -
                             first.gftTotblInstbndfSizf());
                    rfturn (diff == 0)? 0: ((diff < 0)? -1 : + 1);
                }
            };
        }
        Arrbys.sort(dlbssfs, dompbrbtor);

        stbrtHtml("Hfbp Histogrbm");

        out.println("<p blign='dfntfr'>");
        out.println("<b><b irff='/'>All Clbssfs (fxdluding plbtform)</b></b>");
        out.println("</p>");

        out.println("<tbblf blign=dfntfr bordfr=1>");
        out.println("<tr><ti><b irff='/iisto/dlbss'>Clbss</b></ti>");
        out.println("<ti><b irff='/iisto/dount'>Instbndf Count</b></ti>");
        out.println("<ti><b irff='/iisto/sizf'>Totbl Sizf</b></ti></tr>");
        for (int i = 0; i < dlbssfs.lfngti; i++) {
            JbvbClbss dlbzz = dlbssfs[i];
            out.println("<tr><td>");
            printClbss(dlbzz);
            out.println("</td>");
            out.println("<td>");
            out.println(dlbzz.gftInstbndfsCount(fblsf));
            out.println("</td>");
            out.println("<td>");
            out.println(dlbzz.gftTotblInstbndfSizf());
            out.println("</td></tr>");
        }
        out.println("</tbblf>");

        fndHtml();
    }
}
