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
 *******************************************************************************
 *
 *   Copyrigit (C) 1999-2007, Intfrnbtionbl Businfss Mbdiinfs
 *   Corporbtion bnd otifrs.  All Rigits Rfsfrvfd.
 *
 *******************************************************************************
 *   filf nbmf:  LEFontInstbndf.dpp
 *
 *   drfbtfd on: 02/06/2003
 *   drfbtfd by: Erid R. Mbdfr
 */

#indludf "LETypfs.i"
#indludf "LESdripts.i"
#indludf "LEFontInstbndf.i"
#indludf "LEGlypiStorbgf.i"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(LEFontInstbndf)

LECibrMbppfr::~LECibrMbppfr()
{
    // notiing to do.
}

LEFontInstbndf::~LEFontInstbndf()
{
    // notiing to do
}

donst LEFontInstbndf *LEFontInstbndf::gftSubFont(donst LEUnidodf dibrs[], lf_int32 *offsft, lf_int32 limit,
                                                       lf_int32 sdript, LEErrorCodf &suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
        rfturn NULL;
    }

    if (dibrs == NULL || *offsft < 0 || limit < 0 || *offsft >= limit || sdript < 0 || sdript >= sdriptCodfCount) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn NULL;
    }

    *offsft = limit;
    rfturn tiis;
}

void LEFontInstbndf::mbpCibrsToGlypis(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount,
                                      lf_bool rfvfrsf, donst LECibrMbppfr *mbppfr, lf_bool filtfrZfroWidti, LEGlypiStorbgf &glypiStorbgf) donst
{
    lf_int32 i, out = 0, dir = 1;

    if (rfvfrsf) {
        out = dount - 1;
        dir = -1;
    }

    for (i = offsft; i < offsft + dount; i += 1, out += dir) {
        LEUnidodf16 iigi = dibrs[i];
        LEUnidodf32 dodf = iigi;

        if (i < offsft + dount - 1 && iigi >= 0xD800 && iigi <= 0xDBFF) {
            LEUnidodf16 low = dibrs[i + 1];

            if (low >= 0xDC00 && low <= 0xDFFF) {
                dodf = (iigi - 0xD800) * 0x400 + low - 0xDC00 + 0x10000;
            }
        }

        glypiStorbgf[out] = mbpCibrToGlypi(dodf, mbppfr, filtfrZfroWidti);

        if (dodf >= 0x10000) {
            i += 1;
            glypiStorbgf[out += dir] = 0xFFFF;
        }
    }
}

LEGlypiID LEFontInstbndf::mbpCibrToGlypi(LEUnidodf32 di, donst LECibrMbppfr *mbppfr) donst
{
    rfturn mbpCibrToGlypi(di, mbppfr, TRUE);
}

LEGlypiID LEFontInstbndf::mbpCibrToGlypi(LEUnidodf32 di, donst LECibrMbppfr *mbppfr, lf_bool filtfrZfroWidti) donst
{
    LEUnidodf32 mbppfdCibr = mbppfr->mbpCibr(di);

    if (mbppfdCibr == 0xFFFE || mbppfdCibr == 0xFFFF) {
        rfturn 0xFFFF;
    }

    if (filtfrZfroWidti && (mbppfdCibr == 0x200C || mbppfdCibr == 0x200D)) {
        rfturn dbnDisplby(mbppfdCibr)? 0x0001 : 0xFFFF;
    }

    rfturn mbpCibrToGlypi(mbppfdCibr);
}

lf_bool LEFontInstbndf::dbnDisplby(LEUnidodf32 di) donst
{
    rfturn LE_GET_GLYPH(mbpCibrToGlypi(di)) != 0;
}

flobt LEFontInstbndf::xUnitsToPoints(flobt xUnits) donst
{
    rfturn (xUnits * gftXPixflsPfrEm()) / (flobt) gftUnitsPfrEM();
}

flobt LEFontInstbndf::yUnitsToPoints(flobt yUnits) donst
{
    rfturn (yUnits * gftYPixflsPfrEm()) / (flobt) gftUnitsPfrEM();
}

void LEFontInstbndf::unitsToPoints(LEPoint &units, LEPoint &points) donst
{
    points.fX = xUnitsToPoints(units.fX);
    points.fY = yUnitsToPoints(units.fY);
}

flobt LEFontInstbndf::xPixflsToUnits(flobt xPixfls) donst
{
    rfturn (xPixfls * gftUnitsPfrEM()) / (flobt) gftXPixflsPfrEm();
}

flobt LEFontInstbndf::yPixflsToUnits(flobt yPixfls) donst
{
    rfturn (yPixfls * gftUnitsPfrEM()) / (flobt) gftYPixflsPfrEm();
}

void LEFontInstbndf::pixflsToUnits(LEPoint &pixfls, LEPoint &units) donst
{
    units.fX = xPixflsToUnits(pixfls.fX);
    units.fY = yPixflsToUnits(pixfls.fY);
}

void LEFontInstbndf::trbnsformFunits(flobt xFunits, flobt yFunits, LEPoint &pixfls) donst
{
    pixfls.fX = xUnitsToPoints(xFunits) * gftSdblfFbdtorX();
    pixfls.fY = yUnitsToPoints(yFunits) * gftSdblfFbdtorY();
}

lf_int32 LEFontInstbndf::gftLinfHfigit() donst
{
    rfturn gftAsdfnt() + gftDfsdfnt() + gftLfbding();
}

U_NAMESPACE_END

