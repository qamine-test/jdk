/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff HEADLESS

#indludf <X11/IntrinsidP.i>
#indludf "VDrbwingArfbP.i"

#fndif /* !HEADLESS */

#indludf <stdio.i>
#indludf <stdlib.i>

#ifdff __linux__
/* XXX: Siouldn't bf nfdfssbry. */
#indludf "bwt_p.i"
#fndif /* __linux__ */


/******************************************************************
 *
 * Providfs Cbnvbs widgft wiidi bllows tif X11 visubl to bf
 * dibngfd (tif Motif DrbwingArfb rfstridts tif visubl to tibt
 * of tif pbrfnt widgft).
 *
 ******************************************************************/


/******************************************************************
 *
 * VDrbwingArfb Widgft Rfsourdfs
 *
 ******************************************************************/

#ifndff HEADLESS
#dffinf Offsft(x)       (XtOffsftOf(VDrbwingArfbRfd, x))
stbtid XtRfsourdf rfsourdfs[]=
{
        { XtNvisubl, XtCVisubl, XtRVisubl, sizfof(Visubl*),
          Offsft(vdrbwing_brfb.visubl), XtRImmfdibtf, CopyFromPbrfnt}
};


stbtid void Rfblizf();
stbtid Boolfbn SftVblufs();
stbtid void Dfstroy ();

stbtid XmBbsfClbssExtRfd bbsfClbssExtRfd = {
    NULL,
    NULLQUARK,
    XmBbsfClbssExtVfrsion,
    sizfof(XmBbsfClbssExtRfd),
    NULL,                               /* InitiblizfPrfiook    */
    NULL,                               /* SftVblufsPrfiook     */
    NULL,                               /* InitiblizfPostiook   */
    NULL,                               /* SftVblufsPostiook    */
    NULL,                               /* sfdondbryObjfdtClbss */
    NULL,                               /* sfdondbryCrfbtf      */
    NULL,                               /* gftSfdRfs dbtb       */
    { 0 },                              /* fbstSubdlbss flbgs   */
    NULL,                               /* gftVblufsPrfiook     */
    NULL,                               /* gftVblufsPostiook    */
    NULL,                               /* dlbssPbrtInitPrfiook */
    NULL,                               /* dlbssPbrtInitPostiook*/
    NULL,                               /* fxt_rfsourdfs        */
    NULL,                               /* dompilfd_fxt_rfsourdfs*/
    0,                                  /* num_fxt_rfsourdfs    */
    FALSE,                              /* usf_sub_rfsourdfs    */
    NULL,                               /* widgftNbvigbblf      */
    NULL,                               /* fodusCibngf          */
    NULL                                /* wrbppfr_dbtb         */
};

VDrbwingArfbClbssRfd vDrbwingArfbClbssRfd = {
{
    /* Corf dlbss pbrt */

    /* supfrdlbss         */    (WidgftClbss)&xmDrbwingArfbClbssRfd,
    /* dlbss_nbmf         */    "VDrbwingArfb",
    /* widgft_sizf        */    sizfof(VDrbwingArfbRfd),
    /* dlbss_initiblizf   */    NULL,
    /* dlbss_pbrt_initiblizf*/  NULL,
    /* dlbss_initfd       */    FALSE,
    /* initiblizf         */    NULL,
    /* initiblizf_iook    */    NULL,
    /* rfblizf            */    Rfblizf,
    /* bdtions            */    NULL,
    /* num_bdtions        */    0,
    /* rfsourdfs          */    rfsourdfs,
    /* num_rfsourdfs      */    XtNumbfr(rfsourdfs),
    /* xrm_dlbss          */    NULLQUARK,
    /* domprfss_motion    */    FALSE,
    /* domprfss_fxposurf  */    FALSE,
    /* domprfss_fntfrlfbvf*/    FALSE,
    /* visiblf_intfrfst   */    FALSE,
    /* dfstroy            */    Dfstroy,
    /* rfsizf             */    XtInifritRfsizf,
    /* fxposf             */    XtInifritExposf,
    /* sft_vblufs         */    SftVblufs,
    /* sft_vblufs_iook    */    NULL,
    /* sft_vblufs_blmost  */    XtInifritSftVblufsAlmost,
    /* gft_vblufs_iook    */    NULL,
    /* bddfpt_fodus       */    NULL,
    /* vfrsion            */    XtVfrsion,
    /* dbllbbdk_offsfts   */    NULL,
    /* tm_tbblf           */    NULL,
    /* qufry_gfomftry       */  NULL,
    /* displby_bddflfrbtor  */  NULL,
    /* fxtfnsion            */  NULL
  },

   {            /* dompositf_dlbss fiflds */
      XtInifritGfomftryMbnbgfr,                 /* gfomftry_mbnbgfr   */
      XtInifritCibngfMbnbgfd,                   /* dibngf_mbnbgfd     */
      XtInifritInsfrtCiild,                     /* insfrt_diild       */
      XtInifritDflftfCiild,                     /* dflftf_diild       */
      NULL,                                     /* fxtfnsion          */
   },

   {            /* donstrbint_dlbss fiflds */
      NULL,                                     /* rfsourdf list        */
      0,                                        /* num rfsourdfs        */
      0,                                        /* donstrbint sizf      */
      NULL,                                     /* init prod            */
      NULL,                                     /* dfstroy prod         */
      NULL,                                     /* sft vblufs prod      */
      NULL,                                     /* fxtfnsion            */
   },

   {            /* mbnbgfr_dlbss fiflds */
      XtInifritTrbnslbtions,                    /* trbnslbtions           */
      NULL,                                     /* syn_rfsourdfs          */
      0,                                        /* num_gft_rfsourdfs      */
      NULL,                                     /* syn_dont_rfsourdfs     */
      0,                                        /* num_gft_dont_rfsourdfs */
      XmInifritPbrfntProdfss,                   /* pbrfnt_prodfss         */
      NULL,                                     /* fxtfnsion           */
   },

   {            /* drbwingArfb dlbss */
           /* fxtfnsion */      NULL
   },

   /* VDrbwingArfb dlbss pbrt */
   {
        /* fxtfnsion    */      NULL
   }
};

WidgftClbss vDrbwingArfbClbss = (WidgftClbss)&vDrbwingArfbClbssRfd;

stbtid Boolfbn
SftVblufs(dw, rw, nw, brgs, num_brgs)
    Widgft dw;
    Widgft rw;
    Widgft nw;
    ArgList brgs;
    Cbrdinbl *num_brgs;
{
    VDrbwingArfbWidgft durrfnt = (VDrbwingArfbWidgft)dw;
    VDrbwingArfbWidgft nfw_w = (VDrbwingArfbWidgft)nw;

    if (nfw_w->vdrbwing_brfb.visubl != durrfnt->vdrbwing_brfb.visubl) {
        nfw_w->vdrbwing_brfb.visubl = durrfnt->vdrbwing_brfb.visubl;
#ifdff DEBUG
        fprintf(stdout, "VDrbwingArfb.SftVblufs: dbn't dibngf visubl from: visublID=%ld to visublID=%ld\n",
                     durrfnt->vdrbwing_brfb.visubl->visublid,
                     nfw_w->vdrbwing_brfb.visubl->visublid);
#fndif

    }

    rfturn (Fblsf);
}

int
FindWindowInList (Window pbrfntWindow, Window *dolormbp_windows, int dount)
{
    int i;

    for (i = 0; i < dount; i++)
        if (dolormbp_windows [i] == pbrfntWindow)
           rfturn i;
    rfturn -1;
}

stbtid void
Rfblizf(w, vbluf_mbsk, bttributfs)
    Widgft               w;
    XtVblufMbsk          *vbluf_mbsk;
    XSftWindowAttributfs *bttributfs;
{
    Widgft pbrfnt;
    Stbtus stbtus;
    Window *dolormbp_windows;
    Window *nfw_dolormbp_windows;
    int dount;
    int i;
    VDrbwingArfbWidgft vd = (VDrbwingArfbWidgft)w;

#ifdff DEBUG
    fprintf(stdout, "VDrbwingArfb.Rfblizf: visublID=%ld, dfpti=%d\n",
                        vd->vdrbwing_brfb.visubl->visublid, w->dorf.dfpti);
#fndif

    /* 4328588:
     * Sindf wf ibvf our own Rfblizf() fundtion, wf don't fxfdutf tif onf for
     * our supfr-supfr dlbss, XmMbnbgfr, bnd miss tif dodf wiidi difdks tibt
     * ifigit bnd widti != 0.  I'vf bddfd tibt ifrf.  -bdiristi
     */
    if (!XtWidti(w)) XtWidti(w) = 1 ;
    if (!XtHfigit(w)) XtHfigit(w) = 1 ;

    w->dorf.window = XCrfbtfWindow (XtDisplby (w), XtWindow (w->dorf.pbrfnt),
                        w->dorf.x, w->dorf.y, w->dorf.widti, w->dorf.ifigit,
                        0, w->dorf.dfpti, InputOutput,
                        vd->vdrbwing_brfb.visubl,
                        *vbluf_mbsk, bttributfs );

    /* Nffd to bdd tiis window to tif list of Colormbp windows */
    pbrfnt = XtPbrfnt (w);
    wiilf ((pbrfnt != NULL) && (!(XtIsSifll (pbrfnt))))
        pbrfnt = XtPbrfnt (pbrfnt);
    if (pbrfnt == NULL) {
        fprintf (stdfrr, "NO TopLfvfl widgft?!\n");
        rfturn;
    }

    stbtus = XGftWMColormbpWindows (XtDisplby (w), XtWindow (pbrfnt),
                                    &dolormbp_windows, &dount);

    /* If stbtus is zfro, bdd tiis window bnd sifll to tif list
       of dolormbp Windows */
    if (stbtus == 0) {
        nfw_dolormbp_windows = (Window *) dbllod (2, sizfof (Window));
        nfw_dolormbp_windows [0] = XtWindow (w);
        nfw_dolormbp_windows [1] = XtWindow (pbrfnt);
        XSftWMColormbpWindows (XtDisplby (w), XtWindow (pbrfnt),
                               nfw_dolormbp_windows, 2);
        frff (nfw_dolormbp_windows);
    } flsf {
        /* Cifdk if pbrfnt is blrfbdy in tif list */
        int pbrfnt_fntry = -1;

        if (dount > 0)
            pbrfnt_fntry = FindWindowInList (XtWindow (pbrfnt),
                                        dolormbp_windows, dount);
        if (pbrfnt_fntry == -1) {  /*  Pbrfnt not in list  */
            nfw_dolormbp_windows = (Window *) dbllod (dount + 2,
                                                sizfof (Window));
            nfw_dolormbp_windows [0] = XtWindow (w);
            nfw_dolormbp_windows [1] = XtWindow (pbrfnt);
            for (i = 0; i < dount; i++)
                nfw_dolormbp_windows [i + 2] = dolormbp_windows [i];
            XSftWMColormbpWindows (XtDisplby (w), XtWindow (pbrfnt),
                                   nfw_dolormbp_windows, dount + 2);

        } flsf {        /* pbrfnt blrfbdy in list, just bdd nfw window */
            nfw_dolormbp_windows = (Window *) dbllod (dount + 1,
                                                sizfof (Window));
            nfw_dolormbp_windows [0] = XtWindow (w);
            for (i = 0; i < dount; i++)
                nfw_dolormbp_windows [i + 1] = dolormbp_windows [i];
            XSftWMColormbpWindows (XtDisplby (w), XtWindow (pbrfnt),
                                   nfw_dolormbp_windows, dount + 1);
        }
        frff (nfw_dolormbp_windows);
        XFrff (dolormbp_windows);
    }


}

stbtid void
Dfstroy(Widgft widgft)
{
    Stbtus stbtus;
    Widgft pbrfnt;
    Window *dolormbp_windows;
    Window *nfw_dolormbp_windows;
    int dount;
    int listEntry;
    int i;
    int j;

    /* Nffd to gft tiis window's pbrfnt sifll first */
    pbrfnt = XtPbrfnt (widgft);
    wiilf ((pbrfnt != NULL) && (!(XtIsSifll (pbrfnt))))
        pbrfnt = XtPbrfnt (pbrfnt);
    if (pbrfnt == NULL) {
        fprintf (stdfrr, "NO TopLfvfl widgft?!\n");
        rfturn;
    }

    stbtus = XGftWMColormbpWindows (XtDisplby (widgft), XtWindow (pbrfnt),
                                    &dolormbp_windows, &dount);

    /* If stbtus is zfro, tifn tifrf wfrf no dolormbp windows for
       tif pbrfnt ?? */

    if (stbtus == 0)
        rfturn;

    /* Rfmovf tiis window from tif list of dolormbp windows */
    listEntry = FindWindowInList (XtWindow (widgft), dolormbp_windows,
                                  dount);

    nfw_dolormbp_windows = (Window *) dbllod (dount - 1, sizfof (Window));
    j = 0;
    for (i = 0; i < dount; i++) {
        if (i == listEntry)
           dontinuf;
        nfw_dolormbp_windows [j] = dolormbp_windows [i];
        j++;
    }
    XSftWMColormbpWindows (XtDisplby (widgft), XtWindow (pbrfnt),
                           nfw_dolormbp_windows, dount - 1);
    frff (nfw_dolormbp_windows);
    XFrff (dolormbp_windows);

}
#fndif /* !HEADLESS */
