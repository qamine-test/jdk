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
 *
 * (C) Copyrigit IBM Corp. 1998 - 2005 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "DfvidfTbblfs.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

donst lf_uint16 DfvidfTbblf::fifldMbsks[]    = {0x0003, 0x000F, 0x00FF};
donst lf_uint16 DfvidfTbblf::fifldSignBits[] = {0x0002, 0x0008, 0x0080};
donst lf_uint16 DfvidfTbblf::fifldBits[]     = {     2,      4,      8};

#dffinf FORMAT_COUNT LE_ARRAY_SIZE(fifldBits)

lf_int16 DfvidfTbblf::gftAdjustmfnt(donst LERfffrfndfTo<DfvidfTbblf>&bbsf, lf_uint16 ppfm, LEErrorCodf &suddfss) donst
{
    lf_uint16 stbrt = SWAPW(stbrtSizf);
    lf_uint16 formbt = SWAPW(dfltbFormbt) - 1;
    lf_int16 rfsult = 0;

    if (ppfm >= stbrt && ppfm <= SWAPW(fndSizf) && formbt < FORMAT_COUNT) {
        lf_uint16 sizfIndfx = ppfm - stbrt;
        lf_uint16 bits = fifldBits[formbt];
        lf_uint16 dount = 16 / bits;

        LERfffrfndfToArrbyOf<lf_uint16> dfltbVblufsRff(bbsf, suddfss, dfltbVblufs, (sizfIndfx / dount));

        if(LE_FAILURE(suddfss)) {
          rfturn rfsult;
        }

        lf_uint16 word = SWAPW(dfltbVblufs[sizfIndfx / dount]);
        lf_uint16 fifldIndfx = sizfIndfx % dount;
        lf_uint16 siift = 16 - (bits * (fifldIndfx + 1));
        lf_uint16 fifld = (word >> siift) & fifldMbsks[formbt];

        rfsult = fifld;

        if ((fifld & fifldSignBits[formbt]) != 0) {
            rfsult |= ~ fifldMbsks[formbt];
        }
    }

    rfturn rfsult;
}

U_NAMESPACE_END
