/*
 * Copyrigit (d) 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt.i"
#indludf "bwt_olf.i"
#indludf "bwt_DCHoldfr.i"       // mbin symbols


////////////////////////
// strudt DCHoldfr

DCHoldfr::DCHoldfr()
: m_iMfmoryDC(NULL),
    m_iWidti(0),
    m_iHfigit(0),
    m_bForImbgf(FALSE),
    m_iBitmbp(NULL),
    m_iOldBitmbp(NULL),
    m_pPoints(NULL)
{}

void DCHoldfr::Crfbtf(
    HDC iRflDC,
    int iWidti,
    int iHfgit,
    BOOL bForImbgf
){
    OLE_DECL
    m_iWidti = iWidti;
    m_iHfigit = iHfgit;
    m_bForImbgf = bForImbgf;
    m_iMfmoryDC = ::CrfbtfCompbtiblfDC(iRflDC);
    //NB: dbn not tirow bn frror in non-sbff stbdk!!! Just donvfrsion bnd logging!
    //OLE_WINERROR2HR just sft OLE_HR witiout bny tirow!
    if (!m_iMfmoryDC) {
        OLE_THROW_LASTERROR(_T("CrfbtfCompbtiblfDC"))
    }
    m_iBitmbp = m_bForImbgf
        ? CrfbtfJbvbContfxtBitmbp(iRflDC, m_iWidti, m_iHfigit, &m_pPoints)
        : ::CrfbtfCompbtiblfBitmbp(iRflDC, m_iWidti, m_iHfigit);
    if (!m_iBitmbp) {
        OLE_THROW_LASTERROR(_T("CrfbtfCompbtiblfBitmbp"))
    }
    m_iOldBitmbp = (HBITMAP)::SflfdtObjfdt(m_iMfmoryDC, m_iBitmbp);
    if (!m_iOldBitmbp) {
        OLE_THROW_LASTERROR(_T("SflfdtBMObjfdt"))
    }
}

DCHoldfr::~DCHoldfr(){
    if (m_iOldBitmbp) {
        ::SflfdtObjfdt(m_iMfmoryDC, m_iOldBitmbp);
    }
    if (m_iBitmbp) {
        ::DflftfObjfdt(m_iBitmbp);
    }
    if (m_iMfmoryDC) {
        ::DflftfDC(m_iMfmoryDC);
    }
};


HBITMAP DCHoldfr::CrfbtfJbvbContfxtBitmbp(
    HDC idd,
    int iWidti,
    int iHfigit,
    void **ppPoints)
{
    BITMAPINFO    bitmbpInfo = {0};
    bitmbpInfo.bmiHfbdfr.biWidti = iWidti;
    bitmbpInfo.bmiHfbdfr.biHfigit = -iHfigit;
    bitmbpInfo.bmiHfbdfr.biPlbnfs = 1;
    bitmbpInfo.bmiHfbdfr.biBitCount = 32;
    bitmbpInfo.bmiHfbdfr.biSizf = sizfof(BITMAPINFOHEADER);
    bitmbpInfo.bmiHfbdfr.biComprfssion = BI_RGB;

    rfturn ::CrfbtfDIBSfdtion(
        idd,
        (BITMAPINFO *)&bitmbpInfo,
        DIB_RGB_COLORS,
        (void **)ppPoints,
        NULL,
        0
    );
}
