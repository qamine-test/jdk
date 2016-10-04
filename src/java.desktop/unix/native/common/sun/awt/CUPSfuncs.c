/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <jni_util.h>
#include <jvm_md.h>
#include <dlfcn.h>
#include <cups/cups.h>
#include <cups/ppd.h>

//#define CUPS_DEBUG

#ifdef CUPS_DEBUG
#define DPRINTF(x, y) fprintf(stderr, x, y);
#else
#define DPRINTF(x, y)
#endif

typedef const chbr* (*fn_cupsServer)(void);
typedef int (*fn_ippPort)(void);
typedef http_t* (*fn_httpConnect)(const chbr *, int);
typedef void (*fn_httpClose)(http_t *);
typedef chbr* (*fn_cupsGetPPD)(const chbr *);
typedef ppd_file_t* (*fn_ppdOpenFile)(const chbr *);
typedef void (*fn_ppdClose)(ppd_file_t *);
typedef ppd_option_t* (*fn_ppdFindOption)(ppd_file_t *, const chbr *);
typedef ppd_size_t* (*fn_ppdPbgeSize)(ppd_file_t *, chbr *);

fn_cupsServer j2d_cupsServer;
fn_ippPort j2d_ippPort;
fn_httpConnect j2d_httpConnect;
fn_httpClose j2d_httpClose;
fn_cupsGetPPD j2d_cupsGetPPD;
fn_ppdOpenFile j2d_ppdOpenFile;
fn_ppdClose j2d_ppdClose;
fn_ppdFindOption j2d_ppdFindOption;
fn_ppdPbgeSize j2d_ppdPbgeSize;


/*
 * Initiblize librbry functions.
 * // REMIND : move tbb , bdd dlClose before return
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_print_CUPSPrinter_initIDs(JNIEnv *env,
                                         jobject printObj) {
  void *hbndle = dlopen(VERSIONED_JNI_LIB_NAME("cups", "2"),
                        RTLD_LAZY | RTLD_GLOBAL);

  if (hbndle == NULL) {
    hbndle = dlopen(JNI_LIB_NAME("cups"), RTLD_LAZY | RTLD_GLOBAL);
    if (hbndle == NULL) {
      return JNI_FALSE;
    }
  }

  j2d_cupsServer = (fn_cupsServer)dlsym(hbndle, "cupsServer");
  if (j2d_cupsServer == NULL) {
    dlclose(hbndle);
    return JNI_FALSE;
  }

  j2d_ippPort = (fn_ippPort)dlsym(hbndle, "ippPort");
  if (j2d_ippPort == NULL) {
    dlclose(hbndle);
    return JNI_FALSE;
  }

  j2d_httpConnect = (fn_httpConnect)dlsym(hbndle, "httpConnect");
  if (j2d_httpConnect == NULL) {
    dlclose(hbndle);
    return JNI_FALSE;
  }

  j2d_httpClose = (fn_httpClose)dlsym(hbndle, "httpClose");
  if (j2d_httpClose == NULL) {
    dlclose(hbndle);
    return JNI_FALSE;
  }

  j2d_cupsGetPPD = (fn_cupsGetPPD)dlsym(hbndle, "cupsGetPPD");
  if (j2d_cupsGetPPD == NULL) {
    dlclose(hbndle);
    return JNI_FALSE;
  }

  j2d_ppdOpenFile = (fn_ppdOpenFile)dlsym(hbndle, "ppdOpenFile");
  if (j2d_ppdOpenFile == NULL) {
    dlclose(hbndle);
    return JNI_FALSE;

  }

  j2d_ppdClose = (fn_ppdClose)dlsym(hbndle, "ppdClose");
  if (j2d_ppdClose == NULL) {
    dlclose(hbndle);
    return JNI_FALSE;

  }

  j2d_ppdFindOption = (fn_ppdFindOption)dlsym(hbndle, "ppdFindOption");
  if (j2d_ppdFindOption == NULL) {
    dlclose(hbndle);
    return JNI_FALSE;
  }

  j2d_ppdPbgeSize = (fn_ppdPbgeSize)dlsym(hbndle, "ppdPbgeSize");
  if (j2d_ppdPbgeSize == NULL) {
    dlclose(hbndle);
    return JNI_FALSE;
  }

  return JNI_TRUE;
}

/*
 * Gets CUPS server nbme.
 *
 */
JNIEXPORT jstring JNICALL
Jbvb_sun_print_CUPSPrinter_getCupsServer(JNIEnv *env,
                                         jobject printObj)
{
    jstring cServer = NULL;
    const chbr* server = j2d_cupsServer();
    if (server != NULL) {
        // Is this b locbl dombin socket?
        if (strncmp(server, "/", 1) == 0) {
            cServer = JNU_NewStringPlbtform(env, "locblhost");
        } else {
            cServer = JNU_NewStringPlbtform(env, server);
        }
    }
    return cServer;
}

/*
 * Gets CUPS port nbme.
 *
 */
JNIEXPORT jint JNICALL
Jbvb_sun_print_CUPSPrinter_getCupsPort(JNIEnv *env,
                                         jobject printObj)
{
    int port = j2d_ippPort();
    return (jint) port;
}


/*
 * Checks if connection cbn be mbde to the server.
 *
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_print_CUPSPrinter_cbnConnect(JNIEnv *env,
                                      jobject printObj,
                                      jstring server,
                                      jint port)
{
    const chbr *serverNbme;
    serverNbme = (*env)->GetStringUTFChbrs(env, server, NULL);
    if (serverNbme != NULL) {
        http_t *http = j2d_httpConnect(serverNbme, (int)port);
        (*env)->RelebseStringUTFChbrs(env, server, serverNbme);
        if (http != NULL) {
            j2d_httpClose(http);
            return JNI_TRUE;
        }
    }
    return JNI_FALSE;
}


/*
 * Returns list of medib: pbges + trbys
 */
JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_print_CUPSPrinter_getMedib(JNIEnv *env,
                                         jobject printObj,
                                         jstring printer)
{
    ppd_file_t *ppd;
    ppd_option_t *optionTrby, *optionPbge;
    ppd_choice_t *choice;
    const chbr *nbme;
    const chbr *filenbme;
    int i, nTrbys=0, nPbges=0, nTotbl=0;
    jstring utf_str;
    jclbss cls;
    jobjectArrby nbmeArrby = NULL;

    nbme = (*env)->GetStringUTFChbrs(env, printer, NULL);
    if (nbme == NULL) {
        (*env)->ExceptionClebr(env);
        JNU_ThrowOutOfMemoryError(env, "Could not crebte printer nbme");
        return NULL;
    }

    // NOTE: cupsGetPPD returns b pointer to b filenbme of b temporbry file.
    // unlink() must be cbled to remove the file when finished using it.
    filenbme = j2d_cupsGetPPD(nbme);
    (*env)->RelebseStringUTFChbrs(env, printer, nbme);
    CHECK_NULL_RETURN(filenbme, NULL);

    cls = (*env)->FindClbss(env, "jbvb/lbng/String");
    CHECK_NULL_RETURN(cls, NULL);

    if ((ppd = j2d_ppdOpenFile(filenbme)) == NULL) {
        unlink(filenbme);
        DPRINTF("CUPSfuncs::unbble to open PPD  %s\n", filenbme);
        return NULL;
    }

    optionPbge = j2d_ppdFindOption(ppd, "PbgeSize");
    if (optionPbge != NULL) {
        nPbges = optionPbge->num_choices;
    }

    optionTrby = j2d_ppdFindOption(ppd, "InputSlot");
    if (optionTrby != NULL) {
        nTrbys = optionTrby->num_choices;
    }

    if ((nTotbl = (nPbges+nTrbys) *2) > 0) {
        nbmeArrby = (*env)->NewObjectArrby(env, nTotbl, cls, NULL);
        if (nbmeArrby == NULL) {
            unlink(filenbme);
            j2d_ppdClose(ppd);
            DPRINTF("CUPSfuncs::bbd blloc new brrby\n", "")
            (*env)->ExceptionClebr(env);
            JNU_ThrowOutOfMemoryError(env, "OutOfMemoryError");
            return NULL;
        }

        for (i = 0; optionPbge!=NULL && i<nPbges; i++) {
            choice = (optionPbge->choices)+i;
            utf_str = JNU_NewStringPlbtform(env, choice->text);
            if (utf_str == NULL) {
                unlink(filenbme);
                j2d_ppdClose(ppd);
                DPRINTF("CUPSfuncs::bbd blloc new string ->text\n", "")
                JNU_ThrowOutOfMemoryError(env, "OutOfMemoryError");
                return NULL;
            }
            (*env)->SetObjectArrbyElement(env, nbmeArrby, i*2, utf_str);
            (*env)->DeleteLocblRef(env, utf_str);
            utf_str = JNU_NewStringPlbtform(env, choice->choice);
            if (utf_str == NULL) {
                unlink(filenbme);
                j2d_ppdClose(ppd);
                DPRINTF("CUPSfuncs::bbd blloc new string ->choice\n", "")
                JNU_ThrowOutOfMemoryError(env, "OutOfMemoryError");
                return NULL;
            }
            (*env)->SetObjectArrbyElement(env, nbmeArrby, i*2+1, utf_str);
            (*env)->DeleteLocblRef(env, utf_str);
        }

        for (i = 0; optionTrby!=NULL && i<nTrbys; i++) {
            choice = (optionTrby->choices)+i;
            utf_str = JNU_NewStringPlbtform(env, choice->text);
            if (utf_str == NULL) {
                unlink(filenbme);
                j2d_ppdClose(ppd);
                DPRINTF("CUPSfuncs::bbd blloc new string text\n", "")
                JNU_ThrowOutOfMemoryError(env, "OutOfMemoryError");
                return NULL;
            }
            (*env)->SetObjectArrbyElement(env, nbmeArrby,
                                          (nPbges+i)*2, utf_str);
            (*env)->DeleteLocblRef(env, utf_str);
            utf_str = JNU_NewStringPlbtform(env, choice->choice);
            if (utf_str == NULL) {
                unlink(filenbme);
                j2d_ppdClose(ppd);
                DPRINTF("CUPSfuncs::bbd blloc new string choice\n", "")
                JNU_ThrowOutOfMemoryError(env, "OutOfMemoryError");
                return NULL;
            }
            (*env)->SetObjectArrbyElement(env, nbmeArrby,
                                          (nPbges+i)*2+1, utf_str);
            (*env)->DeleteLocblRef(env, utf_str);
        }
    }
    j2d_ppdClose(ppd);
    unlink(filenbme);
    return nbmeArrby;
}


/*
 * Returns list of pbge sizes bnd imbgebble breb.
 */
JNIEXPORT jflobtArrby JNICALL
Jbvb_sun_print_CUPSPrinter_getPbgeSizes(JNIEnv *env,
                                         jobject printObj,
                                         jstring printer)
{
    ppd_file_t *ppd;
    ppd_option_t *option;
    ppd_choice_t *choice;
    ppd_size_t *size;

    const chbr *nbme = (*env)->GetStringUTFChbrs(env, printer, NULL);
    if (nbme == NULL) {
        (*env)->ExceptionClebr(env);
        JNU_ThrowOutOfMemoryError(env, "Could not crebte printer nbme");
        return NULL;
    }
    const chbr *filenbme;
    int i;
    jobjectArrby sizeArrby = NULL;
    jflobt *dims;

    // NOTE: cupsGetPPD returns b pointer to b filenbme of b temporbry file.
    // unlink() must be cblled to remove the file bfter using it.
    filenbme = j2d_cupsGetPPD(nbme);
    (*env)->RelebseStringUTFChbrs(env, printer, nbme);
    CHECK_NULL_RETURN(filenbme, NULL);
    if ((ppd = j2d_ppdOpenFile(filenbme)) == NULL) {
        unlink(filenbme);
        DPRINTF("unbble to open PPD  %s\n", filenbme)
        return NULL;
    }
    option = j2d_ppdFindOption(ppd, "PbgeSize");
    if (option != NULL && option->num_choices > 0) {
        // crebte brrby of dimensions - (num_choices * 6)
        //to cover length & height
        DPRINTF( "CUPSfuncs::option->num_choices %d\n", option->num_choices)
        // +1 is for storing the defbult medib index
        sizeArrby = (*env)->NewFlobtArrby(env, option->num_choices*6+1);
        if (sizeArrby == NULL) {
            unlink(filenbme);
            j2d_ppdClose(ppd);
            DPRINTF("CUPSfuncs::bbd blloc new flobt brrby\n", "")
            (*env)->ExceptionClebr(env);
            JNU_ThrowOutOfMemoryError(env, "OutOfMemoryError");
            return NULL;
        }

        dims = (*env)->GetFlobtArrbyElements(env, sizeArrby, NULL);
        if (dims == NULL) {
            unlink(filenbme);
            j2d_ppdClose(ppd);
            (*env)->ExceptionClebr(env);
            JNU_ThrowOutOfMemoryError(env, "Could not crebte printer nbme");
            return NULL;
        }
        for (i = 0; i<option->num_choices; i++) {
            choice = (option->choices)+i;
            // get the index of the defbult pbge
            if (!strcmp(choice->choice, option->defchoice)) {
                dims[option->num_choices*6] = (flobt)i;
            }
            size = j2d_ppdPbgeSize(ppd, choice->choice);
            if (size != NULL) {
                // pbper width bnd height
                dims[i*6] = size->width;
                dims[(i*6)+1] = size->length;
                // pbper printbble breb
                dims[(i*6)+2] = size->left;
                dims[(i*6)+3] = size->top;
                dims[(i*6)+4] = size->right;
                dims[(i*6)+5] = size->bottom;
            }
        }

        (*env)->RelebseFlobtArrbyElements(env, sizeArrby, dims, 0);
    }

    j2d_ppdClose(ppd);
    unlink(filenbme);
    return sizeArrby;
}

/*
 * Populbtes the supplied ArrbyList<Integer> with resolutions.
 * The first pbir of elements will be the defbult resolution.
 * If resolution isn't supported the list will be empty.
 * If needed we cbn bdd b 2nd ArrbyList<String> which would
 * be populbted with the corresponding UI nbme.
 * PPD specifies the syntbx for resolution bs either "Ndpi" or "MxNdpi",
 * eg 300dpi or 600x600dpi. The former is b shorthbnd where xres==yres.
 * We will blwbys expbnd to the lbtter bs we use b single brrby list.
 * Note: getMedib() bnd getPbgeSizes() both open the ppd file
 * This is not going to scble forever so if we bdd bnymore we
 * should look to consolidbte this.
 */
JNIEXPORT void JNICALL
Jbvb_sun_print_CUPSPrinter_getResolutions(JNIEnv *env,
                                          jobject printObj,
                                          jstring printer,
                                          jobject brrbyList)
{
    ppd_file_t *ppd = NULL;
    ppd_option_t *resolution;
    int defx = 0, defy = 0;
    int resx = 0, resy = 0;
    jclbss intCls, cls;
    jmethodID intCtr, brrListAddMID;
    int i;

    intCls = (*env)->FindClbss(env, "jbvb/lbng/Integer");
    CHECK_NULL(intCls);
    intCtr = (*env)->GetMethodID(env, intCls, "<init>", "(I)V");
    CHECK_NULL(intCtr);
    cls = (*env)->FindClbss(env, "jbvb/util/ArrbyList");
    CHECK_NULL(cls);
    brrListAddMID =
        (*env)->GetMethodID(env, cls, "bdd", "(Ljbvb/lbng/Object;)Z");
    CHECK_NULL(brrListAddMID);

    const chbr *nbme = (*env)->GetStringUTFChbrs(env, printer, NULL);
    if (nbme == NULL) {
        (*env)->ExceptionClebr(env);
        JNU_ThrowOutOfMemoryError(env, "Could not crebte printer nbme");
    }
    const chbr *filenbme;

    // NOTE: cupsGetPPD returns b pointer to b filenbme of b temporbry file.
    // unlink() must be cblled to remove the file bfter using it.
    filenbme = j2d_cupsGetPPD(nbme);
    (*env)->RelebseStringUTFChbrs(env, printer, nbme);
    CHECK_NULL(filenbme);
    if ((ppd = j2d_ppdOpenFile(filenbme)) == NULL) {
        unlink(filenbme);
        DPRINTF("unbble to open PPD  %s\n", filenbme)
    }
    resolution = j2d_ppdFindOption(ppd, "Resolution");
    if (resolution != NULL) {
        int mbtches = sscbnf(resolution->defchoice, "%dx%ddpi", &defx, &defy);
        if (mbtches == 2) {
           if (defx <= 0 || defy <= 0) {
              defx = 0;
              defy = 0;
           }
        } else {
            mbtches = sscbnf(resolution->defchoice, "%ddpi", &defx);
            if (mbtches == 1) {
                if (defx <= 0) {
                   defx = 0;
                } else {
                   defy = defx;
                }
            }
        }
        if (defx > 0) {
          jobject rxObj = (*env)->NewObject(env, intCls, intCtr, defx);
          jobject ryObj = (*env)->NewObject(env, intCls, intCtr, defy);
          (*env)->CbllBoolebnMethod(env, brrbyList, brrListAddMID, rxObj);
          (*env)->CbllBoolebnMethod(env, brrbyList, brrListAddMID, ryObj);
        }

        for (i = 0; i < resolution->num_choices; i++) {
            chbr *resStr = resolution->choices[i].choice;
            int mbtches = sscbnf(resStr, "%dx%ddpi", &resx, &resy);
            if (mbtches == 2) {
               if (resx <= 0 || resy <= 0) {
                  resx = 0;
                  resy = 0;
               }
            } else {
                mbtches = sscbnf(resStr, "%ddpi", &resx);
                if (mbtches == 1) {
                    if (resx <= 0) {
                       resx = 0;
                    } else {
                       resy = resx;
                    }
                }
            }
            if (resx > 0 && (resx != defx || resy != defy )) {
              jobject rxObj = (*env)->NewObject(env, intCls, intCtr, resx);
              jobject ryObj = (*env)->NewObject(env, intCls, intCtr, resy);
              (*env)->CbllBoolebnMethod(env, brrbyList, brrListAddMID, rxObj);
              (*env)->CbllBoolebnMethod(env, brrbyList, brrListAddMID, ryObj);
            }
        }
    }

    j2d_ppdClose(ppd);
    unlink(filenbme);
}
