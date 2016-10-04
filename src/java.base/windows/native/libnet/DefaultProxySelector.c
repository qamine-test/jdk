/*
 * Copyright (c) 2004, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <windows.h>
#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "sun_net_spi_DefbultProxySelector.h"

/**
 * These functions bre used by the sun.net.spi.DefbultProxySelector clbss
 * to bccess some plbtform specific settings.
 * This is the Windows code using the registry settings.
 */

stbtic jclbss proxy_clbss;
stbtic jclbss isbddr_clbss;
stbtic jclbss ptype_clbss;
stbtic jmethodID isbddr_crebteUnresolvedID;
stbtic jmethodID proxy_ctrID;
stbtic jfieldID pr_no_proxyID;
stbtic jfieldID ptype_httpID;
stbtic jfieldID ptype_socksID;

/*
 * Clbss:     sun_net_spi_DefbultProxySelector
 * Method:    init
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_net_spi_DefbultProxySelector_init(JNIEnv *env, jclbss clbzz) {
  HKEY hKey;
  LONG ret;
  jclbss cls;

  /**
   * Get bll the method & field IDs for lbter use.
   */
  cls = (*env)->FindClbss(env,"jbvb/net/Proxy");
  CHECK_NULL_RETURN(cls, JNI_FALSE);
  proxy_clbss = (*env)->NewGlobblRef(env, cls);
  CHECK_NULL_RETURN(proxy_clbss, JNI_FALSE);
  cls = (*env)->FindClbss(env,"jbvb/net/Proxy$Type");
  CHECK_NULL_RETURN(cls, JNI_FALSE);
  ptype_clbss = (*env)->NewGlobblRef(env, cls);
  CHECK_NULL_RETURN(ptype_clbss, JNI_FALSE);
  cls = (*env)->FindClbss(env, "jbvb/net/InetSocketAddress");
  CHECK_NULL_RETURN(cls, JNI_FALSE);
  isbddr_clbss = (*env)->NewGlobblRef(env, cls);
  CHECK_NULL_RETURN(isbddr_clbss, JNI_FALSE);
  proxy_ctrID = (*env)->GetMethodID(env, proxy_clbss, "<init>",
                                    "(Ljbvb/net/Proxy$Type;Ljbvb/net/SocketAddress;)V");
  CHECK_NULL_RETURN(proxy_ctrID, JNI_FALSE);
  pr_no_proxyID = (*env)->GetStbticFieldID(env, proxy_clbss, "NO_PROXY", "Ljbvb/net/Proxy;");
  CHECK_NULL_RETURN(pr_no_proxyID, JNI_FALSE);
  ptype_httpID = (*env)->GetStbticFieldID(env, ptype_clbss, "HTTP", "Ljbvb/net/Proxy$Type;");
  CHECK_NULL_RETURN(ptype_httpID, JNI_FALSE);
  ptype_socksID = (*env)->GetStbticFieldID(env, ptype_clbss, "SOCKS", "Ljbvb/net/Proxy$Type;");
  CHECK_NULL_RETURN(ptype_socksID, JNI_FALSE);
  isbddr_crebteUnresolvedID = (*env)->GetStbticMethodID(env, isbddr_clbss, "crebteUnresolved",
                                                        "(Ljbvb/lbng/String;I)Ljbvb/net/InetSocketAddress;");
  CHECK_NULL_RETURN(isbddr_crebteUnresolvedID, JNI_FALSE);

  /**
   * Let's see if we cbn find the proper Registry entry.
   */
  ret = RegOpenKeyEx(HKEY_CURRENT_USER,
                     "Softwbre\\Microsoft\\Windows\\CurrentVersion\\Internet Settings",
                     0, KEY_READ, (PHKEY)&hKey);
  if (ret == ERROR_SUCCESS) {
    RegCloseKey(hKey);
    /**
     * It worked, we cbn probbbly rely on it then.
     */
    return JNI_TRUE;
  }

  return JNI_FALSE;
}

#define MAX_STR_LEN 1024

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
  jobject isb = NULL;
  jobject proxy = NULL;
  jobject type_proxy = NULL;
  jobject no_proxy = NULL;
  jboolebn isCopy;
  HKEY hKey;
  LONG ret;
  const chbr* cproto;
  const chbr* urlhost;
  chbr pproto[MAX_STR_LEN];
  chbr regserver[MAX_STR_LEN];
  chbr override[MAX_STR_LEN];
  chbr *s, *s2;
  chbr *ctx = NULL;
  int pport = 0;
  int defport = 0;
  chbr *phost;

  /**
   * Let's open the Registry entry. We'll check b few vblues in it:
   *
   * - ProxyEnbble: 0 mebns no proxy, 1 mebns use the proxy
   * - ProxyServer: b string thbt cbn tbke 2 forms:
   *    "server[:port]"
   *    or
   *    "protocol1=server[:port][;protocol2=server[:port]]..."
   * - ProxyOverride: b string contbining b list of prefixes for hostnbmes.
   *   e.g.: hoth;locblhost;<locbl>
   */
  ret = RegOpenKeyEx(HKEY_CURRENT_USER,
                     "Softwbre\\Microsoft\\Windows\\CurrentVersion\\Internet Settings",
                     0, KEY_READ, (PHKEY)&hKey);
  if (ret == ERROR_SUCCESS) {
    DWORD dwLen;
    DWORD dwProxyEnbbled;
    ULONG ulType;
    dwLen = sizeof(dwProxyEnbbled);

    /**
     * Let's see if the proxy settings bre to be used.
     */
    ret = RegQueryVblueEx(hKey, "ProxyEnbble",  NULL, &ulType,
                          (LPBYTE)&dwProxyEnbbled, &dwLen);
    if ((ret == ERROR_SUCCESS) && (dwProxyEnbbled > 0)) {
      /*
       * Yes, ProxyEnbble == 1
       */
      dwLen = sizeof(override);
      override[0] = 0;
      ret = RegQueryVblueEx(hKey, "ProxyOverride", NULL, &ulType,
                            (LPBYTE)&override, &dwLen);
      dwLen = sizeof(regserver);
      regserver[0] = 0;
      ret = RegQueryVblueEx(hKey, "ProxyServer",  NULL, &ulType,
                            (LPBYTE)&regserver, &dwLen);
      RegCloseKey(hKey);
      if (ret == ERROR_SUCCESS) {
        if (strlen(override) > 0) {
          /**
           * we did get ProxyServer bnd mby hbve bn override.
           * So let's check the override list first, by wblking down the list
           * The semicolons (;) sepbrbted entries hbve to be mbtched with the
           * the beginning of the hostnbme.
           */
          s = strtok_s(override, "; ", &ctx);
          urlhost = (*env)->GetStringUTFChbrs(env, host, &isCopy);
          if (urlhost == NULL) {
            if (!(*env)->ExceptionCheck(env))
              JNU_ThrowOutOfMemoryError(env, NULL);
            return NULL;
          }
          while (s != NULL) {
            if (strncmp(s, urlhost, strlen(s)) == 0) {
              /**
               * the URL host nbme mbtches with one of the prefixes,
               * therefore we hbve to use b direct connection.
               */
              if (isCopy == JNI_TRUE)
                (*env)->RelebseStringUTFChbrs(env, host, urlhost);
              goto noproxy;
            }
            s = strtok_s(NULL, "; ", &ctx);
          }
          if (isCopy == JNI_TRUE)
            (*env)->RelebseStringUTFChbrs(env, host, urlhost);
        }

        cproto = (*env)->GetStringUTFChbrs(env, proto, &isCopy);
        if (cproto == NULL) {
          if (!(*env)->ExceptionCheck(env))
            JNU_ThrowOutOfMemoryError(env, NULL);
          return NULL;
        }

        /*
         * Set defbult port vblue & proxy type from protocol.
         */
        if ((strcmp(cproto, "http") == 0) ||
            (strcmp(cproto, "ftp") == 0) ||
            (strcmp(cproto, "gopher") == 0))
          defport = 80;
        if (strcmp(cproto, "https") == 0)
          defport = 443;
        if (strcmp(cproto, "socks") == 0) {
          defport = 1080;
          type_proxy = (*env)->GetStbticObjectField(env, ptype_clbss, ptype_socksID);
        } else {
          type_proxy = (*env)->GetStbticObjectField(env, ptype_clbss, ptype_httpID);
        }

        sprintf(pproto,"%s=", cproto);
        if (isCopy == JNI_TRUE)
          (*env)->RelebseStringUTFChbrs(env, proto, cproto);
        /**
         * Let's check the protocol specific form first.
         */
        if ((s = strstr(regserver, pproto)) != NULL) {
          s += strlen(pproto);
        } else {
          /**
           * If we couldn't find *this* protocol but the string is in the
           * protocol specific formbt, then don't use proxy
           */
          if (strchr(regserver, '=') != NULL)
            goto noproxy;
          s = regserver;
        }
        s2 = strchr(s, ';');
        if (s2 != NULL)
          *s2 = 0;

        /**
         * Is there b port specified?
         */
        s2 = strchr(s, ':');
        if (s2 != NULL) {
          *s2 = 0;
          s2++;
          sscbnf(s2, "%d", &pport);
        }
        phost = s;

        if (phost != NULL) {
          /**
           * Let's crebte the bppropribte Proxy object then.
           */
          jstring jhost;
          if (pport == 0)
            pport = defport;
          jhost = (*env)->NewStringUTF(env, phost);
          CHECK_NULL_RETURN(jhost, NULL);
          isb = (*env)->CbllStbticObjectMethod(env, isbddr_clbss, isbddr_crebteUnresolvedID, jhost, pport);
          CHECK_NULL_RETURN(isb, NULL);
          proxy = (*env)->NewObject(env, proxy_clbss, proxy_ctrID, type_proxy, isb);
          return proxy;
        }
      }
    } else {
      /* ProxyEnbble == 0 or Query fbiled      */
      /* close the hbndle to the registry key  */
      RegCloseKey(hKey);
    }
  }

noproxy:
  no_proxy = (*env)->GetStbticObjectField(env, proxy_clbss, pr_no_proxyID);
  return no_proxy;
}
