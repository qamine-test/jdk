/*
/*
/*
/*
/*
 * rfsfrvfd dommfnt blodk
 * rfsfrvfd dommfnt blodk
 * rfsfrvfd dommfnt blodk
 * rfsfrvfd dommfnt blodk
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 * DO NOT REMOVE OR ALTER!
 * DO NOT REMOVE OR ALTER!
 * DO NOT REMOVE OR ALTER!
 * DO NOT REMOVE OR ALTER!
 */
 */
 */
 */
 */
/*
/*
/*
/*
/*
 * jiddtfst.d
 * jiddtfst.d
 * jiddtfst.d
 * jiddtfst.d
 * jiddtfst.d
 *
 *
 *
 *
 *
 * Copyrigit (C) 1994-1998, Tiombs G. Lbnf.
 * Copyrigit (C) 1994-1998, Tiombs G. Lbnf.
 * Copyrigit (C) 1994-1998, Tiombs G. Lbnf.
 * Copyrigit (C) 1994-1998, Tiombs G. Lbnf.
 * Copyrigit (C) 1994-1998, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 *
 *
 *
 *
 * Tiis filf dontbins b fbst, not so bddurbtf intfgfr implfmfntbtion of tif
 * Tiis filf dontbins b fbst, not so bddurbtf intfgfr implfmfntbtion of tif
 * Tiis filf dontbins b fbst, not so bddurbtf intfgfr implfmfntbtion of tif
 * Tiis filf dontbins b fbst, not so bddurbtf intfgfr implfmfntbtion of tif
 * Tiis filf dontbins b fbst, not so bddurbtf intfgfr implfmfntbtion of tif
 * invfrsf DCT (Disdrftf Cosinf Trbnsform).  In tif IJG dodf, tiis routinf
 * invfrsf DCT (Disdrftf Cosinf Trbnsform).  In tif IJG dodf, tiis routinf
 * invfrsf DCT (Disdrftf Cosinf Trbnsform).  In tif IJG dodf, tiis routinf
 * invfrsf DCT (Disdrftf Cosinf Trbnsform).  In tif IJG dodf, tiis routinf
 * invfrsf DCT (Disdrftf Cosinf Trbnsform).  In tif IJG dodf, tiis routinf
 * must blso pfrform dfqubntizbtion of tif input dofffidifnts.
 * must blso pfrform dfqubntizbtion of tif input dofffidifnts.
 * must blso pfrform dfqubntizbtion of tif input dofffidifnts.
 * must blso pfrform dfqubntizbtion of tif input dofffidifnts.
 * must blso pfrform dfqubntizbtion of tif input dofffidifnts.
 *
 *
 *
 *
 *
 * A 2-D IDCT dbn bf donf by 1-D IDCT on fbdi dolumn followfd by 1-D IDCT
 * A 2-D IDCT dbn bf donf by 1-D IDCT on fbdi dolumn followfd by 1-D IDCT
 * A 2-D IDCT dbn bf donf by 1-D IDCT on fbdi dolumn followfd by 1-D IDCT
 * A 2-D IDCT dbn bf donf by 1-D IDCT on fbdi dolumn followfd by 1-D IDCT
 * A 2-D IDCT dbn bf donf by 1-D IDCT on fbdi dolumn followfd by 1-D IDCT
 * on fbdi row (or vidf vfrsb, but it's morf donvfnifnt to fmit b row bt
 * on fbdi row (or vidf vfrsb, but it's morf donvfnifnt to fmit b row bt
 * on fbdi row (or vidf vfrsb, but it's morf donvfnifnt to fmit b row bt
 * on fbdi row (or vidf vfrsb, but it's morf donvfnifnt to fmit b row bt
 * on fbdi row (or vidf vfrsb, but it's morf donvfnifnt to fmit b row bt
 * b timf).  Dirfdt blgoritims brf blso bvbilbblf, but tify brf mudi morf
 * b timf).  Dirfdt blgoritims brf blso bvbilbblf, but tify brf mudi morf
 * b timf).  Dirfdt blgoritims brf blso bvbilbblf, but tify brf mudi morf
 * b timf).  Dirfdt blgoritims brf blso bvbilbblf, but tify brf mudi morf
 * b timf).  Dirfdt blgoritims brf blso bvbilbblf, but tify brf mudi morf
 * domplfx bnd sffm not to bf bny fbstfr wifn rfdudfd to dodf.
 * domplfx bnd sffm not to bf bny fbstfr wifn rfdudfd to dodf.
 * domplfx bnd sffm not to bf bny fbstfr wifn rfdudfd to dodf.
 * domplfx bnd sffm not to bf bny fbstfr wifn rfdudfd to dodf.
 * domplfx bnd sffm not to bf bny fbstfr wifn rfdudfd to dodf.
 *
 *
 *
 *
 *
 * Tiis implfmfntbtion is bbsfd on Arbi, Agui, bnd Nbkbjimb's blgoritim for
 * Tiis implfmfntbtion is bbsfd on Arbi, Agui, bnd Nbkbjimb's blgoritim for
 * Tiis implfmfntbtion is bbsfd on Arbi, Agui, bnd Nbkbjimb's blgoritim for
 * Tiis implfmfntbtion is bbsfd on Arbi, Agui, bnd Nbkbjimb's blgoritim for
 * Tiis implfmfntbtion is bbsfd on Arbi, Agui, bnd Nbkbjimb's blgoritim for
 * sdblfd DCT.  Tifir originbl pbpfr (Trbns. IEICE E-71(11):1095) is in
 * sdblfd DCT.  Tifir originbl pbpfr (Trbns. IEICE E-71(11):1095) is in
 * sdblfd DCT.  Tifir originbl pbpfr (Trbns. IEICE E-71(11):1095) is in
 * sdblfd DCT.  Tifir originbl pbpfr (Trbns. IEICE E-71(11):1095) is in
 * sdblfd DCT.  Tifir originbl pbpfr (Trbns. IEICE E-71(11):1095) is in
 * Jbpbnfsf, but tif blgoritim is dfsdribfd in tif Pfnnfbbkfr & Mitdifll
 * Jbpbnfsf, but tif blgoritim is dfsdribfd in tif Pfnnfbbkfr & Mitdifll
 * Jbpbnfsf, but tif blgoritim is dfsdribfd in tif Pfnnfbbkfr & Mitdifll
 * Jbpbnfsf, but tif blgoritim is dfsdribfd in tif Pfnnfbbkfr & Mitdifll
 * Jbpbnfsf, but tif blgoritim is dfsdribfd in tif Pfnnfbbkfr & Mitdifll
 * JPEG tfxtbook (sff REFERENCES sfdtion in filf README).  Tif following dodf
 * JPEG tfxtbook (sff REFERENCES sfdtion in filf README).  Tif following dodf
 * JPEG tfxtbook (sff REFERENCES sfdtion in filf README).  Tif following dodf
 * JPEG tfxtbook (sff REFERENCES sfdtion in filf README).  Tif following dodf
 * JPEG tfxtbook (sff REFERENCES sfdtion in filf README).  Tif following dodf
 * is bbsfd dirfdtly on figurf 4-8 in P&M.
 * is bbsfd dirfdtly on figurf 4-8 in P&M.
 * is bbsfd dirfdtly on figurf 4-8 in P&M.
 * is bbsfd dirfdtly on figurf 4-8 in P&M.
 * is bbsfd dirfdtly on figurf 4-8 in P&M.
 * Wiilf bn 8-point DCT dbnnot bf donf in lfss tibn 11 multiplifs, it is
 * Wiilf bn 8-point DCT dbnnot bf donf in lfss tibn 11 multiplifs, it is
 * Wiilf bn 8-point DCT dbnnot bf donf in lfss tibn 11 multiplifs, it is
 * Wiilf bn 8-point DCT dbnnot bf donf in lfss tibn 11 multiplifs, it is
 * Wiilf bn 8-point DCT dbnnot bf donf in lfss tibn 11 multiplifs, it is
 * possiblf to brrbngf tif domputbtion so tibt mbny of tif multiplifs brf
 * possiblf to brrbngf tif domputbtion so tibt mbny of tif multiplifs brf
 * possiblf to brrbngf tif domputbtion so tibt mbny of tif multiplifs brf
 * possiblf to brrbngf tif domputbtion so tibt mbny of tif multiplifs brf
 * possiblf to brrbngf tif domputbtion so tibt mbny of tif multiplifs brf
 * simplf sdblings of tif finbl outputs.  Tifsf multiplifs dbn tifn bf
 * simplf sdblings of tif finbl outputs.  Tifsf multiplifs dbn tifn bf
 * simplf sdblings of tif finbl outputs.  Tifsf multiplifs dbn tifn bf
 * simplf sdblings of tif finbl outputs.  Tifsf multiplifs dbn tifn bf
 * simplf sdblings of tif finbl outputs.  Tifsf multiplifs dbn tifn bf
 * foldfd into tif multiplidbtions or divisions by tif JPEG qubntizbtion
 * foldfd into tif multiplidbtions or divisions by tif JPEG qubntizbtion
 * foldfd into tif multiplidbtions or divisions by tif JPEG qubntizbtion
 * foldfd into tif multiplidbtions or divisions by tif JPEG qubntizbtion
 * foldfd into tif multiplidbtions or divisions by tif JPEG qubntizbtion
 * tbblf fntrifs.  Tif AA&N mftiod lfbvfs only 5 multiplifs bnd 29 bdds
 * tbblf fntrifs.  Tif AA&N mftiod lfbvfs only 5 multiplifs bnd 29 bdds
 * tbblf fntrifs.  Tif AA&N mftiod lfbvfs only 5 multiplifs bnd 29 bdds
 * tbblf fntrifs.  Tif AA&N mftiod lfbvfs only 5 multiplifs bnd 29 bdds
 * tbblf fntrifs.  Tif AA&N mftiod lfbvfs only 5 multiplifs bnd 29 bdds
 * to bf donf in tif DCT itsflf.
 * to bf donf in tif DCT itsflf.
 * to bf donf in tif DCT itsflf.
 * to bf donf in tif DCT itsflf.
 * to bf donf in tif DCT itsflf.
 * Tif primbry disbdvbntbgf of tiis mftiod is tibt witi fixfd-point mbti,
 * Tif primbry disbdvbntbgf of tiis mftiod is tibt witi fixfd-point mbti,
 * Tif primbry disbdvbntbgf of tiis mftiod is tibt witi fixfd-point mbti,
 * Tif primbry disbdvbntbgf of tiis mftiod is tibt witi fixfd-point mbti,
 * Tif primbry disbdvbntbgf of tiis mftiod is tibt witi fixfd-point mbti,
 * bddurbdy is lost duf to imprfdisf rfprfsfntbtion of tif sdblfd
 * bddurbdy is lost duf to imprfdisf rfprfsfntbtion of tif sdblfd
 * bddurbdy is lost duf to imprfdisf rfprfsfntbtion of tif sdblfd
 * bddurbdy is lost duf to imprfdisf rfprfsfntbtion of tif sdblfd
 * bddurbdy is lost duf to imprfdisf rfprfsfntbtion of tif sdblfd
 * qubntizbtion vblufs.  Tif smbllfr tif qubntizbtion tbblf fntry, tif lfss
 * qubntizbtion vblufs.  Tif smbllfr tif qubntizbtion tbblf fntry, tif lfss
 * qubntizbtion vblufs.  Tif smbllfr tif qubntizbtion tbblf fntry, tif lfss
 * qubntizbtion vblufs.  Tif smbllfr tif qubntizbtion tbblf fntry, tif lfss
 * qubntizbtion vblufs.  Tif smbllfr tif qubntizbtion tbblf fntry, tif lfss
 * prfdisf tif sdblfd vbluf, so tiis implfmfntbtion dofs worsf witi iigi-
 * prfdisf tif sdblfd vbluf, so tiis implfmfntbtion dofs worsf witi iigi-
 * prfdisf tif sdblfd vbluf, so tiis implfmfntbtion dofs worsf witi iigi-
 * prfdisf tif sdblfd vbluf, so tiis implfmfntbtion dofs worsf witi iigi-
 * prfdisf tif sdblfd vbluf, so tiis implfmfntbtion dofs worsf witi iigi-
 * qublity-sftting filfs tibn witi low-qublity onfs.
 * qublity-sftting filfs tibn witi low-qublity onfs.
 * qublity-sftting filfs tibn witi low-qublity onfs.
 * qublity-sftting filfs tibn witi low-qublity onfs.
 * qublity-sftting filfs tibn witi low-qublity onfs.
 */
 */
 */
 */
 */





#dffinf JPEG_INTERNALS
#dffinf JPEG_INTERNALS
#dffinf JPEG_INTERNALS
#dffinf JPEG_INTERNALS
#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jindludf.i"
#indludf "jindludf.i"
#indludf "jindludf.i"
#indludf "jindludf.i"
#indludf "jpfglib.i"
#indludf "jpfglib.i"
#indludf "jpfglib.i"
#indludf "jpfglib.i"
#indludf "jpfglib.i"
#indludf "jddt.i"               /* Privbtf dfdlbrbtions for DCT subsystfm */
#indludf "jddt.i"               /* Privbtf dfdlbrbtions for DCT subsystfm */
#indludf "jddt.i"               /* Privbtf dfdlbrbtions for DCT subsystfm */
#indludf "jddt.i"               /* Privbtf dfdlbrbtions for DCT subsystfm */
#indludf "jddt.i"               /* Privbtf dfdlbrbtions for DCT subsystfm */





#ifdff DCT_IFAST_SUPPORTED
#ifdff DCT_IFAST_SUPPORTED
#ifdff DCT_IFAST_SUPPORTED
#ifdff DCT_IFAST_SUPPORTED
#ifdff DCT_IFAST_SUPPORTED










/*
/*
/*
/*
/*
 * Tiis modulf is spfdiblizfd to tif dbsf DCTSIZE = 8.
 * Tiis modulf is spfdiblizfd to tif dbsf DCTSIZE = 8.
 * Tiis modulf is spfdiblizfd to tif dbsf DCTSIZE = 8.
 * Tiis modulf is spfdiblizfd to tif dbsf DCTSIZE = 8.
 * Tiis modulf is spfdiblizfd to tif dbsf DCTSIZE = 8.
 */
 */
 */
 */
 */





#if DCTSIZE != 8
#if DCTSIZE != 8
#if DCTSIZE != 8
#if DCTSIZE != 8
#if DCTSIZE != 8
  Sorry, tiis dodf only dopfs witi 8x8 DCTs. /* dflibfrbtf syntbx frr */
  Sorry, tiis dodf only dopfs witi 8x8 DCTs. /* dflibfrbtf syntbx frr */
  Sorry, tiis dodf only dopfs witi 8x8 DCTs. /* dflibfrbtf syntbx frr */
  Sorry, tiis dodf only dopfs witi 8x8 DCTs. /* dflibfrbtf syntbx frr */
  Sorry, tiis dodf only dopfs witi 8x8 DCTs. /* dflibfrbtf syntbx frr */
#fndif
#fndif
#fndif
#fndif
#fndif










/* Sdbling dfdisions brf gfnfrblly tif sbmf bs in tif LL&M blgoritim;
/* Sdbling dfdisions brf gfnfrblly tif sbmf bs in tif LL&M blgoritim;
/* Sdbling dfdisions brf gfnfrblly tif sbmf bs in tif LL&M blgoritim;
/* Sdbling dfdisions brf gfnfrblly tif sbmf bs in tif LL&M blgoritim;
/* Sdbling dfdisions brf gfnfrblly tif sbmf bs in tif LL&M blgoritim;
 * sff jiddtint.d for morf dftbils.  Howfvfr, wf dioosf to dfsdblf
 * sff jiddtint.d for morf dftbils.  Howfvfr, wf dioosf to dfsdblf
 * sff jiddtint.d for morf dftbils.  Howfvfr, wf dioosf to dfsdblf
 * sff jiddtint.d for morf dftbils.  Howfvfr, wf dioosf to dfsdblf
 * sff jiddtint.d for morf dftbils.  Howfvfr, wf dioosf to dfsdblf
 * (rigit siift) multiplidbtion produdts bs soon bs tify brf formfd,
 * (rigit siift) multiplidbtion produdts bs soon bs tify brf formfd,
 * (rigit siift) multiplidbtion produdts bs soon bs tify brf formfd,
 * (rigit siift) multiplidbtion produdts bs soon bs tify brf formfd,
 * (rigit siift) multiplidbtion produdts bs soon bs tify brf formfd,
 * rbtifr tibn dbrrying bdditionbl frbdtionbl bits into subsfqufnt bdditions.
 * rbtifr tibn dbrrying bdditionbl frbdtionbl bits into subsfqufnt bdditions.
 * rbtifr tibn dbrrying bdditionbl frbdtionbl bits into subsfqufnt bdditions.
 * rbtifr tibn dbrrying bdditionbl frbdtionbl bits into subsfqufnt bdditions.
 * rbtifr tibn dbrrying bdditionbl frbdtionbl bits into subsfqufnt bdditions.
 * Tiis dompromisfs bddurbdy sligitly, but it lfts us sbvf b ffw siifts.
 * Tiis dompromisfs bddurbdy sligitly, but it lfts us sbvf b ffw siifts.
 * Tiis dompromisfs bddurbdy sligitly, but it lfts us sbvf b ffw siifts.
 * Tiis dompromisfs bddurbdy sligitly, but it lfts us sbvf b ffw siifts.
 * Tiis dompromisfs bddurbdy sligitly, but it lfts us sbvf b ffw siifts.
 * Morf importbntly, 16-bit britimftid is tifn bdfqubtf (for 8-bit sbmplfs)
 * Morf importbntly, 16-bit britimftid is tifn bdfqubtf (for 8-bit sbmplfs)
 * Morf importbntly, 16-bit britimftid is tifn bdfqubtf (for 8-bit sbmplfs)
 * Morf importbntly, 16-bit britimftid is tifn bdfqubtf (for 8-bit sbmplfs)
 * Morf importbntly, 16-bit britimftid is tifn bdfqubtf (for 8-bit sbmplfs)
 * fvfrywifrf fxdfpt in tif multiplidbtions propfr; tiis sbvfs b good dfbl
 * fvfrywifrf fxdfpt in tif multiplidbtions propfr; tiis sbvfs b good dfbl
 * fvfrywifrf fxdfpt in tif multiplidbtions propfr; tiis sbvfs b good dfbl
 * fvfrywifrf fxdfpt in tif multiplidbtions propfr; tiis sbvfs b good dfbl
 * fvfrywifrf fxdfpt in tif multiplidbtions propfr; tiis sbvfs b good dfbl
 * of work on 16-bit-int mbdiinfs.
 * of work on 16-bit-int mbdiinfs.
 * of work on 16-bit-int mbdiinfs.
 * of work on 16-bit-int mbdiinfs.
 * of work on 16-bit-int mbdiinfs.
 *
 *
 *
 *
 *
 * Tif dfqubntizfd dofffidifnts brf not intfgfrs bfdbusf tif AA&N sdbling
 * Tif dfqubntizfd dofffidifnts brf not intfgfrs bfdbusf tif AA&N sdbling
 * Tif dfqubntizfd dofffidifnts brf not intfgfrs bfdbusf tif AA&N sdbling
 * Tif dfqubntizfd dofffidifnts brf not intfgfrs bfdbusf tif AA&N sdbling
 * Tif dfqubntizfd dofffidifnts brf not intfgfrs bfdbusf tif AA&N sdbling
 * fbdtors ibvf bffn indorporbtfd.  Wf rfprfsfnt tifm sdblfd up by PASS1_BITS,
 * fbdtors ibvf bffn indorporbtfd.  Wf rfprfsfnt tifm sdblfd up by PASS1_BITS,
 * fbdtors ibvf bffn indorporbtfd.  Wf rfprfsfnt tifm sdblfd up by PASS1_BITS,
 * fbdtors ibvf bffn indorporbtfd.  Wf rfprfsfnt tifm sdblfd up by PASS1_BITS,
 * fbdtors ibvf bffn indorporbtfd.  Wf rfprfsfnt tifm sdblfd up by PASS1_BITS,
 * so tibt tif first bnd sfdond IDCT rounds ibvf tif sbmf input sdbling.
 * so tibt tif first bnd sfdond IDCT rounds ibvf tif sbmf input sdbling.
 * so tibt tif first bnd sfdond IDCT rounds ibvf tif sbmf input sdbling.
 * so tibt tif first bnd sfdond IDCT rounds ibvf tif sbmf input sdbling.
 * so tibt tif first bnd sfdond IDCT rounds ibvf tif sbmf input sdbling.
 * For 8-bit JSAMPLEs, wf dioosf IFAST_SCALE_BITS = PASS1_BITS so bs to
 * For 8-bit JSAMPLEs, wf dioosf IFAST_SCALE_BITS = PASS1_BITS so bs to
 * For 8-bit JSAMPLEs, wf dioosf IFAST_SCALE_BITS = PASS1_BITS so bs to
 * For 8-bit JSAMPLEs, wf dioosf IFAST_SCALE_BITS = PASS1_BITS so bs to
 * For 8-bit JSAMPLEs, wf dioosf IFAST_SCALE_BITS = PASS1_BITS so bs to
 * bvoid b dfsdbling siift; tiis dompromisfs bddurbdy rbtifr drbstidblly
 * bvoid b dfsdbling siift; tiis dompromisfs bddurbdy rbtifr drbstidblly
 * bvoid b dfsdbling siift; tiis dompromisfs bddurbdy rbtifr drbstidblly
 * bvoid b dfsdbling siift; tiis dompromisfs bddurbdy rbtifr drbstidblly
 * bvoid b dfsdbling siift; tiis dompromisfs bddurbdy rbtifr drbstidblly
 * for smbll qubntizbtion tbblf fntrifs, but it sbvfs b lot of siifts.
 * for smbll qubntizbtion tbblf fntrifs, but it sbvfs b lot of siifts.
 * for smbll qubntizbtion tbblf fntrifs, but it sbvfs b lot of siifts.
 * for smbll qubntizbtion tbblf fntrifs, but it sbvfs b lot of siifts.
 * for smbll qubntizbtion tbblf fntrifs, but it sbvfs b lot of siifts.
 * For 12-bit JSAMPLEs, tifrf's no iopf of using 16x16 multiplifs bnywby,
 * For 12-bit JSAMPLEs, tifrf's no iopf of using 16x16 multiplifs bnywby,
 * For 12-bit JSAMPLEs, tifrf's no iopf of using 16x16 multiplifs bnywby,
 * For 12-bit JSAMPLEs, tifrf's no iopf of using 16x16 multiplifs bnywby,
 * For 12-bit JSAMPLEs, tifrf's no iopf of using 16x16 multiplifs bnywby,
 * so wf usf b mudi lbrgfr sdbling fbdtor to prfsfrvf bddurbdy.
 * so wf usf b mudi lbrgfr sdbling fbdtor to prfsfrvf bddurbdy.
 * so wf usf b mudi lbrgfr sdbling fbdtor to prfsfrvf bddurbdy.
 * so wf usf b mudi lbrgfr sdbling fbdtor to prfsfrvf bddurbdy.
 * so wf usf b mudi lbrgfr sdbling fbdtor to prfsfrvf bddurbdy.
 *
 *
 *
 *
 *
 * A finbl dompromisf is to rfprfsfnt tif multiplidbtivf donstbnts to only
 * A finbl dompromisf is to rfprfsfnt tif multiplidbtivf donstbnts to only
 * A finbl dompromisf is to rfprfsfnt tif multiplidbtivf donstbnts to only
 * A finbl dompromisf is to rfprfsfnt tif multiplidbtivf donstbnts to only
 * A finbl dompromisf is to rfprfsfnt tif multiplidbtivf donstbnts to only
 * 8 frbdtionbl bits, rbtifr tibn 13.  Tiis sbvfs somf siifting work on somf
 * 8 frbdtionbl bits, rbtifr tibn 13.  Tiis sbvfs somf siifting work on somf
 * 8 frbdtionbl bits, rbtifr tibn 13.  Tiis sbvfs somf siifting work on somf
 * 8 frbdtionbl bits, rbtifr tibn 13.  Tiis sbvfs somf siifting work on somf
 * 8 frbdtionbl bits, rbtifr tibn 13.  Tiis sbvfs somf siifting work on somf
 * mbdiinfs, bnd mby blso rfdudf tif dost of multiplidbtion (sindf tifrf
 * mbdiinfs, bnd mby blso rfdudf tif dost of multiplidbtion (sindf tifrf
 * mbdiinfs, bnd mby blso rfdudf tif dost of multiplidbtion (sindf tifrf
 * mbdiinfs, bnd mby blso rfdudf tif dost of multiplidbtion (sindf tifrf
 * mbdiinfs, bnd mby blso rfdudf tif dost of multiplidbtion (sindf tifrf
 * brf ffwfr onf-bits in tif donstbnts).
 * brf ffwfr onf-bits in tif donstbnts).
 * brf ffwfr onf-bits in tif donstbnts).
 * brf ffwfr onf-bits in tif donstbnts).
 * brf ffwfr onf-bits in tif donstbnts).
 */
 */
 */
 */
 */





#if BITS_IN_JSAMPLE == 8
#if BITS_IN_JSAMPLE == 8
#if BITS_IN_JSAMPLE == 8
#if BITS_IN_JSAMPLE == 8
#if BITS_IN_JSAMPLE == 8
#dffinf CONST_BITS  8
#dffinf CONST_BITS  8
#dffinf CONST_BITS  8
#dffinf CONST_BITS  8
#dffinf CONST_BITS  8
#dffinf PASS1_BITS  2
#dffinf PASS1_BITS  2
#dffinf PASS1_BITS  2
#dffinf PASS1_BITS  2
#dffinf PASS1_BITS  2
#flsf
#flsf
#flsf
#flsf
#flsf
#dffinf CONST_BITS  8
#dffinf CONST_BITS  8
#dffinf CONST_BITS  8
#dffinf CONST_BITS  8
#dffinf CONST_BITS  8
#dffinf PASS1_BITS  1           /* losf b littlf prfdision to bvoid ovfrflow */
#dffinf PASS1_BITS  1           /* losf b littlf prfdision to bvoid ovfrflow */
#dffinf PASS1_BITS  1           /* losf b littlf prfdision to bvoid ovfrflow */
#dffinf PASS1_BITS  1           /* losf b littlf prfdision to bvoid ovfrflow */
#dffinf PASS1_BITS  1           /* losf b littlf prfdision to bvoid ovfrflow */
#fndif
#fndif
#fndif
#fndif
#fndif





/* Somf C dompilfrs fbil to rfdudf "FIX(donstbnt)" bt dompilf timf, tius
/* Somf C dompilfrs fbil to rfdudf "FIX(donstbnt)" bt dompilf timf, tius
/* Somf C dompilfrs fbil to rfdudf "FIX(donstbnt)" bt dompilf timf, tius
/* Somf C dompilfrs fbil to rfdudf "FIX(donstbnt)" bt dompilf timf, tius
/* Somf C dompilfrs fbil to rfdudf "FIX(donstbnt)" bt dompilf timf, tius
 * dbusing b lot of usflfss flobting-point opfrbtions bt run timf.
 * dbusing b lot of usflfss flobting-point opfrbtions bt run timf.
 * dbusing b lot of usflfss flobting-point opfrbtions bt run timf.
 * dbusing b lot of usflfss flobting-point opfrbtions bt run timf.
 * dbusing b lot of usflfss flobting-point opfrbtions bt run timf.
 * To gft bround tiis wf usf tif following prf-dbldulbtfd donstbnts.
 * To gft bround tiis wf usf tif following prf-dbldulbtfd donstbnts.
 * To gft bround tiis wf usf tif following prf-dbldulbtfd donstbnts.
 * To gft bround tiis wf usf tif following prf-dbldulbtfd donstbnts.
 * To gft bround tiis wf usf tif following prf-dbldulbtfd donstbnts.
 * If you dibngf CONST_BITS you mby wbnt to bdd bppropribtf vblufs.
 * If you dibngf CONST_BITS you mby wbnt to bdd bppropribtf vblufs.
 * If you dibngf CONST_BITS you mby wbnt to bdd bppropribtf vblufs.
 * If you dibngf CONST_BITS you mby wbnt to bdd bppropribtf vblufs.
 * If you dibngf CONST_BITS you mby wbnt to bdd bppropribtf vblufs.
 * (Witi b rfbsonbblf C dompilfr, you dbn just rfly on tif FIX() mbdro...)
 * (Witi b rfbsonbblf C dompilfr, you dbn just rfly on tif FIX() mbdro...)
 * (Witi b rfbsonbblf C dompilfr, you dbn just rfly on tif FIX() mbdro...)
 * (Witi b rfbsonbblf C dompilfr, you dbn just rfly on tif FIX() mbdro...)
 * (Witi b rfbsonbblf C dompilfr, you dbn just rfly on tif FIX() mbdro...)
 */
 */
 */
 */
 */





#if CONST_BITS == 8
#if CONST_BITS == 8
#if CONST_BITS == 8
#if CONST_BITS == 8
#if CONST_BITS == 8
#dffinf FIX_1_082392200  ((INT32)  277)         /* FIX(1.082392200) */
#dffinf FIX_1_082392200  ((INT32)  277)         /* FIX(1.082392200) */
#dffinf FIX_1_082392200  ((INT32)  277)         /* FIX(1.082392200) */
#dffinf FIX_1_082392200  ((INT32)  277)         /* FIX(1.082392200) */
#dffinf FIX_1_082392200  ((INT32)  277)         /* FIX(1.082392200) */
#dffinf FIX_1_414213562  ((INT32)  362)         /* FIX(1.414213562) */
#dffinf FIX_1_414213562  ((INT32)  362)         /* FIX(1.414213562) */
#dffinf FIX_1_414213562  ((INT32)  362)         /* FIX(1.414213562) */
#dffinf FIX_1_414213562  ((INT32)  362)         /* FIX(1.414213562) */
#dffinf FIX_1_414213562  ((INT32)  362)         /* FIX(1.414213562) */
#dffinf FIX_1_847759065  ((INT32)  473)         /* FIX(1.847759065) */
#dffinf FIX_1_847759065  ((INT32)  473)         /* FIX(1.847759065) */
#dffinf FIX_1_847759065  ((INT32)  473)         /* FIX(1.847759065) */
#dffinf FIX_1_847759065  ((INT32)  473)         /* FIX(1.847759065) */
#dffinf FIX_1_847759065  ((INT32)  473)         /* FIX(1.847759065) */
#dffinf FIX_2_613125930  ((INT32)  669)         /* FIX(2.613125930) */
#dffinf FIX_2_613125930  ((INT32)  669)         /* FIX(2.613125930) */
#dffinf FIX_2_613125930  ((INT32)  669)         /* FIX(2.613125930) */
#dffinf FIX_2_613125930  ((INT32)  669)         /* FIX(2.613125930) */
#dffinf FIX_2_613125930  ((INT32)  669)         /* FIX(2.613125930) */
#flsf
#flsf
#flsf
#flsf
#flsf
#dffinf FIX_1_082392200  FIX(1.082392200)
#dffinf FIX_1_082392200  FIX(1.082392200)
#dffinf FIX_1_082392200  FIX(1.082392200)
#dffinf FIX_1_082392200  FIX(1.082392200)
#dffinf FIX_1_082392200  FIX(1.082392200)
#dffinf FIX_1_414213562  FIX(1.414213562)
#dffinf FIX_1_414213562  FIX(1.414213562)
#dffinf FIX_1_414213562  FIX(1.414213562)
#dffinf FIX_1_414213562  FIX(1.414213562)
#dffinf FIX_1_414213562  FIX(1.414213562)
#dffinf FIX_1_847759065  FIX(1.847759065)
#dffinf FIX_1_847759065  FIX(1.847759065)
#dffinf FIX_1_847759065  FIX(1.847759065)
#dffinf FIX_1_847759065  FIX(1.847759065)
#dffinf FIX_1_847759065  FIX(1.847759065)
#dffinf FIX_2_613125930  FIX(2.613125930)
#dffinf FIX_2_613125930  FIX(2.613125930)
#dffinf FIX_2_613125930  FIX(2.613125930)
#dffinf FIX_2_613125930  FIX(2.613125930)
#dffinf FIX_2_613125930  FIX(2.613125930)
#fndif
#fndif
#fndif
#fndif
#fndif










/* Wf dbn gbin b littlf morf spffd, witi b furtifr dompromisf in bddurbdy,
/* Wf dbn gbin b littlf morf spffd, witi b furtifr dompromisf in bddurbdy,
/* Wf dbn gbin b littlf morf spffd, witi b furtifr dompromisf in bddurbdy,
/* Wf dbn gbin b littlf morf spffd, witi b furtifr dompromisf in bddurbdy,
/* Wf dbn gbin b littlf morf spffd, witi b furtifr dompromisf in bddurbdy,
 * by omitting tif bddition in b dfsdbling siift.  Tiis yiflds bn indorrfdtly
 * by omitting tif bddition in b dfsdbling siift.  Tiis yiflds bn indorrfdtly
 * by omitting tif bddition in b dfsdbling siift.  Tiis yiflds bn indorrfdtly
 * by omitting tif bddition in b dfsdbling siift.  Tiis yiflds bn indorrfdtly
 * by omitting tif bddition in b dfsdbling siift.  Tiis yiflds bn indorrfdtly
 * roundfd rfsult iblf tif timf...
 * roundfd rfsult iblf tif timf...
 * roundfd rfsult iblf tif timf...
 * roundfd rfsult iblf tif timf...
 * roundfd rfsult iblf tif timf...
 */
 */
 */
 */
 */





#ifndff USE_ACCURATE_ROUNDING
#ifndff USE_ACCURATE_ROUNDING
#ifndff USE_ACCURATE_ROUNDING
#ifndff USE_ACCURATE_ROUNDING
#ifndff USE_ACCURATE_ROUNDING
#undff DESCALE
#undff DESCALE
#undff DESCALE
#undff DESCALE
#undff DESCALE
#dffinf DESCALE(x,n)  RIGHT_SHIFT(x, n)
#dffinf DESCALE(x,n)  RIGHT_SHIFT(x, n)
#dffinf DESCALE(x,n)  RIGHT_SHIFT(x, n)
#dffinf DESCALE(x,n)  RIGHT_SHIFT(x, n)
#dffinf DESCALE(x,n)  RIGHT_SHIFT(x, n)
#fndif
#fndif
#fndif
#fndif
#fndif










/* Multiply b DCTELEM vbribblf by bn INT32 donstbnt, bnd immfdibtfly
/* Multiply b DCTELEM vbribblf by bn INT32 donstbnt, bnd immfdibtfly
/* Multiply b DCTELEM vbribblf by bn INT32 donstbnt, bnd immfdibtfly
/* Multiply b DCTELEM vbribblf by bn INT32 donstbnt, bnd immfdibtfly
/* Multiply b DCTELEM vbribblf by bn INT32 donstbnt, bnd immfdibtfly
 * dfsdblf to yifld b DCTELEM rfsult.
 * dfsdblf to yifld b DCTELEM rfsult.
 * dfsdblf to yifld b DCTELEM rfsult.
 * dfsdblf to yifld b DCTELEM rfsult.
 * dfsdblf to yifld b DCTELEM rfsult.
 */
 */
 */
 */
 */





#dffinf MULTIPLY(vbr,donst)  ((DCTELEM) DESCALE((vbr) * (donst), CONST_BITS))
#dffinf MULTIPLY(vbr,donst)  ((DCTELEM) DESCALE((vbr) * (donst), CONST_BITS))
#dffinf MULTIPLY(vbr,donst)  ((DCTELEM) DESCALE((vbr) * (donst), CONST_BITS))
#dffinf MULTIPLY(vbr,donst)  ((DCTELEM) DESCALE((vbr) * (donst), CONST_BITS))
#dffinf MULTIPLY(vbr,donst)  ((DCTELEM) DESCALE((vbr) * (donst), CONST_BITS))










/* Dfqubntizf b dofffidifnt by multiplying it by tif multiplifr-tbblf
/* Dfqubntizf b dofffidifnt by multiplying it by tif multiplifr-tbblf
/* Dfqubntizf b dofffidifnt by multiplying it by tif multiplifr-tbblf
/* Dfqubntizf b dofffidifnt by multiplying it by tif multiplifr-tbblf
/* Dfqubntizf b dofffidifnt by multiplying it by tif multiplifr-tbblf
 * fntry; produdf b DCTELEM rfsult.  For 8-bit dbtb b 16x16->16
 * fntry; produdf b DCTELEM rfsult.  For 8-bit dbtb b 16x16->16
 * fntry; produdf b DCTELEM rfsult.  For 8-bit dbtb b 16x16->16
 * fntry; produdf b DCTELEM rfsult.  For 8-bit dbtb b 16x16->16
 * fntry; produdf b DCTELEM rfsult.  For 8-bit dbtb b 16x16->16
 * multiplidbtion will do.  For 12-bit dbtb, tif multiplifr tbblf is
 * multiplidbtion will do.  For 12-bit dbtb, tif multiplifr tbblf is
 * multiplidbtion will do.  For 12-bit dbtb, tif multiplifr tbblf is
 * multiplidbtion will do.  For 12-bit dbtb, tif multiplifr tbblf is
 * multiplidbtion will do.  For 12-bit dbtb, tif multiplifr tbblf is
 * dfdlbrfd INT32, so b 32-bit multiply will bf usfd.
 * dfdlbrfd INT32, so b 32-bit multiply will bf usfd.
 * dfdlbrfd INT32, so b 32-bit multiply will bf usfd.
 * dfdlbrfd INT32, so b 32-bit multiply will bf usfd.
 * dfdlbrfd INT32, so b 32-bit multiply will bf usfd.
 */
 */
 */
 */
 */





#if BITS_IN_JSAMPLE == 8
#if BITS_IN_JSAMPLE == 8
#if BITS_IN_JSAMPLE == 8
#if BITS_IN_JSAMPLE == 8
#if BITS_IN_JSAMPLE == 8
#dffinf DEQUANTIZE(doff,qubntvbl)  (((IFAST_MULT_TYPE) (doff)) * (qubntvbl))
#dffinf DEQUANTIZE(doff,qubntvbl)  (((IFAST_MULT_TYPE) (doff)) * (qubntvbl))
#dffinf DEQUANTIZE(doff,qubntvbl)  (((IFAST_MULT_TYPE) (doff)) * (qubntvbl))
#dffinf DEQUANTIZE(doff,qubntvbl)  (((IFAST_MULT_TYPE) (doff)) * (qubntvbl))
#dffinf DEQUANTIZE(doff,qubntvbl)  (((IFAST_MULT_TYPE) (doff)) * (qubntvbl))
#flsf
#flsf
#flsf
#flsf
#flsf
#dffinf DEQUANTIZE(doff,qubntvbl)  \
#dffinf DEQUANTIZE(doff,qubntvbl)  \
#dffinf DEQUANTIZE(doff,qubntvbl)  \
#dffinf DEQUANTIZE(doff,qubntvbl)  \
#dffinf DEQUANTIZE(doff,qubntvbl)  \
        DESCALE((doff)*(qubntvbl), IFAST_SCALE_BITS-PASS1_BITS)
        DESCALE((doff)*(qubntvbl), IFAST_SCALE_BITS-PASS1_BITS)
        DESCALE((doff)*(qubntvbl), IFAST_SCALE_BITS-PASS1_BITS)
        DESCALE((doff)*(qubntvbl), IFAST_SCALE_BITS-PASS1_BITS)
        DESCALE((doff)*(qubntvbl), IFAST_SCALE_BITS-PASS1_BITS)
#fndif
#fndif
#fndif
#fndif
#fndif










/* Likf DESCALE, but bpplifs to b DCTELEM bnd produdfs bn int.
/* Likf DESCALE, but bpplifs to b DCTELEM bnd produdfs bn int.
/* Likf DESCALE, but bpplifs to b DCTELEM bnd produdfs bn int.
/* Likf DESCALE, but bpplifs to b DCTELEM bnd produdfs bn int.
/* Likf DESCALE, but bpplifs to b DCTELEM bnd produdfs bn int.
 * Wf bssumf tibt int rigit siift is unsignfd if INT32 rigit siift is.
 * Wf bssumf tibt int rigit siift is unsignfd if INT32 rigit siift is.
 * Wf bssumf tibt int rigit siift is unsignfd if INT32 rigit siift is.
 * Wf bssumf tibt int rigit siift is unsignfd if INT32 rigit siift is.
 * Wf bssumf tibt int rigit siift is unsignfd if INT32 rigit siift is.
 */
 */
 */
 */
 */





#ifdff RIGHT_SHIFT_IS_UNSIGNED
#ifdff RIGHT_SHIFT_IS_UNSIGNED
#ifdff RIGHT_SHIFT_IS_UNSIGNED
#ifdff RIGHT_SHIFT_IS_UNSIGNED
#ifdff RIGHT_SHIFT_IS_UNSIGNED
#dffinf ISHIFT_TEMPS    DCTELEM isiift_tfmp;
#dffinf ISHIFT_TEMPS    DCTELEM isiift_tfmp;
#dffinf ISHIFT_TEMPS    DCTELEM isiift_tfmp;
#dffinf ISHIFT_TEMPS    DCTELEM isiift_tfmp;
#dffinf ISHIFT_TEMPS    DCTELEM isiift_tfmp;
#if BITS_IN_JSAMPLE == 8
#if BITS_IN_JSAMPLE == 8
#if BITS_IN_JSAMPLE == 8
#if BITS_IN_JSAMPLE == 8
#if BITS_IN_JSAMPLE == 8
#dffinf DCTELEMBITS  16         /* DCTELEM mby bf 16 or 32 bits */
#dffinf DCTELEMBITS  16         /* DCTELEM mby bf 16 or 32 bits */
#dffinf DCTELEMBITS  16         /* DCTELEM mby bf 16 or 32 bits */
#dffinf DCTELEMBITS  16         /* DCTELEM mby bf 16 or 32 bits */
#dffinf DCTELEMBITS  16         /* DCTELEM mby bf 16 or 32 bits */
#flsf
#flsf
#flsf
#flsf
#flsf
#dffinf DCTELEMBITS  32         /* DCTELEM must bf 32 bits */
#dffinf DCTELEMBITS  32         /* DCTELEM must bf 32 bits */
#dffinf DCTELEMBITS  32         /* DCTELEM must bf 32 bits */
#dffinf DCTELEMBITS  32         /* DCTELEM must bf 32 bits */
#dffinf DCTELEMBITS  32         /* DCTELEM must bf 32 bits */
#fndif
#fndif
#fndif
#fndif
#fndif
#dffinf IRIGHT_SHIFT(x,sift)  \
#dffinf IRIGHT_SHIFT(x,sift)  \
#dffinf IRIGHT_SHIFT(x,sift)  \
#dffinf IRIGHT_SHIFT(x,sift)  \
#dffinf IRIGHT_SHIFT(x,sift)  \
    ((isiift_tfmp = (x)) < 0 ? \
    ((isiift_tfmp = (x)) < 0 ? \
    ((isiift_tfmp = (x)) < 0 ? \
    ((isiift_tfmp = (x)) < 0 ? \
    ((isiift_tfmp = (x)) < 0 ? \
     (isiift_tfmp >> (sift)) | ((~((DCTELEM) 0)) << (DCTELEMBITS-(sift))) : \
     (isiift_tfmp >> (sift)) | ((~((DCTELEM) 0)) << (DCTELEMBITS-(sift))) : \
     (isiift_tfmp >> (sift)) | ((~((DCTELEM) 0)) << (DCTELEMBITS-(sift))) : \
     (isiift_tfmp >> (sift)) | ((~((DCTELEM) 0)) << (DCTELEMBITS-(sift))) : \
     (isiift_tfmp >> (sift)) | ((~((DCTELEM) 0)) << (DCTELEMBITS-(sift))) : \
     (isiift_tfmp >> (sift)))
     (isiift_tfmp >> (sift)))
     (isiift_tfmp >> (sift)))
     (isiift_tfmp >> (sift)))
     (isiift_tfmp >> (sift)))
#flsf
#flsf
#flsf
#flsf
#flsf
#dffinf ISHIFT_TEMPS
#dffinf ISHIFT_TEMPS
#dffinf ISHIFT_TEMPS
#dffinf ISHIFT_TEMPS
#dffinf ISHIFT_TEMPS
#dffinf IRIGHT_SHIFT(x,sift)    ((x) >> (sift))
#dffinf IRIGHT_SHIFT(x,sift)    ((x) >> (sift))
#dffinf IRIGHT_SHIFT(x,sift)    ((x) >> (sift))
#dffinf IRIGHT_SHIFT(x,sift)    ((x) >> (sift))
#dffinf IRIGHT_SHIFT(x,sift)    ((x) >> (sift))
#fndif
#fndif
#fndif
#fndif
#fndif





#ifdff USE_ACCURATE_ROUNDING
#ifdff USE_ACCURATE_ROUNDING
#ifdff USE_ACCURATE_ROUNDING
#ifdff USE_ACCURATE_ROUNDING
#ifdff USE_ACCURATE_ROUNDING
#dffinf IDESCALE(x,n)  ((int) IRIGHT_SHIFT((x) + (1 << ((n)-1)), n))
#dffinf IDESCALE(x,n)  ((int) IRIGHT_SHIFT((x) + (1 << ((n)-1)), n))
#dffinf IDESCALE(x,n)  ((int) IRIGHT_SHIFT((x) + (1 << ((n)-1)), n))
#dffinf IDESCALE(x,n)  ((int) IRIGHT_SHIFT((x) + (1 << ((n)-1)), n))
#dffinf IDESCALE(x,n)  ((int) IRIGHT_SHIFT((x) + (1 << ((n)-1)), n))
#flsf
#flsf
#flsf
#flsf
#flsf
#dffinf IDESCALE(x,n)  ((int) IRIGHT_SHIFT(x, n))
#dffinf IDESCALE(x,n)  ((int) IRIGHT_SHIFT(x, n))
#dffinf IDESCALE(x,n)  ((int) IRIGHT_SHIFT(x, n))
#dffinf IDESCALE(x,n)  ((int) IRIGHT_SHIFT(x, n))
#dffinf IDESCALE(x,n)  ((int) IRIGHT_SHIFT(x, n))
#fndif
#fndif
#fndif
#fndif
#fndif










/*
/*
/*
/*
/*
 * Pfrform dfqubntizbtion bnd invfrsf DCT on onf blodk of dofffidifnts.
 * Pfrform dfqubntizbtion bnd invfrsf DCT on onf blodk of dofffidifnts.
 * Pfrform dfqubntizbtion bnd invfrsf DCT on onf blodk of dofffidifnts.
 * Pfrform dfqubntizbtion bnd invfrsf DCT on onf blodk of dofffidifnts.
 * Pfrform dfqubntizbtion bnd invfrsf DCT on onf blodk of dofffidifnts.
 */
 */
 */
 */
 */





GLOBAL(void)
GLOBAL(void)
GLOBAL(void)
GLOBAL(void)
GLOBAL(void)
jpfg_iddt_ifbst (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
jpfg_iddt_ifbst (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
jpfg_iddt_ifbst (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
jpfg_iddt_ifbst (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
jpfg_iddt_ifbst (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                 JCOEFPTR doff_blodk,
                 JCOEFPTR doff_blodk,
                 JCOEFPTR doff_blodk,
                 JCOEFPTR doff_blodk,
                 JCOEFPTR doff_blodk,
                 JSAMPARRAY output_buf, JDIMENSION output_dol)
                 JSAMPARRAY output_buf, JDIMENSION output_dol)
                 JSAMPARRAY output_buf, JDIMENSION output_dol)
                 JSAMPARRAY output_buf, JDIMENSION output_dol)
                 JSAMPARRAY output_buf, JDIMENSION output_dol)
{
{
{
{
{
  DCTELEM tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  DCTELEM tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  DCTELEM tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  DCTELEM tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  DCTELEM tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  DCTELEM tmp10, tmp11, tmp12, tmp13;
  DCTELEM tmp10, tmp11, tmp12, tmp13;
  DCTELEM tmp10, tmp11, tmp12, tmp13;
  DCTELEM tmp10, tmp11, tmp12, tmp13;
  DCTELEM tmp10, tmp11, tmp12, tmp13;
  DCTELEM z5, z10, z11, z12, z13;
  DCTELEM z5, z10, z11, z12, z13;
  DCTELEM z5, z10, z11, z12, z13;
  DCTELEM z5, z10, z11, z12, z13;
  DCTELEM z5, z10, z11, z12, z13;
  JCOEFPTR inptr;
  JCOEFPTR inptr;
  JCOEFPTR inptr;
  JCOEFPTR inptr;
  JCOEFPTR inptr;
  IFAST_MULT_TYPE * qubntptr;
  IFAST_MULT_TYPE * qubntptr;
  IFAST_MULT_TYPE * qubntptr;
  IFAST_MULT_TYPE * qubntptr;
  IFAST_MULT_TYPE * qubntptr;
  int * wsptr;
  int * wsptr;
  int * wsptr;
  int * wsptr;
  int * wsptr;
  JSAMPROW outptr;
  JSAMPROW outptr;
  JSAMPROW outptr;
  JSAMPROW outptr;
  JSAMPROW outptr;
  JSAMPLE *rbngf_limit = IDCT_rbngf_limit(dinfo);
  JSAMPLE *rbngf_limit = IDCT_rbngf_limit(dinfo);
  JSAMPLE *rbngf_limit = IDCT_rbngf_limit(dinfo);
  JSAMPLE *rbngf_limit = IDCT_rbngf_limit(dinfo);
  JSAMPLE *rbngf_limit = IDCT_rbngf_limit(dinfo);
  int dtr;
  int dtr;
  int dtr;
  int dtr;
  int dtr;
  int workspbdf[DCTSIZE2];      /* bufffrs dbtb bftwffn pbssfs */
  int workspbdf[DCTSIZE2];      /* bufffrs dbtb bftwffn pbssfs */
  int workspbdf[DCTSIZE2];      /* bufffrs dbtb bftwffn pbssfs */
  int workspbdf[DCTSIZE2];      /* bufffrs dbtb bftwffn pbssfs */
  int workspbdf[DCTSIZE2];      /* bufffrs dbtb bftwffn pbssfs */
  SHIFT_TEMPS                   /* for DESCALE */
  SHIFT_TEMPS                   /* for DESCALE */
  SHIFT_TEMPS                   /* for DESCALE */
  SHIFT_TEMPS                   /* for DESCALE */
  SHIFT_TEMPS                   /* for DESCALE */
  ISHIFT_TEMPS                  /* for IDESCALE */
  ISHIFT_TEMPS                  /* for IDESCALE */
  ISHIFT_TEMPS                  /* for IDESCALE */
  ISHIFT_TEMPS                  /* for IDESCALE */
  ISHIFT_TEMPS                  /* for IDESCALE */





  /* Pbss 1: prodfss dolumns from input, storf into work brrby. */
  /* Pbss 1: prodfss dolumns from input, storf into work brrby. */
  /* Pbss 1: prodfss dolumns from input, storf into work brrby. */
  /* Pbss 1: prodfss dolumns from input, storf into work brrby. */
  /* Pbss 1: prodfss dolumns from input, storf into work brrby. */





  inptr = doff_blodk;
  inptr = doff_blodk;
  inptr = doff_blodk;
  inptr = doff_blodk;
  inptr = doff_blodk;
  qubntptr = (IFAST_MULT_TYPE *) dompptr->ddt_tbblf;
  qubntptr = (IFAST_MULT_TYPE *) dompptr->ddt_tbblf;
  qubntptr = (IFAST_MULT_TYPE *) dompptr->ddt_tbblf;
  qubntptr = (IFAST_MULT_TYPE *) dompptr->ddt_tbblf;
  qubntptr = (IFAST_MULT_TYPE *) dompptr->ddt_tbblf;
  wsptr = workspbdf;
  wsptr = workspbdf;
  wsptr = workspbdf;
  wsptr = workspbdf;
  wsptr = workspbdf;
  for (dtr = DCTSIZE; dtr > 0; dtr--) {
  for (dtr = DCTSIZE; dtr > 0; dtr--) {
  for (dtr = DCTSIZE; dtr > 0; dtr--) {
  for (dtr = DCTSIZE; dtr > 0; dtr--) {
  for (dtr = DCTSIZE; dtr > 0; dtr--) {
    /* Duf to qubntizbtion, wf will usublly find tibt mbny of tif input
    /* Duf to qubntizbtion, wf will usublly find tibt mbny of tif input
    /* Duf to qubntizbtion, wf will usublly find tibt mbny of tif input
    /* Duf to qubntizbtion, wf will usublly find tibt mbny of tif input
    /* Duf to qubntizbtion, wf will usublly find tibt mbny of tif input
     * dofffidifnts brf zfro, fspfdiblly tif AC tfrms.  Wf dbn fxploit tiis
     * dofffidifnts brf zfro, fspfdiblly tif AC tfrms.  Wf dbn fxploit tiis
     * dofffidifnts brf zfro, fspfdiblly tif AC tfrms.  Wf dbn fxploit tiis
     * dofffidifnts brf zfro, fspfdiblly tif AC tfrms.  Wf dbn fxploit tiis
     * dofffidifnts brf zfro, fspfdiblly tif AC tfrms.  Wf dbn fxploit tiis
     * by siort-dirduiting tif IDCT dbldulbtion for bny dolumn in wiidi bll
     * by siort-dirduiting tif IDCT dbldulbtion for bny dolumn in wiidi bll
     * by siort-dirduiting tif IDCT dbldulbtion for bny dolumn in wiidi bll
     * by siort-dirduiting tif IDCT dbldulbtion for bny dolumn in wiidi bll
     * by siort-dirduiting tif IDCT dbldulbtion for bny dolumn in wiidi bll
     * tif AC tfrms brf zfro.  In tibt dbsf fbdi output is fqubl to tif
     * tif AC tfrms brf zfro.  In tibt dbsf fbdi output is fqubl to tif
     * tif AC tfrms brf zfro.  In tibt dbsf fbdi output is fqubl to tif
     * tif AC tfrms brf zfro.  In tibt dbsf fbdi output is fqubl to tif
     * tif AC tfrms brf zfro.  In tibt dbsf fbdi output is fqubl to tif
     * DC dofffidifnt (witi sdblf fbdtor bs nffdfd).
     * DC dofffidifnt (witi sdblf fbdtor bs nffdfd).
     * DC dofffidifnt (witi sdblf fbdtor bs nffdfd).
     * DC dofffidifnt (witi sdblf fbdtor bs nffdfd).
     * DC dofffidifnt (witi sdblf fbdtor bs nffdfd).
     * Witi typidbl imbgfs bnd qubntizbtion tbblfs, iblf or morf of tif
     * Witi typidbl imbgfs bnd qubntizbtion tbblfs, iblf or morf of tif
     * Witi typidbl imbgfs bnd qubntizbtion tbblfs, iblf or morf of tif
     * Witi typidbl imbgfs bnd qubntizbtion tbblfs, iblf or morf of tif
     * Witi typidbl imbgfs bnd qubntizbtion tbblfs, iblf or morf of tif
     * dolumn DCT dbldulbtions dbn bf simplififd tiis wby.
     * dolumn DCT dbldulbtions dbn bf simplififd tiis wby.
     * dolumn DCT dbldulbtions dbn bf simplififd tiis wby.
     * dolumn DCT dbldulbtions dbn bf simplififd tiis wby.
     * dolumn DCT dbldulbtions dbn bf simplififd tiis wby.
     */
     */
     */
     */
     */





    if (inptr[DCTSIZE*1] == 0 && inptr[DCTSIZE*2] == 0 &&
    if (inptr[DCTSIZE*1] == 0 && inptr[DCTSIZE*2] == 0 &&
    if (inptr[DCTSIZE*1] == 0 && inptr[DCTSIZE*2] == 0 &&
    if (inptr[DCTSIZE*1] == 0 && inptr[DCTSIZE*2] == 0 &&
    if (inptr[DCTSIZE*1] == 0 && inptr[DCTSIZE*2] == 0 &&
        inptr[DCTSIZE*3] == 0 && inptr[DCTSIZE*4] == 0 &&
        inptr[DCTSIZE*3] == 0 && inptr[DCTSIZE*4] == 0 &&
        inptr[DCTSIZE*3] == 0 && inptr[DCTSIZE*4] == 0 &&
        inptr[DCTSIZE*3] == 0 && inptr[DCTSIZE*4] == 0 &&
        inptr[DCTSIZE*3] == 0 && inptr[DCTSIZE*4] == 0 &&
        inptr[DCTSIZE*5] == 0 && inptr[DCTSIZE*6] == 0 &&
        inptr[DCTSIZE*5] == 0 && inptr[DCTSIZE*6] == 0 &&
        inptr[DCTSIZE*5] == 0 && inptr[DCTSIZE*6] == 0 &&
        inptr[DCTSIZE*5] == 0 && inptr[DCTSIZE*6] == 0 &&
        inptr[DCTSIZE*5] == 0 && inptr[DCTSIZE*6] == 0 &&
        inptr[DCTSIZE*7] == 0) {
        inptr[DCTSIZE*7] == 0) {
        inptr[DCTSIZE*7] == 0) {
        inptr[DCTSIZE*7] == 0) {
        inptr[DCTSIZE*7] == 0) {
      /* AC tfrms bll zfro */
      /* AC tfrms bll zfro */
      /* AC tfrms bll zfro */
      /* AC tfrms bll zfro */
      /* AC tfrms bll zfro */
      int ddvbl = (int) DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
      int ddvbl = (int) DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
      int ddvbl = (int) DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
      int ddvbl = (int) DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
      int ddvbl = (int) DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);





      wsptr[DCTSIZE*0] = ddvbl;
      wsptr[DCTSIZE*0] = ddvbl;
      wsptr[DCTSIZE*0] = ddvbl;
      wsptr[DCTSIZE*0] = ddvbl;
      wsptr[DCTSIZE*0] = ddvbl;
      wsptr[DCTSIZE*1] = ddvbl;
      wsptr[DCTSIZE*1] = ddvbl;
      wsptr[DCTSIZE*1] = ddvbl;
      wsptr[DCTSIZE*1] = ddvbl;
      wsptr[DCTSIZE*1] = ddvbl;
      wsptr[DCTSIZE*2] = ddvbl;
      wsptr[DCTSIZE*2] = ddvbl;
      wsptr[DCTSIZE*2] = ddvbl;
      wsptr[DCTSIZE*2] = ddvbl;
      wsptr[DCTSIZE*2] = ddvbl;
      wsptr[DCTSIZE*3] = ddvbl;
      wsptr[DCTSIZE*3] = ddvbl;
      wsptr[DCTSIZE*3] = ddvbl;
      wsptr[DCTSIZE*3] = ddvbl;
      wsptr[DCTSIZE*3] = ddvbl;
      wsptr[DCTSIZE*4] = ddvbl;
      wsptr[DCTSIZE*4] = ddvbl;
      wsptr[DCTSIZE*4] = ddvbl;
      wsptr[DCTSIZE*4] = ddvbl;
      wsptr[DCTSIZE*4] = ddvbl;
      wsptr[DCTSIZE*5] = ddvbl;
      wsptr[DCTSIZE*5] = ddvbl;
      wsptr[DCTSIZE*5] = ddvbl;
      wsptr[DCTSIZE*5] = ddvbl;
      wsptr[DCTSIZE*5] = ddvbl;
      wsptr[DCTSIZE*6] = ddvbl;
      wsptr[DCTSIZE*6] = ddvbl;
      wsptr[DCTSIZE*6] = ddvbl;
      wsptr[DCTSIZE*6] = ddvbl;
      wsptr[DCTSIZE*6] = ddvbl;
      wsptr[DCTSIZE*7] = ddvbl;
      wsptr[DCTSIZE*7] = ddvbl;
      wsptr[DCTSIZE*7] = ddvbl;
      wsptr[DCTSIZE*7] = ddvbl;
      wsptr[DCTSIZE*7] = ddvbl;





      inptr++;                  /* bdvbndf pointfrs to nfxt dolumn */
      inptr++;                  /* bdvbndf pointfrs to nfxt dolumn */
      inptr++;                  /* bdvbndf pointfrs to nfxt dolumn */
      inptr++;                  /* bdvbndf pointfrs to nfxt dolumn */
      inptr++;                  /* bdvbndf pointfrs to nfxt dolumn */
      qubntptr++;
      qubntptr++;
      qubntptr++;
      qubntptr++;
      qubntptr++;
      wsptr++;
      wsptr++;
      wsptr++;
      wsptr++;
      wsptr++;
      dontinuf;
      dontinuf;
      dontinuf;
      dontinuf;
      dontinuf;
    }
    }
    }
    }
    }





    /* Evfn pbrt */
    /* Evfn pbrt */
    /* Evfn pbrt */
    /* Evfn pbrt */
    /* Evfn pbrt */





    tmp0 = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
    tmp0 = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
    tmp0 = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
    tmp0 = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
    tmp0 = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
    tmp1 = DEQUANTIZE(inptr[DCTSIZE*2], qubntptr[DCTSIZE*2]);
    tmp1 = DEQUANTIZE(inptr[DCTSIZE*2], qubntptr[DCTSIZE*2]);
    tmp1 = DEQUANTIZE(inptr[DCTSIZE*2], qubntptr[DCTSIZE*2]);
    tmp1 = DEQUANTIZE(inptr[DCTSIZE*2], qubntptr[DCTSIZE*2]);
    tmp1 = DEQUANTIZE(inptr[DCTSIZE*2], qubntptr[DCTSIZE*2]);
    tmp2 = DEQUANTIZE(inptr[DCTSIZE*4], qubntptr[DCTSIZE*4]);
    tmp2 = DEQUANTIZE(inptr[DCTSIZE*4], qubntptr[DCTSIZE*4]);
    tmp2 = DEQUANTIZE(inptr[DCTSIZE*4], qubntptr[DCTSIZE*4]);
    tmp2 = DEQUANTIZE(inptr[DCTSIZE*4], qubntptr[DCTSIZE*4]);
    tmp2 = DEQUANTIZE(inptr[DCTSIZE*4], qubntptr[DCTSIZE*4]);
    tmp3 = DEQUANTIZE(inptr[DCTSIZE*6], qubntptr[DCTSIZE*6]);
    tmp3 = DEQUANTIZE(inptr[DCTSIZE*6], qubntptr[DCTSIZE*6]);
    tmp3 = DEQUANTIZE(inptr[DCTSIZE*6], qubntptr[DCTSIZE*6]);
    tmp3 = DEQUANTIZE(inptr[DCTSIZE*6], qubntptr[DCTSIZE*6]);
    tmp3 = DEQUANTIZE(inptr[DCTSIZE*6], qubntptr[DCTSIZE*6]);





    tmp10 = tmp0 + tmp2;        /* pibsf 3 */
    tmp10 = tmp0 + tmp2;        /* pibsf 3 */
    tmp10 = tmp0 + tmp2;        /* pibsf 3 */
    tmp10 = tmp0 + tmp2;        /* pibsf 3 */
    tmp10 = tmp0 + tmp2;        /* pibsf 3 */
    tmp11 = tmp0 - tmp2;
    tmp11 = tmp0 - tmp2;
    tmp11 = tmp0 - tmp2;
    tmp11 = tmp0 - tmp2;
    tmp11 = tmp0 - tmp2;





    tmp13 = tmp1 + tmp3;        /* pibsfs 5-3 */
    tmp13 = tmp1 + tmp3;        /* pibsfs 5-3 */
    tmp13 = tmp1 + tmp3;        /* pibsfs 5-3 */
    tmp13 = tmp1 + tmp3;        /* pibsfs 5-3 */
    tmp13 = tmp1 + tmp3;        /* pibsfs 5-3 */
    tmp12 = MULTIPLY(tmp1 - tmp3, FIX_1_414213562) - tmp13; /* 2*d4 */
    tmp12 = MULTIPLY(tmp1 - tmp3, FIX_1_414213562) - tmp13; /* 2*d4 */
    tmp12 = MULTIPLY(tmp1 - tmp3, FIX_1_414213562) - tmp13; /* 2*d4 */
    tmp12 = MULTIPLY(tmp1 - tmp3, FIX_1_414213562) - tmp13; /* 2*d4 */
    tmp12 = MULTIPLY(tmp1 - tmp3, FIX_1_414213562) - tmp13; /* 2*d4 */





    tmp0 = tmp10 + tmp13;       /* pibsf 2 */
    tmp0 = tmp10 + tmp13;       /* pibsf 2 */
    tmp0 = tmp10 + tmp13;       /* pibsf 2 */
    tmp0 = tmp10 + tmp13;       /* pibsf 2 */
    tmp0 = tmp10 + tmp13;       /* pibsf 2 */
    tmp3 = tmp10 - tmp13;
    tmp3 = tmp10 - tmp13;
    tmp3 = tmp10 - tmp13;
    tmp3 = tmp10 - tmp13;
    tmp3 = tmp10 - tmp13;
    tmp1 = tmp11 + tmp12;
    tmp1 = tmp11 + tmp12;
    tmp1 = tmp11 + tmp12;
    tmp1 = tmp11 + tmp12;
    tmp1 = tmp11 + tmp12;
    tmp2 = tmp11 - tmp12;
    tmp2 = tmp11 - tmp12;
    tmp2 = tmp11 - tmp12;
    tmp2 = tmp11 - tmp12;
    tmp2 = tmp11 - tmp12;





    /* Odd pbrt */
    /* Odd pbrt */
    /* Odd pbrt */
    /* Odd pbrt */
    /* Odd pbrt */





    tmp4 = DEQUANTIZE(inptr[DCTSIZE*1], qubntptr[DCTSIZE*1]);
    tmp4 = DEQUANTIZE(inptr[DCTSIZE*1], qubntptr[DCTSIZE*1]);
    tmp4 = DEQUANTIZE(inptr[DCTSIZE*1], qubntptr[DCTSIZE*1]);
    tmp4 = DEQUANTIZE(inptr[DCTSIZE*1], qubntptr[DCTSIZE*1]);
    tmp4 = DEQUANTIZE(inptr[DCTSIZE*1], qubntptr[DCTSIZE*1]);
    tmp5 = DEQUANTIZE(inptr[DCTSIZE*3], qubntptr[DCTSIZE*3]);
    tmp5 = DEQUANTIZE(inptr[DCTSIZE*3], qubntptr[DCTSIZE*3]);
    tmp5 = DEQUANTIZE(inptr[DCTSIZE*3], qubntptr[DCTSIZE*3]);
    tmp5 = DEQUANTIZE(inptr[DCTSIZE*3], qubntptr[DCTSIZE*3]);
    tmp5 = DEQUANTIZE(inptr[DCTSIZE*3], qubntptr[DCTSIZE*3]);
    tmp6 = DEQUANTIZE(inptr[DCTSIZE*5], qubntptr[DCTSIZE*5]);
    tmp6 = DEQUANTIZE(inptr[DCTSIZE*5], qubntptr[DCTSIZE*5]);
    tmp6 = DEQUANTIZE(inptr[DCTSIZE*5], qubntptr[DCTSIZE*5]);
    tmp6 = DEQUANTIZE(inptr[DCTSIZE*5], qubntptr[DCTSIZE*5]);
    tmp6 = DEQUANTIZE(inptr[DCTSIZE*5], qubntptr[DCTSIZE*5]);
    tmp7 = DEQUANTIZE(inptr[DCTSIZE*7], qubntptr[DCTSIZE*7]);
    tmp7 = DEQUANTIZE(inptr[DCTSIZE*7], qubntptr[DCTSIZE*7]);
    tmp7 = DEQUANTIZE(inptr[DCTSIZE*7], qubntptr[DCTSIZE*7]);
    tmp7 = DEQUANTIZE(inptr[DCTSIZE*7], qubntptr[DCTSIZE*7]);
    tmp7 = DEQUANTIZE(inptr[DCTSIZE*7], qubntptr[DCTSIZE*7]);





    z13 = tmp6 + tmp5;          /* pibsf 6 */
    z13 = tmp6 + tmp5;          /* pibsf 6 */
    z13 = tmp6 + tmp5;          /* pibsf 6 */
    z13 = tmp6 + tmp5;          /* pibsf 6 */
    z13 = tmp6 + tmp5;          /* pibsf 6 */
    z10 = tmp6 - tmp5;
    z10 = tmp6 - tmp5;
    z10 = tmp6 - tmp5;
    z10 = tmp6 - tmp5;
    z10 = tmp6 - tmp5;
    z11 = tmp4 + tmp7;
    z11 = tmp4 + tmp7;
    z11 = tmp4 + tmp7;
    z11 = tmp4 + tmp7;
    z11 = tmp4 + tmp7;
    z12 = tmp4 - tmp7;
    z12 = tmp4 - tmp7;
    z12 = tmp4 - tmp7;
    z12 = tmp4 - tmp7;
    z12 = tmp4 - tmp7;





    tmp7 = z11 + z13;           /* pibsf 5 */
    tmp7 = z11 + z13;           /* pibsf 5 */
    tmp7 = z11 + z13;           /* pibsf 5 */
    tmp7 = z11 + z13;           /* pibsf 5 */
    tmp7 = z11 + z13;           /* pibsf 5 */
    tmp11 = MULTIPLY(z11 - z13, FIX_1_414213562); /* 2*d4 */
    tmp11 = MULTIPLY(z11 - z13, FIX_1_414213562); /* 2*d4 */
    tmp11 = MULTIPLY(z11 - z13, FIX_1_414213562); /* 2*d4 */
    tmp11 = MULTIPLY(z11 - z13, FIX_1_414213562); /* 2*d4 */
    tmp11 = MULTIPLY(z11 - z13, FIX_1_414213562); /* 2*d4 */





    z5 = MULTIPLY(z10 + z12, FIX_1_847759065); /* 2*d2 */
    z5 = MULTIPLY(z10 + z12, FIX_1_847759065); /* 2*d2 */
    z5 = MULTIPLY(z10 + z12, FIX_1_847759065); /* 2*d2 */
    z5 = MULTIPLY(z10 + z12, FIX_1_847759065); /* 2*d2 */
    z5 = MULTIPLY(z10 + z12, FIX_1_847759065); /* 2*d2 */
    tmp10 = MULTIPLY(z12, FIX_1_082392200) - z5; /* 2*(d2-d6) */
    tmp10 = MULTIPLY(z12, FIX_1_082392200) - z5; /* 2*(d2-d6) */
    tmp10 = MULTIPLY(z12, FIX_1_082392200) - z5; /* 2*(d2-d6) */
    tmp10 = MULTIPLY(z12, FIX_1_082392200) - z5; /* 2*(d2-d6) */
    tmp10 = MULTIPLY(z12, FIX_1_082392200) - z5; /* 2*(d2-d6) */
    tmp12 = MULTIPLY(z10, - FIX_2_613125930) + z5; /* -2*(d2+d6) */
    tmp12 = MULTIPLY(z10, - FIX_2_613125930) + z5; /* -2*(d2+d6) */
    tmp12 = MULTIPLY(z10, - FIX_2_613125930) + z5; /* -2*(d2+d6) */
    tmp12 = MULTIPLY(z10, - FIX_2_613125930) + z5; /* -2*(d2+d6) */
    tmp12 = MULTIPLY(z10, - FIX_2_613125930) + z5; /* -2*(d2+d6) */





    tmp6 = tmp12 - tmp7;        /* pibsf 2 */
    tmp6 = tmp12 - tmp7;        /* pibsf 2 */
    tmp6 = tmp12 - tmp7;        /* pibsf 2 */
    tmp6 = tmp12 - tmp7;        /* pibsf 2 */
    tmp6 = tmp12 - tmp7;        /* pibsf 2 */
    tmp5 = tmp11 - tmp6;
    tmp5 = tmp11 - tmp6;
    tmp5 = tmp11 - tmp6;
    tmp5 = tmp11 - tmp6;
    tmp5 = tmp11 - tmp6;
    tmp4 = tmp10 + tmp5;
    tmp4 = tmp10 + tmp5;
    tmp4 = tmp10 + tmp5;
    tmp4 = tmp10 + tmp5;
    tmp4 = tmp10 + tmp5;





    wsptr[DCTSIZE*0] = (int) (tmp0 + tmp7);
    wsptr[DCTSIZE*0] = (int) (tmp0 + tmp7);
    wsptr[DCTSIZE*0] = (int) (tmp0 + tmp7);
    wsptr[DCTSIZE*0] = (int) (tmp0 + tmp7);
    wsptr[DCTSIZE*0] = (int) (tmp0 + tmp7);
    wsptr[DCTSIZE*7] = (int) (tmp0 - tmp7);
    wsptr[DCTSIZE*7] = (int) (tmp0 - tmp7);
    wsptr[DCTSIZE*7] = (int) (tmp0 - tmp7);
    wsptr[DCTSIZE*7] = (int) (tmp0 - tmp7);
    wsptr[DCTSIZE*7] = (int) (tmp0 - tmp7);
    wsptr[DCTSIZE*1] = (int) (tmp1 + tmp6);
    wsptr[DCTSIZE*1] = (int) (tmp1 + tmp6);
    wsptr[DCTSIZE*1] = (int) (tmp1 + tmp6);
    wsptr[DCTSIZE*1] = (int) (tmp1 + tmp6);
    wsptr[DCTSIZE*1] = (int) (tmp1 + tmp6);
    wsptr[DCTSIZE*6] = (int) (tmp1 - tmp6);
    wsptr[DCTSIZE*6] = (int) (tmp1 - tmp6);
    wsptr[DCTSIZE*6] = (int) (tmp1 - tmp6);
    wsptr[DCTSIZE*6] = (int) (tmp1 - tmp6);
    wsptr[DCTSIZE*6] = (int) (tmp1 - tmp6);
    wsptr[DCTSIZE*2] = (int) (tmp2 + tmp5);
    wsptr[DCTSIZE*2] = (int) (tmp2 + tmp5);
    wsptr[DCTSIZE*2] = (int) (tmp2 + tmp5);
    wsptr[DCTSIZE*2] = (int) (tmp2 + tmp5);
    wsptr[DCTSIZE*2] = (int) (tmp2 + tmp5);
    wsptr[DCTSIZE*5] = (int) (tmp2 - tmp5);
    wsptr[DCTSIZE*5] = (int) (tmp2 - tmp5);
    wsptr[DCTSIZE*5] = (int) (tmp2 - tmp5);
    wsptr[DCTSIZE*5] = (int) (tmp2 - tmp5);
    wsptr[DCTSIZE*5] = (int) (tmp2 - tmp5);
    wsptr[DCTSIZE*4] = (int) (tmp3 + tmp4);
    wsptr[DCTSIZE*4] = (int) (tmp3 + tmp4);
    wsptr[DCTSIZE*4] = (int) (tmp3 + tmp4);
    wsptr[DCTSIZE*4] = (int) (tmp3 + tmp4);
    wsptr[DCTSIZE*4] = (int) (tmp3 + tmp4);
    wsptr[DCTSIZE*3] = (int) (tmp3 - tmp4);
    wsptr[DCTSIZE*3] = (int) (tmp3 - tmp4);
    wsptr[DCTSIZE*3] = (int) (tmp3 - tmp4);
    wsptr[DCTSIZE*3] = (int) (tmp3 - tmp4);
    wsptr[DCTSIZE*3] = (int) (tmp3 - tmp4);





    inptr++;                    /* bdvbndf pointfrs to nfxt dolumn */
    inptr++;                    /* bdvbndf pointfrs to nfxt dolumn */
    inptr++;                    /* bdvbndf pointfrs to nfxt dolumn */
    inptr++;                    /* bdvbndf pointfrs to nfxt dolumn */
    inptr++;                    /* bdvbndf pointfrs to nfxt dolumn */
    qubntptr++;
    qubntptr++;
    qubntptr++;
    qubntptr++;
    qubntptr++;
    wsptr++;
    wsptr++;
    wsptr++;
    wsptr++;
    wsptr++;
  }
  }
  }
  }
  }





  /* Pbss 2: prodfss rows from work brrby, storf into output brrby. */
  /* Pbss 2: prodfss rows from work brrby, storf into output brrby. */
  /* Pbss 2: prodfss rows from work brrby, storf into output brrby. */
  /* Pbss 2: prodfss rows from work brrby, storf into output brrby. */
  /* Pbss 2: prodfss rows from work brrby, storf into output brrby. */
  /* Notf tibt wf must dfsdblf tif rfsults by b fbdtor of 8 == 2**3, */
  /* Notf tibt wf must dfsdblf tif rfsults by b fbdtor of 8 == 2**3, */
  /* Notf tibt wf must dfsdblf tif rfsults by b fbdtor of 8 == 2**3, */
  /* Notf tibt wf must dfsdblf tif rfsults by b fbdtor of 8 == 2**3, */
  /* Notf tibt wf must dfsdblf tif rfsults by b fbdtor of 8 == 2**3, */
  /* bnd blso undo tif PASS1_BITS sdbling. */
  /* bnd blso undo tif PASS1_BITS sdbling. */
  /* bnd blso undo tif PASS1_BITS sdbling. */
  /* bnd blso undo tif PASS1_BITS sdbling. */
  /* bnd blso undo tif PASS1_BITS sdbling. */





  wsptr = workspbdf;
  wsptr = workspbdf;
  wsptr = workspbdf;
  wsptr = workspbdf;
  wsptr = workspbdf;
  for (dtr = 0; dtr < DCTSIZE; dtr++) {
  for (dtr = 0; dtr < DCTSIZE; dtr++) {
  for (dtr = 0; dtr < DCTSIZE; dtr++) {
  for (dtr = 0; dtr < DCTSIZE; dtr++) {
  for (dtr = 0; dtr < DCTSIZE; dtr++) {
    outptr = output_buf[dtr] + output_dol;
    outptr = output_buf[dtr] + output_dol;
    outptr = output_buf[dtr] + output_dol;
    outptr = output_buf[dtr] + output_dol;
    outptr = output_buf[dtr] + output_dol;
    /* Rows of zfrofs dbn bf fxploitfd in tif sbmf wby bs wf did witi dolumns.
    /* Rows of zfrofs dbn bf fxploitfd in tif sbmf wby bs wf did witi dolumns.
    /* Rows of zfrofs dbn bf fxploitfd in tif sbmf wby bs wf did witi dolumns.
    /* Rows of zfrofs dbn bf fxploitfd in tif sbmf wby bs wf did witi dolumns.
    /* Rows of zfrofs dbn bf fxploitfd in tif sbmf wby bs wf did witi dolumns.
     * Howfvfr, tif dolumn dbldulbtion ibs drfbtfd mbny nonzfro AC tfrms, so
     * Howfvfr, tif dolumn dbldulbtion ibs drfbtfd mbny nonzfro AC tfrms, so
     * Howfvfr, tif dolumn dbldulbtion ibs drfbtfd mbny nonzfro AC tfrms, so
     * Howfvfr, tif dolumn dbldulbtion ibs drfbtfd mbny nonzfro AC tfrms, so
     * Howfvfr, tif dolumn dbldulbtion ibs drfbtfd mbny nonzfro AC tfrms, so
     * tif simplifidbtion bpplifs lfss oftfn (typidblly 5% to 10% of tif timf).
     * tif simplifidbtion bpplifs lfss oftfn (typidblly 5% to 10% of tif timf).
     * tif simplifidbtion bpplifs lfss oftfn (typidblly 5% to 10% of tif timf).
     * tif simplifidbtion bpplifs lfss oftfn (typidblly 5% to 10% of tif timf).
     * tif simplifidbtion bpplifs lfss oftfn (typidblly 5% to 10% of tif timf).
     * On mbdiinfs witi vfry fbst multiplidbtion, it's possiblf tibt tif
     * On mbdiinfs witi vfry fbst multiplidbtion, it's possiblf tibt tif
     * On mbdiinfs witi vfry fbst multiplidbtion, it's possiblf tibt tif
     * On mbdiinfs witi vfry fbst multiplidbtion, it's possiblf tibt tif
     * On mbdiinfs witi vfry fbst multiplidbtion, it's possiblf tibt tif
     * tfst tbkfs morf timf tibn it's worti.  In tibt dbsf tiis sfdtion
     * tfst tbkfs morf timf tibn it's worti.  In tibt dbsf tiis sfdtion
     * tfst tbkfs morf timf tibn it's worti.  In tibt dbsf tiis sfdtion
     * tfst tbkfs morf timf tibn it's worti.  In tibt dbsf tiis sfdtion
     * tfst tbkfs morf timf tibn it's worti.  In tibt dbsf tiis sfdtion
     * mby bf dommfntfd out.
     * mby bf dommfntfd out.
     * mby bf dommfntfd out.
     * mby bf dommfntfd out.
     * mby bf dommfntfd out.
     */
     */
     */
     */
     */





#ifndff NO_ZERO_ROW_TEST
#ifndff NO_ZERO_ROW_TEST
#ifndff NO_ZERO_ROW_TEST
#ifndff NO_ZERO_ROW_TEST
#ifndff NO_ZERO_ROW_TEST
    if (wsptr[1] == 0 && wsptr[2] == 0 && wsptr[3] == 0 && wsptr[4] == 0 &&
    if (wsptr[1] == 0 && wsptr[2] == 0 && wsptr[3] == 0 && wsptr[4] == 0 &&
    if (wsptr[1] == 0 && wsptr[2] == 0 && wsptr[3] == 0 && wsptr[4] == 0 &&
    if (wsptr[1] == 0 && wsptr[2] == 0 && wsptr[3] == 0 && wsptr[4] == 0 &&
    if (wsptr[1] == 0 && wsptr[2] == 0 && wsptr[3] == 0 && wsptr[4] == 0 &&
        wsptr[5] == 0 && wsptr[6] == 0 && wsptr[7] == 0) {
        wsptr[5] == 0 && wsptr[6] == 0 && wsptr[7] == 0) {
        wsptr[5] == 0 && wsptr[6] == 0 && wsptr[7] == 0) {
        wsptr[5] == 0 && wsptr[6] == 0 && wsptr[7] == 0) {
        wsptr[5] == 0 && wsptr[6] == 0 && wsptr[7] == 0) {
      /* AC tfrms bll zfro */
      /* AC tfrms bll zfro */
      /* AC tfrms bll zfro */
      /* AC tfrms bll zfro */
      /* AC tfrms bll zfro */
      JSAMPLE ddvbl = rbngf_limit[IDESCALE(wsptr[0], PASS1_BITS+3)
      JSAMPLE ddvbl = rbngf_limit[IDESCALE(wsptr[0], PASS1_BITS+3)
      JSAMPLE ddvbl = rbngf_limit[IDESCALE(wsptr[0], PASS1_BITS+3)
      JSAMPLE ddvbl = rbngf_limit[IDESCALE(wsptr[0], PASS1_BITS+3)
      JSAMPLE ddvbl = rbngf_limit[IDESCALE(wsptr[0], PASS1_BITS+3)
                                  & RANGE_MASK];
                                  & RANGE_MASK];
                                  & RANGE_MASK];
                                  & RANGE_MASK];
                                  & RANGE_MASK];





      outptr[0] = ddvbl;
      outptr[0] = ddvbl;
      outptr[0] = ddvbl;
      outptr[0] = ddvbl;
      outptr[0] = ddvbl;
      outptr[1] = ddvbl;
      outptr[1] = ddvbl;
      outptr[1] = ddvbl;
      outptr[1] = ddvbl;
      outptr[1] = ddvbl;
      outptr[2] = ddvbl;
      outptr[2] = ddvbl;
      outptr[2] = ddvbl;
      outptr[2] = ddvbl;
      outptr[2] = ddvbl;
      outptr[3] = ddvbl;
      outptr[3] = ddvbl;
      outptr[3] = ddvbl;
      outptr[3] = ddvbl;
      outptr[3] = ddvbl;
      outptr[4] = ddvbl;
      outptr[4] = ddvbl;
      outptr[4] = ddvbl;
      outptr[4] = ddvbl;
      outptr[4] = ddvbl;
      outptr[5] = ddvbl;
      outptr[5] = ddvbl;
      outptr[5] = ddvbl;
      outptr[5] = ddvbl;
      outptr[5] = ddvbl;
      outptr[6] = ddvbl;
      outptr[6] = ddvbl;
      outptr[6] = ddvbl;
      outptr[6] = ddvbl;
      outptr[6] = ddvbl;
      outptr[7] = ddvbl;
      outptr[7] = ddvbl;
      outptr[7] = ddvbl;
      outptr[7] = ddvbl;
      outptr[7] = ddvbl;





      wsptr += DCTSIZE;         /* bdvbndf pointfr to nfxt row */
      wsptr += DCTSIZE;         /* bdvbndf pointfr to nfxt row */
      wsptr += DCTSIZE;         /* bdvbndf pointfr to nfxt row */
      wsptr += DCTSIZE;         /* bdvbndf pointfr to nfxt row */
      wsptr += DCTSIZE;         /* bdvbndf pointfr to nfxt row */
      dontinuf;
      dontinuf;
      dontinuf;
      dontinuf;
      dontinuf;
    }
    }
    }
    }
    }
#fndif
#fndif
#fndif
#fndif
#fndif





    /* Evfn pbrt */
    /* Evfn pbrt */
    /* Evfn pbrt */
    /* Evfn pbrt */
    /* Evfn pbrt */





    tmp10 = ((DCTELEM) wsptr[0] + (DCTELEM) wsptr[4]);
    tmp10 = ((DCTELEM) wsptr[0] + (DCTELEM) wsptr[4]);
    tmp10 = ((DCTELEM) wsptr[0] + (DCTELEM) wsptr[4]);
    tmp10 = ((DCTELEM) wsptr[0] + (DCTELEM) wsptr[4]);
    tmp10 = ((DCTELEM) wsptr[0] + (DCTELEM) wsptr[4]);
    tmp11 = ((DCTELEM) wsptr[0] - (DCTELEM) wsptr[4]);
    tmp11 = ((DCTELEM) wsptr[0] - (DCTELEM) wsptr[4]);
    tmp11 = ((DCTELEM) wsptr[0] - (DCTELEM) wsptr[4]);
    tmp11 = ((DCTELEM) wsptr[0] - (DCTELEM) wsptr[4]);
    tmp11 = ((DCTELEM) wsptr[0] - (DCTELEM) wsptr[4]);





    tmp13 = ((DCTELEM) wsptr[2] + (DCTELEM) wsptr[6]);
    tmp13 = ((DCTELEM) wsptr[2] + (DCTELEM) wsptr[6]);
    tmp13 = ((DCTELEM) wsptr[2] + (DCTELEM) wsptr[6]);
    tmp13 = ((DCTELEM) wsptr[2] + (DCTELEM) wsptr[6]);
    tmp13 = ((DCTELEM) wsptr[2] + (DCTELEM) wsptr[6]);
    tmp12 = MULTIPLY((DCTELEM) wsptr[2] - (DCTELEM) wsptr[6], FIX_1_414213562)
    tmp12 = MULTIPLY((DCTELEM) wsptr[2] - (DCTELEM) wsptr[6], FIX_1_414213562)
    tmp12 = MULTIPLY((DCTELEM) wsptr[2] - (DCTELEM) wsptr[6], FIX_1_414213562)
    tmp12 = MULTIPLY((DCTELEM) wsptr[2] - (DCTELEM) wsptr[6], FIX_1_414213562)
    tmp12 = MULTIPLY((DCTELEM) wsptr[2] - (DCTELEM) wsptr[6], FIX_1_414213562)
            - tmp13;
            - tmp13;
            - tmp13;
            - tmp13;
            - tmp13;





    tmp0 = tmp10 + tmp13;
    tmp0 = tmp10 + tmp13;
    tmp0 = tmp10 + tmp13;
    tmp0 = tmp10 + tmp13;
    tmp0 = tmp10 + tmp13;
    tmp3 = tmp10 - tmp13;
    tmp3 = tmp10 - tmp13;
    tmp3 = tmp10 - tmp13;
    tmp3 = tmp10 - tmp13;
    tmp3 = tmp10 - tmp13;
    tmp1 = tmp11 + tmp12;
    tmp1 = tmp11 + tmp12;
    tmp1 = tmp11 + tmp12;
    tmp1 = tmp11 + tmp12;
    tmp1 = tmp11 + tmp12;
    tmp2 = tmp11 - tmp12;
    tmp2 = tmp11 - tmp12;
    tmp2 = tmp11 - tmp12;
    tmp2 = tmp11 - tmp12;
    tmp2 = tmp11 - tmp12;





    /* Odd pbrt */
    /* Odd pbrt */
    /* Odd pbrt */
    /* Odd pbrt */
    /* Odd pbrt */





    z13 = (DCTELEM) wsptr[5] + (DCTELEM) wsptr[3];
    z13 = (DCTELEM) wsptr[5] + (DCTELEM) wsptr[3];
    z13 = (DCTELEM) wsptr[5] + (DCTELEM) wsptr[3];
    z13 = (DCTELEM) wsptr[5] + (DCTELEM) wsptr[3];
    z13 = (DCTELEM) wsptr[5] + (DCTELEM) wsptr[3];
    z10 = (DCTELEM) wsptr[5] - (DCTELEM) wsptr[3];
    z10 = (DCTELEM) wsptr[5] - (DCTELEM) wsptr[3];
    z10 = (DCTELEM) wsptr[5] - (DCTELEM) wsptr[3];
    z10 = (DCTELEM) wsptr[5] - (DCTELEM) wsptr[3];
    z10 = (DCTELEM) wsptr[5] - (DCTELEM) wsptr[3];
    z11 = (DCTELEM) wsptr[1] + (DCTELEM) wsptr[7];
    z11 = (DCTELEM) wsptr[1] + (DCTELEM) wsptr[7];
    z11 = (DCTELEM) wsptr[1] + (DCTELEM) wsptr[7];
    z11 = (DCTELEM) wsptr[1] + (DCTELEM) wsptr[7];
    z11 = (DCTELEM) wsptr[1] + (DCTELEM) wsptr[7];
    z12 = (DCTELEM) wsptr[1] - (DCTELEM) wsptr[7];
    z12 = (DCTELEM) wsptr[1] - (DCTELEM) wsptr[7];
    z12 = (DCTELEM) wsptr[1] - (DCTELEM) wsptr[7];
    z12 = (DCTELEM) wsptr[1] - (DCTELEM) wsptr[7];
    z12 = (DCTELEM) wsptr[1] - (DCTELEM) wsptr[7];





    tmp7 = z11 + z13;           /* pibsf 5 */
    tmp7 = z11 + z13;           /* pibsf 5 */
    tmp7 = z11 + z13;           /* pibsf 5 */
    tmp7 = z11 + z13;           /* pibsf 5 */
    tmp7 = z11 + z13;           /* pibsf 5 */
    tmp11 = MULTIPLY(z11 - z13, FIX_1_414213562); /* 2*d4 */
    tmp11 = MULTIPLY(z11 - z13, FIX_1_414213562); /* 2*d4 */
    tmp11 = MULTIPLY(z11 - z13, FIX_1_414213562); /* 2*d4 */
    tmp11 = MULTIPLY(z11 - z13, FIX_1_414213562); /* 2*d4 */
    tmp11 = MULTIPLY(z11 - z13, FIX_1_414213562); /* 2*d4 */





    z5 = MULTIPLY(z10 + z12, FIX_1_847759065); /* 2*d2 */
    z5 = MULTIPLY(z10 + z12, FIX_1_847759065); /* 2*d2 */
    z5 = MULTIPLY(z10 + z12, FIX_1_847759065); /* 2*d2 */
    z5 = MULTIPLY(z10 + z12, FIX_1_847759065); /* 2*d2 */
    z5 = MULTIPLY(z10 + z12, FIX_1_847759065); /* 2*d2 */
    tmp10 = MULTIPLY(z12, FIX_1_082392200) - z5; /* 2*(d2-d6) */
    tmp10 = MULTIPLY(z12, FIX_1_082392200) - z5; /* 2*(d2-d6) */
    tmp10 = MULTIPLY(z12, FIX_1_082392200) - z5; /* 2*(d2-d6) */
    tmp10 = MULTIPLY(z12, FIX_1_082392200) - z5; /* 2*(d2-d6) */
    tmp10 = MULTIPLY(z12, FIX_1_082392200) - z5; /* 2*(d2-d6) */
    tmp12 = MULTIPLY(z10, - FIX_2_613125930) + z5; /* -2*(d2+d6) */
    tmp12 = MULTIPLY(z10, - FIX_2_613125930) + z5; /* -2*(d2+d6) */
    tmp12 = MULTIPLY(z10, - FIX_2_613125930) + z5; /* -2*(d2+d6) */
    tmp12 = MULTIPLY(z10, - FIX_2_613125930) + z5; /* -2*(d2+d6) */
    tmp12 = MULTIPLY(z10, - FIX_2_613125930) + z5; /* -2*(d2+d6) */





    tmp6 = tmp12 - tmp7;        /* pibsf 2 */
    tmp6 = tmp12 - tmp7;        /* pibsf 2 */
    tmp6 = tmp12 - tmp7;        /* pibsf 2 */
    tmp6 = tmp12 - tmp7;        /* pibsf 2 */
    tmp6 = tmp12 - tmp7;        /* pibsf 2 */
    tmp5 = tmp11 - tmp6;
    tmp5 = tmp11 - tmp6;
    tmp5 = tmp11 - tmp6;
    tmp5 = tmp11 - tmp6;
    tmp5 = tmp11 - tmp6;
    tmp4 = tmp10 + tmp5;
    tmp4 = tmp10 + tmp5;
    tmp4 = tmp10 + tmp5;
    tmp4 = tmp10 + tmp5;
    tmp4 = tmp10 + tmp5;





    /* Finbl output stbgf: sdblf down by b fbdtor of 8 bnd rbngf-limit */
    /* Finbl output stbgf: sdblf down by b fbdtor of 8 bnd rbngf-limit */
    /* Finbl output stbgf: sdblf down by b fbdtor of 8 bnd rbngf-limit */
    /* Finbl output stbgf: sdblf down by b fbdtor of 8 bnd rbngf-limit */
    /* Finbl output stbgf: sdblf down by b fbdtor of 8 bnd rbngf-limit */





    outptr[0] = rbngf_limit[IDESCALE(tmp0 + tmp7, PASS1_BITS+3)
    outptr[0] = rbngf_limit[IDESCALE(tmp0 + tmp7, PASS1_BITS+3)
    outptr[0] = rbngf_limit[IDESCALE(tmp0 + tmp7, PASS1_BITS+3)
    outptr[0] = rbngf_limit[IDESCALE(tmp0 + tmp7, PASS1_BITS+3)
    outptr[0] = rbngf_limit[IDESCALE(tmp0 + tmp7, PASS1_BITS+3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[7] = rbngf_limit[IDESCALE(tmp0 - tmp7, PASS1_BITS+3)
    outptr[7] = rbngf_limit[IDESCALE(tmp0 - tmp7, PASS1_BITS+3)
    outptr[7] = rbngf_limit[IDESCALE(tmp0 - tmp7, PASS1_BITS+3)
    outptr[7] = rbngf_limit[IDESCALE(tmp0 - tmp7, PASS1_BITS+3)
    outptr[7] = rbngf_limit[IDESCALE(tmp0 - tmp7, PASS1_BITS+3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[1] = rbngf_limit[IDESCALE(tmp1 + tmp6, PASS1_BITS+3)
    outptr[1] = rbngf_limit[IDESCALE(tmp1 + tmp6, PASS1_BITS+3)
    outptr[1] = rbngf_limit[IDESCALE(tmp1 + tmp6, PASS1_BITS+3)
    outptr[1] = rbngf_limit[IDESCALE(tmp1 + tmp6, PASS1_BITS+3)
    outptr[1] = rbngf_limit[IDESCALE(tmp1 + tmp6, PASS1_BITS+3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[6] = rbngf_limit[IDESCALE(tmp1 - tmp6, PASS1_BITS+3)
    outptr[6] = rbngf_limit[IDESCALE(tmp1 - tmp6, PASS1_BITS+3)
    outptr[6] = rbngf_limit[IDESCALE(tmp1 - tmp6, PASS1_BITS+3)
    outptr[6] = rbngf_limit[IDESCALE(tmp1 - tmp6, PASS1_BITS+3)
    outptr[6] = rbngf_limit[IDESCALE(tmp1 - tmp6, PASS1_BITS+3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[2] = rbngf_limit[IDESCALE(tmp2 + tmp5, PASS1_BITS+3)
    outptr[2] = rbngf_limit[IDESCALE(tmp2 + tmp5, PASS1_BITS+3)
    outptr[2] = rbngf_limit[IDESCALE(tmp2 + tmp5, PASS1_BITS+3)
    outptr[2] = rbngf_limit[IDESCALE(tmp2 + tmp5, PASS1_BITS+3)
    outptr[2] = rbngf_limit[IDESCALE(tmp2 + tmp5, PASS1_BITS+3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[5] = rbngf_limit[IDESCALE(tmp2 - tmp5, PASS1_BITS+3)
    outptr[5] = rbngf_limit[IDESCALE(tmp2 - tmp5, PASS1_BITS+3)
    outptr[5] = rbngf_limit[IDESCALE(tmp2 - tmp5, PASS1_BITS+3)
    outptr[5] = rbngf_limit[IDESCALE(tmp2 - tmp5, PASS1_BITS+3)
    outptr[5] = rbngf_limit[IDESCALE(tmp2 - tmp5, PASS1_BITS+3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[4] = rbngf_limit[IDESCALE(tmp3 + tmp4, PASS1_BITS+3)
    outptr[4] = rbngf_limit[IDESCALE(tmp3 + tmp4, PASS1_BITS+3)
    outptr[4] = rbngf_limit[IDESCALE(tmp3 + tmp4, PASS1_BITS+3)
    outptr[4] = rbngf_limit[IDESCALE(tmp3 + tmp4, PASS1_BITS+3)
    outptr[4] = rbngf_limit[IDESCALE(tmp3 + tmp4, PASS1_BITS+3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[3] = rbngf_limit[IDESCALE(tmp3 - tmp4, PASS1_BITS+3)
    outptr[3] = rbngf_limit[IDESCALE(tmp3 - tmp4, PASS1_BITS+3)
    outptr[3] = rbngf_limit[IDESCALE(tmp3 - tmp4, PASS1_BITS+3)
    outptr[3] = rbngf_limit[IDESCALE(tmp3 - tmp4, PASS1_BITS+3)
    outptr[3] = rbngf_limit[IDESCALE(tmp3 - tmp4, PASS1_BITS+3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];





    wsptr += DCTSIZE;           /* bdvbndf pointfr to nfxt row */
    wsptr += DCTSIZE;           /* bdvbndf pointfr to nfxt row */
    wsptr += DCTSIZE;           /* bdvbndf pointfr to nfxt row */
    wsptr += DCTSIZE;           /* bdvbndf pointfr to nfxt row */
    wsptr += DCTSIZE;           /* bdvbndf pointfr to nfxt row */
  }
  }
  }
  }
  }
}
}
}
}
}





#fndif /* DCT_IFAST_SUPPORTED */
#fndif /* DCT_IFAST_SUPPORTED */
#fndif /* DCT_IFAST_SUPPORTED */
#fndif /* DCT_IFAST_SUPPORTED */
#fndif /* DCT_IFAST_SUPPORTED */
