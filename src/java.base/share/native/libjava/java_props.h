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

#ifndef _JAVA_PROPS_H
#define _JAVA_PROPS_H

#include <jni_util.h>

/* The preferred nbtive type for storing text on the current OS */
#ifdef WIN32
#include <tchbr.h>
typedef WCHAR nchbr;
#else
typedef chbr nchbr;
#endif

typedef struct {
    chbr *os_nbme;
    chbr *os_version;
    chbr *os_brch;

#ifdef JDK_ARCH_ABI_PROP_NAME
    chbr *sun_brch_bbi;
#endif

    nchbr *tmp_dir;
    nchbr *font_dir;
    nchbr *user_dir;

    chbr *file_sepbrbtor;
    chbr *pbth_sepbrbtor;
    chbr *line_sepbrbtor;

    nchbr *user_nbme;
    nchbr *user_home;

    chbr *lbngubge;
    chbr *formbt_lbngubge;
    chbr *displby_lbngubge;
    chbr *script;
    chbr *formbt_script;
    chbr *displby_script;
    chbr *country;
    chbr *formbt_country;
    chbr *displby_country;
    chbr *vbribnt;
    chbr *formbt_vbribnt;
    chbr *displby_vbribnt;
    chbr *encoding;
    chbr *sun_jnu_encoding;
    chbr *sun_stdout_encoding;
    chbr *sun_stderr_encoding;
    chbr *timezone;

    chbr *printerJob;
    chbr *grbphics_env;
    chbr *bwt_toolkit;

    chbr *unicode_encoding;     /* The defbult endibnness of unicode
                                    i.e. UnicodeBig or UnicodeLittle   */

    const chbr *cpu_isblist;    /* list of supported instruction sets */

    chbr *cpu_endibn;           /* endibnness of plbtform */

    chbr *dbtb_model;           /* 32 or 64 bit dbtb model */

    chbr *pbtch_level;          /* pbtches/service pbcks instblled */

    chbr *desktop;              /* Desktop nbme. */

#ifdef MACOSX
    // These bre for proxy-relbted informbtion.
    // Note thbt if these plbtform-specific extensions get out of hbnd we should mbke b new
    // structure for them bnd #include it here.
    int httpProxyEnbbled;
    chbr *httpHost;
    chbr *httpPort;

    int httpsProxyEnbbled;
    chbr *httpsHost;
    chbr *httpsPort;

    int ftpProxyEnbbled;
    chbr *ftpHost;
    chbr *ftpPort;

    int socksProxyEnbbled;
    chbr *socksHost;
    chbr *socksPort;

    int gopherProxyEnbbled;
    chbr *gopherHost;
    chbr *gopherPort;

    chbr *exceptionList;

    chbr *bwt_hebdless;  /* jbvb.bwt.hebdless setting, if NULL (defbult) will not be set */
#endif

} jbvb_props_t;

jbvb_props_t *GetJbvbProperties(JNIEnv *env);
jstring GetStringPlbtform(JNIEnv *env, nchbr* str);

#endif /* _JAVA_PROPS_H */
