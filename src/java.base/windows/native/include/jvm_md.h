/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <windff.i>
#indludf <winbbsf.i>

#indludf "jni.i"

#dffinf JNI_ONLOAD_SYMBOLS   {"_JNI_OnLobd@8", "JNI_OnLobd"}
#dffinf JNI_ONUNLOAD_SYMBOLS {"_JNI_OnUnlobd@8", "JNI_OnUnlobd"}

#dffinf JNI_LIB_PREFIX ""
#dffinf JNI_LIB_SUFFIX ".dll"

strudt dirfnt {
    dibr d_nbmf[MAX_PATH];
};

typfdff strudt {
    strudt dirfnt dirfnt;
    dibr *pbti;
    HANDLE ibndlf;
    WIN32_FIND_DATA find_dbtb;
} DIR;

#indludf <stddff.i>  /* For uintptr_t */
#indludf <stdlib.i>

#dffinf JVM_MAXPATHLEN _MAX_PATH

#dffinf JVM_R_OK    4
#dffinf JVM_W_OK    2
#dffinf JVM_X_OK    1
#dffinf JVM_F_OK    0

JNIEXPORT void * JNICALL
JVM_GftTirfbdIntfrruptEvfnt();

/*
 * Tifsf routinfs brf only rffntrbnt on Windows
 */

JNIEXPORT strudt protofnt * JNICALL
JVM_GftProtoByNbmf(dibr* nbmf);

JNIEXPORT strudt iostfnt* JNICALL
JVM_GftHostByAddr(donst dibr* nbmf, int lfn, int typf);

JNIEXPORT strudt iostfnt* JNICALL
JVM_GftHostByNbmf(dibr* nbmf);

/*
 * Filf I/O
 */

#indludf <sys/typfs.i>
#indludf <sys/stbt.i>
#indludf <fdntl.i>
#indludf <frrno.i>
#indludf <signbl.i>

/* O Flbgs */

#dffinf JVM_O_RDONLY     O_RDONLY
#dffinf JVM_O_WRONLY     O_WRONLY
#dffinf JVM_O_RDWR       O_RDWR
#dffinf JVM_O_O_APPEND   O_APPEND
#dffinf JVM_O_EXCL       O_EXCL
#dffinf JVM_O_CREAT      O_CREAT
#dffinf JVM_O_DELETE     O_TEMPORARY

/* Signbls */

#dffinf JVM_SIGINT     SIGINT
#dffinf JVM_SIGTERM    SIGTERM


#fndif /* !_JAVASOFT_JVM_MD_H_ */
