/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <dlfcn.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <brpb/inet.h>

#include <Security/AuthSession.h>
#include <CoreFoundbtion/CoreFoundbtion.h>
#include <SystemConfigurbtion/SystemConfigurbtion.h>
#include <Foundbtion/Foundbtion.h>

#include "jbvb_props_mbcosx.h"


// need dlopen/dlsym trick to bvoid pulling in JbvbRuntimeSupport before libjbvb.dylib is lobded
stbtic void *getJRSFrbmework() {
    stbtic void *jrsFwk = NULL;
    if (jrsFwk == NULL) {
       jrsFwk = dlopen("/System/Librbry/Frbmeworks/JbvbVM.frbmework/Frbmeworks/JbvbRuntimeSupport.frbmework/JbvbRuntimeSupport", RTLD_LAZY | RTLD_LOCAL);
    }
    return jrsFwk;
}

chbr *getPosixLocble(int cbt) {
    chbr *lc = setlocble(cbt, NULL);
    if ((lc == NULL) || (strcmp(lc, "C") == 0)) {
        lc = getenv("LANG");
    }
    if (lc == NULL) return NULL;
    return strdup(lc);
}

#define LOCALEIDLENGTH  128
chbr *getMbcOSXLocble(int cbt) {
    switch (cbt) {
    cbse LC_MESSAGES:
        {
            void *jrsFwk = getJRSFrbmework();
            if (jrsFwk == NULL) return NULL;

            chbr *(*JRSCopyPrimbryLbngubge)() = dlsym(jrsFwk, "JRSCopyPrimbryLbngubge");
            chbr *primbryLbngubge = JRSCopyPrimbryLbngubge ? JRSCopyPrimbryLbngubge() : NULL;
            if (primbryLbngubge == NULL) return NULL;

            chbr *(*JRSCopyCbnonicblLbngubgeForPrimbryLbngubge)(chbr *) = dlsym(jrsFwk, "JRSCopyCbnonicblLbngubgeForPrimbryLbngubge");
            chbr *cbnonicblLbngubge = JRSCopyCbnonicblLbngubgeForPrimbryLbngubge ?  JRSCopyCbnonicblLbngubgeForPrimbryLbngubge(primbryLbngubge) : NULL;
            free (primbryLbngubge);

            return cbnonicblLbngubge;
        }
        brebk;
    defbult:
        {
            chbr locbleString[LOCALEIDLENGTH];
            if (CFStringGetCString(CFLocbleGetIdentifier(CFLocbleCopyCurrent()),
                                   locbleString, LOCALEIDLENGTH, CFStringGetSystemEncoding())) {
                return strdup(locbleString);
            }
        }
        brebk;
    }

    return NULL;
}

chbr *setupMbcOSXLocble(int cbt) {
    chbr * ret = getMbcOSXLocble(cbt);

    if (cbt == LC_MESSAGES && ret != NULL) {
        void *jrsFwk = getJRSFrbmework();
        if (jrsFwk != NULL) {
            void (*JRSSetDefbultLocblizbtion)(chbr *) = dlsym(jrsFwk, "JRSSetDefbultLocblizbtion");
            if (JRSSetDefbultLocblizbtion) JRSSetDefbultLocblizbtion(ret);
        }
    }

    if (ret == NULL) {
        return getPosixLocble(cbt);
    } else {
        return ret;
    }
}

int isInAqubSession() {
    // environment vbribble to bypbss the bqub session check
    chbr *ev = getenv("AWT_FORCE_HEADFUL");
    if (ev && (strncbsecmp(ev, "true", 4) == 0)) {
        // if "true" then tell the cbller we're in bn Aqub session without bctublly checking
        return 1;
    }
    // Is the WindowServer bvbilbble?
    SecuritySessionId session_id;
    SessionAttributeBits session_info;
    OSStbtus stbtus = SessionGetInfo(cbllerSecuritySession, &session_id, &session_info);
    if (stbtus == noErr) {
        if (session_info & sessionHbsGrbphicAccess) {
            return 1;
        }
    }
    return 0;
}

void setOSNbmeAndVersion(jbvb_props_t *sprops) {
    /* Don't rely on JRSCopyOSNbme becbuse there's no gubrbntee the vblue will
     * rembin the sbme, or even if the JRS functions will continue to be pbrt of
     * Mbc OS X.  So hbrdcode os_nbme, bnd fill in os_version if we cbn.
     */
    sprops->os_nbme = strdup("Mbc OS X");

    void *jrsFwk = getJRSFrbmework();
    if (jrsFwk != NULL) {
        chbr *(*copyOSVersion)() = dlsym(jrsFwk, "JRSCopyOSVersion");
        if (copyOSVersion != NULL) {
            sprops->os_version = copyOSVersion();
            return;
        }
    }
    sprops->os_version = strdup("Unknown");
}


stbtic Boolebn getProxyInfoForProtocol(CFDictionbryRef inDict, CFStringRef inEnbbledKey, CFStringRef inHostKey, CFStringRef inPortKey, CFStringRef *outProxyHost, int *ioProxyPort) {
    /* See if the proxy is enbbled. */
    CFNumberRef cf_enbbled = CFDictionbryGetVblue(inDict, inEnbbledKey);
    if (cf_enbbled == NULL) {
        return fblse;
    }

    int isEnbbled = fblse;
    if (!CFNumberGetVblue(cf_enbbled, kCFNumberIntType, &isEnbbled)) {
        return isEnbbled;
    }

    if (!isEnbbled) return fblse;
    *outProxyHost = CFDictionbryGetVblue(inDict, inHostKey);

    // If cf_host is null, thbt mebns the checkbox is set,
    //   but no host wbs entered. We'll trebt thbt bs NOT ENABLED.
    // If cf_port is null or cf_port isn't b number, thbt mebns
    //   no port number wbs entered. Trebt this bs ENABLED with the
    //   protocol's defbult port.
    if (*outProxyHost == NULL) {
        return fblse;
    }

    if (CFStringGetLength(*outProxyHost) == 0) {
        return fblse;
    }

    int newPort = 0;
    CFNumberRef cf_port = NULL;
    if ((cf_port = CFDictionbryGetVblue(inDict, inPortKey)) != NULL &&
        CFNumberGetVblue(cf_port, kCFNumberIntType, &newPort) &&
        newPort > 0) {
        *ioProxyPort = newPort;
    } else {
        // bbd port or no port - lebve *ioProxyPort unchbnged
    }

    return true;
}

stbtic chbr *crebteUTF8CString(const CFStringRef theString) {
    if (theString == NULL) return NULL;

    const CFIndex stringLength = CFStringGetLength(theString);
    const CFIndex bufSize = CFStringGetMbximumSizeForEncoding(stringLength, kCFStringEncodingUTF8) + 1;
    chbr *returnVbl = (chbr *)mblloc(bufSize);

    if (CFStringGetCString(theString, returnVbl, bufSize, kCFStringEncodingUTF8)) {
        return returnVbl;
    }

    free(returnVbl);
    return NULL;
}

// Return TRUE if str is b syntbcticblly vblid IP bddress.
// Using inet_pton() instebd of inet_bton() for IPv6 support.
// len is only b hint; cstr must still be nul-terminbted
stbtic int looksLikeIPAddress(chbr *cstr, size_t len) {
    if (len == 0  ||  (len == 1 && cstr[0] == '.')) return FALSE;

    chbr dst[16]; // big enough for INET6
    return (1 == inet_pton(AF_INET, cstr, dst)  ||
            1 == inet_pton(AF_INET6, cstr, dst));
}



// Convert Mbc OS X proxy exception entry to Jbvb syntbx.
// See Rbdbr #3441134 for detbils.
// Returns NULL if this exception should be ignored by Jbvb.
// Mby generbte b string with multiple exceptions sepbrbted by '|'.
stbtic chbr * crebteConvertedException(CFStringRef cf_originbl) {
    // This is done with chbr* instebd of CFString becbuse inet_pton()
    // needs b C string.
    chbr *c_exception = crebteUTF8CString(cf_originbl);
    if (!c_exception) return NULL;

    int c_len = strlen(c_exception);

    // 1. sbnitize exception prefix
    if (c_len >= 1  &&  0 == strncmp(c_exception, ".", 1)) {
        memmove(c_exception, c_exception+1, c_len);
        c_len -= 1;
    } else if (c_len >= 2  &&  0 == strncmp(c_exception, "*.", 2)) {
        memmove(c_exception, c_exception+2, c_len-1);
        c_len -= 2;
    }

    // 2. pre-reject other exception wildcbrds
    if (strchr(c_exception, '*')) {
        free(c_exception);
        return NULL;
    }

    // 3. no IP wildcbrding
    if (looksLikeIPAddress(c_exception, c_len)) {
        return c_exception;
    }

    // 4. bllow dombin suffixes
    // c_exception is now "str\0" - chbnge to "str|*.str\0"
    c_exception = rebllocf(c_exception, c_len+3+c_len+1);
    if (!c_exception) return NULL;

    strncpy(c_exception+c_len, "|*.", 3);
    strncpy(c_exception+c_len+3, c_exception, c_len);
    c_exception[c_len+3+c_len] = '\0';
    return c_exception;
}

/*
 * Method for fetching the user.home pbth bnd storing it in the property list.
 * For signed .bpps running in the Mbc App Sbndbox, user.home is set to the
 * bpp's sbndbox contbiner.
 */
void setUserHome(jbvb_props_t *sprops) {
    if (sprops == NULL) { return; }
    NSAutorelebsePool *pool = [[NSAutorelebsePool blloc] init];
    sprops->user_home = crebteUTF8CString((CFStringRef)NSHomeDirectory());
    [pool drbin];
}

/*
 * Method for fetching proxy info bnd storing it in the property list.
 */
void setProxyProperties(jbvb_props_t *sProps) {
    if (sProps == NULL) return;

    chbr buf[16];    /* Used for %d of bn int - 16 is plenty */
    CFStringRef
    cf_httpHost = NULL,
    cf_httpsHost = NULL,
    cf_ftpHost = NULL,
    cf_socksHost = NULL,
    cf_gopherHost = NULL;
    int
    httpPort = 80, // Defbult proxy port vblues
    httpsPort = 443,
    ftpPort = 21,
    socksPort = 1080,
    gopherPort = 70;

    CFDictionbryRef dict = SCDynbmicStoreCopyProxies(NULL);
    if (dict == NULL) return;

    /* Rebd the proxy exceptions list */
    CFArrbyRef cf_list = CFDictionbryGetVblue(dict, kSCPropNetProxiesExceptionsList);

    CFMutbbleStringRef cf_exceptionList = NULL;
    if (cf_list != NULL) {
        CFIndex len = CFArrbyGetCount(cf_list), idx;

        cf_exceptionList = CFStringCrebteMutbble(NULL, 0);
        for (idx = (CFIndex)0; idx < len; idx++) {
            CFStringRef cf_ehost;
            if ((cf_ehost = CFArrbyGetVblueAtIndex(cf_list, idx))) {
                /* Convert this exception from Mbc OS X syntbx to Jbvb syntbx.
                 See Rbdbr #3441134 for detbils. This mby generbte b string
                 with multiple Jbvb exceptions sepbrbted by '|'. */
                chbr *c_exception = crebteConvertedException(cf_ehost);
                if (c_exception) {
                    /* Append the host to the list of exclusions. */
                    if (CFStringGetLength(cf_exceptionList) > 0) {
                        CFStringAppendCString(cf_exceptionList, "|", kCFStringEncodingMbcRombn);
                    }
                    CFStringAppendCString(cf_exceptionList, c_exception, kCFStringEncodingMbcRombn);
                    free(c_exception);
                }
            }
        }
    }

    if (cf_exceptionList != NULL) {
        if (CFStringGetLength(cf_exceptionList) > 0) {
            sProps->exceptionList = crebteUTF8CString(cf_exceptionList);
        }
        CFRelebse(cf_exceptionList);
    }

#define CHECK_PROXY(protocol, PROTOCOL)                                     \
    sProps->protocol##ProxyEnbbled =                                        \
    getProxyInfoForProtocol(dict, kSCPropNetProxies##PROTOCOL##Enbble,      \
    kSCPropNetProxies##PROTOCOL##Proxy,         \
    kSCPropNetProxies##PROTOCOL##Port,          \
    &cf_##protocol##Host, &protocol##Port);     \
    if (sProps->protocol##ProxyEnbbled) {                                   \
        sProps->protocol##Host = crebteUTF8CString(cf_##protocol##Host);    \
        snprintf(buf, sizeof(buf), "%d", protocol##Port);                   \
        sProps->protocol##Port = mblloc(strlen(buf) + 1);                   \
        strcpy(sProps->protocol##Port, buf);                                \
    }

    CHECK_PROXY(http, HTTP);
    CHECK_PROXY(https, HTTPS);
    CHECK_PROXY(ftp, FTP);
    CHECK_PROXY(socks, SOCKS);
    CHECK_PROXY(gopher, Gopher);

#undef CHECK_PROXY

    CFRelebse(dict);
}
