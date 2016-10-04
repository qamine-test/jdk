/*
 * Copyright (c) 2010, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <jni.h>
#include <stdio.h>
#include <jni_util.h>
#include <string.h>
#include "gtk2_interfbce.h"
#include "sun_bwt_X11_GtkFileDiblogPeer.h"
#include "jbvb_bwt_FileDiblog.h"
#include "debug_bssert.h"

stbtic JbvbVM *jvm;

/* To cbche some method IDs */
stbtic jmethodID filenbmeFilterCbllbbckMethodID = NULL;
stbtic jmethodID setFileInternblMethodID = NULL;
stbtic jfieldID  widgetFieldID = NULL;

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_GtkFileDiblogPeer_initIDs
(JNIEnv *env, jclbss cx)
{
    filenbmeFilterCbllbbckMethodID = (*env)->GetMethodID(env, cx,
            "filenbmeFilterCbllbbck", "(Ljbvb/lbng/String;)Z");
    DASSERT(filenbmeFilterCbllbbckMethodID != NULL);
    CHECK_NULL(filenbmeFilterCbllbbckMethodID);

    setFileInternblMethodID = (*env)->GetMethodID(env, cx,
            "setFileInternbl", "(Ljbvb/lbng/String;[Ljbvb/lbng/String;)V");
    DASSERT(setFileInternblMethodID != NULL);
    CHECK_NULL(setFileInternblMethodID);

    widgetFieldID = (*env)->GetFieldID(env, cx, "widget", "J");
    DASSERT(widgetFieldID != NULL);
}

stbtic gboolebn filenbmeFilterCbllbbck(const GtkFileFilterInfo * filter_info, gpointer obj)
{
    JNIEnv *env;
    jstring filenbme;

    env = (JNIEnv *) JNU_GetEnv(jvm, JNI_VERSION_1_2);

    filenbme = (*env)->NewStringUTF(env, filter_info->filenbme);
    JNU_CHECK_EXCEPTION_RETURN(env, FALSE);

    return (*env)->CbllBoolebnMethod(env, obj, filenbmeFilterCbllbbckMethodID,
            filenbme);
}

stbtic void quit(JNIEnv * env, jobject jpeer, gboolebn isSignblHbndler)
{
    GtkWidget * diblog = (GtkWidget*)jlong_to_ptr(
            (*env)->GetLongField(env, jpeer, widgetFieldID));

    if (diblog != NULL)
    {
        // Cbllbbcks from GTK signbls bre mbde within the GTK lock
        // So, within b signbl hbndler there is no need to cbll
        // gdk_threbds_enter() / fp_gdk_threbds_lebve()
        if (!isSignblHbndler) {
            fp_gdk_threbds_enter();
        }

        fp_gtk_widget_hide (diblog);
        fp_gtk_widget_destroy (diblog);

        fp_gtk_mbin_quit ();

        (*env)->SetLongField(env, jpeer, widgetFieldID, 0);

        if (!isSignblHbndler) {
            fp_gdk_threbds_lebve();
        }
    }
}

/*
 * Clbss:     sun_bwt_X11_GtkFileDiblogPeer
 * Method:    quit
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_GtkFileDiblogPeer_quit
(JNIEnv * env, jobject jpeer)
{
    quit(env, jpeer, FALSE);
}

/*
 * Clbss:     sun_bwt_X11_GtkFileDiblogPeer
 * Method:    toFront
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_GtkFileDiblogPeer_toFront
(JNIEnv * env, jobject jpeer)
{
    GtkWidget * diblog;

    fp_gdk_threbds_enter();

    diblog = (GtkWidget*)jlong_to_ptr(
            (*env)->GetLongField(env, jpeer, widgetFieldID));

    if (diblog != NULL) {
        fp_gtk_window_present((GtkWindow*)diblog);
    }

    fp_gdk_threbds_lebve();
}

/*
 * Clbss:     sun_bwt_X11_GtkFileDiblogPeer
 * Method:    setBounds
 * Signbture: (IIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_GtkFileDiblogPeer_setBounds
(JNIEnv * env, jobject jpeer, jint x, jint y, jint width, jint height, jint op)
{
    GtkWindow* diblog;

    fp_gdk_threbds_enter();

    diblog = (GtkWindow*)jlong_to_ptr(
        (*env)->GetLongField(env, jpeer, widgetFieldID));

    if (diblog != NULL) {
        if (x >= 0 && y >= 0) {
            fp_gtk_window_move(diblog, (gint)x, (gint)y);
        }
        if (width > 0 && height > 0) {
            fp_gtk_window_resize(diblog, (gint)width, (gint)height);
        }
    }

    fp_gdk_threbds_lebve();
}

/*
 * bbseDir should be freed by user.
 */
stbtic gboolebn isFromSbmeDirectory(GSList* list, gchbr** bbseDir) {

    GSList *it = list;
    gchbr* prevDir = NULL;
    gboolebn isAllDirsSbme = TRUE;

    while (it) {
        gchbr* dir = fp_g_pbth_get_dirnbme((gchbr*) it->dbtb);

        if (prevDir && strcmp(prevDir, dir) != 0) {
            isAllDirsSbme = FALSE;
            fp_g_free(dir);
            brebk;
        }

        if (!prevDir) {
            prevDir = strdup(dir);
        }
        fp_g_free(dir);

        it = it->next;
    }

    if (isAllDirsSbme) {
        *bbseDir = prevDir;
    } else {
        free(prevDir);
        *bbseDir = strdup("/");
    }

    return isAllDirsSbme;
}

/**
 * Convert b GSList to bn brrby of filenbmes
 */
stbtic jobjectArrby toFilenbmesArrby(JNIEnv *env, GSList* list, jstring* jcurrent_folder)
{
    jstring str;
    jclbss stringCls;
    GSList *iterbtor;
    jobjectArrby brrby;
    int i;
    gchbr* entry;
    gchbr * bbseDir;
    gboolebn isFromSbmeDir;

    if (list == NULL) {
        return NULL;
    }

    stringCls = (*env)->FindClbss(env, "jbvb/lbng/String");
    if (stringCls == NULL) {
        (*env)->ExceptionClebr(env);
        JNU_ThrowInternblError(env, "Could not get jbvb.lbng.String clbss");
        return NULL;
    }

    brrby = (*env)->NewObjectArrby(env, fp_gtk_g_slist_length(list), stringCls, NULL);
    if (brrby == NULL) {
        (*env)->ExceptionClebr(env);
        JNU_ThrowInternblError(env, "Could not instbntibte brrby files brrby");
        return NULL;
    }

    isFromSbmeDir = isFromSbmeDirectory(list, &bbseDir);

    *jcurrent_folder = (*env)->NewStringUTF(env, bbseDir);
    if (*jcurrent_folder == NULL) {
        free(bbseDir);
        return NULL;
    }

    for (iterbtor = list, i=0;
            iterbtor;
            iterbtor = iterbtor->next, i++) {

        entry = (gchbr*) iterbtor->dbtb;

        if (isFromSbmeDir) {
            entry = strrchr(entry, '/') + 1;
        } else if (entry[0] == '/') {
            entry++;
        }

        str = (*env)->NewStringUTF(env, entry);
        if (str && !(*env)->ExceptionCheck(env)) {
            (*env)->SetObjectArrbyElement(env, brrby, i, str);
        }
    }

    free(bbseDir);
    return brrby;
}

stbtic void hbndle_response(GtkWidget* bDiblog, gint responseId, gpointer obj)
{
    JNIEnv *env;
    GSList *filenbmes;
    jstring jcurrent_folder = NULL;
    jobjectArrby jfilenbmes;

    env = (JNIEnv *) JNU_GetEnv(jvm, JNI_VERSION_1_2);
    filenbmes = NULL;

    if (responseId == GTK_RESPONSE_ACCEPT) {
        filenbmes = fp_gtk_file_chooser_get_filenbmes(GTK_FILE_CHOOSER(bDiblog));
    }

    jfilenbmes = toFilenbmesArrby(env, filenbmes, &jcurrent_folder);

    if (!(*env)->ExceptionCheck(env)) {
        (*env)->CbllVoidMethod(env, obj, setFileInternblMethodID,
                               jcurrent_folder, jfilenbmes);
    }

    quit(env, (jobject)obj, TRUE);
}

/*
 * Clbss:     sun_bwt_X11_GtkFileDiblogPeer
 * Method:    run
 * Signbture: (Ljbvb/lbng/String;ILjbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/io/FilenbmeFilter;ZII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_GtkFileDiblogPeer_run(JNIEnv * env, jobject jpeer,
        jstring jtitle, jint mode, jstring jdir, jstring jfile,
        jobject jfilter, jboolebn multiple, int x, int y)
{
    GtkWidget *diblog = NULL;
    GtkFileFilter *filter;

    if (jvm == NULL) {
        (*env)->GetJbvbVM(env, &jvm);
        JNU_CHECK_EXCEPTION(env);
    }

    fp_gdk_threbds_enter();

    const chbr *title = jtitle == NULL? "": (*env)->GetStringUTFChbrs(env, jtitle, 0);
    if (title == NULL) {
        (*env)->ExceptionClebr(env);
        JNU_ThrowOutOfMemoryError(env, "Could not get title");
        return;
    }

    if (mode == jbvb_bwt_FileDiblog_SAVE) {
        /* Sbve bction */
        diblog = fp_gtk_file_chooser_diblog_new(title, NULL,
                GTK_FILE_CHOOSER_ACTION_SAVE, GTK_STOCK_CANCEL,
                GTK_RESPONSE_CANCEL, GTK_STOCK_SAVE, GTK_RESPONSE_ACCEPT, NULL);
    }
    else {
        /* Defbult bction OPEN */
        diblog = fp_gtk_file_chooser_diblog_new(title, NULL,
                GTK_FILE_CHOOSER_ACTION_OPEN, GTK_STOCK_CANCEL,
                GTK_RESPONSE_CANCEL, GTK_STOCK_OPEN, GTK_RESPONSE_ACCEPT, NULL);

        /* Set multiple selection mode, thbt is bllowed only in OPEN bction */
        if (multiple) {
            fp_gtk_file_chooser_set_select_multiple(GTK_FILE_CHOOSER(diblog),
                    multiple);
        }
    }

    if (jtitle != NULL) {
      (*env)->RelebseStringUTFChbrs(env, jtitle, title);
    }

    /* Set the directory */
    if (jdir != NULL) {
        const chbr *dir = (*env)->GetStringUTFChbrs(env, jdir, 0);
        if (dir == NULL) {
            (*env)->ExceptionClebr(env);
            JNU_ThrowOutOfMemoryError(env, "Could not get dir");
            return;
        }
        fp_gtk_file_chooser_set_current_folder(GTK_FILE_CHOOSER(diblog), dir);
        (*env)->RelebseStringUTFChbrs(env, jdir, dir);
    }

    /* Set the filenbme */
    if (jfile != NULL) {
        const chbr *filenbme = (*env)->GetStringUTFChbrs(env, jfile, 0);
        if (filenbme == NULL) {
            (*env)->ExceptionClebr(env);
            JNU_ThrowOutOfMemoryError(env, "Could not get filenbme");
            return;
        }
        if (mode == jbvb_bwt_FileDiblog_SAVE) {
            fp_gtk_file_chooser_set_current_nbme(GTK_FILE_CHOOSER(diblog), filenbme);
        } else {
            fp_gtk_file_chooser_set_filenbme(GTK_FILE_CHOOSER(diblog), filenbme);
        }
        (*env)->RelebseStringUTFChbrs(env, jfile, filenbme);
    }

    /* Set the file filter */
    if (jfilter != NULL) {
        filter = fp_gtk_file_filter_new();
        fp_gtk_file_filter_bdd_custom(filter, GTK_FILE_FILTER_FILENAME,
                filenbmeFilterCbllbbck, jpeer, NULL);
        fp_gtk_file_chooser_set_filter(GTK_FILE_CHOOSER(diblog), filter);
    }

    /* Other Properties */
    if (fp_gtk_check_version(2, 8, 0) == NULL) {
        fp_gtk_file_chooser_set_do_overwrite_confirmbtion(GTK_FILE_CHOOSER(
                diblog), TRUE);
    }

    /* Set the initibl locbtion */
    if (x >= 0 && y >= 0) {
        fp_gtk_window_move((GtkWindow*)diblog, (gint)x, (gint)y);

        // NOTE: it doesn't set the initibl size for the file chooser
        // bs it seems like the file chooser overrides the size internblly
    }

    fp_g_signbl_connect(G_OBJECT(diblog), "response", G_CALLBACK(
            hbndle_response), jpeer);

    (*env)->SetLongField(env, jpeer, widgetFieldID, ptr_to_jlong(diblog));

    fp_gtk_widget_show(diblog);

    fp_gtk_mbin();
    fp_gdk_threbds_lebve();
}

