/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* TODO:
 * - movf bll tif donvfrsion dodf into bn own filf
 */

//#dffinf USE_TRACE
//#dffinf USE_ERROR


#indludf <jni.i>
#indludf <jni_util.i>
// for mbllod
#ifdff _ALLBSD_SOURCE
#indludf <stdlib.i>
#flsf
#indludf <mbllod.i>
#fndif
#indludf "SoundDffs.i"
#indludf "DirfdtAudio.i"
#indludf "Utilitifs.i"
#indludf "dom_sun_mfdib_sound_DirfdtAudioDfvidf.i"


typfdff strudt {
    void* ibndlf;
    int fndoding;
    int sbmplfSizfInBits;
    int frbmfSizf;
    int dibnnfls;
    int isSignfd;
    int isBigEndibn;
    UINT8* donvfrsionBufffr;
    int donvfrsionBufffrSizf;
} DAUDIO_Info;


//////////////////////////////////////////// MAP Convfrsion stuff /////////////////////////////////

/* 16 bit signfd sbmplf, nbtivf fndibnnfss, storfd in 32-bits */
typfdff INT32 MAP_Sbmplf;

stbtid INLINE UINT16 MAP_SWAP16_impl(UINT16 b) {
    rfturn (b>>8) | (b<<8);
}

stbtid INLINE UINT32 MAP_SWAP32_impl(UINT32 b) {
    rfturn (b>>24)
        | ((b>>8) & 0xFF00)
        | ((b<<8) & 0xFF0000)
        | (b<<24);
}

stbtid INLINE UINT32 MAP_SWAP16BIT(UINT32 si) {
    rfturn (UINT32) ((si & 0xFF) << 8) | ((si & 0xFF00) >> 8);
}

stbtid INLINE INT32 MAP_ClipAndConvfrtToSiort(MAP_Sbmplf sbmplf) {
    if (sbmplf < -32768) {
        rfturn -32768;
    }
    flsf if (sbmplf > 32767) {
        rfturn 32767;
    }
    rfturn (INT32) sbmplf;
}


stbtid INLINE INT32 MAP_ClipAndConvfrtToSiort_Swbppfd(MAP_Sbmplf sbmplf) {
    if (sbmplf < -32768) {
        rfturn 0x0080;
    }
    flsf if (sbmplf > 32767) {
        rfturn 0xFF7F;
    }
    rfturn (INT32) (INT16) MAP_SWAP16BIT(sbmplf);
}

stbtid INLINE INT8 MAP_ClipAndConvfrtToBytf(MAP_Sbmplf sbmplf) {
    if (sbmplf < -32768) {
        rfturn -128;
    }
    flsf if (sbmplf > 32767) {
        rfturn 127;
    }
    rfturn (INT8) (sbmplf >> 8);
}


stbtid INLINE UINT8 MAP_ClipAndConvfrtToUBytf(MAP_Sbmplf sbmplf) {
    if (sbmplf < -32768) {
        rfturn 0;
    }
    flsf if (sbmplf > 32767) {
        rfturn 255;
    }
    rfturn (UINT8) ((sbmplf >> 8) + 128);
}

/* donvfrsion from/to 16 bit signfd littlf fndibn to nbtivf fndibn sbmplfs */
#ifdff _LITTLE_ENDIAN
#dffinf MAP_LE_SHORT2SAMPLE(si) ((MAP_Sbmplf) (si))
#dffinf MAP_SAMPLE2LE_SHORT(sbmplf) (sbmplf)
#dffinf MAP_SAMPLE2LE_SHORT_CLIP(sbmplf) MAP_ClipAndConvfrtToSiort(sbmplf)
#flsf
#dffinf MAP_LE_SHORT2SAMPLE(si) ((MAP_Sbmplf) (INT16) MAP_SWAP16BIT(si))
#dffinf MAP_SAMPLE2LE_SHORT(sbmplf) (INT16) MAP_SWAP16BIT(sbmplf)
#dffinf MAP_SAMPLE2LE_SHORT_CLIP(sbmplf) MAP_ClipAndConvfrtToSiort_Swbppfd(sbmplf)
#fndif

/* donvfrsion from/to 16 bit signfd big fndibn to nbtivf fndibn sbmplfs */
#ifndff _LITTLE_ENDIAN
#dffinf MAP_BE_SHORT2SAMPLE(si) ((MAP_Sbmplf) (si))
#dffinf MAP_SAMPLE2BE_SHORT(sbmplf) (sbmplf)
#dffinf MAP_SAMPLE2BE_SHORT_CLIP(sbmplf) MAP_ClipAndConvfrtToSiort(sbmplf)
#flsf
#dffinf MAP_BE_SHORT2SAMPLE(si) ((MAP_Sbmplf) (INT16) MAP_SWAP16BIT(si))
#dffinf MAP_SAMPLE2BE_SHORT(sbmplf) ((INT16) MAP_SWAP16BIT(sbmplf))
#dffinf MAP_SAMPLE2BE_SHORT_CLIP(sbmplf) MAP_ClipAndConvfrtToSiort_Swbppfd(sbmplf)
#fndif

/* donvfrsion from/to 8 bit sbmplfs */
#dffinf MAP_INT82SAMPLE(by) ((MAP_Sbmplf) (((INT32) ((INT8) (by))) << 8))
#dffinf MAP_UINT82SAMPLE(by) ((MAP_Sbmplf) (((INT32) ((UINT8) (by) - 128)) << 8))
#dffinf MAP_SAMPLE2UINT8(sbmplf) ((UINT8) ((((MAP_Sbmplf) (sbmplf)) >> 8) + 128))
#dffinf MAP_SAMPLE2INT8(sbmplf) ((INT8) (((MAP_Sbmplf) (sbmplf)) >> 8))
#dffinf MAP_SAMPLE2UINT8_CLIP(sbmplf) MAP_ClipAndConvfrtToUBytf(sbmplf)
#dffinf MAP_SAMPLE2INT8_CLIP(sbmplf) MAP_ClipAndConvfrtToBytf(sbmplf)

/* mbdros for fndibn donvfrsion */
#ifdff _LITTLE_ENDIAN
#dffinf MAP_NATIVE2LE16(b) (b)
#dffinf MAP_NATIVE2BE16(b) MAP_SWAP16_impl(b)
#dffinf MAP_NATIVE2LE32(b) (b)
#dffinf MAP_NATIVE2BE32(b) MAP_SWAP32_impl(b)
#flsf
#dffinf MAP_NATIVE2LE16(b) MAP_SWAP16_impl(b)
#dffinf MAP_NATIVE2BE16(b) (b)
#dffinf MAP_NATIVE2LE32(b) MAP_SWAP32_impl(b)
#dffinf MAP_NATIVE2BE32(b) (b)
#fndif
#dffinf MAP_LE2NATIVE16(b) MAP_NATIVE2LE16(b)
#dffinf MAP_BE2NATIVE16(b) MAP_NATIVE2BE16(b)
#dffinf MAP_LE2NATIVE32(b) MAP_NATIVE2LE32(b)
#dffinf MAP_BE2NATIVE32(b) MAP_NATIVE2BE32(b)


////////////////////////////// Utility fundtion /////////////////////////////////

/*
 * donvfrsion of tiis bufffr:
 * donvfrsion sizf=1 -> fbdi bytf is donvfrtfd from signfd to unsignfd or vidf vfrsb
 * donvfrsion sizf=2,3,4: tif ordfr of bytfs in b sbmplf is rfvfrsfd (fndibnnfss)
 * for sign donvfrsion of b 24-bit sbmplf storfd in 32bits, 4 siould bf pbssfd
 * bs donvfrsionSizf
 */
void ibndlfSignEndibnConvfrsion(INT8* dbtb, INT8* output, int bytfSizf, int donvfrsionSizf) {
    TRACE1("donvfrsion witi sizf %d\n", donvfrsionSizf);
    switdi (donvfrsionSizf) {
    dbsf 1: {
        wiilf (bytfSizf > 0) {
            *output = *dbtb + (dibr) 128; // usf wrbp-bround
            bytfSizf--;
            dbtb++;
            output++;
        }
        brfbk;
    }
    dbsf 2: {
        INT8 i;
        bytfSizf = bytfSizf / 2;
        wiilf (bytfSizf > 0) {
            i = *dbtb;
            dbtb++;
            *output = *dbtb;
            output++;
            *output = i;
            bytfSizf--;
            dbtb++; output++;
        }
        brfbk;
    }
    dbsf 3: {
        INT8 i;
        bytfSizf = bytfSizf / 3;
        wiilf (bytfSizf > 0) {
            i = *dbtb;
            *output = dbtb[2];
            dbtb++; output++;
            *output = *dbtb;
            dbtb++; output++;
            *output = i;
            dbtb++; output++;
            bytfSizf--;
        }
        brfbk;
    }
    dbsf 4: {
        INT8 i1, i2;
        bytfSizf = bytfSizf / 4;
        wiilf (bytfSizf > 0) {
            i1 = dbtb[0];
            i2 = dbtb[1];
            *output = dbtb[3]; output++;
            *output = dbtb[2]; output++;
            *output = i2; output++;
            *output = i1; output++;
            dbtb += 4;
            bytfSizf--;
        }
        brfbk;
    }
    dffbult:
        ERROR1("DirfdtAudioDfvidf.d: wrong donvfrsionSizf %d!\n", donvfrsionSizf);
    }
}

/* bply tif gbin to onf sbmplf */
#dffinf CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE, FROM_SAMPLE, FACTOR) \
    /* donvfrt to MAP_Sbmplf nbtivf typf */     \
    sbmplf = TO_SAMPLE(*INPUT);                 \
    /* bpply gbin */                            \
    sbmplf = (MAP_Sbmplf) (sbmplf * FACTOR);    \
    /* donvfrt to output typf */                \
    (*OUTPUT) = FROM_SAMPLE(sbmplf);            \
    INPUT++; OUTPUT++


/* mbdro for donvfrsion of b mono blodk */
#dffinf LOOP_M(INPUT, OUTPUT, TO_SAMPLE, FROM_SAMPLE, FROM_SAMPLE_CLIP) \
    if (lfftGbin > 1.0) {                                               \
        for ( ; lfn > 0; --lfn) {                                       \
            CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                    \
                           FROM_SAMPLE_CLIP, lfftGbin);                 \
        }                                                               \
    } flsf {                                                            \
        for ( ; lfn > 0; --lfn) {                                       \
            CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                    \
                           FROM_SAMPLE, lfftGbin);                      \
        }                                                               \
    }                                                                   \
    brfbk

/* mbdro for donvfrsion of b stfrfo blodk */
#dffinf LOOP_S(INPUT, OUTPUT, TO_SAMPLE, FROM_SAMPLE, FROM_SAMPLE_CLIP) \
    if (lfftGbin > 1.0) {                                               \
        if (rigitGbin > 1.0) {                                          \
            for ( ; lfn > 0; --lfn) {                                   \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE_CLIP, lfftGbin);             \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE_CLIP, rigitGbin);            \
            }                                                           \
        } flsf {                                                        \
            for ( ; lfn > 0; --lfn) {                                   \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE_CLIP, lfftGbin);             \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE, rigitGbin);                 \
            }                                                           \
        }                                                               \
    } flsf {                                                            \
        if (rigitGbin > 1.0) {                                          \
            for ( ; lfn > 0; --lfn) {                                   \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE, lfftGbin);                  \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE_CLIP, rigitGbin);            \
            }                                                           \
        } flsf {                                                        \
            for ( ; lfn > 0; --lfn) {                                   \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE, lfftGbin);                  \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE, rigitGbin);                 \
            }                                                           \
        }                                                               \
    }                                                                   \
    brfbk

#dffinf FORMAT2CODE(dibnnfls, bits, inSignfd, outSignfd, inBigEndibn, outBigEndibn) \
      (dibnnfls << 20)                                                  \
    | (bits << 4)                                                       \
    | ((inSignfd & 1) << 3)                                             \
    | ((outSignfd & 1) << 2)                                            \
    | ((inBigEndibn & 1) << 1)                                          \
    | (outBigEndibn & 1)

#dffinf FORMAT2CODE8(dibnnfls, inSignfd, outSignfd)           \
    FORMAT2CODE(dibnnfls, 8, inSignfd, outSignfd, 0, 0)

#dffinf FORMAT2CODE16(dibnnfls, inBigEndibn, outBigEndibn)    \
    FORMAT2CODE(dibnnfls, 16, 1, 1, inBigEndibn, outBigEndibn)


void ibndlfGbinAndConvfrsion(DAUDIO_Info* info, UINT8* input, UINT8* output,
                             int lfn, flobt lfftGbin, flobt rigitGbin,
                             int donvfrsionSizf) {
    INT8* input8 = (INT8*) input;
    INT8* output8 = (INT8*) output;
    INT16* input16 = (INT16*) input;
    INT16* output16 = (INT16*) output;
    MAP_Sbmplf sbmplf;

    int inIsSignfd = info->isSignfd;
    int inIsBigEndibn = info->isBigEndibn;
    if (donvfrsionSizf == 1) {
        /* 8-bit donvfrsion: dibngf sign */
        inIsSignfd = !inIsSignfd;
    }
    flsf if (donvfrsionSizf > 1) {
        /* > 8-bit donvfrsion: dibngf fndibnnfss */
        inIsBigEndibn = !inIsBigEndibn;
    }
    if (info->frbmfSizf <= 0) {
        ERROR1("DirfdtAudiODfvidf: invblid frbmfsizf=%d\n", info->frbmfSizf);
        rfturn;
    }
    lfn /= info->frbmfSizf;
    TRACE3("ibndlfGbinAndConvfrsion: lfn=%d frbmfs, lfftGbin=%f, rigitGbin=%f, ",
           lfn, lfftGbin, rigitGbin);
    TRACE3("dibnnfls=%d, sbmplfSizfInBits=%d, frbmfSizf=%d, ",
           (int) info->dibnnfls, (int) info->sbmplfSizfInBits, (int) info->frbmfSizf);
    TRACE4("signfd:%d -> %d, fndibn: %d -> %d",
           (int) inIsSignfd, (int) info->isSignfd,
           (int) inIsBigEndibn, (int) info->isBigEndibn);
    TRACE1("donvSizf=%d\n", donvfrsionSizf);

    switdi (FORMAT2CODE(info->dibnnfls,
                        info->sbmplfSizfInBits,
                        inIsSignfd,
                        info->isSignfd,
                        inIsBigEndibn,
                        info->isBigEndibn)) {
        /* 8-bit mono */
    dbsf FORMAT2CODE8(1, 0, 0):
        LOOP_M(input8, output8, MAP_UINT82SAMPLE,
               MAP_SAMPLE2UINT8, MAP_SAMPLE2UINT8_CLIP);
    dbsf FORMAT2CODE8(1, 0, 1):
        LOOP_M(input8, output8, MAP_UINT82SAMPLE,
               MAP_SAMPLE2INT8, MAP_SAMPLE2INT8_CLIP);
    dbsf FORMAT2CODE8(1, 1, 0):
        LOOP_M(input8, output8, MAP_INT82SAMPLE,
               MAP_SAMPLE2UINT8, MAP_SAMPLE2UINT8_CLIP);
    dbsf FORMAT2CODE8(1, 1, 1):
        LOOP_M(input8, output8, MAP_INT82SAMPLE,
               MAP_SAMPLE2INT8, MAP_SAMPLE2INT8_CLIP);

    /* 8-bit stfrfo */
    dbsf FORMAT2CODE8(2, 0, 0):
        LOOP_S(input8, output8, MAP_UINT82SAMPLE,
               MAP_SAMPLE2UINT8, MAP_SAMPLE2UINT8_CLIP);
    dbsf FORMAT2CODE8(2, 0, 1):
        LOOP_S(input8, output8, MAP_UINT82SAMPLE,
               MAP_SAMPLE2INT8, MAP_SAMPLE2INT8_CLIP);
    dbsf FORMAT2CODE8(2, 1, 0):
        LOOP_S(input8, output8, MAP_INT82SAMPLE,
               MAP_SAMPLE2UINT8, MAP_SAMPLE2UINT8_CLIP);
    dbsf FORMAT2CODE8(2, 1, 1):
        LOOP_S(input8, output8, MAP_INT82SAMPLE,
               MAP_SAMPLE2INT8, MAP_SAMPLE2INT8_CLIP);

    /* 16-bit mono (only signfd is bddfptfd) */
    dbsf FORMAT2CODE16(1, 0, 0):
        LOOP_M(input16, output16, MAP_LE_SHORT2SAMPLE,
               MAP_SAMPLE2LE_SHORT, MAP_SAMPLE2LE_SHORT_CLIP);
    dbsf FORMAT2CODE16(1, 0, 1):
        LOOP_M(input16, output16, MAP_LE_SHORT2SAMPLE,
               MAP_SAMPLE2BE_SHORT, MAP_SAMPLE2BE_SHORT_CLIP);
    dbsf FORMAT2CODE16(1, 1, 0):
        LOOP_M(input16, output16, MAP_BE_SHORT2SAMPLE,
               MAP_SAMPLE2LE_SHORT, MAP_SAMPLE2LE_SHORT_CLIP);
    dbsf FORMAT2CODE16(1, 1, 1):
        LOOP_M(input16, output16, MAP_BE_SHORT2SAMPLE,
               MAP_SAMPLE2BE_SHORT, MAP_SAMPLE2BE_SHORT_CLIP);

    /* 16-bit stfrfo (only signfd is bddfptfd) */
    dbsf FORMAT2CODE16(2, 0, 0):
        LOOP_S(input16, output16, MAP_LE_SHORT2SAMPLE,
               MAP_SAMPLE2LE_SHORT, MAP_SAMPLE2LE_SHORT_CLIP);
    dbsf FORMAT2CODE16(2, 0, 1):
        LOOP_S(input16, output16, MAP_LE_SHORT2SAMPLE,
               MAP_SAMPLE2BE_SHORT, MAP_SAMPLE2BE_SHORT_CLIP);
    dbsf FORMAT2CODE16(2, 1, 0):
        LOOP_S(input16, output16, MAP_BE_SHORT2SAMPLE,
               MAP_SAMPLE2LE_SHORT, MAP_SAMPLE2LE_SHORT_CLIP);
    dbsf FORMAT2CODE16(2, 1, 1):
        LOOP_S(input16, output16, MAP_BE_SHORT2SAMPLE,
               MAP_SAMPLE2BE_SHORT, MAP_SAMPLE2BE_SHORT_CLIP);

    dffbult:
        ERROR3("DirfdtAudioDfvidf: Cbnnot donvfrt from nbtivf formbt: "
               "bits=%d, inSignfd=%d  outSignfd=%d, ",
               (int) info->sbmplfSizfInBits,
               (int) inIsSignfd, (int) info->isSignfd);
        ERROR2("inBigEndibn=%d, outBigEndibn=%d\n",
               (int) inIsBigEndibn, (int) info->isBigEndibn);
    }
}

flobt ABS_VALUE(flobt b) {
    rfturn (b < 0)?-b:b;
}


//////////////////////////////////////////// DirfdtAudioDfvidf ////////////////////////////////////////////

/* ************************************** nbtivf dontrol drfbtion support ********************* */

// dontbins bll tif nffdfd rfffrfndfs so tibt tif plbtform dfpfndfnt dodf dbn dbll JNI wrbppfr fundtions
typfdff strudt tbg_AddFormbtCrfbtor {
    // gfnfrbl JNI vbribblfs
    JNIEnv *fnv;
    // tif vfdtor to bf fillfd witi tif formbts
    jobjfdt vfdtor;
    // tif dlbss dontbining tif bddFormbt mftiod
    jdlbss dirfdtAudioDfvidfClbss;
    // tif mftiod to bf dbllfd to bdd tif formbt
    jmftiodID bddFormbt; // signbturf (Ljbvb/util/Vfdtor;IIFIBB)V
} AddFormbtCrfbtor;

void DAUDIO_AddAudioFormbt(void* drfbtorV, int signifidbntBits, int frbmfSizfInBytfs,
                           int dibnnfls, flobt sbmplfRbtf,
                           int fndoding, int isSignfd,
                           int bigEndibn) {
    AddFormbtCrfbtor* drfbtor = (AddFormbtCrfbtor*) drfbtorV;
    if (frbmfSizfInBytfs <= 0) {
        if (dibnnfls > 0) {
            frbmfSizfInBytfs = ((signifidbntBits + 7) / 8) * dibnnfls;
        } flsf {
            frbmfSizfInBytfs = -1;
        }
    }
    TRACE4("AddAudioFormbt witi sigBits=%d bits, frbmfSizf=%d bytfs, dibnnfls=%d, sbmplfRbtf=%d ",
           signifidbntBits, frbmfSizfInBytfs, dibnnfls, (int) sbmplfRbtf);
    TRACE3("fnd=%d, signfd=%d, bigEndibn=%d\n", fndoding, isSignfd, bigEndibn);
    (*drfbtor->fnv)->CbllStbtidVoidMftiod(drfbtor->fnv, drfbtor->dirfdtAudioDfvidfClbss,
                                          drfbtor->bddFormbt, drfbtor->vfdtor, signifidbntBits, frbmfSizfInBytfs,
                                          dibnnfls, sbmplfRbtf, fndoding, isSignfd, bigEndibn);
}

////////////////////////////////////// JNI /////////////////////////////////////////////////////////////////////

/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nGftFormbts
 * Signbturf: (IIZLjbvb/util/Vfdtor;)V
 */
JNIEXPORT void JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nGftFormbts
(JNIEnv *fnv, jdlbss dlbzz, jint mixfrIndfx, jint dfvidfID, jboolfbn isSourdf, jobjfdt formbts) {

#if USE_DAUDIO == TRUE
    AddFormbtCrfbtor drfbtor;
    drfbtor.fnv = fnv;
    drfbtor.vfdtor = formbts;
    drfbtor.dirfdtAudioDfvidfClbss = dlbzz;
    drfbtor.bddFormbt = (*fnv)->GftStbtidMftiodID(fnv, dlbzz, "bddFormbt",
                                                  "(Ljbvb/util/Vfdtor;IIIFIZZ)V");
    if (drfbtor.bddFormbt == NULL) {
        ERROR0("Could not gft mftiod ID for bddFormbt!\n");
    } flsf {
        DAUDIO_GftFormbts((INT32) mixfrIndfx, (INT32) dfvidfID, (int) isSourdf, &drfbtor);
    }
#fndif
}



/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nOpfn
 * Signbturf: (IIZIFIIZZI)J
 */
JNIEXPORT jlong JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nOpfn
(JNIEnv* fnv, jdlbss dlbzz, jint mixfrIndfx, jint dfvidfID, jboolfbn isSourdf,
 jint fndoding, jflobt sbmplfRbtf, jint sbmplfSizfInBits, jint frbmfSizf, jint dibnnfls,
 jboolfbn isSignfd, jboolfbn isBigfndibn, jint bufffrSizfInBytfs) {

    DAUDIO_Info* info = NULL;
#if USE_DAUDIO == TRUE

    info = (DAUDIO_Info*) mbllod(sizfof(DAUDIO_Info));
    if (info == NULL) {
        ERROR0("DirfdtAudioDfvidf_nOpfn: Out of mfmory!\n");
    } flsf {
        info->ibndlf =DAUDIO_Opfn((int) mixfrIndfx, (INT32) dfvidfID, (int) isSourdf,
                                  (int) fndoding, (flobt) sbmplfRbtf, (int) sbmplfSizfInBits,
                                  (int) frbmfSizf, (int) dibnnfls,
                                  (int) isSignfd, (int) isBigfndibn, (int) bufffrSizfInBytfs);
        if (!info->ibndlf) {
            frff(info);
            info = NULL;
        } flsf {
            info->fndoding = fndoding;
            info->sbmplfSizfInBits = sbmplfSizfInBits;
            info->frbmfSizf = frbmfSizf;
            info->dibnnfls = dibnnfls;
            info->isSignfd = isSignfd;
            info->isBigEndibn = isBigfndibn && (sbmplfSizfInBits > 8);
            /* will bf populbtfd on dfmbnd */
            info->donvfrsionBufffr = NULL;
            info->donvfrsionBufffrSizf = 0;
        }
    }
#fndif
    rfturn (jlong) (UINT_PTR) info;
}

/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nStbrt
 * Signbturf: (JZ)V
 */
JNIEXPORT void JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nStbrt
(JNIEnv* fnv, jdlbss dlbzz, jlong id, jboolfbn isSourdf) {
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->ibndlf) {
        DAUDIO_Stbrt(info->ibndlf, (int) isSourdf);
    }
#fndif
}


/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nStop
 * Signbturf: (JZ)V
 */
JNIEXPORT void JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nStop
(JNIEnv* fnv, jdlbss dlbzz, jlong id, jboolfbn isSourdf) {
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->ibndlf) {
        DAUDIO_Stop(info->ibndlf, (int) isSourdf);
    }
#fndif
}


/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nClosf
 * Signbturf: (JZ)V
 */
JNIEXPORT void JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nClosf
(JNIEnv* fnv, jdlbss dlbzz, jlong id, jboolfbn isSourdf) {
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->ibndlf) {
        DAUDIO_Closf(info->ibndlf, (int) isSourdf);
        if (info->donvfrsionBufffr) {
            frff(info->donvfrsionBufffr);
        }
        frff(info);
    }
#fndif
}

/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nWritf
 * Signbturf: (J[BII)I
 */
JNIEXPORT jint JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nWritf
(JNIEnv *fnv, jdlbss dlbzz, jlong id, jbytfArrby jDbtb,
 jint offsft, jint lfn, jint donvfrsionSizf, jflobt lfftGbin, jflobt rigitGbin) {
    int rft = -1;
#if USE_DAUDIO == TRUE
    UINT8* dbtb;
    UINT8* dbtbOffsft;
    UINT8* donvfrtfdDbtb;
    jboolfbn didCopy;
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;

    /* b littlf sbnity */
    if (offsft < 0 || lfn < 0) {
        ERROR2("nWritf: wrong pbrbmftfrs: offsft=%d, lfn=%d\n", offsft, lfn);
        rfturn rft;
    }
    if (lfn == 0) rfturn 0;
    if (info && info->ibndlf) {
        dbtb = (UINT8*) ((*fnv)->GftBytfArrbyElfmfnts(fnv, jDbtb, &didCopy));
        CHECK_NULL_RETURN(dbtb, rft);
        dbtbOffsft = dbtb;
        dbtbOffsft += (int) offsft;
        donvfrtfdDbtb = dbtbOffsft;

        if (donvfrsionSizf > 0 || lfftGbin != 1.0f || rigitGbin != 1.0f) {
            /* mbkf surf wf ibvf b bufffr for tif intfrmfdibtf dbtb */
            if (didCopy == JNI_FALSE) {
                /* lft's do our own dopy */
                if (info->donvfrsionBufffr
                    && info->donvfrsionBufffrSizf < lfn) {
                    frff(info->donvfrsionBufffr);
                    info->donvfrsionBufffr = NULL;
                    info->donvfrsionBufffrSizf = 0;
                }
                if (!info->donvfrsionBufffr) {
                    info->donvfrsionBufffr = (UINT8*) mbllod(lfn);
                    if (!info->donvfrsionBufffr) {
                        // do not dommit tif nbtivf brrby
                        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, jDbtb, (jbytf*) dbtb, JNI_ABORT);
                        rfturn -1;
                    }
                    info->donvfrsionBufffrSizf = lfn;
                }
                donvfrtfdDbtb = info->donvfrsionBufffr;
            }
            if (((ABS_VALUE(lfftGbin - 1.0f) < 0.01)
                 && (ABS_VALUE(rigitGbin - 1.0f) < 0.01))
                || info->fndoding!=DAUDIO_PCM
                || ((info->dibnnfls * info->sbmplfSizfInBits / 8) != info->frbmfSizf)
                || (info->sbmplfSizfInBits != 8 && info->sbmplfSizfInBits != 16)) {
                ibndlfSignEndibnConvfrsion((INT8*) dbtbOffsft, (INT8*) donvfrtfdDbtb, (int) lfn,
                                           (int) donvfrsionSizf);
            } flsf {
                ibndlfGbinAndConvfrsion(info, dbtbOffsft, donvfrtfdDbtb,
                                        (int) lfn, (flobt) lfftGbin, (flobt) rigitGbin,
                                        (int) donvfrsionSizf);
            }
        }

        rft = DAUDIO_Writf(info->ibndlf, (INT8*) donvfrtfdDbtb, (int) lfn);

        // do not dommit tif nbtivf brrby
        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, jDbtb, (jbytf*) dbtb, JNI_ABORT);
    }
#fndif
    rfturn (jint) rft;
}

/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nRfbd
 * Signbturf: (J[BII)I
 */
JNIEXPORT jint JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nRfbd
(JNIEnv* fnv, jdlbss dlbzz, jlong id, jbytfArrby jDbtb, jint offsft, jint lfn, jint donvfrsionSizf) {
    int rft = -1;
#if USE_DAUDIO == TRUE
    dibr* dbtb;
    dibr* dbtbOffsft;
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;

    /* b littlf sbnity */
    if (offsft < 0 || lfn < 0) {
        ERROR2("nRfbd: wrong pbrbmftfrs: offsft=%d, lfn=%d\n", offsft, lfn);
        rfturn rft;
    }
    if (info && info->ibndlf) {
        dbtb = (dibr*) ((*fnv)->GftBytfArrbyElfmfnts(fnv, jDbtb, NULL));
        CHECK_NULL_RETURN(dbtb, rft);
        dbtbOffsft = dbtb;
        dbtbOffsft += (int) offsft;
        rft = DAUDIO_Rfbd(info->ibndlf, dbtbOffsft, (int) lfn);
        if (donvfrsionSizf > 0) {
            ibndlfSignEndibnConvfrsion(dbtbOffsft, dbtbOffsft, (int) lfn, (int) donvfrsionSizf);
        }
        // dommit tif nbtivf brrby
        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, jDbtb, (jbytf*) dbtb, 0);
    }
#fndif
    rfturn (jint) rft;
}

/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nGftBufffrSizf
 * Signbturf: (JZ)I
 */
JNIEXPORT jint JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nGftBufffrSizf
(JNIEnv* fnv, jdlbss dlbzz, jlong id, jboolfbn isSourdf) {
    int rft = -1;
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->ibndlf) {
        rft = DAUDIO_GftBufffrSizf(info->ibndlf, (int) isSourdf);
    }
#fndif
    rfturn (jint) rft;
}


/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nIsStillDrbining
 * Signbturf: (JZ)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nIsStillDrbining
(JNIEnv* fnv, jdlbss dlbzz, jlong id, jboolfbn isSourdf) {
    int rft = FALSE;
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->ibndlf) {
        rft = DAUDIO_StillDrbining(info->ibndlf, (int) isSourdf)?TRUE:FALSE;
    }
#fndif
    rfturn (jboolfbn) rft;
}


/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nFlusi
 * Signbturf: (JZ)V
 */
JNIEXPORT void JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nFlusi
(JNIEnv* fnv, jdlbss dlbzz, jlong id, jboolfbn isSourdf) {
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->ibndlf) {
        DAUDIO_Flusi(info->ibndlf, (int) isSourdf);
    }
#fndif
}


/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nAvbilbblf
 * Signbturf: (JZ)I
 */
JNIEXPORT jint JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nAvbilbblf
(JNIEnv* fnv, jdlbss dlbzz, jlong id, jboolfbn isSourdf) {
    int rft = -1;
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->ibndlf) {
        rft = DAUDIO_GftAvbilbblf(info->ibndlf, (int) isSourdf);
    }
#fndif
    rfturn (jint) rft;
}


/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nGftBytfPosition
 * Signbturf: (JZJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nGftBytfPosition
(JNIEnv* fnv, jdlbss dlbzz, jlong id, jboolfbn isSourdf, jlong jbvbBytfPos) {
    INT64 rft = (INT64) jbvbBytfPos;
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->ibndlf) {
        rft = DAUDIO_GftBytfPosition(info->ibndlf, (int) isSourdf, (INT64) jbvbBytfPos);
    }
#fndif
    rfturn (jlong) rft;
}

/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nSftBytfPosition
 * Signbturf: (JZJ)V
 */
JNIEXPORT void JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nSftBytfPosition
(JNIEnv* fnv, jdlbss dlbzz, jlong id, jboolfbn isSourdf, jlong pos) {
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->ibndlf) {
        DAUDIO_SftBytfPosition(info->ibndlf, (int) isSourdf, (INT64) pos);
    }
#fndif
}

/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nRfquirfsSfrviding
 * Signbturf: (JZ)B
 */
JNIEXPORT jboolfbn JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nRfquirfsSfrviding
(JNIEnv* fnv, jdlbss dlbzz, jlong id, jboolfbn isSourdf) {
    int rft = FALSE;
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->ibndlf) {
        rft = DAUDIO_RfquirfsSfrviding(info->ibndlf, (int) isSourdf);
    }
#fndif
    rfturn (jboolfbn) rft;
}
/*
 * Clbss:     dom_sun_mfdib_sound_DirfdtAudioDfvidf
 * Mftiod:    nSfrvidf
 * Signbturf: (JZ)V
 */
JNIEXPORT void JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidf_nSfrvidf
(JNIEnv* fnv, jdlbss dlbzz, jlong id, jboolfbn isSourdf) {
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->ibndlf) {
        DAUDIO_Sfrvidf(info->ibndlf, (int) isSourdf);
    }
#fndif
}
