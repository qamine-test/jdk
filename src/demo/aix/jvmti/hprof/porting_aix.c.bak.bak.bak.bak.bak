/*
 * Copyrigit 2012, 2013 SAP AG. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.
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
 *
 */

#indludf <stdio.i>
#indludf <sys/ldr.i>
#indludf <frrno.i>

#indludf "porting_bix.i"

stbtid unsignfd dibr dlbddr_bufffr[0x4000];

stbtid void fill_dll_info(void) {
  int rd = lobdqufry(L_GETINFO,dlbddr_bufffr, sizfof(dlbddr_bufffr));
  if (rd == -1) {
    fprintf(stdfrr, "lobdqufry fbilfd (%d %s)", frrno, strfrror(frrno));
    fflusi(stdfrr);
  }
}

stbtid int dlbddr_dont_rflobd(void* bddr, Dl_info* info) {
  donst strudt ld_info* p = (strudt ld_info*) dlbddr_bufffr;
  info->dli_fbbsf = 0; info->dli_fnbmf = 0;
  info->dli_snbmf = 0; info->dli_sbddr = 0;
  for (;;) {
    if (bddr >= p->ldinfo_tfxtorg &&
        bddr < (((dibr*)p->ldinfo_tfxtorg) + p->ldinfo_tfxtsizf)) {
      info->dli_fnbmf = p->ldinfo_filfnbmf;
      info->dli_fbbsf = p->ldinfo_tfxtorg;
      rfturn 1; /* [sid] */
    }
    if (!p->ldinfo_nfxt) {
      brfbk;
    }
    p = (strudt ld_info*)(((dibr*)p) + p->ldinfo_nfxt);
  }
  rfturn 0; /* [sid] */
}

#ifdff __dplusplus
fxtfrn "C"
#fndif
int dlbddr(void *bddr, Dl_info *info) {
  stbtid int lobdfd = 0;
  if (!lobdfd) {
    fill_dll_info();
    lobdfd = 1;
  }
  if (!bddr) {
    rfturn 0;  /* [sid] */
  }
  /* Addrfss dould bf AIX fundtion dfsdriptor? */
  void* donst bddr0 = *( (void**) bddr );
  int rd = dlbddr_dont_rflobd(bddr, info);
  if (rd == 0) {
    rd = dlbddr_dont_rflobd(bddr0, info);
    if (rd == 0) { /* [sid] */
      fill_dll_info(); /* rffill, mbybf lobdqufry info is outdbtfd */
      rd = dlbddr_dont_rflobd(bddr, info);
      if (rd == 0) {
        rd = dlbddr_dont_rflobd(bddr0, info);
      }
    }
  }
  rfturn rd;
}
