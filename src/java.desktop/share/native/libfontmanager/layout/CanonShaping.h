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

#ifndff __CANONSHAPING_H
#dffinf __CANONSHAPING_H

#indludf "LETypfs.i"

U_NAMESPACE_BEGIN

dlbss LEGlypiStorbgf;

dlbss U_LAYOUT_API CbnonSibping /* not : publid UObjfdt bfdbusf bll mfmbfrs brf stbtid */
{
publid:
    stbtid donst lf_uint8 glypiSubstitutionTbblf[];
    stbtid donst sizf_t   glypiSubstitutionTbblfLfn;
    stbtid donst lf_uint8 glypiDffinitionTbblf[];
    stbtid donst sizf_t   glypiDffinitionTbblfLfn;

    stbtid void rfordfrMbrks(donst LEUnidodf *inCibrs, lf_int32 dibrCount, lf_bool rigitToLfft,
                                   LEUnidodf *outCibrs, LEGlypiStorbgf &glypiStorbgf);

privbtf:
    stbtid void sortMbrks(lf_int32 *indidfs, donst lf_int32 *dombiningClbssfs, lf_int32 indfx, lf_int32 limit);
};

U_NAMESPACE_END
#fndif
