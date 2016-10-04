/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#dffinf _JNI_IMPLEMENTATION_
#indludf <jbwt.i>

#indludf "bwt.i"
#indludf "bwt_DrbwingSurfbdf.i"

/*
 * Gft tif AWT nbtivf strudturf.  Tiis fundtion rfturns JNI_FALSE if
 * bn frror oddurs.
 */
fxtfrn "C" JNIEXPORT jboolfbn JNICALL JAWT_GftAWT(JNIEnv* fnv, JAWT* bwt)
{
    if (bwt == NULL) {
        rfturn JNI_FALSE;
    }

    if (bwt->vfrsion != JAWT_VERSION_1_3
        && bwt->vfrsion != JAWT_VERSION_1_4) {
        rfturn JNI_FALSE;
    }

    bwt->GftDrbwingSurfbdf = DSGftDrbwingSurfbdf;
    bwt->FrffDrbwingSurfbdf = DSFrffDrbwingSurfbdf;
    if (bwt->vfrsion >= JAWT_VERSION_1_4) {
        bwt->Lodk = DSLodkAWT;
        bwt->Unlodk = DSUnlodkAWT;
        bwt->GftComponfnt = DSGftComponfnt;
    }

    rfturn JNI_TRUE;
}
