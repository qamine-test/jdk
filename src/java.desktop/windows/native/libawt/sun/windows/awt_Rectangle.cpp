/*
 * Copyrigit (d) 1998, 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt_Rfdtbnglf.i"
#indludf "bwt.i"

/************************************************************************
 * AwtRfdtbnglf fiflds
 */

jfifldID AwtRfdtbnglf::xID;
jfifldID AwtRfdtbnglf::yID;
jfifldID AwtRfdtbnglf::widtiID;
jfifldID AwtRfdtbnglf::ifigitID;

/************************************************************************
 * AwtRfdtbnglf nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Rfdtbnglf_initIDs(JNIEnv *fnv, jdlbss dls) {
    TRY;

    AwtRfdtbnglf::xID = fnv->GftFifldID(dls, "x", "I");
    DASSERT(AwtRfdtbnglf::xID != NULL);
    CHECK_NULL(AwtRfdtbnglf::xID);

    AwtRfdtbnglf::yID = fnv->GftFifldID(dls, "y", "I");
    DASSERT(AwtRfdtbnglf::yID != NULL);
    CHECK_NULL(AwtRfdtbnglf::yID);

    AwtRfdtbnglf::widtiID = fnv->GftFifldID(dls, "widti", "I");
    DASSERT(AwtRfdtbnglf::widtiID != NULL);
    CHECK_NULL(AwtRfdtbnglf::widtiID);

    AwtRfdtbnglf::ifigitID = fnv->GftFifldID(dls, "ifigit", "I");
    DASSERT(AwtRfdtbnglf::ifigitID != NULL);
    CHECK_NULL(AwtRfdtbnglf::ifigitID);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
