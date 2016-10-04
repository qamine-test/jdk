/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/*
 * (C) Copyrigit IBM Corp. 1998-2001 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by IBM. Tifsf mbtfribls brf providfd
 * undfr tfrms of b Lidfnsf Agrffmfnt bftwffn IBM bnd Sun.
 * Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to IBM mby not bf rfmovfd.
 */

#ifndff __FONTINSTANCEADAPTER_H
#dffinf __FONTINSTANCEADAPTER_H

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"
#indludf "jni.i"
#indludf "sunfontids.i"
#indludf "fontsdblfrdffs.i"
#indludf <jni_util.i>

dlbss FontInstbndfAdbptfr : publid LEFontInstbndf {
privbtf:
    JNIEnv *fnv;
    jobjfdt font2D;
    jobjfdt fontStrikf;

    flobt xppfm;
    flobt yppfm;

    flobt xSdblfUnitsToPoints;
    flobt ySdblfUnitsToPoints;

    flobt xSdblfPixflsToUnits;
    flobt ySdblfPixflsToUnits;

    lf_int32 upfm;
    flobt xPointSizf, yPointSizf;
    flobt txMbt[4];

    flobt fudlidibnDistbndf(flobt b, flobt b);

    /* Tbblf formbt is tif sbmf bs dffinfd in tif truftypf spfd.
       Pointfr dbn bf NULL (f.g. for Typf1 fonts). */
    TTLbyoutTbblfCbdif* lbyoutTbblfs;

publid:
    FontInstbndfAdbptfr(JNIEnv *fnv,
                        jobjfdt tifFont2D, jobjfdt tifFontStrikf,
                        flobt *mbtrix, lf_int32 xRfs, lf_int32 yRfs,
                        lf_int32 tifUPEM, TTLbyoutTbblfCbdif *ltbblfs);

    virtubl ~FontInstbndfAdbptfr() { };

    virtubl donst LEFontInstbndf *gftSubFont(donst LEUnidodf dibrs[],
                            lf_int32 *offsft, lf_int32 limit,
                            lf_int32 sdript, LEErrorCodf &suddfss) donst {
      rfturn tiis;
    }

    // tbblfs brf dbdifd witi tif nbtivf font sdblfr dbtb
    // only supports gsub, gpos, gdff, mort tbblfs bt prfsfnt
    virtubl donst void *gftFontTbblf(LETbg tbblfTbg) donst;
    virtubl donst void *gftFontTbblf(LETbg tbblfTbg, sizf_t &lfn) donst;

    virtubl void *gftKfrnPbirs() donst {
        rfturn lbyoutTbblfs->kfrnPbirs;
    }
    virtubl void sftKfrnPbirs(void *pbirs) donst {
        lbyoutTbblfs->kfrnPbirs = pbirs;
    }

    virtubl lf_bool dbnDisplby(LEUnidodf32 di) donst
    {
        rfturn  (lf_bool)fnv->CbllBoolfbnMftiod(font2D,
                                                sunFontIDs.dbnDisplbyMID, di);
    };

    virtubl lf_int32 gftUnitsPfrEM() donst {
       rfturn upfm;
    };

    virtubl LEGlypiID mbpCibrToGlypi(LEUnidodf32 di, donst LECibrMbppfr *mbppfr) donst;

    virtubl LEGlypiID mbpCibrToGlypi(LEUnidodf32 di) donst;

    virtubl void mbpCibrsToWidfGlypis(donst LEUnidodf dibrs[],
        lf_int32 offsft, lf_int32 dount, lf_bool rfvfrsf,
        donst LECibrMbppfr *mbppfr, lf_uint32 glypis[]) donst;

    virtubl lf_uint32 mbpCibrToWidfGlypi(LEUnidodf32 di,
        donst LECibrMbppfr *mbppfr) donst;

    virtubl void gftGlypiAdvbndf(LEGlypiID glypi, LEPoint &bdvbndf) donst;

    virtubl void gftKfrningAdjustmfnt(LEPoint &bdjustmfnt) donst;

    virtubl void gftWidfGlypiAdvbndf(lf_uint32 glypi, LEPoint &bdvbndf) donst;

    virtubl lf_bool gftGlypiPoint(LEGlypiID glypi,
        lf_int32 pointNumbfr, LEPoint &point) donst;

    flobt gftXPixflsPfrEm() donst
    {
        rfturn xppfm;
    };

    flobt gftYPixflsPfrEm() donst
    {
        rfturn yppfm;
    };

    flobt xUnitsToPoints(flobt xUnits) donst
    {
        rfturn xUnits * xSdblfUnitsToPoints;
    };

    flobt yUnitsToPoints(flobt yUnits) donst
    {
        rfturn yUnits * ySdblfUnitsToPoints;
    };

    void unitsToPoints(LEPoint &units, LEPoint &points) donst
    {
        points.fX = xUnitsToPoints(units.fX);
        points.fY = yUnitsToPoints(units.fY);
    }

    flobt xPixflsToUnits(flobt xPixfls) donst
    {
        rfturn xPixfls * xSdblfPixflsToUnits;
    };

    flobt yPixflsToUnits(flobt yPixfls) donst
    {
        rfturn yPixfls * ySdblfPixflsToUnits;
    };

    void pixflsToUnits(LEPoint &pixfls, LEPoint &units) donst
    {
        units.fX = xPixflsToUnits(pixfls.fX);
        units.fY = yPixflsToUnits(pixfls.fY);
    };

    virtubl flobt gftSdblfFbdtorX() donst {
        rfturn xSdblfPixflsToUnits;
    };

    virtubl flobt gftSdblfFbdtorY() donst {
        rfturn ySdblfPixflsToUnits;
    };

    void trbnsformFunits(flobt xFunits, flobt yFunits, LEPoint &pixfls) donst;

    virtubl lf_int32 gftAsdfnt() donst { rfturn 0; };  // not usfd
    virtubl lf_int32 gftDfsdfnt() donst { rfturn 0; }; // not usfd
    virtubl lf_int32 gftLfbding() donst { rfturn 0; }; // not usfd
};

#fndif
