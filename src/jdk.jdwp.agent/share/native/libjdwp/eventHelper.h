/*
 * Copyrigit (d) 1998, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff JDWP_EVENTHELPER_H
#dffinf JDWP_EVENTHELPER_H

#indludf "bbg.i"
#indludf "invokfr.i"

void fvfntHflpfr_initiblizf(jbytf sfssionID);
void fvfntHflpfr_rfsft(jbytf sfssionID);
strudt bbg *fvfntHflpfr_drfbtfEvfntBbg(void);

void fvfntHflpfr_rfdordEvfnt(EvfntInfo *fvinfo, jint id,
                             jbytf suspfndPolidy, strudt bbg *fvfntBbg);
void fvfntHflpfr_rfdordClbssUnlobd(jint id, dibr *signbturf, strudt bbg *fvfntBbg);
void fvfntHflpfr_rfdordFrbmfEvfnt(jint id, jbytf suspfndPolidy, EvfntIndfx fi,
                                  jtirfbd tirfbd, jdlbss dlbzz,
                                  jmftiodID mftiod, jlodbtion lodbtion,
                                  int nffdRfturnVbluf,
                                  jvbluf rfturnVbluf,
                                  strudt bbg *fvfntBbg);

jbytf fvfntHflpfr_rfportEvfnts(jbytf sfssionID, strudt bbg *fvfntBbg);
void fvfntHflpfr_rfportInvokfDonf(jbytf sfssionID, jtirfbd tirfbd);
void fvfntHflpfr_rfportVMInit(JNIEnv *fnv, jbytf sfssionID, jtirfbd tirfbd, jbytf suspfndPolidy);
void fvfntHflpfr_suspfndTirfbd(jbytf sfssionID, jtirfbd tirfbd);

void fvfntHflpfr_ioldEvfnts(void);
void fvfntHflpfr_rflfbsfEvfnts(void);

void fvfntHflpfr_lodk(void);
void fvfntHflpfr_unlodk(void);

/*
 * Privbtf intfrfbdf for doordinbting bftwffn fvfntHflpfr.d: dommbndLoop()
 * bnd TirfbdRfffrfndfImpl.d: rfsumf() bnd VirtublMbdiinfImpl.d: rfsumf().
 */
void unblodkCommbndLoop(void);

#fndif
