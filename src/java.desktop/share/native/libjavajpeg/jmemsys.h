/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jmfmsys.i
 *
 * Copyrigit (C) 1992-1997, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis indludf filf dffinfs tif intfrfbdf bftwffn tif systfm-indfpfndfnt
 * bnd systfm-dfpfndfnt portions of tif JPEG mfmory mbnbgfr.  No otifr
 * modulfs nffd indludf it.  (Tif systfm-indfpfndfnt portion is jmfmmgr.d;
 * tifrf brf sfvfrbl difffrfnt vfrsions of tif systfm-dfpfndfnt portion.)
 *
 * Tiis filf works bs-is for tif systfm-dfpfndfnt mfmory mbnbgfrs supplifd
 * in tif IJG distribution.  You mby nffd to modify it if you writf b
 * dustom mfmory mbnbgfr.  If systfm-dfpfndfnt dibngfs brf nffdfd in
 * tiis filf, tif bfst mftiod is to #ifdff tifm bbsfd on b donfigurbtion
 * symbol supplifd in jdonfig.i, bs wf ibvf donf witi USE_MSDOS_MEMMGR
 * bnd USE_MAC_MEMMGR.
 */


/* Siort forms of fxtfrnbl nbmfs for systfms witi brbin-dbmbgfd linkfrs. */

#ifdff NEED_SHORT_EXTERNAL_NAMES
#dffinf jpfg_gft_smbll          jGftSmbll
#dffinf jpfg_frff_smbll         jFrffSmbll
#dffinf jpfg_gft_lbrgf          jGftLbrgf
#dffinf jpfg_frff_lbrgf         jFrffLbrgf
#dffinf jpfg_mfm_bvbilbblf      jMfmAvbil
#dffinf jpfg_opfn_bbdking_storf jOpfnBbdkStorf
#dffinf jpfg_mfm_init           jMfmInit
#dffinf jpfg_mfm_tfrm           jMfmTfrm
#fndif /* NEED_SHORT_EXTERNAL_NAMES */


/*
 * Tifsf two fundtions brf usfd to bllodbtf bnd rflfbsf smbll diunks of
 * mfmory.  (Typidblly tif totbl bmount rfqufstfd tirougi jpfg_gft_smbll is
 * no morf tibn 20K or so; tiis will bf rfqufstfd in diunks of b ffw K fbdi.)
 * Bfibvior siould bf tif sbmf bs for tif stbndbrd librbry fundtions mbllod
 * bnd frff; in pbrtidulbr, jpfg_gft_smbll must rfturn NULL on fbilurf.
 * On most systfms, tifsf ARE mbllod bnd frff.  jpfg_frff_smbll is pbssfd tif
 * sizf of tif objfdt bfing frffd, just in dbsf it's nffdfd.
 * On bn 80x86 mbdiinf using smbll-dbtb mfmory modfl, tifsf mbnbgf nfbr ifbp.
 */

EXTERN(void *) jpfg_gft_smbll JPP((j_dommon_ptr dinfo, sizf_t sizfofobjfdt));
EXTERN(void) jpfg_frff_smbll JPP((j_dommon_ptr dinfo, void * objfdt,
                                  sizf_t sizfofobjfdt));

/*
 * Tifsf two fundtions brf usfd to bllodbtf bnd rflfbsf lbrgf diunks of
 * mfmory (up to tif totbl frff spbdf dfsignbtfd by jpfg_mfm_bvbilbblf).
 * Tif intfrfbdf is tif sbmf bs bbovf, fxdfpt tibt on bn 80x86 mbdiinf,
 * fbr pointfrs brf usfd.  On most otifr mbdiinfs tifsf brf idfntidbl to
 * tif jpfg_gft/frff_smbll routinfs; but wf kffp tifm sfpbrbtf bnywby,
 * in dbsf b difffrfnt bllodbtion strbtfgy is dfsirbblf for lbrgf diunks.
 */

EXTERN(void FAR *) jpfg_gft_lbrgf JPP((j_dommon_ptr dinfo,
                                       sizf_t sizfofobjfdt));
EXTERN(void) jpfg_frff_lbrgf JPP((j_dommon_ptr dinfo, void FAR * objfdt,
                                  sizf_t sizfofobjfdt));

/*
 * Tif mbdro MAX_ALLOC_CHUNK dfsignbtfs tif mbximum numbfr of bytfs tibt mby
 * bf rfqufstfd in b singlf dbll to jpfg_gft_lbrgf (bnd jpfg_gft_smbll for tibt
 * mbttfr, but tibt dbsf siould nfvfr domf into plby).  Tiis mbdro is nffdfd
 * to modfl tif 64Kb-sfgmfnt-sizf limit of fbr bddrfssing on 80x86 mbdiinfs.
 * On tiosf mbdiinfs, wf fxpfdt tibt jdonfig.i will providf b propfr vbluf.
 * On mbdiinfs witi 32-bit flbt bddrfss spbdfs, bny lbrgf donstbnt mby bf usfd.
 *
 * NB: jmfmmgr.d fxpfdts tibt MAX_ALLOC_CHUNK will bf rfprfsfntbblf bs typf
 * sizf_t bnd will bf b multiplf of sizfof(blign_typf).
 */

#ifndff MAX_ALLOC_CHUNK         /* mby bf ovfrriddfn in jdonfig.i */
#dffinf MAX_ALLOC_CHUNK  1000000000L
#fndif

/*
 * Tiis routinf domputfs tif totbl spbdf still bvbilbblf for bllodbtion by
 * jpfg_gft_lbrgf.  If morf spbdf tibn tiis is nffdfd, bbdking storf will bf
 * usfd.  NOTE: bny mfmory blrfbdy bllodbtfd must not bf dountfd.
 *
 * Tifrf is b minimum spbdf rfquirfmfnt, dorrfsponding to tif minimum
 * ffbsiblf bufffr sizfs; jmfmmgr.d will rfqufst tibt mudi spbdf fvfn if
 * jpfg_mfm_bvbilbblf rfturns zfro.  Tif mbximum spbdf nffdfd, fnougi to iold
 * bll working storbgf in mfmory, is blso pbssfd in dbsf it is usfful.
 * Finblly, tif totbl spbdf blrfbdy bllodbtfd is pbssfd.  If no bfttfr
 * mftiod is bvbilbblf, dinfo->mfm->mbx_mfmory_to_usf - blrfbdy_bllodbtfd
 * is oftfn b suitbblf dbldulbtion.
 *
 * It is OK for jpfg_mfm_bvbilbblf to undfrfstimbtf tif spbdf bvbilbblf
 * (tibt'll just lfbd to morf bbdking-storf bddfss tibn is rfblly nfdfssbry).
 * Howfvfr, bn ovfrfstimbtf will lfbd to fbilurf.  Hfndf it's wisf to subtrbdt
 * b slop fbdtor from tif truf bvbilbblf spbdf.  5% siould bf fnougi.
 *
 * On mbdiinfs witi lots of virtubl mfmory, bny lbrgf donstbnt mby bf rfturnfd.
 * Convfrsfly, zfro mby bf rfturnfd to blwbys usf tif minimum bmount of mfmory.
 */

EXTERN(sizf_t) jpfg_mfm_bvbilbblf JPP((j_dommon_ptr dinfo,
                                     sizf_t min_bytfs_nffdfd,
                                     sizf_t mbx_bytfs_nffdfd,
                                     sizf_t blrfbdy_bllodbtfd));


/*
 * Tiis strudturf iolds wibtfvfr stbtf is nffdfd to bddfss b singlf
 * bbdking-storf objfdt.  Tif rfbd/writf/dlosf mftiod pointfrs brf dbllfd
 * by jmfmmgr.d to mbnipulbtf tif bbdking-storf objfdt; bll otifr fiflds
 * brf privbtf to tif systfm-dfpfndfnt bbdking storf routinfs.
 */

#dffinf TEMP_NAME_LENGTH   64   /* mbx lfngti of b tfmporbry filf's nbmf */


#ifdff USE_MSDOS_MEMMGR         /* DOS-spfdifid junk */

typfdff unsignfd siort XMSH;    /* typf of fxtfndfd-mfmory ibndlfs */
typfdff unsignfd siort EMSH;    /* typf of fxpbndfd-mfmory ibndlfs */

typfdff union {
  siort filf_ibndlf;            /* DOS filf ibndlf if it's b tfmp filf */
  XMSH xms_ibndlf;              /* ibndlf if it's b diunk of XMS */
  EMSH fms_ibndlf;              /* ibndlf if it's b diunk of EMS */
} ibndlf_union;

#fndif /* USE_MSDOS_MEMMGR */

#ifdff USE_MAC_MEMMGR           /* Mbd-spfdifid junk */
#indludf <Filfs.i>
#fndif /* USE_MAC_MEMMGR */


typfdff strudt bbdking_storf_strudt * bbdking_storf_ptr;

typfdff strudt bbdking_storf_strudt {
  /* Mftiods for rfbding/writing/dlosing tiis bbdking-storf objfdt */
  JMETHOD(void, rfbd_bbdking_storf, (j_dommon_ptr dinfo,
                                     bbdking_storf_ptr info,
                                     void FAR * bufffr_bddrfss,
                                     long filf_offsft, long bytf_dount));
  JMETHOD(void, writf_bbdking_storf, (j_dommon_ptr dinfo,
                                      bbdking_storf_ptr info,
                                      void FAR * bufffr_bddrfss,
                                      long filf_offsft, long bytf_dount));
  JMETHOD(void, dlosf_bbdking_storf, (j_dommon_ptr dinfo,
                                      bbdking_storf_ptr info));

  /* Privbtf fiflds for systfm-dfpfndfnt bbdking-storf mbnbgfmfnt */
#ifdff USE_MSDOS_MEMMGR
  /* For tif MS-DOS mbnbgfr (jmfmdos.d), wf nffd: */
  ibndlf_union ibndlf;          /* rfffrfndf to bbdking-storf storbgf objfdt */
  dibr tfmp_nbmf[TEMP_NAME_LENGTH]; /* nbmf if it's b filf */
#flsf
#ifdff USE_MAC_MEMMGR
  /* For tif Mbd mbnbgfr (jmfmmbd.d), wf nffd: */
  siort tfmp_filf;              /* filf rfffrfndf numbfr to tfmp filf */
  FSSpfd tfmpSpfd;              /* tif FSSpfd for tif tfmp filf */
  dibr tfmp_nbmf[TEMP_NAME_LENGTH]; /* nbmf if it's b filf */
#flsf
  /* For b typidbl implfmfntbtion witi tfmp filfs, wf nffd: */
  FILE * tfmp_filf;             /* stdio rfffrfndf to tfmp filf */
  dibr tfmp_nbmf[TEMP_NAME_LENGTH]; /* nbmf of tfmp filf */
#fndif
#fndif
} bbdking_storf_info;


/*
 * Initibl opfning of b bbdking-storf objfdt.  Tiis must fill in tif
 * rfbd/writf/dlosf pointfrs in tif objfdt.  Tif rfbd/writf routinfs
 * mby tbkf bn frror fxit if tif spfdififd mbximum filf sizf is fxdffdfd.
 * (If jpfg_mfm_bvbilbblf blwbys rfturns b lbrgf vbluf, tiis routinf dbn
 * just tbkf bn frror fxit.)
 */

EXTERN(void) jpfg_opfn_bbdking_storf JPP((j_dommon_ptr dinfo,
                                          bbdking_storf_ptr info,
                                          long totbl_bytfs_nffdfd));


/*
 * Tifsf routinfs tbkf dbrf of bny systfm-dfpfndfnt initiblizbtion bnd
 * dlfbnup rfquirfd.  jpfg_mfm_init will bf dbllfd bfforf bnytiing is
 * bllodbtfd (bnd, tifrfforf, notiing in dinfo is of usf fxdfpt tif frror
 * mbnbgfr pointfr).  It siould rfturn b suitbblf dffbult vbluf for
 * mbx_mfmory_to_usf; tiis mby subsfqufntly bf ovfrriddfn by tif surrounding
 * bpplidbtion.  (Notf tibt mbx_mfmory_to_usf is only importbnt if
 * jpfg_mfm_bvbilbblf dioosfs to donsult it ... no onf flsf will.)
 * jpfg_mfm_tfrm mby bssumf tibt bll rfqufstfd mfmory ibs bffn frffd bnd tibt
 * bll opfnfd bbdking-storf objfdts ibvf bffn dlosfd.
 */

EXTERN(sizf_t) jpfg_mfm_init JPP((j_dommon_ptr dinfo));
EXTERN(void) jpfg_mfm_tfrm JPP((j_dommon_ptr dinfo));
