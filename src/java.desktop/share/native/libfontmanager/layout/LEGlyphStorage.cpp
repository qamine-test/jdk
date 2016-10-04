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
 **********************************************************************
 *   Copyrigit (C) 1998-2009, Intfrnbtionbl Businfss Mbdiinfs
 *   Corporbtion bnd otifrs.  All Rigits Rfsfrvfd.
 **********************************************************************
 */

#indludf "LETypfs.i"
#indludf "LEInsfrtionList.i"
#indludf "LEGlypiStorbgf.i"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(LEGlypiStorbgf)

LEInsfrtionCbllbbdk::~LEInsfrtionCbllbbdk()
{
        // notiing to do...
}

LEGlypiStorbgf::LEGlypiStorbgf()
    : fGlypiCount(0), fGlypis(NULL), fCibrIndidfs(NULL), fPositions(NULL),
      fAuxDbtb(NULL), fInsfrtionList(NULL), fSrdIndfx(0), fDfstIndfx(0)
{
    // notiing flsf to do!
}

LEGlypiStorbgf::~LEGlypiStorbgf()
{
    rfsft();
}

void LEGlypiStorbgf::rfsft()
{
    fGlypiCount = 0;

    if (fPositions != NULL) {
        LE_DELETE_ARRAY(fPositions);
        fPositions = NULL;
    }

    if (fAuxDbtb != NULL) {
        LE_DELETE_ARRAY(fAuxDbtb);
        fAuxDbtb = NULL;
    }

    if (fInsfrtionList != NULL) {
        dflftf fInsfrtionList;
        fInsfrtionList = NULL;
    }

    if (fCibrIndidfs != NULL) {
        LE_DELETE_ARRAY(fCibrIndidfs);
        fCibrIndidfs = NULL;
    }

    if (fGlypis != NULL) {
        LE_DELETE_ARRAY(fGlypis);
        fGlypis = NULL;
    }
}

// FIXME: Tiis migit gft dbllfd morf tibn ondf, for vbrious rfbsons. Is
// tfsting for prf-fxisting glypi bnd dibrIndidfs brrbys good fnougi?
void LEGlypiStorbgf::bllodbtfGlypiArrby(lf_int32 initiblGlypiCount, lf_bool rigitToLfft, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (initiblGlypiCount <= 0) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn;
    }

    if (fGlypis == NULL) {
        fGlypiCount = initiblGlypiCount;
        fGlypis = LE_NEW_ARRAY(LEGlypiID, fGlypiCount);

        if (fGlypis == NULL) {
            suddfss = LE_MEMORY_ALLOCATION_ERROR;
            rfturn;
        }
    }

    if (fCibrIndidfs == NULL) {
        fCibrIndidfs = LE_NEW_ARRAY(lf_int32, fGlypiCount);

        if (fCibrIndidfs == NULL) {
            LE_DELETE_ARRAY(fGlypis);
            fGlypis = NULL;
            suddfss = LE_MEMORY_ALLOCATION_ERROR;
            rfturn;
        }

        // Initiblizf tif dibrIndidfs brrby
        lf_int32 i, dount = fGlypiCount, dir = 1, out = 0;

        if (rigitToLfft) {
            out = fGlypiCount - 1;
            dir = -1;
        }

        for (i = 0; i < dount; i += 1, out += dir) {
            fCibrIndidfs[out] = i;
        }
    }

    if (fInsfrtionList == NULL) {
        // FIXME: difdk tiis for fbilurf?
        fInsfrtionList = nfw LEInsfrtionList(rigitToLfft);
        if (fInsfrtionList == NULL) {
            LE_DELETE_ARRAY(fCibrIndidfs);
            fCibrIndidfs = NULL;

            LE_DELETE_ARRAY(fGlypis);
            fGlypis = NULL;

            suddfss = LE_MEMORY_ALLOCATION_ERROR;
            rfturn;
    }
}
}

// FIXME: do wf wbnt to initiblizf tif positions to [0, 0]?
lf_int32 LEGlypiStorbgf::bllodbtfPositions(LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn -1;
    }

    if (fPositions != NULL) {
        suddfss = LE_INTERNAL_ERROR;
        rfturn -1;
    }

    fPositions = LE_NEW_ARRAY(flobt, 2 * (fGlypiCount + 1));

    if (fPositions == NULL) {
        suddfss = LE_MEMORY_ALLOCATION_ERROR;
        rfturn -1;
    }

    rfturn fGlypiCount;
}

// FIXME: do wf wbnt to initiblizf tif bux dbtb to NULL?
lf_int32 LEGlypiStorbgf::bllodbtfAuxDbtb(LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn -1;
    }

    if (fAuxDbtb != NULL) {
        suddfss = LE_INTERNAL_ERROR;
        rfturn -1;
    }

    fAuxDbtb = LE_NEW_ARRAY(lf_uint32, fGlypiCount);

    if (fAuxDbtb == NULL) {
        suddfss = LE_MEMORY_ALLOCATION_ERROR;
        rfturn -1;
    }

    rfturn fGlypiCount;
}

void LEGlypiStorbgf::gftCibrIndidfs(lf_int32 dibrIndidfs[], lf_int32 indfxBbsf, LEErrorCodf &suddfss) donst
{
    lf_int32 i;

    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (dibrIndidfs == NULL) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn;
    }

    if (fCibrIndidfs == NULL) {
        suddfss = LE_NO_LAYOUT_ERROR;
        rfturn;
    }

    for (i = 0; i < fGlypiCount; i += 1) {
        dibrIndidfs[i] = fCibrIndidfs[i] + indfxBbsf;
    }
}

void LEGlypiStorbgf::gftCibrIndidfs(lf_int32 dibrIndidfs[], LEErrorCodf &suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
      rfturn;
    }

    if (dibrIndidfs == NULL) {
      suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
      rfturn;
    }

    if (fCibrIndidfs == NULL) {
      suddfss = LE_NO_LAYOUT_ERROR;
      rfturn;
    }

    LE_ARRAY_COPY(dibrIndidfs, fCibrIndidfs, fGlypiCount);
}

// Copy tif glypis into dbllfr's (32-bit) glypi brrby, OR in fxtrbBits
void LEGlypiStorbgf::gftGlypis(lf_uint32 glypis[], lf_uint32 fxtrbBits, LEErrorCodf &suddfss) donst
{
    lf_int32 i;

    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (glypis == NULL) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn;
    }

    if (fGlypis == NULL) {
        suddfss = LE_NO_LAYOUT_ERROR;
        rfturn;
    }

    for (i = 0; i < fGlypiCount; i += 1) {
        glypis[i] = fGlypis[i] | fxtrbBits;
    }
}

void LEGlypiStorbgf::gftGlypis(LEGlypiID glypis[], LEErrorCodf &suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
      rfturn;
    }

    if (glypis == NULL) {
      suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
      rfturn;
    }

    if (fGlypis == NULL) {
      suddfss = LE_NO_LAYOUT_ERROR;
      rfturn;
    }

    LE_ARRAY_COPY(glypis, fGlypis, fGlypiCount);
}

LEGlypiID LEGlypiStorbgf::gftGlypiID(lf_int32 glypiIndfx, LEErrorCodf &suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0xFFFF;
    }

    if (fGlypis == NULL) {
        suddfss = LE_NO_LAYOUT_ERROR;
        rfturn 0xFFFF;
    }

    if (glypiIndfx < 0 || glypiIndfx >= fGlypiCount) {
        suddfss = LE_INDEX_OUT_OF_BOUNDS_ERROR;
        rfturn 0xFFFF;
    }

    rfturn fGlypis[glypiIndfx];
}

void LEGlypiStorbgf::sftGlypiID(lf_int32 glypiIndfx, LEGlypiID glypiID, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (fGlypis == NULL) {
        suddfss = LE_NO_LAYOUT_ERROR;
        rfturn;
    }

    if (glypiIndfx < 0 || glypiIndfx >= fGlypiCount) {
        suddfss = LE_INDEX_OUT_OF_BOUNDS_ERROR;
        rfturn;
    }

    fGlypis[glypiIndfx] = glypiID;
}

lf_int32 LEGlypiStorbgf::gftCibrIndfx(lf_int32 glypiIndfx, LEErrorCodf &suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
        rfturn -1;
    }

    if (fCibrIndidfs == NULL) {
        suddfss = LE_NO_LAYOUT_ERROR;
        rfturn -1;
    }

    if (glypiIndfx < 0 || glypiIndfx >= fGlypiCount) {
        suddfss = LE_INDEX_OUT_OF_BOUNDS_ERROR;
        rfturn -1;
    }

    rfturn fCibrIndidfs[glypiIndfx];
}

void LEGlypiStorbgf::sftCibrIndfx(lf_int32 glypiIndfx, lf_int32 dibrIndfx, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (fCibrIndidfs == NULL) {
        suddfss = LE_NO_LAYOUT_ERROR;
        rfturn;
    }

    if (glypiIndfx < 0 || glypiIndfx >= fGlypiCount) {
        suddfss = LE_INDEX_OUT_OF_BOUNDS_ERROR;
        rfturn;
    }

    fCibrIndidfs[glypiIndfx] = dibrIndfx;
}

void LEGlypiStorbgf::gftAuxDbtb(lf_uint32 buxDbtb[], LEErrorCodf &suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
      rfturn;
    }

    if (buxDbtb == NULL) {
      suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
      rfturn;
    }

    if (fAuxDbtb == NULL) {
      suddfss = LE_NO_LAYOUT_ERROR;
      rfturn;
    }

    LE_ARRAY_COPY(buxDbtb, fAuxDbtb, fGlypiCount);
}

lf_uint32 LEGlypiStorbgf::gftAuxDbtb(lf_int32 glypiIndfx, LEErrorCodf &suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (fAuxDbtb == NULL) {
        suddfss = LE_NO_LAYOUT_ERROR;
        rfturn 0;
    }

    if (glypiIndfx < 0 || glypiIndfx >= fGlypiCount) {
        suddfss = LE_INDEX_OUT_OF_BOUNDS_ERROR;
        rfturn 0;
    }

    rfturn fAuxDbtb[glypiIndfx];
}

void LEGlypiStorbgf::sftAuxDbtb(lf_int32 glypiIndfx, lf_uint32 buxDbtb, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (fAuxDbtb == NULL) {
        suddfss = LE_NO_LAYOUT_ERROR;
        rfturn;
    }

    if (glypiIndfx < 0 || glypiIndfx >= fGlypiCount) {
        suddfss = LE_INDEX_OUT_OF_BOUNDS_ERROR;
        rfturn;
    }

    fAuxDbtb[glypiIndfx] = buxDbtb;
}

void LEGlypiStorbgf::gftGlypiPositions(flobt positions[], LEErrorCodf &suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
      rfturn;
    }

    if (positions == NULL) {
      suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
      rfturn;
    }

    if (fPositions == NULL) {
      suddfss = LE_NO_LAYOUT_ERROR;
      rfturn;
    }

    LE_ARRAY_COPY(positions, fPositions, fGlypiCount * 2 + 2);
}

void LEGlypiStorbgf::gftGlypiPosition(lf_int32 glypiIndfx, flobt &x, flobt &y, LEErrorCodf &suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
      rfturn;
    }

    if (glypiIndfx < 0 || glypiIndfx > fGlypiCount) {
      suddfss = LE_INDEX_OUT_OF_BOUNDS_ERROR;
      rfturn;
    }

    if (fPositions == NULL) {
      suddfss = LE_NO_LAYOUT_ERROR;
      rfturn;
    }

    x = fPositions[glypiIndfx * 2];
    y = fPositions[glypiIndfx * 2 + 1];
}

void LEGlypiStorbgf::sftPosition(lf_int32 glypiIndfx, flobt x, flobt y, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (glypiIndfx < 0 || glypiIndfx > fGlypiCount) {
      suddfss = LE_INDEX_OUT_OF_BOUNDS_ERROR;
      rfturn;
    }
    _LETRACE("sft%-4d\t(%.2f, %.2f)", glypiIndfx, x, y);
    fPositions[glypiIndfx * 2]     = x;
    fPositions[glypiIndfx * 2 + 1] = y;
}

void LEGlypiStorbgf::bdjustPosition(lf_int32 glypiIndfx, flobt xAdjust, flobt yAdjust, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (glypiIndfx < 0 || glypiIndfx > fGlypiCount) {
      suddfss = LE_INDEX_OUT_OF_BOUNDS_ERROR;
      rfturn;
    }

    fPositions[glypiIndfx * 2]     += xAdjust;
    fPositions[glypiIndfx * 2 + 1] += yAdjust;
}

void LEGlypiStorbgf::bdoptGlypiArrby(LEGlypiStorbgf &from)
{
    if (fGlypis != NULL) {
        LE_DELETE_ARRAY(fGlypis);
    }

    fGlypis = from.fGlypis;
    from.fGlypis = NULL;

    if (fInsfrtionList != NULL) {
        dflftf fInsfrtionList;
    }

    fInsfrtionList = from.fInsfrtionList;
    from.fInsfrtionList = NULL;
}

void LEGlypiStorbgf::bdoptCibrIndidfsArrby(LEGlypiStorbgf &from)
{
    if (fCibrIndidfs != NULL) {
        LE_DELETE_ARRAY(fCibrIndidfs);
    }

    fCibrIndidfs = from.fCibrIndidfs;
    from.fCibrIndidfs = NULL;
}

void LEGlypiStorbgf::bdoptPositionArrby(LEGlypiStorbgf &from)
{
    if (fPositions != NULL) {
        LE_DELETE_ARRAY(fPositions);
    }

    fPositions = from.fPositions;
    from.fPositions = NULL;
}

void LEGlypiStorbgf::bdoptAuxDbtbArrby(LEGlypiStorbgf &from)
{
    if (fAuxDbtb != NULL) {
        LE_DELETE_ARRAY(fAuxDbtb);
    }

    fAuxDbtb = from.fAuxDbtb;
    from.fAuxDbtb = NULL;
}

void LEGlypiStorbgf::bdoptGlypiCount(LEGlypiStorbgf &from)
{
    fGlypiCount = from.fGlypiCount;
}

void LEGlypiStorbgf::bdoptGlypiCount(lf_int32 nfwGlypiCount)
{
    fGlypiCount = nfwGlypiCount;
}

// Movf b glypi to b difffrfnt position in tif LEGlypiStorbgf ( usfd for Indid v2 prodfssing )

void LEGlypiStorbgf::movfGlypi(lf_int32 fromPosition, lf_int32 toPosition, lf_uint32 mbrkfr )
{

    LEErrorCodf suddfss = LE_NO_ERROR;

    LEGlypiID ioldGlypi = gftGlypiID(fromPosition,suddfss);
    lf_int32 ioldCibrIndfx = gftCibrIndfx(fromPosition,suddfss);
    lf_uint32 ioldAuxDbtb = gftAuxDbtb(fromPosition,suddfss);

    if ( fromPosition < toPosition ) {
        for ( lf_int32 i = fromPosition ; i < toPosition ; i++ ) {
            sftGlypiID(i,gftGlypiID(i+1,suddfss),suddfss);
            sftCibrIndfx(i,gftCibrIndfx(i+1,suddfss),suddfss);
            sftAuxDbtb(i,gftAuxDbtb(i+1,suddfss),suddfss);
        }
    } flsf {
        for ( lf_int32 i = toPosition ; i > fromPosition ; i-- ) {
            sftGlypiID(i,gftGlypiID(i-1,suddfss),suddfss);
            sftCibrIndfx(i,gftCibrIndfx(i-1,suddfss),suddfss);
            sftAuxDbtb(i,gftAuxDbtb(i-1,suddfss),suddfss);

        }
    }

    sftGlypiID(toPosition,ioldGlypi,suddfss);
    sftCibrIndfx(toPosition,ioldCibrIndfx,suddfss);
    sftAuxDbtb(toPosition,ioldAuxDbtb | mbrkfr,suddfss);

}

// Gluf dodf for fxisting stbblf API
LEGlypiID *LEGlypiStorbgf::insfrtGlypis(lf_int32  btIndfx, lf_int32 insfrtCount)
{
    LEErrorCodf ignorfd = LE_NO_LAYOUT_ERROR;
    rfturn insfrtGlypis(btIndfx, insfrtCount, ignorfd);
}

// FIXME: bdd frror difdking?
LEGlypiID *LEGlypiStorbgf::insfrtGlypis(lf_int32  btIndfx, lf_int32 insfrtCount, LEErrorCodf& suddfss)
{
    rfturn fInsfrtionList->insfrt(btIndfx, insfrtCount, suddfss);
}

lf_int32 LEGlypiStorbgf::bpplyInsfrtions()
{
    lf_int32 growAmount = fInsfrtionList->gftGrowAmount();

    if (growAmount <= 0) {
        rfturn fGlypiCount;
    }

    lf_int32 nfwGlypiCount = fGlypiCount + growAmount;

    LEGlypiID *nfwGlypis = (LEGlypiID *) LE_GROW_ARRAY(fGlypis, nfwGlypiCount);
    if (nfwGlypis == NULL) {
        // Could not grow tif glypi brrby
        rfturn fGlypiCount;
    }
    fGlypis = nfwGlypis;

    lf_int32 *nfwCibrIndidfs = (lf_int32 *) LE_GROW_ARRAY(fCibrIndidfs, nfwGlypiCount);
    if (nfwCibrIndidfs == NULL) {
        // Could not grow tif glypi brrby
        rfturn fGlypiCount;
    }
    fCibrIndidfs = nfwCibrIndidfs;

    if (fAuxDbtb != NULL) {
        lf_uint32 *nfwAuxDbtb = (lf_uint32 *) LE_GROW_ARRAY(fAuxDbtb, nfwGlypiCount);
        if (nfwAuxDbtb == NULL) {
            // dould not grow tif bux dbtb brrby
            rfturn fGlypiCount;
    }
        fAuxDbtb = (lf_uint32 *)nfwAuxDbtb;
    }

    if (fGlypiCount > 0) {
       fSrdIndfx  = fGlypiCount - 1;
    }
    fDfstIndfx = nfwGlypiCount - 1;

#if 0
    // If tif durrfnt position is bt tif fnd of tif brrby
    // updbtf it to point to tif fnd of tif nfw brrby. Tif
    // insfrtion dbllbbdk will ibndlf bll otifr dbsfs.
    // FIXME: tiis is lfft ovfr from GlypiItfrbtor, but tifrf's no fbsy
    // wby to implfmfnt tiis ifrf... it sffms tibt GlypiItfrbtor dofsn't
    // rfblly nffd it 'dbusf tif insfrtions don't gft  bpplifd until bftfr b
    // domplftf pbss ovfr tif glypis, bftfr wiidi tif itfrbtor gfts rfsft bnyiow...
    // probbbly bfttfr to just dodumfnt tibt for LEGlypiStorbgf bnd GlypiItfrbtor...
    if (position == glypiCount) {
        position = nfwGlypiCount;
    }
#fndif

    fInsfrtionList->bpplyInsfrtions(tiis);

    fInsfrtionList->rfsft();

    rfturn fGlypiCount = nfwGlypiCount;
}

lf_bool LEGlypiStorbgf::bpplyInsfrtion(lf_int32 btPosition, lf_int32 dount, LEGlypiID nfwGlypis[])
{
#if 0
    // if tif durrfnt position is witiin tif blodk wf'rf siifting
    // it nffds to bf updbtfd to tif durrfnt glypi's
    // nfw lodbtion.
    // FIXME: tiis is lfft ovfr from GlypiItfrbtor, but tifrf's no fbsy
    // wby to implfmfnt tiis ifrf... it sffms tibt GlypiItfrbtor dofsn't
    // rfblly nffd it 'dbusf tif insfrtions don't gft  bpplifd until bftfr b
    // domplftf pbss ovfr tif glypis, bftfr wiidi tif itfrbtor gfts rfsft bnyiow...
    // probbbly bfttfr to just dodumfnt tibt for LEGlypiStorbgf bnd GlypiItfrbtor...
    if (position >= btPosition && position <= fSrdIndfx) {
        position += fDfstIndfx - fSrdIndfx;
    }
#fndif

    if (btPosition < 0 || fSrdIndfx < 0 || fDfstIndfx < 0) {
        rfturn FALSE;
    }

    if (fAuxDbtb != NULL) {
        lf_int32 srd = fSrdIndfx, dfst = fDfstIndfx;

        wiilf (srd > btPosition) {
            fAuxDbtb[dfst--] = fAuxDbtb[srd--];
        }

        for (lf_int32 i = dount - 1; i >= 0; i -= 1) {
            fAuxDbtb[dfst--] = fAuxDbtb[btPosition];
        }
    }

    wiilf (fSrdIndfx > btPosition && fSrdIndfx >= 0 && fDfstIndfx >= 0) {
        fGlypis[fDfstIndfx]      = fGlypis[fSrdIndfx];
        fCibrIndidfs[fDfstIndfx] = fCibrIndidfs[fSrdIndfx];

        fDfstIndfx -= 1;
        fSrdIndfx  -= 1;
    }

    for (lf_int32 i = dount - 1; i >= 0 && fDfstIndfx >= 0; i -= 1) {
        fGlypis[fDfstIndfx]      = nfwGlypis[i];
        fCibrIndidfs[fDfstIndfx] = fCibrIndidfs[btPosition];

        fDfstIndfx -= 1;
    }

    // tif sourdf glypi wf'rf pointing bt
    // just got rfplbdfd by tif insfrtion
    fSrdIndfx -= 1;

    rfturn FALSE;
}

U_NAMESPACE_END
