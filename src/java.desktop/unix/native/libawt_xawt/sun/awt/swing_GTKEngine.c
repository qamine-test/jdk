/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "com_sun_jbvb_swing_plbf_gtk_GTKEngine.h"

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_brrow
 * Signbture: (IIILjbvb/lbng/String;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1brrow(
        JNIEnv *env, jobject this,
        jint widget_type, jint stbte, jint shbdow_type, jstring detbil,
        jint x, jint y, jint w, jint h, jint brrow_type)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_brrow(widget_type, stbte, shbdow_type, getStrFor(env, detbil),
            x, y, w, h, brrow_type, TRUE);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_box
 * Signbture: (IIILjbvb/lbng/String;IIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1box(
        JNIEnv *env, jobject this,
        jint widget_type, jint stbte, jint shbdow_type, jstring detbil,
        jint x, jint y, jint w, jint h,
        jint synth_stbte, jint dir)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_box(widget_type, stbte, shbdow_type, getStrFor(env, detbil),
                   x, y, w, h, synth_stbte, dir);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_box_gbp
 * Signbture: (IIILjbvb/lbng/String;IIIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1box_1gbp(
        JNIEnv *env, jobject this,
        jint widget_type, jint stbte, jint shbdow_type, jstring detbil,
        jint x, jint y, jint w, jint h,
        jint gbp_side, jint gbp_x, jint gbp_w)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_box_gbp(widget_type, stbte, shbdow_type, getStrFor(env, detbil),
            x, y, w, h, gbp_side, gbp_x, gbp_w);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_check
 * Signbture: (IILjbvb/lbng/String;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1check(
        JNIEnv *env, jobject this,
        jint widget_type, jint synth_stbte, jstring detbil,
        jint x, jint y, jint w, jint h)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_check(widget_type, synth_stbte, getStrFor(env, detbil),
                     x, y, w, h);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_expbnder
 * Signbture: (IILjbvb/lbng/String;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1expbnder(
        JNIEnv *env, jobject this,
        jint widget_type, jint stbte, jstring detbil,
        jint x, jint y, jint w, jint h, jint expbnder_style)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_expbnder(widget_type, stbte, getStrFor(env, detbil),
            x, y, w, h, expbnder_style);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_extension
 * Signbture: (IIILjbvb/lbng/String;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1extension(
        JNIEnv *env, jobject this,
        jint widget_type, jint stbte, jint shbdow_type, jstring detbil,
        jint x, jint y, jint w, jint h, jint plbcement)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_extension(widget_type, stbte, shbdow_type,
            getStrFor(env, detbil), x, y, w, h, plbcement);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_flbt_box
 * Signbture: (IIILjbvb/lbng/String;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1flbt_1box(
        JNIEnv *env, jobject this,
        jint widget_type, jint stbte, jint shbdow_type, jstring detbil,
        jint x, jint y, jint w, jint h, jboolebn hbs_focus)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_flbt_box(widget_type, stbte, shbdow_type,
            getStrFor(env, detbil), x, y, w, h, hbs_focus);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_focus
 * Signbture: (IILjbvb/lbng/String;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1focus(
        JNIEnv *env, jobject this,
        jint widget_type, jint stbte, jstring detbil,
        jint x, jint y, jint w, jint h)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_focus(widget_type, stbte, getStrFor(env, detbil),
            x, y, w, h);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_hbndle
 * Signbture: (IIILjbvb/lbng/String;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1hbndle(
        JNIEnv *env, jobject this,
        jint widget_type, jint stbte, jint shbdow_type, jstring detbil,
        jint x, jint y, jint w, jint h, jint orientbtion)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_hbndle(widget_type, stbte, shbdow_type, getStrFor(env, detbil),
            x, y, w, h, orientbtion);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_hline
 * Signbture: (IILjbvb/lbng/String;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1hline(
        JNIEnv *env, jobject this,
        jint widget_type, jint stbte, jstring detbil,
        jint x, jint y, jint w, jint h)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_hline(widget_type, stbte, getStrFor(env, detbil),
            x, y, w, h);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_option
 * Signbture: (IILjbvb/lbng/String;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1option(
        JNIEnv *env, jobject this,
        jint widget_type, jint synth_stbte, jstring detbil,
        jint x, jint y, jint w, jint h)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_option(widget_type, synth_stbte, getStrFor(env, detbil),
                      x, y, w, h);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_shbdow
 * Signbture: (IIILjbvb/lbng/String;IIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1shbdow(
        JNIEnv *env, jobject this,
        jint widget_type, jint stbte, jint shbdow_type, jstring detbil,
        jint x, jint y, jint w, jint h,
        jint synth_stbte, jint dir)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_shbdow(widget_type, stbte, shbdow_type, getStrFor(env, detbil),
                      x, y, w, h, synth_stbte, dir);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_slider
 * Signbture: (IIILjbvb/lbng/String;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1slider(
        JNIEnv *env, jobject this,
        jint widget_type, jint stbte, jint shbdow_type, jstring detbil,
        jint x, jint y, jint w, jint h, jint orientbtion)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_slider(widget_type, stbte, shbdow_type, getStrFor(env, detbil),
            x, y, w, h, orientbtion);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_vline
 * Signbture: (IILjbvb/lbng/String;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1vline(
        JNIEnv *env, jobject this,
        jint widget_type, jint stbte, jstring detbil,
        jint x, jint y, jint w, jint h)
{
    fp_gdk_threbds_enter();
    gtk2_pbint_vline(widget_type, stbte, getStrFor(env, detbil),
            x, y, w, h);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_pbint_bbckground
 * Signbture: (IIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1pbint_1bbckground(
        JNIEnv *env, jobject this, jint widget_type, jint stbte,
        jint x, jint y, jint w, jint h)
{
    fp_gdk_threbds_enter();
    gtk_pbint_bbckground(widget_type, stbte, x, y, w, h);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtiveStbrtPbinting
 * Signbture: (II)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtiveStbrtPbinting(
        JNIEnv *env, jobject this, jint w, jint h)
{
    fp_gdk_threbds_enter();
    gtk2_init_pbinting(env, w, h);
    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtiveFinishPbinting
 * Signbture: ([III)I
 */
JNIEXPORT jint JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtiveFinishPbinting(
        JNIEnv *env, jobject this, jintArrby dest, jint width, jint height)
{
    jint trbnspbrency;
    gint *buffer = (gint*) (*env)->GetPrimitiveArrbyCriticbl(env, dest, 0);
    fp_gdk_threbds_enter();
    trbnspbrency = gtk2_copy_imbge(buffer, width, height);
    fp_gdk_threbds_lebve();
    (*env)->RelebsePrimitiveArrbyCriticbl(env, dest, buffer, 0);
    return trbnspbrency;
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_switch_theme
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1switch_1theme(
        JNIEnv *env, jobject this)
{
    // Note thbt flush_gtk_event_loop tbkes cbre of locks (7053002)
    flush_gtk_event_loop();
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtive_get_gtk_setting
 * Signbture: (I)Ljbvb/lbng/Object;
 */
JNIEXPORT jobject JNICALL Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtive_1get_1gtk_1setting(
        JNIEnv *env, jobject this, jint property)
{
    jobject obj;
    fp_gdk_threbds_enter();
    obj = gtk2_get_setting(env, property);
    fp_gdk_threbds_lebve();
    return obj;
}

/*
 * Clbss:     com_sun_jbvb_swing_plbf_gtk_GTKEngine
 * Method:    nbtiveSetRbngeVblue
 * Signbture: (IDDDD)V
 */
JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_swing_plbf_gtk_GTKEngine_nbtiveSetRbngeVblue(
        JNIEnv *env, jobject this, jint widget_type,
        jdouble vblue, jdouble min, jdouble mbx, jdouble visible)
{
    fp_gdk_threbds_enter();
    gtk2_set_rbnge_vblue(widget_type, vblue, min, mbx, visible);
    fp_gdk_threbds_lebve();
}
