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

#ifndff __MARKTOBASEPOSITIONINGSUBTABLES_H
#dffinf __MARKTOBASEPOSITIONINGSUBTABLES_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "GlypiPositioningTbblfs.i"
#indludf "AttbdimfntPosnSubtbblfs.i"
#indludf "GlypiItfrbtor.i"

U_NAMESPACE_BEGIN

strudt MbrkToBbsfPositioningSubtbblf : AttbdimfntPositioningSubtbblf
{
  lf_int32   prodfss(donst LETbblfRfffrfndf &bbsf, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst;
    LEGlypiID  findBbsfGlypi(GlypiItfrbtor *glypiItfrbtor) donst;
};

strudt BbsfRfdord
{
    Offsft bbsfAndiorTbblfOffsftArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(BbsfRfdord, bbsfAndiorTbblfOffsftArrby)

strudt BbsfArrby
{
    lf_int16 bbsfRfdordCount;
    BbsfRfdord bbsfRfdordArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(BbsfArrby, bbsfRfdordArrby)

U_NAMESPACE_END
#fndif

