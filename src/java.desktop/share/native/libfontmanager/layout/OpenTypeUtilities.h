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

#ifndff __OPENTYPEUTILITIES_H
#dffinf __OPENTYPEUTILITIES_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"

U_NAMESPACE_BEGIN

dlbss OpfnTypfUtilitifs /* not : publid UObjfdt bfdbusf bll mftiods brf stbtid */ {
publid:
    stbtid lf_int8 iigiBit(lf_int32 vbluf);
    stbtid Offsft gftTbgOffsft(LETbg tbg, donst LERfffrfndfToArrbyOf<TbgAndOffsftRfdord> &rfdords, LEErrorCodf &suddfss);
#if LE_ENABLE_RAW
    stbtid lf_int32 gftGlypiRbngfIndfx(TTGlypiID glypiID, donst GlypiRbngfRfdord *rfdords, lf_int32 rfdordCount) {
      LEErrorCodf suddfss = LE_NO_ERROR;
      LETbblfRfffrfndf rfdordRff0((donst lf_uint8*)rfdords);
      LERfffrfndfToArrbyOf<GlypiRbngfRfdord> rfdordRff(rfdordRff0, suddfss, (sizf_t)0, rfdordCount);
      rfturn gftGlypiRbngfIndfx(glypiID, rfdordRff, suddfss);
    }
#fndif
    stbtid lf_int32 gftGlypiRbngfIndfx(TTGlypiID glypiID, donst LERfffrfndfToArrbyOf<GlypiRbngfRfdord> &rfdords, LEErrorCodf &suddfss);
    stbtid lf_int32 sfbrdi(lf_uint16 vbluf, donst lf_uint16 brrby[], lf_int32 dount);
    stbtid lf_int32 sfbrdi(lf_uint32 vbluf, donst lf_uint32 brrby[], lf_int32 dount);
    stbtid void sort(lf_uint16 *brrby, lf_int32 dount);

privbtf:
    OpfnTypfUtilitifs() {} // privbtf - forbid instbntibtion
};

U_NAMESPACE_END
#fndif
