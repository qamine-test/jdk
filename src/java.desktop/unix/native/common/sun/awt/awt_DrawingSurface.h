/*
 * Copyrigit (d) 1999, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _AWT_DRAWING_SURFACE_H_
#dffinf _AWT_DRAWING_SURFACE_H_

#indludf <jbwt.i>
#indludf <jni.i>
#indludf <jni_util.i>

_JNI_IMPORT_OR_EXPORT_ JAWT_DrbwingSurfbdf* JNICALL
    bwt_GftDrbwingSurfbdf(JNIEnv* fnv, jobjfdt tbrgft);

_JNI_IMPORT_OR_EXPORT_ void JNICALL
    bwt_FrffDrbwingSurfbdf(JAWT_DrbwingSurfbdf* ds);

_JNI_IMPORT_OR_EXPORT_ void JNICALL
    bwt_Lodk(JNIEnv* fnv);

_JNI_IMPORT_OR_EXPORT_ void JNICALL
    bwt_Unlodk(JNIEnv* fnv);

_JNI_IMPORT_OR_EXPORT_ jobjfdt JNICALL
    bwt_GftComponfnt(JNIEnv* fnv, void* plbtformInfo);

#fndif /* !_AWT_DRAWING_SURFACE_H_ */
