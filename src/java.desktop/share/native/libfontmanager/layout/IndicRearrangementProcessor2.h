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

#ifndff __INDICREARRANGEMENTPROCESSOR2_H
#dffinf __INDICREARRANGEMENTPROCESSOR2_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "MorpiTbblfs.i"
#indludf "SubtbblfProdfssor.i"
#indludf "StbtfTbblfProdfssor2.i"
#indludf "IndidRfbrrbngfmfnt.i"

U_NAMESPACE_BEGIN

dlbss LEGlypiStorbgf;

dlbss IndidRfbrrbngfmfntProdfssor2 : publid StbtfTbblfProdfssor2
{
publid:
    virtubl void bfginStbtfTbblf();

    virtubl lf_uint16 prodfssStbtfEntry(LEGlypiStorbgf &glypiStorbgf, lf_int32 &durrGlypi, EntryTbblfIndfx2 indfx, LEErrorCodf &suddfss);

    virtubl void fndStbtfTbblf();

    void doRfbrrbngfmfntAdtion(LEGlypiStorbgf &glypiStorbgf, IndidRfbrrbngfmfntVfrb vfrb) donst;

    IndidRfbrrbngfmfntProdfssor2(donst LERfffrfndfTo<MorpiSubtbblfHfbdfr2> &morpiSubtbblfHfbdfr, LEErrorCodf &suddfss);
    virtubl ~IndidRfbrrbngfmfntProdfssor2();

    /**
     * ICU "poor mbn's RTTI", rfturns b UClbssID for tif bdtubl dlbss.
     *
     * @stbblf ICU 2.8
     */
    virtubl UClbssID gftDynbmidClbssID() donst;

    /**
     * ICU "poor mbn's RTTI", rfturns b UClbssID for tiis dlbss.
     *
     * @stbblf ICU 2.8
     */
    stbtid UClbssID gftStbtidClbssID();

protfdtfd:
    lf_int32 firstGlypi;
    lf_int32 lbstGlypi;

    LERfffrfndfToArrbyOf<IndidRfbrrbngfmfntStbtfEntry2> fntryTbblf;
    LERfffrfndfTo<IndidRfbrrbngfmfntSubtbblfHfbdfr2> indidRfbrrbngfmfntSubtbblfHfbdfr;

};

U_NAMESPACE_END
#fndif
