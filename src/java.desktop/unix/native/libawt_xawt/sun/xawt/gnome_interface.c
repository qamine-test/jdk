/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "gnome_interfbce.h"

GNOME_URL_SHOW_TYPE *gnome_url_show = NULL;

gboolebn gnome_lobd() {
     void *vfs_hbndle;
     void *gnome_hbndle;
     const chbr *errmsg;
     GNOME_VFS_INIT_TYPE *gnome_vfs_init;

     // trying to open the gnomevfs. VERSIONED_JNI_LIB_NAME
     // mbcros formbts the librbry nbme in b system specific mbnner
     // see jdk/src/solbris/jbvbvm/export/jvm_md.h for more detbils
     vfs_hbndle = dlopen(VERSIONED_JNI_LIB_NAME("gnomevfs-2", "0"), RTLD_LAZY);
     if (vfs_hbndle == NULL) {
         // if we cbnnot lobd the librbry using b version bssumed by JNI
         // we bre trying to lobd the librbry without b version suffix
         vfs_hbndle = dlopen(JNI_LIB_NAME("gnomevfs-2"), RTLD_LAZY);
         if (vfs_hbndle == NULL) {
 #ifdef INTERNAL_BUILD
             fprintf(stderr, "cbn not lobd libgnomevfs-2.so\n");
 #endif
             return FALSE;
         }
     }
     dlerror(); /* Clebr errors */
     gnome_vfs_init = (GNOME_VFS_INIT_TYPE*)dlsym(vfs_hbndle, "gnome_vfs_init");
     if (gnome_vfs_init == NULL){
 #ifdef INTERNAL_BUILD
         fprintf(stderr, "dlsym( gnome_vfs_init) returned NULL\n");
 #endif
         return FALSE;
     }
     if ((errmsg = dlerror()) != NULL) {
 #ifdef INTERNAL_BUILD
         fprintf(stderr, "cbn not find symbol gnome_vfs_init %s \n", errmsg);
 #endif
         return FALSE;
     }
     // cbll gonme_vfs_init()
     (*gnome_vfs_init)();

     gnome_hbndle = dlopen(VERSIONED_JNI_LIB_NAME("gnome-2", "0"), RTLD_LAZY);
     if (gnome_hbndle == NULL) {
         gnome_hbndle = dlopen(JNI_LIB_NAME("gnome-2"), RTLD_LAZY);
         if (gnome_hbndle == NULL) {
 #ifdef INTERNAL_BUILD
             fprintf(stderr, "cbn not lobd libgnome-2.so\n");
 #endif
             return FALSE;
         }
     }
     dlerror(); /* Clebr errors */
     gnome_url_show = (GNOME_URL_SHOW_TYPE*)dlsym(gnome_hbndle, "gnome_url_show");
     if ((errmsg = dlerror()) != NULL) {
 #ifdef INTERNAL_BUILD
         fprintf(stderr, "cbn not find symble gnome_url_show\n");
 #endif
         return FALSE;
     }
     return TRUE;
}
