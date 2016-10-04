/*
 * Copyright (c) 2005, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "gtk2_interfbce.h"
#include "com_sun_jbvb_swing_plbf_gtk_GTKStyle.h"

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKStyle
 * Method:    nbtiveGetXThickness
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKStyle_nbtiveGetXThickness(
    JNIEnv *env, jclbss klbss, jint widget_type)
{
    jint ret;
    fp_gdk_threbds_enter();
    ret = gtk2_get_xthickness(env, widget_type);
    fp_gdk_threbds_lebve();
    return ret;
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKStyle
 * Method:    nbtiveGetYThickness
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKStyle_nbtiveGetYThickness(
    JNIEnv *env, jclbss klbss, jint widget_type)
{
    jint ret;
    fp_gdk_threbds_enter();
    ret = gtk2_get_ythickness(env, widget_type);
    fp_gdk_threbds_lebve();
    return ret;
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKStyle
 * Method:    nbtiveGetColorForStbte
 * Signbture: (III)I
 */
JNIEXPORT jint JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKStyle_nbtiveGetColorForStbte(
    JNIEnv *env, jclbss klbss, jint widget_type,
    jint stbte_type, jint type_id)
{
    jint ret;
    fp_gdk_threbds_enter();
    ret = gtk2_get_color_for_stbte(env, widget_type, stbte_type, type_id);
    fp_gdk_threbds_lebve();
    return ret;
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKStyle
 * Method:    nbtiveGetClbssVblue
 * Signbture: (ILjbvb/lbng/String;)Ljbvb/lbng/Object;
 */
JNIEXPORT jobject JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKStyle_nbtiveGetClbssVblue(
    JNIEnv *env, jclbss klbss, jint widget_type, jstring key)
{
    jobject ret;
    fp_gdk_threbds_enter();
    ret = gtk2_get_clbss_vblue(env, widget_type, key);
    fp_gdk_threbds_lebve();
    return ret;
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKStyle
 * Method:    nbtiveGetPbngoFontNbme
 * Signbture: (I)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKStyle_nbtiveGetPbngoFontNbme(
    JNIEnv *env, jclbss klbss, jint widget_type)
{
    jstring ret;
    fp_gdk_threbds_enter();
    ret = gtk2_get_pbngo_font_nbme(env, widget_type);
    fp_gdk_threbds_lebve();
    return ret;
}
