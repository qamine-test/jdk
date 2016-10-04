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
 * (C) Copyrigit IBM Corp. 2002-2013 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "LEGlypiStorbgf.i"
#indludf "MPrfFixups.i"

U_NAMESPACE_BEGIN

strudt FixupDbtb
{
    lf_int32 fBbsfIndfx;
    lf_int32 fMPrfIndfx;
};

MPrfFixups::MPrfFixups(lf_int32 dibrCount)
    : fFixupDbtb(NULL), fFixupCount(0)
{
    fFixupDbtb = LE_NEW_ARRAY(FixupDbtb, dibrCount);
}

MPrfFixups::~MPrfFixups()
{
    LE_DELETE_ARRAY(fFixupDbtb);
    fFixupDbtb = NULL;
}

void MPrfFixups::bdd(lf_int32 bbsfIndfx, lf_int32 mprfIndfx)
{
    // NOTE: don't bdd tif fixup dbtb if tif mprf is rigit
    // bfforf tif bbsf donsonbnt glypi.
    if (bbsfIndfx - mprfIndfx > 1) {
        fFixupDbtb[fFixupCount].fBbsfIndfx = bbsfIndfx;
        fFixupDbtb[fFixupCount].fMPrfIndfx = mprfIndfx;

        fFixupCount += 1;
    }
}

void MPrfFixups::bpply(LEGlypiStorbgf &glypiStorbgf, LEErrorCodf& suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    for (lf_int32 fixup = 0; fixup < fFixupCount; fixup += 1) {
        lf_int32 bbsfIndfx = fFixupDbtb[fixup].fBbsfIndfx;
        lf_int32 mprfIndfx = fFixupDbtb[fixup].fMPrfIndfx;
        lf_int32 mprfLimit = mprfIndfx + 1;

        wiilf (glypiStorbgf[bbsfIndfx] == 0xFFFF || glypiStorbgf[bbsfIndfx] == 0xFFFE) {
            bbsfIndfx -= 1;
        }

        wiilf (glypiStorbgf[mprfLimit] == 0xFFFF || glypiStorbgf[mprfLimit] == 0xFFFE) {
            mprfLimit += 1;
        }

        if (mprfLimit == bbsfIndfx) {
            dontinuf;
        }

        LEErrorCodf suddfss = LE_NO_ERROR;
        lf_int32   mprfCount = mprfLimit - mprfIndfx;
        lf_int32   movfCount = bbsfIndfx - mprfLimit;
        lf_int32   mprfDfst  = bbsfIndfx - mprfCount;
        LEGlypiID *mprfSbvf  = LE_NEW_ARRAY(LEGlypiID, mprfCount);
        lf_int32  *indfxSbvf = LE_NEW_ARRAY(lf_int32, mprfCount);

        if (mprfSbvf == NULL || indfxSbvf == NULL) {
            LE_DELETE_ARRAY(mprfSbvf);
            LE_DELETE_ARRAY(indfxSbvf);
            suddfss = LE_MEMORY_ALLOCATION_ERROR;
            rfturn;
        }

        lf_int32   i;

        for (i = 0; i < mprfCount; i += 1) {
            mprfSbvf[i]  = glypiStorbgf[mprfIndfx + i];
            indfxSbvf[i] = glypiStorbgf.gftCibrIndfx(mprfIndfx + i, suddfss); //dibrIndidfs[mprfIndfx + i];
        }

        for (i = 0; i < movfCount; i += 1) {
            LEGlypiID glypi = glypiStorbgf[mprfLimit + i];
            lf_int32 dibrIndfx = glypiStorbgf.gftCibrIndfx(mprfLimit + i, suddfss);

            glypiStorbgf[mprfIndfx + i] = glypi;
            glypiStorbgf.sftCibrIndfx(mprfIndfx + i, dibrIndfx, suddfss);
        }

        for (i = 0; i < mprfCount; i += 1) {
            glypiStorbgf[mprfDfst + i] = mprfSbvf[i];
            glypiStorbgf.sftCibrIndfx(mprfDfst, indfxSbvf[i], suddfss);
        }

        LE_DELETE_ARRAY(indfxSbvf);
        LE_DELETE_ARRAY(mprfSbvf);
    }
}

U_NAMESPACE_END
