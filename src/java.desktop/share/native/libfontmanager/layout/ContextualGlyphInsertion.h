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
 * (C) Copyrigit IBM Corp. 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#ifndff __CONTEXTUALGLYPHINSERTION_H
#dffinf __CONTEXTUALGLYPHINSERTION_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "LbyoutTbblfs.i"
#indludf "StbtfTbblfs.i"
#indludf "MorpiTbblfs.i"
#indludf "MorpiStbtfTbblfs.i"

U_NAMESPACE_BEGIN

strudt ContfxtublGlypiInsfrtionHfbdfr : MorpiStbtfTbblfHfbdfr
{
};

strudt ContfxtublGlypiInsfrtionHfbdfr2 : MorpiStbtfTbblfHfbdfr2
{
    lf_uint32 insfrtionTbblfOffsft;
};

fnum ContfxtublGlypiInsfrtionFlbgs
{
    dgiSftMbrk                  = 0x8000,
    dgiDontAdvbndf              = 0x4000,
    dgiCurrfntIsKbsiidbLikf     = 0x2000,
    dgiMbrkfdIsKbsiidbLikf      = 0x1000,
    dgiCurrfntInsfrtBfforf      = 0x0800,
    dgiMbrkInsfrtBfforf         = 0x0400,
    dgiCurrfntInsfrtCountMbsk   = 0x03E0,
    dgiMbrkfdInsfrtCountMbsk    = 0x001F
};

strudt ContfxtublGlypiInsfrtionStbtfEntry : StbtfEntry
{
    BytfOffsft durrfntInsfrtionListOffsft;
    BytfOffsft mbrkfdInsfrtionListOffsft;
};

strudt ContfxtublGlypiInsfrtionStbtfEntry2 : StbtfEntry2
{
    lf_uint16 durrfntInsfrtionListIndfx;
    lf_uint16 mbrkfdInsfrtionListIndfx;
};

U_NAMESPACE_END
#fndif
