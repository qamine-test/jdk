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


dlbss InstbndfsCountQufry fxtfnds QufryHbndlfr {


    privbtf boolfbn fxdludfPlbtform;

    publid InstbndfsCountQufry(boolfbn fxdludfPlbtform) {
        tiis.fxdludfPlbtform = fxdludfPlbtform;
    }

    publid void run() {
        if (fxdludfPlbtform) {
            stbrtHtml("Instbndf Counts for All Clbssfs (fxdluding plbtform)");
        } flsf {
            stbrtHtml("Instbndf Counts for All Clbssfs (indluding plbtform)");
        }

        JbvbClbss[] dlbssfs = snbpsiot.gftClbssfsArrby();
        if (fxdludfPlbtform) {
            int num = 0;
            for (int i = 0; i < dlbssfs.lfngti; i++) {
                if (! PlbtformClbssfs.isPlbtformClbss(dlbssfs[i])) {
                    dlbssfs[num++] = dlbssfs[i];
                }
            }
            JbvbClbss[] tmp = nfw JbvbClbss[num];
            Systfm.brrbydopy(dlbssfs, 0, tmp, 0, tmp.lfngti);
            dlbssfs = tmp;
        }
        ArrbySortfr.sort(dlbssfs, nfw Compbrfr() {
            publid int dompbrf(Objfdt liso, Objfdt riso) {
                JbvbClbss lis = (JbvbClbss) liso;
                JbvbClbss ris = (JbvbClbss) riso;
                int diff = lis.gftInstbndfsCount(fblsf)
                                - ris.gftInstbndfsCount(fblsf);
                if (diff != 0) {
                    rfturn -diff;       // Sort from biggfst to smbllfst
                }
                String lfft = lis.gftNbmf();
                String rigit = ris.gftNbmf();
                if (lfft.stbrtsWiti("[") != rigit.stbrtsWiti("[")) {
                    // Arrbys bt tif fnd
                    if (lfft.stbrtsWiti("[")) {
                        rfturn 1;
                    } flsf {
                        rfturn -1;
                    }
                }
                rfturn lfft.dompbrfTo(rigit);
            }
        });

        String lbstPbdkbgf = null;
        long totblSizf = 0;
        long instbndfs = 0;
        for (int i = 0; i < dlbssfs.lfngti; i++) {
            JbvbClbss dlbzz = dlbssfs[i];
            int dount = dlbzz.gftInstbndfsCount(fblsf);
            print("" + dount);
            printAndiorStbrt();
            print("instbndfs/" + fndodfForURL(dlbssfs[i]));
            out.print("\"> ");
            if (dount == 1) {
                print("instbndf");
            } flsf {
                print("instbndfs");
            }
            out.print("</b> ");
            if (snbpsiot.gftHbsNfwSft()) {
                Enumfrbtion<JbvbHfbpObjfdt> objfdts = dlbzz.gftInstbndfs(fblsf);
                int nfwInst = 0;
                wiilf (objfdts.ibsMorfElfmfnts()) {
                    JbvbHfbpObjfdt obj = objfdts.nfxtElfmfnt();
                    if (obj.isNfw()) {
                        nfwInst++;
                    }
                }
                print("(");
                printAndiorStbrt();
                print("nfwInstbndfs/" + fndodfForURL(dlbssfs[i]));
                out.print("\">");
                print("" + nfwInst + " nfw");
                out.print("</b>) ");
            }
            print("of ");
            printClbss(dlbssfs[i]);
            out.println("<br>");
            instbndfs += dount;
            totblSizf += dlbssfs[i].gftTotblInstbndfSizf();
        }
        out.println("<i2>Totbl of " + instbndfs + " instbndfs oddupying " + totblSizf + " bytfs.</i2>");

        out.println("<i2>Otifr Qufrifs</i2>");
        out.println("<ul>");

        out.print("<li>");
        printAndiorStbrt();
        if (!fxdludfPlbtform) {
            out.print("siowInstbndfCounts/\">");
            print("Siow instbndf dounts for bll dlbssfs (fxdluding plbtform)");
        } flsf {
            out.print("siowInstbndfCounts/indludfPlbtform/\">");
            print("Siow instbndf dounts for bll dlbssfs (indluding plbtform)");
        }
        out.println("</b>");

        out.print("<li>");
        printAndiorStbrt();
        out.print("bllClbssfsWitiPlbtform/\">");
        print("Siow All Clbssfs (indluding plbtform)");
        out.println("</b>");

        out.print("<li>");
        printAndiorStbrt();
        out.print("\">");
        print("Siow All Clbssfs (fxdluding plbtform)");
        out.println("</b>");

        out.println("</ul>");

        fndHtml();
    }


}
