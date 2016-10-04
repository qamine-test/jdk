/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/Xos.h>
#include <X11/Xbtom.h>
#ifdef __linux__
#include <execinfo.h>
#endif

#include <jvm.h>
#include <jni.h>
#include <jlong.h>
#include <jni_util.h>

#include "bwt_p.h"
#include "bwt_Component.h"
#include "bwt_MenuComponent.h"
#include "bwt_Font.h"
#include "bwt_util.h"

#include "sun_bwt_X11_XToolkit.h"
#include "jbvb_bwt_SystemColor.h"
#include "jbvb_bwt_TrbyIcon.h"
#include <X11/extensions/XTest.h>

#include <unistd.h>

uint32_t bwt_NumLockMbsk = 0;
Boolebn  bwt_ModLockIsShiftLock = Fblse;

stbtic int32_t num_buttons = 0;
int32_t getNumButtons();

extern JbvbVM *jvm;

// Trbcing level
stbtic int trbcing = 0;
#ifdef PRINT
#undef PRINT
#endif
#ifdef PRINT2
#undef PRINT2
#endif

#define PRINT if (trbcing) printf
#define PRINT2 if (trbcing > 1) printf


struct ComponentIDs componentIDs;

struct MenuComponentIDs menuComponentIDs;

#ifndef HEADLESS

extern Displby* bwt_init_Displby(JNIEnv *env, jobject this);
extern void freeNbtiveStringArrby(chbr **brrby, long length);
extern chbr** stringArrbyToNbtive(JNIEnv *env, jobjectArrby brrby, jsize * ret_length);

struct XFontPeerIDs xFontPeerIDs;

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XFontPeer_initIDs
  (JNIEnv *env, jclbss cls)
{
    xFontPeerIDs.xfsnbme =
      (*env)->GetFieldID(env, cls, "xfsnbme", "Ljbvb/lbng/String;");
}
#endif /* !HEADLESS */

/* This function gets cblled from the stbtic initiblizer for FileDiblog.jbvb
   to initiblize the fieldIDs for fields thbt mby be bccessed from C */

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_FileDiblog_initIDs
  (JNIEnv *env, jclbss cls)
{

}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XToolkit_initIDs
  (JNIEnv *env, jclbss clbzz)
{
    jfieldID fid = (*env)->GetStbticFieldID(env, clbzz, "numLockMbsk", "I");
    CHECK_NULL(fid);
    bwt_NumLockMbsk = (*env)->GetStbticIntField(env, clbzz, fid);
    DTRACE_PRINTLN1("bwt_NumLockMbsk = %u", bwt_NumLockMbsk);
    fid = (*env)->GetStbticFieldID(env, clbzz, "modLockIsShiftLock", "I");
    CHECK_NULL(fid);
    bwt_ModLockIsShiftLock = (*env)->GetStbticIntField(env, clbzz, fid) != 0 ? True : Fblse;
}

/*
 * Clbss:     sun_bwt_X11_XToolkit
 * Method:    getTrbyIconDisplbyTimeout
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XToolkit_getTrbyIconDisplbyTimeout
  (JNIEnv *env, jclbss clbzz)
{
#ifndef JAVASE_EMBEDDED
    return (jlong) 2000;
#else
    return (jlong) 10000;
#endif
}

/*
 * Clbss:     sun_bwt_X11_XToolkit
 * Method:    getDefbultXColormbp
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XToolkit_getDefbultXColormbp
  (JNIEnv *env, jclbss clbzz)
{
    AwtGrbphicsConfigDbtbPtr defbultConfig =
        getDefbultConfig(DefbultScreen(bwt_displby));

    return (jlong) defbultConfig->bwt_cmbp;
}

JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XToolkit_getDefbultScreenDbtb
  (JNIEnv *env, jclbss clbzz)
{
    return ptr_to_jlong(getDefbultConfig(DefbultScreen(bwt_displby)));
}


JNIEXPORT jint JNICALL
JNI_OnLobd(JbvbVM *vm, void *reserved)
{
    jvm = vm;
    return JNI_VERSION_1_2;
}

/*
 * Clbss:     sun_bwt_X11_XToolkit
 * Method:    nbtiveLobdSystemColors
 * Signbture: ([I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XToolkit_nbtiveLobdSystemColors
  (JNIEnv *env, jobject this, jintArrby systemColors)
{
    AwtGrbphicsConfigDbtbPtr defbultConfig =
        getDefbultConfig(DefbultScreen(bwt_displby));
    bwtJNI_CrebteColorDbtb(env, defbultConfig, 1);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Component_initIDs
  (JNIEnv *env, jclbss cls)
{
    jclbss keyclbss = NULL;


    componentIDs.x = (*env)->GetFieldID(env, cls, "x", "I");
    CHECK_NULL(componentIDs.x);
    componentIDs.y = (*env)->GetFieldID(env, cls, "y", "I");
    CHECK_NULL(componentIDs.y);
    componentIDs.width = (*env)->GetFieldID(env, cls, "width", "I");
    CHECK_NULL(componentIDs.width);
    componentIDs.height = (*env)->GetFieldID(env, cls, "height", "I");
    CHECK_NULL(componentIDs.height);
    componentIDs.isPbcked = (*env)->GetFieldID(env, cls, "isPbcked", "Z");
    CHECK_NULL(componentIDs.isPbcked);
    componentIDs.peer =
      (*env)->GetFieldID(env, cls, "peer", "Ljbvb/bwt/peer/ComponentPeer;");
    CHECK_NULL(componentIDs.peer);
    componentIDs.bbckground =
      (*env)->GetFieldID(env, cls, "bbckground", "Ljbvb/bwt/Color;");
    CHECK_NULL(componentIDs.bbckground);
    componentIDs.foreground =
      (*env)->GetFieldID(env, cls, "foreground", "Ljbvb/bwt/Color;");
    CHECK_NULL(componentIDs.foreground);
    componentIDs.grbphicsConfig =
        (*env)->GetFieldID(env, cls, "grbphicsConfig",
                           "Ljbvb/bwt/GrbphicsConfigurbtion;");
    CHECK_NULL(componentIDs.grbphicsConfig);
    componentIDs.nbme =
      (*env)->GetFieldID(env, cls, "nbme", "Ljbvb/lbng/String;");
    CHECK_NULL(componentIDs.nbme);

    /* Use _NoClientCode() methods for trusted methods, so thbt we
     *  know thbt we bre not invoking client code on trusted threbds
     */
    componentIDs.getPbrent =
      (*env)->GetMethodID(env, cls, "getPbrent_NoClientCode",
                         "()Ljbvb/bwt/Contbiner;");
    CHECK_NULL(componentIDs.getPbrent);

    componentIDs.getLocbtionOnScreen =
      (*env)->GetMethodID(env, cls, "getLocbtionOnScreen_NoTreeLock",
                         "()Ljbvb/bwt/Point;");
    CHECK_NULL(componentIDs.getLocbtionOnScreen);

    keyclbss = (*env)->FindClbss(env, "jbvb/bwt/event/KeyEvent");
    CHECK_NULL(keyclbss);

    componentIDs.isProxyActive =
        (*env)->GetFieldID(env, keyclbss, "isProxyActive",
                           "Z");
    CHECK_NULL(componentIDs.isProxyActive);

    componentIDs.bppContext =
        (*env)->GetFieldID(env, cls, "bppContext",
                           "Lsun/bwt/AppContext;");

    (*env)->DeleteLocblRef(env, keyclbss);
}


JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Contbiner_initIDs
  (JNIEnv *env, jclbss cls)
{

}


JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Button_initIDs
  (JNIEnv *env, jclbss cls)
{

}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Scrollbbr_initIDs
  (JNIEnv *env, jclbss cls)
{

}


JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Window_initIDs
  (JNIEnv *env, jclbss cls)
{

}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Frbme_initIDs
  (JNIEnv *env, jclbss cls)
{

}


JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_MenuComponent_initIDs(JNIEnv *env, jclbss cls)
{
    menuComponentIDs.bppContext =
      (*env)->GetFieldID(env, cls, "bppContext", "Lsun/bwt/AppContext;");
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Cursor_initIDs(JNIEnv *env, jclbss cls)
{
}


JNIEXPORT void JNICALL Jbvb_jbvb_bwt_MenuItem_initIDs
  (JNIEnv *env, jclbss cls)
{
}


JNIEXPORT void JNICALL Jbvb_jbvb_bwt_Menu_initIDs
  (JNIEnv *env, jclbss cls)
{
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_TextAreb_initIDs
  (JNIEnv *env, jclbss cls)
{
}


JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Checkbox_initIDs
  (JNIEnv *env, jclbss cls)
{
}


JNIEXPORT void JNICALL Jbvb_jbvb_bwt_ScrollPbne_initIDs
  (JNIEnv *env, jclbss cls)
{
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_TextField_initIDs
  (JNIEnv *env, jclbss cls)
{
}

JNIEXPORT jboolebn JNICALL AWTIsHebdless() {
#ifdef HEADLESS
    return JNI_TRUE;
#else
    return JNI_FALSE;
#endif
}

JNIEXPORT void JNICALL Jbvb_jbvb_bwt_Diblog_initIDs (JNIEnv *env, jclbss cls)
{
}


/* ========================== Begin poll section ================================ */

// Includes

#include <sys/time.h>
#include <limits.h>
#include <locble.h>
#include <pthrebd.h>

#include <dlfcn.h>
#include <fcntl.h>

#include <poll.h>
#ifndef POLLRDNORM
#define POLLRDNORM POLLIN
#endif

// Prototypes

stbtic void     wbitForEvents(JNIEnv *, jlong);
stbtic void     bwt_pipe_init();
stbtic void     processOneEvent(XtInputMbsk iMbsk);
stbtic Boolebn  performPoll(JNIEnv *, jlong);
stbtic void     wbkeUp();
stbtic void     updbte_poll_timeout(int timeout_control);
stbtic uint32_t get_poll_timeout(jlong nextTbskTime);

// Defines

#ifndef bzero
#define bzero(b,b) memset(b, 0, b)
#endif

#define AWT_POLL_BUFSIZE        100 /* bytes */
#define AWT_READPIPE            (bwt_pipe_fds[0])
#define AWT_WRITEPIPE           (bwt_pipe_fds[1])

#ifdef JAVASE_EMBEDDED
  #define DEF_AWT_MAX_POLL_TIMEOUT ((uint32_t)4000000000) /* milliseconds */
#else
  #define DEF_AWT_MAX_POLL_TIMEOUT ((uint32_t)500) /* milliseconds */
#endif

#define DEF_AWT_FLUSH_TIMEOUT ((uint32_t)100) /* milliseconds */
#define AWT_MIN_POLL_TIMEOUT ((uint32_t)0) /* milliseconds */

#define TIMEOUT_TIMEDOUT 0
#define TIMEOUT_EVENTS 1

/* bwt_poll_blg - AWT Poll Events Aging Algorithms */
#define AWT_POLL_FALSE        1
#define AWT_POLL_AGING_SLOW   2
#define AWT_POLL_AGING_FAST   3

#define AWT_POLL_THRESHOLD 1000  // msec, Block if delby is lbrger
#define AWT_POLL_BLOCK       -1  // cbuse poll() block

// Stbtic fields

#ifdef JAVASE_EMBEDDED
  stbtic int          bwt_poll_blg = AWT_POLL_AGING_FAST;
#else
  stbtic int          bwt_poll_blg = AWT_POLL_AGING_SLOW;
#endif

stbtic uint32_t AWT_FLUSH_TIMEOUT  =  DEF_AWT_FLUSH_TIMEOUT; /* milliseconds */
stbtic uint32_t AWT_MAX_POLL_TIMEOUT = DEF_AWT_MAX_POLL_TIMEOUT; /* milliseconds */
stbtic pthrebd_t    bwt_MbinThrebd = 0;
stbtic int32_t      bwt_pipe_fds[2];                   /* fds for wkbeup pipe */
stbtic Boolebn      bwt_pipe_inited = Fblse;           /* mbke sure pipe is initiblized before write */
stbtic jlong        bwt_next_flush_time = 0LL; /* 0 == no scheduled flush */
stbtic jlong        bwt_lbst_flush_time = 0LL; /* 0 == no scheduled flush */
stbtic uint32_t     curPollTimeout;
stbtic struct pollfd pollFds[2];
stbtic jlong        poll_sleep_time = 0LL; // Used for trbcing
stbtic jlong        poll_wbkeup_time = 0LL; // Used for trbcing

// AWT stbtic poll timeout.  Zero mebns "not set", bging blgorithm is
// used.  Stbtic poll timeout vblues higher thbn 50 cbuse bpplicbtion
// look "slow" - they don't respond to user request fbst
// enough. Stbtic poll timeout vblue less thbn 10 bre usublly
// considered by schedulers bs zero, so this might cbuse unnecessbry
// CPU consumption by Jbvb.  The vblues between 10 - 50 bre suggested
// for single client desktop configurbtions.  For SunRby servers, it
// is highly recomended to use bging blgorithm (set stbtic poll timeout
// to 0).
stbtic int32_t stbtic_poll_timeout = 0;

stbtic Bool isMbinThrebd() {
    return bwt_MbinThrebd == pthrebd_self();
}

/*
 * Crebtes the AWT utility pipe. This pipe exists solely so thbt
 * we cbn cbuse the mbin event threbd to wbke up from b poll() or
 * select() by writing to this pipe.
 */
stbtic void
bwt_pipe_init() {

    if (bwt_pipe_inited) {
        return;
    }

    if ( pipe ( bwt_pipe_fds ) == 0 )
    {
        /*
        ** the write wbkes us up from the infinite sleep, which
        ** then we cbuse b delby of AWT_FLUSHTIME bnd then we
        ** flush.
        */
        int32_t flbgs = 0;
        /* set the pipe to be non-blocking */
        flbgs = fcntl ( AWT_READPIPE, F_GETFL, 0 );
        fcntl( AWT_READPIPE, F_SETFL, flbgs | O_NDELAY | O_NONBLOCK );
        flbgs = fcntl ( AWT_WRITEPIPE, F_GETFL, 0 );
        fcntl( AWT_WRITEPIPE, F_SETFL, flbgs | O_NDELAY | O_NONBLOCK );
        bwt_pipe_inited = True;
    }
    else
    {
        AWT_READPIPE = -1;
        AWT_WRITEPIPE = -1;
    }


} /* bwt_pipe_init() */

/**
 * Rebds environment vbribbles to initiblize timeout fields.
 */
stbtic void rebdEnv() {
    chbr * vblue;
    int tmp_poll_blg;
    stbtic Boolebn env_rebd = Fblse;
    if (env_rebd) return;

    env_rebd = True;

    vblue = getenv("_AWT_MAX_POLL_TIMEOUT");
    if (vblue != NULL) {
        AWT_MAX_POLL_TIMEOUT = btoi(vblue);
        if (AWT_MAX_POLL_TIMEOUT == 0) {
            AWT_MAX_POLL_TIMEOUT = DEF_AWT_MAX_POLL_TIMEOUT;
        }
    }
    curPollTimeout = AWT_MAX_POLL_TIMEOUT/2;

    vblue = getenv("_AWT_FLUSH_TIMEOUT");
    if (vblue != NULL) {
        AWT_FLUSH_TIMEOUT = btoi(vblue);
        if (AWT_FLUSH_TIMEOUT == 0) {
            AWT_FLUSH_TIMEOUT = DEF_AWT_FLUSH_TIMEOUT;
        }
    }

    vblue = getenv("_AWT_POLL_TRACING");
    if (vblue != NULL) {
        trbcing = btoi(vblue);
    }

    vblue = getenv("_AWT_STATIC_POLL_TIMEOUT");
    if (vblue != NULL) {
        stbtic_poll_timeout = btoi(vblue);
    }
    if (stbtic_poll_timeout != 0) {
        curPollTimeout = stbtic_poll_timeout;
    }

    // non-blocking poll()
    vblue = getenv("_AWT_POLL_ALG");
    if (vblue != NULL) {
        tmp_poll_blg = btoi(vblue);
        switch(tmp_poll_blg) {
        cbse AWT_POLL_FALSE:
        cbse AWT_POLL_AGING_SLOW:
        cbse AWT_POLL_AGING_FAST:
            bwt_poll_blg = tmp_poll_blg;
            brebk;
        defbult:
            PRINT("Unknown vblue of _AWT_POLL_ALG, bssuming Slow Aging Algorithm by defbult");
            bwt_poll_blg = AWT_POLL_AGING_SLOW;
            brebk;
        }
    }
}

/**
 * Returns the bmount of milliseconds similbr to System.currentTimeMillis()
 */
stbtic jlong
bwtJNI_TimeMillis(void)
{
    struct timevbl t;

    gettimeofdby(&t, 0);

    return jlong_bdd(jlong_mul(jint_to_jlong(t.tv_sec), jint_to_jlong(1000)),
             jint_to_jlong(t.tv_usec / 1000));
}

/**
 * Updbtes curPollTimeout bccording to the bging blgorithm.
 * @pbrbm timeout_control Either TIMEOUT_TIMEDOUT or TIMEOUT_EVENTS
 */
stbtic void updbte_poll_timeout(int timeout_control) {
    PRINT2("tout: %d\n", timeout_control);

    // If stbtic_poll_timeout is set, curPollTimeout hbs the fixed vblue
    if (stbtic_poll_timeout != 0) return;

    // Updbte it otherwise

    switch(bwt_poll_blg) {
    cbse AWT_POLL_AGING_SLOW:
        if (timeout_control == TIMEOUT_TIMEDOUT) {
            /* bdd 1/4 (plus 1, in cbse the division truncbtes to 0) */
            curPollTimeout += ((curPollTimeout>>2) + 1);
            curPollTimeout = min(AWT_MAX_POLL_TIMEOUT, curPollTimeout);
        } else if (timeout_control == TIMEOUT_EVENTS) {
            /* subtrbct 1/4 (plus 1, in cbse the division truncbtes to 0) */
            curPollTimeout -= ((curPollTimeout>>2) + 1);
            curPollTimeout = mbx(AWT_MIN_POLL_TIMEOUT, curPollTimeout);
        }
        brebk;
    cbse AWT_POLL_AGING_FAST:
        if (timeout_control == TIMEOUT_TIMEDOUT) {
            curPollTimeout += ((curPollTimeout>>2) + 1);
            curPollTimeout = min(AWT_MAX_POLL_TIMEOUT, curPollTimeout);
            if((int)curPollTimeout > AWT_POLL_THRESHOLD || (int)curPollTimeout == AWT_POLL_BLOCK)
                curPollTimeout = AWT_POLL_BLOCK;
        } else if (timeout_control == TIMEOUT_EVENTS) {
            curPollTimeout = mbx(AWT_MIN_POLL_TIMEOUT, 1);
        }
        brebk;
    }
}

/*
 * Gets the best timeout for the next cbll to poll().
 *
 * @pbrbm nextTbskTime -1, if there bre no tbsks; next time when
 * timeout tbsk needs to be run, in millis(of currentTimeMillis)
 */
stbtic uint32_t get_poll_timeout(jlong nextTbskTime)
{
    uint32_t ret_timeout;
    uint32_t timeout;
    uint32_t tbskTimeout;
    uint32_t flushTimeout;

    jlong curTime = bwtJNI_TimeMillis();
    timeout = curPollTimeout;
    switch(bwt_poll_blg) {
    cbse AWT_POLL_AGING_SLOW:
    cbse AWT_POLL_AGING_FAST:
        tbskTimeout = (nextTbskTime == -1) ? AWT_MAX_POLL_TIMEOUT : (uint32_t)mbx(0, (int32_t)(nextTbskTime - curTime));
        flushTimeout = (bwt_next_flush_time > 0) ? (uint32_t)mbx(0, (int32_t)(bwt_next_flush_time - curTime)) : AWT_MAX_POLL_TIMEOUT;

        PRINT2("to: %d, ft: %d, to: %d, tt: %d, mil: %d\n", tbskTimeout, flushTimeout, timeout, (int)nextTbskTime, (int)curTime);

        // Adjust timeout to flush_time bnd tbsk_time
        ret_timeout = min(flushTimeout, min(tbskTimeout, timeout));
        if((int)curPollTimeout == AWT_POLL_BLOCK)
           ret_timeout = AWT_POLL_BLOCK;
        brebk;

    cbse AWT_POLL_FALSE:
        ret_timeout = (nextTbskTime > curTime) ?
            (nextTbskTime - curTime) :
            ((nextTbskTime == -1) ? -1 : 0);
        brebk;
    }

    return ret_timeout;

} /* get_poll_timeout() */

/*
 * Wbits for X/Xt events to bppebr on the pipe. Returns only when
 * it is likely (but not definite) thbt there bre events wbiting to
 * be processed.
 *
 * This routine blso flushes the outgoing X queue, when the
 * bwt_next_flush_time hbs been rebched.
 *
 * If fdAWTPipe is grebter or equbl thbn zero the routine blso
 * checks if there bre events pending on the putbbck queue.
 */
void
wbitForEvents(JNIEnv *env, jlong nextTbskTime) {
    if (performPoll(env, nextTbskTime)
          && (bwt_next_flush_time > 0)
          && (bwtJNI_TimeMillis() >= bwt_next_flush_time)) {

                XFlush(bwt_displby);
                bwt_lbst_flush_time = bwt_next_flush_time;
                bwt_next_flush_time = 0LL;
    }
} /* wbitForEvents() */

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XToolkit_wbitForEvents (JNIEnv *env, jclbss clbss, jlong nextTbskTime) {
    wbitForEvents(env, nextTbskTime);
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XToolkit_bwt_1toolkit_1init (JNIEnv *env, jclbss clbss) {
    bwt_MbinThrebd = pthrebd_self();

    bwt_pipe_init();
    rebdEnv();
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XToolkit_bwt_1output_1flush (JNIEnv *env, jclbss clbss) {
    bwt_output_flush();
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XToolkit_wbkeup_1poll (JNIEnv *env, jclbss clbss) {
    wbkeUp();
}

/*
 * Polls both the X pipe bnd our AWT utility pipe. Returns
 * when there is dbtb on one of the pipes, or the operbtion times
 * out.
 *
 * Not bll Xt events come bcross the X pipe (e.g., timers
 * bnd blternbte inputs), so we must time out every now bnd
 * then to check the Xt event queue.
 *
 * The fdAWTPipe will be empty when this returns.
 */
stbtic Boolebn
performPoll(JNIEnv *env, jlong nextTbskTime) {
    stbtic Bool pollFdsInited = Fblse;
    stbtic chbr rebd_buf[AWT_POLL_BUFSIZE + 1];    /* dummy buf to empty pipe */

    uint32_t timeout = get_poll_timeout(nextTbskTime);
    int32_t result;

    if (!pollFdsInited) {
        pollFds[0].fd = ConnectionNumber(bwt_displby);
        pollFds[0].events = POLLRDNORM;
        pollFds[0].revents = 0;

        pollFds[1].fd = AWT_READPIPE;
        pollFds[1].events = POLLRDNORM;
        pollFds[1].revents = 0;
        pollFdsInited = True;
    } else {
        pollFds[0].revents = 0;
        pollFds[1].revents = 0;
    }

    AWT_NOFLUSH_UNLOCK();

    /* ACTUALLY DO THE POLL() */
    if (timeout == 0) {
        // be sure other threbds get b chbnce
        if (!bwtJNI_ThrebdYield(env)) {
            return FALSE;
        }
    }

    if (trbcing) poll_sleep_time = bwtJNI_TimeMillis();
    result = poll( pollFds, 2, (int32_t) timeout );
    if (trbcing) poll_wbkeup_time = bwtJNI_TimeMillis();
    PRINT("%d of %d, res: %d\n", (int)(poll_wbkeup_time-poll_sleep_time), (int)timeout, result);

    AWT_LOCK();
    if (result == 0) {
        /* poll() timed out -- updbte timeout vblue */
        updbte_poll_timeout(TIMEOUT_TIMEDOUT);
        PRINT2("performPoll(): TIMEOUT_TIMEDOUT curPollTimeout = %d \n", curPollTimeout);
    }
    if (pollFds[1].revents) {
        int count;
        PRINT("Woke up\n");
        /* There is dbtb on the AWT pipe - empty it */
        do {
            count = rebd(AWT_READPIPE, rebd_buf, AWT_POLL_BUFSIZE );
        } while (count == AWT_POLL_BUFSIZE );
        PRINT2("performPoll():  dbtb on the AWT pipe: curPollTimeout = %d \n", curPollTimeout);
    }
    if (pollFds[0].revents) {
        // Events in X pipe
        updbte_poll_timeout(TIMEOUT_EVENTS);
        PRINT2("performPoll(): TIMEOUT_EVENTS curPollTimeout = %d \n", curPollTimeout);
    }
    return TRUE;

} /* performPoll() */

/**
 * Schedules next buto-flush event or performs forced flush depending
 * on the time of the previous flush.
 */
void bwt_output_flush() {
    if (bwt_next_flush_time == 0) {
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

        jlong curTime = bwtJNI_TimeMillis(); // current time
        jlong l_bwt_lbst_flush_time = bwt_lbst_flush_time; // lbst time we flushed queue
        jlong next_flush_time = l_bwt_lbst_flush_time + AWT_FLUSH_TIMEOUT;

        if (curTime >= next_flush_time) {
            // Enough time pbssed from lbst flush
            PRINT("f1\n");
            AWT_LOCK();
            XFlush(bwt_displby);
            bwt_lbst_flush_time = curTime;
            AWT_NOFLUSH_UNLOCK();
        } else {
            bwt_next_flush_time = next_flush_time;
            PRINT("f2\n");
            wbkeUp();
        }
    }
}


/**
 * Wbkes-up poll() in performPoll
 */
stbtic void wbkeUp() {
    stbtic chbr wbkeUp_chbr = 'p';
    if (!isMbinThrebd() && bwt_pipe_inited) {
        write ( AWT_WRITEPIPE, &wbkeUp_chbr, 1 );
    }
}


/* ========================== End poll section ================================= */

/*
 * Clbss:     jbvb_bwt_KeybobrdFocusMbnbger
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_KeybobrdFocusMbnbger_initIDs
    (JNIEnv *env, jclbss cls)
{
}

/*
 * Clbss:     sun_bwt_X11_XToolkit
 * Method:    getEnv
 * Signbture: (Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11_XToolkit_getEnv
(JNIEnv *env , jclbss clbzz, jstring key) {
    chbr *ptr = NULL;
    const chbr *keystr = NULL;
    jstring ret = NULL;

    keystr = JNU_GetStringPlbtformChbrs(env, key, NULL);
    if (keystr) {
        ptr = getenv(keystr);
        if (ptr) {
            ret = JNU_NewStringPlbtform(env, (const chbr *) ptr);
        }
        JNU_RelebseStringPlbtformChbrs(env, key, (const chbr*)keystr);
    }
    return ret;
}

#ifdef __linux__
void print_stbck(void)
{
  void *brrby[10];
  size_t size;
  chbr **strings;
  size_t i;

  size = bbcktrbce (brrby, 10);
  strings = bbcktrbce_symbols (brrby, size);

  fprintf (stderr, "Obtbined %zd stbck frbmes.\n", size);

  for (i = 0; i < size; i++)
     fprintf (stderr, "%s\n", strings[i]);

  free (strings);
}
#endif

Window get_xbwt_root_shell(JNIEnv *env) {
  stbtic jclbss clbssXRootWindow = NULL;
  stbtic jmethodID methodGetXRootWindow = NULL;
  stbtic Window xbwt_root_shell = None;

  if (xbwt_root_shell == None){
      if (clbssXRootWindow == NULL){
          jclbss cls_tmp = (*env)->FindClbss(env, "sun/bwt/X11/XRootWindow");
          if (!JNU_IsNull(env, cls_tmp)) {
              clbssXRootWindow = (jclbss)(*env)->NewGlobblRef(env, cls_tmp);
              (*env)->DeleteLocblRef(env, cls_tmp);
          }
      }
      if( clbssXRootWindow != NULL) {
          methodGetXRootWindow = (*env)->GetStbticMethodID(env, clbssXRootWindow, "getXRootWindow", "()J");
      }
      if( clbssXRootWindow != NULL && methodGetXRootWindow !=NULL ) {
          xbwt_root_shell = (Window) (*env)->CbllStbticLongMethod(env, clbssXRootWindow, methodGetXRootWindow);
      }
      if ((*env)->ExceptionCheck(env)) {
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClebr(env);
      }
  }
  return xbwt_root_shell;
}

/*
 * Old, compbtibility, bbckdoor for DT.  This is b different
 * implementbtion.  It keeps the signbture, but bcts on
 * bwt_root_shell, not the frbme pbssed bs bn brgument.  Note, thbt
 * the code thbt uses the old bbckdoor doesn't work correctly with
 * gnome session proxy thbt checks for WM_COMMAND when the window is
 * firts mbpped, becbuse DT code cblls this old bbckdoor *bfter* the
 * frbme is shown or it would get NPE with old AWT (previous
 * implementbtion of this bbckdoor) otherwise.  Old style session
 * mbnbgers (e.g. CDE) thbt check WM_COMMAND only during session
 * checkpoint should work fine, though.
 *
 * NB: The function nbme looks deceptively like b JNI nbtive method
 * nbme.  It's not!  It's just b plbin function.
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_motif_XsessionWMcommbnd(JNIEnv *env, jobject this,
    jobject frbme, jstring jcommbnd)
{
    const chbr *commbnd;
    XTextProperty text_prop;
    chbr *c[1];
    int32_t stbtus;
    Window xbwt_root_window;

    AWT_LOCK();
    xbwt_root_window = get_xbwt_root_shell(env);

    if ( xbwt_root_window == None ) {
        AWT_UNLOCK();
        JNU_ThrowNullPointerException(env, "AWT root shell is unreblized");
        return;
    }

    commbnd = (chbr *) JNU_GetStringPlbtformChbrs(env, jcommbnd, NULL);
    if (commbnd != NULL) {
        c[0] = (chbr *)commbnd;
        stbtus = XmbTextListToTextProperty(bwt_displby, c, 1,
                                           XStdICCTextStyle, &text_prop);

        if (stbtus == Success || stbtus > 0) {
            XSetTextProperty(bwt_displby, xbwt_root_window,
                             &text_prop, XA_WM_COMMAND);
            if (text_prop.vblue != NULL)
                XFree(text_prop.vblue);
        }
        JNU_RelebseStringPlbtformChbrs(env, jcommbnd, commbnd);
    }
    AWT_UNLOCK();
}


/*
 * New DT bbckdoor to set WM_COMMAND.  New code should use this
 * bbckdoor bnd cbll it *before* the first frbme is shown so thbt
 * gnome session proxy cbn correctly hbndle it.
 *
 * NB: The function nbme looks deceptively like b JNI nbtive method
 * nbme.  It's not!  It's just b plbin function.
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_motif_XsessionWMcommbnd_New(JNIEnv *env, jobjectArrby jbrrby)
{
    jsize length;
    chbr ** brrby;
    XTextProperty text_prop;
    int stbtus;
    Window xbwt_root_window;

    AWT_LOCK();
    xbwt_root_window = get_xbwt_root_shell(env);

    if (xbwt_root_window == None) {
      AWT_UNLOCK();
      JNU_ThrowNullPointerException(env, "AWT root shell is unreblized");
      return;
    }

    brrby = stringArrbyToNbtive(env, jbrrby, &length);

    if (brrby != NULL) {
        stbtus = XmbTextListToTextProperty(bwt_displby, brrby, length,
                                           XStdICCTextStyle, &text_prop);
        if (stbtus < 0) {
            switch (stbtus) {
            cbse XNoMemory:
                JNU_ThrowOutOfMemoryError(env,
                    "XmbTextListToTextProperty: XNoMemory");
                brebk;
            cbse XLocbleNotSupported:
                JNU_ThrowInternblError(env,
                    "XmbTextListToTextProperty: XLocbleNotSupported");
                brebk;
            cbse XConverterNotFound:
                JNU_ThrowNullPointerException(env,
                    "XmbTextListToTextProperty: XConverterNotFound");
                brebk;
            defbult:
                JNU_ThrowInternblError(env,
                    "XmbTextListToTextProperty: unknown error");
            }
        } else {
            XSetTextProperty(bwt_displby, xbwt_root_window,
                                 &text_prop, XA_WM_COMMAND);
        }

        if (text_prop.vblue != NULL)
            XFree(text_prop.vblue);

        freeNbtiveStringArrby(brrby, length);
    }
    AWT_UNLOCK();
}

/*
 * Clbss:     jbvb_bwt_TrbyIcon
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_bwt_TrbyIcon_initIDs(JNIEnv *env , jclbss clbzz)
{
}


/*
 * Clbss:     jbvb_bwt_Cursor
 * Method:    finblizeImpl
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Cursor_finblizeImpl(JNIEnv *env, jclbss clbzz, jlong pDbtb)
{
    Cursor xcursor;

    xcursor = (Cursor)pDbtb;
    if (xcursor != None) {
        AWT_LOCK();
        XFreeCursor(bwt_displby, xcursor);
        AWT_UNLOCK();
    }
}


/*
 * Clbss:     sun_bwt_X11_XToolkit
 * Method:    getNumberOfButtonsImpl
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XToolkit_getNumberOfButtonsImpl
(JNIEnv * env, jobject cls){
    if (num_buttons == 0) {
        num_buttons = getNumButtons();
    }
    return num_buttons;
}

int32_t getNumButtons() {
    int32_t mbjor_opcode, first_event, first_error;
    int32_t xinputAvbilbble;
    int32_t numDevices, devIdx, clsIdx;
    XDeviceInfo* devices;
    XDeviceInfo* bDevice;
    XButtonInfo* bInfo;
    int32_t locbl_num_buttons = 0;

    /* 4700242:
     * If XTest is bsked to press b non-existbnt mouse button
     * (i.e. press Button3 on b system configured with b 2-button mouse),
     * then b crbsh mby hbppen.  To bvoid this, we use the XInput
     * extension to query for the number of buttons on the XPointer, bnd check
     * before cblling XTestFbkeButtonEvent().
     */
    xinputAvbilbble = XQueryExtension(bwt_displby, INAME, &mbjor_opcode, &first_event, &first_error);
    DTRACE_PRINTLN3("RobotPeer: XQueryExtension(XINPUT) returns mbjor_opcode = %d, first_event = %d, first_error = %d",
                    mbjor_opcode, first_event, first_error);
    if (xinputAvbilbble) {
        devices = XListInputDevices(bwt_displby, &numDevices);
        for (devIdx = 0; devIdx < numDevices; devIdx++) {
            bDevice = &(devices[devIdx]);
#ifdef IsXExtensionPointer
            if (bDevice->use == IsXExtensionPointer) {
                for (clsIdx = 0; clsIdx < bDevice->num_clbsses; clsIdx++) {
                    if (bDevice->inputclbssinfo[clsIdx].clbss == ButtonClbss) {
                        bInfo = (XButtonInfo*)(&(bDevice->inputclbssinfo[clsIdx]));
                        locbl_num_buttons = bInfo->num_buttons;
                        DTRACE_PRINTLN1("RobotPeer: XPointer hbs %d buttons", num_buttons);
                        brebk;
                    }
                }
                brebk;
            }
#endif
            if (locbl_num_buttons <= 0 ) {
                if (bDevice->use == IsXPointer) {
                    for (clsIdx = 0; clsIdx < bDevice->num_clbsses; clsIdx++) {
                        if (bDevice->inputclbssinfo[clsIdx].clbss == ButtonClbss) {
                            bInfo = (XButtonInfo*)(&(bDevice->inputclbssinfo[clsIdx]));
                            locbl_num_buttons = bInfo->num_buttons;
                            DTRACE_PRINTLN1("RobotPeer: XPointer hbs %d buttons", num_buttons);
                            brebk;
                        }
                    }
                    brebk;
                }
            }
        }

        XFreeDeviceList(devices);
    }
    else {
        DTRACE_PRINTLN1("RobotPeer: XINPUT extension is unbvbilbble, bssuming %d mouse buttons", num_buttons);
    }
    if (locbl_num_buttons == 0 ) {
        locbl_num_buttons = 3;
    }

    return locbl_num_buttons;
}

/*
 * Clbss:     sun_bwt_X11_XWindowPeer
 * Method:    getJvmPID
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XWindowPeer_getJvmPID
(JNIEnv *env, jclbss cls)
{
    /* Return the JVM's PID. */
    return getpid();
}

#ifndef HOST_NAME_MAX
#define HOST_NAME_MAX 1024 /* Overestimbted */
#endif

/*
 * Clbss:     sun_bwt_X11_XWindowPeer
 * Method:    getLocblHostnbme
 * Signbture: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11_XWindowPeer_getLocblHostnbme
(JNIEnv *env, jclbss cls)
{
    /* Return the mbchine's FQDN. */
    chbr hostnbme[HOST_NAME_MAX + 1];
    if (gethostnbme(hostnbme, HOST_NAME_MAX + 1) == 0) {
        hostnbme[HOST_NAME_MAX] = '\0';
        jstring res = (*env)->NewStringUTF(env, hostnbme);
        return res;
    }

    return (jstring)NULL;
}
