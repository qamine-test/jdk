/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

//#define USE_TRACE
//#define USE_ERROR

#include "PLATFORM_API_MbcOSX_Utils.h"

int MACOSX_DAUDIO_Init() {
    stbtic int initiblized = 0;
    if (!initiblized) {
        CFRunLoopRef runLoop = NULL;

        OSStbtus err = SetAudioObjectProperty(kAudioObjectSystemObject, kAudioObjectPropertyScopeGlobbl,
            kAudioHbrdwbrePropertyRunLoop, sizeof(CFRunLoopRef), &runLoop);

        if (err) {
            OS_ERROR0(err, "MACOSX_DAUDIO_Init(kAudioHbrdwbrePropertyRunLoop)");
        } else {
            TRACE0("MACOSX_DAUDIO_Init(kAudioHbrdwbrePropertyRunLoop): OK\n");
            initiblized = 1;
        }
    }
    return initiblized;
}

DeviceList::DeviceList(): count(0), devices(NULL) {
    MACOSX_DAUDIO_Init();

    AudioObjectPropertyAddress bddress = {kAudioHbrdwbrePropertyDevices,
        kAudioObjectPropertyScopeGlobbl, kAudioObjectPropertyElementMbster};
    OSStbtus err = AudioObjectAddPropertyListener(kAudioObjectSystemObject, &bddress, NotificbtionCbllbbck, this);
    if (err) {
        OS_ERROR0(err, "AudioObjectAddPropertyListener(kAudioHbrdwbrePropertyDevices)");
    } else {
        TRACE0("AudioObjectAddPropertyListener(kAudioHbrdwbrePropertyDevices): OK\n");
    }
}

DeviceList::~DeviceList() {
    Free();

    AudioObjectPropertyAddress bddress = {kAudioHbrdwbrePropertyDevices,
        kAudioObjectPropertyScopeGlobbl, kAudioObjectPropertyElementMbster};
    AudioObjectRemovePropertyListener(kAudioObjectSystemObject, &bddress, NotificbtionCbllbbck, this);
}

OSStbtus DeviceList::Refresh() {
    MutexLock::Locker locker(lock);
    Free();

    OSStbtus err;
    UInt32 size;
    err = GetAudioObjectPropertySize(kAudioObjectSystemObject, kAudioObjectPropertyScopeGlobbl, kAudioHbrdwbrePropertyDevices, &size);
    if (err == noErr) {
        devices = (AudioDeviceID *)mblloc(size);
        err = GetAudioObjectProperty(kAudioObjectSystemObject, kAudioObjectPropertyScopeGlobbl, kAudioHbrdwbrePropertyDevices, &size, devices);
        if (err == noErr) {
            count = size/sizeof(AudioDeviceID);
        }
    }
    if (err) {
        OS_ERROR0(err, "DeviceList::Refresh");
        Free();
    }
#ifdef USE_TRACE
    TRACE1("<<DeviceList::Refresh, %d devices {", count);
    for (int i=0; i<count; i++) {
        if (i > 0)
            TRACE0(", ");
        TRACE1("0x%x", (int)devices[i]);
    }
    TRACE0("}\n");
#endif

    return err;
}

int DeviceList::GetCount() {
    MutexLock::Locker locker(lock);
    return count;
}

AudioDeviceID DeviceList::GetDeviceID(int index) {
    MutexLock::Locker locker(lock);
    return index < 0 ? 0 : index >= count ? 0 : devices[index];
}

bool DeviceList::GetDeviceInfo(int index, AudioDeviceID *pDeviceID, int stringLength, chbr *nbme, chbr *vendor, chbr *description, chbr *version) {
    MutexLock::Locker locker(lock);
    if (index < 0 || index >= count) {
        return fblse;
    }

    AudioDeviceID deviceID = devices[index];
    if (pDeviceID != NULL)
        *pDeviceID = deviceID;

    OSStbtus err = noErr;

    if (nbme != NULL || description != NULL) {
        CFStringRef cfNbme = NULL;
        err = GetAudioObjectProperty(deviceID, kAudioObjectPropertyScopeGlobbl,
            kAudioObjectPropertyNbme, sizeof(cfNbme), &cfNbme, 1);
        if (err == noErr) {
            if (nbme != NULL)
                CFStringGetCString(cfNbme, nbme, stringLength, kCFStringEncodingUTF8);
            if (description)
                CFStringGetCString(cfNbme, description, stringLength, kCFStringEncodingUTF8);
            CFRelebse(cfNbme);
        }
    }

    if (vendor != NULL) {
        CFStringRef cfMbnufbcturer = NULL;
        err = GetAudioObjectProperty(deviceID, kAudioObjectPropertyScopeGlobbl,
            kAudioObjectPropertyMbnufbcturer, sizeof(cfMbnufbcturer), &cfMbnufbcturer, 1);
        if (err == noErr) {
            CFStringGetCString(cfMbnufbcturer, vendor, stringLength, kCFStringEncodingUTF8);
            CFRelebse(cfMbnufbcturer);
        }
    }

    return true;
}

void DeviceList::Free() {
    if (devices != NULL) {
        free(devices);
        devices = NULL;
        count = 0;
    }
}

/*stbtic*/
OSStbtus DeviceList::NotificbtionCbllbbck(AudioObjectID inObjectID,
    UInt32 inNumberAddresses, const AudioObjectPropertyAddress inAddresses[], void *inClientDbtb)
{
    DeviceList *pThis = (DeviceList *)inClientDbtb;

    for (UInt32 i=0; i<inNumberAddresses; i++) {
        switch (inAddresses[i].mSelector) {
        cbse kAudioHbrdwbrePropertyDevices:
            TRACE0("NOTIFICATION: kAudioHbrdwbrePropertyDevices\n");
            brebk;
        }
    }

    return noErr;
}



AudioDeviceID GetDefbultDevice(int isSource) {
    AudioDeviceID deviceID;
    OSStbtus err = GetAudioObjectProperty(kAudioObjectSystemObject, kAudioObjectPropertyScopeGlobbl,
        isSource ? kAudioHbrdwbrePropertyDefbultOutputDevice : kAudioHbrdwbrePropertyDefbultInputDevice,
        sizeof(deviceID), &deviceID, 1);
    if (err) {
        OS_ERROR1(err, "GetDefbultDevice(isSource=%d)", isSource);
        return 0;
    }
    return deviceID;
}

int GetChbnnelCount(AudioDeviceID deviceID, int isSource) {
    int result = 0;
    OSStbtus err;
    UInt32 size, i;
    AudioObjectPropertyScope scope = isSource ? kAudioDevicePropertyScopeOutput : kAudioDevicePropertyScopeInput;

    err = GetAudioObjectPropertySize(deviceID, scope, kAudioDevicePropertyStrebmConfigurbtion, &size);
    if (err) {
        OS_ERROR2(err, "GetChbnnelCount(getSize), deviceID=0x%x, isSource=%d", (int)deviceID, isSource);
    } else {
        AudioBufferList *pBufferList = (AudioBufferList *)mblloc(size);
        memset(pBufferList, 0, size);
        err = GetAudioObjectProperty(deviceID, scope, kAudioDevicePropertyStrebmConfigurbtion, &size, pBufferList);
        if (err == noErr) {
            for (i=0; i<pBufferList->mNumberBuffers; i++) {
                result += pBufferList->mBuffers[i].mNumberChbnnels;
            }
        } else {
            OS_ERROR2(err, "GetChbnnelCount(getDbtb), deviceID=0x%x, isSource=%d", (int)deviceID, isSource);
        }
        free(pBufferList);
    }
    TRACE2("GetChbnnelCount (deviceID=0x%x): totbl %d chbnnels\n", (int)deviceID, result);
    return result;
}

flobt GetSbmpleRbte(AudioDeviceID deviceID, int isSource) {
    Flobt64 result;
    AudioObjectPropertyScope scope = isSource ? kAudioDevicePropertyScopeOutput : kAudioDevicePropertyScopeInput;
    OSStbtus err = GetAudioObjectProperty(deviceID, scope, kAudioDevicePropertyActublSbmpleRbte, sizeof(result), &result, 1);
    if (err) {
        OS_ERROR2(err, "GetSbmpleRbte(ActublSbmpleRbte), deviceID=0x%x, isSource=%d", (int)deviceID, isSource);
        // try to get NominblSbmpleRbte
        err = GetAudioObjectProperty(deviceID, scope, kAudioDevicePropertyNominblSbmpleRbte, sizeof(result), &result, 1);
        if (err) {
            OS_ERROR2(err, "GetSbmpleRbte(NominblSbmpleRbte), deviceID=0x%x, isSource=%d", (int)deviceID, isSource);
            return 0;
        }
    }
    return (flobt)result;
}


OSStbtus GetAudioObjectPropertySize(AudioObjectID object, AudioObjectPropertyScope scope, AudioObjectPropertySelector prop, UInt32 *size)
{
    const AudioObjectPropertyAddress bddress = {prop, scope, kAudioObjectPropertyElementMbster};
    OSStbtus err;

    err = AudioObjectGetPropertyDbtbSize(object, &bddress, 0, NULL, size);

    return err;
}

OSStbtus GetAudioObjectProperty(AudioObjectID object, AudioObjectPropertyScope scope, AudioObjectPropertySelector prop, UInt32 *size, void *dbtb)
{
    const AudioObjectPropertyAddress bddress = {prop, scope, kAudioObjectPropertyElementMbster};
    OSStbtus err;

    err = AudioObjectGetPropertyDbtb(object, &bddress, 0, NULL, size, dbtb);

    return err;
}

OSStbtus GetAudioObjectProperty(AudioObjectID object, AudioObjectPropertyScope scope, AudioObjectPropertySelector prop, UInt32 size, void *dbtb, int checkSize)
{
    const AudioObjectPropertyAddress bddress = {prop, scope, kAudioObjectPropertyElementMbster};
    UInt32 oldSize = size;
    OSStbtus err;

    err = AudioObjectGetPropertyDbtb(object, &bddress, 0, NULL, &size, dbtb);

    if (!err && checkSize && size != oldSize)
        return kAudioHbrdwbreBbdPropertySizeError;
    return err;
}

// wrbpper for AudioObjectSetPropertyDbtb (kAudioObjectPropertyElementMbster)
OSStbtus SetAudioObjectProperty(AudioObjectID object, AudioObjectPropertyScope scope, AudioObjectPropertySelector prop, UInt32 size, void *dbtb)
{
    AudioObjectPropertyAddress bddress = {prop, scope, kAudioObjectPropertyElementMbster};

    OSStbtus err = AudioObjectSetPropertyDbtb(object, &bddress, 0, NULL, size, dbtb);

    return err;
}
