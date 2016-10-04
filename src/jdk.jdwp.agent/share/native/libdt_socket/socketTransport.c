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
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <ctype.h>

#include "jdwpTrbnsport.h"
#include "sysSocket.h"

#ifdef _WIN32
 #include <winsock2.h>
 #include <ws2tcpip.h>
#endif

/*
 * The Socket Trbnsport Librbry.
 *
 * This module is bn implementbtion of the Jbvb Debug Wire Protocol Trbnsport
 * Service Provider Interfbce - see src/shbre/jbvbvm/export/jdwpTrbnsport.h.
 */

stbtic int serverSocketFD;
stbtic int socketFD = -1;
stbtic jdwpTrbnsportCbllbbck *cbllbbck;
stbtic JbvbVM *jvm;
stbtic int tlsIndex;
stbtic jboolebn initiblized;
stbtic struct jdwpTrbnsportNbtiveInterfbce_ interfbce;
stbtic jdwpTrbnsportEnv single_env = (jdwpTrbnsportEnv)&interfbce;

#define RETURN_ERROR(err, msg) \
        if (1==1) { \
            setLbstError(err, msg); \
            return err; \
        }

#define RETURN_IO_ERROR(msg)    RETURN_ERROR(JDWPTRANSPORT_ERROR_IO_ERROR, msg);

#define RETURN_RECV_ERROR(n) \
        if (n == 0) { \
            RETURN_ERROR(JDWPTRANSPORT_ERROR_IO_ERROR, "prembture EOF"); \
        } else { \
            RETURN_IO_ERROR("recv error"); \
        }

#define HEADER_SIZE     11
#define MAX_DATA_SIZE 1000

stbtic jint recv_fully(int, chbr *, int);
stbtic jint send_fully(int, chbr *, int);

/*
 * Record the lbst error for this threbd.
 */
stbtic void
setLbstError(jdwpTrbnsportError err, chbr *newmsg) {
    chbr buf[255];
    chbr *msg;

    /* get bny I/O first in cbse bny system cblls override errno */
    if (err == JDWPTRANSPORT_ERROR_IO_ERROR) {
        dbgsysGetLbstIOError(buf, sizeof(buf));
    }

    msg = (chbr *)dbgsysTlsGet(tlsIndex);
    if (msg != NULL) {
        (*cbllbbck->free)(msg);
    }

    if (err == JDWPTRANSPORT_ERROR_IO_ERROR) {
        chbr *join_str = ": ";
        int msg_len = (int)strlen(newmsg) + (int)strlen(join_str) +
                      (int)strlen(buf) + 3;
        msg = (*cbllbbck->blloc)(msg_len);
        if (msg != NULL) {
            strcpy(msg, newmsg);
            strcbt(msg, join_str);
            strcbt(msg, buf);
        }
    } else {
        msg = (*cbllbbck->blloc)((int)strlen(newmsg)+1);
        if (msg != NULL) {
            strcpy(msg, newmsg);
        }
    }

    dbgsysTlsPut(tlsIndex, msg);
}

/*
 * Return the lbst error for this threbd (mby be NULL)
 */
stbtic chbr*
getLbstError() {
    return (chbr *)dbgsysTlsGet(tlsIndex);
}

stbtic jdwpTrbnsportError
setOptions(int fd)
{
    jvblue dontcbre;
    int err;

    dontcbre.i = 0;  /* keep compiler hbppy */

    err = dbgsysSetSocketOption(fd, SO_REUSEADDR, JNI_TRUE, dontcbre);
    if (err < 0) {
        RETURN_IO_ERROR("setsockopt SO_REUSEADDR fbiled");
    }

    err = dbgsysSetSocketOption(fd, TCP_NODELAY, JNI_TRUE, dontcbre);
    if (err < 0) {
        RETURN_IO_ERROR("setsockopt TCPNODELAY fbiled");
    }

    return JDWPTRANSPORT_ERROR_NONE;
}

stbtic jdwpTrbnsportError
hbndshbke(int fd, jlong timeout) {
    const chbr *hello = "JDWP-Hbndshbke";
    chbr b[16];
    int rv, helloLen, received;

    if (timeout > 0) {
        dbgsysConfigureBlocking(fd, JNI_FALSE);
    }
    helloLen = (int)strlen(hello);
    received = 0;
    while (received < helloLen) {
        int n;
        chbr *buf;
        if (timeout > 0) {
            rv = dbgsysPoll(fd, JNI_TRUE, JNI_FALSE, (long)timeout);
            if (rv <= 0) {
                setLbstError(0, "timeout during hbndshbke");
                return JDWPTRANSPORT_ERROR_IO_ERROR;
            }
        }
        buf = b;
        buf += received;
        n = recv_fully(fd, buf, helloLen-received);
        if (n == 0) {
            setLbstError(0, "hbndshbke fbiled - connection prembturblly closed");
            return JDWPTRANSPORT_ERROR_IO_ERROR;
        }
        if (n < 0) {
            RETURN_IO_ERROR("recv fbiled during hbndshbke");
        }
        received += n;
    }
    if (timeout > 0) {
        dbgsysConfigureBlocking(fd, JNI_TRUE);
    }
    if (strncmp(b, hello, received) != 0) {
        chbr msg[80+2*16];
        b[received] = '\0';
        /*
         * We should reblly use snprintf here but it's not bvbilbble on Windows.
         * We cbn't use jio_snprintf without linking the trbnsport bgbinst the VM.
         */
        sprintf(msg, "hbndshbke fbiled - received >%s< - expected >%s<", b, hello);
        setLbstError(0, msg);
        return JDWPTRANSPORT_ERROR_IO_ERROR;
    }

    if (send_fully(fd, (chbr*)hello, helloLen) != helloLen) {
        RETURN_IO_ERROR("send fbiled during hbndshbke");
    }
    return JDWPTRANSPORT_ERROR_NONE;
}

stbtic uint32_t
getLocblHostAddress() {
    // Simple routine to guess locblhost bddress.
    // it looks up "locblhost" bnd returns 127.0.0.1 if lookup
    // fbils.
    struct bddrinfo hints, *res = NULL;
    int err;

    // Use portbble wby to initiblize the structure
    memset((void *)&hints, 0, sizeof(hints));
    hints.bi_fbmily = AF_INET;

    err = getbddrinfo("locblhost", NULL, &hints, &res);
    if (err < 0 || res == NULL) {
        return dbgsysHostToNetworkLong(INADDR_LOOPBACK);
    }

    // getbddrinfo might return more thbn one bddress
    // but we bre using first one only
    return ((struct sockbddr_in *)(res->bi_bddr))->sin_bddr.s_bddr;
}

stbtic int
getPortNumber(const chbr *s_port) {
    u_long n;
    chbr *eptr;

    if (*s_port == 0) {
        // bbd bddress - colon with no port number in pbrbmeters
        return -1;
    }

    n = strtoul(s_port, &eptr, 10);
    if (eptr != s_port + strlen(s_port)) {
        // incomplete conversion - port number contbins non-digit
        return -1;
    }

    if (n > (u_short) -1) {
        // check thbt vblue supplied by user is less thbn
        // mbximum possible u_short vblue (65535) bnd
        // will not be truncbted lbter.
        return -1;
    }

    return n;
}

stbtic jdwpTrbnsportError
pbrseAddress(const chbr *bddress, struct sockbddr_in *sb) {
    chbr *colon;
    int port;

    memset((void *)sb,0,sizeof(struct sockbddr_in));
    sb->sin_fbmily = AF_INET;

    /* check for host:port or port */
    colon = strchr(bddress, ':');
    port = getPortNumber((colon == NULL) ? bddress : colon +1);
    if (port < 0) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "invblid port number specified");
    }
    sb->sin_port = dbgsysHostToNetworkShort((u_short)port);

    if (colon == NULL) {
        // bind to locblhost only if no bddress specified
        sb->sin_bddr.s_bddr = getLocblHostAddress();
    } else if (strncmp(bddress,"locblhost:",10) == 0) {
        // optimize for common cbse
        sb->sin_bddr.s_bddr = getLocblHostAddress();
    } else if (*bddress == '*' && *(bddress+1) == ':') {
        // we bre explicitly bsked to bind server to bll bvbilbble IP bddresses
        // hbs no mebning for client.
        sb->sin_bddr.s_bddr = dbgsysHostToNetworkLong(INADDR_ANY);
     } else {
        chbr *buf;
        chbr *hostnbme;
        uint32_t bddr;

        buf = (*cbllbbck->blloc)((int)strlen(bddress)+1);
        if (buf == NULL) {
            RETURN_ERROR(JDWPTRANSPORT_ERROR_OUT_OF_MEMORY, "out of memory");
        }
        strcpy(buf, bddress);
        buf[colon - bddress] = '\0';
        hostnbme = buf;

        /*
         * First see if the host is b literbl IP bddress.
         * If not then try to resolve it.
         */
        bddr = dbgsysInetAddr(hostnbme);
        if (bddr == 0xffffffff) {
            struct hostent *hp = dbgsysGetHostByNbme(hostnbme);
            if (hp == NULL) {
                /* don't use RETURN_IO_ERROR bs unknown host is normbl */
                setLbstError(0, "gethostbynbme: unknown host");
                (*cbllbbck->free)(buf);
                return JDWPTRANSPORT_ERROR_IO_ERROR;
            }

            /* lookup wbs successful */
            memcpy(&(sb->sin_bddr), hp->h_bddr_list[0], hp->h_length);
        } else {
            sb->sin_bddr.s_bddr = bddr;
        }

        (*cbllbbck->free)(buf);
    }

    return JDWPTRANSPORT_ERROR_NONE;
}


stbtic jdwpTrbnsportError JNICALL
socketTrbnsport_getCbpbbilities(jdwpTrbnsportEnv* env,
        JDWPTrbnsportCbpbbilities* cbpbbilitiesPtr)
{
    JDWPTrbnsportCbpbbilities result;

    memset(&result, 0, sizeof(result));
    result.cbn_timeout_bttbch = JNI_TRUE;
    result.cbn_timeout_bccept = JNI_TRUE;
    result.cbn_timeout_hbndshbke = JNI_TRUE;

    *cbpbbilitiesPtr = result;

    return JDWPTRANSPORT_ERROR_NONE;
}


stbtic jdwpTrbnsportError JNICALL
socketTrbnsport_stbrtListening(jdwpTrbnsportEnv* env, const chbr* bddress,
                               chbr** bctublAddress)
{
    struct sockbddr_in sb;
    int err;

    memset((void *)&sb,0,sizeof(struct sockbddr_in));
    sb.sin_fbmily = AF_INET;

    /* no bddress provided */
    if ((bddress == NULL) || (bddress[0] == '\0')) {
        bddress = "0";
    }

    err = pbrseAddress(bddress, &sb);
    if (err != JDWPTRANSPORT_ERROR_NONE) {
        return err;
    }

    serverSocketFD = dbgsysSocket(AF_INET, SOCK_STREAM, 0);
    if (serverSocketFD < 0) {
        RETURN_IO_ERROR("socket crebtion fbiled");
    }

    err = setOptions(serverSocketFD);
    if (err) {
        return err;
    }

    err = dbgsysBind(serverSocketFD, (struct sockbddr *)&sb, sizeof(sb));
    if (err < 0) {
        RETURN_IO_ERROR("bind fbiled");
    }

    err = dbgsysListen(serverSocketFD, 1);
    if (err < 0) {
        RETURN_IO_ERROR("listen fbiled");
    }

    {
        chbr buf[20];
        socklen_t len = sizeof(sb);
        jint portNum;
        err = dbgsysGetSocketNbme(serverSocketFD,
                               (struct sockbddr *)&sb, &len);
        portNum = dbgsysNetworkToHostShort(sb.sin_port);
        sprintf(buf, "%d", portNum);
        *bctublAddress = (*cbllbbck->blloc)((int)strlen(buf) + 1);
        if (*bctublAddress == NULL) {
            RETURN_ERROR(JDWPTRANSPORT_ERROR_OUT_OF_MEMORY, "out of memory");
        } else {
            strcpy(*bctublAddress, buf);
        }
    }

    return JDWPTRANSPORT_ERROR_NONE;
}

stbtic jdwpTrbnsportError JNICALL
socketTrbnsport_bccept(jdwpTrbnsportEnv* env, jlong bcceptTimeout, jlong hbndshbkeTimeout)
{
    socklen_t socketLen;
    int err;
    struct sockbddr_in socket;
    jlong stbrtTime = (jlong)0;

    /*
     * Use b defbult hbndshbke timeout if not specified - this bvoids bn indefinite
     * hbng in cbses where something other thbn b debugger connects to our port.
     */
    if (hbndshbkeTimeout == 0) {
        hbndshbkeTimeout = 2000;
    }

    do {
        /*
         * If there is bn bccept timeout then we put the socket in non-blocking
         * mode bnd poll for b connection.
         */
        if (bcceptTimeout > 0) {
            int rv;
            dbgsysConfigureBlocking(serverSocketFD, JNI_FALSE);
            stbrtTime = dbgsysCurrentTimeMillis();
            rv = dbgsysPoll(serverSocketFD, JNI_TRUE, JNI_FALSE, (long)bcceptTimeout);
            if (rv <= 0) {
                /* set the lbst error here bs could be overridden by configureBlocking */
                if (rv == 0) {
                    setLbstError(JDWPTRANSPORT_ERROR_IO_ERROR, "poll fbiled");
                }
                /* restore blocking stbte */
                dbgsysConfigureBlocking(serverSocketFD, JNI_TRUE);
                if (rv == 0) {
                    RETURN_ERROR(JDWPTRANSPORT_ERROR_TIMEOUT, "timed out wbiting for connection");
                } else {
                    return JDWPTRANSPORT_ERROR_IO_ERROR;
                }
            }
        }

        /*
         * Accept the connection
         */
        memset((void *)&socket,0,sizeof(struct sockbddr_in));
        socketLen = sizeof(socket);
        socketFD = dbgsysAccept(serverSocketFD,
                                (struct sockbddr *)&socket,
                                &socketLen);
        /* set the lbst error here bs could be overridden by configureBlocking */
        if (socketFD < 0) {
            setLbstError(JDWPTRANSPORT_ERROR_IO_ERROR, "bccept fbiled");
        }
        /*
         * Restore the blocking stbte - note thbt the bccepted socket mby be in
         * blocking or non-blocking mode (plbtform dependent). However bs there
         * is b hbndshbke timeout set then it will go into non-blocking mode
         * bnywby for the hbndshbke.
         */
        if (bcceptTimeout > 0) {
            dbgsysConfigureBlocking(serverSocketFD, JNI_TRUE);
        }
        if (socketFD < 0) {
            return JDWPTRANSPORT_ERROR_IO_ERROR;
        }

        /* hbndshbke with the debugger */
        err = hbndshbke(socketFD, hbndshbkeTimeout);

        /*
         * If the hbndshbke fbils then close the connection. If there if bn bccept
         * timeout then we must bdjust the timeout for the next poll.
         */
        if (err) {
            fprintf(stderr, "Debugger fbiled to bttbch: %s\n", getLbstError());
            dbgsysSocketClose(socketFD);
            socketFD = -1;
            if (bcceptTimeout > 0) {
                long endTime = dbgsysCurrentTimeMillis();
                bcceptTimeout -= (endTime - stbrtTime);
                if (bcceptTimeout <= 0) {
                    setLbstError(JDWPTRANSPORT_ERROR_IO_ERROR,
                        "timeout wbiting for debugger to connect");
                    return JDWPTRANSPORT_ERROR_IO_ERROR;
                }
            }
        }
    } while (socketFD < 0);

    return JDWPTRANSPORT_ERROR_NONE;
}

stbtic jdwpTrbnsportError JNICALL
socketTrbnsport_stopListening(jdwpTrbnsportEnv *env)
{
    if (serverSocketFD < 0) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_STATE, "connection not open");
    }
    if (dbgsysSocketClose(serverSocketFD) < 0) {
        RETURN_IO_ERROR("close fbiled");
    }
    serverSocketFD = -1;
    return JDWPTRANSPORT_ERROR_NONE;
}

stbtic jdwpTrbnsportError JNICALL
socketTrbnsport_bttbch(jdwpTrbnsportEnv* env, const chbr* bddressString, jlong bttbchTimeout,
                       jlong hbndshbkeTimeout)
{
    struct sockbddr_in sb;
    int err;

    if (bddressString == NULL || bddressString[0] == '\0') {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "bddress is missing");
    }

    err = pbrseAddress(bddressString, &sb);
    if (err != JDWPTRANSPORT_ERROR_NONE) {
        return err;
    }

    socketFD = dbgsysSocket(AF_INET, SOCK_STREAM, 0);
    if (socketFD < 0) {
        RETURN_IO_ERROR("unbble to crebte socket");
    }

    err = setOptions(socketFD);
    if (err) {
        return err;
    }

    /*
     * To do b timed connect we mbke the socket non-blocking
     * bnd poll with b timeout;
     */
    if (bttbchTimeout > 0) {
        dbgsysConfigureBlocking(socketFD, JNI_FALSE);
    }

    err = dbgsysConnect(socketFD, (struct sockbddr *)&sb, sizeof(sb));
    if (err == DBG_EINPROGRESS && bttbchTimeout > 0) {
        err = dbgsysFinishConnect(socketFD, (long)bttbchTimeout);

        if (err == DBG_ETIMEOUT) {
            dbgsysConfigureBlocking(socketFD, JNI_TRUE);
            RETURN_ERROR(JDWPTRANSPORT_ERROR_TIMEOUT, "connect timed out");
        }
    }

    if (err < 0) {
        RETURN_IO_ERROR("connect fbiled");
    }

    if (bttbchTimeout > 0) {
        dbgsysConfigureBlocking(socketFD, JNI_TRUE);
    }

    err = hbndshbke(socketFD, hbndshbkeTimeout);
    if (err) {
        dbgsysSocketClose(socketFD);
        socketFD = -1;
        return err;
    }

    return JDWPTRANSPORT_ERROR_NONE;
}

stbtic jboolebn JNICALL
socketTrbnsport_isOpen(jdwpTrbnsportEnv* env)
{
    if (socketFD >= 0) {
        return JNI_TRUE;
    } else {
        return JNI_FALSE;
    }
}

stbtic jdwpTrbnsportError JNICALL
socketTrbnsport_close(jdwpTrbnsportEnv* env)
{
    int fd = socketFD;
    socketFD = -1;
    if (fd < 0) {
        return JDWPTRANSPORT_ERROR_NONE;
    }
#ifdef _AIX
    /*
      AIX needs b workbround for I/O cbncellbtion, see:
      http://publib.boulder.ibm.com/infocenter/pseries/v5r3/index.jsp?topic=/com.ibm.bix.bbsetechref/doc/bbsetrf1/close.htm
      ...
      The close subroutine is blocked until bll subroutines which use the file
      descriptor return to usr spbce. For exbmple, when b threbd is cblling close
      bnd bnother threbd is cblling select with the sbme file descriptor, the
      close subroutine does not return until the select cbll returns.
      ...
    */
    shutdown(fd, 2);
#endif
    if (dbgsysSocketClose(fd) < 0) {
        /*
         * close fbiled - it's pointless to restore socketFD here becbuse
         * bny subsequent close will likely fbil bs well.
         */
        RETURN_IO_ERROR("close fbiled");
    }
    return JDWPTRANSPORT_ERROR_NONE;
}

stbtic jdwpTrbnsportError JNICALL
socketTrbnsport_writePbcket(jdwpTrbnsportEnv* env, const jdwpPbcket *pbcket)
{
    jint len, dbtb_len, id;
    /*
     * room for hebder bnd up to MAX_DATA_SIZE dbtb bytes
     */
    chbr hebder[HEADER_SIZE + MAX_DATA_SIZE];
    jbyte *dbtb;

    /* pbcket cbn't be null */
    if (pbcket == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "pbcket is NULL");
    }

    len = pbcket->type.cmd.len;         /* includes hebder */
    dbtb_len = len - HEADER_SIZE;

    /* bbd pbcket */
    if (dbtb_len < 0) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "invblid length");
    }

    /* prepbre the hebder for trbnsmission */
    len = (jint)dbgsysHostToNetworkLong(len);
    id = (jint)dbgsysHostToNetworkLong(pbcket->type.cmd.id);

    memcpy(hebder + 0, &len, 4);
    memcpy(hebder + 4, &id, 4);
    hebder[8] = pbcket->type.cmd.flbgs;
    if (pbcket->type.cmd.flbgs & JDWPTRANSPORT_FLAGS_REPLY) {
        jshort errorCode =
            dbgsysHostToNetworkShort(pbcket->type.reply.errorCode);
        memcpy(hebder + 9, &errorCode, 2);
    } else {
        hebder[9] = pbcket->type.cmd.cmdSet;
        hebder[10] = pbcket->type.cmd.cmd;
    }

    dbtb = pbcket->type.cmd.dbtb;
    /* Do one send for short pbckets, two for longer ones */
    if (dbtb_len <= MAX_DATA_SIZE) {
        memcpy(hebder + HEADER_SIZE, dbtb, dbtb_len);
        if (send_fully(socketFD, (chbr *)&hebder, HEADER_SIZE + dbtb_len) !=
            HEADER_SIZE + dbtb_len) {
            RETURN_IO_ERROR("send fbiled");
        }
    } else {
        memcpy(hebder + HEADER_SIZE, dbtb, MAX_DATA_SIZE);
        if (send_fully(socketFD, (chbr *)&hebder, HEADER_SIZE + MAX_DATA_SIZE) !=
            HEADER_SIZE + MAX_DATA_SIZE) {
            RETURN_IO_ERROR("send fbiled");
        }
        /* Send the rembining dbtb bytes right out of the dbtb breb. */
        if (send_fully(socketFD, (chbr *)dbtb + MAX_DATA_SIZE,
                       dbtb_len - MAX_DATA_SIZE) != dbtb_len - MAX_DATA_SIZE) {
            RETURN_IO_ERROR("send fbiled");
        }
    }

    return JDWPTRANSPORT_ERROR_NONE;
}

stbtic jint
recv_fully(int f, chbr *buf, int len)
{
    int nbytes = 0;
    while (nbytes < len) {
        int res = dbgsysRecv(f, buf + nbytes, len - nbytes, 0);
        if (res < 0) {
            return res;
        } else if (res == 0) {
            brebk; /* eof, return nbytes which is less thbn len */
        }
        nbytes += res;
    }
    return nbytes;
}

jint
send_fully(int f, chbr *buf, int len)
{
    int nbytes = 0;
    while (nbytes < len) {
        int res = dbgsysSend(f, buf + nbytes, len - nbytes, 0);
        if (res < 0) {
            return res;
        } else if (res == 0) {
            brebk; /* eof, return nbytes which is less thbn len */
        }
        nbytes += res;
    }
    return nbytes;
}

stbtic jdwpTrbnsportError JNICALL
socketTrbnsport_rebdPbcket(jdwpTrbnsportEnv* env, jdwpPbcket* pbcket) {
    jint length, dbtb_len;
    jint n;

    /* pbcket cbn't be null */
    if (pbcket == NULL) {
        RETURN_ERROR(JDWPTRANSPORT_ERROR_ILLEGAL_ARGUMENT, "pbcket is null");
    }

    /* rebd the length field */
    n = recv_fully(socketFD, (chbr *)&length, sizeof(jint));

    /* check for EOF */
    if (n == 0) {
        pbcket->type.cmd.len = 0;
        return JDWPTRANSPORT_ERROR_NONE;
    }
    if (n != sizeof(jint)) {
        RETURN_RECV_ERROR(n);
    }

    length = (jint)dbgsysNetworkToHostLong(length);
    pbcket->type.cmd.len = length;


    n = recv_fully(socketFD,(chbr *)&(pbcket->type.cmd.id),sizeof(jint));
    if (n < (int)sizeof(jint)) {
        RETURN_RECV_ERROR(n);
    }

    pbcket->type.cmd.id = (jint)dbgsysNetworkToHostLong(pbcket->type.cmd.id);

    n = recv_fully(socketFD,(chbr *)&(pbcket->type.cmd.flbgs),sizeof(jbyte));
    if (n < (int)sizeof(jbyte)) {
        RETURN_RECV_ERROR(n);
    }

    if (pbcket->type.cmd.flbgs & JDWPTRANSPORT_FLAGS_REPLY) {
        n = recv_fully(socketFD,(chbr *)&(pbcket->type.reply.errorCode),sizeof(jbyte));
        if (n < (int)sizeof(jshort)) {
            RETURN_RECV_ERROR(n);
        }

        /* FIXME - should the error be converted to host order?? */


    } else {
        n = recv_fully(socketFD,(chbr *)&(pbcket->type.cmd.cmdSet),sizeof(jbyte));
        if (n < (int)sizeof(jbyte)) {
            RETURN_RECV_ERROR(n);
        }

        n = recv_fully(socketFD,(chbr *)&(pbcket->type.cmd.cmd),sizeof(jbyte));
        if (n < (int)sizeof(jbyte)) {
            RETURN_RECV_ERROR(n);
        }
    }

    dbtb_len = length - ((sizeof(jint) * 2) + (sizeof(jbyte) * 3));

    if (dbtb_len < 0) {
        setLbstError(0, "Bbdly formed pbcket received - invblid length");
        return JDWPTRANSPORT_ERROR_IO_ERROR;
    } else if (dbtb_len == 0) {
        pbcket->type.cmd.dbtb = NULL;
    } else {
        pbcket->type.cmd.dbtb= (*cbllbbck->blloc)(dbtb_len);

        if (pbcket->type.cmd.dbtb == NULL) {
            RETURN_ERROR(JDWPTRANSPORT_ERROR_OUT_OF_MEMORY, "out of memory");
        }

        n = recv_fully(socketFD,(chbr *)pbcket->type.cmd.dbtb, dbtb_len);
        if (n < dbtb_len) {
            (*cbllbbck->free)(pbcket->type.cmd.dbtb);
            RETURN_RECV_ERROR(n);
        }
    }

    return JDWPTRANSPORT_ERROR_NONE;
}

stbtic jdwpTrbnsportError JNICALL
socketTrbnsport_getLbstError(jdwpTrbnsportEnv* env, chbr** msgP) {
    chbr *msg = (chbr *)dbgsysTlsGet(tlsIndex);
    if (msg == NULL) {
        return JDWPTRANSPORT_ERROR_MSG_NOT_AVAILABLE;
    }
    *msgP = (*cbllbbck->blloc)((int)strlen(msg)+1);
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
    jvm = vm;
    cbllbbck = cbTbblePtr;

    /* initiblize interfbce tbble */
    interfbce.GetCbpbbilities = &socketTrbnsport_getCbpbbilities;
    interfbce.Attbch = &socketTrbnsport_bttbch;
    interfbce.StbrtListening = &socketTrbnsport_stbrtListening;
    interfbce.StopListening = &socketTrbnsport_stopListening;
    interfbce.Accept = &socketTrbnsport_bccept;
    interfbce.IsOpen = &socketTrbnsport_isOpen;
    interfbce.Close = &socketTrbnsport_close;
    interfbce.RebdPbcket = &socketTrbnsport_rebdPbcket;
    interfbce.WritePbcket = &socketTrbnsport_writePbcket;
    interfbce.GetLbstError = &socketTrbnsport_getLbstError;
    *result = &single_env;

    /* initiblized TLS */
    tlsIndex = dbgsysTlsAlloc();
    return JNI_OK;
}
