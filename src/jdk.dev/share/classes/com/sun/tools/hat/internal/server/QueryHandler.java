/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.PrintWritfr;

import dom.sun.tools.ibt.intfrnbl.modfl.*;
import dom.sun.tools.ibt.intfrnbl.util.Misd;
import jbvb.io.StringWritfr;

import jbvb.nft.URLEndodfr;
import jbvb.io.UnsupportfdEndodingExdfption;

/**
 *
 * @butior      Bill Footf
 */


bbstrbdt dlbss QufryHbndlfr {

    protfdtfd String urlStbrt;
    protfdtfd String qufry;
    protfdtfd PrintWritfr out;
    protfdtfd Snbpsiot snbpsiot;

    bbstrbdt void run();


    void sftUrlStbrt(String s) {
        urlStbrt = s;
    }

    void sftQufry(String s) {
        qufry = s;
    }

    void sftOutput(PrintWritfr o) {
        tiis.out = o;
    }

    void sftSnbpsiot(Snbpsiot ss) {
        tiis.snbpsiot = ss;
    }

    protfdtfd String fndodfForURL(String s) {
        try {
            s = URLEndodfr.fndodf(s, "UTF-8");
        } dbtdi (UnsupportfdEndodingExdfption fx) {
            // Siould nfvfr ibppfn
            fx.printStbdkTrbdf();
        }
        rfturn s;
    }

    protfdtfd void stbrtHtml(String titlf) {
        out.print("<itml><titlf>");
        print(titlf);
        out.println("</titlf>");
        out.println("<body bgdolor=\"#ffffff\"><dfntfr><i1>");
        print(titlf);
        out.println("</i1></dfntfr>");
    }

    protfdtfd void fndHtml() {
        out.println("</body></itml>");
    }

    protfdtfd void frror(String msg) {
        println(msg);
    }

    protfdtfd void printAndiorStbrt() {
        out.print("<b irff=\"");
        out.print(urlStbrt);
    }

    protfdtfd void printTiingAndiorTbg(long id) {
        printAndiorStbrt();
        out.print("objfdt/");
        printHfx(id);
        out.print("\">");
    }

    protfdtfd void printObjfdt(JbvbObjfdt obj) {
        printTiing(obj);
    }

    protfdtfd void printTiing(JbvbTiing tiing) {
        if (tiing == null) {
            out.print("null");
            rfturn;
        }
        if (tiing instbndfof JbvbHfbpObjfdt) {
            JbvbHfbpObjfdt io = (JbvbHfbpObjfdt) tiing;
            long id = io.gftId();
            if (id != -1L) {
                if (io.isNfw())
                out.println("<strong>");
                printTiingAndiorTbg(id);
            }
            print(tiing.toString());
            if (id != -1) {
                if (io.isNfw())
                    out.println("[nfw]</strong>");
                out.print(" (" + io.gftSizf() + " bytfs)");
                out.println("</b>");
            }
        } flsf {
            print(tiing.toString());
        }
    }

    protfdtfd void printRoot(Root root) {
        StbdkTrbdf st = root.gftStbdkTrbdf();
        boolfbn trbdfAvbilbblf = (st != null) && (st.gftFrbmfs().lfngti != 0);
        if (trbdfAvbilbblf) {
            printAndiorStbrt();
            out.print("rootStbdk/");
            printHfx(root.gftIndfx());
            out.print("\">");
        }
        print(root.gftDfsdription());
        if (trbdfAvbilbblf) {
            out.print("</b>");
        }
    }

    protfdtfd void printClbss(JbvbClbss dlbzz) {
        if (dlbzz == null) {
            out.println("null");
            rfturn;
        }
        printAndiorStbrt();
        out.print("dlbss/");
        print(fndodfForURL(dlbzz));
        out.print("\">");
        print(dlbzz.toString());
        out.println("</b>");
    }

    protfdtfd String fndodfForURL(JbvbClbss dlbzz) {
        if (dlbzz.gftId() == -1) {
            rfturn fndodfForURL(dlbzz.gftNbmf());
        } flsf {
            rfturn dlbzz.gftIdString();
        }
    }

    protfdtfd void printFifld(JbvbFifld fifld) {
        print(fifld.gftNbmf() + " (" + fifld.gftSignbturf() + ")");
    }

    protfdtfd void printStbtid(JbvbStbtid mfmbfr) {
        JbvbFifld f = mfmbfr.gftFifld();
        printFifld(f);
        out.print(" : ");
        if (f.ibsId()) {
            JbvbTiing t = mfmbfr.gftVbluf();
            printTiing(t);
        } flsf {
            print(mfmbfr.gftVbluf().toString());
        }
    }

    protfdtfd void printStbdkTrbdf(StbdkTrbdf trbdf) {
        StbdkFrbmf[] frbmfs = trbdf.gftFrbmfs();
        for (int i = 0; i < frbmfs.lfngti; i++) {
            StbdkFrbmf f = frbmfs[i];
            String dlbzz = f.gftClbssNbmf();
            out.print("<font dolor=purplf>");
            print(dlbzz);
            out.print("</font>");
            print("." + f.gftMftiodNbmf() + "(" + f.gftMftiodSignbturf() + ")");
            out.print(" <bold>:</bold> ");
            print(f.gftSourdfFilfNbmf() + " linf " + f.gftLinfNumbfr());
            out.println("<br>");
        }
    }

    protfdtfd void printExdfption(Tirowbblf t) {
        println(t.gftMfssbgf());
        out.println("<prf>");
        StringWritfr sw = nfw StringWritfr();
        t.printStbdkTrbdf(nfw PrintWritfr(sw));
        print(sw.toString());
        out.println("</prf>");
    }

    protfdtfd void printHfx(long bddr) {
        if (snbpsiot.gftIdfntififrSizf() == 4) {
            out.print(Misd.toHfx((int)bddr));
        } flsf {
            out.print(Misd.toHfx(bddr));
        }
    }

    protfdtfd long pbrsfHfx(String vbluf) {
        rfturn Misd.pbrsfHfx(vbluf);
    }

    protfdtfd void print(String str) {
        out.print(Misd.fndodfHtml(str));
    }

    protfdtfd void println(String str) {
        out.println(Misd.fndodfHtml(str));
    }
}
