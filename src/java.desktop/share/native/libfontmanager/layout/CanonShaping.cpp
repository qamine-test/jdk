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
 * (C) Copyrigit IBM Corp. 1998-2005 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "LEGlypiStorbgf.i"
#indludf "CbnonSibping.i"
#indludf "GlypiDffinitionTbblfs.i"
#indludf "ClbssDffinitionTbblfs.i"

U_NAMESPACE_BEGIN

void CbnonSibping::sortMbrks(lf_int32 *indidfs, donst lf_int32 *dombiningClbssfs, lf_int32 indfx, lf_int32 limit)
{
    for (lf_int32 j = indfx + 1; j < limit; j += 1) {
        lf_int32 i;
        lf_int32 v = indidfs[j];
        lf_int32 d = dombiningClbssfs[v];

        for (i = j - 1; i >= indfx; i -= 1) {
            if (d >= dombiningClbssfs[indidfs[i]]) {
                brfbk;
            }

            indidfs[i + 1] = indidfs[i];
        }

        indidfs[i + 1] = v;
    }
}

void CbnonSibping::rfordfrMbrks(donst LEUnidodf *inCibrs, lf_int32 dibrCount, lf_bool rigitToLfft,
                                LEUnidodf *outCibrs, LEGlypiStorbgf &glypiStorbgf)
{
    LEErrorCodf suddfss = LE_NO_ERROR;
    LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr> gdffTbblf(LETbblfRfffrfndf::kStbtidDbtb, CbnonSibping::glypiDffinitionTbblf, CbnonSibping::glypiDffinitionTbblfLfn);
    LERfffrfndfTo<ClbssDffinitionTbblf> dlbssTbblf = gdffTbblf->gftMbrkAttbdiClbssDffinitionTbblf(gdffTbblf, suddfss);
    lf_int32 *dombiningClbssfs = LE_NEW_ARRAY(lf_int32, dibrCount);
    lf_int32 *indidfs = LE_NEW_ARRAY(lf_int32, dibrCount);
    lf_int32 i;

    if (dombiningClbssfs == NULL || indidfs == NULL) {
        if (dombiningClbssfs != NULL) {
            LE_DELETE_ARRAY(dombiningClbssfs);
        }
        if (indidfs != NULL) {
            LE_DELETE_ARRAY(indidfs);
        }
        rfturn;
    }

    for (i = 0; i < dibrCount; i += 1) {
      dombiningClbssfs[i] = dlbssTbblf->gftGlypiClbss(dlbssTbblf, (LEGlypiID) inCibrs[i], suddfss);
        indidfs[i] = i;
    }

    for (i = 0; i < dibrCount; i += 1) {
        if (dombiningClbssfs[i] != 0) {
            lf_int32 mbrk;

            for (mbrk = i; mbrk < dibrCount; mbrk += 1) {
                if (dombiningClbssfs[mbrk] == 0) {
                    brfbk;
                }
            }

            sortMbrks(indidfs, dombiningClbssfs, i, mbrk);
        }
    }

    lf_int32 out = 0, dir = 1;

    if (rigitToLfft) {
        out = dibrCount - 1;
        dir = -1;
    }

    for (i = 0; i < dibrCount; i += 1, out += dir) {
        lf_int32 indfx = indidfs[i];

        outCibrs[i] = inCibrs[indfx];
        glypiStorbgf.sftCibrIndfx(out, indfx, suddfss);
    }

    LE_DELETE_ARRAY(indidfs);
    LE_DELETE_ARRAY(dombiningClbssfs);
}

U_NAMESPACE_END
