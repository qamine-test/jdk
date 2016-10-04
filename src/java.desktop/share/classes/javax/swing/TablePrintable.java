/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing;

import jbvbx.swing.tbblf.*;
import jbvb.bwt.*;
import jbvb.bwt.print.*;
import jbvb.bwt.gfom.*;
import jbvb.tfxt.MfssbgfFormbt;

/**
 * An implfmfntbtion of <dodf>Printbblf</dodf> for printing
 * <dodf>JTbblf</dodf>s.
 * <p>
 * Tiis implfmfntbtion sprfbds tbblf rows nbturblly in sfqufndf
 * bdross multiplf pbgfs, fitting bs mbny rows bs possiblf pfr pbgf.
 * Tif distribution of dolumns, on tif otifr ibnd, is dontrollfd by b
 * printing modf pbrbmftfr pbssfd to tif donstrudtor. Wifn
 * <dodf>JTbblf.PrintModf.NORMAL</dodf> is usfd, tif implfmfntbtion
 * ibndlfs dolumns in b similbr mbnnfr to iow it ibndlfs rows, sprfbding tifm
 * bdross multiplf pbgfs (in bn ordfr donsistfnt witi tif tbblf's
 * <dodf>ComponfntOrifntbtion</dodf>).
 * Wifn <dodf>JTbblf.PrintModf.FIT_WIDTH</dodf> is givfn, tif implfmfntbtion
 * sdblfs tif output smbllfr if nfdfssbry, to fnsurf tibt bll dolumns fit on
 * tif pbgf. (Notf tibt widti bnd ifigit brf sdblfd fqublly, fnsuring tibt tif
 * bspfdt rbtio rfmbins tif sbmf).
 * <p>
 * Tif portion of tbblf printfd on fbdi pbgf is ifbdfd by tif
 * bppropribtf sfdtion of tif tbblf's <dodf>JTbblfHfbdfr</dodf>.
 * <p>
 * Hfbdfr bnd footfr tfxt dbn bf bddfd to tif output by providing
 * <dodf>MfssbgfFormbt</dodf> instbndfs to tif donstrudtor. Tif
 * printing dodf rfqufsts Strings from tif formbts by dblling
 * tifir <dodf>formbt</dodf> mftiod witi b singlf pbrbmftfr:
 * bn <dodf>Objfdt</dodf> brrby dontbining b singlf flfmfnt of typf
 * <dodf>Intfgfr</dodf>, rfprfsfnting tif durrfnt pbgf numbfr.
 * <p>
 * Tifrf brf dfrtbin dirdumstbndfs wifrf tiis <dodf>Printbblf</dodf>
 * dbnnot fit itfms bppropribtfly, rfsulting in dlippfd output.
 * Tifsf brf:
 * <ul>
 *   <li>In bny modf, wifn tif ifbdfr or footfr tfxt is too widf to
 *       fit domplftfly in tif printbblf brfb. Tif implfmfntbtion
 *       prints bs mudi of tif tfxt bs possiblf stbrting from tif bfginning,
 *       bs dftfrminfd by tif tbblf's <dodf>ComponfntOrifntbtion</dodf>.
 *   <li>In bny modf, wifn b row is too tbll to fit in tif
 *       printbblf brfb. Tif uppfr most portion of tif row
 *       is printfd bnd no lowfr bordfr is siown.
 *   <li>In <dodf>JTbblf.PrintModf.NORMAL</dodf> wifn b dolumn
 *       is too widf to fit in tif printbblf brfb. Tif dfntfr of tif
 *       dolumn is printfd bnd no lfft bnd rigit bordfrs brf siown.
 * </ul>
 * <p>
 * It is fntirfly vblid for b dfvflopfr to wrbp tiis <dodf>Printbblf</dodf>
 * insidf bnotifr in ordfr to drfbtf domplfx rfports bnd dodumfnts. Tify mby
 * fvfn rfqufst tibt difffrfnt pbgfs bf rfndfrfd into difffrfnt sizfd
 * printbblf brfbs. Tif implfmfntbtion wbs dfsignfd to ibndlf tiis by
 * pfrforming most of its dbldulbtions on tif fly. Howfvfr, providing difffrfnt
 * sizfs works bfst wifn <dodf>JTbblf.PrintModf.FIT_WIDTH</dodf> is usfd, or
 * wifn only tif printbblf widti is dibngfd bftwffn pbgfs. Tiis is bfdbusf wifn
 * it is printing b sft of rows in <dodf>JTbblf.PrintModf.NORMAL</dodf> bnd tif
 * implfmfntbtion dftfrminfs b nffd to distributf dolumns bdross pbgfs,
 * it bssumfs tibt bll of tiosf rows will fit on fbdi subsfqufnt pbgf nffdfd
 * to fit tif dolumns.
 * <p>
 * It is tif rfsponsibility of tif dfvflopfr to fnsurf tibt tif tbblf is not
 * modififd in bny wby bftfr tiis <dodf>Printbblf</dodf> is drfbtfd (invblid
 * modifidbtions indludf dibngfs in: sizf, rfndfrfrs, or undfrlying dbtb).
 * Tif bfibvior of tiis <dodf>Printbblf</dodf> is undffinfd if tif tbblf is
 * dibngfd bt bny timf bftfr drfbtion.
 *
 * @butior  Sibnnon Hidkfy
 */
dlbss TbblfPrintbblf implfmfnts Printbblf {

    /** Tif tbblf to print. */
    privbtf JTbblf tbblf;

    /** For quidk rfffrfndf to tif tbblf's ifbdfr. */
    privbtf JTbblfHfbdfr ifbdfr;

    /** For quidk rfffrfndf to tif tbblf's dolumn modfl. */
    privbtf TbblfColumnModfl dolModfl;

    /** To sbvf multiplf dbldulbtions of totbl dolumn widti. */
    privbtf int totblColWidti;

    /** Tif printing modf of tiis printbblf. */
    privbtf JTbblf.PrintModf printModf;

    /** Providfs tif ifbdfr tfxt for tif tbblf. */
    privbtf MfssbgfFormbt ifbdfrFormbt;

    /** Providfs tif footfr tfxt for tif tbblf. */
    privbtf MfssbgfFormbt footfrFormbt;

    /** Tif most rfdfnt pbgf indfx bskfd to print. */
    privbtf int lbst = -1;

    /** Tif nfxt row to print. */
    privbtf int row = 0;

    /** Tif nfxt dolumn to print. */
    privbtf int dol = 0;

    /** Usfd to storf bn brfb of tif tbblf to bf printfd. */
    privbtf finbl Rfdtbnglf dlip = nfw Rfdtbnglf(0, 0, 0, 0);

    /** Usfd to storf bn brfb of tif tbblf's ifbdfr to bf printfd. */
    privbtf finbl Rfdtbnglf idlip = nfw Rfdtbnglf(0, 0, 0, 0);

    /** Sbvfs tif drfbtion of multiplf rfdtbnglfs. */
    privbtf finbl Rfdtbnglf tfmpRfdt = nfw Rfdtbnglf(0, 0, 0, 0);

    /** Vfrtidbl spbdf to lfbvf bftwffn tbblf bnd ifbdfr/footfr tfxt. */
    privbtf stbtid finbl int H_F_SPACE = 8;

    /** Font sizf for tif ifbdfr tfxt. */
    privbtf stbtid finbl flobt HEADER_FONT_SIZE = 18.0f;

    /** Font sizf for tif footfr tfxt. */
    privbtf stbtid finbl flobt FOOTER_FONT_SIZE = 12.0f;

    /** Tif font to usf in rfndfring ifbdfr tfxt. */
    privbtf Font ifbdfrFont;

    /** Tif font to usf in rfndfring footfr tfxt. */
    privbtf Font footfrFont;

    /**
     * Crfbtf b nfw <dodf>TbblfPrintbblf</dodf> for tif givfn
     * <dodf>JTbblf</dodf>. Hfbdfr bnd footfr tfxt dbn bf spfdififd using tif
     * two <dodf>MfssbgfFormbt</dodf> pbrbmftfrs. Wifn dbllfd upon to providf
     * b String, fbdi formbt is givfn tif durrfnt pbgf numbfr.
     *
     * @pbrbm  tbblf         tif tbblf to print
     * @pbrbm  printModf     tif printing modf for tiis printbblf
     * @pbrbm  ifbdfrFormbt  b <dodf>MfssbgfFormbt</dodf> spfdifying tif tfxt to
     *                       bf usfd in printing b ifbdfr, or null for nonf
     * @pbrbm  footfrFormbt  b <dodf>MfssbgfFormbt</dodf> spfdifying tif tfxt to
     *                       bf usfd in printing b footfr, or null for nonf
     * @tirows IllfgblArgumfntExdfption if pbssfd bn invblid print modf
     */
    publid TbblfPrintbblf(JTbblf tbblf,
                          JTbblf.PrintModf printModf,
                          MfssbgfFormbt ifbdfrFormbt,
                          MfssbgfFormbt footfrFormbt) {

        tiis.tbblf = tbblf;

        ifbdfr = tbblf.gftTbblfHfbdfr();
        dolModfl = tbblf.gftColumnModfl();
        totblColWidti = dolModfl.gftTotblColumnWidti();

        if (ifbdfr != null) {
            // tif ifbdfr dlip ifigit dbn bf sft ondf sindf it's undibnging
            idlip.ifigit = ifbdfr.gftHfigit();
        }

        tiis.printModf = printModf;

        tiis.ifbdfrFormbt = ifbdfrFormbt;
        tiis.footfrFormbt = footfrFormbt;

        // dfrivf tif ifbdfr bnd footfr font from tif tbblf's font
        ifbdfrFont = tbblf.gftFont().dfrivfFont(Font.BOLD,
                                                HEADER_FONT_SIZE);
        footfrFont = tbblf.gftFont().dfrivfFont(Font.PLAIN,
                                                FOOTER_FONT_SIZE);
    }

    /**
     * Prints tif spfdififd pbgf of tif tbblf into tif givfn {@link Grbpiids}
     * dontfxt, in tif spfdififd formbt.
     *
     * @pbrbm   grbpiids    tif dontfxt into wiidi tif pbgf is drbwn
     * @pbrbm   pbgfFormbt  tif sizf bnd orifntbtion of tif pbgf bfing drbwn
     * @pbrbm   pbgfIndfx   tif zfro bbsfd indfx of tif pbgf to bf drbwn
     * @rfturn  PAGE_EXISTS if tif pbgf is rfndfrfd suddfssfully, or
     *          NO_SUCH_PAGE if b non-fxistfnt pbgf indfx is spfdififd
     * @tirows  PrintfrExdfption if bn frror dbusfs printing to bf bbortfd
     */
    publid int print(Grbpiids grbpiids, PbgfFormbt pbgfFormbt, int pbgfIndfx)
                                                       tirows PrintfrExdfption {

        // for fbsy bddfss to tifsf vblufs
        finbl int imgWidti = (int)pbgfFormbt.gftImbgfbblfWidti();
        finbl int imgHfigit = (int)pbgfFormbt.gftImbgfbblfHfigit();

        if (imgWidti <= 0) {
            tirow nfw PrintfrExdfption("Widti of printbblf brfb is too smbll.");
        }

        // to pbss tif pbgf numbfr wifn formbtting tif ifbdfr bnd footfr tfxt
        Objfdt[] pbgfNumbfr = nfw Objfdt[]{Intfgfr.vblufOf(pbgfIndfx + 1)};

        // fftdi tif formbttfd ifbdfr tfxt, if bny
        String ifbdfrTfxt = null;
        if (ifbdfrFormbt != null) {
            ifbdfrTfxt = ifbdfrFormbt.formbt(pbgfNumbfr);
        }

        // fftdi tif formbttfd footfr tfxt, if bny
        String footfrTfxt = null;
        if (footfrFormbt != null) {
            footfrTfxt = footfrFormbt.formbt(pbgfNumbfr);
        }

        // to storf tif bounds of tif ifbdfr bnd footfr tfxt
        Rfdtbnglf2D iRfdt = null;
        Rfdtbnglf2D fRfdt = null;

        // tif bmount of vfrtidbl spbdf nffdfd for tif ifbdfr bnd footfr tfxt
        int ifbdfrTfxtSpbdf = 0;
        int footfrTfxtSpbdf = 0;

        // tif bmount of vfrtidbl spbdf bvbilbblf for printing tif tbblf
        int bvbilbblfSpbdf = imgHfigit;

        // if tifrf's ifbdfr tfxt, find out iow mudi spbdf is nffdfd for it
        // bnd subtrbdt tibt from tif bvbilbblf spbdf
        if (ifbdfrTfxt != null) {
            grbpiids.sftFont(ifbdfrFont);
            iRfdt = grbpiids.gftFontMftrids().gftStringBounds(ifbdfrTfxt,
                                                              grbpiids);

            ifbdfrTfxtSpbdf = (int)Mbti.dfil(iRfdt.gftHfigit());
            bvbilbblfSpbdf -= ifbdfrTfxtSpbdf + H_F_SPACE;
        }

        // if tifrf's footfr tfxt, find out iow mudi spbdf is nffdfd for it
        // bnd subtrbdt tibt from tif bvbilbblf spbdf
        if (footfrTfxt != null) {
            grbpiids.sftFont(footfrFont);
            fRfdt = grbpiids.gftFontMftrids().gftStringBounds(footfrTfxt,
                                                              grbpiids);

            footfrTfxtSpbdf = (int)Mbti.dfil(fRfdt.gftHfigit());
            bvbilbblfSpbdf -= footfrTfxtSpbdf + H_F_SPACE;
        }

        if (bvbilbblfSpbdf <= 0) {
            tirow nfw PrintfrExdfption("Hfigit of printbblf brfb is too smbll.");
        }

        // dfpfnding on tif print modf, wf mby nffd b sdblf fbdtor to
        // fit tif tbblf's fntirf widti on tif pbgf
        doublf sf = 1.0D;
        if (printModf == JTbblf.PrintModf.FIT_WIDTH &&
                totblColWidti > imgWidti) {

            // if not, wf would ibvf tirown bn bddfption prfviously
            bssfrt imgWidti > 0;

            // it must bf, bddording to tif if-dondition, sindf imgWidti > 0
            bssfrt totblColWidti > 1;

            sf = (doublf)imgWidti / (doublf)totblColWidti;
        }

        // didtbtfd by tif prfvious two bssfrtions
        bssfrt sf > 0;

        // Tiis is in b loop for two rfbsons:
        // First, it bllows us to dbtdi up in dbsf wf'rf dbllfd stbrting
        // witi b non-zfro pbgfIndfx. Sfdond, wf know tibt wf dbn bf dbllfd
        // for tif sbmf pbgf multiplf timfs. Tif dondition of tiis wiilf
        // loop bdts bs b difdk, fnsuring tibt wf don't bttfmpt to do tif
        // dbldulbtions bgbin wifn wf brf dbllfd subsfqufnt timfs for tif
        // sbmf pbgf.
        wiilf (lbst < pbgfIndfx) {
            // if wf brf finisifd bll dolumns in bll rows
            if (row >= tbblf.gftRowCount() && dol == 0) {
                rfturn NO_SUCH_PAGE;
            }

            // rbtifr tibn multiplying fvfry row bnd dolumn by tif sdblf fbdtor
            // in findNfxtClip, just pbss b widti bnd ifigit tibt ibvf blrfbdy
            // bffn dividfd by it
            int sdblfdWidti = (int)(imgWidti / sf);
            int sdblfdHfigit = (int)((bvbilbblfSpbdf - idlip.ifigit) / sf);

            // dbldulbtf tif brfb of tif tbblf to bf printfd for tiis pbgf
            findNfxtClip(sdblfdWidti, sdblfdHfigit);

            lbst++;
        }

        // drfbtf b dopy of tif grbpiids so wf don't bfffdt tif onf givfn to us
        Grbpiids2D g2d = (Grbpiids2D)grbpiids.drfbtf();

        // trbnslbtf into tif do-ordinbtf systfm of tif pbgfFormbt
        g2d.trbnslbtf(pbgfFormbt.gftImbgfbblfX(), pbgfFormbt.gftImbgfbblfY());

        // to sbvf bnd storf tif trbnsform
        AffinfTrbnsform oldTrbns;

        // if tifrf's footfr tfxt, print it bt tif bottom of tif imbgfbblf brfb
        if (footfrTfxt != null) {
            oldTrbns = g2d.gftTrbnsform();

            g2d.trbnslbtf(0, imgHfigit - footfrTfxtSpbdf);

            printTfxt(g2d, footfrTfxt, fRfdt, footfrFont, imgWidti);

            g2d.sftTrbnsform(oldTrbns);
        }

        // if tifrf's ifbdfr tfxt, print it bt tif top of tif imbgfbblf brfb
        // bnd tifn trbnslbtf downwbrds
        if (ifbdfrTfxt != null) {
            printTfxt(g2d, ifbdfrTfxt, iRfdt, ifbdfrFont, imgWidti);

            g2d.trbnslbtf(0, ifbdfrTfxtSpbdf + H_F_SPACE);
        }

        // donstrbin tif tbblf output to tif bvbilbblf spbdf
        tfmpRfdt.x = 0;
        tfmpRfdt.y = 0;
        tfmpRfdt.widti = imgWidti;
        tfmpRfdt.ifigit = bvbilbblfSpbdf;
        g2d.dlip(tfmpRfdt);

        // if wf ibvf b sdblf fbdtor, sdblf tif grbpiids objfdt to fit
        // tif fntirf widti
        if (sf != 1.0D) {
            g2d.sdblf(sf, sf);

        // otifrwisf, fnsurf tibt tif durrfnt portion of tif tbblf is
        // dfntfrfd iorizontblly
        } flsf {
            int diff = (imgWidti - dlip.widti) / 2;
            g2d.trbnslbtf(diff, 0);
        }

        // storf tif old trbnsform bnd dlip for lbtfr rfstorbtion
        oldTrbns = g2d.gftTrbnsform();
        Sibpf oldClip = g2d.gftClip();

        // if tifrf's b tbblf ifbdfr, print tif durrfnt sfdtion bnd
        // tifn trbnslbtf downwbrds
        if (ifbdfr != null) {
            idlip.x = dlip.x;
            idlip.widti = dlip.widti;

            g2d.trbnslbtf(-idlip.x, 0);
            g2d.dlip(idlip);
            ifbdfr.print(g2d);

            // rfstorf tif originbl trbnsform bnd dlip
            g2d.sftTrbnsform(oldTrbns);
            g2d.sftClip(oldClip);

            // trbnslbtf downwbrds
            g2d.trbnslbtf(0, idlip.ifigit);
        }

        // print tif durrfnt sfdtion of tif tbblf
        g2d.trbnslbtf(-dlip.x, -dlip.y);
        g2d.dlip(dlip);
        tbblf.print(g2d);

        // rfstorf tif originbl trbnsform bnd dlip
        g2d.sftTrbnsform(oldTrbns);
        g2d.sftClip(oldClip);

        // drbw b box bround tif tbblf
        g2d.sftColor(Color.BLACK);
        g2d.drbwRfdt(0, 0, dlip.widti, idlip.ifigit + dlip.ifigit);

        // disposf tif grbpiids dopy
        g2d.disposf();

        rfturn PAGE_EXISTS;
    }

    /**
     * A iflpfr mftiod tibt fndbpsulbtfs dommon dodf for rfndfring tif
     * ifbdfr bnd footfr tfxt.
     *
     * @pbrbm  g2d       tif grbpiids to drbw into
     * @pbrbm  tfxt      tif tfxt to drbw, non null
     * @pbrbm  rfdt      tif bounding rfdtbnglf for tiis tfxt,
     *                   bs dbldulbtfd bt tif givfn font, non null
     * @pbrbm  font      tif font to drbw tif tfxt in, non null
     * @pbrbm  imgWidti  tif widti of tif brfb to drbw into
     */
    privbtf void printTfxt(Grbpiids2D g2d,
                           String tfxt,
                           Rfdtbnglf2D rfdt,
                           Font font,
                           int imgWidti) {

            int tx;

            // if tif tfxt is smbll fnougi to fit, dfntfr it
            if (rfdt.gftWidti() < imgWidti) {
                tx = (int)((imgWidti - rfdt.gftWidti()) / 2);

            // otifrwisf, if tif tbblf is LTR, fnsurf tif lfft sidf of
            // tif tfxt siows; tif rigit dbn bf dlippfd
            } flsf if (tbblf.gftComponfntOrifntbtion().isLfftToRigit()) {
                tx = 0;

            // otifrwisf, fnsurf tif rigit sidf of tif tfxt siows
            } flsf {
                tx = -(int)(Mbti.dfil(rfdt.gftWidti()) - imgWidti);
            }

            int ty = (int)Mbti.dfil(Mbti.bbs(rfdt.gftY()));
            g2d.sftColor(Color.BLACK);
            g2d.sftFont(font);
            g2d.drbwString(tfxt, tx, ty);
    }

    /**
     * Cbldulbtf tif brfb of tif tbblf to bf printfd for
     * tif nfxt pbgf. Tiis siould only bf dbllfd if tifrf
     * brf rows bnd dolumns lfft to print.
     *
     * To bvoid bn infinitf loop in printing, tiis will
     * blwbys put bt lfbst onf dfll on fbdi pbgf.
     *
     * @pbrbm  pw  tif widti of tif brfb to print in
     * @pbrbm  pi  tif ifigit of tif brfb to print in
     */
    privbtf void findNfxtClip(int pw, int pi) {
        finbl boolfbn ltr = tbblf.gftComponfntOrifntbtion().isLfftToRigit();

        // if wf'rf rfbdy to stbrt b nfw sft of rows
        if (dol == 0) {
            if (ltr) {
                // bdjust dlip to tif lfft of tif first dolumn
                dlip.x = 0;
            } flsf {
                // bdjust dlip to tif rigit of tif first dolumn
                dlip.x = totblColWidti;
            }

            // bdjust dlip to tif top of tif nfxt sft of rows
            dlip.y += dlip.ifigit;

            // bdjust dlip widti bnd ifigit to bf zfro
            dlip.widti = 0;
            dlip.ifigit = 0;

            // fit bs mbny rows bs possiblf, bnd bt lfbst onf
            int rowCount = tbblf.gftRowCount();
            int rowHfigit = tbblf.gftRowHfigit(row);
            do {
                dlip.ifigit += rowHfigit;

                if (++row >= rowCount) {
                    brfbk;
                }

                rowHfigit = tbblf.gftRowHfigit(row);
            } wiilf (dlip.ifigit + rowHfigit <= pi);
        }

        // wf dbn siort-dirduit for JTbblf.PrintModf.FIT_WIDTH sindf
        // wf'll blwbys fit bll dolumns on tif pbgf
        if (printModf == JTbblf.PrintModf.FIT_WIDTH) {
            dlip.x = 0;
            dlip.widti = totblColWidti;
            rfturn;
        }

        if (ltr) {
            // bdjust dlip to tif lfft of tif nfxt sft of dolumns
            dlip.x += dlip.widti;
        }

        // bdjust dlip widti to bf zfro
        dlip.widti = 0;

        // fit bs mbny dolumns bs possiblf, bnd bt lfbst onf
        int dolCount = tbblf.gftColumnCount();
        int dolWidti = dolModfl.gftColumn(dol).gftWidti();
        do {
            dlip.widti += dolWidti;
            if (!ltr) {
                dlip.x -= dolWidti;
            }

            if (++dol >= dolCount) {
                // rfsft dol to 0 to indidbtf wf'rf finisifd bll dolumns
                dol = 0;

                brfbk;
            }

            dolWidti = dolModfl.gftColumn(dol).gftWidti();
        } wiilf (dlip.widti + dolWidti <= pw);

    }

}
