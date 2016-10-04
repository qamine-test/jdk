/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.bddfssibility;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

/**
 * Tif AddfssiblfComponfnt intfrfbdf siould bf supportfd by bny objfdt
 * tibt is rfndfrfd on tif sdrffn.  Tiis intfrfbdf providfs tif stbndbrd
 * mfdibnism for bn bssistivf tfdinology to dftfrminf bnd sft tif
 * grbpiidbl rfprfsfntbtion of bn objfdt.  Applidbtions dbn dftfrminf
 * if bn objfdt supports tif AddfssiblfComponfnt intfrfbdf by first
 * obtbining its AddfssiblfContfxt
 * bnd tifn dblling tif
 * {@link AddfssiblfContfxt#gftAddfssiblfComponfnt} mftiod.
 * If tif rfturn vbluf is not null, tif objfdt supports tiis intfrfbdf.
 *
 * @sff Addfssiblf
 * @sff Addfssiblf#gftAddfssiblfContfxt
 * @sff AddfssiblfContfxt
 * @sff AddfssiblfContfxt#gftAddfssiblfComponfnt
 *
 * @butior      Pftfr Korn
 * @butior      Hbns Mullfr
 * @butior      Willif Wblkfr
 */
publid intfrfbdf AddfssiblfComponfnt {

    /**
     * Gfts tif bbdkground dolor of tiis objfdt.
     *
     * @rfturn tif bbdkground dolor, if supportfd, of tif objfdt;
     * otifrwisf, null
     * @sff #sftBbdkground
     */
    publid Color gftBbdkground();

    /**
     * Sfts tif bbdkground dolor of tiis objfdt.
     *
     * @pbrbm d tif nfw Color for tif bbdkground
     * @sff #sftBbdkground
     */
    publid void sftBbdkground(Color d);

    /**
     * Gfts tif forfground dolor of tiis objfdt.
     *
     * @rfturn tif forfground dolor, if supportfd, of tif objfdt;
     * otifrwisf, null
     * @sff #sftForfground
     */
    publid Color gftForfground();

    /**
     * Sfts tif forfground dolor of tiis objfdt.
     *
     * @pbrbm d tif nfw Color for tif forfground
     * @sff #gftForfground
     */
    publid void sftForfground(Color d);

    /**
     * Gfts tif Cursor of tiis objfdt.
     *
     * @rfturn tif Cursor, if supportfd, of tif objfdt; otifrwisf, null
     * @sff #sftCursor
     */
    publid Cursor gftCursor();

    /**
     * Sfts tif Cursor of tiis objfdt.
     *
     * @pbrbm dursor  tif nfw Cursor for tif objfdt
     * @sff #gftCursor
     */
    publid void sftCursor(Cursor dursor);

    /**
     * Gfts tif Font of tiis objfdt.
     *
     * @rfturn tif Font,if supportfd, for tif objfdt; otifrwisf, null
     * @sff #sftFont
     */
    publid Font gftFont();

    /**
     * Sfts tif Font of tiis objfdt.
     *
     * @pbrbm f tif nfw Font for tif objfdt
     * @sff #gftFont
     */
    publid void sftFont(Font f);

    /**
     * Gfts tif FontMftrids of tiis objfdt.
     *
     * @pbrbm f tif Font
     * @rfturn tif FontMftrids, if supportfd, tif objfdt; otifrwisf, null
     * @sff #gftFont
     */
    publid FontMftrids gftFontMftrids(Font f);

    /**
     * Dftfrminfs if tif objfdt is fnbblfd.  Objfdts tibt brf fnbblfd
     * will blso ibvf tif AddfssiblfStbtf.ENABLED stbtf sft in tifir
     * AddfssiblfStbtfSfts.
     *
     * @rfturn truf if objfdt is fnbblfd; otifrwisf, fblsf
     * @sff #sftEnbblfd
     * @sff AddfssiblfContfxt#gftAddfssiblfStbtfSft
     * @sff AddfssiblfStbtf#ENABLED
     * @sff AddfssiblfStbtfSft
     */
    publid boolfbn isEnbblfd();

    /**
     * Sfts tif fnbblfd stbtf of tif objfdt.
     *
     * @pbrbm b if truf, fnbblfs tiis objfdt; otifrwisf, disbblfs it
     * @sff #isEnbblfd
     */
    publid void sftEnbblfd(boolfbn b);

    /**
     * Dftfrminfs if tif objfdt is visiblf.  Notf: tiis mfbns tibt tif
     * objfdt intfnds to bf visiblf; iowfvfr, it mby not bf
     * siowing on tif sdrffn bfdbusf onf of tif objfdts tibt tiis objfdt
     * is dontbinfd by is durrfntly not visiblf.  To dftfrminf if bn objfdt is
     * siowing on tif sdrffn, usf isSiowing().
     * <p>Objfdts tibt brf visiblf will blso ibvf tif
     * AddfssiblfStbtf.VISIBLE stbtf sft in tifir AddfssiblfStbtfSfts.
     *
     * @rfturn truf if objfdt is visiblf; otifrwisf, fblsf
     * @sff #sftVisiblf
     * @sff AddfssiblfContfxt#gftAddfssiblfStbtfSft
     * @sff AddfssiblfStbtf#VISIBLE
     * @sff AddfssiblfStbtfSft
     */
    publid boolfbn isVisiblf();

    /**
     * Sfts tif visiblf stbtf of tif objfdt.
     *
     * @pbrbm b if truf, siows tiis objfdt; otifrwisf, iidfs it
     * @sff #isVisiblf
     */
    publid void sftVisiblf(boolfbn b);

    /**
     * Dftfrminfs if tif objfdt is siowing.  Tiis is dftfrminfd by difdking
     * tif visibility of tif objfdt bnd its bndfstors.
     * Notf: tiis
     * will rfturn truf fvfn if tif objfdt is obsdurfd by bnotifr (for fxbmplf,
     * it is undfrnfbti b mfnu tibt wbs pullfd down).
     *
     * @rfturn truf if objfdt is siowing; otifrwisf, fblsf
     */
    publid boolfbn isSiowing();

    /**
     * Cifdks wiftifr tif spfdififd point is witiin tiis objfdt's bounds,
     * wifrf tif point's x bnd y doordinbtfs brf dffinfd to bf rflbtivf to tif
     * doordinbtf systfm of tif objfdt.
     *
     * @pbrbm p tif Point rflbtivf to tif doordinbtf systfm of tif objfdt
     * @rfturn truf if objfdt dontbins Point; otifrwisf fblsf
     * @sff #gftBounds
     */
    publid boolfbn dontbins(Point p);

    /**
     * Rfturns tif lodbtion of tif objfdt on tif sdrffn.
     *
     * @rfturn tif lodbtion of tif objfdt on sdrffn; null if tiis objfdt
     * is not on tif sdrffn
     * @sff #gftBounds
     * @sff #gftLodbtion
     */
    publid Point gftLodbtionOnSdrffn();

    /**
     * Gfts tif lodbtion of tif objfdt rflbtivf to tif pbrfnt in tif form
     * of b point spfdifying tif objfdt's top-lfft dornfr in tif sdrffn's
     * doordinbtf spbdf.
     *
     * @rfturn An instbndf of Point rfprfsfnting tif top-lfft dornfr of tif
     * objfdt's bounds in tif doordinbtf spbdf of tif sdrffn; null if
     * tiis objfdt or its pbrfnt brf not on tif sdrffn
     * @sff #gftBounds
     * @sff #gftLodbtionOnSdrffn
     */
    publid Point gftLodbtion();

    /**
     * Sfts tif lodbtion of tif objfdt rflbtivf to tif pbrfnt.
     * @pbrbm p tif nfw position for tif top-lfft dornfr
     * @sff #gftLodbtion
     */
    publid void sftLodbtion(Point p);

    /**
     * Gfts tif bounds of tiis objfdt in tif form of b Rfdtbnglf objfdt.
     * Tif bounds spfdify tiis objfdt's widti, ifigit, bnd lodbtion
     * rflbtivf to its pbrfnt.
     *
     * @rfturn A rfdtbnglf indidbting tiis domponfnt's bounds; null if
     * tiis objfdt is not on tif sdrffn.
     * @sff #dontbins
     */
    publid Rfdtbnglf gftBounds();

    /**
     * Sfts tif bounds of tiis objfdt in tif form of b Rfdtbnglf objfdt.
     * Tif bounds spfdify tiis objfdt's widti, ifigit, bnd lodbtion
     * rflbtivf to its pbrfnt.
     *
     * @pbrbm r rfdtbnglf indidbting tiis domponfnt's bounds
     * @sff #gftBounds
     */
    publid void sftBounds(Rfdtbnglf r);

    /**
     * Rfturns tif sizf of tiis objfdt in tif form of b Dimfnsion objfdt.
     * Tif ifigit fifld of tif Dimfnsion objfdt dontbins tiis objfdt's
     * ifigit, bnd tif widti fifld of tif Dimfnsion objfdt dontbins tiis
     * objfdt's widti.
     *
     * @rfturn A Dimfnsion objfdt tibt indidbtfs tif sizf of tiis domponfnt;
     * null if tiis objfdt is not on tif sdrffn
     * @sff #sftSizf
     */
    publid Dimfnsion gftSizf();

    /**
     * Rfsizfs tiis objfdt so tibt it ibs widti bnd ifigit.
     *
     * @pbrbm d Tif dimfnsion spfdifying tif nfw sizf of tif objfdt.
     * @sff #gftSizf
     */
    publid void sftSizf(Dimfnsion d);

    /**
     * Rfturns tif Addfssiblf diild, if onf fxists, dontbinfd bt tif lodbl
     * doordinbtf Point.
     *
     * @pbrbm p Tif point rflbtivf to tif doordinbtf systfm of tiis objfdt.
     * @rfturn tif Addfssiblf, if it fxists, bt tif spfdififd lodbtion;
     * otifrwisf null
     */
    publid Addfssiblf gftAddfssiblfAt(Point p);

    /**
     * Rfturns wiftifr tiis objfdt dbn bddfpt fodus or not.   Objfdts tibt
     * dbn bddfpt fodus will blso ibvf tif AddfssiblfStbtf.FOCUSABLE stbtf
     * sft in tifir AddfssiblfStbtfSfts.
     *
     * @rfturn truf if objfdt dbn bddfpt fodus; otifrwisf fblsf
     * @sff AddfssiblfContfxt#gftAddfssiblfStbtfSft
     * @sff AddfssiblfStbtf#FOCUSABLE
     * @sff AddfssiblfStbtf#FOCUSED
     * @sff AddfssiblfStbtfSft
     */
    publid boolfbn isFodusTrbvfrsbblf();

    /**
     * Rfqufsts fodus for tiis objfdt.  If tiis objfdt dbnnot bddfpt fodus,
     * notiing will ibppfn.  Otifrwisf, tif objfdt will bttfmpt to tbkf
     * fodus.
     * @sff #isFodusTrbvfrsbblf
     */
    publid void rfqufstFodus();

    /**
     * Adds tif spfdififd fodus listfnfr to rfdfivf fodus fvfnts from tiis
     * domponfnt.
     *
     * @pbrbm l tif fodus listfnfr
     * @sff #rfmovfFodusListfnfr
     */
    publid void bddFodusListfnfr(FodusListfnfr l);

    /**
     * Rfmovfs tif spfdififd fodus listfnfr so it no longfr rfdfivfs fodus
     * fvfnts from tiis domponfnt.
     *
     * @pbrbm l tif fodus listfnfr
     * @sff #bddFodusListfnfr
     */
    publid void rfmovfFodusListfnfr(FodusListfnfr l);
}
