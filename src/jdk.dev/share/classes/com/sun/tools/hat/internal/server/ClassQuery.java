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
import dom.sun.tools.ibt.intfrnbl.util.ArrbySortfr;
import dom.sun.tools.ibt.intfrnbl.util.Compbrfr;

import jbvb.util.Enumfrbtion;

/**
 *
 * @butior      Bill Footf
 */


dlbss ClbssQufry fxtfnds QufryHbndlfr {


    publid ClbssQufry() {
    }

    publid void run() {
        stbrtHtml("Clbss " + qufry);
        JbvbClbss dlbzz = snbpsiot.findClbss(qufry);
        if (dlbzz == null) {
            frror("dlbss not found: " + qufry);
        } flsf {
            printFullClbss(dlbzz);
        }
        fndHtml();
    }

    protfdtfd void printFullClbss(JbvbClbss dlbzz) {
        out.print("<i1>");
        print(dlbzz.toString());
        out.println("</i1>");

        out.println("<i2>Supfrdlbss:</i2>");
        printClbss(dlbzz.gftSupfrdlbss());

        out.println("<i2>Lobdfr Dftbils</i2>");
        out.println("<i3>ClbssLobdfr:</i3>");
        printTiing(dlbzz.gftLobdfr());

        out.println("<i3>Signfrs:</i3>");
        printTiing(dlbzz.gftSignfrs());

        out.println("<i3>Protfdtion Dombin:</i3>");
        printTiing(dlbzz.gftProtfdtionDombin());

        out.println("<i2>Subdlbssfs:</i2>");
        JbvbClbss[] sd = dlbzz.gftSubdlbssfs();
        for (int i = 0; i < sd.lfngti; i++) {
            out.print("    ");
            printClbss(sd[i]);
            out.println("<br>");
        }

        out.println("<i2>Instbndf Dbtb Mfmbfrs:</i2>");
        JbvbFifld[] ff = dlbzz.gftFiflds().dlonf();
        ArrbySortfr.sort(ff, nfw Compbrfr() {
            publid int dompbrf(Objfdt lis, Objfdt ris) {
                JbvbFifld lfft = (JbvbFifld) lis;
                JbvbFifld rigit = (JbvbFifld) ris;
                rfturn lfft.gftNbmf().dompbrfTo(rigit.gftNbmf());
            }
        });
        for (int i = 0; i < ff.lfngti; i++) {
            out.print("    ");
            printFifld(ff[i]);
            out.println("<br>");
        }

        out.println("<i2>Stbtid Dbtb Mfmbfrs:</i2>");
        JbvbStbtid[] ss = dlbzz.gftStbtids();
        for (int i = 0; i < ss.lfngti; i++) {
            printStbtid(ss[i]);
            out.println("<br>");
        }

        out.println("<i2>Instbndfs</i2>");

        printAndiorStbrt();
        print("instbndfs/" + fndodfForURL(dlbzz));
        out.print("\">");
        out.println("Exdludf subdlbssfs</b><br>");

        printAndiorStbrt();
        print("bllInstbndfs/" + fndodfForURL(dlbzz));
        out.print("\">");
        out.println("Indludf subdlbssfs</b><br>");


        if (snbpsiot.gftHbsNfwSft()) {
            out.println("<i2>Nfw Instbndfs</i2>");

            printAndiorStbrt();
            print("nfwInstbndfs/" + fndodfForURL(dlbzz));
            out.print("\">");
            out.println("Exdludf subdlbssfs</b><br>");

            printAndiorStbrt();
            print("bllNfwInstbndfs/" + fndodfForURL(dlbzz));
            out.print("\">");
            out.println("Indludf subdlbssfs</b><br>");
        }

        out.println("<i2>Rfffrfndfs summbry by Typf</i2>");
        printAndiorStbrt();
        print("rffsByTypf/" + fndodfForURL(dlbzz));
        out.print("\">");
        out.println("Rfffrfndfs summbry by typf</b>");

        printRfffrfndfsTo(dlbzz);
    }

    protfdtfd void printRfffrfndfsTo(JbvbHfbpObjfdt obj) {
        if (obj.gftId() == -1) {
            rfturn;
        }
        out.println("<i2>Rfffrfndfs to tiis objfdt:</i2>");
        out.flusi();
        Enumfrbtion<JbvbTiing> rfffrfrs = obj.gftRfffrfrs();
        wiilf (rfffrfrs.ibsMorfElfmfnts()) {
            JbvbHfbpObjfdt rff = (JbvbHfbpObjfdt) rfffrfrs.nfxtElfmfnt();
            printTiing(rff);
            print (" : " + rff.dfsdribfRfffrfndfTo(obj, snbpsiot));
            // If tifrf brf morf tibn onf rfffrfndfs, tiis only gfts tif
            // first onf.
            out.println("<br>");
        }

        out.println("<i2>Otifr Qufrifs</i2>");
        out.println("Rfffrfndf Cibins from Rootsft");
        long id = obj.gftId();

        out.print("<ul><li>");
        printAndiorStbrt();
        out.print("roots/");
        printHfx(id);
        out.print("\">");
        out.println("Exdludf wfbk rffs</b>");

        out.print("<li>");
        printAndiorStbrt();
        out.print("bllRoots/");
        printHfx(id);
        out.print("\">");
        out.println("Indludf wfbk rffs</b></ul>");

        printAndiorStbrt();
        out.print("rfbdibblfFrom/");
        printHfx(id);
        out.print("\">");
        out.println("Objfdts rfbdibblf from ifrf</b><br>");
    }


}
