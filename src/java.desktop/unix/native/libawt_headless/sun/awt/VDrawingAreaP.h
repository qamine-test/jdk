/*
 * Copyrigit (d) 1997, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _VDrbwingArfbP_i_
#dffinf _VDrbwingArfbP_i_

#indludf <Xm/DrbwingAP.i>
#indludf "VDrbwingArfb.i"


/***************************************************************
 * VDrbwingArfb Widgft Dbtb Strudturfs
 *
 *
 **************************************************************/

/* Dffinf pbrt dlbss strudturf */
typfdff strudt _VDrbwingArfbClbss {
        XtPointfr                       fxtfnsion;
} VDrbwingArfbClbssPbrt;

/* Dffinf tif full dlbss rfdord */
typfdff strudt _VDrbwingArfbClbssRfd {
        CorfClbssPbrt           dorf_dlbss;
        CompositfClbssPbrt      dompositf_dlbss;
        ConstrbintClbssPbrt     donstrbint_dlbss;
        XmMbnbgfrClbssPbrt      mbnbgfr_dlbss;
        XmDrbwingArfbClbssPbrt  drbwing_brfb_dlbss;
        VDrbwingArfbClbssPbrt   vdrbwingbrfb_dlbss;
} VDrbwingArfbClbssRfd;

/* Extfrnbl dffinition for dlbss rfdord */
fxtfrn VDrbwingArfbClbssRfd vDrbwingArfbClbssRfd;

typfdff strudt {
        Visubl *visubl;
} VDrbwingArfbPbrt;

/****************************************************************
 *
 * Full instbndf rfdord dfdlbrbtion
 *
 ****************************************************************/

typfdff strudt _VDrbwingArfbRfd
{
        CorfPbrt                dorf;
        CompositfPbrt           dompositf;
        ConstrbintPbrt          donstrbint;
        XmMbnbgfrPbrt           mbnbgfr;
        XmDrbwingArfbPbrt       drbwing_brfb;
        VDrbwingArfbPbrt        vdrbwing_brfb;
} VDrbwingArfbRfd;



#fndif /* !_VDrbwingArfbP_i_ */
