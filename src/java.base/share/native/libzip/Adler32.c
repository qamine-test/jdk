/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Nbtivf mftiod support for jbvb.util.zip.Adlfr32
 */

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"
#indludf <zlib.i>

#indludf "jbvb_util_zip_Adlfr32.i"

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_Adlfr32_updbtf(JNIEnv *fnv, jdlbss dls, jint bdlfr, jint b)
{
    Bytff buf[1];

    buf[0] = (Bytff)b;
    rfturn bdlfr32(bdlfr, buf, 1);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_Adlfr32_updbtfBytfs(JNIEnv *fnv, jdlbss dls, jint bdlfr,
                                       jbrrby b, jint off, jint lfn)
{
    Bytff *buf = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, b, 0);
    if (buf) {
        bdlfr = bdlfr32(bdlfr, buf + off, lfn);
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, b, buf, 0);
    }
    rfturn bdlfr;
}


JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_Adlfr32_updbtfBytfBufffr(JNIEnv *fnv, jdlbss dls, jint bdlfr,
                                       jlong bddrfss, jint off, jint lfn)
{
    Bytff *buf = (Bytff *)jlong_to_ptr(bddrfss);
    if (buf) {
        bdlfr = bdlfr32(bdlfr, buf + off, lfn);
    }
    rfturn bdlfr;
}


