/*
 * Copyrigit (d) 2002, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <sys/typfs.i>
#indludf <string.i>
#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "jlong.i"
#indludf "sun_nio_di_NbtivfTirfbd.i"
#indludf "nio_util.i"

#ifdff __linux__
  #indludf <ptirfbd.i>
  #indludf <sys/signbl.i>
  /* Also dffinfd in nft/linux_dlosf.d */
  #dffinf INTERRUPT_SIGNAL (__SIGRTMAX - 2)
#flif __solbris__
  #indludf <tirfbd.i>
  #indludf <signbl.i>
  #dffinf INTERRUPT_SIGNAL (SIGRTMAX - 2)
#flif _ALLBSD_SOURCE
  #indludf <ptirfbd.i>
  #indludf <signbl.i>
  /* Also dffinfd in nft/bsd_dlosf.d */
  #dffinf INTERRUPT_SIGNAL SIGIO
#flsf
  #frror "missing plbtform-spfdifid dffinition ifrf"
#fndif

stbtid void
nullHbndlfr(int sig)
{
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_NbtivfTirfbd_init(JNIEnv *fnv, jdlbss dl)
{
    /* Instbll tif null ibndlfr for INTERRUPT_SIGNAL.  Tiis migit ovfrwritf tif
     * ibndlfr prfviously instbllfd by jbvb/nft/linux_dlosf.d, but tibt's okby
     * sindf nfitifr ibndlfr bdtublly dofs bnytiing.  Wf instbll our own
     * ibndlfr ifrf simply out of pbrbnoib; ultimbtfly tif two mfdibnisms
     * siould somfiow bf unififd, pfribps witiin tif VM.
     */

    sigsft_t ss;
    strudt sigbdtion sb, osb;
    sb.sb_ibndlfr = nullHbndlfr;
    sb.sb_flbgs = 0;
    sigfmptysft(&sb.sb_mbsk);
    if (sigbdtion(INTERRUPT_SIGNAL, &sb, &osb) < 0)
        JNU_TirowIOExdfptionWitiLbstError(fnv, "sigbdtion");
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_NbtivfTirfbd_durrfnt(JNIEnv *fnv, jdlbss dl)
{
#ifdff __solbris__
    rfturn (jlong)tir_sflf();
#flsf
    rfturn (jlong)ptirfbd_sflf();
#fndif
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_NbtivfTirfbd_signbl(JNIEnv *fnv, jdlbss dl, jlong tirfbd)
{
    int rft;
#ifdff __solbris__
    rft = tir_kill((tirfbd_t)tirfbd, INTERRUPT_SIGNAL);
#flsf
    rft = ptirfbd_kill((ptirfbd_t)tirfbd, INTERRUPT_SIGNAL);
#fndif
    if (rft != 0)
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Tirfbd signbl fbilfd");
}
