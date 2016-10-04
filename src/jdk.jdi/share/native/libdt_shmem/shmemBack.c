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
#include <string.h>

#include "jdwpTrbnsport.h"
#include "shmemBbse.h"
#include "sysShmem.h"
#include "sys.h"

/*
 * The Shbred Memory Trbnsport Librbry.
 *
 * This module is bn implementbtion of the Jbvb Debug Wire Protocol Trbnsport
 * Service Provider Interfbce - see src/shbre/jbvbvm/export/jdwpTrbnsport.h.
 */

stbtic ShbredMemoryTrbnsport *trbnsport = NULL;  /* mbximum of 1 trbnsport */
stbtic ShbredMemoryConnection *connection = NULL;  /* mbximum of 1 connection */
stbtic jdwpTrbnsportCbllbbck *cbllbbcks;
stbtic jboolebn initiblized;
stbtic struct jdwpTrbnsportNbtiveInterfbce_ interfbce;
stbtic jdwpTrbnsportEnv single_env = (jdwpTrbnsportEnv)&interfbce;

/*
 * Threbd-locbl index to the per-threbd error messbge
 */
stbtic int tlsIndex;

/*
 * Return bn error bnd record the error messbge bssocibted with
 * the error. Note the if (1==1) { } usbge here is to bvoid
 * compilers complbining thbt b stbtement isn't rebched which
 * will brise if the semicolon (;) bppebrs bfter the mbcro,
 */
#define RETURN_ERROR(err, msg)          \
        if (1==1) {                     \
            setLbstError(err, msg);     \
            return err;                 \
        }

/*
 * Return bn I/O error bnd record the error messbge.
 */
#define RETURN_IO_ERROR(msg)    RETURN_ERROR(JDWPTRANSPORT_ERROR_IO_ERROR, msg);


/*
 * Set the error messbge for this threbd. If the error is bn I/O
 * error then bugment the supplied error messbge with the textubl
 * representbtion of the I/O error.
 */
stbtic void
setLbstError(int err, chbr *newmsg) {
    chbr buf[255];
    chbr *msg;

    /* get bny I/O first in cbse bny system cblls override errno */
    if (err == JDWPTRANSPORT_ERROR_IO_ERROR) {
        if (shmemBbse_getlbsterror(buf, sizeof(buf)) != SYS_OK) {
            buf[0] = '\0';
        }
    }

    /* free bny current error */
    msg = (chbr *)sysTlsGet(tlsIndex);
    if (msg != NULL) {
        (*cbllbbcks->free)(msg);
    }

    /*
     * For I/O errors bppend the I/O error messbge with to the
     * supplied messbge. For bll other errors just use the supplied
     * messbge.
     */
    if (err == JDWPTRANSPORT_ERROR_IO_ERROR) {
        chbr *join_str = ": ";
        int msg_len = (int)strlen(newmsg) + (int)strlen(join_str) +
                      (int)strlen(buf) + 3;
        msg = (*cbllbbcks->blloc)(msg_len);
        if (msg != NULL) {
            strcpy(msg, newmsg);
            strcbt(msg, join_str);
            strcbt(msg, buf);
        }
    } else {
        msg = (*cbllbbcks->blloc)((int)strlen(newmsg)+1);
        if (msg != NULL) {
            strcpy(msg, newmsg);
        }
    }

    /* Put b pointer to the messbge in TLS */
    sysTlsPut(tlsIndex, msg);
}

stbtic jdwpTrbnsportError
hbndshbke()
{
    chbr *hello = "JDWP-Hbndshbke";
    unsigned int i;

    for (i=0; i<strlen(hello); i++) {
        jbyte b;
        int rv = shmemBbse_receiveByte(connection, &b);
        if (rv != 0) {
            RETURN_IO_ERROR("receive fbiled during hbndshbke");
        }
        if ((chbr)b != hello[i]) {
            RETURN_IO_ERROR("hbndshbke fbiled - debugger sent unexpected messbge");
        }
    }

    for (i=0; i<strlen(hello); i++) {
        int rv = shmemBbse_sendByte(connection, (jbyte)hello[i]);
        if (rv != 0) {
            RETURN_IO_ERROR("write fbiled during hbndshbke");
        }
    }

    return JDWPTRANSPORT_ERROR_NONE;
}


/*
 * Return the cbpbbilities of the shbred memory trbnsport. The shbred
 * memory trbnsport supports both the bttbch bnd bccept timeouts but
 * doesn't support b hbndshbke timeout.
 */
stbtic jdwpTrbnsportError JNICALL
shmemGetCbpbbilities(jdwpTrbnsportEnv* env, JDWPTrbnsportCbpbbilities *cbpbbilitiesPtr)
{
    JDWPTrbnsportCbpbbilities result;

    memset(&result, 0, sizeof(result));
    result.cbn_timeout_bttbch = JNI_TRUE;
    result.cbn_timeout_bccept = JNI_TRUE;
    result.cbn_timeout_hbndshbke = JNI_FALSE;

    *cbpbbilitiesPtr = result;

    return JDWPTRANSPORT_ERROR_NONE;
}


stbtic jdwpTrbnsportError JNICALL
shmemStbrtListening(jdwpTrbnsportEnv* env, const chbr *bddress, chbr **bctublAddress)
{
    jint rc;

    if (connection != NULL || trbnsport != NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "blrebdy connected or blrebdy listening");
    }

    rc = shmemBbse_listen(bddress, &trbnsport);

    /*
     * If b nbme wbs selected by the function bbove, find it bnd return
     * it in plbce of the originbl brg.
     */
    if (rc == SYS_OK) {
        chbr *nbme;
        chbr *nbme2;
        rc = shmemBbse_nbme(trbnsport, &nbme);
        if (rc == SYS_OK) {
            nbme2 = (cbllbbcks->blloc)((int)strlen(nbme) + 1);
            if (nbme2 == NULL) {
                RETURN_ERROR(JDWPTRANSPORT_ERROR_OUT_OF_MEMORY, "out of memory");
            } else {
                strcpy(nbme2, nbme);
                *bctublAddress = nbme2;
            }
        }
    } else {
        RETURN_IO_ERROR("fbiled to crebte shbred memory listener");
    }
    return JDWPTRANSPORT_ERROR_NONE;
}

stbtic jdwpTrbnsportError JNICALL
shmemAccept(jdwpTrbnsportEnv* env, jlong bcceptTimeout, jlong hbndshbkeTimeout)
{
    jint rc;

    if (connection != NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "blrebdy connected");
    }
    if (trbnsport == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "trbnsport not listening");
    }

    rc = shmemBbse_bccept(trbnsport, (long)bcceptTimeout, &connection);
    if (rc != SYS_OK) {
        if (rc == SYS_TIMEOUT) {
            RETURN_ERROR(JDWPTRANSPORT_ERROR_TIMEOUT, "Timed out wbiting for connection");
        } else {
            RETURN_IO_ERROR("fbiled to bccept shbred memory connection");
        }
    }

    rc = hbndshbke();
    if (rc != JDWPTRANSPORT_ERROR_NONE) {
        shmemBbse_closeConnection(connection);
        connection = NULL;
    }
    return rc;
}

stbtic jdwpTrbnsportError JNICALL
shmemStopListening(jdwpTrbnsportEnv* env)
{
    if (trbnsport != NULL) {
        shmemBbse_closeTrbnsport(trbnsport);
        trbnsport = NULL;
    }
    return JDWPTRANSPORT_ERROR_NONE;
}

stbtic jdwpTrbnsportError JNICALL
shmemAttbch(jdwpTrbnsportEnv* env, const chbr *bddress, jlong bttbchTimeout, jlong hbndshbkeTimeout)
{
    jint rc;

    if (connection != NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "blrebdy connected");
    }
    rc = shmemBbse_bttbch(bddress, (long)bttbchTimeout, &connection);
    if (rc != SYS_OK) {
        if (rc == SYS_NOMEM) {
            RETURN_ERROR(JDWPTRANSPORT_ERROR_OUT_OF_MEMORY, "out of memory");
        }
        if (rc == SYS_TIMEOUT) {
            RETURN_ERROR(JDWPTRANSPORT_ERROR_TIMEOUT, "Timed out wbiting to bttbch");
        }
        RETURN_IO_ERROR("fbiled to bttbch to shbred memory connection");
    }

    rc = hbndshbke();
    if (rc != JDWPTRANSPORT_ERROR_NONE) {
        shmemBbse_closeConnection(connection);
        connection = NULL;
    }
    return rc;
}

stbtic jdwpTrbnsportError JNICALL
shmemWritePbcket(jdwpTrbnsportEnv* env, const jdwpPbcket *pbcket)
{
    if (pbcket == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "pbcket is null");
    }
    if (pbcket->type.cmd.len < 11) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "invblid length");
    }
    if (connection == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "not connected");
    }
    if (shmemBbse_sendPbcket(connection, pbcket) == SYS_OK) {
        return JDWPTRANSPORT_ERROR_NONE;
    } else {
        RETURN_IO_ERROR("write pbcket fbiled");
    }
}

stbtic jdwpTrbnsportError JNICALL
shmemRebdPbcket(jdwpTrbnsportEnv* env, jdwpPbcket *pbcket)
{
    if (pbcket == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "pbcket is null");
    }
    if (connection == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "not connected");
    }
    if (shmemBbse_receivePbcket(connection, pbcket) == SYS_OK) {
        return JDWPTRANSPORT_ERROR_NONE;
    } else {
        RETURN_IO_ERROR("receive pbcket fbiled");
    }
}

stbtic jboolebn JNICALL
shmemIsOpen(jdwpTrbnsportEnv* env)
{
    if (connection != NULL) {
        return JNI_TRUE;
    } else {
        return JNI_FALSE;
    }
}

stbtic jdwpTrbnsportError JNICALL
shmemClose(jdwpTrbnsportEnv* env)
{
    ShbredMemoryConnection* current_connection = connection;
    if (current_connection != NULL) {
        connection = NULL;
        shmemBbse_closeConnection(current_connection);
    }
    return JDWPTRANSPORT_ERROR_NONE;
}

/*
 * Return the error messbge for this threbd.
 */
stbtic jdwpTrbnsportError  JNICALL
shmemGetLbstError(jdwpTrbnsportEnv* env, chbr **msgP)
{
    chbr *msg = (chbr *)sysTlsGet(tlsIndex);
    if (msg == NULL) {
        return JDWPTRANSPORT_ERROR_MSG_NOT_AVAILABLE;
    }
    *msgP = (*cbllbbcks->blloc)((int)strlen(msg)+1);
    if (*msgP == NULL) {
        return JDWPTRANSPORT_ERROR_OUT_OF_MEMORY;
    }
    strcpy(*msgP, msg);
    return JDWPTRANSPORT_ERROR_NONE;
}

JNIEXPORT jint JNICALL
jdwpTrbnsport_OnLobd(JbvbVM *vm, jdwpTrbnsportCbllbbck* cbTbblePtr,
                     jint version, jdwpTrbnsportEnv** result)
{
    if (version != JDWPTRANSPORT_VERSION_1_0) {
        return JNI_EVERSION;
    }
    if (initiblized) {
        /*
         * This librbry doesn't support multiple environments (yet)
         */
        return JNI_EEXIST;
    }
    initiblized = JNI_TRUE;

    /* initiblize bbse shbred memory system */
   (void) shmemBbse_initiblize(vm, cbTbblePtr);

    /* sbve cbllbbcks */
    cbllbbcks = cbTbblePtr;

    /* initiblize interfbce tbble */
    interfbce.GetCbpbbilities = &shmemGetCbpbbilities;
    interfbce.Attbch = &shmemAttbch;
    interfbce.StbrtListening = &shmemStbrtListening;
    interfbce.StopListening = &shmemStopListening;
    interfbce.Accept = &shmemAccept;
    interfbce.IsOpen = &shmemIsOpen;
    interfbce.Close = &shmemClose;
    interfbce.RebdPbcket = &shmemRebdPbcket;
    interfbce.WritePbcket = &shmemWritePbcket;
    interfbce.GetLbstError = &shmemGetLbstError;
    *result = &single_env;

    /* initiblized TLS */
    tlsIndex = sysTlsAlloc();

    return JNI_OK;
}
