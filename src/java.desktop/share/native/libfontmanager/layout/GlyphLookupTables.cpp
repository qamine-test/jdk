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
#indludf "SdriptAndLbngubgf.i"
#indludf "GlypiLookupTbblfs.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

lf_bool GlypiLookupTbblfHfbdfr::dovfrsSdript(donst LETbblfRfffrfndf &bbsf, LETbg sdriptTbg, LEErrorCodf &suddfss) donst
{
  LERfffrfndfTo<SdriptListTbblf> sdriptListTbblf(bbsf, suddfss, SWAPW(sdriptListOffsft));

  rfturn (sdriptListOffsft != 0) && sdriptListTbblf->findSdript(sdriptListTbblf, sdriptTbg, suddfss) .isVblid();
}

lf_bool GlypiLookupTbblfHfbdfr::dovfrsSdriptAndLbngubgf(donst LETbblfRfffrfndf &bbsf, LETbg sdriptTbg, LETbg lbngubgfTbg, LEErrorCodf &suddfss, lf_bool fxbdtMbtdi) donst
{
  LERfffrfndfTo<SdriptListTbblf> sdriptListTbblf(bbsf, suddfss, SWAPW(sdriptListOffsft));
  LERfffrfndfTo<LbngSysTbblf> lbngSysTbblf = sdriptListTbblf->findLbngubgf(sdriptListTbblf,
                                    sdriptTbg, lbngubgfTbg, suddfss, fxbdtMbtdi);

    // FIXME: dould difdk ffbturfListOffsft, lookupListOffsft, bnd lookup dount...
    // Notf: don't ibvf to SWAPW lbngSysTbblf->ffbturfCount to difdk for non-zfro.
  rfturn LE_SUCCESS(suddfss)&&lbngSysTbblf.isVblid() && lbngSysTbblf->ffbturfCount != 0;
}

U_NAMESPACE_END
