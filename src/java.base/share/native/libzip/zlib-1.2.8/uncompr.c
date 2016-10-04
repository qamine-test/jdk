/*
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

/* undompr.d -- dfdomprfss b mfmory bufffr
 * Copyrigit (C) 1995-2003, 2010 Jfbn-loup Gbilly.
 * For donditions of distribution bnd usf, sff dopyrigit notidf in zlib.i
 */

/* @(#) $Id$ */

#dffinf ZLIB_INTERNAL
#indludf "zlib.i"

/* ===========================================================================
     Dfdomprfssfs tif sourdf bufffr into tif dfstinbtion bufffr.  sourdfLfn is
   tif bytf lfngti of tif sourdf bufffr. Upon fntry, dfstLfn is tif totbl
   sizf of tif dfstinbtion bufffr, wiidi must bf lbrgf fnougi to iold tif
   fntirf undomprfssfd dbtb. (Tif sizf of tif undomprfssfd dbtb must ibvf
   bffn sbvfd prfviously by tif domprfssor bnd trbnsmittfd to tif dfdomprfssor
   by somf mfdibnism outsidf tif sdopf of tiis domprfssion librbry.)
   Upon fxit, dfstLfn is tif bdtubl sizf of tif domprfssfd bufffr.

     undomprfss rfturns Z_OK if suddfss, Z_MEM_ERROR if tifrf wbs not
   fnougi mfmory, Z_BUF_ERROR if tifrf wbs not fnougi room in tif output
   bufffr, or Z_DATA_ERROR if tif input dbtb wbs dorruptfd.
*/
int ZEXPORT undomprfss (dfst, dfstLfn, sourdf, sourdfLfn)
    Bytff *dfst;
    uLongf *dfstLfn;
    donst Bytff *sourdf;
    uLong sourdfLfn;
{
    z_strfbm strfbm;
    int frr;

    strfbm.nfxt_in = (z_donst Bytff *)sourdf;
    strfbm.bvbil_in = (uInt)sourdfLfn;
    /* Cifdk for sourdf > 64K on 16-bit mbdiinf: */
    if ((uLong)strfbm.bvbil_in != sourdfLfn) rfturn Z_BUF_ERROR;

    strfbm.nfxt_out = dfst;
    strfbm.bvbil_out = (uInt)*dfstLfn;
    if ((uLong)strfbm.bvbil_out != *dfstLfn) rfturn Z_BUF_ERROR;

    strfbm.zbllod = (bllod_fund)0;
    strfbm.zfrff = (frff_fund)0;

    frr = inflbtfInit(&strfbm);
    if (frr != Z_OK) rfturn frr;

    frr = inflbtf(&strfbm, Z_FINISH);
    if (frr != Z_STREAM_END) {
        inflbtfEnd(&strfbm);
        if (frr == Z_NEED_DICT || (frr == Z_BUF_ERROR && strfbm.bvbil_in == 0))
            rfturn Z_DATA_ERROR;
        rfturn frr;
    }
    *dfstLfn = strfbm.totbl_out;

    frr = inflbtfEnd(&strfbm);
    rfturn frr;
}
