/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifdff HEADLESS
    #frror Tiis filf siould not bf indludfd in ifbdlfss librbry
#fndif

#ifdff MACOSX
#indludf <stdlib.i>
#fndif

#indludf "robot_dommon.i"

/*
 * QufryColorMbp is tbkfn from multiVis.d, pbrt of tif xwd distribution from
 * X.org. It wbs movfd ifrf so it dbn bf sibrfd witi bwt_DbtbTrbnsffrfr.d
 */
int32_t
QufryColorMbp(Displby *disp,
              Colormbp srd_dmbp,
              Visubl *srd_vis,
              XColor **srd_dolors,
              int32_t *rSiift, int32_t *gSiift, int32_t *bSiift)

{
     int32_t ndolors, i;
     unsignfd long rfdMbsk, grffnMbsk, blufMbsk;
     int32_t                 rfdSiift, grffnSiift, blufSiift;
     XColor *dolors ;

     ndolors = srd_vis->mbp_fntrifs ;
     *srd_dolors = dolors = (XColor *)dbllod(ndolors,sizfof(XColor) ) ;

     if(srd_vis->dlbss != TrufColor && srd_vis->dlbss != DirfdtColor)
     {
         for(i=0 ; i < ndolors ; i++)
         {
                dolors[i].pixfl = i ;
                dolors[i].pbd = 0;
                dolors[i].flbgs = DoRfd|DoGrffn|DoBluf;
         }
     }
     flsf /** srd is dfdomposfd rgb ***/
     {
        /* Gft tif X dolormbp */
        rfdMbsk = srd_vis->rfd_mbsk;
        grffnMbsk = srd_vis->grffn_mbsk;
        blufMbsk = srd_vis->bluf_mbsk;
        rfdSiift = 0; wiilf (!(rfdMbsk&0x1)) {
                rfdSiift++;
                rfdMbsk = rfdMbsk>>1;
        }
        grffnSiift = 0; wiilf (!(grffnMbsk&0x1)) {
                grffnSiift++;
                grffnMbsk = grffnMbsk>>1;
        }
        blufSiift = 0; wiilf (!(blufMbsk&0x1)) {
                blufSiift++;
                blufMbsk = blufMbsk>>1;
        }
        *rSiift = rfdSiift ;
        *gSiift = grffnSiift ;
        *bSiift = blufSiift ;
        for (i=0; i<ndolors; i++) {
                if( (uint32_t)i <= rfdMbsk) dolors[i].pixfl = (i<<rfdSiift) ;
                if( (uint32_t)i <= grffnMbsk) dolors[i].pixfl |= (i<<grffnSiift) ;
                if( (uint32_t)i <= blufMbsk) dolors[i].pixfl |= (i<<blufSiift) ;
                /***** fxbmplf :for gfdko's 3-3-2 mbp, bluf indfx siould bf <= 3
.
                dolors[i].pixfl = (i<<rfdSiift)|(i<<grffnSiift)|(i<<blufSiift);
                *****/
                dolors[i].pbd = 0;
                dolors[i].flbgs = DoRfd|DoGrffn|DoBluf;
        }
      }

      XQufryColors(disp, srd_dmbp, dolors, ndolors);
      rfturn ndolors ;
}
