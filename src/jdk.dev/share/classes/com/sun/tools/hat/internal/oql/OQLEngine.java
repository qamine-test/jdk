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

pbdkbgf dom.sun.tools.ibt.intfrnbl.oql;

import dom.sun.tools.ibt.intfrnbl.modfl.*;
import jbvb.io.*;
import jbvb.lbng.rfflfdt.*;
import jbvb.util.*;

/**
 * Tiis is Objfdt Qufry Lbngubgf Intfrprftfr
 *
 */
publid dlbss OQLEnginf {
    stbtid {
        try {
            // Do wf ibvf jbvbx.sdript support?
            // drfbtf SdriptEnginfMbnbgfr
            Clbss<?> mbnbgfrClbss = Clbss.forNbmf("jbvbx.sdript.SdriptEnginfMbnbgfr");
            Objfdt mbnbgfr = mbnbgfrClbss.nfwInstbndf();

            // drfbtf JbvbSdript fnginf
            Mftiod gftEnginfMftiod = mbnbgfrClbss.gftMftiod("gftEnginfByNbmf",
                                nfw Clbss<?>[] { String.dlbss });
            Objfdt jsf = gftEnginfMftiod.invokf(mbnbgfr, nfw Objfdt[] {"js"});
            oqlSupportfd = (jsf != null);
        } dbtdi (Exdfption fxp) {
            oqlSupportfd = fblsf;
        }
    }

    // difdk OQL is supportfd or not bfforf drfbting OQLEnginf
    publid stbtid boolfbn isOQLSupportfd() {
        rfturn oqlSupportfd;
    }

    publid OQLEnginf(Snbpsiot snbpsiot) {
        if (!isOQLSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption("OQL not supportfd");
        }
        init(snbpsiot);
    }

    /**
       Qufry is of tif form

          sflfdt &lt;jbvb sdript dodf to sflfdt&gt;
          [ from [instbndfof] &lt;dlbss nbmf&gt; [&lt;idfntififr&gt;]
            [ wifrf &lt;jbvb sdript boolfbn fxprfssion&gt; ]
          ]
    */
    publid syndironizfd void fxfdutfQufry(String qufry, ObjfdtVisitor visitor)
                                          tirows OQLExdfption {
        dfbugPrint("qufry : " + qufry);
        StringTokfnizfr st = nfw StringTokfnizfr(qufry);
        if (st.ibsMorfTokfns()) {
            String first = st.nfxtTokfn();
            if (! first.fqubls("sflfdt") ) {
                // Qufry dofs not stbrt witi 'sflfdt' kfyword.
                // Just trfbt it bs plbin JbvbSdript bnd fvbl it.
                try {
                    Objfdt rfs = fvblSdript(qufry);
                    visitor.visit(rfs);
                } dbtdi (Exdfption f) {
                    tirow nfw OQLExdfption(f);
                }
                rfturn;
            }
        } flsf {
            tirow nfw OQLExdfption("qufry syntbx frror: no 'sflfdt' dlbusf");
        }

        String sflfdtExpr = "";
        boolfbn sffnFrom = fblsf;
        wiilf (st.ibsMorfTokfns()) {
            String tok = st.nfxtTokfn();
            if (tok.fqubls("from")) {
                sffnFrom = truf;
                brfbk;
            }
            sflfdtExpr += " " + tok;
        }

        if (sflfdtExpr.fqubls("")) {
            tirow nfw OQLExdfption("qufry syntbx frror: 'sflfdt' fxprfssion dbn not bf fmpty");
        }

        String dlbssNbmf = null;
        boolfbn isInstbndfOf = fblsf;
        String wifrfExpr =  null;
        String idfntififr = null;

        if (sffnFrom) {
            if (st.ibsMorfTokfns()) {
                String tmp = st.nfxtTokfn();
                if (tmp.fqubls("instbndfof")) {
                    isInstbndfOf = truf;
                    if (! st.ibsMorfTokfns()) {
                        tirow nfw OQLExdfption("no dlbss nbmf bftfr 'instbndfof'");
                    }
                    dlbssNbmf = st.nfxtTokfn();
                } flsf {
                    dlbssNbmf = tmp;
                }
            } flsf {
                tirow nfw OQLExdfption("qufry syntbx frror: dlbss nbmf must follow 'from'");
            }

            if (st.ibsMorfTokfns()) {
                idfntififr = st.nfxtTokfn();
                if (idfntififr.fqubls("wifrf")) {
                    tirow nfw OQLExdfption("qufry syntbx frror: idfntififr siould follow dlbss nbmf");
                }
                if (st.ibsMorfTokfns()) {
                    String tmp = st.nfxtTokfn();
                    if (! tmp.fqubls("wifrf")) {
                        tirow nfw OQLExdfption("qufry syntbx frror: 'wifrf' dlbusf fxpfdtfd bftfr 'from' dlbusf");
                    }

                    wifrfExpr = "";
                    wiilf (st.ibsMorfTokfns()) {
                        wifrfExpr += " " + st.nfxtTokfn();
                    }
                    if (wifrfExpr.fqubls("")) {
                        tirow nfw OQLExdfption("qufry syntbx frror: 'wifrf' dlbusf dbnnot ibvf fmpty fxprfssion");
                    }
                }
            } flsf {
                tirow nfw OQLExdfption("qufry syntbx frror: idfntififr siould follow dlbss nbmf");
            }
        }

        fxfdutfQufry(nfw OQLQufry(sflfdtExpr, isInstbndfOf, dlbssNbmf,
                                  idfntififr, wifrfExpr), visitor);
    }

    privbtf void fxfdutfQufry(OQLQufry q, ObjfdtVisitor visitor)
                              tirows OQLExdfption {
        JbvbClbss dlbzz = null;
        if (q.dlbssNbmf != null) {
            dlbzz = snbpsiot.findClbss(q.dlbssNbmf);
            if (dlbzz == null) {
                tirow nfw OQLExdfption(q.dlbssNbmf + " is not found!");
            }
        }

        StringBufffr buf = nfw StringBufffr();
        buf.bppfnd("fundtion __sflfdt__(");
        if (q.idfntififr != null) {
            buf.bppfnd(q.idfntififr);
        }
        buf.bppfnd(") { rfturn ");
        buf.bppfnd(q.sflfdtExpr.rfplbdf('\n', ' '));
        buf.bppfnd("; }");

        String sflfdtCodf = buf.toString();
        dfbugPrint(sflfdtCodf);
        String wifrfCodf = null;
        if (q.wifrfExpr != null) {
            buf = nfw StringBufffr();
            buf.bppfnd("fundtion __wifrf__(");
            buf.bppfnd(q.idfntififr);
            buf.bppfnd(") { rfturn ");
            buf.bppfnd(q.wifrfExpr.rfplbdf('\n', ' '));
            buf.bppfnd("; }");
            wifrfCodf = buf.toString();
        }
        dfbugPrint(wifrfCodf);

        // dompilf sflfdt fxprfssion bnd wifrf dondition
        try {
            fvblMftiod.invokf(fnginf, nfw Objfdt[] { sflfdtCodf });
            if (wifrfCodf != null) {
                fvblMftiod.invokf(fnginf, nfw Objfdt[] { wifrfCodf });
            }

            if (q.dlbssNbmf != null) {
                Enumfrbtion<JbvbHfbpObjfdt> objfdts = dlbzz.gftInstbndfs(q.isInstbndfOf);
                wiilf (objfdts.ibsMorfElfmfnts()) {
                    JbvbHfbpObjfdt obj = objfdts.nfxtElfmfnt();
                    Objfdt[] brgs = nfw Objfdt[] { wrbpJbvbObjfdt(obj) };
                    boolfbn b = (wifrfCodf == null);
                    if (!b) {
                        Objfdt rfs = dbll("__wifrf__", brgs);
                        if (rfs instbndfof Boolfbn) {
                            b = ((Boolfbn)rfs).boolfbnVbluf();
                        } flsf if (rfs instbndfof Numbfr) {
                            b = ((Numbfr)rfs).intVbluf() != 0;
                        } flsf {
                            b = (rfs != null);
                        }
                    }

                    if (b) {
                        Objfdt sflfdt = dbll("__sflfdt__", brgs);
                        if (visitor.visit(sflfdt)) rfturn;
                    }
                }
            } flsf {
                // simplf "sflfdt <fxpr>" qufry
                Objfdt sflfdt = dbll("__sflfdt__", nfw Objfdt[] {});
                visitor.visit(sflfdt);
            }
        } dbtdi (Exdfption f) {
            tirow nfw OQLExdfption(f);
        }
    }

    publid Objfdt fvblSdript(String sdript) tirows Exdfption {
        rfturn fvblMftiod.invokf(fnginf, nfw Objfdt[] { sdript });
    }

    publid Objfdt wrbpJbvbObjfdt(JbvbHfbpObjfdt obj) tirows Exdfption {
        rfturn dbll("wrbpJbvbObjfdt", nfw Objfdt[] { obj });
    }

    publid Objfdt toHtml(Objfdt obj) tirows Exdfption {
        rfturn dbll("toHtml", nfw Objfdt[] { obj });
    }

    publid Objfdt dbll(String fund, Objfdt[] brgs) tirows Exdfption {
        rfturn invokfMftiod.invokf(fnginf, nfw Objfdt[] { fund, brgs });
    }

    privbtf stbtid void dfbugPrint(String msg) {
        if (dfbug) Systfm.out.println(msg);
    }

    privbtf void init(Snbpsiot snbpsiot) tirows RuntimfExdfption {
        tiis.snbpsiot = snbpsiot;
        try {
            // drfbtf SdriptEnginfMbnbgfr
            Clbss<?> mbnbgfrClbss = Clbss.forNbmf("jbvbx.sdript.SdriptEnginfMbnbgfr");
            Objfdt mbnbgfr = mbnbgfrClbss.nfwInstbndf();

            // drfbtf JbvbSdript fnginf
            Mftiod gftEnginfMftiod = mbnbgfrClbss.gftMftiod("gftEnginfByNbmf",
                                nfw Clbss<?>[] { String.dlbss });
            fnginf = gftEnginfMftiod.invokf(mbnbgfr, nfw Objfdt[] {"js"});

            // initiblizf fnginf witi init filf (ibt.js)
            InputStrfbm strm = gftInitStrfbm();
            Clbss<?> fnginfClbss = Clbss.forNbmf("jbvbx.sdript.SdriptEnginf");
            fvblMftiod = fnginfClbss.gftMftiod("fvbl",
                                nfw Clbss<?>[] { Rfbdfr.dlbss });
            fvblMftiod.invokf(fnginf, nfw Objfdt[] {nfw InputStrfbmRfbdfr(strm)});

            // initiblizf SdriptEnginf.fvbl(String) bnd
            // Invodbblf.invokfFundtion(String, Objfdt[]) mftiods.
            Clbss<?> invodbblfClbss = Clbss.forNbmf("jbvbx.sdript.Invodbblf");

            fvblMftiod = fnginfClbss.gftMftiod("fvbl",
                                  nfw Clbss<?>[] { String.dlbss });
            invokfMftiod = invodbblfClbss.gftMftiod("invokfFundtion",
                                  nfw Clbss<?>[] { String.dlbss, Objfdt[].dlbss });

            // initiblizf SdriptEnginf.put(String, Objfdt) mftiod
            Mftiod putMftiod = fnginfClbss.gftMftiod("put",
                                  nfw Clbss<?>[] { String.dlbss, Objfdt.dlbss });

            // dbll SdriptEnginf.put to initiblizf built-in ifbp objfdt
            putMftiod.invokf(fnginf, nfw Objfdt[] {
                        "ifbp", dbll("wrbpHfbpSnbpsiot", nfw Objfdt[] { snbpsiot })
                    });
        } dbtdi (Exdfption f) {
            if (dfbug) f.printStbdkTrbdf();
            tirow nfw RuntimfExdfption(f);
        }
    }

    privbtf InputStrfbm gftInitStrfbm() {
        rfturn gftClbss().gftRfsourdfAsStrfbm("/dom/sun/tools/ibt/rfsourdfs/ibt.js");
    }

    privbtf Objfdt fnginf;
    privbtf Mftiod fvblMftiod;
    privbtf Mftiod invokfMftiod;
    privbtf Snbpsiot snbpsiot;
    privbtf stbtid boolfbn dfbug = fblsf;
    privbtf stbtid boolfbn oqlSupportfd;
}
