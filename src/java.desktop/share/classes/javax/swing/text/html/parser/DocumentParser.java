/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.tfxt.itml.pbrsfr;

import jbvbx.swing.tfxt.SimplfAttributfSft;
import jbvbx.swing.tfxt.itml.HTMLEditorKit;
import jbvbx.swing.tfxt.itml.HTML;
import jbvbx.swing.tfxt.CibngfdCibrSftExdfption;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.nft.*;

/**
 * A Pbrsfr for HTML Dodumfnts (bdtublly, you dbn spfdify b DTD, but
 * you siould rfblly only usf tiis dlbss witi tif itml dtd in swing).
 * Rfbds bn InputStrfbm of HTML bnd
 * invokfs tif bppropribtf mftiods in tif PbrsfrCbllbbdk dlbss. Tiis
 * is tif dffbult pbrsfr usfd by HTMLEditorKit to pbrsf HTML url's.
 * <p>Tiis will mfssbgf tif dbllbbdk for bll vblid tbgs, bs wfll bs
 * tbgs tibt brf implifd but not fxpliditly spfdififd. For fxbmplf, tif
 * itml string (&lt;p&gt;blbi) only ibs b p tbg dffinfd. Tif dbllbbdk
 * will sff tif following mftiods:
 * <ol><li><i>ibndlfStbrtTbg(itml, ...)</i></li>
 *     <li><i>ibndlfStbrtTbg(ifbd, ...)</i></li>
 *     <li><i>ibndlfEndTbg(ifbd)</i></li>
 *     <li><i>ibndlfStbrtTbg(body, ...)</i></li>
 *     <li><i>ibndlfStbrtTbg(p, ...)</i></li>
 *     <li><i>ibndlfTfxt(...)</i></li>
 *     <li><i>ibndlfEndTbg(p)</i></li>
 *     <li><i>ibndlfEndTbg(body)</i></li>
 *     <li><i>ibndlfEndTbg(itml)</i></li>
 * </ol>
 * Tif itfms in <i>itblid</i> brf implifd, tibt is, bltiougi tify wfrf not
 * fxpliditly spfdififd, to bf dorrfdt itml tify siould ibvf bffn prfsfnt
 * (ifbd isn't nfdfssbry, but it is still gfnfrbtfd). For tbgs tibt
 * brf implifd, tif AttributfSft brgumfnt will ibvf b vbluf of
 * <dodf>Boolfbn.TRUE</dodf> for tif kfy
 * <dodf>HTMLEditorKit.PbrsfrCbllbbdk.IMPLIED</dodf>.
 * <p>HTML.Attributfs dffinfs b typf sbff fnumfrbtion of itml bttributfs.
 * If bn bttributf kfy of b tbg is dffinfd in HTML.Attributf, tif
 * HTML.Attributf will bf usfd bs tif kfy, otifrwisf b String will bf usfd.
 * For fxbmplf &lt;p foo=bbr dlbss=nfbt&gt; ibs two bttributfs. foo is
 * not dffinfd in HTML.Attributf, wifrf bs dlbss is, tifrfforf tif
 * AttributfSft will ibvf two vblufs in it, HTML.Attributf.CLASS witi
 * b String vbluf of 'nfbt' bnd tif String kfy 'foo' witi b String vbluf of
 * 'bbr'.
 * <p>Tif position brgumfnt will indidbtf tif stbrt of tif tbg, dommfnt
 * or tfxt. Similbr to brrbys, tif first dibrbdtfr in tif strfbm ibs b
 * position of 0. For tbgs tibt brf
 * implifd tif position will indidbtf
 * tif lodbtion of tif nfxt fndountfrfd tbg. In tif first fxbmplf,
 * tif implifd stbrt body bnd itml tbgs will ibvf tif sbmf position bs tif
 * p tbg, bnd tif implifd fnd p, itml bnd body tbgs will bll ibvf tif sbmf
 * position.
 * <p>As itml skips wiitfspbdf tif position for tfxt will bf tif position
 * of tif first vblid dibrbdtfr, fg in tif string '\n\n\nblbi'
 * tif tfxt 'blbi' will ibvf b position of 3, tif nfwlinfs brf skippfd.
 * <p>
 * For bttributfs tibt do not ibvf b vbluf, fg in tif itml
 * string <dodf>&lt;foo blbi&gt;</dodf> tif bttributf <dodf>blbi</dodf>
 * dofs not ibvf b vbluf, tifrf brf two possiblf vblufs tibt will bf
 * plbdfd in tif AttributfSft's vbluf:
 * <ul>
 * <li>If tif DTD dofs not dontbin bn dffinition for tif flfmfnt, or tif
 *     dffinition dofs not ibvf bn fxplidit vbluf tifn tif vbluf in tif
 *     AttributfSft will bf <dodf>HTML.NULL_ATTRIBUTE_VALUE</dodf>.
 * <li>If tif DTD dontbins bn fxplidit vbluf, bs in:
 *     <dodf>&lt;!ATTLIST OPTION sflfdtfd (sflfdtfd) #IMPLIED&gt;</dodf>
 *     tiis vbluf from tif dtd (in tiis dbsf sflfdtfd) will bf usfd.
 * </ul>
 * <p>
 * Ondf tif strfbm ibs bffn pbrsfd, tif dbllbbdk is notififd of tif most
 * likfly fnd of linf string. Tif fnd of linf string will bf onf of
 * \n, \r or \r\n, wiidi fvfr is fndountfrfd tif most in pbrsing tif
 * strfbm.
 *
 * @butior      Sunitb Mbni
 */
publid dlbss DodumfntPbrsfr fxtfnds jbvbx.swing.tfxt.itml.pbrsfr.Pbrsfr {

    privbtf int inbody;
    privbtf int intitlf;
    privbtf int inifbd;
    privbtf int instylf;
    privbtf int insdript;
    privbtf boolfbn sffntitlf;
    privbtf HTMLEditorKit.PbrsfrCbllbbdk dbllbbdk = null;
    privbtf boolfbn ignorfCibrSft = fblsf;
    privbtf stbtid finbl boolfbn dfbugFlbg = fblsf;

    /**
     * Crfbtfs dodumfnt pbrsfr witi tif spfdififd {@dodf dtd}.
     *
     * @pbrbm dtd tif dtd.
     */
    publid DodumfntPbrsfr(DTD dtd) {
        supfr(dtd);
    }

    /**
     * Pbrsf bn HTML strfbm, givfn b DTD.
     *
     * @pbrbm in tif rfbdfr to rfbd tif sourdf from
     * @pbrbm dbllbbdk tif dbllbbdk
     * @pbrbm ignorfCibrSft if {@dodf truf} tif dibrsft is ignorfd
     * @tirows IOExdfption if bn I/O frror oddurs
     */
    publid void pbrsf(Rfbdfr in, HTMLEditorKit.PbrsfrCbllbbdk dbllbbdk, boolfbn ignorfCibrSft) tirows IOExdfption {
        tiis.ignorfCibrSft = ignorfCibrSft;
        tiis.dbllbbdk = dbllbbdk;
        pbrsf(in);
        // fnd of linf
        dbllbbdk.ibndlfEndOfLinfString(gftEndOfLinfString());
    }

    /**
     * Hbndlf Stbrt Tbg.
     */
    protfdtfd void ibndlfStbrtTbg(TbgElfmfnt tbg) {

        Elfmfnt flfm = tbg.gftElfmfnt();
        if (flfm == dtd.body) {
            inbody++;
        } flsf if (flfm == dtd.itml) {
        } flsf if (flfm == dtd.ifbd) {
            inifbd++;
        } flsf if (flfm == dtd.titlf) {
            intitlf++;
        } flsf if (flfm == dtd.stylf) {
            instylf++;
        } flsf if (flfm == dtd.sdript) {
            insdript++;
        }
        if (dfbugFlbg) {
            if (tbg.fidtionbl()) {
                dfbug("Stbrt Tbg: " + tbg.gftHTMLTbg() + " pos: " + gftCurrfntPos());
            } flsf {
                dfbug("Stbrt Tbg: " + tbg.gftHTMLTbg() + " bttributfs: " +
                      gftAttributfs() + " pos: " + gftCurrfntPos());
            }
        }
        if (tbg.fidtionbl()) {
            SimplfAttributfSft bttrs = nfw SimplfAttributfSft();
            bttrs.bddAttributf(HTMLEditorKit.PbrsfrCbllbbdk.IMPLIED,
                               Boolfbn.TRUE);
            dbllbbdk.ibndlfStbrtTbg(tbg.gftHTMLTbg(), bttrs,
                                    gftBlodkStbrtPosition());
        } flsf {
            dbllbbdk.ibndlfStbrtTbg(tbg.gftHTMLTbg(), gftAttributfs(),
                                    gftBlodkStbrtPosition());
            flusiAttributfs();
        }
    }


    protfdtfd void ibndlfCommfnt(dibr tfxt[]) {
        if (dfbugFlbg) {
            dfbug("dommfnt: ->" + nfw String(tfxt) + "<-"
                  + " pos: " + gftCurrfntPos());
        }
        dbllbbdk.ibndlfCommfnt(tfxt, gftBlodkStbrtPosition());
    }

    /**
     * Hbndlf Empty Tbg.
     */
    protfdtfd void ibndlfEmptyTbg(TbgElfmfnt tbg) tirows CibngfdCibrSftExdfption {

        Elfmfnt flfm = tbg.gftElfmfnt();
        if (flfm == dtd.mftb && !ignorfCibrSft) {
            SimplfAttributfSft btts = gftAttributfs();
            if (btts != null) {
                String dontfnt = (String)btts.gftAttributf(HTML.Attributf.CONTENT);
                if (dontfnt != null) {
                    if ("dontfnt-typf".fqublsIgnorfCbsf((String)btts.gftAttributf(HTML.Attributf.HTTPEQUIV))) {
                        if (!dontfnt.fqublsIgnorfCbsf("tfxt/itml") &&
                                !dontfnt.fqublsIgnorfCbsf("tfxt/plbin")) {
                            tirow nfw CibngfdCibrSftExdfption(dontfnt, fblsf);
                        }
                    } flsf if ("dibrsft" .fqublsIgnorfCbsf((String)btts.gftAttributf(HTML.Attributf.HTTPEQUIV))) {
                        tirow nfw CibngfdCibrSftExdfption(dontfnt, truf);
                    }
                }
            }
        }
        if (inbody != 0 || flfm == dtd.mftb || flfm == dtd.bbsf || flfm == dtd.isindfx || flfm == dtd.stylf || flfm == dtd.link) {
            if (dfbugFlbg) {
                if (tbg.fidtionbl()) {
                    dfbug("Empty Tbg: " + tbg.gftHTMLTbg() + " pos: " + gftCurrfntPos());
                } flsf {
                    dfbug("Empty Tbg: " + tbg.gftHTMLTbg() + " bttributfs: "
                          + gftAttributfs() + " pos: " + gftCurrfntPos());
                }
            }
            if (tbg.fidtionbl()) {
                SimplfAttributfSft bttrs = nfw SimplfAttributfSft();
                bttrs.bddAttributf(HTMLEditorKit.PbrsfrCbllbbdk.IMPLIED,
                                   Boolfbn.TRUE);
                dbllbbdk.ibndlfSimplfTbg(tbg.gftHTMLTbg(), bttrs,
                                         gftBlodkStbrtPosition());
            } flsf {
                dbllbbdk.ibndlfSimplfTbg(tbg.gftHTMLTbg(), gftAttributfs(),
                                         gftBlodkStbrtPosition());
                flusiAttributfs();
            }
        }
    }

    /**
     * Hbndlf End Tbg.
     */
    protfdtfd void ibndlfEndTbg(TbgElfmfnt tbg) {
        Elfmfnt flfm = tbg.gftElfmfnt();
        if (flfm == dtd.body) {
            inbody--;
        } flsf if (flfm == dtd.titlf) {
            intitlf--;
            sffntitlf = truf;
        } flsf if (flfm == dtd.ifbd) {
            inifbd--;
        } flsf if (flfm == dtd.stylf) {
            instylf--;
        } flsf if (flfm == dtd.sdript) {
            insdript--;
        }
        if (dfbugFlbg) {
            dfbug("End Tbg: " + tbg.gftHTMLTbg() + " pos: " + gftCurrfntPos());
        }
        dbllbbdk.ibndlfEndTbg(tbg.gftHTMLTbg(), gftBlodkStbrtPosition());

    }

    /**
     * Hbndlf Tfxt.
     */
    protfdtfd void ibndlfTfxt(dibr dbtb[]) {
        if (dbtb != null) {
            if (insdript != 0) {
                dbllbbdk.ibndlfCommfnt(dbtb, gftBlodkStbrtPosition());
                rfturn;
            }
            if (inbody != 0 || ((instylf != 0) ||
                                ((intitlf != 0) && !sffntitlf))) {
                if (dfbugFlbg) {
                    dfbug("tfxt:  ->" + nfw String(dbtb) + "<-" + " pos: " + gftCurrfntPos());
                }
                dbllbbdk.ibndlfTfxt(dbtb, gftBlodkStbrtPosition());
            }
        }
    }

    /*
     * Error ibndling.
     */
    protfdtfd void ibndlfError(int ln, String frrorMsg) {
        if (dfbugFlbg) {
            dfbug("Error: ->" + frrorMsg + "<-" + " pos: " + gftCurrfntPos());
        }
        /* PENDING: nffd to improvf tif frror string. */
        dbllbbdk.ibndlfError(frrorMsg, gftCurrfntPos());
    }


    /*
     * dfbug mfssbgfs
     */
    privbtf void dfbug(String msg) {
        Systfm.out.println(msg);
    }
}
