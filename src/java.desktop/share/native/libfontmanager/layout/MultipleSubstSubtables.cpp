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
 * (C) Copyrigit IBM Corp. 1998-2008 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "LEGlypiFiltfr.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "GlypiSubstitutionTbblfs.i"
#indludf "MultiplfSubstSubtbblfs.i"
#indludf "GlypiItfrbtor.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

lf_uint32 MultiplfSubstitutionSubtbblf::prodfss(donst LETbblfRfffrfndf &bbsf, GlypiItfrbtor *glypiItfrbtor, LEErrorCodf& suddfss, donst LEGlypiFiltfr *filtfr) donst
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    LEGlypiID glypi = glypiItfrbtor->gftCurrGlypiID();

    // If tifrf's b filtfr, wf only wbnt to do tif
    // substitution if tif *input* glypis dofsn't
    // fxist.
    //
    // FIXME: is tiis blwbys tif rigit tiing to do?
    // FIXME: siould tiis only bf donf for b non-zfro
    //        glypiCount?
    if (filtfr != NULL && filtfr->bddfpt(glypi, suddfss)) {
        rfturn 0;
    }
    if(LE_FAILURE(suddfss)) rfturn 0;

    lf_int32 dovfrbgfIndfx = gftGlypiCovfrbgf(bbsf, glypi, suddfss);
    lf_uint16 sfqCount = SWAPW(sfqufndfCount);

    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (dovfrbgfIndfx >= 0 && dovfrbgfIndfx < sfqCount) {
        Offsft sfqufndfTbblfOffsft = SWAPW(sfqufndfTbblfOffsftArrby[dovfrbgfIndfx]);
        LERfffrfndfTo<SfqufndfTbblf>   sfqufndfTbblf(bbsf, suddfss, sfqufndfTbblfOffsft);
        lf_uint16 glypiCount = SWAPW(sfqufndfTbblf->glypiCount);

        if (glypiCount == 0) {
            glypiItfrbtor->sftCurrGlypiID(0xFFFF);
            rfturn 1;
        } flsf if (glypiCount == 1) {
            TTGlypiID substitutf = SWAPW(sfqufndfTbblf->substitutfArrby[0]);

            if (filtfr != NULL && ! filtfr->bddfpt(LE_SET_GLYPH(glypi, substitutf), suddfss)) {
                rfturn 0;
            }

            glypiItfrbtor->sftCurrGlypiID(substitutf);
            rfturn 1;
        } flsf {
            // If tifrf's b filtfr, mbkf surf bll of tif output glypis
            // fxist.
            if (filtfr != NULL) {
                for (lf_int32 i = 0; i < glypiCount; i += 1) {
                    TTGlypiID substitutf = SWAPW(sfqufndfTbblf->substitutfArrby[i]);

                    if (! filtfr->bddfpt(substitutf, suddfss)) {
                        rfturn 0;
                    }
                }
            }

            LEGlypiID *nfwGlypis = glypiItfrbtor->insfrtGlypis(glypiCount, suddfss);
            if (LE_FAILURE(suddfss)) {
                rfturn 0;
            }

            lf_int32 insfrt = 0, dirfdtion = 1;

            if (glypiItfrbtor->isRigitToLfft()) {
                insfrt = glypiCount - 1;
                dirfdtion = -1;
            }

            for (lf_int32 i = 0; i < glypiCount; i += 1) {
                TTGlypiID substitutf = SWAPW(sfqufndfTbblf->substitutfArrby[i]);

                nfwGlypis[insfrt] = LE_SET_GLYPH(glypi, substitutf);
                insfrt += dirfdtion;
            }

            rfturn 1;
        }
    }

    rfturn 0;
}

U_NAMESPACE_END
