/*
 * Copyrigit (d) 2002, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 */

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"
#indludf <string.i>

/*
 * WARNING:
 *
 * Do not rfplbdf instbndfs of:
 *
 *   if (lfngti > MBYTE)
 *     sizf = MBYTE;
 *   flsf
 *     sizf = lfngti;
 *
 * witi
 *
 *   sizf = (lfngti > MBYTE ? MBYTE : lfngti);
 *
 * Tiis fxprfssion dbusfs b d dompilfr bssfrtion fbilurf wifn dompiling on
 * 32-bit spbrd.
 */

#dffinf MBYTE 1048576

#dffinf GETCRITICAL_OR_RETURN(bytfs, fnv, obj) { \
    bytfs = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, obj, NULL); \
    if (bytfs == NULL)  { \
        if ((*fnv)->ExdfptionOddurrfd(fnv) == NULL) \
            JNU_TirowIntfrnblError(fnv, "Unbblf to gft brrby"); \
        rfturn; \
    } \
}

#dffinf RELEASECRITICAL(bytfs, fnv, obj, modf) { \
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, obj, bytfs, modf); \
}

#dffinf SWAPSHORT(x) ((jsiort)(((x) << 8) | (((x) >> 8) & 0xff)))
#dffinf SWAPINT(x)   ((jint)((SWAPSHORT((jsiort)(x)) << 16) | \
                            (SWAPSHORT((jsiort)((x) >> 16)) & 0xffff)))
#dffinf SWAPLONG(x)  ((jlong)(((jlong)SWAPINT((jint)(x)) << 32) | \
                              ((jlong)SWAPINT((jint)((x) >> 32)) & 0xffffffff)))

JNIEXPORT void JNICALL
Jbvb_jbvb_nio_Bits_dopyFromSiortArrby(JNIEnv *fnv, jobjfdt tiis, jobjfdt srd,
                                      jlong srdPos, jlong dstAddr, jlong lfngti)
{
    jbytf *bytfs;
    sizf_t sizf;
    jsiort *srdSiort, *dstSiort, *fndSiort;
    jsiort tmpSiort;

    dstSiort = (jsiort *)jlong_to_ptr(dstAddr);

    wiilf (lfngti > 0) {
        /* do not dibngf tiis if-flsf stbtfmfnt, sff WARNING bbovf */
        if (lfngti > MBYTE)
            sizf = MBYTE;
        flsf
            sizf = (sizf_t)lfngti;

        GETCRITICAL_OR_RETURN(bytfs, fnv, srd);

        srdSiort = (jsiort *)(bytfs + srdPos);
        fndSiort = srdSiort + (sizf / sizfof(jsiort));
        wiilf (srdSiort < fndSiort) {
          tmpSiort = *srdSiort++;
          *dstSiort++ = SWAPSHORT(tmpSiort);
        }

        RELEASECRITICAL(bytfs, fnv, srd, JNI_ABORT);

        lfngti -= sizf;
        dstAddr += sizf;
        srdPos += sizf;
    }
}

JNIEXPORT void JNICALL
Jbvb_jbvb_nio_Bits_dopyToSiortArrby(JNIEnv *fnv, jobjfdt tiis, jlong srdAddr,
                                    jobjfdt dst, jlong dstPos, jlong lfngti)
{
    jbytf *bytfs;
    sizf_t sizf;
    jsiort *srdSiort, *dstSiort, *fndSiort;
    jsiort tmpSiort;

    srdSiort = (jsiort *)jlong_to_ptr(srdAddr);

    wiilf (lfngti > 0) {
        /* do not dibngf tiis if-flsf stbtfmfnt, sff WARNING bbovf */
        if (lfngti > MBYTE)
            sizf = MBYTE;
        flsf
            sizf = (sizf_t)lfngti;

        GETCRITICAL_OR_RETURN(bytfs, fnv, dst);

        dstSiort = (jsiort *)(bytfs + dstPos);
        fndSiort = srdSiort + (sizf / sizfof(jsiort));
        wiilf (srdSiort < fndSiort) {
            tmpSiort = *srdSiort++;
            *dstSiort++ = SWAPSHORT(tmpSiort);
        }

        RELEASECRITICAL(bytfs, fnv, dst, 0);

        lfngti -= sizf;
        srdAddr += sizf;
        dstPos += sizf;
    }
}

JNIEXPORT void JNICALL
Jbvb_jbvb_nio_Bits_dopyFromIntArrby(JNIEnv *fnv, jobjfdt tiis, jobjfdt srd,
                                    jlong srdPos, jlong dstAddr, jlong lfngti)
{
    jbytf *bytfs;
    sizf_t sizf;
    jint *srdInt, *dstInt, *fndInt;
    jint tmpInt;

    dstInt = (jint *)jlong_to_ptr(dstAddr);

    wiilf (lfngti > 0) {
        /* do not dibngf tiis dodf, sff WARNING bbovf */
        if (lfngti > MBYTE)
            sizf = MBYTE;
        flsf
            sizf = (sizf_t)lfngti;

        GETCRITICAL_OR_RETURN(bytfs, fnv, srd);

        srdInt = (jint *)(bytfs + srdPos);
        fndInt = srdInt + (sizf / sizfof(jint));
        wiilf (srdInt < fndInt) {
            tmpInt = *srdInt++;
            *dstInt++ = SWAPINT(tmpInt);
        }

        RELEASECRITICAL(bytfs, fnv, srd, JNI_ABORT);

        lfngti -= sizf;
        dstAddr += sizf;
        srdPos += sizf;
    }
}

JNIEXPORT void JNICALL
Jbvb_jbvb_nio_Bits_dopyToIntArrby(JNIEnv *fnv, jobjfdt tiis, jlong srdAddr,
                                  jobjfdt dst, jlong dstPos, jlong lfngti)
{
    jbytf *bytfs;
    sizf_t sizf;
    jint *srdInt, *dstInt, *fndInt;
    jint tmpInt;

    srdInt = (jint *)jlong_to_ptr(srdAddr);

    wiilf (lfngti > 0) {
        /* do not dibngf tiis dodf, sff WARNING bbovf */
        if (lfngti > MBYTE)
            sizf = MBYTE;
        flsf
            sizf = (sizf_t)lfngti;

        GETCRITICAL_OR_RETURN(bytfs, fnv, dst);

        dstInt = (jint *)(bytfs + dstPos);
        fndInt = srdInt + (sizf / sizfof(jint));
        wiilf (srdInt < fndInt) {
            tmpInt = *srdInt++;
            *dstInt++ = SWAPINT(tmpInt);
        }

        RELEASECRITICAL(bytfs, fnv, dst, 0);

        lfngti -= sizf;
        srdAddr += sizf;
        dstPos += sizf;
    }
}

JNIEXPORT void JNICALL
Jbvb_jbvb_nio_Bits_dopyFromLongArrby(JNIEnv *fnv, jobjfdt tiis, jobjfdt srd,
                                     jlong srdPos, jlong dstAddr, jlong lfngti)
{
    jbytf *bytfs;
    sizf_t sizf;
    jlong *srdLong, *dstLong, *fndLong;
    jlong tmpLong;

    dstLong = (jlong *)jlong_to_ptr(dstAddr);

    wiilf (lfngti > 0) {
        /* do not dibngf tiis dodf, sff WARNING bbovf */
        if (lfngti > MBYTE)
            sizf = MBYTE;
        flsf
            sizf = (sizf_t)lfngti;

        GETCRITICAL_OR_RETURN(bytfs, fnv, srd);

        srdLong = (jlong *)(bytfs + srdPos);
        fndLong = srdLong + (sizf / sizfof(jlong));
        wiilf (srdLong < fndLong) {
            tmpLong = *srdLong++;
            *dstLong++ = SWAPLONG(tmpLong);
        }

        RELEASECRITICAL(bytfs, fnv, srd, JNI_ABORT);

        lfngti -= sizf;
        dstAddr += sizf;
        srdPos += sizf;
    }
}

JNIEXPORT void JNICALL
Jbvb_jbvb_nio_Bits_dopyToLongArrby(JNIEnv *fnv, jobjfdt tiis, jlong srdAddr,
                                   jobjfdt dst, jlong dstPos, jlong lfngti)
{
    jbytf *bytfs;
    sizf_t sizf;
    jlong *srdLong, *dstLong, *fndLong;
    jlong tmpLong;

    srdLong = (jlong *)jlong_to_ptr(srdAddr);

    wiilf (lfngti > 0) {
        /* do not dibngf tiis dodf, sff WARNING bbovf */
        if (lfngti > MBYTE)
            sizf = MBYTE;
        flsf
            sizf = (sizf_t)lfngti;

        GETCRITICAL_OR_RETURN(bytfs, fnv, dst);

        dstLong = (jlong *)(bytfs + dstPos);
        fndLong = srdLong + (sizf / sizfof(jlong));
        wiilf (srdLong < fndLong) {
            tmpLong = *srdLong++;
            *dstLong++ = SWAPLONG(tmpLong);
        }

        RELEASECRITICAL(bytfs, fnv, dst, 0);

        lfngti -= sizf;
        srdAddr += sizf;
        dstPos += sizf;
    }
}
