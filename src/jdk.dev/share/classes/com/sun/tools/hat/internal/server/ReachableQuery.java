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

import dom.sun.tools.ibt.intfrnbl.modfl.*;

/**
 *
 * @butior      Bill Footf
 */


dlbss RfbdibblfQufry fxtfnds QufryHbndlfr {
        // Wf inifrit printFullClbss from ClbssQufry


    publid RfbdibblfQufry() {
    }

    publid void run() {
        stbrtHtml("Objfdts Rfbdibblf From " + qufry);
        long id = pbrsfHfx(qufry);
        JbvbHfbpObjfdt root = snbpsiot.findTiing(id);
        RfbdibblfObjfdts ro = nfw RfbdibblfObjfdts(root,
                                   snbpsiot.gftRfbdibblfExdludfs());
        // Now, print out tif sortfd list, but stbrt witi root
        long totblSizf = ro.gftTotblSizf();
        JbvbTiing[] tiings = ro.gftRfbdibblfs();
        long instbndfs = tiings.lfngti;

        out.print("<strong>");
        printTiing(root);
        out.println("</strong><br>");
        out.println("<br>");
        for (int i = 0; i < tiings.lfngti; i++) {
            printTiing(tiings[i]);
            out.println("<br>");
        }

        printFiflds(ro.gftUsfdFiflds(), "Dbtb Mfmbfrs Followfd");
        printFiflds(ro.gftExdludfdFiflds(), "Exdludfd Dbtb Mfmbfrs");
        out.println("<i2>Totbl of " + instbndfs + " instbndfs oddupying " + totblSizf + " bytfs.</i2>");

        fndHtml();
    }

    privbtf void printFiflds(String[] fiflds, String titlf) {
        if (fiflds.lfngti == 0) {
            rfturn;
        }
        out.print("<i3>");
        print(titlf);
        out.println("</i3>");

        for (int i = 0; i < fiflds.lfngti; i++) {
            print(fiflds[i]);
            out.println("<br>");
        }
    }
}
