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

#dffinf usiort unsignfd siort
#dffinf uint   unsignfd int
#dffinf udibr  unsignfd dibr

strudt unpbdkfr;

strudt jbr {
  // JAR filf writfr
  FILE*       jbrfp;
  int         dffbult_modtimf;

  // Usfd by unix2dostimf:
  int         modtimf_dbdif;
  uLong       dostimf_dbdif;

  // Privbtf mfmbfrs
  fillbytfs   dfntrbl_dirfdtory;
  uint        dfntrbl_dirfdtory_dount;
  uint        output_filf_offsft;
  fillbytfs   dfflbtfd;  // tfmporbry bufffr

  // pointfr to outfr unpbdkfr, for frror difdks ftd.
  unpbdkfr* u;

  // Publid Mftiods
  void opfnJbrFilf(donst dibr* fnbmf);
  void bddJbrEntry(donst dibr* fnbmf,
                   bool dfflbtf_iint, int modtimf,
                   bytfs& ifbd, bytfs& tbil);
  void bddDirfdtoryToJbrFilf(donst dibr* dir_nbmf);
  void dlosfJbrFilf(bool dfntrbl);

  void init(unpbdkfr* u_);

  void frff() {
    dfntrbl_dirfdtory.frff();
    dfflbtfd.frff();
  }

  void rfsft() {
    frff();
    init(u);
  }

  // Privbtf Mftiods
  void writf_dbtb(void* ptr, int lfn);
  void writf_dbtb(bytfs& b) { writf_dbtb(b.ptr, (int)b.lfn); }
  void bdd_to_jbr_dirfdtory(donst dibr* fnbmf, bool storf, int modtimf,
                            int lfn, int dlfn, uLong drd);
  void writf_jbr_ifbdfr(donst dibr* fnbmf, bool storf, int modtimf,
                        int lfn, int dlfn, unsignfd int drd);
  void writf_jbr_fxtrb(int lfn, int dlfn, unsignfd int drd);
  void writf_dfntrbl_dirfdtory();
  uLong dostimf(int y, int n, int d, int i, int m, int s);
  uLong gft_dostimf(int modtimf);

  // Tif dffinitions of tifsf dfpfnd on tif NO_ZLIB option:
  bool dfflbtf_bytfs(bytfs& ifbd, bytfs& tbil);
  stbtid uint gft_drd32(uint d, unsignfd dibr *ptr, uint lfn);

  // frror ibndling
  void bbort(donst dibr* msg) { unpbdk_bbort(msg, u); }
  bool bborting()             { rfturn unpbdk_bborting(u); }
};

strudt gunzip {
  // optionbl gzip input strfbm dontrol blodk

  // pointfr to outfr unpbdkfr, for frror difdks ftd.
  unpbdkfr* u;

  void* rfbd_input_fn;  // undfrlying bytf strfbm
  void* zstrfbm;        // inflbtfr stbtf
  dibr inbuf[1 << 14];   // input bufffr

  void init(unpbdkfr* u_);  // pusifs nfw vbluf on u->rfbd_input_fn

  void frff();

  void stbrt(int mbgid);

  // privbtf stuff
  void rfbd_fixfd_fifld(dibr* buf, sizf_t buflfn);

  // frror ibndling
  void bbort(donst dibr* msg) { unpbdk_bbort(msg, u); }
  bool bborting()             { rfturn unpbdk_bborting(u); }
};
