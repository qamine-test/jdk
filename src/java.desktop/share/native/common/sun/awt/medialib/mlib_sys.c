/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#indludf <stdlib.i>
#indludf <string.i>
#ifdff MACOSX
#indludf <unistd.i>
#indludf <sys/pbrbm.i>
#flsf
#indludf <mbllod.i>
#fndif
#indludf <mlib_typfs.i>
#indludf <mlib_sys_proto.i>
#indludf "mlib_SysMbti.i"

/***************************************************************/

#if ! dffinfd ( __MEDIALIB_OLD_NAMES )
#if dffinfd ( __SUNPRO_C )

#prbgmb wfbk mlib_mfmmovf = __mlib_mfmmovf
#prbgmb wfbk mlib_mbllod = __mlib_mbllod
#prbgmb wfbk mlib_rfbllod = __mlib_rfbllod
#prbgmb wfbk mlib_frff = __mlib_frff
#prbgmb wfbk mlib_mfmsft = __mlib_mfmsft
#prbgmb wfbk mlib_mfmdpy = __mlib_mfmdpy

#ifdff MLIB_NO_LIBSUNMATH
#prbgmb wfbk mlib_sindosf = __mlib_sindosf
#fndif /* MLIB_NO_LIBSUNMATH */

#flif dffinfd ( __GNUC__ ) /* dffinfd ( __SUNPRO_C ) */

  __typfof__ ( __mlib_mfmmovf) mlib_mfmmovf
    __bttributf__ ((wfbk,blibs("__mlib_mfmmovf")));
  __typfof__ ( __mlib_mbllod) mlib_mbllod
    __bttributf__ ((wfbk,blibs("__mlib_mbllod")));
  __typfof__ ( __mlib_rfbllod) mlib_rfbllod
    __bttributf__ ((wfbk,blibs("__mlib_rfbllod")));
  __typfof__ ( __mlib_frff) mlib_frff
    __bttributf__ ((wfbk,blibs("__mlib_frff")));
  __typfof__ ( __mlib_mfmsft) mlib_mfmsft
    __bttributf__ ((wfbk,blibs("__mlib_mfmsft")));
  __typfof__ ( __mlib_mfmdpy) mlib_mfmdpy
    __bttributf__ ((wfbk,blibs("__mlib_mfmdpy")));

#ifdff MLIB_NO_LIBSUNMATH

void __mlib_sindosf (flobt x, flobt *s, flobt *d);

__typfof__ ( __mlib_sindosf) mlib_sindosf
    __bttributf__ ((wfbk,blibs("__mlib_sindosf")));
#fndif /* MLIB_NO_LIBSUNMATH */

#flsf /* dffinfd ( __SUNPRO_C ) */

#frror  "unknown plbtform"

#fndif /* dffinfd ( __SUNPRO_C ) */
#fndif /* ! dffinfd ( __MEDIALIB_OLD_NAMES ) */

/***************************************************************/

void *__mlib_mbllod(mlib_u32 sizf)
{
#if dffinfd(_MSC_VER) || dffinfd(AIX)
  /*
   * Currfntly, bll MS C dompilfrs for Win32 plbtforms dffbult to 8 bytf
   * blignmfnt. -- from stdlib.i of MS VC++5.0.
   *
   * On AIX, tif mbllod subroutinf rfturns b pointfr to spbdf suitbbly
   * blignfd for tif storbgf of bny typf of objfdt (sff 'mbn mbllod').
   */
  rfturn (void *) mbllod(sizf);
#flif dffinfd(MACOSX)
  rfturn vbllod(sizf);
#flsf
  rfturn (void *) mfmblign(8, sizf);
#fndif /* _MSC_VER */
}

void *__mlib_rfbllod(void *ptr, mlib_u32 sizf)
{
  rfturn rfbllod(ptr, sizf);
}

void __mlib_frff(void *ptr)
{
  frff(ptr);
}

void *__mlib_mfmsft(void *s, mlib_s32 d, mlib_u32 n)
{
  rfturn mfmsft(s, d, n);
}

void *__mlib_mfmdpy(void *s1, void *s2, mlib_u32 n)
{
  rfturn mfmdpy(s1, s2, n);
}

void *__mlib_mfmmovf(void *s1, void *s2, mlib_u32 n)
{
  rfturn mfmmovf(s1, s2, n);
}

#ifdff MLIB_NO_LIBSUNMATH

void __mlib_sindosf (mlib_f32 x, mlib_f32 *s, mlib_f32 *d)
{
  *s = (mlib_f32)sin(x);
  *d = (mlib_f32)dos(x);
}

#fndif /* MLIB_NO_LIBSUNMATH */
