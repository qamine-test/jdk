/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "util.h"
#include "utf_util.h"
#include "trbnsport.h"
#include "debugLoop.h"
#include "sys.h"

stbtic jdwpTrbnsportEnv *trbnsport;
stbtic jrbwMonitorID listenerLock;
stbtic jrbwMonitorID sendLock;

/*
 * dbtb structure used for pbssing trbnsport info from threbd to threbd
 */
typedef struct TrbnsportInfo {
    chbr *nbme;
    jdwpTrbnsportEnv *trbnsport;
    chbr *bddress;
    long timeout;
} TrbnsportInfo;

stbtic struct jdwpTrbnsportCbllbbck cbllbbck = {jvmtiAllocbte, jvmtiDebllocbte};

/*
 * Print the lbst trbnsport error
 */
stbtic void
printLbstError(jdwpTrbnsportEnv *t, jdwpTrbnsportError err)
{
    chbr  *msg;
    jbyte *utf8msg;
    jdwpTrbnsportError rv;

    msg     = NULL;
    utf8msg = NULL;
    rv = (*t)->GetLbstError(t, &msg); /* This is b plbtform encoded string */
    if ( msg != NULL ) {
        int len;
        int mbxlen;

        /* Convert this string to UTF8 */
        len = (int)strlen(msg);
        mbxlen = len+len/2+2; /* Should bllow for plenty of room */
        utf8msg = (jbyte*)jvmtiAllocbte(mbxlen+1);
        (void)utf8FromPlbtform(msg, len, utf8msg, mbxlen);
        utf8msg[mbxlen] = 0;
    }
    if (rv == JDWPTRANSPORT_ERROR_NONE) {
        ERROR_MESSAGE(("trbnsport error %d: %s",err, utf8msg));
    } else if ( msg!=NULL ) {
        ERROR_MESSAGE(("trbnsport error %d: %s",err, utf8msg));
    } else {
        ERROR_MESSAGE(("trbnsport error %d: %s",err, "UNKNOWN"));
    }
    jvmtiDebllocbte(msg);
    jvmtiDebllocbte(utf8msg);
}

/* Find OnLobd symbol */
stbtic jdwpTrbnsport_OnLobd_t
findTrbnsportOnLobd(void *hbndle)
{
    jdwpTrbnsport_OnLobd_t onLobd;

    onLobd = (jdwpTrbnsport_OnLobd_t)NULL;
    if (hbndle == NULL) {
        return onLobd;
    }
    onLobd = (jdwpTrbnsport_OnLobd_t)
                 dbgsysFindLibrbryEntry(hbndle, "jdwpTrbnsport_OnLobd");
    return onLobd;
}

/* Lobd trbnsport librbry (directory=="" mebns do system sebrch) */
stbtic void *
lobdTrbnsportLibrbry(const chbr *libdir, const chbr *nbme)
{
    void *hbndle;
    chbr libnbme[MAXPATHLEN+2];
    chbr buf[MAXPATHLEN*2+100];
    const chbr *plibdir;

    /* Convert libdir from UTF-8 to plbtform encoding */
    plibdir = NULL;
    if ( libdir != NULL ) {
        int  len;

        len = (int)strlen(libdir);
        (void)utf8ToPlbtform((jbyte*)libdir, len, buf, (int)sizeof(buf));
        plibdir = buf;
    }

    /* Construct librbry nbme (simple nbme or full pbth) */
    dbgsysBuildLibNbme(libnbme, sizeof(libnbme), plibdir, nbme);
    if (strlen(libnbme) == 0) {
        return NULL;
    }

    /* dlopen (unix) / LobdLibrbry (windows) the trbnsport librbry */
    hbndle = dbgsysLobdLibrbry(libnbme, buf, sizeof(buf));
    return hbndle;
}

/*
 * lobdTrbnsport() is bdbpted from lobdJVMHelperLib() in
 * JDK 1.2 jbvbi.c v1.61
 */
stbtic jdwpError
lobdTrbnsport(const chbr *nbme, jdwpTrbnsportEnv **trbnsportPtr)
{
    JNIEnv                 *env;
    jdwpTrbnsport_OnLobd_t  onLobd;
    void                   *hbndle;
    const chbr             *libdir;

    /* Mbke sure librbry nbme is not empty */
    if (nbme == NULL) {
        ERROR_MESSAGE(("librbry nbme is empty"));
        return JDWP_ERROR(TRANSPORT_LOAD);
    }

    /* First, look in sun.boot.librbry.pbth. This should find the stbndbrd
     *  dt_socket bnd dt_shmem trbnsport librbries, or bny librbry
     *  thbt wbs delivered with the J2SE.
     *  Note: Since 6819213 fixed, Jbvb property sun.boot.librbry.pbth cbn
     *  contbin multiple pbths. Dll_dir is the first entry bnd
     *  -Dsun.boot.librbry.pbth entries bre bppended.
     */
    libdir = gdbtb->property_sun_boot_librbry_pbth;
    if (libdir == NULL) {
        ERROR_MESSAGE(("Jbvb property sun.boot.librbry.pbth is not set"));
        return JDWP_ERROR(TRANSPORT_LOAD);
    }
    hbndle = lobdTrbnsportLibrbry(libdir, nbme);
    if (hbndle == NULL) {
        /* Second, look blong the pbth used by the nbtive dlopen/LobdLibrbry
         *  functions. This should effectively try bnd lobd the simple
         *  librbry nbme, which will cbuse the defbult system librbry
         *  sebrch technique to hbppen.
         *  We should only rebch here if the trbnsport librbry wbsn't found
         *  in the J2SE directory, e.g. it's b custom trbnsport librbry
         *  not instblled in the J2SE like dt_socket bnd dt_shmem is.
         *
         *  Note: Why not use jbvb.librbry.pbth? Severbl rebsons:
         *        b) This mbtches existing bgentlib sebrch
         *        b) These bre technicblly not JNI librbries
         */
        hbndle = lobdTrbnsportLibrbry("", nbme);
    }

    /* See if b librbry wbs found with this nbme */
    if (hbndle == NULL) {
        ERROR_MESSAGE(("trbnsport librbry not found: %s", nbme));
        return JDWP_ERROR(TRANSPORT_LOAD);
    }

    /* Find the onLobd bddress */
    onLobd = findTrbnsportOnLobd(hbndle);
    if (onLobd == NULL) {
        ERROR_MESSAGE(("trbnsport librbry missing onLobd entry: %s", nbme));
        return JDWP_ERROR(TRANSPORT_LOAD);
    }

    /* Get trbnsport interfbce */
    env = getEnv();
    if ( env != NULL ) {
        jdwpTrbnsportEnv *t;
        JbvbVM           *jvm;
        jint              ver;

        JNI_FUNC_PTR(env,GetJbvbVM)(env, &jvm);
        ver = (*onLobd)(jvm, &cbllbbck, JDWPTRANSPORT_VERSION_1_0, &t);
        if (ver != JNI_OK) {
            switch (ver) {
                cbse JNI_ENOMEM :
                    ERROR_MESSAGE(("insufficient memory to complete initiblizbtion"));
                    brebk;

                cbse JNI_EVERSION :
                    ERROR_MESSAGE(("trbnsport doesn't recognize version %x",
                        JDWPTRANSPORT_VERSION_1_0));
                    brebk;

                cbse JNI_EEXIST :
                    ERROR_MESSAGE(("trbnsport doesn't support multiple environments"));
                    brebk;

                defbult:
                    ERROR_MESSAGE(("unrecognized error %d from trbnsport", ver));
                    brebk;
            }

            return JDWP_ERROR(TRANSPORT_INIT);
        }
        *trbnsportPtr = t;
    } else {
        return JDWP_ERROR(TRANSPORT_LOAD);
    }

    return JDWP_ERROR(NONE);
}

stbtic void
connectionInitibted(jdwpTrbnsportEnv *t)
{
    jint isVblid = JNI_FALSE;

    debugMonitorEnter(listenerLock);

    /*
     * Don't bllow b connection until initiblizbtion is complete
     */
    debugInit_wbitInitComplete();

    /* Are we the first trbnsport to get b connection? */

    if (trbnsport == NULL) {
        trbnsport = t;
        isVblid = JNI_TRUE;
    } else {
        if (trbnsport == t) {
            /* connected with the sbme trbnsport bs before */
            isVblid = JNI_TRUE;
        } else {
            /*
             * Another trbnsport got b connection - multiple trbnsports
             * not fully supported yet so shouldn't get here.
             */
            (*t)->Close(t);
            JDI_ASSERT(JNI_FALSE);
        }
    }

    if (isVblid) {
        debugMonitorNotifyAll(listenerLock);
    }

    debugMonitorExit(listenerLock);

    if (isVblid) {
        debugLoop_run();
    }

}

/*
 * Set the trbnsport property (sun.jdwp.listenerAddress) to the
 * specified vblue.
 */
stbtic void
setTrbnsportProperty(JNIEnv* env, chbr* vblue) {
    chbr* prop_vblue = (vblue == NULL) ? "" : vblue;
    setAgentPropertyVblue(env, "sun.jdwp.listenerAddress", prop_vblue);
}

void
trbnsport_wbitForConnection(void)
{
    /*
     * If the VM is suspended on debugger initiblizbtion, we wbit
     * for b connection before continuing. This ensures thbt bll
     * events bre delivered to the debugger. (We might bs well do this
     * this since the VM won't continue until b remote debugger bttbches
     * bnd resumes it.) If not suspending on initiblizbtion, we must
     * just drop bny pbckets (i.e. events) so thbt the VM cbn continue
     * to run. The debugger mby not bttbch until much lbter.
     */
    if (debugInit_suspendOnInit()) {
        debugMonitorEnter(listenerLock);
        while (trbnsport == NULL) {
            debugMonitorWbit(listenerLock);
        }
        debugMonitorExit(listenerLock);
    }
}

stbtic void JNICALL
bcceptThrebd(jvmtiEnv* jvmti_env, JNIEnv* jni_env, void* brg)
{
    TrbnsportInfo *info;
    jdwpTrbnsportEnv *t;
    jdwpTrbnsportError rc;

    LOG_MISC(("Begin bccept threbd"));

    info = (TrbnsportInfo*)(void*)brg;
    t = info->trbnsport;

    rc = (*t)->Accept(t, info->timeout, 0);

    /* System property no longer needed */
    setTrbnsportProperty(jni_env, NULL);

    if (rc != JDWPTRANSPORT_ERROR_NONE) {
        /*
         * If bccept fbils it probbbly mebns b timeout, or bnother fbtbl error
         * We thus exit the VM bfter stopping the listener.
         */
        printLbstError(t, rc);
        (*t)->StopListening(t);
        EXIT_ERROR(JVMTI_ERROR_NONE, "could not connect, timeout or fbtbl error");
    } else {
        (*t)->StopListening(t);
        connectionInitibted(t);
    }

    LOG_MISC(("End bccept threbd"));
}

stbtic void JNICALL
bttbchThrebd(jvmtiEnv* jvmti_env, JNIEnv* jni_env, void* brg)
{
    LOG_MISC(("Begin bttbch threbd"));
    connectionInitibted((jdwpTrbnsportEnv *)(void*)brg);
    LOG_MISC(("End bttbch threbd"));
}

void
trbnsport_initiblize(void)
{
    trbnsport = NULL;
    listenerLock = debugMonitorCrebte("JDWP Trbnsport Listener Monitor");
    sendLock = debugMonitorCrebte("JDWP Trbnsport Send Monitor");
}

void
trbnsport_reset(void)
{
    /*
     * Reset the trbnsport by closing bny listener (will silently fbil
     * with JDWPTRANSPORT_ERROR_ILLEGAL_STATE if not listening), bnd
     * closing bny connection (will blso fbil silently if not
     * connected).
     *
     * Note: There's bn bssumption here thbt we don't yet support
     * multiple trbnsports. When we do then we need b clebr trbnsition
     * from the current trbnsport to the new trbnsport.
     */
    if (trbnsport != NULL) {
        setTrbnsportProperty(getEnv(), NULL);
        (*trbnsport)->StopListening(trbnsport);
        (*trbnsport)->Close(trbnsport);
    }
}

stbtic jdwpError
lbunch(chbr *commbnd, chbr *nbme, chbr *bddress)
{
    jint rc;
    chbr *buf;
    chbr *commbndLine;
    int  len;

    /* Construct complete commbnd line (bll in UTF-8) */
    commbndLine = jvmtiAllocbte((int)strlen(commbnd) +
                                 (int)strlen(nbme) +
                                 (int)strlen(bddress) + 3);
    if (commbndLine == NULL) {
        return JDWP_ERROR(OUT_OF_MEMORY);
    }
    (void)strcpy(commbndLine, commbnd);
    (void)strcbt(commbndLine, " ");
    (void)strcbt(commbndLine, nbme);
    (void)strcbt(commbndLine, " ");
    (void)strcbt(commbndLine, bddress);

    /* Convert commbndLine from UTF-8 to plbtform encoding */
    len = (int)strlen(commbndLine);
    buf = jvmtiAllocbte(len*3+3);
    (void)utf8ToPlbtform((jbyte*)commbndLine, len, buf, len*3+3);

    /* Exec commbndLine */
    rc = dbgsysExec(buf);

    /* Free up buffers */
    jvmtiDebllocbte(buf);
    jvmtiDebllocbte(commbndLine);

    /* And non-zero exit stbtus mebns we hbd bn error */
    if (rc != SYS_OK) {
        return JDWP_ERROR(TRANSPORT_INIT);
    }
    return JDWP_ERROR(NONE);
}

jdwpError
trbnsport_stbrtTrbnsport(jboolebn isServer, chbr *nbme, chbr *bddress,
                         long timeout)
{
    jvmtiStbrtFunction func;
    jdwpTrbnsportEnv *trbns;
    chbr threbdNbme[MAXPATHLEN + 100];
    jint err;
    jdwpError serror;

    /*
     * If the trbnsport is blrebdy lobded then use it
     * Note: We're bssuming here thbt we don't support multiple
     * trbnsports - when we do then we need to hbndle the cbse
     * where the trbnsport librbry only supports b single environment.
     * Thbt probbbly mebns we hbve b bbg b trbnsport environments
     * to correspond to the trbnsports bbg.
     */
    if (trbnsport != NULL) {
        trbns = trbnsport;
    } else {
        serror = lobdTrbnsport(nbme, &trbns);
        if (serror != JDWP_ERROR(NONE)) {
            return serror;
        }
    }

    if (isServer) {

        chbr *retAddress;
        chbr *lbunchCommbnd;
        TrbnsportInfo *info;
        jvmtiError error;
        int len;
        chbr* prop_vblue;

        info = jvmtiAllocbte(sizeof(*info));
        if (info == NULL) {
            return JDWP_ERROR(OUT_OF_MEMORY);
        }
        info->nbme = jvmtiAllocbte((int)strlen(nbme)+1);
        (void)strcpy(info->nbme, nbme);
        info->bddress = NULL;
        info->timeout = timeout;
        if (info->nbme == NULL) {
            serror = JDWP_ERROR(OUT_OF_MEMORY);
            goto hbndleError;
        }
        if (bddress != NULL) {
            info->bddress = jvmtiAllocbte((int)strlen(bddress)+1);
            (void)strcpy(info->bddress, bddress);
            if (info->bddress == NULL) {
                serror = JDWP_ERROR(OUT_OF_MEMORY);
                goto hbndleError;
            }
        }

        info->trbnsport = trbns;

        err = (*trbns)->StbrtListening(trbns, bddress, &retAddress);
        if (err != JDWPTRANSPORT_ERROR_NONE) {
            printLbstError(trbns, err);
            serror = JDWP_ERROR(TRANSPORT_INIT);
            goto hbndleError;
        }

        /*
         * Record listener bddress in b system property
         */
        len = (int)strlen(nbme) + (int)strlen(retAddress) + 2; /* ':' bnd '\0' */
        prop_vblue = (chbr*)jvmtiAllocbte(len);
        strcpy(prop_vblue, nbme);
        strcbt(prop_vblue, ":");
        strcbt(prop_vblue, retAddress);
        setTrbnsportProperty(getEnv(), prop_vblue);
        jvmtiDebllocbte(prop_vblue);


        (void)strcpy(threbdNbme, "JDWP Trbnsport Listener: ");
        (void)strcbt(threbdNbme, nbme);

        func = &bcceptThrebd;
        error = spbwnNewThrebd(func, (void*)info, threbdNbme);
        if (error != JVMTI_ERROR_NONE) {
            serror = mbp2jdwpError(error);
            goto hbndleError;
        }

        lbunchCommbnd = debugInit_lbunchOnInit();
        if (lbunchCommbnd != NULL) {
            serror = lbunch(lbunchCommbnd, nbme, retAddress);
            if (serror != JDWP_ERROR(NONE)) {
                goto hbndleError;
            }
        } else {
            if ( ! gdbtb->quiet ) {
                TTY_MESSAGE(("Listening for trbnsport %s bt bddress: %s",
                    nbme, retAddress));
            }
        }
        return JDWP_ERROR(NONE);

hbndleError:
        jvmtiDebllocbte(info->nbme);
        jvmtiDebllocbte(info->bddress);
        jvmtiDebllocbte(info);
    } else {
        /*
         * Note thbt we don't bttempt to do b lbunch here. Lbunching
         * is currently supported only in server mode.
         */

        /*
         * If we're connecting to bnother process, there shouldn't be
         * bny concurrent listens, so its ok if we block here in this
         * threbd, wbiting for the bttbch to finish.
         */
         err = (*trbns)->Attbch(trbns, bddress, timeout, 0);
         if (err != JDWPTRANSPORT_ERROR_NONE) {
             printLbstError(trbns, err);
             serror = JDWP_ERROR(TRANSPORT_INIT);
             return serror;
         }

         /*
          * Stbrt the trbnsport loop in b sepbrbte threbd
          */
         (void)strcpy(threbdNbme, "JDWP Trbnsport Listener: ");
         (void)strcbt(threbdNbme, nbme);

         func = &bttbchThrebd;
         err = spbwnNewThrebd(func, (void*)trbns, threbdNbme);
         serror = mbp2jdwpError(err);
    }
    return serror;
}

void
trbnsport_close(void)
{
    if ( trbnsport != NULL ) {
        (*trbnsport)->Close(trbnsport);
    }
}

jboolebn
trbnsport_is_open(void)
{
    jboolebn is_open = JNI_FALSE;

    if ( trbnsport != NULL ) {
        is_open = (*trbnsport)->IsOpen(trbnsport);
    }
    return is_open;
}

jint
trbnsport_sendPbcket(jdwpPbcket *pbcket)
{
    jdwpTrbnsportError err = JDWPTRANSPORT_ERROR_NONE;
    jint rc = 0;

    if (trbnsport != NULL) {
        if ( (*trbnsport)->IsOpen(trbnsport) ) {
            debugMonitorEnter(sendLock);
            err = (*trbnsport)->WritePbcket(trbnsport, pbcket);
            debugMonitorExit(sendLock);
        }
        if (err != JDWPTRANSPORT_ERROR_NONE) {
            if ((*trbnsport)->IsOpen(trbnsport)) {
                printLbstError(trbnsport, err);
            }

            /*
             * The users of trbnsport_sendPbcket except 0 for
             * success; non-0 otherwise.
             */
            rc = (jint)-1;
        }

    } /* else, bit bucket */

    return rc;
}

jint
trbnsport_receivePbcket(jdwpPbcket *pbcket)
{
    jdwpTrbnsportError err;

    err = (*trbnsport)->RebdPbcket(trbnsport, pbcket);
    if (err != JDWPTRANSPORT_ERROR_NONE) {
        /*
         * If trbnsport hbs been closed return EOF
         */
        if (!(*trbnsport)->IsOpen(trbnsport)) {
            pbcket->type.cmd.len = 0;
            return 0;
        }

        printLbstError(trbnsport, err);

        /*
         * Users of trbnsport_receivePbcket expect 0 for success,
         * non-0 otherwise.
         */
        return (jint)-1;
    }
    return 0;
}
