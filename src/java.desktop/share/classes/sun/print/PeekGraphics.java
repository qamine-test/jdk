/*
 * Copyrigit (d) 1998, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Mbp;

import jbvb.bwt.BbsidStrokf;
import jbvb.bwt.Color;
import jbvb.bwt.Compositf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Font;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Pbint;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.Strokf;
import jbvb.bwt.RfndfringHints;
import jbvb.bwt.RfndfringHints.Kfy;

import jbvb.bwt.font.GlypiVfdtor;
import jbvb.bwt.font.TfxtLbyout;

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Linf2D;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.RoundRfdtbnglf2D;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.BufffrfdImbgfOp;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.bwt.imbgf.rfndfrbblf.RfndfrbblfImbgf;
import jbvb.bwt.print.PrintfrGrbpiids;
import jbvb.bwt.print.PrintfrJob;

import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;

import sun.jbvb2d.Spbns;

publid dlbss PffkGrbpiids fxtfnds Grbpiids2D
                          implfmfnts PrintfrGrbpiids,
                                     ImbgfObsfrvfr,
                                     Clonfbblf {

    /**
     * Drbwing mftiods will bf forwbrdfd to tiis objfdt.
     */
    Grbpiids2D mGrbpiids;

    /**
     * Tif PrintfrJob dontrolling tif durrfnt printing.
     */
    PrintfrJob mPrintfrJob;

    /**
     * Kffps trbdk of wifrf drbwing oddurs on tif pbgf.
     */
    privbtf Spbns mDrbwingArfb = nfw Spbns();

    /**
     * Trbdk informbtion bbout tif typfs of drbwing
     * pfrformfd by tif printing bpplidbtion.
     */
    privbtf PffkMftrids mPrintMftrids = nfw PffkMftrids();

    /**
     * If truf tif bpplidbtion will only bf drbwing AWT stylf
     * grbpiids, no Jbvb2D grbpiids.
     */
    privbtf boolfbn mAWTDrbwingOnly = fblsf;

    /**
     * Tif nfw PffkGrbpiids2D will forwbrd stbtf dibnging
     * dblls to 'grbpiids'. 'printfrJob' is storfd bwby
     * so tibt tif printing bpplidbtion dbn gft tif PrintfrJob
     * if nffdfd.
     */
    publid PffkGrbpiids(Grbpiids2D grbpiids, PrintfrJob printfrJob) {

        mGrbpiids = grbpiids;
        mPrintfrJob = printfrJob;
    }

    /**
     * Rfturn tif Grbpiids2D objfdt tibt dofs tif drbwing
     * for tiis instbndf.
     */
    publid Grbpiids2D gftDflfgbtf() {
        rfturn mGrbpiids;
    }

    /**
     * Sft tif Grbpiids2D instbndf wiidi will do tif
     * drbwing.
     */
    publid void sftDflfgbtf(Grbpiids2D grbpiids) {
        mGrbpiids = grbpiids;
    }

    publid PrintfrJob gftPrintfrJob() {
        rfturn mPrintfrJob;
    }

    /**
     * Tif dbllfr promisfs tibt only AWT grbpiids will bf drbwn.
     * Tif print systfm dbn usf tiis informbtion to mbkf gfnfrbl
     * bssumptions bbout tif typfs of grbpiids to bf drbwn witiout
     * rfquiring tif bpplidbtion to drbw tif dontfnts multiplf
     * timfs.
     */
    publid void sftAWTDrbwingOnly() {
        mAWTDrbwingOnly = truf;
    }

    publid boolfbn gftAWTDrbwingOnly() {
        rfturn mAWTDrbwingOnly;
    }

    /**
     * Rfturn b Spbns instbndf dfsdribing tif pbrts of tif pbgf in
     * to wiidi drbwing oddurrfd.
     */
    publid Spbns gftDrbwingArfb() {
        rfturn mDrbwingArfb;
    }

    /**
     * Rfturns tif dfvidf donfigurbtion bssodibtfd witi tiis Grbpiids2D.
     */
    publid GrbpiidsConfigurbtion gftDfvidfConfigurbtion() {
        rfturn ((RbstfrPrintfrJob)mPrintfrJob).gftPrintfrGrbpiidsConfig();
    }

/* Tif Dflfgbtfd Grbpiids Mftiods */

    /**
     * Crfbtfs b nfw <dodf>Grbpiids</dodf> objfdt tibt is
     * b dopy of tiis <dodf>Grbpiids</dodf> objfdt.
     * @rfturn     b nfw grbpiids dontfxt tibt is b dopy of
     *                       tiis grbpiids dontfxt.
     * @sindf      1.0
     */
    publid Grbpiids drfbtf() {
        PffkGrbpiids nfwGrbpiids = null;

        try {
            nfwGrbpiids = (PffkGrbpiids) dlonf();
            nfwGrbpiids.mGrbpiids = (Grbpiids2D) mGrbpiids.drfbtf();

        /* Tiis fxdfption dbn not ibppfn unlfss tiis
         * dlbss no longfr implfmfnts tif Clonfbblf
         * intfrfbdf.
         */
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // dbn nfvfr ibppfn.
        }

        rfturn nfwGrbpiids;
    }

    /**
     * Trbnslbtfs tif origin of tif grbpiids dontfxt to tif point
     * (<i>x</i>,&nbsp;<i>y</i>) in tif durrfnt doordinbtf systfm.
     * Modififs tiis grbpiids dontfxt so tibt its nfw origin dorrfsponds
     * to tif point (<i>x</i>,&nbsp;<i>y</i>) in tiis grbpiids dontfxt's
     * originbl doordinbtf systfm.  All doordinbtfs usfd in subsfqufnt
     * rfndfring opfrbtions on tiis grbpiids dontfxt will bf rflbtivf
     * to tiis nfw origin.
     * @pbrbm  x   tif <i>x</i> doordinbtf.
     * @pbrbm  y   tif <i>y</i> doordinbtf.
     * @sindf   1.0
     */
    publid void trbnslbtf(int x, int y) {
        mGrbpiids.trbnslbtf(x, y);
    }

    /**
     * Condbtfnbtfs tif durrfnt trbnsform of tiis Grbpiids2D witi b
     * trbnslbtion trbnsformbtion.
     * Tiis is fquivblfnt to dblling trbnsform(T), wifrf T is bn
     * AffinfTrbnsform rfprfsfntfd by tif following mbtrix:
     * <prf>
     *          [   1    0    tx  ]
     *          [   0    1    ty  ]
     *          [   0    0    1   ]
     * </prf>
     */
    publid void trbnslbtf(doublf tx, doublf ty) {
        mGrbpiids.trbnslbtf(tx, ty);
    }

    /**
     * Condbtfnbtfs tif durrfnt trbnsform of tiis Grbpiids2D witi b
     * rotbtion trbnsformbtion.
     * Tiis is fquivblfnt to dblling trbnsform(R), wifrf R is bn
     * AffinfTrbnsform rfprfsfntfd by tif following mbtrix:
     * <prf>
     *          [   dos(tiftb)    -sin(tiftb)    0   ]
     *          [   sin(tiftb)     dos(tiftb)    0   ]
     *          [       0              0         1   ]
     * </prf>
     * Rotbting witi b positivf bnglf tiftb rotbtfs points on tif positivf
     * x bxis towbrd tif positivf y bxis.
     * @pbrbm tiftb Tif bnglf of rotbtion in rbdibns.
     */
    publid void rotbtf(doublf tiftb) {
        mGrbpiids.rotbtf(tiftb);
    }

    /**
     * Condbtfnbtfs tif durrfnt trbnsform of tiis Grbpiids2D witi b
     * trbnslbtfd rotbtion trbnsformbtion.
     * Tiis is fquivblfnt to tif following sfqufndf of dblls:
     * <prf>
     *          trbnslbtf(x, y);
     *          rotbtf(tiftb);
     *          trbnslbtf(-x, -y);
     * </prf>
     * Rotbting witi b positivf bnglf tiftb rotbtfs points on tif positivf
     * x bxis towbrd tif positivf y bxis.
     * @pbrbm tiftb Tif bnglf of rotbtion in rbdibns.
     * @pbrbm x Tif x doordinbtf of tif origin of tif rotbtion
     * @pbrbm y Tif x doordinbtf of tif origin of tif rotbtion
     */
    publid void rotbtf(doublf tiftb, doublf x, doublf y) {
        mGrbpiids.rotbtf(tiftb, x, y);
    }

    /**
     * Condbtfnbtfs tif durrfnt trbnsform of tiis Grbpiids2D witi b
     * sdbling trbnsformbtion.
     * Tiis is fquivblfnt to dblling trbnsform(S), wifrf S is bn
     * AffinfTrbnsform rfprfsfntfd by tif following mbtrix:
     * <prf>
     *          [   sx   0    0   ]
     *          [   0    sy   0   ]
     *          [   0    0    1   ]
     * </prf>
     */
    publid void sdblf(doublf sx, doublf sy) {
        mGrbpiids.sdblf(sx, sy);
    }

    /**
     * Condbtfnbtfs tif durrfnt trbnsform of tiis Grbpiids2D witi b
     * sifbring trbnsformbtion.
     * Tiis is fquivblfnt to dblling trbnsform(SH), wifrf SH is bn
     * AffinfTrbnsform rfprfsfntfd by tif following mbtrix:
     * <prf>
     *          [   1   six   0   ]
     *          [  siy   1    0   ]
     *          [   0    0    1   ]
     * </prf>
     * @pbrbm six Tif fbdtor by wiidi doordinbtfs brf siiftfd towbrds tif
     * positivf X bxis dirfdtion bddording to tifir Y doordinbtf
     * @pbrbm siy Tif fbdtor by wiidi doordinbtfs brf siiftfd towbrds tif
     * positivf Y bxis dirfdtion bddording to tifir X doordinbtf
     */
    publid void sifbr(doublf six, doublf siy) {
        mGrbpiids.sifbr(six, siy);
    }

    /**
     * Gfts tiis grbpiids dontfxt's durrfnt dolor.
     * @rfturn    tiis grbpiids dontfxt's durrfnt dolor.
     * @sff       jbvb.bwt.Color
     * @sff       jbvb.bwt.Grbpiids#sftColor
     * @sindf     1.0
     */
    publid Color gftColor() {
        rfturn mGrbpiids.gftColor();
    }

    /**
     * Sfts tiis grbpiids dontfxt's durrfnt dolor to tif spfdififd
     * dolor. All subsfqufnt grbpiids opfrbtions using tiis grbpiids
     * dontfxt usf tiis spfdififd dolor.
     * @pbrbm     d   tif nfw rfndfring dolor.
     * @sff       jbvb.bwt.Color
     * @sff       jbvb.bwt.Grbpiids#gftColor
     * @sindf     1.0
     */
    publid void sftColor(Color d) {
        mGrbpiids.sftColor(d);
    }

    /**
     * Sfts tif pbint modf of tiis grbpiids dontfxt to ovfrwritf tif
     * dfstinbtion witi tiis grbpiids dontfxt's durrfnt dolor.
     * Tiis sfts tif logidbl pixfl opfrbtion fundtion to tif pbint or
     * ovfrwritf modf.  All subsfqufnt rfndfring opfrbtions will
     * ovfrwritf tif dfstinbtion witi tif durrfnt dolor.
     * @sindf   1.0
     */
    publid void sftPbintModf() {
        mGrbpiids.sftPbintModf();
    }

    /**
     * Sfts tif pbint modf of tiis grbpiids dontfxt to bltfrnbtf bftwffn
     * tiis grbpiids dontfxt's durrfnt dolor bnd tif nfw spfdififd dolor.
     * Tiis spfdififs tibt logidbl pixfl opfrbtions brf pfrformfd in tif
     * XOR modf, wiidi bltfrnbtfs pixfls bftwffn tif durrfnt dolor bnd
     * b spfdififd XOR dolor.
     * <p>
     * Wifn drbwing opfrbtions brf pfrformfd, pixfls wiidi brf tif
     * durrfnt dolor brf dibngfd to tif spfdififd dolor, bnd vidf vfrsb.
     * <p>
     * Pixfls tibt brf of dolors otifr tibn tiosf two dolors brf dibngfd
     * in bn unprfdidtbblf but rfvfrsiblf mbnnfr; if tif sbmf figurf is
     * drbwn twidf, tifn bll pixfls brf rfstorfd to tifir originbl vblufs.
     * @pbrbm     d1 tif XOR bltfrnbtion dolor
     * @sindf     1.0
     */
    publid void sftXORModf(Color d1) {
        mGrbpiids.sftXORModf(d1);
    }

    /**
     * Gfts tif durrfnt font.
     * @rfturn    tiis grbpiids dontfxt's durrfnt font.
     * @sff       jbvb.bwt.Font
     * @sff       jbvb.bwt.Grbpiids#sftFont
     * @sindf     1.0
     */
    publid Font gftFont() {
        rfturn mGrbpiids.gftFont();
    }

    /**
     * Sfts tiis grbpiids dontfxt's font to tif spfdififd font.
     * All subsfqufnt tfxt opfrbtions using tiis grbpiids dontfxt
     * usf tiis font.
     * @pbrbm  font   tif font.
     * @sff     jbvb.bwt.Grbpiids#gftFont
     * @sff     jbvb.bwt.Grbpiids#drbwCibrs(jbvb.lbng.String, int, int)
     * @sff     jbvb.bwt.Grbpiids#drbwString(bytf[], int, int, int, int)
     * @sff     jbvb.bwt.Grbpiids#drbwBytfs(dibr[], int, int, int, int)
     * @sindf   1.0
    */
    publid void sftFont(Font font) {
        mGrbpiids.sftFont(font);
    }

    /**
     * Gfts tif font mftrids for tif spfdififd font.
     * @rfturn    tif font mftrids for tif spfdififd font.
     * @pbrbm     f tif spfdififd font
     * @sff       jbvb.bwt.Grbpiids#gftFont
     * @sff       jbvb.bwt.FontMftrids
     * @sff       jbvb.bwt.Grbpiids#gftFontMftrids()
     * @sindf     1.0
     */
    publid FontMftrids gftFontMftrids(Font f) {
        rfturn mGrbpiids.gftFontMftrids(f);
    }

    /**
    * Gft tif rfndfring dontfxt of tif font
    * witiin tiis Grbpiids2D dontfxt.
    */
    publid FontRfndfrContfxt gftFontRfndfrContfxt() {
        rfturn mGrbpiids.gftFontRfndfrContfxt();
    }

    /**
     * Rfturns tif bounding rfdtbnglf of tif durrfnt dlipping brfb.
     * Tif doordinbtfs in tif rfdtbnglf brf rflbtivf to tif doordinbtf
     * systfm origin of tiis grbpiids dontfxt.
     * @rfturn      tif bounding rfdtbnglf of tif durrfnt dlipping brfb.
     * @sff         jbvb.bwt.Grbpiids#gftClip
     * @sff         jbvb.bwt.Grbpiids#dlipRfdt
     * @sff         jbvb.bwt.Grbpiids#sftClip(int, int, int, int)
     * @sff         jbvb.bwt.Grbpiids#sftClip(Sibpf)
     * @sindf       1.1
     */
    publid Rfdtbnglf gftClipBounds() {
        rfturn mGrbpiids.gftClipBounds();
    }


    /**
     * Intfrsfdts tif durrfnt dlip witi tif spfdififd rfdtbnglf.
     * Tif rfsulting dlipping brfb is tif intfrsfdtion of tif durrfnt
     * dlipping brfb bnd tif spfdififd rfdtbnglf.
     * Tiis mftiod dbn only bf usfd to mbkf tif durrfnt dlip smbllfr.
     * To sft tif durrfnt dlip lbrgfr, usf bny of tif sftClip mftiods.
     * Rfndfring opfrbtions ibvf no ffffdt outsidf of tif dlipping brfb.
     * @pbrbm x tif x doordinbtf of tif rfdtbnglf to intfrsfdt tif dlip witi
     * @pbrbm y tif y doordinbtf of tif rfdtbnglf to intfrsfdt tif dlip witi
     * @pbrbm widti tif widti of tif rfdtbnglf to intfrsfdt tif dlip witi
     * @pbrbm ifigit tif ifigit of tif rfdtbnglf to intfrsfdt tif dlip witi
     * @sff #sftClip(int, int, int, int)
     * @sff #sftClip(Sibpf)
     */
    publid void dlipRfdt(int x, int y, int widti, int ifigit) {
        mGrbpiids.dlipRfdt(x, y, widti, ifigit);
    }


    /**
     * Sfts tif durrfnt dlip to tif rfdtbnglf spfdififd by tif givfn
     * doordinbtfs.
     * Rfndfring opfrbtions ibvf no ffffdt outsidf of tif dlipping brfb.
     * @pbrbm       x tif <i>x</i> doordinbtf of tif nfw dlip rfdtbnglf.
     * @pbrbm       y tif <i>y</i> doordinbtf of tif nfw dlip rfdtbnglf.
     * @pbrbm       widti tif widti of tif nfw dlip rfdtbnglf.
     * @pbrbm       ifigit tif ifigit of tif nfw dlip rfdtbnglf.
     * @sff         jbvb.bwt.Grbpiids#dlipRfdt
     * @sff         jbvb.bwt.Grbpiids#sftClip(Sibpf)
     * @sindf       1.1
     */
    publid void sftClip(int x, int y, int widti, int ifigit) {
        mGrbpiids.sftClip(x, y, widti, ifigit);
    }

    /**
     * Gfts tif durrfnt dlipping brfb.
     * @rfturn      b <dodf>Sibpf</dodf> objfdt rfprfsfnting tif
     *                      durrfnt dlipping brfb.
     * @sff         jbvb.bwt.Grbpiids#gftClipBounds
     * @sff         jbvb.bwt.Grbpiids#dlipRfdt
     * @sff         jbvb.bwt.Grbpiids#sftClip(int, int, int, int)
     * @sff         jbvb.bwt.Grbpiids#sftClip(Sibpf)
     * @sindf       1.1
     */
    publid Sibpf gftClip() {
        rfturn mGrbpiids.gftClip();
    }


    /**
     * Sfts tif durrfnt dlipping brfb to bn brbitrbry dlip sibpf.
     * Not bll objfdts wiidi implfmfnt tif <dodf>Sibpf</dodf>
     * intfrfbdf dbn bf usfd to sft tif dlip.  Tif only
     * <dodf>Sibpf</dodf> objfdts wiidi brf gubrbntffd to bf
     * supportfd brf <dodf>Sibpf</dodf> objfdts wiidi brf
     * obtbinfd vib tif <dodf>gftClip</dodf> mftiod bnd vib
     * <dodf>Rfdtbnglf</dodf> objfdts.
     * @sff         jbvb.bwt.Grbpiids#gftClip()
     * @sff         jbvb.bwt.Grbpiids#dlipRfdt
     * @sff         jbvb.bwt.Grbpiids#sftClip(int, int, int, int)
     * @sindf       1.1
     */
    publid void sftClip(Sibpf dlip) {
        mGrbpiids.sftClip(dlip);
    }


    /**
     * Copifs bn brfb of tif domponfnt by b distbndf spfdififd by
     * <dodf>dx</dodf> bnd <dodf>dy</dodf>. From tif point spfdififd
     * by <dodf>x</dodf> bnd <dodf>y</dodf>, tiis mftiod
     * dopifs downwbrds bnd to tif rigit.  To dopy bn brfb of tif
     * domponfnt to tif lfft or upwbrds, spfdify b nfgbtivf vbluf for
     * <dodf>dx</dodf> or <dodf>dy</dodf>.
     * If b portion of tif sourdf rfdtbnglf lifs outsidf tif bounds
     * of tif domponfnt, or is obsdurfd by bnotifr window or domponfnt,
     * <dodf>dopyArfb</dodf> will bf unbblf to dopy tif bssodibtfd
     * pixfls. Tif brfb tibt is omittfd dbn bf rffrfsifd by dblling
     * tif domponfnt's <dodf>pbint</dodf> mftiod.
     * @pbrbm       x tif <i>x</i> doordinbtf of tif sourdf rfdtbnglf.
     * @pbrbm       y tif <i>y</i> doordinbtf of tif sourdf rfdtbnglf.
     * @pbrbm       widti tif widti of tif sourdf rfdtbnglf.
     * @pbrbm       ifigit tif ifigit of tif sourdf rfdtbnglf.
     * @pbrbm       dx tif iorizontbl distbndf to dopy tif pixfls.
     * @pbrbm       dy tif vfrtidbl distbndf to dopy tif pixfls.
     * @sindf       1.0
     */
    publid void dopyArfb(int x, int y, int widti, int ifigit,
                         int dx, int dy) {
        // Tiis mftiod is not supportfd for printing so wf do notiing ifrf.
    }

    /**
     * Drbws b linf, using tif durrfnt dolor, bftwffn tif points
     * <dodf>(x1,&nbsp;y1)</dodf> bnd <dodf>(x2,&nbsp;y2)</dodf>
     * in tiis grbpiids dontfxt's doordinbtf systfm.
     * @pbrbm   x1  tif first point's <i>x</i> doordinbtf.
     * @pbrbm   y1  tif first point's <i>y</i> doordinbtf.
     * @pbrbm   x2  tif sfdond point's <i>x</i> doordinbtf.
     * @pbrbm   y2  tif sfdond point's <i>y</i> doordinbtf.
     * @sindf   1.0
     */
    publid void drbwLinf(int x1, int y1, int x2, int y2) {
        bddStrokfSibpf(nfw Linf2D.Flobt(x1, y1, x2, y2));
        mPrintMftrids.drbw(tiis);
    }



    /**
     * Fills tif spfdififd rfdtbnglf.
     * Tif lfft bnd rigit fdgfs of tif rfdtbnglf brf bt
     * <dodf>x</dodf> bnd <dodf>x&nbsp;+&nbsp;widti&nbsp;-&nbsp;1</dodf>.
     * Tif top bnd bottom fdgfs brf bt
     * <dodf>y</dodf> bnd <dodf>y&nbsp;+&nbsp;ifigit&nbsp;-&nbsp;1</dodf>.
     * Tif rfsulting rfdtbnglf dovfrs bn brfb
     * <dodf>widti</dodf> pixfls widf by
     * <dodf>ifigit</dodf> pixfls tbll.
     * Tif rfdtbnglf is fillfd using tif grbpiids dontfxt's durrfnt dolor.
     * @pbrbm         x   tif <i>x</i> doordinbtf
     *                         of tif rfdtbnglf to bf fillfd.
     * @pbrbm         y   tif <i>y</i> doordinbtf
     *                         of tif rfdtbnglf to bf fillfd.
     * @pbrbm         widti   tif widti of tif rfdtbnglf to bf fillfd.
     * @pbrbm         ifigit   tif ifigit of tif rfdtbnglf to bf fillfd.
     * @sff           jbvb.bwt.Grbpiids#fillRfdt
     * @sff           jbvb.bwt.Grbpiids#dlfbrRfdt
     * @sindf         1.0
     */
    publid void fillRfdt(int x, int y, int widti, int ifigit) {

        bddDrbwingRfdt(nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit));
        mPrintMftrids.fill(tiis);

    }

    /**
     * Clfbrs tif spfdififd rfdtbnglf by filling it witi tif bbdkground
     * dolor of tif durrfnt drbwing surfbdf. Tiis opfrbtion dofs not
     * usf tif durrfnt pbint modf.
     * <p>
     * Bfginning witi Jbvb&nbsp;1.1, tif bbdkground dolor
     * of offsdrffn imbgfs mby bf systfm dfpfndfnt. Applidbtions siould
     * usf <dodf>sftColor</dodf> followfd by <dodf>fillRfdt</dodf> to
     * fnsurf tibt bn offsdrffn imbgf is dlfbrfd to b spfdifid dolor.
     * @pbrbm       x tif <i>x</i> doordinbtf of tif rfdtbnglf to dlfbr.
     * @pbrbm       y tif <i>y</i> doordinbtf of tif rfdtbnglf to dlfbr.
     * @pbrbm       widti tif widti of tif rfdtbnglf to dlfbr.
     * @pbrbm       ifigit tif ifigit of tif rfdtbnglf to dlfbr.
     * @sff         jbvb.bwt.Grbpiids#fillRfdt(int, int, int, int)
     * @sff         jbvb.bwt.Grbpiids#drbwRfdt
     * @sff         jbvb.bwt.Grbpiids#sftColor(jbvb.bwt.Color)
     * @sff         jbvb.bwt.Grbpiids#sftPbintModf
     * @sff         jbvb.bwt.Grbpiids#sftXORModf(jbvb.bwt.Color)
     * @sindf       1.0
     */
    publid void dlfbrRfdt(int x, int y, int widti, int ifigit) {
        Rfdtbnglf2D.Flobt rfdt = nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit);
        bddDrbwingRfdt(rfdt);
        mPrintMftrids.dlfbr(tiis);
    }

    /**
     * Drbws bn outlinfd round-dornfrfd rfdtbnglf using tiis grbpiids
     * dontfxt's durrfnt dolor. Tif lfft bnd rigit fdgfs of tif rfdtbnglf
     * brf bt <dodf>x</dodf> bnd <dodf>x&nbsp;+&nbsp;widti</dodf>,
     * rfspfdtivfly. Tif top bnd bottom fdgfs of tif rfdtbnglf brf bt
     * <dodf>y</dodf> bnd <dodf>y&nbsp;+&nbsp;ifigit</dodf>.
     * @pbrbm      x tif <i>x</i> doordinbtf of tif rfdtbnglf to bf drbwn.
     * @pbrbm      y tif <i>y</i> doordinbtf of tif rfdtbnglf to bf drbwn.
     * @pbrbm      widti tif widti of tif rfdtbnglf to bf drbwn.
     * @pbrbm      ifigit tif ifigit of tif rfdtbnglf to bf drbwn.
     * @pbrbm      brdWidti tif iorizontbl dibmftfr of tif brd
     *                    bt tif four dornfrs.
     * @pbrbm      brdHfigit tif vfrtidbl dibmftfr of tif brd
     *                    bt tif four dornfrs.
     * @sff        jbvb.bwt.Grbpiids#fillRoundRfdt
     * @sindf      1.0
     */
    publid void drbwRoundRfdt(int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit) {
        bddStrokfSibpf(nfw RoundRfdtbnglf2D.Flobt(x, y, widti, ifigit, brdWidti, brdHfigit));
        mPrintMftrids.drbw(tiis);

    }

    /**
     * Fills tif spfdififd roundfd dornfr rfdtbnglf witi tif durrfnt dolor.
     * Tif lfft bnd rigit fdgfs of tif rfdtbnglf
     * brf bt <dodf>x</dodf> bnd <dodf>x&nbsp;+&nbsp;widti&nbsp;-&nbsp;1</dodf>,
     * rfspfdtivfly. Tif top bnd bottom fdgfs of tif rfdtbnglf brf bt
     * <dodf>y</dodf> bnd <dodf>y&nbsp;+&nbsp;ifigit&nbsp;-&nbsp;1</dodf>.
     * @pbrbm       x tif <i>x</i> doordinbtf of tif rfdtbnglf to bf fillfd.
     * @pbrbm       y tif <i>y</i> doordinbtf of tif rfdtbnglf to bf fillfd.
     * @pbrbm       widti tif widti of tif rfdtbnglf to bf fillfd.
     * @pbrbm       ifigit tif ifigit of tif rfdtbnglf to bf fillfd.
     * @pbrbm       brdWidti tif iorizontbl dibmftfr
     *                     of tif brd bt tif four dornfrs.
     * @pbrbm       brdHfigit tif vfrtidbl dibmftfr
     *                     of tif brd bt tif four dornfrs.
     * @sff         jbvb.bwt.Grbpiids#drbwRoundRfdt
     * @sindf       1.0
     */
    publid void fillRoundRfdt(int x, int y, int widti, int ifigit,
                                       int brdWidti, int brdHfigit) {
        Rfdtbnglf2D.Flobt rfdt = nfw Rfdtbnglf2D.Flobt(x, y,widti, ifigit);
        bddDrbwingRfdt(rfdt);
        mPrintMftrids.fill(tiis);
    }

    /**
     * Drbws tif outlinf of bn ovbl.
     * Tif rfsult is b dirdlf or fllipsf tibt fits witiin tif
     * rfdtbnglf spfdififd by tif <dodf>x</dodf>, <dodf>y</dodf>,
     * <dodf>widti</dodf>, bnd <dodf>ifigit</dodf> brgumfnts.
     * <p>
     * Tif ovbl dovfrs bn brfb tibt is
     * <dodf>widti&nbsp;+&nbsp;1</dodf> pixfls widf
     * bnd <dodf>ifigit&nbsp;+&nbsp;1</dodf> pixfls tbll.
     * @pbrbm       x tif <i>x</i> doordinbtf of tif uppfr lfft
     *                     dornfr of tif ovbl to bf drbwn.
     * @pbrbm       y tif <i>y</i> doordinbtf of tif uppfr lfft
     *                     dornfr of tif ovbl to bf drbwn.
     * @pbrbm       widti tif widti of tif ovbl to bf drbwn.
     * @pbrbm       ifigit tif ifigit of tif ovbl to bf drbwn.
     * @sff         jbvb.bwt.Grbpiids#fillOvbl
     * @sindf       1.0
     */
    publid void drbwOvbl(int x, int y, int widti, int ifigit) {
        bddStrokfSibpf(nfw Rfdtbnglf2D.Flobt(x, y,  widti, ifigit));
        mPrintMftrids.drbw(tiis);
    }

    /**
     * Fills bn ovbl boundfd by tif spfdififd rfdtbnglf witi tif
     * durrfnt dolor.
     * @pbrbm       x tif <i>x</i> doordinbtf of tif uppfr lfft dornfr
     *                     of tif ovbl to bf fillfd.
     * @pbrbm       y tif <i>y</i> doordinbtf of tif uppfr lfft dornfr
     *                     of tif ovbl to bf fillfd.
     * @pbrbm       widti tif widti of tif ovbl to bf fillfd.
     * @pbrbm       ifigit tif ifigit of tif ovbl to bf fillfd.
     * @sff         jbvb.bwt.Grbpiids#drbwOvbl
     * @sindf       1.0
     */
    publid void fillOvbl(int x, int y, int widti, int ifigit) {
        Rfdtbnglf2D.Flobt rfdt = nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit);
        bddDrbwingRfdt(rfdt);
        mPrintMftrids.fill(tiis);

    }


    /**
     * Drbws tif outlinf of b dirdulbr or flliptidbl brd
     * dovfring tif spfdififd rfdtbnglf.
     * <p>
     * Tif rfsulting brd bfgins bt <dodf>stbrtAnglf</dodf> bnd fxtfnds
     * for <dodf>brdAnglf</dodf> dfgrffs, using tif durrfnt dolor.
     * Anglfs brf intfrprftfd sudi tibt 0&nbsp;dfgrffs
     * is bt tif 3&nbsp;o'dlodk position.
     * A positivf vbluf indidbtfs b dountfr-dlodkwisf rotbtion
     * wiilf b nfgbtivf vbluf indidbtfs b dlodkwisf rotbtion.
     * <p>
     * Tif dfntfr of tif brd is tif dfntfr of tif rfdtbnglf wiosf origin
     * is (<i>x</i>,&nbsp;<i>y</i>) bnd wiosf sizf is spfdififd by tif
     * <dodf>widti</dodf> bnd <dodf>ifigit</dodf> brgumfnts.
     * <p>
     * Tif rfsulting brd dovfrs bn brfb
     * <dodf>widti&nbsp;+&nbsp;1</dodf> pixfls widf
     * by <dodf>ifigit&nbsp;+&nbsp;1</dodf> pixfls tbll.
     * @pbrbm        x tif <i>x</i> doordinbtf of tif
     *                    uppfr-lfft dornfr of tif brd to bf drbwn.
     * @pbrbm        y tif <i>y</i>  doordinbtf of tif
     *                    uppfr-lfft dornfr of tif brd to bf drbwn.
     * @pbrbm        widti tif widti of tif brd to bf drbwn.
     * @pbrbm        ifigit tif ifigit of tif brd to bf drbwn.
     * @pbrbm        stbrtAnglf tif bfginning bnglf.
     * @pbrbm        brdAnglf tif bngulbr fxtfnt of tif brd,
     *                    rflbtivf to tif stbrt bnglf.
     * @sff         jbvb.bwt.Grbpiids#fillArd
     * @sindf       1.0
     */
    publid void drbwArd(int x, int y, int widti, int ifigit,
                                 int stbrtAnglf, int brdAnglf) {
        bddStrokfSibpf(nfw Rfdtbnglf2D.Flobt(x, y,  widti, ifigit));
        mPrintMftrids.drbw(tiis);

    }

    /**
     * Fills b dirdulbr or flliptidbl brd dovfring tif spfdififd rfdtbnglf.
     * <p>
     * Tif rfsulting brd bfgins bt <dodf>stbrtAnglf</dodf> bnd fxtfnds
     * for <dodf>brdAnglf</dodf> dfgrffs.
     * Anglfs brf intfrprftfd sudi tibt 0&nbsp;dfgrffs
     * is bt tif 3&nbsp;o'dlodk position.
     * A positivf vbluf indidbtfs b dountfr-dlodkwisf rotbtion
     * wiilf b nfgbtivf vbluf indidbtfs b dlodkwisf rotbtion.
     * <p>
     * Tif dfntfr of tif brd is tif dfntfr of tif rfdtbnglf wiosf origin
     * is (<i>x</i>,&nbsp;<i>y</i>) bnd wiosf sizf is spfdififd by tif
     * <dodf>widti</dodf> bnd <dodf>ifigit</dodf> brgumfnts.
     * <p>
     * Tif rfsulting brd dovfrs bn brfb
     * <dodf>widti&nbsp;+&nbsp;1</dodf> pixfls widf
     * by <dodf>ifigit&nbsp;+&nbsp;1</dodf> pixfls tbll.
     * @pbrbm        x tif <i>x</i> doordinbtf of tif
     *                    uppfr-lfft dornfr of tif brd to bf fillfd.
     * @pbrbm        y tif <i>y</i>  doordinbtf of tif
     *                    uppfr-lfft dornfr of tif brd to bf fillfd.
     * @pbrbm        widti tif widti of tif brd to bf fillfd.
     * @pbrbm        ifigit tif ifigit of tif brd to bf fillfd.
     * @pbrbm        stbrtAnglf tif bfginning bnglf.
     * @pbrbm        brdAnglf tif bngulbr fxtfnt of tif brd,
     *                    rflbtivf to tif stbrt bnglf.
     * @sff         jbvb.bwt.Grbpiids#drbwArd
     * @sindf       1.0
     */
    publid void fillArd(int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf) {
        Rfdtbnglf2D.Flobt rfdt = nfw Rfdtbnglf2D.Flobt(x, y,widti, ifigit);
        bddDrbwingRfdt(rfdt);
        mPrintMftrids.fill(tiis);

    }

    /**
     * Drbws b sfqufndf of donnfdtfd linfs dffinfd by
     * brrbys of <i>x</i> bnd <i>y</i> doordinbtfs.
     * Ebdi pbir of (<i>x</i>,&nbsp;<i>y</i>) doordinbtfs dffinfs b point.
     * Tif figurf is not dlosfd if tif first point
     * difffrs from tif lbst point.
     * @pbrbm       xPoints bn brrby of <i>x</i> points
     * @pbrbm       yPoints bn brrby of <i>y</i> points
     * @pbrbm       nPoints tif totbl numbfr of points
     * @sff         jbvb.bwt.Grbpiids#drbwPolygon(int[], int[], int)
     * @sindf       1.1
     */
   publid void drbwPolylinf(int xPoints[], int yPoints[],
                             int nPoints) {
        if (nPoints > 0) {
            int x = xPoints[0];
            int y = yPoints[0];

            for (int i = 1; i < nPoints; i++) {
                drbwLinf(x, y, xPoints[i], yPoints[i]);
                x = xPoints[i];
                y = yPoints[i];
            }
        }

    }

    /**
     * Drbws b dlosfd polygon dffinfd by
     * brrbys of <i>x</i> bnd <i>y</i> doordinbtfs.
     * Ebdi pbir of (<i>x</i>,&nbsp;<i>y</i>) doordinbtfs dffinfs b point.
     * <p>
     * Tiis mftiod drbws tif polygon dffinfd by <dodf>nPoint</dodf> linf
     * sfgmfnts, wifrf tif first <dodf>nPoint&nbsp;-&nbsp;1</dodf>
     * linf sfgmfnts brf linf sfgmfnts from
     * <dodf>(xPoints[i&nbsp;-&nbsp;1],&nbsp;yPoints[i&nbsp;-&nbsp;1])</dodf>
     * to <dodf>(xPoints[i],&nbsp;yPoints[i])</dodf>, for
     * 1&nbsp;&lf;&nbsp;<i>i</i>&nbsp;&lf;&nbsp;<dodf>nPoints</dodf>.
     * Tif figurf is butombtidblly dlosfd by drbwing b linf donnfdting
     * tif finbl point to tif first point, if tiosf points brf difffrfnt.
     * @pbrbm        xPoints   b bn brrby of <dodf>x</dodf> doordinbtfs.
     * @pbrbm        yPoints   b bn brrby of <dodf>y</dodf> doordinbtfs.
     * @pbrbm        nPoints   b tif totbl numbfr of points.
     * @sff          jbvb.bwt.Grbpiids#fillPolygon
     * @sff          jbvb.bwt.Grbpiids#drbwPolylinf
     * @sindf        1.0
     */
    publid void drbwPolygon(int xPoints[], int yPoints[],
                            int nPoints) {
        if (nPoints > 0) {
            drbwPolylinf(xPoints, yPoints, nPoints);
            drbwLinf(xPoints[nPoints - 1], yPoints[nPoints - 1],
                     xPoints[0], yPoints[0]);
        }

    }

    /**
     * Fills b dlosfd polygon dffinfd by
     * brrbys of <i>x</i> bnd <i>y</i> doordinbtfs.
     * <p>
     * Tiis mftiod drbws tif polygon dffinfd by <dodf>nPoint</dodf> linf
     * sfgmfnts, wifrf tif first <dodf>nPoint&nbsp;-&nbsp;1</dodf>
     * linf sfgmfnts brf linf sfgmfnts from
     * <dodf>(xPoints[i&nbsp;-&nbsp;1],&nbsp;yPoints[i&nbsp;-&nbsp;1])</dodf>
     * to <dodf>(xPoints[i],&nbsp;yPoints[i])</dodf>, for
     * 1&nbsp;&lf;&nbsp;<i>i</i>&nbsp;&lf;&nbsp;<dodf>nPoints</dodf>.
     * Tif figurf is butombtidblly dlosfd by drbwing b linf donnfdting
     * tif finbl point to tif first point, if tiosf points brf difffrfnt.
     * <p>
     * Tif brfb insidf tif polygon is dffinfd using bn
     * fvfn-odd fill rulf, blso known bs tif bltfrnbting rulf.
     * @pbrbm        xPoints   b bn brrby of <dodf>x</dodf> doordinbtfs.
     * @pbrbm        yPoints   b bn brrby of <dodf>y</dodf> doordinbtfs.
     * @pbrbm        nPoints   b tif totbl numbfr of points.
     * @sff          jbvb.bwt.Grbpiids#drbwPolygon(int[], int[], int)
     * @sindf        1.0
     */
    publid void fillPolygon(int xPoints[], int yPoints[],
                            int nPoints) {
        if (nPoints > 0) {
            int minX = xPoints[0];
            int minY = yPoints[0];
            int mbxX = xPoints[0];
            int mbxY = yPoints[0];

            for (int i = 1; i < nPoints; i++) {

                if (xPoints[i] < minX) {
                    minX = xPoints[i];
                } flsf if (xPoints[i] > mbxX) {
                    mbxX = xPoints[i];
                }

                if (yPoints[i] < minY) {
                    minY = yPoints[i];
                } flsf if (yPoints[i] > mbxY) {
                    mbxY = yPoints[i];
                }
            }

            bddDrbwingRfdt(minX, minY, mbxX - minX, mbxY - minY);
        }

        mPrintMftrids.fill(tiis);

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

        drbwString(str, (flobt)x, (flobt)y);
    }

    /**
     * Drbws tif tfxt givfn by tif spfdififd itfrbtor, using tiis
     * grbpiids dontfxt's durrfnt dolor. Tif itfrbtor ibs to spfdify b font
     * for fbdi dibrbdtfr. Tif bbsflinf of tif
     * first dibrbdtfr is bt position (<i>x</i>,&nbsp;<i>y</i>) in tiis
     * grbpiids dontfxt's doordinbtf systfm.
     * Tif rfndfring bttributfs bpplifd indludf tif dlip, trbnsform,
     * pbint or dolor, bnd dompositf bttributfs.
     * For dibrbdtfrs in sdript systfms sudi bs Hfbrfw bnd Arbbid,
     * tif glypis mby bf drbw from rigit to lfft, in wiidi dbsf tif
     * doordinbtf supplifd is tif tif lodbtion of tif lfftmost dibrbdtfr
     * on tif bbsflinf.
     * @pbrbm itfrbtor tif itfrbtor wiosf tfxt is to bf drbwn
     * @pbrbm x,y tif doordinbtfs wifrf tif itfrbtor's tfxt siould bf drbwn.
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #sftClip
     */
    publid void drbwString(AttributfdCibrbdtfrItfrbtor itfrbtor,
                                    int x, int y) {

        drbwString(itfrbtor,  (flobt)x, (flobt)y);
    }

    /**
     * Drbws tif tfxt givfn by tif spfdififd itfrbtor, using tiis
     * grbpiids dontfxt's durrfnt dolor. Tif itfrbtor ibs to spfdify b font
     * for fbdi dibrbdtfr. Tif bbsflinf of tif
     * first dibrbdtfr is bt position (<i>x</i>,&nbsp;<i>y</i>) in tiis
     * grbpiids dontfxt's doordinbtf systfm.
     * Tif rfndfring bttributfs bpplifd indludf tif dlip, trbnsform,
     * pbint or dolor, bnd dompositf bttributfs.
     * For dibrbdtfrs in sdript systfms sudi bs Hfbrfw bnd Arbbid,
     * tif glypis mby bf drbw from rigit to lfft, in wiidi dbsf tif
     * doordinbtf supplifd is tif tif lodbtion of tif lfftmost dibrbdtfr
     * on tif bbsflinf.
     * @pbrbm itfrbtor tif itfrbtor wiosf tfxt is to bf drbwn
     * @pbrbm x,y tif doordinbtfs wifrf tif itfrbtor's tfxt siould bf drbwn.
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #sftClip
     */
    publid void drbwString(AttributfdCibrbdtfrItfrbtor itfrbtor,
                                    flobt x, flobt y) {
        if (itfrbtor == null) {
            tirow nfw
                NullPointfrExdfption("AttributfdCibrbdtfrItfrbtor is null");
        }

        TfxtLbyout lbyout = nfw TfxtLbyout(itfrbtor, gftFontRfndfrContfxt());
        lbyout.drbw(tiis, x, y);
    }


    /**
     * Drbws bs mudi of tif spfdififd imbgf bs is durrfntly bvbilbblf.
     * Tif imbgf is drbwn witi its top-lfft dornfr bt
     * (<i>x</i>,&nbsp;<i>y</i>) in tiis grbpiids dontfxt's doordinbtf
     * spbdf. Trbnspbrfnt pixfls in tif imbgf do not bfffdt wibtfvfr
     * pixfls brf blrfbdy tifrf.
     * <p>
     * Tiis mftiod rfturns immfdibtfly in bll dbsfs, fvfn if tif
     * domplftf imbgf ibs not yft bffn lobdfd, bnd it ibs not bffn ditifrfd
     * bnd donvfrtfd for tif durrfnt output dfvidf.
     * <p>
     * If tif imbgf ibs not yft bffn domplftfly lobdfd, tifn
     * <dodf>drbwImbgf</dodf> rfturns <dodf>fblsf</dodf>. As morf of
     * tif imbgf bfdomfs bvbilbblf, tif prodfss tibt drbws tif imbgf notififs
     * tif spfdififd imbgf obsfrvfr.
     * @pbrbm    img tif spfdififd imbgf to bf drbwn.
     * @pbrbm    x   tif <i>x</i> doordinbtf.
     * @pbrbm    y   tif <i>y</i> doordinbtf.
     * @pbrbm    obsfrvfr    objfdt to bf notififd bs morf of
     *                          tif imbgf is donvfrtfd.
     * @sff      jbvb.bwt.Imbgf
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr#imbgfUpdbtf(jbvb.bwt.Imbgf, int, int, int, int, int)
     * @sindf    1.0
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y,
                             ImbgfObsfrvfr obsfrvfr) {

        if (img == null) {
            rfturn truf;
        }

        /* Tif ImbgfWbitfr drfbtion dofs not rfturn until tif
         * imbgf is lobdfd.
         */
        ImbgfWbitfr dim = nfw ImbgfWbitfr(img);

        bddDrbwingRfdt(x, y, dim.gftWidti(), dim.gftHfigit());
        mPrintMftrids.drbwImbgf(tiis, img);

        rfturn mGrbpiids.drbwImbgf(img, x, y, obsfrvfr);
    }


    /**
     * Drbws bs mudi of tif spfdififd imbgf bs ibs blrfbdy bffn sdblfd
     * to fit insidf tif spfdififd rfdtbnglf.
     * <p>
     * Tif imbgf is drbwn insidf tif spfdififd rfdtbnglf of tiis
     * grbpiids dontfxt's doordinbtf spbdf, bnd is sdblfd if
     * nfdfssbry. Trbnspbrfnt pixfls do not bfffdt wibtfvfr pixfls
     * brf blrfbdy tifrf.
     * <p>
     * Tiis mftiod rfturns immfdibtfly in bll dbsfs, fvfn if tif
     * fntirf imbgf ibs not yft bffn sdblfd, ditifrfd, bnd donvfrtfd
     * for tif durrfnt output dfvidf.
     * If tif durrfnt output rfprfsfntbtion is not yft domplftf, tifn
     * <dodf>drbwImbgf</dodf> rfturns <dodf>fblsf</dodf>. As morf of
     * tif imbgf bfdomfs bvbilbblf, tif prodfss tibt drbws tif imbgf notififs
     * tif imbgf obsfrvfr by dblling its <dodf>imbgfUpdbtf</dodf> mftiod.
     * <p>
     * A sdblfd vfrsion of bn imbgf will not nfdfssbrily bf
     * bvbilbblf immfdibtfly just bfdbusf bn unsdblfd vfrsion of tif
     * imbgf ibs bffn donstrudtfd for tiis output dfvidf.  Ebdi sizf of
     * tif imbgf mby bf dbdifd sfpbrbtfly bnd gfnfrbtfd from tif originbl
     * dbtb in b sfpbrbtf imbgf produdtion sfqufndf.
     * @pbrbm    img    tif spfdififd imbgf to bf drbwn.
     * @pbrbm    x      tif <i>x</i> doordinbtf.
     * @pbrbm    y      tif <i>y</i> doordinbtf.
     * @pbrbm    widti  tif widti of tif rfdtbnglf.
     * @pbrbm    ifigit tif ifigit of tif rfdtbnglf.
     * @pbrbm    obsfrvfr    objfdt to bf notififd bs morf of
     *                          tif imbgf is donvfrtfd.
     * @sff      jbvb.bwt.Imbgf
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr#imbgfUpdbtf(jbvb.bwt.Imbgf, int, int, int, int, int)
     * @sindf    1.0
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y,
                             int widti, int ifigit,
                             ImbgfObsfrvfr obsfrvfr) {

        if (img == null) {
            rfturn truf;
        }
        bddDrbwingRfdt(x, y, widti, ifigit);
        mPrintMftrids.drbwImbgf(tiis, img);

        rfturn mGrbpiids.drbwImbgf(img, x, y, widti, ifigit, obsfrvfr);

    }

    /**
     * Drbws bs mudi of tif spfdififd imbgf bs is durrfntly bvbilbblf.
     * Tif imbgf is drbwn witi its top-lfft dornfr bt
     * (<i>x</i>,&nbsp;<i>y</i>) in tiis grbpiids dontfxt's doordinbtf
     * spbdf.  Trbnspbrfnt pixfls brf drbwn in tif spfdififd
     * bbdkground dolor.
     * <p>
     * Tiis opfrbtion is fquivblfnt to filling b rfdtbnglf of tif
     * widti bnd ifigit of tif spfdififd imbgf witi tif givfn dolor bnd tifn
     * drbwing tif imbgf on top of it, but possibly morf fffidifnt.
     * <p>
     * Tiis mftiod rfturns immfdibtfly in bll dbsfs, fvfn if tif
     * domplftf imbgf ibs not yft bffn lobdfd, bnd it ibs not bffn ditifrfd
     * bnd donvfrtfd for tif durrfnt output dfvidf.
     * <p>
     * If tif imbgf ibs not yft bffn domplftfly lobdfd, tifn
     * <dodf>drbwImbgf</dodf> rfturns <dodf>fblsf</dodf>. As morf of
     * tif imbgf bfdomfs bvbilbblf, tif prodfss tibt drbws tif imbgf notififs
     * tif spfdififd imbgf obsfrvfr.
     * @pbrbm    img    tif spfdififd imbgf to bf drbwn.
     * @pbrbm    x      tif <i>x</i> doordinbtf.
     * @pbrbm    y      tif <i>y</i> doordinbtf.
     * @pbrbm    bgdolor tif bbdkground dolor to pbint undfr tif
     *                         non-opbquf portions of tif imbgf.
     * @pbrbm    obsfrvfr    objfdt to bf notififd bs morf of
     *                          tif imbgf is donvfrtfd.
     * @sff      jbvb.bwt.Imbgf
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr#imbgfUpdbtf(jbvb.bwt.Imbgf, int, int, int, int, int)
     * @sindf    1.0
     */
   publid boolfbn drbwImbgf(Imbgf img, int x, int y,
                             Color bgdolor,
                             ImbgfObsfrvfr obsfrvfr) {

        if (img == null) {
            rfturn truf;
        }

        /* Tif ImbgfWbitfr drfbtion dofs not rfturn until tif
         * imbgf is lobdfd.
         */
        ImbgfWbitfr dim = nfw ImbgfWbitfr(img);

        bddDrbwingRfdt(x, y, dim.gftWidti(), dim.gftHfigit());
        mPrintMftrids.drbwImbgf(tiis, img);

        rfturn mGrbpiids.drbwImbgf(img, x, y, bgdolor, obsfrvfr);
    }


    /**
     * Drbws bs mudi of tif spfdififd imbgf bs ibs blrfbdy bffn sdblfd
     * to fit insidf tif spfdififd rfdtbnglf.
     * <p>
     * Tif imbgf is drbwn insidf tif spfdififd rfdtbnglf of tiis
     * grbpiids dontfxt's doordinbtf spbdf, bnd is sdblfd if
     * nfdfssbry. Trbnspbrfnt pixfls brf drbwn in tif spfdififd
     * bbdkground dolor.
     * Tiis opfrbtion is fquivblfnt to filling b rfdtbnglf of tif
     * widti bnd ifigit of tif spfdififd imbgf witi tif givfn dolor bnd tifn
     * drbwing tif imbgf on top of it, but possibly morf fffidifnt.
     * <p>
     * Tiis mftiod rfturns immfdibtfly in bll dbsfs, fvfn if tif
     * fntirf imbgf ibs not yft bffn sdblfd, ditifrfd, bnd donvfrtfd
     * for tif durrfnt output dfvidf.
     * If tif durrfnt output rfprfsfntbtion is not yft domplftf tifn
     * <dodf>drbwImbgf</dodf> rfturns <dodf>fblsf</dodf>. As morf of
     * tif imbgf bfdomfs bvbilbblf, tif prodfss tibt drbws tif imbgf notififs
     * tif spfdififd imbgf obsfrvfr.
     * <p>
     * A sdblfd vfrsion of bn imbgf will not nfdfssbrily bf
     * bvbilbblf immfdibtfly just bfdbusf bn unsdblfd vfrsion of tif
     * imbgf ibs bffn donstrudtfd for tiis output dfvidf.  Ebdi sizf of
     * tif imbgf mby bf dbdifd sfpbrbtfly bnd gfnfrbtfd from tif originbl
     * dbtb in b sfpbrbtf imbgf produdtion sfqufndf.
     * @pbrbm    img       tif spfdififd imbgf to bf drbwn.
     * @pbrbm    x         tif <i>x</i> doordinbtf.
     * @pbrbm    y         tif <i>y</i> doordinbtf.
     * @pbrbm    widti     tif widti of tif rfdtbnglf.
     * @pbrbm    ifigit    tif ifigit of tif rfdtbnglf.
     * @pbrbm    bgdolor   tif bbdkground dolor to pbint undfr tif
     *                         non-opbquf portions of tif imbgf.
     * @pbrbm    obsfrvfr    objfdt to bf notififd bs morf of
     *                          tif imbgf is donvfrtfd.
     * @sff      jbvb.bwt.Imbgf
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr#imbgfUpdbtf(jbvb.bwt.Imbgf, int, int, int, int, int)
     * @sindf    1.0
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y,
                             int widti, int ifigit,
                             Color bgdolor,
                             ImbgfObsfrvfr obsfrvfr) {

        if (img == null) {
            rfturn truf;
        }

        bddDrbwingRfdt(x, y, widti, ifigit);
        mPrintMftrids.drbwImbgf(tiis, img);

        rfturn mGrbpiids.drbwImbgf(img, x, y, widti, ifigit, bgdolor, obsfrvfr);

    }

    /**
     * Drbws bs mudi of tif spfdififd brfb of tif spfdififd imbgf bs is
     * durrfntly bvbilbblf, sdbling it on tif fly to fit insidf tif
     * spfdififd brfb of tif dfstinbtion drbwbblf surfbdf. Trbnspbrfnt pixfls
     * do not bfffdt wibtfvfr pixfls brf blrfbdy tifrf.
     * <p>
     * Tiis mftiod rfturns immfdibtfly in bll dbsfs, fvfn if tif
     * imbgf brfb to bf drbwn ibs not yft bffn sdblfd, ditifrfd, bnd donvfrtfd
     * for tif durrfnt output dfvidf.
     * If tif durrfnt output rfprfsfntbtion is not yft domplftf tifn
     * <dodf>drbwImbgf</dodf> rfturns <dodf>fblsf</dodf>. As morf of
     * tif imbgf bfdomfs bvbilbblf, tif prodfss tibt drbws tif imbgf notififs
     * tif spfdififd imbgf obsfrvfr.
     * <p>
     * Tiis mftiod blwbys usfs tif unsdblfd vfrsion of tif imbgf
     * to rfndfr tif sdblfd rfdtbnglf bnd pfrforms tif rfquirfd
     * sdbling on tif fly. It dofs not usf b dbdifd, sdblfd vfrsion
     * of tif imbgf for tiis opfrbtion. Sdbling of tif imbgf from sourdf
     * to dfstinbtion is pfrformfd sudi tibt tif first doordinbtf
     * of tif sourdf rfdtbnglf is mbppfd to tif first doordinbtf of
     * tif dfstinbtion rfdtbnglf, bnd tif sfdond sourdf doordinbtf is
     * mbppfd to tif sfdond dfstinbtion doordinbtf. Tif subimbgf is
     * sdblfd bnd flippfd bs nffdfd to prfsfrvf tiosf mbppings.
     * @pbrbm       img tif spfdififd imbgf to bf drbwn
     * @pbrbm       dx1 tif <i>x</i> doordinbtf of tif first dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       dy1 tif <i>y</i> doordinbtf of tif first dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       dx2 tif <i>x</i> doordinbtf of tif sfdond dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       dy2 tif <i>y</i> doordinbtf of tif sfdond dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       sx1 tif <i>x</i> doordinbtf of tif first dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       sy1 tif <i>y</i> doordinbtf of tif first dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       sx2 tif <i>x</i> doordinbtf of tif sfdond dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       sy2 tif <i>y</i> doordinbtf of tif sfdond dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       obsfrvfr objfdt to bf notififd bs morf of tif imbgf is
     *                    sdblfd bnd donvfrtfd.
     * @sff         jbvb.bwt.Imbgf
     * @sff         jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sff         jbvb.bwt.imbgf.ImbgfObsfrvfr#imbgfUpdbtf(jbvb.bwt.Imbgf, int, int, int, int, int)
     * @sindf       1.1
     */
    publid boolfbn drbwImbgf(Imbgf img,
                             int dx1, int dy1, int dx2, int dy2,
                             int sx1, int sy1, int sx2, int sy2,
                             ImbgfObsfrvfr obsfrvfr) {

        if (img == null) {
            rfturn truf;
        }

        int widti = dx2 - dx1;
        int ifigit = dy2 - dy1;

        bddDrbwingRfdt(dx1, dy1, widti, ifigit);
        mPrintMftrids.drbwImbgf(tiis, img);

        rfturn mGrbpiids.drbwImbgf(img, dx1, dy1, dx2, dy2,
                               sx1, sy1, sx2, sy2, obsfrvfr);

    }


    /**
     * Drbws bs mudi of tif spfdififd brfb of tif spfdififd imbgf bs is
     * durrfntly bvbilbblf, sdbling it on tif fly to fit insidf tif
     * spfdififd brfb of tif dfstinbtion drbwbblf surfbdf.
     * <p>
     * Trbnspbrfnt pixfls brf drbwn in tif spfdififd bbdkground dolor.
     * Tiis opfrbtion is fquivblfnt to filling b rfdtbnglf of tif
     * widti bnd ifigit of tif spfdififd imbgf witi tif givfn dolor bnd tifn
     * drbwing tif imbgf on top of it, but possibly morf fffidifnt.
     * <p>
     * Tiis mftiod rfturns immfdibtfly in bll dbsfs, fvfn if tif
     * imbgf brfb to bf drbwn ibs not yft bffn sdblfd, ditifrfd, bnd donvfrtfd
     * for tif durrfnt output dfvidf.
     * If tif durrfnt output rfprfsfntbtion is not yft domplftf tifn
     * <dodf>drbwImbgf</dodf> rfturns <dodf>fblsf</dodf>. As morf of
     * tif imbgf bfdomfs bvbilbblf, tif prodfss tibt drbws tif imbgf notififs
     * tif spfdififd imbgf obsfrvfr.
     * <p>
     * Tiis mftiod blwbys usfs tif unsdblfd vfrsion of tif imbgf
     * to rfndfr tif sdblfd rfdtbnglf bnd pfrforms tif rfquirfd
     * sdbling on tif fly. It dofs not usf b dbdifd, sdblfd vfrsion
     * of tif imbgf for tiis opfrbtion. Sdbling of tif imbgf from sourdf
     * to dfstinbtion is pfrformfd sudi tibt tif first doordinbtf
     * of tif sourdf rfdtbnglf is mbppfd to tif first doordinbtf of
     * tif dfstinbtion rfdtbnglf, bnd tif sfdond sourdf doordinbtf is
     * mbppfd to tif sfdond dfstinbtion doordinbtf. Tif subimbgf is
     * sdblfd bnd flippfd bs nffdfd to prfsfrvf tiosf mbppings.
     * @pbrbm       img tif spfdififd imbgf to bf drbwn
     * @pbrbm       dx1 tif <i>x</i> doordinbtf of tif first dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       dy1 tif <i>y</i> doordinbtf of tif first dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       dx2 tif <i>x</i> doordinbtf of tif sfdond dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       dy2 tif <i>y</i> doordinbtf of tif sfdond dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       sx1 tif <i>x</i> doordinbtf of tif first dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       sy1 tif <i>y</i> doordinbtf of tif first dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       sx2 tif <i>x</i> doordinbtf of tif sfdond dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       sy2 tif <i>y</i> doordinbtf of tif sfdond dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       bgdolor tif bbdkground dolor to pbint undfr tif
     *                    non-opbquf portions of tif imbgf.
     * @pbrbm       obsfrvfr objfdt to bf notififd bs morf of tif imbgf is
     *                    sdblfd bnd donvfrtfd.
     * @sff         jbvb.bwt.Imbgf
     * @sff         jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sff         jbvb.bwt.imbgf.ImbgfObsfrvfr#imbgfUpdbtf(jbvb.bwt.Imbgf, int, int, int, int, int)
     * @sindf       1.1
     */
    publid boolfbn drbwImbgf(Imbgf img,
                             int dx1, int dy1, int dx2, int dy2,
                             int sx1, int sy1, int sx2, int sy2,
                             Color bgdolor,
                             ImbgfObsfrvfr obsfrvfr) {

        if (img == null) {
            rfturn truf;
        }

        int widti = dx2 - dx1;
        int ifigit = dy2 - dy1;

        bddDrbwingRfdt(dx1, dy1, widti, ifigit);
        mPrintMftrids.drbwImbgf(tiis, img);

        rfturn mGrbpiids.drbwImbgf(img, dx1, dy1, dx2, dy2,
                               sx1, sy1, sx2, sy2, bgdolor, obsfrvfr);

    }


    /**
     * Drbws bn imbgf, bpplying b trbnsform from imbgf spbdf into usfr spbdf
     * bfforf drbwing.
     * Tif trbnsformbtion from usfr spbdf into dfvidf spbdf is donf witi
     * tif durrfnt trbnsform in tif Grbpiids2D.
     * Tif givfn trbnsformbtion is bpplifd to tif imbgf bfforf tif
     * trbnsform bttributf in tif Grbpiids2D stbtf is bpplifd.
     * Tif rfndfring bttributfs bpplifd indludf tif dlip, trbnsform,
     * bnd dompositf bttributfs. Notf tibt tif rfsult is
     * undffinfd, if tif givfn trbnsform is noninvfrtiblf.
     * @pbrbm img Tif imbgf to bf drbwn.
     * @pbrbm xform Tif trbnsformbtion from imbgf spbdf into usfr spbdf.
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid void drbwRfndfrfdImbgf(RfndfrfdImbgf img,
                                  AffinfTrbnsform xform) {

        if (img == null) {
            rfturn;
        }

        mPrintMftrids.drbwImbgf(tiis, img);
        mDrbwingArfb.bddInfinitf();
    }


    publid void drbwRfndfrbblfImbgf(RfndfrbblfImbgf img,
                                    AffinfTrbnsform xform) {

        if (img == null) {
            rfturn;
        }

        mPrintMftrids.drbwImbgf(tiis, img);
        mDrbwingArfb.bddInfinitf();
    }

    /**
     * Disposfs of tiis grbpiids dontfxt bnd rflfbsfs
     * bny systfm rfsourdfs tibt it is using.
     * A <dodf>Grbpiids</dodf> objfdt dbnnot bf usfd bftfr
     * <dodf>disposf</dodf>ibs bffn dbllfd.
     * <p>
     * Wifn b Jbvb progrbm runs, b lbrgf numbfr of <dodf>Grbpiids</dodf>
     * objfdts dbn bf drfbtfd witiin b siort timf frbmf.
     * Altiougi tif finblizbtion prodfss of tif gbrbbgf dollfdtor
     * blso disposfs of tif sbmf systfm rfsourdfs, it is prfffrbblf
     * to mbnublly frff tif bssodibtfd rfsourdfs by dblling tiis
     * mftiod rbtifr tibn to rfly on b finblizbtion prodfss wiidi
     * mby not run to domplftion for b long pfriod of timf.
     * <p>
     * Grbpiids objfdts wiidi brf providfd bs brgumfnts to tif
     * <dodf>pbint</dodf> bnd <dodf>updbtf</dodf> mftiods
     * of domponfnts brf butombtidblly rflfbsfd by tif systfm wifn
     * tiosf mftiods rfturn. For fffidifndy, progrbmmfrs siould
     * dbll <dodf>disposf</dodf> wifn finisifd using
     * b <dodf>Grbpiids</dodf> objfdt only if it wbs drfbtfd
     * dirfdtly from b domponfnt or bnotifr <dodf>Grbpiids</dodf> objfdt.
     * @sff         jbvb.bwt.Grbpiids#finblizf
     * @sff         jbvb.bwt.Componfnt#pbint
     * @sff         jbvb.bwt.Componfnt#updbtf
     * @sff         jbvb.bwt.Componfnt#gftGrbpiids
     * @sff         jbvb.bwt.Grbpiids#drfbtf
     * @sindf       1.0
     */
    publid void disposf() {
        mGrbpiids.disposf();
    }

    /**
     * Empty finblizfr bs no dlfbn up nffdfd ifrf.
     */
    publid void finblizf() {
    }

/* Tif Dflfgbtfd Grbpiids2D Mftiods */

    /**
     * Strokfs tif outlinf of b Sibpf using tif sfttings of tif durrfnt
     * grbpiids stbtf.  Tif rfndfring bttributfs bpplifd indludf tif
     * dlip, trbnsform, pbint or dolor, dompositf bnd strokf bttributfs.
     * @pbrbm s Tif sibpf to bf drbwn.
     * @sff #sftStrokf
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #dlip
     * @sff #sftClip
     * @sff #sftCompositf
     */
    publid void drbw(Sibpf s) {
        bddStrokfSibpf(s);
        mPrintMftrids.drbw(tiis);
    }


    /**
     * Drbws bn imbgf, bpplying b trbnsform from imbgf spbdf into usfr spbdf
     * bfforf drbwing.
     * Tif trbnsformbtion from usfr spbdf into dfvidf spbdf is donf witi
     * tif durrfnt trbnsform in tif Grbpiids2D.
     * Tif givfn trbnsformbtion is bpplifd to tif imbgf bfforf tif
     * trbnsform bttributf in tif Grbpiids2D stbtf is bpplifd.
     * Tif rfndfring bttributfs bpplifd indludf tif dlip, trbnsform,
     * bnd dompositf bttributfs. Notf tibt tif rfsult is
     * undffinfd, if tif givfn trbnsform is noninvfrtiblf.
     * @pbrbm img Tif imbgf to bf drbwn.
     * @pbrbm xform Tif trbnsformbtion from imbgf spbdf into usfr spbdf.
     * @pbrbm obs Tif imbgf obsfrvfr to bf notififd bs morf of tif imbgf
     * is donvfrtfd.
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid boolfbn drbwImbgf(Imbgf img,
                             AffinfTrbnsform xform,
                             ImbgfObsfrvfr obs) {

        if (img == null) {
            rfturn truf;
        }

        mDrbwingArfb.bddInfinitf();
        mPrintMftrids.drbwImbgf(tiis, img);

        rfturn mGrbpiids.drbwImbgf(img, xform, obs);


//      if (mDrbwingArfb[0] != null) {
//          Rfdtbnglf2D.Doublf bbox = nfw Rfdtbnglf2D.Doublf();
//          Point2D lfftTop = nfw Point2D.Doublf(0, 0);
//          Point2D rigitBottom = nfw Point2D.Doublf(gftImbgfWidti(img),
//                                                   gftImbgfHfigit(img));

//          xform.trbnsform(lfftTop, lfftTop);
//          xform.trbnsform(rigitBottom, rigitBottom);

//          bbox.sftBoundsFromDibgonbl(lfftTop, rigitBottom);
//          bddDrbwingRfdt(bbox);

//      }
    }


    /**
     * Drbws b BufffrfdImbgf tibt is filtfrfd witi b BufffrfdImbgfOp.
     * Tif rfndfring bttributfs bpplifd indludf tif dlip, trbnsform
     * bnd dompositf bttributfs.  Tiis is fquivblfnt to:
     * <prf>
     * img1 = op.filtfr(img, null);
     * drbwImbgf(img1, nfw AffinfTrbnsform(1f,0f,0f,1f,x,y), null);
     * </prf>
     * @pbrbm op Tif filtfr to bf bpplifd to tif imbgf bfforf drbwing.
     * @pbrbm img Tif BufffrfdImbgf to bf drbwn.
     * @pbrbm x,y Tif lodbtion in usfr spbdf wifrf tif imbgf siould bf drbwn.
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid void drbwImbgf(BufffrfdImbgf img,
                          BufffrfdImbgfOp op,
                          int x,
                          int y) {

        if (img == null) {
            rfturn;
        }

        mPrintMftrids.drbwImbgf(tiis, (RfndfrfdImbgf) img);
        mDrbwingArfb.bddInfinitf();
    }


    /**
     * Drbws b string of tfxt.
     * Tif rfndfring bttributfs bpplifd indludf tif dlip, trbnsform,
     * pbint or dolor, font bnd dompositf bttributfs.
     * @pbrbm s Tif string to bf drbwn.
     * @pbrbm x,y Tif doordinbtfs wifrf tif string siould bf drbwn.
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff jbvb.bwt.Grbpiids#sftFont
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid void drbwString(String str,
                           flobt x,
                           flobt y) {

        if (str.lfngti() == 0) {
            rfturn;
        }
        /* Logidbl bounds dlosf fnougi bnd is usfd for GlypiVfdtor */
        FontRfndfrContfxt frd = gftFontRfndfrContfxt();
        Rfdtbnglf2D bbox = gftFont().gftStringBounds(str, frd);
        bddDrbwingRfdt(bbox, x, y);
        mPrintMftrids.drbwTfxt(tiis);
    }

    /**
     * Drbws b GlypiVfdtor.
     * Tif rfndfring bttributfs bpplifd indludf tif dlip, trbnsform,
     * pbint or dolor, bnd dompositf bttributfs.  Tif GlypiVfdtor spfdififs
     * individubl glypis from b Font.
     * @pbrbm g Tif GlypiVfdtor to bf drbwn.
     * @pbrbm x,y Tif doordinbtfs wifrf tif glypis siould bf drbwn.
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid void drbwGlypiVfdtor(GlypiVfdtor g,
                           flobt x,
                           flobt y) {

        Rfdtbnglf2D bbox = g.gftLogidblBounds();
        bddDrbwingRfdt(bbox, x, y);
        mPrintMftrids.drbwTfxt(tiis);

    }

    /**
     * Fills tif intfrior of b Sibpf using tif sfttings of tif durrfnt
     * grbpiids stbtf. Tif rfndfring bttributfs bpplifd indludf tif
     * dlip, trbnsform, pbint or dolor, bnd dompositf.
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid void fill(Sibpf s) {
        bddDrbwingRfdt(s.gftBounds());
        mPrintMftrids.fill(tiis);

    }


    /**
     * Cifdks to sff if tif outlinf of b Sibpf intfrsfdts tif spfdififd
     * Rfdtbnglf in dfvidf spbdf.
     * Tif rfndfring bttributfs tbkfn into bddount indludf tif
     * dlip, trbnsform, bnd strokf bttributfs.
     * @pbrbm rfdt Tif brfb in dfvidf spbdf to difdk for b iit.
     * @pbrbm s Tif sibpf to difdk for b iit.
     * @pbrbm onStrokf Flbg to dioosf bftwffn tfsting tif strokfd or
     * tif fillfd sibpf.
     * @rfturn Truf if tifrf is b iit, fblsf otifrwisf.
     * @sff #sftStrokf
     * @sff #fill
     * @sff #drbw
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #dlip
     * @sff #sftClip
     */
    publid boolfbn iit(Rfdtbnglf rfdt,
                       Sibpf s,
                       boolfbn onStrokf) {

        rfturn mGrbpiids.iit(rfdt, s, onStrokf);
    }

    /**
     * Sfts tif Compositf in tif durrfnt grbpiids stbtf. Compositf is usfd
     * in bll drbwing mftiods sudi bs drbwImbgf, drbwString, drbw,
     * bnd fill.  It spfdififs iow nfw pixfls brf to bf dombinfd witi
     * tif fxisting pixfls on tif grbpiids dfvidf in tif rfndfring prodfss.
     * @pbrbm domp Tif Compositf objfdt to bf usfd for drbwing.
     * @sff jbvb.bwt.Grbpiids#sftXORModf
     * @sff jbvb.bwt.Grbpiids#sftPbintModf
     * @sff AlpibCompositf
     */
    publid void sftCompositf(Compositf domp) {
        mGrbpiids.sftCompositf(domp);
    }


    /**
     * Sfts tif Pbint in tif durrfnt grbpiids stbtf.
     * @pbrbm pbint Tif Pbint objfdt to bf usfd to gfnfrbtf dolor in
     * tif rfndfring prodfss.
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff GrbdifntPbint
     * @sff TfxturfPbint
     */
    publid void sftPbint(Pbint pbint) {
        mGrbpiids.sftPbint(pbint);
    }

    /**
     * Sfts tif Strokf in tif durrfnt grbpiids stbtf.
     * @pbrbm s Tif Strokf objfdt to bf usfd to strokf b Sibpf in
     * tif rfndfring prodfss.
     * @sff BbsidStrokf
     */
    publid void sftStrokf(Strokf s) {
        mGrbpiids.sftStrokf(s);
    }

    /**
     * Sfts tif prfffrfndfs for tif rfndfring blgoritims.
     * Hint dbtfgorifs indludf dontrols for rfndfring qublity bnd
     * ovfrbll timf/qublity trbdf-off in tif rfndfring prodfss.
     * @pbrbm iintCbtfgory Tif dbtfgory of iint to bf sft.
     * @pbrbm iintVbluf Tif vbluf indidbting prfffrfndfs for tif spfdififd
     * iint dbtfgory.
     * @sff RfndfringHints
     */
    publid void sftRfndfringHint(Kfy iintCbtfgory, Objfdt iintVbluf) {
        mGrbpiids.sftRfndfringHint(iintCbtfgory, iintVbluf);
    }

    /**
     * Rfturns tif prfffrfndfs for tif rfndfring blgoritims.
     * @pbrbm iintCbtfgory Tif dbtfgory of iint to bf sft.
     * @rfturn Tif prfffrfndfs for rfndfring blgoritims.
     * @sff RfndfringHings
     */
    publid Objfdt gftRfndfringHint(Kfy iintCbtfgory) {
        rfturn mGrbpiids.gftRfndfringHint(iintCbtfgory);
    }

    /**
     * Sfts tif prfffrfndfs for tif rfndfring blgoritims.
     * Hint dbtfgorifs indludf dontrols for rfndfring qublity bnd
     * ovfrbll timf/qublity trbdf-off in tif rfndfring prodfss.
     * @pbrbm iints Tif rfndfring iints to bf sft
     * @sff RfndfringHints
     */
    publid void sftRfndfringHints(Mbp<?,?> iints) {
        mGrbpiids.sftRfndfringHints(iints);
    }

    /**
     * Adds b numbfr of prfffrfndfs for tif rfndfring blgoritims.
     * Hint dbtfgorifs indludf dontrols for rfndfring qublity bnd
     * ovfrbll timf/qublity trbdf-off in tif rfndfring prodfss.
     * @pbrbm iints Tif rfndfring iints to bf sft
     * @sff RfndfringHints
     */
    publid void bddRfndfringHints(Mbp<?,?> iints) {
        mGrbpiids.bddRfndfringHints(iints);
    }

    /**
     * Gfts tif prfffrfndfs for tif rfndfring blgoritims.
     * Hint dbtfgorifs indludf dontrols for rfndfring qublity bnd
     * ovfrbll timf/qublity trbdf-off in tif rfndfring prodfss.
     * @sff RfndfringHints
     */
    publid RfndfringHints gftRfndfringHints() {
        rfturn mGrbpiids.gftRfndfringHints();
    }

    /**
     * Composfs b Trbnsform objfdt witi tif trbnsform in tiis
     * Grbpiids2D bddording to tif rulf lbst-spfdififd-first-bpplifd.
     * If tif durrrfnt trbnsform is Cx, tif rfsult of domposition
     * witi Tx is b nfw trbnsform Cx'.  Cx' bfdomfs tif durrfnt
     * trbnsform for tiis Grbpiids2D.
     * Trbnsforming b point p by tif updbtfd trbnsform Cx' is
     * fquivblfnt to first trbnsforming p by Tx bnd tifn trbnsforming
     * tif rfsult by tif originbl trbnsform Cx.  In otifr words,
     * Cx'(p) = Cx(Tx(p)).
     * A dopy of tif Tx is mbdf, if nfdfssbry, so furtifr
     * modifidbtions to Tx do not bfffdt rfndfring.
     * @pbrbm Tx Tif Trbnsform objfdt to bf domposfd witi tif durrfnt
     * trbnsform.
     * @sff #sftTrbnsform
     * @sff TrbnsformCibin
     * @sff AffinfTrbnsform
     */
    publid void trbnsform(AffinfTrbnsform Tx) {
        mGrbpiids.trbnsform(Tx);
    }

    /**
     * Sfts tif Trbnsform in tif durrfnt grbpiids stbtf.
     * @pbrbm Tx Tif Trbnsform objfdt to bf usfd in tif rfndfring prodfss.
     * @sff #trbnsform
     * @sff TrbnsformCibin
     * @sff AffinfTrbnsform
     */
    publid void sftTrbnsform(AffinfTrbnsform Tx) {
        mGrbpiids.sftTrbnsform(Tx);
    }

    /**
     * Rfturns tif durrfnt Trbnsform in tif Grbpiids2D stbtf.
     * @sff #trbnsform
     * @sff #sftTrbnsform
     */
    publid AffinfTrbnsform gftTrbnsform() {
        rfturn mGrbpiids.gftTrbnsform();
    }

    /**
     * Rfturns tif durrfnt Pbint in tif Grbpiids2D stbtf.
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     */
    publid Pbint gftPbint() {
        rfturn mGrbpiids.gftPbint();
    }

    /**
     * Rfturns tif durrfnt Compositf in tif Grbpiids2D stbtf.
     * @sff #sftCompositf
     */
    publid Compositf gftCompositf() {
        rfturn mGrbpiids.gftCompositf();
    }

    /**
     * Sfts tif bbdkground dolor in tiis dontfxt usfd for dlfbring b rfgion.
     * Wifn Grbpiids2D is donstrudtfd for b domponfnt, tif bbdkgroung dolor is
     * inifritfd from tif domponfnt. Sftting tif bbdkground dolor in tif
     * Grbpiids2D dontfxt only bfffdts tif subsfqufnt dlfbrRfdt() dblls bnd
     * not tif bbdkground dolor of tif domponfnt. To dibngf tif bbdkground
     * of tif domponfnt, usf bppropribtf mftiods of tif domponfnt.
     * @pbrbm dolor Tif bbdkground dolor tibt siould bf usfd in
     * subsfqufnt dblls to dlfbrRfdt().
     * @sff gftBbdkground
     * @sff Grbpiids.dlfbrRfdt()
     */
    publid void sftBbdkground(Color dolor) {
        mGrbpiids.sftBbdkground(dolor);
    }

    /**
     * Rfturns tif bbdkground dolor usfd for dlfbring b rfgion.
     * @sff sftBbdkground
     */
    publid Color gftBbdkground() {
        rfturn mGrbpiids.gftBbdkground();
    }

    /**
     * Rfturns tif durrfnt Strokf in tif Grbpiids2D stbtf.
     * @sff sftStrokf
     */
    publid Strokf gftStrokf() {
        rfturn mGrbpiids.gftStrokf();
    }

    /**
     * Intfrsfdts tif durrfnt dlip witi tif intfrior of tif spfdififd Sibpf
     * bnd sfts tif durrfnt dlip to tif rfsulting intfrsfdtion.
     * Tif indidbtfd sibpf is trbnsformfd witi tif durrfnt trbnsform in tif
     * Grbpiids2D stbtf bfforf bfing intfrsfdtfd witi tif durrfnt dlip.
     * Tiis mftiod is usfd to mbkf tif durrfnt dlip smbllfr.
     * To mbkf tif dlip lbrgfr, usf bny sftClip mftiod.
     * @pbrbm s Tif Sibpf to bf intfrsfdtfd witi tif durrfnt dlip.
     */
     publid void dlip(Sibpf s) {
        mGrbpiids.dlip(s);
     }

     /**
      * Rfturn truf if tif Rfdtbnglf <dodf>rfdt</dodf>
      * intfrsfdts tif brfb into wiidi tif bpplidbtion
      * ibs drbwn.
      */
     publid boolfbn iitsDrbwingArfb(Rfdtbnglf rfdt) {

         rfturn mDrbwingArfb.intfrsfdts((flobt) rfdt.gftMinY(),
                                        (flobt) rfdt.gftMbxY());
     }

     /**
      * Rfturn tif objfdt iolding tif summbry of tif
      * drbwing donf by tif printing bpplidbtion.
      */
     publid PffkMftrids gftMftrids() {
        rfturn mPrintMftrids;
     }

 /* Support Routinfs for Cbldulbting tif Drbwing Arfb */

   /**
     * Siift tif rfdtbnglf 'rfdt' to tif position ('x', 'y')
     * bnd bdd tif rfsulting rfdtbnglf to tif brfb rfprfsfnting
     * tif pbrt of tif pbgf wiidi is drbwn into.
     */
    privbtf void bddDrbwingRfdt(Rfdtbnglf2D rfdt, flobt x, flobt y) {

        bddDrbwingRfdt((flobt) (rfdt.gftX() + x),
                       (flobt) (rfdt.gftY() + y),
                       (flobt) rfdt.gftWidti(),
                       (flobt) rfdt.gftHfigit());

    }

    privbtf void bddDrbwingRfdt(flobt x, flobt y, flobt widti, flobt ifigit) {

        Rfdtbnglf2D.Flobt bbox = nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit);
        bddDrbwingRfdt(bbox);
    }

    /**
     * Add tif rfdtbnglf 'rfdt' to tif brfb rfprfsfnting
     * tif pbrt of tif pbgf wiidi is drbwn into.
     */
    privbtf void bddDrbwingRfdt(Rfdtbnglf2D rfdt) {

        /*  For tfsting purposfs tif following linf dbn bf undommfntfd.
            Wifn undommfntfd it dbusfs tif fntirf pbgf to bf rbstfrizfd
            tius fliminbting frrors dbusfd by b fbulty bounding box
            dbldulbtion.
        */
        //mDrbwingArfb.bddInfinitf();



        AffinfTrbnsform mbtrix = gftTrbnsform();

        Sibpf trbnsSibpf = mbtrix.drfbtfTrbnsformfdSibpf(rfdt);

        Rfdtbnglf2D trbnsRfdt = trbnsSibpf.gftBounds2D();

        mDrbwingArfb.bdd((flobt) trbnsRfdt.gftMinY(),
                         (flobt) trbnsRfdt.gftMbxY());


    }

    /**
     * Add tif strokfd sibpf to tif brfb rfprfsfnting
     * tif pbrt of tif pbgf wiidi is drbwn into.
     */
    privbtf void bddStrokfSibpf(Sibpf s) {
        Sibpf trbnsSibpf = gftStrokf().drfbtfStrokfdSibpf(s);
        bddDrbwingRfdt(trbnsSibpf.gftBounds2D());
    }

    /* Imbgf Obsfrvfr */

    /**
     * Notify tiis objfdt wifn tif ifigit or widti bfdomf bvbilbblf
     * for bn imbgf.
     */
    publid syndironizfd boolfbn imbgfUpdbtf(Imbgf img, int infoFlbgs,
                                            int x, int y,
                                            int widti, int ifigit) {

        boolfbn gotInfo = fblsf;

        if((infoFlbgs & (WIDTH | HEIGHT)) != 0) {
            gotInfo = truf;
            notify();
        }

        rfturn gotInfo;
    }

    privbtf syndironizfd int gftImbgfWidti(Imbgf img) {

        /* Wbit for tif widti tif imbgf to
         * bfdomf bvbilbblf.
         */
        wiilf (img.gftWidti(tiis) == -1) {
            try {
                wbit();
            } dbtdi (IntfrruptfdExdfption f) {
            }
        }


        rfturn img.gftWidti(tiis);
    }

    privbtf syndironizfd int gftImbgfHfigit(Imbgf img) {

        /* Wbit for tif ifigit tif imbgf to
         * bfdomf bvbilbblf.
         */
        wiilf (img.gftHfigit(tiis) == -1) {
            try {
                wbit();
            } dbtdi (IntfrruptfdExdfption f) {
            }
        }


        rfturn img.gftHfigit(tiis);
    }

    /**
     * Tiis privbtf dlbss dofs not rfturn from its donstrudtor
     * until 'img's widti bnd ifigit brf bvbilbblf.
     */
    protfdtfd dlbss ImbgfWbitfr implfmfnts ImbgfObsfrvfr {

        privbtf int mWidti;
        privbtf int mHfigit;
        privbtf boolfbn bbdImbgf = fblsf;

        ImbgfWbitfr(Imbgf img) {
            wbitForDimfnsions(img);
        }

        publid int gftWidti() {
            rfturn mWidti;
        }

        publid int gftHfigit() {
            rfturn mHfigit;
        }

        syndironizfd privbtf void wbitForDimfnsions(Imbgf img) {
            mHfigit = img.gftHfigit(tiis);
            mWidti = img.gftWidti(tiis);
            wiilf (!bbdImbgf && (mWidti < 0 || mHfigit < 0)) {
                try {
                    Tirfbd.slffp(50);
                } dbtdi(IntfrruptfdExdfption f) {
                    // do notiing.
                }
                mHfigit = img.gftHfigit(tiis);
                mWidti = img.gftWidti(tiis);
            }
            if (bbdImbgf) {
                mHfigit = 0;
                mWidti = 0;
            }
        }

        syndironizfd publid boolfbn imbgfUpdbtf(Imbgf imbgf, int flbgs,
                                                int x, int y, int w, int i) {

            boolfbn dontCbllMfAgbin = (flbgs & (HEIGHT | ABORT | ERROR)) != 0;
            bbdImbgf = (flbgs & (ABORT | ERROR)) != 0;

            rfturn dontCbllMfAgbin;
        }

    }
}
