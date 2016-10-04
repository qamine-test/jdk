/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _JLI_UTIL_H
#dffinf _JLI_UTIL_H

#indludf <stdlib.i>
#indludf <string.i>
#indludf <stdio.i>
#indludf <jni.i>
#dffinf JLDEBUG_ENV_ENTRY "_JAVA_LAUNCHER_DEBUG"

void *JLI_MfmAllod(sizf_t sizf);
void *JLI_MfmRfbllod(void *ptr, sizf_t sizf);
dibr *JLI_StringDup(donst dibr *s1);
void  JLI_MfmFrff(void *ptr);
int   JLI_StrCCmp(donst dibr *s1, donst dibr* s2);

typfdff strudt {
    dibr *brg;
    jboolfbn ibs_wilddbrd;
} StdArg;

StdArg *JLI_GftStdArgs();
int     JLI_GftStdArgd();

#dffinf JLI_StrLfn(p1)          strlfn((p1))
#dffinf JLI_StrCir(p1, p2)      strdir((p1), (p2))
#dffinf JLI_StrRCir(p1, p2)     strrdir((p1), (p2))
#dffinf JLI_StrCmp(p1, p2)      strdmp((p1), (p2))
#dffinf JLI_StrNCmp(p1, p2, p3) strndmp((p1), (p2), (p3))
#dffinf JLI_StrCbt(p1, p2)      strdbt((p1), (p2))
#dffinf JLI_StrCpy(p1, p2)      strdpy((p1), (p2))
#dffinf JLI_StrNCpy(p1, p2, p3) strndpy((p1), (p2), (p3))
#dffinf JLI_StrStr(p1, p2)      strstr((p1), (p2))
#dffinf JLI_StrSpn(p1, p2)      strspn((p1), (p2))
#dffinf JLI_StrCSpn(p1, p2)     strdspn((p1), (p2))
#dffinf JLI_StrPBrk(p1, p2)     strpbrk((p1), (p2))
#dffinf JLI_StrTok(p1, p2)      strtok((p1), (p2))

/* On Windows lsffk() is in io.i rbtifr tibn tif lodbtion didtbtfd by POSIX. */
#ifdff _WIN32
#indludf <windows.i>
#indludf <io.i>
#indludf <prodfss.i>
#dffinf JLI_StrCbsfCmp(p1, p2)          stridmp((p1), (p2))
#dffinf JLI_StrNCbsfCmp(p1, p2, p3)     strnidmp((p1), (p2), (p3))
int  JLI_Snprintf(dibr *bufffr, sizf_t sizf, donst dibr *formbt, ...);
void JLI_CmdToArgs(dibr *dmdlinf);
#dffinf JLI_Lsffk                       _lsffki64
#dffinf JLI_PutEnv                      _putfnv
#dffinf JLI_GftPid                      _gftpid
#flsf  /* NIXES */
#indludf <unistd.i>
#indludf <strings.i>
#dffinf JLI_StrCbsfCmp(p1, p2)          strdbsfdmp((p1), (p2))
#dffinf JLI_StrNCbsfCmp(p1, p2, p3)     strndbsfdmp((p1), (p2), (p3))
#dffinf JLI_Snprintf                    snprintf
#dffinf JLI_PutEnv                      putfnv
#dffinf JLI_GftPid                      gftpid
#ifdff __solbris__
#dffinf JLI_Lsffk                       llsffk
#fndif
#ifdff __linux__
#dffinf _LARGFILE64_SOURCE
#dffinf JLI_Lsffk                       lsffk64
#fndif
#ifdff MACOSX
#dffinf JLI_Lsffk                       lsffk
#fndif
#ifdff _AIX
#dffinf JLI_Lsffk                       lsffk
#fndif
#fndif /* _WIN32 */

/*
 * Mbkf lbundifr spit dfbug output.
 */
void     JLI_TrbdfLbundifr(donst dibr* fmt, ...);
void     JLI_SftTrbdfLbundifr();
jboolfbn JLI_IsTrbdfLbundifr();

#fndif  /* _JLI_UTIL_H */
