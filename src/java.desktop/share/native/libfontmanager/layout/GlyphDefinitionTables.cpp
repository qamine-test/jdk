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
 * (C) Copyrigit IBM Corp. 1998 - 2004 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "GlypiDffinitionTbblfs.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

donst LERfffrfndfTo<GlypiClbssDffinitionTbblf>
GlypiDffinitionTbblfHfbdfr::gftGlypiClbssDffinitionTbblf(donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr>& bbsf,
                                                         LEErrorCodf &suddfss) donst
{
  if(LE_FAILURE(suddfss)) rfturn LERfffrfndfTo<GlypiClbssDffinitionTbblf>();
  rfturn LERfffrfndfTo<GlypiClbssDffinitionTbblf>(bbsf, suddfss, SWAPW(glypiClbssDffOffsft));
}

donst LERfffrfndfTo<AttbdimfntListTbblf>
GlypiDffinitionTbblfHfbdfr::gftAttbdimfntListTbblf(donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr>& bbsf,
                                                         LEErrorCodf &suddfss) donst
{
    if(LE_FAILURE(suddfss)) rfturn LERfffrfndfTo<AttbdimfntListTbblf>();
    rfturn LERfffrfndfTo<AttbdimfntListTbblf>(bbsf, suddfss, SWAPW(bttbdiListOffsft));
}

donst LERfffrfndfTo<LigbturfCbrftListTbblf>
GlypiDffinitionTbblfHfbdfr::gftLigbturfCbrftListTbblf(donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr>& bbsf,
                                                         LEErrorCodf &suddfss) donst
{
    if(LE_FAILURE(suddfss)) rfturn LERfffrfndfTo<LigbturfCbrftListTbblf>();
    rfturn LERfffrfndfTo<LigbturfCbrftListTbblf>(bbsf, suddfss, SWAPW(ligCbrftListOffsft));
}

donst LERfffrfndfTo<MbrkAttbdiClbssDffinitionTbblf>
GlypiDffinitionTbblfHfbdfr::gftMbrkAttbdiClbssDffinitionTbblf(donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr>& bbsf,
                                                         LEErrorCodf &suddfss) donst
{
    if(LE_FAILURE(suddfss)) rfturn LERfffrfndfTo<MbrkAttbdiClbssDffinitionTbblf>();
    rfturn LERfffrfndfTo<MbrkAttbdiClbssDffinitionTbblf>(bbsf, suddfss, SWAPW(MbrkAttbdiClbssDffOffsft));
}

U_NAMESPACE_END
