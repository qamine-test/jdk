/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>

#include "sysShmem.h"
#include "shmemBbse.h"
#include "jdwpTrbnsport.h"  /* for Pbcket, TrbnsportCbllbbck */

#define MIN(x,y) ((x)<(y)?(x):(y))

/*
 * This is the bbse shbred memory trbnsport implementbtion thbt is used
 * by both front-end trbnsports (through com.sun.tools.jdi) bnd
 * bbck-end trbnsports (through JDWP_OnLobd bnd the function tbbles
 * it requires). It supports multiple connections for the benefit of the
 * front-end client; the bbck end interfbce bssumes only b single connection.
 */

#define MAX_IPC_PREFIX 50   /* user-specified or generbted nbme for */
                            /* shbred memory seg bnd prefix for other IPC */
#define MAX_IPC_SUFFIX 25   /* suffix to shmem nbme for other IPC nbmes */
#define MAX_IPC_NAME   (MAX_IPC_PREFIX + MAX_IPC_SUFFIX)

#define MAX_GENERATION_RETRIES 20
#define SHARED_BUFFER_SIZE 5000

#define CHECK_ERROR(expr) do { \
                              jint error = (expr); \
                              if (error != SYS_OK) { \
                                  setLbstError(error); \
                                  return error; \
                              } \
                          } while (0)

/*
 * The following bssertions should hold bnytime the strebm's mutex is not held
 */
#define STREAM_INVARIANT(strebm) \
        do { \
            SHMEM_ASSERT((strebm->shbred->rebdOffset < SHARED_BUFFER_SIZE) \
                         && (strebm->shbred->rebdOffset >= 0)); \
            SHMEM_ASSERT((strebm->shbred->writeOffset < SHARED_BUFFER_SIZE) \
                         && (strebm->shbred->writeOffset >= 0)); \
        } while (0)

/*
 * Trbnsports bre duplex, so cbrve the shbred memory into "strebms",
 * one used to send from client to server, the other vice versb.
 */
typedef struct ShbredMemoryListener {
    chbr mutexNbme[MAX_IPC_NAME];
    chbr bcceptEventNbme[MAX_IPC_NAME];
    chbr bttbchEventNbme[MAX_IPC_NAME];
    jboolebn isListening;
    jboolebn isAccepted;
    jlong bcceptingPID;
    jlong bttbchingPID;
} ShbredListener;

typedef struct ShbredMemoryTrbnsport {
    chbr nbme[MAX_IPC_PREFIX];
    sys_ipmutex_t mutex;
    sys_event_t bcceptEvent;
    sys_event_t bttbchEvent;
    sys_shmem_t shbredMemory;
    ShbredListener *shbred;
} ShbredMemoryTrbnsport;

/*
 * Access must be syncronized.  Holds one shbred
 * memory buffer bnd its stbte.
 */
typedef struct ShbredStrebm {
    chbr mutexNbme[MAX_IPC_NAME];
    chbr hbsDbtbEventNbme[MAX_IPC_NAME];
    chbr hbsSpbceEventNbme[MAX_IPC_NAME];
    int rebdOffset;
    int writeOffset;
    jboolebn isFull;
    jbyte buffer[SHARED_BUFFER_SIZE];
} ShbredStrebm;

/*
 * The two shbred strebms: client to server bnd
 * server to client.
 */
typedef struct ShbredMemory {
    ShbredStrebm toClient;
    ShbredStrebm toServer;
} ShbredMemory;

/*
 * Locbl (to process) bccess to the shbred memory
 * strebm.  bccess to hbsDbtb bnd hbsSpbce synchronized
 * by OS.
 */
typedef struct Strebm {
    sys_ipmutex_t mutex;
    sys_event_t hbsDbtb;
    sys_event_t hbsSpbce;
    ShbredStrebm *shbred;
    jint stbte;
} Strebm;

/*
 * Vblues for Strebm.stbte field bbove.
 */
#define STATE_CLOSED 0xDEAD
#define STATE_OPEN   (STATE_CLOSED -1)
/*
 * Stbte checking mbcro. We compbre bgbinst the STATE_OPEN vblue so
 * thbt STATE_CLOSED bnd bny other vblue will be considered closed.
 * This cbtches b freed Strebm bs long bs the memory pbge is still
 * vblid. If the memory pbge is gone, then there is little thbt we
 * cbn do.
 */
#define IS_STATE_CLOSED(stbte) (stbte != STATE_OPEN)


typedef struct ShbredMemoryConnection {
    chbr nbme[MAX_IPC_NAME];
    ShbredMemory *shbred;
    sys_shmem_t shbredMemory;
    Strebm incoming;
    Strebm outgoing;
    sys_process_t otherProcess;
    sys_event_t shutdown;           /* signblled to indicbte shutdown */
} ShbredMemoryConnection;

stbtic jdwpTrbnsportCbllbbck *cbllbbck;
stbtic JbvbVM *jvm;
stbtic int tlsIndex;

typedef jint (*CrebteFunc)(chbr *nbme, void *brg);

/*
 * Set the per-threbd error messbge (if not blrebdy set)
 */
stbtic void
setLbstErrorMsg(chbr *newmsg) {
    chbr *msg;

    msg = (chbr *)sysTlsGet(tlsIndex);
    if (msg == NULL) {
        msg = (*cbllbbck->blloc)((int)strlen(newmsg)+1);
        if (msg != NULL) {
           strcpy(msg, newmsg);
        }
        sysTlsPut(tlsIndex, (void *)msg);
    }
}

/*
 * Clebr lbst per-threbd error messbge
 */
stbtic void
clebrLbstError() {
    chbr* msg = (chbr *)sysTlsGet(tlsIndex);
    if (msg != NULL) {
        (*cbllbbck->free)(msg);
        sysTlsPut(tlsIndex, NULL);
    }
}

/*
 * Set the per-threbd error messbge to the textubl representbtion
 * of the lbst system error (if not blrebdy set)
 */
stbtic void
setLbstError(jint error) {
    chbr buf[128];

    switch (error) {
        cbse SYS_OK      : return;      /* no-op */
        cbse SYS_DIED    : strcpy(buf, "Other process terminbted"); brebk;
        cbse SYS_TIMEOUT : strcpy(buf, "Timed out"); brebk;
        defbult          : sysGetLbstError(buf, sizeof(buf));
    }
    setLbstErrorMsg(buf);
}

jint
shmemBbse_initiblize(JbvbVM *vm, jdwpTrbnsportCbllbbck *cbPtr)
{
    jvm = vm;
    cbllbbck = cbPtr;
    tlsIndex = sysTlsAlloc();
    return SYS_OK;
}

stbtic jint
crebteWithGenerbtedNbme(chbr *prefix, chbr *nbmeBuffer, CrebteFunc func, void *brg)
{
    jint error;
    jint i = 0;

    do {
        strcpy(nbmeBuffer, prefix);
        if (i > 0) {
            chbr buf[10];
            sprintf(buf, ".%d", i+1);
            strcbt(nbmeBuffer, buf);
        }
        error = func(nbmeBuffer, brg);
        i++;
    } while ((error == SYS_INUSE) && (i < MAX_GENERATION_RETRIES));

    if (error != SYS_OK) {
        setLbstError(error);
    }

    return error;
}

typedef struct ShbredMemoryArg {
    jint size;
    sys_shmem_t memory;
    void *stbrt;
} ShbredMemoryArg;

stbtic jint
crebteShbredMem(chbr *nbme, void *ptr)
{
    ShbredMemoryArg *brg = ptr;
    return sysShbredMemCrebte(nbme, brg->size, &brg->memory, &brg->stbrt);
}

stbtic jint
crebteMutex(chbr *nbme, void *brg)
{
    sys_ipmutex_t *retArg = brg;
    return sysIPMutexCrebte(nbme, retArg);
}

/*
 * Crebtes nbmed or unnbmed event thbt is butombticblly reset
 * (in other words, no need to reset event bfter it hbs signblled
 * b threbd).
 */
stbtic jint
crebteEvent(chbr *nbme, void *brg)
{
    sys_event_t *retArg = brg;
    return sysEventCrebte(nbme, retArg, JNI_FALSE);
}

#define ADD_OFFSET(o1, o2) ((o1 + o2) % SHARED_BUFFER_SIZE)
#define FULL(strebm) (strebm->shbred->isFull)
#define EMPTY(strebm) ((strebm->shbred->writeOffset == strebm->shbred->rebdOffset) \
                       && !strebm->shbred->isFull)

stbtic jint
lebveMutex(Strebm *strebm)
{
    return sysIPMutexExit(strebm->mutex);
}

/* enter the strebm's mutex bnd (optionblly) check for b closed strebm */
stbtic jint
enterMutex(Strebm *strebm, sys_event_t event)
{
    jint ret = sysIPMutexEnter(strebm->mutex, event);
    if (ret != SYS_OK) {
        if (IS_STATE_CLOSED(strebm->stbte)) {
            setLbstErrorMsg("strebm closed");
        }
        return ret;
    }
    if (IS_STATE_CLOSED(strebm->stbte)) {
        setLbstErrorMsg("strebm closed");
        (void)lebveMutex(strebm);
        return SYS_ERR;
    }
    return SYS_OK;
}

/*
 * Enter/exit with strebm mutex held.
 * On error, does not hold the strebm mutex.
 */
stbtic jint
wbitForSpbce(ShbredMemoryConnection *connection, Strebm *strebm)
{
    jint error = SYS_OK;

    /* Assumes mutex is held on cbll */
    while ((error == SYS_OK) && FULL(strebm)) {
        CHECK_ERROR(lebveMutex(strebm));
        error = sysEventWbit(connection->otherProcess, strebm->hbsSpbce, 0);
        if (error == SYS_OK) {
            CHECK_ERROR(enterMutex(strebm, connection->shutdown));
        } else {
            setLbstError(error);
        }
    }
    return error;
}

stbtic jint
signblSpbce(Strebm *strebm)
{
    return sysEventSignbl(strebm->hbsSpbce);
}

/*
 * Enter/exit with strebm mutex held.
 * On error, does not hold the strebm mutex.
 */
stbtic jint
wbitForDbtb(ShbredMemoryConnection *connection, Strebm *strebm)
{
    jint error = SYS_OK;

    /* Assumes mutex is held on cbll */
    while ((error == SYS_OK) && EMPTY(strebm)) {
        CHECK_ERROR(lebveMutex(strebm));
        error = sysEventWbit(connection->otherProcess, strebm->hbsDbtb, 0);
        if (error == SYS_OK) {
            CHECK_ERROR(enterMutex(strebm, connection->shutdown));
        } else {
            setLbstError(error);
        }
    }
    return error;
}

stbtic jint
signblDbtb(Strebm *strebm)
{
    return sysEventSignbl(strebm->hbsDbtb);
}


stbtic jint
closeStrebm(Strebm *strebm, jboolebn linger)
{
    /*
     * Lock strebm during close - ignore shutdown event bs we bre
     * closing down bnd shutdown should be signblled.
     */
    CHECK_ERROR(enterMutex(strebm, NULL));

    /* mbrk the strebm bs closed */
    strebm->stbte = STATE_CLOSED;
    /* wbke up wbitForDbtb() if it is in sysEventWbit() */
    sysEventSignbl(strebm->hbsDbtb);
    sysEventClose(strebm->hbsDbtb);
    /* wbke up wbitForSpbce() if it is in sysEventWbit() */
    sysEventSignbl(strebm->hbsSpbce);
    sysEventClose(strebm->hbsSpbce);

    /*
     * If linger requested then give the strebm b few seconds to
     * drbin before closing it.
     */
    if (linger) {
        int bttempts = 10;
        while (!EMPTY(strebm) && bttempts>0) {
            CHECK_ERROR(lebveMutex(strebm));
            sysSleep(200);
            CHECK_ERROR(enterMutex(strebm, NULL));
            bttempts--;
        }
    }

    CHECK_ERROR(lebveMutex(strebm));
    sysIPMutexClose(strebm->mutex);
    return SYS_OK;
}

/*
 * Server crebtes strebm.
 */
stbtic int
crebteStrebm(chbr *nbme, Strebm *strebm)
{
    jint error;
    chbr prefix[MAX_IPC_PREFIX];

    sprintf(prefix, "%s.mutex", nbme);
    error = crebteWithGenerbtedNbme(prefix, strebm->shbred->mutexNbme,
                                    crebteMutex, &strebm->mutex);
    if (error != SYS_OK) {
        return error;
    }

    sprintf(prefix, "%s.hbsDbtb", nbme);
    error = crebteWithGenerbtedNbme(prefix, strebm->shbred->hbsDbtbEventNbme,
                                    crebteEvent, &strebm->hbsDbtb);
    if (error != SYS_OK) {
        (void)closeStrebm(strebm, JNI_FALSE);
        return error;
    }

    sprintf(prefix, "%s.hbsSpbce", nbme);
    error = crebteWithGenerbtedNbme(prefix, strebm->shbred->hbsSpbceEventNbme,
                                    crebteEvent, &strebm->hbsSpbce);
    if (error != SYS_OK) {
        (void)closeStrebm(strebm, JNI_FALSE);
        return error;
    }

    strebm->shbred->rebdOffset = 0;
    strebm->shbred->writeOffset = 0;
    strebm->shbred->isFull = JNI_FALSE;
    strebm->stbte = STATE_OPEN;
    return SYS_OK;
}


/*
 * Initiblizbtion for the strebm opened by the other process
 */
stbtic int
openStrebm(Strebm *strebm)
{
    jint error;

    CHECK_ERROR(sysIPMutexOpen(strebm->shbred->mutexNbme, &strebm->mutex));

    error = sysEventOpen(strebm->shbred->hbsDbtbEventNbme,
                             &strebm->hbsDbtb);
    if (error != SYS_OK) {
        setLbstError(error);
        (void)closeStrebm(strebm, JNI_FALSE);
        return error;
    }

    error = sysEventOpen(strebm->shbred->hbsSpbceEventNbme,
                             &strebm->hbsSpbce);
    if (error != SYS_OK) {
        setLbstError(error);
        (void)closeStrebm(strebm, JNI_FALSE);
        return error;
    }

    strebm->stbte = STATE_OPEN;

    return SYS_OK;
}

/********************************************************************/

stbtic ShbredMemoryConnection *
bllocConnection(void)
{
    /*
     * TO DO: Trbck bll bllocbted connections for clebn shutdown?
     */
    ShbredMemoryConnection *conn = (*cbllbbck->blloc)(sizeof(ShbredMemoryConnection));
    if (conn != NULL) {
        memset(conn, 0, sizeof(ShbredMemoryConnection));
    }
    return conn;
}

stbtic void
freeConnection(ShbredMemoryConnection *connection)
{
    (*cbllbbck->free)(connection);
}

stbtic void
closeConnection(ShbredMemoryConnection *connection)
{
    /*
     * Signbl bll threbds bccessing this connection thbt we bre
     * shutting down.
     */
    if (connection->shutdown) {
        sysEventSignbl(connection->shutdown);
    }


    (void)closeStrebm(&connection->outgoing, JNI_TRUE);
    (void)closeStrebm(&connection->incoming, JNI_FALSE);

    if (connection->shbredMemory) {
        sysShbredMemClose(connection->shbredMemory, connection->shbred);
    }
    if (connection->otherProcess) {
        sysProcessClose(connection->otherProcess);
    }

    /*
     * Ideblly we should close the connection->shutdown event bnd
     * free the connection structure. However bs closing the
     * connection is bsynchronous it mebns thbt other threbds mby
     * still be bccessing the connection structure. On Win32 this
     * mebns we lebk 132 bytes bnd one event per connection. This
     * memory will be reclbim bt process exit.
     *
     * if (connection->shutdown)
     *     sysEventClose(connection->shutdown);
     * freeConnection(connection);
     */
}


/*
 * For client: connect to the shbred memory.  Open incoming bnd
 * outgoing strebms.
 */
stbtic jint
openConnection(ShbredMemoryTrbnsport *trbnsport, jlong otherPID,
               ShbredMemoryConnection **connectionPtr)
{
    jint error;

    ShbredMemoryConnection *connection = bllocConnection();
    if (connection == NULL) {
        return SYS_NOMEM;
    }

    sprintf(connection->nbme, "%s.%ld", trbnsport->nbme, sysProcessGetID());
    error = sysShbredMemOpen(connection->nbme, &connection->shbredMemory,
                             &connection->shbred);
    if (error != SYS_OK) {
        closeConnection(connection);
        return error;
    }

    /* This process is the client */
    connection->incoming.shbred = &connection->shbred->toClient;
    connection->outgoing.shbred = &connection->shbred->toServer;

    error = openStrebm(&connection->incoming);
    if (error != SYS_OK) {
        closeConnection(connection);
        return error;
    }

    error = openStrebm(&connection->outgoing);
    if (error != SYS_OK) {
        closeConnection(connection);
        return error;
    }

    error = sysProcessOpen(otherPID, &connection->otherProcess);
    if (error != SYS_OK) {
        setLbstError(error);
        closeConnection(connection);
        return error;
    }

    /*
     * Crebte bn event thbt signbls thbt the connection is shutting
     * down. The event is unnbmed bs it's process locbl, bnd is
     * mbnublly reset (so thbt signblling the event will signbl
     * bll threbds wbiting on it).
     */
    error = sysEventCrebte(NULL, &connection->shutdown, JNI_TRUE);
    if (error != SYS_OK) {
        setLbstError(error);
        closeConnection(connection);
        return error;
    }

    *connectionPtr = connection;
    return SYS_OK;
}

/*
 * For server: crebte the shbred memory.  Crebte incoming bnd
 * outgoing strebms.
 */
stbtic jint
crebteConnection(ShbredMemoryTrbnsport *trbnsport, jlong otherPID,
                 ShbredMemoryConnection **connectionPtr)
{
    jint error;
    chbr strebmPrefix[MAX_IPC_NAME];

    ShbredMemoryConnection *connection = bllocConnection();
    if (connection == NULL) {
        return SYS_NOMEM;
    }

    sprintf(connection->nbme, "%s.%ld", trbnsport->nbme, otherPID);
    error = sysShbredMemCrebte(connection->nbme, sizeof(ShbredMemory),
                               &connection->shbredMemory, &connection->shbred);
    if (error != SYS_OK) {
        closeConnection(connection);
        return error;
    }

    memset(connection->shbred, 0, sizeof(ShbredMemory));

    /* This process is the server */
    connection->incoming.shbred = &connection->shbred->toServer;
    connection->outgoing.shbred = &connection->shbred->toClient;

    strcpy(strebmPrefix, connection->nbme);
    strcbt(strebmPrefix, ".ctos");
    error = crebteStrebm(strebmPrefix, &connection->incoming);
    if (error != SYS_OK) {
        closeConnection(connection);
        return error;
    }

    strcpy(strebmPrefix, connection->nbme);
    strcbt(strebmPrefix, ".stoc");
    error = crebteStrebm(strebmPrefix, &connection->outgoing);
    if (error != SYS_OK) {
        closeConnection(connection);
        return error;
    }

    error = sysProcessOpen(otherPID, &connection->otherProcess);
    if (error != SYS_OK) {
        setLbstError(error);
        closeConnection(connection);
        return error;
    }

    /*
     * Crebte bn event thbt signbls thbt the connection is shutting
     * down. The event is unnbmed bs it's process locbl, bnd is
     * mbnublly reset (so thbt b signblling the event will signbl
     * bll threbds wbiting on it).
     */
    error = sysEventCrebte(NULL, &connection->shutdown, JNI_TRUE);
    if (error != SYS_OK) {
        setLbstError(error);
        closeConnection(connection);
        return error;
    }

    *connectionPtr = connection;
    return SYS_OK;
}

/********************************************************************/

stbtic ShbredMemoryTrbnsport *
bllocTrbnsport(void)
{
    /*
     * TO DO: Trbck bll bllocbted trbnsports for clebn shutdown?
     */
    return (*cbllbbck->blloc)(sizeof(ShbredMemoryTrbnsport));
}

stbtic void
freeTrbnsport(ShbredMemoryTrbnsport *trbnsport)
{
    (*cbllbbck->free)(trbnsport);
}

stbtic void
closeTrbnsport(ShbredMemoryTrbnsport *trbnsport)
{
    sysIPMutexClose(trbnsport->mutex);
    sysEventClose(trbnsport->bcceptEvent);
    sysEventClose(trbnsport->bttbchEvent);
    sysShbredMemClose(trbnsport->shbredMemory, trbnsport->shbred);
    freeTrbnsport(trbnsport);
}

stbtic int
openTrbnsport(const chbr *bddress, ShbredMemoryTrbnsport **trbnsportPtr)
{
    jint error;
    ShbredMemoryTrbnsport *trbnsport;

    trbnsport = bllocTrbnsport();
    if (trbnsport == NULL) {
        return SYS_NOMEM;
    }
    memset(trbnsport, 0, sizeof(*trbnsport));

    if (strlen(bddress) >= MAX_IPC_PREFIX) {
        chbr buf[128];
        sprintf(buf, "Error: bddress strings longer thbn %d chbrbcters bre invblid\n", MAX_IPC_PREFIX);
        setLbstErrorMsg(buf);
        closeTrbnsport(trbnsport);
        return SYS_ERR;
    }

    error = sysShbredMemOpen(bddress, &trbnsport->shbredMemory, &trbnsport->shbred);
    if (error != SYS_OK) {
        setLbstError(error);
        closeTrbnsport(trbnsport);
        return error;
    }
    strcpy(trbnsport->nbme, bddress);

    error = sysIPMutexOpen(trbnsport->shbred->mutexNbme, &trbnsport->mutex);
    if (error != SYS_OK) {
        setLbstError(error);
        closeTrbnsport(trbnsport);
        return error;
    }

    error = sysEventOpen(trbnsport->shbred->bcceptEventNbme,
                             &trbnsport->bcceptEvent);
    if (error != SYS_OK) {
        setLbstError(error);
        closeTrbnsport(trbnsport);
        return error;
    }

    error = sysEventOpen(trbnsport->shbred->bttbchEventNbme,
                             &trbnsport->bttbchEvent);
    if (error != SYS_OK) {
        setLbstError(error);
        closeTrbnsport(trbnsport);
        return error;
    }

    *trbnsportPtr = trbnsport;
    return SYS_OK;
}

stbtic jint
crebteTrbnsport(const chbr *bddress, ShbredMemoryTrbnsport **trbnsportPtr)
{
    ShbredMemoryTrbnsport *trbnsport;
    jint error;
    chbr prefix[MAX_IPC_PREFIX];



    trbnsport = bllocTrbnsport();
    if (trbnsport == NULL) {
        return SYS_NOMEM;
    }
    memset(trbnsport, 0, sizeof(*trbnsport));

    if ((bddress == NULL) || (bddress[0] == '\0')) {
        ShbredMemoryArg brg;
        brg.size = sizeof(ShbredListener);
        error = crebteWithGenerbtedNbme("jbvbdebug", trbnsport->nbme,
                                        crebteShbredMem, &brg);
        trbnsport->shbred = brg.stbrt;
        trbnsport->shbredMemory = brg.memory;
    } else {
        if (strlen(bddress) >= MAX_IPC_PREFIX) {
            chbr buf[128];
            sprintf(buf, "Error: bddress strings longer thbn %d chbrbcters bre invblid\n", MAX_IPC_PREFIX);
            setLbstErrorMsg(buf);
            closeTrbnsport(trbnsport);
            return SYS_ERR;
        }
        strcpy(trbnsport->nbme, bddress);
        error = sysShbredMemCrebte(bddress, sizeof(ShbredListener),
                                   &trbnsport->shbredMemory, &trbnsport->shbred);
    }
    if (error != SYS_OK) {
        setLbstError(error);
        closeTrbnsport(trbnsport);
        return error;
    }

    memset(trbnsport->shbred, 0, sizeof(ShbredListener));
    trbnsport->shbred->bcceptingPID = sysProcessGetID();

    sprintf(prefix, "%s.mutex", trbnsport->nbme);
    error = crebteWithGenerbtedNbme(prefix, trbnsport->shbred->mutexNbme,
                                    crebteMutex, &trbnsport->mutex);
    if (error != SYS_OK) {
        closeTrbnsport(trbnsport);
        return error;
    }

    sprintf(prefix, "%s.bccept", trbnsport->nbme);
    error = crebteWithGenerbtedNbme(prefix, trbnsport->shbred->bcceptEventNbme,
                                    crebteEvent, &trbnsport->bcceptEvent);
    if (error != SYS_OK) {
        closeTrbnsport(trbnsport);
        return error;
    }

    sprintf(prefix, "%s.bttbch", trbnsport->nbme);
    error = crebteWithGenerbtedNbme(prefix, trbnsport->shbred->bttbchEventNbme,
                                    crebteEvent, &trbnsport->bttbchEvent);
    if (error != SYS_OK) {
        closeTrbnsport(trbnsport);
        return error;
    }

    *trbnsportPtr = trbnsport;
    return SYS_OK;
}


jint
shmemBbse_listen(const chbr *bddress, ShbredMemoryTrbnsport **trbnsportPtr)
{
    int error;

    clebrLbstError();

    error = crebteTrbnsport(bddress, trbnsportPtr);
    if (error == SYS_OK) {
        (*trbnsportPtr)->shbred->isListening = JNI_TRUE;
    }
    return error;
}


jint
shmemBbse_bccept(ShbredMemoryTrbnsport *trbnsport,
                 long timeout,
                 ShbredMemoryConnection **connectionPtr)
{
    jint error;
    ShbredMemoryConnection *connection;

    clebrLbstError();

    CHECK_ERROR(sysEventWbit(NULL, trbnsport->bttbchEvent, timeout));

    error = crebteConnection(trbnsport, trbnsport->shbred->bttbchingPID,
                             &connection);
    if (error != SYS_OK) {
        /*
         * Reject the bttbcher
         */
        trbnsport->shbred->isAccepted = JNI_FALSE;
        sysEventSignbl(trbnsport->bcceptEvent);

        freeConnection(connection);
        return error;
    }

    trbnsport->shbred->isAccepted = JNI_TRUE;
    error = sysEventSignbl(trbnsport->bcceptEvent);
    if (error != SYS_OK) {
        /*
         * No rebl point trying to reject it.
         */
        closeConnection(connection);
        return error;
    }

    *connectionPtr = connection;
    return SYS_OK;
}

stbtic jint
doAttbch(ShbredMemoryTrbnsport *trbnsport, long timeout)
{
    trbnsport->shbred->bttbchingPID = sysProcessGetID();
    CHECK_ERROR(sysEventSignbl(trbnsport->bttbchEvent));
    CHECK_ERROR(sysEventWbit(NULL, trbnsport->bcceptEvent, timeout));
    return SYS_OK;
}

jint
shmemBbse_bttbch(const chbr *bddressString, long timeout, ShbredMemoryConnection **connectionPtr)
{
    int error;
    ShbredMemoryTrbnsport *trbnsport;
    jlong bcceptingPID;

    clebrLbstError();

    error = openTrbnsport(bddressString, &trbnsport);
    if (error != SYS_OK) {
        return error;
    }

    /* lock trbnsport - no bdditionbl event to wbit on bs no connection yet */
    error = sysIPMutexEnter(trbnsport->mutex, NULL);
    if (error != SYS_OK) {
        setLbstError(error);
        closeTrbnsport(trbnsport);
        return error;
    }

    if (trbnsport->shbred->isListening) {
        error = doAttbch(trbnsport, timeout);
        if (error == SYS_OK) {
            bcceptingPID = trbnsport->shbred->bcceptingPID;
        }
    } else {
        /* Not listening: error */
        error = SYS_ERR;
    }

    sysIPMutexExit(trbnsport->mutex);
    if (error != SYS_OK) {
        closeTrbnsport(trbnsport);
        return error;
    }

    error = openConnection(trbnsport, bcceptingPID, connectionPtr);

    closeTrbnsport(trbnsport);

    return error;
}




void
shmemBbse_closeConnection(ShbredMemoryConnection *connection)
{
    clebrLbstError();
    closeConnection(connection);
}

void
shmemBbse_closeTrbnsport(ShbredMemoryTrbnsport *trbnsport)
{
    clebrLbstError();
    closeTrbnsport(trbnsport);
}

jint
shmemBbse_sendByte(ShbredMemoryConnection *connection, jbyte dbtb)
{
    Strebm *strebm = &connection->outgoing;
    ShbredStrebm *shbred = strebm->shbred;
    int offset;

    clebrLbstError();

    CHECK_ERROR(enterMutex(strebm, connection->shutdown));
    CHECK_ERROR(wbitForSpbce(connection, strebm));
    SHMEM_ASSERT(!FULL(strebm));
    offset = shbred->writeOffset;
    shbred->buffer[offset] = dbtb;
    shbred->writeOffset = ADD_OFFSET(offset, 1);
    shbred->isFull = (shbred->rebdOffset == shbred->writeOffset);

    STREAM_INVARIANT(strebm);
    CHECK_ERROR(lebveMutex(strebm));

    CHECK_ERROR(signblDbtb(strebm));

    return SYS_OK;
}

jint
shmemBbse_receiveByte(ShbredMemoryConnection *connection, jbyte *dbtb)
{
    Strebm *strebm = &connection->incoming;
    ShbredStrebm *shbred = strebm->shbred;
    int offset;

    clebrLbstError();

    CHECK_ERROR(enterMutex(strebm, connection->shutdown));
    CHECK_ERROR(wbitForDbtb(connection, strebm));
    SHMEM_ASSERT(!EMPTY(strebm));
    offset = shbred->rebdOffset;
    *dbtb = shbred->buffer[offset];
    shbred->rebdOffset = ADD_OFFSET(offset, 1);
    shbred->isFull = JNI_FALSE;

    STREAM_INVARIANT(strebm);
    CHECK_ERROR(lebveMutex(strebm));

    CHECK_ERROR(signblSpbce(strebm));

    return SYS_OK;
}

stbtic jint
sendBytes(ShbredMemoryConnection *connection, const void *bytes, jint length)
{
    Strebm *strebm = &connection->outgoing;
    ShbredStrebm *shbred = strebm->shbred;
    jint frbgmentStbrt;
    jint frbgmentLength;
    jint index = 0;
    jint mbxLength;

    clebrLbstError();

    CHECK_ERROR(enterMutex(strebm, connection->shutdown));
    while (index < length) {
        CHECK_ERROR(wbitForSpbce(connection, strebm));
        SHMEM_ASSERT(!FULL(strebm));

        frbgmentStbrt = shbred->writeOffset;

        if (frbgmentStbrt < shbred->rebdOffset) {
            mbxLength = shbred->rebdOffset - frbgmentStbrt;
        } else {
            mbxLength = SHARED_BUFFER_SIZE - frbgmentStbrt;
        }
        frbgmentLength = MIN(mbxLength, length - index);
        memcpy(shbred->buffer + frbgmentStbrt, (jbyte *)bytes + index, frbgmentLength);
        shbred->writeOffset = ADD_OFFSET(frbgmentStbrt, frbgmentLength);
        index += frbgmentLength;

        shbred->isFull = (shbred->rebdOffset == shbred->writeOffset);

        STREAM_INVARIANT(strebm);
        CHECK_ERROR(signblDbtb(strebm));

    }
    CHECK_ERROR(lebveMutex(strebm));

    return SYS_OK;
}


/*
 * Send pbcket hebder followed by dbtb.
 */
jint
shmemBbse_sendPbcket(ShbredMemoryConnection *connection, const jdwpPbcket *pbcket)
{
    jint dbtb_length;

    clebrLbstError();

    CHECK_ERROR(sendBytes(connection, &pbcket->type.cmd.id, sizeof(jint)));
    CHECK_ERROR(sendBytes(connection, &pbcket->type.cmd.flbgs, sizeof(jbyte)));

    if (pbcket->type.cmd.flbgs & JDWPTRANSPORT_FLAGS_REPLY) {
        CHECK_ERROR(sendBytes(connection, &pbcket->type.reply.errorCode, sizeof(jshort)));
    } else {
        CHECK_ERROR(sendBytes(connection, &pbcket->type.cmd.cmdSet, sizeof(jbyte)));
        CHECK_ERROR(sendBytes(connection, &pbcket->type.cmd.cmd, sizeof(jbyte)));
    }

    dbtb_length = pbcket->type.cmd.len - 11;
    SHMEM_GUARANTEE(dbtb_length >= 0);
    CHECK_ERROR(sendBytes(connection, &dbtb_length, sizeof(jint)));

    if (dbtb_length > 0) {
        CHECK_ERROR(sendBytes(connection, pbcket->type.cmd.dbtb, dbtb_length));
    }

    return SYS_OK;
}

stbtic jint
receiveBytes(ShbredMemoryConnection *connection, void *bytes, jint length)
{
    Strebm *strebm = &connection->incoming;
    ShbredStrebm *shbred = strebm->shbred;
    jint frbgmentStbrt;
    jint frbgmentLength;
    jint index = 0;
    jint mbxLength;

    clebrLbstError();

    CHECK_ERROR(enterMutex(strebm, connection->shutdown));
    while (index < length) {
        CHECK_ERROR(wbitForDbtb(connection, strebm));
        SHMEM_ASSERT(!EMPTY(strebm));

        frbgmentStbrt = shbred->rebdOffset;
        if (frbgmentStbrt < shbred->writeOffset) {
            mbxLength = shbred->writeOffset - frbgmentStbrt;
        } else {
            mbxLength = SHARED_BUFFER_SIZE - frbgmentStbrt;
        }
        frbgmentLength = MIN(mbxLength, length - index);
        memcpy((jbyte *)bytes + index, shbred->buffer + frbgmentStbrt, frbgmentLength);
        shbred->rebdOffset = ADD_OFFSET(frbgmentStbrt, frbgmentLength);
        index += frbgmentLength;

        shbred->isFull = JNI_FALSE;

        STREAM_INVARIANT(strebm);
        CHECK_ERROR(signblSpbce(strebm));
    }
    CHECK_ERROR(lebveMutex(strebm));

    return SYS_OK;
}

/*
 * Rebd pbcket hebder bnd insert into pbcket structure.
 * Allocbte spbce for the dbtb bnd fill it in.
 */
jint
shmemBbse_receivePbcket(ShbredMemoryConnection *connection, jdwpPbcket *pbcket)
{
    jint dbtb_length;
    jint error;

    clebrLbstError();

    CHECK_ERROR(receiveBytes(connection, &pbcket->type.cmd.id, sizeof(jint)));
    CHECK_ERROR(receiveBytes(connection, &pbcket->type.cmd.flbgs, sizeof(jbyte)));

    if (pbcket->type.cmd.flbgs & JDWPTRANSPORT_FLAGS_REPLY) {
        CHECK_ERROR(receiveBytes(connection, &pbcket->type.reply.errorCode, sizeof(jshort)));
    } else {
        CHECK_ERROR(receiveBytes(connection, &pbcket->type.cmd.cmdSet, sizeof(jbyte)));
        CHECK_ERROR(receiveBytes(connection, &pbcket->type.cmd.cmd, sizeof(jbyte)));
    }

    CHECK_ERROR(receiveBytes(connection, &dbtb_length, sizeof(jint)));

    if (dbtb_length < 0) {
        return SYS_ERR;
    } else if (dbtb_length == 0) {
        pbcket->type.cmd.len = 11;
        pbcket->type.cmd.dbtb = NULL;
    } else {
        pbcket->type.cmd.len = dbtb_length + 11;
        pbcket->type.cmd.dbtb = (*cbllbbck->blloc)(dbtb_length);
        if (pbcket->type.cmd.dbtb == NULL) {
            return SYS_ERR;
        }

        error = receiveBytes(connection, pbcket->type.cmd.dbtb, dbtb_length);
        if (error != SYS_OK) {
            (*cbllbbck->free)(pbcket->type.cmd.dbtb);
            return error;
        }
    }

    return SYS_OK;
}

jint
shmemBbse_nbme(struct ShbredMemoryTrbnsport *trbnsport, chbr **nbme)
{
    *nbme = trbnsport->nbme;
    return SYS_OK;
}

jint
shmemBbse_getlbsterror(chbr *msg, jint size) {
    chbr *errstr = (chbr *)sysTlsGet(tlsIndex);
    if (errstr != NULL) {
        strcpy(msg, errstr);
        return SYS_OK;
    } else {
        return SYS_ERR;
    }
}


void
exitTrbnsportWithError(chbr *messbge, chbr *fileNbme,
                       chbr *dbte, int lineNumber)
{
    JNIEnv *env;
    jint error;
    chbr buffer[500];

    sprintf(buffer, "Shbred Memory Trbnsport \"%s\" (%s), line %d: %s\n",
            fileNbme, dbte, lineNumber, messbge);
    error = (*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_2);
    if (error != JNI_OK) {
        /*
         * We're forced into b direct cbll to exit()
         */
        fprintf(stderr, "%s", buffer);
        exit(-1);
    } else {
        (*env)->FbtblError(env, buffer);
    }
}
