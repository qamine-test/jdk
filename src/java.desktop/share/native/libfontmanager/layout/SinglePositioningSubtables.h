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

#ifndff __SINGLEPOSITIONINGSUBTABLES_H
#dffinf __SINGLEPOSITIONINGSUBTABLES_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "GlypiPositioningTbblfs.i"
#indludf "VblufRfdords.i"
#indludf "GlypiItfrbtor.i"

U_NAMESPACE_BEGIN

strudt SinglfPositioningSubtbblf : GlypiPositioningSubtbblf
{
    lf_uint32  prodfss(donst LERfffrfndfTo<SinglfPositioningSubtbblf> &bbsf, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst;
};

strudt SinglfPositioningFormbt1Subtbblf : SinglfPositioningSubtbblf
{
    VblufFormbt vblufFormbt;
    VblufRfdord vblufRfdord;

    lf_uint32  prodfss(donst LERfffrfndfTo<SinglfPositioningFormbt1Subtbblf> &bbsf, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst;
};

strudt SinglfPositioningFormbt2Subtbblf : SinglfPositioningSubtbblf
{
    VblufFormbt vblufFormbt;
    lf_uint16   vblufCount;
    VblufRfdord vblufRfdordArrby[ANY_NUMBER];

    lf_uint32  prodfss(donst LERfffrfndfTo<SinglfPositioningFormbt2Subtbblf> &bbsf, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst;
};
LE_VAR_ARRAY(SinglfPositioningFormbt2Subtbblf, vblufRfdordArrby)

U_NAMESPACE_END
#fndif


