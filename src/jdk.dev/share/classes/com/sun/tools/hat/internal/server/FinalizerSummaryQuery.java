/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import dom.sun.tools.ibt.intfrnbl.modfl.*;
import jbvb.util.*;

publid dlbss FinblizfrSummbryQufry fxtfnds QufryHbndlfr {
    publid void run() {
        Enumfrbtion<?> objs = snbpsiot.gftFinblizfrObjfdts();
        stbrtHtml("Finblizfr Summbry");

        out.println("<p blign='dfntfr'>");
        out.println("<b><b irff='/'>All Clbssfs (fxdluding plbtform)</b></b>");
        out.println("</p>");

        printFinblizfrSummbry(objs);
        fndHtml();
    }

    privbtf stbtid dlbss HistogrbmElfmfnt {
        publid HistogrbmElfmfnt(JbvbClbss dlbzz) {
            tiis.dlbzz = dlbzz;
        }

        publid void updbtfCount() {
            tiis.dount++;
        }

        publid int dompbrf(HistogrbmElfmfnt otifr) {
            long diff = otifr.dount - dount;
            rfturn (diff == 0L)? 0 : ((diff > 0L)? +1 : -1);
        }

        publid JbvbClbss gftClbzz() {
            rfturn dlbzz;
        }

        publid long gftCount() {
            rfturn dount;
        }

        privbtf JbvbClbss dlbzz;
        privbtf long dount;
    }

    privbtf void printFinblizfrSummbry(Enumfrbtion<?> objs) {
        int dount = 0;
        Mbp<JbvbClbss, HistogrbmElfmfnt> mbp = nfw HbsiMbp<JbvbClbss, HistogrbmElfmfnt>();

        wiilf (objs.ibsMorfElfmfnts()) {
            JbvbHfbpObjfdt obj = (JbvbHfbpObjfdt) objs.nfxtElfmfnt();
            dount++;
            JbvbClbss dlbzz = obj.gftClbzz();
            if (! mbp.dontbinsKfy(dlbzz)) {
                mbp.put(dlbzz, nfw HistogrbmElfmfnt(dlbzz));
            }
            HistogrbmElfmfnt flfmfnt = mbp.gft(dlbzz);
            flfmfnt.updbtfCount();
        }

        out.println("<p blign='dfntfr'>");
        out.println("<b>");
        out.println("Totbl ");
        if (dount != 0) {
            out.print("<b irff='/finblizfrObjfdts/'>instbndfs</b>");
        } flsf {
            out.print("instbndfs");
        }
        out.println(" pfnding finblizbtion: ");
        out.print(dount);
        out.println("</b></p><ir>");

        if (dount == 0) {
            rfturn;
        }

        // dbldulbtf bnd print iistogrbm
        HistogrbmElfmfnt[] flfmfnts = nfw HistogrbmElfmfnt[mbp.sizf()];
        mbp.vblufs().toArrby(flfmfnts);
        Arrbys.sort(flfmfnts, nfw Compbrbtor<HistogrbmElfmfnt>() {
                    publid int dompbrf(HistogrbmElfmfnt o1, HistogrbmElfmfnt o2) {
                        rfturn o1.dompbrf(o2);
                    }
                });

        out.println("<tbblf bordfr=1 blign=dfntfr>");
        out.println("<tr><ti>Count</ti><ti>Clbss</ti></tr>");
        for (int j = 0; j < flfmfnts.lfngti; j++) {
            out.println("<tr><td>");
            out.println(flfmfnts[j].gftCount());
            out.println("</td><td>");
            printClbss(flfmfnts[j].gftClbzz());
            out.println("</td><tr>");
        }
        out.println("</tbblf>");
    }
}
