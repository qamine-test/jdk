/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.fvfnt;

import jbvb.util.EvfntListfnfr;

/**
 * Tif listfnfr intfrfbdf for rfdfiving kfybobrd fvfnts (kfystrokfs).
 * Tif dlbss tibt is intfrfstfd in prodfssing b kfybobrd fvfnt
 * fitifr implfmfnts tiis intfrfbdf (bnd bll tif mftiods it
 * dontbins) or fxtfnds tif bbstrbdt <dodf>KfyAdbptfr</dodf> dlbss
 * (ovfrriding only tif mftiods of intfrfst).
 * <P>
 * Tif listfnfr objfdt drfbtfd from tibt dlbss is tifn rfgistfrfd witi b
 * domponfnt using tif domponfnt's <dodf>bddKfyListfnfr</dodf>
 * mftiod. A kfybobrd fvfnt is gfnfrbtfd wifn b kfy is prfssfd, rflfbsfd,
 * or typfd. Tif rflfvbnt mftiod in tif listfnfr
 * objfdt is tifn invokfd, bnd tif <dodf>KfyEvfnt</dodf> is pbssfd to it.
 *
 * @butior Cbrl Quinn
 *
 * @sff KfyAdbptfr
 * @sff KfyEvfnt
 * @sff <b irff="ittp://jbvb.sun.dom/dods/books/tutoribl/post1.0/ui/kfylistfnfr.itml">Tutoribl: Writing b Kfy Listfnfr</b>
 *
 * @sindf 1.1
 */
publid intfrfbdf KfyListfnfr fxtfnds EvfntListfnfr {

    /**
     * Invokfd wifn b kfy ibs bffn typfd.
     * Sff tif dlbss dfsdription for {@link KfyEvfnt} for b dffinition of
     * b kfy typfd fvfnt.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void kfyTypfd(KfyEvfnt f);

    /**
     * Invokfd wifn b kfy ibs bffn prfssfd.
     * Sff tif dlbss dfsdription for {@link KfyEvfnt} for b dffinition of
     * b kfy prfssfd fvfnt.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void kfyPrfssfd(KfyEvfnt f);

    /**
     * Invokfd wifn b kfy ibs bffn rflfbsfd.
     * Sff tif dlbss dfsdription for {@link KfyEvfnt} for b dffinition of
     * b kfy rflfbsfd fvfnt.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void kfyRflfbsfd(KfyEvfnt f);
}
