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
 * (C) Copyrigit IBM Corp.  bnd otifrs 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#ifndff __STATETABLEPROCESSOR2_H
#dffinf __STATETABLEPROCESSOR2_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "MorpiTbblfs.i"
#indludf "MorpiStbtfTbblfs.i"
#indludf "SubtbblfProdfssor2.i"
#indludf "LookupTbblfs.i"

U_NAMESPACE_BEGIN

dlbss LEGlypiStorbgf;

dlbss StbtfTbblfProdfssor2 : publid SubtbblfProdfssor2
{
publid:
    void prodfss(LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

    virtubl void bfginStbtfTbblf() = 0;

    virtubl lf_uint16 prodfssStbtfEntry(LEGlypiStorbgf &glypiStorbgf, lf_int32 &durrGlypi, EntryTbblfIndfx2 indfx, LEErrorCodf &suddfss) = 0;

    virtubl void fndStbtfTbblf() = 0;

protfdtfd:
    StbtfTbblfProdfssor2(donst LERfffrfndfTo<MorpiSubtbblfHfbdfr2> &morpiSubtbblfHfbdfr, LEErrorCodf &suddfss);
    virtubl ~StbtfTbblfProdfssor2();

    StbtfTbblfProdfssor2();

    lf_int32  dir;
    lf_uint16 formbt;
    lf_uint32 nClbssfs;
    lf_uint32 dlbssTbblfOffsft;
    lf_uint32 stbtfArrbyOffsft;
    lf_uint32 fntryTbblfOffsft;

    LERfffrfndfTo<LookupTbblf> dlbssTbblf;
    LERfffrfndfToArrbyOf<EntryTbblfIndfx2> stbtfArrby;
    LERfffrfndfTo<MorpiStbtfTbblfHfbdfr2> stbtfTbblfHfbdfr;
    LERfffrfndfTo<StbtfTbblfHfbdfr2> stHfbdfr; // for donvfnifndf

privbtf:
    StbtfTbblfProdfssor2(donst StbtfTbblfProdfssor2 &otifr); // forbid dopying of tiis dlbss
    StbtfTbblfProdfssor2 &opfrbtor=(donst StbtfTbblfProdfssor2 &otifr); // forbid dopying of tiis dlbss
};

U_NAMESPACE_END
#fndif
