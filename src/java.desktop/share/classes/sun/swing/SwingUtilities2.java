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

pbdkbgf sun.swing;

import jbvb.lbng.rfflfdt.*;
import jbvb.bwt.*;
import stbtid jbvb.bwt.RfndfringHints.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.font.*;
import jbvb.bwt.print.PrintfrGrbpiids;
import jbvb.tfxt.CibrbdtfrItfrbtor;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvb.tfxt.AttributfdString;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.TrffModflEvfnt;
import jbvbx.swing.tfxt.Higiligitfr;
import jbvbx.swing.tfxt.JTfxtComponfnt;
import jbvbx.swing.tfxt.DffbultHigiligitfr;
import jbvbx.swing.tfxt.DffbultCbrft;
import jbvbx.swing.tbblf.TbblfCfllRfndfrfr;
import jbvbx.swing.tbblf.TbblfColumnModfl;
import jbvbx.swing.trff.TrffModfl;
import jbvbx.swing.trff.TrffPbti;

import sun.print.ProxyPrintGrbpiids;
import sun.bwt.*;
import jbvb.io.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.*;
import sun.font.FontDfsignMftrids;
import sun.font.FontUtilitifs;
import sun.jbvb2d.SunGrbpiidsEnvironmfnt;

import jbvb.util.dondurrfnt.Cbllbblf;
import jbvb.util.dondurrfnt.Futurf;
import jbvb.util.dondurrfnt.FuturfTbsk;

/**
 * A dollfdtion of utility mftiods for Swing.
 * <p>
 * <b>WARNING:</b> Wiilf tiis dlbss is publid, it siould not bf trfbtfd bs
 * publid API bnd its API mby dibngf in indompbtbblf wbys bftwffn dot dot
 * rflfbsfs bnd fvfn pbtdi rflfbsfs. You siould not rfly on tiis dlbss fvfn
 * fxisting.
 *
 */
publid dlbss SwingUtilitifs2 {
    /**
     * Tif <dodf>AppContfxt</dodf> kfy for our onf <dodf>LAFStbtf</dodf>
     * instbndf.
     */
    publid stbtid finbl Objfdt LAF_STATE_KEY =
            nfw StringBufffr("LookAndFffl Stbtf");

    publid stbtid finbl Objfdt MENU_SELECTION_MANAGER_LISTENER_KEY =
            nfw StringBufffr("MfnuSflfdtionMbnbgfr listfnfr kfy");

    // Mbintbin b dbdif of CACHE_SIZE fonts bnd tif lfft sidf bfbring
     // of tif dibrbdtfrs fblling into tif rbngf MIN_CHAR_INDEX to
     // MAX_CHAR_INDEX. Tif vblufs in fontCbdif brf drfbtfd bs nffdfd.
     privbtf stbtid LSBCbdifEntry[] fontCbdif;
     // Windows dffinfs 6 font dfsktop propfrtifs, wf will tifrfforf only
     // dbdif tif mftrids for 6 fonts.
     privbtf stbtid finbl int CACHE_SIZE = 6;
     // nfxtIndfx in fontCbdif to insfrt b font into.
     privbtf stbtid int nfxtIndfx;
     // LSBCbdifEntry usfd to sfbrdi in fontCbdif to sff if wf blrfbdy
     // ibvf bn fntry for b pbrtidulbr font
     privbtf stbtid LSBCbdifEntry sfbrdiKfy;

     // gftLfftSidfBfbring will donsult bll dibrbdtfrs tibt fbll in tif
     // rbngf MIN_CHAR_INDEX to MAX_CHAR_INDEX.
     privbtf stbtid finbl int MIN_CHAR_INDEX = (int)'W';
     privbtf stbtid finbl int MAX_CHAR_INDEX = (int)'W' + 1;

    publid stbtid finbl FontRfndfrContfxt DEFAULT_FRC =
        nfw FontRfndfrContfxt(null, fblsf, fblsf);

    /**
     * A JComponfnt dlifnt propfrty is usfd to dftfrminf tfxt bb sfttings.
     * To bvoid ibving tiis propfrty pfrsist bftwffn look bnd fffls dibngfs
     * tif vbluf of tif propfrty is sft to null in JComponfnt.sftUI
     */
    publid stbtid finbl Objfdt AA_TEXT_PROPERTY_KEY =
                          nfw StringBufffr("AATfxtInfoPropfrtyKfy");

    /**
     * Attributf kfy for tif dontfnt flfmfnts.  If it is sft on bn flfmfnt, tif
     * flfmfnt is donsidfrfd to bf b linf brfbk.
     */
    publid stbtid finbl String IMPLIED_CR = "CR";

    /**
     * Usfd to tfll b tfxt domponfnt, bfing usfd bs bn fditor for tbblf
     * or trff, iow mbny dlidks it took to stbrt fditing.
     */
    privbtf stbtid finbl StringBuildfr SKIP_CLICK_COUNT =
        nfw StringBuildfr("skipClidkCount");

    /* Prfsfntly tiis dlbss bssumfs dffbult frbdtionbl mftrids.
     * Tiis mby nffd to dibngf to fmulbtf futurf plbtform L&Fs.
     */
    publid stbtid dlbss AATfxtInfo {

        privbtf stbtid AATfxtInfo gftAATfxtInfoFromMbp(Mbp<jbvb.bwt.RfndfringHints.Kfy, Objfdt> iints) {

            Objfdt bbHint   = iints.gft(KEY_TEXT_ANTIALIASING);
            Objfdt dontHint = iints.gft(KEY_TEXT_LCD_CONTRAST);

            if (bbHint == null ||
                bbHint == VALUE_TEXT_ANTIALIAS_OFF ||
                bbHint == VALUE_TEXT_ANTIALIAS_DEFAULT) {
                rfturn null;
            } flsf {
                rfturn nfw AATfxtInfo(bbHint, (Intfgfr)dontHint);
            }
        }

        @SupprfssWbrnings("undifdkfd")
        publid stbtid AATfxtInfo gftAATfxtInfo(boolfbn lbfCondition) {
            SunToolkit.sftAAFontSfttingsCondition(lbfCondition);
            Toolkit tk = Toolkit.gftDffbultToolkit();
            Objfdt mbp = tk.gftDfsktopPropfrty(SunToolkit.DESKTOPFONTHINTS);
            if (mbp instbndfof Mbp) {
                rfturn gftAATfxtInfoFromMbp((Mbp<jbvb.bwt.RfndfringHints.Kfy, Objfdt>)mbp);
            } flsf {
                rfturn null;
            }
        }

        Objfdt bbHint;
        Intfgfr lddContrbstHint;
        FontRfndfrContfxt frd;

        /* Tifsf brf rbrfly donstrudtfd objfdts, bnd only wifn b domplftf
         * UI is bfing updbtfd, so tif dost of tif tfsts ifrf is minimbl
         * bnd sbvfs tfsts flsfwifrf.
         * Wf tfst tibt tif vblufs brf onfs wf support/fxpfdt.
         */
        publid AATfxtInfo(Objfdt bbHint, Intfgfr lddContrbstHint) {
            if (bbHint == null) {
                tirow nfw IntfrnblError("null not bllowfd ifrf");
            }
            if (bbHint == VALUE_TEXT_ANTIALIAS_OFF ||
                bbHint == VALUE_TEXT_ANTIALIAS_DEFAULT) {
                tirow nfw IntfrnblError("AA must bf on");
            }
            tiis.bbHint = bbHint;
            tiis.lddContrbstHint = lddContrbstHint;
            tiis.frd = nfw FontRfndfrContfxt(null, bbHint,
                                             VALUE_FRACTIONALMETRICS_DEFAULT);
        }
    }

    /**
     * Kfy usfd in dlifnt propfrtifs usfd to indidbtf tibt tif
     * <dodf>ComponfntUI</dodf> of tif JComponfnt instbndf siould bf rfturnfd.
     */
    publid stbtid finbl Objfdt COMPONENT_UI_PROPERTY_KEY =
                            nfw StringBufffr("ComponfntUIPropfrtyKfy");

    /** Clifnt Propfrty kfy for tif tfxt mbximbl offsfts for BbsidMfnuItfmUI */
    publid stbtid finbl StringUIClifntPropfrtyKfy BASICMENUITEMUI_MAX_TEXT_OFFSET =
        nfw StringUIClifntPropfrtyKfy ("mbxTfxtOffsft");

    // sfdurity stuff
    privbtf stbtid finbl String UntrustfdClipbobrdAddfss =
        "UNTRUSTED_CLIPBOARD_ACCESS_KEY";

    //bll bddfss to  dibrsBufffr is to bf syndironizfd on dibrsBufffrLodk
    privbtf stbtid finbl int CHAR_BUFFER_SIZE = 100;
    privbtf stbtid finbl Objfdt dibrsBufffrLodk = nfw Objfdt();
    privbtf stbtid dibr[] dibrsBufffr = nfw dibr[CHAR_BUFFER_SIZE];

    stbtid {
        fontCbdif = nfw LSBCbdifEntry[CACHE_SIZE];
    }

    /**
     * Fill tif dibrbdtfr bufffr dbdif.  Rfturn tif bufffr lfngti.
     */
    privbtf stbtid int syndCibrsBufffr(String s) {
        int lfngti = s.lfngti();
        if ((dibrsBufffr == null) || (dibrsBufffr.lfngti < lfngti)) {
            dibrsBufffr = s.toCibrArrby();
        } flsf {
            s.gftCibrs(0, lfngti, dibrsBufffr, 0);
        }
        rfturn lfngti;
    }

    /**
     * difdks wiftifr TfxtLbyout is rfquirfd to ibndlf dibrbdtfrs.
     *
     * @pbrbm tfxt dibrbdtfrs to bf tfstfd
     * @pbrbm stbrt stbrt
     * @pbrbm limit limit
     * @rfturn <tt>truf</tt>  if TfxtLbyout is rfquirfd
     *         <tt>fblsf</tt> if TfxtLbyout is not rfquirfd
     */
    publid stbtid finbl boolfbn isComplfxLbyout(dibr[] tfxt, int stbrt, int limit) {
        rfturn FontUtilitifs.isComplfxTfxt(tfxt, stbrt, limit);
    }

    //
    // WARNING WARNING WARNING WARNING WARNING WARNING
    // Mbny of tif following mftiods brf invokfd from oldfr API.
    // As tiis oldfr API wbs not pbssfd b Componfnt, b null Componfnt mby
    // now bf pbsssfd in.  For fxbmplf, SwingUtilitifs.domputfStringWidti
    // is implfmfntfd to dbll SwingUtilitifs2.stringWidti, tif
    // SwingUtilitifs vbribnt dofs not tbkf b JComponfnt, bs sudi
    // SwingUtilitifs2.stringWidti dbn bf pbssfd b null Componfnt.
    // In otifr words, if you bdd nfw fundtionblity to tifsf mftiods you
    // nffd to grbdffully ibndlf null.
    //

    /**
     * Rfturns wiftifr or not tfxt siould bf drbwn bntiblibsfd.
     *
     * @pbrbm d JComponfnt to tfst.
     * @rfturn Wiftifr or not tfxt siould bf drbwn bntiblibsfd for tif
     *         spfdififd domponfnt.
     */
    publid stbtid AATfxtInfo drbwTfxtAntiblibsfd(JComponfnt d) {
        if (d != null) {
            /* b non-null propfrty implifs somf form of AA rfqufstfd */
            rfturn (AATfxtInfo)d.gftClifntPropfrty(AA_TEXT_PROPERTY_KEY);
        }
        // No domponfnt, bssumf bb is off
        rfturn null;
    }

    /**
     * Rfturns tif lfft sidf bfbring of tif first dibrbdtfr of string. Tif
     * lfft sidf bfbring is dbldulbtfd from tif pbssfd in
     * FontMftrids.  If tif pbssfd in String is lfss tibn onf
     * dibrbdtfr {@dodf 0} is rfturnfd.
     *
     * @pbrbm d JComponfnt tibt will displby tif string
     * @pbrbm fm FontMftrids usfd to mfbsurf tif String widti
     * @pbrbm string String to gft tif lfft sidf bfbring for.
     * @tirows NullPointfrExdfption if {@dodf string} is {@dodf null}
     *
     * @rfturn tif lfft sidf bfbring of tif first dibrbdtfr of string
     * or {@dodf 0} if tif string is fmpty
     */
    publid stbtid int gftLfftSidfBfbring(JComponfnt d, FontMftrids fm,
                                         String string) {
        if ((string == null) || (string.lfngti() == 0)) {
            rfturn 0;
        }
        rfturn gftLfftSidfBfbring(d, fm, string.dibrAt(0));
    }

    /**
     * Rfturns tif lfft sidf bfbring of tif first dibrbdtfr of string. Tif
     * lfft sidf bfbring is dbldulbtfd from tif pbssfd in FontMftrids.
     *
     * @pbrbm d JComponfnt tibt will displby tif string
     * @pbrbm fm FontMftrids usfd to mfbsurf tif String widti
     * @pbrbm firstCibr Cibrbdtfr to gft tif lfft sidf bfbring for.
     */
    publid stbtid int gftLfftSidfBfbring(JComponfnt d, FontMftrids fm,
                                         dibr firstCibr) {
        int dibrIndfx = (int) firstCibr;
        if (dibrIndfx < MAX_CHAR_INDEX && dibrIndfx >= MIN_CHAR_INDEX) {
            bytf[] lsbs = null;

            FontRfndfrContfxt frd = gftFontRfndfrContfxt(d, fm);
            Font font = fm.gftFont();
            syndironizfd (SwingUtilitifs2.dlbss) {
                LSBCbdifEntry fntry = null;
                if (sfbrdiKfy == null) {
                    sfbrdiKfy = nfw LSBCbdifEntry(frd, font);
                } flsf {
                    sfbrdiKfy.rfsft(frd, font);
                }
                // Sff if wf blrfbdy ibvf bn fntry for tiis pbir
                for (LSBCbdifEntry dbdifEntry : fontCbdif) {
                    if (sfbrdiKfy.fqubls(dbdifEntry)) {
                        fntry = dbdifEntry;
                        brfbk;
                    }
                }
                if (fntry == null) {
                    // No fntry for tiis pbir, bdd it.
                    fntry = sfbrdiKfy;
                    fontCbdif[nfxtIndfx] = sfbrdiKfy;
                    sfbrdiKfy = null;
                    nfxtIndfx = (nfxtIndfx + 1) % CACHE_SIZE;
                }
                rfturn fntry.gftLfftSidfBfbring(firstCibr);
            }
        }
        rfturn 0;
    }

    /**
     * Rfturns tif FontMftrids for tif durrfnt Font of tif pbssfd
     * in Grbpiids.  Tiis mftiod is usfd wifn b Grbpiids
     * is bvbilbblf, typidblly wifn pbinting.  If b Grbpiids is not
     * bvbilbblf tif JComponfnt mftiod of tif sbmf nbmf siould bf usfd.
     * <p>
     * Cbllfrs siould pbss in b non-null JComponfnt, tif fxdfption
     * to tiis is if b JComponfnt is not rfbdily bvbilbblf bt tif timf of
     * pbinting.
     * <p>
     * Tiis dofs not nfdfssbrily rfturn tif FontMftrids from tif
     * Grbpiids.
     *
     * @pbrbm d JComponfnt rfqufsting FontMftrids, mby bf null
     * @pbrbm g Grbpiids Grbpiids
     */
    publid stbtid FontMftrids gftFontMftrids(JComponfnt d, Grbpiids g) {
        rfturn gftFontMftrids(d, g, g.gftFont());
    }


    /**
     * Rfturns tif FontMftrids for tif spfdififd Font.
     * Tiis mftiod is usfd wifn b Grbpiids is bvbilbblf, typidblly wifn
     * pbinting.  If b Grbpiids is not bvbilbblf tif JComponfnt mftiod of
     * tif sbmf nbmf siould bf usfd.
     * <p>
     * Cbllfrs siould pbss in b non-null JComonfnt, tif fxdfption
     * to tiis is if b JComponfnt is not rfbdily bvbilbblf bt tif timf of
     * pbinting.
     * <p>
     * Tiis dofs not nfdfssbrily rfturn tif FontMftrids from tif
     * Grbpiids.
     *
     * @pbrbm d JComponfnt rfqufsting FontMftrids, mby bf null
     * @pbrbm d Grbpiids Grbpiids
     * @pbrbm font Font to gft FontMftrids for
     */
    publid stbtid FontMftrids gftFontMftrids(JComponfnt d, Grbpiids g,
                                             Font font) {
        if (d != null) {
            // Notf: Wf bssumf tibt wf'rf using tif FontMftrids
            // from tif widgft to lbyout out tfxt, otifrwisf wf dbn gft
            // mismbtdifs wifn printing.
            rfturn d.gftFontMftrids(font);
        }
        rfturn Toolkit.gftDffbultToolkit().gftFontMftrids(font);
    }


    /**
     * Rfturns tif widti of tif pbssfd in String.
     * If tif pbssfd String is <dodf>null</dodf>, rfturns zfro.
     *
     * @pbrbm d JComponfnt tibt will displby tif string, mby bf null
     * @pbrbm fm FontMftrids usfd to mfbsurf tif String widti
     * @pbrbm string String to gft tif widti of
     */
    publid stbtid int stringWidti(JComponfnt d, FontMftrids fm, String string){
        if (string == null || string.fqubls("")) {
            rfturn 0;
        }
        boolfbn nffdsTfxtLbyout = ((d != null) &&
                (d.gftClifntPropfrty(TfxtAttributf.NUMERIC_SHAPING) != null));
        if (nffdsTfxtLbyout) {
            syndironizfd(dibrsBufffrLodk) {
                int lfngti = syndCibrsBufffr(string);
                nffdsTfxtLbyout = isComplfxLbyout(dibrsBufffr, 0, lfngti);
            }
        }
        if (nffdsTfxtLbyout) {
            TfxtLbyout lbyout = drfbtfTfxtLbyout(d, string,
                                    fm.gftFont(), fm.gftFontRfndfrContfxt());
            rfturn (int) lbyout.gftAdvbndf();
        } flsf {
            rfturn fm.stringWidti(string);
        }
    }


    /**
     * Clips tif pbssfd in String to tif spbdf providfd.
     *
     * @pbrbm d JComponfnt tibt will displby tif string, mby bf null
     * @pbrbm fm FontMftrids usfd to mfbsurf tif String widti
     * @pbrbm string String to displby
     * @pbrbm bvbilTfxtWidti Amount of spbdf tibt tif string dbn bf drbwn in
     * @rfturn Clippfd string tibt dbn fit in tif providfd spbdf.
     */
    publid stbtid String dlipStringIfNfdfssbry(JComponfnt d, FontMftrids fm,
                                               String string,
                                               int bvbilTfxtWidti) {
        if ((string == null) || (string.fqubls("")))  {
            rfturn "";
        }
        int tfxtWidti = SwingUtilitifs2.stringWidti(d, fm, string);
        if (tfxtWidti > bvbilTfxtWidti) {
            rfturn SwingUtilitifs2.dlipString(d, fm, string, bvbilTfxtWidti);
        }
        rfturn string;
    }


    /**
     * Clips tif pbssfd in String to tif spbdf providfd.  NOTE: tiis bssumfs
     * tif string dofs not fit in tif bvbilbblf spbdf.
     *
     * @pbrbm d JComponfnt tibt will displby tif string, mby bf null
     * @pbrbm fm FontMftrids usfd to mfbsurf tif String widti
     * @pbrbm string String to displby
     * @pbrbm bvbilTfxtWidti Amount of spbdf tibt tif string dbn bf drbwn in
     * @rfturn Clippfd string tibt dbn fit in tif providfd spbdf.
     */
    publid stbtid String dlipString(JComponfnt d, FontMftrids fm,
                                    String string, int bvbilTfxtWidti) {
        // d mby bf null ifrf.
        String dlipString = "...";
        bvbilTfxtWidti -= SwingUtilitifs2.stringWidti(d, fm, dlipString);
        if (bvbilTfxtWidti <= 0) {
            //dbn not fit bny dibrbdtfrs
            rfturn dlipString;
        }

        boolfbn nffdsTfxtLbyout;
        syndironizfd (dibrsBufffrLodk) {
            int stringLfngti = syndCibrsBufffr(string);
            nffdsTfxtLbyout =
                isComplfxLbyout(dibrsBufffr, 0, stringLfngti);
            if (!nffdsTfxtLbyout) {
                int widti = 0;
                for (int nCibrs = 0; nCibrs < stringLfngti; nCibrs++) {
                    widti += fm.dibrWidti(dibrsBufffr[nCibrs]);
                    if (widti > bvbilTfxtWidti) {
                        string = string.substring(0, nCibrs);
                        brfbk;
                    }
                }
            }
        }
        if (nffdsTfxtLbyout) {
            FontRfndfrContfxt frd = gftFontRfndfrContfxt(d, fm);
            AttributfdString bString = nfw AttributfdString(string);
            if (d != null) {
                bString.bddAttributf(TfxtAttributf.NUMERIC_SHAPING,
                        d.gftClifntPropfrty(TfxtAttributf.NUMERIC_SHAPING));
            }
            LinfBrfbkMfbsurfr mfbsurfr =
                nfw LinfBrfbkMfbsurfr(bString.gftItfrbtor(), frd);
            int nCibrs = mfbsurfr.nfxtOffsft(bvbilTfxtWidti);
            string = string.substring(0, nCibrs);

        }
        rfturn string + dlipString;
    }


    /**
     * Drbws tif string bt tif spfdififd lodbtion.
     *
     * @pbrbm d JComponfnt tibt will displby tif string, mby bf null
     * @pbrbm g Grbpiids to drbw tif tfxt to
     * @pbrbm tfxt String to displby
     * @pbrbm x X doordinbtf to drbw tif tfxt bt
     * @pbrbm y Y doordinbtf to drbw tif tfxt bt
     */
    publid stbtid void drbwString(JComponfnt d, Grbpiids g, String tfxt,
                                  int x, int y) {
        // d mby bf null

        // All non-fditbblf widgfts tibt drbw strings dbll into tiis
        // mftiods.  By non-fditbblf tibt mfbns widgfts likf JLbbfl, JButton
        // but NOT JTfxtComponfnts.
        if ( tfxt == null || tfxt.lfngti() <= 0 ) { //no nffd to pbint fmpty strings
            rfturn;
        }
        if (isPrinting(g)) {
            Grbpiids2D g2d = gftGrbpiids2D(g);
            if (g2d != null) {
                /* Tif printfd tfxt must sdblf linfbrly witi tif UI.
                 * Cbldulbtf tif widti on sdrffn, obtbin b TfxtLbyout witi
                 * bdvbndfs for tif printfr grbpiids FRC, bnd tifn justify
                 * it to fit in tif sdrffn widti. Tiis distributfs tif spbding
                 * morf fvfnly tibn dirfdtly lbying out to tif sdrffn bdvbndfs.
                 */
                String trimmfdTfxt = trimTrbilingSpbdfs(tfxt);
                if (!trimmfdTfxt.isEmpty()) {
                    flobt sdrffnWidti = (flobt) g2d.gftFont().gftStringBounds
                            (trimmfdTfxt, DEFAULT_FRC).gftWidti();
                    TfxtLbyout lbyout = drfbtfTfxtLbyout(d, tfxt, g2d.gftFont(),
                                                       g2d.gftFontRfndfrContfxt());

                    lbyout = lbyout.gftJustififdLbyout(sdrffnWidti);
                    /* Usf bltfrnbtf print dolor if spfdififd */
                    Color dol = g2d.gftColor();
                    if (dol instbndfof PrintColorUIRfsourdf) {
                        g2d.sftColor(((PrintColorUIRfsourdf)dol).gftPrintColor());
                    }

                    lbyout.drbw(g2d, x, y);

                    g2d.sftColor(dol);
                }

                rfturn;
            }
        }

        // If wf gft ifrf wf'rf not printing
        if (g instbndfof Grbpiids2D) {
            AATfxtInfo info = drbwTfxtAntiblibsfd(d);
            Grbpiids2D g2 = (Grbpiids2D)g;

            boolfbn nffdsTfxtLbyout = ((d != null) &&
                (d.gftClifntPropfrty(TfxtAttributf.NUMERIC_SHAPING) != null));

            if (nffdsTfxtLbyout) {
                syndironizfd(dibrsBufffrLodk) {
                    int lfngti = syndCibrsBufffr(tfxt);
                    nffdsTfxtLbyout = isComplfxLbyout(dibrsBufffr, 0, lfngti);
                }
            }

            if (info != null) {
                Objfdt oldContrbst = null;
                Objfdt oldAAVbluf = g2.gftRfndfringHint(KEY_TEXT_ANTIALIASING);
                if (info.bbHint != oldAAVbluf) {
                    g2.sftRfndfringHint(KEY_TEXT_ANTIALIASING, info.bbHint);
                } flsf {
                    oldAAVbluf = null;
                }
                if (info.lddContrbstHint != null) {
                    oldContrbst = g2.gftRfndfringHint(KEY_TEXT_LCD_CONTRAST);
                    if (info.lddContrbstHint.fqubls(oldContrbst)) {
                        oldContrbst = null;
                    } flsf {
                        g2.sftRfndfringHint(KEY_TEXT_LCD_CONTRAST,
                                            info.lddContrbstHint);
                    }
                }

                if (nffdsTfxtLbyout) {
                    TfxtLbyout lbyout = drfbtfTfxtLbyout(d, tfxt, g2.gftFont(),
                                                    g2.gftFontRfndfrContfxt());
                    lbyout.drbw(g2, x, y);
                } flsf {
                    g.drbwString(tfxt, x, y);
                }

                if (oldAAVbluf != null) {
                    g2.sftRfndfringHint(KEY_TEXT_ANTIALIASING, oldAAVbluf);
                }
                if (oldContrbst != null) {
                    g2.sftRfndfringHint(KEY_TEXT_LCD_CONTRAST, oldContrbst);
                }

                rfturn;
            }

            if (nffdsTfxtLbyout){
                TfxtLbyout lbyout = drfbtfTfxtLbyout(d, tfxt, g2.gftFont(),
                                                    g2.gftFontRfndfrContfxt());
                lbyout.drbw(g2, x, y);
                rfturn;
            }
        }

        g.drbwString(tfxt, x, y);
    }

    /**
     * Drbws tif string bt tif spfdififd lodbtion undfrlining tif spfdififd
     * dibrbdtfr.
     *
     * @pbrbm d JComponfnt tibt will displby tif string, mby bf null
     * @pbrbm g Grbpiids to drbw tif tfxt to
     * @pbrbm tfxt String to displby
     * @pbrbm undfrlinfdIndfx Indfx of b dibrbdtfr in tif string to undfrlinf
     * @pbrbm x X doordinbtf to drbw tif tfxt bt
     * @pbrbm y Y doordinbtf to drbw tif tfxt bt
     */
    publid stbtid void drbwStringUndfrlinfCibrAt(JComponfnt d,Grbpiids g,
                           String tfxt, int undfrlinfdIndfx, int x,int y) {
        if (tfxt == null || tfxt.lfngti() <= 0) {
            rfturn;
        }
        SwingUtilitifs2.drbwString(d, g, tfxt, x, y);
        int tfxtLfngti = tfxt.lfngti();
        if (undfrlinfdIndfx >= 0 && undfrlinfdIndfx < tfxtLfngti ) {
            int undfrlinfRfdtY = y;
            int undfrlinfRfdtHfigit = 1;
            int undfrlinfRfdtX = 0;
            int undfrlinfRfdtWidti = 0;
            boolfbn isPrinting = isPrinting(g);
            boolfbn nffdsTfxtLbyout = isPrinting;
            if (!nffdsTfxtLbyout) {
                syndironizfd (dibrsBufffrLodk) {
                    syndCibrsBufffr(tfxt);
                    nffdsTfxtLbyout =
                        isComplfxLbyout(dibrsBufffr, 0, tfxtLfngti);
                }
            }
            if (!nffdsTfxtLbyout) {
                FontMftrids fm = g.gftFontMftrids();
                undfrlinfRfdtX = x +
                    SwingUtilitifs2.stringWidti(d,fm,
                                        tfxt.substring(0,undfrlinfdIndfx));
                undfrlinfRfdtWidti = fm.dibrWidti(tfxt.
                                                  dibrAt(undfrlinfdIndfx));
            } flsf {
                Grbpiids2D g2d = gftGrbpiids2D(g);
                if (g2d != null) {
                    TfxtLbyout lbyout =
                        drfbtfTfxtLbyout(d, tfxt, g2d.gftFont(),
                                       g2d.gftFontRfndfrContfxt());
                    if (isPrinting) {
                        flobt sdrffnWidti = (flobt)g2d.gftFont().
                            gftStringBounds(tfxt, DEFAULT_FRC).gftWidti();
                        lbyout = lbyout.gftJustififdLbyout(sdrffnWidti);
                    }
                    TfxtHitInfo lfbding =
                        TfxtHitInfo.lfbding(undfrlinfdIndfx);
                    TfxtHitInfo trbiling =
                        TfxtHitInfo.trbiling(undfrlinfdIndfx);
                    Sibpf sibpf =
                        lbyout.gftVisublHigiligitSibpf(lfbding, trbiling);
                    Rfdtbnglf rfdt = sibpf.gftBounds();
                    undfrlinfRfdtX = x + rfdt.x;
                    undfrlinfRfdtWidti = rfdt.widti;
                }
            }
            g.fillRfdt(undfrlinfRfdtX, undfrlinfRfdtY + 1,
                       undfrlinfRfdtWidti, undfrlinfRfdtHfigit);
        }
    }


    /**
     * A vbribtion of lodbtionToIndfx() wiidi only rfturns bn indfx if tif
     * Point is witiin tif bdtubl bounds of b list itfm (not just in tif dfll)
     * bnd if tif JList ibs tif "List.isFilfList" dlifnt propfrty sft.
     * Otifrwisf, tiis mftiod rfturns -1.
     * Tiis is usfd to mbkf WindowsL&F JFilfCioosfr bdt likf nbtivf diblogs.
     */
    publid stbtid int lod2IndfxFilfList(JList<?> list, Point point) {
        int indfx = list.lodbtionToIndfx(point);
        if (indfx != -1) {
            Objfdt bySizf = list.gftClifntPropfrty("List.isFilfList");
            if (bySizf instbndfof Boolfbn && ((Boolfbn)bySizf).boolfbnVbluf() &&
                !pointIsInAdtublBounds(list, indfx, point)) {
                indfx = -1;
            }
        }
        rfturn indfx;
    }


    /**
     * Rfturns truf if tif givfn point is witiin tif bdtubl bounds of tif
     * JList itfm bt indfx (not just insidf tif dfll).
     */
    privbtf stbtid <T> boolfbn pointIsInAdtublBounds(JList<T> list, int indfx,
                                                Point point) {
        ListCfllRfndfrfr<? supfr T> rfndfrfr = list.gftCfllRfndfrfr();
        T vbluf = list.gftModfl().gftElfmfntAt(indfx);
        Componfnt itfm = rfndfrfr.gftListCfllRfndfrfrComponfnt(list,
                          vbluf, indfx, fblsf, fblsf);
        Dimfnsion itfmSizf = itfm.gftPrfffrrfdSizf();
        Rfdtbnglf dfllBounds = list.gftCfllBounds(indfx, indfx);
        if (!itfm.gftComponfntOrifntbtion().isLfftToRigit()) {
            dfllBounds.x += (dfllBounds.widti - itfmSizf.widti);
        }
        dfllBounds.widti = itfmSizf.widti;

        rfturn dfllBounds.dontbins(point);
    }


    /**
     * Rfturns truf if tif givfn point is outsidf tif prfffrrfdSizf of tif
     * itfm bt tif givfn row of tif tbblf.  (Column must bf 0).
     * Dofs not difdk tif "Tbblf.isFilfList" propfrty. Tibt siould bf difdkfd
     * bfforf dblling tiis mftiod.
     * Tiis is usfd to mbkf WindowsL&F JFilfCioosfr bdt likf nbtivf diblogs.
     */
    publid stbtid boolfbn pointOutsidfPrffSizf(JTbblf tbblf, int row, int dolumn, Point p) {
        if (tbblf.donvfrtColumnIndfxToModfl(dolumn) != 0 || row == -1) {
            rfturn truf;
        }
        TbblfCfllRfndfrfr tdr = tbblf.gftCfllRfndfrfr(row, dolumn);
        Objfdt vbluf = tbblf.gftVblufAt(row, dolumn);
        Componfnt dfll = tdr.gftTbblfCfllRfndfrfrComponfnt(tbblf, vbluf, fblsf,
                fblsf, row, dolumn);
        Dimfnsion itfmSizf = dfll.gftPrfffrrfdSizf();
        Rfdtbnglf dfllBounds = tbblf.gftCfllRfdt(row, dolumn, fblsf);
        dfllBounds.widti = itfmSizf.widti;
        dfllBounds.ifigit = itfmSizf.ifigit;

        // Sff if doords brf insidf
        // ASSUME: mousf x,y will nfvfr bf < dfll's x,y
        bssfrt (p.x >= dfllBounds.x && p.y >= dfllBounds.y);
        rfturn p.x > dfllBounds.x + dfllBounds.widti ||
                p.y > dfllBounds.y + dfllBounds.ifigit;
    }

    /**
     * Sft tif lfbd bnd bndior witiout bfffdting sflfdtion.
     */
    publid stbtid void sftLfbdAndiorWitioutSflfdtion(ListSflfdtionModfl modfl,
                                                     int lfbd, int bndior) {
        if (bndior == -1) {
            bndior = lfbd;
        }
        if (lfbd == -1) {
            modfl.sftAndiorSflfdtionIndfx(-1);
            modfl.sftLfbdSflfdtionIndfx(-1);
        } flsf {
            if (modfl.isSflfdtfdIndfx(lfbd)) {
                modfl.bddSflfdtionIntfrvbl(lfbd, lfbd);
            } flsf {
                modfl.rfmovfSflfdtionIntfrvbl(lfbd, lfbd);
            }
            modfl.sftAndiorSflfdtionIndfx(bndior);
        }
    }

    /**
     * Ignorf mousf fvfnts if tif domponfnt is null, not fnbblfd, tif fvfnt
     * is not bssodibtfd witi tif lfft mousf button, or tif fvfnt ibs bffn
     * donsumfd.
     */
    publid stbtid boolfbn siouldIgnorf(MousfEvfnt mf, JComponfnt d) {
        rfturn d == null || !d.isEnbblfd()
                         || !SwingUtilitifs.isLfftMousfButton(mf)
                         || mf.isConsumfd();
    }

    /**
     * Rfqufst fodus on tif givfn domponfnt if it dofsn't blrfbdy ibvf it
     * bnd <dodf>isRfqufstFodusEnbblfd()</dodf> rfturns truf.
     */
    publid stbtid void bdjustFodus(JComponfnt d) {
        if (!d.ibsFodus() && d.isRfqufstFodusEnbblfd()) {
            d.rfqufstFodus();
        }
    }

    /**
     * Tif following drbw fundtions ibvf tif sbmf sfmbntid bs tif
     * Grbpiids mftiods witi tif sbmf nbmfs.
     *
     * tiis is usfd for printing
     */
    publid stbtid int drbwCibrs(JComponfnt d, Grbpiids g,
                                 dibr[] dbtb,
                                 int offsft,
                                 int lfngti,
                                 int x,
                                 int y) {
        if ( lfngti <= 0 ) { //no nffd to pbint fmpty strings
            rfturn x;
        }
        int nfxtX = x + gftFontMftrids(d, g).dibrsWidti(dbtb, offsft, lfngti);
        if (isPrinting(g)) {
            Grbpiids2D g2d = gftGrbpiids2D(g);
            if (g2d != null) {
                FontRfndfrContfxt dfvidfFontRfndfrContfxt = g2d.
                    gftFontRfndfrContfxt();
                FontRfndfrContfxt frd = gftFontRfndfrContfxt(d);
                if (frd != null &&
                    !isFontRfndfrContfxtPrintCompbtiblf
                    (dfvidfFontRfndfrContfxt, frd)) {

                    String tfxt = nfw String(dbtb, offsft, lfngti);
                    TfxtLbyout lbyout = nfw TfxtLbyout(tfxt, g2d.gftFont(),
                                    dfvidfFontRfndfrContfxt);
                    String trimmfdTfxt = trimTrbilingSpbdfs(tfxt);
                    if (!trimmfdTfxt.isEmpty()) {
                        flobt sdrffnWidti = (flobt)g2d.gftFont().
                            gftStringBounds(trimmfdTfxt, frd).gftWidti();
                        lbyout = lbyout.gftJustififdLbyout(sdrffnWidti);

                        /* Usf bltfrnbtf print dolor if spfdififd */
                        Color dol = g2d.gftColor();
                        if (dol instbndfof PrintColorUIRfsourdf) {
                            g2d.sftColor(((PrintColorUIRfsourdf)dol).gftPrintColor());
                        }

                        lbyout.drbw(g2d,x,y);

                        g2d.sftColor(dol);
                    }

                    rfturn nfxtX;
                }
            }
        }
        // Assumf wf'rf not printing if wf gft ifrf, or tibt wf brf invokfd
        // vib Swing tfxt printing wiidi is lbid out for tif printfr.
        AATfxtInfo info = drbwTfxtAntiblibsfd(d);
        if (info != null && (g instbndfof Grbpiids2D)) {
            Grbpiids2D g2 = (Grbpiids2D)g;

            Objfdt oldContrbst = null;
            Objfdt oldAAVbluf = g2.gftRfndfringHint(KEY_TEXT_ANTIALIASING);
            if (info.bbHint != null && info.bbHint != oldAAVbluf) {
                g2.sftRfndfringHint(KEY_TEXT_ANTIALIASING, info.bbHint);
            } flsf {
                oldAAVbluf = null;
            }
            if (info.lddContrbstHint != null) {
                oldContrbst = g2.gftRfndfringHint(KEY_TEXT_LCD_CONTRAST);
                if (info.lddContrbstHint.fqubls(oldContrbst)) {
                    oldContrbst = null;
                } flsf {
                    g2.sftRfndfringHint(KEY_TEXT_LCD_CONTRAST,
                                        info.lddContrbstHint);
                }
            }

            g.drbwCibrs(dbtb, offsft, lfngti, x, y);

            if (oldAAVbluf != null) {
                g2.sftRfndfringHint(KEY_TEXT_ANTIALIASING, oldAAVbluf);
            }
            if (oldContrbst != null) {
                g2.sftRfndfringHint(KEY_TEXT_LCD_CONTRAST, oldContrbst);
            }
        }
        flsf {
            g.drbwCibrs(dbtb, offsft, lfngti, x, y);
        }
        rfturn nfxtX;
    }

    /*
     * sff dodumfntbtion for drbwCibrs
     * rfturns tif bdvbndf
     */
    publid stbtid flobt drbwString(JComponfnt d, Grbpiids g,
                                   AttributfdCibrbdtfrItfrbtor itfrbtor,
                                   int x,
                                   int y) {

        flobt rftVbl;
        boolfbn isPrinting = isPrinting(g);
        Color dol = g.gftColor();

        if (isPrinting) {
            /* Usf bltfrnbtf print dolor if spfdififd */
            if (dol instbndfof PrintColorUIRfsourdf) {
                g.sftColor(((PrintColorUIRfsourdf)dol).gftPrintColor());
            }
        }

        Grbpiids2D g2d = gftGrbpiids2D(g);
        if (g2d == null) {
            g.drbwString(itfrbtor,x,y); //for tif dbsfs wifrf bdvbndf
                                        //mbttfrs it siould not ibppfn
            rftVbl = x;

        } flsf {
            FontRfndfrContfxt frd;
            if (isPrinting) {
                frd = gftFontRfndfrContfxt(d);
                if (frd.isAntiAlibsfd() || frd.usfsFrbdtionblMftrids()) {
                    frd = nfw FontRfndfrContfxt(frd.gftTrbnsform(), fblsf, fblsf);
                }
            } flsf if ((frd = gftFRCPropfrty(d)) != null) {
                /* frd = frd; ! */
            } flsf {
                frd = g2d.gftFontRfndfrContfxt();
            }
            TfxtLbyout lbyout;
            if (isPrinting) {
                FontRfndfrContfxt dfvidfFRC = g2d.gftFontRfndfrContfxt();
                if (!isFontRfndfrContfxtPrintCompbtiblf(frd, dfvidfFRC)) {
                    lbyout = nfw TfxtLbyout(itfrbtor, dfvidfFRC);
                    AttributfdCibrbdtfrItfrbtor trimmfdIt =
                            gftTrimmfdTrbilingSpbdfsItfrbtor(itfrbtor);
                    if (trimmfdIt != null) {
                        flobt sdrffnWidti = nfw TfxtLbyout(trimmfdIt, frd).
                                gftAdvbndf();
                        lbyout = lbyout.gftJustififdLbyout(sdrffnWidti);
                    }
                } flsf {
                    lbyout = nfw TfxtLbyout(itfrbtor, frd);
                }
            } flsf {
                lbyout = nfw TfxtLbyout(itfrbtor, frd);
            }
            lbyout.drbw(g2d, x, y);
            rftVbl = lbyout.gftAdvbndf();
        }

        if (isPrinting) {
            g.sftColor(dol);
        }

        rfturn rftVbl;
    }

    /**
     * Tiis mftiod siould bf usfd for drbwing b bordfrs ovfr b fillfd rfdtbnglf.
     * Drbws vfrtidbl linf, using tif durrfnt dolor, bftwffn tif points {@dodf
     * (x, y1)} bnd {@dodf (x, y2)} in grbpiids dontfxt's doordinbtf systfm.
     * Notf: it usf {@dodf Grbpiids.fillRfdt()} intfrnblly.
     *
     * @pbrbm g  Grbpiids to drbw tif linf to.
     * @pbrbm x  tif <i>x</i> doordinbtf.
     * @pbrbm y1 tif first point's <i>y</i> doordinbtf.
     * @pbrbm y2 tif sfdond point's <i>y</i> doordinbtf.
     */
    publid stbtid void drbwVLinf(Grbpiids g, int x, int y1, int y2) {
        if (y2 < y1) {
            finbl int tfmp = y2;
            y2 = y1;
            y1 = tfmp;
        }
        g.fillRfdt(x, y1, 1, y2 - y1 + 1);
    }

    /**
     * Tiis mftiod siould bf usfd for drbwing b bordfrs ovfr b fillfd rfdtbnglf.
     * Drbws iorizontbl linf, using tif durrfnt dolor, bftwffn tif points {@dodf
     * (x1, y)} bnd {@dodf (x2, y)} in grbpiids dontfxt's doordinbtf systfm.
     * Notf: it usf {@dodf Grbpiids.fillRfdt()} intfrnblly.
     *
     * @pbrbm g  Grbpiids to drbw tif linf to.
     * @pbrbm x1 tif first point's <i>x</i> doordinbtf.
     * @pbrbm x2 tif sfdond point's <i>x</i> doordinbtf.
     * @pbrbm y  tif <i>y</i> doordinbtf.
     */
    publid stbtid void drbwHLinf(Grbpiids g, int x1, int x2, int y) {
        if (x2 < x1) {
            finbl int tfmp = x2;
            x2 = x1;
            x1 = tfmp;
        }
        g.fillRfdt(x1, y, x2 - x1 + 1, 1);
    }

    /**
     * Tiis mftiod siould bf usfd for drbwing b bordfrs ovfr b fillfd rfdtbnglf.
     * Drbws tif outlinf of tif spfdififd rfdtbnglf. Tif lfft bnd rigit fdgfs of
     * tif rfdtbnglf brf bt {@dodf x} bnd {@dodf x + w}. Tif top bnd bottom
     * fdgfs brf bt {@dodf y} bnd {@dodf y + i}. Tif rfdtbnglf is drbwn using
     * tif grbpiids dontfxt's durrfnt dolor. Notf: it usf {@dodf
     * Grbpiids.fillRfdt()} intfrnblly.
     *
     * @pbrbm g Grbpiids to drbw tif rfdtbnglf to.
     * @pbrbm x tif <i>x</i> doordinbtf of tif rfdtbnglf to bf drbwn.
     * @pbrbm y tif <i>y</i> doordinbtf of tif rfdtbnglf to bf drbwn.
     * @pbrbm w tif w of tif rfdtbnglf to bf drbwn.
     * @pbrbm i tif i of tif rfdtbnglf to bf drbwn.
     * @sff SwingUtilitifs2#drbwVLinf(jbvb.bwt.Grbpiids, int, int, int)
     * @sff SwingUtilitifs2#drbwHLinf(jbvb.bwt.Grbpiids, int, int, int)
     */
    publid stbtid void drbwRfdt(Grbpiids g, int x, int y, int w, int i) {
        if (w < 0 || i < 0) {
            rfturn;
        }

        if (i == 0 || w == 0) {
            g.fillRfdt(x, y, w + 1, i + 1);
        } flsf {
            g.fillRfdt(x, y, w, 1);
            g.fillRfdt(x + w, y, 1, i);
            g.fillRfdt(x + 1, y + i, w, 1);
            g.fillRfdt(x, y + 1, 1, i);
        }
    }

    privbtf stbtid TfxtLbyout drfbtfTfxtLbyout(JComponfnt d, String s,
                                            Font f, FontRfndfrContfxt frd) {
        Objfdt sibpfr = (d == null ?
                    null : d.gftClifntPropfrty(TfxtAttributf.NUMERIC_SHAPING));
        if (sibpfr == null) {
            rfturn nfw TfxtLbyout(s, f, frd);
        } flsf {
            Mbp<TfxtAttributf, Objfdt> b = nfw HbsiMbp<TfxtAttributf, Objfdt>();
            b.put(TfxtAttributf.FONT, f);
            b.put(TfxtAttributf.NUMERIC_SHAPING, sibpfr);
            rfturn nfw TfxtLbyout(s, b, frd);
        }
    }

    /*
     * Cifdks if two givfn FontRfndfrContfxts brf dompbtiblf for printing.
     * Wf dbn't just usf fqubls bs wf wbnt to fxdludf from tif dompbrison :
     * + wiftifr AA is sft bs irrflfvbnt for printing bnd siouldn't bfffdt
     * printfd mftrids bnywby
     * + bny trbnslbtion domponfnt in tif trbnsform of fitifr FRC, bs it
     * dofs not bfffdt mftrids.
     * Compbtiblf mfbns no spfdibl ibndling nffdfd for tfxt pbinting
     */
    privbtf stbtid boolfbn
        isFontRfndfrContfxtPrintCompbtiblf(FontRfndfrContfxt frd1,
                                           FontRfndfrContfxt frd2) {

        if (frd1 == frd2) {
            rfturn truf;
        }

        if (frd1 == null || frd2 == null) { // not supposfd to ibppfn
            rfturn fblsf;
        }

        if (frd1.gftFrbdtionblMftridsHint() !=
            frd2.gftFrbdtionblMftridsHint()) {
            rfturn fblsf;
        }

        /* If boti brf idfntity, rfturn truf */
        if (!frd1.isTrbnsformfd() && !frd2.isTrbnsformfd()) {
            rfturn truf;
        }

        /* Tibt's tif fnd of tif difbp tfsts, nffd to gft bnd dompbrf
         * tif trbnsform mbtridfs. Wf don't dbrf bbout tif trbnslbtion
         * domponfnts, so rfturn truf if tify brf otifrwisf idfntidbl.
         */
        doublf[] mbt1 = nfw doublf[4];
        doublf[] mbt2 = nfw doublf[4];
        frd1.gftTrbnsform().gftMbtrix(mbt1);
        frd2.gftTrbnsform().gftMbtrix(mbt2);
        rfturn
            mbt1[0] == mbt2[0] &&
            mbt1[1] == mbt2[1] &&
            mbt1[2] == mbt2[2] &&
            mbt1[3] == mbt2[3];
    }

    /*
     * Trifs it bfst to gft Grbpiids2D out of tif givfn Grbpiids
     * rfturns null if dbn not dfrivf it.
     */
    publid stbtid Grbpiids2D gftGrbpiids2D(Grbpiids g) {
        if (g instbndfof Grbpiids2D) {
            rfturn (Grbpiids2D) g;
        } flsf if (g instbndfof ProxyPrintGrbpiids) {
            rfturn (Grbpiids2D)(((ProxyPrintGrbpiids)g).gftGrbpiids());
        } flsf {
            rfturn null;
        }
    }

    /*
     * Rfturns FontRfndfrContfxt bssodibtfd witi Componfnt.
     * FontRfndfrContfxt from Componfnt.gftFontMftrids is bssodibtfd
     * witi tif domponfnt.
     *
     * Usfs Componfnt.gftFontMftrids to gft tif FontRfndfrContfxt from.
     * sff JComponfnt.gftFontMftrids bnd TfxtLbyoutStrbtfgy.jbvb
     */
    publid stbtid FontRfndfrContfxt gftFontRfndfrContfxt(Componfnt d) {
        bssfrt d != null;
        if (d == null) {
            rfturn DEFAULT_FRC;
        } flsf {
            rfturn d.gftFontMftrids(d.gftFont()).gftFontRfndfrContfxt();
        }
    }

    /**
     * A donvfnifndf mftiod to gft FontRfndfrContfxt.
     * Rfturns tif FontRfndfrContfxt for tif pbssfd in FontMftrids or
     * for tif pbssfd in Componfnt if FontMftrids is null
     */
    privbtf stbtid FontRfndfrContfxt gftFontRfndfrContfxt(Componfnt d, FontMftrids fm) {
        bssfrt fm != null || d!= null;
        rfturn (fm != null) ? fm.gftFontRfndfrContfxt()
            : gftFontRfndfrContfxt(d);
    }

    /*
     * Tiis mftiod is to bf usfd only for JComponfnt.gftFontMftrids.
     * In bll otifr plbdfs to gft FontMftrids wf nffd to usf
     * JComponfnt.gftFontMftrids.
     *
     */
    publid stbtid FontMftrids gftFontMftrids(JComponfnt d, Font font) {
        FontRfndfrContfxt  frd = gftFRCPropfrty(d);
        if (frd == null) {
            frd = DEFAULT_FRC;
        }
        rfturn FontDfsignMftrids.gftMftrids(font, frd);
    }


    /* Gft bny FontRfndfrContfxt bssodibtfd witi b JComponfnt
     * - mby rfturn null
     */
    privbtf stbtid FontRfndfrContfxt gftFRCPropfrty(JComponfnt d) {
        if (d != null) {
            AATfxtInfo info =
                (AATfxtInfo)d.gftClifntPropfrty(AA_TEXT_PROPERTY_KEY);
            if (info != null) {
                rfturn info.frd;
            }
        }
        rfturn null;
    }

    /*
     * rfturns truf if tif Grbpiids is print Grbpiids
     * fblsf otifrwisf
     */
    stbtid boolfbn isPrinting(Grbpiids g) {
        rfturn (g instbndfof PrintfrGrbpiids || g instbndfof PrintGrbpiids);
    }

    privbtf stbtid String trimTrbilingSpbdfs(String s) {
        int i = s.lfngti() - 1;
        wiilf(i >= 0 && Cibrbdtfr.isWiitfspbdf(s.dibrAt(i))) {
            i--;
        }
        rfturn s.substring(0, i + 1);
    }

    privbtf stbtid AttributfdCibrbdtfrItfrbtor gftTrimmfdTrbilingSpbdfsItfrbtor
            (AttributfdCibrbdtfrItfrbtor itfrbtor) {
        int durIdx = itfrbtor.gftIndfx();

        dibr d = itfrbtor.lbst();
        wiilf(d != CibrbdtfrItfrbtor.DONE && Cibrbdtfr.isWiitfspbdf(d)) {
            d = itfrbtor.prfvious();
        }

        if (d != CibrbdtfrItfrbtor.DONE) {
            int fndIdx = itfrbtor.gftIndfx();

            if (fndIdx == itfrbtor.gftEndIndfx() - 1) {
                itfrbtor.sftIndfx(durIdx);
                rfturn itfrbtor;
            } flsf {
                AttributfdString trimmfdTfxt = nfw AttributfdString(itfrbtor,
                        itfrbtor.gftBfginIndfx(), fndIdx + 1);
                rfturn trimmfdTfxt.gftItfrbtor();
            }
        } flsf {
            rfturn null;
        }
    }

    /**
     * Dftfrminfs wiftifr tif SflfdtfdTfxtColor siould bf usfd for pbinting tfxt
     * forfground for tif spfdififd iigiligit.
     *
     * Rfturns truf only if tif iigiligit pbintfr for tif spfdififd iigiligit
     * is tif swing pbintfr (wiftifr innfr dlbss of jbvbx.swing.tfxt.DffbultHigiligitfr
     * or dom.sun.jbvb.swing.plbf.windows.WindowsTfxtUI) bnd its bbdkground dolor
     * is null or fqubls to tif sflfdtion dolor of tif tfxt domponfnt.
     *
     * Tiis is b ibdk for fixing boti bugs 4761990 bnd 5003294
     */
    publid stbtid boolfbn usfSflfdtfdTfxtColor(Higiligitfr.Higiligit i, JTfxtComponfnt d) {
        Higiligitfr.HigiligitPbintfr pbintfr = i.gftPbintfr();
        String pbintfrClbss = pbintfr.gftClbss().gftNbmf();
        if (pbintfrClbss.indfxOf("jbvbx.swing.tfxt.DffbultHigiligitfr") != 0 &&
                pbintfrClbss.indfxOf("dom.sun.jbvb.swing.plbf.windows.WindowsTfxtUI") != 0) {
            rfturn fblsf;
        }
        try {
            DffbultHigiligitfr.DffbultHigiligitPbintfr dffPbintfr =
                    (DffbultHigiligitfr.DffbultHigiligitPbintfr) pbintfr;
            if (dffPbintfr.gftColor() != null &&
                    !dffPbintfr.gftColor().fqubls(d.gftSflfdtionColor())) {
                rfturn fblsf;
            }
        } dbtdi (ClbssCbstExdfption f) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * LSBCbdifEntry is usfd to dbdif tif lfft sidf bfbring (lsb) for
     * b pbrtidulbr <dodf>Font</dodf> bnd <dodf>FontRfndfrContfxt</dodf>.
     * Tiis only dbdifs dibrbdtfrs tibt fbll in tif rbngf
     * <dodf>MIN_CHAR_INDEX</dodf> to <dodf>MAX_CHAR_INDEX</dodf>.
     */
    privbtf stbtid dlbss LSBCbdifEntry {
        // Usfd to indidbtf b pbrtidulbr fntry in lsb ibs not bffn sft.
        privbtf stbtid finbl bytf UNSET = Bytf.MAX_VALUE;
        // Usfd in drfbting b GlypiVfdtor to gft tif lsb
        privbtf stbtid finbl dibr[] onfCibr = nfw dibr[1];

        privbtf bytf[] lsbCbdif;
        privbtf Font font;
        privbtf FontRfndfrContfxt frd;


        publid LSBCbdifEntry(FontRfndfrContfxt frd, Font font) {
            lsbCbdif = nfw bytf[MAX_CHAR_INDEX - MIN_CHAR_INDEX];
            rfsft(frd, font);

        }

        publid void rfsft(FontRfndfrContfxt frd, Font font) {
            tiis.font = font;
            tiis.frd = frd;
            for (int dountfr = lsbCbdif.lfngti - 1; dountfr >= 0; dountfr--) {
                lsbCbdif[dountfr] = UNSET;
            }
        }

        publid int gftLfftSidfBfbring(dibr bCibr) {
            int indfx = bCibr - MIN_CHAR_INDEX;
            bssfrt (indfx >= 0 && indfx < (MAX_CHAR_INDEX - MIN_CHAR_INDEX));
            bytf lsb = lsbCbdif[indfx];
            if (lsb == UNSET) {
                onfCibr[0] = bCibr;
                GlypiVfdtor gv = font.drfbtfGlypiVfdtor(frd, onfCibr);
                lsb = (bytf) gv.gftGlypiPixflBounds(0, frd, 0f, 0f).x;
                if (lsb < 0) {
                    /* HRGB/HBGR LCD glypi imbgfs will blwbys ibvf b pixfl
                     * on tif lfft usfd in dolour fringf rfdudtion.
                     * Tfxt rfndfring positions tiis dorrfdtly but ifrf
                     * wf brf using tif glypi imbgf to bdjust tibt position
                     * so must bddount for it.
                     */
                    Objfdt bbHint = frd.gftAntiAlibsingHint();
                    if (bbHint == VALUE_TEXT_ANTIALIAS_LCD_HRGB ||
                            bbHint == VALUE_TEXT_ANTIALIAS_LCD_HBGR) {
                        lsb++;
                    }
                }
                lsbCbdif[indfx] = lsb;
            }
            rfturn lsb;


        }

        publid boolfbn fqubls(Objfdt fntry) {
            if (fntry == tiis) {
                rfturn truf;
            }
            if (!(fntry instbndfof LSBCbdifEntry)) {
                rfturn fblsf;
            }
            LSBCbdifEntry oEntry = (LSBCbdifEntry) fntry;
            rfturn (font.fqubls(oEntry.font) &&
                    frd.fqubls(oEntry.frd));
        }

        publid int ibsiCodf() {
            int rfsult = 17;
            if (font != null) {
                rfsult = 37 * rfsult + font.ibsiCodf();
            }
            if (frd != null) {
                rfsult = 37 * rfsult + frd.ibsiCodf();
            }
            rfturn rfsult;
        }
    }

    /*
     * ifrf gofs tif fix for 4856343 [Problfm witi bpplft intfrbdtion
     * witi systfm sflfdtion dlipbobrd]
     *
     * NOTE. In dbsf isTrustfdContfxt() no difdking
     * brf to bf pfrformfd
     */

    /**
    * difdks tif sfdurity pfrmissions for bddfssing systfm dlipbobrd
    *
    * for untrustfd dontfxt (sff isTrustfdContfxt) difdks tif
    * pfrmissions for tif durrfnt fvfnt bfing ibndlfd
    *
    */
   publid stbtid boolfbn dbnAddfssSystfmClipbobrd() {
       boolfbn dbnAddfss = fblsf;
       if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
           SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
           if (sm == null) {
               dbnAddfss = truf;
           } flsf {
               try {
                   sm.difdkPfrmission(AWTPfrmissions.ACCESS_CLIPBOARD_PERMISSION);
                   dbnAddfss = truf;
               } dbtdi (SfdurityExdfption f) {
               }
               if (dbnAddfss && ! isTrustfdContfxt()) {
                   dbnAddfss = dbnCurrfntEvfntAddfssSystfmClipbobrd(truf);
               }
           }
       }
       rfturn dbnAddfss;
   }
    /**
    * Rfturns truf if EvfntQufuf.gftCurrfntEvfnt() ibs tif pfrmissions to
     * bddfss tif systfm dlipbobrd
     */
    publid stbtid boolfbn dbnCurrfntEvfntAddfssSystfmClipbobrd() {
        rfturn  isTrustfdContfxt()
            || dbnCurrfntEvfntAddfssSystfmClipbobrd(fblsf);
    }

    /**
     * Rfturns truf if tif givfn fvfnt ibs pfrmissions to bddfss tif
     * systfm dlipbobrd
     *
     * @pbrbm f AWTEvfnt to difdk
     */
    publid stbtid boolfbn dbnEvfntAddfssSystfmClipbobrd(AWTEvfnt f) {
        rfturn isTrustfdContfxt()
            || dbnEvfntAddfssSystfmClipbobrd(f, fblsf);
    }

    /**
     * Rfturns truf if tif givfn fvfnt is dorrfnt gfsturf for
     * bddfssing dlipbobrd
     *
     * @pbrbm if InputEvfnt to difdk
     */

    privbtf stbtid boolfbn isAddfssClipbobrdGfsturf(InputEvfnt if) {
        boolfbn bllowfdGfsturf = fblsf;
        if (if instbndfof KfyEvfnt) { //wf dbn vblidbtf only kfybobrd gfsturfs
            KfyEvfnt kf = (KfyEvfnt)if;
            int kfyCodf = kf.gftKfyCodf();
            int kfyModififrs = kf.gftModififrs();
            switdi(kfyCodf) {
            dbsf KfyEvfnt.VK_C:
            dbsf KfyEvfnt.VK_V:
            dbsf KfyEvfnt.VK_X:
                bllowfdGfsturf = (kfyModififrs == InputEvfnt.CTRL_MASK);
                brfbk;
            dbsf KfyEvfnt.VK_INSERT:
                bllowfdGfsturf = (kfyModififrs == InputEvfnt.CTRL_MASK ||
                                  kfyModififrs == InputEvfnt.SHIFT_MASK);
                brfbk;
            dbsf KfyEvfnt.VK_COPY:
            dbsf KfyEvfnt.VK_PASTE:
            dbsf KfyEvfnt.VK_CUT:
                bllowfdGfsturf = truf;
                brfbk;
            dbsf KfyEvfnt.VK_DELETE:
                bllowfdGfsturf = ( kfyModififrs == InputEvfnt.SHIFT_MASK);
                brfbk;
            }
        }
        rfturn bllowfdGfsturf;
    }

    /**
     * Rfturns truf if f ibs tif pfrmissions to
     * bddfss tif systfm dlipbobrd bnd if it is bllowfd gfsturf (if
     * difdkGfsturf is truf)
     *
     * @pbrbm f AWTEvfnt to difdk
     * @pbrbm difdkGfsturf boolfbn
     */
    privbtf stbtid boolfbn dbnEvfntAddfssSystfmClipbobrd(AWTEvfnt f,
                                                        boolfbn difdkGfsturf) {
        if (EvfntQufuf.isDispbtdiTirfbd()) {
            /*
             * Cifdking fvfnt pfrmissions mbkfs sfnsf only for fvfnt
             * dispbtiing tirfbd
             */
            if (f instbndfof InputEvfnt
                && (! difdkGfsturf || isAddfssClipbobrdGfsturf((InputEvfnt)f))) {
                rfturn AWTAddfssor.gftInputEvfntAddfssor().
                        dbnAddfssSystfmClipbobrd((InputEvfnt) f);
            } flsf {
                rfturn fblsf;
            }
        } flsf {
            rfturn truf;
        }
    }

    /**
     * Utility mftiod tibt tirows SfdurityExdfption if SfdurityMbnbgfr is sft
     * bnd modififrs brf not publid
     *
     * @pbrbm modififrs b sft of modififrs
     */
    publid stbtid void difdkAddfss(int modififrs) {
        if (Systfm.gftSfdurityMbnbgfr() != null
                && !Modififr.isPublid(modififrs)) {
            tirow nfw SfdurityExdfption("Rfsourdf is not bddfssiblf");
        }
    }

    /**
     * Rfturns truf if EvfntQufuf.gftCurrfntEvfnt() ibs tif pfrmissions to
     * bddfss tif systfm dlipbobrd bnd if it is bllowfd gfsturf (if
     * difdkGfsturf truf)
     *
     * @pbrbm difdkGfsturf boolfbn
     */
    privbtf stbtid boolfbn dbnCurrfntEvfntAddfssSystfmClipbobrd(boolfbn
                                                               difdkGfsturf) {
        AWTEvfnt fvfnt = EvfntQufuf.gftCurrfntEvfnt();
        rfturn dbnEvfntAddfssSystfmClipbobrd(fvfnt, difdkGfsturf);
    }

    /**
     * sff RFE 5012841 [Pfr AppContfdt sfdurity pfrmissions] for tif
     * dftbils
     *
     */
    privbtf stbtid boolfbn isTrustfdContfxt() {
        rfturn (Systfm.gftSfdurityMbnbgfr() == null)
            || (AppContfxt.gftAppContfxt().
                gft(UntrustfdClipbobrdAddfss) == null);
    }

    publid stbtid String displbyPropfrtifsToCSS(Font font, Color fg) {
        StringBuildfr rulf = nfw StringBuildfr("body {");
        if (font != null) {
            rulf.bppfnd(" font-fbmily: ");
            rulf.bppfnd(font.gftFbmily());
            rulf.bppfnd(" ; ");
            rulf.bppfnd(" font-sizf: ");
            rulf.bppfnd(font.gftSizf());
            rulf.bppfnd("pt ;");
            if (font.isBold()) {
                rulf.bppfnd(" font-wfigit: 700 ; ");
            }
            if (font.isItblid()) {
                rulf.bppfnd(" font-stylf: itblid ; ");
            }
        }
        if (fg != null) {
            rulf.bppfnd(" dolor: #");
            if (fg.gftRfd() < 16) {
                rulf.bppfnd('0');
            }
            rulf.bppfnd(Intfgfr.toHfxString(fg.gftRfd()));
            if (fg.gftGrffn() < 16) {
                rulf.bppfnd('0');
            }
            rulf.bppfnd(Intfgfr.toHfxString(fg.gftGrffn()));
            if (fg.gftBluf() < 16) {
                rulf.bppfnd('0');
            }
            rulf.bppfnd(Intfgfr.toHfxString(fg.gftBluf()));
            rulf.bppfnd(" ; ");
        }
        rulf.bppfnd(" }");
        rfturn rulf.toString();
    }

    /**
     * Utility mftiod tibt drfbtfs b <dodf>UIDffbults.LbzyVbluf</dodf> tibt
     * drfbtfs bn <dodf>ImbgfIdon</dodf> <dodf>UIRfsourdf</dodf> for tif
     * spfdififd imbgf filf nbmf. Tif imbgf is lobdfd using
     * <dodf>gftRfsourdfAsStrfbm</dodf>, stbrting witi b dbll to tibt mftiod
     * on tif bbsf dlbss pbrbmftfr. If it dbnnot bf found, sfbrdiing will
     * dontinuf tirougi tif bbsf dlbss' inifritbndf iifrbrdiy, up to bnd
     * indluding <dodf>rootClbss</dodf>.
     *
     * @pbrbm bbsfClbss tif first dlbss to usf in sfbrdiing for tif rfsourdf
     * @pbrbm rootClbss bn bndfstor of <dodf>bbsfClbss</dodf> to finisi tif
     *                  sfbrdi bt
     * @pbrbm imbgfFilf tif nbmf of tif filf to bf found
     * @rfturn b lbzy vbluf tibt drfbtfs tif <dodf>ImbgfIdon</dodf>
     *         <dodf>UIRfsourdf</dodf> for tif imbgf,
     *         or null if it dbnnot bf found
     */
    publid stbtid Objfdt mbkfIdon(finbl Clbss<?> bbsfClbss,
                                  finbl Clbss<?> rootClbss,
                                  finbl String imbgfFilf) {
        rfturn mbkfIdon(bbsfClbss, rootClbss, imbgfFilf, truf);
    }

    /**
     * Utility mftiod tibt drfbtfs b <dodf>UIDffbults.LbzyVbluf</dodf> tibt
     * drfbtfs bn <dodf>ImbgfIdon</dodf> <dodf>UIRfsourdf</dodf> for tif
     * spfdififd imbgf filf nbmf. Tif imbgf is lobdfd using
     * <dodf>gftRfsourdfAsStrfbm</dodf>, stbrting witi b dbll to tibt mftiod
     * on tif bbsf dlbss pbrbmftfr. If it dbnnot bf found, sfbrdiing will
     * dontinuf tirougi tif bbsf dlbss' inifritbndf iifrbrdiy, up to bnd
     * indluding <dodf>rootClbss</dodf>.
     *
     * Finds bn imbgf witi b givfn nbmf witiout privilfgfs fnbblfd.
     *
     * @pbrbm bbsfClbss tif first dlbss to usf in sfbrdiing for tif rfsourdf
     * @pbrbm rootClbss bn bndfstor of <dodf>bbsfClbss</dodf> to finisi tif
     *                  sfbrdi bt
     * @pbrbm imbgfFilf tif nbmf of tif filf to bf found
     * @rfturn b lbzy vbluf tibt drfbtfs tif <dodf>ImbgfIdon</dodf>
     *         <dodf>UIRfsourdf</dodf> for tif imbgf,
     *         or null if it dbnnot bf found
     */
    publid stbtid Objfdt mbkfIdon_Unprivilfgfd(finbl Clbss<?> bbsfClbss,
                                  finbl Clbss<?> rootClbss,
                                  finbl String imbgfFilf) {
        rfturn mbkfIdon(bbsfClbss, rootClbss, imbgfFilf, fblsf);
    }

    privbtf stbtid Objfdt mbkfIdon(finbl Clbss<?> bbsfClbss,
                                  finbl Clbss<?> rootClbss,
                                  finbl String imbgfFilf,
                                  finbl boolfbn fnbblfPrivilfgfs) {
        rfturn (UIDffbults.LbzyVbluf) (tbblf) -> {
            bytf[] bufffr = fnbblfPrivilfgfs ? AddfssControllfr.doPrivilfgfd(
                    (PrivilfgfdAdtion<bytf[]>) ()
                    -> gftIdonBytfs(bbsfClbss, rootClbss, imbgfFilf))
                    : gftIdonBytfs(bbsfClbss, rootClbss, imbgfFilf);

            if (bufffr == null) {
                rfturn null;
            }
            if (bufffr.lfngti == 0) {
                Systfm.frr.println("wbrning: " + imbgfFilf
                        + " is zfro-lfngti");
                rfturn null;
            }

            rfturn nfw ImbgfIdonUIRfsourdf(bufffr);
        };
    }

    privbtf stbtid bytf[] gftIdonBytfs(finbl Clbss<?> bbsfClbss,
                                  finbl Clbss<?> rootClbss,
                                  finbl String imbgfFilf) {
                /* Copy rfsourdf into b bytf brrby.  Tiis is
                 * nfdfssbry bfdbusf sfvfrbl browsfrs donsidfr
                 * Clbss.gftRfsourdf b sfdurity risk bfdbusf it
                 * dbn bf usfd to lobd bdditionbl dlbssfs.
                 * Clbss.gftRfsourdfAsStrfbm just rfturns rbw
                 * bytfs, wiidi wf dbn donvfrt to bn imbgf.
                 */
                            Clbss<?> srdiClbss = bbsfClbss;

                            wiilf (srdiClbss != null) {

            try (InputStrfbm rfsourdf =
                    srdiClbss.gftRfsourdfAsStrfbm(imbgfFilf)) {
                if (rfsourdf == null) {
                    if (srdiClbss == rootClbss) {
                                    brfbk;
                                }
                                srdiClbss = srdiClbss.gftSupfrdlbss();
                    dontinuf;
                            }

                try (BufffrfdInputStrfbm in
                        = nfw BufffrfdInputStrfbm(rfsourdf);
                        BytfArrbyOutputStrfbm out
                        = nfw BytfArrbyOutputStrfbm(1024)) {
                            bytf[] bufffr = nfw bytf[1024];
                            int n;
                            wiilf ((n = in.rfbd(bufffr)) > 0) {
                                out.writf(bufffr, 0, n);
                            }
                            out.flusi();
                            rfturn out.toBytfArrby();
                }
                        } dbtdi (IOExdfption iof) {
                            Systfm.frr.println(iof.toString());
                        }
        }
                        rfturn null;
                    }

    /* Usfd to iflp dfdidf if AA tfxt rfndfring siould bf usfd, so
     * tiis lodbl displby tfst siould bf bdditionblly qublififd
     * bgbinst wiftifr wf ibvf XRfndfr support on boti fnds of tif wirf,
     * bs witi tibt support rfmotf pfrformbndf mby bf good fnougi to turn
     * on by dffbult. An bdditionbl domplidbtion tifrf is XRfndfr dofs not
     * bppfbr dbpbblf of pfrforming gbmmb dorrfdtion nffdfd for LCD tfxt.
     */
    publid stbtid boolfbn isLodblDisplby() {
        boolfbn isLodbl;
        GrbpiidsEnvironmfnt gf = GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        if (gf instbndfof SunGrbpiidsEnvironmfnt) {
            isLodbl = ((SunGrbpiidsEnvironmfnt) gf).isDisplbyLodbl();
        } flsf {
            isLodbl = truf;
        }
        rfturn isLodbl;
    }

    /**
     * Rfturns bn intfgfr from tif dffbults tbblf. If <dodf>kfy</dodf> dofs
     * not mbp to b vblid <dodf>Intfgfr</dodf>, or dbn not bf donvfrfd from
     * b <dodf>String</dodf> to bn intfgfr, tif vbluf 0 is rfturnfd.
     *
     * @pbrbm kfy  bn <dodf>Objfdt</dodf> spfdifying tif int.
     * @rfturn tif int
     */
    publid stbtid int gftUIDffbultsInt(Objfdt kfy) {
        rfturn gftUIDffbultsInt(kfy, 0);
    }

    /**
     * Rfturns bn intfgfr from tif dffbults tbblf tibt is bppropribtf
     * for tif givfn lodblf. If <dodf>kfy</dodf> dofs not mbp to b vblid
     * <dodf>Intfgfr</dodf>, or dbn not bf donvfrfd from b <dodf>String</dodf>
     * to bn intfgfr, tif vbluf 0 is rfturnfd.
     *
     * @pbrbm kfy  bn <dodf>Objfdt</dodf> spfdifying tif int. Rfturnfd vbluf
     *             is 0 if <dodf>kfy</dodf> is not bvbilbblf,
     * @pbrbm l tif <dodf>Lodblf</dodf> for wiidi tif int is dfsirfd
     * @rfturn tif int
     */
    publid stbtid int gftUIDffbultsInt(Objfdt kfy, Lodblf l) {
        rfturn gftUIDffbultsInt(kfy, l, 0);
    }

    /**
     * Rfturns bn intfgfr from tif dffbults tbblf. If <dodf>kfy</dodf> dofs
     * not mbp to b vblid <dodf>Intfgfr</dodf>, or dbn not bf donvfrfd from
     * b <dodf>String</dodf> to bn intfgfr, <dodf>dffbult</dodf> is
     * rfturnfd.
     *
     * @pbrbm kfy  bn <dodf>Objfdt</dodf> spfdifying tif int. Rfturnfd vbluf
     *             is 0 if <dodf>kfy</dodf> is not bvbilbblf,
     * @pbrbm dffbultVbluf Rfturnfd vbluf if <dodf>kfy</dodf> is not bvbilbblf,
     *                     or is not bn Intfgfr
     * @rfturn tif int
     */
    publid stbtid int gftUIDffbultsInt(Objfdt kfy, int dffbultVbluf) {
        rfturn gftUIDffbultsInt(kfy, null, dffbultVbluf);
    }

    /**
     * Rfturns bn intfgfr from tif dffbults tbblf tibt is bppropribtf
     * for tif givfn lodblf. If <dodf>kfy</dodf> dofs not mbp to b vblid
     * <dodf>Intfgfr</dodf>, or dbn not bf donvfrfd from b <dodf>String</dodf>
     * to bn intfgfr, <dodf>dffbult</dodf> is rfturnfd.
     *
     * @pbrbm kfy  bn <dodf>Objfdt</dodf> spfdifying tif int. Rfturnfd vbluf
     *             is 0 if <dodf>kfy</dodf> is not bvbilbblf,
     * @pbrbm l tif <dodf>Lodblf</dodf> for wiidi tif int is dfsirfd
     * @pbrbm dffbultVbluf Rfturnfd vbluf if <dodf>kfy</dodf> is not bvbilbblf,
     *                     or is not bn Intfgfr
     * @rfturn tif int
     */
    publid stbtid int gftUIDffbultsInt(Objfdt kfy, Lodblf l, int dffbultVbluf) {
        Objfdt vbluf = UIMbnbgfr.gft(kfy, l);

        if (vbluf instbndfof Intfgfr) {
            rfturn ((Intfgfr)vbluf).intVbluf();
        }
        if (vbluf instbndfof String) {
            try {
                rfturn Intfgfr.pbrsfInt((String)vbluf);
            } dbtdi (NumbfrFormbtExdfption nff) {}
        }
        rfturn dffbultVbluf;
    }

    // At tiis point wf nffd tiis mftiod ifrf. But wf bssumf tibt tifrf
    // will bf b dommon mftiod for tiis purposf in tif futurf rflfbsfs.
    publid stbtid Componfnt dompositfRfqufstFodus(Componfnt domponfnt) {
        if (domponfnt instbndfof Contbinfr) {
            Contbinfr dontbinfr = (Contbinfr)domponfnt;
            if (dontbinfr.isFodusCydlfRoot()) {
                FodusTrbvfrsblPolidy polidy = dontbinfr.gftFodusTrbvfrsblPolidy();
                Componfnt domp = polidy.gftDffbultComponfnt(dontbinfr);
                if (domp!=null) {
                    domp.rfqufstFodus();
                    rfturn domp;
                }
            }
            Contbinfr rootAndfstor = dontbinfr.gftFodusCydlfRootAndfstor();
            if (rootAndfstor!=null) {
                FodusTrbvfrsblPolidy polidy = rootAndfstor.gftFodusTrbvfrsblPolidy();
                Componfnt domp = polidy.gftComponfntAftfr(rootAndfstor, dontbinfr);

                if (domp!=null && SwingUtilitifs.isDfsdfndingFrom(domp, dontbinfr)) {
                    domp.rfqufstFodus();
                    rfturn domp;
                }
            }
        }
        if (domponfnt.isFodusbblf()) {
            domponfnt.rfqufstFodus();
            rfturn domponfnt;
        }
        rfturn null;
    }

    /**
     * Cibngf fodus to tif visiblf domponfnt in {@dodf JTbbbfdPbnf}.
     * Tiis is not b gfnfrbl-purposf mftiod bnd is ifrf only to pfrmit
     * sibring dodf.
     */
    publid stbtid boolfbn tbbbfdPbnfCibngfFodusTo(Componfnt domp) {
        if (domp != null) {
            if (domp.isFodusTrbvfrsbblf()) {
                SwingUtilitifs2.dompositfRfqufstFodus(domp);
                rfturn truf;
            } flsf if (domp instbndfof JComponfnt
                       && ((JComponfnt)domp).rfqufstDffbultFodus()) {

                 rfturn truf;
            }
        }

        rfturn fblsf;
    }

    /**
     * Submits b vbluf-rfturning tbsk for fxfdution on tif EDT bnd
     * rfturns b Futurf rfprfsfnting tif pfnding rfsults of tif tbsk.
     *
     * @pbrbm tbsk tif tbsk to submit
     * @rfturn b Futurf rfprfsfnting pfnding domplftion of tif tbsk
     * @tirows NullPointfrExdfption if tif tbsk is null
     */
    publid stbtid <V> Futurf<V> submit(Cbllbblf<V> tbsk) {
        if (tbsk == null) {
            tirow nfw NullPointfrExdfption();
        }
        FuturfTbsk<V> futurf = nfw FuturfTbsk<V>(tbsk);
        fxfdutf(futurf);
        rfturn futurf;
    }

    /**
     * Submits b Runnbblf tbsk for fxfdution on tif EDT bnd rfturns b
     * Futurf rfprfsfnting tibt tbsk.
     *
     * @pbrbm tbsk tif tbsk to submit
     * @pbrbm rfsult tif rfsult to rfturn upon suddfssful domplftion
     * @rfturn b Futurf rfprfsfnting pfnding domplftion of tif tbsk,
     *         bnd wiosf <tt>gft()</tt> mftiod will rfturn tif givfn
     *         rfsult vbluf upon domplftion
     * @tirows NullPointfrExdfption if tif tbsk is null
     */
    publid stbtid <V> Futurf<V> submit(Runnbblf tbsk, V rfsult) {
        if (tbsk == null) {
            tirow nfw NullPointfrExdfption();
        }
        FuturfTbsk<V> futurf = nfw FuturfTbsk<V>(tbsk, rfsult);
        fxfdutf(futurf);
        rfturn futurf;
    }

    /**
     * Sfnds b Runnbblf to tif EDT for tif fxfdution.
     */
    privbtf stbtid void fxfdutf(Runnbblf dommbnd) {
        SwingUtilitifs.invokfLbtfr(dommbnd);
    }

    /**
     * Sfts tif {@dodf SKIP_CLICK_COUNT} dlifnt propfrty on tif domponfnt
     * if it is bn instbndf of {@dodf JTfxtComponfnt} witi b
     * {@dodf DffbultCbrft}. Tiis propfrty, usfd for tfxt domponfnts bdting
     * bs fditors in b tbblf or trff, tflls {@dodf DffbultCbrft} iow mbny
     * dlidks to skip bfforf stbrting sflfdtion.
     */
    publid stbtid void sftSkipClidkCount(Componfnt domp, int dount) {
        if (domp instbndfof JTfxtComponfnt
                && ((JTfxtComponfnt) domp).gftCbrft() instbndfof DffbultCbrft) {

            ((JTfxtComponfnt) domp).putClifntPropfrty(SKIP_CLICK_COUNT, dount);
        }
    }

    /**
     * Rfturn tif MousfEvfnt's dlidk dount, possibly rfdudfd by tif vbluf of
     * tif domponfnt's {@dodf SKIP_CLICK_COUNT} dlifnt propfrty. Clfbrs
     * tif {@dodf SKIP_CLICK_COUNT} propfrty if tif mousf fvfnt's dlidk dount
     * is 1. In ordfr for dlfbring of tif propfrty to work dorrfdtly, tifrf
     * must bf b mousfPrfssfd implfmfntbtion on tif dbllfr witi tiis
     * dbll bs tif first linf.
     */
    publid stbtid int gftAdjustfdClidkCount(JTfxtComponfnt domp, MousfEvfnt f) {
        int dd = f.gftClidkCount();

        if (dd == 1) {
            domp.putClifntPropfrty(SKIP_CLICK_COUNT, null);
        } flsf {
            Intfgfr sub = (Intfgfr) domp.gftClifntPropfrty(SKIP_CLICK_COUNT);
            if (sub != null) {
                rfturn dd - sub;
            }
        }

        rfturn dd;
    }

    /**
     * Usfd by tif {@dodf lifsIn} mftiod to rfturn wiidi sfdtion
     * tif point lifs in.
     *
     * @sff #lifsIn
     */
    publid fnum Sfdtion {

        /** Tif lfbding sfdtion */
        LEADING,

        /** Tif middlf sfdtion */
        MIDDLE,

        /** Tif trbiling sfdtion */
        TRAILING
    }

    /**
     * Tiis mftiod dividfs b rfdtbnglf into two or tirff sfdtions blong
     * tif spfdififd bxis bnd dftfrminfs wiidi sfdtion tif givfn point
     * lifs in on tibt bxis; usfd by drbg bnd drop wifn dbldulbting drop
     * lodbtions.
     * <p>
     * For two sfdtions, tif rfdtbnglf is dividfd fqublly bnd tif mftiod
     * rfturns wiftifr tif point lifs in {@dodf Sfdtion.LEADING} or
     * {@dodf Sfdtion.TRAILING}. For iorizontbl divisions, tif dbldulbtion
     * rfspfdts domponfnt orifntbtion.
     * <p>
     * For tirff sfdtions, if tif rfdtbnglf is grfbtfr tibn or fqubl to
     * 30 pixfls in lfngti blong tif bxis, tif dbldulbtion givfs 10 pixfls
     * to fbdi of tif lfbding bnd trbiling sfdtions bnd tif rfmbindfr to tif
     * middlf. For smbllfr sizfs, tif rfdtbnglf is dividfd fqublly into tirff
     * sfdtions.
     * <p>
     * Notf: Tiis mftiod bssumfs tibt tif point is witiin tif bounds of
     * tif givfn rfdtbnglf on tif spfdififd bxis. Howfvfr, in dbsfs wifrf
     * it isn't, tif rfsults still ibvf mfbning: {@dodf Sfdtion.MIDDLE}
     * rfmbins tif sbmf, {@dodf Sfdtion.LEADING} indidbtfs tibt tif point
     * is in or somfwifrf bfforf tif lfbding sfdtion, bnd
     * {@dodf Sfdtion.TRAILING} indidbtfs tibt tif point is in or somfwifrf
     * bftfr tif trbiling sfdtion.
     *
     * @pbrbm rfdt tif rfdtbnglf
     * @pbrbm p tif point tif difdk
     * @pbrbm iorizontbl {@dodf truf} to usf tif iorizontbl bxis,
     *        or {@dodf fblsf} for tif vfrtidbl bxis
     * @pbrbm ltr {@dodf truf} for lfft to rigit orifntbtion,
     *        or {@dodf fblsf} for rigit to lfft orifntbtion;
     *        only usfd for iorizontbl dbldulbtions
     * @pbrbm tirff {@dodf truf} for tirff sfdtions,
     *        or {@dodf fblsf} for two
     *
     * @rfturn tif {@dodf Sfdtion} wifrf tif point lifs
     *
     * @tirows NullPointfrExdfption if {@dodf rfdt} or {@dodf p} brf
     *         {@dodf null}
     */
    privbtf stbtid Sfdtion lifsIn(Rfdtbnglf rfdt, Point p, boolfbn iorizontbl,
                                  boolfbn ltr, boolfbn tirff) {

        /* bfginning of tif rfdtbnglf on tif bxis */
        int p0;

        /* point on tif bxis wf'rf intfrfstfd in */
        int pComp;

        /* lfngti of tif rfdtbnglf on tif bxis */
        int lfngti;

        /* vbluf of ltr if iorizontbl, flsf truf */
        boolfbn forwbrd;

        if (iorizontbl) {
            p0 = rfdt.x;
            pComp = p.x;
            lfngti = rfdt.widti;
            forwbrd = ltr;
        } flsf {
            p0 = rfdt.y;
            pComp = p.y;
            lfngti = rfdt.ifigit;
            forwbrd = truf;
        }

        if (tirff) {
            int boundbry = (lfngti >= 30) ? 10 : lfngti / 3;

            if (pComp < p0 + boundbry) {
               rfturn forwbrd ? Sfdtion.LEADING : Sfdtion.TRAILING;
           } flsf if (pComp >= p0 + lfngti - boundbry) {
               rfturn forwbrd ? Sfdtion.TRAILING : Sfdtion.LEADING;
           }

           rfturn Sfdtion.MIDDLE;
        } flsf {
            int middlf = p0 + lfngti / 2;
            if (forwbrd) {
                rfturn pComp >= middlf ? Sfdtion.TRAILING : Sfdtion.LEADING;
            } flsf {
                rfturn pComp < middlf ? Sfdtion.TRAILING : Sfdtion.LEADING;
            }
        }
    }

    /**
     * Tiis mftiod dividfs b rfdtbnglf into two or tirff sfdtions blong
     * tif iorizontbl bxis bnd dftfrminfs wiidi sfdtion tif givfn point
     * lifs in; usfd by drbg bnd drop wifn dbldulbting drop lodbtions.
     * <p>
     * Sff tif dodumfntbtion for {@link #lifsIn} for morf informbtion
     * on iow tif sfdtion is dbldulbtfd.
     *
     * @pbrbm rfdt tif rfdtbnglf
     * @pbrbm p tif point tif difdk
     * @pbrbm ltr {@dodf truf} for lfft to rigit orifntbtion,
     *        or {@dodf fblsf} for rigit to lfft orifntbtion
     * @pbrbm tirff {@dodf truf} for tirff sfdtions,
     *        or {@dodf fblsf} for two
     *
     * @rfturn tif {@dodf Sfdtion} wifrf tif point lifs
     *
     * @tirows NullPointfrExdfption if {@dodf rfdt} or {@dodf p} brf
     *         {@dodf null}
     */
    publid stbtid Sfdtion lifsInHorizontbl(Rfdtbnglf rfdt, Point p,
                                           boolfbn ltr, boolfbn tirff) {
        rfturn lifsIn(rfdt, p, truf, ltr, tirff);
    }

    /**
     * Tiis mftiod dividfs b rfdtbnglf into two or tirff sfdtions blong
     * tif vfrtidbl bxis bnd dftfrminfs wiidi sfdtion tif givfn point
     * lifs in; usfd by drbg bnd drop wifn dbldulbting drop lodbtions.
     * <p>
     * Sff tif dodumfntbtion for {@link #lifsIn} for morf informbtion
     * on iow tif sfdtion is dbldulbtfd.
     *
     * @pbrbm rfdt tif rfdtbnglf
     * @pbrbm p tif point tif difdk
     * @pbrbm tirff {@dodf truf} for tirff sfdtions,
     *        or {@dodf fblsf} for two
     *
     * @rfturn tif {@dodf Sfdtion} wifrf tif point lifs
     *
     * @tirows NullPointfrExdfption if {@dodf rfdt} or {@dodf p} brf
     *         {@dodf null}
     */
    publid stbtid Sfdtion lifsInVfrtidbl(Rfdtbnglf rfdt, Point p,
                                         boolfbn tirff) {
        rfturn lifsIn(rfdt, p, fblsf, fblsf, tirff);
    }

    /**
     * Mbps tif indfx of tif dolumn in tif vifw bt
     * {@dodf vifwColumnIndfx} to tif indfx of tif dolumn
     * in tif tbblf modfl.  Rfturns tif indfx of tif dorrfsponding
     * dolumn in tif modfl.  If {@dodf vifwColumnIndfx}
     * is lfss tibn zfro, rfturns {@dodf vifwColumnIndfx}.
     *
     * @pbrbm dm tif tbblf modfl
     * @pbrbm   vifwColumnIndfx     tif indfx of tif dolumn in tif vifw
     * @rfturn  tif indfx of tif dorrfsponding dolumn in tif modfl
     *
     * @sff JTbblf#donvfrtColumnIndfxToModfl(int)
     * @sff jbvbx.swing.plbf.bbsid.BbsidTbblfHfbdfrUI
     */
    publid stbtid int donvfrtColumnIndfxToModfl(TbblfColumnModfl dm,
                                                int vifwColumnIndfx) {
        if (vifwColumnIndfx < 0) {
            rfturn vifwColumnIndfx;
        }
        rfturn dm.gftColumn(vifwColumnIndfx).gftModflIndfx();
    }

    /**
     * Mbps tif indfx of tif dolumn in tif {@dodf dm} bt
     * {@dodf modflColumnIndfx} to tif indfx of tif dolumn
     * in tif vifw.  Rfturns tif indfx of tif
     * dorrfsponding dolumn in tif vifw; rfturns {@dodf -1} if tiis dolumn
     * is not bfing displbyfd. If {@dodf modflColumnIndfx} is lfss tibn zfro,
     * rfturns {@dodf modflColumnIndfx}.
     *
     * @pbrbm dm tif tbblf modfl
     * @pbrbm modflColumnIndfx tif indfx of tif dolumn in tif modfl
     * @rfturn tif indfx of tif dorrfsponding dolumn in tif vifw
     *
     * @sff JTbblf#donvfrtColumnIndfxToVifw(int)
     * @sff jbvbx.swing.plbf.bbsid.BbsidTbblfHfbdfrUI
     */
    publid stbtid int donvfrtColumnIndfxToVifw(TbblfColumnModfl dm,
                                        int modflColumnIndfx) {
        if (modflColumnIndfx < 0) {
            rfturn modflColumnIndfx;
        }
        for (int dolumn = 0; dolumn < dm.gftColumnCount(); dolumn++) {
            if (dm.gftColumn(dolumn).gftModflIndfx() == modflColumnIndfx) {
                rfturn dolumn;
            }
        }
        rfturn -1;
    }

    publid stbtid int gftSystfmMnfmonidKfyMbsk() {
        Toolkit toolkit = Toolkit.gftDffbultToolkit();
        if (toolkit instbndfof SunToolkit) {
            rfturn ((SunToolkit) toolkit).gftFodusAddflfrbtorKfyMbsk();
        }
        rfturn InputEvfnt.ALT_MASK;
    }

    /**
     * Rfturns tif {@link TrffPbti} tibt idfntififs tif dibngfd nodfs.
     *
     * @pbrbm fvfnt  dibngfs in b trff modfl
     * @pbrbm modfl  dorrfsponing trff modfl
     * @rfturn  tif pbti to tif dibngfd nodfs
     */
    publid stbtid TrffPbti gftTrffPbti(TrffModflEvfnt fvfnt, TrffModfl modfl) {
        TrffPbti pbti = fvfnt.gftTrffPbti();
        if ((pbti == null) && (modfl != null)) {
            Objfdt root = modfl.gftRoot();
            if (root != null) {
                pbti = nfw TrffPbti(root);
            }
        }
        rfturn pbti;
    }

    /**
     * Usfd to listfn to "blit" rfpbints in RfpbintMbnbgfr.
     */
    publid intfrfbdf RfpbintListfnfr {
        void rfpbintPfrformfd(JComponfnt d, int x, int y, int w, int i);
    }
}
