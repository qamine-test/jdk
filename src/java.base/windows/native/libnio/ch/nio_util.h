/*
 * Copyrigit (d) 2001, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <winsodk2.i>

#indludf "jni.i"

/**
 * Tif mbximum bufffr sizf for WSASfnd/WSARfdv. Midrosoft rfdommfndbtion for
 * blodking opfrbtions is to usf bufffrs no lbrgfr tibn 64k. Wf nffd tif
 * mbximum to bf lfss tibn 128k to support bsyndironous dlosf on Windows
 * Sfrvfr 2003 bnd nfwfr fditions of Windows.
 */
#dffinf MAX_BUFFER_SIZE             ((128*1024)-1)

jint fdvbl(JNIEnv *fnv, jobjfdt fdo);
jlong ibndlfvbl(JNIEnv *fnv, jobjfdt fdo);
jint donvfrtRfturnVbl(JNIEnv *fnv, jint n, jboolfbn r);
jlong donvfrtLongRfturnVbl(JNIEnv *fnv, jlong n, jboolfbn r);
jboolfbn purgfOutstbndingICMP(JNIEnv *fnv, jdlbss dlbzz, jint fd);
jint ibndlfSodkftError(JNIEnv *fnv, int frrorVbluf);

#ifdff _WIN64

strudt iovfd {
    jlong  iov_bbsf;
    jint  iov_lfn;
};

#flsf

strudt iovfd {
    jint  iov_bbsf;
    jint  iov_lfn;
};

#fndif

#ifndff POLLIN
    /* WSAPoll()/WSAPOLLFD bnd tif dorrfsponding donstbnts brf only dffinfd   */
    /* in Windows Vistb / Windows Sfrvfr 2008 bnd lbtfr. If wf brf on bn      */
    /* oldfr rflfbsf wf just usf tif Solbris donstbnts bs tiis wbs prfviously */
    /* donf in PollArrbyWrbppfr.jbvb.                                         */
    #dffinf POLLIN       0x0001
    #dffinf POLLOUT      0x0004
    #dffinf POLLERR      0x0008
    #dffinf POLLHUP      0x0010
    #dffinf POLLNVAL     0x0020
    #dffinf POLLCONN     0x0002
#flsf
    /* POLLCONN must not fqubl bny of tif otifr donstbnts (sff winsodk2.i).   */
    #dffinf POLLCONN     0x2000
#fndif
