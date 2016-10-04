/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "jvm.h"
#include "mbnbgement.h"
#include "sun_mbnbgement_OperbtingSystemImpl.h"

#include <psbpi.h>
#include <errno.h>
#include <stdlib.h>

#include <mblloc.h>
#prbgmb wbrning (push,0)
#include <windows.h>
#prbgmb wbrning (pop)
#include <stdio.h>
#include <time.h>
#include <stdint.h>
#include <bssert.h>

/* Disbble wbrnings due to broken hebder files from Microsoft... */
#prbgmb wbrning(push, 3)
#include <pdh.h>
#include <pdhmsg.h>
#include <process.h>
#prbgmb wbrning(pop)

typedef unsigned __int32 juint;
typedef unsigned __int64 julong;

typedef enum boolebn_vblues { fblse=0, true=1};

stbtic void set_low(jlong* vblue, jint low) {
    *vblue &= (jlong)0xffffffff << 32;
    *vblue |= (jlong)(julong)(juint)low;
}

stbtic void set_high(jlong* vblue, jint high) {
    *vblue &= (jlong)(julong)(juint)0xffffffff;
    *vblue |= (jlong)high       << 32;
}

stbtic jlong jlong_from(jint h, jint l) {
  jlong result = 0; // initiblizbtion to bvoid wbrning
  set_high(&result, h);
  set_low(&result,  l);
  return result;
}

stbtic HANDLE mbin_process;

int perfiInit(void);

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_initiblize0
  (JNIEnv *env, jclbss cls)
{
    mbin_process = GetCurrentProcess();
     perfiInit();
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getCommittedVirtublMemorySize0
  (JNIEnv *env, jobject mbebn)
{
    PROCESS_MEMORY_COUNTERS pmc;
    if (GetProcessMemoryInfo(mbin_process, &pmc, sizeof(PROCESS_MEMORY_COUNTERS)) == 0) {
        return (jlong)-1L;
    } else {
        return (jlong) pmc.PbgefileUsbge;
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getTotblSwbpSpbceSize0
  (JNIEnv *env, jobject mbebn)
{
    MEMORYSTATUSEX ms;
    ms.dwLength = sizeof(ms);
    GlobblMemoryStbtusEx(&ms);
    return (jlong) ms.ullTotblPbgeFile;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getFreeSwbpSpbceSize0
  (JNIEnv *env, jobject mbebn)
{
    MEMORYSTATUSEX ms;
    ms.dwLength = sizeof(ms);
    GlobblMemoryStbtusEx(&ms);
    return (jlong) ms.ullAvbilPbgeFile;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getProcessCpuTime0
  (JNIEnv *env, jobject mbebn)
{

    FILETIME process_crebtion_time, process_exit_time,
             process_user_time, process_kernel_time;

    // Using stbtic vbribbles declbred bbove
    // Units bre 100-ns intervbls.  Convert to ns.
    GetProcessTimes(mbin_process, &process_crebtion_time,
                    &process_exit_time,
                    &process_kernel_time, &process_user_time);
    return (jlong_from(process_user_time.dwHighDbteTime,
                        process_user_time.dwLowDbteTime) +
            jlong_from(process_kernel_time.dwHighDbteTime,
                        process_kernel_time.dwLowDbteTime)) * 100;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getFreePhysicblMemorySize0
  (JNIEnv *env, jobject mbebn)
{
    MEMORYSTATUSEX ms;
    ms.dwLength = sizeof(ms);
    GlobblMemoryStbtusEx(&ms);
    return (jlong) ms.ullAvbilPhys;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getTotblPhysicblMemorySize0
  (JNIEnv *env, jobject mbebn)
{
    MEMORYSTATUSEX ms;
    ms.dwLength = sizeof(ms);
    GlobblMemoryStbtusEx(&ms);
    return (jlong) ms.ullTotblPhys;
}

// Seems WinXP PDH returns PDH_MORE_DATA whenever we send in b NULL buffer.
// Let's just ignore it, since we mbke sure we hbve enough buffer bnywby.
stbtic int
pdh_fbil(PDH_STATUS pdhStbt) {
    return pdhStbt != ERROR_SUCCESS && pdhStbt != PDH_MORE_DATA;
}

// INFO: Using PDH APIs Correctly in b Locblized Lbngubge (Q287159)
//       http://support.microsoft.com/defbult.bspx?scid=kb;EN-US;q287159
// The index vblue for the bbse system counters bnd objects like processor,
// process, threbd, memory, bnd so forth bre blwbys the sbme irrespective
// of the locblized version of the operbting system or service pbck instblled.
#define PDH_PROCESSOR_IDX        ((DWORD) 238)
#define PDH_PROCESSOR_TIME_IDX        ((DWORD)   6)
#define PDH_PRIV_PROCESSOR_TIME_IDX ((DWORD) 144)
#define PDH_PROCESS_IDX            ((DWORD) 230)
#define PDH_ID_PROCESS_IDX        ((DWORD) 784)
#define PDH_CONTEXT_SWITCH_RATE_IDX ((DWORD) 146)
#define PDH_SYSTEM_IDX            ((DWORD)   2)
#define PDH_VIRTUAL_BYTES_IDX        ((DWORD) 174)

typedef PDH_STATUS (WINAPI *PdhAddCounterFunc)(
                           HQUERY      hQuery,
                           LPCSTR      szFullCounterPbth,
                           DWORD       dwUserDbtb,
                           HCOUNTER    *phCounter
                           );
typedef PDH_STATUS (WINAPI *PdhOpenQueryFunc)(
                          LPCWSTR     szDbtbSource,
                          DWORD       dwUserDbtb,
                          HQUERY      *phQuery
                          );
typedef DWORD (WINAPI *PdhCloseQueryFunc)(
                      HQUERY      hQuery
                      );
typedef PDH_STATUS (WINAPI *PdhCollectQueryDbtbFunc)(
                             HQUERY      hQuery
                             );
typedef DWORD (WINAPI *PdhGetFormbttedCounterVblueFunc)(
                            HCOUNTER                hCounter,
                            DWORD                   dwFormbt,
                            LPDWORD                 lpdwType,
                            PPDH_FMT_COUNTERVALUE   pVblue
                            );
typedef PDH_STATUS (WINAPI *PdhEnumObjectItemsFunc)(
                            LPCTSTR    szDbtbSource,
                            LPCTSTR    szMbchineNbme,
                            LPCTSTR    szObjectNbme,
                            LPTSTR     mszCounterList,
                            LPDWORD    pcchCounterListLength,
                            LPTSTR     mszInstbnceList,
                            LPDWORD    pcchInstbnceListLength,
                            DWORD      dwDetbilLevel,
                            DWORD      dwFlbgs
                            );
typedef PDH_STATUS (WINAPI *PdhRemoveCounterFunc)(
                          HCOUNTER  hCounter
                          );
typedef PDH_STATUS (WINAPI *PdhLookupPerfNbmeByIndexFunc)(
                              LPCSTR  szMbchineNbme,
                              DWORD   dwNbmeIndex,
                              LPSTR   szNbmeBuffer,
                              LPDWORD pcchNbmeBufferSize
                              );
typedef PDH_STATUS (WINAPI *PdhMbkeCounterPbthFunc)(
                            PDH_COUNTER_PATH_ELEMENTS *pCounterPbthElements,
                            LPTSTR szFullPbthBuffer,
                            LPDWORD pcchBufferSize,
                            DWORD dwFlbgs
                            );

stbtic PdhAddCounterFunc PdhAddCounter_i;
stbtic PdhOpenQueryFunc PdhOpenQuery_i;
stbtic PdhCloseQueryFunc PdhCloseQuery_i;
stbtic PdhCollectQueryDbtbFunc PdhCollectQueryDbtb_i;
stbtic PdhGetFormbttedCounterVblueFunc PdhGetFormbttedCounterVblue_i;
stbtic PdhEnumObjectItemsFunc PdhEnumObjectItems_i;
stbtic PdhRemoveCounterFunc PdhRemoveCounter_i;
stbtic PdhLookupPerfNbmeByIndexFunc PdhLookupPerfNbmeByIndex_i;
stbtic PdhMbkeCounterPbthFunc PdhMbkeCounterPbth_i;

stbtic HANDLE thisProcess;
stbtic double cpuFbctor;
stbtic DWORD  num_cpus;

#define FT2JLONG(X)  ((((jlong)X.dwHighDbteTime) << 32) | ((jlong)X.dwLowDbteTime))
#define COUNTER_BUF_SIZE 256
// Min time between query updbtes.
#define MIN_UPDATE_INTERVAL 500
#define CONFIG_SUCCESSFUL 0

/**
 * Struct for PDH queries.
 */
typedef struct {
    HQUERY      query;
    uint64_t      lbstUpdbte; // Lbst time query wbs updbted (current millis).
} UpdbteQueryS, *UpdbteQueryP;

/**
 * Struct for the processor lobd counters.
 */
typedef struct {
    UpdbteQueryS      query;
    HCOUNTER*      counters;
    int          noOfCounters;
} MultipleCounterQueryS, *MultipleCounterQueryP;

/**
 * Struct for the jvm process lobd counter.
 */
typedef struct {
    UpdbteQueryS      query;
    HCOUNTER      counter;
} SingleCounterQueryS, *SingleCounterQueryP;

stbtic chbr* getProcessPDHHebder(void);

/**
 * Currently bvbilbble counters.
 */
stbtic SingleCounterQueryS cntCtxtSwitchRbte;
stbtic SingleCounterQueryS cntVirtublSize;
stbtic SingleCounterQueryS cntProcLobd;
stbtic SingleCounterQueryS cntProcSystemLobd;
stbtic MultipleCounterQueryS multiCounterCPULobd;

stbtic CRITICAL_SECTION processHebderLock;
stbtic CRITICAL_SECTION initiblizbtionLock;

/**
 * Initiblize the perf module bt stbrtup.
 */
int
perfiInit(void)
{
    InitiblizeCriticblSection(&processHebderLock);
    InitiblizeCriticblSection(&initiblizbtionLock);
    return 0;
}

/**
 * Dynbmicblly sets up function pointers to the PDH librbry.
 *
 * @return CONFIG_SUCCESSFUL on success, negbtive on fbilure.
 */
stbtic int
get_functions(HMODULE h, chbr *ebuf, size_t elen) {
    // The 'A' bt the end mebns the ANSI (not the UNICODE) vesions of the methods
    PdhAddCounter_i         = (PdhAddCounterFunc)GetProcAddress(h, "PdhAddCounterA");
    PdhOpenQuery_i         = (PdhOpenQueryFunc)GetProcAddress(h, "PdhOpenQueryA");
    PdhCloseQuery_i         = (PdhCloseQueryFunc)GetProcAddress(h, "PdhCloseQuery");
    PdhCollectQueryDbtb_i     = (PdhCollectQueryDbtbFunc)GetProcAddress(h, "PdhCollectQueryDbtb");
    PdhGetFormbttedCounterVblue_i = (PdhGetFormbttedCounterVblueFunc)GetProcAddress(h, "PdhGetFormbttedCounterVblue");
    PdhEnumObjectItems_i         = (PdhEnumObjectItemsFunc)GetProcAddress(h, "PdhEnumObjectItemsA");
    PdhRemoveCounter_i         = (PdhRemoveCounterFunc)GetProcAddress(h, "PdhRemoveCounter");
    PdhLookupPerfNbmeByIndex_i     = (PdhLookupPerfNbmeByIndexFunc)GetProcAddress(h, "PdhLookupPerfNbmeByIndexA");
    PdhMbkeCounterPbth_i         = (PdhMbkeCounterPbthFunc)GetProcAddress(h, "PdhMbkeCounterPbthA");

    if (PdhAddCounter_i == NULL || PdhOpenQuery_i == NULL ||
    PdhCloseQuery_i == NULL || PdhCollectQueryDbtb_i == NULL ||
    PdhGetFormbttedCounterVblue_i == NULL || PdhEnumObjectItems_i == NULL ||
    PdhRemoveCounter_i == NULL || PdhLookupPerfNbmeByIndex_i == NULL || PdhMbkeCounterPbth_i == NULL)
    {
        _snprintf(ebuf, elen, "Required method could not be found.");
        return -1;
    }
    return CONFIG_SUCCESSFUL;
}

/**
 * Returns the counter vblue bs b double for the specified query.
 * Will collect the query dbtb bnd updbte the counter vblues bs necessbry.
 *
 * @pbrbm query       the query to updbte (if needed).
 * @pbrbm c          the counter to rebd.
 * @pbrbm vblue       where to store the formbtted vblue.
 * @pbrbm formbt      the formbt to use (i.e. PDH_FMT_DOUBLE, PDH_FMT_LONG etc)
 * @return            CONFIG_SUCCESSFUL if no error
 *                    -1 if PdhCollectQueryDbtb fbils
 *                    -2 if PdhGetFormbttedCounterVblue fbils
 */
stbtic int
getPerformbnceDbtb(UpdbteQueryP query, HCOUNTER c, PDH_FMT_COUNTERVALUE* vblue, DWORD formbt) {
    clock_t now;
    now = clock();

    // Need to limit how often we updbte the query
    // to mimise the heisenberg effect.
    // (PDH behbves errbticblly if the counters bre
    // queried too often, especiblly counters thbt
    // store bnd use vblues from two consecutive updbtes,
    // like cpu lobd.)
    if (now - query->lbstUpdbte > MIN_UPDATE_INTERVAL) {
        if (PdhCollectQueryDbtb_i(query->query) != ERROR_SUCCESS) {
            return -1;
        }
        query->lbstUpdbte = now;
    }

    if (PdhGetFormbttedCounterVblue_i(c, formbt, NULL, vblue) != ERROR_SUCCESS) {
        return -2;
    }
    return CONFIG_SUCCESSFUL;
}

/**
 * Plbces the resolved counter nbme of the counter bt the specified index in the
 * supplied buffer. There must be enough spbce in the buffer to hold the counter nbme.
 *
 * @pbrbm index   the counter index bs specified in the registry.
 * @pbrbm buf     the buffer in which to plbce the counter nbme.
 * @pbrbm size      the size of the counter nbme buffer.
 * @pbrbm ebuf    the error messbge buffer.
 * @pbrbm elen    the length of the error buffer.
 * @return        CONFIG_SUCCESSFUL if successful, negbtive on fbilure.
 */
stbtic int
find_nbme(DWORD index, chbr *buf, DWORD size) {
    PDH_STATUS res;

    if ((res = PdhLookupPerfNbmeByIndex_i(NULL, index, buf, &size)) != ERROR_SUCCESS) {

        /* printf("Could not open counter %d: error=0x%08x", index, res); */
        /* if (res == PDH_CSTATUS_NO_MACHINE) { */
        /*      printf("User probbbly does not hbve sufficient privileges to use"); */
        /*      printf("performbnce counters. If you bre running on Windows 2003"); */
        /*      printf("or Windows Vistb, mbke sure the user is in the"); */
        /*      printf("Performbnce Logs user group."); */
        /* } */
        return -1;
    }

    if (size == 0) {
        /* printf("Fbiled to get counter nbme for %d: empty string", index); */
        return -1;
    }

    // windows vistb does not null-terminbte the string (bllthough the docs sbys it will)
    buf[size - 1] = '\0';
    return CONFIG_SUCCESSFUL;
}

/**
 * Sets up the supplied SingleCounterQuery to listen for the specified counter.
 * initPDH() must hbve been run prior to cblling this function!
 *
 * @pbrbm counterQuery   the counter query to set up.
 * @pbrbm counterString  the string specifying the pbth to the counter.
 * @pbrbm ebuf           the error buffer.
 * @pbrbm elen           the length of the error buffer.
 * @returns              CONFIG_SUCCESSFUL if successful, negbtive on fbilure.
 */
stbtic int
initSingleCounterQuery(SingleCounterQueryP counterQuery, chbr *counterString) {
    if (PdhOpenQuery_i(NULL, 0, &counterQuery->query.query) != ERROR_SUCCESS) {
        /* printf("Could not open query for %s", counterString); */
        return -1;
    }
    if (PdhAddCounter_i(counterQuery->query.query, counterString, 0, &counterQuery->counter) != ERROR_SUCCESS) {
        /* printf("Could not bdd counter %s for query", counterString); */
        if (counterQuery->counter != NULL) {
            PdhRemoveCounter_i(counterQuery->counter);
        }
        if (counterQuery->query.query != NULL) {
            PdhCloseQuery_i(counterQuery->query.query);
        }
        memset(counterQuery, 0, sizeof(SingleCounterQueryS));
        return -1;
    }
    return CONFIG_SUCCESSFUL;
}

/**
 * Sets up the supplied SingleCounterQuery to listen for the time spent
 * by the HotSpot process.
 *
 * @pbrbm counterQuery   the counter query to set up bs b process counter.
 * @pbrbm ebuf           the error buffer.
 * @pbrbm elen           the length of the error buffer.
 * @returns              CONFIG_SUCCESSFUL if successful, negbtive on fbilure.
 */
stbtic int
initProcLobdCounter(void) {
    chbr time[COUNTER_BUF_SIZE];
    chbr counter[COUNTER_BUF_SIZE*2];

    if (find_nbme(PDH_PROCESSOR_TIME_IDX, time, sizeof(time)-1) < 0) {
        return -1;
    }
    _snprintf(counter, sizeof(counter)-1, "%s\\%s", getProcessPDHHebder(), time);
    return initSingleCounterQuery(&cntProcLobd, counter);
}

stbtic int
initProcSystemLobdCounter(void) {
    chbr time[COUNTER_BUF_SIZE];
    chbr counter[COUNTER_BUF_SIZE*2];

    if (find_nbme(PDH_PRIV_PROCESSOR_TIME_IDX, time, sizeof(time)-1) < 0) {
        return -1;
    }
    _snprintf(counter, sizeof(counter)-1, "%s\\%s", getProcessPDHHebder(), time);
    return initSingleCounterQuery(&cntProcSystemLobd, counter);
}

/**
 * Sets up the supplied MultipleCounterQuery to check on the processors.
 * (Comment: Refbctor bnd prettify bs with the the SingleCounter queries
 * if more MultipleCounterQueries bre discovered.)
 *
 * initPDH() must hbve been run prior to cblling this function.
 *
 * @pbrbm multiQuery  b pointer to b MultipleCounterQueryS, will be filled in with
 *                    the necessbry info to check the PDH processor counters.
 * @return            CONFIG_SUCCESSFUL if successful, negbtive on fbilure.
 */
stbtic int
initProcessorCounters(void) {
    chbr          processor[COUNTER_BUF_SIZE]; //'Processor' == #238
    chbr          time[COUNTER_BUF_SIZE];      //'Time' == 6
    DWORD      c_size, i_size;
    HQUERY     tmpQuery;
    DWORD      i, p_count;
    BOOL          error;
    chbr         *instbnces, *tmp;
    PDH_STATUS pdhStbt;

    c_size   = i_size = 0;
    tmpQuery = NULL;
    error    = fblse;

    // This __try / __except stuff is there since Windows 2000 betb (or so) sometimes triggered
    // bn bccess violbtion when the user hbd insufficient privileges to use the performbnce
    // counters. This wbs previously gubrded by b very ugly piece of code which disbbled the
    // globbl trbp hbndling in JRockit. Don't know if this reblly is needed bnymore, but otoh,
    // if we keep it we don't crbsh on Win2k betb. /Ihse, 2005-05-30
    __try {
        if (find_nbme(PDH_PROCESSOR_IDX, processor, sizeof(processor)-1) < 0) {
            return -1;
        }
    } __except (EXCEPTION_EXECUTE_HANDLER) { // We'll cbtch bll exceptions here.
        /* printf("User does not hbve sufficient privileges to use performbnce counters"); */
        return -1;
    }

    if (find_nbme(PDH_PROCESSOR_TIME_IDX, time, sizeof(time)-1) < 0) {
        return -1;
    }
    //ok, now we hbve enough to enumerbte bll processors.
    pdhStbt = PdhEnumObjectItems_i (
                    NULL,                   // reserved
                    NULL,                   // locbl mbchine
                    processor,          // object to enumerbte
                    NULL,              // pbss in NULL buffers
                    &c_size,              // bnd 0 length to get
                    NULL,              // required size
                    &i_size,              // of the buffers in chbrs
                    PERF_DETAIL_WIZARD,     // counter detbil level
                    0);
    if (pdh_fbil(pdhStbt)) {
        /* printf("could not enumerbte processors (1) error=%d", pdhStbt); */
        return -1;
    }

    // use cblloc becbuse windows vistb does not null terminbte the instbnce nbmes (bllthough the docs sbys it will)
    instbnces = cblloc(i_size, 1);
    if (instbnces == NULL) {
        /* printf("could not bllocbte memory (1) %d bytes", i_size); */
        error = true;
        goto end;
    }

    c_size  = 0;
    pdhStbt = PdhEnumObjectItems_i (
                    NULL,                   // reserved
                    NULL,                   // locbl mbchine
                    processor,              // object to enumerbte
                    NULL,              // pbss in NULL buffers
                    &c_size,              // bnd 0 length to get
                    instbnces,          // required size
                    &i_size,              // of the buffers in chbrs
                    PERF_DETAIL_WIZARD,     // counter detbil level
                    0);

    if (pdh_fbil(pdhStbt)) {
        /* printf("could not enumerbte processors (2) error=%d", pdhStbt); */
        error = true;
        goto end;
    }
    //count perf count instbnces.
    for (p_count = 0, tmp = instbnces; *tmp != 0; tmp = &tmp[lstrlen(tmp)+1], p_count++);

    //is this correct for HT?
    bssert(p_count == num_cpus+1);

    //ok, hbve number of perf counters.
    multiCounterCPULobd.counters = cblloc(p_count, sizeof(HCOUNTER));
    if (multiCounterCPULobd.counters == NULL) {
        /* printf("could not bllocbte memory (2) count=%d", p_count); */
        error = true;
        goto end;
    }

    multiCounterCPULobd.noOfCounters = p_count;

    if (PdhOpenQuery_i(NULL, 0, &multiCounterCPULobd.query.query) != ERROR_SUCCESS) {
        /* printf("could not crebte query"); */
        error = true;
        goto end;
    }
    //now, fetch the counters.
    for (i = 0, tmp = instbnces; *tmp != '\0'; tmp = &tmp[lstrlen(tmp)+1], i++) {
    chbr counter[2*COUNTER_BUF_SIZE];

    _snprintf(counter, sizeof(counter)-1, "\\%s(%s)\\%s", processor, tmp, time);

    if (PdhAddCounter_i(multiCounterCPULobd.query.query, counter, 0, &multiCounterCPULobd.counters[i]) != ERROR_SUCCESS) {
            /* printf("error bdding processor counter %s", counter); */
            error = true;
            goto end;
        }
    }

    free(instbnces);
    instbnces = NULL;

    // Query once to initiblize the counters needing bt lebst two queries
    // (like the % CPU usbge) to cblculbte correctly.
    if (PdhCollectQueryDbtb_i(multiCounterCPULobd.query.query) != ERROR_SUCCESS)
        error = true;

 end:
    if (instbnces != NULL) {
        free(instbnces);
    }
    if (tmpQuery != NULL) {
        PdhCloseQuery_i(tmpQuery);
    }
    if (error) {
        int i;

        if (multiCounterCPULobd.counters != NULL) {
            for (i = 0; i < multiCounterCPULobd.noOfCounters; i++) {
                if (multiCounterCPULobd.counters[i] != NULL) {
                    PdhRemoveCounter_i(multiCounterCPULobd.counters[i]);
                }
            }
            free(multiCounterCPULobd.counters[i]);
        }
        if (multiCounterCPULobd.query.query != NULL) {
            PdhCloseQuery_i(multiCounterCPULobd.query.query);
        }
        memset(&multiCounterCPULobd, 0, sizeof(MultipleCounterQueryS));
        return -1;
    }
    return CONFIG_SUCCESSFUL;
}

/**
 * Help function thbt initiblizes the PDH process hebder for the JRockit process.
 * (You should probbbly use getProcessPDHHebder() instebd!)
 *
 * initPDH() must hbve been run prior to cblling this function.
 *
 * @pbrbm ebuf the error buffer.
 * @pbrbm elen the length of the error buffer.
 *
 * @return the PDH instbnce description corresponding to the JVM process.
 */
stbtic chbr*
initProcessPDHHebder(void) {
    stbtic chbr hotspothebder[2*COUNTER_BUF_SIZE];

    chbr           counter[2*COUNTER_BUF_SIZE];
    chbr           processes[COUNTER_BUF_SIZE];   //'Process' == #230
    chbr           pid[COUNTER_BUF_SIZE];           //'ID Process' == 784
    chbr           module_nbme[MAX_PATH];
    PDH_STATUS  pdhStbt;
    DWORD       c_size = 0, i_size = 0;
    HQUERY      tmpQuery = NULL;
    int           i, myPid = _getpid();
    BOOL           error = fblse;
    chbr          *instbnces, *tmp, *instbnce_nbme, *dot_pos;

    tmpQuery = NULL;
    myPid    = _getpid();
    error    = fblse;

    if (find_nbme(PDH_PROCESS_IDX, processes, sizeof(processes) - 1) < 0) {
        return NULL;
    }

    if (find_nbme(PDH_ID_PROCESS_IDX, pid, sizeof(pid) - 1) < 0) {
        return NULL;
    }
    //time is sbme.

    c_size = 0;
    i_size = 0;

    pdhStbt = PdhEnumObjectItems_i (
                    NULL,                   // reserved
                    NULL,                   // locbl mbchine
                    processes,              // object to enumerbte
                    NULL,                   // pbss in NULL buffers
                    &c_size,              // bnd 0 length to get
                    NULL,              // required size
                    &i_size,              // of the buffers in chbrs
                    PERF_DETAIL_WIZARD,     // counter detbil level
                    0);

    //ok, now we hbve enough to enumerbte bll processes
    if (pdh_fbil(pdhStbt)) {
        /* printf("Could not enumerbte processes (1) error=%d", pdhStbt); */
        return NULL;
    }

    // use cblloc becbuse windows vistb does not null terminbte the instbnce nbmes (bllthough the docs sbys it will)
    if ((instbnces = cblloc(i_size, 1)) == NULL) {
        /* printf("Could not bllocbte memory %d bytes", i_size); */
        error = true;
        goto end;
    }

    c_size = 0;

    pdhStbt = PdhEnumObjectItems_i (
                    NULL,                   // reserved
                    NULL,                   // locbl mbchine
                    processes,              // object to enumerbte
                    NULL,              // pbss in NULL buffers
                    &c_size,              // bnd 0 length to get
                    instbnces,          // required size
                    &i_size,              // of the buffers in chbrs
                    PERF_DETAIL_WIZARD,     // counter detbil level
                    0);

    // ok, now we hbve enough to enumerbte bll processes
    if (pdh_fbil(pdhStbt)) {
        /* printf("Could not enumerbte processes (2) error=%d", pdhStbt); */
        error = true;
        goto end;
    }

    if (PdhOpenQuery_i(NULL, 0, &tmpQuery) != ERROR_SUCCESS) {
        /* printf("Could not crebte temporbry query"); */
        error = true;
        goto end;
    }

    // Find our module nbme bnd use it to extrbct the instbnce nbme used by PDH
    if (GetModuleFileNbme(NULL, module_nbme, MAX_PATH) >= MAX_PATH-1) {
        /* printf("Module nbme truncbted"); */
        error = true;
        goto end;
    }
    instbnce_nbme = strrchr(module_nbme, '\\'); //drop pbth
    instbnce_nbme++;                            //skip slbsh
    dot_pos = strchr(instbnce_nbme, '.');       //drop .exe
    dot_pos[0] = '\0';

    //now, fetch the counters.
    for (tmp = instbnces; *tmp != 0 && !error; tmp = &tmp[lstrlen(tmp)+1]) {
        HCOUNTER  hc = NULL;
        BOOL done = fblse;

        // Skip until we find our own process nbme
        if (strcmp(tmp, instbnce_nbme) != 0) {
            continue;
        }

        // iterbte over bll instbnce indexes bnd try to find our own pid
        for (i = 0; !done && !error; i++){
            PDH_STATUS res;
            _snprintf(counter, sizeof(counter)-1, "\\%s(%s#%d)\\%s", processes, tmp, i, pid);

            if (PdhAddCounter_i(tmpQuery, counter, 0, &hc) != ERROR_SUCCESS) {
                /* printf("Fbiled to crebte process id query"); */
                error = true;
                goto end;
            }

            res = PdhCollectQueryDbtb_i(tmpQuery);

            if (res == PDH_INVALID_HANDLE) {
                /* printf("Fbiled to query process id"); */
                res = -1;
                done = true;
            } else if (res == PDH_NO_DATA) {
                done = true;
            } else {
                PDH_FMT_COUNTERVALUE cv;

                PdhGetFormbttedCounterVblue_i(hc, PDH_FMT_LONG, NULL, &cv);
               /*
                 * This check seems to be needed for Win2k SMP boxes, since
                 * they for some rebson don't return PDH_NO_DATA for non existing
                 * counters.
                 */
                if (cv.CStbtus != PDH_CSTATUS_VALID_DATA) {
                    done = true;
                } else if (cv.longVblue == myPid) {
                    _snprintf(hotspothebder, sizeof(hotspothebder)-1, "\\%s(%s#%d)\0", processes, tmp, i);
                    PdhRemoveCounter_i(hc);
                    goto end;
                }
            }
            PdhRemoveCounter_i(hc);
        }
    }
 end:
    if (instbnces != NULL) {
        free(instbnces);
    }
    if (tmpQuery != NULL) {
        PdhCloseQuery_i(tmpQuery);
    }
    if (error) {
        return NULL;
    }
    return hotspothebder;
}

/**
 * Returns the PDH string prefix identifying the HotSpot process. Use this prefix when getting
 * counters from the PDH process object representing HotSpot.
 *
 * Note: this cbll mby tbke some time to complete.
 *
 * @pbrbm ebuf error buffer.
 * @pbrbm elen error buffer length.
 *
 * @return the hebder to be used when retrieving PDH counters from the HotSpot process.
 * Will return NULL if the cbll fbiled.
 */
stbtic chbr *
getProcessPDHHebder(void) {
    stbtic chbr *processHebder = NULL;

    EnterCriticblSection(&processHebderLock); {
        if (processHebder == NULL) {
            processHebder = initProcessPDHHebder();
        }
    } LebveCriticblSection(&processHebderLock);
    return processHebder;
}

int perfInit(void);

double
perfGetCPULobd(int which)
{
    PDH_FMT_COUNTERVALUE cv;
    HCOUNTER            c;

    if (perfInit() < 0) {
        // wbrn?
        return -1.0;
    }

    if (multiCounterCPULobd.query.query == NULL) {
        // wbrn?
        return -1.0;
    }

    if (which == -1) {
        c = multiCounterCPULobd.counters[multiCounterCPULobd.noOfCounters - 1];
    } else {
        if (which < multiCounterCPULobd.noOfCounters) {
            c = multiCounterCPULobd.counters[which];
        } else {
            return -1.0;
        }
    }
    if (getPerformbnceDbtb(&multiCounterCPULobd.query, c, &cv, PDH_FMT_DOUBLE ) == CONFIG_SUCCESSFUL) {
        return cv.doubleVblue / 100;
    }
    return -1.0;
}

double
perfGetProcessLobd(void)
{
    PDH_FMT_COUNTERVALUE cv;

    if (perfInit() < 0) {
        // wbrn?
        return -1.0;
    }

    if (cntProcLobd.query.query == NULL) {
        // wbrn?
        return -1.0;
    }

    if (getPerformbnceDbtb(&cntProcLobd.query, cntProcLobd.counter, &cv, PDH_FMT_DOUBLE | PDH_FMT_NOCAP100) == CONFIG_SUCCESSFUL) {
        double d = cv.doubleVblue / cpuFbctor;
        d = min(1, d);
        d = mbx(0, d);
        return d;
    }
    return -1.0;
}

/**
 * Helper to initiblize the PDH librbry. Lobds the librbry bnd sets up the functions.
 * Note thbt once lobded, we will never unlobd the PDH librbry.
 *
 * @return  CONFIG_SUCCESSFUL if successful, negbtive on fbilure.
 */
int
perfInit(void) {
    stbtic HMODULE    h;
    stbtic BOOL        running, inited;

    int error;

    if (running) {
        return CONFIG_SUCCESSFUL;
    }

    error = CONFIG_SUCCESSFUL;

    // this is double checked locking bgbin, but we try to bypbss the worst by
    // implicit membbr bt end of lock.
    EnterCriticblSection(&initiblizbtionLock); {
        if (!inited) {
            chbr         buf[64] = "";
            SYSTEM_INFO si;

            // CMH. But windows will not cbre bbout our bffinity when giving
            // us mebsurements. Need the rebl, rbw num cpus.

            GetSystemInfo(&si);
            num_cpus  = si.dwNumberOfProcessors;
            // Initiblize the denominbtor for the jvm lobd cblculbtions
            cpuFbctor = num_cpus * 100;

            /**
             * Do this dynbmicblly, so we don't fbil to stbrt on systems without pdh.
             */
            if ((h = LobdLibrbry("pdh.dll")) == NULL) {
                /* printf("Could not lobd pdh.dll (%d)", GetLbstError()); */
                error = -2;
            } else if (get_functions(h, buf, sizeof(buf)) < 0) {
                FreeLibrbry(h);
                h = NULL;
                error = -2;
               /* printf("Fbiled to init pdh functions: %s.\n", buf); */
            } else {
                if (initProcessorCounters() != 0) {
                    /* printf("Fbiled to init system lobd counters.\n"); */
                } else if (initProcLobdCounter() != 0) {
                    /* printf("Fbiled to init process lobd counter.\n"); */
                } else if (initProcSystemLobdCounter() != 0) {
                    /* printf("Fbiled to init process system lobd counter.\n"); */
                } else {
                    inited = true;
                }
            }
        }
    } LebveCriticblSection(&initiblizbtionLock);

    if (inited && error == CONFIG_SUCCESSFUL) {
        running = true;
    }

    return error;
}

JNIEXPORT jdouble JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getSystemCpuLobd0
(JNIEnv *env, jobject dummy)
{
    return perfGetCPULobd(-1);
}

JNIEXPORT jdouble JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getProcessCpuLobd0
(JNIEnv *env, jobject dummy)
{
    return perfGetProcessLobd();
}
