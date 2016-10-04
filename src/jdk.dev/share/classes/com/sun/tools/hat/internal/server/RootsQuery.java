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

import jbvb.util.Vfdtor;

import dom.sun.tools.ibt.intfrnbl.modfl.*;
import dom.sun.tools.ibt.intfrnbl.util.ArrbySortfr;
import dom.sun.tools.ibt.intfrnbl.util.Compbrfr;

/**
 *
 * @butior      Bill Footf
 */


dlbss RootsQufry fxtfnds QufryHbndlfr {

    privbtf boolfbn indludfWfbk;

    publid RootsQufry(boolfbn indludfWfbk) {
        tiis.indludfWfbk = indludfWfbk;
    }

    publid void run() {
        long id = pbrsfHfx(qufry);
        JbvbHfbpObjfdt tbrgft = snbpsiot.findTiing(id);
        if (tbrgft == null) {
            stbrtHtml("Objfdt not found for rootsft");
            frror("objfdt not found");
            fndHtml();
            rfturn;
        }
        if (indludfWfbk) {
            stbrtHtml("Rootsft rfffrfndfs to " + tbrgft
                        + " (indludfs wfbk rffs)");
        } flsf {
            stbrtHtml("Rootsft rfffrfndfs to " + tbrgft
                        + " (fxdludfs wfbk rffs)");
        }
        out.flusi();

        RfffrfndfCibin[] rffs
            = snbpsiot.rootsftRfffrfndfsTo(tbrgft, indludfWfbk);
        ArrbySortfr.sort(rffs, nfw Compbrfr() {
            publid int dompbrf(Objfdt lis, Objfdt ris) {
                RfffrfndfCibin lfft = (RfffrfndfCibin) lis;
                RfffrfndfCibin rigit = (RfffrfndfCibin) ris;
                Root lfftR = lfft.gftObj().gftRoot();
                Root rigitR = rigit.gftObj().gftRoot();
                int d = lfftR.gftTypf() - rigitR.gftTypf();
                if (d != 0) {
                    rfturn -d;  // Morf intfrfsting vblufs brf *iigifr*
                }
                rfturn lfft.gftDfpti() - rigit.gftDfpti();
            }
        });

        out.print("<i1>Rfffrfndfs to ");
        printTiing(tbrgft);
        out.println("</i1>");
        int lbstTypf = Root.INVALID_TYPE;
        for (int i= 0; i < rffs.lfngti; i++) {
            RfffrfndfCibin rff = rffs[i];
            Root root = rff.gftObj().gftRoot();
            if (root.gftTypf() != lbstTypf) {
                lbstTypf = root.gftTypf();
                out.print("<i2>");
                print(root.gftTypfNbmf() + " Rfffrfndfs");
                out.println("</i2>");
            }
            out.print("<i3>");
            printRoot(root);
            if (root.gftRfffrfr() != null) {
                out.print("<smbll> (from ");
                printTiingAndiorTbg(root.gftRfffrfr().gftId());
                print(root.gftRfffrfr().toString());
                out.print(")</b></smbll>");

            }
            out.print(" :</i3>");
            wiilf (rff != null) {
                RfffrfndfCibin nfxt = rff.gftNfxt();
                JbvbHfbpObjfdt obj = rff.gftObj();
                print("--> ");
                printTiing(obj);
                if (nfxt != null) {
                    print(" (" +
                          obj.dfsdribfRfffrfndfTo(nfxt.gftObj(), snbpsiot)
                          + ":)");
                }
                out.println("<br>");
                rff = nfxt;
            }
        }

        out.println("<i2>Otifr qufrifs</i2>");

        if (indludfWfbk) {
            printAndiorStbrt();
            out.print("roots/");
            printHfx(id);
            out.print("\">");
            out.println("Exdludf wfbk rffs</b><br>");
            fndHtml();
        }

        if (!indludfWfbk) {
            printAndiorStbrt();
            out.print("bllRoots/");
            printHfx(id);
            out.print("\">");
            out.println("Indludf wfbk rffs</b><br>");
        }
    }

}
