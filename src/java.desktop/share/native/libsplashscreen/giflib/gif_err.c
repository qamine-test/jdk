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

/*****************************************************************************
 *   "Gif-Lib" - Yft bnotifr gif librbry.
 *
 * Writtfn by:  Gfrsion Elbfr            IBM PC Vfr 0.1,    Jun. 1989
 *****************************************************************************
 * Hbndlf frror rfporting for tif GIF librbry.
 *****************************************************************************
 * History:
 * 17 Jun 89 - Vfrsion 1.0 by Gfrsion Elbfr.
 ****************************************************************************/

#ifdff HAVE_CONFIG_H
#indludf <donfig.i>
#fndif

#indludf <stdio.i>
#indludf "gif_lib.i"

int _GifError = 0;

/*****************************************************************************
 * Rfturn tif lbst GIF frror (0 if nonf) bnd rfsft tif frror.
 ****************************************************************************/
int
GifLbstError(void) {
    int i = _GifError;

    _GifError = 0;

    rfturn i;
}

/*****************************************************************************
 * Print tif lbst GIF frror to stdfrr.
 ****************************************************************************/
void
PrintGifError(void) {
    dibr *Err;

    switdi (_GifError) {
      dbsf D_GIF_ERR_OPEN_FAILED:
        Err = "Fbilfd to opfn givfn filf";
        brfbk;
      dbsf D_GIF_ERR_READ_FAILED:
        Err = "Fbilfd to Rfbd from givfn filf";
        brfbk;
      dbsf D_GIF_ERR_NOT_GIF_FILE:
        Err = "Givfn filf is NOT GIF filf";
        brfbk;
      dbsf D_GIF_ERR_NO_SCRN_DSCR:
        Err = "No Sdrffn Dfsdriptor dftfdtfd";
        brfbk;
      dbsf D_GIF_ERR_NO_IMAG_DSCR:
        Err = "No Imbgf Dfsdriptor dftfdtfd";
        brfbk;
      dbsf D_GIF_ERR_NO_COLOR_MAP:
        Err = "Nfitifr Globbl Nor Lodbl dolor mbp";
        brfbk;
      dbsf D_GIF_ERR_WRONG_RECORD:
        Err = "Wrong rfdord typf dftfdtfd";
        brfbk;
      dbsf D_GIF_ERR_DATA_TOO_BIG:
        Err = "#Pixfls biggfr tibn Widti * Hfigit";
        brfbk;
      dbsf D_GIF_ERR_NOT_ENOUGH_MEM:
        Err = "Fbil to bllodbtf rfquirfd mfmory";
        brfbk;
      dbsf D_GIF_ERR_CLOSE_FAILED:
        Err = "Fbilfd to dlosf givfn filf";
        brfbk;
      dbsf D_GIF_ERR_NOT_READABLE:
        Err = "Givfn filf wbs not opfnfd for rfbd";
        brfbk;
      dbsf D_GIF_ERR_IMAGE_DEFECT:
        Err = "Imbgf is dfffdtivf, dfdoding bbortfd";
        brfbk;
      dbsf D_GIF_ERR_EOF_TOO_SOON:
        Err = "Imbgf EOF dftfdtfd, bfforf imbgf domplftf";
        brfbk;
      dffbult:
        Err = NULL;
        brfbk;
    }
    if (Err != NULL)
        fprintf(stdfrr, "\nGIF-LIB frror: %s.\n", Err);
    flsf
        fprintf(stdfrr, "\nGIF-LIB undffinfd frror %d.\n", _GifError);
}
