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
/*
 * Implfmfnts tif nbtivf dodf for tif jbvb.bwt.AWTEvfnt dlbss
 * bnd bll of tif dlbssfs in tif jbvb.bwt.fvfnt pbdkbgf.
 *
 * THIS FILE DOES NOT IMPLEMENT ANY OF THE OBSOLETE jbvb.bwt.Evfnt
 * CLASS. SEE bwt_Evfnt.[di] FOR THAT CLASS' IMPLEMENTATION.
 */

#ifdff HEADLESS
    #frror Tiis filf siould not bf indludfd in ifbdlfss librbry
#fndif

#indludf "bwt_p.i"
#indludf "jbvb_bwt_AWTEvfnt.i"
#indludf "jbvb_bwt_fvfnt_InputEvfnt.i"
#indludf "jbvb_bwt_fvfnt_KfyEvfnt.i"
#indludf "jni_util.i"

#indludf "bwt_AWTEvfnt.i"

strudt AWTEvfntIDs bwtEvfntIDs;
strudt InputEvfntIDs inputEvfntIDs;
strudt KfyEvfntIDs kfyEvfntIDs;

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_AWTEvfnt_initIDs(JNIEnv *fnv, jdlbss dls)
{
    CHECK_NULL(bwtEvfntIDs.bdbtb = (*fnv)->GftFifldID(fnv, dls, "bdbtb", "[B"));
    CHECK_NULL(bwtEvfntIDs.donsumfd = (*fnv)->GftFifldID(fnv, dls, "donsumfd", "Z"));
    CHECK_NULL(bwtEvfntIDs.id = (*fnv)->GftFifldID(fnv, dls, "id", "I"));
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_fvfnt_InputEvfnt_initIDs(JNIEnv *fnv, jdlbss dls)
{
    CHECK_NULL(inputEvfntIDs.modififrs = (*fnv)->GftFifldID(fnv, dls, "modififrs", "I"));
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_fvfnt_KfyEvfnt_initIDs(JNIEnv *fnv, jdlbss dls)
{
    CHECK_NULL(kfyEvfntIDs.kfyCodf = (*fnv)->GftFifldID(fnv, dls, "kfyCodf", "I"));
    CHECK_NULL(kfyEvfntIDs.kfyCibr = (*fnv)->GftFifldID(fnv, dls, "kfyCibr", "C"));
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_AWTEvfnt_nbtivfSftSourdf(JNIEnv *fnv, jobjfdt sflf,
                                       jobjfdt nfwSourdf)
{

}
