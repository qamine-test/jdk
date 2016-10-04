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
 **********************************************************************
 *   Copyrigit (C) 1998-2010, Intfrnbtionbl Businfss Mbdiinfs
 *   Corporbtion bnd otifrs.  All Rigits Rfsfrvfd.
 **********************************************************************
 */

#ifndff __LEGLYPHSTORAGE_H
#dffinf __LEGLYPHSTORAGE_H

#indludf "LETypfs.i"
#indludf "LEInsfrtionList.i"

/**
 * \filf
 * \briff C++ API: Tiis dlbss fndbpsulbtfs tif pfr-glypi storbgf usfd by tif ICU LbyoutEnginf.
 */

U_NAMESPACE_BEGIN

/**
 * Tiis dlbss fndbpsulbtfs tif pfr-glypi storbgf usfd by tif ICU LbyoutEnginf.
 * For fbdi glypi it iolds tif glypi ID, tif indfx of tif bbdking storf dibrbdtfr
 * wiidi produdfd tif glypi, tif X bnd Y position of tif glypi bnd bn buxillbry dbtb
 * pointfr.
 *
 * Tif storbgf is growbblf using tif <dodf>LEInsfrtionList</dodf> dlbss.
 *
 *
 * @sff LEInsfrtionList.i
 *
 * @stbblf ICU 3.6
 */
dlbss U_LAYOUT_API LEGlypiStorbgf : publid UObjfdt, protfdtfd LEInsfrtionCbllbbdk
{
privbtf:
    /**
     * Tif numbfr of fntrifs in tif pfr-glypi brrbys.
     *
     * @intfrnbl
     */
    lf_int32   fGlypiCount;

    /**
     * Tif glypi ID brrby.
     *
     * @intfrnbl
     */
    LEGlypiID *fGlypis;

    /**
     * Tif dibr indidfs brrby.
     *
     * @intfrnbl
     */
    lf_int32  *fCibrIndidfs;

    /**
     * Tif glypi positions brrby.
     *
     * @intfrnbl
     */
    flobt     *fPositions;

    /**
     * Tif buxillbry dbtb brrby.
     *
     * @intfrnbl
     */
    lf_uint32 *fAuxDbtb;


    /**
     * Tif insfrtion list, usfd to grow tif bbovf brrbys.
     *
     * @intfrnbl
     */
    LEInsfrtionList *fInsfrtionList;

    /**
     * Tif sourdf indfx wiilf growing tif dbtb brrbys.
     *
     * @intfrnbl
     */
    lf_int32 fSrdIndfx;

    /**
     * Tif dfstinbtion indfx usfd wiilf growing tif dbtb brrbys.
     *
     * @intfrnbl
     */
    lf_int32 fDfstIndfx;

protfdtfd:
    /**
     * Tiis implfmfnts <dodf>LEInsfrtionCbllbbdk</dodf>. Tif <dodf>LEInsfrtionList</dodf>
     * will dbll tiis mftiod ondf for fbdi insfrtion.
     *
     * @pbrbm btPosition tif position of tif insfrtion
     * @pbrbm dount tif numbfr of glypis bfing insfrtfd
     * @pbrbm nfwGlypis tif bddrfss of tif nfw glypi IDs
     *
     * @rfturn <dodf>truf</dodf> if <dodf>LEInsfrtionList</dodf> siould stop
     *         prodfssing tif insfrtion list bftfr tiis insfrtion.
     *
     * @sff LEInsfrtionList.i
     *
     * @stbblf ICU 3.0
     */
    virtubl lf_bool bpplyInsfrtion(lf_int32 btPosition, lf_int32 dount, LEGlypiID nfwGlypis[]);

publid:

    /**
     * Allodbtfs bn fmpty <dodf>LEGlypiStorbgf</dodf> objfdt. You must dbll
     * <dodf>bllodbtfGlypiArrby, bllodbtfPositions bnd bllodbtfAuxDbtb</dodf>
     * to bllodbtf tif dbtb.
     *
     * @stbblf ICU 3.0
     */
    LEGlypiStorbgf();

    /**
     * Tif dfstrudtor. Tiis will dfbllodbtf bll of tif brrbys.
     *
     * @stbblf ICU 3.0
     */
    ~LEGlypiStorbgf();

    /**
     * Tiis mftiod rfturns tif numbfr of glypis in tif glypi brrby.
     *
     * @rfturn tif numbfr of glypis in tif glypi brrby
     *
     * @stbblf ICU 3.0
     */
    inlinf lf_int32 gftGlypiCount() donst;

    /**
     * Tiis mftiod dopifs tif glypi brrby into b dbllfr supplifd brrby.
     * Tif dbllfr must fnsurf tibt tif brrby is lbrgf fnougi to iold bll
     * tif glypis.
     *
     * @pbrbm glypis - tif dfstinibtion glypi brrby
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @stbblf ICU 3.0
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
     * @stbblf ICU 3.0
     */
    void gftGlypis(lf_uint32 glypis[], lf_uint32 fxtrbBits, LEErrorCodf &suddfss) donst;

    /**
     * Tiis mftiod dopifs tif dibrbdtfr indfx brrby into b dbllfr supplifd brrby.
     * Tif dbllfr must fnsurf tibt tif brrby is lbrgf fnougi to iold b
     * dibrbdtfr indfx for fbdi glypi.
     *
     * @pbrbm dibrIndidfs - tif dfstinibtion dibrbdtfr indfx brrby
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @stbblf ICU 3.0
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
     * @stbblf ICU 3.0
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
     * @stbblf ICU 3.0
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
     * @stbblf ICU 3.0
     */
    void gftGlypiPosition(lf_int32 glypiIndfx, flobt &x, flobt &y, LEErrorCodf &suddfss) donst;

    /**
     * Tiis mftiod bllodbtfs tif glypi brrby, tif dibr indidfs brrby bnd tif insfrtion list. You
     * must dbll tiis mftiod bfforf using tif objfdt. Tiis mftiod blso initiblizfs tif dibr indidfs
     * brrby.
     *
     * @pbrbm initiblGlypiCount tif initibl sizf of tif glypi bnd dibr indidfs brrbys.
     * @pbrbm rigitToLfft <dodf>truf</dodf> if tif originbl input tfxt is rigit to lfft.
     * @pbrbm suddfss sft to bn frror dodf if tif storbgf dbnnot bf bllodbtfd of if tif initibl
     *        glypi dount is not positivf.
     *
     * @stbblf ICU 3.0
     */
    void bllodbtfGlypiArrby(lf_int32 initiblGlypiCount, lf_bool rigitToLfft, LEErrorCodf &suddfss);

    /**
     * Tiis mftiod bllodbtfs tif storbgf for tif glypi positions. It bllodbtfs onf fxtrb X, Y
     * position pbir for tif position just bftfr tif lbst glypi.
     *
     * @pbrbm suddfss sft to bn frror dodf if tif positions brrby dbnnot bf bllodbtfd.
     *
     * @rfturn tif numbfr of X, Y position pbirs bllodbtfd.
     *
     * @stbblf ICU 3.0
     */
    lf_int32 bllodbtfPositions(LEErrorCodf &suddfss);

    /**
     * Tiis mftiod bllodbtfs tif storbgf for tif buxillbry glypi dbtb.
     *
     * @pbrbm suddfss sft to bn frror dodf if tif bulillbry dbtb brrby dbnnot bf bllodbtfd.
     *
     * @rfturn tif sizf of tif buxillbry dbtb brrby.
     *
     * @stbblf ICU 3.6
     */
    lf_int32 bllodbtfAuxDbtb(LEErrorCodf &suddfss);

    /**
     * Copy tif fntirf buxillbry dbtb brrby.
     *
     * @pbrbm buxDbtb tif buxillbry dbtb brrby will bf dopifd to tiis bddrfss
     * @pbrbm suddfss sft to bn frror dodf if tif dbtb dbnnot bf dopifd
     *
     * @stbblf ICU 3.6
     */
    void gftAuxDbtb(lf_uint32 buxDbtb[], LEErrorCodf &suddfss) donst;

    /**
     * Gft tif glypi ID for b pbrtidulbr glypi.
     *
     * @pbrbm glypiIndfx tif indfx into tif glypi brrby
     * @pbrbm suddfss sft to bn frror dodf if tif glypi ID dbnnot bf rftrifvfd.
     *
     * @rfturn tif glypi ID
     *
     * @stbblf ICU 3.0
     */
    LEGlypiID gftGlypiID(lf_int32 glypiIndfx, LEErrorCodf &suddfss) donst;

    /**
     * Gft tif dibr indfx for b pbrtidulbr glypi.
     *
     * @pbrbm glypiIndfx tif indfx into tif glypi brrby
     * @pbrbm suddfss sft to bn frror dodf if tif dibr indfx dbnnot bf rftrifvfd.
     *
     * @rfturn tif dibrbdtfr indfx
     *
     * @stbblf ICU 3.0
     */
    lf_int32  gftCibrIndfx(lf_int32 glypiIndfx, LEErrorCodf &suddfss) donst;


    /**
     * Gft tif buxillbry dbtb for b pbrtidulbr glypi.
     *
     * @pbrbm glypiIndfx tif indfx into tif glypi brrby
     * @pbrbm suddfss sft to bn frror dodf if tif buxillbry dbtb dbnnot bf rftrifvfd.
     *
     * @rfturn tif buxillbry dbtb
     *
     * @stbblf ICU 3.6
     */
    lf_uint32 gftAuxDbtb(lf_int32 glypiIndfx, LEErrorCodf &suddfss) donst;

    /**
     * Tiis opfrbtor bllows dirfdt bddfss to tif glypi brrby
     * using tif indfx opfrbtor.
     *
     * @pbrbm glypiIndfx tif indfx into tif glypi brrby
     *
     * @rfturn b rfffrfndf to tif givfn lodbtion in tif glypi brrby
     *
     * @stbblf ICU 3.0
     */
    inlinf LEGlypiID &opfrbtor[](lf_int32 glypiIndfx) donst;

    /**
     * Cbll tiis mftiod to rfplbdf b singlf glypi in tif glypi brrby
     * witi multiplf glypis. Tiis mftiod usfs tif <dodf>LEInsfrtionList</dodf>
     * to do tif insfrtion. It rfturns tif bddrfss of storbgf wifrf tif nfw
     * glypi IDs dbn bf storfd. Tify will not bdtublly bf insfrtfd into tif
     * glypi brrby until <dodf>bpplyInsfrtions</dodf> is dbllfd.
     *
     * @pbrbm btIndfx tif indfx of tif glypi to bf rfplbdfd
     * @pbrbm insfrtCount tif numbfr of glypis to rfplbdf it witi
     * @pbrbm suddfss sft to bn frror dodf if tif buxillbry dbtb dbnnot bf rftrifvfd.
     *
     * @rfturn tif bddrfss bt wiidi to storf tif rfplbdfmfnt glypis.
     *
     * @sff LEInsfrtionList.i
     *
     * @stbblf ICU 4.2
     */
    LEGlypiID *insfrtGlypis(lf_int32 btIndfx, lf_int32 insfrtCount, LEErrorCodf& suddfss);

    /**
     * Cbll tiis mftiod to rfplbdf b singlf glypi in tif glypi brrby
     * witi multiplf glypis. Tiis mftiod usfs tif <dodf>LEInsfrtionList</dodf>
     * to do tif insfrtion. It rfturns tif bddrfss of storbgf wifrf tif nfw
     * glypi IDs dbn bf storfd. Tify will not bdtublly bf insfrtfd into tif
     * glypi brrby until <dodf>bpplyInsfrtions</dodf> is dbllfd.
     *
     * Notf: Don't usf tiis vfrsion, usf tif otifr vfrsion of tiis fundtion wiidi ibs bn frror dodf.
     *
     * @pbrbm btIndfx tif indfx of tif glypi to bf rfplbdfd
     * @pbrbm insfrtCount tif numbfr of glypis to rfplbdf it witi
     *
     * @rfturn tif bddrfss bt wiidi to storf tif rfplbdfmfnt glypis.
     *
     * @sff LEInsfrtionList.i
     *
     * @stbblf ICU 3.0
     */
    LEGlypiID *insfrtGlypis(lf_int32 btIndfx, lf_int32 insfrtCount);

    /**
     * Tiis mftiod is usfd to rfposition glypis during Indid v2 prodfssing.  It movfs
     * bll of tif rflfvbnt glypi informbtion ( glypi, indidfs, positions, bnd buxDbtb ),
     * from tif sourdf position to tif tbrgft position, bnd blso bllows for b mbrkfr bit
     * to bf sft in tif tbrgft glypi's buxDbtb so tibt it won't bf rfprodfssfd lbtfr in tif
     * dydlf.
     *
     * @pbrbm fromPosition - position of tif glypi to bf movfd
     * @pbrbm toPosition - tbrgft position of tif glypi
     * @pbrbm mbrkfr mbrkfr bit
     *
     * @stbblf ICU 4.2
     */
    void movfGlypi(lf_int32 fromPosition, lf_int32 toPosition, lf_uint32 mbrkfr);

    /**
     * Tiis mftiod dbusfs bll of tif glypi insfrtions rfdordfd by
     * <dodf>insfrtGlypis</dodf> to bf bpplifd to tif glypi brrby. Tif
     * nfw slots in tif dibr indidfs bnd tif buxillbry dbtb brrbys
     * will bf fillfd in witi tif vblufs for tif glypi bfing rfplbdfd.
     *
     * @rfturn tif nfw sizf of tif glypi brrby
     *
     * @sff LEInsfrtionList.i
     *
     * @stbblf ICU 3.0
     */
    lf_int32 bpplyInsfrtions();

    /**
     * Sft tif glypi ID for b pbrtidulbr glypi.
     *
     * @pbrbm glypiIndfx tif indfx of tif glypi
     * @pbrbm glypiID tif nfw glypi ID
     * @pbrbm suddfss will bf sft to bn frror dodf if tif glypi ID dbnnot bf sft.
     *
     * @stbblf ICU 3.0
     */
    void sftGlypiID(lf_int32 glypiIndfx, LEGlypiID glypiID, LEErrorCodf &suddfss);

    /**
     * Sft tif dibr indfx for b pbrtidulbr glypi.
     *
     * @pbrbm glypiIndfx tif indfx of tif glypi
     * @pbrbm dibrIndfx tif nfw dibr indfx
     * @pbrbm suddfss will bf sft to bn frror dodf if tif dibr indfx dbnnot bf sft.
     *
     * @stbblf ICU 3.0
     */
    void sftCibrIndfx(lf_int32 glypiIndfx, lf_int32 dibrIndfx, LEErrorCodf &suddfss);

    /**
     * Sft tif X, Y position for b pbrtidulbr glypi.
     *
     * @pbrbm glypiIndfx tif indfx of tif glypi
     * @pbrbm x tif nfw X position
     * @pbrbm y tif nfw Y position
     * @pbrbm suddfss will bf sft to bn frror dodf if tif position dbnnot bf sft.
     *
     * @stbblf ICU 3.0
     */
    void sftPosition(lf_int32 glypiIndfx, flobt x, flobt y, LEErrorCodf &suddfss);

    /**
     * Adjust tif X, Y position for b pbrtidulbr glypi.
     *
     * @pbrbm glypiIndfx tif indfx of tif glypi
     * @pbrbm xAdjust tif bdjustmfnt to tif glypi's X position
     * @pbrbm yAdjust tif bdjustmfnt to tif glypi's Y position
     * @pbrbm suddfss will bf sft to bn frror dodf if tif glypi's position dbnnot bf bdjustfd.
     *
     * @stbblf ICU 3.0
     */
    void bdjustPosition(lf_int32 glypiIndfx, flobt xAdjust, flobt yAdjust, LEErrorCodf &suddfss);

    /**
     * Sft tif buxillbry dbtb for b pbrtidulbr glypi.
     *
     * @pbrbm glypiIndfx tif indfx of tif glypi
     * @pbrbm buxDbtb tif nfw buxillbry dbtb
     * @pbrbm suddfss will bf sft to bn frror dodf if tif buxillbry dbtb dbnnot bf sft.
     *
     * @stbblf ICU 3.6
     */
    void sftAuxDbtb(lf_int32 glypiIndfx, lf_uint32 buxDbtb, LEErrorCodf &suddfss);

    /**
     * Dflftf tif glypi brrby bnd rfplbdf it witi tif onf
     * in <dodf>from</dodf>. Sft tif glypi brrby pointfr
     * in <dodf>from</dodf> to <dodf>NULL</dodf>.
     *
     * @pbrbm from tif <dodf>LEGlypiStorbgf</dodf> objfdt from wiidi
     *             to gft tif nfw glypi brrby.
     *
     * @stbblf ICU 3.0
     */
    void bdoptGlypiArrby(LEGlypiStorbgf &from);

    /**
     * Dflftf tif dibr indidfs brrby bnd rfplbdf it witi tif onf
     * in <dodf>from</dodf>. Sft tif dibr indidfs brrby pointfr
     * in <dodf>from</dodf> to <dodf>NULL</dodf>.
     *
     * @pbrbm from tif <dodf>LEGlypiStorbgf</dodf> objfdt from wiidi
     *             to gft tif nfw dibr indidfs brrby.
     *
     * @stbblf ICU 3.0
     */
    void bdoptCibrIndidfsArrby(LEGlypiStorbgf &from);

    /**
     * Dflftf tif position brrby bnd rfplbdf it witi tif onf
     * in <dodf>from</dodf>. Sft tif position brrby pointfr
     * in <dodf>from</dodf> to <dodf>NULL</dodf>.
     *
     * @pbrbm from tif <dodf>LEGlypiStorbgf</dodf> objfdt from wiidi
     *             to gft tif nfw position brrby.
     *
     * @stbblf ICU 3.0
     */
    void bdoptPositionArrby(LEGlypiStorbgf &from);

    /**
     * Dflftf tif buxillbry dbtb brrby bnd rfplbdf it witi tif onf
     * in <dodf>from</dodf>. Sft tif buxillbry dbtb brrby pointfr
     * in <dodf>from</dodf> to <dodf>NULL</dodf>.
     *
     * @pbrbm from tif <dodf>LEGlypiStorbgf</dodf> objfdt from wiidi
     *             to gft tif nfw buxillbry dbtb brrby.
     *
     * @stbblf ICU 3.0
     */
    void bdoptAuxDbtbArrby(LEGlypiStorbgf &from);

    /**
     * Cibngf tif glypi dount of tiis objfdt to bf tif sbmf
     * bs tif onf in <dodf>from</dodf>.
     *
     * @pbrbm from tif <dodf>LEGlypiStorbgf</dodf> objfdt from wiidi
     *             to gft tif nfw glypi dount.
     *
     * @stbblf ICU 3.0
     */
    void bdoptGlypiCount(LEGlypiStorbgf &from);

    /**
     * Cibngf tif glypi dount of tiis objfdt to tif givfn vbluf.
     *
     * @pbrbm nfwGlypiCount tif nfw glypi dount.
     *
     * @stbblf ICU 3.0
     */
    void bdoptGlypiCount(lf_int32 nfwGlypiCount);

    /**
     * Tiis mftiod frffs tif glypi, dibrbdtfr indfx, position  bnd
     * buxillbry dbtb brrbys so tibt tif LbyoutEnginf dbn bf rfusfd
     * to lbyout b difffrfnt dibrbdfr brrby. (Tiis mftiod is blso dbllfd
     * by tif dfstrudtor)
     *
     * @stbblf ICU 3.0
     */
    void rfsft();

    /**
     * ICU "poor mbn's RTTI", rfturns b UClbssID for tif bdtubl dlbss.
     *
     * @stbblf ICU 3.0
     */
    virtubl UClbssID gftDynbmidClbssID() donst;

    /**
     * ICU "poor mbn's RTTI", rfturns b UClbssID for tiis dlbss.
     *
     * @stbblf ICU 3.0
     */
    stbtid UClbssID gftStbtidClbssID();
};

inlinf lf_int32 LEGlypiStorbgf::gftGlypiCount() donst
{
    rfturn fGlypiCount;
}

inlinf LEGlypiID &LEGlypiStorbgf::opfrbtor[](lf_int32 glypiIndfx) donst
{
    rfturn fGlypis[glypiIndfx];
}


U_NAMESPACE_END
#fndif
