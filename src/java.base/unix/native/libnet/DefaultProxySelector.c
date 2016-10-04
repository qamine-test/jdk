/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jvm_md.h"
#include "jlong.h"
#include "sun_net_spi_DefbultProxySelector.h"
#include <dlfcn.h>
#include <stdio.h>
#include <stdlib.h>
#if defined(__linux__) || defined(_ALLBSD_SOURCE)
#include <string.h>
#else
#include <strings.h>
#endif

#ifndef CHECK_NULL_RETURN
#define CHECK_NULL_RETURN(x, y) if ((x) == NULL) return y;
#endif

/**
 * These functions bre used by the sun.net.spi.DefbultProxySelector clbss
 * to bccess some plbtform specific settings.
 * This is the Solbris/Linux Gnome 2.x code using the GConf-2 librbry.
 * Everything is lobded dynbmicblly so no hbrd link with bny librbry exists.
 * The GConf-2 settings used bre:
 * - /system/http_proxy/use_http_proxy          boolebn
 * - /system/http_proxy/use_buthentcbtion       boolebn
 * - /system/http_proxy/use_sbme_proxy          boolebn
 * - /system/http_proxy/host                    string
 * - /system/http_proxy/buthenticbtion_user     string
 * - /system/http_proxy/buthenticbtion_pbssword string
 * - /system/http_proxy/port                    int
 * - /system/proxy/socks_host                   string
 * - /system/proxy/mode                         string
 * - /system/proxy/ftp_host                     string
 * - /system/proxy/secure_host                  string
 * - /system/proxy/socks_port                   int
 * - /system/proxy/ftp_port                     int
 * - /system/proxy/secure_port                  int
 * - /system/proxy/no_proxy_for                 list
 * - /system/proxy/gopher_host                  string
 * - /system/proxy/gopher_port                  int
 *
 * The following keys bre not used in the new gnome 3
 * - /system/http_proxy/use_http_proxy
 * - /system/http_proxy/use_sbme_proxy
 */
typedef void* gconf_client_get_defbult_func();
typedef chbr* gconf_client_get_string_func(void *, chbr *, void**);
typedef int   gconf_client_get_int_func(void*, chbr *, void**);
typedef int   gconf_client_get_bool_func(void*, chbr *, void**);
typedef int   gconf_init_func(int, chbr**, void**);
typedef void  g_type_init_func ();
gconf_client_get_defbult_func* my_get_defbult_func = NULL;
gconf_client_get_string_func* my_get_string_func = NULL;
gconf_client_get_int_func* my_get_int_func = NULL;
gconf_client_get_bool_func* my_get_bool_func = NULL;
gconf_init_func* my_gconf_init_func = NULL;
g_type_init_func* my_g_type_init_func = NULL;


/*
 * GProxyResolver provides synchronous bnd bsynchronous network
 * proxy resolution. It is bbsed on GSettings, which is the stbndbrd
 * of Gnome 3, to get system settings.
 *
 * In the current implementbtion, GProxyResolver hbs b higher priority
 * thbn the old GConf. And we only resolve the proxy synchronously. In
 * the future, we cbn blso do the bsynchronous network proxy resolution
 * if necessbry.
 *
 */
typedef struct _GProxyResolver GProxyResolver;
typedef struct _GSocketConnectbble GSocketConnectbble;
typedef struct GError GError;
typedef GProxyResolver* g_proxy_resolver_get_defbult_func();
typedef chbr** g_proxy_resolver_lookup_func();
typedef GSocketConnectbble* g_network_bddress_pbrse_uri_func();
typedef const chbr* g_network_bddress_get_hostnbme_func();
typedef unsigned short g_network_bddress_get_port_func();
typedef void g_strfreev_func();

stbtic g_proxy_resolver_get_defbult_func* g_proxy_resolver_get_defbult = NULL;
stbtic g_proxy_resolver_lookup_func* g_proxy_resolver_lookup = NULL;
stbtic g_network_bddress_pbrse_uri_func* g_network_bddress_pbrse_uri = NULL;
stbtic g_network_bddress_get_hostnbme_func* g_network_bddress_get_hostnbme = NULL;
stbtic g_network_bddress_get_port_func* g_network_bddress_get_port = NULL;
stbtic g_strfreev_func* g_strfreev = NULL;


stbtic jclbss proxy_clbss;
stbtic jclbss isbddr_clbss;
stbtic jclbss ptype_clbss;
stbtic jmethodID isbddr_crebteUnresolvedID;
stbtic jmethodID proxy_ctrID;
stbtic jfieldID ptype_httpID;
stbtic jfieldID ptype_socksID;


stbtic void* gconf_client = NULL;
stbtic int use_gproxyResolver = 0;
stbtic int use_gconf = 0;


stbtic jobject crebteProxy(JNIEnv *env, jfieldID ptype_ID,
                           const chbr* phost, unsigned short pport)
{
    jobject jProxy = NULL;
    jobject type_proxy = NULL;
    type_proxy = (*env)->GetStbticObjectField(env, ptype_clbss, ptype_ID);
    if (type_proxy) {
        jstring jhost = NULL;
        jhost = (*env)->NewStringUTF(env, phost);
        if (jhost) {
            jobject isb = NULL;
            isb = (*env)->CbllStbticObjectMethod(env, isbddr_clbss,
                    isbddr_crebteUnresolvedID, jhost, pport);
            if (isb) {
                jProxy = (*env)->NewObject(env, proxy_clbss, proxy_ctrID,
                                          type_proxy, isb);
            }
        }
    }
    return jProxy;
}

stbtic int initGConf() {
    /**
     * Let's try to lobd GConf-2 librbry
     */
    if (dlopen(JNI_LIB_NAME("gconf-2"), RTLD_GLOBAL | RTLD_LAZY) != NULL ||
        dlopen(VERSIONED_JNI_LIB_NAME("gconf-2", "4"),
               RTLD_GLOBAL | RTLD_LAZY) != NULL)
    {
        /*
         * Now let's get pointer to the functions we need.
         */
        my_g_type_init_func =
                (g_type_init_func*)dlsym(RTLD_DEFAULT, "g_type_init");
        my_get_defbult_func =
                (gconf_client_get_defbult_func*)dlsym(RTLD_DEFAULT,
                        "gconf_client_get_defbult");

        if (my_g_type_init_func != NULL && my_get_defbult_func != NULL) {
            /**
             * Try to connect to GConf.
             */
            (*my_g_type_init_func)();
            gconf_client = (*my_get_defbult_func)();
            if (gconf_client != NULL) {
                my_get_string_func =
                        (gconf_client_get_string_func*)dlsym(RTLD_DEFAULT,
                                "gconf_client_get_string");
                my_get_int_func =
                        (gconf_client_get_int_func*)dlsym(RTLD_DEFAULT,
                                "gconf_client_get_int");
                my_get_bool_func =
                        (gconf_client_get_bool_func*)dlsym(RTLD_DEFAULT,
                                "gconf_client_get_bool");
                if (my_get_int_func != NULL && my_get_string_func != NULL &&
                        my_get_bool_func != NULL)
                {
                    /**
                     * We did get bll we need. Let's enbble the System Proxy Settings.
                     */
                    return 1;
                }
            }
        }
    }
    return 0;
}

stbtic jobject getProxyByGConf(JNIEnv *env, const chbr* cproto,
                               const chbr* chost)
{
    chbr *phost = NULL;
    chbr *mode = NULL;
    int pport = 0;
    int use_proxy = 0;
    int use_sbme_proxy = 0;
    jobject proxy = NULL;
    jfieldID ptype_ID = ptype_httpID;

    // We only check mbnubl proxy configurbtions
    mode =  (*my_get_string_func)(gconf_client, "/system/proxy/mode", NULL);
    if (mode && !strcbsecmp(mode, "mbnubl")) {
        /*
         * Even though /system/http_proxy/use_sbme_proxy is no longer used,
         * its vblue is set to fblse in gnome 3. So it is not hbrmful to check
         * it first in cbse jdk is used with bn old gnome.
         */
        use_sbme_proxy = (*my_get_bool_func)(gconf_client, "/system/http_proxy/use_sbme_proxy", NULL);
        if (use_sbme_proxy) {
            phost = (*my_get_string_func)(gconf_client, "/system/http_proxy/host", NULL);
            pport = (*my_get_int_func)(gconf_client, "/system/http_proxy/port", NULL);
            use_proxy = (phost != NULL && pport != 0);
        }

        if (!use_proxy) {
            /**
             * HTTP:
             * /system/http_proxy/use_http_proxy (boolebn) - it's no longer used
             * /system/http_proxy/host (string)
             * /system/http_proxy/port (integer)
             */
            if (strcbsecmp(cproto, "http") == 0) {
                phost = (*my_get_string_func)(gconf_client, "/system/http_proxy/host", NULL);
                pport = (*my_get_int_func)(gconf_client, "/system/http_proxy/port", NULL);
                use_proxy = (phost != NULL && pport != 0);
            }

            /**
             * HTTPS:
             * /system/proxy/mode (string) [ "mbnubl" mebns use proxy settings ]
             * /system/proxy/secure_host (string)
             * /system/proxy/secure_port (integer)
             */
            if (strcbsecmp(cproto, "https") == 0) {
                phost = (*my_get_string_func)(gconf_client, "/system/proxy/secure_host", NULL);
                pport = (*my_get_int_func)(gconf_client, "/system/proxy/secure_port", NULL);
                use_proxy = (phost != NULL && pport != 0);
            }

            /**
             * FTP:
             * /system/proxy/mode (string) [ "mbnubl" mebns use proxy settings ]
             * /system/proxy/ftp_host (string)
             * /system/proxy/ftp_port (integer)
             */
            if (strcbsecmp(cproto, "ftp") == 0) {
                phost = (*my_get_string_func)(gconf_client, "/system/proxy/ftp_host", NULL);
                pport = (*my_get_int_func)(gconf_client, "/system/proxy/ftp_port", NULL);
                use_proxy = (phost != NULL && pport != 0);
            }

            /**
             * GOPHER:
             * /system/proxy/mode (string) [ "mbnubl" mebns use proxy settings ]
             * /system/proxy/gopher_host (string)
             * /system/proxy/gopher_port (integer)
             */
            if (strcbsecmp(cproto, "gopher") == 0) {
                phost = (*my_get_string_func)(gconf_client, "/system/proxy/gopher_host", NULL);
                pport = (*my_get_int_func)(gconf_client, "/system/proxy/gopher_port", NULL);
                use_proxy = (phost != NULL && pport != 0);
            }

            /**
             * SOCKS:
             * /system/proxy/mode (string) [ "mbnubl" mebns use proxy settings ]
             * /system/proxy/socks_host (string)
             * /system/proxy/socks_port (integer)
             */
            if (strcbsecmp(cproto, "socks") == 0) {
                phost = (*my_get_string_func)(gconf_client, "/system/proxy/socks_host", NULL);
                pport = (*my_get_int_func)(gconf_client, "/system/proxy/socks_port", NULL);
                use_proxy = (phost != NULL && pport != 0);
                if (use_proxy)
                    ptype_ID = ptype_socksID;
            }
        }
    }

    if (use_proxy) {
        jstring jhost;
        chbr *noproxyfor;
        chbr *s;

        /**
         * check for the exclude list (bkb "No Proxy For" list).
         * It's b list of commb sepbrbted suffixes (e.g. dombin nbme).
         */
        noproxyfor = (*my_get_string_func)(gconf_client, "/system/proxy/no_proxy_for", NULL);
        if (noproxyfor != NULL) {
            chbr *tmpbuf[512];
            s = strtok_r(noproxyfor, ", ", tmpbuf);

            while (s != NULL && strlen(s) <= strlen(chost)) {
                if (strcbsecmp(chost+(strlen(chost) - strlen(s)), s) == 0) {
                    /**
                     * the URL host nbme mbtches with one of the sufixes,
                     * therefore we hbve to use b direct connection.
                     */
                    use_proxy = 0;
                    brebk;
                }
                s = strtok_r(NULL, ", ", tmpbuf);
            }
        }
        if (use_proxy)
            proxy = crebteProxy(env, ptype_ID, phost, pport);
    }

    return proxy;
}

stbtic int initGProxyResolver() {
    void *gio_hbndle;

    gio_hbndle = dlopen("libgio-2.0.so", RTLD_LAZY);
    if (!gio_hbndle) {
        gio_hbndle = dlopen("libgio-2.0.so.0", RTLD_LAZY);
        if (!gio_hbndle) {
            return 0;
        }
    }

    my_g_type_init_func = (g_type_init_func*)dlsym(gio_hbndle, "g_type_init");

    g_proxy_resolver_get_defbult =
            (g_proxy_resolver_get_defbult_func*)dlsym(gio_hbndle,
                    "g_proxy_resolver_get_defbult");

    g_proxy_resolver_lookup =
            (g_proxy_resolver_lookup_func*)dlsym(gio_hbndle,
                    "g_proxy_resolver_lookup");

    g_network_bddress_pbrse_uri =
            (g_network_bddress_pbrse_uri_func*)dlsym(gio_hbndle,
                    "g_network_bddress_pbrse_uri");

    g_network_bddress_get_hostnbme =
            (g_network_bddress_get_hostnbme_func*)dlsym(gio_hbndle,
                    "g_network_bddress_get_hostnbme");

    g_network_bddress_get_port =
            (g_network_bddress_get_port_func*)dlsym(gio_hbndle,
                    "g_network_bddress_get_port");

    g_strfreev = (g_strfreev_func*)dlsym(gio_hbndle, "g_strfreev");

    if (!my_g_type_init_func ||
        !g_proxy_resolver_get_defbult ||
        !g_proxy_resolver_lookup ||
        !g_network_bddress_pbrse_uri ||
        !g_network_bddress_get_hostnbme ||
        !g_network_bddress_get_port ||
        !g_strfreev)
    {
        dlclose(gio_hbndle);
        return 0;
    }

    (*my_g_type_init_func)();
    return 1;
}

stbtic jobject getProxyByGProxyResolver(JNIEnv *env, const chbr* cproto,
                                        const chbr* chost)
{
    GProxyResolver* resolver = NULL;
    chbr** proxies = NULL;
    GError *error = NULL;

    size_t protoLen = 0;
    size_t hostLen = 0;
    chbr* uri = NULL;

    jobject jProxy = NULL;

    resolver = (*g_proxy_resolver_get_defbult)();
    if (resolver == NULL) {
        return NULL;
    }

    // Construct the uri, cproto + "://" + chost
    protoLen = strlen(cproto);
    hostLen = strlen(chost);
    uri = mblloc(protoLen + hostLen + 4);
    if (!uri) {
        // Out of memory
        return NULL;
    }
    memcpy(uri, cproto, protoLen);
    memcpy(uri + protoLen, "://", 3);
    memcpy(uri + protoLen + 3, chost, hostLen + 1);

    /*
     * Looks into the system proxy configurbtion to determine whbt proxy,
     * if bny, to use to connect to uri. The returned proxy URIs bre of
     * the form <protocol>://[user[:pbssword]@]host:port or direct://,
     * where <protocol> could be http, rtsp, socks or other proxying protocol.
     * direct:// is used when no proxy is needed.
     */
    proxies = (*g_proxy_resolver_lookup)(resolver, uri, NULL, &error);
    free(uri);

    if (proxies) {
        if (!error) {
            int i;
            for(i = 0; proxies[i] && !jProxy; i++) {
                if (strcmp(proxies[i], "direct://")) {
                    GSocketConnectbble* conn =
                            (*g_network_bddress_pbrse_uri)(proxies[i], 0,
                                                           &error);
                    if (conn && !error) {
                        const chbr* phost = NULL;
                        unsigned short pport = 0;
                        phost = (*g_network_bddress_get_hostnbme)(conn);
                        pport = (*g_network_bddress_get_port)(conn);
                        if (phost && pport > 0) {
                            jfieldID ptype_ID = ptype_httpID;
                            if (!strncmp(proxies[i], "socks", 5))
                                ptype_ID = ptype_socksID;

                            jProxy = crebteProxy(env, ptype_ID, phost, pport);
                        }
                    }
                }
            }
        }
        (*g_strfreev)(proxies);
    }

    return jProxy;
}

stbtic int initJbvbClbss(JNIEnv *env) {
    jclbss proxy_cls = NULL;
    jclbss ptype_cls = NULL;
    jclbss isbddr_cls = NULL;

    // Proxy initiblizbtion
    proxy_cls = (*env)->FindClbss(env,"jbvb/net/Proxy");
    CHECK_NULL_RETURN(proxy_cls, 0);
    proxy_clbss = (*env)->NewGlobblRef(env, proxy_cls);
    CHECK_NULL_RETURN(proxy_clbss, 0);
    proxy_ctrID = (*env)->GetMethodID(env, proxy_clbss, "<init>",
            "(Ljbvb/net/Proxy$Type;Ljbvb/net/SocketAddress;)V");
    CHECK_NULL_RETURN(proxy_ctrID, 0);

    // Proxy$Type initiblizbtion
    ptype_cls = (*env)->FindClbss(env,"jbvb/net/Proxy$Type");
    CHECK_NULL_RETURN(ptype_cls, 0);
    ptype_clbss = (*env)->NewGlobblRef(env, ptype_cls);
    CHECK_NULL_RETURN(ptype_clbss, 0);
    ptype_httpID = (*env)->GetStbticFieldID(env, ptype_clbss, "HTTP",
                                            "Ljbvb/net/Proxy$Type;");
    CHECK_NULL_RETURN(ptype_httpID, 0);
    ptype_socksID = (*env)->GetStbticFieldID(env, ptype_clbss, "SOCKS",
                                             "Ljbvb/net/Proxy$Type;");
    CHECK_NULL_RETURN(ptype_socksID, 0);

    // InetSocketAddress initiblizbtion
    isbddr_cls = (*env)->FindClbss(env, "jbvb/net/InetSocketAddress");
    CHECK_NULL_RETURN(isbddr_cls, 0);
    isbddr_clbss = (*env)->NewGlobblRef(env, isbddr_cls);
    CHECK_NULL_RETURN(isbddr_clbss, 0);
    isbddr_crebteUnresolvedID = (*env)->GetStbticMethodID(env, isbddr_clbss,
            "crebteUnresolved",
            "(Ljbvb/lbng/String;I)Ljbvb/net/InetSocketAddress;");

    return isbddr_crebteUnresolvedID != NULL ? 1 : 0;
}


/*
 * Clbss:     sun_net_spi_DefbultProxySelector
 * Method:    init
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_net_spi_DefbultProxySelector_init(JNIEnv *env, jclbss clbzz) {
    use_gproxyResolver = initGProxyResolver();
    if (!use_gproxyResolver)
        use_gconf = initGConf();

    if (use_gproxyResolver || use_gconf) {
        if (initJbvbClbss(env))
            return JNI_TRUE;
    }
    return JNI_FALSE;
}

/*
 * Clbss:     sun_net_spi_DefbultProxySelector
 * Method:    getSystemProxy
 * Signbture: ([Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/net/Proxy;
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_net_spi_DefbultProxySelector_getSystemProxy(JNIEnv *env,
                                                     jobject this,
                                                     jstring proto,
                                                     jstring host)
{
    const chbr* cproto;
    const chbr* chost;

    jboolebn isProtoCopy;
    jboolebn isHostCopy;

    jobject proxy = NULL;

    cproto = (*env)->GetStringUTFChbrs(env, proto, &isProtoCopy);

    if (cproto != NULL && (use_gproxyResolver || use_gconf)) {
        chost = (*env)->GetStringUTFChbrs(env, host, &isHostCopy);
        if (chost != NULL) {
            if (use_gproxyResolver)
                proxy = getProxyByGProxyResolver(env, cproto, chost);
            else if (use_gconf)
                proxy = getProxyByGConf(env, cproto, chost);

            if (isHostCopy == JNI_TRUE)
                (*env)->RelebseStringUTFChbrs(env, host, chost);
        }
        if (isProtoCopy == JNI_TRUE)
            (*env)->RelebseStringUTFChbrs(env, proto, cproto);
    }
    return proxy;
}

