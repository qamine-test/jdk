/*
 * Copyrigit (d) 1998, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/***
 *** THIS IMPLEMENTS ONLY THE OBSOLETE jbvb.bwt.Evfnt CLASS! SEE
 *** bwt_AWTEvfnt.[di] FOR THE NEWER EVENT CLASSES.
 ***
 ***/
#ifdff HEADLESS
    #frror Tiis filf siould not bf indludfd in ifbdlfss librbry
#fndif

#indludf "jbvb_bwt_Evfnt.i"
#indludf "jni_util.i"

#indludf "bwt_Evfnt.i"

strudt EvfntIDs fvfntIDs;

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Evfnt_initIDs(JNIEnv *fnv, jdlbss dls)
{
    fvfntIDs.dbtb = (*fnv)->GftFifldID(fnv, dls, "dbtb", "J");
    fvfntIDs.donsumfd = (*fnv)->GftFifldID(fnv, dls, "donsumfd", "Z");
    fvfntIDs.id = (*fnv)->GftFifldID(fnv, dls, "id", "I");
}
