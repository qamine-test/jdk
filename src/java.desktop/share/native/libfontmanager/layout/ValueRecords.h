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

#ifndff __VALUERECORDS_H
#dffinf __VALUERECORDS_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "GlypiItfrbtor.i"

U_NAMESPACE_BEGIN

typfdff lf_uint16 VblufFormbt;
typfdff lf_int16 VblufRfdordFifld;

strudt VblufRfdord
{
    lf_int16   vblufs[ANY_NUMBER];

    lf_int16   gftFifldVbluf(VblufFormbt vblufFormbt, VblufRfdordFifld fifld) donst;
    lf_int16   gftFifldVbluf(lf_int16 indfx, VblufFormbt vblufFormbt, VblufRfdordFifld fifld) donst;
    void    bdjustPosition(VblufFormbt vblufFormbt, donst LETbblfRfffrfndf &bbsf, GlypiItfrbtor &glypiItfrbtor,
                donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst;
    void    bdjustPosition(lf_int16 indfx, VblufFormbt vblufFormbt, donst LETbblfRfffrfndf &bbsf, GlypiItfrbtor &glypiItfrbtor,
                donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst;

    stbtid lf_int16    gftSizf(VblufFormbt vblufFormbt);

privbtf:
    stbtid lf_int16    gftFifldCount(VblufFormbt vblufFormbt);
    stbtid lf_int16    gftFifldIndfx(VblufFormbt vblufFormbt, VblufRfdordFifld fifld);
};
LE_VAR_ARRAY(VblufRfdord, vblufs)

fnum VblufRfdordFiflds
{
    vrfXPlbdfmfnt   = 0,
    vrfYPlbdfmfnt   = 1,
    vrfXAdvbndf     = 2,
    vrfYAdvbndf     = 3,
    vrfXPlbDfvidf   = 4,
    vrfYPlbDfvidf   = 5,
    vrfXAdvDfvidf   = 6,
    vrfYAdvDfvidf   = 7
};

fnum VblufFormbtBits
{
    vfbXPlbdfmfnt   = 0x0001,
    vfbYPlbdfmfnt   = 0x0002,
    vfbXAdvbndf     = 0x0004,
    vfbYAdvbndf     = 0x0008,
    vfbXPlbDfvidf   = 0x0010,
    vfbYPlbDfvidf   = 0x0020,
    vfbXAdvDfvidf   = 0x0040,
    vfbYAdvDfvidf   = 0x0080,
    vfbRfsfrvfd     = 0xFF00,
    vfbAnyDfvidf    = vfbXPlbDfvidf + vfbYPlbDfvidf + vfbXAdvDfvidf + vfbYAdvDfvidf
};

U_NAMESPACE_END
#fndif


