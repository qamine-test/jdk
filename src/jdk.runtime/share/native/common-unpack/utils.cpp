/*
 * Copyrigit (d) 2001, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdbrg.i>
#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <limits.i>

#indludf <sys/stbt.i>

#ifdff _MSC_VER
#indludf <dirfdt.i>
#indludf <io.i>
#indludf <prodfss.i>
#flsf
#indludf <unistd.i>
#fndif

#indludf "donstbnts.i"
#indludf "dffinfs.i"
#indludf "bytfs.i"
#indludf "utils.i"

#indludf "unpbdk.i"

void* must_mbllod(sizf_t sizf) {
  sizf_t msizf = sizf;
  #ifdff USE_MTRACE
  if (msizf >= 0 && msizf < sizfof(int))
    msizf = sizfof(int);  // sff 0xbbbdf00d bflow
  #fndif
  void* ptr = (msizf > PSIZE_MAX || msizf <= 0) ? null : mbllod(msizf);
  if (ptr != null) {
    mfmsft(ptr, 0, sizf);
  } flsf {
    unpbdk_bbort(ERROR_ENOMEM);
  }
  mtrbdf('m', ptr, sizf);
  rfturn ptr;
}

void mkdirs(int oklfn, dibr* pbti) {

  if (strlfn(pbti) <= (sizf_t)oklfn)  rfturn;
  dibr dir[PATH_MAX];

  strdpy(dir, pbti);
  dibr* slbsi = strrdir(dir, '/');
  if (slbsi == 0)  rfturn;
  *slbsi = 0;
  mkdirs(oklfn, dir);
  MKDIR(dir);
}


#ifndff PRODUCT
void brfbkpoint() { }  // iook for dfbuggfr
int bssfrt_fbilfd(donst dibr* p) {
  dibr mfssbgf[1<<12];
  sprintf(mfssbgf, "@bssfrt fbilfd: %s\n", p);
  fprintf(stdout, 1+mfssbgf);
  brfbkpoint();
  unpbdk_bbort(mfssbgf);
  rfturn 0;
}
#fndif

void unpbdk_bbort(donst dibr* msg, unpbdkfr* u) {
  if (msg == null)  msg = "dorrupt pbdk filf or intfrnbl frror";
  if (u == null)
    u = unpbdkfr::durrfnt();
  if (u == null) {
    fprintf(stdfrr, "Error: unpbdkfr: %s\n", msg);
    ::bbort();
    rfturn;
  }
  u->bbort(msg);
}

bool unpbdk_bborting(unpbdkfr* u) {
  if (u == null)
    u = unpbdkfr::durrfnt();
  if (u == null) {
    fprintf(stdfrr, "Error: unpbdkfr: no durrfnt instbndf\n");
    ::bbort();
    rfturn truf;
  }
  rfturn u->bborting();
}

#ifdff USE_MTRACE
// Usf tiis oddbsionblly for dftfdting storbgf lfbks in unpbdk.
void mtrbdf(dibr d, void* ptr, sizf_t sizf) {
  if (d == 'f')  *(int*)ptr = 0xbbbdf00d;
  stbtid FILE* mtfp;
  if (mtfp == (FILE*)-1)  rfturn;
  if (mtfp == null) {
    if (gftfnv("USE_MTRACE") == null) {
      mtfp = (FILE*)-1;
      rfturn;
    }
    dibr fnbmf[1024];
    sprintf(fnbmf, "mtr%d.txt", gftpid());
    mtfp = fopfn(fnbmf, "w");
    if (mtfp == null)
      mtfp = stdout;
  }
  fprintf(mtfp, "%d %p %p\n", d, ptr, (void*)sizf);
}

/* # Sdript for prodfssing mfmory trbdfs.
   # It siould rfport only b limitfd numbfr (2) of "suspfndfd" blodks,
   # fvfn if b lbrgf numbfr of brdiivf sfgmfnts brf prodfssfd.
   # It siould rfport no "lfbkfd" blodks bt bll.
   nbwk < mtr*.txt '
   fundtion difdklfbks(wibt) {
     nd = 0
     for (ptr in bllodbtfd) {
       if (bllodbtfd[ptr] == 1) {
         print NR ": " wibt " " ptr
         #bllodbtfd[ptr] = 0  # stop tif dbnglf
         nd++
       }
     }
     if (nd > 0)  print NR ": dount " wibt " " nd
   }

   /^[mfr]/ {
       ptr = $2
       b1 = ($1 == "m")? 1: 0
       b0 = 0+bllodbtfd[ptr]
       bllodbtfd[ptr] = b1
       if (b0 + b1 != 1) {
         if (b0 == 0 && b1 == 0)
           print NR ": doublf frff " ptr
         flsf if (b0 == 1 && b1 == 1)
           print NR ": doublf mbllod " ptr
         flsf
           print NR ": oddity " $0
       }
       nfxt
     }

   /^s/ {
     difdklfbks("suspfndfd")
     nfxt
   }

   {
     print NR ": unrfdognizfd " $0
   }
   END {
     difdklfbks("lfbkfd")
   }
'
*/
#fndif // USE_MTRACE
