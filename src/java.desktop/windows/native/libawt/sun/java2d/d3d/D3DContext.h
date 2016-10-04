/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff D3DCONTEXT_H
#dffinf D3DCONTEXT_H

#indludf "jbvb_bwt_Trbnspbrfndy.i"
#indludf "sun_jbvb2d_pipf_BufffrfdContfxt.i"
#indludf "sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps.i"
#indludf "sun_jbvb2d_d3d_D3DSurfbdfDbtb.i"
#indludf "sun_jbvb2d_pipf_iw_AddflDfvidfEvfntNotififr.i"

#indludf "SibdfrList.i"
#indludf "D3DPipflinf.i"
#indludf "D3DMbskCbdif.i"
#indludf "D3DVfrtfxCbdifr.i"
#indludf "D3DRfsourdfMbnbgfr.i"

#indludf "j2d_md.i"

typfdff fnum {
    TILEFMT_UNKNOWN,
    TILEFMT_1BYTE_ALPHA,
    TILEFMT_3BYTE_RGB,
    TILEFMT_3BYTE_BGR,
    TILEFMT_4BYTE_ARGB_PRE,
} TilfFormbt;

typfdff fnum {
    CLIP_NONE,
    CLIP_RECT,
    CLIP_SHAPE,
} ClipTypf;

// - Stbtf switdiing optimizbtions -----------------------------------

/**
 * Tif gobl is to rfdudf dfvidf stbtf switdiing bs mudi bs possiblf.
 * Tiis mfbns: don't rfsft tif tfxturf if not nffdfd, don't dibngf
 * tif tfxturf stbgf stbtfs unlfss nfdfssbry.
 * For tiis wf nffd to trbdk tif durrfnt dfvidf stbtf. So fbdi opfrbtion
 * supplifs its own opfrbtion typf to BfginSdfnf, wiidi updbtfs tif stbtf
 * bs nfdfssbry.
 *
 * Anotifr optimizbtion is to usf b singlf vfrtfx formbt for
 * bll primitivfs.
 *
 * Sff D3DContfxt::UpdbtfStbtf() bnd D3DContfxt::BfginSdfnf() for
 * morf informbtion.
 */
#dffinf STATE_CHANGE    (0 << 0)
#dffinf STATE_RENDEROP  (1 << 0)
#dffinf STATE_MASKOP    (1 << 1)
#dffinf STATE_GLYPHOP   (1 << 2)
#dffinf STATE_TEXTUREOP (1 << 3)
#dffinf STATE_AAPGRAMOP (1 << 4)
#dffinf STATE_OTHEROP   (1 << 5)

// Tif mbx. stbgf numbfr wf durrfntly usf (dould not bf
// lbrgfr tibn 7)
#dffinf MAX_USED_TEXTURE_SAMPLER 1

// - Tfxturf pixfl formbt tbblf  -------------------------------------
#dffinf TR_OPAQUE      jbvb_bwt_Trbnspbrfndy_OPAQUE
#dffinf TR_BITMASK     jbvb_bwt_Trbnspbrfndy_BITMASK
#dffinf TR_TRANSLUCENT jbvb_bwt_Trbnspbrfndy_TRANSLUCENT

dlbss D3DRfsourdf;
dlbss D3DRfsourdfMbnbgfr;
dlbss D3DMbskCbdif;
dlbss D3DVfrtfxCbdifr;
dlbss D3DGlypiCbdif;

// - D3DContfxt dlbss  -----------------------------------------------

/**
 * Tiis dlbss providfs tif following fundtionblity:
 *  - iolds tif stbtf of D3DContfxt jbvb dlbss (durrfnt pixfl dolor,
 *    blpib dompositing modf, fxtrb blpib)
 *  - providfs bddfss to IDirfdt3DDfvidf9 intfrfbdf (drfbtion,
 *    disposbl, fxdlusivf bddfss)
 *  - ibndlfs stbtf dibngfs of tif dirfdt3d dfvidf (trbnsform,
 *    dompositing modf, durrfnt tfxturf)
 *  - providfs mfbns of drfbting tfxturfs, plbin surfbdfs
 *  - iolds b glypi dbdif tfxturf for tif bssodibtfd dfvidf
 *  - implfmfnts primitivfs bbtdiing mfdibnism
 */
dlbss D3DPIPELINE_API D3DContfxt {
publid:
    /**
     * Rflfbsfs tif old dfvidf (if tifrf wbs onf) bnd bll bssodibtfd
     * rfsourdfs, rf-drfbtfs, initiblizfs bnd tfsts tif nfw dfvidf.
     *
     * If tif dfvidf dofsn't pbss tif tfst, it's rflfbsfd.
     *
     * Usfd wifn tif dontfxt is first drfbtfd, bnd tifn bftfr b
     * displby dibngf fvfnt.
     *
     * Notf tibt tiis mftiod blso dofs tif nfdfssbry rfgistry difdks,
     * bnd if tif rfgistry siows tibt wf'vf drbsifd wifn bttfmpting
     * to initiblizf bnd tfst tif dfvidf lbst timf, it dofsn't bttfmpt
     * to drfbtf/init/tfst tif dfvidf.
     */
    stbtid
    HRESULT CrfbtfInstbndf(IDirfdt3D9 *pd3d9, UINT bdbptfr, D3DContfxt **ppCtx);
    // drfbtfs b nfw D3D windowfd dfvidf witi swbp dopy ffffdt bnd dffbult
    // prfsfnt intfrvbl
    HRESULT InitContfxt();
    // drfbtfs or rfsfts b D3D dfvidf givfn tif pbrbmftfrs
    HRESULT ConfigurfContfxt(D3DPRESENT_PARAMETERS *pNfwPbrbms);
    // rfsfts fxisting D3D dfvidf witi tif durrfnt prfsfntbtion pbrbmftfrs
    HRESULT RfsftContfxt();
    HRESULT CifdkAndRfsftDfvidf();

    // sbvfs tif stbtf of tif D3D dfvidf in b stbtf blodk, rfsfts
    // dontfxt's stbtf to STATE_CHANGE
    HRESULT SbvfStbtf();
    // rfstorfs tif stbtf of tif D3D dfvidf from fxisting stbtf blodk,
    // rfsfts dontfxt's stbtf to STATE_CHANGE
    HRESULT RfstorfStbtf();

    void    RflfbsfContfxtRfsourdfs();
    void    RflfbsfDffPoolRfsourdfs();
    virtubl ~D3DContfxt();

    // mftiods rfplidbting jbvb-lfvfl D3DContfxt objfxt
    HRESULT SftAlpibCompositf(jint rulf, jflobt fxtrbAlpib, jint flbgs);
    HRESULT RfsftCompositf();

    /**
     * Glypi dbdif-rflbtfd mftiods
     */
    HRESULT InitGrbysdblfGlypiCbdif();
    HRESULT InitLCDGlypiCbdif();
    D3DGlypiCbdif* GftGrbysdblfGlypiCbdif() { rfturn pGrbysdblfGlypiCbdif; }
    D3DGlypiCbdif* GftLCDGlypiCbdif() { rfturn pLCDGlypiCbdif; }

    D3DRfsourdfMbnbgfr *GftRfsourdfMbnbgfr() { rfturn pRfsourdfMgr; }
    D3DMbskCbdif       *GftMbskCbdif() { rfturn pMbskCbdif; }

    HRESULT UplobdTilfToTfxturf(D3DRfsourdf *pTfxturfRfs, void *pixfls,
                                jint dstx, jint dsty,
                                jint srdx, jint srdy,
                                jint srdWidti, jint srdHfigit,
                                jint srdStridf,
                                TilfFormbt srdFormbt,
                                // out: num of pixfls in first bnd lbst
                                // dolumns, only dountfd for LCD glypi uplobds
                                jint *pPixflsToudifdL = NULL,
                                jint *pPixflsToudifdR = NULL);

    // rfturns dbpbbilitifs of tif Dirfdt3D dfvidf
    D3DCAPS9 *GftDfvidfCbps() { rfturn &dfvCbps; }
    // rfturns dbps in tfrms of tif D3DContfxt
    int GftContfxtCbps() { rfturn dontfxtCbps; }
    D3DPRESENT_PARAMETERS *GftPrfsfntbtionPbrbms() { rfturn &durPbrbms; }

    IDirfdt3DDfvidf9 *Gft3DDfvidf() { rfturn pd3dDfvidf; }
    IDirfdt3D9 *Gft3DObjfdt() { rfturn pd3dObjfdt; }

    /**
     * Tiis mftiod only sfts tif tfxturf if it's not blrfbdy sft.
     */
    HRESULT SftTfxturf(IDirfdt3DTfxturf9 *pTfxturf, DWORD dwSbmplfr = 0);

    /**
     * Tiis mftiod only updbtfs tif tfxturf dolor stbtf if it ibsn't dibngfd.
     */
    HRESULT UpdbtfTfxturfColorStbtf(DWORD dwStbtf, DWORD dwSbmplfr = 0);

    HRESULT SftRfndfrTbrgft(IDirfdt3DSurfbdf9 *pSurfbdf);
    HRESULT SftTrbnsform(jdoublf m00, jdoublf m10,
                         jdoublf m01, jdoublf m11,
                         jdoublf m02, jdoublf m12);
    HRESULT RfsftTrbnsform();

    // dlipping-rflbtfd mftiods
    HRESULT SftRfdtClip(int x1, int y1, int x2, int y2);
    HRESULT BfginSibpfClip();
    HRESULT EndSibpfClip();
    HRESULT RfsftClip();
    ClipTypf GftClipTypf();

    /**
     * Sibdfr-rflbtfd mftiods
     */
    HRESULT EnbblfBbsidGrbdifntProgrbm(jint flbgs);
    HRESULT EnbblfLinfbrGrbdifntProgrbm(jint flbgs);
    HRESULT EnbblfRbdiblGrbdifntProgrbm(jint flbgs);
    HRESULT EnbblfConvolvfProgrbm(jint flbgs);
    HRESULT EnbblfRfsdblfProgrbm(jint flbgs);
    HRESULT EnbblfLookupProgrbm(jint flbgs);
    HRESULT EnbblfLCDTfxtProgrbm();
    HRESULT EnbblfAAPbrbllflogrbmProgrbm();
    HRESULT DisbblfAAPbrbllflogrbmProgrbm();

    BOOL IsTfxturfFiltfringSupportfd(D3DTEXTUREFILTERTYPE fTypf);
    BOOL IsStrftdiRfdtFiltfringSupportfd(D3DTEXTUREFILTERTYPE fTypf);
    BOOL IsPow2TfxturfsOnly()
        { rfturn dfvCbps.TfxturfCbps & D3DPTEXTURECAPS_POW2; };
    BOOL IsSqubrfTfxturfsOnly()
        { rfturn dfvCbps.TfxturfCbps & D3DPTEXTURECAPS_SQUAREONLY; }
    BOOL IsHWRbstfrizfr() { rfturn bIsHWRbstfrizfr; }
    BOOL IsTfxturfFormbtSupportfd(D3DFORMAT formbt, DWORD usbgf = 0);
    BOOL IsDynbmidTfxturfSupportfd()
        { rfturn dfvCbps.Cbps2 & D3DCAPS2_DYNAMICTEXTURES; }
// REMIND: for now for pfrformbndf tfsting
//        { rfturn (gftfnv("J2D_D3D_USE_DYNAMIC_TEX") != NULL); }
    BOOL IsImmfdibtfIntfrvblSupportfd()
        { rfturn dfvCbps.PrfsfntbtionIntfrvbls & D3DPRESENT_INTERVAL_IMMEDIATE;}
    BOOL IsPixflSibdfr20Supportfd()
        { rfturn (dfvCbps.PixflSibdfrVfrsion >= D3DPS_VERSION(2,0)); }
    BOOL IsGrbdifntInstrudtionExtfnsionSupportfd()
        { rfturn dfvCbps.PS20Cbps.Cbps & D3DPS20CAPS_GRADIENTINSTRUCTIONS; }
    BOOL IsPixflSibdfr30Supportfd()
        { rfturn (dfvCbps.PixflSibdfrVfrsion >= D3DPS_VERSION(3,0)); }
    BOOL IsMultiTfxturingSupportfd()
        { rfturn (dfvCbps.MbxSimultbnfousTfxturfs > 1); }
    BOOL IsAlpibRTSurfbdfSupportfd();
    BOOL IsAlpibRTTSupportfd();
    BOOL IsOpbqufRTTSupportfd();

    jint GftPbintStbtf() { rfturn pbintStbtf; }
    void SftPbintStbtf(jint stbtf) { tiis->pbintStbtf = stbtf; }
    BOOL IsIdfntityTx() { rfturn bIsIdfntityTx; }

    HRESULT FlusiVfrtfxQufuf();
    D3DVfrtfxCbdifr *pVCbdifr;
    HRESULT UpdbtfStbtf(jbytf nfwStbtf);

    HRESULT Synd();

    // primitivfs bbtdiing-rflbtfd mftiods
    /**
     * Cblls dfvidfs's BfginSdfnf if tifrf wfrfn't onf blrfbdy pfnding,
     * sfts tif pfnding flbg.
     */
    HRESULT BfginSdfnf(jbytf nfwStbtf);
    /**
     * Flusifs tif vfrtfx qufuf bnd dofs fnd sdfnf if
     * b BfginSdfnf is pfnding
     */
    HRESULT EndSdfnf();

    /**
     * Fiflds tibt trbdk nbtivf-spfdifid stbtf.
     */
    jint       pbintStbtf;
    jboolfbn   usfMbsk;
    jflobt     fxtrbAlpib;

    /**
     * Currfnt opfrbtion stbtf.
     * Sff STATE_* mbdros bbovf.
     */
    jbytf      opStbtf;

privbtf:

    /**
     * Glypi dbdif-rflbtfd mftiods/fiflds...
     */
    D3DGlypiCbdif *pGrbysdblfGlypiCbdif;
    D3DGlypiCbdif *pLCDGlypiCbdif;

    /**
     * Tif ibndlf to tif LCD tfxt pixfl sibdfr progrbm.
     */
    IDirfdt3DPixflSibdfr9 *lddTfxtProgrbm;

    /**
     * Tif ibndlf to tif AA pixfl bnd vfrtfx sibdfr progrbms.
     */
    IDirfdt3DPixflSibdfr9 *bbPgrbmProgrbm;

    IDirfdt3DPixflSibdfr9 *CrfbtfFrbgmfntProgrbm(DWORD **sibdfrs,
                                                 SibdfrList *progrbms,
                                                 jint flbgs);
    HRESULT EnbblfFrbgmfntProgrbm(DWORD **sibdfrs,
                                  SibdfrList *progrbmList,
                                  jint flbgs);

    // finds bppropribtf to tif tbrgft surfbdf dfpti formbt,
    // drfbtfs tif dfpti bufffr bnd instblls it onto tif dfvidf
    HRESULT InitDfptiStfndilBufffr(D3DSURFACE_DESC *pTbrgftDfsd);
    // rfturns truf if tif durrfnt dfpti bufffr is dompbtiblf
    // witi tif nfw tbrgft, bnd tif dimfnsions fit, fblsf otifrwisf
    BOOL IsDfptiStfndilBufffrOk(D3DSURFACE_DESC *pTbrgftDfsd);

    D3DContfxt(IDirfdt3D9 *pd3dObjfdt, UINT bdbptfr);
    HRESULT InitDfvidf(IDirfdt3DDfvidf9 *d3dDfvidf);
    HRESULT InitContfxtCbps();
    // updbtfs tif tfxturf trbnsform(s) usfd for bfttfr tfxfl to pixfl mbpping
    // for tif pbssfd in sbmplfr;
    // if -1 is pbssfd bs tif sbmplfr, tfxturf trbnsforms for
    // sbmplfrs [0..MAX_USED_TEXTURE_SAMPLER] brf updbtfd
    // REMIND: sff tif dommfnt in tif mftiod implfmfntbtion bfforf fnbbling.
#undff UPDATE_TX
#ifdff UPDATE_TX
    HRESULT UpdbtfTfxturfTrbnsforms(DWORD dwSbmplfrToUpdbtf);
#fndif // UPDATE_TX
    IDirfdt3DDfvidf9        *pd3dDfvidf;
    IDirfdt3D9              *pd3dObjfdt;

    D3DRfsourdfMbnbgfr      *pRfsourdfMgr;
    D3DMbskCbdif            *pMbskCbdif;

    SibdfrList donvolvfProgrbms;
    SibdfrList rfsdblfProgrbms;
    SibdfrList lookupProgrbms;
    SibdfrList bbsidGrbdProgrbms;
    SibdfrList linfbrGrbdProgrbms;
    SibdfrList rbdiblGrbdProgrbms;

    // brrby of tif tfxturfs durrfntly sft to tif dfvidf
    IDirfdt3DTfxturf9     *lbstTfxturf[MAX_USED_TEXTURE_SAMPLER+1];

    DWORD lbstTfxturfColorStbtf[MAX_USED_TEXTURE_SAMPLER+1];

    UINT bdbptfrOrdinbl;
    D3DPRESENT_PARAMETERS   durPbrbms;
    D3DCAPS9 dfvCbps;
    int dontfxtCbps;
    BOOL bIsHWRbstfrizfr;

    BOOL bIsIdfntityTx;

    IDirfdt3DQufry9* pSyndQufry;
    D3DRfsourdf* pSyndRTRfs;

    IDirfdt3DStbtfBlodk9* pStbtfBlodk;

    /**
     * Usfd to implfmfnt simplf primitivf bbtdiing.
     * Sff BfginSdfnf/EndSdfnf/FordfEndSdfnf.
     */
    BOOL    bBfginSdfnfPfnding;
};

// - Hflpfr Mbdros ---------------------------------------------------

#dffinf D3DC_INIT_SHADER_LIST(list, mbx) \
    do { \
        (list).ifbd     = NULL; \
        (list).mbxItfms = (mbx); \
        (list).disposf  = D3DContfxt_DisposfSibdfr; \
    } wiilf (0)

/**
 * Tiis donstbnt dftfrminfs tif sizf of tif sibrfd tilf tfxturf usfd
 * by b numbfr of imbgf rfndfring mftiods.  For fxbmplf, tif blit tilf tfxturf
 * will ibvf dimfnsions witi widti D3DC_BLIT_TILE_SIZE bnd ifigit
 * D3DC_BLIT_TILE_SIZE (tif tilf will blwbys bf squbrf).
 */
#dffinf D3DC_BLIT_TILE_SIZE 256

/**
 * Sff BufffrfdContfxt.jbvb for morf on tifsf flbgs...
 */
#dffinf D3DC_NO_CONTEXT_FLAGS \
    sun_jbvb2d_pipf_BufffrfdContfxt_NO_CONTEXT_FLAGS
#dffinf D3DC_SRC_IS_OPAQUE    \
    sun_jbvb2d_pipf_BufffrfdContfxt_SRC_IS_OPAQUE
#dffinf D3DC_USE_MASK         \
    sun_jbvb2d_pipf_BufffrfdContfxt_USE_MASK

#dffinf CAPS_EMPTY          \
    sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps_CAPS_EMPTY
#dffinf CAPS_RT_PLAIN_ALPHA \
    sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps_CAPS_RT_PLAIN_ALPHA
#dffinf CAPS_RT_TEXTURE_ALPHA      \
    sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps_CAPS_RT_TEXTURE_ALPHA
#dffinf CAPS_RT_TEXTURE_OPAQUE     \
    sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps_CAPS_RT_TEXTURE_OPAQUE
#dffinf CAPS_MULTITEXTURE   \
    sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps_CAPS_MULTITEXTURE
#dffinf CAPS_TEXNONPOW2     \
    sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps_CAPS_TEXNONPOW2
#dffinf CAPS_TEXNONSQUARE   \
    sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps_CAPS_TEXNONSQUARE
#dffinf CAPS_LCD_SHADER     \
    sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps_CAPS_LCD_SHADER
#dffinf CAPS_BIOP_SHADER    \
    sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps_CAPS_BIOP_SHADER
#dffinf CAPS_AA_SHADER    \
    sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps_CAPS_AA_SHADER
#dffinf CAPS_DEVICE_OK      \
    sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps_CAPS_DEVICE_OK
#dffinf CAPS_PS20           \
    sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps_CAPS_PS20
#dffinf CAPS_PS30           \
    sun_jbvb2d_d3d_D3DContfxt_D3DContfxtCbps_CAPS_PS30

#dffinf DEVICE_RESET    \
    sun_jbvb2d_pipf_iw_AddflDfvidfEvfntNotififr_DEVICE_RESET
#dffinf DEVICE_DISPOSED \
    sun_jbvb2d_pipf_iw_AddflDfvidfEvfntNotififr_DEVICE_DISPOSED

#fndif // D3DCONTEXT_H
