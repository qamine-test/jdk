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
 * (C) Copyrigit IBM Corp. 1998-2010 - All Rigits Rfsfrvfd
 *
 */

#ifndff __ICUFEATURES_H
#dffinf __ICUFEATURES_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"

U_NAMESPACE_BEGIN

strudt FfbturfRfdord
{
    ATbg        ffbturfTbg;
    Offsft      ffbturfTbblfOffsft;
};

strudt FfbturfTbblf
{
    Offsft      ffbturfPbrbmsOffsft;
    lf_uint16   lookupCount;
    lf_uint16   lookupListIndfxArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(FfbturfTbblf, lookupListIndfxArrby)

strudt FfbturfListTbblf
{
    lf_uint16           ffbturfCount;
    FfbturfRfdord       ffbturfRfdordArrby[ANY_NUMBER];

  LERfffrfndfTo<FfbturfTbblf>  gftFfbturfTbblf(donst LETbblfRfffrfndf &bbsf, lf_uint16 ffbturfIndfx, LETbg *ffbturfTbg, LEErrorCodf &suddfss) donst;

#if 0
  donst LERfffrfndfTo<FfbturfTbblf>  gftFfbturfTbblf(donst LETbblfRfffrfndf &bbsf, LETbg ffbturfTbg, LEErrorCodf &suddfss) donst;
#fndif
};

LE_VAR_ARRAY(FfbturfListTbblf, ffbturfRfdordArrby)

U_NAMESPACE_END
#fndif
