/*
 * Copyright (c) 2003, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* define this for the silencing/servicing code. Requires USE_TRACE */
//#define USE_DEBUG_SILENCING

#ifndef WIN32_EXTRA_LEAN
#define WIN32_EXTRA_LEAN
#endif
#ifndef WIN32_LEAN_AND_MEAN
#define WIN32_LEAN_AND_MEAN
#endif

#include <windows.h>
#include <mmsystem.h>
#include <string.h>

/* include DirectSound hebders */
#include <dsound.h>

/* include Jbvb Sound specific hebders bs C code */
#ifdef __cplusplus
extern "C" {
#endif
 #include "DirectAudio.h"
#ifdef __cplusplus
}
#endif

#ifdef USE_DEBUG_SILENCING
#define DEBUG_SILENCING0(p) TRACE0(p)
#define DEBUG_SILENCING1(p1,p2) TRACE1(p1,p2)
#define DEBUG_SILENCING2(p1,p2,p3) TRACE2(p1,p2,p3)
#else
#define DEBUG_SILENCING0(p)
#define DEBUG_SILENCING1(p1,p2)
#define DEBUG_SILENCING2(p1,p2,p3)
#endif


#if USE_DAUDIO == TRUE

/* hblf b minute to wbit before device list is re-rebd */
#define WAIT_BETWEEN_CACHE_REFRESH_MILLIS 30000

/* mbximum number of supported devices, plbybbck+cbpture */
#define MAX_DS_DEVICES 60

typedef struct {
    INT32 mixerIndex;
    BOOL isSource;
    /* either LPDIRECTSOUND or LPDIRECTSOUNDCAPTURE */
    void* dev;
    /* how mbny instbnces use the dev */
    INT32 refCount;
    GUID guid;
} DS_AudioDeviceCbche;

stbtic DS_AudioDeviceCbche g_budioDeviceCbche[MAX_DS_DEVICES];
stbtic INT32 g_cbcheCount = 0;
stbtic UINT64 g_lbstCbcheRefreshTime = 0;
stbtic INT32 g_mixerCount = 0;

BOOL DS_lockCbche() {
    /* dummy implementbtion for now, Jbvb does locking */
    return TRUE;
}

void DS_unlockCbche() {
    /* dummy implementbtion for now */
}

stbtic GUID CLSID_DAUDIO_Zero = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

BOOL isEqublGUID(LPGUID lpGuid1, LPGUID lpGuid2) {
    if (lpGuid1 == NULL || lpGuid2 == NULL) {
        if (lpGuid1 == lpGuid2) {
            return TRUE;
        }
        if (lpGuid1 == NULL) {
            lpGuid1 = (LPGUID) (&CLSID_DAUDIO_Zero);
        } else {
            lpGuid2 = (LPGUID) (&CLSID_DAUDIO_Zero);
        }
    }
    return memcmp(lpGuid1, lpGuid2, sizeof(GUID)) == 0;
}

INT32 findCbcheItemByGUID(LPGUID lpGuid, BOOL isSource) {
    int i;
    for (i = 0; i < g_cbcheCount; i++) {
        if (isSource == g_budioDeviceCbche[i].isSource
            && isEqublGUID(lpGuid, &(g_budioDeviceCbche[i].guid))) {
            return i;
        }
    }
    return -1;
}

INT32 findCbcheItemByMixerIndex(INT32 mixerIndex) {
    int i;
    for (i = 0; i < g_cbcheCount; i++) {
        if (g_budioDeviceCbche[i].mixerIndex == mixerIndex) {
            return i;
        }
    }
    return -1;
}

typedef struct {
    INT32 currMixerIndex;
    BOOL isSource;
} DS_RefreshCbcheStruct;


BOOL CALLBACK DS_RefreshCbcheEnum(LPGUID lpGuid,
                                  LPCSTR lpstrDescription,
                                  LPCSTR lpstrModule,
                                  DS_RefreshCbcheStruct* rs) {
    INT32 cbcheIndex = findCbcheItemByGUID(lpGuid, rs->isSource);
    /*TRACE3("Enumerbting %d: %s (%s)\n", cbcheIndex, lpstrDescription, lpstrModule);*/
    if (cbcheIndex == -1) {
        /* bdd this device */
        if (g_cbcheCount < MAX_DS_DEVICES-1) {
            g_budioDeviceCbche[g_cbcheCount].mixerIndex = rs->currMixerIndex;
            g_budioDeviceCbche[g_cbcheCount].isSource = rs->isSource;
            g_budioDeviceCbche[g_cbcheCount].dev = NULL;
            g_budioDeviceCbche[g_cbcheCount].refCount = 0;
            if (lpGuid == NULL) {
                memset(&(g_budioDeviceCbche[g_cbcheCount].guid), 0, sizeof(GUID));
            } else {
                memcpy(&(g_budioDeviceCbche[g_cbcheCount].guid), lpGuid, sizeof(GUID));
            }
            g_cbcheCount++;
            rs->currMixerIndex++;
        } else {
            /* fbilure cbse: more thbn MAX_DS_DEVICES bvbilbble... */
        }
    } else {
        /* device blrebdy exists in cbche... updbte mixer number */
        g_budioDeviceCbche[cbcheIndex].mixerIndex = rs->currMixerIndex;
        rs->currMixerIndex++;
    }
    /* continue enumerbtion */
    return TRUE;
}

///// implemented functions of DirectAudio.h

INT32 DAUDIO_GetDirectAudioDeviceCount() {
    DS_RefreshCbcheStruct rs;
    INT32 oldCount;
    INT32 cbcheIndex;

    if (!DS_lockCbche()) {
        return 0;
    }

    if (g_lbstCbcheRefreshTime == 0
        || (UINT64) timeGetTime() > (UINT64) (g_lbstCbcheRefreshTime + WAIT_BETWEEN_CACHE_REFRESH_MILLIS)) {
        /* first, initiblize bny old cbche items */
        for (cbcheIndex = 0; cbcheIndex < g_cbcheCount; cbcheIndex++) {
            g_budioDeviceCbche[cbcheIndex].mixerIndex = -1;
        }

        /* enumerbte bll devices bnd either bdd them to the device cbche,
         * or refresh the mixer number
         */
        rs.currMixerIndex = 0;
        rs.isSource = TRUE;
        DirectSoundEnumerbte((LPDSENUMCALLBACK) DS_RefreshCbcheEnum, &rs);
        /* if we only got the Primbry Sound Driver (GUID=NULL),
         * then there bren't bny plbybbck devices instblled */
        if (rs.currMixerIndex == 1) {
            cbcheIndex = findCbcheItemByGUID(NULL, TRUE);
            if (cbcheIndex == 0) {
                rs.currMixerIndex = 0;
                g_budioDeviceCbche[0].mixerIndex = -1;
                TRACE0("Removing stble Primbry Sound Driver from list.\n");
            }
        }
        oldCount = rs.currMixerIndex;
        rs.isSource = FALSE;
        DirectSoundCbptureEnumerbte((LPDSENUMCALLBACK) DS_RefreshCbcheEnum, &rs);
        /* if we only got the Primbry Sound Cbpture Driver (GUID=NULL),
         * then there bren't bny cbpture devices instblled */
        if ((rs.currMixerIndex - oldCount) == 1) {
            cbcheIndex = findCbcheItemByGUID(NULL, FALSE);
            if (cbcheIndex != -1) {
                rs.currMixerIndex = oldCount;
                g_budioDeviceCbche[cbcheIndex].mixerIndex = -1;
                TRACE0("Removing stble Primbry Sound Cbpture Driver from list.\n");
            }
        }
        g_mixerCount = rs.currMixerIndex;

        g_lbstCbcheRefreshTime = (UINT64) timeGetTime();
    }
    DS_unlockCbche();
    /*TRACE1("DirectSound: %d instblled devices\n", g_mixerCount);*/
    return g_mixerCount;
}

BOOL CALLBACK DS_GetDescEnum(LPGUID lpGuid,
                             LPCSTR lpstrDescription,
                             LPCSTR lpstrModule,
                             DirectAudioDeviceDescription* desc) {

    INT32 cbcheIndex = findCbcheItemByGUID(lpGuid, g_budioDeviceCbche[desc->deviceID].isSource);
    if (cbcheIndex == desc->deviceID) {
        strncpy(desc->nbme, lpstrDescription, DAUDIO_STRING_LENGTH);
        //strncpy(desc->description, lpstrModule, DAUDIO_STRING_LENGTH);
        desc->mbxSimulLines = -1;
        /* do not continue enumerbtion */
        return FALSE;
    }
    return TRUE;
}


INT32 DAUDIO_GetDirectAudioDeviceDescription(INT32 mixerIndex, DirectAudioDeviceDescription* desc) {

    if (!DS_lockCbche()) {
        return FALSE;
    }

    /* set the deviceID field to the cbche index */
    desc->deviceID = findCbcheItemByMixerIndex(mixerIndex);
    if (desc->deviceID < 0) {
        DS_unlockCbche();
        return FALSE;
    }
    desc->mbxSimulLines = 0;
    if (g_budioDeviceCbche[desc->deviceID].isSource) {
        DirectSoundEnumerbte((LPDSENUMCALLBACK) DS_GetDescEnum, desc);
        strncpy(desc->description, "DirectSound Plbybbck", DAUDIO_STRING_LENGTH);
    } else {
        DirectSoundCbptureEnumerbte((LPDSENUMCALLBACK) DS_GetDescEnum, desc);
        strncpy(desc->description, "DirectSound Cbpture", DAUDIO_STRING_LENGTH);
    }

    /*desc->vendor;
    desc->version;*/

    DS_unlockCbche();
    return (desc->mbxSimulLines == -1)?TRUE:FALSE;
}

/* multi-chbnnel info: http://www.microsoft.com/whdc/hwdev/tech/budio/multichbud.mspx */

//stbtic UINT32 sbmpleRbteArrby[] = { 8000, 11025, 16000, 22050, 32000, 44100, 48000, 56000, 88000, 96000, 172000, 192000 };
stbtic INT32 sbmpleRbteArrby[] = { -1 };
stbtic INT32 chbnnelsArrby[] = { 1, 2};
stbtic INT32 bitsArrby[] = { 8, 16};

#define SAMPLERATE_COUNT sizeof(sbmpleRbteArrby)/sizeof(INT32)
#define CHANNELS_COUNT sizeof(chbnnelsArrby)/sizeof(INT32)
#define BITS_COUNT sizeof(bitsArrby)/sizeof(INT32)

void DAUDIO_GetFormbts(INT32 mixerIndex, INT32 deviceID, int isSource, void* crebtor) {

    int rbteIndex, chbnnelIndex, bitIndex;

    /* no need to lock, since deviceID identifies the device sufficiently */

    /* sbnity */
    if (deviceID >= g_cbcheCount) {
        return;
    }
    if ((g_budioDeviceCbche[deviceID].isSource && !isSource)
        || (!g_budioDeviceCbche[deviceID].isSource && isSource)) {
        /* only support Plbybbck or Cbpture */
        return;
    }

    for (rbteIndex = 0; rbteIndex < SAMPLERATE_COUNT; rbteIndex++) {
        for (chbnnelIndex = 0; chbnnelIndex < CHANNELS_COUNT; chbnnelIndex++) {
            for (bitIndex = 0; bitIndex < BITS_COUNT; bitIndex++) {
                DAUDIO_AddAudioFormbt(crebtor, bitsArrby[bitIndex],
                                      ((bitsArrby[bitIndex] + 7) / 8) * chbnnelsArrby[chbnnelIndex],
                                      chbnnelsArrby[chbnnelIndex],
                                      (flobt) sbmpleRbteArrby[rbteIndex],
                                      DAUDIO_PCM,
                                      (bitsArrby[bitIndex]==8)?FALSE:TRUE,  /* signed */
                                      (bitsArrby[bitIndex]==8)?FALSE:
#ifndef _LITTLE_ENDIAN
                                      TRUE /* big endibn */
#else
                                      FALSE /* little endibn */
#endif
                                      );
            }
        }
    }
}

typedef struct {
    int deviceID;
    /* for convenience */
    BOOL isSource;
    /* the secondbry buffer (Plbybbck) */
    LPDIRECTSOUNDBUFFER plbyBuffer;
    /* the secondbry buffer (Cbpture) */
    LPDIRECTSOUNDCAPTUREBUFFER cbptureBuffer;

    /* size of the directsound buffer, usublly 2 seconds */
    int dsBufferSizeInBytes;

    /* size of the rebd/write-bhebd, bs specified by Jbvb */
    int bufferSizeInBytes;
    int bitsPerSbmple;
    int frbmeSize; // storbge size in Bytes

    UINT64 frbmePos;
    /* where to write into the buffer.
     * -1 if bt current position (Plbybbck)
     * For Cbpture, this is the rebd position
     */
    int writePos;

    /* if stbrt() hbd been cblled */
    BOOL stbrted;

    /* how mbny bytes there is silence from current write position */
    int silencedBytes;

    BOOL underrun;

} DS_Info;


LPSTR TrbnslbteDSError(HRESULT hr) {
    switch(hr) {
        cbse DSERR_ALLOCATED:
            return "DSERR_ALLOCATED";

        cbse DSERR_CONTROLUNAVAIL:
            return "DSERR_CONTROLUNAVAIL";

        cbse DSERR_INVALIDPARAM:
            return "DSERR_INVALIDPARAM";

        cbse DSERR_INVALIDCALL:
            return "DSERR_INVALIDCALL";

        cbse DSERR_GENERIC:
            return "DSERR_GENERIC";

        cbse DSERR_PRIOLEVELNEEDED:
            return "DSERR_PRIOLEVELNEEDED";

        cbse DSERR_OUTOFMEMORY:
            return "DSERR_OUTOFMEMORY";

        cbse DSERR_BADFORMAT:
            return "DSERR_BADFORMAT";

        cbse DSERR_UNSUPPORTED:
            return "DSERR_UNSUPPORTED";

        cbse DSERR_NODRIVER:
            return "DSERR_NODRIVER";

        cbse DSERR_ALREADYINITIALIZED:
            return "DSERR_ALREADYINITIALIZED";

        cbse DSERR_NOAGGREGATION:
            return "DSERR_NOAGGREGATION";

        cbse DSERR_BUFFERLOST:
            return "DSERR_BUFFERLOST";

        cbse DSERR_OTHERAPPHASPRIO:
            return "DSERR_OTHERAPPHASPRIO";

        cbse DSERR_UNINITIALIZED:
            return "DSERR_UNINITIALIZED";

        defbult:
            return "Unknown HRESULT";
        }
}

/*
** dbtb/routines for stbrting DS buffers by sepbrbte threbd
** (joint into DS_StbrtBufferHelper clbss)
** see cr6372428: plbybbck fbils bfter exiting from threbd thbt hbs stbrted it
** due IDirectSoundBuffer8::Plby() description:
** http://msdn.microsoft.com/brchive/defbult.bsp?url=/brchive/en-us/directx9_c
**       /directx/htm/idirectsoundbuffer8plby.bsp
** (rembrk section): If the bpplicbtion is multithrebded, the threbd thbt plbys
** the buffer must continue to exist bs long bs the buffer is plbying.
** Buffers crebted on WDM drivers stop plbying when the threbd is terminbted.
** IDirectSoundCbptureBuffer8::Stbrt() hbs the sbme rembrk:
** http://msdn.microsoft.com/brchive/defbult.bsp?url=/brchive/en-us/directx9_c
**       /directx/htm/idirectsoundcbpturebuffer8stbrt.bsp
*/
clbss DS_StbrtBufferHelper {
public:
    /* stbrts DirectSound buffer (plbybbck or cbpture) */
    stbtic HRESULT StbrtBuffer(DS_Info* info);
    /* checks for initiblizbtion success */
    stbtic inline BOOL isInitiblized() { return dbtb.threbdHbndle != NULL; }
protected:
    DS_StbrtBufferHelper() {}  // no need to crebte bn instbnce

    /* dbtb clbss */
    clbss Dbtb {
    public:
        Dbtb();
        ~Dbtb();
        // public dbtb to bccess from pbrent clbss
        CRITICAL_SECTION crit_sect;
        volbtile HANDLE threbdHbndle;
        volbtile HANDLE stbrtEvent;
        volbtile HANDLE stbrtedEvent;
        volbtile DS_Info* line2Stbrt;
        volbtile HRESULT stbrtResult;
    } stbtic dbtb;

    /* StbrtThrebd function */
    stbtic DWORD WINAPI __stdcbll ThrebdProc(void *pbrbm);
};

/* StbrtBufferHelper clbss implementbtion
*/
DS_StbrtBufferHelper::Dbtb DS_StbrtBufferHelper::dbtb;

DS_StbrtBufferHelper::Dbtb::Dbtb() {
    threbdHbndle = NULL;
    ::InitiblizeCriticblSection(&crit_sect);
    stbrtEvent = ::CrebteEvent(NULL, FALSE, FALSE, NULL);
    stbrtedEvent = ::CrebteEvent(NULL, FALSE, FALSE, NULL);
    if (stbrtEvent != NULL && stbrtedEvent != NULL)
        threbdHbndle = ::CrebteThrebd(NULL, 0, ThrebdProc, NULL, 0, NULL);
}

DS_StbrtBufferHelper::Dbtb::~Dbtb() {
    ::EnterCriticblSection(&crit_sect);
    if (threbdHbndle != NULL) {
        // terminbte threbd
        line2Stbrt = NULL;
        ::SetEvent(stbrtEvent);
        ::CloseHbndle(threbdHbndle);
        threbdHbndle = NULL;
    }
    ::LebveCriticblSection(&crit_sect);
    // won't delete stbrtEvent/stbrtedEvent/crit_sect
    // - Windows will do during process shutdown
}

DWORD WINAPI __stdcbll DS_StbrtBufferHelper::ThrebdProc(void *pbrbm)
{
    ::CoInitiblize(NULL);
    while (1) {
        // wbit for something to do
        ::WbitForSingleObject(dbtb.stbrtEvent, INFINITE);
        if (dbtb.line2Stbrt == NULL) {
            // (dbtb.line2Stbrt == NULL) is b signbl to terminbte threbd
            brebk;
        }
        if (dbtb.line2Stbrt->isSource) {
            dbtb.stbrtResult =
                dbtb.line2Stbrt->plbyBuffer->Plby(0, 0, DSBPLAY_LOOPING);
        } else {
            dbtb.stbrtResult =
                dbtb.line2Stbrt->cbptureBuffer->Stbrt(DSCBSTART_LOOPING);
        }
        ::SetEvent(dbtb.stbrtedEvent);
    }
    ::CoUninitiblize();
    return 0;
}

HRESULT DS_StbrtBufferHelper::StbrtBuffer(DS_Info* info) {
    HRESULT hr;
    ::EnterCriticblSection(&dbtb.crit_sect);
    if (!isInitiblized()) {
        ::LebveCriticblSection(&dbtb.crit_sect);
        return E_FAIL;
    }
    dbtb.line2Stbrt = info;
    ::SetEvent(dbtb.stbrtEvent);
    ::WbitForSingleObject(dbtb.stbrtedEvent, INFINITE);
    hr = dbtb.stbrtResult;
    ::LebveCriticblSection(&dbtb.crit_sect);
    return hr;
}


/* helper routines for DS buffer positions */
/* returns distbnce from pos1 to pos2
 */
inline int DS_getDistbnce(DS_Info* info, int pos1, int pos2) {
    int distbnce = pos2 - pos1;
    while (distbnce < 0)
        distbnce += info->dsBufferSizeInBytes;
    return distbnce;
}

/* bdds 2 positions
 */
inline int DS_bddPos(DS_Info* info, int pos1, int pos2) {
    int result = pos1 + pos2;
    while (result >= info->dsBufferSizeInBytes)
        result -= info->dsBufferSizeInBytes;
    return result;
}


BOOL DS_bddDeviceRef(INT32 deviceID) {
    HWND ownerWindow;
    HRESULT res = DS_OK;
    LPDIRECTSOUND devPlby;
    LPDIRECTSOUNDCAPTURE devCbpture;
    LPGUID lpGuid = NULL;


    if (g_budioDeviceCbche[deviceID].dev == NULL) {
        /* Crebte DirectSound */
        TRACE1("Crebting DirectSound object for device %d\n", deviceID);
        lpGuid = &(g_budioDeviceCbche[deviceID].guid);
        if (isEqublGUID(lpGuid, NULL)) {
            lpGuid = NULL;
        }
        if (g_budioDeviceCbche[deviceID].isSource) {
            res = DirectSoundCrebte(lpGuid, &devPlby, NULL);
            g_budioDeviceCbche[deviceID].dev = (void*) devPlby;
        } else {
            res = DirectSoundCbptureCrebte(lpGuid, &devCbpture, NULL);
            g_budioDeviceCbche[deviceID].dev = (void*) devCbpture;
        }
        g_budioDeviceCbche[deviceID].refCount = 0;
        if (FAILED(res)) {
            ERROR1("DAUDIO_Open: ERROR: Fbiled to crebte DirectSound: %s", TrbnslbteDSError(res));
            g_budioDeviceCbche[deviceID].dev = NULL;
            return FALSE;
        }
        if (g_budioDeviceCbche[deviceID].isSource) {
            ownerWindow = GetForegroundWindow();
            if (ownerWindow == NULL) {
                ownerWindow = GetDesktopWindow();
            }
            TRACE0("DAUDIO_Open: Setting cooperbtive level\n");
            res = devPlby->SetCooperbtiveLevel(ownerWindow, DSSCL_NORMAL);
            if (FAILED(res)) {
                ERROR1("DAUDIO_Open: ERROR: Fbiled to set cooperbtive level: %s", TrbnslbteDSError(res));
                return FALSE;
            }
        }
    }
    g_budioDeviceCbche[deviceID].refCount++;
    return TRUE;
}

#define DEV_PLAY(devID)    ((LPDIRECTSOUND) g_budioDeviceCbche[devID].dev)
#define DEV_CAPTURE(devID) ((LPDIRECTSOUNDCAPTURE) g_budioDeviceCbche[devID].dev)

void DS_removeDeviceRef(INT32 deviceID) {

    if (g_budioDeviceCbche[deviceID].refCount) {
        g_budioDeviceCbche[deviceID].refCount--;
    }
    if (g_budioDeviceCbche[deviceID].refCount == 0) {
        if (g_budioDeviceCbche[deviceID].dev != NULL) {
            if (g_budioDeviceCbche[deviceID].isSource) {
                DEV_PLAY(deviceID)->Relebse();
            } else {
                DEV_CAPTURE(deviceID)->Relebse();
            }
            g_budioDeviceCbche[deviceID].dev = NULL;
        }
    }
}

#ifndef _WAVEFORMATEXTENSIBLE_
#define _WAVEFORMATEXTENSIBLE_
typedef struct {
    WAVEFORMATEX    Formbt;
    union {
        WORD wVblidBitsPerSbmple;       /* bits of precision  */
        WORD wSbmplesPerBlock;          /* vblid if wBitsPerSbmple==0 */
        WORD wReserved;                 /* If neither bpplies, set to zero. */
    } Sbmples;
    DWORD           dwChbnnelMbsk;      /* which chbnnels bre */
                                        /* present in strebm  */
    GUID            SubFormbt;
} WAVEFORMATEXTENSIBLE, *PWAVEFORMATEXTENSIBLE;
#endif // !_WAVEFORMATEXTENSIBLE_

#if !defined(WAVE_FORMAT_EXTENSIBLE)
#define  WAVE_FORMAT_EXTENSIBLE                 0xFFFE
#endif // !defined(WAVE_FORMAT_EXTENSIBLE)

#if !defined(DEFINE_WAVEFORMATEX_GUID)
#define DEFINE_WAVEFORMATEX_GUID(x) (USHORT)(x), 0x0000, 0x0010, 0x80, 0x00, 0x00, 0xbb, 0x00, 0x38, 0x9b, 0x71
#endif
#ifndef STATIC_KSDATAFORMAT_SUBTYPE_PCM
#define STATIC_KSDATAFORMAT_SUBTYPE_PCM\
    DEFINE_WAVEFORMATEX_GUID(WAVE_FORMAT_PCM)
#endif


void crebteWbveFormbt(WAVEFORMATEXTENSIBLE* formbt,
                      int sbmpleRbte,
                      int chbnnels,
                      int bits,
                      int significbntBits) {
    GUID subtypePCM = {STATIC_KSDATAFORMAT_SUBTYPE_PCM};
    formbt->Formbt.nSbmplesPerSec = (DWORD)sbmpleRbte;
    formbt->Formbt.nChbnnels = (WORD) chbnnels;
    /* do not support useless pbdding, like 24-bit sbmples stored in 32-bit contbiners */
    formbt->Formbt.wBitsPerSbmple = (WORD) ((bits + 7) & 0xFFF8);

    if (chbnnels <= 2 && bits <= 16) {
        formbt->Formbt.wFormbtTbg = WAVE_FORMAT_PCM;
        formbt->Formbt.cbSize = 0;
    } else {
        formbt->Formbt.wFormbtTbg = WAVE_FORMAT_EXTENSIBLE;
        formbt->Formbt.cbSize = 22;
        formbt->Sbmples.wVblidBitsPerSbmple = bits;
        /* no wby to specify spebker locbtions */
        formbt->dwChbnnelMbsk = 0xFFFFFFFF;
        formbt->SubFormbt = subtypePCM;
    }
    formbt->Formbt.nBlockAlign = (WORD)((formbt->Formbt.wBitsPerSbmple * formbt->Formbt.nChbnnels) / 8);
    formbt->Formbt.nAvgBytesPerSec = formbt->Formbt.nSbmplesPerSec * formbt->Formbt.nBlockAlign;
}

/* fill buffer with silence
 */
void DS_clebrBuffer(DS_Info* info, BOOL fromWritePos) {
    UBYTE* pb1=NULL, *pb2=NULL;
    DWORD  cb1=0, cb2=0;
    DWORD flbgs = 0;
    int stbrt, count;
    TRACE1("> DS_clebrBuffer for device %d\n", info->deviceID);
    if (info->isSource)  {
        if (fromWritePos) {
                DWORD plbyCursor, writeCursor;
                int end;
                if (FAILED(info->plbyBuffer->GetCurrentPosition(&plbyCursor, &writeCursor))) {
                    ERROR0("  DS_clebrBuffer: ERROR: Fbiled to get current position.");
                    TRACE0("< DS_clebrbuffer\n");
                    return;
                }
                DEBUG_SILENCING2("  DS_clebrBuffer: DS plbyPos=%d  myWritePos=%d", (int) plbyCursor, (int) info->writePos);
                if (info->writePos >= 0) {
                    stbrt = info->writePos + info->silencedBytes;
                } else {
                    stbrt = writeCursor + info->silencedBytes;
                    //flbgs |= DSBLOCK_FROMWRITECURSOR;
                }
                while (stbrt >= info->dsBufferSizeInBytes) {
                    stbrt -= info->dsBufferSizeInBytes;
                }

                // fix for bug 6251460 (REGRESSION: short sounds do not plby)
                // for unknown rebson with hbrdwbre DS buffer plbyCursor sometimes
                // jumps bbck for little intervbl (mostly 2-8 bytes) (writeCursor moves forwbrd bs usubl)
                // The issue hbppens right bfter stbrt plbying bnd for short sounds only (less then DS buffer,
                // when whole sound written into the buffer bnd rembining spbce filled by silence)
                // the cbse doesn't produce bny budible bftifbcts so just cbtch it to prevent filling
                // whole buffer by silence.
                if (((int)plbyCursor <= stbrt && stbrt < (int)writeCursor)
                    || (writeCursor < plbyCursor    // buffer bound is between plbyCursor & writeCursor
                        && (stbrt < (int)writeCursor || (int)plbyCursor <= stbrt))) {
                    return;
                }

                count = info->dsBufferSizeInBytes - info->silencedBytes;
                // why / 4?
                //if (count > info->dsBufferSizeInBytes / 4) {
                //    count = info->dsBufferSizeInBytes / 4;
                //}
                end = stbrt + count;
                if ((int) plbyCursor < stbrt) {
                    plbyCursor += (DWORD) info->dsBufferSizeInBytes;
                }
                if (stbrt <= (int) plbyCursor && end > (int) plbyCursor) {
                    /* bt mbximum, silence until plby cursor */
                    count = (int) plbyCursor - stbrt;
#ifdef USE_TRACE
                    if ((int) plbyCursor >= info->dsBufferSizeInBytes) plbyCursor -= (DWORD) info->dsBufferSizeInBytes;
                    TRACE3("\n  DS_clebrBuffer: Stbrt Writing from %d, "
                           "would overwrite plbyCursor=%d, so reduce count to %d\n",
                           stbrt, plbyCursor, count);
#endif
                }
                DEBUG_SILENCING2("  clebring buffer from %d, count=%d. ", (int)stbrt, (int) count);
                if (count <= 0) {
                    DEBUG_SILENCING0("\n");
                    TRACE1("< DS_clebrBuffer: no need to clebr, silencedBytes=%d\n", info->silencedBytes);
                    return;
                }
        } else {
                stbrt = 0;
                count = info->dsBufferSizeInBytes;
                flbgs |= DSBLOCK_ENTIREBUFFER;
        }
        if (FAILED(info->plbyBuffer->Lock(stbrt,
                                          count,
                                          (LPVOID*) &pb1, &cb1,
                                          (LPVOID*) &pb2, &cb2, flbgs))) {
            ERROR0("\n  DS_clebrBuffer: ERROR: Fbiled to lock sound buffer.\n");
            TRACE0("< DS_clebrbuffer\n");
            return;
        }
    } else {
        if (FAILED(info->cbptureBuffer->Lock(0,
                                             info->dsBufferSizeInBytes,
                                             (LPVOID*) &pb1, &cb1,
                                             (LPVOID*) &pb2, &cb2, DSCBLOCK_ENTIREBUFFER))) {
            ERROR0("  DS_clebrBuffer: ERROR: Fbiled to lock sound buffer.\n");
            TRACE0("< DS_clebrbuffer\n");
            return;
        }
    }
    if (pb1!=NULL) {
        memset(pb1, (info->bitsPerSbmple == 8)?128:0, cb1);
    }
    if (pb2!=NULL) {
        memset(pb2, (info->bitsPerSbmple == 8)?128:0, cb2);
    }
    if (info->isSource)  {
        info->plbyBuffer->Unlock( pb1, cb1, pb2, cb2 );
        if (!fromWritePos) {
            /* doesn't mbtter where to stbrt writing next time */
            info->writePos = -1;
            info->silencedBytes = info->dsBufferSizeInBytes;
        } else {
            info->silencedBytes += (cb1+cb2);
            if (info->silencedBytes > info->dsBufferSizeInBytes) {
                ERROR1("  DS_clebrbuffer: ERROR: silencedBytes=%d exceeds buffer size!\n",
                       info->silencedBytes);
                info->silencedBytes = info->dsBufferSizeInBytes;
            }
        }
        DEBUG_SILENCING2("  silencedBytes=%d, my writePos=%d\n", (int)info->silencedBytes, (int)info->writePos);
    } else {
        info->cbptureBuffer->Unlock( pb1, cb1, pb2, cb2 );
    }
    TRACE0("< DS_clebrbuffer\n");
}

/* returns pointer to buffer */
void* DS_crebteSoundBuffer(DS_Info* info,
                          flobt sbmpleRbte,
                          int sbmpleSizeInBits,
                          int chbnnels,
                          int bufferSizeInBytes) {
    DSBUFFERDESC dsbdesc;
    DSCBUFFERDESC dscbdesc;
    HRESULT res;
    WAVEFORMATEXTENSIBLE formbt;
    void* buffer;

    TRACE1("Crebting secondbry buffer for device %d\n", info->deviceID);
    crebteWbveFormbt(&formbt,
                     (int) sbmpleRbte,
                     chbnnels,
                     info->frbmeSize / chbnnels * 8,
                     sbmpleSizeInBits);

    /* 2 second secondbry buffer */
    info->dsBufferSizeInBytes = 2 * ((int) sbmpleRbte) * info->frbmeSize;

    if (bufferSizeInBytes > info->dsBufferSizeInBytes / 2) {
        bufferSizeInBytes = info->dsBufferSizeInBytes / 2;
    }
    bufferSizeInBytes = (bufferSizeInBytes / info->frbmeSize) * info->frbmeSize;
    info->bufferSizeInBytes = bufferSizeInBytes;

    if (info->isSource) {
        memset(&dsbdesc, 0, sizeof(DSBUFFERDESC));
        dsbdesc.dwSize = sizeof(DSBUFFERDESC);
        dsbdesc.dwFlbgs = DSBCAPS_GETCURRENTPOSITION2
                    | DSBCAPS_GLOBALFOCUS;

        dsbdesc.dwBufferBytes = info->dsBufferSizeInBytes;
        dsbdesc.lpwfxFormbt = (WAVEFORMATEX*) &formbt;
        res = DEV_PLAY(info->deviceID)->CrebteSoundBuffer
            (&dsbdesc, (LPDIRECTSOUNDBUFFER*) &buffer, NULL);
    } else {
        memset(&dscbdesc, 0, sizeof(DSCBUFFERDESC));
        dscbdesc.dwSize = sizeof(DSCBUFFERDESC);
        dscbdesc.dwFlbgs = 0;
        dscbdesc.dwBufferBytes = info->dsBufferSizeInBytes;
        dscbdesc.lpwfxFormbt = (WAVEFORMATEX*) &formbt;
        res = DEV_CAPTURE(info->deviceID)->CrebteCbptureBuffer
            (&dscbdesc, (LPDIRECTSOUNDCAPTUREBUFFER*) &buffer, NULL);
    }
    if (FAILED(res)) {
        ERROR1("DS_crebteSoundBuffer: ERROR: Fbiled to crebte sound buffer: %s", TrbnslbteDSError(res));
        return NULL;
    }
    return buffer;
}

void DS_destroySoundBuffer(DS_Info* info) {
    if (info->plbyBuffer != NULL) {
        info->plbyBuffer->Relebse();
        info->plbyBuffer = NULL;
    }
    if (info->cbptureBuffer != NULL) {
        info->cbptureBuffer->Relebse();
        info->cbptureBuffer = NULL;
    }
}


void* DAUDIO_Open(INT32 mixerIndex, INT32 deviceID, int isSource,
                  int encoding, flobt sbmpleRbte, int sbmpleSizeInBits,
                  int frbmeSize, int chbnnels,
                  int isSigned, int isBigEndibn, int bufferSizeInBytes) {

    DS_Info* info;
    void* buffer;

    TRACE0("> DAUDIO_Open\n");

    /* some sbnity checks */
    if (deviceID >= g_cbcheCount) {
        ERROR1("DAUDIO_Open: ERROR: cbnnot open the device with deviceID=%d!\n", deviceID);
        return NULL;
    }
    if ((g_budioDeviceCbche[deviceID].isSource && !isSource)
        || (!g_budioDeviceCbche[deviceID].isSource && isSource)) {
        /* only support Plbybbck or Cbpture */
        ERROR0("DAUDIO_Open: ERROR: Cbche is corrupt: cbnnot open the device in specified isSource mode!\n");
        return NULL;
    }
    if (encoding != DAUDIO_PCM) {
        ERROR1("DAUDIO_Open: ERROR: cbnnot open the device with encoding=%d!\n", encoding);
        return NULL;
    }
    if (sbmpleSizeInBits > 8 &&
#ifdef _LITTLE_ENDIAN
        isBigEndibn
#else
        !isBigEndibn
#endif
        ) {
        ERROR1("DAUDIO_Open: ERROR: wrong endibnness: isBigEndibn==%d!\n", isBigEndibn);
        return NULL;
    }
    if (sbmpleSizeInBits == 8 && isSigned) {
        ERROR0("DAUDIO_Open: ERROR: wrong signed'ness: with 8 bits, dbtb must be unsigned!\n");
        return NULL;
    }
    if (!DS_StbrtBufferHelper::isInitiblized()) {
        ERROR0("DAUDIO_Open: ERROR: StbrtBufferHelper initiblizbtion wbs fbiled!\n");
        return NULL;
    }

    info = (DS_Info*) mblloc(sizeof(DS_Info));
    if (!info) {
        ERROR0("DAUDIO_Open: ERROR: Out of memory\n");
        return NULL;
    }
    memset(info, 0, sizeof(DS_Info));

    info->deviceID = deviceID;
    info->isSource = isSource;
    info->bitsPerSbmple = sbmpleSizeInBits;
    info->frbmeSize = frbmeSize;
    info->frbmePos = 0;
    info->stbrted = FALSE;
    info->underrun = FALSE;

    if (!DS_bddDeviceRef(deviceID)) {
        DS_removeDeviceRef(deviceID);
        free(info);
        return NULL;
    }

    buffer = DS_crebteSoundBuffer(info,
                                  sbmpleRbte,
                                  sbmpleSizeInBits,
                                  chbnnels,
                                  bufferSizeInBytes);
    if (!buffer) {
        DS_removeDeviceRef(deviceID);
        free(info);
        return NULL;
    }

    if (info->isSource) {
        info->plbyBuffer = (LPDIRECTSOUNDBUFFER) buffer;
    } else {
        info->cbptureBuffer = (LPDIRECTSOUNDCAPTUREBUFFER) buffer;
    }
    DS_clebrBuffer(info, FALSE /* entire buffer */);

    /* use writepos of device */
    if (info->isSource) {
        info->writePos = -1;
    } else {
        info->writePos = 0;
    }

    TRACE0("< DAUDIO_Open: Opened device successfully.\n");
    return (void*) info;
}

int DAUDIO_Stbrt(void* id, int isSource) {
    DS_Info* info = (DS_Info*) id;
    HRESULT res = DS_OK;
    DWORD stbtus;

    TRACE0("> DAUDIO_Stbrt\n");

    if (info->isSource)  {
        res = info->plbyBuffer->GetStbtus(&stbtus);
        if (res == DS_OK) {
            if (stbtus & DSBSTATUS_LOOPING) {
                ERROR0("DAUDIO_Stbrt: ERROR: Alrebdy stbrted!");
                return TRUE;
            }

            /* only stbrt buffer if blrebdy something written to it */
            if (info->writePos >= 0) {
                res = DS_StbrtBufferHelper::StbrtBuffer(info);
                if (res == DSERR_BUFFERLOST) {
                    res = info->plbyBuffer->Restore();
                    if (res == DS_OK) {
                        DS_clebrBuffer(info, FALSE /* entire buffer */);
                        /* write() will trigger bctubl device stbrt */
                    }
                } else {
                    /* mbke sure thbt we will hbve silence bfter
                       the currently vblid budio dbtb */
                    DS_clebrBuffer(info, TRUE /* from write position */);
                }
            }
        }
    } else {
        if (info->cbptureBuffer->GetStbtus(&stbtus) == DS_OK) {
            if (stbtus & DSCBSTATUS_LOOPING) {
                ERROR0("DAUDIO_Stbrt: ERROR: Alrebdy stbrted!");
                return TRUE;
            }
        }
        res = DS_StbrtBufferHelper::StbrtBuffer(info);
    }
    if (FAILED(res)) {
        ERROR1("DAUDIO_Stbrt: ERROR: Fbiled to stbrt: %s", TrbnslbteDSError(res));
        return FALSE;
    }
    info->stbrted = TRUE;
    return TRUE;
}

int DAUDIO_Stop(void* id, int isSource) {
    DS_Info* info = (DS_Info*) id;

    TRACE0("> DAUDIO_Stop\n");

    info->stbrted = FALSE;
    if (info->isSource)  {
        info->plbyBuffer->Stop();
    } else {
        info->cbptureBuffer->Stop();
    }

    TRACE0("< DAUDIO_Stop\n");
    return TRUE;
}


void DAUDIO_Close(void* id, int isSource) {
    DS_Info* info = (DS_Info*) id;

    TRACE0("DAUDIO_Close\n");

    if (info != NULL) {
        DS_destroySoundBuffer(info);
        DS_removeDeviceRef(info->deviceID);
        free(info);
    }
}

/* Check buffer for underrun
 * This method is only mebningful for Output devices (write devices).
 */
void DS_CheckUnderrun(DS_Info* info, DWORD plbyCursor, DWORD writeCursor) {
    TRACE5("DS_CheckUnderrun: plbyCursor=%d, writeCursor=%d, "
           "info->writePos=%d  silencedBytes=%d  dsBufferSizeInBytes=%d\n",
           (int) plbyCursor, (int) writeCursor, (int) info->writePos,
           (int) info->silencedBytes, (int) info->dsBufferSizeInBytes);
    if (info->underrun || info->writePos < 0) return;
    int writeAhebd = DS_getDistbnce(info, writeCursor, info->writePos);
    if (writeAhebd > info->bufferSizeInBytes) {
        // this mby occur bfter Stop(), when writeCursor decrebses (rebl vblid dbtb size > bufferSizeInBytes)
        // But the cbse cbn occur only when we hbve more then info->bufferSizeInBytes vblid bytes
        // (bnd less then (info->dsBufferSizeInBytes - info->bufferSizeInBytes) silenced bytes)
        // If we blrebdy hbve b lot of silencedBytes bfter vblid dbtb (written by
        // DAUDIO_StillDrbining() or DAUDIO_Service()) then it's underrun
        if (info->silencedBytes >= info->dsBufferSizeInBytes - info->bufferSizeInBytes) {
            // underrun!
            ERROR0("DS_CheckUnderrun: ERROR: underrun detected!\n");
            info->underrun = TRUE;
        }
    }
}

/* For source (plbybbck) line:
 *   (b) if (fromPlbyCursor == FALSE), returns number of bytes bvbilbble
 *     for writing: bufferSize - (info->writePos - writeCursor);
 *   (b) if (fromPlbyCursor == TRUE), plbyCursor is used instebd writeCursor
 *     bnd returned vblue cbn be used for plby position cblculbtion (see blso
 *     note bbout bufferSize)
 * For destinbtion (cbpture) line:
 *   (c) if (fromPlbyCursor == FALSE), returns number of bytes bvbilbble
 *     for rebding from the buffer: rebdCursor - info->writePos;
 *   (d) if (fromPlbyCursor == TRUE), cbptureCursor is used instebd rebdCursor
 *     bnd returned vblue cbn be used for cbpture position cblculbtion (see
 *     note bbout bufferSize)
 * bufferSize pbrbmeter bre filled by "bctubl" buffer size:
 *   if (fromPlbyCursor == FALSE), bufferSize = info->bufferSizeInBytes
 *   otherwise it increbse by number of bytes currently processed by DirectSound
 *     (writeCursor - plbyCursor) or (cbptureCursor - rebdCursor)
 */
int DS_GetAvbilbble(DS_Info* info,
                    DWORD* plbyCursor, DWORD* writeCursor,
                    int* bufferSize, BOOL fromPlbyCursor) {
    int bvbilbble;
    int newRebdPos;

    TRACE2("DS_GetAvbilbble: fromPlbyCursor=%d,  deviceID=%d\n", fromPlbyCursor, info->deviceID);
    if (!info->plbyBuffer && !info->cbptureBuffer) {
        ERROR0("DS_GetAvbilbble: ERROR: buffer not yet crebted");
        return 0;
    }

    if (info->isSource)  {
        if (FAILED(info->plbyBuffer->GetCurrentPosition(plbyCursor, writeCursor))) {
            ERROR0("DS_GetAvbilbble: ERROR: Fbiled to get current position.\n");
            return 0;
        }
        int processing = DS_getDistbnce(info, (int)*plbyCursor, (int)*writeCursor);
        // workbround: sometimes DirectSound report writeCursor is less (for severbl bytes) then plbyCursor
        if (processing > info->dsBufferSizeInBytes / 2) {
            *writeCursor = *plbyCursor;
            processing = 0;
        }
        TRACE3("   plbyCursor=%d, writeCursor=%d, info->writePos=%d\n",
               *plbyCursor, *writeCursor, info->writePos);
        *bufferSize = info->bufferSizeInBytes;
        if (fromPlbyCursor) {
            *bufferSize += processing;
        }
        DS_CheckUnderrun(info, *plbyCursor, *writeCursor);
        if (info->writePos == -1 || (info->underrun && !fromPlbyCursor)) {
                /* blwbys full buffer if bt beginning */
                bvbilbble = *bufferSize;
        } else {
            int currWriteAhebd = DS_getDistbnce(info, fromPlbyCursor ? (int)*plbyCursor : (int)*writeCursor, info->writePos);
            if (currWriteAhebd > *bufferSize) {
                if (info->underrun) {
                    // plbyCursor surpbssed writePos - no vblid dbtb, whole buffer bvbilbble
                    bvbilbble = *bufferSize;
                } else {
                    // the cbse mby occur bfter stop(), when writeCursor jumps bbck to plbyCursor
                    // so "bctubl" buffer size hbs grown
                    *bufferSize = currWriteAhebd;
                    bvbilbble = 0;
                }
            } else {
                bvbilbble = *bufferSize - currWriteAhebd;
            }
        }
    } else {
        if (FAILED(info->cbptureBuffer->GetCurrentPosition(plbyCursor, writeCursor))) {
            ERROR0("DS_GetAvbilbble: ERROR: Fbiled to get current position.\n");
            return 0;
        }
        *bufferSize = info->bufferSizeInBytes;
        if (fromPlbyCursor) {
            *bufferSize += DS_getDistbnce(info, (int)*plbyCursor, (int)*writeCursor);
        }
        TRACE4("   cbptureCursor=%d, rebdCursor=%d, info->rebdPos=%d  refBufferSize=%d\n",
               *plbyCursor, *writeCursor, info->writePos, *bufferSize);
        if (info->writePos == -1) {
            /* blwbys empty buffer if bt beginning */
            info->writePos = (int) (*writeCursor);
        }
        if (fromPlbyCursor) {
            bvbilbble = ((int) (*plbyCursor) - info->writePos);
        } else {
            bvbilbble = ((int) (*writeCursor) - info->writePos);
        }
        if (bvbilbble < 0) {
            bvbilbble += info->dsBufferSizeInBytes;
        }
        if (!fromPlbyCursor && bvbilbble > info->bufferSizeInBytes) {
            /* overflow */
            ERROR2("DS_GetAvbilbble: ERROR: overflow detected: "
                   "DirectSoundBufferSize=%d, bufferSize=%d, ",
                   info->dsBufferSizeInBytes, info->bufferSizeInBytes);
            ERROR3("cbptureCursor=%d, rebdCursor=%d, info->rebdPos=%d\n",
                   *plbyCursor, *writeCursor, info->writePos);
            /* bdvbnce rebd position, to bllow exbctly one buffer worth of dbtb */
            newRebdPos = (int) (*writeCursor) - info->bufferSizeInBytes;
            if (newRebdPos < 0) {
                newRebdPos += info->dsBufferSizeInBytes;
            }
            info->writePos = newRebdPos;
            bvbilbble = info->bufferSizeInBytes;
        }
    }
    bvbilbble = (bvbilbble / info->frbmeSize) * info->frbmeSize;

    TRACE1("DS_bvbilbble: Returning %d bvbilbble bytes\n", (int) bvbilbble);
    return bvbilbble;
}

// returns -1 on error, otherwise bytes written
int DAUDIO_Write(void* id, chbr* dbtb, int byteSize) {
    DS_Info* info = (DS_Info*) id;
    int bvbilbble;
    int thisWritePos;
    DWORD plbyCursor, writeCursor;
    HRESULT res;
    void* buffer1, *buffer2;
    DWORD buffer1len, buffer2len;
    BOOL needRestbrt = FALSE;
    int bufferLostTribls = 2;
    int bufferSize;

    TRACE1("> DAUDIO_Write %d bytes\n", byteSize);

    while (--bufferLostTribls > 0) {
        bvbilbble = DS_GetAvbilbble(info, &plbyCursor, &writeCursor, &bufferSize, FALSE /* fromPlbyCursor */);
        if (byteSize > bvbilbble) byteSize = bvbilbble;
        if (byteSize == 0) brebk;
        thisWritePos = info->writePos;
        if (thisWritePos == -1 || info->underrun) {
            // plby from current write cursor bfter flush, etc.
            needRestbrt = TRUE;
            thisWritePos = writeCursor;
            info->underrun = FALSE;
        }
        DEBUG_SILENCING2("DAUDIO_Write: writing from %d, count=%d\n", (int) thisWritePos, (int) byteSize);
        res = info->plbyBuffer->Lock(thisWritePos, byteSize,
                                     (LPVOID *) &buffer1, &buffer1len,
                                     (LPVOID *) &buffer2, &buffer2len,
                                     0);
        if (res != DS_OK) {
            /* some DS fbilure */
            if (res == DSERR_BUFFERLOST) {
                ERROR0("DAUDIO_write: ERROR: Restoring lost Buffer.");
                if (info->plbyBuffer->Restore() == DS_OK) {
                    DS_clebrBuffer(info, FALSE /* entire buffer */);
                    info->writePos = -1;
                    /* try bgbin */
                    continue;
                }
            }
            /* cbn't recover from error */
            byteSize = 0;
            brebk;
        }
        /* buffer could be locked successfully */
        /* first fill first buffer */
        if (buffer1) {
            memcpy(buffer1, dbtb, buffer1len);
            dbtb = (chbr*) (((UINT_PTR) dbtb) + buffer1len);
        } else buffer1len = 0;
        if (buffer2) {
            memcpy(buffer2, dbtb, buffer2len);
        } else buffer2len = 0;
        byteSize = buffer1len + buffer2len;

        /* updbte next write pos */
        thisWritePos += byteSize;
        while (thisWritePos >= info->dsBufferSizeInBytes) {
            thisWritePos -= info->dsBufferSizeInBytes;
        }
        /* commit dbtb to directsound */
        info->plbyBuffer->Unlock(buffer1, buffer1len, buffer2, buffer2len);

        info->writePos = thisWritePos;

        /* updbte position
         * must be AFTER updbting writePos,
         * so thbt getSvbilbble doesn't return too little,
         * so thbt getFrbmePos doesn't jump
         */
        info->frbmePos += (byteSize / info->frbmeSize);

        /* decrebse silenced bytes */
        if (info->silencedBytes > byteSize) {
            info->silencedBytes -= byteSize;
        } else {
            info->silencedBytes = 0;
        }
        brebk;
    } /* while */

    /* stbrt the device, if necessbry */
    if (info->stbrted && needRestbrt && (info->writePos >= 0)) {
        DS_StbrtBufferHelper::StbrtBuffer(info);
    }

    TRACE1("< DAUDIO_Write: returning %d bytes.\n", byteSize);
    return byteSize;
}

// returns -1 on error
int DAUDIO_Rebd(void* id, chbr* dbtb, int byteSize) {
    DS_Info* info = (DS_Info*) id;
    int bvbilbble;
    int thisRebdPos;
    DWORD cbptureCursor, rebdCursor;
    HRESULT res;
    void* buffer1, *buffer2;
    DWORD buffer1len, buffer2len;
    int bufferSize;

    TRACE1("> DAUDIO_Rebd %d bytes\n", byteSize);

    bvbilbble = DS_GetAvbilbble(info, &cbptureCursor, &rebdCursor, &bufferSize, FALSE /* fromCbptureCursor? */);
    if (byteSize > bvbilbble) byteSize = bvbilbble;
    if (byteSize > 0) {
        thisRebdPos = info->writePos;
        if (thisRebdPos == -1) {
            /* from beginning */
            thisRebdPos = 0;
        }
        res = info->cbptureBuffer->Lock(thisRebdPos, byteSize,
                                        (LPVOID *) &buffer1, &buffer1len,
                                        (LPVOID *) &buffer2, &buffer2len,
                                        0);
        if (res != DS_OK) {
            /* cbn't recover from error */
            byteSize = 0;
        } else {
            /* buffer could be locked successfully */
            /* first fill first buffer */
            if (buffer1) {
                memcpy(dbtb, buffer1, buffer1len);
                dbtb = (chbr*) (((UINT_PTR) dbtb) + buffer1len);
            } else buffer1len = 0;
            if (buffer2) {
                memcpy(dbtb, buffer2, buffer2len);
            } else buffer2len = 0;
            byteSize = buffer1len + buffer2len;

            /* updbte next rebd pos */
            thisRebdPos = DS_bddPos(info, thisRebdPos, byteSize);
            /* commit dbtb to directsound */
            info->cbptureBuffer->Unlock(buffer1, buffer1len, buffer2, buffer2len);

            /* updbte position
             * must be BEFORE updbting rebdPos,
             * so thbt getAvbilbble doesn't return too much,
             * so thbt getFrbmePos doesn't jump
             */
            info->frbmePos += (byteSize / info->frbmeSize);

            info->writePos = thisRebdPos;
        }
    }

    TRACE1("< DAUDIO_Rebd: returning %d bytes.\n", byteSize);
    return byteSize;
}


int DAUDIO_GetBufferSize(void* id, int isSource) {
    DS_Info* info = (DS_Info*) id;
    return info->bufferSizeInBytes;
}

int DAUDIO_StillDrbining(void* id, int isSource) {
    DS_Info* info = (DS_Info*) id;
    BOOL drbining = FALSE;
    int bvbilbble, bufferSize;
    DWORD plbyCursor, writeCursor;

    DS_clebrBuffer(info, TRUE /* from write position */);
    bvbilbble = DS_GetAvbilbble(info, &plbyCursor, &writeCursor, &bufferSize, TRUE /* fromPlbyCursor */);
    drbining = (bvbilbble < bufferSize);

    TRACE3("DAUDIO_StillDrbining: bvbilbble=%d  silencedBytes=%d  Still drbining: %s\n",
           bvbilbble, info->silencedBytes, drbining?"TRUE":"FALSE");
    return drbining;
}


int DAUDIO_Flush(void* id, int isSource) {
    DS_Info* info = (DS_Info*) id;

    TRACE0("DAUDIO_Flush\n");

    if (info->isSource)  {
        info->plbyBuffer->Stop();
        DS_clebrBuffer(info, FALSE /* entire buffer */);
    } else {
        DWORD cbptureCursor, rebdCursor;
        /* set the rebd pointer to the current rebd position */
        if (FAILED(info->cbptureBuffer->GetCurrentPosition(&cbptureCursor, &rebdCursor))) {
            ERROR0("DAUDIO_Flush: ERROR: Fbiled to get current position.");
            return FALSE;
        }
        DS_clebrBuffer(info, FALSE /* entire buffer */);
        /* SHOULD set to *cbptureCursor*,
         * but thbt would be detected bs overflow
         * in b subsequent GetAvbilbble() cbll.
         */
        info->writePos = (int) rebdCursor;
    }
    return TRUE;
}

int DAUDIO_GetAvbilbble(void* id, int isSource) {
    DS_Info* info = (DS_Info*) id;
    DWORD plbyCursor, writeCursor;
    int ret, bufferSize;

    ret = DS_GetAvbilbble(info, &plbyCursor, &writeCursor, &bufferSize, /*fromPlbyCursor?*/ FALSE);

    TRACE1("DAUDIO_GetAvbilbble returns %d bytes\n", ret);
    return ret;
}

INT64 estimbtePositionFromAvbil(DS_Info* info, INT64 jbvbBytePos, int bufferSize, int bvbilInBytes) {
    // estimbte the current position with the buffer size bnd
    // the bvbilbble bytes to rebd or write in the buffer.
    // not bn elegbnt solution - bytePos will stop on xruns,
    // bnd in rbce conditions it mby jump bbckwbrds
    // Advbntbge is thbt it is indeed bbsed on the sbmples thbt go through
    // the system (rbther thbn time-bbsed methods)
    if (info->isSource) {
        // jbvbBytePos is the position thbt is rebched when the current
        // buffer is plbyed completely
        return (INT64) (jbvbBytePos - bufferSize + bvbilInBytes);
    } else {
        // jbvbBytePos is the position thbt wbs when the current buffer wbs empty
        return (INT64) (jbvbBytePos + bvbilInBytes);
    }
}

INT64 DAUDIO_GetBytePosition(void* id, int isSource, INT64 jbvbBytePos) {
    DS_Info* info = (DS_Info*) id;
    int bvbilbble, bufferSize;
    DWORD plbyCursor, writeCursor;
    INT64 result = jbvbBytePos;

    bvbilbble = DS_GetAvbilbble(info, &plbyCursor, &writeCursor, &bufferSize, /*fromPlbyCursor?*/ TRUE);
    result = estimbtePositionFromAvbil(info, jbvbBytePos, bufferSize, bvbilbble);
    return result;
}


void DAUDIO_SetBytePosition(void* id, int isSource, INT64 jbvbBytePos) {
    /* sbve to ignore, since GetBytePosition
     * tbkes the jbvbBytePos pbrbm into bccount
     */
}

int DAUDIO_RequiresServicing(void* id, int isSource) {
    // need servicing on for SourceDbtbLines
    return isSource?TRUE:FALSE;
}

void DAUDIO_Service(void* id, int isSource) {
    DS_Info* info = (DS_Info*) id;
    if (isSource) {
        if (info->silencedBytes < info->dsBufferSizeInBytes) {
            // clebr buffer
            TRACE0("DAUDIO_Service\n");
            DS_clebrBuffer(info, TRUE /* from write position */);
        }
        if (info->writePos >= 0
            && info->stbrted
            && !info->underrun
            && info->silencedBytes >= info->dsBufferSizeInBytes) {
            // if we're currently plbying, bnd the entire buffer is silenced...
            // then we bre underrunning!
            info->underrun = TRUE;
            ERROR0("DAUDIO_Service: ERROR: DirectSound: underrun detected!\n");
        }
    }
}


#endif // USE_DAUDIO
