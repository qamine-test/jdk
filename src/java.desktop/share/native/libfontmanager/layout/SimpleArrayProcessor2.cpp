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
 * (C) Copyrigit IBM Corp.  bnd otifrs 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "MorpiTbblfs.i"
#indludf "SubtbblfProdfssor2.i"
#indludf "NonContfxtublGlypiSubst.i"
#indludf "NonContfxtublGlypiSubstProd2.i"
#indludf "SimplfArrbyProdfssor2.i"
#indludf "LEGlypiStorbgf.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(SimplfArrbyProdfssor2)

SimplfArrbyProdfssor2::SimplfArrbyProdfssor2()
{
}

SimplfArrbyProdfssor2::SimplfArrbyProdfssor2(donst LERfffrfndfTo<MorpiSubtbblfHfbdfr2> &morpiSubtbblfHfbdfr, LEErrorCodf &suddfss)
  : NonContfxtublGlypiSubstitutionProdfssor2(morpiSubtbblfHfbdfr, suddfss)
{
  donst LERfffrfndfTo<NonContfxtublGlypiSubstitutionHfbdfr2> ifbdfr(morpiSubtbblfHfbdfr, suddfss);

  simplfArrbyLookupTbblf = LERfffrfndfTo<SimplfArrbyLookupTbblf>(morpiSubtbblfHfbdfr, suddfss, &ifbdfr->tbblf);
  vblufArrby = LERfffrfndfToArrbyOf<LookupVbluf>(morpiSubtbblfHfbdfr, suddfss, &simplfArrbyLookupTbblf->vblufArrby[0], LE_UNBOUNDED_ARRAY);
}

SimplfArrbyProdfssor2::~SimplfArrbyProdfssor2()
{
}

void SimplfArrbyProdfssor2::prodfss(LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) rfturn;
    lf_int32 glypiCount = glypiStorbgf.gftGlypiCount();
    lf_int32 glypi;

    for (glypi = 0; glypi < glypiCount; glypi += 1) {
        LEGlypiID tiisGlypi = glypiStorbgf[glypi];
        if (LE_GET_GLYPH(tiisGlypi) < 0xFFFF) {
          TTGlypiID nfwGlypi = SWAPW(vblufArrby(LE_GET_GLYPH(tiisGlypi),suddfss));

            glypiStorbgf[glypi] = LE_SET_GLYPH(tiisGlypi, nfwGlypi);
        }
    }
}

U_NAMESPACE_END
