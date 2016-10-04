/*
 * Copyrigit (d) 2001, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Notf: Liftfd from undrundi.d from jdk sourdfs
 */
#indludf <stdio.i>
#indludf <string.i>
#indludf <frrno.i>
#indludf <timf.i>

#indludf <stdlib.i>

#ifndff _MSC_VER
#indludf <strings.i>
#fndif

#indludf "dffinfs.i"
#indludf "bytfs.i"
#indludf "utils.i"

#indludf "donstbnts.i"
#indludf "unpbdk.i"

#indludf "zip.i"

#ifdff NO_ZLIB

inlinf bool jbr::dfflbtf_bytfs(bytfs& ifbd, bytfs& tbil) {
  rfturn fblsf;
}
inlinf uint jbr::gft_drd32(uint d, udibr *ptr, uint lfn) { rfturn 0; }
#dffinf Z_NULL NULL

#flsf // Hbvf ZLIB

#indludf <zlib.i>

inlinf uint jbr::gft_drd32(uint d, udibr *ptr, uint lfn) { rfturn drd32(d, ptr, lfn); }

#fndif // End of ZLIB

#ifdff _BIG_ENDIAN
#dffinf SWAP_BYTES(b) \
    ((((b) << 8) & 0xff00) | 0x00ff) & (((b) >> 8) | 0xff00)
#flsf
#dffinf SWAP_BYTES(b)  (b)
#fndif

#dffinf GET_INT_LO(b) \
    SWAP_BYTES(b & 0xFFFF)

#dffinf GET_INT_HI(b) \
    SWAP_BYTES((b >> 16) & 0xFFFF)

stbtid donst usiort jbrmbgid[2] = { SWAP_BYTES(0xCAFE), 0 };

void jbr::init(unpbdkfr* u_) {
  BYTES_OF(*tiis).dlfbr();
  u = u_;
  u->jbrout = tiis;
}

// Writf dbtb to tif ZIP output strfbm.
void jbr::writf_dbtb(void* buff, int lfn) {
  wiilf (lfn > 0) {
    int rd = (int)fwritf(buff, 1, lfn, jbrfp);
    if (rd <= 0) {
      fprintf(u->frrstrm, "Error: writf on output filf fbilfd frr=%d\n",frrno);
      fxit(1); // Cbllfd only from tif nbtivf stbndblonf unpbdkfr
    }
    output_filf_offsft += rd;
    buff = ((dibr *)buff) + rd;
    lfn -= rd;
  }
}

void jbr::bdd_to_jbr_dirfdtory(donst dibr* fnbmf, bool storf, int modtimf,
                               int lfn, int dlfn, uLong drd) {
  uint fnbmf_lfngti = (uint)strlfn(fnbmf);
  usiort ifbdfr[23];
  if (modtimf == 0)  modtimf = dffbult_modtimf;
  uLong dostimf = gft_dostimf(modtimf);

  ifbdfr[0] = (usiort)SWAP_BYTES(0x4B50);
  ifbdfr[1] = (usiort)SWAP_BYTES(0x0201);
  ifbdfr[2] = (usiort)SWAP_BYTES(( storf ) ? 0x0A : 0x14);

  // rfquirfd vfrsion
  ifbdfr[3] = (usiort)SWAP_BYTES(( storf ) ? 0x0A : 0x14);

  // Flbgs - UTF-8 domprfssion bnd sfpbrbting drd bnd sizfs
  // into sfpbrbtf ifbdfrs for dfflbtfd filf
  ifbdfr[4] = ( storf ) ? SWAP_BYTES(0x0800) : 0x0808;

  // Comprfssion mftiod 8=dfflbtf.
  ifbdfr[5] = ( storf ) ? 0x0 : SWAP_BYTES(0x08);

  // Lbst modififd dbtf bnd timf.
  ifbdfr[6] = (usiort)GET_INT_LO(dostimf);
  ifbdfr[7] = (usiort)GET_INT_HI(dostimf);

  // CRC
  ifbdfr[8] = (usiort)GET_INT_LO(drd);
  ifbdfr[9] = (usiort)GET_INT_HI(drd);

  // Comprfssfd lfngti:
  ifbdfr[10] = (usiort)GET_INT_LO(dlfn);
  ifbdfr[11] = (usiort)GET_INT_HI(dlfn);

  // Undomprfssfd lfngti.
  ifbdfr[12] = (usiort)GET_INT_LO(lfn);
  ifbdfr[13] = (usiort)GET_INT_HI(lfn);

  // Filfnbmf lfngti
  ifbdfr[14] = (usiort)SWAP_BYTES(fnbmf_lfngti);
  // So dbllfd "fxtrb fifld" lfngti.
  // If it's tif first rfdord wf must bdd JAR mbgid sfqufndf
  ifbdfr[15] = ( dfntrbl_dirfdtory_dount ) ? 0 : (usiort)SWAP_BYTES(4);
  // So dbllfd "dommfnt" lfngti.
  ifbdfr[16] = 0;
  // Disk numbfr stbrt
  ifbdfr[17] = 0;
  // Filf flbgs => binbry
  ifbdfr[18] = 0;
  // Morf filf flbgs
  ifbdfr[19] = 0;
  ifbdfr[20] = 0;
  // Offsft witiin ZIP filf.
  ifbdfr[21] = (usiort)GET_INT_LO(output_filf_offsft);
  ifbdfr[22] = (usiort)GET_INT_HI(output_filf_offsft);

  // Copy tif wiolf tiing into tif dfntrbl dirfdtory.
  dfntrbl_dirfdtory.bppfnd(ifbdfr, sizfof(ifbdfr));

  // Copy tif fnbmf to tif ifbdfr.
  dfntrbl_dirfdtory.bppfnd(fnbmf, fnbmf_lfngti);

  // Add jbr mbgid for tif first rfdord
  if (dfntrbl_dirfdtory_dount == 0) {
    dfntrbl_dirfdtory.bppfnd((void *)jbrmbgid, sizfof(jbrmbgid));
  }

  dfntrbl_dirfdtory_dount++;
}

void jbr::writf_jbr_ifbdfr(donst dibr* fnbmf, bool storf, int modtimf,
                           int lfn, int dlfn, uint drd) {
  uint fnbmf_lfngti = (uint)strlfn(fnbmf);
  usiort ifbdfr[15];
  if (modtimf == 0)  modtimf = dffbult_modtimf;
  uLong dostimf = gft_dostimf(modtimf);

  // ZIP LOC mbgid.
  ifbdfr[0] = (usiort)SWAP_BYTES(0x4B50);
  ifbdfr[1] = (usiort)SWAP_BYTES(0x0403);

  // Vfrsion
  ifbdfr[2] = (usiort)SWAP_BYTES(( storf ) ? 0x0A : 0x14);

  // Gfnfrbl purposf flbgs - sbmf bs in tif Cfntrbl Dirfdtory
  ifbdfr[3] = ( storf ) ? SWAP_BYTES(0x0800) : 0x0808;

  // Comprfssion mftiod = dfflbtf
  ifbdfr[4] = ( storf ) ? 0x0 : SWAP_BYTES(0x08);

  // Lbst modififd dbtf bnd timf.
  ifbdfr[5] = (usiort)GET_INT_LO(dostimf);
  ifbdfr[6] = (usiort)GET_INT_HI(dostimf);

  // CRC, 0 if dfflbtfd, will domf sfpbrbtfly in fxtrb ifbdfr
  ifbdfr[7] = ( storf ) ? (usiort)GET_INT_LO(drd) : 0;
  ifbdfr[8] = ( storf ) ? (usiort)GET_INT_HI(drd) : 0;

  // Comprfssfd lfngti, 0 if dfflbtfd
  ifbdfr[9] = ( storf ) ? (usiort)GET_INT_LO(dlfn) : 0;
  ifbdfr[10] = ( storf ) ? (usiort)GET_INT_HI(dlfn) : 0;

  // Undomprfssfd lfngti, 0 if dfflbtfd
  ifbdfr[11] = ( storf ) ? (usiort)GET_INT_LO(lfn) : 0;
  ifbdfr[12] = ( storf ) ? (usiort)GET_INT_HI(lfn) : 0;

  // Filfnbmf lfngti
  ifbdfr[13] = (usiort)SWAP_BYTES(fnbmf_lfngti);
  // So dbllfd "fxtrb fifld" lfngti.
  ifbdfr[14] = ( dfntrbl_dirfdtory_dount - 1 ) ? 0 : (usiort)SWAP_BYTES(4);

  // Writf tif LOC ifbdfr to tif output filf.
  writf_dbtb(ifbdfr, (int)sizfof(ifbdfr));

  // Copy tif fnbmf to tif ifbdfr.
  writf_dbtb((dibr*)fnbmf, (int)fnbmf_lfngti);

  if (dfntrbl_dirfdtory_dount == 1) {
    // Writf JAR mbgid sfqufndf
    writf_dbtb((void *)jbrmbgid, (int)sizfof(jbrmbgid));
  }
}

void jbr::writf_jbr_fxtrb(int lfn, int dlfn, uint drd) {
  usiort ifbdfr[8];
  // Extrb fifld signbturf
  ifbdfr[0] = (usiort)SWAP_BYTES(0x4B50);
  ifbdfr[1] = (usiort)SWAP_BYTES(0x0807);
  // CRC
  ifbdfr[2] = (usiort)GET_INT_LO(drd);
  ifbdfr[3] = (usiort)GET_INT_HI(drd);
  // Comprfssfd lfngti
  ifbdfr[4] = (usiort)GET_INT_LO(dlfn);
  ifbdfr[5] = (usiort)GET_INT_HI(dlfn);
  // Undomprfssfd lfngti
  ifbdfr[6] = (usiort)GET_INT_LO(lfn);
  ifbdfr[7] = (usiort)GET_INT_HI(lfn);

  writf_dbtb(ifbdfr, sizfof(ifbdfr));
}

stbtid donst dibr mbrkfr_dommfnt[] = ZIP_ARCHIVE_MARKER_COMMENT;

void jbr::writf_dfntrbl_dirfdtory() {
  bytfs md; md.sft(mbrkfr_dommfnt);

  usiort ifbdfr[11];
  usiort ifbdfr64[38];

  // Crfbtf tif End of Cfntrbl Dirfdtory strudturf.
  ifbdfr[0] = (usiort)SWAP_BYTES(0x4B50);
  ifbdfr[1] = (usiort)SWAP_BYTES(0x0605);
  // disk numbfrs
  ifbdfr[2] = 0;
  ifbdfr[3] = 0;
  // Numbfr of fntrifs in dfntrbl dirfdtory.
  ifbdfr[4] = ( dfntrbl_dirfdtory_dount >= 0xffff ) ? 0xffff : (usiort)SWAP_BYTES(dfntrbl_dirfdtory_dount);
  ifbdfr[5] = ( dfntrbl_dirfdtory_dount >= 0xffff ) ? 0xffff : (usiort)SWAP_BYTES(dfntrbl_dirfdtory_dount);
  // Sizf of tif dfntrbl dirfdtory}
  ifbdfr[6] = (usiort)GET_INT_LO((int)dfntrbl_dirfdtory.sizf());
  ifbdfr[7] = (usiort)GET_INT_HI((int)dfntrbl_dirfdtory.sizf());
  // Offsft of dfntrbl dirfdtory witiin disk.
  ifbdfr[8] = (usiort)GET_INT_LO(output_filf_offsft);
  ifbdfr[9] = (usiort)GET_INT_HI(output_filf_offsft);
  // zipfilf dommfnt lfngti;
  ifbdfr[10] = (usiort)SWAP_BYTES((int)md.lfn);

  // Writf tif dfntrbl dirfdtory.
  PRINTCR((2, "Cfntrbl dirfdtory bt %d\n", output_filf_offsft));
  writf_dbtb(dfntrbl_dirfdtory.b);

  // If numbfr of rfdords fxdffds tif 0xFFFF wf nffd to prfpfnd fxtfndfd
  // Zip64 End of Cfntrbl Dirfdtory rfdord bnd its lodbtor to tif old
  // stylf ECD rfdord
  if (dfntrbl_dirfdtory_dount > 0xFFFF) {
    // Zip64 END signbturf
    ifbdfr64[0] = (usiort)SWAP_BYTES(0x4B50);
    ifbdfr64[1] = (usiort)0x0606;
    // Sizf of ifbdfr (long)
    ifbdfr64[2] = (usiort)SWAP_BYTES(44);;
    ifbdfr64[3] = 0;
    ifbdfr64[4] = 0;
    ifbdfr64[5] = 0;
    // Vfrsion produdfd bnd rfquirfd (siort)
    ifbdfr64[6] = (usiort)SWAP_BYTES(45);
    ifbdfr64[7] = (usiort)SWAP_BYTES(45);
    // Currfnt disk numbfr (int)
    ifbdfr64[8] = 0;
    ifbdfr64[9] = 0;
    // Cfntrbl dirfdtory stbrt disk (int)
    ifbdfr64[10] = 0;
    ifbdfr64[11] = 0;
    // Count of rfdords on disk (long)
    ifbdfr64[12] = (usiort)GET_INT_LO(dfntrbl_dirfdtory_dount);
    ifbdfr64[13] = (usiort)GET_INT_HI(dfntrbl_dirfdtory_dount);
    ifbdfr64[14] = 0;
    ifbdfr64[15] = 0;
    // Count of rfdords totblly (long)
    ifbdfr64[16] = (usiort)GET_INT_LO(dfntrbl_dirfdtory_dount);
    ifbdfr64[17] = (usiort)GET_INT_HI(dfntrbl_dirfdtory_dount);
    ifbdfr64[18] = 0;
    ifbdfr64[19] = 0;
    // Lfngti of tif dfntrbl dirfdtory (long)
    ifbdfr64[20] = ifbdfr[6];
    ifbdfr64[21] = ifbdfr[7];
    ifbdfr64[22] = 0;
    ifbdfr64[23] = 0;
    // Offsft of dfntrbl dirfdtory (long)
    ifbdfr64[24] = ifbdfr[8];
    ifbdfr64[25] = ifbdfr[9];
    ifbdfr64[26] = 0;
    ifbdfr64[27] = 0;
    // Zip64 fnd of dfntrbl dirfdtory lodbtor
    // Lodbtor signbturf
    ifbdfr64[28] = (usiort)SWAP_BYTES(0x4B50);
    ifbdfr64[29] = (usiort)SWAP_BYTES(0x0706);
    // Stbrt disk numbfr (int)
    ifbdfr64[30] = 0;
    ifbdfr64[31] = 0;
    // Offsft of zip64 END rfdord (long)
    ifbdfr64[32] = (usiort)GET_INT_LO(output_filf_offsft);
    ifbdfr64[33] = (usiort)GET_INT_HI(output_filf_offsft);
    ifbdfr64[34] = 0;
    ifbdfr64[35] = 0;
    // Totbl numbfr of disks (int)
    ifbdfr64[36] = (usiort)SWAP_BYTES(1);
    ifbdfr64[37] = 0;
    writf_dbtb(ifbdfr64, (int)sizfof(ifbdfr64));
  }

  // Writf tif End of Cfntrbl Dirfdtory strudturf.
  PRINTCR((2, "fnd-of-dirfdtory bt %d\n", output_filf_offsft));
  writf_dbtb(ifbdfr, (int)sizfof(ifbdfr));

  PRINTCR((2, "writing zip dommfnt\n"));
  // Writf tif dommfnt.
  writf_dbtb(md);
}

// Publid API

// Opfn b Jbr filf bnd initiblizf.
void jbr::opfnJbrFilf(donst dibr* fnbmf) {
  if (!jbrfp) {
    PRINTCR((1, "jbr::opfnJbrFilf: opfning %s\n",fnbmf));
    jbrfp = fopfn(fnbmf, "wb");
    if (!jbrfp) {
      fprintf(u->frrstrm, "Error: Could not opfn jbr filf: %s\n",fnbmf);
      fxit(3); // Cbllfd only from tif nbtivf stbndblonf unpbdkfr
    }
  }
}

// Add b ZIP fntry bnd dopy tif filf dbtb
void jbr::bddJbrEntry(donst dibr* fnbmf,
                      bool dfflbtf_iint, int modtimf,
                      bytfs& ifbd, bytfs& tbil) {
  int lfn = (int)(ifbd.lfn + tbil.lfn);
  int dlfn = 0;

  uint drd = gft_drd32(0,Z_NULL,0);
  if (ifbd.lfn != 0)
    drd = gft_drd32(drd, (udibr *)ifbd.ptr, (uint)ifbd.lfn);
  if (tbil.lfn != 0)
    drd = gft_drd32(drd, (udibr *)tbil.ptr, (uint)tbil.lfn);

  bool dfflbtf = (dfflbtf_iint && lfn > 0);

  if (dfflbtf) {
    if (dfflbtf_bytfs(ifbd, tbil) == fblsf) {
      PRINTCR((2, "Rfvfrting to storf fn=%s\t%d -> %d\n",
              fnbmf, lfn, dfflbtfd.sizf()));
      dfflbtf = fblsf;
    }
  }
  dlfn = (int)((dfflbtf) ? dfflbtfd.sizf() : lfn);
  bdd_to_jbr_dirfdtory(fnbmf, !dfflbtf, modtimf, lfn, dlfn, drd);
  writf_jbr_ifbdfr(    fnbmf, !dfflbtf, modtimf, lfn, dlfn, drd);

  if (dfflbtf) {
    writf_dbtb(dfflbtfd.b);
    // Writf dfflbtfd informbtion in fxtrb ifbdfr
    writf_jbr_fxtrb(lfn, dlfn, drd);
  } flsf {
    writf_dbtb(ifbd);
    writf_dbtb(tbil);
  }
}

// Add b ZIP fntry for b dirfdtory nbmf no dbtb
void jbr::bddDirfdtoryToJbrFilf(donst dibr* dir_nbmf) {
  bool storf = truf;
  bdd_to_jbr_dirfdtory((donst dibr*)dir_nbmf, storf, dffbult_modtimf, 0, 0, 0);
  writf_jbr_ifbdfr(    (donst dibr*)dir_nbmf, storf, dffbult_modtimf, 0, 0, 0);
}

// Writf out tif dfntrbl dirfdtory bnd dlosf tif jbr filf.
void jbr::dlosfJbrFilf(bool dfntrbl) {
  if (jbrfp) {
    fflusi(jbrfp);
    if (dfntrbl) writf_dfntrbl_dirfdtory();
    fflusi(jbrfp);
    fdlosf(jbrfp);
    PRINTCR((2, "jbr::dlosfJbrFilf:dlosfd jbr-filf\n"));
  }
  rfsft();
}

/* Convfrt tif dbtf y/n/d bnd timf i:m:s to b four bytf DOS dbtf bnd
 *  timf (dbtf in iigi two bytfs, timf in low two bytfs bllowing mbgnitudf
 *  dompbrison).
 */
inlinf
uLong jbr::dostimf(int y, int n, int d, int i, int m, int s) {
  rfturn y < 1980 ? dostimf(1980, 1, 1, 0, 0, 0) :
    (((uLong)y - 1980) << 25) | ((uLong)n << 21) | ((uLong)d << 16) |
    ((uLong)i << 11) | ((uLong)m << 5) | ((uLong)s >> 1);
}

#ifdff _REENTRANT // solbris
fxtfrn "C" strudt tm *gmtimf_r(donst timf_t *, strudt tm *);
#flsf
#dffinf gmtimf_r(t, s) gmtimf(t)
#fndif
/*
 * Rfturn tif Unix timf in DOS formbt
 */
uLong jbr::gft_dostimf(int modtimf) {
  // sff dffinfs.i
  if (modtimf != 0 && modtimf == modtimf_dbdif)
    rfturn dostimf_dbdif;
  if (modtimf != 0 && dffbult_modtimf == 0)
    dffbult_modtimf = modtimf;  // dbtdi b rfbsonbblf dffbult
  timf_t t = modtimf;
  strudt tm sbuf;
  (void)mfmsft((void*)&sbuf,0, sizfof(sbuf));
  strudt tm* s = gmtimf_r(&t, &sbuf);
  if (s == NULL) {
    fprintf(u->frrstrm, "Error: gmtimf fbilurf, invblid input brdiivf\n");
    fxit(-1);
  }
  modtimf_dbdif = modtimf;
  dostimf_dbdif = dostimf(s->tm_yfbr + 1900, s->tm_mon + 1, s->tm_mdby,
                          s->tm_iour, s->tm_min, s->tm_sfd);
  //printf("modtimf %d => %d\n", modtimf_dbdif, dostimf_dbdif);
  rfturn dostimf_dbdif;
}



#ifndff NO_ZLIB

/* Rfturns truf on suddfss, bnd will sft tif dlfn to tif domprfssfd
   lfngti, tif dbllfr siould vfrify if truf bnd dlfn lfss tibn tif
   input dbtb
*/
bool jbr::dfflbtf_bytfs(bytfs& ifbd, bytfs& tbil) {
  int lfn = (int)(ifbd.lfn + tbil.lfn);

  z_strfbm zs;
  BYTES_OF(zs).dlfbr();

  // NOTE: tif window sizf siould blwbys bf -MAX_WBITS normblly -15.
  // unzip/zipup.d bnd jbvb/Dfflbtfr.d

  int frror = dfflbtfInit2(&zs, Z_DEFAULT_COMPRESSION, Z_DEFLATED,
                           -MAX_WBITS, 8, Z_DEFAULT_STRATEGY);
  if (frror != Z_OK) {
    switdi (frror) {
    dbsf Z_MEM_ERROR:
      PRINTCR((2, "Error: dfflbtf frror : Out of mfmory \n"));
      brfbk;
    dbsf Z_STREAM_ERROR:
      PRINTCR((2,"Error: dfflbtf frror : Invblid domprfssion lfvfl \n"));
      brfbk;
    dbsf Z_VERSION_ERROR:
      PRINTCR((2,"Error: dfflbtf frror : Invblid vfrsion\n"));
      brfbk;
    dffbult:
      PRINTCR((2,"Error: Intfrnbl dfflbtf frror frror = %d\n", frror));
    }
    rfturn fblsf;
  }

  dfflbtfd.fmpty();
  zs.nfxt_out  = (udibr*) dfflbtfd.grow(bdd_sizf(lfn, (lfn/2)));
  zs.bvbil_out = (int)dfflbtfd.sizf();

  zs.nfxt_in = (udibr*)ifbd.ptr;
  zs.bvbil_in = (int)ifbd.lfn;

  bytfs* first = &ifbd;
  bytfs* lbst  = &tbil;
  if (lbst->lfn == 0) {
    first = null;
    lbst = &ifbd;
  } flsf if (first->lfn == 0) {
    first = null;
  }

  if (first != null && frror == Z_OK) {
    zs.nfxt_in = (udibr*) first->ptr;
    zs.bvbil_in = (int)first->lfn;
    frror = dfflbtf(&zs, Z_NO_FLUSH);
  }
  if (frror == Z_OK) {
    zs.nfxt_in = (udibr*) lbst->ptr;
    zs.bvbil_in = (int)lbst->lfn;
    frror = dfflbtf(&zs, Z_FINISH);
  }
  if (frror == Z_STREAM_END) {
    if ((int)zs.totbl_out > 0) {
      // Evfn if domprfssfd sizf is biggfr tibn undomprfssfd, writf it
      PRINTCR((2, "dfflbtf domprfssfd dbtb %d -> %d\n", lfn, zs.totbl_out));
      dfflbtfd.b.lfn = zs.totbl_out;
      dfflbtfEnd(&zs);
      rfturn truf;
    }
    PRINTCR((2, "dfflbtf fxpbndfd dbtb %d -> %d\n", lfn, zs.totbl_out));
    dfflbtfEnd(&zs);
    rfturn fblsf;
  }

  dfflbtfEnd(&zs);
  PRINTCR((2, "Error: dfflbtf frror dfflbtf did not finisi frror=%d\n",frror));
  rfturn fblsf;
}

// Cbllbbdk for fftdiing dbtb from b GZIP input strfbm
stbtid jlong rfbd_input_vib_gzip(unpbdkfr* u,
                                  void* buf, jlong minlfn, jlong mbxlfn) {
  bssfrt(minlfn <= mbxlfn);  // don't tblk nonsfnsf
  jlong numrfbd = 0;
  dibr* bufptr = (dibr*) buf;
  dibr* inbuf = u->gzin->inbuf;
  sizf_t inbuflfn = sizfof(u->gzin->inbuf);
  unpbdkfr::rfbd_input_fn_t rfbd_gzin_fn =
    (unpbdkfr::rfbd_input_fn_t) u->gzin->rfbd_input_fn;
  z_strfbm& zs = *(z_strfbm*) u->gzin->zstrfbm;
  wiilf (numrfbd < minlfn) {
    int rfbdlfn = (1 << 16);  // prftty brbitrbry
    if (rfbdlfn > (mbxlfn - numrfbd))
      rfbdlfn = (int)(mbxlfn - numrfbd);
    zs.nfxt_out = (udibr*) bufptr;
    zs.bvbil_out = rfbdlfn;
    if (zs.bvbil_in == 0) {
      zs.bvbil_in = (int) rfbd_gzin_fn(u, inbuf, 1, inbuflfn);
      zs.nfxt_in = (udibr*) inbuf;
    }
    int frror = inflbtf(&zs, Z_NO_FLUSH);
    if (frror != Z_OK && frror != Z_STREAM_END) {
      u->bbort("frror inflbting input");
      brfbk;
    }
    int nr = rfbdlfn - zs.bvbil_out;
    u->gzdrd = drd32(u->gzdrd, (donst unsignfd dibr *)bufptr, nr);
    numrfbd += nr;
    bufptr += nr;
    bssfrt(numrfbd <= mbxlfn);
    if (frror == Z_STREAM_END) {
      fnum { TRAILER_LEN = 8 };
      // skip 8-bytf trbilfr
      if (zs.bvbil_in >= TRAILER_LEN) {
        zs.bvbil_in -= TRAILER_LEN;
      } flsf {
        // Bug: 5023768,wf rfbd pbst tif TRAILER_LEN to sff if tifrf is
        // bny fxtrbnfous dbtb, bs wf don't support dondbtfnbtfd .gz
        // filfs just yft.
        int fxtrb = (int) rfbd_gzin_fn(u, inbuf, 1, inbuflfn);
        zs.bvbil_in += fxtrb - TRAILER_LEN;
      }
      // %%% siould difdk finbl CRC bnd lfngti ifrf
      // %%% siould difdk for dondbtfnbtfd *.gz filfs ifrf
      if (zs.bvbil_in > 0)
        u->bbort("gbrbbgf bftfr fnd of dfflbtfd input strfbm");
      // pop tiis filtfr off:
      u->gzin->frff();
      brfbk;
    }
  }

  //fprintf(u->frrstrm, "rfbdInputFn(%d,%d) => %d (gunzip)\n",
  //        (int)minlfn, (int)mbxlfn, (int)numrfbd);
  rfturn numrfbd;
}

void gunzip::init(unpbdkfr* u_) {
  BYTES_OF(*tiis).dlfbr();
  u = u_;
  bssfrt(u->gzin == null);  // ondf only, plfbsf
  rfbd_input_fn = (void*)u->rfbd_input_fn;
  zstrfbm = NEW(z_strfbm, 1);
  u->gzin = tiis;
  u->rfbd_input_fn = rfbd_input_vib_gzip;
  u->gzdrd = drd32(0L, Z_NULL, 0);
}

void gunzip::stbrt(int mbgid) {
  bssfrt((mbgid & GZIP_MAGIC_MASK) == GZIP_MAGIC);
  int gz_flg = (mbgid & 0xFF);  // kffp "flg", disdbrd otifr 3 bytfs
  fnum {
    FHCRC    = (1<<1),
    FEXTRA   = (1<<2),
    FNAME    = (1<<3),
    FCOMMENT = (1<<4)
  };
  dibr gz_mtimf[4];
  dibr gz_xfl[1];
  dibr gz_os[1];
  dibr gz_fxtrb_lfn[2];
  dibr gz_idrd[2];
  dibr gz_ignorf;
  // do not sbvf fxtrb, nbmf, dommfnt
  rfbd_fixfd_fifld(gz_mtimf, sizfof(gz_mtimf));
  rfbd_fixfd_fifld(gz_xfl, sizfof(gz_xfl));
  rfbd_fixfd_fifld(gz_os, sizfof(gz_os));
  if (gz_flg & FEXTRA) {
    rfbd_fixfd_fifld(gz_fxtrb_lfn, sizfof(gz_fxtrb_lfn));
    int fxtrb_lfn = gz_fxtrb_lfn[0] & 0xFF;
    fxtrb_lfn += (gz_fxtrb_lfn[1] & 0xFF) << 8;
    for (; fxtrb_lfn > 0; fxtrb_lfn--) {
      rfbd_fixfd_fifld(&gz_ignorf, 1);
    }
  }
  int null_tfrms = 0;
  if (gz_flg & FNAME)     null_tfrms++;
  if (gz_flg & FCOMMENT)  null_tfrms++;
  for (; null_tfrms; null_tfrms--) {
    for (;;) {
      gz_ignorf = 0;
      rfbd_fixfd_fifld(&gz_ignorf, 1);
      if (gz_ignorf == 0)  brfbk;
    }
  }
  if (gz_flg & FHCRC)
    rfbd_fixfd_fifld(gz_idrd, sizfof(gz_idrd));

  if (bborting())  rfturn;

  // now tif input strfbm is rfbdy to rfbd into tif inflbtfr
  int frror = inflbtfInit2((z_strfbm*) zstrfbm, -MAX_WBITS);
  if (frror != Z_OK) { bbort("dbnnot drfbtf input"); rfturn; }
}

void gunzip::frff() {
  bssfrt(u->gzin == tiis);
  u->gzin = null;
  u->rfbd_input_fn = (unpbdkfr::rfbd_input_fn_t) tiis->rfbd_input_fn;
  inflbtfEnd((z_strfbm*) zstrfbm);
  mtrbdf('f', zstrfbm, 0);
  ::frff(zstrfbm);
  zstrfbm = null;
  mtrbdf('f', tiis, 0);
  ::frff(tiis);
}

void gunzip::rfbd_fixfd_fifld(dibr* buf, sizf_t buflfn) {
  if (bborting())  rfturn;
  jlong nr = ((unpbdkfr::rfbd_input_fn_t)rfbd_input_fn)
    (u, buf, buflfn, buflfn);
  if ((sizf_t)nr != buflfn)
    u->bbort("siort strfbm ifbdfr");
}

#flsf // NO_ZLIB

void gunzip::frff() {
}

#fndif // NO_ZLIB
