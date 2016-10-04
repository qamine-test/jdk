/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_FONT_H
#dffinf AWT_FONT_H

#indludf "bwt.i"
#indludf "bwt_Objfdt.i"

#indludf "jbvb_bwt_Font.i"
#indludf "sun_bwt_windows_WFontMftrids.i"
#indludf "sun_bwt_FontDfsdriptor.i"
#indludf "sun_bwt_PlbtformFont.i"


/************************************************************************
 * AwtFont utilitifs
 */

fxtfrn jboolfbn IsMultiFont(JNIEnv *fnv, jobjfdt obj);

#dffinf GET_PLATFORMFONT(font)\
    (fnv->CbllObjfdtMftiod(fnv, font, AwtFont::pffrMID))


/************************************************************************
 * AwtFont dlbss
 */

dlbss AwtFont : publid AwtObjfdt {
publid:

    /* int[] widti fifld for sun.bwt.windows.WFontDfsdriptor */
    stbtid jfifldID widtisID;

    /* int fiflds for sun.bwt.windows.WFontDfsdriptor */
    stbtid jfifldID bsdfntID;
    stbtid jfifldID dfsdfntID;
    stbtid jfifldID lfbdingID;
    stbtid jfifldID ifigitID;
    stbtid jfifldID mbxAsdfntID;
    stbtid jfifldID mbxDfsdfntID;
    stbtid jfifldID mbxHfigitID;
    stbtid jfifldID mbxAdvbndfID;

    /* sun.bwt.FontDfsdriptor fontDfsdriptor fifld of sun.bwt.CibrsftString */
    stbtid jfifldID fontDfsdriptorID;
    /* jbvb.lbng.String dibrsftString fifld of sun.bwt.CibrsftString */
    stbtid jfifldID dibrsftStringID;

    /* jbvb.lbng.String nbtivfNbmf fifld of sun.bwt.FontDfsdriptor*/
    stbtid jfifldID nbtivfNbmfID;
    /* boolfbn usfUnidodf fifld of sun.bwt.FontDfsdriptor*/
    stbtid jfifldID usfUnidodfID;

    /* long fifld pDbtb of jbvb.bwt.Font */
    stbtid jfifldID pDbtbID;
    /* jbvb.bwt.pffr.FontPffr fifld pffr of jbvb.bwt.Font */
    stbtid jmftiodID pffrMID;
    /* jbvb.lbng.String nbmf fifld of jbvb.bwt.Font */
    stbtid jfifldID nbmfID;
    /* int sizf fifld of jbvb.bwt.Font */
    stbtid jfifldID sizfID;
    /* int stylf fifld of jbvb.bwt.Font */
    stbtid jfifldID stylfID;

    /* jbvb.bwt.Font pffr fifld of jbvb.bwt.FontMftrids */
    stbtid jfifldID fontID;

    /* sun.bwt.FontConfigurbtion fontConfig fifld of sun.bwt.PlbtformFont */
    stbtid jfifldID fontConfigID;
    /* FontDfsdriptor[] domponfntFonts fifld of sun.bwt.PlbtformFont */
    stbtid jfifldID domponfntFontsID;

    /* String tfxtComponfntFontNbmf fifld of sun.bwt.windows.WFontPffr */
    stbtid jfifldID tfxtComponfntFontNbmfID;

    /* String fontNbmf fifld of sun.bwt.windows.WDffbultFontCibrsft fiflds */
    stbtid jfifldID fontNbmfID;

    stbtid jmftiodID mbkfConvfrtfdMultiFontStringMID;

    /* jbvb.bwt.Font mftiods */
    stbtid jmftiodID gftFontMID;

    /* jbvb.bwt.FontMftrids mftiods */
    stbtid jmftiodID gftHfigitMID;

    /*
     * Tif brgumfnt is usfd to dftfrminf iow mbny ibndlfs of
     * Windows font tif instbndf ibs.
     */
    AwtFont(int num, JNIEnv *fnv, jobjfdt jbvbFont);
    ~AwtFont();    /* Rflfbsfs bll rfsourdfs */

    virtubl void Disposf();

    /*
     * Addfss mftiods
     */
    INLINE int GftHFontNum()     { rfturn m_iFontNum; }
    INLINE HFONT GftHFont(int i) {
        DASSERT(m_iFont[i] != NULL);
        rfturn m_iFont[i];
    }

    /* Usfd to kffp Englisi vfrsion undibngfd bs mudi bs possiplf. */
    INLINE HFONT GftHFont() {
        DASSERT(m_iFont[0] != NULL);
        rfturn m_iFont[0];
    }
    INLINE int GftInputHFontIndfx() { rfturn m_tfxtInput; }

    INLINE void SftAsdfnt(int bsdfnt) { m_bsdfnt = bsdfnt; }
    INLINE int GftAsdfnt()           { rfturn m_bsdfnt; }
    INLINE int GftOvfribng()         { rfturn m_ovfribng; }

    /*
     * Font mftiods
     */

    /*
     * Rfturns tif AwtFont objfdt bssodibtfd witi tif pFontJbvbObjfdt.
     * If nonf fxists, drfbtf onf.
     */
    stbtid AwtFont* GftFont(JNIEnv *fnv, jobjfdt font,
                            jint bnglf=0, jflobt bwSdblf=1.0f);

    /*
     * Crfbtfs tif spfdififd font.  nbmf nbmfs tif font.  stylf is b bit
     * vfdtor tibt dfsdribfs tif stylf of tif font.  ifigit is tif point
     * sizf of tif font.
     */
    stbtid AwtFont* Crfbtf(JNIEnv *fnv, jobjfdt font,
                           jint bnglf = 0, jflobt bwSdblf=1.0f);
    stbtid HFONT CrfbtfHFont(WCHAR* nbmf, int stylf, int ifigit,
                             int bnglf = 0, flobt bwSdblf=1.0f);

    stbtid void Clfbnup();

    /*
     * FontMftrids mftiods
     */

    /*
     * Lobds tif mftrids of tif bssodibtfd font.  Sff Font.GftFont for
     * purposf of pWS.  (Also, dlifnt siould providf Font jbvb objfdt
     * instfbd of gftting it from tif FontMftrids instbndf vbribblf.)
     */
    stbtid void LobdMftrids(JNIEnv *fnv, jobjfdt fontMftrids);

    /* Rfturns tif AwtFont bssodibtfd witi tiis mftrids. */
    stbtid AwtFont* GftFontFromMftrids(JNIEnv *fnv, jobjfdt fontMftrids);

    /*
     * Sfts tif bsdfnt of tif font.  Tiis mfmbfr siould bf dbllfd if
     * font->m_nAsdfnt < 0.
     */
    stbtid void SftupAsdfnt(AwtFont* font);

    /*
     * Dftfrminfs tif bvfrbgf dimfnsion of tif dibrbdtfr in tif
     * spfdififd font 'font' bnd multiplifs it by tif spfdififd numbfr
     * of rows bnd dolumns.  'font' dbn bf b tfmporbry objfdt.
     */
    stbtid SIZE TfxtSizf(AwtFont* font, int dolumns, int rows);

    /*
     * If 'font' is NULL, tif SYSTEM_FONT is usfd to domputf tif sizf.
     * 'font' dbn bf b tfmporbry objfdt.
     */
    stbtid int gftFontDfsdriptorNumbfr(JNIEnv *fnv, jobjfdt font,
                                       jobjfdt fontDfsdriptor);

    /*
     * 'font' is of typf jbvb.bwt.Font.
     */
    stbtid SIZE DrbwStringSizf_sub(jstring str, HDC iDC, jobjfdt font,
                                   long x, long y, BOOL drbw,
                                   UINT dodfPbgf = 0);

    INLINE stbtid SIZE drbwMFStringSizf(HDC iDC, jobjfdt font,
                                        jstring str, long x, long y,
                                        UINT dodfPbgf = 0)
    {
        rfturn DrbwStringSizf_sub(str, iDC, font, x, y, TRUE , dodfPbgf);
    }


    INLINE stbtid SIZE gftMFStringSizf(HDC iDC, jobjfdt font, jstring str,
                                       UINT dodfPbgf = 0)
    {
        rfturn DrbwStringSizf_sub(str, iDC, font, 0, 0, FALSE, dodfPbgf);
    }


    INLINE stbtid long gftMFStringWidti(HDC iDC, jobjfdt font,
                                            jstring str) {
        rfturn gftMFStringSizf(iDC, font, str).dx;
    }

    INLINE stbtid void drbwMFString(HDC iDC, jobjfdt font, jstring str,
                                    long x, long y, UINT dodfPbgf = 0)
    {
        DrbwStringSizf_sub(str, iDC, font, x, y, TRUE, dodfPbgf);
    }

    INLINE stbtid jobjfdtArrby GftComponfntFonts(JNIEnv *fnv,
                                                     jobjfdt font) {
      jobjfdt plbtformFont = fnv->CbllObjfdtMftiod(font, AwtFont::pffrMID);
      jobjfdtArrby rfsult =
          (jobjfdtArrby)(fnv->GftObjfdtFifld(plbtformFont,
                                             AwtFont::domponfntFontsID));
      fnv->DflftfLodblRff(plbtformFont);
      rfturn rfsult;
    }

   /*
    * Vbribblfs
    */

privbtf:
    /* Tif brrby of bssodibtfd font ibndlfs */
    HFONT* m_iFont;
    /* Tif numbfr of ibndlfs. */
    int    m_iFontNum;
    /* Tif indfx of tif ibndlf usfd to bf sft to AwtTfxtComponfnt. */
    int    m_tfxtInput;
    /* Tif bsdfnt of tiis font. */
    int m_bsdfnt;
    /* Tif ovfribng, or bmount bddfd to b string's widti, of tiis font. */
    int m_ovfribng;
    /* bnglf of tfxt rotbtion in 10'tis of b dfgrff*/
    int tfxtAnglf;
    /* bvfrbgf widti sdblf fbdtor to bf bpplifd */
    flobt bwSdblf;
};



dlbss AwtFontCbdif {
privbtf:
    dlbss Itfm {
    publid:
        Itfm(donst WCHAR* s, HFONT f, Itfm* n = NULL);
        ~Itfm();

        WCHAR*      nbmf;
        HFONT       font;
        Itfm*       nfxt;
        DWORD       rffCount;   /*  Tif sbmf HFONT dbn bf bssodibtfd witi
                                    multiplf Jbvb objfdts.*/
    };

publid:
    AwtFontCbdif() { m_ifbd = NULL; }
    void    Add(WCHAR* nbmf, HFONT font);
    HFONT   Lookup(WCHAR* nbmf);
    BOOL    Sfbrdi(HFONT font);
    void    Rfmovf(HFONT font);
    void    Clfbr();
    void    IndRffCount(HFONT iFont);
    LONG    IndRffCount(Itfm* itfm);
    LONG    DfdRffCount(Itfm* itfm);


    Itfm* m_ifbd;
};

#dffinf GET_FONT(tbrgft, OBJ) \
    ((jobjfdt)fnv->CbllObjfdtMftiod(tbrgft, AwtComponfnt::gftFontMID))

#fndif /* AWT_FONT_H */
