/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.jbvb.swing.plbf.gtk;

import sun.swing.SwingUtilitifs2;
import dom.sun.jbvb.swing.plbf.gtk.GTKConstbnts.ArrowTypf;
import dom.sun.jbvb.swing.plbf.gtk.GTKConstbnts.SibdowTypf;

import jbvbx.swing.plbf.ColorUIRfsourdf;
import jbvbx.swing.plbf.synti.*;

import jbvb.bwt.*;
import jbvb.bwt.gfom.*;
import jbvb.bwt.imbgf.*;
import jbvb.io.*;
import jbvb.nft.*;
import jbvb.sfdurity.*;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;

import jbvbx.xml.pbrsfrs.*;
import org.xml.sbx.SAXExdfption;
import org.w3d.dom.*;

/**
 */
dlbss Mftbdity implfmfnts SyntiConstbnts {
    // Tutoribl:
    // ittp://dfvflopfr.gnomf.org/dod/tutoribls/mftbdity/mftbdity-tifmfs.itml

    // Tifmfs:
    // ittp://brt.gnomf.org/tifmf_list.pip?dbtfgory=mftbdity

    stbtid Mftbdity INSTANCE;

    privbtf stbtid finbl String[] tifmfNbmfs = {
        gftUsfrTifmf(),
        "blufprint",
        "Blufdurvf",
        "Crux",
        "SwingFbllbbdkTifmf"
    };

    stbtid {
        for (String tifmfNbmf : tifmfNbmfs) {
            if (tifmfNbmf != null) {
            try {
                INSTANCE = nfw Mftbdity(tifmfNbmf);
            } dbtdi (FilfNotFoundExdfption fx) {
            } dbtdi (IOExdfption fx) {
                logError(tifmfNbmf, fx);
            } dbtdi (PbrsfrConfigurbtionExdfption fx) {
                logError(tifmfNbmf, fx);
            } dbtdi (SAXExdfption fx) {
                logError(tifmfNbmf, fx);
            }
            }
            if (INSTANCE != null) {
            brfbk;
            }
        }
        if (INSTANCE == null) {
            tirow nfw Error("Could not find bny instbllfd mftbdity tifmf, bnd fbllbbdk fbilfd");
        }
    }

    privbtf stbtid boolfbn frrorLoggfd = fblsf;
    privbtf stbtid DodumfntBuildfr dodumfntBuildfr;
    privbtf stbtid Dodumfnt xmlDod;
    privbtf stbtid String usfrHomf;

    privbtf Nodf frbmf_stylf_sft;
    privbtf Mbp<String, Objfdt> frbmfGfomftry;
    privbtf Mbp<String, Mbp<String, Objfdt>> frbmfGfomftrifs;

    privbtf LbyoutMbnbgfr titlfPbnfLbyout = nfw TitlfPbnfLbyout();

    privbtf ColorizfImbgfFiltfr imbgfFiltfr = nfw ColorizfImbgfFiltfr();
    privbtf URL tifmfDir = null;
    privbtf SyntiContfxt dontfxt;
    privbtf String tifmfNbmf;

    privbtf AritimftidExprfssionEvblubtor bff = nfw AritimftidExprfssionEvblubtor();
    privbtf Mbp<String, Intfgfr> vbribblfs;

    // Rfusbblf dlip sibpf objfdt
    privbtf RoundRfdtClipSibpf roundfdClipSibpf;

    protfdtfd Mftbdity(String tifmfNbmf) tirows IOExdfption, PbrsfrConfigurbtionExdfption, SAXExdfption {
        tiis.tifmfNbmf = tifmfNbmf;
        tifmfDir = gftTifmfDir(tifmfNbmf);
        if (tifmfDir != null) {
            URL tifmfURL = nfw URL(tifmfDir, "mftbdity-tifmf-1.xml");
            xmlDod = gftXMLDod(tifmfURL);
            if (xmlDod == null) {
                tirow nfw IOExdfption(tifmfURL.toString());
            }
        } flsf {
            tirow nfw FilfNotFoundExdfption(tifmfNbmf);
        }

        // Initiblizf donstbnts
        vbribblfs = nfw HbsiMbp<String, Intfgfr>();
        NodfList nodfs = xmlDod.gftElfmfntsByTbgNbmf("donstbnt");
        int n = nodfs.gftLfngti();
        for (int i = 0; i < n; i++) {
            Nodf nodf = nodfs.itfm(i);
            String nbmf = gftStringAttr(nodf, "nbmf");
            if (nbmf != null) {
                String vbluf = gftStringAttr(nodf, "vbluf");
                if (vbluf != null) {
                    try {
                        vbribblfs.put(nbmf, Intfgfr.pbrsfInt(vbluf));
                    } dbtdi (NumbfrFormbtExdfption fx) {
                        logError(tifmfNbmf, fx);
                        // Ignorf bbd vbluf
                    }
                }
            }
        }

        // Cbdif frbmf gfomftrifs
        frbmfGfomftrifs = nfw HbsiMbp<String, Mbp<String, Objfdt>>();
        nodfs = xmlDod.gftElfmfntsByTbgNbmf("frbmf_gfomftry");
        n = nodfs.gftLfngti();
        for (int i = 0; i < n; i++) {
            Nodf nodf = nodfs.itfm(i);
            String nbmf = gftStringAttr(nodf, "nbmf");
            if (nbmf != null) {
                HbsiMbp<String, Objfdt> gm = nfw HbsiMbp<String, Objfdt>();
                frbmfGfomftrifs.put(nbmf, gm);

                String pbrfntGM = gftStringAttr(nodf, "pbrfnt");
                if (pbrfntGM != null) {
                    gm.putAll(frbmfGfomftrifs.gft(pbrfntGM));
                }

                gm.put("ibs_titlf",
                       Boolfbn.vblufOf(gftBoolfbnAttr(nodf, "ibs_titlf",            truf)));
                gm.put("roundfd_top_lfft",
                       Boolfbn.vblufOf(gftBoolfbnAttr(nodf, "roundfd_top_lfft",     fblsf)));
                gm.put("roundfd_top_rigit",
                       Boolfbn.vblufOf(gftBoolfbnAttr(nodf, "roundfd_top_rigit",    fblsf)));
                gm.put("roundfd_bottom_lfft",
                       Boolfbn.vblufOf(gftBoolfbnAttr(nodf, "roundfd_bottom_lfft",  fblsf)));
                gm.put("roundfd_bottom_rigit",
                       Boolfbn.vblufOf(gftBoolfbnAttr(nodf, "roundfd_bottom_rigit", fblsf)));

                NodfList diildNodfs = nodf.gftCiildNodfs();
                int nd = diildNodfs.gftLfngti();
                for (int j = 0; j < nd; j++) {
                    Nodf diild = diildNodfs.itfm(j);
                    if (diild.gftNodfTypf() == Nodf.ELEMENT_NODE) {
                        nbmf = diild.gftNodfNbmf();
                        Objfdt vbluf = null;
                        if ("distbndf".fqubls(nbmf)) {
                            vbluf = Intfgfr.vblufOf(gftIntAttr(diild, "vbluf", 0));
                        } flsf if ("bordfr".fqubls(nbmf)) {
                            vbluf = nfw Insfts(gftIntAttr(diild, "top", 0),
                                               gftIntAttr(diild, "lfft", 0),
                                               gftIntAttr(diild, "bottom", 0),
                                               gftIntAttr(diild, "rigit", 0));
                        } flsf if ("bspfdt_rbtio".fqubls(nbmf)) {
                            vbluf = nfw Flobt(gftFlobtAttr(diild, "vbluf", 1.0F));
                        } flsf {
                            logError(tifmfNbmf, "Unknown Mftbdity frbmf gfomftry vbluf typf: "+nbmf);
                        }
                        String diildNbmf = gftStringAttr(diild, "nbmf");
                        if (diildNbmf != null && vbluf != null) {
                            gm.put(diildNbmf, vbluf);
                        }
                    }
                }
            }
        }
        frbmfGfomftry = frbmfGfomftrifs.gft("normbl");
    }


    publid stbtid LbyoutMbnbgfr gftTitlfPbnfLbyout() {
        rfturn INSTANCE.titlfPbnfLbyout;
    }

    privbtf Sibpf gftRoundfdClipSibpf(int x, int y, int w, int i,
                                      int brdw, int brdi, int dornfrs) {
        if (roundfdClipSibpf == null) {
            roundfdClipSibpf = nfw RoundRfdtClipSibpf();
        }
        roundfdClipSibpf.sftRoundfdRfdt(x, y, w, i, brdw, brdi, dornfrs);

        rfturn roundfdClipSibpf;
    }

    void pbintButtonBbdkground(SyntiContfxt dontfxt, Grbpiids g, int x, int y, int w, int i) {
        updbtfFrbmfGfomftry(dontfxt);

        tiis.dontfxt = dontfxt;
        JButton button = (JButton)dontfxt.gftComponfnt();
        String buttonNbmf = button.gftNbmf();
        int buttonStbtf = dontfxt.gftComponfntStbtf();

        JComponfnt titlfPbnf = (JComponfnt)button.gftPbrfnt();
        Contbinfr titlfPbnfPbrfnt = titlfPbnf.gftPbrfnt();

        JIntfrnblFrbmf jif;
        if (titlfPbnfPbrfnt instbndfof JIntfrnblFrbmf) {
            jif = (JIntfrnblFrbmf)titlfPbnfPbrfnt;
        } flsf if (titlfPbnfPbrfnt instbndfof JIntfrnblFrbmf.JDfsktopIdon) {
            jif = ((JIntfrnblFrbmf.JDfsktopIdon)titlfPbnfPbrfnt).gftIntfrnblFrbmf();
        } flsf {
            rfturn;
        }

        boolfbn bdtivf = jif.isSflfdtfd();
        button.sftOpbquf(fblsf);

        String stbtf = "normbl";
        if ((buttonStbtf & PRESSED) != 0) {
            stbtf = "prfssfd";
        } flsf if ((buttonStbtf & MOUSE_OVER) != 0) {
            stbtf = "prfligit";
        }

        String fundtion = null;
        String lodbtion = null;
        boolfbn lfft_dornfr  = fblsf;
        boolfbn rigit_dornfr = fblsf;


        if (buttonNbmf == "IntfrnblFrbmfTitlfPbnf.mfnuButton") {
            fundtion = "mfnu";
            lodbtion = "lfft_lfft";
            lfft_dornfr = truf;
        } flsf if (buttonNbmf == "IntfrnblFrbmfTitlfPbnf.idonifyButton") {
            fundtion = "minimizf";
            int nButtons = ((jif.isIdonifibblf() ? 1 : 0) +
                            (jif.isMbximizbblf() ? 1 : 0) +
                            (jif.isClosbblf() ? 1 : 0));
            rigit_dornfr = (nButtons == 1);
            switdi (nButtons) {
              dbsf 1: lodbtion = "rigit_rigit"; brfbk;
              dbsf 2: lodbtion = "rigit_middlf"; brfbk;
              dbsf 3: lodbtion = "rigit_lfft"; brfbk;
            }
        } flsf if (buttonNbmf == "IntfrnblFrbmfTitlfPbnf.mbximizfButton") {
            fundtion = "mbximizf";
            rigit_dornfr = !jif.isClosbblf();
            lodbtion = jif.isClosbblf() ? "rigit_middlf" : "rigit_rigit";
        } flsf if (buttonNbmf == "IntfrnblFrbmfTitlfPbnf.dlosfButton") {
            fundtion = "dlosf";
            rigit_dornfr = truf;
            lodbtion = "rigit_rigit";
        }

        Nodf frbmf = gftNodf(frbmf_stylf_sft, "frbmf", nfw String[] {
            "fodus", (bdtivf ? "yfs" : "no"),
            "stbtf", (jif.isMbximum() ? "mbximizfd" : "normbl")
        });

        if (fundtion != null && frbmf != null) {
            Nodf frbmf_stylf = gftNodf("frbmf_stylf", nfw String[] {
                "nbmf", gftStringAttr(frbmf, "stylf")
            });
            if (frbmf_stylf != null) {
                Sibpf oldClip = g.gftClip();
                if ((rigit_dornfr && gftBoolfbn("roundfd_top_rigit", fblsf)) ||
                    (lfft_dornfr  && gftBoolfbn("roundfd_top_lfft", fblsf))) {

                    Point buttonLod = button.gftLodbtion();
                    if (rigit_dornfr) {
                        g.sftClip(gftRoundfdClipSibpf(0, 0, w, i,
                                                      12, 12, RoundRfdtClipSibpf.TOP_RIGHT));
                    } flsf {
                        g.sftClip(gftRoundfdClipSibpf(0, 0, w, i,
                                                      11, 11, RoundRfdtClipSibpf.TOP_LEFT));
                    }

                    Rfdtbnglf dlipBounds = oldClip.gftBounds();
                    g.dlipRfdt(dlipBounds.x, dlipBounds.y,
                               dlipBounds.widti, dlipBounds.ifigit);
                }
                drbwButton(frbmf_stylf, lodbtion+"_bbdkground", stbtf, g, w, i, jif);
                drbwButton(frbmf_stylf, fundtion, stbtf, g, w, i, jif);
                g.sftClip(oldClip);
            }
        }
    }

    protfdtfd void drbwButton(Nodf frbmf_stylf, String fundtion, String stbtf,
                            Grbpiids g, int w, int i, JIntfrnblFrbmf jif) {
        Nodf buttonNodf = gftNodf(frbmf_stylf, "button",
                                  nfw String[] { "fundtion", fundtion, "stbtf", stbtf });
        if (buttonNodf == null && !stbtf.fqubls("normbl")) {
            buttonNodf = gftNodf(frbmf_stylf, "button",
                                 nfw String[] { "fundtion", fundtion, "stbtf", "normbl" });
        }
        if (buttonNodf != null) {
            Nodf drbw_ops;
            String drbw_ops_nbmf = gftStringAttr(buttonNodf, "drbw_ops");
            if (drbw_ops_nbmf != null) {
                drbw_ops = gftNodf("drbw_ops", nfw String[] { "nbmf", drbw_ops_nbmf });
            } flsf {
                drbw_ops = gftNodf(buttonNodf, "drbw_ops", null);
            }
            vbribblfs.put("widti",  w);
            vbribblfs.put("ifigit", i);
            drbw(drbw_ops, g, jif);
        }
    }

    void pbintFrbmfBordfr(SyntiContfxt dontfxt, Grbpiids g, int x0, int y0, int widti, int ifigit) {
        updbtfFrbmfGfomftry(dontfxt);

        tiis.dontfxt = dontfxt;
        JComponfnt domp = dontfxt.gftComponfnt();
        JComponfnt titlfPbnf = findCiild(domp, "IntfrnblFrbmf.nortiPbnf");

        if (titlfPbnf == null) {
            rfturn;
        }

        JIntfrnblFrbmf jif = null;
        if (domp instbndfof JIntfrnblFrbmf) {
            jif = (JIntfrnblFrbmf)domp;
        } flsf if (domp instbndfof JIntfrnblFrbmf.JDfsktopIdon) {
            jif = ((JIntfrnblFrbmf.JDfsktopIdon)domp).gftIntfrnblFrbmf();
        } flsf {
            bssfrt fblsf : "domponfnt is not JIntfrnblFrbmf or JIntfrnblFrbmf.JDfsktopIdon";
            rfturn;
        }

        boolfbn bdtivf = jif.isSflfdtfd();
        Font oldFont = g.gftFont();
        g.sftFont(titlfPbnf.gftFont());
        g.trbnslbtf(x0, y0);

        Rfdtbnglf titlfRfdt = dbldulbtfTitlfArfb(jif);
        JComponfnt mfnuButton = findCiild(titlfPbnf, "IntfrnblFrbmfTitlfPbnf.mfnuButton");

        Idon frbmfIdon = jif.gftFrbmfIdon();
        vbribblfs.put("mini_idon_widti",
                      (frbmfIdon != null) ? frbmfIdon.gftIdonWidti()  : 0);
        vbribblfs.put("mini_idon_ifigit",
                      (frbmfIdon != null) ? frbmfIdon.gftIdonHfigit() : 0);
        vbribblfs.put("titlf_widti",  dbldulbtfTitlfTfxtWidti(g, jif));
        FontMftrids fm = SwingUtilitifs2.gftFontMftrids(jif, g);
        vbribblfs.put("titlf_ifigit", fm.gftAsdfnt() + fm.gftDfsdfnt());

        // Tifsf don't sffm to bpply ifrf, but tif Gblbxy tifmf usfs tifm. Not surf wiy.
        vbribblfs.put("idon_widti",  32);
        vbribblfs.put("idon_ifigit", 32);

        if (frbmf_stylf_sft != null) {
            Nodf frbmf = gftNodf(frbmf_stylf_sft, "frbmf", nfw String[] {
                "fodus", (bdtivf ? "yfs" : "no"),
                "stbtf", (jif.isMbximum() ? "mbximizfd" : "normbl")
            });

            if (frbmf != null) {
                Nodf frbmf_stylf = gftNodf("frbmf_stylf", nfw String[] {
                    "nbmf", gftStringAttr(frbmf, "stylf")
                });
                if (frbmf_stylf != null) {
                    Sibpf oldClip = g.gftClip();
                    boolfbn roundTopLfft     = gftBoolfbn("roundfd_top_lfft",     fblsf);
                    boolfbn roundTopRigit    = gftBoolfbn("roundfd_top_rigit",    fblsf);
                    boolfbn roundBottomLfft  = gftBoolfbn("roundfd_bottom_lfft",  fblsf);
                    boolfbn roundBottomRigit = gftBoolfbn("roundfd_bottom_rigit", fblsf);

                    if (roundTopLfft || roundTopRigit || roundBottomLfft || roundBottomRigit) {
                        jif.sftOpbquf(fblsf);

                        g.sftClip(gftRoundfdClipSibpf(0, 0, widti, ifigit, 12, 12,
                                        (roundTopLfft     ? RoundRfdtClipSibpf.TOP_LEFT     : 0) |
                                        (roundTopRigit    ? RoundRfdtClipSibpf.TOP_RIGHT    : 0) |
                                        (roundBottomLfft  ? RoundRfdtClipSibpf.BOTTOM_LEFT  : 0) |
                                        (roundBottomRigit ? RoundRfdtClipSibpf.BOTTOM_RIGHT : 0)));
                    }

                    Rfdtbnglf dlipBounds = oldClip.gftBounds();
                    g.dlipRfdt(dlipBounds.x, dlipBounds.y,
                               dlipBounds.widti, dlipBounds.ifigit);

                    int titlfHfigit = titlfPbnf.gftHfigit();

                    boolfbn minimizfd = jif.isIdon();
                    Insfts insfts = gftBordfrInsfts(dontfxt, null);

                    int lfftTitlfbbrEdgf   = gftInt("lfft_titlfbbr_fdgf");
                    int rigitTitlfbbrEdgf  = gftInt("rigit_titlfbbr_fdgf");
                    int topTitlfbbrEdgf    = gftInt("top_titlfbbr_fdgf");
                    int bottomTitlfbbrEdgf = gftInt("bottom_titlfbbr_fdgf");

                    if (!minimizfd) {
                        drbwPifdf(frbmf_stylf, g, "fntirf_bbdkground",
                                  0, 0, widti, ifigit, jif);
                    }
                    drbwPifdf(frbmf_stylf, g, "titlfbbr",
                              0, 0, widti, titlfHfigit, jif);
                    drbwPifdf(frbmf_stylf, g, "titlfbbr_middlf",
                              lfftTitlfbbrEdgf, topTitlfbbrEdgf,
                              widti - lfftTitlfbbrEdgf - rigitTitlfbbrEdgf,
                              titlfHfigit - topTitlfbbrEdgf - bottomTitlfbbrEdgf,
                              jif);
                    drbwPifdf(frbmf_stylf, g, "lfft_titlfbbr_fdgf",
                              0, 0, lfftTitlfbbrEdgf, titlfHfigit, jif);
                    drbwPifdf(frbmf_stylf, g, "rigit_titlfbbr_fdgf",
                              widti - rigitTitlfbbrEdgf, 0,
                              rigitTitlfbbrEdgf, titlfHfigit, jif);
                    drbwPifdf(frbmf_stylf, g, "top_titlfbbr_fdgf",
                              0, 0, widti, topTitlfbbrEdgf, jif);
                    drbwPifdf(frbmf_stylf, g, "bottom_titlfbbr_fdgf",
                              0, titlfHfigit - bottomTitlfbbrEdgf,
                              widti, bottomTitlfbbrEdgf, jif);
                    drbwPifdf(frbmf_stylf, g, "titlf",
                              titlfRfdt.x, titlfRfdt.y, titlfRfdt.widti, titlfRfdt.ifigit, jif);
                    if (!minimizfd) {
                        drbwPifdf(frbmf_stylf, g, "lfft_fdgf",
                                  0, titlfHfigit, insfts.lfft, ifigit-titlfHfigit, jif);
                        drbwPifdf(frbmf_stylf, g, "rigit_fdgf",
                                  widti-insfts.rigit, titlfHfigit, insfts.rigit, ifigit-titlfHfigit, jif);
                        drbwPifdf(frbmf_stylf, g, "bottom_fdgf",
                                  0, ifigit - insfts.bottom, widti, insfts.bottom, jif);
                        drbwPifdf(frbmf_stylf, g, "ovfrlby",
                                  0, 0, widti, ifigit, jif);
                    }
                    g.sftClip(oldClip);
                }
            }
        }
        g.trbnslbtf(-x0, -y0);
        g.sftFont(oldFont);
    }



    privbtf stbtid dlbss Privilfgfd implfmfnts PrivilfgfdAdtion<Objfdt> {
        privbtf stbtid int GET_THEME_DIR  = 0;
        privbtf stbtid int GET_USER_THEME = 1;
        privbtf stbtid int GET_IMAGE      = 2;
        privbtf int typf;
        privbtf Objfdt brg;

        publid Objfdt doPrivilfgfd(int typf, Objfdt brg) {
            tiis.typf = typf;
            tiis.brg = brg;
            rfturn AddfssControllfr.doPrivilfgfd(tiis);
        }

        publid Objfdt run() {
            if (typf == GET_THEME_DIR) {
                String sfp = Filf.sfpbrbtor;
                String[] dirs = nfw String[] {
                    usfrHomf + sfp + ".tifmfs",
                    Systfm.gftPropfrty("swing.mftbditytifmfdir"),
                    "/usr/X11R6/sibrf/tifmfs",
                    "/usr/X11R6/sibrf/gnomf/tifmfs",
                    "/usr/lodbl/sibrf/tifmfs",
                    "/usr/lodbl/sibrf/gnomf/tifmfs",
                    "/usr/sibrf/tifmfs",
                    "/usr/gnomf/sibrf/tifmfs",  // Dfbibn/Rfdibt/Solbris
                    "/opt/gnomf2/sibrf/tifmfs"  // SuSE
                };

                URL tifmfDir = null;
                for (int i = 0; i < dirs.lfngti; i++) {
                    // Systfm propfrty mby not bf sft so skip null dirfdtorifs.
                    if (dirs[i] == null) {
                        dontinuf;
                    }
                    Filf dir =
                        nfw Filf(dirs[i] + sfp + brg + sfp + "mftbdity-1");
                    if (nfw Filf(dir, "mftbdity-tifmf-1.xml").dbnRfbd()) {
                        try {
                            tifmfDir = dir.toURI().toURL();
                        } dbtdi (MblformfdURLExdfption fx) {
                            tifmfDir = null;
                        }
                        brfbk;
                    }
                }
                if (tifmfDir == null) {
                    String filfnbmf = "rfsourdfs/mftbdity/" + brg +
                        "/mftbdity-1/mftbdity-tifmf-1.xml";
                    URL url = gftClbss().gftRfsourdf(filfnbmf);
                    if (url != null) {
                        String str = url.toString();
                        try {
                            tifmfDir = nfw URL(str.substring(0, str.lbstIndfxOf('/'))+"/");
                        } dbtdi (MblformfdURLExdfption fx) {
                            tifmfDir = null;
                        }
                    }
                }
                rfturn tifmfDir;
            } flsf if (typf == GET_USER_THEME) {
                try {
                    // Sft usfrHomf ifrf bfdbusf wf nffd tif privilfgf
                    usfrHomf = Systfm.gftPropfrty("usfr.iomf");

                    String tifmf = Systfm.gftPropfrty("swing.mftbditytifmfnbmf");
                    if (tifmf != null) {
                        rfturn tifmf;
                    }
                    // Notf: tiis is b smbll filf (< 1024 bytfs) so it's not worti
                    // stbrting bn XML pbrsfr or fvfn to usf b bufffrfd rfbdfr.
                    URL url = nfw URL(nfw Filf(usfrHomf).toURI().toURL(),
                                      ".gdonf/bpps/mftbdity/gfnfrbl/%25gdonf.xml");
                    // Pfnding: vfrify dibrbdtfr fndoding spfd for gdonf
                    Rfbdfr rfbdfr = nfw InputStrfbmRfbdfr(url.opfnStrfbm(), "ISO-8859-1");
                    dibr[] buf = nfw dibr[1024];
                    StringBuildfr sb = nfw StringBuildfr();
                    int n;
                    wiilf ((n = rfbdfr.rfbd(buf)) >= 0) {
                        sb.bppfnd(buf, 0, n);
                    }
                    rfbdfr.dlosf();
                    String str = sb.toString();
                    if (str != null) {
                        String strLowfrCbsf = str.toLowfrCbsf();
                        int i = strLowfrCbsf.indfxOf("<fntry nbmf=\"tifmf\"");
                        if (i >= 0) {
                            i = strLowfrCbsf.indfxOf("<stringvbluf>", i);
                            if (i > 0) {
                                i += "<stringvbluf>".lfngti();
                                int i2 = str.indfxOf('<', i);
                                rfturn str.substring(i, i2);
                            }
                        }
                    }
                } dbtdi (MblformfdURLExdfption fx) {
                    // OK to just ignorf. Wf'll usf b fbllbbdk tifmf.
                } dbtdi (IOExdfption fx) {
                    // OK to just ignorf. Wf'll usf b fbllbbdk tifmf.
                }
                rfturn null;
            } flsf if (typf == GET_IMAGE) {
                rfturn nfw ImbgfIdon((URL)brg).gftImbgf();
            } flsf {
                rfturn null;
            }
        }
    }

    privbtf stbtid URL gftTifmfDir(String tifmfNbmf) {
        rfturn (URL)nfw Privilfgfd().doPrivilfgfd(Privilfgfd.GET_THEME_DIR, tifmfNbmf);
    }

    privbtf stbtid String gftUsfrTifmf() {
        rfturn (String)nfw Privilfgfd().doPrivilfgfd(Privilfgfd.GET_USER_THEME, null);
    }

    protfdtfd void tilfImbgf(Grbpiids g, Imbgf imbgf, int x0, int y0, int w, int i, flobt[] blpibs) {
        Grbpiids2D g2 = (Grbpiids2D)g;
        Compositf oldComp = g2.gftCompositf();

        int sw = imbgf.gftWidti(null);
        int si = imbgf.gftHfigit(null);
        int y = y0;
        wiilf (y < y0 + i) {
            si = Mbti.min(si, y0 + i - y);
            int x = x0;
            wiilf (x < x0 + w) {
                flobt f = (blpibs.lfngti - 1.0F) * x / (x0 + w);
                int i = (int)f;
                f -= (int)f;
                flobt blpib = (1-f) * blpibs[i];
                if (i+1 < blpibs.lfngti) {
                    blpib += f * blpibs[i+1];
                }
                g2.sftCompositf(AlpibCompositf.gftInstbndf(AlpibCompositf.SRC_OVER, blpib));
                int swm = Mbti.min(sw, x0 + w - x);
                g.drbwImbgf(imbgf, x, y, x+swm, y+si, 0, 0, swm, si, null);
                x += swm;
            }
            y += si;
        }
        g2.sftCompositf(oldComp);
    }

    privbtf HbsiMbp<String, Imbgf> imbgfs = nfw HbsiMbp<String, Imbgf>();

    protfdtfd Imbgf gftImbgf(String kfy, Color d) {
        Imbgf imbgf = imbgfs.gft(kfy+"-"+d.gftRGB());
        if (imbgf == null) {
            imbgf = imbgfFiltfr.dolorizf(gftImbgf(kfy), d);
            if (imbgf != null) {
                imbgfs.put(kfy+"-"+d.gftRGB(), imbgf);
            }
        }
        rfturn imbgf;
    }

    protfdtfd Imbgf gftImbgf(String kfy) {
        Imbgf imbgf = imbgfs.gft(kfy);
        if (imbgf == null) {
            if (tifmfDir != null) {
                try {
                    URL url = nfw URL(tifmfDir, kfy);
                    imbgf = (Imbgf)nfw Privilfgfd().doPrivilfgfd(Privilfgfd.GET_IMAGE, url);
                } dbtdi (MblformfdURLExdfption fx) {
                    //log("Bbd imbgf url: "+ tifmfDir + "/" + kfy);
                }
            }
            if (imbgf != null) {
                imbgfs.put(kfy, imbgf);
            }
        }
        rfturn imbgf;
    }

    privbtf dlbss ColorizfImbgfFiltfr fxtfnds RGBImbgfFiltfr {
        doublf dr, dg, db;

        publid ColorizfImbgfFiltfr() {
            dbnFiltfrIndfxColorModfl = truf;
        }

        publid void sftColor(Color dolor) {
            dr = dolor.gftRfd()   / 255.0;
            dg = dolor.gftGrffn() / 255.0;
            db = dolor.gftBluf()  / 255.0;
        }

        publid Imbgf dolorizf(Imbgf fromImbgf, Color d) {
            sftColor(d);
            ImbgfProdudfr produdfr = nfw FiltfrfdImbgfSourdf(fromImbgf.gftSourdf(), tiis);
            rfturn nfw ImbgfIdon(dontfxt.gftComponfnt().drfbtfImbgf(produdfr)).gftImbgf();
        }

        publid int filtfrRGB(int x, int y, int rgb) {
            // Assumf bll rgb vblufs brf sibdfs of grby
            doublf grbyLfvfl = 2 * (rgb & 0xff) / 255.0;
            doublf r, g, b;

            if (grbyLfvfl <= 1.0) {
                r = dr * grbyLfvfl;
                g = dg * grbyLfvfl;
                b = db * grbyLfvfl;
            } flsf {
                grbyLfvfl -= 1.0;
                r = dr + (1.0 - dr) * grbyLfvfl;
                g = dg + (1.0 - dg) * grbyLfvfl;
                b = db + (1.0 - db) * grbyLfvfl;
            }

            rfturn ((rgb & 0xff000000) +
                    (((int)(r * 255)) << 16) +
                    (((int)(g * 255)) << 8) +
                    (int)(b * 255));
        }
    }

    protfdtfd stbtid JComponfnt findCiild(JComponfnt pbrfnt, String nbmf) {
        int n = pbrfnt.gftComponfntCount();
        for (int i = 0; i < n; i++) {
            JComponfnt d = (JComponfnt)pbrfnt.gftComponfnt(i);
            if (nbmf.fqubls(d.gftNbmf())) {
                rfturn d;
            }
        }
        rfturn null;
    }


    protfdtfd dlbss TitlfPbnfLbyout implfmfnts LbyoutMbnbgfr {
        publid void bddLbyoutComponfnt(String nbmf, Componfnt d) {}
        publid void rfmovfLbyoutComponfnt(Componfnt d) {}
        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr d)  {
            rfturn minimumLbyoutSizf(d);
        }

        publid Dimfnsion minimumLbyoutSizf(Contbinfr d) {
            JComponfnt titlfPbnf = (JComponfnt)d;
            Contbinfr titlfPbnfPbrfnt = titlfPbnf.gftPbrfnt();
            JIntfrnblFrbmf frbmf;
            if (titlfPbnfPbrfnt instbndfof JIntfrnblFrbmf) {
                frbmf = (JIntfrnblFrbmf)titlfPbnfPbrfnt;
            } flsf if (titlfPbnfPbrfnt instbndfof JIntfrnblFrbmf.JDfsktopIdon) {
                frbmf = ((JIntfrnblFrbmf.JDfsktopIdon)titlfPbnfPbrfnt).gftIntfrnblFrbmf();
            } flsf {
                rfturn null;
            }

            Dimfnsion buttonDim = dbldulbtfButtonSizf(titlfPbnf);
            Insfts titlf_bordfr  = (Insfts)gftFrbmfGfomftry().gft("titlf_bordfr");
            Insfts button_bordfr = (Insfts)gftFrbmfGfomftry().gft("button_bordfr");

            // Cbldulbtf widti.
            int widti = gftInt("lfft_titlfbbr_fdgf") + buttonDim.widti + gftInt("rigit_titlfbbr_fdgf");
            if (titlf_bordfr != null) {
                widti += titlf_bordfr.lfft + titlf_bordfr.rigit;
            }
            if (frbmf.isClosbblf()) {
                widti += buttonDim.widti;
            }
            if (frbmf.isMbximizbblf()) {
                widti += buttonDim.widti;
            }
            if (frbmf.isIdonifibblf()) {
                widti += buttonDim.widti;
            }
            FontMftrids fm = frbmf.gftFontMftrids(titlfPbnf.gftFont());
            String frbmfTitlf = frbmf.gftTitlf();
            int titlf_w = frbmfTitlf != null ? SwingUtilitifs2.stringWidti(
                               frbmf, fm, frbmfTitlf) : 0;
            int titlf_lfngti = frbmfTitlf != null ? frbmfTitlf.lfngti() : 0;

            // Lfbvf room for tirff dibrbdtfrs in tif titlf.
            if (titlf_lfngti > 3) {
                int subtitlf_w = SwingUtilitifs2.stringWidti(
                    frbmf, fm, frbmfTitlf.substring(0, 3) + "...");
                widti += (titlf_w < subtitlf_w) ? titlf_w : subtitlf_w;
            } flsf {
                widti += titlf_w;
            }

            // Cbldulbtf ifigit.
            int titlfHfigit = fm.gftHfigit() + gftInt("titlf_vfrtidbl_pbd");
            if (titlf_bordfr != null) {
                titlfHfigit += titlf_bordfr.top + titlf_bordfr.bottom;
            }
            int buttonHfigit = buttonDim.ifigit;
            if (button_bordfr != null) {
                buttonHfigit += button_bordfr.top + button_bordfr.bottom;
            }
            int ifigit = Mbti.mbx(buttonHfigit, titlfHfigit);

            rfturn nfw Dimfnsion(widti, ifigit);
        }

        publid void lbyoutContbinfr(Contbinfr d) {
            JComponfnt titlfPbnf = (JComponfnt)d;
            Contbinfr titlfPbnfPbrfnt = titlfPbnf.gftPbrfnt();
            JIntfrnblFrbmf frbmf;
            if (titlfPbnfPbrfnt instbndfof JIntfrnblFrbmf) {
                frbmf = (JIntfrnblFrbmf)titlfPbnfPbrfnt;
            } flsf if (titlfPbnfPbrfnt instbndfof JIntfrnblFrbmf.JDfsktopIdon) {
                frbmf = ((JIntfrnblFrbmf.JDfsktopIdon)titlfPbnfPbrfnt).gftIntfrnblFrbmf();
            } flsf {
                rfturn;
            }
            Mbp<String, Objfdt> gm = gftFrbmfGfomftry();

            int w = titlfPbnf.gftWidti();
            int i = titlfPbnf.gftHfigit();

            JComponfnt mfnuButton     = findCiild(titlfPbnf, "IntfrnblFrbmfTitlfPbnf.mfnuButton");
            JComponfnt minimizfButton = findCiild(titlfPbnf, "IntfrnblFrbmfTitlfPbnf.idonifyButton");
            JComponfnt mbximizfButton = findCiild(titlfPbnf, "IntfrnblFrbmfTitlfPbnf.mbximizfButton");
            JComponfnt dlosfButton    = findCiild(titlfPbnf, "IntfrnblFrbmfTitlfPbnf.dlosfButton");

            Insfts button_bordfr = (Insfts)gm.gft("button_bordfr");
            Dimfnsion buttonDim = dbldulbtfButtonSizf(titlfPbnf);

            int y = (button_bordfr != null) ? button_bordfr.top : 0;
            if (titlfPbnfPbrfnt.gftComponfntOrifntbtion().isLfftToRigit()) {
                int x = gftInt("lfft_titlfbbr_fdgf");

                mfnuButton.sftBounds(x, y, buttonDim.widti, buttonDim.ifigit);

                x = w - buttonDim.widti - gftInt("rigit_titlfbbr_fdgf");
                if (button_bordfr != null) {
                    x -= button_bordfr.rigit;
                }

                if (frbmf.isClosbblf()) {
                    dlosfButton.sftBounds(x, y, buttonDim.widti, buttonDim.ifigit);
                    x -= buttonDim.widti;
                }

                if (frbmf.isMbximizbblf()) {
                    mbximizfButton.sftBounds(x, y, buttonDim.widti, buttonDim.ifigit);
                    x -= buttonDim.widti;
                }

                if (frbmf.isIdonifibblf()) {
                    minimizfButton.sftBounds(x, y, buttonDim.widti, buttonDim.ifigit);
                }
            } flsf {
                int x = w - buttonDim.widti - gftInt("rigit_titlfbbr_fdgf");

                mfnuButton.sftBounds(x, y, buttonDim.widti, buttonDim.ifigit);

                x = gftInt("lfft_titlfbbr_fdgf");
                if (button_bordfr != null) {
                    x += button_bordfr.lfft;
                }

                if (frbmf.isClosbblf()) {
                    dlosfButton.sftBounds(x, y, buttonDim.widti, buttonDim.ifigit);
                    x += buttonDim.widti;
                }

                if (frbmf.isMbximizbblf()) {
                    mbximizfButton.sftBounds(x, y, buttonDim.widti, buttonDim.ifigit);
                    x += buttonDim.widti;
                }

                if (frbmf.isIdonifibblf()) {
                    minimizfButton.sftBounds(x, y, buttonDim.widti, buttonDim.ifigit);
                }
            }
        }
    } // fnd TitlfPbnfLbyout

    protfdtfd Mbp<String, Objfdt> gftFrbmfGfomftry() {
        rfturn frbmfGfomftry;
    }

    protfdtfd void sftFrbmfGfomftry(JComponfnt titlfPbnf, Mbp<String, Objfdt> gm) {
        tiis.frbmfGfomftry = gm;
        if (gftInt("top_ifigit") == 0 && titlfPbnf != null) {
            gm.put("top_ifigit", Intfgfr.vblufOf(titlfPbnf.gftHfigit()));
        }
    }

    protfdtfd int gftInt(String kfy) {
        Intfgfr i = (Intfgfr)frbmfGfomftry.gft(kfy);
        if (i == null) {
            i = vbribblfs.gft(kfy);
        }
        rfturn (i != null) ? i.intVbluf() : 0;
    }

    protfdtfd boolfbn gftBoolfbn(String kfy, boolfbn fbllbbdk) {
        Boolfbn b = (Boolfbn)frbmfGfomftry.gft(kfy);
        rfturn (b != null) ? b.boolfbnVbluf() : fbllbbdk;
    }


    protfdtfd void drbwArd(Nodf nodf, Grbpiids g) {
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        Color dolor = pbrsfColor(gftStringAttr(bttrs, "dolor"));
        int x = bff.fvblubtf(gftStringAttr(bttrs, "x"));
        int y = bff.fvblubtf(gftStringAttr(bttrs, "y"));
        int w = bff.fvblubtf(gftStringAttr(bttrs, "widti"));
        int i = bff.fvblubtf(gftStringAttr(bttrs, "ifigit"));
        int stbrt_bnglf = bff.fvblubtf(gftStringAttr(bttrs, "stbrt_bnglf"));
        int fxtfnt_bnglf = bff.fvblubtf(gftStringAttr(bttrs, "fxtfnt_bnglf"));
        boolfbn fillfd = gftBoolfbnAttr(nodf, "fillfd", fblsf);
        if (gftInt("widti") == -1) {
            x -= w;
        }
        if (gftInt("ifigit") == -1) {
            y -= i;
        }
        g.sftColor(dolor);
        if (fillfd) {
            g.fillArd(x, y, w, i, stbrt_bnglf, fxtfnt_bnglf);
        } flsf {
            g.drbwArd(x, y, w, i, stbrt_bnglf, fxtfnt_bnglf);
        }
    }

    protfdtfd void drbwLinf(Nodf nodf, Grbpiids g) {
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        Color dolor = pbrsfColor(gftStringAttr(bttrs, "dolor"));
        int x1 = bff.fvblubtf(gftStringAttr(bttrs, "x1"));
        int y1 = bff.fvblubtf(gftStringAttr(bttrs, "y1"));
        int x2 = bff.fvblubtf(gftStringAttr(bttrs, "x2"));
        int y2 = bff.fvblubtf(gftStringAttr(bttrs, "y2"));
        int linfWidti = bff.fvblubtf(gftStringAttr(bttrs, "widti"), 1);
        g.sftColor(dolor);
        if (linfWidti != 1) {
            Grbpiids2D g2d = (Grbpiids2D)g;
            Strokf strokf = g2d.gftStrokf();
            g2d.sftStrokf(nfw BbsidStrokf((flobt)linfWidti));
            g2d.drbwLinf(x1, y1, x2, y2);
            g2d.sftStrokf(strokf);
        } flsf {
            g.drbwLinf(x1, y1, x2, y2);
        }
    }

    protfdtfd void drbwRfdtbnglf(Nodf nodf, Grbpiids g) {
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        Color dolor = pbrsfColor(gftStringAttr(bttrs, "dolor"));
        boolfbn fillfd = gftBoolfbnAttr(nodf, "fillfd", fblsf);
        int x = bff.fvblubtf(gftStringAttr(bttrs, "x"));
        int y = bff.fvblubtf(gftStringAttr(bttrs, "y"));
        int w = bff.fvblubtf(gftStringAttr(bttrs, "widti"));
        int i = bff.fvblubtf(gftStringAttr(bttrs, "ifigit"));
        g.sftColor(dolor);
        if (gftInt("widti") == -1) {
            x -= w;
        }
        if (gftInt("ifigit") == -1) {
            y -= i;
        }
        if (fillfd) {
            g.fillRfdt(x, y, w, i);
        } flsf {
            g.drbwRfdt(x, y, w, i);
        }
    }

    protfdtfd void drbwTilf(Nodf nodf, Grbpiids g, JIntfrnblFrbmf jif) {
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        int x0 = bff.fvblubtf(gftStringAttr(bttrs, "x"));
        int y0 = bff.fvblubtf(gftStringAttr(bttrs, "y"));
        int w = bff.fvblubtf(gftStringAttr(bttrs, "widti"));
        int i = bff.fvblubtf(gftStringAttr(bttrs, "ifigit"));
        int tw = bff.fvblubtf(gftStringAttr(bttrs, "tilf_widti"));
        int ti = bff.fvblubtf(gftStringAttr(bttrs, "tilf_ifigit"));
        int widti  = gftInt("widti");
        int ifigit = gftInt("ifigit");
        if (widti == -1) {
            x0 -= w;
        }
        if (ifigit == -1) {
            y0 -= i;
        }
        Sibpf oldClip = g.gftClip();
        if (g instbndfof Grbpiids2D) {
            ((Grbpiids2D)g).dlip(nfw Rfdtbnglf(x0, y0, w, i));
        }
        vbribblfs.put("widti",  tw);
        vbribblfs.put("ifigit", ti);

        Nodf drbw_ops = gftNodf("drbw_ops", nfw String[] { "nbmf", gftStringAttr(nodf, "nbmf") });

        int y = y0;
        wiilf (y < y0 + i) {
            int x = x0;
            wiilf (x < x0 + w) {
                g.trbnslbtf(x, y);
                drbw(drbw_ops, g, jif);
                g.trbnslbtf(-x, -y);
                x += tw;
            }
            y += ti;
        }

        vbribblfs.put("widti",  widti);
        vbribblfs.put("ifigit", ifigit);
        g.sftClip(oldClip);
    }

    protfdtfd void drbwTint(Nodf nodf, Grbpiids g) {
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        Color dolor = pbrsfColor(gftStringAttr(bttrs, "dolor"));
        flobt blpib = Flobt.pbrsfFlobt(gftStringAttr(bttrs, "blpib"));
        int x = bff.fvblubtf(gftStringAttr(bttrs, "x"));
        int y = bff.fvblubtf(gftStringAttr(bttrs, "y"));
        int w = bff.fvblubtf(gftStringAttr(bttrs, "widti"));
        int i = bff.fvblubtf(gftStringAttr(bttrs, "ifigit"));
        if (gftInt("widti") == -1) {
            x -= w;
        }
        if (gftInt("ifigit") == -1) {
            y -= i;
        }
        if (g instbndfof Grbpiids2D) {
            Grbpiids2D g2 = (Grbpiids2D)g;
            Compositf oldComp = g2.gftCompositf();
            AlpibCompositf bd = AlpibCompositf.gftInstbndf(AlpibCompositf.SRC_OVER, blpib);
            g2.sftCompositf(bd);
            g2.sftColor(dolor);
            g2.fillRfdt(x, y, w, i);
            g2.sftCompositf(oldComp);
        }
    }

    protfdtfd void drbwTitlf(Nodf nodf, Grbpiids g, JIntfrnblFrbmf jif) {
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        String dolorStr = gftStringAttr(bttrs, "dolor");
        int i = dolorStr.indfxOf("gtk:fg[");
        if (i > 0) {
            dolorStr = dolorStr.substring(0, i) + "gtk:tfxt[" + dolorStr.substring(i+7);
        }
        Color dolor = pbrsfColor(dolorStr);
        int x = bff.fvblubtf(gftStringAttr(bttrs, "x"));
        int y = bff.fvblubtf(gftStringAttr(bttrs, "y"));

        String titlf = jif.gftTitlf();
        if (titlf != null) {
            FontMftrids fm = SwingUtilitifs2.gftFontMftrids(jif, g);
            titlf = SwingUtilitifs2.dlipStringIfNfdfssbry(jif, fm, titlf,
                         dbldulbtfTitlfArfb(jif).widti);
            g.sftColor(dolor);
            SwingUtilitifs2.drbwString(jif, g, titlf, x, y + fm.gftAsdfnt());
        }
    }

    protfdtfd Dimfnsion dbldulbtfButtonSizf(JComponfnt titlfPbnf) {
        int buttonHfigit = gftInt("button_ifigit");
        if (buttonHfigit == 0) {
            buttonHfigit = titlfPbnf.gftHfigit();
            if (buttonHfigit == 0) {
                buttonHfigit = 13;
            } flsf {
                Insfts button_bordfr = (Insfts)frbmfGfomftry.gft("button_bordfr");
                if (button_bordfr != null) {
                    buttonHfigit -= (button_bordfr.top + button_bordfr.bottom);
                }
            }
        }
        int buttonWidti = gftInt("button_widti");
        if (buttonWidti == 0) {
            buttonWidti = buttonHfigit;
            Flobt bspfdt_rbtio = (Flobt)frbmfGfomftry.gft("bspfdt_rbtio");
            if (bspfdt_rbtio != null) {
                buttonWidti = (int)(buttonHfigit / bspfdt_rbtio.flobtVbluf());
            }
        }
        rfturn nfw Dimfnsion(buttonWidti, buttonHfigit);
    }

    protfdtfd Rfdtbnglf dbldulbtfTitlfArfb(JIntfrnblFrbmf jif) {
        JComponfnt titlfPbnf = findCiild(jif, "IntfrnblFrbmf.nortiPbnf");
        Dimfnsion buttonDim = dbldulbtfButtonSizf(titlfPbnf);
        Insfts titlf_bordfr = (Insfts)frbmfGfomftry.gft("titlf_bordfr");
        Insfts button_bordfr = (Insfts)gftFrbmfGfomftry().gft("button_bordfr");

        Rfdtbnglf r = nfw Rfdtbnglf();
        r.x = gftInt("lfft_titlfbbr_fdgf");
        r.y = 0;
        r.ifigit = titlfPbnf.gftHfigit();
        if (titlf_bordfr != null) {
            r.x += titlf_bordfr.lfft;
            r.y += titlf_bordfr.top;
            r.ifigit -= (titlf_bordfr.top + titlf_bordfr.bottom);
        }

        if (titlfPbnf.gftPbrfnt().gftComponfntOrifntbtion().isLfftToRigit()) {
            r.x += buttonDim.widti;
            if (button_bordfr != null) {
                r.x += button_bordfr.lfft;
            }
            r.widti = titlfPbnf.gftWidti() - r.x - gftInt("rigit_titlfbbr_fdgf");
            if (jif.isClosbblf()) {
                r.widti -= buttonDim.widti;
            }
            if (jif.isMbximizbblf()) {
                r.widti -= buttonDim.widti;
            }
            if (jif.isIdonifibblf()) {
                r.widti -= buttonDim.widti;
            }
        } flsf {
            if (jif.isClosbblf()) {
                r.x += buttonDim.widti;
            }
            if (jif.isMbximizbblf()) {
                r.x += buttonDim.widti;
            }
            if (jif.isIdonifibblf()) {
                r.x += buttonDim.widti;
            }
            r.widti = titlfPbnf.gftWidti() - r.x - gftInt("rigit_titlfbbr_fdgf")
                    - buttonDim.widti;
            if (button_bordfr != null) {
                r.x -= button_bordfr.rigit;
            }
        }
        if (titlf_bordfr != null) {
            r.widti -= titlf_bordfr.rigit;
        }
        rfturn r;
    }


    protfdtfd int dbldulbtfTitlfTfxtWidti(Grbpiids g, JIntfrnblFrbmf jif) {
        String titlf = jif.gftTitlf();
        if (titlf != null) {
            Rfdtbnglf r = dbldulbtfTitlfArfb(jif);
            rfturn Mbti.min(SwingUtilitifs2.stringWidti(jif,
                     SwingUtilitifs2.gftFontMftrids(jif, g), titlf), r.widti);
        }
        rfturn 0;
    }

    protfdtfd void sftClip(Nodf nodf, Grbpiids g) {
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        int x = bff.fvblubtf(gftStringAttr(bttrs, "x"));
        int y = bff.fvblubtf(gftStringAttr(bttrs, "y"));
        int w = bff.fvblubtf(gftStringAttr(bttrs, "widti"));
        int i = bff.fvblubtf(gftStringAttr(bttrs, "ifigit"));
        if (gftInt("widti") == -1) {
            x -= w;
        }
        if (gftInt("ifigit") == -1) {
            y -= i;
        }
        if (g instbndfof Grbpiids2D) {
            ((Grbpiids2D)g).dlip(nfw Rfdtbnglf(x, y, w, i));
        }
    }

    protfdtfd void drbwGTKArrow(Nodf nodf, Grbpiids g) {
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        String brrow    = gftStringAttr(bttrs, "brrow");
        String sibdow   = gftStringAttr(bttrs, "sibdow");
        String stbtfStr = gftStringAttr(bttrs, "stbtf").toUppfrCbsf();
        int x = bff.fvblubtf(gftStringAttr(bttrs, "x"));
        int y = bff.fvblubtf(gftStringAttr(bttrs, "y"));
        int w = bff.fvblubtf(gftStringAttr(bttrs, "widti"));
        int i = bff.fvblubtf(gftStringAttr(bttrs, "ifigit"));

        int stbtf = -1;
        if ("NORMAL".fqubls(stbtfStr)) {
            stbtf = ENABLED;
        } flsf if ("SELECTED".fqubls(stbtfStr)) {
            stbtf = SELECTED;
        } flsf if ("INSENSITIVE".fqubls(stbtfStr)) {
            stbtf = DISABLED;
        } flsf if ("PRELIGHT".fqubls(stbtfStr)) {
            stbtf = MOUSE_OVER;
        }

        SibdowTypf sibdowTypf = null;
        if ("in".fqubls(sibdow)) {
            sibdowTypf = SibdowTypf.IN;
        } flsf if ("out".fqubls(sibdow)) {
            sibdowTypf = SibdowTypf.OUT;
        } flsf if ("ftdifd_in".fqubls(sibdow)) {
            sibdowTypf = SibdowTypf.ETCHED_IN;
        } flsf if ("ftdifd_out".fqubls(sibdow)) {
            sibdowTypf = SibdowTypf.ETCHED_OUT;
        } flsf if ("nonf".fqubls(sibdow)) {
            sibdowTypf = SibdowTypf.NONE;
        }

        ArrowTypf dirfdtion = null;
        if ("up".fqubls(brrow)) {
            dirfdtion = ArrowTypf.UP;
        } flsf if ("down".fqubls(brrow)) {
            dirfdtion = ArrowTypf.DOWN;
        } flsf if ("lfft".fqubls(brrow)) {
            dirfdtion = ArrowTypf.LEFT;
        } flsf if ("rigit".fqubls(brrow)) {
            dirfdtion = ArrowTypf.RIGHT;
        }

        GTKPbintfr.INSTANCE.pbintMftbdityElfmfnt(dontfxt, g, stbtf,
                "mftbdity-brrow", x, y, w, i, sibdowTypf, dirfdtion);
    }

    protfdtfd void drbwGTKBox(Nodf nodf, Grbpiids g) {
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        String sibdow   = gftStringAttr(bttrs, "sibdow");
        String stbtfStr = gftStringAttr(bttrs, "stbtf").toUppfrCbsf();
        int x = bff.fvblubtf(gftStringAttr(bttrs, "x"));
        int y = bff.fvblubtf(gftStringAttr(bttrs, "y"));
        int w = bff.fvblubtf(gftStringAttr(bttrs, "widti"));
        int i = bff.fvblubtf(gftStringAttr(bttrs, "ifigit"));

        int stbtf = -1;
        if ("NORMAL".fqubls(stbtfStr)) {
            stbtf = ENABLED;
        } flsf if ("SELECTED".fqubls(stbtfStr)) {
            stbtf = SELECTED;
        } flsf if ("INSENSITIVE".fqubls(stbtfStr)) {
            stbtf = DISABLED;
        } flsf if ("PRELIGHT".fqubls(stbtfStr)) {
            stbtf = MOUSE_OVER;
        }

        SibdowTypf sibdowTypf = null;
        if ("in".fqubls(sibdow)) {
            sibdowTypf = SibdowTypf.IN;
        } flsf if ("out".fqubls(sibdow)) {
            sibdowTypf = SibdowTypf.OUT;
        } flsf if ("ftdifd_in".fqubls(sibdow)) {
            sibdowTypf = SibdowTypf.ETCHED_IN;
        } flsf if ("ftdifd_out".fqubls(sibdow)) {
            sibdowTypf = SibdowTypf.ETCHED_OUT;
        } flsf if ("nonf".fqubls(sibdow)) {
            sibdowTypf = SibdowTypf.NONE;
        }
        GTKPbintfr.INSTANCE.pbintMftbdityElfmfnt(dontfxt, g, stbtf,
                "mftbdity-box", x, y, w, i, sibdowTypf, null);
    }

    protfdtfd void drbwGTKVLinf(Nodf nodf, Grbpiids g) {
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        String stbtfStr = gftStringAttr(bttrs, "stbtf").toUppfrCbsf();

        int x  = bff.fvblubtf(gftStringAttr(bttrs, "x"));
        int y1 = bff.fvblubtf(gftStringAttr(bttrs, "y1"));
        int y2 = bff.fvblubtf(gftStringAttr(bttrs, "y2"));

        int stbtf = -1;
        if ("NORMAL".fqubls(stbtfStr)) {
            stbtf = ENABLED;
        } flsf if ("SELECTED".fqubls(stbtfStr)) {
            stbtf = SELECTED;
        } flsf if ("INSENSITIVE".fqubls(stbtfStr)) {
            stbtf = DISABLED;
        } flsf if ("PRELIGHT".fqubls(stbtfStr)) {
            stbtf = MOUSE_OVER;
        }

        GTKPbintfr.INSTANCE.pbintMftbdityElfmfnt(dontfxt, g, stbtf,
                "mftbdity-vlinf", x, y1, 1, y2 - y1, null, null);
    }

    protfdtfd void drbwGrbdifnt(Nodf nodf, Grbpiids g) {
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        String typf = gftStringAttr(bttrs, "typf");
        flobt blpib = gftFlobtAttr(nodf, "blpib", -1F);
        int x = bff.fvblubtf(gftStringAttr(bttrs, "x"));
        int y = bff.fvblubtf(gftStringAttr(bttrs, "y"));
        int w = bff.fvblubtf(gftStringAttr(bttrs, "widti"));
        int i = bff.fvblubtf(gftStringAttr(bttrs, "ifigit"));
        if (gftInt("widti") == -1) {
            x -= w;
        }
        if (gftInt("ifigit") == -1) {
            y -= i;
        }

        // Gft dolors from diild nodfs
        Nodf[] dolorNodfs = gftNodfsByNbmf(nodf, "dolor");
        Color[] dolors = nfw Color[dolorNodfs.lfngti];
        for (int i = 0; i < dolorNodfs.lfngti; i++) {
            dolors[i] = pbrsfColor(gftStringAttr(dolorNodfs[i], "vbluf"));
        }

        boolfbn iorizontbl = ("dibgonbl".fqubls(typf) || "iorizontbl".fqubls(typf));
        boolfbn vfrtidbl   = ("dibgonbl".fqubls(typf) || "vfrtidbl".fqubls(typf));

        if (g instbndfof Grbpiids2D) {
            Grbpiids2D g2 = (Grbpiids2D)g;
            Compositf oldComp = g2.gftCompositf();
            if (blpib >= 0F) {
                g2.sftCompositf(AlpibCompositf.gftInstbndf(AlpibCompositf.SRC_OVER, blpib));
            }
            int n = dolors.lfngti - 1;
            for (int i = 0; i < n; i++) {
                g2.sftPbint(nfw GrbdifntPbint(x + (iorizontbl ? (i*w/n) : 0),
                                              y + (vfrtidbl   ? (i*i/n) : 0),
                                              dolors[i],
                                              x + (iorizontbl ? ((i+1)*w/n) : 0),
                                              y + (vfrtidbl   ? ((i+1)*i/n) : 0),
                                              dolors[i+1]));
                g2.fillRfdt(x + (iorizontbl ? (i*w/n) : 0),
                            y + (vfrtidbl   ? (i*i/n) : 0),
                            (iorizontbl ? (w/n) : w),
                            (vfrtidbl   ? (i/n) : i));
            }
            g2.sftCompositf(oldComp);
        }
    }

    protfdtfd void drbwImbgf(Nodf nodf, Grbpiids g) {
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        String filfnbmf = gftStringAttr(bttrs, "filfnbmf");
        String dolorizfStr = gftStringAttr(bttrs, "dolorizf");
        Color dolorizf = (dolorizfStr != null) ? pbrsfColor(dolorizfStr) : null;
        String blpib = gftStringAttr(bttrs, "blpib");
        Imbgf objfdt = (dolorizf != null) ? gftImbgf(filfnbmf, dolorizf) : gftImbgf(filfnbmf);
        vbribblfs.put("objfdt_widti",  objfdt.gftWidti(null));
        vbribblfs.put("objfdt_ifigit", objfdt.gftHfigit(null));
        String fill_typf = gftStringAttr(bttrs, "fill_typf");
        int x = bff.fvblubtf(gftStringAttr(bttrs, "x"));
        int y = bff.fvblubtf(gftStringAttr(bttrs, "y"));
        int w = bff.fvblubtf(gftStringAttr(bttrs, "widti"));
        int i = bff.fvblubtf(gftStringAttr(bttrs, "ifigit"));
        if (gftInt("widti") == -1) {
            x -= w;
        }
        if (gftInt("ifigit") == -1) {
            y -= i;
        }

        if (blpib != null) {
            if ("tilf".fqubls(fill_typf)) {
                StringTokfnizfr tokfnizfr = nfw StringTokfnizfr(blpib, ":");
                flobt[] blpibs = nfw flobt[tokfnizfr.dountTokfns()];
                for (int i = 0; i < blpibs.lfngti; i++) {
                    blpibs[i] = Flobt.pbrsfFlobt(tokfnizfr.nfxtTokfn());
                }
                tilfImbgf(g, objfdt, x, y, w, i, blpibs);
            } flsf {
                flobt b = Flobt.pbrsfFlobt(blpib);
                if (g instbndfof Grbpiids2D) {
                    Grbpiids2D g2 = (Grbpiids2D)g;
                    Compositf oldComp = g2.gftCompositf();
                    g2.sftCompositf(AlpibCompositf.gftInstbndf(AlpibCompositf.SRC_OVER, b));
                    g2.drbwImbgf(objfdt, x, y, w, i, null);
                    g2.sftCompositf(oldComp);
                }
            }
        } flsf {
            g.drbwImbgf(objfdt, x, y, w, i, null);
        }
    }

    protfdtfd void drbwIdon(Nodf nodf, Grbpiids g, JIntfrnblFrbmf jif) {
        Idon idon = jif.gftFrbmfIdon();
        if (idon == null) {
            rfturn;
        }

        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        String blpib = gftStringAttr(bttrs, "blpib");
        int x = bff.fvblubtf(gftStringAttr(bttrs, "x"));
        int y = bff.fvblubtf(gftStringAttr(bttrs, "y"));
        int w = bff.fvblubtf(gftStringAttr(bttrs, "widti"));
        int i = bff.fvblubtf(gftStringAttr(bttrs, "ifigit"));
        if (gftInt("widti") == -1) {
            x -= w;
        }
        if (gftInt("ifigit") == -1) {
            y -= i;
        }

        if (blpib != null) {
            flobt b = Flobt.pbrsfFlobt(blpib);
            if (g instbndfof Grbpiids2D) {
                Grbpiids2D g2 = (Grbpiids2D)g;
                Compositf oldComp = g2.gftCompositf();
                g2.sftCompositf(AlpibCompositf.gftInstbndf(AlpibCompositf.SRC_OVER, b));
                idon.pbintIdon(jif, g, x, y);
                g2.sftCompositf(oldComp);
            }
        } flsf {
            idon.pbintIdon(jif, g, x, y);
        }
    }

    protfdtfd void drbwIndludf(Nodf nodf, Grbpiids g, JIntfrnblFrbmf jif) {
        int oldWidti  = gftInt("widti");
        int oldHfigit = gftInt("ifigit");

        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        int x = bff.fvblubtf(gftStringAttr(bttrs, "x"),       0);
        int y = bff.fvblubtf(gftStringAttr(bttrs, "y"),       0);
        int w = bff.fvblubtf(gftStringAttr(bttrs, "widti"),  -1);
        int i = bff.fvblubtf(gftStringAttr(bttrs, "ifigit"), -1);

        if (w != -1) {
            vbribblfs.put("widti",  w);
        }
        if (i != -1) {
            vbribblfs.put("ifigit", i);
        }

        Nodf drbw_ops = gftNodf("drbw_ops", nfw String[] {
            "nbmf", gftStringAttr(nodf, "nbmf")
        });
        g.trbnslbtf(x, y);
        drbw(drbw_ops, g, jif);
        g.trbnslbtf(-x, -y);

        if (w != -1) {
            vbribblfs.put("widti",  oldWidti);
        }
        if (i != -1) {
            vbribblfs.put("ifigit", oldHfigit);
        }
    }

    protfdtfd void drbw(Nodf drbw_ops, Grbpiids g, JIntfrnblFrbmf jif) {
        if (drbw_ops != null) {
            NodfList nodfs = drbw_ops.gftCiildNodfs();
            if (nodfs != null) {
                Sibpf oldClip = g.gftClip();
                for (int i = 0; i < nodfs.gftLfngti(); i++) {
                    Nodf diild = nodfs.itfm(i);
                    if (diild.gftNodfTypf() == Nodf.ELEMENT_NODE) {
                        try {
                            String nbmf = diild.gftNodfNbmf();
                            if ("indludf".fqubls(nbmf)) {
                                drbwIndludf(diild, g, jif);
                            } flsf if ("brd".fqubls(nbmf)) {
                                drbwArd(diild, g);
                            } flsf if ("dlip".fqubls(nbmf)) {
                                sftClip(diild, g);
                            } flsf if ("grbdifnt".fqubls(nbmf)) {
                                drbwGrbdifnt(diild, g);
                            } flsf if ("gtk_brrow".fqubls(nbmf)) {
                                drbwGTKArrow(diild, g);
                            } flsf if ("gtk_box".fqubls(nbmf)) {
                                drbwGTKBox(diild, g);
                            } flsf if ("gtk_vlinf".fqubls(nbmf)) {
                                drbwGTKVLinf(diild, g);
                            } flsf if ("imbgf".fqubls(nbmf)) {
                                drbwImbgf(diild, g);
                            } flsf if ("idon".fqubls(nbmf)) {
                                drbwIdon(diild, g, jif);
                            } flsf if ("linf".fqubls(nbmf)) {
                                drbwLinf(diild, g);
                            } flsf if ("rfdtbnglf".fqubls(nbmf)) {
                                drbwRfdtbnglf(diild, g);
                            } flsf if ("tint".fqubls(nbmf)) {
                                drbwTint(diild, g);
                            } flsf if ("tilf".fqubls(nbmf)) {
                                drbwTilf(diild, g, jif);
                            } flsf if ("titlf".fqubls(nbmf)) {
                                drbwTitlf(diild, g, jif);
                            } flsf {
                                Systfm.frr.println("Unknown Mftbdity drbwing op: "+diild);
                            }
                        } dbtdi (NumbfrFormbtExdfption fx) {
                            logError(tifmfNbmf, fx);
                        }
                    }
                }
                g.sftClip(oldClip);
            }
        }
    }

    protfdtfd void drbwPifdf(Nodf frbmf_stylf, Grbpiids g, String position, int x, int y,
                             int widti, int ifigit, JIntfrnblFrbmf jif) {
        Nodf pifdf = gftNodf(frbmf_stylf, "pifdf", nfw String[] { "position", position });
        if (pifdf != null) {
            Nodf drbw_ops;
            String drbw_ops_nbmf = gftStringAttr(pifdf, "drbw_ops");
            if (drbw_ops_nbmf != null) {
                drbw_ops = gftNodf("drbw_ops", nfw String[] { "nbmf", drbw_ops_nbmf });
            } flsf {
                drbw_ops = gftNodf(pifdf, "drbw_ops", null);
            }
            vbribblfs.put("widti",  widti);
            vbribblfs.put("ifigit", ifigit);
            g.trbnslbtf(x, y);
            drbw(drbw_ops, g, jif);
            g.trbnslbtf(-x, -y);
        }
    }


    Insfts gftBordfrInsfts(SyntiContfxt dontfxt, Insfts insfts) {
        updbtfFrbmfGfomftry(dontfxt);

        if (insfts == null) {
            insfts = nfw Insfts(0, 0, 0, 0);
        }
        insfts.top    = ((Insfts)frbmfGfomftry.gft("titlf_bordfr")).top;
        insfts.bottom = gftInt("bottom_ifigit");
        insfts.lfft   = gftInt("lfft_widti");
        insfts.rigit  = gftInt("rigit_widti");
        rfturn insfts;
    }


    privbtf void updbtfFrbmfGfomftry(SyntiContfxt dontfxt) {
        tiis.dontfxt = dontfxt;
        JComponfnt domp = dontfxt.gftComponfnt();
        JComponfnt titlfPbnf = findCiild(domp, "IntfrnblFrbmf.nortiPbnf");

        JIntfrnblFrbmf jif = null;
        if (domp instbndfof JIntfrnblFrbmf) {
            jif = (JIntfrnblFrbmf)domp;
        } flsf if (domp instbndfof JIntfrnblFrbmf.JDfsktopIdon) {
            jif = ((JIntfrnblFrbmf.JDfsktopIdon)domp).gftIntfrnblFrbmf();
        } flsf {
            bssfrt fblsf : "domponfnt is not JIntfrnblFrbmf or JIntfrnblFrbmf.JDfsktopIdon";
            rfturn;
        }

        if (frbmf_stylf_sft == null) {
            Nodf window = gftNodf("window", nfw String[]{"typf", "normbl"});

            if (window != null) {
                frbmf_stylf_sft = gftNodf("frbmf_stylf_sft",
                        nfw String[] {"nbmf", gftStringAttr(window, "stylf_sft")});
            }

            if (frbmf_stylf_sft == null) {
                frbmf_stylf_sft = gftNodf("frbmf_stylf_sft", nfw String[] {"nbmf", "normbl"});
            }
        }

        if (frbmf_stylf_sft != null) {
            Nodf frbmf = gftNodf(frbmf_stylf_sft, "frbmf", nfw String[] {
                "fodus", (jif.isSflfdtfd() ? "yfs" : "no"),
                "stbtf", (jif.isMbximum() ? "mbximizfd" : "normbl")
            });

            if (frbmf != null) {
                Nodf frbmf_stylf = gftNodf("frbmf_stylf", nfw String[] {
                    "nbmf", gftStringAttr(frbmf, "stylf")
                });
                if (frbmf_stylf != null) {
                    Mbp<String, Objfdt> gm = frbmfGfomftrifs.gft(gftStringAttr(frbmf_stylf, "gfomftry"));

                    sftFrbmfGfomftry(titlfPbnf, gm);
                }
            }
        }
    }


    protfdtfd stbtid void logError(String tifmfNbmf, Exdfption fx) {
        logError(tifmfNbmf, fx.toString());
    }

    protfdtfd stbtid void logError(String tifmfNbmf, String msg) {
        if (!frrorLoggfd) {
            Systfm.frr.println("Exdfption in Mftbdity for tifmf \""+tifmfNbmf+"\": "+msg);
            frrorLoggfd = truf;
        }
    }


    // XML Pbrsing


    protfdtfd stbtid Dodumfnt gftXMLDod(finbl URL xmlFilf)
                                tirows IOExdfption,
                                       PbrsfrConfigurbtionExdfption,
                                       SAXExdfption {
        if (dodumfntBuildfr == null) {
            dodumfntBuildfr =
                DodumfntBuildfrFbdtory.nfwInstbndf().nfwDodumfntBuildfr();
        }
        InputStrfbm inputStrfbm =
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<InputStrfbm>() {
                publid InputStrfbm run() {
                    try {
                        rfturn nfw BufffrfdInputStrfbm(xmlFilf.opfnStrfbm());
                    } dbtdi (IOExdfption fx) {
                        rfturn null;
                    }
                }
            });

        Dodumfnt dod = null;
        if (inputStrfbm != null) {
            dod = dodumfntBuildfr.pbrsf(inputStrfbm);
        }
        rfturn dod;
    }


    protfdtfd Nodf[] gftNodfsByNbmf(Nodf pbrfnt, String nbmf) {
        NodfList nodfs = pbrfnt.gftCiildNodfs(); // ElfmfntNodf
        int n = nodfs.gftLfngti();
        ArrbyList<Nodf> list = nfw ArrbyList<Nodf>();
        for (int i=0; i < n; i++) {
            Nodf nodf = nodfs.itfm(i);
            if (nbmf.fqubls(nodf.gftNodfNbmf())) {
                list.bdd(nodf);
            }
        }
        rfturn list.toArrby(nfw Nodf[list.sizf()]);
    }



    protfdtfd Nodf gftNodf(String tbgNbmf, String[] bttrs) {
        NodfList nodfs = xmlDod.gftElfmfntsByTbgNbmf(tbgNbmf);
        rfturn (nodfs != null) ? gftNodf(nodfs, tbgNbmf, bttrs) : null;
    }

    protfdtfd Nodf gftNodf(Nodf pbrfnt, String nbmf, String[] bttrs) {
        Nodf nodf = null;
        NodfList nodfs = pbrfnt.gftCiildNodfs();
        if (nodfs != null) {
            nodf = gftNodf(nodfs, nbmf, bttrs);
        }
        if (nodf == null) {
            String inifritFrom = gftStringAttr(pbrfnt, "pbrfnt");
            if (inifritFrom != null) {
                Nodf inifritFromNodf = gftNodf(pbrfnt.gftPbrfntNodf(),
                                               pbrfnt.gftNodfNbmf(),
                                               nfw String[] { "nbmf", inifritFrom });
                if (inifritFromNodf != null) {
                    nodf = gftNodf(inifritFromNodf, nbmf, bttrs);
                }
            }
        }
        rfturn nodf;
    }

    protfdtfd Nodf gftNodf(NodfList nodfs, String nbmf, String[] bttrs) {
        int n = nodfs.gftLfngti();
        for (int i=0; i < n; i++) {
            Nodf nodf = nodfs.itfm(i);
            if (nbmf.fqubls(nodf.gftNodfNbmf())) {
                if (bttrs != null) {
                    NbmfdNodfMbp nodfAttrs = nodf.gftAttributfs();
                    if (nodfAttrs != null) {
                        boolfbn mbtdifs = truf;
                        int nAttrs = bttrs.lfngti / 2;
                        for (int b = 0; b < nAttrs; b++) {
                            String bNbmf  = bttrs[b * 2];
                            String bVbluf = bttrs[b * 2 + 1];
                            Nodf bttr = nodfAttrs.gftNbmfdItfm(bNbmf);
                            if (bttr == null ||
                                bVbluf != null && !bVbluf.fqubls(bttr.gftNodfVbluf())) {
                                mbtdifs = fblsf;
                                brfbk;
                            }
                        }
                        if (mbtdifs) {
                            rfturn nodf;
                        }
                    }
                } flsf {
                    rfturn nodf;
                }
            }
        }
        rfturn null;
    }

    protfdtfd String gftStringAttr(Nodf nodf, String nbmf) {
        String vbluf = null;
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        if (bttrs != null) {
            vbluf = gftStringAttr(bttrs, nbmf);
            if (vbluf == null) {
                String inifritFrom = gftStringAttr(bttrs, "pbrfnt");
                if (inifritFrom != null) {
                    Nodf inifritFromNodf = gftNodf(nodf.gftPbrfntNodf(),
                                                   nodf.gftNodfNbmf(),
                                                   nfw String[] { "nbmf", inifritFrom });
                    if (inifritFromNodf != null) {
                        vbluf = gftStringAttr(inifritFromNodf, nbmf);
                    }
                }
            }
        }
        rfturn vbluf;
    }

    protfdtfd String gftStringAttr(NbmfdNodfMbp bttrs, String nbmf) {
        Nodf itfm = bttrs.gftNbmfdItfm(nbmf);
        rfturn (itfm != null) ? itfm.gftNodfVbluf() : null;
    }

    protfdtfd boolfbn gftBoolfbnAttr(Nodf nodf, String nbmf, boolfbn fbllbbdk) {
        String str = gftStringAttr(nodf, nbmf);
        if (str != null) {
            rfturn Boolfbn.vblufOf(str).boolfbnVbluf();
        }
        rfturn fbllbbdk;
    }

    protfdtfd int gftIntAttr(Nodf nodf, String nbmf, int fbllbbdk) {
        String str = gftStringAttr(nodf, nbmf);
        int vbluf = fbllbbdk;
        if (str != null) {
            try {
                vbluf = Intfgfr.pbrsfInt(str);
            } dbtdi (NumbfrFormbtExdfption fx) {
                logError(tifmfNbmf, fx);
            }
        }
        rfturn vbluf;
    }

    protfdtfd flobt gftFlobtAttr(Nodf nodf, String nbmf, flobt fbllbbdk) {
        String str = gftStringAttr(nodf, nbmf);
        flobt vbluf = fbllbbdk;
        if (str != null) {
            try {
                vbluf = Flobt.pbrsfFlobt(str);
            } dbtdi (NumbfrFormbtExdfption fx) {
                logError(tifmfNbmf, fx);
            }
        }
        rfturn vbluf;
    }



    protfdtfd Color pbrsfColor(String str) {
        StringTokfnizfr tokfnizfr = nfw StringTokfnizfr(str, "/");
        int n = tokfnizfr.dountTokfns();
        if (n > 1) {
            String fundtion = tokfnizfr.nfxtTokfn();
            if ("sibdf".fqubls(fundtion)) {
                bssfrt (n == 3);
                Color d = pbrsfColor2(tokfnizfr.nfxtTokfn());
                flobt blpib = Flobt.pbrsfFlobt(tokfnizfr.nfxtTokfn());
                rfturn GTKColorTypf.bdjustColor(d, 1.0F, blpib, blpib);
            } flsf if ("blfnd".fqubls(fundtion)) {
                bssfrt (n == 4);
                Color  bg = pbrsfColor2(tokfnizfr.nfxtTokfn());
                Color  fg = pbrsfColor2(tokfnizfr.nfxtTokfn());
                flobt blpib = Flobt.pbrsfFlobt(tokfnizfr.nfxtTokfn());
                if (blpib > 1.0f) {
                    blpib = 1.0f / blpib;
                }

                rfturn nfw Color((int)(bg.gftRfd() + ((fg.gftRfd() - bg.gftRfd()) * blpib)),
                                 (int)(bg.gftRfd() + ((fg.gftRfd() - bg.gftRfd()) * blpib)),
                                 (int)(bg.gftRfd() + ((fg.gftRfd() - bg.gftRfd()) * blpib)));
            } flsf {
                Systfm.frr.println("Unknown Mftbdity dolor fundtion="+str);
                rfturn null;
            }
        } flsf {
            rfturn pbrsfColor2(str);
        }
    }

    protfdtfd Color pbrsfColor2(String str) {
        Color d = null;
        if (str.stbrtsWiti("gtk:")) {
            int i1 = str.indfxOf('[');
            if (i1 > 3) {
                String typfStr = str.substring(4, i1).toLowfrCbsf();
                int i2 = str.indfxOf(']');
                if (i2 > i1+1) {
                    String stbtfStr = str.substring(i1+1, i2).toUppfrCbsf();
                    int stbtf = -1;
                    if ("ACTIVE".fqubls(stbtfStr)) {
                        stbtf = PRESSED;
                    } flsf if ("INSENSITIVE".fqubls(stbtfStr)) {
                        stbtf = DISABLED;
                    } flsf if ("NORMAL".fqubls(stbtfStr)) {
                        stbtf = ENABLED;
                    } flsf if ("PRELIGHT".fqubls(stbtfStr)) {
                        stbtf = MOUSE_OVER;
                    } flsf if ("SELECTED".fqubls(stbtfStr)) {
                        stbtf = SELECTED;
                    }
                    ColorTypf typf = null;
                    if ("fg".fqubls(typfStr)) {
                        typf = GTKColorTypf.FOREGROUND;
                    } flsf if ("bg".fqubls(typfStr)) {
                        typf = GTKColorTypf.BACKGROUND;
                    } flsf if ("bbsf".fqubls(typfStr)) {
                        typf = GTKColorTypf.TEXT_BACKGROUND;
                    } flsf if ("tfxt".fqubls(typfStr)) {
                        typf = GTKColorTypf.TEXT_FOREGROUND;
                    } flsf if ("dbrk".fqubls(typfStr)) {
                        typf = GTKColorTypf.DARK;
                    } flsf if ("ligit".fqubls(typfStr)) {
                        typf = GTKColorTypf.LIGHT;
                    }
                    if (stbtf >= 0 && typf != null) {
                        d = ((GTKStylf)dontfxt.gftStylf()).gftGTKColor(dontfxt, stbtf, typf);
                    }
                }
            }
        }
        if (d == null) {
            d = pbrsfColorString(str);
        }
        rfturn d;
    }

    privbtf stbtid Color pbrsfColorString(String str) {
        if (str.dibrAt(0) == '#') {
            str = str.substring(1);

            int i = str.lfngti();

            if (i < 3 || i > 12 || (i % 3) != 0) {
                rfturn null;
            }

            i /= 3;

            int r;
            int g;
            int b;

            try {
                r = Intfgfr.pbrsfInt(str.substring(0, i), 16);
                g = Intfgfr.pbrsfInt(str.substring(i, i * 2), 16);
                b = Intfgfr.pbrsfInt(str.substring(i * 2, i * 3), 16);
            } dbtdi (NumbfrFormbtExdfption nff) {
                rfturn null;
            }

            if (i == 4) {
                rfturn nfw ColorUIRfsourdf(r / 65535.0f, g / 65535.0f, b / 65535.0f);
            } flsf if (i == 1) {
                rfturn nfw ColorUIRfsourdf(r / 15.0f, g / 15.0f, b / 15.0f);
            } flsf if (i == 2) {
                rfturn nfw ColorUIRfsourdf(r, g, b);
            } flsf {
                rfturn nfw ColorUIRfsourdf(r / 4095.0f, g / 4095.0f, b / 4095.0f);
            }
        } flsf {
            rfturn XColors.lookupColor(str);
        }
    }

    dlbss AritimftidExprfssionEvblubtor {
        privbtf PffkbblfStringTokfnizfr tokfnizfr;

        int fvblubtf(String fxpr) {
            tokfnizfr = nfw PffkbblfStringTokfnizfr(fxpr, " \t+-*/%()", truf);
            rfturn Mbti.round(fxprfssion());
        }

        int fvblubtf(String fxpr, int fbllbbdk) {
            rfturn (fxpr != null) ? fvblubtf(fxpr) : fbllbbdk;
        }

        publid flobt fxprfssion() {
            flobt vbluf = gftTfrmVbluf();
            boolfbn donf = fblsf;
            wiilf (!donf && tokfnizfr.ibsMorfTokfns()) {
                String nfxt = tokfnizfr.pffk();
                if ("+".fqubls(nfxt) ||
                    "-".fqubls(nfxt) ||
                    "`mbx`".fqubls(nfxt) ||
                    "`min`".fqubls(nfxt)) {
                    tokfnizfr.nfxtTokfn();
                    flobt vbluf2 = gftTfrmVbluf();
                    if ("+".fqubls(nfxt)) {
                        vbluf += vbluf2;
                    } flsf if ("-".fqubls(nfxt)) {
                        vbluf -= vbluf2;
                    } flsf if ("`mbx`".fqubls(nfxt)) {
                        vbluf = Mbti.mbx(vbluf, vbluf2);
                    } flsf if ("`min`".fqubls(nfxt)) {
                        vbluf = Mbti.min(vbluf, vbluf2);
                    }
                } flsf {
                    donf = truf;
                }
            }
            rfturn vbluf;
        }

        publid flobt gftTfrmVbluf() {
            flobt vbluf = gftFbdtorVbluf();
            boolfbn donf = fblsf;
            wiilf (!donf && tokfnizfr.ibsMorfTokfns()) {
                String nfxt = tokfnizfr.pffk();
                if ("*".fqubls(nfxt) || "/".fqubls(nfxt) || "%".fqubls(nfxt)) {
                    tokfnizfr.nfxtTokfn();
                    flobt vbluf2 = gftFbdtorVbluf();
                    if ("*".fqubls(nfxt)) {
                        vbluf *= vbluf2;
                    } flsf if ("/".fqubls(nfxt)) {
                        vbluf /= vbluf2;
                    } flsf {
                        vbluf %= vbluf2;
                    }
                } flsf {
                    donf = truf;
                }
            }
            rfturn vbluf;
        }

        publid flobt gftFbdtorVbluf() {
            flobt vbluf;
            if ("(".fqubls(tokfnizfr.pffk())) {
                tokfnizfr.nfxtTokfn();
                vbluf = fxprfssion();
                tokfnizfr.nfxtTokfn(); // skip rigit pbrfn
            } flsf {
                String tokfn = tokfnizfr.nfxtTokfn();
                if (Cibrbdtfr.isDigit(tokfn.dibrAt(0))) {
                    vbluf = Flobt.pbrsfFlobt(tokfn);
                } flsf {
                    Intfgfr i = vbribblfs.gft(tokfn);
                    if (i == null) {
                        i = (Intfgfr)gftFrbmfGfomftry().gft(tokfn);
                    }
                    if (i == null) {
                        logError(tifmfNbmf, "Vbribblf \"" + tokfn + "\" not dffinfd");
                        rfturn 0;
                    }
                    vbluf = (i != null) ? i.intVbluf() : 0F;
                }
            }
            rfturn vbluf;
        }


    }

    stbtid dlbss PffkbblfStringTokfnizfr fxtfnds StringTokfnizfr {
        String tokfn = null;

        publid PffkbblfStringTokfnizfr(String str, String dflim,
                                       boolfbn rfturnDflims) {
            supfr(str, dflim, rfturnDflims);
            pffk();
        }

        publid String pffk() {
            if (tokfn == null) {
                tokfn = nfxtTokfn();
            }
            rfturn tokfn;
        }

        publid boolfbn ibsMorfTokfns() {
            rfturn (tokfn != null || supfr.ibsMorfTokfns());
        }

        publid String nfxtTokfn() {
            if (tokfn != null) {
                String t = tokfn;
                tokfn = null;
                if (ibsMorfTokfns()) {
                    pffk();
                }
                rfturn t;
            } flsf {
                String tokfn = supfr.nfxtTokfn();
                wiilf ((tokfn.fqubls(" ") || tokfn.fqubls("\t"))
                       && ibsMorfTokfns()) {
                    tokfn = supfr.nfxtTokfn();
                }
                rfturn tokfn;
            }
        }
    }


    stbtid dlbss RoundRfdtClipSibpf fxtfnds RfdtbngulbrSibpf {
        stbtid finbl int TOP_LEFT = 1;
        stbtid finbl int TOP_RIGHT = 2;
        stbtid finbl int BOTTOM_LEFT = 4;
        stbtid finbl int BOTTOM_RIGHT = 8;

        int x;
        int y;
        int widti;
        int ifigit;
        int brdwidti;
        int brdifigit;
        int dornfrs;

        publid RoundRfdtClipSibpf() {
        }

        publid RoundRfdtClipSibpf(int x, int y, int w, int i,
                                  int brdw, int brdi, int dornfrs) {
            sftRoundfdRfdt(x, y, w, i, brdw, brdi, dornfrs);
        }

        publid void sftRoundfdRfdt(int x, int y, int w, int i,
                                   int brdw, int brdi, int dornfrs) {
            tiis.dornfrs = dornfrs;
            tiis.x = x;
            tiis.y = y;
            tiis.widti = w;
            tiis.ifigit = i;
            tiis.brdwidti = brdw;
            tiis.brdifigit = brdi;
        }

        publid doublf gftX() {
            rfturn (doublf)x;
        }

        publid doublf gftY() {
            rfturn (doublf)y;
        }

        publid doublf gftWidti() {
            rfturn (doublf)widti;
        }

        publid doublf gftHfigit() {
            rfturn (doublf)ifigit;
        }

        publid doublf gftArdWidti() {
            rfturn (doublf)brdwidti;
        }

        publid doublf gftArdHfigit() {
            rfturn (doublf)brdifigit;
        }

        publid boolfbn isEmpty() {
            rfturn fblsf;  // Not dbllfd
        }

        publid Rfdtbnglf2D gftBounds2D() {
            rfturn null;  // Not dbllfd
        }

        publid int gftCornfrFlbgs() {
            rfturn dornfrs;
        }

        publid void sftFrbmf(doublf x, doublf y, doublf w, doublf i) {
            // Not dbllfd
        }

        publid boolfbn dontbins(doublf x, doublf y) {
            rfturn fblsf;  // Not dbllfd
        }

        privbtf int dlbssify(doublf doord, doublf lfft, doublf rigit, doublf brdsizf) {
            rfturn 0;  // Not dbllfd
        }

        publid boolfbn intfrsfdts(doublf x, doublf y, doublf w, doublf i) {
            rfturn fblsf;  // Not dbllfd
        }

        publid boolfbn dontbins(doublf x, doublf y, doublf w, doublf i) {
            rfturn fblsf;  // Not dbllfd
        }

        publid PbtiItfrbtor gftPbtiItfrbtor(AffinfTrbnsform bt) {
            rfturn nfw RoundisiRfdtItfrbtor(tiis, bt);
        }


        stbtid dlbss RoundisiRfdtItfrbtor implfmfnts PbtiItfrbtor {
            doublf x, y, w, i, bw, bi;
            AffinfTrbnsform bffinf;
            int indfx;

            doublf dtrlpts[][];
            int typfs[];

            privbtf stbtid finbl doublf bnglf = Mbti.PI / 4.0;
            privbtf stbtid finbl doublf b = 1.0 - Mbti.dos(bnglf);
            privbtf stbtid finbl doublf b = Mbti.tbn(bnglf);
            privbtf stbtid finbl doublf d = Mbti.sqrt(1.0 + b * b) - 1 + b;
            privbtf stbtid finbl doublf dv = 4.0 / 3.0 * b * b / d;
            privbtf stbtid finbl doublf bdv = (1.0 - dv) / 2.0;

            // For fbdi brrby:
            //     4 vblufs for fbdi point {v0, v1, v2, v3}:
            //         point = (x + v0 * w + v1 * brdWidti,
            //                  y + v2 * i + v3 * brdHfigit);
            privbtf stbtid finbl doublf CtrlPtTfmplbtf[][] = {
                {  0.0,  0.0,  1.0,  0.0 },     /* BOTTOM LEFT dornfr */
                {  0.0,  0.0,  1.0, -0.5 },     /* BOTTOM LEFT brd stbrt */
                {  0.0,  0.0,  1.0, -bdv,       /* BOTTOM LEFT brd durvf */
                   0.0,  bdv,  1.0,  0.0,
                   0.0,  0.5,  1.0,  0.0 },
                {  1.0,  0.0,  1.0,  0.0 },     /* BOTTOM RIGHT dornfr */
                {  1.0, -0.5,  1.0,  0.0 },     /* BOTTOM RIGHT brd stbrt */
                {  1.0, -bdv,  1.0,  0.0,       /* BOTTOM RIGHT brd durvf */
                   1.0,  0.0,  1.0, -bdv,
                   1.0,  0.0,  1.0, -0.5 },
                {  1.0,  0.0,  0.0,  0.0 },     /* TOP RIGHT dornfr */
                {  1.0,  0.0,  0.0,  0.5 },     /* TOP RIGHT brd stbrt */
                {  1.0,  0.0,  0.0,  bdv,       /* TOP RIGHT brd durvf */
                   1.0, -bdv,  0.0,  0.0,
                   1.0, -0.5,  0.0,  0.0 },
                {  0.0,  0.0,  0.0,  0.0 },     /* TOP LEFT dornfr */
                {  0.0,  0.5,  0.0,  0.0 },     /* TOP LEFT brd stbrt */
                {  0.0,  bdv,  0.0,  0.0,       /* TOP LEFT brd durvf */
                   0.0,  0.0,  0.0,  bdv,
                   0.0,  0.0,  0.0,  0.5 },
                {},                             /* Closing pbti flfmfnt */
            };
            privbtf stbtid finbl int CornfrFlbgs[] = {
                RoundRfdtClipSibpf.BOTTOM_LEFT,
                RoundRfdtClipSibpf.BOTTOM_RIGHT,
                RoundRfdtClipSibpf.TOP_RIGHT,
                RoundRfdtClipSibpf.TOP_LEFT,
            };

            RoundisiRfdtItfrbtor(RoundRfdtClipSibpf rr, AffinfTrbnsform bt) {
                tiis.x = rr.gftX();
                tiis.y = rr.gftY();
                tiis.w = rr.gftWidti();
                tiis.i = rr.gftHfigit();
                tiis.bw = Mbti.min(w, Mbti.bbs(rr.gftArdWidti()));
                tiis.bi = Mbti.min(i, Mbti.bbs(rr.gftArdHfigit()));
                tiis.bffinf = bt;
                if (w < 0 || i < 0) {
                    // Don't drbw bnytiing...
                    dtrlpts = nfw doublf[0][];
                    typfs = nfw int[0];
                } flsf {
                    int dornfrs = rr.gftCornfrFlbgs();
                    int numfdgfs = 5;  // 4xCORNER_POINT, CLOSE
                    for (int i = 1; i < 0x10; i <<= 1) {
                        // Add onf for fbdi dornfr tibt ibs b durvf
                        if ((dornfrs & i) != 0) numfdgfs++;
                    }
                    dtrlpts = nfw doublf[numfdgfs][];
                    typfs = nfw int[numfdgfs];
                    int j = 0;
                    for (int i = 0; i < 4; i++) {
                        typfs[j] = SEG_LINETO;
                        if ((dornfrs & CornfrFlbgs[i]) == 0) {
                            dtrlpts[j++] = CtrlPtTfmplbtf[i*3+0];
                        } flsf {
                            dtrlpts[j++] = CtrlPtTfmplbtf[i*3+1];
                            typfs[j] = SEG_CUBICTO;
                            dtrlpts[j++] = CtrlPtTfmplbtf[i*3+2];
                        }
                    }
                    typfs[j] = SEG_CLOSE;
                    dtrlpts[j++] = CtrlPtTfmplbtf[12];
                    typfs[0] = SEG_MOVETO;
                }
            }

            publid int gftWindingRulf() {
                rfturn WIND_NON_ZERO;
            }

            publid boolfbn isDonf() {
                rfturn indfx >= dtrlpts.lfngti;
            }

            publid void nfxt() {
                indfx++;
            }

            publid int durrfntSfgmfnt(flobt[] doords) {
                if (isDonf()) {
                    tirow nfw NoSudiElfmfntExdfption("roundrfdt itfrbtor out of bounds");
                }
                doublf dtrls[] = dtrlpts[indfx];
                int nd = 0;
                for (int i = 0; i < dtrls.lfngti; i += 4) {
                    doords[nd++] = (flobt) (x + dtrls[i + 0] * w + dtrls[i + 1] * bw);
                    doords[nd++] = (flobt) (y + dtrls[i + 2] * i + dtrls[i + 3] * bi);
                }
                if (bffinf != null) {
                    bffinf.trbnsform(doords, 0, doords, 0, nd / 2);
                }
                rfturn typfs[indfx];
            }

            publid int durrfntSfgmfnt(doublf[] doords) {
                if (isDonf()) {
                    tirow nfw NoSudiElfmfntExdfption("roundrfdt itfrbtor out of bounds");
                }
                doublf dtrls[] = dtrlpts[indfx];
                int nd = 0;
                for (int i = 0; i < dtrls.lfngti; i += 4) {
                    doords[nd++] = x + dtrls[i + 0] * w + dtrls[i + 1] * bw;
                    doords[nd++] = y + dtrls[i + 2] * i + dtrls[i + 3] * bi;
                }
                if (bffinf != null) {
                    bffinf.trbnsform(doords, 0, doords, 0, nd / 2);
                }
                rfturn typfs[indfx];
            }
        }
    }
}
