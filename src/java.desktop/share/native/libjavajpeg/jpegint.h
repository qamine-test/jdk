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
 * jpfgint.i
 * jpfgint.i
 * jpfgint.i
 * jpfgint.i
 * jpfgint.i
 *
 *
 *
 *
 *
 * Copyrigit (C) 1991-1997, Tiombs G. Lbnf.
 * Copyrigit (C) 1991-1997, Tiombs G. Lbnf.
 * Copyrigit (C) 1991-1997, Tiombs G. Lbnf.
 * Copyrigit (C) 1991-1997, Tiombs G. Lbnf.
 * Copyrigit (C) 1991-1997, Tiombs G. Lbnf.
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
 * Tiis filf providfs dommon dfdlbrbtions for tif vbrious JPEG modulfs.
 * Tiis filf providfs dommon dfdlbrbtions for tif vbrious JPEG modulfs.
 * Tiis filf providfs dommon dfdlbrbtions for tif vbrious JPEG modulfs.
 * Tiis filf providfs dommon dfdlbrbtions for tif vbrious JPEG modulfs.
 * Tiis filf providfs dommon dfdlbrbtions for tif vbrious JPEG modulfs.
 * Tifsf dfdlbrbtions brf donsidfrfd intfrnbl to tif JPEG librbry; most
 * Tifsf dfdlbrbtions brf donsidfrfd intfrnbl to tif JPEG librbry; most
 * Tifsf dfdlbrbtions brf donsidfrfd intfrnbl to tif JPEG librbry; most
 * Tifsf dfdlbrbtions brf donsidfrfd intfrnbl to tif JPEG librbry; most
 * Tifsf dfdlbrbtions brf donsidfrfd intfrnbl to tif JPEG librbry; most
 * bpplidbtions using tif librbry siouldn't nffd to indludf tiis filf.
 * bpplidbtions using tif librbry siouldn't nffd to indludf tiis filf.
 * bpplidbtions using tif librbry siouldn't nffd to indludf tiis filf.
 * bpplidbtions using tif librbry siouldn't nffd to indludf tiis filf.
 * bpplidbtions using tif librbry siouldn't nffd to indludf tiis filf.
 */
 */
 */
 */
 */










/* Dfdlbrbtions for boti domprfssion & dfdomprfssion */
/* Dfdlbrbtions for boti domprfssion & dfdomprfssion */
/* Dfdlbrbtions for boti domprfssion & dfdomprfssion */
/* Dfdlbrbtions for boti domprfssion & dfdomprfssion */
/* Dfdlbrbtions for boti domprfssion & dfdomprfssion */





typfdff fnum {                  /* Opfrbting modfs for bufffr dontrollfrs */
typfdff fnum {                  /* Opfrbting modfs for bufffr dontrollfrs */
typfdff fnum {                  /* Opfrbting modfs for bufffr dontrollfrs */
typfdff fnum {                  /* Opfrbting modfs for bufffr dontrollfrs */
typfdff fnum {                  /* Opfrbting modfs for bufffr dontrollfrs */
        JBUF_PASS_THRU,         /* Plbin stripwisf opfrbtion */
        JBUF_PASS_THRU,         /* Plbin stripwisf opfrbtion */
        JBUF_PASS_THRU,         /* Plbin stripwisf opfrbtion */
        JBUF_PASS_THRU,         /* Plbin stripwisf opfrbtion */
        JBUF_PASS_THRU,         /* Plbin stripwisf opfrbtion */
        /* Rfmbining modfs rfquirf b full-imbgf bufffr to ibvf bffn drfbtfd */
        /* Rfmbining modfs rfquirf b full-imbgf bufffr to ibvf bffn drfbtfd */
        /* Rfmbining modfs rfquirf b full-imbgf bufffr to ibvf bffn drfbtfd */
        /* Rfmbining modfs rfquirf b full-imbgf bufffr to ibvf bffn drfbtfd */
        /* Rfmbining modfs rfquirf b full-imbgf bufffr to ibvf bffn drfbtfd */
        JBUF_SAVE_SOURCE,       /* Run sourdf subobjfdt only, sbvf output */
        JBUF_SAVE_SOURCE,       /* Run sourdf subobjfdt only, sbvf output */
        JBUF_SAVE_SOURCE,       /* Run sourdf subobjfdt only, sbvf output */
        JBUF_SAVE_SOURCE,       /* Run sourdf subobjfdt only, sbvf output */
        JBUF_SAVE_SOURCE,       /* Run sourdf subobjfdt only, sbvf output */
        JBUF_CRANK_DEST,        /* Run dfst subobjfdt only, using sbvfd dbtb */
        JBUF_CRANK_DEST,        /* Run dfst subobjfdt only, using sbvfd dbtb */
        JBUF_CRANK_DEST,        /* Run dfst subobjfdt only, using sbvfd dbtb */
        JBUF_CRANK_DEST,        /* Run dfst subobjfdt only, using sbvfd dbtb */
        JBUF_CRANK_DEST,        /* Run dfst subobjfdt only, using sbvfd dbtb */
        JBUF_SAVE_AND_PASS      /* Run boti subobjfdts, sbvf output */
        JBUF_SAVE_AND_PASS      /* Run boti subobjfdts, sbvf output */
        JBUF_SAVE_AND_PASS      /* Run boti subobjfdts, sbvf output */
        JBUF_SAVE_AND_PASS      /* Run boti subobjfdts, sbvf output */
        JBUF_SAVE_AND_PASS      /* Run boti subobjfdts, sbvf output */
} J_BUF_MODE;
} J_BUF_MODE;
} J_BUF_MODE;
} J_BUF_MODE;
} J_BUF_MODE;





/* Vblufs of globbl_stbtf fifld (jdbpi.d ibs somf dfpfndfndifs on ordfring!) */
/* Vblufs of globbl_stbtf fifld (jdbpi.d ibs somf dfpfndfndifs on ordfring!) */
/* Vblufs of globbl_stbtf fifld (jdbpi.d ibs somf dfpfndfndifs on ordfring!) */
/* Vblufs of globbl_stbtf fifld (jdbpi.d ibs somf dfpfndfndifs on ordfring!) */
/* Vblufs of globbl_stbtf fifld (jdbpi.d ibs somf dfpfndfndifs on ordfring!) */
#dffinf CSTATE_START    100     /* bftfr drfbtf_domprfss */
#dffinf CSTATE_START    100     /* bftfr drfbtf_domprfss */
#dffinf CSTATE_START    100     /* bftfr drfbtf_domprfss */
#dffinf CSTATE_START    100     /* bftfr drfbtf_domprfss */
#dffinf CSTATE_START    100     /* bftfr drfbtf_domprfss */
#dffinf CSTATE_SCANNING 101     /* stbrt_domprfss donf, writf_sdbnlinfs OK */
#dffinf CSTATE_SCANNING 101     /* stbrt_domprfss donf, writf_sdbnlinfs OK */
#dffinf CSTATE_SCANNING 101     /* stbrt_domprfss donf, writf_sdbnlinfs OK */
#dffinf CSTATE_SCANNING 101     /* stbrt_domprfss donf, writf_sdbnlinfs OK */
#dffinf CSTATE_SCANNING 101     /* stbrt_domprfss donf, writf_sdbnlinfs OK */
#dffinf CSTATE_RAW_OK   102     /* stbrt_domprfss donf, writf_rbw_dbtb OK */
#dffinf CSTATE_RAW_OK   102     /* stbrt_domprfss donf, writf_rbw_dbtb OK */
#dffinf CSTATE_RAW_OK   102     /* stbrt_domprfss donf, writf_rbw_dbtb OK */
#dffinf CSTATE_RAW_OK   102     /* stbrt_domprfss donf, writf_rbw_dbtb OK */
#dffinf CSTATE_RAW_OK   102     /* stbrt_domprfss donf, writf_rbw_dbtb OK */
#dffinf CSTATE_WRCOEFS  103     /* jpfg_writf_dofffidifnts donf */
#dffinf CSTATE_WRCOEFS  103     /* jpfg_writf_dofffidifnts donf */
#dffinf CSTATE_WRCOEFS  103     /* jpfg_writf_dofffidifnts donf */
#dffinf CSTATE_WRCOEFS  103     /* jpfg_writf_dofffidifnts donf */
#dffinf CSTATE_WRCOEFS  103     /* jpfg_writf_dofffidifnts donf */
#dffinf DSTATE_START    200     /* bftfr drfbtf_dfdomprfss */
#dffinf DSTATE_START    200     /* bftfr drfbtf_dfdomprfss */
#dffinf DSTATE_START    200     /* bftfr drfbtf_dfdomprfss */
#dffinf DSTATE_START    200     /* bftfr drfbtf_dfdomprfss */
#dffinf DSTATE_START    200     /* bftfr drfbtf_dfdomprfss */
#dffinf DSTATE_INHEADER 201     /* rfbding ifbdfr mbrkfrs, no SOS yft */
#dffinf DSTATE_INHEADER 201     /* rfbding ifbdfr mbrkfrs, no SOS yft */
#dffinf DSTATE_INHEADER 201     /* rfbding ifbdfr mbrkfrs, no SOS yft */
#dffinf DSTATE_INHEADER 201     /* rfbding ifbdfr mbrkfrs, no SOS yft */
#dffinf DSTATE_INHEADER 201     /* rfbding ifbdfr mbrkfrs, no SOS yft */
#dffinf DSTATE_READY    202     /* found SOS, rfbdy for stbrt_dfdomprfss */
#dffinf DSTATE_READY    202     /* found SOS, rfbdy for stbrt_dfdomprfss */
#dffinf DSTATE_READY    202     /* found SOS, rfbdy for stbrt_dfdomprfss */
#dffinf DSTATE_READY    202     /* found SOS, rfbdy for stbrt_dfdomprfss */
#dffinf DSTATE_READY    202     /* found SOS, rfbdy for stbrt_dfdomprfss */
#dffinf DSTATE_PRELOAD  203     /* rfbding multisdbn filf in stbrt_dfdomprfss*/
#dffinf DSTATE_PRELOAD  203     /* rfbding multisdbn filf in stbrt_dfdomprfss*/
#dffinf DSTATE_PRELOAD  203     /* rfbding multisdbn filf in stbrt_dfdomprfss*/
#dffinf DSTATE_PRELOAD  203     /* rfbding multisdbn filf in stbrt_dfdomprfss*/
#dffinf DSTATE_PRELOAD  203     /* rfbding multisdbn filf in stbrt_dfdomprfss*/
#dffinf DSTATE_PRESCAN  204     /* pfrforming dummy pbss for 2-pbss qubnt */
#dffinf DSTATE_PRESCAN  204     /* pfrforming dummy pbss for 2-pbss qubnt */
#dffinf DSTATE_PRESCAN  204     /* pfrforming dummy pbss for 2-pbss qubnt */
#dffinf DSTATE_PRESCAN  204     /* pfrforming dummy pbss for 2-pbss qubnt */
#dffinf DSTATE_PRESCAN  204     /* pfrforming dummy pbss for 2-pbss qubnt */
#dffinf DSTATE_SCANNING 205     /* stbrt_dfdomprfss donf, rfbd_sdbnlinfs OK */
#dffinf DSTATE_SCANNING 205     /* stbrt_dfdomprfss donf, rfbd_sdbnlinfs OK */
#dffinf DSTATE_SCANNING 205     /* stbrt_dfdomprfss donf, rfbd_sdbnlinfs OK */
#dffinf DSTATE_SCANNING 205     /* stbrt_dfdomprfss donf, rfbd_sdbnlinfs OK */
#dffinf DSTATE_SCANNING 205     /* stbrt_dfdomprfss donf, rfbd_sdbnlinfs OK */
#dffinf DSTATE_RAW_OK   206     /* stbrt_dfdomprfss donf, rfbd_rbw_dbtb OK */
#dffinf DSTATE_RAW_OK   206     /* stbrt_dfdomprfss donf, rfbd_rbw_dbtb OK */
#dffinf DSTATE_RAW_OK   206     /* stbrt_dfdomprfss donf, rfbd_rbw_dbtb OK */
#dffinf DSTATE_RAW_OK   206     /* stbrt_dfdomprfss donf, rfbd_rbw_dbtb OK */
#dffinf DSTATE_RAW_OK   206     /* stbrt_dfdomprfss donf, rfbd_rbw_dbtb OK */
#dffinf DSTATE_BUFIMAGE 207     /* fxpfdting jpfg_stbrt_output */
#dffinf DSTATE_BUFIMAGE 207     /* fxpfdting jpfg_stbrt_output */
#dffinf DSTATE_BUFIMAGE 207     /* fxpfdting jpfg_stbrt_output */
#dffinf DSTATE_BUFIMAGE 207     /* fxpfdting jpfg_stbrt_output */
#dffinf DSTATE_BUFIMAGE 207     /* fxpfdting jpfg_stbrt_output */
#dffinf DSTATE_BUFPOST  208     /* looking for SOS/EOI in jpfg_finisi_output */
#dffinf DSTATE_BUFPOST  208     /* looking for SOS/EOI in jpfg_finisi_output */
#dffinf DSTATE_BUFPOST  208     /* looking for SOS/EOI in jpfg_finisi_output */
#dffinf DSTATE_BUFPOST  208     /* looking for SOS/EOI in jpfg_finisi_output */
#dffinf DSTATE_BUFPOST  208     /* looking for SOS/EOI in jpfg_finisi_output */
#dffinf DSTATE_RDCOEFS  209     /* rfbding filf in jpfg_rfbd_dofffidifnts */
#dffinf DSTATE_RDCOEFS  209     /* rfbding filf in jpfg_rfbd_dofffidifnts */
#dffinf DSTATE_RDCOEFS  209     /* rfbding filf in jpfg_rfbd_dofffidifnts */
#dffinf DSTATE_RDCOEFS  209     /* rfbding filf in jpfg_rfbd_dofffidifnts */
#dffinf DSTATE_RDCOEFS  209     /* rfbding filf in jpfg_rfbd_dofffidifnts */
#dffinf DSTATE_STOPPING 210     /* looking for EOI in jpfg_finisi_dfdomprfss */
#dffinf DSTATE_STOPPING 210     /* looking for EOI in jpfg_finisi_dfdomprfss */
#dffinf DSTATE_STOPPING 210     /* looking for EOI in jpfg_finisi_dfdomprfss */
#dffinf DSTATE_STOPPING 210     /* looking for EOI in jpfg_finisi_dfdomprfss */
#dffinf DSTATE_STOPPING 210     /* looking for EOI in jpfg_finisi_dfdomprfss */










/* Dfdlbrbtions for domprfssion modulfs */
/* Dfdlbrbtions for domprfssion modulfs */
/* Dfdlbrbtions for domprfssion modulfs */
/* Dfdlbrbtions for domprfssion modulfs */
/* Dfdlbrbtions for domprfssion modulfs */





/* Mbstfr dontrol modulf */
/* Mbstfr dontrol modulf */
/* Mbstfr dontrol modulf */
/* Mbstfr dontrol modulf */
/* Mbstfr dontrol modulf */
strudt jpfg_domp_mbstfr {
strudt jpfg_domp_mbstfr {
strudt jpfg_domp_mbstfr {
strudt jpfg_domp_mbstfr {
strudt jpfg_domp_mbstfr {
  JMETHOD(void, prfpbrf_for_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, prfpbrf_for_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, prfpbrf_for_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, prfpbrf_for_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, prfpbrf_for_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, pbss_stbrtup, (j_domprfss_ptr dinfo));
  JMETHOD(void, pbss_stbrtup, (j_domprfss_ptr dinfo));
  JMETHOD(void, pbss_stbrtup, (j_domprfss_ptr dinfo));
  JMETHOD(void, pbss_stbrtup, (j_domprfss_ptr dinfo));
  JMETHOD(void, pbss_stbrtup, (j_domprfss_ptr dinfo));
  JMETHOD(void, finisi_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, finisi_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, finisi_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, finisi_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, finisi_pbss, (j_domprfss_ptr dinfo));





  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  boolfbn dbll_pbss_stbrtup;    /* Truf if pbss_stbrtup must bf dbllfd */
  boolfbn dbll_pbss_stbrtup;    /* Truf if pbss_stbrtup must bf dbllfd */
  boolfbn dbll_pbss_stbrtup;    /* Truf if pbss_stbrtup must bf dbllfd */
  boolfbn dbll_pbss_stbrtup;    /* Truf if pbss_stbrtup must bf dbllfd */
  boolfbn dbll_pbss_stbrtup;    /* Truf if pbss_stbrtup must bf dbllfd */
  boolfbn is_lbst_pbss;         /* Truf during lbst pbss */
  boolfbn is_lbst_pbss;         /* Truf during lbst pbss */
  boolfbn is_lbst_pbss;         /* Truf during lbst pbss */
  boolfbn is_lbst_pbss;         /* Truf during lbst pbss */
  boolfbn is_lbst_pbss;         /* Truf during lbst pbss */
};
};
};
};
};





/* Mbin bufffr dontrol (downsbmplfd-dbtb bufffr) */
/* Mbin bufffr dontrol (downsbmplfd-dbtb bufffr) */
/* Mbin bufffr dontrol (downsbmplfd-dbtb bufffr) */
/* Mbin bufffr dontrol (downsbmplfd-dbtb bufffr) */
/* Mbin bufffr dontrol (downsbmplfd-dbtb bufffr) */
strudt jpfg_d_mbin_dontrollfr {
strudt jpfg_d_mbin_dontrollfr {
strudt jpfg_d_mbin_dontrollfr {
strudt jpfg_d_mbin_dontrollfr {
strudt jpfg_d_mbin_dontrollfr {
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, prodfss_dbtb, (j_domprfss_ptr dinfo,
  JMETHOD(void, prodfss_dbtb, (j_domprfss_ptr dinfo,
  JMETHOD(void, prodfss_dbtb, (j_domprfss_ptr dinfo,
  JMETHOD(void, prodfss_dbtb, (j_domprfss_ptr dinfo,
  JMETHOD(void, prodfss_dbtb, (j_domprfss_ptr dinfo,
                               JSAMPARRAY input_buf, JDIMENSION *in_row_dtr,
                               JSAMPARRAY input_buf, JDIMENSION *in_row_dtr,
                               JSAMPARRAY input_buf, JDIMENSION *in_row_dtr,
                               JSAMPARRAY input_buf, JDIMENSION *in_row_dtr,
                               JSAMPARRAY input_buf, JDIMENSION *in_row_dtr,
                               JDIMENSION in_rows_bvbil));
                               JDIMENSION in_rows_bvbil));
                               JDIMENSION in_rows_bvbil));
                               JDIMENSION in_rows_bvbil));
                               JDIMENSION in_rows_bvbil));
};
};
};
};
};





/* Comprfssion prfprodfssing (downsbmpling input bufffr dontrol) */
/* Comprfssion prfprodfssing (downsbmpling input bufffr dontrol) */
/* Comprfssion prfprodfssing (downsbmpling input bufffr dontrol) */
/* Comprfssion prfprodfssing (downsbmpling input bufffr dontrol) */
/* Comprfssion prfprodfssing (downsbmpling input bufffr dontrol) */
strudt jpfg_d_prfp_dontrollfr {
strudt jpfg_d_prfp_dontrollfr {
strudt jpfg_d_prfp_dontrollfr {
strudt jpfg_d_prfp_dontrollfr {
strudt jpfg_d_prfp_dontrollfr {
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, prf_prodfss_dbtb, (j_domprfss_ptr dinfo,
  JMETHOD(void, prf_prodfss_dbtb, (j_domprfss_ptr dinfo,
  JMETHOD(void, prf_prodfss_dbtb, (j_domprfss_ptr dinfo,
  JMETHOD(void, prf_prodfss_dbtb, (j_domprfss_ptr dinfo,
  JMETHOD(void, prf_prodfss_dbtb, (j_domprfss_ptr dinfo,
                                   JSAMPARRAY input_buf,
                                   JSAMPARRAY input_buf,
                                   JSAMPARRAY input_buf,
                                   JSAMPARRAY input_buf,
                                   JSAMPARRAY input_buf,
                                   JDIMENSION *in_row_dtr,
                                   JDIMENSION *in_row_dtr,
                                   JDIMENSION *in_row_dtr,
                                   JDIMENSION *in_row_dtr,
                                   JDIMENSION *in_row_dtr,
                                   JDIMENSION in_rows_bvbil,
                                   JDIMENSION in_rows_bvbil,
                                   JDIMENSION in_rows_bvbil,
                                   JDIMENSION in_rows_bvbil,
                                   JDIMENSION in_rows_bvbil,
                                   JSAMPIMAGE output_buf,
                                   JSAMPIMAGE output_buf,
                                   JSAMPIMAGE output_buf,
                                   JSAMPIMAGE output_buf,
                                   JSAMPIMAGE output_buf,
                                   JDIMENSION *out_row_group_dtr,
                                   JDIMENSION *out_row_group_dtr,
                                   JDIMENSION *out_row_group_dtr,
                                   JDIMENSION *out_row_group_dtr,
                                   JDIMENSION *out_row_group_dtr,
                                   JDIMENSION out_row_groups_bvbil));
                                   JDIMENSION out_row_groups_bvbil));
                                   JDIMENSION out_row_groups_bvbil));
                                   JDIMENSION out_row_groups_bvbil));
                                   JDIMENSION out_row_groups_bvbil));
};
};
};
};
};





/* Cofffidifnt bufffr dontrol */
/* Cofffidifnt bufffr dontrol */
/* Cofffidifnt bufffr dontrol */
/* Cofffidifnt bufffr dontrol */
/* Cofffidifnt bufffr dontrol */
strudt jpfg_d_doff_dontrollfr {
strudt jpfg_d_doff_dontrollfr {
strudt jpfg_d_doff_dontrollfr {
strudt jpfg_d_doff_dontrollfr {
strudt jpfg_d_doff_dontrollfr {
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(boolfbn, domprfss_dbtb, (j_domprfss_ptr dinfo,
  JMETHOD(boolfbn, domprfss_dbtb, (j_domprfss_ptr dinfo,
  JMETHOD(boolfbn, domprfss_dbtb, (j_domprfss_ptr dinfo,
  JMETHOD(boolfbn, domprfss_dbtb, (j_domprfss_ptr dinfo,
  JMETHOD(boolfbn, domprfss_dbtb, (j_domprfss_ptr dinfo,
                                   JSAMPIMAGE input_buf));
                                   JSAMPIMAGE input_buf));
                                   JSAMPIMAGE input_buf));
                                   JSAMPIMAGE input_buf));
                                   JSAMPIMAGE input_buf));
};
};
};
};
};





/* Colorspbdf donvfrsion */
/* Colorspbdf donvfrsion */
/* Colorspbdf donvfrsion */
/* Colorspbdf donvfrsion */
/* Colorspbdf donvfrsion */
strudt jpfg_dolor_donvfrtfr {
strudt jpfg_dolor_donvfrtfr {
strudt jpfg_dolor_donvfrtfr {
strudt jpfg_dolor_donvfrtfr {
strudt jpfg_dolor_donvfrtfr {
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, dolor_donvfrt, (j_domprfss_ptr dinfo,
  JMETHOD(void, dolor_donvfrt, (j_domprfss_ptr dinfo,
  JMETHOD(void, dolor_donvfrt, (j_domprfss_ptr dinfo,
  JMETHOD(void, dolor_donvfrt, (j_domprfss_ptr dinfo,
  JMETHOD(void, dolor_donvfrt, (j_domprfss_ptr dinfo,
                                JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                                JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                                JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                                JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                                JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                                JDIMENSION output_row, int num_rows));
                                JDIMENSION output_row, int num_rows));
                                JDIMENSION output_row, int num_rows));
                                JDIMENSION output_row, int num_rows));
                                JDIMENSION output_row, int num_rows));
};
};
};
};
};





/* Downsbmpling */
/* Downsbmpling */
/* Downsbmpling */
/* Downsbmpling */
/* Downsbmpling */
strudt jpfg_downsbmplfr {
strudt jpfg_downsbmplfr {
strudt jpfg_downsbmplfr {
strudt jpfg_downsbmplfr {
strudt jpfg_downsbmplfr {
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, downsbmplf, (j_domprfss_ptr dinfo,
  JMETHOD(void, downsbmplf, (j_domprfss_ptr dinfo,
  JMETHOD(void, downsbmplf, (j_domprfss_ptr dinfo,
  JMETHOD(void, downsbmplf, (j_domprfss_ptr dinfo,
  JMETHOD(void, downsbmplf, (j_domprfss_ptr dinfo,
                             JSAMPIMAGE input_buf, JDIMENSION in_row_indfx,
                             JSAMPIMAGE input_buf, JDIMENSION in_row_indfx,
                             JSAMPIMAGE input_buf, JDIMENSION in_row_indfx,
                             JSAMPIMAGE input_buf, JDIMENSION in_row_indfx,
                             JSAMPIMAGE input_buf, JDIMENSION in_row_indfx,
                             JSAMPIMAGE output_buf,
                             JSAMPIMAGE output_buf,
                             JSAMPIMAGE output_buf,
                             JSAMPIMAGE output_buf,
                             JSAMPIMAGE output_buf,
                             JDIMENSION out_row_group_indfx));
                             JDIMENSION out_row_group_indfx));
                             JDIMENSION out_row_group_indfx));
                             JDIMENSION out_row_group_indfx));
                             JDIMENSION out_row_group_indfx));





  boolfbn nffd_dontfxt_rows;    /* TRUE if nffd rows bbovf & bflow */
  boolfbn nffd_dontfxt_rows;    /* TRUE if nffd rows bbovf & bflow */
  boolfbn nffd_dontfxt_rows;    /* TRUE if nffd rows bbovf & bflow */
  boolfbn nffd_dontfxt_rows;    /* TRUE if nffd rows bbovf & bflow */
  boolfbn nffd_dontfxt_rows;    /* TRUE if nffd rows bbovf & bflow */
};
};
};
};
};





/* Forwbrd DCT (blso dontrols dofffidifnt qubntizbtion) */
/* Forwbrd DCT (blso dontrols dofffidifnt qubntizbtion) */
/* Forwbrd DCT (blso dontrols dofffidifnt qubntizbtion) */
/* Forwbrd DCT (blso dontrols dofffidifnt qubntizbtion) */
/* Forwbrd DCT (blso dontrols dofffidifnt qubntizbtion) */
strudt jpfg_forwbrd_ddt {
strudt jpfg_forwbrd_ddt {
strudt jpfg_forwbrd_ddt {
strudt jpfg_forwbrd_ddt {
strudt jpfg_forwbrd_ddt {
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo));
  /* pfribps tiis siould bf bn brrby??? */
  /* pfribps tiis siould bf bn brrby??? */
  /* pfribps tiis siould bf bn brrby??? */
  /* pfribps tiis siould bf bn brrby??? */
  /* pfribps tiis siould bf bn brrby??? */
  JMETHOD(void, forwbrd_DCT, (j_domprfss_ptr dinfo,
  JMETHOD(void, forwbrd_DCT, (j_domprfss_ptr dinfo,
  JMETHOD(void, forwbrd_DCT, (j_domprfss_ptr dinfo,
  JMETHOD(void, forwbrd_DCT, (j_domprfss_ptr dinfo,
  JMETHOD(void, forwbrd_DCT, (j_domprfss_ptr dinfo,
                              jpfg_domponfnt_info * dompptr,
                              jpfg_domponfnt_info * dompptr,
                              jpfg_domponfnt_info * dompptr,
                              jpfg_domponfnt_info * dompptr,
                              jpfg_domponfnt_info * dompptr,
                              JSAMPARRAY sbmplf_dbtb, JBLOCKROW doff_blodks,
                              JSAMPARRAY sbmplf_dbtb, JBLOCKROW doff_blodks,
                              JSAMPARRAY sbmplf_dbtb, JBLOCKROW doff_blodks,
                              JSAMPARRAY sbmplf_dbtb, JBLOCKROW doff_blodks,
                              JSAMPARRAY sbmplf_dbtb, JBLOCKROW doff_blodks,
                              JDIMENSION stbrt_row, JDIMENSION stbrt_dol,
                              JDIMENSION stbrt_row, JDIMENSION stbrt_dol,
                              JDIMENSION stbrt_row, JDIMENSION stbrt_dol,
                              JDIMENSION stbrt_row, JDIMENSION stbrt_dol,
                              JDIMENSION stbrt_row, JDIMENSION stbrt_dol,
                              JDIMENSION num_blodks));
                              JDIMENSION num_blodks));
                              JDIMENSION num_blodks));
                              JDIMENSION num_blodks));
                              JDIMENSION num_blodks));
};
};
};
};
};





/* Entropy fndoding */
/* Entropy fndoding */
/* Entropy fndoding */
/* Entropy fndoding */
/* Entropy fndoding */
strudt jpfg_fntropy_fndodfr {
strudt jpfg_fntropy_fndodfr {
strudt jpfg_fntropy_fndodfr {
strudt jpfg_fntropy_fndodfr {
strudt jpfg_fntropy_fndodfr {
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, boolfbn gbtifr_stbtistids));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, boolfbn gbtifr_stbtistids));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, boolfbn gbtifr_stbtistids));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, boolfbn gbtifr_stbtistids));
  JMETHOD(void, stbrt_pbss, (j_domprfss_ptr dinfo, boolfbn gbtifr_stbtistids));
  JMETHOD(boolfbn, fndodf_mdu, (j_domprfss_ptr dinfo, JBLOCKROW *MCU_dbtb));
  JMETHOD(boolfbn, fndodf_mdu, (j_domprfss_ptr dinfo, JBLOCKROW *MCU_dbtb));
  JMETHOD(boolfbn, fndodf_mdu, (j_domprfss_ptr dinfo, JBLOCKROW *MCU_dbtb));
  JMETHOD(boolfbn, fndodf_mdu, (j_domprfss_ptr dinfo, JBLOCKROW *MCU_dbtb));
  JMETHOD(boolfbn, fndodf_mdu, (j_domprfss_ptr dinfo, JBLOCKROW *MCU_dbtb));
  JMETHOD(void, finisi_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, finisi_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, finisi_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, finisi_pbss, (j_domprfss_ptr dinfo));
  JMETHOD(void, finisi_pbss, (j_domprfss_ptr dinfo));
};
};
};
};
};





/* Mbrkfr writing */
/* Mbrkfr writing */
/* Mbrkfr writing */
/* Mbrkfr writing */
/* Mbrkfr writing */
strudt jpfg_mbrkfr_writfr {
strudt jpfg_mbrkfr_writfr {
strudt jpfg_mbrkfr_writfr {
strudt jpfg_mbrkfr_writfr {
strudt jpfg_mbrkfr_writfr {
  JMETHOD(void, writf_filf_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_filf_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_filf_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_filf_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_filf_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_frbmf_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_frbmf_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_frbmf_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_frbmf_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_frbmf_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_sdbn_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_sdbn_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_sdbn_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_sdbn_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_sdbn_ifbdfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_filf_trbilfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_filf_trbilfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_filf_trbilfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_filf_trbilfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_filf_trbilfr, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_tbblfs_only, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_tbblfs_only, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_tbblfs_only, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_tbblfs_only, (j_domprfss_ptr dinfo));
  JMETHOD(void, writf_tbblfs_only, (j_domprfss_ptr dinfo));
  /* Tifsf routinfs brf fxportfd to bllow insfrtion of fxtrb mbrkfrs */
  /* Tifsf routinfs brf fxportfd to bllow insfrtion of fxtrb mbrkfrs */
  /* Tifsf routinfs brf fxportfd to bllow insfrtion of fxtrb mbrkfrs */
  /* Tifsf routinfs brf fxportfd to bllow insfrtion of fxtrb mbrkfrs */
  /* Tifsf routinfs brf fxportfd to bllow insfrtion of fxtrb mbrkfrs */
  /* Probbbly only COM bnd APPn mbrkfrs siould bf writtfn tiis wby */
  /* Probbbly only COM bnd APPn mbrkfrs siould bf writtfn tiis wby */
  /* Probbbly only COM bnd APPn mbrkfrs siould bf writtfn tiis wby */
  /* Probbbly only COM bnd APPn mbrkfrs siould bf writtfn tiis wby */
  /* Probbbly only COM bnd APPn mbrkfrs siould bf writtfn tiis wby */
  JMETHOD(void, writf_mbrkfr_ifbdfr, (j_domprfss_ptr dinfo, int mbrkfr,
  JMETHOD(void, writf_mbrkfr_ifbdfr, (j_domprfss_ptr dinfo, int mbrkfr,
  JMETHOD(void, writf_mbrkfr_ifbdfr, (j_domprfss_ptr dinfo, int mbrkfr,
  JMETHOD(void, writf_mbrkfr_ifbdfr, (j_domprfss_ptr dinfo, int mbrkfr,
  JMETHOD(void, writf_mbrkfr_ifbdfr, (j_domprfss_ptr dinfo, int mbrkfr,
                                      unsignfd int dbtblfn));
                                      unsignfd int dbtblfn));
                                      unsignfd int dbtblfn));
                                      unsignfd int dbtblfn));
                                      unsignfd int dbtblfn));
  JMETHOD(void, writf_mbrkfr_bytf, (j_domprfss_ptr dinfo, int vbl));
  JMETHOD(void, writf_mbrkfr_bytf, (j_domprfss_ptr dinfo, int vbl));
  JMETHOD(void, writf_mbrkfr_bytf, (j_domprfss_ptr dinfo, int vbl));
  JMETHOD(void, writf_mbrkfr_bytf, (j_domprfss_ptr dinfo, int vbl));
  JMETHOD(void, writf_mbrkfr_bytf, (j_domprfss_ptr dinfo, int vbl));
};
};
};
};
};










/* Dfdlbrbtions for dfdomprfssion modulfs */
/* Dfdlbrbtions for dfdomprfssion modulfs */
/* Dfdlbrbtions for dfdomprfssion modulfs */
/* Dfdlbrbtions for dfdomprfssion modulfs */
/* Dfdlbrbtions for dfdomprfssion modulfs */





/* Mbstfr dontrol modulf */
/* Mbstfr dontrol modulf */
/* Mbstfr dontrol modulf */
/* Mbstfr dontrol modulf */
/* Mbstfr dontrol modulf */
strudt jpfg_dfdomp_mbstfr {
strudt jpfg_dfdomp_mbstfr {
strudt jpfg_dfdomp_mbstfr {
strudt jpfg_dfdomp_mbstfr {
strudt jpfg_dfdomp_mbstfr {
  JMETHOD(void, prfpbrf_for_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, prfpbrf_for_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, prfpbrf_for_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, prfpbrf_for_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, prfpbrf_for_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_output_pbss, (j_dfdomprfss_ptr dinfo));





  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  boolfbn is_dummy_pbss;        /* Truf during 1st pbss for 2-pbss qubnt */
  boolfbn is_dummy_pbss;        /* Truf during 1st pbss for 2-pbss qubnt */
  boolfbn is_dummy_pbss;        /* Truf during 1st pbss for 2-pbss qubnt */
  boolfbn is_dummy_pbss;        /* Truf during 1st pbss for 2-pbss qubnt */
  boolfbn is_dummy_pbss;        /* Truf during 1st pbss for 2-pbss qubnt */
};
};
};
};
};





/* Input dontrol modulf */
/* Input dontrol modulf */
/* Input dontrol modulf */
/* Input dontrol modulf */
/* Input dontrol modulf */
strudt jpfg_input_dontrollfr {
strudt jpfg_input_dontrollfr {
strudt jpfg_input_dontrollfr {
strudt jpfg_input_dontrollfr {
strudt jpfg_input_dontrollfr {
  JMETHOD(int, donsumf_input, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, donsumf_input, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, donsumf_input, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, donsumf_input, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, donsumf_input, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, rfsft_input_dontrollfr, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, rfsft_input_dontrollfr, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, rfsft_input_dontrollfr, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, rfsft_input_dontrollfr, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, rfsft_input_dontrollfr, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_input_pbss, (j_dfdomprfss_ptr dinfo));





  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  /* Stbtf vbribblfs mbdf visiblf to otifr modulfs */
  boolfbn ibs_multiplf_sdbns;   /* Truf if filf ibs multiplf sdbns */
  boolfbn ibs_multiplf_sdbns;   /* Truf if filf ibs multiplf sdbns */
  boolfbn ibs_multiplf_sdbns;   /* Truf if filf ibs multiplf sdbns */
  boolfbn ibs_multiplf_sdbns;   /* Truf if filf ibs multiplf sdbns */
  boolfbn ibs_multiplf_sdbns;   /* Truf if filf ibs multiplf sdbns */
  boolfbn foi_rfbdifd;          /* Truf wifn EOI ibs bffn donsumfd */
  boolfbn foi_rfbdifd;          /* Truf wifn EOI ibs bffn donsumfd */
  boolfbn foi_rfbdifd;          /* Truf wifn EOI ibs bffn donsumfd */
  boolfbn foi_rfbdifd;          /* Truf wifn EOI ibs bffn donsumfd */
  boolfbn foi_rfbdifd;          /* Truf wifn EOI ibs bffn donsumfd */
};
};
};
};
};





/* Mbin bufffr dontrol (downsbmplfd-dbtb bufffr) */
/* Mbin bufffr dontrol (downsbmplfd-dbtb bufffr) */
/* Mbin bufffr dontrol (downsbmplfd-dbtb bufffr) */
/* Mbin bufffr dontrol (downsbmplfd-dbtb bufffr) */
/* Mbin bufffr dontrol (downsbmplfd-dbtb bufffr) */
strudt jpfg_d_mbin_dontrollfr {
strudt jpfg_d_mbin_dontrollfr {
strudt jpfg_d_mbin_dontrollfr {
strudt jpfg_d_mbin_dontrollfr {
strudt jpfg_d_mbin_dontrollfr {
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, prodfss_dbtb, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, prodfss_dbtb, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, prodfss_dbtb, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, prodfss_dbtb, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, prodfss_dbtb, (j_dfdomprfss_ptr dinfo,
                               JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
                               JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
                               JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
                               JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
                               JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
                               JDIMENSION out_rows_bvbil));
                               JDIMENSION out_rows_bvbil));
                               JDIMENSION out_rows_bvbil));
                               JDIMENSION out_rows_bvbil));
                               JDIMENSION out_rows_bvbil));
};
};
};
};
};





/* Cofffidifnt bufffr dontrol */
/* Cofffidifnt bufffr dontrol */
/* Cofffidifnt bufffr dontrol */
/* Cofffidifnt bufffr dontrol */
/* Cofffidifnt bufffr dontrol */
strudt jpfg_d_doff_dontrollfr {
strudt jpfg_d_doff_dontrollfr {
strudt jpfg_d_doff_dontrollfr {
strudt jpfg_d_doff_dontrollfr {
strudt jpfg_d_doff_dontrollfr {
  JMETHOD(void, stbrt_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_input_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, donsumf_dbtb, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, donsumf_dbtb, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, donsumf_dbtb, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, donsumf_dbtb, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, donsumf_dbtb, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_output_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, dfdomprfss_dbtb, (j_dfdomprfss_ptr dinfo,
  JMETHOD(int, dfdomprfss_dbtb, (j_dfdomprfss_ptr dinfo,
  JMETHOD(int, dfdomprfss_dbtb, (j_dfdomprfss_ptr dinfo,
  JMETHOD(int, dfdomprfss_dbtb, (j_dfdomprfss_ptr dinfo,
  JMETHOD(int, dfdomprfss_dbtb, (j_dfdomprfss_ptr dinfo,
                                 JSAMPIMAGE output_buf));
                                 JSAMPIMAGE output_buf));
                                 JSAMPIMAGE output_buf));
                                 JSAMPIMAGE output_buf));
                                 JSAMPIMAGE output_buf));
  /* Pointfr to brrby of dofffidifnt virtubl brrbys, or NULL if nonf */
  /* Pointfr to brrby of dofffidifnt virtubl brrbys, or NULL if nonf */
  /* Pointfr to brrby of dofffidifnt virtubl brrbys, or NULL if nonf */
  /* Pointfr to brrby of dofffidifnt virtubl brrbys, or NULL if nonf */
  /* Pointfr to brrby of dofffidifnt virtubl brrbys, or NULL if nonf */
  jvirt_bbrrby_ptr *doff_brrbys;
  jvirt_bbrrby_ptr *doff_brrbys;
  jvirt_bbrrby_ptr *doff_brrbys;
  jvirt_bbrrby_ptr *doff_brrbys;
  jvirt_bbrrby_ptr *doff_brrbys;
};
};
};
};
};





/* Dfdomprfssion postprodfssing (dolor qubntizbtion bufffr dontrol) */
/* Dfdomprfssion postprodfssing (dolor qubntizbtion bufffr dontrol) */
/* Dfdomprfssion postprodfssing (dolor qubntizbtion bufffr dontrol) */
/* Dfdomprfssion postprodfssing (dolor qubntizbtion bufffr dontrol) */
/* Dfdomprfssion postprodfssing (dolor qubntizbtion bufffr dontrol) */
strudt jpfg_d_post_dontrollfr {
strudt jpfg_d_post_dontrollfr {
strudt jpfg_d_post_dontrollfr {
strudt jpfg_d_post_dontrollfr {
strudt jpfg_d_post_dontrollfr {
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, J_BUF_MODE pbss_modf));
  JMETHOD(void, post_prodfss_dbtb, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, post_prodfss_dbtb, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, post_prodfss_dbtb, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, post_prodfss_dbtb, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, post_prodfss_dbtb, (j_dfdomprfss_ptr dinfo,
                                    JSAMPIMAGE input_buf,
                                    JSAMPIMAGE input_buf,
                                    JSAMPIMAGE input_buf,
                                    JSAMPIMAGE input_buf,
                                    JSAMPIMAGE input_buf,
                                    JDIMENSION *in_row_group_dtr,
                                    JDIMENSION *in_row_group_dtr,
                                    JDIMENSION *in_row_group_dtr,
                                    JDIMENSION *in_row_group_dtr,
                                    JDIMENSION *in_row_group_dtr,
                                    JDIMENSION in_row_groups_bvbil,
                                    JDIMENSION in_row_groups_bvbil,
                                    JDIMENSION in_row_groups_bvbil,
                                    JDIMENSION in_row_groups_bvbil,
                                    JDIMENSION in_row_groups_bvbil,
                                    JSAMPARRAY output_buf,
                                    JSAMPARRAY output_buf,
                                    JSAMPARRAY output_buf,
                                    JSAMPARRAY output_buf,
                                    JSAMPARRAY output_buf,
                                    JDIMENSION *out_row_dtr,
                                    JDIMENSION *out_row_dtr,
                                    JDIMENSION *out_row_dtr,
                                    JDIMENSION *out_row_dtr,
                                    JDIMENSION *out_row_dtr,
                                    JDIMENSION out_rows_bvbil));
                                    JDIMENSION out_rows_bvbil));
                                    JDIMENSION out_rows_bvbil));
                                    JDIMENSION out_rows_bvbil));
                                    JDIMENSION out_rows_bvbil));
};
};
};
};
};





/* Mbrkfr rfbding & pbrsing */
/* Mbrkfr rfbding & pbrsing */
/* Mbrkfr rfbding & pbrsing */
/* Mbrkfr rfbding & pbrsing */
/* Mbrkfr rfbding & pbrsing */
strudt jpfg_mbrkfr_rfbdfr {
strudt jpfg_mbrkfr_rfbdfr {
strudt jpfg_mbrkfr_rfbdfr {
strudt jpfg_mbrkfr_rfbdfr {
strudt jpfg_mbrkfr_rfbdfr {
  JMETHOD(void, rfsft_mbrkfr_rfbdfr, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, rfsft_mbrkfr_rfbdfr, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, rfsft_mbrkfr_rfbdfr, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, rfsft_mbrkfr_rfbdfr, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, rfsft_mbrkfr_rfbdfr, (j_dfdomprfss_ptr dinfo));
  /* Rfbd mbrkfrs until SOS or EOI.
  /* Rfbd mbrkfrs until SOS or EOI.
  /* Rfbd mbrkfrs until SOS or EOI.
  /* Rfbd mbrkfrs until SOS or EOI.
  /* Rfbd mbrkfrs until SOS or EOI.
   * Rfturns sbmf dodfs bs brf dffinfd for jpfg_donsumf_input:
   * Rfturns sbmf dodfs bs brf dffinfd for jpfg_donsumf_input:
   * Rfturns sbmf dodfs bs brf dffinfd for jpfg_donsumf_input:
   * Rfturns sbmf dodfs bs brf dffinfd for jpfg_donsumf_input:
   * Rfturns sbmf dodfs bs brf dffinfd for jpfg_donsumf_input:
   * JPEG_SUSPENDED, JPEG_REACHED_SOS, or JPEG_REACHED_EOI.
   * JPEG_SUSPENDED, JPEG_REACHED_SOS, or JPEG_REACHED_EOI.
   * JPEG_SUSPENDED, JPEG_REACHED_SOS, or JPEG_REACHED_EOI.
   * JPEG_SUSPENDED, JPEG_REACHED_SOS, or JPEG_REACHED_EOI.
   * JPEG_SUSPENDED, JPEG_REACHED_SOS, or JPEG_REACHED_EOI.
   */
   */
   */
   */
   */
  JMETHOD(int, rfbd_mbrkfrs, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, rfbd_mbrkfrs, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, rfbd_mbrkfrs, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, rfbd_mbrkfrs, (j_dfdomprfss_ptr dinfo));
  JMETHOD(int, rfbd_mbrkfrs, (j_dfdomprfss_ptr dinfo));
  /* Rfbd b rfstbrt mbrkfr --- fxportfd for usf by fntropy dfdodfr only */
  /* Rfbd b rfstbrt mbrkfr --- fxportfd for usf by fntropy dfdodfr only */
  /* Rfbd b rfstbrt mbrkfr --- fxportfd for usf by fntropy dfdodfr only */
  /* Rfbd b rfstbrt mbrkfr --- fxportfd for usf by fntropy dfdodfr only */
  /* Rfbd b rfstbrt mbrkfr --- fxportfd for usf by fntropy dfdodfr only */
  jpfg_mbrkfr_pbrsfr_mftiod rfbd_rfstbrt_mbrkfr;
  jpfg_mbrkfr_pbrsfr_mftiod rfbd_rfstbrt_mbrkfr;
  jpfg_mbrkfr_pbrsfr_mftiod rfbd_rfstbrt_mbrkfr;
  jpfg_mbrkfr_pbrsfr_mftiod rfbd_rfstbrt_mbrkfr;
  jpfg_mbrkfr_pbrsfr_mftiod rfbd_rfstbrt_mbrkfr;





  /* Stbtf of mbrkfr rfbdfr --- nominblly intfrnbl, but bpplidbtions
  /* Stbtf of mbrkfr rfbdfr --- nominblly intfrnbl, but bpplidbtions
  /* Stbtf of mbrkfr rfbdfr --- nominblly intfrnbl, but bpplidbtions
  /* Stbtf of mbrkfr rfbdfr --- nominblly intfrnbl, but bpplidbtions
  /* Stbtf of mbrkfr rfbdfr --- nominblly intfrnbl, but bpplidbtions
   * supplying COM or APPn ibndlfrs migit likf to know tif stbtf.
   * supplying COM or APPn ibndlfrs migit likf to know tif stbtf.
   * supplying COM or APPn ibndlfrs migit likf to know tif stbtf.
   * supplying COM or APPn ibndlfrs migit likf to know tif stbtf.
   * supplying COM or APPn ibndlfrs migit likf to know tif stbtf.
   */
   */
   */
   */
   */
  boolfbn sbw_SOI;              /* found SOI? */
  boolfbn sbw_SOI;              /* found SOI? */
  boolfbn sbw_SOI;              /* found SOI? */
  boolfbn sbw_SOI;              /* found SOI? */
  boolfbn sbw_SOI;              /* found SOI? */
  boolfbn sbw_SOF;              /* found SOF? */
  boolfbn sbw_SOF;              /* found SOF? */
  boolfbn sbw_SOF;              /* found SOF? */
  boolfbn sbw_SOF;              /* found SOF? */
  boolfbn sbw_SOF;              /* found SOF? */
  int nfxt_rfstbrt_num;         /* nfxt rfstbrt numbfr fxpfdtfd (0-7) */
  int nfxt_rfstbrt_num;         /* nfxt rfstbrt numbfr fxpfdtfd (0-7) */
  int nfxt_rfstbrt_num;         /* nfxt rfstbrt numbfr fxpfdtfd (0-7) */
  int nfxt_rfstbrt_num;         /* nfxt rfstbrt numbfr fxpfdtfd (0-7) */
  int nfxt_rfstbrt_num;         /* nfxt rfstbrt numbfr fxpfdtfd (0-7) */
  unsignfd int disdbrdfd_bytfs; /* # of bytfs skippfd looking for b mbrkfr */
  unsignfd int disdbrdfd_bytfs; /* # of bytfs skippfd looking for b mbrkfr */
  unsignfd int disdbrdfd_bytfs; /* # of bytfs skippfd looking for b mbrkfr */
  unsignfd int disdbrdfd_bytfs; /* # of bytfs skippfd looking for b mbrkfr */
  unsignfd int disdbrdfd_bytfs; /* # of bytfs skippfd looking for b mbrkfr */
};
};
};
};
};





/* Entropy dfdoding */
/* Entropy dfdoding */
/* Entropy dfdoding */
/* Entropy dfdoding */
/* Entropy dfdoding */
strudt jpfg_fntropy_dfdodfr {
strudt jpfg_fntropy_dfdodfr {
strudt jpfg_fntropy_dfdodfr {
strudt jpfg_fntropy_dfdodfr {
strudt jpfg_fntropy_dfdodfr {
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(boolfbn, dfdodf_mdu, (j_dfdomprfss_ptr dinfo,
  JMETHOD(boolfbn, dfdodf_mdu, (j_dfdomprfss_ptr dinfo,
  JMETHOD(boolfbn, dfdodf_mdu, (j_dfdomprfss_ptr dinfo,
  JMETHOD(boolfbn, dfdodf_mdu, (j_dfdomprfss_ptr dinfo,
  JMETHOD(boolfbn, dfdodf_mdu, (j_dfdomprfss_ptr dinfo,
                                JBLOCKROW *MCU_dbtb));
                                JBLOCKROW *MCU_dbtb));
                                JBLOCKROW *MCU_dbtb));
                                JBLOCKROW *MCU_dbtb));
                                JBLOCKROW *MCU_dbtb));





  /* Tiis is ifrf to sibrf dodf bftwffn bbsflinf bnd progrfssivf dfdodfrs; */
  /* Tiis is ifrf to sibrf dodf bftwffn bbsflinf bnd progrfssivf dfdodfrs; */
  /* Tiis is ifrf to sibrf dodf bftwffn bbsflinf bnd progrfssivf dfdodfrs; */
  /* Tiis is ifrf to sibrf dodf bftwffn bbsflinf bnd progrfssivf dfdodfrs; */
  /* Tiis is ifrf to sibrf dodf bftwffn bbsflinf bnd progrfssivf dfdodfrs; */
  /* otifr modulfs probbbly siould not usf it */
  /* otifr modulfs probbbly siould not usf it */
  /* otifr modulfs probbbly siould not usf it */
  /* otifr modulfs probbbly siould not usf it */
  /* otifr modulfs probbbly siould not usf it */
  boolfbn insuffidifnt_dbtb;    /* sft TRUE bftfr fmitting wbrning */
  boolfbn insuffidifnt_dbtb;    /* sft TRUE bftfr fmitting wbrning */
  boolfbn insuffidifnt_dbtb;    /* sft TRUE bftfr fmitting wbrning */
  boolfbn insuffidifnt_dbtb;    /* sft TRUE bftfr fmitting wbrning */
  boolfbn insuffidifnt_dbtb;    /* sft TRUE bftfr fmitting wbrning */
};
};
};
};
};





/* Invfrsf DCT (blso pfrforms dfqubntizbtion) */
/* Invfrsf DCT (blso pfrforms dfqubntizbtion) */
/* Invfrsf DCT (blso pfrforms dfqubntizbtion) */
/* Invfrsf DCT (blso pfrforms dfqubntizbtion) */
/* Invfrsf DCT (blso pfrforms dfqubntizbtion) */
typfdff JMETHOD(void, invfrsf_DCT_mftiod_ptr,
typfdff JMETHOD(void, invfrsf_DCT_mftiod_ptr,
typfdff JMETHOD(void, invfrsf_DCT_mftiod_ptr,
typfdff JMETHOD(void, invfrsf_DCT_mftiod_ptr,
typfdff JMETHOD(void, invfrsf_DCT_mftiod_ptr,
                (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                 JCOEFPTR doff_blodk,
                 JCOEFPTR doff_blodk,
                 JCOEFPTR doff_blodk,
                 JCOEFPTR doff_blodk,
                 JCOEFPTR doff_blodk,
                 JSAMPARRAY output_buf, JDIMENSION output_dol));
                 JSAMPARRAY output_buf, JDIMENSION output_dol));
                 JSAMPARRAY output_buf, JDIMENSION output_dol));
                 JSAMPARRAY output_buf, JDIMENSION output_dol));
                 JSAMPARRAY output_buf, JDIMENSION output_dol));





strudt jpfg_invfrsf_ddt {
strudt jpfg_invfrsf_ddt {
strudt jpfg_invfrsf_ddt {
strudt jpfg_invfrsf_ddt {
strudt jpfg_invfrsf_ddt {
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  /* It is usfful to bllow fbdi domponfnt to ibvf b sfpbrbtf IDCT mftiod. */
  /* It is usfful to bllow fbdi domponfnt to ibvf b sfpbrbtf IDCT mftiod. */
  /* It is usfful to bllow fbdi domponfnt to ibvf b sfpbrbtf IDCT mftiod. */
  /* It is usfful to bllow fbdi domponfnt to ibvf b sfpbrbtf IDCT mftiod. */
  /* It is usfful to bllow fbdi domponfnt to ibvf b sfpbrbtf IDCT mftiod. */
  invfrsf_DCT_mftiod_ptr invfrsf_DCT[MAX_COMPONENTS];
  invfrsf_DCT_mftiod_ptr invfrsf_DCT[MAX_COMPONENTS];
  invfrsf_DCT_mftiod_ptr invfrsf_DCT[MAX_COMPONENTS];
  invfrsf_DCT_mftiod_ptr invfrsf_DCT[MAX_COMPONENTS];
  invfrsf_DCT_mftiod_ptr invfrsf_DCT[MAX_COMPONENTS];
};
};
};
};
};





/* Upsbmpling (notf tibt upsbmplfr must blso dbll dolor donvfrtfr) */
/* Upsbmpling (notf tibt upsbmplfr must blso dbll dolor donvfrtfr) */
/* Upsbmpling (notf tibt upsbmplfr must blso dbll dolor donvfrtfr) */
/* Upsbmpling (notf tibt upsbmplfr must blso dbll dolor donvfrtfr) */
/* Upsbmpling (notf tibt upsbmplfr must blso dbll dolor donvfrtfr) */
strudt jpfg_upsbmplfr {
strudt jpfg_upsbmplfr {
strudt jpfg_upsbmplfr {
strudt jpfg_upsbmplfr {
strudt jpfg_upsbmplfr {
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, upsbmplf, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, upsbmplf, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, upsbmplf, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, upsbmplf, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, upsbmplf, (j_dfdomprfss_ptr dinfo,
                           JSAMPIMAGE input_buf,
                           JSAMPIMAGE input_buf,
                           JSAMPIMAGE input_buf,
                           JSAMPIMAGE input_buf,
                           JSAMPIMAGE input_buf,
                           JDIMENSION *in_row_group_dtr,
                           JDIMENSION *in_row_group_dtr,
                           JDIMENSION *in_row_group_dtr,
                           JDIMENSION *in_row_group_dtr,
                           JDIMENSION *in_row_group_dtr,
                           JDIMENSION in_row_groups_bvbil,
                           JDIMENSION in_row_groups_bvbil,
                           JDIMENSION in_row_groups_bvbil,
                           JDIMENSION in_row_groups_bvbil,
                           JDIMENSION in_row_groups_bvbil,
                           JSAMPARRAY output_buf,
                           JSAMPARRAY output_buf,
                           JSAMPARRAY output_buf,
                           JSAMPARRAY output_buf,
                           JSAMPARRAY output_buf,
                           JDIMENSION *out_row_dtr,
                           JDIMENSION *out_row_dtr,
                           JDIMENSION *out_row_dtr,
                           JDIMENSION *out_row_dtr,
                           JDIMENSION *out_row_dtr,
                           JDIMENSION out_rows_bvbil));
                           JDIMENSION out_rows_bvbil));
                           JDIMENSION out_rows_bvbil));
                           JDIMENSION out_rows_bvbil));
                           JDIMENSION out_rows_bvbil));





  boolfbn nffd_dontfxt_rows;    /* TRUE if nffd rows bbovf & bflow */
  boolfbn nffd_dontfxt_rows;    /* TRUE if nffd rows bbovf & bflow */
  boolfbn nffd_dontfxt_rows;    /* TRUE if nffd rows bbovf & bflow */
  boolfbn nffd_dontfxt_rows;    /* TRUE if nffd rows bbovf & bflow */
  boolfbn nffd_dontfxt_rows;    /* TRUE if nffd rows bbovf & bflow */
};
};
};
};
};





/* Colorspbdf donvfrsion */
/* Colorspbdf donvfrsion */
/* Colorspbdf donvfrsion */
/* Colorspbdf donvfrsion */
/* Colorspbdf donvfrsion */
strudt jpfg_dolor_dfdonvfrtfr {
strudt jpfg_dolor_dfdonvfrtfr {
strudt jpfg_dolor_dfdonvfrtfr {
strudt jpfg_dolor_dfdonvfrtfr {
strudt jpfg_dolor_dfdonvfrtfr {
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, dolor_donvfrt, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, dolor_donvfrt, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, dolor_donvfrt, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, dolor_donvfrt, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, dolor_donvfrt, (j_dfdomprfss_ptr dinfo,
                                JSAMPIMAGE input_buf, JDIMENSION input_row,
                                JSAMPIMAGE input_buf, JDIMENSION input_row,
                                JSAMPIMAGE input_buf, JDIMENSION input_row,
                                JSAMPIMAGE input_buf, JDIMENSION input_row,
                                JSAMPIMAGE input_buf, JDIMENSION input_row,
                                JSAMPARRAY output_buf, int num_rows));
                                JSAMPARRAY output_buf, int num_rows));
                                JSAMPARRAY output_buf, int num_rows));
                                JSAMPARRAY output_buf, int num_rows));
                                JSAMPARRAY output_buf, int num_rows));
};
};
};
};
};





/* Color qubntizbtion or dolor prfdision rfdudtion */
/* Color qubntizbtion or dolor prfdision rfdudtion */
/* Color qubntizbtion or dolor prfdision rfdudtion */
/* Color qubntizbtion or dolor prfdision rfdudtion */
/* Color qubntizbtion or dolor prfdision rfdudtion */
strudt jpfg_dolor_qubntizfr {
strudt jpfg_dolor_qubntizfr {
strudt jpfg_dolor_qubntizfr {
strudt jpfg_dolor_qubntizfr {
strudt jpfg_dolor_qubntizfr {
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, boolfbn is_prf_sdbn));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, boolfbn is_prf_sdbn));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, boolfbn is_prf_sdbn));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, boolfbn is_prf_sdbn));
  JMETHOD(void, stbrt_pbss, (j_dfdomprfss_ptr dinfo, boolfbn is_prf_sdbn));
  JMETHOD(void, dolor_qubntizf, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, dolor_qubntizf, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, dolor_qubntizf, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, dolor_qubntizf, (j_dfdomprfss_ptr dinfo,
  JMETHOD(void, dolor_qubntizf, (j_dfdomprfss_ptr dinfo,
                                 JSAMPARRAY input_buf, JSAMPARRAY output_buf,
                                 JSAMPARRAY input_buf, JSAMPARRAY output_buf,
                                 JSAMPARRAY input_buf, JSAMPARRAY output_buf,
                                 JSAMPARRAY input_buf, JSAMPARRAY output_buf,
                                 JSAMPARRAY input_buf, JSAMPARRAY output_buf,
                                 int num_rows));
                                 int num_rows));
                                 int num_rows));
                                 int num_rows));
                                 int num_rows));
  JMETHOD(void, finisi_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, finisi_pbss, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, nfw_dolor_mbp, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, nfw_dolor_mbp, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, nfw_dolor_mbp, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, nfw_dolor_mbp, (j_dfdomprfss_ptr dinfo));
  JMETHOD(void, nfw_dolor_mbp, (j_dfdomprfss_ptr dinfo));
};
};
};
};
};










/* Misdfllbnfous usfful mbdros */
/* Misdfllbnfous usfful mbdros */
/* Misdfllbnfous usfful mbdros */
/* Misdfllbnfous usfful mbdros */
/* Misdfllbnfous usfful mbdros */





#undff MAX
#undff MAX
#undff MAX
#undff MAX
#undff MAX
#dffinf MAX(b,b)        ((b) > (b) ? (b) : (b))
#dffinf MAX(b,b)        ((b) > (b) ? (b) : (b))
#dffinf MAX(b,b)        ((b) > (b) ? (b) : (b))
#dffinf MAX(b,b)        ((b) > (b) ? (b) : (b))
#dffinf MAX(b,b)        ((b) > (b) ? (b) : (b))
#undff MIN
#undff MIN
#undff MIN
#undff MIN
#undff MIN
#dffinf MIN(b,b)        ((b) < (b) ? (b) : (b))
#dffinf MIN(b,b)        ((b) < (b) ? (b) : (b))
#dffinf MIN(b,b)        ((b) < (b) ? (b) : (b))
#dffinf MIN(b,b)        ((b) < (b) ? (b) : (b))
#dffinf MIN(b,b)        ((b) < (b) ? (b) : (b))










/* Wf bssumf tibt rigit siift dorrfsponds to signfd division by 2 witi
/* Wf bssumf tibt rigit siift dorrfsponds to signfd division by 2 witi
/* Wf bssumf tibt rigit siift dorrfsponds to signfd division by 2 witi
/* Wf bssumf tibt rigit siift dorrfsponds to signfd division by 2 witi
/* Wf bssumf tibt rigit siift dorrfsponds to signfd division by 2 witi
 * rounding towbrds minus infinity.  Tiis is dorrfdt for typidbl "britimftid
 * rounding towbrds minus infinity.  Tiis is dorrfdt for typidbl "britimftid
 * rounding towbrds minus infinity.  Tiis is dorrfdt for typidbl "britimftid
 * rounding towbrds minus infinity.  Tiis is dorrfdt for typidbl "britimftid
 * rounding towbrds minus infinity.  Tiis is dorrfdt for typidbl "britimftid
 * siift" instrudtions tibt siift in dopifs of tif sign bit.  But somf
 * siift" instrudtions tibt siift in dopifs of tif sign bit.  But somf
 * siift" instrudtions tibt siift in dopifs of tif sign bit.  But somf
 * siift" instrudtions tibt siift in dopifs of tif sign bit.  But somf
 * siift" instrudtions tibt siift in dopifs of tif sign bit.  But somf
 * C dompilfrs implfmfnt >> witi bn unsignfd siift.  For tifsf mbdiinfs you
 * C dompilfrs implfmfnt >> witi bn unsignfd siift.  For tifsf mbdiinfs you
 * C dompilfrs implfmfnt >> witi bn unsignfd siift.  For tifsf mbdiinfs you
 * C dompilfrs implfmfnt >> witi bn unsignfd siift.  For tifsf mbdiinfs you
 * C dompilfrs implfmfnt >> witi bn unsignfd siift.  For tifsf mbdiinfs you
 * must dffinf RIGHT_SHIFT_IS_UNSIGNED.
 * must dffinf RIGHT_SHIFT_IS_UNSIGNED.
 * must dffinf RIGHT_SHIFT_IS_UNSIGNED.
 * must dffinf RIGHT_SHIFT_IS_UNSIGNED.
 * must dffinf RIGHT_SHIFT_IS_UNSIGNED.
 * RIGHT_SHIFT providfs b propfr signfd rigit siift of bn INT32 qubntity.
 * RIGHT_SHIFT providfs b propfr signfd rigit siift of bn INT32 qubntity.
 * RIGHT_SHIFT providfs b propfr signfd rigit siift of bn INT32 qubntity.
 * RIGHT_SHIFT providfs b propfr signfd rigit siift of bn INT32 qubntity.
 * RIGHT_SHIFT providfs b propfr signfd rigit siift of bn INT32 qubntity.
 * It is only bpplifd witi donstbnt siift dounts.  SHIFT_TEMPS must bf
 * It is only bpplifd witi donstbnt siift dounts.  SHIFT_TEMPS must bf
 * It is only bpplifd witi donstbnt siift dounts.  SHIFT_TEMPS must bf
 * It is only bpplifd witi donstbnt siift dounts.  SHIFT_TEMPS must bf
 * It is only bpplifd witi donstbnt siift dounts.  SHIFT_TEMPS must bf
 * indludfd in tif vbribblfs of bny routinf using RIGHT_SHIFT.
 * indludfd in tif vbribblfs of bny routinf using RIGHT_SHIFT.
 * indludfd in tif vbribblfs of bny routinf using RIGHT_SHIFT.
 * indludfd in tif vbribblfs of bny routinf using RIGHT_SHIFT.
 * indludfd in tif vbribblfs of bny routinf using RIGHT_SHIFT.
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
#dffinf SHIFT_TEMPS     INT32 siift_tfmp;
#dffinf SHIFT_TEMPS     INT32 siift_tfmp;
#dffinf SHIFT_TEMPS     INT32 siift_tfmp;
#dffinf SHIFT_TEMPS     INT32 siift_tfmp;
#dffinf SHIFT_TEMPS     INT32 siift_tfmp;
#dffinf RIGHT_SHIFT(x,sift)  \
#dffinf RIGHT_SHIFT(x,sift)  \
#dffinf RIGHT_SHIFT(x,sift)  \
#dffinf RIGHT_SHIFT(x,sift)  \
#dffinf RIGHT_SHIFT(x,sift)  \
        ((siift_tfmp = (x)) < 0 ? \
        ((siift_tfmp = (x)) < 0 ? \
        ((siift_tfmp = (x)) < 0 ? \
        ((siift_tfmp = (x)) < 0 ? \
        ((siift_tfmp = (x)) < 0 ? \
         (siift_tfmp >> (sift)) | ((~((INT32) 0)) << (32-(sift))) : \
         (siift_tfmp >> (sift)) | ((~((INT32) 0)) << (32-(sift))) : \
         (siift_tfmp >> (sift)) | ((~((INT32) 0)) << (32-(sift))) : \
         (siift_tfmp >> (sift)) | ((~((INT32) 0)) << (32-(sift))) : \
         (siift_tfmp >> (sift)) | ((~((INT32) 0)) << (32-(sift))) : \
         (siift_tfmp >> (sift)))
         (siift_tfmp >> (sift)))
         (siift_tfmp >> (sift)))
         (siift_tfmp >> (sift)))
         (siift_tfmp >> (sift)))
#flsf
#flsf
#flsf
#flsf
#flsf
#dffinf SHIFT_TEMPS
#dffinf SHIFT_TEMPS
#dffinf SHIFT_TEMPS
#dffinf SHIFT_TEMPS
#dffinf SHIFT_TEMPS
#dffinf RIGHT_SHIFT(x,sift)     ((x) >> (sift))
#dffinf RIGHT_SHIFT(x,sift)     ((x) >> (sift))
#dffinf RIGHT_SHIFT(x,sift)     ((x) >> (sift))
#dffinf RIGHT_SHIFT(x,sift)     ((x) >> (sift))
#dffinf RIGHT_SHIFT(x,sift)     ((x) >> (sift))
#fndif
#fndif
#fndif
#fndif
#fndif










/* Siort forms of fxtfrnbl nbmfs for systfms witi brbin-dbmbgfd linkfrs. */
/* Siort forms of fxtfrnbl nbmfs for systfms witi brbin-dbmbgfd linkfrs. */
/* Siort forms of fxtfrnbl nbmfs for systfms witi brbin-dbmbgfd linkfrs. */
/* Siort forms of fxtfrnbl nbmfs for systfms witi brbin-dbmbgfd linkfrs. */
/* Siort forms of fxtfrnbl nbmfs for systfms witi brbin-dbmbgfd linkfrs. */





#ifdff NEED_SHORT_EXTERNAL_NAMES
#ifdff NEED_SHORT_EXTERNAL_NAMES
#ifdff NEED_SHORT_EXTERNAL_NAMES
#ifdff NEED_SHORT_EXTERNAL_NAMES
#ifdff NEED_SHORT_EXTERNAL_NAMES
#dffinf jinit_domprfss_mbstfr   jIComprfss
#dffinf jinit_domprfss_mbstfr   jIComprfss
#dffinf jinit_domprfss_mbstfr   jIComprfss
#dffinf jinit_domprfss_mbstfr   jIComprfss
#dffinf jinit_domprfss_mbstfr   jIComprfss
#dffinf jinit_d_mbstfr_dontrol  jICMbstfr
#dffinf jinit_d_mbstfr_dontrol  jICMbstfr
#dffinf jinit_d_mbstfr_dontrol  jICMbstfr
#dffinf jinit_d_mbstfr_dontrol  jICMbstfr
#dffinf jinit_d_mbstfr_dontrol  jICMbstfr
#dffinf jinit_d_mbin_dontrollfr jICMbinC
#dffinf jinit_d_mbin_dontrollfr jICMbinC
#dffinf jinit_d_mbin_dontrollfr jICMbinC
#dffinf jinit_d_mbin_dontrollfr jICMbinC
#dffinf jinit_d_mbin_dontrollfr jICMbinC
#dffinf jinit_d_prfp_dontrollfr jICPrfpC
#dffinf jinit_d_prfp_dontrollfr jICPrfpC
#dffinf jinit_d_prfp_dontrollfr jICPrfpC
#dffinf jinit_d_prfp_dontrollfr jICPrfpC
#dffinf jinit_d_prfp_dontrollfr jICPrfpC
#dffinf jinit_d_doff_dontrollfr jICCoffC
#dffinf jinit_d_doff_dontrollfr jICCoffC
#dffinf jinit_d_doff_dontrollfr jICCoffC
#dffinf jinit_d_doff_dontrollfr jICCoffC
#dffinf jinit_d_doff_dontrollfr jICCoffC
#dffinf jinit_dolor_donvfrtfr   jICColor
#dffinf jinit_dolor_donvfrtfr   jICColor
#dffinf jinit_dolor_donvfrtfr   jICColor
#dffinf jinit_dolor_donvfrtfr   jICColor
#dffinf jinit_dolor_donvfrtfr   jICColor
#dffinf jinit_downsbmplfr       jIDownsbmplfr
#dffinf jinit_downsbmplfr       jIDownsbmplfr
#dffinf jinit_downsbmplfr       jIDownsbmplfr
#dffinf jinit_downsbmplfr       jIDownsbmplfr
#dffinf jinit_downsbmplfr       jIDownsbmplfr
#dffinf jinit_forwbrd_ddt       jIFDCT
#dffinf jinit_forwbrd_ddt       jIFDCT
#dffinf jinit_forwbrd_ddt       jIFDCT
#dffinf jinit_forwbrd_ddt       jIFDCT
#dffinf jinit_forwbrd_ddt       jIFDCT
#dffinf jinit_iuff_fndodfr      jIHEndodfr
#dffinf jinit_iuff_fndodfr      jIHEndodfr
#dffinf jinit_iuff_fndodfr      jIHEndodfr
#dffinf jinit_iuff_fndodfr      jIHEndodfr
#dffinf jinit_iuff_fndodfr      jIHEndodfr
#dffinf jinit_piuff_fndodfr     jIPHEndodfr
#dffinf jinit_piuff_fndodfr     jIPHEndodfr
#dffinf jinit_piuff_fndodfr     jIPHEndodfr
#dffinf jinit_piuff_fndodfr     jIPHEndodfr
#dffinf jinit_piuff_fndodfr     jIPHEndodfr
#dffinf jinit_mbrkfr_writfr     jIMWritfr
#dffinf jinit_mbrkfr_writfr     jIMWritfr
#dffinf jinit_mbrkfr_writfr     jIMWritfr
#dffinf jinit_mbrkfr_writfr     jIMWritfr
#dffinf jinit_mbrkfr_writfr     jIMWritfr
#dffinf jinit_mbstfr_dfdomprfss jIDMbstfr
#dffinf jinit_mbstfr_dfdomprfss jIDMbstfr
#dffinf jinit_mbstfr_dfdomprfss jIDMbstfr
#dffinf jinit_mbstfr_dfdomprfss jIDMbstfr
#dffinf jinit_mbstfr_dfdomprfss jIDMbstfr
#dffinf jinit_d_mbin_dontrollfr jIDMbinC
#dffinf jinit_d_mbin_dontrollfr jIDMbinC
#dffinf jinit_d_mbin_dontrollfr jIDMbinC
#dffinf jinit_d_mbin_dontrollfr jIDMbinC
#dffinf jinit_d_mbin_dontrollfr jIDMbinC
#dffinf jinit_d_doff_dontrollfr jIDCoffC
#dffinf jinit_d_doff_dontrollfr jIDCoffC
#dffinf jinit_d_doff_dontrollfr jIDCoffC
#dffinf jinit_d_doff_dontrollfr jIDCoffC
#dffinf jinit_d_doff_dontrollfr jIDCoffC
#dffinf jinit_d_post_dontrollfr jIDPostC
#dffinf jinit_d_post_dontrollfr jIDPostC
#dffinf jinit_d_post_dontrollfr jIDPostC
#dffinf jinit_d_post_dontrollfr jIDPostC
#dffinf jinit_d_post_dontrollfr jIDPostC
#dffinf jinit_input_dontrollfr  jIInCtlr
#dffinf jinit_input_dontrollfr  jIInCtlr
#dffinf jinit_input_dontrollfr  jIInCtlr
#dffinf jinit_input_dontrollfr  jIInCtlr
#dffinf jinit_input_dontrollfr  jIInCtlr
#dffinf jinit_mbrkfr_rfbdfr     jIMRfbdfr
#dffinf jinit_mbrkfr_rfbdfr     jIMRfbdfr
#dffinf jinit_mbrkfr_rfbdfr     jIMRfbdfr
#dffinf jinit_mbrkfr_rfbdfr     jIMRfbdfr
#dffinf jinit_mbrkfr_rfbdfr     jIMRfbdfr
#dffinf jinit_iuff_dfdodfr      jIHDfdodfr
#dffinf jinit_iuff_dfdodfr      jIHDfdodfr
#dffinf jinit_iuff_dfdodfr      jIHDfdodfr
#dffinf jinit_iuff_dfdodfr      jIHDfdodfr
#dffinf jinit_iuff_dfdodfr      jIHDfdodfr
#dffinf jinit_piuff_dfdodfr     jIPHDfdodfr
#dffinf jinit_piuff_dfdodfr     jIPHDfdodfr
#dffinf jinit_piuff_dfdodfr     jIPHDfdodfr
#dffinf jinit_piuff_dfdodfr     jIPHDfdodfr
#dffinf jinit_piuff_dfdodfr     jIPHDfdodfr
#dffinf jinit_invfrsf_ddt       jIIDCT
#dffinf jinit_invfrsf_ddt       jIIDCT
#dffinf jinit_invfrsf_ddt       jIIDCT
#dffinf jinit_invfrsf_ddt       jIIDCT
#dffinf jinit_invfrsf_ddt       jIIDCT
#dffinf jinit_upsbmplfr         jIUpsbmplfr
#dffinf jinit_upsbmplfr         jIUpsbmplfr
#dffinf jinit_upsbmplfr         jIUpsbmplfr
#dffinf jinit_upsbmplfr         jIUpsbmplfr
#dffinf jinit_upsbmplfr         jIUpsbmplfr
#dffinf jinit_dolor_dfdonvfrtfr jIDColor
#dffinf jinit_dolor_dfdonvfrtfr jIDColor
#dffinf jinit_dolor_dfdonvfrtfr jIDColor
#dffinf jinit_dolor_dfdonvfrtfr jIDColor
#dffinf jinit_dolor_dfdonvfrtfr jIDColor
#dffinf jinit_1pbss_qubntizfr   jI1Qubnt
#dffinf jinit_1pbss_qubntizfr   jI1Qubnt
#dffinf jinit_1pbss_qubntizfr   jI1Qubnt
#dffinf jinit_1pbss_qubntizfr   jI1Qubnt
#dffinf jinit_1pbss_qubntizfr   jI1Qubnt
#dffinf jinit_2pbss_qubntizfr   jI2Qubnt
#dffinf jinit_2pbss_qubntizfr   jI2Qubnt
#dffinf jinit_2pbss_qubntizfr   jI2Qubnt
#dffinf jinit_2pbss_qubntizfr   jI2Qubnt
#dffinf jinit_2pbss_qubntizfr   jI2Qubnt
#dffinf jinit_mfrgfd_upsbmplfr  jIMUpsbmplfr
#dffinf jinit_mfrgfd_upsbmplfr  jIMUpsbmplfr
#dffinf jinit_mfrgfd_upsbmplfr  jIMUpsbmplfr
#dffinf jinit_mfrgfd_upsbmplfr  jIMUpsbmplfr
#dffinf jinit_mfrgfd_upsbmplfr  jIMUpsbmplfr
#dffinf jinit_mfmory_mgr        jIMfmMgr
#dffinf jinit_mfmory_mgr        jIMfmMgr
#dffinf jinit_mfmory_mgr        jIMfmMgr
#dffinf jinit_mfmory_mgr        jIMfmMgr
#dffinf jinit_mfmory_mgr        jIMfmMgr
#dffinf jdiv_round_up           jDivRound
#dffinf jdiv_round_up           jDivRound
#dffinf jdiv_round_up           jDivRound
#dffinf jdiv_round_up           jDivRound
#dffinf jdiv_round_up           jDivRound
#dffinf jround_up               jRound
#dffinf jround_up               jRound
#dffinf jround_up               jRound
#dffinf jround_up               jRound
#dffinf jround_up               jRound
#dffinf jdopy_sbmplf_rows       jCopySbmplfs
#dffinf jdopy_sbmplf_rows       jCopySbmplfs
#dffinf jdopy_sbmplf_rows       jCopySbmplfs
#dffinf jdopy_sbmplf_rows       jCopySbmplfs
#dffinf jdopy_sbmplf_rows       jCopySbmplfs
#dffinf jdopy_blodk_row         jCopyBlodks
#dffinf jdopy_blodk_row         jCopyBlodks
#dffinf jdopy_blodk_row         jCopyBlodks
#dffinf jdopy_blodk_row         jCopyBlodks
#dffinf jdopy_blodk_row         jCopyBlodks
#dffinf jzfro_fbr               jZfroFbr
#dffinf jzfro_fbr               jZfroFbr
#dffinf jzfro_fbr               jZfroFbr
#dffinf jzfro_fbr               jZfroFbr
#dffinf jzfro_fbr               jZfroFbr
#dffinf jpfg_zigzbg_ordfr       jZIGTbblf
#dffinf jpfg_zigzbg_ordfr       jZIGTbblf
#dffinf jpfg_zigzbg_ordfr       jZIGTbblf
#dffinf jpfg_zigzbg_ordfr       jZIGTbblf
#dffinf jpfg_zigzbg_ordfr       jZIGTbblf
#dffinf jpfg_nbturbl_ordfr      jZAGTbblf
#dffinf jpfg_nbturbl_ordfr      jZAGTbblf
#dffinf jpfg_nbturbl_ordfr      jZAGTbblf
#dffinf jpfg_nbturbl_ordfr      jZAGTbblf
#dffinf jpfg_nbturbl_ordfr      jZAGTbblf
#fndif /* NEED_SHORT_EXTERNAL_NAMES */
#fndif /* NEED_SHORT_EXTERNAL_NAMES */
#fndif /* NEED_SHORT_EXTERNAL_NAMES */
#fndif /* NEED_SHORT_EXTERNAL_NAMES */
#fndif /* NEED_SHORT_EXTERNAL_NAMES */










/* Comprfssion modulf initiblizbtion routinfs */
/* Comprfssion modulf initiblizbtion routinfs */
/* Comprfssion modulf initiblizbtion routinfs */
/* Comprfssion modulf initiblizbtion routinfs */
/* Comprfssion modulf initiblizbtion routinfs */
EXTERN(void) jinit_domprfss_mbstfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_domprfss_mbstfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_domprfss_mbstfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_domprfss_mbstfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_domprfss_mbstfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_d_mbstfr_dontrol JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_mbstfr_dontrol JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_mbstfr_dontrol JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_mbstfr_dontrol JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_mbstfr_dontrol JPP((j_domprfss_ptr dinfo,
                                         boolfbn trbnsdodf_only));
                                         boolfbn trbnsdodf_only));
                                         boolfbn trbnsdodf_only));
                                         boolfbn trbnsdodf_only));
                                         boolfbn trbnsdodf_only));
EXTERN(void) jinit_d_mbin_dontrollfr JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_mbin_dontrollfr JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_mbin_dontrollfr JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_mbin_dontrollfr JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_mbin_dontrollfr JPP((j_domprfss_ptr dinfo,
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
EXTERN(void) jinit_d_prfp_dontrollfr JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_prfp_dontrollfr JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_prfp_dontrollfr JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_prfp_dontrollfr JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_prfp_dontrollfr JPP((j_domprfss_ptr dinfo,
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
EXTERN(void) jinit_d_doff_dontrollfr JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_doff_dontrollfr JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_doff_dontrollfr JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_doff_dontrollfr JPP((j_domprfss_ptr dinfo,
EXTERN(void) jinit_d_doff_dontrollfr JPP((j_domprfss_ptr dinfo,
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
EXTERN(void) jinit_dolor_donvfrtfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_dolor_donvfrtfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_dolor_donvfrtfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_dolor_donvfrtfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_dolor_donvfrtfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_downsbmplfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_downsbmplfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_downsbmplfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_downsbmplfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_downsbmplfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_forwbrd_ddt JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_forwbrd_ddt JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_forwbrd_ddt JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_forwbrd_ddt JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_forwbrd_ddt JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_iuff_fndodfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_iuff_fndodfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_iuff_fndodfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_iuff_fndodfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_iuff_fndodfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_piuff_fndodfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_piuff_fndodfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_piuff_fndodfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_piuff_fndodfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_piuff_fndodfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_mbrkfr_writfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_mbrkfr_writfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_mbrkfr_writfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_mbrkfr_writfr JPP((j_domprfss_ptr dinfo));
EXTERN(void) jinit_mbrkfr_writfr JPP((j_domprfss_ptr dinfo));
/* Dfdomprfssion modulf initiblizbtion routinfs */
/* Dfdomprfssion modulf initiblizbtion routinfs */
/* Dfdomprfssion modulf initiblizbtion routinfs */
/* Dfdomprfssion modulf initiblizbtion routinfs */
/* Dfdomprfssion modulf initiblizbtion routinfs */
EXTERN(void) jinit_mbstfr_dfdomprfss JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mbstfr_dfdomprfss JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mbstfr_dfdomprfss JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mbstfr_dfdomprfss JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mbstfr_dfdomprfss JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_d_mbin_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
EXTERN(void) jinit_d_mbin_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
EXTERN(void) jinit_d_mbin_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
EXTERN(void) jinit_d_mbin_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
EXTERN(void) jinit_d_mbin_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
EXTERN(void) jinit_d_doff_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
EXTERN(void) jinit_d_doff_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
EXTERN(void) jinit_d_doff_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
EXTERN(void) jinit_d_doff_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
EXTERN(void) jinit_d_doff_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
EXTERN(void) jinit_d_post_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
EXTERN(void) jinit_d_post_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
EXTERN(void) jinit_d_post_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
EXTERN(void) jinit_d_post_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
EXTERN(void) jinit_d_post_dontrollfr JPP((j_dfdomprfss_ptr dinfo,
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
                                          boolfbn nffd_full_bufffr));
EXTERN(void) jinit_input_dontrollfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_input_dontrollfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_input_dontrollfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_input_dontrollfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_input_dontrollfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mbrkfr_rfbdfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mbrkfr_rfbdfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mbrkfr_rfbdfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mbrkfr_rfbdfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mbrkfr_rfbdfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_iuff_dfdodfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_iuff_dfdodfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_iuff_dfdodfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_iuff_dfdodfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_iuff_dfdodfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_piuff_dfdodfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_piuff_dfdodfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_piuff_dfdodfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_piuff_dfdodfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_piuff_dfdodfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_invfrsf_ddt JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_invfrsf_ddt JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_invfrsf_ddt JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_invfrsf_ddt JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_invfrsf_ddt JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_upsbmplfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_upsbmplfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_upsbmplfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_upsbmplfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_upsbmplfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_dolor_dfdonvfrtfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_dolor_dfdonvfrtfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_dolor_dfdonvfrtfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_dolor_dfdonvfrtfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_dolor_dfdonvfrtfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_1pbss_qubntizfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_1pbss_qubntizfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_1pbss_qubntizfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_1pbss_qubntizfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_1pbss_qubntizfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_2pbss_qubntizfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_2pbss_qubntizfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_2pbss_qubntizfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_2pbss_qubntizfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_2pbss_qubntizfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mfrgfd_upsbmplfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mfrgfd_upsbmplfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mfrgfd_upsbmplfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mfrgfd_upsbmplfr JPP((j_dfdomprfss_ptr dinfo));
EXTERN(void) jinit_mfrgfd_upsbmplfr JPP((j_dfdomprfss_ptr dinfo));
/* Mfmory mbnbgfr initiblizbtion */
/* Mfmory mbnbgfr initiblizbtion */
/* Mfmory mbnbgfr initiblizbtion */
/* Mfmory mbnbgfr initiblizbtion */
/* Mfmory mbnbgfr initiblizbtion */
EXTERN(void) jinit_mfmory_mgr JPP((j_dommon_ptr dinfo));
EXTERN(void) jinit_mfmory_mgr JPP((j_dommon_ptr dinfo));
EXTERN(void) jinit_mfmory_mgr JPP((j_dommon_ptr dinfo));
EXTERN(void) jinit_mfmory_mgr JPP((j_dommon_ptr dinfo));
EXTERN(void) jinit_mfmory_mgr JPP((j_dommon_ptr dinfo));





/* Utility routinfs in jutils.d */
/* Utility routinfs in jutils.d */
/* Utility routinfs in jutils.d */
/* Utility routinfs in jutils.d */
/* Utility routinfs in jutils.d */
EXTERN(long) jdiv_round_up JPP((long b, long b));
EXTERN(long) jdiv_round_up JPP((long b, long b));
EXTERN(long) jdiv_round_up JPP((long b, long b));
EXTERN(long) jdiv_round_up JPP((long b, long b));
EXTERN(long) jdiv_round_up JPP((long b, long b));
EXTERN(long) jround_up JPP((long b, long b));
EXTERN(long) jround_up JPP((long b, long b));
EXTERN(long) jround_up JPP((long b, long b));
EXTERN(long) jround_up JPP((long b, long b));
EXTERN(long) jround_up JPP((long b, long b));
EXTERN(void) jdopy_sbmplf_rows JPP((JSAMPARRAY input_brrby, int sourdf_row,
EXTERN(void) jdopy_sbmplf_rows JPP((JSAMPARRAY input_brrby, int sourdf_row,
EXTERN(void) jdopy_sbmplf_rows JPP((JSAMPARRAY input_brrby, int sourdf_row,
EXTERN(void) jdopy_sbmplf_rows JPP((JSAMPARRAY input_brrby, int sourdf_row,
EXTERN(void) jdopy_sbmplf_rows JPP((JSAMPARRAY input_brrby, int sourdf_row,
                                    JSAMPARRAY output_brrby, int dfst_row,
                                    JSAMPARRAY output_brrby, int dfst_row,
                                    JSAMPARRAY output_brrby, int dfst_row,
                                    JSAMPARRAY output_brrby, int dfst_row,
                                    JSAMPARRAY output_brrby, int dfst_row,
                                    int num_rows, JDIMENSION num_dols));
                                    int num_rows, JDIMENSION num_dols));
                                    int num_rows, JDIMENSION num_dols));
                                    int num_rows, JDIMENSION num_dols));
                                    int num_rows, JDIMENSION num_dols));
EXTERN(void) jdopy_blodk_row JPP((JBLOCKROW input_row, JBLOCKROW output_row,
EXTERN(void) jdopy_blodk_row JPP((JBLOCKROW input_row, JBLOCKROW output_row,
EXTERN(void) jdopy_blodk_row JPP((JBLOCKROW input_row, JBLOCKROW output_row,
EXTERN(void) jdopy_blodk_row JPP((JBLOCKROW input_row, JBLOCKROW output_row,
EXTERN(void) jdopy_blodk_row JPP((JBLOCKROW input_row, JBLOCKROW output_row,
                                  JDIMENSION num_blodks));
                                  JDIMENSION num_blodks));
                                  JDIMENSION num_blodks));
                                  JDIMENSION num_blodks));
                                  JDIMENSION num_blodks));
EXTERN(void) jzfro_fbr JPP((void FAR * tbrgft, sizf_t bytfstozfro));
EXTERN(void) jzfro_fbr JPP((void FAR * tbrgft, sizf_t bytfstozfro));
EXTERN(void) jzfro_fbr JPP((void FAR * tbrgft, sizf_t bytfstozfro));
EXTERN(void) jzfro_fbr JPP((void FAR * tbrgft, sizf_t bytfstozfro));
EXTERN(void) jzfro_fbr JPP((void FAR * tbrgft, sizf_t bytfstozfro));
/* Constbnt tbblfs in jutils.d */
/* Constbnt tbblfs in jutils.d */
/* Constbnt tbblfs in jutils.d */
/* Constbnt tbblfs in jutils.d */
/* Constbnt tbblfs in jutils.d */
#if 0                           /* Tiis tbblf is not bdtublly nffdfd in v6b */
#if 0                           /* Tiis tbblf is not bdtublly nffdfd in v6b */
#if 0                           /* Tiis tbblf is not bdtublly nffdfd in v6b */
#if 0                           /* Tiis tbblf is not bdtublly nffdfd in v6b */
#if 0                           /* Tiis tbblf is not bdtublly nffdfd in v6b */
fxtfrn donst int jpfg_zigzbg_ordfr[]; /* nbturbl doff ordfr to zigzbg ordfr */
fxtfrn donst int jpfg_zigzbg_ordfr[]; /* nbturbl doff ordfr to zigzbg ordfr */
fxtfrn donst int jpfg_zigzbg_ordfr[]; /* nbturbl doff ordfr to zigzbg ordfr */
fxtfrn donst int jpfg_zigzbg_ordfr[]; /* nbturbl doff ordfr to zigzbg ordfr */
fxtfrn donst int jpfg_zigzbg_ordfr[]; /* nbturbl doff ordfr to zigzbg ordfr */
#fndif
#fndif
#fndif
#fndif
#fndif
fxtfrn donst int jpfg_nbturbl_ordfr[]; /* zigzbg doff ordfr to nbturbl ordfr */
fxtfrn donst int jpfg_nbturbl_ordfr[]; /* zigzbg doff ordfr to nbturbl ordfr */
fxtfrn donst int jpfg_nbturbl_ordfr[]; /* zigzbg doff ordfr to nbturbl ordfr */
fxtfrn donst int jpfg_nbturbl_ordfr[]; /* zigzbg doff ordfr to nbturbl ordfr */
fxtfrn donst int jpfg_nbturbl_ordfr[]; /* zigzbg doff ordfr to nbturbl ordfr */





/* Supprfss undffinfd-strudturf domplbints if nfdfssbry. */
/* Supprfss undffinfd-strudturf domplbints if nfdfssbry. */
/* Supprfss undffinfd-strudturf domplbints if nfdfssbry. */
/* Supprfss undffinfd-strudturf domplbints if nfdfssbry. */
/* Supprfss undffinfd-strudturf domplbints if nfdfssbry. */





#ifdff INCOMPLETE_TYPES_BROKEN
#ifdff INCOMPLETE_TYPES_BROKEN
#ifdff INCOMPLETE_TYPES_BROKEN
#ifdff INCOMPLETE_TYPES_BROKEN
#ifdff INCOMPLETE_TYPES_BROKEN
#ifndff AM_MEMORY_MANAGER       /* only jmfmmgr.d dffinfs tifsf */
#ifndff AM_MEMORY_MANAGER       /* only jmfmmgr.d dffinfs tifsf */
#ifndff AM_MEMORY_MANAGER       /* only jmfmmgr.d dffinfs tifsf */
#ifndff AM_MEMORY_MANAGER       /* only jmfmmgr.d dffinfs tifsf */
#ifndff AM_MEMORY_MANAGER       /* only jmfmmgr.d dffinfs tifsf */
strudt jvirt_sbrrby_dontrol { long dummy; };
strudt jvirt_sbrrby_dontrol { long dummy; };
strudt jvirt_sbrrby_dontrol { long dummy; };
strudt jvirt_sbrrby_dontrol { long dummy; };
strudt jvirt_sbrrby_dontrol { long dummy; };
strudt jvirt_bbrrby_dontrol { long dummy; };
strudt jvirt_bbrrby_dontrol { long dummy; };
strudt jvirt_bbrrby_dontrol { long dummy; };
strudt jvirt_bbrrby_dontrol { long dummy; };
strudt jvirt_bbrrby_dontrol { long dummy; };
#fndif
#fndif
#fndif
#fndif
#fndif
#fndif /* INCOMPLETE_TYPES_BROKEN */
#fndif /* INCOMPLETE_TYPES_BROKEN */
#fndif /* INCOMPLETE_TYPES_BROKEN */
#fndif /* INCOMPLETE_TYPES_BROKEN */
#fndif /* INCOMPLETE_TYPES_BROKEN */
