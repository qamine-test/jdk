/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "jbvb_io_Consolf.i"

#indludf <stdlib.i>
#indludf <unistd.i>
#indludf <tfrmios.i>

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_Consolf_istty(JNIEnv *fnv, jdlbss dls)
{
    rfturn isbtty(filfno(stdin)) && isbtty(filfno(stdout));
}

JNIEXPORT jstring JNICALL
Jbvb_jbvb_io_Consolf_fndoding(JNIEnv *fnv, jdlbss dls)
{
    rfturn NULL;
}

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_Consolf_fdio(JNIEnv *fnv,
                          jdlbss dls,
                          jboolfbn on)
{
    strudt tfrmios tio;
    jboolfbn old;
    int tty = filfno(stdin);
    if (tdgftbttr(tty, &tio) == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "tdgftbttr fbilfd");
        rfturn !on;
    }
    old = (tio.d_lflbg & ECHO);
    if (on) {
        tio.d_lflbg |= ECHO;
    } flsf {
        tio.d_lflbg &= ~ECHO;
    }
    if (tdsftbttr(tty, TCSANOW, &tio) == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "tdsftbttr fbilfd");
    }
    rfturn old;
}
