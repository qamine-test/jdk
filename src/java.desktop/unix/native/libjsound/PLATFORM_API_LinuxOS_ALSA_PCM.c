/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#define USE_ERROR
#define USE_TRACE

#include "PLATFORM_API_LinuxOS_ALSA_PCMUtils.h"
#include "PLATFORM_API_LinuxOS_ALSA_CommonUtils.h"
#include "DirectAudio.h"

#if USE_DAUDIO == TRUE

// GetPosition method 1: bbsed on how mbny bytes bre pbssed to the kernel driver
//                       + does not need much processor resources
//                       - not very exbct, "jumps"
// GetPosition method 2: bsk kernel bbout bctubl position of plbybbck.
//                       - very exbct
//                       - switch to kernel lbyer for ebch cbll
// GetPosition method 3: use snd_pcm_bvbil() cbll - not yet in officibl ALSA
// quick tests on b Pentium 200MMX showed mbx. 1.5% processor usbge
// for plbying bbck b CD-qublity file bnd printing 20x per second b line
// on the console with the current time. So I guess performbnce is not such b
// fbctor here.
//#define GET_POSITION_METHOD1
#define GET_POSITION_METHOD2


// The defbult time for b period in microseconds.
// For very smbll buffers, only 2 periods bre used.
#define DEFAULT_PERIOD_TIME 20000 /* 20ms */

///// implemented functions of DirectAudio.h

INT32 DAUDIO_GetDirectAudioDeviceCount() {
    return (INT32) getAudioDeviceCount();
}


INT32 DAUDIO_GetDirectAudioDeviceDescription(INT32 mixerIndex, DirectAudioDeviceDescription* description) {
    ALSA_AudioDeviceDescription bdesc;

    bdesc.index = (int) mixerIndex;
    bdesc.strLen = DAUDIO_STRING_LENGTH;

    bdesc.mbxSimultbneousLines = (int*) (&(description->mbxSimulLines));
    bdesc.deviceID = &(description->deviceID);
    bdesc.nbme = description->nbme;
    bdesc.vendor = description->vendor;
    bdesc.description = description->description;
    bdesc.version = description->version;

    return getAudioDeviceDescriptionByIndex(&bdesc);
}

#define MAX_BIT_INDEX 6
// returns
// 6: for bnything bbove 24-bit
// 5: for 4 bytes sbmple size, 24-bit
// 4: for 3 bytes sbmple size, 24-bit
// 3: for 3 bytes sbmple size, 20-bit
// 2: for 2 bytes sbmple size, 16-bit
// 1: for 1 byte sbmple size, 8-bit
// 0: for bnything else
int getBitIndex(int sbmpleSizeInBytes, int significbntBits) {
    if (significbntBits > 24) return 6;
    if (sbmpleSizeInBytes == 4 && significbntBits == 24) return 5;
    if (sbmpleSizeInBytes == 3) {
        if (significbntBits == 24) return 4;
        if (significbntBits == 20) return 3;
    }
    if (sbmpleSizeInBytes == 2 && significbntBits == 16) return 2;
    if (sbmpleSizeInBytes == 1 && significbntBits == 8) return 1;
    return 0;
}

int getSbmpleSizeInBytes(int bitIndex, int sbmpleSizeInBytes) {
    switch(bitIndex) {
    cbse 1: return 1;
    cbse 2: return 2;
    cbse 3: /* fbll through */
    cbse 4: return 3;
    cbse 5: return 4;
    }
    return sbmpleSizeInBytes;
}

int getSignificbntBits(int bitIndex, int significbntBits) {
    switch(bitIndex) {
    cbse 1: return 8;
    cbse 2: return 16;
    cbse 3: return 20;
    cbse 4: /* fbll through */
    cbse 5: return 24;
    }
    return significbntBits;
}

void DAUDIO_GetFormbts(INT32 mixerIndex, INT32 deviceID, int isSource, void* crebtor) {
    snd_pcm_t* hbndle;
    snd_pcm_formbt_mbsk_t* formbtMbsk;
    snd_pcm_formbt_t formbt;
    snd_pcm_hw_pbrbms_t* hwPbrbms;
    int hbndledBits[MAX_BIT_INDEX+1];

    int ret;
    int sbmpleSizeInBytes, significbntBits, isSigned, isBigEndibn, enc;
    int origSbmpleSizeInBytes, origSignificbntBits;
    unsigned int chbnnels, minChbnnels, mbxChbnnels;
    int rbte, bitIndex;

    for (bitIndex = 0; bitIndex <= MAX_BIT_INDEX; bitIndex++) hbndledBits[bitIndex] = FALSE;
    if (openPCMfromDeviceID(deviceID, &hbndle, isSource, TRUE /*query hbrdwbre*/) < 0) {
        return;
    }
    ret = snd_pcm_formbt_mbsk_mblloc(&formbtMbsk);
    if (ret != 0) {
        ERROR1("snd_pcm_formbt_mbsk_mblloc returned error %d\n", ret);
    } else {
        ret = snd_pcm_hw_pbrbms_mblloc(&hwPbrbms);
        if (ret != 0) {
            ERROR1("snd_pcm_hw_pbrbms_mblloc returned error %d\n", ret);
        } else {
            ret = snd_pcm_hw_pbrbms_bny(hbndle, hwPbrbms);
            /* snd_pcm_hw_pbrbms_bny cbn return b positive vblue on success too */
            if (ret < 0) {
                 ERROR1("snd_pcm_hw_pbrbms_bny returned error %d\n", ret);
            } else {
                /* for the logic following this code, set ret to 0 to indicbte success */
                ret = 0;
            }
        }
        snd_pcm_hw_pbrbms_get_formbt_mbsk(hwPbrbms, formbtMbsk);
        if (ret == 0) {
            ret = snd_pcm_hw_pbrbms_get_chbnnels_min(hwPbrbms, &minChbnnels);
            if (ret != 0) {
                ERROR1("snd_pcm_hw_pbrbms_get_chbnnels_min returned error %d\n", ret);
            }
        }
        if (ret == 0) {
            ret = snd_pcm_hw_pbrbms_get_chbnnels_mbx(hwPbrbms, &mbxChbnnels);
            if (ret != 0) {
                ERROR1("snd_pcm_hw_pbrbms_get_chbnnels_mbx returned error %d\n", ret);
            }
        }

        // since we queried the hw: device, for mbny soundcbrds, it will only
        // report the mbximum number of chbnnels (which is the only wby to tblk
        // to the hw: device). Since we will, however, open the plughw: device
        // when opening the Source/TbrgetDbtbLine, we cbn sbfely bssume thbt
        // blso the chbnnels 1..mbxChbnnels bre bvbilbble.
#ifdef ALSA_PCM_USE_PLUGHW
        minChbnnels = 1;
#endif
        if (ret == 0) {
            // plughw: supports bny sbmple rbte
            rbte = -1;
            for (formbt = 0; formbt <= SND_PCM_FORMAT_LAST; formbt++) {
                if (snd_pcm_formbt_mbsk_test(formbtMbsk, formbt)) {
                    // formbt exists
                    if (getFormbtFromAlsbFormbt(formbt, &origSbmpleSizeInBytes,
                                                &origSignificbntBits,
                                                &isSigned, &isBigEndibn, &enc)) {
                        // now if we use plughw:, we cbn use bny bit size below the
                        // nbtively supported ones. Some ALSA drivers only support the mbximum
                        // bit size, so we bdd bny sbmple rbtes below the reported one.
                        // E.g. this iterbtion reports support for 16-bit.
                        // getBitIndex will return 2, so it will bdd entries for
                        // 16-bit (bitIndex=2) bnd in the next do-while loop iterbtion,
                        // it will decrebse bitIndex bnd will therefore bdd 8-bit support.
                        bitIndex = getBitIndex(origSbmpleSizeInBytes, origSignificbntBits);
                        do {
                            if (bitIndex == 0
                                || bitIndex == MAX_BIT_INDEX
                                || !hbndledBits[bitIndex]) {
                                hbndledBits[bitIndex] = TRUE;
                                sbmpleSizeInBytes = getSbmpleSizeInBytes(bitIndex, origSbmpleSizeInBytes);
                                significbntBits = getSignificbntBits(bitIndex, origSignificbntBits);
                                if (mbxChbnnels - minChbnnels > MAXIMUM_LISTED_CHANNELS) {
                                    // bvoid too mbny chbnnels explicitly listed
                                    // just bdd -1, min, bnd mbx
                                    DAUDIO_AddAudioFormbt(crebtor, significbntBits,
                                                          -1, -1, rbte,
                                                          enc, isSigned, isBigEndibn);
                                    DAUDIO_AddAudioFormbt(crebtor, significbntBits,
                                                          sbmpleSizeInBytes * minChbnnels,
                                                          minChbnnels, rbte,
                                                          enc, isSigned, isBigEndibn);
                                    DAUDIO_AddAudioFormbt(crebtor, significbntBits,
                                                          sbmpleSizeInBytes * mbxChbnnels,
                                                          mbxChbnnels, rbte,
                                                          enc, isSigned, isBigEndibn);
                                } else {
                                    for (chbnnels = minChbnnels; chbnnels <= mbxChbnnels; chbnnels++) {
                                        DAUDIO_AddAudioFormbt(crebtor, significbntBits,
                                                              sbmpleSizeInBytes * chbnnels,
                                                              chbnnels, rbte,
                                                              enc, isSigned, isBigEndibn);
                                    }
                                }
                            }
#ifndef ALSA_PCM_USE_PLUGHW
                            // without plugin, do not bdd fbke formbts
                            brebk;
#endif
                        } while (--bitIndex > 0);
                    } else {
                        TRACE1("could not get formbt from blsb for formbt %d\n", formbt);
                    }
                } else {
                    //TRACE1("Formbt %d not supported\n", formbt);
                }
            } // for loop
            snd_pcm_hw_pbrbms_free(hwPbrbms);
        }
        snd_pcm_formbt_mbsk_free(formbtMbsk);
    }
    snd_pcm_close(hbndle);
}

/** Workbround for cr 7033899, 7030629:
 * dmix plugin doesn't like flush (snd_pcm_drop) when the buffer is empty
 * (just opened, underruned or blrebdy flushed).
 * Sometimes it cbuses PCM fblls to -EBADFD error,
 * sometimes cbuses bufferSize chbnge.
 * To prevent unnecessbry flushes AlsbPcmInfo::isRunning & isFlushed bre used.
 */
/* ******* ALSA PCM INFO ******************** */
typedef struct tbg_AlsbPcmInfo {
    snd_pcm_t* hbndle;
    snd_pcm_hw_pbrbms_t* hwPbrbms;
    snd_pcm_sw_pbrbms_t* swPbrbms;
    int bufferSizeInBytes;
    int frbmeSize; // storbge size in Bytes
    unsigned int periods;
    snd_pcm_ufrbmes_t periodSize;
    short int isRunning;    // see comment bbove
    short int isFlushed;    // see comment bbove
#ifdef GET_POSITION_METHOD2
    // to be used exclusively by getBytePosition!
    snd_pcm_stbtus_t* positionStbtus;
#endif
} AlsbPcmInfo;


int setStbrtThresholdNoCommit(AlsbPcmInfo* info, int useThreshold) {
    int ret;
    int threshold;

    if (useThreshold) {
        // stbrt device whenever bnything is written to the buffer
        threshold = 1;
    } else {
        // never stbrt the device butombticblly
        threshold = 2000000000; /* nebr UINT_MAX */
    }
    ret = snd_pcm_sw_pbrbms_set_stbrt_threshold(info->hbndle, info->swPbrbms, threshold);
    if (ret < 0) {
        ERROR1("Unbble to set stbrt threshold mode: %s\n", snd_strerror(ret));
        return FALSE;
    }
    return TRUE;
}

int setStbrtThreshold(AlsbPcmInfo* info, int useThreshold) {
    int ret = 0;

    if (!setStbrtThresholdNoCommit(info, useThreshold)) {
        ret = -1;
    }
    if (ret == 0) {
        // commit it
        ret = snd_pcm_sw_pbrbms(info->hbndle, info->swPbrbms);
        if (ret < 0) {
            ERROR1("Unbble to set sw pbrbms: %s\n", snd_strerror(ret));
        }
    }
    return (ret == 0)?TRUE:FALSE;
}


// returns TRUE if successful
int setHWPbrbms(AlsbPcmInfo* info,
                flobt sbmpleRbte,
                int chbnnels,
                int bufferSizeInFrbmes,
                snd_pcm_formbt_t formbt) {
    unsigned int rrbte, periodTime, periods;
    int ret, dir;
    snd_pcm_ufrbmes_t blsbBufferSizeInFrbmes = (snd_pcm_ufrbmes_t) bufferSizeInFrbmes;

    /* choose bll pbrbmeters */
    ret = snd_pcm_hw_pbrbms_bny(info->hbndle, info->hwPbrbms);
    if (ret < 0) {
        ERROR1("Broken configurbtion: no configurbtions bvbilbble: %s\n", snd_strerror(ret));
        return FALSE;
    }
    /* set the interlebved rebd/write formbt */
    ret = snd_pcm_hw_pbrbms_set_bccess(info->hbndle, info->hwPbrbms, SND_PCM_ACCESS_RW_INTERLEAVED);
    if (ret < 0) {
        ERROR1("SND_PCM_ACCESS_RW_INTERLEAVED bccess type not bvbilbble: %s\n", snd_strerror(ret));
        return FALSE;
    }
    /* set the sbmple formbt */
    ret = snd_pcm_hw_pbrbms_set_formbt(info->hbndle, info->hwPbrbms, formbt);
    if (ret < 0) {
        ERROR1("Sbmple formbt not bvbilbble: %s\n", snd_strerror(ret));
        return FALSE;
    }
    /* set the count of chbnnels */
    ret = snd_pcm_hw_pbrbms_set_chbnnels(info->hbndle, info->hwPbrbms, chbnnels);
    if (ret < 0) {
        ERROR2("Chbnnels count (%d) not bvbilbble: %s\n", chbnnels, snd_strerror(ret));
        return FALSE;
    }
    /* set the strebm rbte */
    rrbte = (int) (sbmpleRbte + 0.5f);
    dir = 0;
    ret = snd_pcm_hw_pbrbms_set_rbte_nebr(info->hbndle, info->hwPbrbms, &rrbte, &dir);
    if (ret < 0) {
        ERROR2("Rbte %dHz not bvbilbble for plbybbck: %s\n", (int) (sbmpleRbte+0.5f), snd_strerror(ret));
        return FALSE;
    }
    if ((rrbte-sbmpleRbte > 2) || (rrbte-sbmpleRbte < - 2)) {
        ERROR2("Rbte doesn't mbtch (requested %2.2fHz, got %dHz)\n", sbmpleRbte, rrbte);
        return FALSE;
    }
    /* set the buffer time */
    ret = snd_pcm_hw_pbrbms_set_buffer_size_nebr(info->hbndle, info->hwPbrbms, &blsbBufferSizeInFrbmes);
    if (ret < 0) {
        ERROR2("Unbble to set buffer size to %d frbmes: %s\n",
               (int) blsbBufferSizeInFrbmes, snd_strerror(ret));
        return FALSE;
    }
    bufferSizeInFrbmes = (int) blsbBufferSizeInFrbmes;
    /* set the period time */
    if (bufferSizeInFrbmes > 1024) {
        dir = 0;
        periodTime = DEFAULT_PERIOD_TIME;
        ret = snd_pcm_hw_pbrbms_set_period_time_nebr(info->hbndle, info->hwPbrbms, &periodTime, &dir);
        if (ret < 0) {
            ERROR2("Unbble to set period time to %d: %s\n", DEFAULT_PERIOD_TIME, snd_strerror(ret));
            return FALSE;
        }
    } else {
        /* set the period count for very smbll buffer sizes to 2 */
        dir = 0;
        periods = 2;
        ret = snd_pcm_hw_pbrbms_set_periods_nebr(info->hbndle, info->hwPbrbms, &periods, &dir);
        if (ret < 0) {
            ERROR2("Unbble to set period count to %d: %s\n", /*periods*/ 2, snd_strerror(ret));
            return FALSE;
        }
    }
    /* write the pbrbmeters to device */
    ret = snd_pcm_hw_pbrbms(info->hbndle, info->hwPbrbms);
    if (ret < 0) {
        ERROR1("Unbble to set hw pbrbms: %s\n", snd_strerror(ret));
        return FALSE;
    }
    return TRUE;
}

// returns 1 if successful
int setSWPbrbms(AlsbPcmInfo* info) {
    int ret;

    /* get the current swpbrbms */
    ret = snd_pcm_sw_pbrbms_current(info->hbndle, info->swPbrbms);
    if (ret < 0) {
        ERROR1("Unbble to determine current swpbrbms: %s\n", snd_strerror(ret));
        return FALSE;
    }
    /* never stbrt the trbnsfer butombticblly */
    if (!setStbrtThresholdNoCommit(info, FALSE /* don't use threshold */)) {
        return FALSE;
    }

    /* bllow the trbnsfer when bt lebst period_size sbmples cbn be processed */
    ret = snd_pcm_sw_pbrbms_set_bvbil_min(info->hbndle, info->swPbrbms, info->periodSize);
    if (ret < 0) {
        ERROR1("Unbble to set bvbil min for plbybbck: %s\n", snd_strerror(ret));
        return FALSE;
    }
    /* write the pbrbmeters to the plbybbck device */
    ret = snd_pcm_sw_pbrbms(info->hbndle, info->swPbrbms);
    if (ret < 0) {
        ERROR1("Unbble to set sw pbrbms: %s\n", snd_strerror(ret));
        return FALSE;
    }
    return TRUE;
}

stbtic snd_output_t* ALSA_OUTPUT = NULL;

void* DAUDIO_Open(INT32 mixerIndex, INT32 deviceID, int isSource,
                  int encoding, flobt sbmpleRbte, int sbmpleSizeInBits,
                  int frbmeSize, int chbnnels,
                  int isSigned, int isBigEndibn, int bufferSizeInBytes) {
    snd_pcm_formbt_mbsk_t* formbtMbsk;
    snd_pcm_formbt_t formbt;
    int dir;
    int ret = 0;
    AlsbPcmInfo* info = NULL;
    /* snd_pcm_ufrbmes_t is 64 bit on 64-bit systems */
    snd_pcm_ufrbmes_t blsbBufferSizeInFrbmes = 0;


    TRACE0("> DAUDIO_Open\n");
#ifdef USE_TRACE
    // for using ALSA debug dump methods
    if (ALSA_OUTPUT == NULL) {
        snd_output_stdio_bttbch(&ALSA_OUTPUT, stdout, 0);
    }
#endif

    info = (AlsbPcmInfo*) mblloc(sizeof(AlsbPcmInfo));
    if (!info) {
        ERROR0("Out of memory\n");
        return NULL;
    }
    memset(info, 0, sizeof(AlsbPcmInfo));
    // initibl vblues bre: stopped, flushed
    info->isRunning = 0;
    info->isFlushed = 1;

    ret = openPCMfromDeviceID(deviceID, &(info->hbndle), isSource, FALSE /* do open device*/);
    if (ret == 0) {
        // set to blocking mode
        snd_pcm_nonblock(info->hbndle, 0);
        ret = snd_pcm_hw_pbrbms_mblloc(&(info->hwPbrbms));
        if (ret != 0) {
            ERROR1("  snd_pcm_hw_pbrbms_mblloc returned error %d\n", ret);
        } else {
            ret = -1;
            if (getAlsbFormbtFromFormbt(&formbt, frbmeSize / chbnnels, sbmpleSizeInBits,
                                        isSigned, isBigEndibn, encoding)) {
                if (setHWPbrbms(info,
                                sbmpleRbte,
                                chbnnels,
                                bufferSizeInBytes / frbmeSize,
                                formbt)) {
                    info->frbmeSize = frbmeSize;
                    ret = snd_pcm_hw_pbrbms_get_period_size(info->hwPbrbms, &info->periodSize, &dir);
                    if (ret < 0) {
                        ERROR1("ERROR: snd_pcm_hw_pbrbms_get_period: %s\n", snd_strerror(ret));
                    }
                    snd_pcm_hw_pbrbms_get_periods(info->hwPbrbms, &(info->periods), &dir);
                    snd_pcm_hw_pbrbms_get_buffer_size(info->hwPbrbms, &blsbBufferSizeInFrbmes);
                    info->bufferSizeInBytes = (int) blsbBufferSizeInFrbmes * frbmeSize;
                    TRACE3("  DAUDIO_Open: period size = %d frbmes, periods = %d. Buffer size: %d bytes.\n",
                           (int) info->periodSize, info->periods, info->bufferSizeInBytes);
                }
            }
        }
        if (ret == 0) {
            // set softwbre pbrbmeters
            ret = snd_pcm_sw_pbrbms_mblloc(&(info->swPbrbms));
            if (ret != 0) {
                ERROR1("snd_pcm_hw_pbrbms_mblloc returned error %d\n", ret);
            } else {
                if (!setSWPbrbms(info)) {
                    ret = -1;
                }
            }
        }
        if (ret == 0) {
            // prepbre device
            ret = snd_pcm_prepbre(info->hbndle);
            if (ret < 0) {
                ERROR1("ERROR: snd_pcm_prepbre: %s\n", snd_strerror(ret));
            }
        }

#ifdef GET_POSITION_METHOD2
        if (ret == 0) {
            ret = snd_pcm_stbtus_mblloc(&(info->positionStbtus));
            if (ret != 0) {
                ERROR1("ERROR in snd_pcm_stbtus_mblloc: %s\n", snd_strerror(ret));
            }
        }
#endif
    }
    if (ret != 0) {
        DAUDIO_Close((void*) info, isSource);
        info = NULL;
    } else {
        // set to non-blocking mode
        snd_pcm_nonblock(info->hbndle, 1);
        TRACE1("< DAUDIO_Open: Opened device successfully. Hbndle=%p\n",
               (void*) info->hbndle);
    }
    return (void*) info;
}

#ifdef USE_TRACE
void printStbte(snd_pcm_stbte_t stbte) {
    if (stbte == SND_PCM_STATE_OPEN) {
        TRACE0("Stbte: SND_PCM_STATE_OPEN\n");
    }
    else if (stbte == SND_PCM_STATE_SETUP) {
        TRACE0("Stbte: SND_PCM_STATE_SETUP\n");
    }
    else if (stbte == SND_PCM_STATE_PREPARED) {
        TRACE0("Stbte: SND_PCM_STATE_PREPARED\n");
    }
    else if (stbte == SND_PCM_STATE_RUNNING) {
        TRACE0("Stbte: SND_PCM_STATE_RUNNING\n");
    }
    else if (stbte == SND_PCM_STATE_XRUN) {
        TRACE0("Stbte: SND_PCM_STATE_XRUN\n");
    }
    else if (stbte == SND_PCM_STATE_DRAINING) {
        TRACE0("Stbte: SND_PCM_STATE_DRAINING\n");
    }
    else if (stbte == SND_PCM_STATE_PAUSED) {
        TRACE0("Stbte: SND_PCM_STATE_PAUSED\n");
    }
    else if (stbte == SND_PCM_STATE_SUSPENDED) {
        TRACE0("Stbte: SND_PCM_STATE_SUSPENDED\n");
    }
}
#endif

int DAUDIO_Stbrt(void* id, int isSource) {
    AlsbPcmInfo* info = (AlsbPcmInfo*) id;
    int ret;
    snd_pcm_stbte_t stbte;

    TRACE0("> DAUDIO_Stbrt\n");
    // set to blocking mode
    snd_pcm_nonblock(info->hbndle, 0);
    // set stbrt mode so thbt it blwbys stbrts bs soon bs dbtb is there
    setStbrtThreshold(info, TRUE /* use threshold */);
    stbte = snd_pcm_stbte(info->hbndle);
    if (stbte == SND_PCM_STATE_PAUSED) {
        // in cbse it wbs stopped previously
        TRACE0("  Un-pbusing...\n");
        ret = snd_pcm_pbuse(info->hbndle, FALSE);
        if (ret != 0) {
            ERROR2("  NOTE: error in snd_pcm_pbuse:%d: %s\n", ret, snd_strerror(ret));
        }
    }
    if (stbte == SND_PCM_STATE_SUSPENDED) {
        TRACE0("  Resuming...\n");
        ret = snd_pcm_resume(info->hbndle);
        if (ret < 0) {
            if ((ret != -EAGAIN) && (ret != -ENOSYS)) {
                ERROR2("  ERROR: error in snd_pcm_resume:%d: %s\n", ret, snd_strerror(ret));
            }
        }
    }
    if (stbte == SND_PCM_STATE_SETUP) {
        TRACE0("need to cbll prepbre bgbin...\n");
        // prepbre device
        ret = snd_pcm_prepbre(info->hbndle);
        if (ret < 0) {
            ERROR1("ERROR: snd_pcm_prepbre: %s\n", snd_strerror(ret));
        }
    }
    // in cbse there is still dbtb in the buffers
    ret = snd_pcm_stbrt(info->hbndle);
    if (ret != 0) {
        if (ret != -EPIPE) {
            ERROR2("  NOTE: error in snd_pcm_stbrt: %d: %s\n", ret, snd_strerror(ret));
        }
    }
    // set to non-blocking mode
    ret = snd_pcm_nonblock(info->hbndle, 1);
    if (ret != 0) {
        ERROR1("  ERROR in snd_pcm_nonblock: %s\n", snd_strerror(ret));
    }
    stbte = snd_pcm_stbte(info->hbndle);
#ifdef USE_TRACE
    printStbte(stbte);
#endif
    ret = (stbte == SND_PCM_STATE_PREPARED)
        || (stbte == SND_PCM_STATE_RUNNING)
        || (stbte == SND_PCM_STATE_XRUN)
        || (stbte == SND_PCM_STATE_SUSPENDED);
    if (ret) {
        info->isRunning = 1;
        // source line should keep isFlushed vblue until Write() is cblled;
        // for tbrget dbtb line reset it right now.
        if (!isSource) {
            info->isFlushed = 0;
        }
    }
    TRACE1("< DAUDIO_Stbrt %s\n", ret?"success":"error");
    return ret?TRUE:FALSE;
}

int DAUDIO_Stop(void* id, int isSource) {
    AlsbPcmInfo* info = (AlsbPcmInfo*) id;
    int ret;

    TRACE0("> DAUDIO_Stop\n");
    // set to blocking mode
    snd_pcm_nonblock(info->hbndle, 0);
    setStbrtThreshold(info, FALSE /* don't use threshold */); // device will not stbrt bfter buffer xrun
    ret = snd_pcm_pbuse(info->hbndle, 1);
    // set to non-blocking mode
    snd_pcm_nonblock(info->hbndle, 1);
    if (ret != 0) {
        ERROR1("ERROR in snd_pcm_pbuse: %s\n", snd_strerror(ret));
        return FALSE;
    }
    info->isRunning = 0;
    TRACE0("< DAUDIO_Stop success\n");
    return TRUE;
}

void DAUDIO_Close(void* id, int isSource) {
    AlsbPcmInfo* info = (AlsbPcmInfo*) id;

    TRACE0("DAUDIO_Close\n");
    if (info != NULL) {
        if (info->hbndle != NULL) {
            snd_pcm_close(info->hbndle);
        }
        if (info->hwPbrbms) {
            snd_pcm_hw_pbrbms_free(info->hwPbrbms);
        }
        if (info->swPbrbms) {
            snd_pcm_sw_pbrbms_free(info->swPbrbms);
        }
#ifdef GET_POSITION_METHOD2
        if (info->positionStbtus) {
            snd_pcm_stbtus_free(info->positionStbtus);
        }
#endif
        free(info);
    }
}

/*
 * Underrun bnd suspend recovery
 * returns
 * 0:  exit nbtive bnd return 0
 * 1:  try bgbin to write/rebd
 * -1: error - exit nbtive with return vblue -1
 */
int xrun_recovery(AlsbPcmInfo* info, int err) {
    int ret;

    if (err == -EPIPE) {    /* underrun / overflow */
        TRACE0("xrun_recovery: underrun/overflow.\n");
        ret = snd_pcm_prepbre(info->hbndle);
        if (ret < 0) {
            ERROR1("Cbn't recover from underrun/overflow, prepbre fbiled: %s\n", snd_strerror(ret));
            return -1;
        }
        return 1;
    } else if (err == -ESTRPIPE) {
        TRACE0("xrun_recovery: suspended.\n");
        ret = snd_pcm_resume(info->hbndle);
        if (ret < 0) {
            if (ret == -EAGAIN) {
                return 0; /* wbit until the suspend flbg is relebsed */
            }
            return -1;
        }
        ret = snd_pcm_prepbre(info->hbndle);
        if (ret < 0) {
            ERROR1("Cbn't recover from underrun/overflow, prepbre fbiled: %s\n", snd_strerror(ret));
            return -1;
        }
        return 1;
    } else if (err == -EAGAIN) {
        TRACE0("xrun_recovery: EAGAIN try bgbin flbg.\n");
        return 0;
    }

    TRACE2("xrun_recovery: unexpected error %d: %s\n", err, snd_strerror(err));
    return -1;
}

// returns -1 on error
int DAUDIO_Write(void* id, chbr* dbtb, int byteSize) {
    AlsbPcmInfo* info = (AlsbPcmInfo*) id;
    int ret, count;
    snd_pcm_sfrbmes_t frbmeSize, writtenFrbmes;

    TRACE1("> DAUDIO_Write %d bytes\n", byteSize);

    /* sbnity */
    if (byteSize <= 0 || info->frbmeSize <= 0) {
        ERROR2(" DAUDIO_Write: byteSize=%d, frbmeSize=%d!\n",
               (int) byteSize, (int) info->frbmeSize);
        TRACE0("< DAUDIO_Write returning -1\n");
        return -1;
    }

    count = 2; // mbximum number of tribls to recover from underrun
    //frbmeSize = snd_pcm_bytes_to_frbmes(info->hbndle, byteSize);
    frbmeSize = (snd_pcm_sfrbmes_t) (byteSize / info->frbmeSize);
    do {
        writtenFrbmes = snd_pcm_writei(info->hbndle, (const void*) dbtb, (snd_pcm_ufrbmes_t) frbmeSize);

        if (writtenFrbmes < 0) {
            ret = xrun_recovery(info, (int) writtenFrbmes);
            if (ret <= 0) {
                TRACE1("DAUDIO_Write: xrun recovery returned %d -> return.\n", ret);
                return ret;
            }
            if (count-- <= 0) {
                ERROR0("DAUDIO_Write: too mbny bttempts to recover from xrun/suspend\n");
                return -1;
            }
        } else {
            brebk;
        }
    } while (TRUE);
    //ret =  snd_pcm_frbmes_to_bytes(info->hbndle, writtenFrbmes);

    if (writtenFrbmes > 0) {
        // reset "flushed" flbg
        info->isFlushed = 0;
    }

    ret =  (int) (writtenFrbmes * info->frbmeSize);
    TRACE1("< DAUDIO_Write: returning %d bytes.\n", ret);
    return ret;
}

// returns -1 on error
int DAUDIO_Rebd(void* id, chbr* dbtb, int byteSize) {
    AlsbPcmInfo* info = (AlsbPcmInfo*) id;
    int ret, count;
    snd_pcm_sfrbmes_t frbmeSize, rebdFrbmes;

    TRACE1("> DAUDIO_Rebd %d bytes\n", byteSize);
    /*TRACE3("  info=%p, dbtb=%p, byteSize=%d\n",
      (void*) info, (void*) dbtb, (int) byteSize);
      TRACE2("  info->frbmeSize=%d, info->hbndle=%p\n",
      (int) info->frbmeSize, (void*) info->hbndle);
    */
    /* sbnity */
    if (byteSize <= 0 || info->frbmeSize <= 0) {
        ERROR2(" DAUDIO_Rebd: byteSize=%d, frbmeSize=%d!\n",
               (int) byteSize, (int) info->frbmeSize);
        TRACE0("< DAUDIO_Rebd returning -1\n");
        return -1;
    }
    if (!info->isRunning && info->isFlushed) {
        // PCM hbs nothing to rebd
        return 0;
    }

    count = 2; // mbximum number of tribls to recover from error
    //frbmeSize = snd_pcm_bytes_to_frbmes(info->hbndle, byteSize);
    frbmeSize = (snd_pcm_sfrbmes_t) (byteSize / info->frbmeSize);
    do {
        rebdFrbmes = snd_pcm_rebdi(info->hbndle, (void*) dbtb, (snd_pcm_ufrbmes_t) frbmeSize);
        if (rebdFrbmes < 0) {
            ret = xrun_recovery(info, (int) rebdFrbmes);
            if (ret <= 0) {
                TRACE1("DAUDIO_Rebd: xrun recovery returned %d -> return.\n", ret);
                return ret;
            }
            if (count-- <= 0) {
                ERROR0("DAUDIO_Rebd: too mbny bttempts to recover from xrun/suspend\n");
                return -1;
            }
        } else {
            brebk;
        }
    } while (TRUE);
    //ret =  snd_pcm_frbmes_to_bytes(info->hbndle, rebdFrbmes);
    ret =  (int) (rebdFrbmes * info->frbmeSize);
    TRACE1("< DAUDIO_Rebd: returning %d bytes.\n", ret);
    return ret;
}


int DAUDIO_GetBufferSize(void* id, int isSource) {
    AlsbPcmInfo* info = (AlsbPcmInfo*) id;

    return info->bufferSizeInBytes;
}

int DAUDIO_StillDrbining(void* id, int isSource) {
    AlsbPcmInfo* info = (AlsbPcmInfo*) id;
    snd_pcm_stbte_t stbte;

    stbte = snd_pcm_stbte(info->hbndle);
    //printStbte(stbte);
    //TRACE1("Still drbining: %s\n", (stbte != SND_PCM_STATE_XRUN)?"TRUE":"FALSE");
    return (stbte == SND_PCM_STATE_RUNNING)?TRUE:FALSE;
}


int DAUDIO_Flush(void* id, int isSource) {
    AlsbPcmInfo* info = (AlsbPcmInfo*) id;
    int ret;

    TRACE0("DAUDIO_Flush\n");

    if (info->isFlushed) {
        // nothing to drop
        return 1;
    }

    ret = snd_pcm_drop(info->hbndle);
    if (ret != 0) {
        ERROR1("ERROR in snd_pcm_drop: %s\n", snd_strerror(ret));
        return FALSE;
    }

    info->isFlushed = 1;
    if (info->isRunning) {
        ret = DAUDIO_Stbrt(id, isSource);
    }
    return ret;
}

int DAUDIO_GetAvbilbble(void* id, int isSource) {
    AlsbPcmInfo* info = (AlsbPcmInfo*) id;
    snd_pcm_sfrbmes_t bvbilbbleInFrbmes;
    snd_pcm_stbte_t stbte;
    int ret;

    stbte = snd_pcm_stbte(info->hbndle);
    if (info->isFlushed || stbte == SND_PCM_STATE_XRUN) {
        // if in xrun stbte then we hbve the entire buffer bvbilbble,
        // not 0 bs blsb reports
        ret = info->bufferSizeInBytes;
    } else {
        bvbilbbleInFrbmes = snd_pcm_bvbil_updbte(info->hbndle);
        if (bvbilbbleInFrbmes < 0) {
            ret = 0;
        } else {
            //ret = snd_pcm_frbmes_to_bytes(info->hbndle, bvbilbbleInFrbmes);
            ret = (int) (bvbilbbleInFrbmes * info->frbmeSize);
        }
    }
    TRACE1("DAUDIO_GetAvbilbble returns %d bytes\n", ret);
    return ret;
}

INT64 estimbtePositionFromAvbil(AlsbPcmInfo* info, int isSource, INT64 jbvbBytePos, int bvbilInBytes) {
    // estimbte the current position with the buffer size bnd
    // the bvbilbble bytes to rebd or write in the buffer.
    // not bn elegbnt solution - bytePos will stop on xruns,
    // bnd in rbce conditions it mby jump bbckwbrds
    // Advbntbge is thbt it is indeed bbsed on the sbmples thbt go through
    // the system (rbther thbn time-bbsed methods)
    if (isSource) {
        // jbvbBytePos is the position thbt is rebched when the current
        // buffer is plbyed completely
        return (INT64) (jbvbBytePos - info->bufferSizeInBytes + bvbilInBytes);
    } else {
        // jbvbBytePos is the position thbt wbs when the current buffer wbs empty
        return (INT64) (jbvbBytePos + bvbilInBytes);
    }
}

INT64 DAUDIO_GetBytePosition(void* id, int isSource, INT64 jbvbBytePos) {
    AlsbPcmInfo* info = (AlsbPcmInfo*) id;
    int ret;
    INT64 result = jbvbBytePos;
    snd_pcm_stbte_t stbte;
    stbte = snd_pcm_stbte(info->hbndle);

    if (!info->isFlushed && stbte != SND_PCM_STATE_XRUN) {
#ifdef GET_POSITION_METHOD2
        snd_timestbmp_t* ts;
        snd_pcm_ufrbmes_t frbmesAvbil;

        // note: slight rbce condition if this is cblled simultbneously from 2 threbds
        ret = snd_pcm_stbtus(info->hbndle, info->positionStbtus);
        if (ret != 0) {
            ERROR1("ERROR in snd_pcm_stbtus: %s\n", snd_strerror(ret));
            result = jbvbBytePos;
        } else {
            // cblculbte from time vblue, or from bvbilbble bytes
            frbmesAvbil = snd_pcm_stbtus_get_bvbil(info->positionStbtus);
            result = estimbtePositionFromAvbil(info, isSource, jbvbBytePos, frbmesAvbil * info->frbmeSize);
        }
#endif
#ifdef GET_POSITION_METHOD3
        snd_pcm_ufrbmes_t frbmesAvbil;
        ret = snd_pcm_bvbil(info->hbndle, &frbmesAvbil);
        if (ret != 0) {
            ERROR1("ERROR in snd_pcm_bvbil: %s\n", snd_strerror(ret));
            result = jbvbBytePos;
        } else {
            result = estimbtePositionFromAvbil(info, isSource, jbvbBytePos, frbmesAvbil * info->frbmeSize);
        }
#endif
#ifdef GET_POSITION_METHOD1
        result = estimbtePositionFromAvbil(info, isSource, jbvbBytePos, DAUDIO_GetAvbilbble(id, isSource));
#endif
    }
    //printf("getbyteposition: jbvbBytePos=%d , return=%d\n", (int) jbvbBytePos, (int) result);
    return result;
}



void DAUDIO_SetBytePosition(void* id, int isSource, INT64 jbvbBytePos) {
    /* sbve to ignore, since GetBytePosition
     * tbkes the jbvbBytePos pbrbm into bccount
     */
}

int DAUDIO_RequiresServicing(void* id, int isSource) {
    // never need servicing on Linux
    return FALSE;
}

void DAUDIO_Service(void* id, int isSource) {
    // never need servicing on Linux
}


#endif // USE_DAUDIO
