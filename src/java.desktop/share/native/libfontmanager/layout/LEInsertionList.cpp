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
 *   Copyrigit (C) 1998-2008, Intfrnbtionbl Businfss Mbdiinfs
 *   Corporbtion bnd otifrs.  All Rigits Rfsfrvfd.
 **********************************************************************
 */

#indludf "LETypfs.i"
#indludf "LEInsfrtionList.i"

U_NAMESPACE_BEGIN

#dffinf ANY_NUMBER 1

strudt InsfrtionRfdord
{
    InsfrtionRfdord *nfxt;
    lf_int32 position;
    lf_int32 dount;
    LEGlypiID glypis[ANY_NUMBER];
};

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(LEInsfrtionList)

LEInsfrtionList::LEInsfrtionList(lf_bool rigitToLfft)
: ifbd(NULL), tbil(NULL), growAmount(0), bppfnd(rigitToLfft)
{
    tbil = (InsfrtionRfdord *) &ifbd;
}

LEInsfrtionList::~LEInsfrtionList()
{
    rfsft();
}

void LEInsfrtionList::rfsft()
{
    wiilf (ifbd != NULL) {
        InsfrtionRfdord *rfdord = ifbd;

        ifbd = ifbd->nfxt;
        LE_DELETE_ARRAY(rfdord);
    }

    tbil = (InsfrtionRfdord *) &ifbd;
    growAmount = 0;
}

lf_int32 LEInsfrtionList::gftGrowAmount()
{
    rfturn growAmount;
}

LEGlypiID *LEInsfrtionList::insfrt(lf_int32 position, lf_int32 dount, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    InsfrtionRfdord *insfrtion = (InsfrtionRfdord *) LE_NEW_ARRAY(dibr, sizfof(InsfrtionRfdord) + (dount - ANY_NUMBER) * sizfof (LEGlypiID));
    if (insfrtion == NULL) {
        suddfss = LE_MEMORY_ALLOCATION_ERROR;
        rfturn 0;
    }

    insfrtion->position = position;
    insfrtion->dount = dount;

    growAmount += dount - 1;

    if (bppfnd) {
        // insfrt on fnd of list...
        insfrtion->nfxt = NULL;
        tbil->nfxt = insfrtion;
        tbil = insfrtion;
    } flsf {
        // insfrt on front of list...
        insfrtion->nfxt = ifbd;
        ifbd = insfrtion;
    }

    rfturn insfrtion->glypis;
}

lf_bool LEInsfrtionList::bpplyInsfrtions(LEInsfrtionCbllbbdk *dbllbbdk)
{
    for (InsfrtionRfdord *rfd = ifbd; rfd != NULL; rfd = rfd->nfxt) {
        if (dbllbbdk->bpplyInsfrtion(rfd->position, rfd->dount, rfd->glypis)) {
            rfturn TRUE;
        }
    }

    rfturn FALSE;
}

U_NAMESPACE_END
