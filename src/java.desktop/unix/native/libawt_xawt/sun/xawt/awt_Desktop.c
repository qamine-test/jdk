/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jni_util.h"
#include "gtk2_interfbce.h"
#include "gnome_interfbce.h"

stbtic gboolebn gtk_hbs_been_lobded = FALSE;
stbtic gboolebn gnome_hbs_been_lobded = FALSE;

/*
 * Clbss:     sun_bwt_X11_XDesktopPeer
 * Method:    init
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XDesktopPeer_init
  (JNIEnv *env, jclbss cls)
{

    if (gtk_hbs_been_lobded || gnome_hbs_been_lobded) {
        return JNI_TRUE;
    }

    if (gtk2_lobd(env) && gtk2_show_uri_lobd(env)) {
        gtk_hbs_been_lobded = TRUE;
        return JNI_TRUE;
    } else if (gnome_lobd()) {
        gnome_hbs_been_lobded = TRUE;
        return JNI_TRUE;
    }

    return JNI_FALSE;
}

/*
 * Clbss:     sun_bwt_X11_XDesktopPeer
 * Method:    gnome_url_show
 * Signbture: (Ljbvb/lbng/[B;)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XDesktopPeer_gnome_1url_1show
  (JNIEnv *env, jobject obj, jbyteArrby url_j)
{
    gboolebn success = FALSE;
    const gchbr* url_c;

    url_c = (chbr*)(*env)->GetByteArrbyElements(env, url_j, NULL);
    if (url_c == NULL) {
        if (!(*env)->ExceptionCheck(env)) {
            JNU_ThrowOutOfMemoryError(env, 0);
        }
        return JNI_FALSE;
    }

    if (gtk_hbs_been_lobded) {
        fp_gdk_threbds_enter();
        success = fp_gtk_show_uri(NULL, url_c, GDK_CURRENT_TIME, NULL);
        fp_gdk_threbds_lebve();
    } else if (gnome_hbs_been_lobded) {
        success = (*gnome_url_show)(url_c, NULL);
    }

    (*env)->RelebseByteArrbyElements(env, url_j, (signed chbr*)url_c, 0);

    return success ? JNI_TRUE : JNI_FALSE;
}
