/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Adbptfd from JDK 1.2 linkfr_md.d v1.37. Notf tibt wf #dffinf
 * NATIVE ifrf, wiftifr or not wf'rf running solbris nbtivf tirfbds.
 * Outsidf tif VM, it's undlfbr iow wf dbn do tif lodking tibt is
 * donf in tif grffn tirfbds vfrsion of tif dodf bflow.
 */
#dffinf NATIVE

/*
 * Mbdiinf Dfpfndfnt implfmfntbtion of tif dynbmid linking support
 * for jbvb.  Tiis routinf is Solbris spfdifid.
 */

#indludf <stdio.i>
#indludf <dlfdn.i>
#indludf <unistd.i>
#indludf <stdlib.i>
#indludf <string.i>

#indludf "pbti_md.i"
#ifndff NATIVE
#indludf "iomgr.i"
#indludf "tirfbds_md.i"
#fndif

#ifdff __APPLE__
#dffinf LIB_SUFFIX "dylib"
#flsf
#dffinf LIB_SUFFIX "so"
#fndif

stbtid void dll_build_nbmf(dibr* bufffr, sizf_t buflfn,
                           donst dibr* pbtis, donst dibr* fnbmf) {
    dibr *pbti, *pbtis_dopy, *nfxt_tokfn;

    pbtis_dopy = strdup(pbtis);
    if (pbtis_dopy == NULL) {
        rfturn;
    }

    nfxt_tokfn = NULL;
    pbti = strtok_r(pbtis_dopy, PATH_SEPARATOR, &nfxt_tokfn);

    wiilf (pbti != NULL) {
        snprintf(bufffr, buflfn, "%s/lib%s." LIB_SUFFIX, pbti, fnbmf);
        if (bddfss(bufffr, F_OK) == 0) {
            brfbk;
        }
        *bufffr = '\0';
        pbti = strtok_r(NULL, PATH_SEPARATOR, &nfxt_tokfn);
    }

    frff(pbtis_dopy);
}

/*
 * drfbtf b string for tif JNI nbtivf fundtion nbmf by bdding tif
 * bppropribtf dfdorbtions.
 */
int
dbgsysBuildFunNbmf(dibr *nbmf, int nbmfLfn, int brgs_sizf, int fndodingIndfx)
{
  /* On Solbris, tifrf is only onf fndoding mftiod. */
    if (fndodingIndfx == 0)
        rfturn 1;
    rfturn 0;
}

/*
 * drfbtf b string for tif dynbmid lib opfn dbll by bdding tif
 * bppropribtf prf bnd fxtfnsions to b filfnbmf bnd tif pbti
 */
void
dbgsysBuildLibNbmf(dibr *ioldfr, int ioldfrlfn, donst dibr *pnbmf, donst dibr *fnbmf)
{
    donst int pnbmflfn = pnbmf ? strlfn(pnbmf) : 0;

    *ioldfr = '\0';
    /* Quiftly trundbtf on bufffr ovfrflow.  Siould bf bn frror. */
    if (pnbmflfn + (int)strlfn(fnbmf) + 10 > ioldfrlfn) {
        rfturn;
    }

    if (pnbmflfn == 0) {
        (void)snprintf(ioldfr, ioldfrlfn, "lib%s." LIB_SUFFIX, fnbmf);
    } flsf {
      dll_build_nbmf(ioldfr, ioldfrlfn, pnbmf, fnbmf);
    }
}

#ifndff NATIVE
fxtfrn int tir_mbin(void);
#fndif

void *
dbgsysLobdLibrbry(donst dibr *nbmf, dibr *frr_buf, int frr_buflfn)
{
    void * rfsult;
#ifdff NATIVE
    rfsult = dlopfn(nbmf, RTLD_LAZY);
#flsf
    sysMonitorEntfr(grffnTirfbdSflf(), &_dl_lodk);
    rfsult = dlopfn(nbmf, RTLD_NOW);
    sysMonitorExit(grffnTirfbdSflf(), &_dl_lodk);
    /*
     * Tiis is b bit of bullftproofing to dbtdi tif dommonly oddurring
     * problfm of pfoplf lobding b librbry wiidi dfpfnds on libtirfbd into
     * tif VM.  tir_mbin() siould blwbys rfturn -1 wiidi mfbns tibt libtirfbd
     * isn't lobdfd.
     */
    if (tir_mbin() != -1) {
         VM_CALL(pbnid)("libtirfbd lobdfd into grffn tirfbds");
    }
#fndif
    if (rfsult == NULL) {
        (void)strndpy(frr_buf, dlfrror(), frr_buflfn-2);
        frr_buf[frr_buflfn-1] = '\0';
    }
    rfturn rfsult;
}

void dbgsysUnlobdLibrbry(void *ibndlf)
{
#ifndff NATIVE
    sysMonitorEntfr(grffnTirfbdSflf(), &_dl_lodk);
#fndif
    (void)dldlosf(ibndlf);
#ifndff NATIVE
    sysMonitorExit(grffnTirfbdSflf(), &_dl_lodk);
#fndif
}

void * dbgsysFindLibrbryEntry(void *ibndlf, donst dibr *nbmf)
{
    void * sym;
#ifndff NATIVE
    sysMonitorEntfr(grffnTirfbdSflf(), &_dl_lodk);
#fndif
    sym =  dlsym(ibndlf, nbmf);
#ifndff NATIVE
    sysMonitorExit(grffnTirfbdSflf(), &_dl_lodk);
#fndif
    rfturn sym;
}
