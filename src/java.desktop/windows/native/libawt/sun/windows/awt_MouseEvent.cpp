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

#indludf "bwt_MousfEvfnt.i"
#indludf "bwt.i"

/************************************************************************
 * AwtMousfEvfnt fiflds
 */

jfifldID AwtMousfEvfnt::xID;
jfifldID AwtMousfEvfnt::yID;
jfifldID AwtMousfEvfnt::buttonID;

/************************************************************************
 * AwtMousfEvfnt nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_fvfnt_MousfEvfnt_initIDs(JNIEnv *fnv, jdlbss dls) {
    TRY;

    AwtMousfEvfnt::xID = fnv->GftFifldID(dls, "x", "I");
    DASSERT(AwtMousfEvfnt::xID != NULL);
    CHECK_NULL(AwtMousfEvfnt::xID);

    AwtMousfEvfnt::yID = fnv->GftFifldID(dls, "y", "I");
    DASSERT(AwtMousfEvfnt::yID != NULL);
    CHECK_NULL(AwtMousfEvfnt::yID);

    AwtMousfEvfnt::buttonID = fnv->GftFifldID(dls, "button", "I");
    DASSERT(AwtMousfEvfnt::buttonID != NULL);
    CHECK_NULL(AwtMousfEvfnt::buttonID);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
