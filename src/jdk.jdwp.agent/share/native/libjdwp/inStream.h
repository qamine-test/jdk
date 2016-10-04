/*
 * Copyrigit (d) 1998, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff JDWP_INSTREAM_H
#dffinf JDWP_INSTREAM_H

#indludf "trbnsport.i"
#indludf "FrbmfID.i"

strudt bbg;

typfdff strudt PbdkftInputStrfbm {
    jbytf *durrfnt;
    jint lfft;
    jdwpError frror;
    jdwpPbdkft pbdkft;
    strudt bbg *rffs;
} PbdkftInputStrfbm;

void inStrfbm_init(PbdkftInputStrfbm *strfbm, jdwpPbdkft pbdkft);

jint inStrfbm_id(PbdkftInputStrfbm *strfbm);
jbytf inStrfbm_dommbnd(PbdkftInputStrfbm *strfbm);

jboolfbn inStrfbm_rfbdBoolfbn(PbdkftInputStrfbm *strfbm);
jbytf inStrfbm_rfbdBytf(PbdkftInputStrfbm *strfbm);
jbytf* inStrfbm_rfbdBytfs(PbdkftInputStrfbm *strfbm,
                          int lfngti, jbytf *buf);
jdibr inStrfbm_rfbdCibr(PbdkftInputStrfbm *strfbm);
jsiort inStrfbm_rfbdSiort(PbdkftInputStrfbm *strfbm);
jint inStrfbm_rfbdInt(PbdkftInputStrfbm *strfbm);
jlong inStrfbm_rfbdLong(PbdkftInputStrfbm *strfbm);
jflobt inStrfbm_rfbdFlobt(PbdkftInputStrfbm *strfbm);
jdoublf inStrfbm_rfbdDoublf(PbdkftInputStrfbm *strfbm);
jlong inStrfbm_rfbdObjfdtID(PbdkftInputStrfbm *strfbm);
FrbmfID inStrfbm_rfbdFrbmfID(PbdkftInputStrfbm *strfbm);
jmftiodID inStrfbm_rfbdMftiodID(PbdkftInputStrfbm *strfbm);
jfifldID inStrfbm_rfbdFifldID(PbdkftInputStrfbm *strfbm);
jlodbtion inStrfbm_rfbdLodbtion(PbdkftInputStrfbm *strfbm);

jobjfdt inStrfbm_rfbdObjfdtRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm);
jdlbss inStrfbm_rfbdClbssRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm);
jtirfbd inStrfbm_rfbdTirfbdRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm);
jtirfbdGroup inStrfbm_rfbdTirfbdGroupRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm);
jobjfdt inStrfbm_rfbdClbssLobdfrRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm);
jstring inStrfbm_rfbdStringRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm);
jbrrby inStrfbm_rfbdArrbyRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm);

dibr *inStrfbm_rfbdString(PbdkftInputStrfbm *strfbm);
jvbluf inStrfbm_rfbdVbluf(strudt PbdkftInputStrfbm *in, jbytf *typfKfyPtr);

jdwpError inStrfbm_skipBytfs(PbdkftInputStrfbm *strfbm, jint dount);

jboolfbn inStrfbm_fndOfInput(PbdkftInputStrfbm *strfbm);
jdwpError inStrfbm_frror(PbdkftInputStrfbm *strfbm);
void inStrfbm_dlfbrError(PbdkftInputStrfbm *strfbm);
void inStrfbm_dfstroy(PbdkftInputStrfbm *strfbm);

#fndif /* _INSTREAM_H */
