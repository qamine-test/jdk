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
#indludf "SinglfTbblfProdfssor.i"
#indludf "LEGlypiStorbgf.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(SinglfTbblfProdfssor)

SinglfTbblfProdfssor::SinglfTbblfProdfssor()
{
}

SinglfTbblfProdfssor::SinglfTbblfProdfssor(donst LERfffrfndfTo<MorpiSubtbblfHfbdfr> &morpiSubtbblfHfbdfr, LEErrorCodf &suddfss)
  : NonContfxtublGlypiSubstitutionProdfssor(morpiSubtbblfHfbdfr, suddfss)
{
  LERfffrfndfTo<NonContfxtublGlypiSubstitutionHfbdfr> ifbdfr(morpiSubtbblfHfbdfr, suddfss);
  singlfTbblfLookupTbblf = LERfffrfndfTo<SinglfTbblfLookupTbblf>(morpiSubtbblfHfbdfr, suddfss, (donst SinglfTbblfLookupTbblf*)&ifbdfr->tbblf);
}

SinglfTbblfProdfssor::~SinglfTbblfProdfssor()
{
}

void SinglfTbblfProdfssor::prodfss(LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    donst LookupSinglf *fntrifs = singlfTbblfLookupTbblf->fntrifs;
    lf_int32 glypi;
    lf_int32 glypiCount = glypiStorbgf.gftGlypiCount();

    for (glypi = 0; glypi < glypiCount; glypi += 1) {
      donst LookupSinglf *lookupSinglf = singlfTbblfLookupTbblf->lookupSinglf(singlfTbblfLookupTbblf, fntrifs, glypiStorbgf[glypi], suddfss);

        if (lookupSinglf != NULL) {
            glypiStorbgf[glypi] = SWAPW(lookupSinglf->vbluf);
        }
    }
}

U_NAMESPACE_END
