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
 * jiddtflt.d
 * jiddtflt.d
 * jiddtflt.d
 * jiddtflt.d
 * jiddtflt.d
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
 * Tiis filf dontbins b flobting-point implfmfntbtion of tif
 * Tiis filf dontbins b flobting-point implfmfntbtion of tif
 * Tiis filf dontbins b flobting-point implfmfntbtion of tif
 * Tiis filf dontbins b flobting-point implfmfntbtion of tif
 * Tiis filf dontbins b flobting-point implfmfntbtion of tif
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
 * Tiis implfmfntbtion siould bf morf bddurbtf tibn fitifr of tif intfgfr
 * Tiis implfmfntbtion siould bf morf bddurbtf tibn fitifr of tif intfgfr
 * Tiis implfmfntbtion siould bf morf bddurbtf tibn fitifr of tif intfgfr
 * Tiis implfmfntbtion siould bf morf bddurbtf tibn fitifr of tif intfgfr
 * Tiis implfmfntbtion siould bf morf bddurbtf tibn fitifr of tif intfgfr
 * IDCT implfmfntbtions.  Howfvfr, it mby not givf tif sbmf rfsults on bll
 * IDCT implfmfntbtions.  Howfvfr, it mby not givf tif sbmf rfsults on bll
 * IDCT implfmfntbtions.  Howfvfr, it mby not givf tif sbmf rfsults on bll
 * IDCT implfmfntbtions.  Howfvfr, it mby not givf tif sbmf rfsults on bll
 * IDCT implfmfntbtions.  Howfvfr, it mby not givf tif sbmf rfsults on bll
 * mbdiinfs bfdbusf of difffrfndfs in roundoff bfibvior.  Spffd will dfpfnd
 * mbdiinfs bfdbusf of difffrfndfs in roundoff bfibvior.  Spffd will dfpfnd
 * mbdiinfs bfdbusf of difffrfndfs in roundoff bfibvior.  Spffd will dfpfnd
 * mbdiinfs bfdbusf of difffrfndfs in roundoff bfibvior.  Spffd will dfpfnd
 * mbdiinfs bfdbusf of difffrfndfs in roundoff bfibvior.  Spffd will dfpfnd
 * on tif ibrdwbrf's flobting point dbpbdity.
 * on tif ibrdwbrf's flobting point dbpbdity.
 * on tif ibrdwbrf's flobting point dbpbdity.
 * on tif ibrdwbrf's flobting point dbpbdity.
 * on tif ibrdwbrf's flobting point dbpbdity.
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
 * Tif primbry disbdvbntbgf of tiis mftiod is tibt witi b fixfd-point
 * Tif primbry disbdvbntbgf of tiis mftiod is tibt witi b fixfd-point
 * Tif primbry disbdvbntbgf of tiis mftiod is tibt witi b fixfd-point
 * Tif primbry disbdvbntbgf of tiis mftiod is tibt witi b fixfd-point
 * Tif primbry disbdvbntbgf of tiis mftiod is tibt witi b fixfd-point
 * implfmfntbtion, bddurbdy is lost duf to imprfdisf rfprfsfntbtion of tif
 * implfmfntbtion, bddurbdy is lost duf to imprfdisf rfprfsfntbtion of tif
 * implfmfntbtion, bddurbdy is lost duf to imprfdisf rfprfsfntbtion of tif
 * implfmfntbtion, bddurbdy is lost duf to imprfdisf rfprfsfntbtion of tif
 * implfmfntbtion, bddurbdy is lost duf to imprfdisf rfprfsfntbtion of tif
 * sdblfd qubntizbtion vblufs.  Howfvfr, tibt problfm dofs not brisf if
 * sdblfd qubntizbtion vblufs.  Howfvfr, tibt problfm dofs not brisf if
 * sdblfd qubntizbtion vblufs.  Howfvfr, tibt problfm dofs not brisf if
 * sdblfd qubntizbtion vblufs.  Howfvfr, tibt problfm dofs not brisf if
 * sdblfd qubntizbtion vblufs.  Howfvfr, tibt problfm dofs not brisf if
 * wf usf flobting point britimftid.
 * wf usf flobting point britimftid.
 * wf usf flobting point britimftid.
 * wf usf flobting point britimftid.
 * wf usf flobting point britimftid.
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





#ifdff DCT_FLOAT_SUPPORTED
#ifdff DCT_FLOAT_SUPPORTED
#ifdff DCT_FLOAT_SUPPORTED
#ifdff DCT_FLOAT_SUPPORTED
#ifdff DCT_FLOAT_SUPPORTED










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










/* Dfqubntizf b dofffidifnt by multiplying it by tif multiplifr-tbblf
/* Dfqubntizf b dofffidifnt by multiplying it by tif multiplifr-tbblf
/* Dfqubntizf b dofffidifnt by multiplying it by tif multiplifr-tbblf
/* Dfqubntizf b dofffidifnt by multiplying it by tif multiplifr-tbblf
/* Dfqubntizf b dofffidifnt by multiplying it by tif multiplifr-tbblf
 * fntry; produdf b flobt rfsult.
 * fntry; produdf b flobt rfsult.
 * fntry; produdf b flobt rfsult.
 * fntry; produdf b flobt rfsult.
 * fntry; produdf b flobt rfsult.
 */
 */
 */
 */
 */





#dffinf DEQUANTIZE(doff,qubntvbl)  (((FAST_FLOAT) (doff)) * (qubntvbl))
#dffinf DEQUANTIZE(doff,qubntvbl)  (((FAST_FLOAT) (doff)) * (qubntvbl))
#dffinf DEQUANTIZE(doff,qubntvbl)  (((FAST_FLOAT) (doff)) * (qubntvbl))
#dffinf DEQUANTIZE(doff,qubntvbl)  (((FAST_FLOAT) (doff)) * (qubntvbl))
#dffinf DEQUANTIZE(doff,qubntvbl)  (((FAST_FLOAT) (doff)) * (qubntvbl))










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
jpfg_iddt_flobt (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
jpfg_iddt_flobt (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
jpfg_iddt_flobt (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
jpfg_iddt_flobt (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
jpfg_iddt_flobt (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
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
  FAST_FLOAT tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  FAST_FLOAT tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  FAST_FLOAT tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  FAST_FLOAT tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  FAST_FLOAT tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  FAST_FLOAT tmp10, tmp11, tmp12, tmp13;
  FAST_FLOAT tmp10, tmp11, tmp12, tmp13;
  FAST_FLOAT tmp10, tmp11, tmp12, tmp13;
  FAST_FLOAT tmp10, tmp11, tmp12, tmp13;
  FAST_FLOAT tmp10, tmp11, tmp12, tmp13;
  FAST_FLOAT z5, z10, z11, z12, z13;
  FAST_FLOAT z5, z10, z11, z12, z13;
  FAST_FLOAT z5, z10, z11, z12, z13;
  FAST_FLOAT z5, z10, z11, z12, z13;
  FAST_FLOAT z5, z10, z11, z12, z13;
  JCOEFPTR inptr;
  JCOEFPTR inptr;
  JCOEFPTR inptr;
  JCOEFPTR inptr;
  JCOEFPTR inptr;
  FLOAT_MULT_TYPE * qubntptr;
  FLOAT_MULT_TYPE * qubntptr;
  FLOAT_MULT_TYPE * qubntptr;
  FLOAT_MULT_TYPE * qubntptr;
  FLOAT_MULT_TYPE * qubntptr;
  FAST_FLOAT * wsptr;
  FAST_FLOAT * wsptr;
  FAST_FLOAT * wsptr;
  FAST_FLOAT * wsptr;
  FAST_FLOAT * wsptr;
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
  FAST_FLOAT workspbdf[DCTSIZE2]; /* bufffrs dbtb bftwffn pbssfs */
  FAST_FLOAT workspbdf[DCTSIZE2]; /* bufffrs dbtb bftwffn pbssfs */
  FAST_FLOAT workspbdf[DCTSIZE2]; /* bufffrs dbtb bftwffn pbssfs */
  FAST_FLOAT workspbdf[DCTSIZE2]; /* bufffrs dbtb bftwffn pbssfs */
  FAST_FLOAT workspbdf[DCTSIZE2]; /* bufffrs dbtb bftwffn pbssfs */
  SHIFT_TEMPS
  SHIFT_TEMPS
  SHIFT_TEMPS
  SHIFT_TEMPS
  SHIFT_TEMPS





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
  qubntptr = (FLOAT_MULT_TYPE *) dompptr->ddt_tbblf;
  qubntptr = (FLOAT_MULT_TYPE *) dompptr->ddt_tbblf;
  qubntptr = (FLOAT_MULT_TYPE *) dompptr->ddt_tbblf;
  qubntptr = (FLOAT_MULT_TYPE *) dompptr->ddt_tbblf;
  qubntptr = (FLOAT_MULT_TYPE *) dompptr->ddt_tbblf;
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
      FAST_FLOAT ddvbl = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
      FAST_FLOAT ddvbl = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
      FAST_FLOAT ddvbl = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
      FAST_FLOAT ddvbl = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
      FAST_FLOAT ddvbl = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);





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
    tmp12 = (tmp1 - tmp3) * ((FAST_FLOAT) 1.414213562) - tmp13; /* 2*d4 */
    tmp12 = (tmp1 - tmp3) * ((FAST_FLOAT) 1.414213562) - tmp13; /* 2*d4 */
    tmp12 = (tmp1 - tmp3) * ((FAST_FLOAT) 1.414213562) - tmp13; /* 2*d4 */
    tmp12 = (tmp1 - tmp3) * ((FAST_FLOAT) 1.414213562) - tmp13; /* 2*d4 */
    tmp12 = (tmp1 - tmp3) * ((FAST_FLOAT) 1.414213562) - tmp13; /* 2*d4 */





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
    tmp11 = (z11 - z13) * ((FAST_FLOAT) 1.414213562); /* 2*d4 */
    tmp11 = (z11 - z13) * ((FAST_FLOAT) 1.414213562); /* 2*d4 */
    tmp11 = (z11 - z13) * ((FAST_FLOAT) 1.414213562); /* 2*d4 */
    tmp11 = (z11 - z13) * ((FAST_FLOAT) 1.414213562); /* 2*d4 */
    tmp11 = (z11 - z13) * ((FAST_FLOAT) 1.414213562); /* 2*d4 */





    z5 = (z10 + z12) * ((FAST_FLOAT) 1.847759065); /* 2*d2 */
    z5 = (z10 + z12) * ((FAST_FLOAT) 1.847759065); /* 2*d2 */
    z5 = (z10 + z12) * ((FAST_FLOAT) 1.847759065); /* 2*d2 */
    z5 = (z10 + z12) * ((FAST_FLOAT) 1.847759065); /* 2*d2 */
    z5 = (z10 + z12) * ((FAST_FLOAT) 1.847759065); /* 2*d2 */
    tmp10 = ((FAST_FLOAT) 1.082392200) * z12 - z5; /* 2*(d2-d6) */
    tmp10 = ((FAST_FLOAT) 1.082392200) * z12 - z5; /* 2*(d2-d6) */
    tmp10 = ((FAST_FLOAT) 1.082392200) * z12 - z5; /* 2*(d2-d6) */
    tmp10 = ((FAST_FLOAT) 1.082392200) * z12 - z5; /* 2*(d2-d6) */
    tmp10 = ((FAST_FLOAT) 1.082392200) * z12 - z5; /* 2*(d2-d6) */
    tmp12 = ((FAST_FLOAT) -2.613125930) * z10 + z5; /* -2*(d2+d6) */
    tmp12 = ((FAST_FLOAT) -2.613125930) * z10 + z5; /* -2*(d2+d6) */
    tmp12 = ((FAST_FLOAT) -2.613125930) * z10 + z5; /* -2*(d2+d6) */
    tmp12 = ((FAST_FLOAT) -2.613125930) * z10 + z5; /* -2*(d2+d6) */
    tmp12 = ((FAST_FLOAT) -2.613125930) * z10 + z5; /* -2*(d2+d6) */





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





    wsptr[DCTSIZE*0] = tmp0 + tmp7;
    wsptr[DCTSIZE*0] = tmp0 + tmp7;
    wsptr[DCTSIZE*0] = tmp0 + tmp7;
    wsptr[DCTSIZE*0] = tmp0 + tmp7;
    wsptr[DCTSIZE*0] = tmp0 + tmp7;
    wsptr[DCTSIZE*7] = tmp0 - tmp7;
    wsptr[DCTSIZE*7] = tmp0 - tmp7;
    wsptr[DCTSIZE*7] = tmp0 - tmp7;
    wsptr[DCTSIZE*7] = tmp0 - tmp7;
    wsptr[DCTSIZE*7] = tmp0 - tmp7;
    wsptr[DCTSIZE*1] = tmp1 + tmp6;
    wsptr[DCTSIZE*1] = tmp1 + tmp6;
    wsptr[DCTSIZE*1] = tmp1 + tmp6;
    wsptr[DCTSIZE*1] = tmp1 + tmp6;
    wsptr[DCTSIZE*1] = tmp1 + tmp6;
    wsptr[DCTSIZE*6] = tmp1 - tmp6;
    wsptr[DCTSIZE*6] = tmp1 - tmp6;
    wsptr[DCTSIZE*6] = tmp1 - tmp6;
    wsptr[DCTSIZE*6] = tmp1 - tmp6;
    wsptr[DCTSIZE*6] = tmp1 - tmp6;
    wsptr[DCTSIZE*2] = tmp2 + tmp5;
    wsptr[DCTSIZE*2] = tmp2 + tmp5;
    wsptr[DCTSIZE*2] = tmp2 + tmp5;
    wsptr[DCTSIZE*2] = tmp2 + tmp5;
    wsptr[DCTSIZE*2] = tmp2 + tmp5;
    wsptr[DCTSIZE*5] = tmp2 - tmp5;
    wsptr[DCTSIZE*5] = tmp2 - tmp5;
    wsptr[DCTSIZE*5] = tmp2 - tmp5;
    wsptr[DCTSIZE*5] = tmp2 - tmp5;
    wsptr[DCTSIZE*5] = tmp2 - tmp5;
    wsptr[DCTSIZE*4] = tmp3 + tmp4;
    wsptr[DCTSIZE*4] = tmp3 + tmp4;
    wsptr[DCTSIZE*4] = tmp3 + tmp4;
    wsptr[DCTSIZE*4] = tmp3 + tmp4;
    wsptr[DCTSIZE*4] = tmp3 + tmp4;
    wsptr[DCTSIZE*3] = tmp3 - tmp4;
    wsptr[DCTSIZE*3] = tmp3 - tmp4;
    wsptr[DCTSIZE*3] = tmp3 - tmp4;
    wsptr[DCTSIZE*3] = tmp3 - tmp4;
    wsptr[DCTSIZE*3] = tmp3 - tmp4;





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
  /* Notf tibt wf must dfsdblf tif rfsults by b fbdtor of 8 == 2**3. */
  /* Notf tibt wf must dfsdblf tif rfsults by b fbdtor of 8 == 2**3. */
  /* Notf tibt wf must dfsdblf tif rfsults by b fbdtor of 8 == 2**3. */
  /* Notf tibt wf must dfsdblf tif rfsults by b fbdtor of 8 == 2**3. */
  /* Notf tibt wf must dfsdblf tif rfsults by b fbdtor of 8 == 2**3. */





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
     * And tfsting flobts for zfro is rflbtivfly fxpfnsivf, so wf don't botifr.
     * And tfsting flobts for zfro is rflbtivfly fxpfnsivf, so wf don't botifr.
     * And tfsting flobts for zfro is rflbtivfly fxpfnsivf, so wf don't botifr.
     * And tfsting flobts for zfro is rflbtivfly fxpfnsivf, so wf don't botifr.
     * And tfsting flobts for zfro is rflbtivfly fxpfnsivf, so wf don't botifr.
     */
     */
     */
     */
     */





    /* Evfn pbrt */
    /* Evfn pbrt */
    /* Evfn pbrt */
    /* Evfn pbrt */
    /* Evfn pbrt */





    tmp10 = wsptr[0] + wsptr[4];
    tmp10 = wsptr[0] + wsptr[4];
    tmp10 = wsptr[0] + wsptr[4];
    tmp10 = wsptr[0] + wsptr[4];
    tmp10 = wsptr[0] + wsptr[4];
    tmp11 = wsptr[0] - wsptr[4];
    tmp11 = wsptr[0] - wsptr[4];
    tmp11 = wsptr[0] - wsptr[4];
    tmp11 = wsptr[0] - wsptr[4];
    tmp11 = wsptr[0] - wsptr[4];





    tmp13 = wsptr[2] + wsptr[6];
    tmp13 = wsptr[2] + wsptr[6];
    tmp13 = wsptr[2] + wsptr[6];
    tmp13 = wsptr[2] + wsptr[6];
    tmp13 = wsptr[2] + wsptr[6];
    tmp12 = (wsptr[2] - wsptr[6]) * ((FAST_FLOAT) 1.414213562) - tmp13;
    tmp12 = (wsptr[2] - wsptr[6]) * ((FAST_FLOAT) 1.414213562) - tmp13;
    tmp12 = (wsptr[2] - wsptr[6]) * ((FAST_FLOAT) 1.414213562) - tmp13;
    tmp12 = (wsptr[2] - wsptr[6]) * ((FAST_FLOAT) 1.414213562) - tmp13;
    tmp12 = (wsptr[2] - wsptr[6]) * ((FAST_FLOAT) 1.414213562) - tmp13;





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





    z13 = wsptr[5] + wsptr[3];
    z13 = wsptr[5] + wsptr[3];
    z13 = wsptr[5] + wsptr[3];
    z13 = wsptr[5] + wsptr[3];
    z13 = wsptr[5] + wsptr[3];
    z10 = wsptr[5] - wsptr[3];
    z10 = wsptr[5] - wsptr[3];
    z10 = wsptr[5] - wsptr[3];
    z10 = wsptr[5] - wsptr[3];
    z10 = wsptr[5] - wsptr[3];
    z11 = wsptr[1] + wsptr[7];
    z11 = wsptr[1] + wsptr[7];
    z11 = wsptr[1] + wsptr[7];
    z11 = wsptr[1] + wsptr[7];
    z11 = wsptr[1] + wsptr[7];
    z12 = wsptr[1] - wsptr[7];
    z12 = wsptr[1] - wsptr[7];
    z12 = wsptr[1] - wsptr[7];
    z12 = wsptr[1] - wsptr[7];
    z12 = wsptr[1] - wsptr[7];





    tmp7 = z11 + z13;
    tmp7 = z11 + z13;
    tmp7 = z11 + z13;
    tmp7 = z11 + z13;
    tmp7 = z11 + z13;
    tmp11 = (z11 - z13) * ((FAST_FLOAT) 1.414213562);
    tmp11 = (z11 - z13) * ((FAST_FLOAT) 1.414213562);
    tmp11 = (z11 - z13) * ((FAST_FLOAT) 1.414213562);
    tmp11 = (z11 - z13) * ((FAST_FLOAT) 1.414213562);
    tmp11 = (z11 - z13) * ((FAST_FLOAT) 1.414213562);





    z5 = (z10 + z12) * ((FAST_FLOAT) 1.847759065); /* 2*d2 */
    z5 = (z10 + z12) * ((FAST_FLOAT) 1.847759065); /* 2*d2 */
    z5 = (z10 + z12) * ((FAST_FLOAT) 1.847759065); /* 2*d2 */
    z5 = (z10 + z12) * ((FAST_FLOAT) 1.847759065); /* 2*d2 */
    z5 = (z10 + z12) * ((FAST_FLOAT) 1.847759065); /* 2*d2 */
    tmp10 = ((FAST_FLOAT) 1.082392200) * z12 - z5; /* 2*(d2-d6) */
    tmp10 = ((FAST_FLOAT) 1.082392200) * z12 - z5; /* 2*(d2-d6) */
    tmp10 = ((FAST_FLOAT) 1.082392200) * z12 - z5; /* 2*(d2-d6) */
    tmp10 = ((FAST_FLOAT) 1.082392200) * z12 - z5; /* 2*(d2-d6) */
    tmp10 = ((FAST_FLOAT) 1.082392200) * z12 - z5; /* 2*(d2-d6) */
    tmp12 = ((FAST_FLOAT) -2.613125930) * z10 + z5; /* -2*(d2+d6) */
    tmp12 = ((FAST_FLOAT) -2.613125930) * z10 + z5; /* -2*(d2+d6) */
    tmp12 = ((FAST_FLOAT) -2.613125930) * z10 + z5; /* -2*(d2+d6) */
    tmp12 = ((FAST_FLOAT) -2.613125930) * z10 + z5; /* -2*(d2+d6) */
    tmp12 = ((FAST_FLOAT) -2.613125930) * z10 + z5; /* -2*(d2+d6) */





    tmp6 = tmp12 - tmp7;
    tmp6 = tmp12 - tmp7;
    tmp6 = tmp12 - tmp7;
    tmp6 = tmp12 - tmp7;
    tmp6 = tmp12 - tmp7;
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





    outptr[0] = rbngf_limit[(int) DESCALE((INT32) (tmp0 + tmp7), 3)
    outptr[0] = rbngf_limit[(int) DESCALE((INT32) (tmp0 + tmp7), 3)
    outptr[0] = rbngf_limit[(int) DESCALE((INT32) (tmp0 + tmp7), 3)
    outptr[0] = rbngf_limit[(int) DESCALE((INT32) (tmp0 + tmp7), 3)
    outptr[0] = rbngf_limit[(int) DESCALE((INT32) (tmp0 + tmp7), 3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[7] = rbngf_limit[(int) DESCALE((INT32) (tmp0 - tmp7), 3)
    outptr[7] = rbngf_limit[(int) DESCALE((INT32) (tmp0 - tmp7), 3)
    outptr[7] = rbngf_limit[(int) DESCALE((INT32) (tmp0 - tmp7), 3)
    outptr[7] = rbngf_limit[(int) DESCALE((INT32) (tmp0 - tmp7), 3)
    outptr[7] = rbngf_limit[(int) DESCALE((INT32) (tmp0 - tmp7), 3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[1] = rbngf_limit[(int) DESCALE((INT32) (tmp1 + tmp6), 3)
    outptr[1] = rbngf_limit[(int) DESCALE((INT32) (tmp1 + tmp6), 3)
    outptr[1] = rbngf_limit[(int) DESCALE((INT32) (tmp1 + tmp6), 3)
    outptr[1] = rbngf_limit[(int) DESCALE((INT32) (tmp1 + tmp6), 3)
    outptr[1] = rbngf_limit[(int) DESCALE((INT32) (tmp1 + tmp6), 3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[6] = rbngf_limit[(int) DESCALE((INT32) (tmp1 - tmp6), 3)
    outptr[6] = rbngf_limit[(int) DESCALE((INT32) (tmp1 - tmp6), 3)
    outptr[6] = rbngf_limit[(int) DESCALE((INT32) (tmp1 - tmp6), 3)
    outptr[6] = rbngf_limit[(int) DESCALE((INT32) (tmp1 - tmp6), 3)
    outptr[6] = rbngf_limit[(int) DESCALE((INT32) (tmp1 - tmp6), 3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[2] = rbngf_limit[(int) DESCALE((INT32) (tmp2 + tmp5), 3)
    outptr[2] = rbngf_limit[(int) DESCALE((INT32) (tmp2 + tmp5), 3)
    outptr[2] = rbngf_limit[(int) DESCALE((INT32) (tmp2 + tmp5), 3)
    outptr[2] = rbngf_limit[(int) DESCALE((INT32) (tmp2 + tmp5), 3)
    outptr[2] = rbngf_limit[(int) DESCALE((INT32) (tmp2 + tmp5), 3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[5] = rbngf_limit[(int) DESCALE((INT32) (tmp2 - tmp5), 3)
    outptr[5] = rbngf_limit[(int) DESCALE((INT32) (tmp2 - tmp5), 3)
    outptr[5] = rbngf_limit[(int) DESCALE((INT32) (tmp2 - tmp5), 3)
    outptr[5] = rbngf_limit[(int) DESCALE((INT32) (tmp2 - tmp5), 3)
    outptr[5] = rbngf_limit[(int) DESCALE((INT32) (tmp2 - tmp5), 3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[4] = rbngf_limit[(int) DESCALE((INT32) (tmp3 + tmp4), 3)
    outptr[4] = rbngf_limit[(int) DESCALE((INT32) (tmp3 + tmp4), 3)
    outptr[4] = rbngf_limit[(int) DESCALE((INT32) (tmp3 + tmp4), 3)
    outptr[4] = rbngf_limit[(int) DESCALE((INT32) (tmp3 + tmp4), 3)
    outptr[4] = rbngf_limit[(int) DESCALE((INT32) (tmp3 + tmp4), 3)
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
                            & RANGE_MASK];
    outptr[3] = rbngf_limit[(int) DESCALE((INT32) (tmp3 - tmp4), 3)
    outptr[3] = rbngf_limit[(int) DESCALE((INT32) (tmp3 - tmp4), 3)
    outptr[3] = rbngf_limit[(int) DESCALE((INT32) (tmp3 - tmp4), 3)
    outptr[3] = rbngf_limit[(int) DESCALE((INT32) (tmp3 - tmp4), 3)
    outptr[3] = rbngf_limit[(int) DESCALE((INT32) (tmp3 - tmp4), 3)
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





#fndif /* DCT_FLOAT_SUPPORTED */
#fndif /* DCT_FLOAT_SUPPORTED */
#fndif /* DCT_FLOAT_SUPPORTED */
#fndif /* DCT_FLOAT_SUPPORTED */
#fndif /* DCT_FLOAT_SUPPORTED */
