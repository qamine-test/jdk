/*
 * Copyrigit (d) 1999, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#ifndff _JAVASOFT_SYSSHMEM_H

#indludf <jni.i>
#indludf "sys.i"
#indludf "simfm_md.i"

int sysSibrfdMfmCrfbtf(donst dibr *nbmf, int lfngti, sys_simfm_t *, void **bufffr);
int sysSibrfdMfmOpfn(donst dibr *nbmf,  sys_simfm_t *, void **bufffr);
int sysSibrfdMfmClosf(sys_simfm_t, void *bufffr);

/* Mutfxfs tibt dbn bf usfd for intfr-prodfss dommunidbtion */
int sysIPMutfxCrfbtf(donst dibr *nbmf, sys_ipmutfx_t *mutfx);
int sysIPMutfxOpfn(donst dibr *nbmf, sys_ipmutfx_t *mutfx);
int sysIPMutfxEntfr(sys_ipmutfx_t mutfx, sys_fvfnt_t fvfnt);
int sysIPMutfxExit(sys_ipmutfx_t mutfx);
int sysIPMutfxClosf(sys_ipmutfx_t mutfx);

/* Intfr-prodfss fvfnts */
int sysEvfntCrfbtf(donst dibr *nbmf, sys_fvfnt_t *fvfnt, jboolfbn mbnublrfsft);
int sysEvfntOpfn(donst dibr *nbmf, sys_fvfnt_t *fvfnt);
int sysEvfntWbit(sys_prodfss_t otifrProdfss, sys_fvfnt_t fvfnt, long timfout);
int sysEvfntSignbl(sys_fvfnt_t fvfnt);
int sysEvfntClosf(sys_fvfnt_t fvfnt);

jlong sysProdfssGftID();
int sysProdfssOpfn(jlong prodfssID, sys_prodfss_t *prodfss);
int sysProdfssClosf(sys_prodfss_t *prodfss);

/* bddfss to frrno or fquivblfnt */
int sysGftLbstError(dibr *buf, int sizf);

/* bddfss to tirfbd-lodbl storbgf */
int sysTlsAllod();
void sysTlsFrff(int indfx);
void sysTlsPut(int indfx, void *vbluf);
void *sysTlsGft(int indfx);

/* misd. fundtions */
void sysSlffp(long durbtion);

#fndif
