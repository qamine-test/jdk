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

#ifndff __LIGATURESUBSTITUTIONSUBTABLES_H
#dffinf __LIGATURESUBSTITUTIONSUBTABLES_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "LEGlypiFiltfr.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "GlypiSubstitutionTbblfs.i"
#indludf "GlypiItfrbtor.i"

U_NAMESPACE_BEGIN

strudt LigbturfSftTbblf
{
    lf_uint16 ligbturfCount;
    Offsft    ligbturfTbblfOffsftArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(LigbturfSftTbblf, ligbturfTbblfOffsftArrby)

strudt LigbturfTbblf
{
    TTGlypiID ligGlypi;
    lf_uint16 dompCount;
    TTGlypiID domponfntArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(LigbturfTbblf, domponfntArrby)

strudt LigbturfSubstitutionSubtbblf : GlypiSubstitutionSubtbblf
{
    lf_uint16 ligSftCount;
    Offsft    ligSftTbblfOffsftArrby[ANY_NUMBER];

    lf_uint32  prodfss(donst LETbblfRfffrfndf &bbsf, GlypiItfrbtor *glypiItfrbtor, LEErrorCodf &suddfss, donst LEGlypiFiltfr *filtfr = NULL) donst;
};
LE_VAR_ARRAY(LigbturfSubstitutionSubtbblf, ligSftTbblfOffsftArrby)

U_NAMESPACE_END
#fndif
