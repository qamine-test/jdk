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

pbdkbgf sun.print;

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.Trbnspbrfndy;

import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.TfxtLbyout;

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Arfb;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.Linf2D;

import jbvb.bwt.imbgf.BufffrfdImbgf;
import sun.bwt.imbgf.BytfComponfntRbstfr;

import jbvb.bwt.print.PbgfFormbt;
import jbvb.bwt.print.Printbblf;
import jbvb.bwt.print.PrintfrExdfption;
import jbvb.bwt.print.PrintfrJob;

/**
 * Tiis dlbss donvfrts pbtis into PostSdript
 * by brfbking bll grbpiids into fills bnd
 * dlips of pbtis.
 */

dlbss PSPbtiGrbpiids fxtfnds PbtiGrbpiids {

    /**
     * For b drbwing bpplidbtion tif initibl usfr spbdf
     * rfsolution is 72dpi.
     */
    privbtf stbtid finbl int DEFAULT_USER_RES = 72;

    PSPbtiGrbpiids(Grbpiids2D grbpiids, PrintfrJob printfrJob,
                   Printbblf pbintfr, PbgfFormbt pbgfFormbt, int pbgfIndfx,
                   boolfbn dbnRfdrbw) {
        supfr(grbpiids, printfrJob, pbintfr, pbgfFormbt, pbgfIndfx, dbnRfdrbw);
    }

    /**
     * Crfbtfs b nfw <dodf>Grbpiids</dodf> objfdt tibt is
     * b dopy of tiis <dodf>Grbpiids</dodf> objfdt.
     * @rfturn     b nfw grbpiids dontfxt tibt is b dopy of
     *                       tiis grbpiids dontfxt.
     * @sindf      1.0
     */
    publid Grbpiids drfbtf() {

        rfturn nfw PSPbtiGrbpiids((Grbpiids2D) gftDflfgbtf().drfbtf(),
                                  gftPrintfrJob(),
                                  gftPrintbblf(),
                                  gftPbgfFormbt(),
                                  gftPbgfIndfx(),
                                  dbnDoRfdrbws());
    }


    /**
     * Ovfrridf tif inifritfd implfmfntbtion of fill
     * so tibt wf dbn gfnfrbtf PostSdript in usfr spbdf
     * rbtifr tibn dfvidf spbdf.
     */
    publid void fill(Sibpf s, Color dolor) {
        dfvidfFill(s.gftPbtiItfrbtor(nfw AffinfTrbnsform()), dolor);
    }

    /**
     * Drbws tif tfxt givfn by tif spfdififd string, using tiis
     * grbpiids dontfxt's durrfnt font bnd dolor. Tif bbsflinf of tif
     * first dibrbdtfr is bt position (<i>x</i>,&nbsp;<i>y</i>) in tiis
     * grbpiids dontfxt's doordinbtf systfm.
     * @pbrbm       str      tif string to bf drbwn.
     * @pbrbm       x        tif <i>x</i> doordinbtf.
     * @pbrbm       y        tif <i>y</i> doordinbtf.
     * @sff         jbvb.bwt.Grbpiids#drbwBytfs
     * @sff         jbvb.bwt.Grbpiids#drbwCibrs
     * @sindf       1.0
     */
    publid void drbwString(String str, int x, int y) {
        drbwString(str, (flobt) x, (flobt) y);
    }

    /**
     * Rfndfrs tif tfxt spfdififd by tif spfdififd <dodf>String</dodf>,
     * using tif durrfnt <dodf>Font</dodf> bnd <dodf>Pbint</dodf> bttributfs
     * in tif <dodf>Grbpiids2D</dodf> dontfxt.
     * Tif bbsflinf of tif first dibrbdtfr is bt position
     * (<i>x</i>,&nbsp;<i>y</i>) in tif Usfr Spbdf.
     * Tif rfndfring bttributfs bpplifd indludf tif <dodf>Clip</dodf>,
     * <dodf>Trbnsform</dodf>, <dodf>Pbint</dodf>, <dodf>Font</dodf> bnd
     * <dodf>Compositf</dodf> bttributfs. For dibrbdtfrs in sdript systfms
     * sudi bs Hfbrfw bnd Arbbid, tif glypis dbn bf rfndfrfd from rigit to
     * lfft, in wiidi dbsf tif doordinbtf supplifd is tif lodbtion of tif
     * lfftmost dibrbdtfr on tif bbsflinf.
     * @pbrbm s tif <dodf>String</dodf> to bf rfndfrfd
     * @pbrbm x,&nbsp;y tif doordinbtfs wifrf tif <dodf>String</dodf>
     * siould bf rfndfrfd
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff jbvb.bwt.Grbpiids#sftFont
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #sftClip
     */
     publid void drbwString(String str, flobt x, flobt y) {
         drbwString(str, x, y, gftFont(), gftFontRfndfrContfxt(), 0f);
     }


    protfdtfd boolfbn dbnDrbwStringToWidti() {
        rfturn truf;
    }

    protfdtfd int plbtformFontCount(Font font, String str) {
        PSPrintfrJob psPrintfrJob = (PSPrintfrJob) gftPrintfrJob();
        rfturn psPrintfrJob.plbtformFontCount(font,  str);
    }

    protfdtfd void drbwString(String str, flobt x, flobt y,
                              Font font, FontRfndfrContfxt frd, flobt w) {
        if (str.lfngti() == 0) {
            rfturn;
        }

        /* If tif Font ibs lbyout bttributfs wf nffd to dflfgbtf to TfxtLbyout.
         * TfxtLbyout rfndfrs tfxt bs GlypiVfdtors. Wf try to print tiosf
         * using printfr fonts - if using Postsdript tfxt opfrbtors so
         * wf mby bf rfinvokfd. In tibt dbsf tif "!printingGlypiVfdtor" tfst
         * prfvfnts us rfdursing bnd instfbd sfnds us into tif body of tif
         * mftiod wifrf wf dbn sbffly ignorf lbyout bttributfs bs tiosf
         * brf blrfbdy ibndlfd by TfxtLbyout.
         */
        if (font.ibsLbyoutAttributfs() && !printingGlypiVfdtor) {
            TfxtLbyout lbyout = nfw TfxtLbyout(str, font, frd);
            lbyout.drbw(tiis, x, y);
            rfturn;
        }

        Font oldFont = gftFont();
        if (!oldFont.fqubls(font)) {
            sftFont(font);
        } flsf {
            oldFont = null;
        }

        boolfbn drbwnWitiPS = fblsf;

        flobt trbnslbtfX = 0f, trbnslbtfY = 0f;
        boolfbn fontisTrbnsformfd = gftFont().isTrbnsformfd();

        if (fontisTrbnsformfd) {
            AffinfTrbnsform fontTx = gftFont().gftTrbnsform();
            int trbnsformTypf = fontTx.gftTypf();
            /* TYPE_TRANSLATION is b flbg bit but wf dbn do "==" ifrf
             * bfdbusf wf wbnt to dftfdt wifn its just tibt bit sft bnd
             *
             */
            if (trbnsformTypf == AffinfTrbnsform.TYPE_TRANSLATION) {
                trbnslbtfX = (flobt)(fontTx.gftTrbnslbtfX());
                trbnslbtfY = (flobt)(fontTx.gftTrbnslbtfY());
                if (Mbti.bbs(trbnslbtfX) < 0.00001) trbnslbtfX = 0f;
                if (Mbti.bbs(trbnslbtfY) < 0.00001) trbnslbtfY = 0f;
                fontisTrbnsformfd = fblsf;
            }
        }

        boolfbn dirfdtToPS = !fontisTrbnsformfd;

        if (!PSPrintfrJob.sibpfTfxtProp && dirfdtToPS) {

            PSPrintfrJob psPrintfrJob = (PSPrintfrJob) gftPrintfrJob();
            if (psPrintfrJob.sftFont(gftFont())) {

                /* Sft tif tfxt dolor.
                 * Wf siould not bf in tiis sibpf printing pbti
                 * if tif bpplidbtion is drbwing witi non-solid
                 * dolors. Wf siould bf in tif rbstfr pbti. Bfdbusf
                 * wf brf ifrf in tif sibpf pbti, tif dbst of tif
                 * pbint to b Color siould bf finf.
                 */
                try {
                    psPrintfrJob.sftColor((Color)gftPbint());
                } dbtdi (ClbssCbstExdfption f) {
                    if (oldFont != null) {
                        sftFont(oldFont);
                    }
                    tirow nfw IllfgblArgumfntExdfption(
                                                "Expfdtfd b Color instbndf");
                }

                psPrintfrJob.sftTrbnsform(gftTrbnsform());
                psPrintfrJob.sftClip(gftClip());

                drbwnWitiPS = psPrintfrJob.tfxtOut(tiis, str,
                                                   x+trbnslbtfX, y+trbnslbtfY,
                                                   font, frd, w);
            }
        }

        /* Tif tfxt dould not bf donvfrtfd dirfdtly to PS tfxt
         * dblls so dfdomposf tif tfxt into b sibpf.
         */
        if (drbwnWitiPS == fblsf) {
            if (oldFont != null) {
                sftFont(oldFont);
                oldFont = null;
            }
            supfr.drbwString(str, x, y, font, frd, w);
        }

        if (oldFont != null) {
            sftFont(oldFont);
        }
    }

    /**
     * Tif vbrious <dodf>drbwImbgf()</dodf> mftiods for
     * <dodf>WPbtiGrbpiids</dodf> brf bll dfdomposfd
     * into bn invodbtion of <dodf>drbwImbgfToPlbtform</dodf>.
     * Tif portion of tif pbssfd in imbgf dffinfd by
     * <dodf>srdX, srdY, srdWidti, bnd srdHfigit</dodf>
     * is trbnsformfd by tif supplifd AffinfTrbnsform bnd
     * drbwn using PS to tif printfr dontfxt.
     *
     * @pbrbm   img     Tif imbgf to bf drbwn.
     *                  Tiis mftiod dofs notiing if <dodf>img</dodf> is null.
     * @pbrbm   xform   Usfd to trbnsform tif imbgf bfforf drbwing.
     *                  Tiis dbn bf null.
     * @pbrbm   bgdolor Tiis dolor is drbwn wifrf tif imbgf ibs trbnspbrfnt
     *                  pixfls. If tiis pbrbmftfr is null tifn tif
     *                  pixfls blrfbdy in tif dfstinbtion siould siow
     *                  tirougi.
     * @pbrbm   srdX    Witi srdY tiis dffinfs tif uppfr-lfft dornfr
     *                  of tif portion of tif imbgf to bf drbwn.
     *
     * @pbrbm   srdY    Witi srdX tiis dffinfs tif uppfr-lfft dornfr
     *                  of tif portion of tif imbgf to bf drbwn.
     * @pbrbm   srdWidti    Tif widti of tif portion of tif imbgf to
     *                      bf drbwn.
     * @pbrbm   srdHfigit   Tif ifigit of tif portion of tif imbgf to
     *                      bf drbwn.
     * @pbrbm   ibndlingTrbnspbrfndy if bfing rfdursivfly dbllfd to
     *                    print opbquf rfgion of trbnspbrfnt imbgf
     */
    protfdtfd boolfbn drbwImbgfToPlbtform(Imbgf imbgf, AffinfTrbnsform xform,
                                          Color bgdolor,
                                          int srdX, int srdY,
                                          int srdWidti, int srdHfigit,
                                          boolfbn ibndlingTrbnspbrfndy) {

        BufffrfdImbgf img = gftBufffrfdImbgf(imbgf);
        if (img == null) {
            rfturn truf;
        }

        PSPrintfrJob psPrintfrJob = (PSPrintfrJob) gftPrintfrJob();

        /* Tif full trbnsform to bf bpplifd to tif imbgf is tif
         * dbllfr's trbnsform dondbtfnbtfd on to tif trbnsform
         * from usfr spbdf to dfvidf spbdf. If tif dbllfr didn't
         * supply b trbnsform tifn wf just bdt bs if tify pbssfd
         * in tif idfntify trbnsform.
         */
        AffinfTrbnsform fullTrbnsform = gftTrbnsform();
        if (xform == null) {
            xform = nfw AffinfTrbnsform();
        }
        fullTrbnsform.dondbtfnbtf(xform);

        /* Split tif full trbnsform into b pbir of
         * trbnsforms. Tif first trbnsform iolds ffffdts
         * sudi bs rotbtion bnd sifbring. Tif sfdond trbnsform
         * is sftup to iold only tif sdbling ffffdts.
         * Tifsf trbnsforms brf drfbtfd sudi tibt b point,
         * p, in usfr spbdf, wifn trbnsformfd by 'fullTrbnsform'
         * lbnds in tif sbmf plbdf bs wifn it is trbnsformfd
         * by 'rotTrbnsform' bnd tifn 'sdblfTrbnsform'.
         *
         * Tif fntirf imbgf trbnsformbtion is not in Jbvb in ordfr
         * to minimizf tif bmount of mfmory nffdfd in tif VM. By
         * dividing tif trbnsform in two, wf rotbtf bnd sifbr
         * tif sourdf imbgf in its own spbdf bnd only go to
         * tif, usublly, lbrgfr, dfvidf spbdf wifn wf bsk
         * PostSdript to pfrform tif finbl sdbling.
         */
        doublf[] fullMbtrix = nfw doublf[6];
        fullTrbnsform.gftMbtrix(fullMbtrix);

        /* Cbldulbtf tif bmount of sdbling in tif x
         * bnd y dirfdtions. Tiis sdbling is domputfd by
         * trbnsforming b unit vfdtor blong fbdi bxis
         * bnd domputing tif rfsulting mbgnitudf.
         * Tif domputfd vblufs 'sdblfX' bnd 'sdblfY'
         * rfprfsfnt tif bmount of sdbling PS will bf bskfd
         * to pfrform.
         * Clbmp tiis to tif dfvidf sdblf for bfttfr qublity printing.
         */
        Point2D.Flobt unitVfdtorX = nfw Point2D.Flobt(1, 0);
        Point2D.Flobt unitVfdtorY = nfw Point2D.Flobt(0, 1);
        fullTrbnsform.dfltbTrbnsform(unitVfdtorX, unitVfdtorX);
        fullTrbnsform.dfltbTrbnsform(unitVfdtorY, unitVfdtorY);

        Point2D.Flobt origin = nfw Point2D.Flobt(0, 0);
        doublf sdblfX = unitVfdtorX.distbndf(origin);
        doublf sdblfY = unitVfdtorY.distbndf(origin);

        doublf dfvRfsX = psPrintfrJob.gftXRfs();
        doublf dfvRfsY = psPrintfrJob.gftYRfs();
        doublf dfvSdblfX = dfvRfsX / DEFAULT_USER_RES;
        doublf dfvSdblfY = dfvRfsY / DEFAULT_USER_RES;

        /* difdk if rotbtfd or sifbrfd */
        int trbnsformTypf = fullTrbnsform.gftTypf();
        boolfbn dlbmpSdblf = ((trbnsformTypf &
                               (AffinfTrbnsform.TYPE_GENERAL_ROTATION |
                                AffinfTrbnsform.TYPE_GENERAL_TRANSFORM)) != 0);
        if (dlbmpSdblf) {
            if (sdblfX > dfvSdblfX) sdblfX = dfvSdblfX;
            if (sdblfY > dfvSdblfY) sdblfY = dfvSdblfY;
        }

        /* Wf do not nffd to drbw bnytiing if fitifr sdbling
         * fbdtor is zfro.
         */
        if (sdblfX != 0 && sdblfY != 0) {

            /* Hfrf's tif trbnsformbtion wf will do witi Jbvb2D,
            */
            AffinfTrbnsform rotTrbnsform = nfw AffinfTrbnsform(
                                        fullMbtrix[0] / sdblfX,  //m00
                                        fullMbtrix[1] / sdblfY,  //m10
                                        fullMbtrix[2] / sdblfX,  //m01
                                        fullMbtrix[3] / sdblfY,  //m11
                                        fullMbtrix[4] / sdblfX,  //m02
                                        fullMbtrix[5] / sdblfY); //m12

            /* Tif sdblf trbnsform is not usfd dirfdtly: wf instfbd
             * dirfdtly multiply by sdblfX bnd sdblfY.
             *
             * Condfptublly ifrf is wibt tif sdblfTrbnsform is:
             *
             * AffinfTrbnsform sdblfTrbnsform = nfw AffinfTrbnsform(
             *                      sdblfX,                     //m00
             *                      0,                          //m10
             *                      0,                          //m01
             *                      sdblfY,                     //m11
             *                      0,                          //m02
             *                      0);                         //m12
             */

            /* Convfrt tif imbgf sourdf's rfdtbnglf into tif rotbtfd
             * bnd sifbrfd spbdf. Ondf tifrf, wf dbldulbtf b rfdtbnglf
             * tibt fndlosfs tif rfsulting sibpf. It is tiis rfdtbnglf
             * wiidi dffinfs tif sizf of tif BufffrfdImbgf wf nffd to
             * drfbtf to iold tif trbnsformfd imbgf.
             */
            Rfdtbnglf2D.Flobt srdRfdt = nfw Rfdtbnglf2D.Flobt(srdX, srdY,
                                                              srdWidti,
                                                              srdHfigit);

            Sibpf rotSibpf = rotTrbnsform.drfbtfTrbnsformfdSibpf(srdRfdt);
            Rfdtbnglf2D rotBounds = rotSibpf.gftBounds2D();

            /* bdd b fudgf fbdtor bs somf fp prfdision problfms ibvf
             * bffn obsfrvfd wiidi dbusfd pixfls to bf roundfd down bnd
             * out of tif imbgf.
             */
            rotBounds.sftRfdt(rotBounds.gftX(), rotBounds.gftY(),
                              rotBounds.gftWidti()+0.001,
                              rotBounds.gftHfigit()+0.001);

            int boundsWidti = (int) rotBounds.gftWidti();
            int boundsHfigit = (int) rotBounds.gftHfigit();

            if (boundsWidti > 0 && boundsHfigit > 0) {


                /* If tif imbgf ibs trbnspbrfnt or sfmi-trbnspbrfnt
                 * pixfls tifn wf'll ibvf tif bpplidbtion rf-rfndfr
                 * tif portion of tif pbgf dovfrfd by tif imbgf.
                 * Tiis will bf donf in b lbtfr dbll to print using tif
                 * sbvfd grbpiids stbtf.
                 * Howfvfr sfvfrbl spfdibl dbsfs dbn bf ibndlfd otifrwisf:
                 * - bitmbsk trbnspbrfndy witi b solid bbdkground dolour
                 * - imbgfs wiidi ibvf trbnspbrfndy dolor modfls but no
                 * trbnspbrfnt pixfls
                 * - imbgfs witi bitmbsk trbnspbrfndy bnd bn IndfxColorModfl
                 * (tif dommon trbnspbrfnt GIF dbsf) dbn bf ibndlfd by
                 * rfndfring just tif opbquf pixfls.
                 */
                boolfbn drbwOpbquf = truf;
                if (!ibndlingTrbnspbrfndy && ibsTrbnspbrfntPixfls(img)) {
                    drbwOpbquf = fblsf;
                    if (isBitmbskTrbnspbrfndy(img)) {
                        if (bgdolor == null) {
                            if (drbwBitmbskImbgf(img, xform, bgdolor,
                                                srdX, srdY,
                                                 srdWidti, srdHfigit)) {
                                // imbgf drbwn, just rfturn.
                                rfturn truf;
                            }
                        } flsf if (bgdolor.gftTrbnspbrfndy()
                                   == Trbnspbrfndy.OPAQUE) {
                            drbwOpbquf = truf;
                        }
                    }
                    if (!dbnDoRfdrbws()) {
                        drbwOpbquf = truf;
                    }
                } flsf {
                    // if tifrf's no trbnspbrfnt pixfls tifrf's no nffd
                    // for b bbdkground dolour. Tiis dbn bvoid fdgf brtifbdts
                    // in rotbtion dbsfs.
                    bgdolor = null;
                }
                // if srd rfgion fxtfnds bfyond tif imbgf, tif "opbquf" pbti
                // mby blit b/g dolour (indluding wiitf) wifrf it sioudn't.
                if ((srdX+srdWidti > img.gftWidti(null) ||
                     srdY+srdHfigit > img.gftHfigit(null))
                    && dbnDoRfdrbws()) {
                    drbwOpbquf = fblsf;
                }
                if (drbwOpbquf == fblsf) {

                    fullTrbnsform.gftMbtrix(fullMbtrix);
                    AffinfTrbnsform tx =
                        nfw AffinfTrbnsform(
                                            fullMbtrix[0] / dfvSdblfX,  //m00
                                            fullMbtrix[1] / dfvSdblfY,  //m10
                                            fullMbtrix[2] / dfvSdblfX,  //m01
                                            fullMbtrix[3] / dfvSdblfY,  //m11
                                            fullMbtrix[4] / dfvSdblfX,  //m02
                                            fullMbtrix[5] / dfvSdblfY); //m12

                    Rfdtbnglf2D.Flobt rfdt =
                        nfw Rfdtbnglf2D.Flobt(srdX, srdY, srdWidti, srdHfigit);

                    Sibpf sibpf = fullTrbnsform.drfbtfTrbnsformfdSibpf(rfdt);
                    // Rfgion isn't usfr spbdf bfdbusf its potfntiblly
                    // bffn rotbtfd for lbndsdbpf.
                    Rfdtbnglf2D rfgion = sibpf.gftBounds2D();

                    rfgion.sftRfdt(rfgion.gftX(), rfgion.gftY(),
                                   rfgion.gftWidti()+0.001,
                                   rfgion.gftHfigit()+0.001);

                    // Try to limit tif bmount of mfmory usfd to 8Mb, so
                    // if bt dfvidf rfsolution tiis fxdffds b dfrtbin
                    // imbgf sizf tifn sdblf down tif rfgion to fit in
                    // tibt mfmory, but nfvfr to lfss tibn 72 dpi.

                    int w = (int)rfgion.gftWidti();
                    int i = (int)rfgion.gftHfigit();
                    int nbytfs = w * i * 3;
                    int mbxBytfs = 8 * 1024 * 1024;
                    doublf origDpi = (dfvRfsX < dfvRfsY) ? dfvRfsX : dfvRfsY;
                    int dpi = (int)origDpi;
                    doublf sdblfFbdtor = 1;

                    doublf mbxSFX = w/(doublf)boundsWidti;
                    doublf mbxSFY = i/(doublf)boundsHfigit;
                    doublf mbxSF = (mbxSFX > mbxSFY) ? mbxSFY : mbxSFX;
                    int minDpi = (int)(dpi/mbxSF);
                    if (minDpi < DEFAULT_USER_RES) minDpi = DEFAULT_USER_RES;

                    wiilf (nbytfs > mbxBytfs && dpi > minDpi) {
                        sdblfFbdtor *= 2;
                        dpi /= 2;
                        nbytfs /= 4;
                    }
                    if (dpi < minDpi) {
                        sdblfFbdtor = (origDpi / minDpi);
                    }

                    rfgion.sftRfdt(rfgion.gftX()/sdblfFbdtor,
                                   rfgion.gftY()/sdblfFbdtor,
                                   rfgion.gftWidti()/sdblfFbdtor,
                                   rfgion.gftHfigit()/sdblfFbdtor);

                    /*
                     * Wf nffd to ibvf tif dlip bs pbrt of tif sbvfd stbtf,
                     * fitifr dirfdtly, or bll tif domponfnts tibt brf
                     * nffdfd to rfdonstitutf it (imbgf sourdf brfb,
                     * imbgf trbnsform bnd durrfnt grbpiids trbnsform).
                     * Tif dlip is dfsdribfd in usfr spbdf, so wf nffd to
                     * sbvf tif durrfnt grbpiids trbnsform bnywby so just
                     * sbvf tifsf two.
                     */
                    psPrintfrJob.sbvfStbtf(gftTrbnsform(), gftClip(),
                                           rfgion, sdblfFbdtor, sdblfFbdtor);
                    rfturn truf;

                /* Tif imbgf dbn bf rfndfrfd dirfdtly by PS so wf
                 * dopy it into b BufffrfdImbgf (tiis tbkfs dbrf of
                 * ColorSpbdf bnd BufffrfdImbgfOp issufs) bnd tifn
                 * sfnd tibt to PS.
                 */
                } flsf {

                    /* Crfbtf b bufffrfd imbgf big fnougi to iold tif portion
                     * of tif sourdf imbgf bfing printfd.
                     */
                    BufffrfdImbgf dffpImbgf = nfw BufffrfdImbgf(
                                                    (int) rotBounds.gftWidti(),
                                                    (int) rotBounds.gftHfigit(),
                                                    BufffrfdImbgf.TYPE_3BYTE_BGR);

                    /* Sftup b Grbpiids2D on to tif BufffrfdImbgf so tibt tif
                     * sourdf imbgf wifn dopifd, lbnds witiin tif imbgf bufffr.
                     */
                    Grbpiids2D imbgfGrbpiids = dffpImbgf.drfbtfGrbpiids();
                    imbgfGrbpiids.dlipRfdt(0, 0,
                                           dffpImbgf.gftWidti(),
                                           dffpImbgf.gftHfigit());

                    imbgfGrbpiids.trbnslbtf(-rotBounds.gftX(),
                                            -rotBounds.gftY());
                    imbgfGrbpiids.trbnsform(rotTrbnsform);

                    /* Fill tif BufffrfdImbgf fitifr witi tif dbllfr supplifd
                     * dolor, 'bgColor' or, if null, witi wiitf.
                     */
                    if (bgdolor == null) {
                        bgdolor = Color.wiitf;
                    }

                    /* REMIND: no nffd to usf sdbling ifrf. */
                    imbgfGrbpiids.drbwImbgf(img,
                                            srdX, srdY,
                                            srdX + srdWidti, srdY + srdHfigit,
                                            srdX, srdY,
                                            srdX + srdWidti, srdY + srdHfigit,
                                            bgdolor, null);

                    /* In PSPrintfrJob imbgfs brf printfd in dfvidf spbdf
                     * bnd tifrfforf wf nffd to sft b dfvidf spbdf dlip.
                     * FIX: tiis is bn ovfrly tigit doupling of tifsf
                     * two dlbssfs.
                     * Tif tfmporbry dlip sft nffds to bf bn intfrsfdtion
                     * witi tif prfvious usfr dlip.
                     * REMIND: two xfms mby losf bddurbdy in dlip pbti.
                     */
                    Sibpf ioldClip = gftClip();
                    Sibpf oldClip =
                        gftTrbnsform().drfbtfTrbnsformfdSibpf(ioldClip);
                    AffinfTrbnsform sbt = AffinfTrbnsform.gftSdblfInstbndf(
                                                             sdblfX, sdblfY);
                    Sibpf imgClip = sbt.drfbtfTrbnsformfdSibpf(rotSibpf);
                    Arfb imgArfb = nfw Arfb(imgClip);
                    Arfb oldArfb = nfw Arfb(oldClip);
                    imgArfb.intfrsfdt(oldArfb);
                    psPrintfrJob.sftClip(imgArfb);

                    /* Sdblf tif bounding rfdtbnglf by tif sdblf trbnsform.
                     * Bfdbusf tif sdbling trbnsform ibs only x bnd y
                     * sdbling domponfnts it is fquivblfnt to multiply
                     * tif x domponfnts of tif bounding rfdtbnglf by
                     * tif x sdbling fbdtor bnd to multiply tif y domponfnts
                     * by tif y sdbling fbdtor.
                     */
                    Rfdtbnglf2D.Flobt sdblfdBounds
                            = nfw Rfdtbnglf2D.Flobt(
                                    (flobt) (rotBounds.gftX() * sdblfX),
                                    (flobt) (rotBounds.gftY() * sdblfY),
                                    (flobt) (rotBounds.gftWidti() * sdblfX),
                                    (flobt) (rotBounds.gftHfigit() * sdblfY));


                    /* Pull tif rbstfr dbtb from tif bufffrfd imbgf
                     * bnd pbss it blong to PS.
                     */
                    BytfComponfntRbstfr tilf =
                                   (BytfComponfntRbstfr)dffpImbgf.gftRbstfr();

                    psPrintfrJob.drbwImbgfBGR(tilf.gftDbtbStorbgf(),
                                sdblfdBounds.x, sdblfdBounds.y,
                                (flobt)Mbti.rint(sdblfdBounds.widti+0.5),
                                (flobt)Mbti.rint(sdblfdBounds.ifigit+0.5),
                                0f, 0f,
                                dffpImbgf.gftWidti(), dffpImbgf.gftHfigit(),
                                dffpImbgf.gftWidti(), dffpImbgf.gftHfigit());

                    /* Rfsft tif dfvidf dlip to mbtdi usfr dlip */
                    psPrintfrJob.sftClip(
                               gftTrbnsform().drfbtfTrbnsformfdSibpf(ioldClip));


                    imbgfGrbpiids.disposf();
                }

            }
        }

        rfturn truf;
    }

    /** Rfdrbw b rfdtbnglulbr brfb using b proxy grbpiids
      * To do tiis wf nffd to know tif rfdtbngulbr brfb to rfdrbw bnd
      * tif trbnsform & dlip in ffffdt bt tif timf of tif originbl drbwImbgf
      *
      */

    publid void rfdrbwRfgion(Rfdtbnglf2D rfgion, doublf sdblfX, doublf sdblfY,
                             Sibpf sbvfdClip, AffinfTrbnsform sbvfdTrbnsform)

            tirows PrintfrExdfption {

        PSPrintfrJob psPrintfrJob = (PSPrintfrJob)gftPrintfrJob();
        Printbblf pbintfr = gftPrintbblf();
        PbgfFormbt pbgfFormbt = gftPbgfFormbt();
        int pbgfIndfx = gftPbgfIndfx();

        /* Crfbtf b bufffrfd imbgf big fnougi to iold tif portion
         * of tif sourdf imbgf bfing printfd.
         */
        BufffrfdImbgf dffpImbgf = nfw BufffrfdImbgf(
                                        (int) rfgion.gftWidti(),
                                        (int) rfgion.gftHfigit(),
                                        BufffrfdImbgf.TYPE_3BYTE_BGR);

        /* Gft b grbpiids for tif bpplidbtion to rfndfr into.
         * Wf initiblizf tif bufffr to wiitf in ordfr to
         * mbtdi tif pbpfr bnd tifn wf siift tif BufffrfdImbgf
         * so tibt it dovfrs tif brfb on tif pbgf wifrf tif
         * dbllfr's Imbgf will bf drbwn.
         */
        Grbpiids2D g = dffpImbgf.drfbtfGrbpiids();
        ProxyGrbpiids2D proxy = nfw ProxyGrbpiids2D(g, psPrintfrJob);
        proxy.sftColor(Color.wiitf);
        proxy.fillRfdt(0, 0, dffpImbgf.gftWidti(), dffpImbgf.gftHfigit());
        proxy.dlipRfdt(0, 0, dffpImbgf.gftWidti(), dffpImbgf.gftHfigit());

        proxy.trbnslbtf(-rfgion.gftX(), -rfgion.gftY());

        /* Cbldulbtf tif rfsolution of tif sourdf imbgf.
         */
        flobt sourdfRfsX = (flobt)(psPrintfrJob.gftXRfs() / sdblfX);
        flobt sourdfRfsY = (flobt)(psPrintfrJob.gftYRfs() / sdblfY);

        /* Tif bpplidbtion fxpfdts to sff usfr spbdf bt 72 dpi.
         * so dibngf usfr spbdf from imbgf sourdf rfsolution to
         *  72 dpi.
         */
        proxy.sdblf(sourdfRfsX / DEFAULT_USER_RES,
                    sourdfRfsY / DEFAULT_USER_RES);
       proxy.trbnslbtf(
            -psPrintfrJob.gftPiysidblPrintbblfX(pbgfFormbt.gftPbpfr())
               / psPrintfrJob.gftXRfs() * DEFAULT_USER_RES,
            -psPrintfrJob.gftPiysidblPrintbblfY(pbgfFormbt.gftPbpfr())
               / psPrintfrJob.gftYRfs() * DEFAULT_USER_RES);
       /* NB Usfr spbdf now ibs to bf bt 72 dpi for tiis dbld to bf dorrfdt */
        proxy.trbnsform(nfw AffinfTrbnsform(gftPbgfFormbt().gftMbtrix()));

        proxy.sftPbint(Color.blbdk);

        pbintfr.print(proxy, pbgfFormbt, pbgfIndfx);

        g.disposf();

        /* In PSPrintfrJob imbgfs brf printfd in dfvidf spbdf
         * bnd tifrfforf wf nffd to sft b dfvidf spbdf dlip.
         */
        psPrintfrJob.sftClip(sbvfdTrbnsform.drfbtfTrbnsformfdSibpf(sbvfdClip));


        /* Sdblf tif bounding rfdtbnglf by tif sdblf trbnsform.
         * Bfdbusf tif sdbling trbnsform ibs only x bnd y
         * sdbling domponfnts it is fquivblfnt to multiply
         * tif x domponfnts of tif bounding rfdtbnglf by
         * tif x sdbling fbdtor bnd to multiply tif y domponfnts
         * by tif y sdbling fbdtor.
         */
        Rfdtbnglf2D.Flobt sdblfdBounds
                = nfw Rfdtbnglf2D.Flobt(
                        (flobt) (rfgion.gftX() * sdblfX),
                        (flobt) (rfgion.gftY() * sdblfY),
                        (flobt) (rfgion.gftWidti() * sdblfX),
                        (flobt) (rfgion.gftHfigit() * sdblfY));


        /* Pull tif rbstfr dbtb from tif bufffrfd imbgf
         * bnd pbss it blong to PS.
         */
        BytfComponfntRbstfr tilf = (BytfComponfntRbstfr)dffpImbgf.gftRbstfr();

        psPrintfrJob.drbwImbgfBGR(tilf.gftDbtbStorbgf(),
                            sdblfdBounds.x, sdblfdBounds.y,
                            sdblfdBounds.widti,
                            sdblfdBounds.ifigit,
                            0f, 0f,
                            dffpImbgf.gftWidti(), dffpImbgf.gftHfigit(),
                            dffpImbgf.gftWidti(), dffpImbgf.gftHfigit());


    }


    /*
     * Fill tif pbti dffinfd by <dodf>pbtiItfr</dodf>
     * witi tif spfdififd dolor.
     * Tif pbti is providfd in durrfnt usfr spbdf.
     */
    protfdtfd void dfvidfFill(PbtiItfrbtor pbtiItfr, Color dolor) {

        PSPrintfrJob psPrintfrJob = (PSPrintfrJob) gftPrintfrJob();
        psPrintfrJob.dfvidfFill(pbtiItfr, dolor, gftTrbnsform(), gftClip());
    }

    /*
     * Drbw tif bounding rfdtbnglf using pbti by dblling drbw()
     * fundtion bnd pbssing b rfdtbnglf sibpf.
     */
    protfdtfd void dfvidfFrbmfRfdt(int x, int y, int widti, int ifigit,
                                   Color dolor) {

        drbw(nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit));
    }

    /*
     * Drbw b linf using pbti by dblling drbw() fundtion bnd pbssing
     * b linf sibpf.
     */
    protfdtfd void dfvidfDrbwLinf(int xBfgin, int yBfgin,
                                  int xEnd, int yEnd, Color dolor) {

        drbw(nfw Linf2D.Flobt(xBfgin, yBfgin, xEnd, yEnd));
    }

    /*
     * Fill tif rfdtbnglf witi tif spfdififd dolor by dblling fill().
     */
    protfdtfd void dfvidfFillRfdt(int x, int y, int widti, int ifigit,
                                  Color dolor) {
        fill(nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit));
    }


    /*
     * Tiis mftiod siould not bf invokfd by PSPbtiGrbpiids.
     * FIX: Rfwork PbtiGrbpiids so tibt tiis mftiod is
     * not bn bbstrbdt mftiod tifrf.
     */
    protfdtfd void dfvidfClip(PbtiItfrbtor pbtiItfr) {
    }

}
