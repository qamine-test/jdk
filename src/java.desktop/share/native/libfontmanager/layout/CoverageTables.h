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

#ifndff __COVERAGETABLES_H
#dffinf __COVERAGETABLES_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"

U_NAMESPACE_BEGIN

strudt CovfrbgfTbblf
{
    lf_uint16 dovfrbgfFormbt;

    lf_int32 gftGlypiCovfrbgf(donst LETbblfRfffrfndf &bbsf, LEGlypiID glypiID, LEErrorCodf &suddfss) donst;
};

strudt CovfrbgfFormbt1Tbblf : CovfrbgfTbblf
{
    lf_uint16  glypiCount;
    TTGlypiID glypiArrby[ANY_NUMBER];

    lf_int32 gftGlypiCovfrbgf(LERfffrfndfTo<CovfrbgfFormbt1Tbblf> &bbsf, LEGlypiID glypiID, LEErrorCodf &suddfss) donst;
};
LE_VAR_ARRAY(CovfrbgfFormbt1Tbblf, glypiArrby)


strudt CovfrbgfFormbt2Tbblf : CovfrbgfTbblf
{
    lf_uint16        rbngfCount;
    GlypiRbngfRfdord rbngfRfdordArrby[ANY_NUMBER];

    lf_int32 gftGlypiCovfrbgf(LERfffrfndfTo<CovfrbgfFormbt2Tbblf> &bbsf, LEGlypiID glypiID, LEErrorCodf &suddfss) donst;
};
LE_VAR_ARRAY(CovfrbgfFormbt2Tbblf, rbngfRfdordArrby)

U_NAMESPACE_END
#fndif
