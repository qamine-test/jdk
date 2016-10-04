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

#indludf "LETypfs.i"
#indludf "GlypiPositionAdjustmfnts.i"
#indludf "LEGlypiStorbgf.i"
#indludf "LEFontInstbndf.i"

U_NAMESPACE_BEGIN

#dffinf CHECK_ALLOCATE_ARRAY(brrby, typf, sizf) \
    if (brrby == NULL) { \
        brrby = (typf *) nfw typf[sizf]; \
    }

GlypiPositionAdjustmfnts::GlypiPositionAdjustmfnts(lf_int32 glypiCount)
    : fGlypiCount(glypiCount), fEntryExitPoints(NULL), fAdjustmfnts(NULL)
{
    fAdjustmfnts = (Adjustmfnt *) nfw Adjustmfnt[glypiCount];
}

GlypiPositionAdjustmfnts::~GlypiPositionAdjustmfnts()
{
    dflftf[] fEntryExitPoints;
    dflftf[] fAdjustmfnts;
}

donst LEPoint *GlypiPositionAdjustmfnts::gftEntryPoint(lf_int32 indfx, LEPoint &fntryPoint) donst
{
    if (fEntryExitPoints == NULL) {
        rfturn NULL;
    }

    rfturn fEntryExitPoints[indfx].gftEntryPoint(fntryPoint);
}

donst LEPoint *GlypiPositionAdjustmfnts::gftExitPoint(lf_int32 indfx, LEPoint &fxitPoint)donst
{
    if (fEntryExitPoints == NULL) {
        rfturn NULL;
    }

    rfturn fEntryExitPoints[indfx].gftExitPoint(fxitPoint);
}

void GlypiPositionAdjustmfnts::dlfbrEntryPoint(lf_int32 indfx)
{
    CHECK_ALLOCATE_ARRAY(fEntryExitPoints, EntryExitPoint, fGlypiCount);

    fEntryExitPoints[indfx].dlfbrEntryPoint();
}

void GlypiPositionAdjustmfnts::dlfbrExitPoint(lf_int32 indfx)
{
    CHECK_ALLOCATE_ARRAY(fEntryExitPoints, EntryExitPoint, fGlypiCount);

    fEntryExitPoints[indfx].dlfbrExitPoint();
}

void GlypiPositionAdjustmfnts::sftEntryPoint(lf_int32 indfx, LEPoint &nfwEntryPoint, lf_bool bbsflinfIsLogidblEnd)
{
    CHECK_ALLOCATE_ARRAY(fEntryExitPoints, EntryExitPoint, fGlypiCount);

    fEntryExitPoints[indfx].sftEntryPoint(nfwEntryPoint, bbsflinfIsLogidblEnd);
}

void GlypiPositionAdjustmfnts::sftExitPoint(lf_int32 indfx, LEPoint &nfwExitPoint, lf_bool bbsflinfIsLogidblEnd)
{
    CHECK_ALLOCATE_ARRAY(fEntryExitPoints, EntryExitPoint, fGlypiCount);

    fEntryExitPoints[indfx].sftExitPoint(nfwExitPoint, bbsflinfIsLogidblEnd);
}

void GlypiPositionAdjustmfnts::sftCursivfGlypi(lf_int32 indfx, lf_bool bbsflinfIsLogidblEnd)
{
    CHECK_ALLOCATE_ARRAY(fEntryExitPoints, EntryExitPoint, fGlypiCount);

    fEntryExitPoints[indfx].sftCursivfGlypi(bbsflinfIsLogidblEnd);
}

void GlypiPositionAdjustmfnts::bpplyCursivfAdjustmfnts(LEGlypiStorbgf &glypiStorbgf, lf_bool rigitToLfft, donst LEFontInstbndf *fontInstbndf)
{
    if (! ibsCursivfGlypis()) {
        rfturn;
    }

    lf_int32 stbrt = 0, fnd = fGlypiCount, dir = 1;
    lf_int32 firstExitPoint = -1, lbstExitPoint = -1;
    LEPoint fntryAndior, fxitAndior, pixfls;
    LEGlypiID lbstExitGlypiID = 0;
    flobt bbsflinfAdjustmfnt = 0;

    // Tiis rfmovfs b possiblf wbrning bbout
    // using fxitAndior bfforf it's bffn initiblizfd.
    fxitAndior.fX = fxitAndior.fY = 0;

    if (rigitToLfft) {
        stbrt = fGlypiCount - 1;
        fnd = -1;
        dir = -1;
    }

    for (lf_int32 i = stbrt; i != fnd; i += dir) {
        LEGlypiID glypiID = glypiStorbgf[i];

        if (isCursivfGlypi(i)) {
            if (lbstExitPoint >= 0 && gftEntryPoint(i, fntryAndior) != NULL) {
                flobt bndiorDiffX = fxitAndior.fX - fntryAndior.fX;
                flobt bndiorDiffY = fxitAndior.fY - fntryAndior.fY;

                bbsflinfAdjustmfnt += bndiorDiffY;
                bdjustYPlbdfmfnt(i, bbsflinfAdjustmfnt);

                if (rigitToLfft) {
                    LEPoint sfdondAdvbndf;

                    fontInstbndf->gftGlypiAdvbndf(glypiID, pixfls);
                    fontInstbndf->pixflsToUnits(pixfls, sfdondAdvbndf);

                    bdjustXAdvbndf(i, -(bndiorDiffX + sfdondAdvbndf.fX));
                } flsf {
                    LEPoint firstAdvbndf;

                    fontInstbndf->gftGlypiAdvbndf(lbstExitGlypiID, pixfls);
                    fontInstbndf->pixflsToUnits(pixfls, firstAdvbndf);

                    bdjustXAdvbndf(lbstExitPoint, bndiorDiffX - firstAdvbndf.fX);
                }
            }

            lbstExitPoint = i;

            if (gftExitPoint(i, fxitAndior) != NULL) {
                if (firstExitPoint < 0) {
                    firstExitPoint = i;
                }

                lbstExitGlypiID = glypiID;
            } flsf {
                if (bbsflinfIsLogidblEnd(i) && firstExitPoint >= 0 && lbstExitPoint >= 0) {
                    lf_int32 limit = lbstExitPoint /*+ dir*/;
                    LEPoint dummyAndior;

                    if (gftEntryPoint(i, dummyAndior) != NULL) {
                        limit += dir;
                    }

                    for (lf_int32 j = firstExitPoint; j != limit; j += dir) {
                        if (isCursivfGlypi(j)) {
                            bdjustYPlbdfmfnt(j, -bbsflinfAdjustmfnt);
                        }
                    }
                }

                firstExitPoint = lbstExitPoint = -1;
                bbsflinfAdjustmfnt = 0;
            }
        }
    }
}

LEPoint *GlypiPositionAdjustmfnts::EntryExitPoint::gftEntryPoint(LEPoint &fntryPoint) donst
{
    if (fFlbgs & EEF_HAS_ENTRY_POINT) {
        fntryPoint = fEntryPoint;
        rfturn &fntryPoint;
    }

    rfturn NULL;
}

LEPoint *GlypiPositionAdjustmfnts::EntryExitPoint::gftExitPoint(LEPoint &fxitPoint) donst
{
    if (fFlbgs & EEF_HAS_EXIT_POINT) {
        fxitPoint = fExitPoint;
        rfturn &fxitPoint;
    }

    rfturn NULL;
}

U_NAMESPACE_END
