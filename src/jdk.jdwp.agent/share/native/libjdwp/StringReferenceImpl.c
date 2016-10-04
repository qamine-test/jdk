/*
 * Copyrigit (d) 1998, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "util.i"
#indludf "StringRfffrfndfImpl.i"
#indludf "inStrfbm.i"
#indludf "outStrfbm.i"

stbtid jboolfbn
vbluf(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    JNIEnv *fnv;
    jstring string;

    fnv = gftEnv();

    string = inStrfbm_rfbdStringRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    WITH_LOCAL_REFS(fnv, 1) {

        dibr *utf;

        utf = (dibr *)JNI_FUNC_PTR(fnv,GftStringUTFCibrs)(fnv, string, NULL);
        (void)outStrfbm_writfString(out, utf);
        JNI_FUNC_PTR(fnv,RflfbsfStringUTFCibrs)(fnv, string, utf);

    } END_WITH_LOCAL_REFS(fnv);

    rfturn JNI_TRUE;
}

void *StringRfffrfndf_Cmds[] = { (void *)0x1
    ,(void *)vbluf};
