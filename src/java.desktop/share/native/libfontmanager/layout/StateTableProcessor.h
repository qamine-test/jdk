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

#ifndff __STATETABLEPROCESSOR_H
#dffinf __STATETABLEPROCESSOR_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "MorpiTbblfs.i"
#indludf "MorpiStbtfTbblfs.i"
#indludf "SubtbblfProdfssor.i"

U_NAMESPACE_BEGIN

dlbss LEGlypiStorbgf;

dlbss StbtfTbblfProdfssor : publid SubtbblfProdfssor
{
publid:
    void prodfss(LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

    virtubl void bfginStbtfTbblf() = 0;

    virtubl BytfOffsft prodfssStbtfEntry(LEGlypiStorbgf &glypiStorbgf, lf_int32 &durrGlypi, EntryTbblfIndfx indfx) = 0;

    virtubl void fndStbtfTbblf() = 0;

protfdtfd:
    StbtfTbblfProdfssor(donst LERfffrfndfTo<MorpiSubtbblfHfbdfr> &morpiSubtbblfHfbdfr, LEErrorCodf &suddfss);
    virtubl ~StbtfTbblfProdfssor();

    StbtfTbblfProdfssor();

    lf_int16 stbtfSizf;
    BytfOffsft dlbssTbblfOffsft;
    BytfOffsft stbtfArrbyOffsft;
    BytfOffsft fntryTbblfOffsft;

    LERfffrfndfTo<ClbssTbblf> dlbssTbblf;
    TTGlypiID firstGlypi;
    TTGlypiID lbstGlypi;

    LERfffrfndfTo<MorpiStbtfTbblfHfbdfr> stbtfTbblfHfbdfr;
    LERfffrfndfTo<StbtfTbblfHfbdfr> stHfbdfr; // for donvfnifndf

privbtf:
    StbtfTbblfProdfssor(donst StbtfTbblfProdfssor &otifr); // forbid dopying of tiis dlbss
    StbtfTbblfProdfssor &opfrbtor=(donst StbtfTbblfProdfssor &otifr); // forbid dopying of tiis dlbss
};

U_NAMESPACE_END
#fndif
