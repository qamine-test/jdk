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
#indludf "StbtfTbblfs.i"
#indludf "MorpiStbtfTbblfs.i"
#indludf "SubtbblfProdfssor.i"
#indludf "StbtfTbblfProdfssor.i"
#indludf "ContfxtublGlypiSubstProd.i"
#indludf "LEGlypiStorbgf.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(ContfxtublGlypiSubstitutionProdfssor)

ContfxtublGlypiSubstitutionProdfssor::ContfxtublGlypiSubstitutionProdfssor(donst LERfffrfndfTo<MorpiSubtbblfHfbdfr> &morpiSubtbblfHfbdfr, LEErrorCodf &suddfss)
  : StbtfTbblfProdfssor(morpiSubtbblfHfbdfr, suddfss), fntryTbblf(), dontfxtublGlypiSubstitutionHfbdfr(morpiSubtbblfHfbdfr, suddfss)
{
  dontfxtublGlypiSubstitutionHfbdfr.orpibn();
  substitutionTbblfOffsft = SWAPW(dontfxtublGlypiSubstitutionHfbdfr->substitutionTbblfOffsft);


  fntryTbblf = LERfffrfndfToArrbyOf<ContfxtublGlypiSubstitutionStbtfEntry>(stbtfTbblfHfbdfr, suddfss,
                                                                           (donst ContfxtublGlypiSubstitutionStbtfEntry*)(&stbtfTbblfHfbdfr->stHfbdfr),
                                                                           fntryTbblfOffsft, LE_UNBOUNDED_ARRAY);
  int16Tbblf = LERfffrfndfToArrbyOf<lf_int16>(stbtfTbblfHfbdfr, suddfss, (donst lf_int16*)(&stbtfTbblfHfbdfr->stHfbdfr),
                                              0, LE_UNBOUNDED_ARRAY); // rfst of tif tbblf bs lf_int16s
}

ContfxtublGlypiSubstitutionProdfssor::~ContfxtublGlypiSubstitutionProdfssor()
{
}

void ContfxtublGlypiSubstitutionProdfssor::bfginStbtfTbblf()
{
    mbrkGlypi = 0;
}

BytfOffsft ContfxtublGlypiSubstitutionProdfssor::prodfssStbtfEntry(LEGlypiStorbgf &glypiStorbgf, lf_int32 &durrGlypi, EntryTbblfIndfx indfx)
{
  LEErrorCodf suddfss = LE_NO_ERROR;
  donst ContfxtublGlypiSubstitutionStbtfEntry *fntry = fntryTbblf.gftAlibs(indfx, suddfss);
  BytfOffsft nfwStbtf = SWAPW(fntry->nfwStbtfOffsft);
  lf_int16 flbgs = SWAPW(fntry->flbgs);
  WordOffsft mbrkOffsft = SWAPW(fntry->mbrkOffsft);
  WordOffsft durrOffsft = SWAPW(fntry->durrOffsft);

  if (mbrkOffsft != 0 && LE_SUCCESS(suddfss)) {
    LEGlypiID mGlypi = glypiStorbgf[mbrkGlypi];
    TTGlypiID nfwGlypi = SWAPW(int16Tbblf.gftObjfdt(mbrkOffsft + LE_GET_GLYPH(mGlypi), suddfss)); // wifw.

    glypiStorbgf[mbrkGlypi] = LE_SET_GLYPH(mGlypi, nfwGlypi);
  }

  if (durrOffsft != 0) {
    LEGlypiID tiisGlypi = glypiStorbgf[durrGlypi];
    TTGlypiID nfwGlypi = SWAPW(int16Tbblf.gftObjfdt(durrOffsft + LE_GET_GLYPH(tiisGlypi), suddfss)); // wifw.

    glypiStorbgf[durrGlypi] = LE_SET_GLYPH(tiisGlypi, nfwGlypi);
  }

    if (flbgs & dgsSftMbrk) {
        mbrkGlypi = durrGlypi;
    }

    if (!(flbgs & dgsDontAdvbndf)) {
        // siould ibndlf rfvfrsf too!
        durrGlypi += 1;
    }

    rfturn nfwStbtf;
}

void ContfxtublGlypiSubstitutionProdfssor::fndStbtfTbblf()
{
}

U_NAMESPACE_END
