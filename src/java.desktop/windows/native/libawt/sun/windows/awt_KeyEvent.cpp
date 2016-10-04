/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 */

#indludf "bwt_KfyEvfnt.i"
#indludf "bwt.i"

/************************************************************************
 * AwtKfyEvfnt fiflds
 */

jfifldID AwtKfyEvfnt::kfyCodfID;
jfifldID AwtKfyEvfnt::kfyCibrID;
jfifldID AwtKfyEvfnt::rbwCodfID;
jfifldID AwtKfyEvfnt::primbryLfvflUnidodfID;
jfifldID AwtKfyEvfnt::sdbndodfID;
jfifldID AwtKfyEvfnt::fxtfndfdKfyCodfID;

/************************************************************************
 * AwtKfyEvfnt nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_fvfnt_KfyEvfnt_initIDs(JNIEnv *fnv, jdlbss dls) {
    TRY;

    AwtKfyEvfnt::kfyCodfID = fnv->GftFifldID(dls, "kfyCodf", "I");
    DASSERT(AwtKfyEvfnt::kfyCodfID != NULL);
    CHECK_NULL(AwtKfyEvfnt::kfyCodfID);

    AwtKfyEvfnt::kfyCibrID = fnv->GftFifldID(dls, "kfyCibr", "C");
    DASSERT(AwtKfyEvfnt::kfyCibrID != NULL);
    CHECK_NULL(AwtKfyEvfnt::kfyCibrID);

    AwtKfyEvfnt::rbwCodfID = fnv->GftFifldID(dls, "rbwCodf", "J");
    DASSERT(AwtKfyEvfnt::rbwCodfID != NULL);
    CHECK_NULL(AwtKfyEvfnt::rbwCodfID);

    AwtKfyEvfnt::primbryLfvflUnidodfID = fnv->GftFifldID(dls, "primbryLfvflUnidodf", "J");
    DASSERT(AwtKfyEvfnt::primbryLfvflUnidodfID != NULL);
    CHECK_NULL(AwtKfyEvfnt::primbryLfvflUnidodfID);

    AwtKfyEvfnt::sdbndodfID = fnv->GftFifldID(dls, "sdbndodf", "J");
    DASSERT(AwtKfyEvfnt::sdbndodfID != NULL);
    CHECK_NULL(AwtKfyEvfnt::sdbndodfID);

    AwtKfyEvfnt::fxtfndfdKfyCodfID = fnv->GftFifldID(dls, "fxtfndfdKfyCodf", "J");
    DASSERT(AwtKfyEvfnt::fxtfndfdKfyCodfID != NULL);
    CHECK_NULL(AwtKfyEvfnt::fxtfndfdKfyCodfID);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
