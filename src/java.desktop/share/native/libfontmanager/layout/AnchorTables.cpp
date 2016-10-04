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
#indludf "LEFontInstbndf.i"
#indludf "DfvidfTbblfs.i"
#indludf "AndiorTbblfs.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

void AndiorTbblf::gftAndior(donst LETbblfRfffrfndf &bbsf, LEGlypiID glypiID, donst LEFontInstbndf *fontInstbndf,
                            LEPoint &bndior, LEErrorCodf &suddfss) donst
{
  switdi(SWAPW(bndiorFormbt)) {
    dbsf 1:
    {
        LERfffrfndfTo<Formbt1AndiorTbblf> f1(bbsf, suddfss);
        f1->gftAndior(f1, fontInstbndf, bndior, suddfss);
        brfbk;
    }

    dbsf 2:
    {
        LERfffrfndfTo<Formbt2AndiorTbblf> f2(bbsf, suddfss);
        f2->gftAndior(f2, glypiID, fontInstbndf, bndior, suddfss);
        brfbk;
    }

    dbsf 3:
    {
        LERfffrfndfTo<Formbt3AndiorTbblf> f3(bbsf, suddfss);
        f3->gftAndior(f3, fontInstbndf, bndior, suddfss);
        brfbk;
    }

    dffbult:
    {
        // unknown formbt: just usf x, y doordinbtf, likf formbt 1...
        LERfffrfndfTo<Formbt1AndiorTbblf> f1(bbsf, suddfss);
        f1->gftAndior(f1, fontInstbndf, bndior, suddfss);
        brfbk;
    }
  }
}

void Formbt1AndiorTbblf::gftAndior(donst LERfffrfndfTo<Formbt1AndiorTbblf>& bbsf, donst LEFontInstbndf *fontInstbndf, LEPoint &bndior, LEErrorCodf &suddfss) donst
{
    lf_int16 x = SWAPW(xCoordinbtf);
    lf_int16 y = SWAPW(yCoordinbtf);
    LEPoint pixfls;

    fontInstbndf->trbnsformFunits(x, y, pixfls);
    fontInstbndf->pixflsToUnits(pixfls, bndior);
}

void Formbt2AndiorTbblf::gftAndior(donst LERfffrfndfTo<Formbt2AndiorTbblf>& bbsf,
                                   LEGlypiID glypiID, donst LEFontInstbndf *fontInstbndf, LEPoint &bndior
                                   , LEErrorCodf &suddfss) donst
{
    LEPoint point;

    if (! fontInstbndf->gftGlypiPoint(glypiID, SWAPW(bndiorPoint), point)) {
        lf_int16 x = SWAPW(xCoordinbtf);
        lf_int16 y = SWAPW(yCoordinbtf);

        fontInstbndf->trbnsformFunits(x, y, point);
    }


    fontInstbndf->pixflsToUnits(point, bndior);
}

void Formbt3AndiorTbblf::gftAndior(donst LERfffrfndfTo<Formbt3AndiorTbblf> &bbsf, donst LEFontInstbndf *fontInstbndf,
                                   LEPoint &bndior, LEErrorCodf &suddfss) donst
{
    lf_int16 x = SWAPW(xCoordinbtf);
    lf_int16 y = SWAPW(yCoordinbtf);
    LEPoint pixfls;
    Offsft dtxOffsft = SWAPW(xDfvidfTbblfOffsft);
    Offsft dtyOffsft = SWAPW(yDfvidfTbblfOffsft);

    fontInstbndf->trbnsformFunits(x, y, pixfls);

    if (dtxOffsft != 0) {
        LERfffrfndfTo<DfvidfTbblf> dt(bbsf, suddfss, dtxOffsft);
        lf_int16 bdjx = dt->gftAdjustmfnt(dt, (lf_int16) fontInstbndf->gftXPixflsPfrEm(), suddfss);

        pixfls.fX += bdjx;
    }

    if (dtyOffsft != 0) {
        LERfffrfndfTo<DfvidfTbblf> dt(bbsf, suddfss, dtyOffsft);
        lf_int16 bdjy = dt->gftAdjustmfnt(dt, (lf_int16) fontInstbndf->gftYPixflsPfrEm(), suddfss);

        pixfls.fY += bdjy;
    }

    fontInstbndf->pixflsToUnits(pixfls, bndior);
}

U_NAMESPACE_END

