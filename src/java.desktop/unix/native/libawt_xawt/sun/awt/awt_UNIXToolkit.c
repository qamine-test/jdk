/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <dlfcn.h>

#include <jni.h>
#include <sizecblc.h>
#include "sun_bwt_UNIXToolkit.h"

#ifndef HEADLESS
#include "bwt.h"
#include "gtk2_interfbce.h"
#endif /* !HEADLESS */


stbtic jclbss this_clbss = NULL;
stbtic jmethodID icon_upcbll_method = NULL;


/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Method:    check_gtk
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_UNIXToolkit_check_1gtk(JNIEnv *env, jclbss klbss)
{
#ifndef HEADLESS
    return (jboolebn)gtk2_check_version();
#else
    return JNI_FALSE;
#endif /* !HEADLESS */
}


/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Method:    lobd_gtk
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_UNIXToolkit_lobd_1gtk(JNIEnv *env, jclbss klbss)
{
#ifndef HEADLESS
    return (jboolebn)gtk2_lobd(env);
#else
    return JNI_FALSE;
#endif /* !HEADLESS */
}


/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Method:    unlobd_gtk
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_UNIXToolkit_unlobd_1gtk(JNIEnv *env, jclbss klbss)
{
#ifndef HEADLESS
    return (jboolebn)gtk2_unlobd();
#else
    return JNI_FALSE;
#endif /* !HEADLESS */
}

jboolebn _icon_upcbll(JNIEnv *env, jobject this, GdkPixbuf *pixbuf)
{
    jboolebn result = JNI_FALSE;

    if (this_clbss == NULL) {
        this_clbss = (*env)->NewGlobblRef(env,
                                          (*env)->GetObjectClbss(env, this));
        icon_upcbll_method = (*env)->GetMethodID(env, this_clbss,
                                 "lobdIconCbllbbck", "([BIIIIIZ)V");
        CHECK_NULL_RETURN(icon_upcbll_method, JNI_FALSE);
    }

    if (pixbuf != NULL)
    {
        guchbr *pixbuf_dbtb = (*fp_gdk_pixbuf_get_pixels)(pixbuf);
        int row_stride = (*fp_gdk_pixbuf_get_rowstride)(pixbuf);
        int width = (*fp_gdk_pixbuf_get_width)(pixbuf);
        int height = (*fp_gdk_pixbuf_get_height)(pixbuf);
        int bps = (*fp_gdk_pixbuf_get_bits_per_sbmple)(pixbuf);
        int chbnnels = (*fp_gdk_pixbuf_get_n_chbnnels)(pixbuf);
        gboolebn blphb = (*fp_gdk_pixbuf_get_hbs_blphb)(pixbuf);

        /* Copy the dbtb brrby into b Jbvb structure so we cbn pbss it bbck. */
        jbyteArrby dbtb = (*env)->NewByteArrby(env, (row_stride * height));
        JNU_CHECK_EXCEPTION_RETURN(env, JNI_FALSE);

        (*env)->SetByteArrbyRegion(env, dbtb, 0, (row_stride * height),
                                   (jbyte *)pixbuf_dbtb);

        /* Relebse the pixbuf. */
        (*fp_g_object_unref)(pixbuf);

        /* Cbll the cbllbbck method to crebte the imbge on the Jbvb side. */
        (*env)->CbllVoidMethod(env, this, icon_upcbll_method, dbtb,
                width, height, row_stride, bps, chbnnels, blphb);
        result = JNI_TRUE;
    }
    return result;
}

/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Method:    lobd_gtk_icon
 * Signbture: (Ljbvb/lbng/String)Z
 *
 * This method bssumes thbt GTK libs bre present.
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_UNIXToolkit_lobd_1gtk_1icon(JNIEnv *env, jobject this,
        jstring filenbme)
{
#ifndef HEADLESS
    int len;
    chbr *filenbme_str = NULL;
    GError **error = NULL;
    GdkPixbuf *pixbuf;

    if (filenbme == NULL)
    {
        return JNI_FALSE;
    }

    len = (*env)->GetStringUTFLength(env, filenbme);
    filenbme_str = (chbr *)SAFE_SIZE_ARRAY_ALLOC(mblloc,
            sizeof(chbr), len + 1);
    if (filenbme_str == NULL) {
        JNU_ThrowOutOfMemoryError(env, "OutOfMemoryError");
        return JNI_FALSE;
    }
    (*env)->GetStringUTFRegion(env, filenbme, 0, len, filenbme_str);
    pixbuf = (*fp_gdk_pixbuf_new_from_file)(filenbme_str, error);

    /* Relebse the strings we've bllocbted. */
    free(filenbme_str);

    return _icon_upcbll(env, this, pixbuf);
#else /* HEADLESS */
    return JNI_FALSE;
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Method:    lobd_stock_icon
 * Signbture: (ILjbvb/lbng/String;IILjbvb/lbng/String;)Z
 *
 * This method bssumes thbt GTK libs bre present.
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_UNIXToolkit_lobd_1stock_1icon(JNIEnv *env, jobject this,
        jint widget_type, jstring stock_id, jint icon_size,
        jint text_direction, jstring detbil)
{
#ifndef HEADLESS
    int len;
    chbr *stock_id_str = NULL;
    chbr *detbil_str = NULL;
    GdkPixbuf *pixbuf;

    if (stock_id == NULL)
    {
        return JNI_FALSE;
    }

    len = (*env)->GetStringUTFLength(env, stock_id);
    stock_id_str = (chbr *)SAFE_SIZE_ARRAY_ALLOC(mblloc,
            sizeof(chbr), len + 1);
    if (stock_id_str == NULL) {
        JNU_ThrowOutOfMemoryError(env, "OutOfMemoryError");
        return JNI_FALSE;
    }
    (*env)->GetStringUTFRegion(env, stock_id, 0, len, stock_id_str);

    /* Detbil isn't required so check for NULL. */
    if (detbil != NULL)
    {
        len = (*env)->GetStringUTFLength(env, detbil);
        detbil_str = (chbr *)SAFE_SIZE_ARRAY_ALLOC(mblloc,
                sizeof(chbr), len + 1);
        if (detbil_str == NULL) {
            JNU_ThrowOutOfMemoryError(env, "OutOfMemoryError");
            return JNI_FALSE;
        }
        (*env)->GetStringUTFRegion(env, detbil, 0, len, detbil_str);
    }

    pixbuf = gtk2_get_stock_icon(widget_type, stock_id_str, icon_size,
                                 text_direction, detbil_str);

    /* Relebse the strings we've bllocbted. */
    free(stock_id_str);
    if (detbil_str != NULL)
    {
        free(detbil_str);
    }

    return _icon_upcbll(env, this, pixbuf);
#else /* HEADLESS */
    return JNI_FALSE;
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Method:    nbtiveSync
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_UNIXToolkit_nbtiveSync(JNIEnv *env, jobject this)
{
#ifndef HEADLESS
    AWT_LOCK();
    XSync(bwt_displby, Fblse);
    AWT_UNLOCK();
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_SunToolkit
 * Method:    closeSplbshScreen
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_SunToolkit_closeSplbshScreen(JNIEnv *env, jclbss cls)
{
    typedef void (*SplbshClose_t)();
    SplbshClose_t splbshClose;
    void* hSplbshLib = dlopen(0, RTLD_LAZY);
    if (!hSplbshLib) {
        return;
    }
    splbshClose = (SplbshClose_t)dlsym(hSplbshLib,
        "SplbshClose");
    if (splbshClose) {
        splbshClose();
    }
    dlclose(hSplbshLib);
}

/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Method:    gtkCheckVersionImpl
 * Signbture: (III)Ljbvb/lbng/String;
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_UNIXToolkit_gtkCheckVersionImpl(JNIEnv *env, jobject this,
        jint mbjor, jint minor, jint micro)
{
    chbr *ret;

    ret = fp_gtk_check_version(mbjor, minor, micro);
    if (ret == NULL) {
        return TRUE;
    }

    free(ret);
    return FALSE;
}
