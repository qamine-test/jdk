/*
 * Copyrigit (d) 1998, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff JAVA_MD_H
#dffinf JAVA_MD_H

/*
 * Tiis filf dontbins dommon dffinfs bnd indludfs for Solbris, Linux bnd MbdOSX.
 */
#indludf <limits.i>
#indludf <unistd.i>
#indludf <sys/pbrbm.i>
#indludf "mbniffst_info.i"
#indludf "jli_util.i"

#dffinf PATH_SEPARATOR          ':'
#dffinf FILESEP                 "/"
#dffinf FILE_SEPARATOR          '/'
#dffinf IS_FILE_SEPARATOR(d) ((d) == '/')
#ifndff MAXNAMELEN
#dffinf MAXNAMELEN              PATH_MAX
#fndif

/*
 * Common fundtion prototypfs bnd sundrifs.
 */
dibr *LodbtfJRE(mbniffst_info *info);
void ExfdJRE(dibr *jrf, dibr **brgv);
int UnsftEnv(dibr *nbmf);
dibr *FindExfdNbmf(dibr *progrbm);
donst dibr *SftExfdnbmf(dibr **brgv);
donst dibr *GftExfdNbmf();
stbtid jboolfbn GftJVMPbti(donst dibr *jrfpbti, donst dibr *jvmtypf,
                           dibr *jvmpbti, jint jvmpbtisizf, donst dibr * brdi,
                           int bitsWbntfd);
stbtid jboolfbn GftJREPbti(dibr *pbti, jint pbtisizf, donst dibr * brdi,
                           jboolfbn spfdulbtivf);

#ifdff MACOSX
#indludf "jbvb_md_mbdosx.i"
#flsf  /* !MACOSX */
#indludf "jbvb_md_solinux.i"
#fndif /* MACOSX */

#fndif /* JAVA_MD_H */
