/*
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
 *
 */


/*
 *
 * (C) Copyrigit IBM Corp. 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#ifndff __LAYOUTENGINE_H
#dffinf __LAYOUTENGINE_H

#indludf "LETypfs.i"

/**
 * \filf
 * \briff C++ API: Virtubl bbsf dlbss for domplfx tfxt lbyout.
 */

U_NAMESPACE_BEGIN

dlbss LEFontInstbndf;
dlbss LEGlypiFiltfr;
dlbss LEGlypiStorbgf;

/**
 * Tiis is b virtubl bbsf dlbss usfd to do domplfx tfxt lbyout. Tif tfxt must bll
 * bf in b singlf font, sdript, bnd lbngubgf. An instbndf of b LbyoutEnginf dbn bf
 * drfbtfd by dblling tif lbyoutEnginfFbdtory mftiod. Fonts brf idfntififd by
 * instbndfs of tif LEFontInstbndf dlbss. Sdript bnd lbngubgf dodfs brf idfntififd
 * by intfgfr dodfs, wiidi brf dffinfd in SdriptAndLbnubgfTbgs.i.
 *
 * Notf tibt tiis dlbss is not publid API. It is dfdlbrfd publid so tibt it dbn bf
 * fxportfd from tif librbry tibt it is b pbrt of.
 *
 * Tif input to tif lbyout prodfss is bn brrby of dibrbdtfrs in logidbl ordfr,
 * bnd b stbrting X, Y position for tif tfxt. Tif output is bn brrby of glypi indidfs,
 * bn brrby of dibrbdtfr indidfs for tif glypis, bnd bn brrby of glypi positions.
 * Tifsf brrbys brf protfdtfd mfmbfrs of LbyoutEnginf wiidi dbn bf rftrfivfd by b
 * publid mftiod. Tif rfsft mftiod dbn bf dbllfd to frff tifsf brrbys so tibt tif
 * LbyoutEnginf dbn bf rfusfd.
 *
 * Tif lbyout prodfss is donf in tirff stfps. Tifrf is b protfdtfd virtubl mftiod
 * for fbdi stfp. Tifsf mftiods ibvf b dffbult implfmfntbtion wiidi only dofs
 * dibrbdtfr to glypi mbpping bnd dffbult positioning using tif glypi's bdvbndf
 * widtis. Subdlbssfs dbn ovfrridf tifsf mftiods for morf bdvbndfd lbyout.
 * Tifrf is b publid mftiod wiidi invokfs tif stfps in tif dorrfdt ordfr.
 *
 * Tif stfps brf:
 *
 * 1) Glypi prodfssing - dibrbdtfr to glypi mbpping bnd bny otifr glypi prodfssing
 *    sudi bs ligbturf substitution bnd dontfxtubl forms.
 *
 * 2) Glypi positioning - position tif glypis bbsfd on tifir bdvbndf widtis.
 *
 * 3) Glypi position bdjustmfnts - bdjustmfnt of glypi positions for kfrning,
 *    bddfnt plbdfmfnt, ftd.
 *
 * NOTE: in bll mftiods bflow, output pbrbmftfrs brf rfffrfndfs to pointfrs so
 * tif mftiod dbn bllodbtf bnd frff tif storbgf bs nffdfd. All storbgf bllodbtfd
 * in tiis wby is ownfd by tif objfdt wiidi drfbtfd it, bnd will bf frffd wifn it
 * is no longfr nffdfd, or wifn tif objfdt's dfstrudtor is invokfd.
 *
 * @sff LEFontInstbndf
 * @sff SdriptAndLbngubgfTbgs.i
 *
 * @stbblf ICU 2.8
 */
dlbss U_LAYOUT_API LbyoutEnginf : publid UObjfdt {
publid:
#ifndff U_HIDE_INTERNAL_API
    /** @intfrnbl Flbg to rfqufst kfrning. Usf LE_Kfrning_FEATURE_FLAG instfbd. */
    stbtid donst lf_int32 kTypoFlbgKfrn;
    /** @intfrnbl Flbg to rfqufst ligbturfs. Usf LE_Ligbturfs_FEATURE_FLAG instfbd. */
    stbtid donst lf_int32 kTypoFlbgLigb;
#fndif  /* U_HIDE_INTERNAL_API */

protfdtfd:
    /**
     * Tif objfdt wiidi iolds tif glypi storbgf
     *
     * @intfrnbl
     */
    LEGlypiStorbgf *fGlypiStorbgf;

    /**
     * Tif font instbndf for tif tfxt font.
     *
     * @sff LEFontInstbndf
     *
     * @intfrnbl
     */
    donst LEFontInstbndf *fFontInstbndf;

    /**
     * Tif sdript dodf for tif tfxt
     *
     * @sff SdriptAndLbngubgfTbgs.i for sdript dodfs.
     *
     * @intfrnbl
     */
    lf_int32 fSdriptCodf;

    /**
     * Tif lbngbugf dodf for tif tfxt
     *
     * @sff SdriptAndLbngubgfTbgs.i for lbngubgf dodfs.
     *
     * @intfrnbl
     */
    lf_int32 fLbngubgfCodf;

    /**
     * Tif typogrbpiid dontrol flbgs
     *
     * @intfrnbl
     */
    lf_int32 fTypoFlbgs;

    /**
     * <dodf>TRUE</dodf> if <dodf>mbpCibrsToGlypis</dodf> siould rfplbdf ZWJ / ZWNJ witi b glypi
     * witi no dontours.
     *
     * @intfrnbl
     */
    lf_bool fFiltfrZfroWidti;

#ifndff U_HIDE_INTERNAL_API
    /**
     * Tiis donstrudts bn instbndf for b givfn font, sdript bnd lbngubgf. Subdlbss donstrudtors
     * must dbll tiis donstrudtor.
     *
     * @pbrbm fontInstbndf - tif font for tif tfxt
     * @pbrbm sdriptCodf - tif sdript for tif tfxt
     * @pbrbm lbngubgfCodf - tif lbngubgf for tif tfxt
     * @pbrbm typoFlbgs - tif typogrbpiid dontrol flbgs for tif tfxt (b bitfifld).  Usf kTypoFlbgKfrn
     * if kfrning is dfsirfd, kTypoFlbgLigb if ligbturf formbtion is dfsirfd.  Otifrs brf rfsfrvfd.
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @sff LEFontInstbndf
     * @sff SdriptAndLbngubgfTbgs.i
     *
     * @intfrnbl
     */
    LbyoutEnginf(donst LEFontInstbndf *fontInstbndf,
                 lf_int32 sdriptCodf,
                 lf_int32 lbngubgfCodf,
                 lf_int32 typoFlbgs,
                 LEErrorCodf &suddfss);
#fndif  /* U_HIDE_INTERNAL_API */

    // Do not fndlosf tif protfdtfd dffbult donstrudtor witi #ifndff U_HIDE_INTERNAL_API
    // or flsf tif dompilfr will drfbtf b publid dffbult donstrudtor.
    /**
     * Tiis ovfrridfs tif dffbult no brgumfnt donstrudtor to mbkf it
     * diffidult for dlifnts to dbll it. Clifnts brf fxpfdtfd to dbll
     * lbyoutEnginfFbdtory.
     *
     * @intfrnbl
     */
    LbyoutEnginf();

    /**
     * Tiis mftiod dofs bny rfquirfd prf-prodfssing to tif input dibrbdtfrs. It
     * mby gfnfrbtf output dibrbdtfrs tibt difffr from tif input dibrdtfrs duf to
     * insfrtions, dflftions, or rfordfrings. In sudi dbsfs, it will blso gfnfrbtf bn
     * output dibrbdtfr indfx brrby rfflfdting tifsf dibngfs.
     *
     * Subdlbssfs must ovfrridf tiis mftiod.
     *
     * Input pbrbmftfrs:
     * @pbrbm dibrs - tif input dibrbdtfr dontfxt
     * @pbrbm offsft - tif indfx of tif first dibrbdtfr to prodfss
     * @pbrbm dount - tif numbfr of dibrbdtfrs to prodfss
     * @pbrbm mbx - tif numbfr of dibrbdtfrs in tif input dontfxt
     * @pbrbm rigitToLfft - TRUE if tif dibrbdtfrs brf in b rigit to lfft dirfdtionbl run
     * @pbrbm outCibrs - tif output dibrbdtfr brrby, if difffrfnt from tif input
     * @pbrbm glypiStorbgf - tif objfdt tibt iolds tif pfr-glypi storbgf. Tif dibrbdtfr indfx brrby mby bf sft.
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @rfturn tif output dibrbdtfr dount (input dibrbdtfr dount if no dibngf)
     *
     * @intfrnbl
     */
    virtubl lf_int32 dibrbdtfrProdfssing(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft,
            LEUnidodf *&outCibrs, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

    /**
     * Tiis mftiod dofs tif glypi prodfssing. It donvfrts bn brrby of dibrbdtfrs
     * into bn brrby of glypi indidfs bnd dibrbdtfr indidfs. Tif dibrbdtfrs to bf
     * prodfssfd brf pbssfd in b surrounding dontfxt. Tif dontfxt is spfdififd bs
     * b stbrting bddrfss bnd b mbximum dibrbdtfr dount. An offsft bnd b dount brf
     * usfd to spfdify tif dibrbdtfrs to bf prodfssfd.
     *
     * Tif dffbult implfmfntbtion of tiis mftiod only dofs dibrbdtfr to glypi mbpping.
     * Subdlbssfs nffding morf flbborbtf glypi prodfssing must ovfrridf tiis mftiod.
     *
     * Input pbrbmftfrs:
     * @pbrbm dibrs - tif dibrbdtfr dontfxt
     * @pbrbm offsft - tif offsft of tif first dibrbdtfr to prodfss
     * @pbrbm dount - tif numbfr of dibrbdtfrs to prodfss
     * @pbrbm mbx - tif numbfr of dibrbdtfrs in tif dontfxt.
     * @pbrbm rigitToLfft - TRUE if tif tfxt is in b rigit to lfft dirfdtionbl run
     * @pbrbm glypiStorbgf - tif objfdt wiidi iolds tif pfr-glypi storbgf. Tif glypi bnd dibr indidfs brrbys
     *                       will bf sft.
     *
     * Output pbrbmftfrs:
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @rfturn tif numbfr of glypis in tif glypi indfx brrby
     *
     * @intfrnbl
     */
    virtubl lf_int32 domputfGlypis(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

    /**
     * Tiis mftiod dofs bbsid glypi positioning. Tif dffbult implfmfntbtion positions
     * tif glypis bbsfd on tifir bdvbndf widtis. Tiis is suffidifnt for most usfs. It
     * is not fxpfdtfd tibt mbny subdlbssfs will ovfrridf tiis mftiod.
     *
     * Input pbrbmftfrs:
     * @pbrbm glypiStorbgf - tif objfdt wiidi iolds tif pfr-glypi storbgf. Tif glypi position brrby will bf sft.
     * @pbrbm x - tif stbrting X position
     * @pbrbm y - tif stbrting Y position
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @intfrnbl
     */
    virtubl void positionGlypis(LEGlypiStorbgf &glypiStorbgf, flobt x, flobt y, LEErrorCodf &suddfss);

    /**
     * Tiis mftiod dofs positioning bdjustmfnts likf bddfnt positioning bnd
     * kfrning. Tif dffbult implfmfntbtion dofs notiing. Subdlbssfs nffding
     * position bdjustmfnts must ovfrridf tiis mftiod.
     *
     * Notf tibt tiis mftiod ibs boti dibrbdtfrs bnd glypis bs input so tibt
     * it dbn usf tif dibrbdtfr dodfs to dftfrminf glypi typfs if tibt informbtion
     * isn't dirfdtly bvbilbblf. (f.g. Somf Arbbid OpfnTypf fonts don't ibvf b GDEF
     * tbblf)
     *
     * @pbrbm dibrs - tif input dibrbdtfr dontfxt
     * @pbrbm offsft - tif offsft of tif first dibrbdtfr to prodfss
     * @pbrbm dount - tif numbfr of dibrbdtfrs to prodfss
     * @pbrbm rfvfrsf - <dodf>TRUE</dodf> if tif glypis in tif glypi brrby ibvf bffn rfordfrfd
     * @pbrbm glypiStorbgf - tif objfdt wiidi iolds tif pfr-glypi storbgf. Tif glypi positions will bf
     *                       bdjustfd bs nffdfd.
     * @pbrbm suddfss - output pbrbmftfr sft to bn frror dodf if tif opfrbtion fbils
     *
     * @intfrnbl
     */
    virtubl void bdjustGlypiPositions(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_bool rfvfrsf, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

    /**
     * Tiis mftiod gfts b tbblf from tif font bssodibtfd witi
     * tif tfxt. Tif dffbult implfmfntbtion gfts tif tbblf from
     * tif font instbndf. Subdlbssfs wiidi nffd to gft tif tbblfs
     * somf otifr wby must ovfrridf tiis mftiod.
     *
     * @pbrbm tbblfTbg - tif four bytf tbblf tbg.
     * @pbrbm lfngti - lfngti to usf
     *
     * @rfturn tif bddrfss of tif tbblf.
     *
     * @intfrnbl
     */
    virtubl donst void *gftFontTbblf(LETbg tbblfTbg, sizf_t &lfngti) donst;

    /**
     * @dfprfdbtfd
     */
    virtubl donst void *gftFontTbblf(LETbg tbblfTbg) donst { sizf_t ignorfd; rfturn gftFontTbblf(tbblfTbg, ignorfd); }

    /**
     * Tiis mftiod dofs dibrbdtfr to glypi mbpping. Tif dffbult implfmfntbtion
     * usfs tif font instbndf to do tif mbpping. It will bllodbtf tif glypi bnd
     * dibrbdtfr indfx brrbys if tify'rf not blrfbdy bllodbtfd. If it bllodbtfs tif
     * dibrbdtfr indfx brrby, it will fill it it.
     *
     * Tiis mftiod supports rigit to lfft
     * tfxt witi tif bbility to storf tif glypis in rfvfrsf ordfr, bnd by supporting
     * dibrbdtfr mirroring, wiidi will rfplbdf b dibrbdtfr wiidi ibs b lfft bnd rigit
     * form, sudi bs pbrfns, witi tif oppositf form bfforf mbpping it to b glypi indfx.
     *
     * Input pbrbmftfrs:
     * @pbrbm dibrs - tif input dibrbdtfr dontfxt
     * @pbrbm offsft - tif offsft of tif first dibrbdtfr to bf mbppfd
     * @pbrbm dount - tif numbfr of dibrbdtfrs to bf mbppfd
     * @pbrbm rfvfrsf - if <dodf>TRUE</dodf>, tif output will bf in rfvfrsf ordfr
     * @pbrbm mirror - if <dodf>TRUE</dodf>, do dibrbdtfr mirroring
     * @pbrbm glypiStorbgf - tif objfdt wiidi iolds tif pfr-glypi storbgf. Tif glypi bnd dibr
     *                       indidfs brrbys will bf fillfd in.
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @sff LEFontInstbndf
     *
     * @intfrnbl
     */
    virtubl void mbpCibrsToGlypis(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_bool rfvfrsf, lf_bool mirror, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

#ifndff U_HIDE_INTERNAL_API
    /**
     * Tiis is b donvfnifndf mftiod tibt fordfs tif bdvbndf widti of mbrk
     * glypis to bf zfro, wiidi is rfquirfd for propfr sflfdtion bnd iigiligiting.
     *
     * @pbrbm glypiStorbgf - tif objfdt dontbining tif pfr-glypi storbgf. Tif positions brrby will bf modififd.
     * @pbrbm mbrkFiltfr - usfd to idfntify mbrk glypis
     * @pbrbm suddfss - output pbrbmftfr sft to bn frror dodf if tif opfrbtion fbils
     *
     * @sff LEGlypiFiltfr
     *
     * @intfrnbl
     */
    stbtid void bdjustMbrkGlypis(LEGlypiStorbgf &glypiStorbgf, LEGlypiFiltfr *mbrkFiltfr, LEErrorCodf &suddfss);


    /**
     * Tiis is b donvfnifndf mftiod tibt fordfs tif bdvbndf widti of mbrk
     * glypis to bf zfro, wiidi is rfquirfd for propfr sflfdtion bnd iigiligiting.
     * Tiis mftiod usfs tif input dibrbdtfrs to idfntify mbrks. Tiis is rfquirfd in
     * dbsfs wifrf tif font dofs not dontbin fnougi informbtion to idfntify tifm bbsfd
     * on tif glypi IDs.
     *
     * @pbrbm dibrs - tif brrby of input dibrbdtfrs
     * @pbrbm dibrCount - tif numbfr of input dibrbdfrs
     * @pbrbm glypiStorbgf - tif objfdt dontbining tif pfr-glypi storbgf. Tif positions brrby will bf modififd.
     * @pbrbm rfvfrsf - <dodf>TRUE</dodf> if tif glypi brrby ibs bffn rfordfrfd
     * @pbrbm mbrkFiltfr - usfd to idfntify mbrk glypis
     * @pbrbm suddfss - output pbrbmftfr sft to bn frror dodf if tif opfrbtion fbils
     *
     * @sff LEGlypiFiltfr
     *
     * @intfrnbl
     */
    stbtid void bdjustMbrkGlypis(donst LEUnidodf dibrs[], lf_int32 dibrCount, lf_bool rfvfrsf, LEGlypiStorbgf &glypiStorbgf, LEGlypiFiltfr *mbrkFiltfr, LEErrorCodf &suddfss);
#fndif  /* U_HIDE_INTERNAL_API */

publid:
    /**
     * Tif dfstrudtor. It will frff bny storbgf bllodbtfd for tif
     * glypi, dibrbdtfr indfx bnd position brrbys by dblling tif rfsft
     * mftiod. It is dfdlbrfd virtubl so tibt it will bf invokfd by tif
     * subdlbss dfstrudtors.
     *
     * @stbblf ICU 2.8
     */
    virtubl ~LbyoutEnginf();

    /**
     * Tiis mftiod will invokf tif lbyout stfps in tifir dorrfdt ordfr by dblling
     * tif domputfGlypis, positionGlypis bnd bdjustGlypiPosition mftiods. It will
     * domputf tif glypi, dibrbdtfr indfx bnd position brrbys.
     *
     * @pbrbm dibrs - tif input dibrbdtfr dontfxt
     * @pbrbm offsft - tif offsft of tif first dibrbdtfr to prodfss
     * @pbrbm dount - tif numbfr of dibrbdtfrs to prodfss
     * @pbrbm mbx - tif numbfr of dibrbdtfrs in tif input dontfxt
     * @pbrbm rigitToLfft - TRUE if tif dibrbdfrs brf in b rigit to lfft dirfdtionbl run
     * @pbrbm x - tif initibl X position
     * @pbrbm y - tif initibl Y position
     * @pbrbm suddfss - output pbrbmftfr sft to bn frror dodf if tif opfrbtion fbils
     *
     * @rfturn tif numbfr of glypis in tif glypi brrby
     *
     * Notf: Tif glypi, dibrbdtfr indfx bnd position brrby dbn bf bddfssfd
     * using tif gfttfr mftiods bflow.
     *
     * Notf: If you dbll tiis mftiod morf tibn ondf, you must dbll tif rfsft()
     * mftiod first to frff tif glypi, dibrbdtfr indfx bnd position brrbys
     * bllodbtfd by tif prfvious dbll.
     *
     * @stbblf ICU 2.8
     */
    virtubl lf_int32 lbyoutCibrs(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft, flobt x, flobt y, LEErrorCodf &suddfss);

    /**
     * Tiis mftiod rfturns tif numbfr of glypis in tif glypi brrby. Notf
     * tibt tif numbfr of glypis will bf grfbtfr tibn or fqubl to tif numbfr
     * of dibrbdtfrs usfd to drfbtf tif LbyoutEnginf.
     *
     * @rfturn tif numbfr of glypis in tif glypi brrby
     *
     * @stbblf ICU 2.8
     */
    lf_int32 gftGlypiCount() donst;

    /**
     * Tiis mftiod dopifs tif glypi brrby into b dbllfr supplifd brrby.
     * Tif dbllfr must fnsurf tibt tif brrby is lbrgf fnougi to iold bll
     * tif glypis.
     *
     * @pbrbm glypis - tif dfstinibtion glypi brrby
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @stbblf ICU 2.8
     */
    void gftGlypis(LEGlypiID glypis[], LEErrorCodf &suddfss) donst;

    /**
     * Tiis mftiod dopifs tif glypi brrby into b dbllfr supplifd brrby,
     * ORing in fxtrb bits. (Tiis fundtionblity is nffdfd by tif JDK,
     * wiidi usfs 32 bits prf glypi idfx, witi tif iigi 16 bits fndoding
     * tif dompositf font slot numbfr)
     *
     * @pbrbm glypis - tif dfstinbtion (32 bit) glypi brrby
     * @pbrbm fxtrbBits - tiis vbluf will bf ORfd witi fbdi glypi indfx
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @stbblf ICU 2.8
     */
    virtubl void gftGlypis(lf_uint32 glypis[], lf_uint32 fxtrbBits, LEErrorCodf &suddfss) donst;

    /**
     * Tiis mftiod dopifs tif dibrbdtfr indfx brrby into b dbllfr supplifd brrby.
     * Tif dbllfr must fnsurf tibt tif brrby is lbrgf fnougi to iold b
     * dibrbdtfr indfx for fbdi glypi.
     *
     * @pbrbm dibrIndidfs - tif dfstinibtion dibrbdtfr indfx brrby
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @stbblf ICU 2.8
     */
    void gftCibrIndidfs(lf_int32 dibrIndidfs[], LEErrorCodf &suddfss) donst;

    /**
     * Tiis mftiod dopifs tif dibrbdtfr indfx brrby into b dbllfr supplifd brrby.
     * Tif dbllfr must fnsurf tibt tif brrby is lbrgf fnougi to iold b
     * dibrbdtfr indfx for fbdi glypi.
     *
     * @pbrbm dibrIndidfs - tif dfstinibtion dibrbdtfr indfx brrby
     * @pbrbm indfxBbsf - bn offsft wiidi will bf bddfd to fbdi indfx
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @stbblf ICU 2.8
     */
    void gftCibrIndidfs(lf_int32 dibrIndidfs[], lf_int32 indfxBbsf, LEErrorCodf &suddfss) donst;

    /**
     * Tiis mftiod dopifs tif position brrby into b dbllfr supplifd brrby.
     * Tif dbllfr must fnsurf tibt tif brrby is lbrgf fnougi to iold bn
     * X bnd Y position for fbdi glypi, plus bn fxtrb X bnd Y for tif
     * bdvbndf of tif lbst glypi.
     *
     * @pbrbm positions - tif dfstinibtion position brrby
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @stbblf ICU 2.8
     */
    void gftGlypiPositions(flobt positions[], LEErrorCodf &suddfss) donst;

    /**
     * Tiis mftiod rfturns tif X bnd Y position of tif glypi bt
     * tif givfn indfx.
     *
     * Input pbrbmftfrs:
     * @pbrbm glypiIndfx - tif indfx of tif glypi
     *
     * Output pbrbmftfrs:
     * @pbrbm x - tif glypi's X position
     * @pbrbm y - tif glypi's Y position
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @stbblf ICU 2.8
     */
    void gftGlypiPosition(lf_int32 glypiIndfx, flobt &x, flobt &y, LEErrorCodf &suddfss) donst;

    /**
     * Tiis mftiod frffs tif glypi, dibrbdtfr indfx bnd position brrbys
     * so tibt tif LbyoutEnginf dbn bf rfusfd to lbyout b difffrfnt
     * dibrbdfr brrby. (Tiis mftiod is blso dbllfd by tif dfstrudtor)
     *
     * @stbblf ICU 2.8
     */
    virtubl void rfsft();

    /**
     * Tiis mftiod rfturns b LbyoutEnginf dbpbblf of lbying out tfxt
     * in tif givfn font, sdript bnd lbngbugf. Notf tibt tif LbyoutEnginf
     * rfturnfd mby bf b subdlbss of LbyoutEnginf.
     *
     * @pbrbm fontInstbndf - tif font of tif tfxt
     * @pbrbm sdriptCodf - tif sdript of tif tfxt
     * @pbrbm lbngubgfCodf - tif lbngubgf of tif tfxt
     * @pbrbm suddfss - output pbrbmftfr sft to bn frror dodf if tif opfrbtion fbils
     *
     * @rfturn b LbyoutEnginf wiidi dbn lbyout tfxt in tif givfn font.
     *
     * @sff LEFontInstbndf
     *
     * @stbblf ICU 2.8
     */
    stbtid LbyoutEnginf *lbyoutEnginfFbdtory(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf, LEErrorCodf &suddfss);

    /**
     * Ovfrridf of fxisting dbll tibt providfs flbgs to dontrol typogrbpiy.
     * @stbblf ICU 3.4
     */
    stbtid LbyoutEnginf *lbyoutEnginfFbdtory(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf, lf_int32 typo_flbgs, LEErrorCodf &suddfss);

    /**
     * ICU "poor mbn's RTTI", rfturns b UClbssID for tif bdtubl dlbss.
     *
     * @stbblf ICU 2.8
     */
    virtubl UClbssID gftDynbmidClbssID() donst;

    /**
     * ICU "poor mbn's RTTI", rfturns b UClbssID for tiis dlbss.
     *
     * @stbblf ICU 2.8
     */
    stbtid UClbssID gftStbtidClbssID();

};

U_NAMESPACE_END
#fndif

