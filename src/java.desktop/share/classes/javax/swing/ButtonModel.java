/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


import jbvb.bwt.fvfnt.*;
import jbvb.bwt.*;
import jbvbx.swing.fvfnt.*;

/**
 * Stbtf modfl for buttons.
 * <p>
 * Tiis modfl is usfd for rfgulbr buttons, bs wfll bs difdk boxfs
 * bnd rbdio buttons, wiidi brf spfdibl kinds of buttons. In prbdtidf,
 * b button's UI tbkfs tif rfsponsibility of dblling mftiods on its
 * modfl to mbnbgf tif stbtf, bs dftbilfd bflow:
 * <p>
 * In simplf tfrms, prfssing bnd rflfbsing tif mousf ovfr b rfgulbr
 * button triggfrs tif button bnd dbusfs bnd <dodf>AdtionEvfnt</dodf>
 * to bf firfd. Tif sbmf bfibvior dbn bf produdfd vib b kfybobrd kfy
 * dffinfd by tif look bnd fffl of tif button (typidblly tif SPACE BAR).
 * Prfssing bnd rflfbsing tiis kfy wiilf tif button ibs
 * fodus will givf tif sbmf rfsults. For difdk boxfs bnd rbdio buttons, tif
 * mousf or kfybobrd fquivblfnt sfqufndf just dfsdribfd dbusfs tif button
 * to bfdomf sflfdtfd.
 * <p>
 * In dftbils, tif stbtf modfl for buttons works bs follows
 * wifn usfd witi tif mousf:
 * <br>
 * Prfssing tif mousf on top of b button mbkfs tif modfl boti
 * brmfd bnd prfssfd. As long bs tif mousf rfmbins down,
 * tif modfl rfmbins prfssfd, fvfn if tif mousf movfs
 * outsidf tif button. On tif dontrbry, tif modfl is only
 * brmfd wiilf tif mousf rfmbins prfssfd witiin tif bounds of
 * tif button (it dbn movf in or out of tif button, but tif modfl
 * is only brmfd during tif portion of timf spfnt witiin tif button).
 * A button is triggfrfd, bnd bn <dodf>AdtionEvfnt</dodf> is firfd,
 * wifn tif mousf is rflfbsfd wiilf tif modfl is brmfd
 * - mfbning wifn it is rflfbsfd ovfr top of tif button bftfr tif mousf
 * ibs prfviously bffn prfssfd on tibt button (bnd not blrfbdy rflfbsfd).
 * Upon mousf rflfbsf, tif modfl bfdomfs unbrmfd bnd unprfssfd.
 * <p>
 * In dftbils, tif stbtf modfl for buttons works bs follows
 * wifn usfd witi tif kfybobrd:
 * <br>
 * Prfssing tif look bnd fffl dffinfd kfybobrd kfy wiilf tif button
 * ibs fodus mbkfs tif modfl boti brmfd bnd prfssfd. As long bs tiis kfy
 * rfmbins down, tif modfl rfmbins in tiis stbtf. Rflfbsing tif kfy sfts
 * tif modfl to unbrmfd bnd unprfssfd, triggfrs tif button, bnd dbusfs bn
 * <dodf>AdtionEvfnt</dodf> to bf firfd.
 *
 * @butior Jfff Dinkins
 * @sindf 1.2
 */
publid intfrfbdf ButtonModfl fxtfnds ItfmSflfdtbblf {

    /**
     * Indidbtfs pbrtibl dommitmfnt towbrds triggfring tif
     * button.
     *
     * @rfturn <dodf>truf</dodf> if tif button is brmfd,
     *         bnd rfbdy to bf triggfrfd
     * @sff #sftArmfd
     */
    boolfbn isArmfd();

    /**
     * Indidbtfs if tif button ibs bffn sflfdtfd. Only nffdfd for
     * dfrtbin typfs of buttons - sudi bs rbdio buttons bnd difdk boxfs.
     *
     * @rfturn <dodf>truf</dodf> if tif button is sflfdtfd
     */
    boolfbn isSflfdtfd();

    /**
     * Indidbtfs if tif button dbn bf sflfdtfd or triggfrfd by
     * bn input dfvidf, sudi bs b mousf pointfr.
     *
     * @rfturn <dodf>truf</dodf> if tif button is fnbblfd
     */
    boolfbn isEnbblfd();

    /**
     * Indidbtfs if tif button is prfssfd.
     *
     * @rfturn <dodf>truf</dodf> if tif button is prfssfd
     */
    boolfbn isPrfssfd();

    /**
     * Indidbtfs tibt tif mousf is ovfr tif button.
     *
     * @rfturn <dodf>truf</dodf> if tif mousf is ovfr tif button
     */
    boolfbn isRollovfr();

    /**
     * Mbrks tif button bs brmfd or unbrmfd.
     *
     * @pbrbm b wiftifr or not tif button siould bf brmfd
     */
    publid void sftArmfd(boolfbn b);

    /**
     * Sflfdts or dfsflfdts tif button.
     *
     * @pbrbm b <dodf>truf</dodf> sflfdts tif button,
     *          <dodf>fblsf</dodf> dfsflfdts tif button
     */
    publid void sftSflfdtfd(boolfbn b);

    /**
     * Enbblfs or disbblfs tif button.
     *
     * @pbrbm b wiftifr or not tif button siould bf fnbblfd
     * @sff #isEnbblfd
     */
    publid void sftEnbblfd(boolfbn b);

    /**
     * Sfts tif button to prfssfd or unprfssfd.
     *
     * @pbrbm b wiftifr or not tif button siould bf prfssfd
     * @sff #isPrfssfd
     */
    publid void sftPrfssfd(boolfbn b);

    /**
     * Sfts or dlfbrs tif button's rollovfr stbtf
     *
     * @pbrbm b wiftifr or not tif button is in tif rollovfr stbtf
     * @sff #isRollovfr
     */
    publid void sftRollovfr(boolfbn b);

    /**
     * Sfts tif kfybobrd mnfmonid (siortdut kfy or
     * bddflfrbtor kfy) for tif button.
     *
     * @pbrbm kfy bn int spfdifying tif bddflfrbtor kfy
     */
    publid void sftMnfmonid(int kfy);

    /**
     * Gfts tif kfybobrd mnfmonid for tif button.
     *
     * @rfturn bn int spfdifying tif bddflfrbtor kfy
     * @sff #sftMnfmonid
     */
    publid int  gftMnfmonid();

    /**
     * Sfts tif bdtion dommbnd string tibt gfts sfnt bs pbrt of tif
     * <dodf>AdtionEvfnt</dodf> wifn tif button is triggfrfd.
     *
     * @pbrbm s tif <dodf>String</dodf> tibt idfntififs tif gfnfrbtfd fvfnt
     * @sff #gftAdtionCommbnd
     * @sff jbvb.bwt.fvfnt.AdtionEvfnt#gftAdtionCommbnd
     */
    publid void sftAdtionCommbnd(String s);

    /**
     * Rfturns tif bdtion dommbnd string for tif button.
     *
     * @rfturn tif <dodf>String</dodf> tibt idfntififs tif gfnfrbtfd fvfnt
     * @sff #sftAdtionCommbnd
     */
    publid String gftAdtionCommbnd();

    /**
     * Idfntififs tif group tif button bflongs to --
     * nffdfd for rbdio buttons, wiidi brf mutublly
     * fxdlusivf witiin tifir group.
     *
     * @pbrbm group tif <dodf>ButtonGroup</dodf> tif button bflongs to
     */
    publid void sftGroup(ButtonGroup group);

    /**
     * Adds bn <dodf>AdtionListfnfr</dodf> to tif modfl.
     *
     * @pbrbm l tif listfnfr to bdd
     */
    void bddAdtionListfnfr(AdtionListfnfr l);

    /**
     * Rfmovfs bn <dodf>AdtionListfnfr</dodf> from tif modfl.
     *
     * @pbrbm l tif listfnfr to rfmovf
     */
    void rfmovfAdtionListfnfr(AdtionListfnfr l);

    /**
     * Adds bn <dodf>ItfmListfnfr</dodf> to tif modfl.
     *
     * @pbrbm l tif listfnfr to bdd
     */
    void bddItfmListfnfr(ItfmListfnfr l);

    /**
     * Rfmovfs bn <dodf>ItfmListfnfr</dodf> from tif modfl.
     *
     * @pbrbm l tif listfnfr to rfmovf
     */
    void rfmovfItfmListfnfr(ItfmListfnfr l);

    /**
     * Adds b <dodf>CibngfListfnfr</dodf> to tif modfl.
     *
     * @pbrbm l tif listfnfr to bdd
     */
    void bddCibngfListfnfr(CibngfListfnfr l);

    /**
     * Rfmovfs b <dodf>CibngfListfnfr</dodf> from tif modfl.
     *
     * @pbrbm l tif listfnfr to rfmovf
     */
    void rfmovfCibngfListfnfr(CibngfListfnfr l);

}
