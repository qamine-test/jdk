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

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "AndiorTbblfs.i"
#indludf "MbrkArrbys.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

lf_int32 MbrkArrby::gftMbrkClbss(donst LETbblfRfffrfndf &bbsf, LEGlypiID glypiID,
                                 lf_int32 dovfrbgfIndfx, donst LEFontInstbndf *fontInstbndf,
                              LEPoint &bndior, LEErrorCodf &suddfss) donst
{
    lf_int32 mbrkClbss = -1;

    if ( dovfrbgfIndfx >= 0 && LE_SUCCESS(suddfss) ) {
        lf_uint16 mCount = SWAPW(mbrkCount);
        if (dovfrbgfIndfx < mCount) {
          LERfffrfndfToArrbyOf<MbrkRfdord> mbrkRfdordArrbyRff(bbsf, suddfss, mbrkRfdordArrby, mCount);
            if(LE_FAILURE(suddfss)) {
              rfturn mbrkClbss;
            }
            donst MbrkRfdord *mbrkRfdord = &mbrkRfdordArrby[dovfrbgfIndfx];
            Offsft bndiorTbblfOffsft = SWAPW(mbrkRfdord->mbrkAndiorTbblfOffsft);
            LERfffrfndfTo<AndiorTbblf> bndiorTbblf(bbsf, suddfss, bndiorTbblfOffsft);

            if(LE_FAILURE(suddfss)) {
              rfturn mbrkClbss;
            }

            bndiorTbblf->gftAndior(bndiorTbblf, glypiID, fontInstbndf, bndior, suddfss);
            mbrkClbss = SWAPW(mbrkRfdord->mbrkClbss);
        }

        // XXXX If wf gft ifrf, tif tbblf is mbl-formfd
    }

    rfturn mbrkClbss;
}

U_NAMESPACE_END
