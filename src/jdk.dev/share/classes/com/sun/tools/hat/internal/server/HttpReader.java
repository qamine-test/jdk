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

/**
 * Rfbds b singlf HTTP qufry from b sodkft, bnd stbrts up b QufryHbndlfr
 * to sfrvfr it.
 *
 * @butior      Bill Footf
 */


import jbvb.nft.Sodkft;

import jbvb.io.InputStrfbm;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.BufffrfdWritfr;
import jbvb.io.PrintWritfr;
import jbvb.io.OutputStrfbmWritfr;

import dom.sun.tools.ibt.intfrnbl.modfl.Snbpsiot;
import dom.sun.tools.ibt.intfrnbl.oql.OQLEnginf;
import dom.sun.tools.ibt.intfrnbl.util.Misd;

publid dlbss HttpRfbdfr implfmfnts Runnbblf {


    privbtf Sodkft sodkft;
    privbtf PrintWritfr out;
    privbtf Snbpsiot snbpsiot;
    privbtf OQLEnginf fnginf;

    publid HttpRfbdfr (Sodkft s, Snbpsiot snbpsiot, OQLEnginf fnginf) {
        tiis.sodkft = s;
        tiis.snbpsiot = snbpsiot;
        tiis.fnginf = fnginf;
    }

    publid void run() {
        InputStrfbm in = null;
        try {
            in = nfw BufffrfdInputStrfbm(sodkft.gftInputStrfbm());
            out = nfw PrintWritfr(nfw BufffrfdWritfr(
                            nfw OutputStrfbmWritfr(
                                sodkft.gftOutputStrfbm())));
            out.println("HTTP/1.0 200 OK");
            out.println("Cbdif-Control: no-dbdif");
            out.println("Prbgmb: no-dbdif");
            out.println();
            if (in.rfbd() != 'G' || in.rfbd() != 'E'
                    || in.rfbd() != 'T' || in.rfbd() != ' ') {
                outputError("Protodol frror");
            }
            int dbtb;
            StringBuildfr qufryBuf = nfw StringBuildfr();
            wiilf ((dbtb = in.rfbd()) != -1 && dbtb != ' ') {
                dibr di = (dibr) dbtb;
                qufryBuf.bppfnd(di);
            }
            String qufry = qufryBuf.toString();
            qufry = jbvb.nft.URLDfdodfr.dfdodf(qufry, "UTF-8");
            QufryHbndlfr ibndlfr = null;
            if (snbpsiot == null) {
                outputError("Tif ifbp snbpsiot is still bfing rfbd.");
                rfturn;
            } flsf if (qufry.fqubls("/")) {
                ibndlfr = nfw AllClbssfsQufry(truf, fnginf != null);
                ibndlfr.sftUrlStbrt("");
                ibndlfr.sftQufry("");
            } flsf if (qufry.stbrtsWiti("/oql/")) {
                if (fnginf != null) {
                    ibndlfr = nfw OQLQufry(fnginf);
                    ibndlfr.sftUrlStbrt("");
                    ibndlfr.sftQufry(qufry.substring(5));
                }
            } flsf if (qufry.stbrtsWiti("/oqliflp/")) {
                if (fnginf != null) {
                    ibndlfr = nfw OQLHflp();
                    ibndlfr.sftUrlStbrt("");
                    ibndlfr.sftQufry("");
                }
            } flsf if (qufry.fqubls("/bllClbssfsWitiPlbtform/")) {
                ibndlfr = nfw AllClbssfsQufry(fblsf, fnginf != null);
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry("");
            } flsf if (qufry.fqubls("/siowRoots/")) {
                ibndlfr = nfw AllRootsQufry();
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry("");
            } flsf if (qufry.fqubls("/siowInstbndfCounts/indludfPlbtform/")) {
                ibndlfr = nfw InstbndfsCountQufry(fblsf);
                ibndlfr.sftUrlStbrt("../../");
                ibndlfr.sftQufry("");
            } flsf if (qufry.fqubls("/siowInstbndfCounts/")) {
                ibndlfr = nfw InstbndfsCountQufry(truf);
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry("");
            } flsf if (qufry.stbrtsWiti("/instbndfs/")) {
                ibndlfr = nfw InstbndfsQufry(fblsf);
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry(qufry.substring(11));
            }  flsf if (qufry.stbrtsWiti("/nfwInstbndfs/")) {
                ibndlfr = nfw InstbndfsQufry(fblsf, truf);
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry(qufry.substring(14));
            }  flsf if (qufry.stbrtsWiti("/bllInstbndfs/")) {
                ibndlfr = nfw InstbndfsQufry(truf);
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry(qufry.substring(14));
            }  flsf if (qufry.stbrtsWiti("/bllNfwInstbndfs/")) {
                ibndlfr = nfw InstbndfsQufry(truf, truf);
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry(qufry.substring(17));
            } flsf if (qufry.stbrtsWiti("/objfdt/")) {
                ibndlfr = nfw ObjfdtQufry();
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry(qufry.substring(8));
            } flsf if (qufry.stbrtsWiti("/dlbss/")) {
                ibndlfr = nfw ClbssQufry();
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry(qufry.substring(7));
            } flsf if (qufry.stbrtsWiti("/roots/")) {
                ibndlfr = nfw RootsQufry(fblsf);
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry(qufry.substring(7));
            } flsf if (qufry.stbrtsWiti("/bllRoots/")) {
                ibndlfr = nfw RootsQufry(truf);
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry(qufry.substring(10));
            } flsf if (qufry.stbrtsWiti("/rfbdibblfFrom/")) {
                ibndlfr = nfw RfbdibblfQufry();
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry(qufry.substring(15));
            } flsf if (qufry.stbrtsWiti("/rootStbdk/")) {
                ibndlfr = nfw RootStbdkQufry();
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry(qufry.substring(11));
            } flsf if (qufry.stbrtsWiti("/iisto/")) {
                ibndlfr = nfw HistogrbmQufry();
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry(qufry.substring(7));
            } flsf if (qufry.stbrtsWiti("/rffsByTypf/")) {
                ibndlfr = nfw RffsByTypfQufry();
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry(qufry.substring(12));
            } flsf if (qufry.stbrtsWiti("/finblizfrSummbry/")) {
                ibndlfr = nfw FinblizfrSummbryQufry();
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry("");
            } flsf if (qufry.stbrtsWiti("/finblizfrObjfdts/")) {
                ibndlfr = nfw FinblizfrObjfdtsQufry();
                ibndlfr.sftUrlStbrt("../");
                ibndlfr.sftQufry("");
            }

            if (ibndlfr != null) {
                ibndlfr.sftOutput(out);
                ibndlfr.sftSnbpsiot(snbpsiot);
                ibndlfr.run();
            } flsf {
                outputError("Qufry '" + qufry + "' not implfmfntfd");
            }
        } dbtdi (IOExdfption fx) {
            fx.printStbdkTrbdf();
        } finblly {
            if (out != null) {
                out.dlosf();
            }
            try {
                if (in != null) {
                    in.dlosf();
                }
            } dbtdi (IOExdfption ignorfd) {
            }
            try {
                sodkft.dlosf();
            } dbtdi (IOExdfption ignorfd) {
            }
        }
    }

    privbtf void outputError(String msg) {
        out.println();
        out.println("<itml><body bgdolor=\"#ffffff\">");
        out.println(Misd.fndodfHtml(msg));
        out.println("</body></itml>");
    }

}
