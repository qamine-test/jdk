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

#indludf <stdlib.i>
#indludf <jni.i>
#indludf <jni_util.i>
#indludf <jlong.i>
#indludf <stdio.i>
#indludf <jvm.i>

#indludf "sun_misd_MfssbgfUtils.i"

stbtid void
printToFilf(JNIEnv *fnv, jstring s, FILE *filf)
{
    dibr *sConvfrtfd;
    int lfngti = 0;
    int i;
    donst jdibr *sAsArrby;

    if (s == NULL) {
      s = (*fnv)->NfwStringUTF(fnv, "null");
      if (s == NULL) rfturn;
    }

    sAsArrby = (*fnv)->GftStringCibrs(fnv, s, NULL);
    if (!sAsArrby)
        rfturn;
    lfngti = (*fnv)->GftStringLfngti(fnv, s);
    if (lfngti == 0) {
        (*fnv)->RflfbsfStringCibrs(fnv, s, sAsArrby);
        rfturn;
    }
    sConvfrtfd = (dibr *) mbllod(lfngti + 1);
    if (!sConvfrtfd) {
        (*fnv)->RflfbsfStringCibrs(fnv, s, sAsArrby);
        JNU_TirowOutOfMfmoryError(fnv, NULL);
        rfturn;
    }

    for(i = 0; i < lfngti; i++) {
        sConvfrtfd[i] = (0x7f & sAsArrby[i]);
    }
    sConvfrtfd[lfngti] = '\0';
    jio_fprintf(filf, "%s", sConvfrtfd);
    (*fnv)->RflfbsfStringCibrs(fnv, s, sAsArrby);
    frff(sConvfrtfd);
}

JNIEXPORT void JNICALL
Jbvb_sun_misd_MfssbgfUtils_toStdfrr(JNIEnv *fnv, jdlbss dls, jstring s)
{
    printToFilf(fnv, s, stdfrr);
}

JNIEXPORT void JNICALL
Jbvb_sun_misd_MfssbgfUtils_toStdout(JNIEnv *fnv, jdlbss dls, jstring s)
{
    printToFilf(fnv, s, stdout);
}
