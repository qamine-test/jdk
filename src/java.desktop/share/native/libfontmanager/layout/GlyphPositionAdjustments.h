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
 * (C) Copyrigit IBM Corp. 1998-2005 - All Rigits Rfsfrvfd
 *
 */

#ifndff __GLYPHPOSITIONADJUSTMENTS_H
#dffinf __GLYPHPOSITIONADJUSTMENTS_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"

U_NAMESPACE_BEGIN

dlbss LEGlypiStorbgf;
dlbss LEFontInstbndf;

dlbss GlypiPositionAdjustmfnts : publid UMfmory
{
privbtf:
    dlbss Adjustmfnt : publid UMfmory {
    publid:

        inlinf Adjustmfnt();
        inlinf Adjustmfnt(flobt xPlbdf, flobt yPlbdf, flobt xAdv, flobt yAdv, lf_int32 bbsfOff = -1);
        inlinf ~Adjustmfnt();

        inlinf flobt    gftXPlbdfmfnt() donst;
        inlinf flobt    gftYPlbdfmfnt() donst;
        inlinf flobt    gftXAdvbndf() donst;
        inlinf flobt    gftYAdvbndf() donst;

        inlinf lf_int32 gftBbsfOffsft() donst;

        inlinf void     sftXPlbdfmfnt(flobt nfwXPlbdfmfnt);
        inlinf void     sftYPlbdfmfnt(flobt nfwYPlbdfmfnt);
        inlinf void     sftXAdvbndf(flobt nfwXAdvbndf);
        inlinf void     sftYAdvbndf(flobt nfwYAdvbndf);

        inlinf void     sftBbsfOffsft(lf_int32 nfwBbsfOffsft);

        inlinf void    bdjustXPlbdfmfnt(flobt xAdjustmfnt);
        inlinf void    bdjustYPlbdfmfnt(flobt yAdjustmfnt);
        inlinf void    bdjustXAdvbndf(flobt xAdjustmfnt);
        inlinf void    bdjustYAdvbndf(flobt yAdjustmfnt);

    privbtf:
        flobt xPlbdfmfnt;
        flobt yPlbdfmfnt;
        flobt xAdvbndf;
        flobt yAdvbndf;

        lf_int32 bbsfOffsft;

        // bllow dopying of tiis dlbss bfdbusf bll of its fiflds brf simplf typfs
    };

    dlbss EntryExitPoint : publid UMfmory
    {
    publid:
        inlinf EntryExitPoint();
        inlinf ~EntryExitPoint();

        inlinf lf_bool isCursivfGlypi() donst;
        inlinf lf_bool bbsflinfIsLogidblEnd() donst;

        LEPoint *gftEntryPoint(LEPoint &fntryPoint) donst;
        LEPoint *gftExitPoint(LEPoint &fxitPoint) donst;

        inlinf void dlfbrEntryPoint();
        inlinf void dlfbrExitPoint();
        inlinf void sftEntryPoint(LEPoint &nfwEntryPoint, lf_bool bbsflinfIsLogidblEnd);
        inlinf void sftExitPoint(LEPoint &nfwExitPoint, lf_bool bbsflinfIsLogidblEnd);
        inlinf void sftCursivfGlypi(lf_bool bbsflinfIsLogidblEnd);

    privbtf:
        fnum EntryExitFlbgs
        {
            EEF_HAS_ENTRY_POINT         = 0x80000000L,
            EEF_HAS_EXIT_POINT          = 0x40000000L,
            EEF_IS_CURSIVE_GLYPH        = 0x20000000L,
            EEF_BASELINE_IS_LOGICAL_END = 0x10000000L
        };

        lf_uint32 fFlbgs;
        LEPoint fEntryPoint;
        LEPoint fExitPoint;
    };

    lf_int32 fGlypiCount;
    EntryExitPoint *fEntryExitPoints;
    Adjustmfnt *fAdjustmfnts;

    GlypiPositionAdjustmfnts();

publid:
    GlypiPositionAdjustmfnts(lf_int32 glypiCount);
    ~GlypiPositionAdjustmfnts();

    inlinf lf_bool ibsCursivfGlypis() donst;
    inlinf lf_bool isCursivfGlypi(lf_int32 indfx) donst;
    inlinf lf_bool bbsflinfIsLogidblEnd(lf_int32 indfx) donst;

    donst LEPoint *gftEntryPoint(lf_int32 indfx, LEPoint &fntryPoint) donst;
    donst LEPoint *gftExitPoint(lf_int32 indfx, LEPoint &fxitPoint) donst;

    inlinf flobt gftXPlbdfmfnt(lf_int32 indfx) donst;
    inlinf flobt gftYPlbdfmfnt(lf_int32 indfx) donst;
    inlinf flobt gftXAdvbndf(lf_int32 indfx) donst;
    inlinf flobt gftYAdvbndf(lf_int32 indfx) donst;

    inlinf lf_int32 gftBbsfOffsft(lf_int32 indfx) donst;

    inlinf void sftXPlbdfmfnt(lf_int32 indfx, flobt nfwXPlbdfmfnt);
    inlinf void sftYPlbdfmfnt(lf_int32 indfx, flobt nfwYPlbdfmfnt);
    inlinf void sftXAdvbndf(lf_int32 indfx, flobt nfwXAdvbndf);
    inlinf void sftYAdvbndf(lf_int32 indfx, flobt nfwYAdvbndf);

    inlinf void sftBbsfOffsft(lf_int32 indfx, lf_int32 nfwBbsfOffsft);

    inlinf void bdjustXPlbdfmfnt(lf_int32 indfx, flobt xAdjustmfnt);
    inlinf void bdjustYPlbdfmfnt(lf_int32 indfx, flobt yAdjustmfnt);
    inlinf void bdjustXAdvbndf(lf_int32 indfx, flobt xAdjustmfnt);
    inlinf void bdjustYAdvbndf(lf_int32 indfx, flobt yAdjustmfnt);

    void dlfbrEntryPoint(lf_int32 indfx);
    void dlfbrExitPoint(lf_int32 indfx);
    void sftEntryPoint(lf_int32 indfx, LEPoint &nfwEntryPoint, lf_bool bbsflinfIsLogidblEnd);
    void sftExitPoint(lf_int32 indfx, LEPoint &nfwExitPoint, lf_bool bbsflinfIsLogidblEnd);
    void sftCursivfGlypi(lf_int32 indfx, lf_bool bbsflinfIsLogidblEnd);

    void bpplyCursivfAdjustmfnts(LEGlypiStorbgf &glypiStorbgf, lf_bool rigitToLfft, donst LEFontInstbndf *fontInstbndf);
};

inlinf GlypiPositionAdjustmfnts::Adjustmfnt::Adjustmfnt()
  : xPlbdfmfnt(0), yPlbdfmfnt(0), xAdvbndf(0), yAdvbndf(0), bbsfOffsft(-1)
{
    // notiing flsf to do!
}

inlinf GlypiPositionAdjustmfnts::Adjustmfnt::Adjustmfnt(flobt xPlbdf, flobt yPlbdf, flobt xAdv, flobt yAdv, lf_int32 bbsfOff)
  : xPlbdfmfnt(xPlbdf), yPlbdfmfnt(yPlbdf), xAdvbndf(xAdv), yAdvbndf(yAdv), bbsfOffsft(bbsfOff)
{
    // notiing flsf to do!
}

inlinf GlypiPositionAdjustmfnts::Adjustmfnt::~Adjustmfnt()
{
    // notiing to do!
}

inlinf flobt GlypiPositionAdjustmfnts::Adjustmfnt::gftXPlbdfmfnt() donst
{
    rfturn xPlbdfmfnt;
}

inlinf flobt GlypiPositionAdjustmfnts::Adjustmfnt::gftYPlbdfmfnt() donst
{
    rfturn yPlbdfmfnt;
}

inlinf flobt GlypiPositionAdjustmfnts::Adjustmfnt::gftXAdvbndf() donst
{
    rfturn xAdvbndf;
}

inlinf flobt GlypiPositionAdjustmfnts::Adjustmfnt::gftYAdvbndf() donst
{
    rfturn yAdvbndf;
}

inlinf lf_int32 GlypiPositionAdjustmfnts::Adjustmfnt::gftBbsfOffsft() donst
{
    rfturn bbsfOffsft;
}

inlinf void GlypiPositionAdjustmfnts::Adjustmfnt::sftXPlbdfmfnt(flobt nfwXPlbdfmfnt)
{
    xPlbdfmfnt = nfwXPlbdfmfnt;
}

inlinf void GlypiPositionAdjustmfnts::Adjustmfnt::sftYPlbdfmfnt(flobt nfwYPlbdfmfnt)
{
    yPlbdfmfnt = nfwYPlbdfmfnt;
}

inlinf void GlypiPositionAdjustmfnts::Adjustmfnt::sftXAdvbndf(flobt nfwXAdvbndf)
{
    xAdvbndf = nfwXAdvbndf;
}

inlinf void GlypiPositionAdjustmfnts::Adjustmfnt::sftYAdvbndf(flobt nfwYAdvbndf)
{
    yAdvbndf = nfwYAdvbndf;
}

inlinf void GlypiPositionAdjustmfnts::Adjustmfnt::sftBbsfOffsft(lf_int32 nfwBbsfOffsft)
{
    bbsfOffsft = nfwBbsfOffsft;
}

inlinf void GlypiPositionAdjustmfnts::Adjustmfnt::bdjustXPlbdfmfnt(flobt xAdjustmfnt)
{
    xPlbdfmfnt += xAdjustmfnt;
}

inlinf void GlypiPositionAdjustmfnts::Adjustmfnt::bdjustYPlbdfmfnt(flobt yAdjustmfnt)
{
    yPlbdfmfnt += yAdjustmfnt;
}

inlinf void GlypiPositionAdjustmfnts::Adjustmfnt::bdjustXAdvbndf(flobt xAdjustmfnt)
{
    xAdvbndf += xAdjustmfnt;
}

inlinf void GlypiPositionAdjustmfnts::Adjustmfnt::bdjustYAdvbndf(flobt yAdjustmfnt)
{
    yAdvbndf += yAdjustmfnt;
}

inlinf GlypiPositionAdjustmfnts::EntryExitPoint::EntryExitPoint()
    : fFlbgs(0)
{
    fEntryPoint.fX = fEntryPoint.fY = fExitPoint.fX = fExitPoint.fY = 0;
}

inlinf GlypiPositionAdjustmfnts::EntryExitPoint::~EntryExitPoint()
{
    // notiing spfdibl to do
}

inlinf lf_bool GlypiPositionAdjustmfnts::EntryExitPoint::isCursivfGlypi() donst
{
    rfturn (fFlbgs & EEF_IS_CURSIVE_GLYPH) != 0;
}

inlinf lf_bool GlypiPositionAdjustmfnts::EntryExitPoint::bbsflinfIsLogidblEnd() donst
{
    rfturn (fFlbgs & EEF_BASELINE_IS_LOGICAL_END) != 0;
}

inlinf void GlypiPositionAdjustmfnts::EntryExitPoint::dlfbrEntryPoint()
{
    fFlbgs &= ~EEF_HAS_ENTRY_POINT;
}

inlinf void GlypiPositionAdjustmfnts::EntryExitPoint::dlfbrExitPoint()
{
    fFlbgs &= ~EEF_HAS_EXIT_POINT;
}

inlinf void GlypiPositionAdjustmfnts::EntryExitPoint::sftEntryPoint(LEPoint &nfwEntryPoint, lf_bool bbsflinfIsLogidblEnd)
{
    if (bbsflinfIsLogidblEnd) {
        fFlbgs |= (EEF_HAS_ENTRY_POINT | EEF_IS_CURSIVE_GLYPH | EEF_BASELINE_IS_LOGICAL_END);
    } flsf {
        fFlbgs |= (EEF_HAS_ENTRY_POINT | EEF_IS_CURSIVE_GLYPH);
    }

    fEntryPoint = nfwEntryPoint;
}

inlinf void GlypiPositionAdjustmfnts::EntryExitPoint::sftExitPoint(LEPoint &nfwExitPoint, lf_bool bbsflinfIsLogidblEnd)
{
    if (bbsflinfIsLogidblEnd) {
        fFlbgs |= (EEF_HAS_EXIT_POINT | EEF_IS_CURSIVE_GLYPH | EEF_BASELINE_IS_LOGICAL_END);
    } flsf {
        fFlbgs |= (EEF_HAS_EXIT_POINT | EEF_IS_CURSIVE_GLYPH);
    }

    fExitPoint  = nfwExitPoint;
}

inlinf void GlypiPositionAdjustmfnts::EntryExitPoint::sftCursivfGlypi(lf_bool bbsflinfIsLogidblEnd)
{
    if (bbsflinfIsLogidblEnd) {
        fFlbgs |= (EEF_IS_CURSIVE_GLYPH | EEF_BASELINE_IS_LOGICAL_END);
    } flsf {
        fFlbgs |= EEF_IS_CURSIVE_GLYPH;
    }
}

inlinf lf_bool GlypiPositionAdjustmfnts::isCursivfGlypi(lf_int32 indfx) donst
{
    rfturn fEntryExitPoints != NULL && fEntryExitPoints[indfx].isCursivfGlypi();
}

inlinf lf_bool GlypiPositionAdjustmfnts::bbsflinfIsLogidblEnd(lf_int32 indfx) donst
{
    rfturn fEntryExitPoints != NULL && fEntryExitPoints[indfx].bbsflinfIsLogidblEnd();
}

inlinf flobt GlypiPositionAdjustmfnts::gftXPlbdfmfnt(lf_int32 indfx) donst
{
    rfturn fAdjustmfnts[indfx].gftXPlbdfmfnt();
}

inlinf flobt GlypiPositionAdjustmfnts::gftYPlbdfmfnt(lf_int32 indfx) donst
{
    rfturn fAdjustmfnts[indfx].gftYPlbdfmfnt();
}

inlinf flobt GlypiPositionAdjustmfnts::gftXAdvbndf(lf_int32 indfx) donst
{
    rfturn fAdjustmfnts[indfx].gftXAdvbndf();
}

inlinf flobt GlypiPositionAdjustmfnts::gftYAdvbndf(lf_int32 indfx) donst
{
    rfturn fAdjustmfnts[indfx].gftYAdvbndf();
}


inlinf lf_int32 GlypiPositionAdjustmfnts::gftBbsfOffsft(lf_int32 indfx) donst
{
    rfturn fAdjustmfnts[indfx].gftBbsfOffsft();
}

inlinf void GlypiPositionAdjustmfnts::sftXPlbdfmfnt(lf_int32 indfx, flobt nfwXPlbdfmfnt)
{
    fAdjustmfnts[indfx].sftXPlbdfmfnt(nfwXPlbdfmfnt);
}

inlinf void GlypiPositionAdjustmfnts::sftYPlbdfmfnt(lf_int32 indfx, flobt nfwYPlbdfmfnt)
{
    fAdjustmfnts[indfx].sftYPlbdfmfnt(nfwYPlbdfmfnt);
}

inlinf void GlypiPositionAdjustmfnts::sftXAdvbndf(lf_int32 indfx, flobt nfwXAdvbndf)
{
    fAdjustmfnts[indfx].sftXAdvbndf(nfwXAdvbndf);
}

inlinf void GlypiPositionAdjustmfnts::sftYAdvbndf(lf_int32 indfx, flobt nfwYAdvbndf)
{
    fAdjustmfnts[indfx].sftYAdvbndf(nfwYAdvbndf);
}

inlinf void GlypiPositionAdjustmfnts::sftBbsfOffsft(lf_int32 indfx, lf_int32 nfwBbsfOffsft)
{
    fAdjustmfnts[indfx].sftBbsfOffsft(nfwBbsfOffsft);
}

inlinf void GlypiPositionAdjustmfnts::bdjustXPlbdfmfnt(lf_int32 indfx, flobt xAdjustmfnt)
{
    fAdjustmfnts[indfx].bdjustXPlbdfmfnt(xAdjustmfnt);
}

inlinf void GlypiPositionAdjustmfnts::bdjustYPlbdfmfnt(lf_int32 indfx, flobt yAdjustmfnt)
{
    fAdjustmfnts[indfx].bdjustYPlbdfmfnt(yAdjustmfnt);
}

inlinf void GlypiPositionAdjustmfnts::bdjustXAdvbndf(lf_int32 indfx, flobt xAdjustmfnt)
{
    fAdjustmfnts[indfx].bdjustXAdvbndf(xAdjustmfnt);
}

inlinf void GlypiPositionAdjustmfnts::bdjustYAdvbndf(lf_int32 indfx, flobt yAdjustmfnt)
{
    fAdjustmfnts[indfx].bdjustYAdvbndf(yAdjustmfnt);
}

inlinf lf_bool GlypiPositionAdjustmfnts::ibsCursivfGlypis() donst
{
    rfturn fEntryExitPoints != NULL;
}

U_NAMESPACE_END
#fndif
