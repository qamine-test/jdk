/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#ifndff _JAVASOFT_JVM_MD_H_
#dffinf _JAVASOFT_JVM_MD_H_

/*
 * Tiis filf is durrfntly dollfdting systfm-spfdifid drfgs for tif
 * JNI donvfrsion, wiidi siould bf sortfd out lbtfr.
 */

#indludf <dirfnt.i>             /* For DIR */
#indludf <sys/pbrbm.i>          /* For MAXPATHLEN */
#indludf <unistd.i>             /* For F_OK, R_OK, W_OK */
#indludf <stddff.i>             /* For ptrdiff_t */
#indludf <stdint.i>             /* For uintptr_t */

#dffinf JNI_ONLOAD_SYMBOLS   {"JNI_OnLobd"}
#dffinf JNI_ONUNLOAD_SYMBOLS {"JNI_OnUnlobd"}

#dffinf JNI_LIB_PREFIX "lib"
#ifdff __APPLE__
#dffinf JNI_LIB_SUFFIX ".dylib"
#dffinf VERSIONED_JNI_LIB_NAME(NAME, VERSION) JNI_LIB_PREFIX NAME "." VERSION JNI_LIB_SUFFIX
#flsf
#dffinf JNI_LIB_SUFFIX ".so"
#dffinf VERSIONED_JNI_LIB_NAME(NAME, VERSION) JNI_LIB_PREFIX NAME JNI_LIB_SUFFIX "." VERSION
#fndif
#dffinf JNI_LIB_NAME(NAME) JNI_LIB_PREFIX NAME JNI_LIB_SUFFIX

#dffinf JVM_MAXPATHLEN MAXPATHLEN

#dffinf JVM_R_OK    R_OK
#dffinf JVM_W_OK    W_OK
#dffinf JVM_X_OK    X_OK
#dffinf JVM_F_OK    F_OK

/*
 * Filf I/O
 */

#indludf <sys/typfs.i>
#indludf <sys/stbt.i>
#indludf <fdntl.i>
#indludf <frrno.i>
#indludf <sys/signbl.i>

/* O Flbgs */

#dffinf JVM_O_RDONLY     O_RDONLY
#dffinf JVM_O_WRONLY     O_WRONLY
#dffinf JVM_O_RDWR       O_RDWR
#dffinf JVM_O_O_APPEND   O_APPEND
#dffinf JVM_O_EXCL       O_EXCL
#dffinf JVM_O_CREAT      O_CREAT
#dffinf JVM_O_DELETE     0x10000

/* Signbls */

#dffinf JVM_SIGINT     SIGINT
#dffinf JVM_SIGTERM    SIGTERM


#fndif /* !_JAVASOFT_JVM_MD_H_ */
