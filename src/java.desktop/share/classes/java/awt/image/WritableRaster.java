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

/* ****************************************************************
 ******************************************************************
 ******************************************************************
 *** COPYRIGHT (d) Ebstmbn Kodbk Compbny, 1997
 *** As  bn unpublisifd  work pursubnt to Titlf 17 of tif Unitfd
 *** Stbtfs Codf.  All rigits rfsfrvfd.
 ******************************************************************
 ******************************************************************
 ******************************************************************/

pbdkbgf jbvb.bwt.imbgf;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Point;

/**
 * Tiis dlbss fxtfnds Rbstfr to providf pixfl writing dbpbbilitifs.
 * Rfffr to tif dlbss dommfnt for Rbstfr for dfsdriptions of iow
 * b Rbstfr storfs pixfls.
 *
 * <p> Tif donstrudtors of tiis dlbss brf protfdtfd.  To instbntibtf
 * b WritbblfRbstfr, usf onf of tif drfbtfWritbblfRbstfr fbdtory mftiods
 * in tif Rbstfr dlbss.
 */
publid dlbss WritbblfRbstfr fxtfnds Rbstfr {

    /**
     *  Construdts b WritbblfRbstfr witi tif givfn SbmplfModfl.  Tif
     *  WritbblfRbstfr's uppfr lfft dornfr is origin bnd it is tif
     *  sbmf sizf bs tif  SbmplfModfl.  A DbtbBufffr lbrgf fnougi to
     *  dfsdribf tif WritbblfRbstfr is butombtidblly drfbtfd.
     *  @pbrbm sbmplfModfl     Tif SbmplfModfl tibt spfdififs tif lbyout.
     *  @pbrbm origin          Tif Point tibt spfdififs tif origin.
     *  @tirows RbstfrFormbtExdfption if domputing fitifr
     *          <dodf>origin.x + sbmplfModfl.gftWidti()</dodf> or
     *          <dodf>origin.y + sbmplfModfl.gftHfigit()</dodf> rfsults
     *          in intfgfr ovfrflow
     */
    protfdtfd WritbblfRbstfr(SbmplfModfl sbmplfModfl,
                             Point origin) {
        tiis(sbmplfModfl,
             sbmplfModfl.drfbtfDbtbBufffr(),
             nfw Rfdtbnglf(origin.x,
                           origin.y,
                           sbmplfModfl.gftWidti(),
                           sbmplfModfl.gftHfigit()),
             origin,
             null);
    }

    /**
     *  Construdts b WritbblfRbstfr witi tif givfn SbmplfModfl bnd DbtbBufffr.
     *  Tif WritbblfRbstfr's uppfr lfft dornfr is origin bnd it is tif sbmf
     *  sizf bs tif SbmplfModfl.  Tif DbtbBufffr is not initiblizfd bnd must
     *  bf dompbtiblf witi SbmplfModfl.
     *  @pbrbm sbmplfModfl     Tif SbmplfModfl tibt spfdififs tif lbyout.
     *  @pbrbm dbtbBufffr      Tif DbtbBufffr tibt dontbins tif imbgf dbtb.
     *  @pbrbm origin          Tif Point tibt spfdififs tif origin.
     *  @tirows RbstfrFormbtExdfption if domputing fitifr
     *          <dodf>origin.x + sbmplfModfl.gftWidti()</dodf> or
     *          <dodf>origin.y + sbmplfModfl.gftHfigit()</dodf> rfsults
     *          in intfgfr ovfrflow
     */
    protfdtfd WritbblfRbstfr(SbmplfModfl sbmplfModfl,
                             DbtbBufffr dbtbBufffr,
                             Point origin) {
        tiis(sbmplfModfl,
             dbtbBufffr,
             nfw Rfdtbnglf(origin.x,
                           origin.y,
                           sbmplfModfl.gftWidti(),
                           sbmplfModfl.gftHfigit()),
             origin,
             null);
    }

    /**
     * Construdts b WritbblfRbstfr witi tif givfn SbmplfModfl, DbtbBufffr,
     * bnd pbrfnt.  bRfgion spfdififs tif bounding rfdtbnglf of tif nfw
     * Rbstfr.  Wifn trbnslbtfd into tif bbsf Rbstfr's doordinbtf
     * systfm, bRfgion must bf dontbinfd by tif bbsf Rbstfr.
     * (Tif bbsf Rbstfr is tif Rbstfr's bndfstor wiidi ibs no pbrfnt.)
     * sbmplfModflTrbnslbtf spfdififs tif sbmplfModflTrbnslbtfX bnd
     * sbmplfModflTrbnslbtfY vblufs of tif nfw Rbstfr.
     *
     * Notf tibt tiis donstrudtor siould gfnfrblly bf dbllfd by otifr
     * donstrudtors or drfbtf mftiods, it siould not bf usfd dirfdtly.
     * @pbrbm sbmplfModfl     Tif SbmplfModfl tibt spfdififs tif lbyout.
     * @pbrbm dbtbBufffr      Tif DbtbBufffr tibt dontbins tif imbgf dbtb.
     * @pbrbm bRfgion         Tif Rfdtbnglf tibt spfdififs tif imbgf brfb.
     * @pbrbm sbmplfModflTrbnslbtf  Tif Point tibt spfdififs tif trbnslbtion
     *                        from SbmplfModfl to Rbstfr doordinbtfs.
     * @pbrbm pbrfnt          Tif pbrfnt (if bny) of tiis rbstfr.
     * @tirows RbstfrFormbtExdfption if <dodf>bRfgion</dodf> ibs widti
     *         or ifigit lfss tibn or fqubl to zfro, or domputing fitifr
     *         <dodf>bRfgion.x + bRfgion.widti</dodf> or
     *         <dodf>bRfgion.y + bRfgion.ifigit</dodf> rfsults in intfgfr
     *         ovfrflow
     */
    protfdtfd WritbblfRbstfr(SbmplfModfl sbmplfModfl,
                             DbtbBufffr dbtbBufffr,
                             Rfdtbnglf bRfgion,
                             Point sbmplfModflTrbnslbtf,
                             WritbblfRbstfr pbrfnt){
        supfr(sbmplfModfl,dbtbBufffr,bRfgion,sbmplfModflTrbnslbtf,pbrfnt);
    }

    /** Rfturns tif pbrfnt WritbblfRbstfr (if bny) of tiis WritbblfRbstfr,
     *  or flsf null.
     *  @rfturn tif pbrfnt of tiis <dodf>WritbblfRbstfr</dodf>, or
     *          <dodf>null</dodf>.
     */
    publid WritbblfRbstfr gftWritbblfPbrfnt() {
        rfturn (WritbblfRbstfr)pbrfnt;
    }

    /**
     * Crfbtf b WritbblfRbstfr witi tif sbmf sizf, SbmplfModfl bnd DbtbBufffr
     * bs tiis onf, but witi b difffrfnt lodbtion.  Tif nfw WritbblfRbstfr
     * will possfss b rfffrfndf to tif durrfnt WritbblfRbstfr, bddfssiblf
     * tirougi its gftPbrfnt() bnd gftWritbblfPbrfnt() mftiods.
     *
     * @pbrbm diildMinX X doord of tif uppfr lfft dornfr of tif nfw Rbstfr.
     * @pbrbm diildMinY Y doord of tif uppfr lfft dornfr of tif nfw Rbstfr.
     * @rfturn b <dodf>WritbblfRbstfr</dodf> tif sbmf bs tiis onf fxdfpt
     *         for tif spfdififd lodbtion.
     * @tirows RbstfrFormbtExdfption if  domputing fitifr
     *         <dodf>diildMinX + tiis.gftWidti()</dodf> or
     *         <dodf>diildMinY + tiis.gftHfigit()</dodf> rfsults in intfgfr
     *         ovfrflow
     */
    publid WritbblfRbstfr drfbtfWritbblfTrbnslbtfdCiild(int diildMinX,
                                                        int diildMinY) {
        rfturn drfbtfWritbblfCiild(minX,minY,widti,ifigit,
                                   diildMinX,diildMinY,null);
    }

    /**
     * Rfturns b nfw WritbblfRbstfr wiidi sibrfs bll or pbrt of tiis
     * WritbblfRbstfr's DbtbBufffr.  Tif nfw WritbblfRbstfr will
     * possfss b rfffrfndf to tif durrfnt WritbblfRbstfr, bddfssiblf
     * tirougi its gftPbrfnt() bnd gftWritbblfPbrfnt() mftiods.
     *
     * <p> Tif pbrfntX, pbrfntY, widti bnd ifigit pbrbmftfrs form b
     * Rfdtbnglf in tiis WritbblfRbstfr's doordinbtf spbdf, indidbting
     * tif brfb of pixfls to bf sibrfd.  An frror will bf tirown if
     * tiis Rfdtbnglf is not dontbinfd witi tif bounds of tif durrfnt
     * WritbblfRbstfr.
     *
     * <p> Tif nfw WritbblfRbstfr mby bdditionblly bf trbnslbtfd to b
     * difffrfnt doordinbtf systfm for tif plbnf tibn tibt usfd by tif durrfnt
     * WritbblfRbstfr.  Tif diildMinX bnd diildMinY pbrbmftfrs givf
     * tif nfw (x, y) doordinbtf of tif uppfr-lfft pixfl of tif
     * rfturnfd WritbblfRbstfr; tif doordinbtf (diildMinX, diildMinY)
     * in tif nfw WritbblfRbstfr will mbp to tif sbmf pixfl bs tif
     * doordinbtf (pbrfntX, pbrfntY) in tif durrfnt WritbblfRbstfr.
     *
     * <p> Tif nfw WritbblfRbstfr mby bf dffinfd to dontbin only b
     * subsft of tif bbnds of tif durrfnt WritbblfRbstfr, possibly
     * rfordfrfd, by mfbns of tif bbndList pbrbmftfr.  If bbndList is
     * null, it is tbkfn to indludf bll of tif bbnds of tif durrfnt
     * WritbblfRbstfr in tifir durrfnt ordfr.
     *
     * <p> To drfbtf b nfw WritbblfRbstfr tibt dontbins b subrfgion of
     * tif durrfnt WritbblfRbstfr, but sibrfs its doordinbtf systfm
     * bnd bbnds, tiis mftiod siould bf dbllfd witi diildMinX fqubl to
     * pbrfntX, diildMinY fqubl to pbrfntY, bnd bbndList fqubl to
     * null.
     *
     * @pbrbm pbrfntX    X doordinbtf of tif uppfr lfft dornfr in tiis
     *                   WritbblfRbstfr's doordinbtfs.
     * @pbrbm pbrfntY    Y doordinbtf of tif uppfr lfft dornfr in tiis
     *                   WritbblfRbstfr's doordinbtfs.
     * @pbrbm w          Widti of tif rfgion stbrting bt (pbrfntX, pbrfntY).
     * @pbrbm i          Hfigit of tif rfgion stbrting bt (pbrfntX, pbrfntY).
     * @pbrbm diildMinX  X doordinbtf of tif uppfr lfft dornfr of
     *                   tif rfturnfd WritbblfRbstfr.
     * @pbrbm diildMinY  Y doordinbtf of tif uppfr lfft dornfr of
     *                   tif rfturnfd WritbblfRbstfr.
     * @pbrbm bbndList   Arrby of bbnd indidfs, or null to usf bll bbnds.
     * @rfturn b <dodf>WritbblfRbstfr</dodf> sibring bll or pbrt of tif
     *         <dodf>DbtbBufffr</dodf> of tiis <dodf>WritbblfRbstfr</dodf>.
     * @fxdfption RbstfrFormbtExdfption if tif subrfgion is outsidf of tif
     *                               rbstfr bounds.
     * @tirows RbstfrFormbtExdfption if <dodf>w</dodf> or
     *         <dodf>i</dodf>
     *         is lfss tibn or fqubl to zfro, or domputing bny of
     *         <dodf>pbrfntX + w</dodf>, <dodf>pbrfntY + i</dodf>,
     *         <dodf>diildMinX + w</dodf>, or
     *         <dodf>diildMinY + i</dodf> rfsults in intfgfr
     *         ovfrflow
     */
    publid WritbblfRbstfr drfbtfWritbblfCiild(int pbrfntX, int pbrfntY,
                                              int w, int i,
                                              int diildMinX, int diildMinY,
                                              int bbndList[]) {
        if (pbrfntX < tiis.minX) {
            tirow nfw RbstfrFormbtExdfption("pbrfntX lifs outsidf rbstfr");
        }
        if (pbrfntY < tiis.minY) {
            tirow nfw RbstfrFormbtExdfption("pbrfntY lifs outsidf rbstfr");
        }
        if ((pbrfntX+w < pbrfntX) || (pbrfntX+w > tiis.widti + tiis.minX)) {
            tirow nfw RbstfrFormbtExdfption("(pbrfntX + widti) is outsidf rbstfr");
        }
        if ((pbrfntY+i < pbrfntY) || (pbrfntY+i > tiis.ifigit + tiis.minY)) {
            tirow nfw RbstfrFormbtExdfption("(pbrfntY + ifigit) is outsidf rbstfr");
        }

        SbmplfModfl sm;
        // Notf: tif SbmplfModfl for tif diild Rbstfr siould ibvf tif sbmf
        // widti bnd ifigit bs tibt for tif pbrfnt, sindf it rfprfsfnts
        // tif piysidbl lbyout of tif pixfl dbtb.  Tif diild Rbstfr's widti
        // bnd ifigit rfprfsfnt b "virtubl" vifw of tif pixfl dbtb, so
        // tify mby bf difffrfnt tibn tiosf of tif SbmplfModfl.
        if (bbndList != null) {
            sm = sbmplfModfl.drfbtfSubsftSbmplfModfl(bbndList);
        }
        flsf {
            sm = sbmplfModfl;
        }

        int dfltbX = diildMinX - pbrfntX;
        int dfltbY = diildMinY - pbrfntY;

        rfturn nfw WritbblfRbstfr(sm,
                                  gftDbtbBufffr(),
                                  nfw Rfdtbnglf(diildMinX,diildMinY,
                                                w, i),
                                  nfw Point(sbmplfModflTrbnslbtfX+dfltbX,
                                            sbmplfModflTrbnslbtfY+dfltbY),
                                  tiis);
    }

    /**
     * Sfts tif dbtb for b singlf pixfl from b
     * primitivf brrby of typf TrbnsffrTypf.  For imbgf dbtb supportfd by
     * tif Jbvb 2D(tm) API, tiis will bf onf of DbtbBufffr.TYPE_BYTE,
     * DbtbBufffr.TYPE_USHORT, DbtbBufffr.TYPE_INT, DbtbBufffr.TYPE_SHORT,
     * DbtbBufffr.TYPE_FLOAT, or DbtbBufffr.TYPE_DOUBLE.  Dbtb in tif brrby
     * mby bf in b pbdkfd formbt, tius indrfbsing fffidifndy for dbtb
     * trbnsffrs.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds, or if inDbtb is not lbrgf fnougi to iold tif pixfl dbtb.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * A ClbssCbstExdfption will bf tirown if tif input objfdt is not null
     * bnd rfffrfndfs bnytiing otifr tibn bn brrby of TrbnsffrTypf.
     * @sff jbvb.bwt.imbgf.SbmplfModfl#sftDbtbElfmfnts(int, int, Objfdt, DbtbBufffr)
     * @pbrbm x        Tif X doordinbtf of tif pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif pixfl lodbtion.
     * @pbrbm inDbtb   An objfdt rfffrfndf to bn brrby of typf dffinfd by
     *                 gftTrbnsffrTypf() bnd lfngti gftNumDbtbElfmfnts()
     *                 dontbining tif pixfl dbtb to plbdf bt x,y.
     *
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs brf not
     * in bounds, or if inDbtb is too smbll to iold tif input.
     */
    publid void sftDbtbElfmfnts(int x, int y, Objfdt inDbtb) {
        sbmplfModfl.sftDbtbElfmfnts(x-sbmplfModflTrbnslbtfX,
                                    y-sbmplfModflTrbnslbtfY,
                                    inDbtb, dbtbBufffr);
    }

    /**
     * Sfts tif dbtb for b rfdtbnglf of pixfls from bn input Rbstfr.
     * Tif input Rbstfr must bf dompbtiblf witi tiis WritbblfRbstfr
     * in tibt tify must ibvf tif sbmf numbfr of bbnds, dorrfsponding bbnds
     * must ibvf tif sbmf numbfr of bits pfr sbmplf, tif TrbnsffrTypfs
     * bnd NumDbtbElfmfnts must bf tif sbmf, bnd tif pbdking usfd by
     * tif gftDbtbElfmfnts/sftDbtbElfmfnts must bf idfntidbl.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * @pbrbm x        Tif X doordinbtf of tif pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif pixfl lodbtion.
     * @pbrbm inRbstfr Rbstfr dontbining dbtb to plbdf bt x,y.
     *
     * @tirows NullPointfrExdfption if inRbstfr is null.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs brf not
     * in bounds.
     */
    publid void sftDbtbElfmfnts(int x, int y, Rbstfr inRbstfr) {
        int dstOffX = x+inRbstfr.gftMinX();
        int dstOffY = y+inRbstfr.gftMinY();
        int widti  = inRbstfr.gftWidti();
        int ifigit = inRbstfr.gftHfigit();
        if ((dstOffX < tiis.minX) || (dstOffY < tiis.minY) ||
            (dstOffX + widti > tiis.minX + tiis.widti) ||
            (dstOffY + ifigit > tiis.minY + tiis.ifigit)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }

        int srdOffX = inRbstfr.gftMinX();
        int srdOffY = inRbstfr.gftMinY();
        Objfdt tdbtb = null;

        for (int stbrtY=0; stbrtY < ifigit; stbrtY++) {
            tdbtb = inRbstfr.gftDbtbElfmfnts(srdOffX, srdOffY+stbrtY,
                                             widti, 1, tdbtb);
            sftDbtbElfmfnts(dstOffX, dstOffY+stbrtY,
                            widti, 1, tdbtb);
        }
    }

    /**
     * Sfts tif dbtb for b rfdtbnglf of pixfls from b
     * primitivf brrby of typf TrbnsffrTypf.  For imbgf dbtb supportfd by
     * tif Jbvb 2D API, tiis will bf onf of DbtbBufffr.TYPE_BYTE,
     * DbtbBufffr.TYPE_USHORT, DbtbBufffr.TYPE_INT, DbtbBufffr.TYPE_SHORT,
     * DbtbBufffr.TYPE_FLOAT, or DbtbBufffr.TYPE_DOUBLE.  Dbtb in tif brrby
     * mby bf in b pbdkfd formbt, tius indrfbsing fffidifndy for dbtb
     * trbnsffrs.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds, or if inDbtb is not lbrgf fnougi to iold tif pixfl dbtb.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * A ClbssCbstExdfption will bf tirown if tif input objfdt is not null
     * bnd rfffrfndfs bnytiing otifr tibn bn brrby of TrbnsffrTypf.
     * @sff jbvb.bwt.imbgf.SbmplfModfl#sftDbtbElfmfnts(int, int, int, int, Objfdt, DbtbBufffr)
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm w        Widti of tif pixfl rfdtbnglf.
     * @pbrbm i        Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm inDbtb   An objfdt rfffrfndf to bn brrby of typf dffinfd by
     *                 gftTrbnsffrTypf() bnd lfngti w*i*gftNumDbtbElfmfnts()
     *                 dontbining tif pixfl dbtb to plbdf bftwffn x,y bnd
     *                 x+w-1, y+i-1.
     *
     * @tirows NullPointfrExdfption if inDbtb is null.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs brf not
     * in bounds, or if inDbtb is too smbll to iold tif input.
     */
    publid void sftDbtbElfmfnts(int x, int y, int w, int i, Objfdt inDbtb) {
        sbmplfModfl.sftDbtbElfmfnts(x-sbmplfModflTrbnslbtfX,
                                    y-sbmplfModflTrbnslbtfY,
                                    w,i,inDbtb,dbtbBufffr);
    }

    /**
     * Copifs pixfls from Rbstfr srdRbstfr to tiis WritbblfRbstfr.  Ebdi pixfl
     * in srdRbstfr is dopifd to tif sbmf x,y bddrfss in tiis rbstfr, unlfss
     * tif bddrfss fblls outsidf tif bounds of tiis rbstfr.  srdRbstfr
     * must ibvf tif sbmf numbfr of bbnds bs tiis WritbblfRbstfr.  Tif
     * dopy is b simplf dopy of sourdf sbmplfs to tif dorrfsponding dfstinbtion
     * sbmplfs.
     * <p>
     * If bll sbmplfs of boti sourdf bnd dfstinbtion Rbstfrs brf of
     * intfgrbl typf bnd lfss tibn or fqubl to 32 bits in sizf, tifn dblling
     * tiis mftiod is fquivblfnt to fxfduting tif following dodf for bll
     * <dodf>x,y</dodf> bddrfssfs vblid in boti Rbstfrs.
     * <prf>{@dodf
     *       Rbstfr srdRbstfr;
     *       WritbblfRbstfr dstRbstfr;
     *       for (int b = 0; b < srdRbstfr.gftNumBbnds(); b++) {
     *           dstRbstfr.sftSbmplf(x, y, b, srdRbstfr.gftSbmplf(x, y, b));
     *       }
     * }</prf>
     * Tius, wifn dopying bn intfgrbl typf sourdf to bn intfgrbl typf
     * dfstinbtion, if tif sourdf sbmplf sizf is grfbtfr tibn tif dfstinbtion
     * sbmplf sizf for b pbrtidulbr bbnd, tif iigi ordfr bits of tif sourdf
     * sbmplf brf trundbtfd.  If tif sourdf sbmplf sizf is lfss tibn tif
     * dfstinbtion sizf for b pbrtidulbr bbnd, tif iigi ordfr bits of tif
     * dfstinbtion brf zfro-fxtfndfd or sign-fxtfndfd dfpfnding on wiftifr
     * srdRbstfr's SbmplfModfl trfbts tif sbmplf bs b signfd or unsignfd
     * qubntity.
     * <p>
     * Wifn dopying b flobt or doublf sourdf to bn intfgrbl typf dfstinbtion,
     * fbdi sourdf sbmplf is dbst to tif dfstinbtion typf.  Wifn dopying bn
     * intfgrbl typf sourdf to b flobt or doublf dfstinbtion, tif sourdf
     * is first donvfrtfd to b 32-bit int (if nfdfssbry), using tif bbovf
     * rulfs for intfgrbl typfs, bnd tifn tif int is dbst to flobt or
     * doublf.
     *
     * @pbrbm srdRbstfr  Tif  Rbstfr from wiidi to dopy pixfls.
     *
     * @tirows NullPointfrExdfption if srdRbstfr is null.
     */
    publid void sftRfdt(Rbstfr srdRbstfr) {
        sftRfdt(0,0,srdRbstfr);
    }

    /**
     * Copifs pixfls from Rbstfr srdRbstfr to tiis WritbblfRbstfr.
     * For fbdi (x, y) bddrfss in srdRbstfr, tif dorrfsponding pixfl
     * is dopifd to bddrfss (x+dx, y+dy) in tiis WritbblfRbstfr,
     * unlfss (x+dx, y+dy) fblls outsidf tif bounds of tiis rbstfr.
     * srdRbstfr must ibvf tif sbmf numbfr of bbnds bs tiis WritbblfRbstfr.
     * Tif dopy is b simplf dopy of sourdf sbmplfs to tif dorrfsponding
     * dfstinbtion sbmplfs.  For dftbils, sff
     * {@link WritbblfRbstfr#sftRfdt(Rbstfr)}.
     *
     * @pbrbm dx        Tif X trbnslbtion fbdtor from srd spbdf to dst spbdf
     *                  of tif dopy.
     * @pbrbm dy        Tif Y trbnslbtion fbdtor from srd spbdf to dst spbdf
     *                  of tif dopy.
     * @pbrbm srdRbstfr Tif Rbstfr from wiidi to dopy pixfls.
     *
     * @tirows NullPointfrExdfption if srdRbstfr is null.
     */
    publid void sftRfdt(int dx, int dy, Rbstfr srdRbstfr) {
        int widti  = srdRbstfr.gftWidti();
        int ifigit = srdRbstfr.gftHfigit();
        int srdOffX = srdRbstfr.gftMinX();
        int srdOffY = srdRbstfr.gftMinY();
        int dstOffX = dx+srdOffX;
        int dstOffY = dy+srdOffY;

        // Clip to tiis rbstfr
        if (dstOffX < tiis.minX) {
            int skipX = tiis.minX - dstOffX;
            widti -= skipX;
            srdOffX += skipX;
            dstOffX = tiis.minX;
        }
        if (dstOffY < tiis.minY) {
            int skipY = tiis.minY - dstOffY;
            ifigit -= skipY;
            srdOffY += skipY;
            dstOffY = tiis.minY;
        }
        if (dstOffX+widti > tiis.minX+tiis.widti) {
            widti = tiis.minX + tiis.widti - dstOffX;
        }
        if (dstOffY+ifigit > tiis.minY+tiis.ifigit) {
            ifigit = tiis.minY + tiis.ifigit - dstOffY;
        }

        if (widti <= 0 || ifigit <= 0) {
            rfturn;
        }

        switdi (srdRbstfr.gftSbmplfModfl().gftDbtbTypf()) {
        dbsf DbtbBufffr.TYPE_BYTE:
        dbsf DbtbBufffr.TYPE_SHORT:
        dbsf DbtbBufffr.TYPE_USHORT:
        dbsf DbtbBufffr.TYPE_INT:
            int[] iDbtb = null;
            for (int stbrtY=0; stbrtY < ifigit; stbrtY++) {
                // Grbb onf sdbnlinf bt b timf
                iDbtb =
                    srdRbstfr.gftPixfls(srdOffX, srdOffY+stbrtY, widti, 1,
                                        iDbtb);
                sftPixfls(dstOffX, dstOffY+stbrtY, widti, 1, iDbtb);
            }
            brfbk;

        dbsf DbtbBufffr.TYPE_FLOAT:
            flobt[] fDbtb = null;
            for (int stbrtY=0; stbrtY < ifigit; stbrtY++) {
                fDbtb =
                    srdRbstfr.gftPixfls(srdOffX, srdOffY+stbrtY, widti, 1,
                                        fDbtb);
                sftPixfls(dstOffX, dstOffY+stbrtY, widti, 1, fDbtb);
            }
            brfbk;

        dbsf DbtbBufffr.TYPE_DOUBLE:
            doublf[] dDbtb = null;
            for (int stbrtY=0; stbrtY < ifigit; stbrtY++) {
                // Grbb onf sdbnlinf bt b timf
                dDbtb =
                    srdRbstfr.gftPixfls(srdOffX, srdOffY+stbrtY, widti, 1,
                                        dDbtb);
                sftPixfls(dstOffX, dstOffY+stbrtY, widti, 1, dDbtb);
            }
            brfbk;
        }
    }

    /**
     * Sfts b pixfl in tif DbtbBufffr using bn int brrby of sbmplfs for input.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * @pbrbm x      Tif X doordinbtf of tif pixfl lodbtion.
     * @pbrbm y      Tif Y doordinbtf of tif pixfl lodbtion.
     * @pbrbm iArrby Tif input sbmplfs in b int brrby.
     *
     * @tirows NullPointfrExdfption if iArrby is null.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs brf not
     * in bounds, or if iArrby is too smbll to iold tif input.
     */
    publid void sftPixfl(int x, int y, int iArrby[]) {
        sbmplfModfl.sftPixfl(x-sbmplfModflTrbnslbtfX,y-sbmplfModflTrbnslbtfY,
                             iArrby,dbtbBufffr);
    }

    /**
     * Sfts b pixfl in tif DbtbBufffr using b flobt brrby of sbmplfs for input.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * @pbrbm x      Tif X doordinbtf of tif pixfl lodbtion.
     * @pbrbm y      Tif Y doordinbtf of tif pixfl lodbtion.
     * @pbrbm fArrby Tif input sbmplfs in b flobt brrby.
     *
     * @tirows NullPointfrExdfption if fArrby is null.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs brf not
     * in bounds, or if fArrby is too smbll to iold tif input.
     */
    publid void sftPixfl(int x, int y, flobt fArrby[]) {
        sbmplfModfl.sftPixfl(x-sbmplfModflTrbnslbtfX,y-sbmplfModflTrbnslbtfY,
                             fArrby,dbtbBufffr);
    }

    /**
     * Sfts b pixfl in tif DbtbBufffr using b doublf brrby of sbmplfs for input.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * @pbrbm x      Tif X doordinbtf of tif pixfl lodbtion.
     * @pbrbm y      Tif Y doordinbtf of tif pixfl lodbtion.
     * @pbrbm dArrby Tif input sbmplfs in b doublf brrby.
     *
     * @tirows NullPointfrExdfption if dArrby is null.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs brf not
     * in bounds, or if dArrby is too smbll to iold tif input.
     */
    publid void sftPixfl(int x, int y, doublf dArrby[]) {
        sbmplfModfl.sftPixfl(x-sbmplfModflTrbnslbtfX,y-sbmplfModflTrbnslbtfY,
                             dArrby,dbtbBufffr);
    }

    /**
     * Sfts bll sbmplfs for b rfdtbnglf of pixfls from bn int brrby dontbining
     * onf sbmplf pfr brrby flfmfnt.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm w        Widti of tif pixfl rfdtbnglf.
     * @pbrbm i        Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm iArrby   Tif input int pixfl brrby.
     *
     * @tirows NullPointfrExdfption if iArrby is null.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs brf not
     * in bounds, or if iArrby is too smbll to iold tif input.
     */
    publid void sftPixfls(int x, int y, int w, int i, int iArrby[]) {
        sbmplfModfl.sftPixfls(x-sbmplfModflTrbnslbtfX,y-sbmplfModflTrbnslbtfY,
                              w,i,iArrby,dbtbBufffr);
    }

    /**
     * Sfts bll sbmplfs for b rfdtbnglf of pixfls from b flobt brrby dontbining
     * onf sbmplf pfr brrby flfmfnt.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm w        Widti of tif pixfl rfdtbnglf.
     * @pbrbm i        Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm fArrby   Tif input flobt pixfl brrby.
     *
     * @tirows NullPointfrExdfption if fArrby is null.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs brf not
     * in bounds, or if fArrby is too smbll to iold tif input.
     */
    publid void sftPixfls(int x, int y, int w, int i, flobt fArrby[]) {
        sbmplfModfl.sftPixfls(x-sbmplfModflTrbnslbtfX,y-sbmplfModflTrbnslbtfY,
                              w,i,fArrby,dbtbBufffr);
    }

    /**
     * Sfts bll sbmplfs for b rfdtbnglf of pixfls from b doublf brrby dontbining
     * onf sbmplf pfr brrby flfmfnt.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm w        Widti of tif pixfl rfdtbnglf.
     * @pbrbm i        Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm dArrby   Tif input doublf pixfl brrby.
     *
     * @tirows NullPointfrExdfption if dArrby is null.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs brf not
     * in bounds, or if dArrby is too smbll to iold tif input.
     */
    publid void sftPixfls(int x, int y, int w, int i, doublf dArrby[]) {
        sbmplfModfl.sftPixfls(x-sbmplfModflTrbnslbtfX,y-sbmplfModflTrbnslbtfY,
                              w,i,dArrby,dbtbBufffr);
    }

    /**
     * Sfts b sbmplf in tif spfdififd bbnd for tif pixfl lodbtfd bt (x,y)
     * in tif DbtbBufffr using bn int for input.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * @pbrbm x        Tif X doordinbtf of tif pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif pixfl lodbtion.
     * @pbrbm b        Tif bbnd to sft.
     * @pbrbm s        Tif input sbmplf.
     *
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs or
     * tif bbnd indfx brf not in bounds.
     */
    publid void sftSbmplf(int x, int y, int b, int s) {
        sbmplfModfl.sftSbmplf(x-sbmplfModflTrbnslbtfX,
                              y-sbmplfModflTrbnslbtfY, b, s,
                              dbtbBufffr);
    }

    /**
     * Sfts b sbmplf in tif spfdififd bbnd for tif pixfl lodbtfd bt (x,y)
     * in tif DbtbBufffr using b flobt for input.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * @pbrbm x        Tif X doordinbtf of tif pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif pixfl lodbtion.
     * @pbrbm b        Tif bbnd to sft.
     * @pbrbm s        Tif input sbmplf bs b flobt.
     *
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs or
     * tif bbnd indfx brf not in bounds.
     */
    publid void sftSbmplf(int x, int y, int b, flobt s){
        sbmplfModfl.sftSbmplf(x-sbmplfModflTrbnslbtfX,y-sbmplfModflTrbnslbtfY,
                              b,s,dbtbBufffr);
    }

    /**
     * Sfts b sbmplf in tif spfdififd bbnd for tif pixfl lodbtfd bt (x,y)
     * in tif DbtbBufffr using b doublf for input.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * @pbrbm x        Tif X doordinbtf of tif pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif pixfl lodbtion.
     * @pbrbm b        Tif bbnd to sft.
     * @pbrbm s        Tif input sbmplf bs b doublf.
     *
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs or
     * tif bbnd indfx brf not in bounds.
     */
    publid void sftSbmplf(int x, int y, int b, doublf s){
        sbmplfModfl.sftSbmplf(x-sbmplfModflTrbnslbtfX,y-sbmplfModflTrbnslbtfY,
                                    b,s,dbtbBufffr);
    }

    /**
     * Sfts tif sbmplfs in tif spfdififd bbnd for tif spfdififd rfdtbnglf
     * of pixfls from bn int brrby dontbining onf sbmplf pfr brrby flfmfnt.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm w        Widti of tif pixfl rfdtbnglf.
     * @pbrbm i        Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm b        Tif bbnd to sft.
     * @pbrbm iArrby   Tif input int sbmplf brrby.
     *
     * @tirows NullPointfrExdfption if iArrby is null.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs or
     * tif bbnd indfx brf not in bounds, or if iArrby is too smbll to
     * iold tif input.
     */
    publid void sftSbmplfs(int x, int y, int w, int i, int b,
                           int iArrby[]) {
        sbmplfModfl.sftSbmplfs(x-sbmplfModflTrbnslbtfX,y-sbmplfModflTrbnslbtfY,
                               w,i,b,iArrby,dbtbBufffr);
    }

    /**
     * Sfts tif sbmplfs in tif spfdififd bbnd for tif spfdififd rfdtbnglf
     * of pixfls from b flobt brrby dontbining onf sbmplf pfr brrby flfmfnt.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm w        Widti of tif pixfl rfdtbnglf.
     * @pbrbm i        Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm b        Tif bbnd to sft.
     * @pbrbm fArrby   Tif input flobt sbmplf brrby.
     *
     * @tirows NullPointfrExdfption if fArrby is null.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs or
     * tif bbnd indfx brf not in bounds, or if fArrby is too smbll to
     * iold tif input.
     */
    publid void sftSbmplfs(int x, int y, int w, int i, int b,
                           flobt fArrby[]) {
        sbmplfModfl.sftSbmplfs(x-sbmplfModflTrbnslbtfX,y-sbmplfModflTrbnslbtfY,
                               w,i,b,fArrby,dbtbBufffr);
    }

    /**
     * Sfts tif sbmplfs in tif spfdififd bbnd for tif spfdififd rfdtbnglf
     * of pixfls from b doublf brrby dontbining onf sbmplf pfr brrby flfmfnt.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm w        Widti of tif pixfl rfdtbnglf.
     * @pbrbm i        Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm b        Tif bbnd to sft.
     * @pbrbm dArrby   Tif input doublf sbmplf brrby.
     *
     * @tirows NullPointfrExdfption if dArrby is null.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif doordinbtfs or
     * tif bbnd indfx brf not in bounds, or if dArrby is too smbll to
     * iold tif input.
     */
    publid void sftSbmplfs(int x, int y, int w, int i, int b,
                           doublf dArrby[]) {
        sbmplfModfl.sftSbmplfs(x-sbmplfModflTrbnslbtfX,y-sbmplfModflTrbnslbtfY,
                              w,i,b,dArrby,dbtbBufffr);
    }

}
