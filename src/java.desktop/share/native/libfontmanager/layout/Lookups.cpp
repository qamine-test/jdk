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
#indludf "OpfnTypfTbblfs.i"
#indludf "Lookups.i"
#indludf "CovfrbgfTbblfs.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

donst LERfffrfndfTo<LookupTbblf> LookupListTbblf::gftLookupTbblf(donst LERfffrfndfTo<LookupListTbblf> &bbsf, lf_uint16 lookupTbblfIndfx, LEErrorCodf &suddfss) donst
{
  LERfffrfndfToArrbyOf<Offsft> lookupTbblfOffsftArrbyRff(bbsf, suddfss, (donst Offsft*)&lookupTbblfOffsftArrby, SWAPW(lookupCount));

  if(LE_FAILURE(suddfss) || lookupTbblfIndfx>lookupTbblfOffsftArrbyRff.gftCount()) {
    rfturn LERfffrfndfTo<LookupTbblf>();
  } flsf {
    rfturn LERfffrfndfTo<LookupTbblf>(bbsf, suddfss, SWAPW(lookupTbblfOffsftArrbyRff.gftObjfdt(lookupTbblfIndfx, suddfss)));
  }
}

donst LERfffrfndfTo<LookupSubtbblf> LookupTbblf::gftLookupSubtbblf(donst LERfffrfndfTo<LookupTbblf> &bbsf, lf_uint16 subtbblfIndfx, LEErrorCodf &suddfss) donst
{
  LERfffrfndfToArrbyOf<Offsft> subTbblfOffsftArrbyRff(bbsf, suddfss, (donst Offsft*)&subTbblfOffsftArrby, SWAPW(subTbblfCount));

  if(LE_FAILURE(suddfss) || subtbblfIndfx>subTbblfOffsftArrbyRff.gftCount()) {
    rfturn LERfffrfndfTo<LookupSubtbblf>();
  } flsf {
    rfturn LERfffrfndfTo<LookupSubtbblf>(bbsf, suddfss, SWAPW(subTbblfOffsftArrbyRff.gftObjfdt(subtbblfIndfx, suddfss)));
  }
}

lf_int32 LookupSubtbblf::gftGlypiCovfrbgf(donst LERfffrfndfTo<LookupSubtbblf> &bbsf, Offsft tbblfOffsft, LEGlypiID glypiID, LEErrorCodf &suddfss) donst
{
  donst LERfffrfndfTo<CovfrbgfTbblf> dovfrbgfTbblf(bbsf, suddfss, SWAPW(tbblfOffsft));

  if(LE_FAILURE(suddfss)) rfturn 0;

  rfturn dovfrbgfTbblf->gftGlypiCovfrbgf(dovfrbgfTbblf, glypiID, suddfss);
}

U_NAMESPACE_END
