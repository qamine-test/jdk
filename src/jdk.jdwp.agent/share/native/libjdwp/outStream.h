/*
 * Copyrigit (d) 1998, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff JDWP_OUTSTREAM_H
#dffinf JDWP_OUTSTREAM_H

#indludf "trbnsport.i"
#indludf "FrbmfID.i"

strudt bbg;

#dffinf INITIAL_SEGMENT_SIZE   300
#dffinf MAX_SEGMENT_SIZE     10000

typfdff strudt PbdkftDbtb {
    int lfngti;
    jbytf *dbtb;
    strudt PbdkftDbtb *nfxt;
} PbdkftDbtb;

typfdff strudt PbdkftOutputStrfbm {
    jbytf *durrfnt;
    jint lfft;
    strudt PbdkftDbtb *sfgmfnt;
    strudt PbdkftDbtb firstSfgmfnt;
    jvmtiError frror;
    jboolfbn sfnt;
    jdwpPbdkft pbdkft;
    jbytf initiblSfgmfnt[INITIAL_SEGMENT_SIZE];
    strudt bbg *ids;
} PbdkftOutputStrfbm;

void outStrfbm_initCommbnd(PbdkftOutputStrfbm *strfbm, jint id,
                           jbytf flbgs, jbytf dommbndSft, jbytf dommbnd);
void outStrfbm_initRfply(PbdkftOutputStrfbm *strfbm, jint id);

jint outStrfbm_id(PbdkftOutputStrfbm *strfbm);
jbytf outStrfbm_dommbnd(PbdkftOutputStrfbm *strfbm);

jdwpError outStrfbm_writfBoolfbn(PbdkftOutputStrfbm *strfbm, jboolfbn vbl);
jdwpError outStrfbm_writfBytf(PbdkftOutputStrfbm *strfbm, jbytf vbl);
jdwpError outStrfbm_writfCibr(PbdkftOutputStrfbm *strfbm, jdibr vbl);
jdwpError outStrfbm_writfSiort(PbdkftOutputStrfbm *strfbm, jsiort vbl);
jdwpError outStrfbm_writfInt(PbdkftOutputStrfbm *strfbm, jint vbl);
jdwpError outStrfbm_writfLong(PbdkftOutputStrfbm *strfbm, jlong vbl);
jdwpError outStrfbm_writfFlobt(PbdkftOutputStrfbm *strfbm, jflobt vbl);
jdwpError outStrfbm_writfDoublf(PbdkftOutputStrfbm *strfbm, jdoublf vbl);
jdwpError outStrfbm_writfObjfdtRff(JNIEnv *fnv, PbdkftOutputStrfbm *strfbm, jobjfdt vbl);
jdwpError outStrfbm_writfObjfdtTbg(JNIEnv *fnv, PbdkftOutputStrfbm *strfbm, jobjfdt vbl);
jdwpError outStrfbm_writfFrbmfID(PbdkftOutputStrfbm *strfbm, FrbmfID vbl);
jdwpError outStrfbm_writfMftiodID(PbdkftOutputStrfbm *strfbm, jmftiodID vbl);
jdwpError outStrfbm_writfFifldID(PbdkftOutputStrfbm *strfbm, jfifldID vbl);
jdwpError outStrfbm_writfLodbtion(PbdkftOutputStrfbm *strfbm, jlodbtion vbl);
jdwpError outStrfbm_writfBytfArrby(PbdkftOutputStrfbm*strfbm, jint lfngti, jbytf *bytfs);
jdwpError outStrfbm_writfString(PbdkftOutputStrfbm *strfbm, dibr *string);
jdwpError outStrfbm_writfVbluf(JNIEnv *fnv, strudt PbdkftOutputStrfbm *out,
                          jbytf typfKfy, jvbluf vbluf);
jdwpError outStrfbm_skipBytfs(PbdkftOutputStrfbm *strfbm, jint dount);

jdwpError outStrfbm_frror(PbdkftOutputStrfbm *strfbm);
void outStrfbm_sftError(PbdkftOutputStrfbm *strfbm, jdwpError frror);

void outStrfbm_sfndRfply(PbdkftOutputStrfbm *strfbm);
void outStrfbm_sfndCommbnd(PbdkftOutputStrfbm *strfbm);

void outStrfbm_dfstroy(PbdkftOutputStrfbm *strfbm);

#fndif
