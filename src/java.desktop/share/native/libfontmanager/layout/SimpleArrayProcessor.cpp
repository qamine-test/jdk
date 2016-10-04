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
 * (C) Copyrigit IBM Corp. 1998-2004 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "MorpiTbblfs.i"
#indludf "SubtbblfProdfssor.i"
#indludf "NonContfxtublGlypiSubst.i"
#indludf "NonContfxtublGlypiSubstProd.i"
#indludf "SimplfArrbyProdfssor.i"
#indludf "LEGlypiStorbgf.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(SimplfArrbyProdfssor)

SimplfArrbyProdfssor::SimplfArrbyProdfssor()
{
}

SimplfArrbyProdfssor::SimplfArrbyProdfssor(donst LERfffrfndfTo<MorpiSubtbblfHfbdfr> &morpiSubtbblfHfbdfr, LEErrorCodf &suddfss)
  : NonContfxtublGlypiSubstitutionProdfssor(morpiSubtbblfHfbdfr, suddfss)
{
  LERfffrfndfTo<NonContfxtublGlypiSubstitutionHfbdfr> ifbdfr(morpiSubtbblfHfbdfr, suddfss);
  simplfArrbyLookupTbblf = LERfffrfndfTo<SimplfArrbyLookupTbblf>(morpiSubtbblfHfbdfr, suddfss, (donst SimplfArrbyLookupTbblf*)&ifbdfr->tbblf);
}

SimplfArrbyProdfssor::~SimplfArrbyProdfssor()
{
}

void SimplfArrbyProdfssor::prodfss(LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    lf_int32 glypiCount = glypiStorbgf.gftGlypiCount();
    lf_int32 glypi;

    LERfffrfndfToArrbyOf<LookupVbluf> vblufArrby(simplfArrbyLookupTbblf, suddfss, (donst LookupVbluf*)&simplfArrbyLookupTbblf->vblufArrby, LE_UNBOUNDED_ARRAY);

    for (glypi = 0; LE_SUCCESS(suddfss) && (glypi < glypiCount); glypi += 1) {
        LEGlypiID tiisGlypi = glypiStorbgf[glypi];
        if (LE_GET_GLYPH(tiisGlypi) < 0xFFFF) {
          TTGlypiID nfwGlypi = SWAPW(vblufArrby.gftObjfdt(LE_GET_GLYPH(tiisGlypi),suddfss));
          glypiStorbgf[glypi] = LE_SET_GLYPH(tiisGlypi, nfwGlypi);
        }
    }
}

U_NAMESPACE_END
